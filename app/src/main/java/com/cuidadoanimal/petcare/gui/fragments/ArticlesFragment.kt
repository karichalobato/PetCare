package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_articles.*

class ArticlesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alimentacion.setOnClickListener {

            val url =
                "https://github.com/karichalobato/GridLayout/blob/master/app/src/main/java/com/example/gridlayout/MainActivity.java"

            var intent = Intent()
            intent.putExtra("url", url)

            activity!!.startActivity(intent)

        }

        atencion.setOnClickListener {

            val url =
                "https://github.com/karichalobato/GridLayout/blob/master/app/src/main/java/com/example/gridlayout/MainActivity.java"

            var intent = Intent()
            intent.putExtra("url", url)

            activity!!.startActivity(intent)

        }

        recreacion.setOnClickListener {

            val url =
                "https://github.com/karichalobato/GridLayout/blob/master/app/src/main/java/com/example/gridlayout/MainActivity.java"

            var intent = Intent()
            intent.putExtra("url", url)

            activity!!.startActivity(intent)

        }

        cuidado.setOnClickListener {

            val url =
                "https://github.com/karichalobato/GridLayout/blob/master/app/src/main/java/com/example/gridlayout/MainActivity.java"

            var intent = Intent()
            intent.putExtra("url", url)

            activity!!.startActivity(intent)

        }

        limpieza.setOnClickListener {

            val url =
                "https://github.com/karichalobato/GridLayout/blob/master/app/src/main/java/com/example/gridlayout/MainActivity.java"

            var intent = Intent()
            intent.putExtra("url", url)

            activity!!.startActivity(intent)

        }

        medico.setOnClickListener {

            val url =
                "https://github.com/karichalobato/GridLayout/blob/master/app/src/main/java/com/example/gridlayout/MainActivity.java"

            var intent = Intent()
            intent.putExtra("url", url)

            activity!!.startActivity(intent)

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.cuidadoanimal.petcare.R.layout.fragment_articles, container, false)
    }

}
