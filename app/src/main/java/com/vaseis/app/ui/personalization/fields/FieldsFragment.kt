package com.vaseis.app.ui.personalization.fields

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentGroupBinding
import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty
import com.vaseis.app.ui.personalization.fields.adapter.FieldsAdapter
import com.vaseis.app.utils.setTopMarginForStatusBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FieldsFragment : BaseFragment<FragmentGroupBinding>() {

    private val viewModel: FieldsViewModel by viewModels()
    private val adapter: FieldsAdapter by lazy { FieldsAdapter() }

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
                val properties = mutableListOf<PrefProperty>()
                for (selectedFields in adapter.currentList.filter { item -> item.isSelected }) {
                    properties.add(PrefProperty(selectedFields.field.key, selectedFields.field.shortName))
                }
                viewModel.savedData(properties)

                isEnabled = false
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setupViews() {
        with(binding) {
            root.setTopMarginForStatusBar()

            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }

            groupRecyclerView.adapter = adapter

            /* proceedButton.setOnClickListener {
                 adapter.currentList.firstOrNull { item -> item.isSelected }?.calculatorGroup?.let { group ->
                     viewModel.savedData(
                         PrefProperty(
                             group.id,
                             group.shortName
                         )
                     )
                 }
             }*/
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            fieldsUI.observe(viewLifecycleOwner, { list ->

                adapter.submitList(list)
            })

            savedUI.observe(viewLifecycleOwner, {
            })

            loadData()
        }
    }
}