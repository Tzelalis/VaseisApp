package com.vaseis.app.data.bases.mapper

import com.vaseis.app.data.bases.model.RemoteDeptInfo
import com.vaseis.app.domain.bases.entities.DeptInfo
import javax.inject.Inject

class DeptInfoMapper @Inject constructor() {

    suspend operator fun invoke(item: RemoteDeptInfo): DeptInfo {
        with(item) {
            return DeptInfo(
                code ?: "",
                email ?: "",
                isActive == 1,
                logoURL ?: "",
                name ?: "",
                phone ?: "",
                uniId ?: "",
                uniFullName ?: "",
                uniShortName ?: "",
                uniLogoUrl ?: "",
                websiteURL ?: "",
                fields?.split("/") ?: listOf()
            )
        }
    }
}