package com.vaseis.app.data.properties.mapper

import com.vaseis.app.data.properties.model.RemoteField
import com.vaseis.app.data.properties.model.RemoteFieldResponse
import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject


class FieldsMapper @Inject constructor() {

    suspend operator fun invoke(item: RemoteFieldResponse): List<Field> {
        return map(item.fields)
    }

    private suspend fun map(mappable: List<RemoteField>?): List<Field> {
        return mappable?.mapAsync { it.toField() } ?: emptyList()
    }

    private fun RemoteField.toField(): Field =
        Field(
            fieldId ?: "",
            key ?: "",
            fullName ?: "",
            shortName ?: "",
            description ?: ""
        )
}