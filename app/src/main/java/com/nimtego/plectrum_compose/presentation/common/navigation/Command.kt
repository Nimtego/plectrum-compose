package com.nimtego.plectrum_compose.presentation.common.navigation


//TODO sealed Interface and classes
interface Command
data class Forward(val screen: Screen) : Command
data class Replace(val screen: Screen) : Command
class Back : Command
data class BackTo(val screen: Screen?) : Command