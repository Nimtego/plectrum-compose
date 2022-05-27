package com.nimtego.plectrum_compose.presentation.common.extension

fun <T> isEqual(first: List<T>, second: List<T>): Boolean {
    return if (first.size != second.size) {
        false
    } else {
        first.zip(second).all { (x, y) -> x == y }
    }
}