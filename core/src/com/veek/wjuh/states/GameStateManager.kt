package com.veek.wjuh.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*

object GameStateManager {

    private var states : Stack<State> = Stack()

    fun set(state : State){
        states.takeIf { !it.empty() }?.pop()?.dispose()
        states.push(state)
    }

    fun update(dt : Float) {
        states.peek().handleInput()
        states.peek().update(dt)
    }

    fun render(sb : SpriteBatch){
        sb.begin()
        states.peek().render(sb)
        sb.end()
    }
}
