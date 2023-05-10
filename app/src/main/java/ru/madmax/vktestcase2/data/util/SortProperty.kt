package ru.madmax.vktestcase2.data.util

sealed class SortProperty(val orderType: OrderType) {
    class Name(order: OrderType = OrderType.Ascending) : SortProperty(order)
    class Date(order: OrderType = OrderType.Descending) : SortProperty(order)
    class Size(order: OrderType = OrderType.Descending) : SortProperty(order)
    class Extension(order: OrderType = OrderType.Descending) : SortProperty(order)

    fun copy(orderType: OrderType): SortProperty {
        return when (this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
            is Size -> Size(orderType)
            is Extension -> Extension(orderType)
        }
    }
}
