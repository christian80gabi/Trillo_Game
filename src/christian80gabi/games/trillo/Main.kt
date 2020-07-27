package christian80gabi.games.trillo

import javax.swing.JButton
import javax.swing.JFrame

object Main {
    fun main(args: Array<String>) {
        val frame = JFrame("Trillo")
        val startScreen = JFrame()
        val start = JButton("Start")
        val panel = BlockBreakerPanel(frame, startScreen)
        start.addActionListener {
            startScreen.isVisible = false
            frame.isVisible = true
        }
        frame.contentPane.add(panel)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = false
        frame.setSize(490, 600)
        frame.isResizable = false
        startScreen.contentPane.add(start)
        startScreen.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        startScreen.isVisible = true
        startScreen.setSize(490, 600)
        startScreen.isResizable = false
    }
}