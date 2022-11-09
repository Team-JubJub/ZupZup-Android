package com.example.zupzup.ui.utils

class AmountManageHelper(
    private val increaseAmount: (Int) -> Unit,
    private val decreaseAmount: (Int) -> Unit
) {
    fun increase(idx : Int) {
        increaseAmount(idx)
    }

    fun decrease(idx : Int) {
        decreaseAmount(idx)
    }
}