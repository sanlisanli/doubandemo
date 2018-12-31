package me.mikasa.doubandemo.http

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by mikasa on 2018/12/26.
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>){

    // 指定 Subscriber 的回调发生在主线程
    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())   // 指定 subscribe() 发生在 IO 线程
            .subscribe(subscriber)
}