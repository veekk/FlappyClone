package com.veek.wjuh.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
 * Created by veek on 28.03.17.
 */
class Animation(region: TextureRegion, frameCount: Int, cycleTime: Float) {

    private var frames : Array<TextureRegion> = arrayOf()
    private var maxFrameTime : Float
    private var currentFrameTime : Float = 0f
    private var frameCount : Int
    private var frame : Int

    init {
        var frameWidth = region.regionWidth / frameCount
        for (it in 0..frameCount){
            frames += TextureRegion(region, it * frameWidth, 0, frameWidth, region.regionHeight)
        }
        this.frameCount = frameCount
        maxFrameTime = cycleTime / frameCount
        frame = 0
    }

    fun update(dt: Float){
        currentFrameTime += dt
        if (currentFrameTime > maxFrameTime){
            frame++
            currentFrameTime = 0f
        }
        if (frame >= frameCount){
            frame = 0
        }
    }

    fun getFrame(): TextureRegion{
        return frames[frame]
    }

}