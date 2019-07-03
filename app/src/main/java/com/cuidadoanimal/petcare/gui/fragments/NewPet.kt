package com.cuidadoanimal.petcare.gui.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.AppConstants
import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import com.google.firebase.firestore.CollectionReference
import kotlinx.android.synthetic.main.fragment_newpet.*
import kotlinx.android.synthetic.main.fragment_newpet.view.*

class NewPet : Fragment(), View.OnClickListener {

    private var listenerTool: NewPetListener? = null

    private lateinit var info: PetCareViewModel

    private lateinit var speciesCollection: CollectionReference

    private lateinit var breedsCollection: CollectionReference

    private var sex = "F"
    private var species = "Cat"
    private var breed = "British shorthair"

    interface NewPetListener {
        fun insertPet(petName: String, petSpecies: String, petBreed: String, petSex: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewPetListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz NewPetListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info = ViewModelProviders.of(this).get(PetCareViewModel::class.java)
        speciesCollection = info.getSpecies()
        breedsCollection = info.getCatBreeds()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_newpet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddButton(view)

        view.findViewById<Button>(R.id.rb_sex_female)?.setOnClickListener(this)
        view.findViewById<Button>(R.id.rb_sex_male)?.setOnClickListener(this)

        initSpinners()
    }

    private fun initSpinners() {

        /** SPINNER DE BREEDS */
        val breeds = ArrayList<String>()

        val bAdapter = setUpSpinnerAdapter(breeds, spinner_breed)

        setUpSpinnerOptions(
            breedsCollection,
            AppConstants.DOCUMENT_FIELD_NAME,
            breeds,
            bAdapter
        )

        spinner_breed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                this@NewPet.breed = spinner_breed?.selectedItem.toString()
            }

        }

        /** SPINNER DE SPECIES */
        val species = ArrayList<String>()

        val sAdapter = setUpSpinnerAdapter(species, spinner_species)

        setUpSpinnerOptions(
            speciesCollection,
            AppConstants.DOCUMENT_FIELD_NAME,
            species,
            sAdapter
        )

        /** Define el manejo de las selecciones (o la falta de ellas) en la lista. */
        spinner_species.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            val initialBreed = ArrayList<String>()

            override fun onNothingSelected(parent: AdapterView<*>?) {
                breedsCollection = info.getCatBreeds()
                setUpSpinnerOptions(
                    breedsCollection,
                    AppConstants.DOCUMENT_FIELD_NAME,
                    initialBreed,
                    bAdapter
                )
            }

            /** Actualiza la lista de razas según la especie seleccionada */
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                this@NewPet.species = spinner_species.selectedItem.toString()

                if (!breeds.isNullOrEmpty()) breeds.clear()

                when (spinner_species.selectedItem.toString()) {
                    AppConstants.CAT_DOCUMENT_NAME -> {
                        breedsCollection = info.getCatBreeds()
                    }

                    AppConstants.DOG_DOCUMENT_NAME -> {
                        breedsCollection = info.getDogBreeds()
                    }

                    AppConstants.HORSE_DOCUMENT_NAME -> {
                        breedsCollection = info.getHorseBreeds()
                    }
                }

                setUpSpinnerOptions(
                    breedsCollection,
                    AppConstants.DOCUMENT_FIELD_NAME,
                    breeds,
                    bAdapter
                )

            }
        }
    }

    private fun setUpSpinnerAdapter(array: ArrayList<String>, spinner: Spinner): ArrayAdapter<String> {

        val adapter = ArrayAdapter(
            this@NewPet.context!!,
            android.R.layout.simple_spinner_item,
            array
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        return adapter
    }


    /** Obtiene los resultados de la consulta y los coloca en una lista, que se asignará al adaptador del spinner*/
    private fun setUpSpinnerOptions(
        collection: CollectionReference,
        field: String,
        optionsArray: ArrayList<String>,
        adapter: ArrayAdapter<String>
    ) {
        collection.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val item = document.getString(field)
                    item?.let { optionsArray.add(it) }
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    /** Define el comportamiento del botón de agregar mascotas.
     *  Envía a la actividad principal los datos seleccionados por el usuario
     *  para la inserción de la mascota en la base de datos. */
    private fun initAddButton(container: View) =
        container.bt_new_pet.setOnClickListener {

            if (PetName.text.isEmpty()) {
                Toast.makeText(this.context!!, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context!!, "Mascota añadida exitosamente", Toast.LENGTH_SHORT).show()

                listenerTool?.insertPet(
                    PetName.text.toString(),
                    species,
                    breed,
                    sex
                )

                findNavController(this)
                    .navigate(R.id.action_newpet_to_main)
            }
        }

    /** Asignación de valores del radio button*/
    override fun onClick(view: View?) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.rb_sex_female ->
                    if (checked) {
                        this.sex = "F"
                    }
                R.id.rb_sex_male ->
                    if (checked) {
                        this.sex = "M"
                    }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}
