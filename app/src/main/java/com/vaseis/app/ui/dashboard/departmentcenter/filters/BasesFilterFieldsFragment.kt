package com.vaseis.app.ui.dashboard.departmentcenter.filters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentBasesFilterFieldsBinding
import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.ui.dashboard.departmentcenter.filters.adapters.BasesFilterFieldsAdapter
import com.vaseis.app.ui.main.MainViewModel
import com.vaseis.app.utils.setTopMarginForStatusBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BasesFilterFieldsFragment : BaseFragment<FragmentBasesFilterFieldsBinding>() {

    private val viewModel: BaseFilterViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val adapter: BasesFilterFieldsAdapter by lazy { BasesFilterFieldsAdapter() }

    override fun getViewBinding(): FragmentBasesFilterFieldsBinding = FragmentBasesFilterFieldsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            root.setTopMarginForStatusBar()
            backButtonImg.backButtonImageView.setOnClickListener { findNavController().navigateUp() }
            fieldsRecyclerView.adapter = adapter
            fieldsRecyclerView.addItemDecoration(DividerItemDecoration(fieldsRecyclerView.context, DividerItemDecoration.VERTICAL))

            confirmBtn.setOnClickListener {
                val fields = mutableListOf<Field>()
                for (field in adapter.currentList.filter { it.isSelected })
                    fields.add(field.field)
                mainViewModel.setFields(fields)

                findNavController().navigateUp()
            }
        }
    }

    private fun setupObservers() {
        viewModel.fieldsItemUI.observe(viewLifecycleOwner, { fields ->
            for (selectedField in mainViewModel.tempFilterSavedState.value?.fields ?: listOf())
                for (field in fields)
                    if (field.field.key == selectedField.key){
                        field.isSelected = true
                        break
                    }

            adapter.submitList(fields)
        })

        viewModel.loadFields()
    }
}