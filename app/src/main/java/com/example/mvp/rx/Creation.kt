package com.example.mvp.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


class Creation {
    fun exec(){
        Consumer(Producer()).execLambda()
    }

    class Producer {
        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }

    }

    class Consumer(val producer: Producer){

        val stringObserver = object : Observer<String> {
            var disposable: Disposable? = null
            override fun onSubscribe(d: Disposable) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(t: String) {
                println("onNext: $t")

            }

            override fun onError(e: Throwable) {
                println("onError: ${e?.message}")
            }

            override fun onComplete() {
                println("onComplete")
            }
        }

        fun exec(){
            execJust()
        }

        fun execJust(){
            producer.just()
                .subscribe(stringObserver)
        }

        fun execLambda(){
            val disposable = producer.just()
                .subscribe({s ->
                    println("onNext: $s")
                }, { e ->
                    println("onError: $e")
                }, {
                    println("onComplete")
                })
        }
    }
}