package com.veek.wjuh.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

/**
 * Created by veek on 27.03.17.
 */

class Bird(x: Float, y: Float) {

    companion object{
        val GRAVITY = -15
        val SPEED = 100
    }

    private val texture = Texture("birdanimation.png")
    private val birdAnimation: Animation = Animation(TextureRegion(texture), 3, 0.5f)
    private val flap : Sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"))



    var position: Vector3 = Vector3(x, y, 0f)
    private var velocity: Vector3 = Vector3(0f, 0f, 0f)

    var bird: Texture = Texture("bird.png")


    val bounds : Rectangle

    init {
        bounds = Rectangle(x, y, bird.width.toFloat(), bird.height.toFloat())
    }

    fun update(dt : Float){
        birdAnimation.update(dt)
        if (position.y > 0f)
            velocity.add(0f, GRAVITY.toFloat(), 0f)
        velocity.scl(dt)
        position.add(SPEED * dt, velocity.y, 0f)
        if (position.y < 0f)
            position.y = 0f

        velocity.scl(1/dt)
        bounds.setPosition(position.x, position.y)
    }

    fun jump(){
        velocity.y = 250f

        flap.play(0.5f)
    }

    fun getTexture(): TextureRegion{
        return birdAnimation.getFrame()
    }

    fun dispose(){
        texture.dispose()
        flap.dispose()
    }


}