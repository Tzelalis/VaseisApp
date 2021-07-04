package com.vaseis.app.framework.calculator

import androidx.room.Dao

@Dao
interface CalculatorDao {
    /*  @Query("SELECT * FROM exam_type e INNER JOIN `calculatorGroup` g ON e.id=g.exam_type_id_fk INNER JOIN calculatorLesson l ON g.id=l.group_id_fk")
      suspend fun getExamTypeDummies(): List<ExamTypeItem>

      @Query("SELECT * FROM  `calculatorGroup` g")
      suspend fun getCalculatorGroups(): List<DummyCalculatorGroup>

      @Insert
      suspend fun insertExamType(vararg users: ExamTypeItem)

      @Insert
      suspend fun insertGroup(vararg calculatorGroup: DummyCalculatorGroup)

      @Insert
      suspend fun insertLesson(vararg calculatorLesson: DummyCalculatorLesson)

      @Query("DELETE FROM exam_type")
      suspend fun nukeExamTypeTable()

      @Query("DELETE FROM `calculatorGroup`")
      suspend fun nukeGroupTable()

      @Query("DELETE FROM calculatorLesson")
      suspend fun nukeLessonTable()*/

}