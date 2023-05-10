package ru.madmax.vktestcase2.data.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}