package com.example.zupzup.data

import com.example.zupzup.data.datamodel.Merchandise
import com.example.zupzup.data.datamodel.Store
import com.example.zupzup.data.datamodel.StoreHeaderInfo

object TestData {
    val storeList = listOf<Store>(
        Store(
            1, StoreHeaderInfo(
                "부산광역시 장전동 123-48 1층", "카페", "로그인 카페", 40, "12:00 ~ 18:00",
                listOf("현금결제 시 1000원 할인", "카드결제 시 무료", "이벤트3")
            ),
            listOf(
                Merchandise(0, "img", "빵빵빵1", 4000, 40),
                Merchandise(1, "img", "빵빵빵2", 4500, 40),
                Merchandise(2, "img", "빵빵빵3", 6000, 40),
                Merchandise(3, "img", "빵빵빵4", 3000, 40),
                Merchandise(4, "img", "빵빵빵5", 5000, 40),
                Merchandise(5, "img", "빵빵빵6", 8000, 40),
                Merchandise(6, "img", "빵빵빵7", 3000, 40),
            )
        ),
        Store(
            2,
            StoreHeaderInfo(
                "부산광역시 장전동 123-48 1층",
                "카페",
                "AM 11:00",
                40,
                "12:00 ~ 18:00",
                listOf("현금결제 시 1000원 할인", "카드결제 시 무료", "이벤트3", "전 상품 공짜 이벤트")
            ),
            listOf(
                Merchandise(0, "img", "빵빵빵1", 4000, 40),
                Merchandise(1, "img", "빵빵빵2", 4500, 40),
                Merchandise(2, "img", "빵빵빵3", 6000, 40),
                Merchandise(3, "img", "빵빵빵4", 3000, 40),
                Merchandise(4, "img", "빵빵빵5", 5000, 40),
                Merchandise(5, "img", "빵빵빵6", 8000, 40),
                Merchandise(6, "img", "빵빵빵7", 3000, 40),
            )
        ),
        Store(
            3,
            StoreHeaderInfo(
                "부산광역시 장전동 123-48 1층", "카페", "번아웃 커피 금정점", 40, "12:00 ~ 18:00",
                listOf("현금결제 시 1000원 할인", "카드결제 시 무료", "이벤트3","이벤트 4")
            ),
            listOf(
                Merchandise(0, "img", "빵빵빵1", 4000, 40),
                Merchandise(1, "img", "빵빵빵2", 4500, 40),
                Merchandise(2, "img", "빵빵빵3", 6000, 40),
                Merchandise(3, "img", "빵빵빵4", 3000, 40),
                Merchandise(4, "img", "빵빵빵5", 5000, 40),
                Merchandise(5, "img", "빵빵빵6", 8000, 40),
                Merchandise(6, "img", "빵빵빵7", 3000, 40),
            )
        ),
    )
}