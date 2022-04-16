package com.nimtego.plectrum_compose.common

// Extend this class for feature specific failures.
abstract class FeatureFailure : Failure()

sealed class Failure
sealed class NetworkFailure : Failure()
sealed class DatabaseFailure : Failure()
sealed class CacheFailure : Failure()
sealed class AuthFailure : Failure()

sealed class PartnerFailure : Failure()
object PartnerIdNotFound : PartnerFailure()

sealed class QrCode : FeatureFailure()
sealed class BonusFailure : FeatureFailure()

object AuthLoginPasswordFailure : AuthFailure()

object DatabaseError : DatabaseFailure()
object DataBaseEmpty : DatabaseFailure()

object EmptyCacheFailure : CacheFailure()
object DataNotCached: CacheFailure()

object TimeEndFailure : Failure()

object NetworkConnection : NetworkFailure()
object ServerError : NetworkFailure()
object BadRequest : NetworkFailure()
object Unauthorized : NetworkFailure()
object Forbidden : NetworkFailure()
object NotFound : NetworkFailure()
object InternalServerError : NetworkFailure()
data class NetworkCodeFailure(val errorCode: String) : NetworkFailure()

abstract class BankCardFailure : NetworkFailure()
object WrongEmailFailure : BankCardFailure()

object EmptyResponseBody : NetworkFailure()
