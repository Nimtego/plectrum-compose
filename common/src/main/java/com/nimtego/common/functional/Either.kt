package com.nimtego.common.functional


sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>


    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun <T> fold(fnL: (L) -> T?, fnR: (R) -> T?): T? =
            when (this) {
                is Left -> fnL(a)
                is Right -> fnR(b)
            }
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <L> L.toLeft(): Either<L, Nothing> = Either.Left(this)
fun <R> R.toRight(): Either<Nothing, R> = Either.Right(this)

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
        when (this) {
            is Either.Left -> Either.Left(a)
            is Either.Right -> fn(b)
        }

fun <L, R> Either<L, R>.onFailure(fn: (failure: L) -> Unit): Either<L, R> =
        this.apply { if (this is Either.Left) fn(a) }

fun <L, R> Either<L, R>.onSuccess(fn: (success: R) -> Unit): Either<L, R> =
        this.apply { if (this is Either.Right) fn(b) }

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))

fun <L, R> Either<L, R>.getOrElse(value: R): R =
        when (this) {
            is Either.Left -> value
            is Either.Right -> b
        }

//region coroutine helpers

suspend fun <T, L, R> Either<L, R>.flatMapSuspendable(fn: suspend (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }

suspend fun <L, R, T> Either<L, R>.foldSuspendable(fnL: suspend (L) -> T?, fnR: suspend (R) -> T?): T? =
        when (this) {
            is Either.Left -> fnL(a)
            is Either.Right -> fnR(b)
        }

suspend fun <L, R> Either<L, R>.onSuccessSuspended(fn: suspend (success: R) -> Unit): Either<L, R> =
        this.apply { if (this is Either.Right) fn(b) }

//endregion
