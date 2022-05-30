package com.leader.eamana.helper

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val nextClickAction = PublishSubject.create<Any>()

    fun createDialogEvent(event: Any) {
        nextClickAction.onNext(event)
    }

    fun <T> listenDialogEvents(eventType: Class<T>): Observable<T> =
            nextClickAction.ofType(eventType)


}