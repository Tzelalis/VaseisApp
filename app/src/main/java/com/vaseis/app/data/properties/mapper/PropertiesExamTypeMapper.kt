package com.vaseis.app.data.properties.mapper

import com.vaseis.app.data.properties.model.RemotePropertiesExamType
import com.vaseis.app.data.properties.model.RemotePropertiesExamTypeResponse
import com.vaseis.app.domain.properties.PropertiesExamType
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject


class PropertiesExamTypeMapper @Inject constructor() {

    suspend operator fun invoke(examTypes: RemotePropertiesExamTypeResponse): List<PropertiesExamType> {
        return map(examTypes.examTypes?.toMutableList())
    }

    suspend fun map(mappable: MutableList<RemotePropertiesExamType?>?): List<PropertiesExamType> {
        return mappable?.mapAsync { it.toPropertiesExamType() } ?: emptyList()
    }

    private fun RemotePropertiesExamType?.toPropertiesExamType() : PropertiesExamType    {
        return PropertiesExamType(
            this?.id ?:"",
            this?.fullName ?:"",
            this?.shortName ?:""
        )
    }
}