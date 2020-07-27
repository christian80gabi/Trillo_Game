package christian80gabi.games.trillo

import java.awt.Component
import java.awt.Graphics
import java.awt.Image
import java.awt.Rectangle
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class Block(x: Int, y: Int, width: Int, height: Int, imagePath: String) : Rectangle() {
    var picture: Image? = null
    var destroyed = false
    var moveX: Int
    var moveY: Int
    fun draw(graphic: Graphics, component: Component?) {
        if (!destroyed) {
            graphic.drawImage(picture, x, y, width, height, component)
        }
    }

    init {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
        try {
            picture = ImageIO.read(File("src/christian80gabi/games/trillo/resources/img/$imagePath"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        moveX = 3
        moveY = 3
    }
}