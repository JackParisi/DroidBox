package com.github.jackparisi.droidboxsample.activity.steam.game

import android.databinding.Observable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.jackparisi.droidbox.delegate.viewModelProvider
import com.github.jackparisi.droidbox.recycler.DroidAdapter
import com.github.jackparisi.droidbox.wrapper.DroidWrapperSettings
import com.github.jackparisi.droidbox.wrapper.DroidWrapperView
import com.github.jackparisi.droidboxsample.core.DroidBoxSampleActivity
import com.github.jackparisi.droidboxsample.core.DroidBoxSampleToolbarConfigurator
import com.github.jackparisi.droidboxsample.core.component
import com.github.jackparisi.droidboxsample.database.games.GameSection
import com.github.jackparisi.droidboxsample.databinding.ActivitySteamGamesBinding
import com.github.jackparisi.droidboxsample.databinding.LoadingBinding
import com.github.jackparisi.droidboxsample.databinding.SectionGameBinding
import com.github.jackparisi.droidboxsample.databinding.ToolbarBinding

/**
 * Created by Giacomo Parisi on 25/10/17.
 * https://github.com/JackParisi
 */

class SteamGamesActivity : DroidBoxSampleActivity() {

    private lateinit var binding: ActivitySteamGamesBinding

    private val viewModel by viewModelProvider { component.homeViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySteamGamesBinding.inflate(layoutInflater)

        var wrappedView = wrapper.wrapToolbarLayout(
                viewModel,
                DroidWrapperView(
                        binding.root,
                        DroidWrapperSettings.getMatchParentParams()
                ),
                DroidBoxSampleToolbarConfigurator(),
                ToolbarBinding::class,
                true
        )

        wrappedView = wrapper.wrapLoadingLayout(
                viewModel,
                DroidWrapperView(
                        wrappedView,
                        DroidWrapperSettings.getMatchParentParams()
                ),
                LoadingBinding::class
        )
        setContentView(wrappedView)

        viewModel.loadData()
        viewModel.games.setCallback(object : Observable.OnPropertyChangedCallback() {

            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                createList()
            }
        })
    }

    private fun createList() {

        val adapter = DroidAdapter(viewModel.games.data.get(), layoutInflater, viewModel)
        binding.sectionRecyclerView.initializeSections(
                GameSection(SectionGameBinding.inflate(layoutInflater)),
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
                viewModel.games.data.get(),
                adapter
        )
    }
}
