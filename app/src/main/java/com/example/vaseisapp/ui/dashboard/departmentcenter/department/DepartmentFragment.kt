package com.example.vaseisapp.ui.dashboard.departmentcenter.department

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentDepartmentLayoutBinding
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentDetailsArguments
import com.example.vaseisapp.utils.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class DepartmentFragment : BaseFragment<FragmentDepartmentLayoutBinding>() {
    private val viewModel: DepartmentViewModel by viewModels()
    private lateinit var adapter: DepartmentAdapter

    override fun getViewBinding(): FragmentDepartmentLayoutBinding = FragmentDepartmentLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        //testing
        //val action = DepartmentFragmentDirections.actionDepartmentFragmentToDepartmentDetailsFragment(1555   , "")
        //findNavController().navigate(action)

        //findNavController().addOnDestinationChangedListener { controller, destination, arguments -> Log.v("MPIKA", "DEPARTMENTFRAGMENT GAMW") }
    }

    private fun setupObservers() {
        with(viewModel) {
            departments.observe(viewLifecycleOwner, {
                setupRecyclerView(it)
                setupSearchTextView(it)
            })

            showDepartmentDetailsUI.observe(viewLifecycleOwner, {
                //val action = DepartmentFragmentDirections.actionDepartmentFragmentToDepartmentDetailsFragment()
                //findNavController().navigate(action)
            })

            loadDepartments()
        }
    }


    private fun setupRecyclerView(list: List<DepartmentWithSelected>) {
        val listener = object : DepartmentAdapter.DepartmentClickListener {

            override fun onItemClickListener(position: Int) {
                val array = arrayOf(
                    DepartmentDetailsArguments("101", " TEst 1"),
                    DepartmentDetailsArguments("106", " TEst 2"),
                    DepartmentDetailsArguments("1010", " TEst 3")
                )
                val action = DepartmentFragmentDirections.actionDepartmentToDepartmentDetails(array)
                //adapter.currentList[position].code, adapter.currentList[position].name
                findNavController().navigate(action)

                //viewModel.showDepartmentDetailsUI(100)
            }

            override fun onCodeClickListener(position: Int) {
                viewModel.setDepartmentsIsSelected(position)
                list[position].isNowSelected = true
                adapter.notifyItemChanged(position)
            }

            override fun onItemLongClickListener(position: Int): Boolean {
                viewModel.setDepartmentsIsSelected(position)
                list[position].isNowSelected = true
                adapter.notifyItemChanged(position)
                return true
            }

        }

        adapter = DepartmentAdapter(listener, requireContext())
        adapter.submitList(list)

        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL

        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = manager

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy > 0) {
                        test(true)

                        // Scrolling up
                        //(activity as? AppActivity)?.test(false)
                        /* binding.searchTextView.isVisible = false
                     binding.searchTextField.isVisible = false*/
                    } else {
                        test(false)
                        // Scrolling down
                        //(activity as? AppActivity)?.test(true)
                        /*binding.searchTextView.isVisible = true
                        binding.searchTextField.isVisible = true*/
                    }
                }
            })
        }
    }

    private fun setupSearchTextView(list: List<DepartmentWithSelected>) {
        with(binding) {
            searchTextView.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        activity?.hideSoftKeyboard()
                        return true;
                    }
                    return false;
                }
            })

            searchTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val filteredList = mutableListOf<DepartmentWithSelected>()

                    if (!p0.isNullOrEmpty()) {
                        adapter.notifyItemRangeRemoved(0, adapter.currentList.size - 1)
                        for (item in list) {
                            if (item.name.toUpperCase(Locale.getDefault()).contains(p0.toString().toUpperCase(Locale.getDefault()))
                                || item.code.toString().contains(p0.toString())
                            ) {
                                filteredList.add(item)
                            }
                        }
                        adapter.submitList(filteredList)

                        if (filteredList.isEmpty()) {
                            errorMessage.text = "Δε βρέθηκε κανένα αποτέλεσμα για \n \"$p0\""
                        }

                        errorImageView.isVisible = filteredList.isEmpty()
                        errorMessage.isVisible = filteredList.isEmpty()

                    } else {
                        adapter.submitList(list)
                        errorImageView.isVisible = false
                        errorMessage.isVisible = false
                    }


                }

                override fun afterTextChanged(p0: Editable?) {
                    adapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.select_multiple_departments_menu, menu)


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

    companion object{
        val propertiesList = listOf(
            listOf(-1, )
        )
    }

}