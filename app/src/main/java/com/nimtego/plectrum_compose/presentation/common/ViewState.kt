package com.nimtego.plectrum_compose.presentation.common

import com.nimtego.common.Failure
import com.nimtego.plectrum_compose.presentation.common.ViewState.Status.*

class ViewState<D>(
    val status: Status,
    val data: D? = null,
    val failure: Failure? = null
) {

    fun invokeResult(
        loading: (() -> Unit)? = null,
        success: (data: D) -> Unit,
        error: ((failure: Failure) -> Unit)? = null
    ) {
        when (this.status) {
            LOADING -> {
                loading?.invoke()
            }
            SUCCESS -> {
                data?.let { success(it) }
            }
            ERROR -> {
                failure?.let { error?.invoke(it) }
            }
        }
    }

    operator fun invoke(
        loading: (() -> Unit)? = null,
        error: ((failure: Failure) -> Unit)? = null,
        success: (data: D) -> Unit
    ) {
        when (this.status) {
            LOADING -> {
                loading?.invoke()
            }
            SUCCESS -> {
                data?.let { success(it) }
            }
            ERROR -> {
                failure?.let { error?.invoke(it) }
            }
        }
    }

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <D : Any> success(date: D) = ViewState(status = SUCCESS, data = date)
        fun <D : Any> loading() = ViewState<D>(status = LOADING)
        fun <D : Any> failure(failure: Failure) = ViewState<D>(status = ERROR, failure = failure)
    }
}

fun <D> Failure.toFailureState(): ViewState<D> {
    return ViewState(status = ERROR, failure = this)
}

fun <D> D.toSuccessState(): ViewState<D> {
    return ViewState(status = SUCCESS, data = this)
}