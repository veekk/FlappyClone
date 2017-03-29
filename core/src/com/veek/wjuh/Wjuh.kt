package com.veek.wjuh

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.veek.wjuh.states.GameStateManager
import com.veek.wjuh.states.MenuState

class Wjuh : ApplicationAdapter() {


    companion object{
        var WIDTH = 480
        var HEIGHT = 800
        var TITLE = "WJUH I NET STOLA"
    }



    lateinit var batch: SpriteBatch
    lateinit var img: Texture

    lateinit var music : Music



    override fun create() {
        batch = SpriteBatch()
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"))
        music.isLooping = true
        music.volume = 0.1f
        music.play()
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.6f, 1f)
        GameStateManager.set(MenuState())
        img = Texture("badlogic.jpg")

    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        GameStateManager.update(Gdx.graphics.deltaTime)
        GameStateManager.render(batch)
    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
        music.dispose()
    }
}
