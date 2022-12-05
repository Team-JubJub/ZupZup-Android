package com.example.zupzup.ui.bindinghelper

import com.example.zupzup.domain.models.MerchandiseModel
import com.example.zupzup.domain.models.StoreDetailHeaderModel

class StoreDetailBindingHelper(
    private val setHeaderAdapter: (StoreDetailHeaderModel) -> Unit,
    private val setBodyAdapter: (List<MerchandiseModel>) -> Unit
) {

    fun setHeader(detailHeaderModel: StoreDetailHeaderModel) {
        setHeaderAdapter(detailHeaderModel)
    }

    fun setBody(merchandiseList : List<MerchandiseModel>) {
        setBodyAdapter(merchandiseList)
    }
}