package com.example.vaseisapp.data.properties.mapper

import com.example.vaseisapp.data.properties.model.RemotePropertiesExamType
import com.example.vaseisapp.data.properties.model.RemotePropertiesExamTypeResponse
import com.example.vaseisapp.domain.properties.PropertiesExamType
import com.example.vaseisapp.utils.mapAsync
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