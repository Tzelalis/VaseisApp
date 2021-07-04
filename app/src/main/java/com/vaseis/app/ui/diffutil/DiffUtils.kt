package com.vaseis.app.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.vaseis.app.domain.topics.Topic
import com.vaseis.app.ui.dashboard.accountcenter.model.PropertyItem
import com.vaseis.app.ui.dashboard.calculator.model.GroupItem
import com.vaseis.app.ui.dashboard.calculator.model.LessonItem
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.DepartmentWithSelected
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.FilterChip
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentItem
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DeptContactItem
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.BaseFilterExamTypeItem
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.CityFilterItem
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.FieldFilterItem
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.vaseis.app.ui.dashboard.topicscenter.model.ExamTypeItem

val DEPARTMENT_DIFF_UTIL = object : DiffUtil.ItemCallback<DepartmentWithSelected>() {
    override fun areItemsTheSame(oldItem: DepartmentWithSelected, newItem: DepartmentWithSelected): Boolean =
        oldItem.department.code == newItem.department.code

    override fun areContentsTheSame(oldItem: DepartmentWithSelected, newItem: DepartmentWithSelected): Boolean =
        oldItem == newItem
}

val DEPARTMENT_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<DepartmentItem>() {
    override fun areItemsTheSame(oldItem: DepartmentItem, newItem: DepartmentItem): Boolean = oldItem.deptCode == newItem.deptCode

    override fun areContentsTheSame(oldItem: DepartmentItem, newItem: DepartmentItem): Boolean = oldItem == newItem
}

val PROPERTY_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<PropertyItem>() {
    override fun areItemsTheSame(oldItem: PropertyItem, newItem: PropertyItem): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PropertyItem, newItem: PropertyItem): Boolean = oldItem == newItem
}

val PROPERTY_IMAGE_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
}

val STRING_DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}

val LESSON_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<LessonItem>() {
    override fun areItemsTheSame(oldItem: LessonItem, newItem: LessonItem): Boolean = false

    override fun areContentsTheSame(oldItem: LessonItem, newItem: LessonItem): Boolean = false
}

val TOPIC_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<Topic>() {
    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean = oldItem.year == newItem.year

    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean = oldItem == newItem
}

val GROUP_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<GroupItem>() {
    override fun areItemsTheSame(oldItem: GroupItem, newItem: GroupItem): Boolean = oldItem.calculatorGroup.groupId == oldItem.calculatorGroup.groupId

    override fun areContentsTheSame(oldItem: GroupItem, newItem: GroupItem): Boolean = oldItem == newItem
}

val EXAM_TYPE_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<ExamTypeItem>() {
    override fun areItemsTheSame(oldItem: ExamTypeItem, newItem: ExamTypeItem): Boolean = oldItem.examType.id == newItem.examType.id

    override fun areContentsTheSame(oldItem: ExamTypeItem, newItem: ExamTypeItem): Boolean = oldItem == newItem
}

val UNIVERSITY_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<UniversityFilterItem>() {
    override fun areItemsTheSame(oldItem: UniversityFilterItem, newItem: UniversityFilterItem): Boolean =
        oldItem.university.id == newItem.university.id

    override fun areContentsTheSame(oldItem: UniversityFilterItem, newItem: UniversityFilterItem): Boolean = oldItem == newItem
}

val CITY_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<CityFilterItem>() {
    override fun areItemsTheSame(oldItem: CityFilterItem, newItem: CityFilterItem): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: CityFilterItem, newItem: CityFilterItem): Boolean = oldItem == newItem
}
val DEPT_CONTACT_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<DeptContactItem>() {
    override fun areItemsTheSame(oldItem: DeptContactItem, newItem: DeptContactItem): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: DeptContactItem, newItem: DeptContactItem): Boolean = oldItem == newItem
}

val BASE_FILTER_EXAM_TYPE_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<BaseFilterExamTypeItem>() {
    override fun areItemsTheSame(oldItem: BaseFilterExamTypeItem, newItem: BaseFilterExamTypeItem): Boolean =
        oldItem.examType.id == newItem.examType.id

    override fun areContentsTheSame(oldItem: BaseFilterExamTypeItem, newItem: BaseFilterExamTypeItem): Boolean = oldItem == newItem
}

val FILTER_CHIP_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<FilterChip>() {
    override fun areItemsTheSame(oldItem: FilterChip, newItem: FilterChip): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: FilterChip, newItem: FilterChip): Boolean = oldItem == newItem
}

val FIELD_FILTER_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<FieldFilterItem>() {
    override fun areItemsTheSame(oldItem: FieldFilterItem, newItem: FieldFilterItem): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: FieldFilterItem, newItem: FieldFilterItem): Boolean = oldItem == newItem
}



