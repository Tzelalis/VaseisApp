package com.example.vaseisapp.domain.calculation.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Calculation(
    var examsTypes : List<ExamType>
) : Parcelable

@Parcelize
data class ExamType(
    var id: String,
    var name: String,
    var short_name: String,
    var groups: List<Group>,
) : Parcelable

@Parcelize
data class Group(
    var id: String,
    var fullName: String,
    var shortName: String,
    var mandatoryLessons: List<Lesson>,
    var optionalCount: Int,
) : Parcelable

@Parcelize
data class Lesson(
    var id: Int = 0,
    var fullName: String,
    var shortName: String,
    var gravity: Double,
    var isMandatory: Boolean,
) : Parcelable


/*
data class ExamTypeWithGroupsWithLessons(
    @Embedded
    var examType: ExamTypeItem,

    @Relation(@Relation(parentColumn = "id", entityColumn = "group_id_fk", entity = Lesson::class)
    val groupsWithLessons: List<GroupWithLessons>,)
    )

data class GroupWithLessons(
    @Embedded
    var group: Group,

    @Relation(parentColumn = "id", entityColumn = "group_id_fk", entity = Lesson::class)
    val examTypes: List<Lesson>,
)

@Entity(tableName = "exam_type")
data class ExamTypeItem(
    var name: String,
    @ColumnInfo(name = "short_name") var short_name: String,
    var groups: List<Group>,
    @PrimaryKey(autoGenerate = true) var id,
) {
    constructor() : this(0, "", "", listOf())
}

@Entity(tableName = "group")
data class Group(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "full_name") var fullName: String,
    @ColumnInfo(name = "short_name") var shortName: String,
    @Ignore @ColumnInfo(name = "mandatory_lessons") var mandatoryLessons: List<Lesson>,
    @ColumnInfo(name = "optional_count") var optionalCount: Int,

    @ForeignKey(entity = ExamTypeItem::class, parentColumns = ["id"], childColumns = ["exam_type_id_fk"], onDelete = ForeignKey.SET_DEFAULT)
    @ColumnInfo(name = "exam_type_id_fk") var examTypeIdFk: Int = 0
) {
    constructor() : this(0, "", "", listOf(), 0, 0)
}

@Entity(tableName = "lesson")
data class Lesson(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "full_name") var fullName: String,
    @ColumnInfo(name = "short_name") var shortName: String,
    var gravity: Double,
    @ColumnInfo(name = "is_mandatory") var isMandatory: Boolean,

    @ForeignKey(entity = ExamTypeItem::class, parentColumns = ["id"], childColumns = ["group_id_fk"], onDelete = ForeignKey.SET_DEFAULT)
    var group_id_fk: Int = 0
) {
    constructor() : this(0, "", "", 0.0, false, 0)
}*/
