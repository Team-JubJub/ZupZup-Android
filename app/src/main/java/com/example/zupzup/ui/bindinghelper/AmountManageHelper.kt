package com.example.zupzup.ui.bindinghelper

class AmountManageHelper(
    private val increaseAmount: (Int) -> Unit,
    private val decreaseAmount: (Int) -> Unit,
    private val getAmountListFromViewModel : () -> List<Int>
) {
    fun increase(idx : Int) {
        increaseAmount(idx)
    }

    fun decrease(idx : Int) {
        decreaseAmount(idx)
    }

    fun getAmountList() : List<Int> {
        return getAmountListFromViewModel()
    }
}