package me.mikasa.doubandemo.http

import rx.Subscriber

/**
 * Created by mikasa on 2018/12/26.
 */
open class BaseSubscriber<T>:Subscriber<T>() {
    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }
}