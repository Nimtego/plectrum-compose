package com.nimtego.plectrum_compose.presentation.common

sealed class Event
abstract class FeatureEvent : Event()

sealed class BaseEvent : FeatureEvent() {
    object First : BaseEvent()
    object Second : BaseEvent()
    object ListCompositeItem : BaseEvent()
    object ToMainView : BaseEvent()
    object LogoClick : BaseEvent()
}