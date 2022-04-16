package com.nimtego.plectrum_compose.presentation.splash

import androidx.compose.foundation.lazy.LazyListState
import cafe.adriel.voyager.navigator.Navigator
import com.nimtego.plectrum_compose.presentation.base.BaseViewModel
import com.nimtego.plectrum_compose.presentation.common.BaseEvent
import com.nimtego.plectrum_compose.presentation.main.MainAppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<BaseEvent>() {

    var stateList: LazyListState? = null
    private val title: MutableSharedFlow<String> = MutableStateFlow("Screen first: title 0")
    val flowTitle: Flow<String> = title

    var navigator: Navigator? = null

//    private val simpleItem = MutableStateFlow<List<SimpleItemModel>>(emptyList())
//    val flowSimpleItem: Flow<List<SimpleItemModel>> = simpleItem

    init {
//        viewModelScope.launch {
//            for (i in 1..5) {
//                title.emit("Screen 1: title $i")
//                delay(2000)
//            }
//        }
//        viewModelScope.launch {
//            for (i in 1..20) {
//                simpleItem.value = simpleItem.value + getRand()
//                delay(200)
//            }
//        }
    }

    override fun onEvent(event: BaseEvent) {
        when(event) {
            BaseEvent.LogoClick -> navigator?.push(MainAppScreen)
            //BaseEvent.Second -> routeNavigator.navigateToRoute(AppScreens.SecondScreen)
        }
    }

    override fun navigator(navigator: Navigator) {
        this.navigator = navigator
    }

//    fun getRand(): List<SimpleItemModel> {
//        return listOf(SimpleItemModel(
//            id = 3,
//            name = "Test ${Random.nextInt(1000)}",
//            description = "Description 3",
//            imageRes = R.drawable.ic_launcher_background
//        ))
//    }
}