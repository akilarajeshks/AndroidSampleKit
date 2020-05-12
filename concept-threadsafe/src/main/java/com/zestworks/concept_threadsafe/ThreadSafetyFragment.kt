package com.zestworks.concept_threadsafe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_thread_safety.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class ThreadSafetyFragment : Fragment(R.layout.fragment_thread_safety) {
    private val countLiveData = MutableLiveData<Int>()
    private var currentCount: AtomicInteger = AtomicInteger(0)
    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_start_safe.setOnClickListener {
            currentCount = AtomicInteger(0)
            val thread1 = Thread {
                repeat(50) {
                    increment()
                    countLiveData.postValue(currentCount.get())
                    Thread.sleep(TimeUnit.MILLISECONDS.toMillis(5))
                }
            }
            val thread2 = Thread {
                repeat(50) {
                    increment()
                    countLiveData.postValue(currentCount.get())
                    Thread.sleep(TimeUnit.MILLISECONDS.toMillis(4))
                }
            }
            thread1.start()
            thread2.start()
        }
        countLiveData.observe(viewLifecycleOwner, Observer {
            textView_counter.text = it.toString()
        })

        btn_start_unsafe.setOnClickListener {
            count = 0
            val thread1 = Thread {
                repeat(50) {
                    countLiveData.postValue(count++)
                    Thread.sleep(TimeUnit.MILLISECONDS.toMillis(5))
                }
            }
            val thread2 = Thread {
                repeat(50) {
                    countLiveData.postValue(count++)
                    Thread.sleep(TimeUnit.MILLISECONDS.toMillis(4))
                }
            }
            thread1.start()
            thread2.start()
        }
        countLiveData.observe(viewLifecycleOwner, Observer {
            textView_counter.text = it.toString()
        })

        webView_thread.loadUrl("https://raw.githubusercontent.com/akilarajeshks/AndroidSampleKit/master/concept-threadsafe/src/main/java/com/zestworks/concept_threadsafe/ThreadSafetyFragment.kt")
    }

    private fun increment() {
        currentCount.getAndIncrement()
    }

}
