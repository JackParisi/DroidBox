package com.github.jackparisi.droidboxsample.activity.game

import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import com.github.jackparisi.droidboxsample.R
import com.github.jackparisi.droidboxsample.core.DroidBoxSampleApplication
import com.github.jackparisi.droidboxsample.database.games.Game
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 31/10/17.
 * https://github.com/JackParisi
 */

class GamesViewModel @Inject constructor(application: DroidBoxSampleApplication) : DroidViewModel(application) {

    val gamesList = mutableListOf<Game>()

    fun loadData() {
        gamesList.clear()
        gamesList.add(Game(
                OVERWATCH,
                getApplication<DroidBoxSampleApplication>().getString(R.string.GAME_Overwatch),
                R.drawable.overwatch_banner
        ))

        droidUiActions {
            if (it is GamesActivity) {
                it.createList(gamesList)
            }
        }
    }


    companion object {
        val OVERWATCH = 0
    }
}