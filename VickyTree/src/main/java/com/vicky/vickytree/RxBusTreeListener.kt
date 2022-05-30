package com.vicky.vickytree

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBusTreeListener {

    private val nextClickAction = PublishSubject.create<Any>()

    fun createDialogEvent(event: Any) {
        nextClickAction.onNext(event)
    }

    fun <T> listenDialogEvents(eventType: Class<T>): Observable<T> =
            nextClickAction.ofType(eventType)


}