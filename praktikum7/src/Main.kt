import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.io.File
import java.io.FileNotFoundException

fun main() {
	val subscriptions = CompositeDisposable()
	
	val firstObservable = Observable.create {
		it.onNext("Satu")
		it.onError(RuntimeException("Error"))
		it.onNext("Dua")
		it.onComplete()
	}
	
	val secondObservable = Observable.create {
		it.onNext("Tiga")
	}
	
	val firstSubscriber = firstObservable.subscribeBy(
		onNext = { println(it) },
		onComplete = { println("Selesai") },
		onError = { println(it) }
	)
	subscriptions.add(firstSubscriber)
	
	val secondSubscriber = secondObservable.subscribeBy(
		onNext = { println(it) },
	)
	subscriptions.add(secondSubscriber)
	
	fun loadText(fileName: String) = Single.create create@{
		//read file from this package
		val file = File("praktikum7/src/$fileName")
		if (!file.exists()) {
			it.onError(FileNotFoundException("File $fileName tidak ditemukan"))
			return@create
		}
		val contents = file.readText(Charsets.UTF_8)
		it.onSuccess(contents)
	}
	
	//create subscriber
	val subscriber = loadText("baca.txt")
		.subscribeBy(
			onSuccess = { println(it) },
			onError = { println("Error, $it") }
		)
	
	subscriptions.add(subscriber)
	
	subscriptions.dispose()
}
