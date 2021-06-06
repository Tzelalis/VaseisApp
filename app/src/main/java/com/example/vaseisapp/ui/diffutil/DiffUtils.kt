package com.example.vaseisapp.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.vaseisapp.domain.topics.Topic
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertyItem
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import com.example.vaseisapp.ui.dashboard.calculator.model.LessonItem
import com.example.vaseisapp.ui.dashboard.departmentcenter.department.models.DepartmentWithSelected
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentItem
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.DeptContactItem
import com.example.vaseisapp.ui.dashboard.departmentcenter.filters.models.CityFilterItem
import com.example.vaseisapp.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem

val DEPARTMENT_DIFF_UTIL = object : DiffUtil.ItemCallback<DepartmentWithSelected>() {
    override fun areItemsTheSame(oldItem: DepartmentWithSelected, newItem: DepartmentWithSelected): Boolean =
        oldItem.code == newItem.code

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
    override fun areItemsTheSame(oldItem: GroupItem, newItem: GroupItem): Boolean = oldItem.calculatorGroup.id == oldItem.calculatorGroup.id

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

