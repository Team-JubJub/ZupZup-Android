package com.example.zupzup.data.dto.reservation
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zupzup.data.dto.Cart
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.MyReservationModel

@Entity
data class ReservationEntity(
    @PrimaryKey @ColumnInfo(name = "reserve_id") val reserveId: Long,
    @ColumnInfo(name = "store_name") val storeName: String,
    @ColumnInfo(name = "store_address") val storeAddress: String,
    @ColumnInfo(name = "cart_list") val cartList: List<Cart>,
    @ColumnInfo(name = "customer_name") val customerName: String,
    @ColumnInfo(name = "customer_phoneNumber") val customerPhone: String,
    @ColumnInfo(name = "visit_time") val visitTime: Int,
) {
    fun toModel(): MyReservationModel {
        return MyReservationModel(
            reserveId = reserveId,
            storeName = storeName,
            storeAddress = storeAddress,
            cartList = cartList.map { it.toModel() },
            visitTime = visitTime,
            customer = CustomerModel(customerName, customerPhone),
        )
    }
}
