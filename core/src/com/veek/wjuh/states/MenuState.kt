package com.veek.wjuh.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.math.Matrix4
import com.veek.wjuh.Wjuh

/**
 * Created by veek on 27.03.17.
 */

class MenuState : State() {

    companion object {
        var GamePreferences = Gdx.app.getPreferences("Wjuh")
    }

    private var background: Texture = Texture("bg.png")
    private var playBtn: Texture = Texture("playbtn.png")

    private val generator : FreeTypeFontGenerator = FreeTypeFontGenerator(Gdx.files.internal("font.ttf"))
    private val parameter : FreeTypeFontGenerator.FreeTypeFontParameter  = FreeTypeFontGenerator.FreeTypeFontParameter()
    private var font : BitmapFont

    init {
        parameter.color = Color.DARK_GRAY
        font = generator.generateFont(parameter)
        font.setUseIntegerPositions(false)

        cam.setToOrtho(false, (Wjuh.WIDTH / 2).toFloat(), (Wjuh.HEIGHT / 2).toFloat())
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            GameStateManager.set(PlayState())
        }
    }


    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.draw(background, cam.position.x - cam.viewportWidth / 2, 0f, (Wjuh.WIDTH / 2).toFloat(), (Wjuh.HEIGHT / 2).toFloat())
        sb.draw(playBtn, cam.position.x - playBtn.width / 2, cam.position.y)

        font.draw(sb, "high: ${GamePreferences.getInteger("highScore", 0)}", cam.position.x - cam.viewportWidth  / 2 + 20, 20f)

    }

    override fun dispose() {
        generator.dispose()
        background.dispose()
        playBtn.dispose()
        System.out.println("MenuState disposed")
    }

}