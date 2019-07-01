package com.cuidadoanimal.petcare.gui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cuidadoanimal.petcare.gui.activities.WebViewActivity
import kotlinx.android.synthetic.main.fragment_articles.*

class ArticlesFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alimentacion.setOnClickListener {

            val url =
                "https://misanimales.com/alimentar-de-forma-adecuada-mascotas/"

            val intent = Intent(this.context!!, WebViewActivity::class.java)
            intent.putExtra("url", url)

            startActivity(intent)

        }

        atencion.setOnClickListener {

            val url =
                "http://mundo.petsy.mx/cuanto-tiempo-debo-dedicarle-a-mi-mascota/"

            val intent = Intent(this.context!!, WebViewActivity::class.java)
            intent.putExtra("url", url)

            startActivity(intent)

        }

        recreacion.setOnClickListener {

            val url =
                "https://veterinariaraymari.wordpress.com/2013/04/18/cuanto-tiempo-debo-dedicar-a-mi-mascota/"

            val intent = Intent(this.context!!, WebViewActivity::class.java)
            intent.putExtra("url", url)

            startActivity(intent)

        }

        cuidado.setOnClickListener {

            val url =
                "https://misanimales.com/trucos-y-consejos-para-peinar-tu-perro/"

            val intent = Intent(this.context!!, WebViewActivity::class.java)
            intent.putExtra("url", url)

            startActivity(intent)

        }

        limpieza.setOnClickListener {

            val url =
                "https://www.huffingtonpost.es/2015/11/02/como-banar-a-mi-perro-y-g_n_8415912.html"

            val intent = Intent(this.context!!, WebViewActivity::class.java)
            intent.putExtra("url", url)

            startActivity(intent)

        }

        medico.setOnClickListener {

            val url =
                "http://www.consumer.es/web/es/mascotas/perros/salud/vacunas-y-enfermedades/2018/03/27/225834.php"

            val intent = Intent(this.context!!, WebViewActivity::class.java)
            intent.putExtra("url", url)

            startActivity(intent)

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
