package com.github.jackparisi.droidboxsample.activity

import android.databinding.Observable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.github.jackparisi.droidbox.delegate.viewModelProvider
import com.github.jackparisi.droidbox.recycler.DroidAdapter
import com.github.jackparisi.droidbox.wrapper.DroidWrapperView
import com.github.jackparisi.droidboxsample.core.DroidBoxSampleActivity
import com.github.jackparisi.droidboxsample.core.DroidBoxSampleToolbarConfigurator
import com.github.jackparisi.droidboxsample.core.component
import com.github.jackparisi.droidboxsample.databinding.ActivityHomeBinding
import com.github.jackparisi.droidboxsample.databinding.ToolbarBinding

class HomeActivity : DroidBoxSampleActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel by viewModelProvider { component.homeViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        val wrappedView = wrapper.wrapToolbarLayout(
                viewModel,
                DroidWrapperView(
                        binding.root,
                        ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT
                        )
                ),
                DroidBoxSampleToolbarConfigurator(),
                ToolbarBinding::class,
                true
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
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}
