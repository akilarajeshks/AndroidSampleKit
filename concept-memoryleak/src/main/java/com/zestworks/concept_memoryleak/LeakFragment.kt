package com.zestworks.concept_memoryleak

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_leak.*
import java.util.concurrent.TimeUnit


class LeakFragment : Fragment(R.layout.fragment_leak) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_action_leak.setOnClickListener {
            LeakyThread(activity!!).start()
            Toast.makeText(context, "Rotate for memory leak", Toast.LENGTH_SHORT).show()
        }

        webView.loadUrl("https://raw.githubusercontent.com/akilarajeshks/AndroidSampleKit/master/concept-memoryleak/src/main/java/com/zestworks/concept_memoryleak/LeakFragment.kt")
    }
}

class LeakyThread(val activity: Activity) : Thread() {
    override fun run() {
        super.run()
        sleep(TimeUnit.MINUTES.toMillis(3))
    }
}