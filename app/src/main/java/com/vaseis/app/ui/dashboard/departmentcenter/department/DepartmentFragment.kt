package com.vaseis.app.ui.dashboard.departmentcenter.department

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.R
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentDepartmentLayoutBinding
import com.vaseis.app.ui.dashboard.departmentcenter.department.adapters.DepartmentAdapter
import com.vaseis.app.ui.dashboard.departmentcenter.department.adapters.DepartmentCountAdapter
import com.vaseis.app.ui.dashboard.departmentcenter.department.adapters.FiltersChipAdapter
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.DepartmentWithSelected
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.FilterChip
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.FilterChipType
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.OrderType
import com.vaseis.app.ui.main.MainFragmentDirections
import com.vaseis.app.ui.main.MainViewModel
import com.vaseis.app.utils.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class DepartmentFragment : BaseFragment<FragmentDepartmentLayoutBinding>() {
    private val viewModel: DepartmentViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val filtersChipAdapter: FiltersChipAdapter by lazy { FiltersChipAdapter(filtersChipsListener) }
    private val totalAdapter: DepartmentCountAdapter by lazy { DepartmentCountAdapter() }
    private val departmentAdapter: DepartmentAdapter by lazy { DepartmentAdapter(listener) }
    private val concatAdapter: ConcatAdapter by lazy { ConcatAdapter(totalAdapter, departmentAdapter) }

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
                if (departmentsFiltered.value?.size == departments.value?.size && binding.searchbar.query.isNotBlank() && departmentsFiltered.value != null) {
                    filterDepartments(binding.searchbar.query.toString())
                    return@observe
                }

                filterDepartments(binding.searchbar.query.toString())
            })

            showDepartmentDetailsUI.observe(viewLifecycleOwner, {
                //val action = DepartmentFragmentDirections.actionDepartmentFragmentToDepartmentDetailsFragment()
                //findNavController().navigate(action)
            })

            if (departments.value.isNullOrEmpty()) {
                loadDepartments(mainViewModel.filterSavedState.value)
                binding.searchbar.setQuery("", false)
            } else {
                mainViewModel.filterSavedState.value?.let { filterList(it) }
            }
        }

        mainViewModel.filterSavedState.observe(viewLifecycleOwner, { filter ->
            val list = mutableListOf<FilterChip>()
            if (filter.order != OrderType.ALPHABETICAL)
                list.add(FilterChip(filter.order.title, FilterChipType.ORDER))

            if (filter.examType.id != "1")
                list.add(FilterChip(filter.examType.shortName, FilterChipType.EXAM_TYPE))

            if (filter.showDisabledDepartments)
                list.add(FilterChip("Μη ενεργά τμήματα", FilterChipType.DISABLE_DEPARTMENTS))

            if (filter.universities.isNotEmpty())
                list.add(
                    FilterChip(
                        resources.getQuantityString(
                            R.plurals.bases_filter_universities_chip,
                            filter.universities.size,
                            filter.universities.size.toString()
                        ), FilterChipType.UNIVERSITIES
                    )
                )

            for(field in filter.fields){
                list.add(FilterChip(field.shortName, FilterChipType.FIELD, field.key))
            }

            filtersChipAdapter.submitList(list)

            if (list.isEmpty())
                binding.filterChipsRv.visibility = View.GONE
            else
                binding.filterChipsRv.visibility = View.VISIBLE


            viewModel.loadDepartments(filter)
        })
    }

    private fun setupViews() {
        with(binding) {
            recyclerView.adapter = concatAdapter
            filterChipsRv.adapter = filtersChipAdapter

            filterFab.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToBaseFilterNavGraph()
                mainViewModel.navigate(action)
                //findNavController().safeNavigate(DepartmentFragmentDirections.actionDepartmentFragmentToBaseFilterNavGraph(), R.id.departmentFragment)
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

                    val manager = recyclerView.layoutManager as? LinearLayoutManager
                    val firstIndex = manager?.findFirstCompletelyVisibleItemPosition()
                    binding.headerBackground.isVisible = firstIndex != 0
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
                    filterDepartments(newText)
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

    fun filterDepartments(newText: String?): Boolean {
        if (viewModel.departmentsFiltered.value.isNullOrEmpty()) {
            return true
        }

        val list = viewModel.departmentsFiltered.value?.filter { item ->
            item.department.name.toUpperCase(Locale.getDefault()).contains((newText ?: "").toUpperCase(Locale.getDefault()))
                    || item.department.uniTitle.toUpperCase(Locale.getDefault()).contains((newText ?: "").toUpperCase(Locale.getDefault()))
                    || item.department.uniFullTitle.toUpperCase(Locale.getDefault()).contains((newText ?: "").toUpperCase(Locale.getDefault()))
                    || item.department.code.toUpperCase(Locale.getDefault()).contains((newText ?: "").toUpperCase(Locale.getDefault()))
        }

        setDepartmentsToAdapters(list)
        return true
    }

    private fun setDepartmentsToAdapters(list: List<DepartmentWithSelected>?) {
        val departments = list ?: listOf()

        departmentAdapter.submitList(departments)
        totalAdapter.submitList(listOf(departments.size.toString()))

        if (list.isNullOrEmpty()) {
            binding.errorMessage.text = "Δε βρέθηκε κανένα αποτέλεσμα"
        }

        lifecycleScope.launch {
            delay(100)
            binding.recyclerView.scrollToPosition(0)
        }
        binding.progressIndicator.isVisible = false
        binding.errorImageView.isVisible = list.isNullOrEmpty()
        binding.errorMessage.isVisible = list.isNullOrEmpty()
    }

    val listener = object : DepartmentAdapter.DepartmentClickListener {

        override fun onItemClickListener(code: String, name: String) {
            val action = DepartmentFragmentDirections.actionDepartmentToSingleDepartmentDetails(
                code,
                name,
                mainViewModel.filterSavedState.value?.examType?.filter ?: "gel-ime-gen"
            )
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

    private val filtersChipsListener = object : FiltersChipAdapter.FiltersChipListener {
        override fun onFilterChipClickListener(filter: FilterChip, position: Int) {
            mainViewModel.removeFilter(filter)
        }
    }

    companion object {
        val propertiesList = listOf(
            listOf(-1)
        )
    }
}