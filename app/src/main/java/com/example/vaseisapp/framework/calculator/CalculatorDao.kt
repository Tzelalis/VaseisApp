package com.example.vaseisapp.framework.calculator

import androidx.room.Dao

@Dao
interface CalculatorDao {
    /*  @Query("SELECT * FROM exam_type e INNER JOIN `group` g ON e.id=g.exam_type_id_fk INNER JOIN lesson l ON g.id=l.group_id_fk")
      suspend fun getExamTypes(): List<ExamTypeItem>

      @Query("SELECT * FROM  `group` g")
      suspend fun getGroups(): List<Group>

      @Insert
      suspend fun insertExamType(vararg users: ExamTypeItem)

      @Insert
      suspend fun insertGroup(vararg group: Group)

      @Insert
      suspend fun insertLesson(vararg lesson: Lesson)

      @Query("DELETE FROM exam_type")
      suspend fun nukeExamTypeTable()

      @Query("DELETE FROM `group`")
      suspend fun nukeGroupTable()

      @Query("DELETE FROM lesson")
      suspend fun nukeLessonTable()*/

}