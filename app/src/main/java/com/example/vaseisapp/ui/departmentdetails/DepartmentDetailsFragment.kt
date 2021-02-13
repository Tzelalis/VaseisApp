package com.example.vaseisapp.ui.departmentdetails

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
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
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


class DepartmentDetailsFragment : BaseFragment<FragmentDepartmentDetailsLayoutBinding>() {

    private val viewModel: DepartmentDetailsViewModel by viewModels()
    private val appViewModel: AppViewModel by activityViewModels()
    private val args: DepartmentDetailsFragmentArgs by navArgs()
    private lateinit var toolbarBinding: MainToolbarBinding
    private lateinit var yearsAdapter : YearsAdapter
    private lateinit var departmentsAdapter : DepartmentsComparisonAdapter
    private lateinit var currentDepartment : DepartmentItem

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
        with(binding){
            //successfulBarChart.clipToOutline = true
            vaseisConstraintLayout.clipToOutline = true
            totalPeopleLinearLayout.clipToOutline = true
            successfulConstraintLayout.clipToOutline = true
        }



        setupVaseisLineChart(departments)
        setupDepartmentsRecyclerView(departments)
        setupBarChart(departments)


        val years = mutableListOf<String>()
        for(year in departments[0].entries){
            years.add(year.x.toInt().toString())
        }
        setupYearsRecyclerView(years)
    }

    private fun setupVaseisLineChart(departments: List<DepartmentItem>) {
        with(binding) {
            lineChart.setScaleEnabled(false)
            lineChart.description.isEnabled = false
            lineChart.setPadding(0, 0, 0, 0)
            lineChart.legend.isEnabled = false
            lineChart.animateY(500)
            lineChart.setDrawMarkers(false)
            //lineChart.marker = CustomMarkerView(context, R.layout.item_marker_view, R.color.purple_500)
            lineChart.enableScroll()
            lineChart.setViewPortOffsets(0f, 0f, 0f, 50f)


            lineChart.axisLeft.isEnabled = false

            lineChart.axisRight.isEnabled = false
            lineChart.axisRight.setDrawGridLines(false)
            lineChart.axisRight.setDrawAxisLine(false)
            lineChart.axisRight.typeface = Typeface.SERIF
            lineChart.axisRight.textColor = Color.GRAY
            lineChart.axisRight.setDrawGridLines(false)

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
        }
    }

    private fun setupDepartmentsRecyclerView(departments: List<DepartmentItem>) {
        with(binding) {
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


            departmentsAdapter = DepartmentsComparisonAdapter(listener)
            departmentsAdapter.submitList(departments)
            departmentRecyclerView.adapter = departmentsAdapter

            departmentRecyclerView.addOnScrollListener(
                SnapOnScrollListener(snapHelper,
                    SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                    object : SnapOnScrollListener.OnSnapPositionChangeListener {
                        override fun onSnapPositionChange(position: Int, oldPosition: Int) {
                            departmentsAdapter.currentList[position].isBackgroundColorful = true
                            departmentsAdapter.notifyItemChanged(position)


                            departmentsAdapter.currentList[oldPosition].isBackgroundColorful = false
                            departmentsAdapter.notifyItemChanged(oldPosition)

                            lineChart.data.dataSets[position].setDrawValues(true)
                            //lineChart.data.dataSets[position].setDrawFilled(true)

                            lineChart.data.dataSets[oldPosition].setDrawValues(false)
                            //lineChart.data.dataSets[oldPosition].setDrawFilled(false)

                            val years = mutableListOf<String>()
                            for(year in departments[position].entries)
                                years.add(year.x.toInt().toString())

                            yearsAdapter.submitList(years)
                            yearsAdapter.notifyDataSetChanged()

                            lineChart.invalidate()
                        }
                    })
            )
        }
    }

    private fun setupYearsRecyclerView(years : List<String>){
        with(binding){
            val snapHelper = CenterSnapHelper()
            snapHelper.attachToRecyclerView(yearsRecyclerView)
            yearsRecyclerView.addItemDecoration(CenterDecoration(-1))

            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.HORIZONTAL
            manager.stackFromEnd = true

            val listener = object : YearsAdapter.YearsListener{
                override fun onYearClick(position: Int) {
                    //smooth scroll to center when item has clicked
                    val view = manager.findViewByPosition(position)
                    if (view != null) {
                        val snapDistance = snapHelper.calculateDistanceToFinalSnap(manager, view)

                        val distance0 = snapDistance?.get(0) ?: 0
                        val distance1 = snapDistance?.get(1) ?: 0

                        if (distance0 != 0 || distance1 != 0) {
                            yearsRecyclerView.smoothScrollBy(distance0, distance1)
                        }
                    }
                }

            }

            yearsAdapter = YearsAdapter(listener)
            yearsAdapter.submitList(years)

            yearsRecyclerView.adapter = yearsAdapter
            yearsRecyclerView.layoutManager = manager

            yearsRecyclerView.addOnScrollListener(
                SnapOnScrollListener(snapHelper,
                    SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                    object : SnapOnScrollListener.OnSnapPositionChangeListener {
                        override fun onSnapPositionChange(position: Int, oldPosition: Int) {

                        }
                    })
            )
        }
    }

    private fun setupBarChart(departments: List<DepartmentItem>) {
        with(binding) {
            successfulBarChart.description.isEnabled = false
            successfulBarChart.animateY(500)
            successfulBarChart.isScaleXEnabled = false
            successfulBarChart.isScaleYEnabled = false
            successfulBarChart.legend.isEnabled = false
            successfulBarChart.isHighlightPerTapEnabled = false
            successfulBarChart.setFitBars(true)


            val xAxisFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return "${value.toInt()}η"
                }
            }

            successfulBarChart.xAxis.valueFormatter = xAxisFormatter
            successfulBarChart.xAxis.labelCount = 6
            successfulBarChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            successfulBarChart.xAxis.setDrawGridLines(false)
            successfulBarChart.xAxis.setDrawAxisLine(false)
            //successfulBarChart.xAxis.spaceMax = lineChart.xChartMax + 0.5f
            //successfulBarChart.xAxis.spaceMin = lineChart.xChartMin + 0.5f
            successfulBarChart.xAxis.typeface = Typeface.SERIF
            successfulBarChart.xAxis.textColor = Color.GRAY
            successfulBarChart.xAxis.spaceMax = 0.1f

            successfulBarChart.axisRight.isEnabled = true
            successfulBarChart.axisRight.setDrawGridLines(true)
            successfulBarChart.axisRight.setDrawAxisLine(false)
            successfulBarChart.axisRight.typeface = Typeface.SERIF
            successfulBarChart.axisRight.textColor = Color.GRAY

            successfulBarChart.axisLeft.isEnabled = true
            successfulBarChart.axisLeft.setDrawGridLines(true)
            successfulBarChart.axisLeft.setDrawAxisLine(false)
            successfulBarChart.axisLeft.typeface = Typeface.SERIF
            successfulBarChart.axisLeft.textColor = Color.GRAY


            val valueList = ArrayList<Double>()
            val entries: ArrayList<BarEntry> = ArrayList()
            val title = "Title"

            //input data
            for (i in 1..7) {
                valueList.add(i * 2 + 10.toDouble())
            }

            for (i in 1 until 7) {
                val barEntry = BarEntry(i.toFloat(), valueList[i].toFloat())
                entries.add(barEntry)
            }

            val barDataSet = BarDataSet(entries, title)
            barDataSet.color = resources.getColor(R.color.blue_500)
            barDataSet.isHighlightEnabled = false

            val valueList1 = ArrayList<Double>()
            val entries1: ArrayList<BarEntry> = ArrayList()
            val title1 = "Title"

            //input data
            for (i in 1..7) {
                valueList1.add(i * 2 + 2.toDouble())
            }

            for (i in 1 until 7) {
                val barEntry1 = BarEntry(i.toFloat(), valueList1[i].toFloat())
                entries.add(barEntry1)
            }

            val barDataSet1 = BarDataSet(entries1, title1)
            barDataSet1.color = resources.getColor(R.color.black)
            barDataSet1.isHighlightEnabled = false


            val data = BarData()
            data.addDataSet(barDataSet)
            data.addDataSet(barDataSet1)
            data.barWidth = 0.9f

            successfulBarChart.data = data
            successfulBarChart.invalidate()
        }
    }
}
