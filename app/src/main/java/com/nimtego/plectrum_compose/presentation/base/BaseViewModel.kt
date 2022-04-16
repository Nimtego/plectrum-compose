package com.nimtego.plectrum_compose.presentation.base

import androidx.lifecycle.*
import com.nimtego.plectrum_compose.common.Failure
import com.nimtego.plectrum_compose.common.ServerError
import com.nimtego.plectrum_compose.presentation.common.FeatureEvent
import com.nimtego.plectrum_compose.presentation.common.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<I : FeatureEvent> : ViewModel(), LifecycleObserver {

    var isLoading = MutableLiveData<Boolean>()

    var onError: MutableLiveData<Throwable?> = MutableLiveData()
    val compositeJobs = HashMap<String, Job?>()

    open fun onError(it: Throwable) {
        it.printStackTrace()
        // Handle errors here in subclass
        //onError.value = it
    }

    open fun onViewVisibleAgain() {}

    open fun onViewHidden() {}

    abstract fun onEvent(event: I)

    //FIXME later do abstract
    protected open fun transformThrowable(throwable: Throwable): Failure {
        return ServerError //when (throwable) {
        //is NoInternetConnectionError -> {
        // NetworkConnection
        //}
        //else -> {
        //ServerError
        // }
        // }
    }


    protected open fun <D : Any> handleLoading(liveDate: MutableLiveData<ViewState<D>>) {
        liveDate.postValue(ViewState.loading())
    }

    protected open fun <D : Any> handleSuccess(liveDate: MutableLiveData<ViewState<D>>, data: D) {
        liveDate.postValue(ViewState.success(data))
    }

    protected open fun <D : Any> handleFailure(
        liveDate: MutableLiveData<ViewState<D>>,
        data: D? = null,
        failure: Failure
    ) {
        liveDate.postValue(ViewState.failure(failure))
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onViewWasDestroy() {
    }


    protected fun CoroutineScope.launchAutoCancel(
        jobTag: String,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        if (compositeJobs.containsKey(jobTag) && compositeJobs[jobTag] != null) {
            compositeJobs[jobTag]?.cancel()
            compositeJobs.remove(jobTag)
        }
        return this.launch(
            context = context,
            start = start,
            block = block
        ).putJob(jobTag)
    }

    protected suspend fun <T> Flow<T>.collectWithCatch(
        actionCatch: suspend FlowCollector<T>.(cause: Throwable) -> Unit = { it.printStackTrace() },
        action: suspend (value: T) -> Unit
    ): Unit = this.catch { actionCatch(it) }.collect { action(it) }

    private fun Job.putJob(jobTag: String): Job {
        compositeJobs[jobTag] = this
        return this
    }
}