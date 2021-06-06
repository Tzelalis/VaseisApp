package com.example.vaseisapp.ui.personalization.group

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                adapter.currentList.firstOrNull { item -> item.isSelected }?.calculatorGroup?.let { group ->
                    viewModel.savedData(PrefProperty(group.id, group.shortName))
                }

                isEnabled = false
                activity?.onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setupViews() {
        with(binding) {
            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }

            groupRecyclerView.adapter = adapter

            proceedButton.setOnClickListener {
                adapter.currentList.firstOrNull { item -> item.isSelected }?.calculatorGroup?.let { group ->
                    viewModel.savedData(
                        PrefProperty(
                            group.id,
                            group.shortName
                        )
                    )
                }
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            groupsUI.observe(viewLifecycleOwner, { list ->
                adapter.submitList(list)
            })

            savedUI.observe(viewLifecycleOwner, {
            })

            loadData()
        }
    }
}