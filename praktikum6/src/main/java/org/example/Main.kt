package org.example

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun main() {
	val firstObservable = Observable.just("satu", "dua", "tiga")
	
	val firstSubscriber = firstObservable.subscribe { println("Subscriber Pertama: $it") }
	val secondSubscriber = firstObservable.subscribe { println("Subscriber Kedua: $it") }
	val thirdSubscriber = firstObservable.subscribeBy(
		onNext = { println(it) },
		onComplete = { println("selesai") },
		onError = { println("gangguan") }
	)
}