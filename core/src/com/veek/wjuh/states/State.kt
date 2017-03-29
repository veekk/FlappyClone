package com.veek.wjuh.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.veek.wjuh.Wjuh

/**
 * Created by veek on 27.03.17.
 */

abstract class State {
    protected val cam : OrthographicCamera = OrthographicCamera()
    protected val mouse : Vector3 = Vector3()

    init {
        cam.setToOrtho(false, (Wjuh.WIDTH).toFloat(), (Wjuh.HEIGHT).toFloat())
    }

    abstract fun handleInput()
    open fun update(dt : Float){
        cam.update()
    }
    abstract fun render(sb : SpriteBatch)
    abstract fun dispose()


}

