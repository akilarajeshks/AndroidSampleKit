package com.zestworks.androidsamplekit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_navigate_leak.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_leakFragment)
        }
    }
}