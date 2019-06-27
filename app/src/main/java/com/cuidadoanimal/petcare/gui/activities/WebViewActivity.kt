package com.cuidadoanimal.petcare.gui.activities

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.SearchView
import com.cuidadoanimal.petcare.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    private var BASE_URL = "https://www.google.com"
    private val SEARCH_PATH = "/search?q=" //PATH que se mostrará al hacer una busqueda especifica.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        var getUrl = intent?.getStringExtra("url")

        if (getUrl != null) BASE_URL = getUrl

        //Refresh
        swipeRefresh.setOnRefreshListener {
            webView.reload()
        }

        //Search
/*
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean { //El usuario realiza la busqueda

                query?.let {
                    if (URLUtil.isValidUrl(it)){
                        //Es una URL
                        webView.loadUrl(it)
                    }else{
                        //No es una URL
                        webView.loadUrl("$BASE_URL$SEARCH_PATH$it")
                    }
                }
                return false
            }
        })
*/

        //WebView
        webView.webChromeClient = object : WebChromeClient() {

        }

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                /*searchView.setQuery(url, false) //vista de la busqueda del usuario.
*/
                swipeRefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                swipeRefresh.isRefreshing = false
            }

        }

        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.loadUrl(BASE_URL)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
