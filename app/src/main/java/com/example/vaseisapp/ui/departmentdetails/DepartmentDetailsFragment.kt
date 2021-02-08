package com.example.vaseisapp.ui.departmentdetails

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentDepartmentDetailsLayoutBinding
import com.example.vaseisapp.databinding.MainToolbarBinding
import com.example.vaseisapp.ui.AppViewModel
import com.example.vaseisapp.utils.centersnap.CenterDecoration
import com.example.vaseisapp.utils.centersnap.CenterSnapHelper
import com.example.vaseisapp.utils.centersnap.SnapOnScrollListener
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


class DepartmentDetailsFragment : BaseFragment<FragmentDepartmentDetailsLayoutBinding>() {

    private val viewModel: DepartmentDetailsViewModel by viewModels()
    private val appViewModel: AppViewModel by activityViewModels()
    private val args: DepartmentDetailsFragmentArgs by navArgs()
    private lateinit var toolbarBinding: MainToolbarBinding

    override fun getViewBinding(): FragmentDepartmentDetailsLayoutBinding = FragmentDepartmentDetailsLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //appViewModel.setToolbarTitle(args.name)
        //(activity as? AppActivity)?.supportActionBar?.show()

        setupToolbar()
        setupObservers()

        findNavController().addOnDestinationChangedListener { controller, destination, arguments -> }
    }

    private fun setupToolbar() {
        (binding.root.findViewById(R.id.toolbar_layout) as? ConstraintLayout)?.let {
            toolbarBinding = MainToolbarBinding.bind(it)
            with(toolbarBinding) {
                toolbar.title = "Σύγκριση"
                toolbar.setNavigationOnClickListener { 
                    findNavController().navigateUp()
                }
            }
        }
    }


    private fun setupObservers() {
        with(viewModel) {
            departmentItem.observe(viewLifecycleOwner, {
                setupViews(it)
            })

            loadSelectedDepartments()
        }
    }

    private fun setupViews(departments: List<DepartmentItem>) {
        with(binding) {
            lineChart.setScaleEnabled(false)
            lineChart.description.isEnabled = false
            lineChart.setPadding(0, 0, 0, 0)
            lineChart.legend.isEnabled = false
            lineChart.animateY(500)
            lineChart.setDrawMarkers(false)
            //lineChart.marker = CustomMarkerView(context, R.layout.item_marker_view, R.color.purple_500)
            lineChart.enableScroll()

            lineChart.axisLeft.isEnabled = false

            lineChart.axisRight.isEnabled = false
            lineChart.axisRight.setDrawGridLines(false)
            lineChart.axisRight.setDrawAxisLine(false)
            lineChart.axisRight.typeface = Typeface.SERIF
            lineChart.axisRight.textColor = Color.GRAY

            val xAxisFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            }

            lineChart.xAxis.valueFormatter = xAxisFormatter
            lineChart.xAxis.labelCount = 5
            lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            lineChart.xAxis.setDrawGridLines(true)
            lineChart.xAxis.setDrawAxisLine(false)
            lineChart.xAxis.spaceMax = lineChart.xChartMax + 0.5f
            lineChart.xAxis.spaceMin = lineChart.xChartMin + 0.5f
            lineChart.xAxis.typeface = Typeface.SERIF
            lineChart.xAxis.textColor = Color.GRAY

            val lines = mutableListOf<LineDataSet>()

            for (dept in departments) {
                val dataSet = LineDataSet(dept.entries, "Μηχανικών Πληροφορικής και Ηλεκτρονικών Συστημάτων")
                dataSet.setDrawValues(false)
                dataSet.valueTextColor = Color.DKGRAY //resources.getColor(dept.color)
                dataSet.valueTypeface = Typeface.DEFAULT
                dataSet.valueTextSize = 13f
                dataSet.setDrawCircles(true)
                dataSet.setDrawCircleHole(false)
                dataSet.mode = LineDataSet.Mode.LINEAR
                dataSet.circleRadius = 2f
                dataSet.lineWidth = 1f
                //dataSet.isHighlightEnabled = false
                dataSet.enableDashedHighlightLine(10f, 5f, 0f)
                dataSet.color = resources.getColor(dept.color)
                dataSet.circleColors = listOf(resources.getColor(dept.color))

/*
                val fillColor = ContextCompat.getColor(requireContext(), dept.color)
                val fillColorTransparent50 = Color.argb(50, Color.red(fillColor), Color.green(fillColor), Color.blue(fillColor))
                val fillColorTransparent0 = Color.argb(0, Color.red(fillColor), Color.green(fillColor), Color.blue(fillColor))

                val gd = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
                        ContextCompat.getColor(requireContext(), dept.color), fillColorTransparent50,
                        fillColorTransparent0
                    )
                )
                gd.cornerRadius = 0f
                dataSet.fillDrawable = gd*/




                lines.add(dataSet)
            }

            lineChart.data = LineData(lines.toList())
            lineChart.invalidate()

            lineChart.setVisibleXRangeMaximum(5f)
            lineChart.moveViewToX(lineChart.xChartMax)


            lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {

                }

                override fun onNothingSelected() {

                }

            })

            val snapHelper = CenterSnapHelper()
            snapHelper.attachToRecyclerView(departmentRecyclerView)
            departmentRecyclerView.addItemDecoration(CenterDecoration(0))

            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.HORIZONTAL
            departmentRecyclerView.layoutManager = manager

            val listener = object : DepartmentsComparisonAdapter.DepartmentsListener {
                override fun onDepartmentClick(position: Int) {
                    departmentRecyclerView.post {
                        //smooth scroll to center when item has clicked
                        val view = manager.findViewByPosition(position)
                        if (view != null) {
                            val snapDistance = snapHelper.calculateDistanceToFinalSnap(manager, view)

                            val distance0 = snapDistance?.get(0) ?: 0
                            val distance1 = snapDistance?.get(1) ?: 0

                            if (distance0 != 0 || distance1 != 0) {
                                departmentRecyclerView.smoothScrollBy(distance0, distance1)
                            }
                        }
                    }
                }
            }


            val adapter = DepartmentsComparisonAdapter(listener)
            adapter.submitList(departments)
            departmentRecyclerView.adapter = adapter

            departmentRecyclerView.addOnScrollListener(
                SnapOnScrollListener(snapHelper,
                    SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                    object : SnapOnScrollListener.OnSnapPositionChangeListener {
                        override fun onSnapPositionChange(position: Int, oldPosition: Int) {
                            adapter.currentList[position].isBackgroundColorful = true
                            adapter.notifyItemChanged(position)


                            adapter.currentList[oldPosition].isBackgroundColorful = false
                            adapter.notifyItemChanged(oldPosition)

                            lineChart.data.dataSets[position].setDrawValues(true)
                            //lineChart.data.dataSets[position].setDrawFilled(true)


                            lineChart.data.dataSets[oldPosition].setDrawValues(false)
                            //lineChart.data.dataSets[oldPosition].setDrawFilled(false)

                            lineChart.invalidate()
                        }
                    })
            )
        }
    }
}