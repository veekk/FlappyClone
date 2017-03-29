package com.veek.wjuh.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import java.util.*

/**
 * Created by veek on 27.03.17.
 */
class Tube (x: Float) {

    companion object{
        val TUBE_WIDTH = 52f
        val FLUCTUATION = 130
        val TUBE_GAP = 100f
        val LOWEST_OPENING = 120f

        val rand = Random()
    }

    var counted : Boolean
    val topTube : Texture = Texture("toptube.png")
    val bottomTube : Texture = Texture("bottomtube.png")

    var posTopTube : Vector2
    var posBottomTube : Vector2

    val boundsTop : Rectangle
    val boundsBottom : Rectangle


    init {
        posTopTube = Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING)
        posBottomTube = Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.height)

        counted = false

        boundsTop = Rectangle(posTopTube.x, posTopTube.y, topTube.width.toFloat(), topTube.height.toFloat())
        boundsBottom = Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.width.toFloat(), bottomTube.height.toFloat())
    }

    fun reposition(x: Float){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING)
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.height)

        boundsTop.setPosition(posTopTube.x, posTopTube.y)
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y)
        counted = false
    }

    fun collides(player: Rectangle) : Boolean{
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom)
    }
}