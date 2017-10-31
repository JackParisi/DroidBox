package com.github.jackparisi.droidboxsample.activity.game

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.jackparisi.droidbox.delegate.viewModelProvider
import com.github.jackparisi.droidbox.recycler.DroidAdapter
import com.github.jackparisi.droidboxsample.core.DroidBoxSampleActivity
import com.github.jackparisi.droidboxsample.core.component
import com.github.jackparisi.droidboxsample.database.games.Game
import com.github.jackparisi.droidboxsample.database.games.GameSection
import com.github.jackparisi.droidboxsample.databinding.ActivityGamesBinding
import com.github.jackparisi.droidboxsample.databinding.SectionGameBinding

/**
 * Created by Giacomo Parisi on 31/10/17.
 * https://github.com/JackParisi
 */

class GamesActivity : DroidBoxSampleActivity() {

    private val viewModel by viewModelProvider { component.gameViewModel() }
    private lateinit var binding: ActivityGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGamesBinding.inflate(layoutInflater)
        viewModel.droidUiActions.observe(this) { it(this) }
        viewModel.loadData()

        setContentView(binding.root)
    }

    fun createList(list: List<Game>) {

        val adapter = DroidAdapter(list, layoutInflater, viewModel)
        binding.sectionRecyclerView.initializeSections(
                GameSection(SectionGameBinding.inflate(layoutInflater)),
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
                list,
                adapter
        )
    }
}