package com.example.zupzup.data.datamodel

import com.example.zupzup.domain.models.MerchandiseModel

data class Merchandise(
    val id: Int,
    val imgUrl: String,
    val name: String,
    val price: Int,
    val sales: Int,
) {

    fun toModel(): MerchandiseModel {
        return MerchandiseModel(
            id = id,
            imgUrl = imgUrl,
            name = name,
            price = price,
            sales = sales
        )
    }
}
