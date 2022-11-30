package com.example.zupzup.data.dto.mapper

import com.example.zupzup.data.dto.Cart
import com.example.zupzup.data.dto.Reservation
import com.example.zupzup.data.dto.lunasoft.parameter.Message
import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.ui.utils.toTimeString

object DtoMapper {

    fun reservationModelToDto(model: ReservationModel, reserveId: Long): Reservation {
        return Reservation.ReservationDto(
            reserveId = reserveId,
            storeId = model.reservationHeaderInfo.storeId,
            state = "NEW",
            cartList = model.reservationHeaderInfo.cartList.map { it.toDto() },
            customerName = model.customer.name,
            customerPhone = model.customer.phoneNumber,
            visitTime = model.visitTime
        )
    }

    fun reservationModelToEntity(model: ReservationModel, reserveId: Long): Reservation {
        return Reservation.ReservationEntity(
            reserveId = reserveId,
            storeName = model.reservationHeaderInfo.storeName,
            storeAddress = model.reservationHeaderInfo.storeAddress,
            cartList = model.reservationHeaderInfo.cartList.map { it.toDto() },
            customerName = model.customer.name,
            customerPhone = model.customer.phoneNumber,
            visitTime = model.visitTime
        )
    }

    private fun CartModel.toDto(): Cart {
        return Cart(
            itemId = itemId,
            storeId = storeId,
            name = itemName!!,
            amount = amount,
            salesPrice = salesPrice
        )
    }

    fun getLunaSoftMessages(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): List<Message> {
        val list = mutableListOf<Message>()
        val messageContent = getMessageContent(reservationModel)
        list.add(
            Message(
                no = "0",
                telNum = reservationModel.customer.phoneNumber,
                msgContent = messageContent,
                smsContent = ""
            )
        )
        list.add(
            Message(
                no = "1",
                telNum = hostPhoneNumber,
                msgContent = messageContent,
                smsContent = ""
            )
        )
        return list.toList()
    }

    private fun getMessageContent(reservationModel: ReservationModel): String {
        return "<예약접수 안내>안녕하세요. ${reservationModel.customer.name}님!예약하신 정보에 대해 안내드립니다.\n" +
                "\n" +
                "-예약매장: ${reservationModel.reservationHeaderInfo.storeName}\n" +
                "\n" +
                "-예약제품:${
                    getCartListText(reservationModel.reservationHeaderInfo.cartList)
                }\n" +
                "\n" +
                "-예정 방문 시간:${reservationModel.visitTime.toTimeString()}\n" +
                "\n" +
                "**${reservationModel.reservationHeaderInfo.storeName}의 마감 시간 제품량을 반영하여 금일 ${reservationModel.reservationHeaderInfo.saleTime.first.toTimeString()}에 예약 확정 문자가 전송됩니다.\n" +
                "\n" +
                "*마감 할인 시간에 남아있는 제품량에 따라 예약이 부분 확정 또는 취소될 수 있습니다.\n" +
                "\n" +
                "*예약취소를 원하시거나 문의사항이 있으시면 상담직원에게 메시지를 보내주시기 바랍니다.\n" +
                "\n" +
                "줍줍을 이용해 주셔서 감사합니다 :)"
    }

    private fun getCartListText(cartList: List<CartModel>): String {
        var text = ""
        cartList.forEachIndexed { idx, item ->
            text += "${item.itemName} ${item.amount}개"
            if (idx != cartList.lastIndex) {
                text += ", "
            }
        }
        return text
    }
}