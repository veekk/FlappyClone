package com.veek.wjuh.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.math.Vector2
import com.veek.wjuh.Wjuh
import com.veek.wjuh.sprites.Bird
import com.veek.wjuh.sprites.Tube

/**
 * Created by veek on 27.03.17.
 */

class PlayState : State() {

    companion object {
        const val TUBE_SPACING = 135f
        const val TUBE_COUNT = 4
        const val GROUND_Y_OFFSET = -60f
        var GamePreferences = Gdx.app.getPreferences("Wjuh")
    }

    private val generator : FreeTypeFontGenerator = FreeTypeFontGenerator(Gdx.files.internal("font.ttf"))
    private val parameter : FreeTypeFontGenerator.FreeTypeFontParameter  = FreeTypeFontGenerator.FreeTypeFontParameter()
    private var font : BitmapFont

    private var score = -1

    private var bird: Bird
    private var tube: Tube

    private var bg: Texture = Texture("bg.png")
    private var ground: Texture = Texture("ground.png")

    private var groundPos1: Vector2
    private var groundPos2: Vector2

    private var tubes: Array<Tube> = Array(TUBE_COUNT, { Tube(it * (TUBE_SPACING + Tube.TUBE_WIDTH)) })

    init {

      //  parameter.size = 12
        parameter.color = Color.DARK_GRAY
        font = generator.generateFont(parameter)
        font.setUseIntegerPositions(false)

        groundPos1 = Vector2(cam.position.x - cam.viewportWidth, GROUND_Y_OFFSET)
        groundPos2 = Vector2(cam.position.x - cam.viewportWidth + ground.width, GROUND_Y_OFFSET)

        bird = Bird(70f, 200f)
        tube = Tube(300f)
        cam.setToOrtho(false, (Wjuh.WIDTH / 2).toFloat(), (Wjuh.HEIGHT / 2).toFloat())


    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump()
        }
    }

    override fun update(dt: Float) {
        updateGround()
        bird.update(dt)
        cam.position.x = bird.position.x
        tubes.forEach {
            if ((cam.position.x - cam.viewportWidth / 2) > it.posTopTube.x + it.topTube.width) {
                it.reposition(it.posTopTube.x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT)
            }

            if (it.collides(bird.bounds)) {
                putHighScore(score)
                GameStateManager.set(MenuState())
            }

            if (it.posBottomTube.x < bird.position.x && !it.counted) {
                it.counted = true
                score ++
                putHighScore(score)
            }
        }
        if (bird.position.y <= ground.height + GROUND_Y_OFFSET) {
            putHighScore(score)
            GameStateManager.set(MenuState())
        }

        super.update(dt)

    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.draw(bg, cam.position.x - cam.viewportWidth / 2, 0f, (Wjuh.WIDTH / 2).toFloat(), (Wjuh.HEIGHT / 2).toFloat())
        sb.draw(bird.getTexture(), bird.position.x, bird.position.y, bird.bounds.width, bird.bounds.height)
        tubes.forEach {
            sb.draw(it.topTube, it.posTopTube.x, it.posTopTube.y)
            sb.draw(it.bottomTube, it.posBottomTube.x, it.posBottomTube.y)
        }
        font.draw(sb, "$score", cam.position.x, cam.viewportHeight - 20)
        sb.draw(ground, groundPos1.x, groundPos1.y)
        sb.draw(ground, groundPos2.x, groundPos2.y)
        font.draw(sb, "high: ${GamePreferences.getInteger("highScore", 0)}", cam.position.x - cam.viewportWidth  / 2 + 20, 20f)
        cam.update()

    }

    override fun dispose() {
        bg.dispose()
        bird.dispose()
        tubes.forEach {
            it.topTube.dispose()
            it.bottomTube.dispose()
        }
        generator.dispose()
        System.out.println("PlayState disposed")
    }

    private fun updateGround() {
        if (cam.position.x - cam.viewportWidth / 2 > groundPos1.x + ground.width) {
            groundPos1.add((ground.width * 2).toFloat(), 0f)
        }
        if (cam.position.x - cam.viewportWidth / 2 > groundPos2.x + ground.width) {
            groundPos2.add((ground.width * 2).toFloat(), 0f)
        }
    }

    fun putHighScore(highScore : Int){
        if (highScore > getHighScore()) {
            GamePreferences.putInteger("highScore", score)
            GamePreferences.flush()
        }
    }

    fun getHighScore() : Int {
        return GamePreferences.getInteger("highScore", 0)
    }

}