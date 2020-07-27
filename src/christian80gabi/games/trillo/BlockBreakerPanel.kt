package christian80gabi.games.trillo

import christian80gabi.games.trillo.Block
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.*
import java.util.function.Consumer
import javax.swing.JFrame
import javax.swing.JPanel

class BlockBreakerPanel(var mainFrame: JFrame, var startScreen: JFrame) : JPanel(), KeyListener {
    var blocks: ArrayList<Block>? = null
    var ball: Block? = null
    var paddle: Block? = null
    var thread: Thread?
    public override fun paintComponent(graphic: Graphics) {
        blocks!!.forEach(Consumer { block: Block -> block.draw(graphic, this) })
        ball!!.draw(graphic, this)
        paddle!!.draw(graphic, this)
    }

    private fun reset() {
        blocks = ArrayList()
        ball = Block(237, 435, 25, 25, "ball.png")
        paddle = Block(175, 480, 150, 25, "paddle.png")
        for (i in 0..7) {
            blocks!!.add(Block(i * 60, 0, 50, 25, "blue.png"))
        }
        for (i in 0..7) {
            blocks!!.add(Block(i * 60, 25, 50, 25, "green.png"))
        }
        for (i in 0..7) {
            blocks!!.add(Block(i * 60, 50, 50, 25, "yellow.png"))
        }
        for (i in 0..7) {
            blocks!!.add(Block(i * 60, 75, 50, 25, "red.png"))
        }
        addKeyListener(this)
        isFocusable = true
    }

    fun update() {
        ball!!.x += ball!!.moveX

        // Move the ball on the x direction
        if (ball!!.x > width - 25 || ball!!.x < 0) {
            ball!!.moveX *= -1
        }

        // Move the ball on the y direction
        if (ball!!.y < 0 || ball!!.intersects(paddle)) {
            ball!!.moveY *= -1
        }
        ball!!.y += ball!!.moveY
        blocks!!.forEach(Consumer { block: Block ->
            if (ball!!.intersects(block) && !block.destroyed) {
                ball!!.moveY *= -1
                block.destroyed = true
            }
        })

        // When we lose the game
        if (ball!!.y > height) {
            thread = null
            reset()
            mainFrame.isVisible = false
            startScreen.isVisible = true
        }
        repaint()
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for [KeyEvent] for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    override fun keyTyped(e: KeyEvent) {}

    /**
     * Invoked when a key has been pressed.
     * See the class description for [KeyEvent] for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    override fun keyPressed(e: KeyEvent) {


        // Move the paddle to the right
        if (e.keyCode == KeyEvent.VK_RIGHT && paddle!!.x < width - paddle!!.width) {
            paddle!!.x += 15
        }

        // Move the paddle to the left
        if (e.keyCode == KeyEvent.VK_LEFT && paddle!!.x > 0) {
            paddle!!.x -= 15
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for [KeyEvent] for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    override fun keyReleased(e: KeyEvent) {}

    init {
        reset()
        thread = Thread(Runnable {
            while (true) {
                update()
                try {
                    Thread.sleep(10)
                } catch (exception: InterruptedException) {
                    exception.printStackTrace()
                }
            }
        })
        thread!!.start()
    }
}