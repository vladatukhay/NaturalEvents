package com.example.myevents.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myevents.R
import com.example.myevents.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        activity?.title = getString(R.string.title_info)

        //webView.webViewClient = DefaultWebViewClient()
        binding.webView.settings.javaScriptEnabled
        binding.webView.loadUrl("https://bplanet.si/")
        return binding.root
    }

//    open class DefaultWebViewClient : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//            return false
//        }
//    }

    companion object {
        val TAG: String = InfoFragment::class.java.simpleName
        fun newInstance() = InfoFragment()
    }
}