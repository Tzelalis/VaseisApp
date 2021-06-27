 package com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.vaseis.app.R
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentSingleDepartmentDetailsBinding
import com.vaseis.app.domain.bases.entities.*
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.adapter.DeptContactAdapter
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.adapter.YearsAdapter
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentItem
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DeptConcatType
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DeptContactItem
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.LIST_OF_COLORS
import com.vaseis.app.utils.centersnap.CenterDecoration
import com.vaseis.app.utils.centersnap.CenterSnapHelper
import com.vaseis.app.utils.centersnap.SnapOnScrollListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SingleDepartmentDetailsFragment : BaseFragment<FragmentSingleDepartmentDetailsBinding>() {

    companion object {
        private const val MOTION_PROGRESS = "progress"
    }

    private var motionProgress: Float? = null
    private val viewModel: DepartmentDetailsViewModel by viewModels()
    private val args: SingleDepartmentDetailsFragmentArgs by navArgs()
    private val yearsAdapter: YearsAdapter by lazy { YearsAdapter(yearsListener) }
    private val yearsSnapHelper: CenterSnapHelper by lazy { CenterSnapHelper() }
    private val deptContactAdapter: DeptContactAdapter by lazy { DeptContactAdapter(deptContactListener) }
    private val successChanger: AnimateDataSetChanged by lazy {
        AnimateDataSetChanged(
            600,
            binding.successfulBarChart,
            interpolator = AccelerateInterpolator()
        )
    }
    private val candidatesChanger: AnimateDataSetChanged by lazy {
        AnimateDataSetChanged(
            600,
            binding.candidatesHorizontalBarChart,
            interpolator = AccelerateInterpolator()
        )
    }

    override fun getViewBinding(): FragmentSingleDepartmentDetailsBinding = FragmentSingleDepartmentDetailsBinding.inflate(layoutInflater)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putFloat(MOTION_PROGRESS, binding.root.progress)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            binding.root.progress = savedInstanceState.getFloat(MOTION_PROGRESS, 0f)
        }

        setupViews()
        setupObservers()
    }


    private fun setupObservers() {
        with(viewModel) {
            singleDepartment.observe(viewLifecycleOwner, { department ->
                fillData(department)
            })

            singleStats.observe(viewLifecycleOwner, { stats ->
                val stat = stats.statistics.maxByOrNull { it.year }

                fillBarChart(stat)
                fillCandidatesBarChart(stat)
                fillTotalCandidates(stat)

                binding.successfulBarChart.updateStatsBarChartAxisHeight(stats.statistics.getMaxSuccessPositions())
                binding.candidatesHorizontalBarChart.updateStatsBarChartAxisHeight(stats.statistics.getMaxCandidatesPositions())
            })

            error.observe(viewLifecycleOwner, {
                loadSingleDepartment(args.id)
            })

            singleDeptInfo.observe(viewLifecycleOwner, { deptInfo ->
                fillDeptInfo(deptInfo)
            })

            loadSingleDepartment(args.id, args.type)
            loadSingleDeptStats(args.id)
        }
    }

    private fun setupViews() {
        with(binding) {
            backButtonImageView.setOnClickListener { findNavController().navigateUp() }
            departmentNameTextView.text = args.name
            titleTextView.text = args.name

            contactRv.adapter = deptContactAdapter

            //successfulBarChart.clipToOutline = true
            vaseisConstraintLayout.clipToOutline = true
            totalPeopleLinearLayout.clipToOutline = true
            successfulConstraintLayout.clipToOutline = true

            setupBasesLineChart()
            setupSuccessBarChart()
            setupYearsRecyclerView()
            setupCandidatesBarChart()
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
            yearsSnapHelper.attachToRecyclerView(yearsRecyclerView)
            yearsRecyclerView.addItemDecoration(CenterDecoration(-1))
            yearsRecyclerView.adapter = yearsAdapter

            yearsRecyclerView.addOnScrollListener(
                SnapOnScrollListener(yearsSnapHelper,
                    SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                    object : SnapOnScrollListener.OnSnapPositionChangeListener {
                        override fun onSnapPositionChange(position: Int, oldPosition: Int) {
                            setFirstBase(position)
                            changeBarChart(yearsAdapter.currentList[position])
                            changeCandidatesBarChart(yearsAdapter.currentList[position])

                            val stat = viewModel.singleStats.value?.statistics?.firstOrNull { it.year == yearsAdapter.currentList[position] }
                            fillTotalCandidates(stat)
                        }
                    })
            )
        }
    }

    private fun setFirstBase(position: Int) {
        val baseFirst = viewModel.singleDepartment.value?.bases?.firstOrNull { it.year.toString() == yearsAdapter.currentList[position] }?.baseFirst
        if (baseFirst.toString().isEmpty()) {
            binding.bestLinearLayout.isVisible = false
            return
        }

        binding.firstBaseTitleTxt.text = getString(R.string.bases_first_degree, baseFirst.toString())
    }

    private fun setupSuccessBarChart() {
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

            xAxis.typeface = Typeface.SERIF
            xAxis.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)
            xAxis.spaceMax = 0.1f

            axisRight.isEnabled = true
            axisRight.setDrawGridLines(true)
            axisRight.setDrawAxisLine(false)
            axisRight.typeface = Typeface.SERIF
            axisRight.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)

            axisLeft.isEnabled = false
            axisLeft.setDrawGridLines(true)
            axisLeft.setDrawAxisLine(false)
            axisLeft.typeface = Typeface.SERIF
            axisLeft.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)

            updateStatsBarChartAxisHeight(200f)
        }
    }

    private fun setupCandidatesBarChart() {
        binding.candidatesHorizontalBarChart.apply {
            legend.isEnabled = false
            description.isEnabled = false
            setPadding(0, 0, 0, 0)
            setTouchEnabled(false)
            setFitBars(true)
            animateY(1000)

            axisRight.isEnabled = false
            axisLeft.isEnabled = false
            xAxis.isEnabled = true
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.labelCount = 3
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return String.format("${value.toInt()}η")
                }
            }
            xAxis.typeface = Typeface.SERIF
            xAxis.textColor = ResourcesCompat.getColor(resources, R.color.text_dr_grey, null)
            invalidate()
        }
    }

    private fun fillCandidatesBarChart(stat: Stats?) {
        if (stat == null) {
            return
        }

        val valueList = getCandidatesPreferencesValues(stat)

        val entries: ArrayList<BarEntry> = ArrayList()
        for (i in valueList.indices) {
            val barEntry = BarEntry(i.toFloat() + 1, valueList[i].toFloat())
            entries.add(barEntry)
        }

        val dataSet = BarDataSet(entries, "Candidates")
        dataSet.apply {
            colors = listOf(
                ResourcesCompat.getColor(resources, R.color.barCharCandidatesSecondColor, null)
            )

            valueTypeface = ResourcesCompat.getFont(requireContext(), R.font.roboto)
            valueTextSize = 14f
            valueTextColor = ResourcesCompat.getColor(resources, R.color.barCharCandidatesSecondColor, null)
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            }
        }

        val data = BarData()
        data.addDataSet(dataSet)
        data.barWidth = 0.2f
        binding.candidatesHorizontalBarChart.data = data
        binding.candidatesHorizontalBarChart.invalidate()
    }

    private fun changeCandidatesBarChart(year: String) {
        viewModel.singleStats.value?.statistics.let { stats ->
            val stat = stats?.firstOrNull { it.year == year }
            val valueList = getCandidatesPreferencesValues(stat)

            val entries: ArrayList<BarEntry> = ArrayList()
            for (i in valueList.indices) {
                val barEntry = BarEntry(i + 1f, valueList[i].toFloat())
                entries.add(barEntry)
            }

            val oldEntries = mutableListOf<Entry>()
            if (binding.candidatesHorizontalBarChart.data.dataSets[0].entryCount < 3)
                oldEntries.addAll(listOf(Entry(1f, 0f), Entry(2f, 0f), Entry(3f, 0f)))
            else {
                for (i in 0 until binding.candidatesHorizontalBarChart.data.dataSets[0].entryCount)
                    oldEntries.add(binding.candidatesHorizontalBarChart.data.dataSets[0].getEntryForIndex(i))
            }

            candidatesChanger.changeAnim(oldEntries, entries)
        }
    }

    private fun BarChart.updateStatsBarChartAxisHeight(max: Float, min: Float = 0f) {
        this.axisRight.axisMaximum = max
        this.axisLeft.axisMaximum = max
        this.axisRight.axisMinimum = min
        this.axisLeft.axisMinimum = min
    }

    private fun fillDeptInfo(department: DeptInfo)  {
        //todo na pw ton malaka na balei to name apo to uni
        with(binding) {
            setLogoImg(department.logoURL, department.uniLogoUrl)

            try {
                departmentNameTextView.text = department.name.substring(0, department.name.indexOf("("))
                cityTextview.text = department.name.substring(department.name.indexOf("(") + 1, department.name.indexOf(")"))
            } catch (e: StringIndexOutOfBoundsException) {
                departmentNameTextView.text = department.name
                cityTextview.visibility = View.GONE
                cityTitleTextview.visibility = View.GONE
            }

            universityTextview.text = getString(R.string.bases_university_format, department.uniFullName, department.uniShortName)
            bestLinearLayout.isVisible = false
            successfulConstraintLayout.isVisible = false
            totalPeopleLinearLayout.isVisible = false
            vaseisConstraintLayout.isVisible = false
            moreStatsTextView.isVisible = false
            fieldTitleTextview.isVisible = false
            fieldTextview.isVisible = false
            errorTxt.text = "Δεν υπάρχει ιστορικό βάσεων για τον επιλεγμένο τύπο εξετάσεων."
            errorTxt.isVisible = true
        }
        fillDeptContactAdapter(department.phone, department.websiteURL, department.email)
    }

    private fun fillData(department: DepartmentBases) {
        with(binding) {
            setLogoImg(department.deptLogoURL, department.uniLogoURL)

            try {
                departmentNameTextView.text = department.deptName.substring(0, department.deptName.indexOf("("))
                cityTextview.text = department.deptName.substring(department.deptName.indexOf("(") + 1, department.deptName.indexOf(")"))
            } catch (e: StringIndexOutOfBoundsException) {
                departmentNameTextView.text = department.deptName
                cityTextview.visibility = View.GONE
                cityTitleTextview.visibility = View.GONE
            }

            val fields = department.bases.firstOrNull { base ->
                base.year == department.bases.maxOf { it.year }
                        && (base.examType == "ΓΕΛ ΗΜΕΡΗΣΙΑ" || base.examType == "ΓΕΛ ΝΕΟ ΗΜΕΡΗΣΙΑ" || base.examType == "ΓΕΛ, ΕΠΑΛΒ ΗΜΕΡΗΣΙΑ")
                        && base.field.isNotBlank()
            }?.field
            if (fields.isNullOrBlank()) {
                fieldTextview.visibility = View.GONE
                fieldTitleTextview.visibility = View.GONE
            } else {
                fieldTextview.text = fields.replace("/", ", ")
            }

            binding.universityTextview.text = getString(R.string.bases_university_format, department.uniTitle, department.uniTitleShort)
        }
        fillDeptContactAdapter(department.phone, department.websiteURL, department.email)

        val entriesList = mutableListOf<Entry>()
        for (base in department.bases)
            entriesList.add(Entry(base.year.toFloat(), base.baseLast.toFloat()))

        val deptItem = DepartmentItem(
            department.code,
            department.deptName,
            department.uniTitle,
            department.uniTitleShort,
            LIST_OF_COLORS[0],
            false,
            entriesList
        )

        fillBasesLineChart(deptItem)

        val years = mutableListOf<String>()
        for (year in deptItem.entries) {
            years.add(year.x.toInt().toString())
        }
        yearsAdapter.submitList(years)
        setFirstBase(years.size - 1)
    }

    private fun setLogoImg(deptLogo: String?, uniLogo: String?) {
        if (!deptLogo.isNullOrBlank()) {
            Glide.with(requireContext()).load(deptLogo).diskCacheStrategy(DiskCacheStrategy.ALL).centerInside().into(binding.departmentImg)
            return
        }

        if (!uniLogo.isNullOrBlank()) {
            Glide.with(requireContext()).load(uniLogo).diskCacheStrategy(DiskCacheStrategy.ALL).centerInside().into(binding.departmentImg)
            return
        }

        binding.departmentImg.visibility = View.INVISIBLE
    }

    private fun fillDeptContactAdapter(phonesValue : String, websitesValue : String, emailsValue : String) {
        val phones = phonesValue.split(", ")
        val websites = websitesValue.split(", ")
        val emails = emailsValue.split(", ")

        val list = mutableListOf<DeptContactItem>()
        for (phone in phones)
            if (phone.isNotBlank())
                list.add(DeptContactItem(DeptConcatType.PHONE, phone))

        for (site in websites)
            if (site.isNotBlank())
                list.add(DeptContactItem(DeptConcatType.WEBSITE, site))

        for (email in emails)
            if (email.isNotEmpty())
                list.add(DeptContactItem(DeptConcatType.EMAIL, email))

        deptContactAdapter.submitList(list)

        if (list.isEmpty())
            binding.contactRv.visibility = View.GONE
    }


    private fun fillBasesLineChart(department: DepartmentItem) {
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

    private fun fillBarChart(stat: Stats?) {
        with(binding) {
            val valueList = getSuccessPreferencesValues(stat)

            val entries: ArrayList<BarEntry> = ArrayList()
            for (i in valueList.indices) {
                val barEntry = BarEntry(i.toFloat() + 1, valueList[i].toFloat())
                entries.add(barEntry)
            }

            val barDataSet = BarDataSet(entries, "Title")
            barDataSet.apply {
                setGradientColor(
                    ResourcesCompat.getColor(resources, R.color.barChartSecondColor, null),
                    ResourcesCompat.getColor(resources, R.color.blue_500, null)
                )
                isHighlightEnabled = false
                valueTextSize = 12f
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return value.toInt().toString()
                    }
                }
                valueTypeface = ResourcesCompat.getFont(requireContext(), R.font.roboto_bold)
                valueTextColor = ResourcesCompat.getColor(resources, R.color.single_department_base_stats_bar_text_color, null)
            }

            val data = BarData()
            data.addDataSet(barDataSet)
            data.barWidth = 0.9f

            successfulBarChart.data = data
            successfulBarChart.invalidate()
        }
    }

    private fun changeBarChart(year: String) {
        viewModel.singleStats.value?.statistics.let { stats ->
            val stat = stats?.firstOrNull { it.year == year }

            val valueList = getSuccessPreferencesValues(stat)

            val entries: ArrayList<BarEntry> = ArrayList()
            for (i in valueList.indices) {
                val barEntry = BarEntry(i.toFloat() + 1, valueList[i].toFloat())
                entries.add(barEntry)
            }

            var oldEntries = mutableListOf<Entry>()
            if (binding.successfulBarChart.data == null)
                oldEntries = mutableListOf()
            else {
                for (i in 0 until binding.successfulBarChart.data.dataSets[0].entryCount)
                    oldEntries.add(binding.successfulBarChart.data.dataSets[0].getEntryForIndex(i))
            }

            successChanger.changeAnim(oldEntries, entries)
        }
    }

    private fun getSuccessPreferencesValues(stat: Stats?): List<Double> {
        return if (stat == null) {
            listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        } else {
            listOf(
                stat.successfulPreferences.first.toDouble(),
                stat.successfulPreferences.second.toDouble(),
                stat.successfulPreferences.third.toDouble(),
                stat.successfulPreferences.fourth.toDouble(),
                stat.successfulPreferences.fifth.toDouble(),
                stat.successfulPreferences.sixth.toDouble()
            )
        }
    }

    private fun getCandidatesPreferencesValues(stat: Stats?): List<Double> {
        return if (stat == null) {
            listOf(0.0, 0.0, 0.0)
        } else {
            listOf(
                stat.candidatePreferences.first.toDouble(),
                stat.candidatePreferences.second.toDouble(),
                stat.candidatePreferences.third.toDouble(),
            )
        }
    }

    private fun fillTotalCandidates(stat: Stats?) {
        binding.totalCandidatesTitleTxt.text = resources.getQuantityString(
            R.plurals.bases_candidate,
            stat?.candidatePreferences?.getTotal() ?: 1,
            stat?.candidatePreferences?.getTotal()?.toString() ?: "-"
        )
    }

    private val yearsListener = object : YearsAdapter.YearsListener {
        override fun onYearClick(year: String, position: Int) {
            //smooth scroll to center when item has clicked
            val view = binding.yearsRecyclerView.layoutManager?.findViewByPosition(position)
            if (view != null) {
                val snapDistance = binding.yearsRecyclerView.layoutManager?.let { yearsSnapHelper.calculateDistanceToFinalSnap(it, view) }

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
                    intent.data = Uri.parse(String.format("mailto:${item.name}"))
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