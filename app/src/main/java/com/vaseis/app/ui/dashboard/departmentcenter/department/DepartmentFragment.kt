package com.vaseis.app.ui.dashboard.departmentcenter.department

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.R
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentDepartmentLayoutBinding
import com.vaseis.app.ui.dashboard.departmentcenter.department.adapters.DepartmentAdapter
import com.vaseis.app.ui.dashboard.departmentcenter.department.adapters.DepartmentCountAdapter
import com.vaseis.app.utils.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DepartmentFragment : BaseFragment<FragmentDepartmentLayoutBinding>() {
    private val viewModel: DepartmentViewModel by viewModels()

    private val totalAdapter : DepartmentCountAdapter by lazy {DepartmentCountAdapter()}
    private val departmentAdapter: DepartmentAdapter by lazy { DepartmentAdapter(listener) }
    private val concatAdapter : ConcatAdapter by lazy { ConcatAdapter(totalAdapter, departmentAdapter) }

    override fun getViewBinding(): FragmentDepartmentLayoutBinding = FragmentDepartmentLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        //testing
        //val action = DepartmentFragmentDirections.actionDepartmentFragmentToDepartmentDetailsFragment(1555   , "")
        //findNavController().navigate(action)

        //findNavController().addOnDestinationChangedListener { controller, destination, arguments -> Log.v("MPIKA", "DEPARTMENTFRAGMENT GAMW") }
    }

    private fun setupObservers() {
        with(viewModel) {
            departmentsFiltered.observe(viewLifecycleOwner, { list ->
                departmentAdapter.submitList(list)
                totalAdapter.submitList(listOf(list.size.toString()))

                if(departmentsFiltered.value?.size == departments.value?.size && binding.searchbar.query.isNotBlank() && departmentsFiltered.value !=null) {
                    filterList(binding.searchbar.query.toString())
                    return@observe
                }

                if (list.isNullOrEmpty()) {
                    binding.errorMessage.text = "Δε βρέθηκε κανένα αποτέλεσμα"
                }

                binding.recyclerView.scrollToPosition(0)

                binding.progressIndicator.isVisible = false
                binding.errorImageView.isVisible = list.isNullOrEmpty()
                binding.errorMessage.isVisible = list.isNullOrEmpty()
            })

            showDepartmentDetailsUI.observe(viewLifecycleOwner, {
                //val action = DepartmentFragmentDirections.actionDepartmentFragmentToDepartmentDetailsFragment()
                //findNavController().navigate(action)
            })

            if (departments.value.isNullOrEmpty()){
                loadDepartments()
                binding.searchbar.setQuery("", false)
            }
        }
    }

    private fun setupViews() {
        with(binding) {
            recyclerView.adapter = concatAdapter

            filterFab.setOnClickListener {
                findNavController().safeNavigate(DepartmentFragmentDirections.actionDepartmentFragmentToBaseFilterNavGraph(), R.id.departmentFragment)
            }

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        filterFab.shrink()

                        test(true)

                        // Scrolling up
                        //(activity as? AppActivity)?.test(false)
                        /* binding.searchTextView.isVisible = false
                     binding.searchTextField.isVisible = false*/
                    } else {
                        filterFab.extend()

                        test(false)
                        // Scrolling down
                        //(activity as? AppActivity)?.test(true)
                        /*binding.searchTextView.isVisible = true
                        binding.searchTextField.isVisible = true*/
                    }
                }
            })
        }

        setupSearchTextView()
    }

    private fun setupSearchTextView() {
        with(binding) {
            searchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    activity?.hideSoftKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.filterList(newText ?: "")
                    return true
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.contextual_comparison_menu, menu)


    }

    fun test(show: Boolean) {
        /*   val transition: Transition = Slide(Gravity.TOP)
           transition.duration = 1000
           transition.addTarget(binding.searchTextField)
           transition.addTarget(binding.searchTextView)

           TransitionManager.beginDelayedTransition(binding.root, transition)
           binding.searchTextField.isVisible = show
           binding.searchTextView.isVisible = show*/
    }

    val listener = object : DepartmentAdapter.DepartmentClickListener {

        override fun onItemClickListener(code: String, name: String) {
            val action = DepartmentFragmentDirections.actionDepartmentToSingleDepartmentDetails(code, name)
            findNavController().safeNavigate(action, R.id.departmentFragment)
        }

        override fun onCodeClickListener(position: Int) {
            viewModel.setDepartmentsIsSelected(position)
            departmentAdapter.currentList[position].isSelected = !departmentAdapter.currentList[position].isSelected
            departmentAdapter.currentList[position].isNowSelected = true
            departmentAdapter.notifyItemChanged(position)
        }

        override fun onItemLongClickListener(position: Int): Boolean {
            viewModel.setDepartmentsIsSelected(position)
            departmentAdapter.currentList[position].isSelected = !departmentAdapter.currentList[position].isSelected
            departmentAdapter.currentList[position].isNowSelected = true
            departmentAdapter.notifyItemChanged(position)
            return true
        }
    }

    companion object {
        val propertiesList = listOf(
            listOf(-1)
        )
    }
}