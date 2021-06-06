package com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentSingleDepartmentDetailsBinding
import com.example.vaseisapp.domain.bases.entities.DepartmentBases
import com.example.vaseisapp.ui.AppViewModel
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.adapter.DeptContactAdapter
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.adapter.YearsAdapter
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentItem
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.DeptConcatType
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.DeptContactItem
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.LIST_OF_COLORS
import com.example.vaseisapp.utils.centersnap.CenterDecoration
import com.example.vaseisapp.utils.centersnap.CenterSnapHelper
import com.example.vaseisapp.utils.centersnap.SnapOnScrollListener
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SingleDepartmentDetailsFragment : BaseFragment<FragmentSingleDepartmentDetailsBinding>() {

    private val viewModel: DepartmentDetailsViewModel by viewModels()
    private val appViewModel: AppViewModel by activityViewModels()
    private val args: SingleDepartmentDetailsFragmentArgs by navArgs()
    private val yearsAdapter: YearsAdapter by lazy { YearsAdapter(yearsListener) }
    private val yearsSnaphelper: CenterSnapHelper by lazy { CenterSnapHelper() }
    private val deptContactAdapter: DeptContactAdapter by lazy { DeptContactAdapter(deptContactListener) }

    override fun getViewBinding(): FragmentSingleDepartmentDetailsBinding = FragmentSingleDepartmentDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            singleDepartment.observe(viewLifecycleOwner, { department ->
                fillData(department)
            })

            loadSingleDepartment(args.id)
        }
    }

    private fun setupViews() {
        with(binding) {
            backButtonImageView.setOnClickListener { findNavController().navigateUp() }
            departmentNameTextView.text = args.name
            titleTextView.text = args.name
            Glide.with(requireContext()).load("https://ece.uowm.gr/images/Logorollover.png").diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(departmentImg)
            contactRv.adapter = deptContactAdapter

            //successfulBarChart.clipToOutline = true
            vaseisConstraintLayout.clipToOutline = true
            totalPeopleLinearLayout.clipToOutline = true
            successfulConstraintLayout.clipToOutline = true

            setupBasesLineChart()
            setupBarChart()
            setupYearsRecyclerView()
        }
    }

    private fun setupBasesLineChart() {
        binding.lineChart.apply {
            setScaleEnabled(false)
            description.isEnabled = false
            setPadding(0, 0, 0, 0)
            legend.isEnabled = false
            animateY(500)
            setDrawMarkers(false)
            //lineChart.marker = CustomMarkerView(context, R.layout.item_marker_view, R.color.purple_500)
            enableScroll()
            setViewPortOffsets(0f, 0f, 0f, 50f)

            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            axisRight.setDrawGridLines(false)
            axisRight.setDrawAxisLine(false)
            axisRight.typeface = Typeface.SERIF
            axisRight.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)
            axisRight.setDrawGridLines(false)

            val xAxisFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            }
            xAxis.valueFormatter = xAxisFormatter

            xAxis.setCenterAxisLabels(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(true)
            xAxis.setDrawAxisLine(false)
            xAxis.spaceMax = xChartMax + 0.5f
            xAxis.spaceMin = xChartMin + 0.5f
            xAxis.typeface = Typeface.SERIF
            xAxis.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)
        }
    }

    private fun setupYearsRecyclerView() {
        with(binding) {
            yearsSnaphelper.attachToRecyclerView(yearsRecyclerView)
            yearsRecyclerView.addItemDecoration(CenterDecoration(-1))
            yearsRecyclerView.adapter = yearsAdapter

            yearsRecyclerView.addOnScrollListener(
                SnapOnScrollListener(yearsSnaphelper,
                    SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                    object : SnapOnScrollListener.OnSnapPositionChangeListener {
                        override fun onSnapPositionChange(position: Int, oldPosition: Int) {
                                setFirstBase(position)
                        }
                    })
            )
        }
    }

    private fun setFirstBase(position: Int) {
        val baseFirst = viewModel.singleDepartment.value?.bases?.firstOrNull {
            (it.examType == "ΓΕΛ ΗΜΕΡΗΣΙΑ" || it.examType == "ΓΕΛ ΝΕΟ ΗΜΕΡΗΣΙΑ" || it.examType == "ΓΕΛ, ΕΠΑΛΒ ΗΜΕΡΗΣΙΑ")
                    && it.year.toString() == yearsAdapter.currentList[position]
        }?.baseFirst

        if (baseFirst.toString().isEmpty()) {
            binding.bestLinearLayout.isVisible = false
            return
        }

        binding.numberBestPeopleTextView.text = getString(R.string.bases_first_degree, baseFirst.toString())
        binding.labelOfBestTextView.text = getString(R.string.bases_first_degree_label, yearsAdapter.currentList[position])
    }

    private fun setupBarChart() {
        binding.successfulBarChart.apply {
            description.isEnabled = false
            animateY(500)
            isScaleXEnabled = false
            isScaleYEnabled = false
            legend.isEnabled = false
            isHighlightPerTapEnabled = false
            setFitBars(true)

            val xAxisFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return "${value.toInt()}η"
                }
            }

            xAxis.valueFormatter = xAxisFormatter
            xAxis.labelCount = 6
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            //xAxis.spaceMax = lineChart.xChartMax + 0.5f
            //xAxis.spaceMin = lineChart.xChartMin + 0.5f
            xAxis.typeface = Typeface.SERIF
            xAxis.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)
            xAxis.spaceMax = 0.1f

            axisRight.isEnabled = true
            axisRight.setDrawGridLines(true)
            axisRight.setDrawAxisLine(false)
            axisRight.typeface = Typeface.SERIF
            axisRight.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)

            axisLeft.isEnabled = true
            axisLeft.setDrawGridLines(true)
            axisLeft.setDrawAxisLine(false)
            axisLeft.typeface = Typeface.SERIF
            axisLeft.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)
        }
    }

    private fun fillData(department: DepartmentBases) {
        with(binding)   {
            try{
                departmentNameTextView.text = department.deptName.substring(0, department.deptName.indexOf("("))
                cityTextview.text = department.deptName.substring(department.deptName.indexOf("(") + 1, department.deptName.indexOf(")"))

            }catch (e : StringIndexOutOfBoundsException)    {
                departmentNameTextView.text = department.deptName
                cityTextview.visibility = View.GONE
                cityTitleTextview.visibility = View.GONE
            }
        }

        binding.universityTextview.text = getString(R.string.bases_university_format, department.uniTitle, department.uniTitleShort)

        //dummy fill
        deptContactAdapter.submitList(
            listOf(
                DeptContactItem(DeptConcatType.EMAIL, "tzel@gmail.com"),
                DeptContactItem(DeptConcatType.WEBSITE, "www.google.com"),
                DeptContactItem(DeptConcatType.PHONE, "6940211909"),
            )
        )

        val entriesList = mutableListOf<Entry>()
        for (base in department.bases) {
            if (base.examType == "ΓΕΛ ΗΜΕΡΗΣΙΑ" || base.examType == "ΓΕΛ ΝΕΟ ΗΜΕΡΗΣΙΑ" || base.examType == "ΓΕΛ, ΕΠΑΛΒ ΗΜΕΡΗΣΙΑ")
                entriesList.add(Entry(base.year.toFloat(), base.baseLast.toFloat()))
        }

        val deptItem = DepartmentItem(
            department.code,
            department.deptName,
            department.uniTitle,
            department.uniTitleShort,
            LIST_OF_COLORS[0],
            false,
            entriesList
        )

        setupBasesLineChart(deptItem)
        setupBarChart(deptItem)

        val years = mutableListOf<String>()
        for (year in deptItem.entries) {
            years.add(year.x.toInt().toString())
        }
        yearsAdapter.submitList(years)
        setFirstBase(years.size - 1)
    }


    private fun setupBasesLineChart(department: DepartmentItem) {
        with(binding) {
            if (department.entries.size > 5) {
                lineChart.xAxis.labelCount = 5
            } else
                lineChart.xAxis.labelCount = department.entries.size

            val fillColor = ContextCompat.getColor(requireContext(), R.color.blue_500)

            val dataSet = LineDataSet(department.entries, "Τμήμα")
            dataSet.apply {
                setDrawValues(false)
                valueTextColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)
                valueTypeface = Typeface.DEFAULT_BOLD
                valueTextSize = 13f
                setDrawCircles(true)
                setDrawCircleHole(false)
                mode = LineDataSet.Mode.LINEAR
                circleRadius = 2f
                lineWidth = 1f
                isHighlightEnabled = false
                enableDashedHighlightLine(10f, 5f, 0f)
                color = fillColor
                circleColors = listOf(fillColor)

                val fillColorTransparent65 = Color.argb(65, Color.red(fillColor), Color.green(fillColor), Color.blue(fillColor))
                val fillColorTransparent45 = Color.argb(45, Color.red(fillColor), Color.green(fillColor), Color.blue(fillColor))
                val fillColorTransparent10 = Color.argb(10, Color.red(fillColor), Color.green(fillColor), Color.blue(fillColor))

                val gd = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
                        fillColorTransparent65,
                        fillColorTransparent45,
                        fillColorTransparent10
                    )
                )
                gd.cornerRadius = 0f
                dataSet.fillDrawable = gd
            }

            lineChart.apply {
                data = LineData(dataSet)
                data.dataSets[0].setDrawFilled(true)
                data.setDrawValues(true)
                invalidate()

                setVisibleXRangeMaximum(5f)
                moveViewToX(lineChart.xChartMax)

                setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                    override fun onValueSelected(e: Entry?, h: Highlight?) {}
                    override fun onNothingSelected() {}
                })
            }
        }
    }

    private fun setupBarChart(department: DepartmentItem) {
        with(binding) {
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

    private val yearsListener = object : YearsAdapter.YearsListener {
        override fun onYearClick(position: Int) {
            //smooth scroll to center when item has clicked
            val view = binding.yearsRecyclerView.layoutManager?.findViewByPosition(position)
            if (view != null) {
                val snapDistance = binding.yearsRecyclerView.layoutManager?.let { yearsSnaphelper.calculateDistanceToFinalSnap(it, view) }

                val distance0 = snapDistance?.get(0) ?: 0
                val distance1 = snapDistance?.get(1) ?: 0

                if (distance0 != 0 || distance1 != 0) {
                    binding.yearsRecyclerView.smoothScrollBy(distance0, distance1)
                }
            }
        }
    }

    private val deptContactListener = object : DeptContactAdapter.DeptContactListener {
        override fun onContactOnClick(item: DeptContactItem) {
            when (item.type) {
                DeptConcatType.EMAIL -> {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:")
                    intent.putExtra(Intent.EXTRA_EMAIL, item.name)
                    startActivity(intent)
                }
                DeptConcatType.PHONE -> {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.name))
                    startActivity(intent)
                }
                DeptConcatType.WEBSITE -> {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://" + item.name)
                    startActivity(intent)
                }
            }
        }
    }
}
