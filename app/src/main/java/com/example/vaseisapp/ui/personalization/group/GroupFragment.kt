package com.example.vaseisapp.ui.personalization.group

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentGroupBinding
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupFragment : BaseFragment<FragmentGroupBinding>() {

    private val viewModel: GroupViewModel by viewModels()

    private val adapter: GroupTypeListAdapter by lazy { GroupTypeListAdapter() }

    override fun getViewBinding(): FragmentGroupBinding = FragmentGroupBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }

            groupRecyclerView.adapter = adapter

            proceedButton.setOnClickListener {
                adapter.currentList.firstOrNull { item -> item.isSelected }?.group?.let { group -> viewModel.savedData(PrefProperty(group.id, group.shortName)) }
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            groupsUI.observe(viewLifecycleOwner, { list ->
                adapter.submitList(list)
            })

            savedUI.observe(viewLifecycleOwner, {
                activity?.onBackPressed()
            })

            loadData()
        }
    }
}