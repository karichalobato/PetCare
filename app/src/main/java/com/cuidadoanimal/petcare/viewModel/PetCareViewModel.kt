package com.cuidadoanimal.petcare.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cuidadoanimal.petcare.database.entities.*
import com.cuidadoanimal.petcare.database.PetCareRoomDatabase
import com.cuidadoanimal.petcare.repository.PetCareRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetCareViewModel(app: Application) : AndroidViewModel(app) {

    private val Repository: PetCareRepository
    val allUsers: LiveData<List<User>>
    val allPets: LiveData<List<Pet>>
    val allTags: LiveData<List<Tag>>
    val allArticle: LiveData<List<Article>>
    val allVaccines: LiveData<List<Vaccine>>
    val allVaccineJOINPets: LiveData<List<VaccineJOINPet>>
    val allUserJOINArticles: LiveData<List<UserJOINArticle>>
    val allArticleJOINTags: LiveData<List<ArticleJOINTag>>

    init {
        val userDAO = PetCareRoomDatabase.getInstance(app).userDAO()
        val petDAO = PetCareRoomDatabase.getInstance(app).petDAO()
        val articleDAO = PetCareRoomDatabase.getInstance(app).articleDAO()
        val tagDAO = PetCareRoomDatabase.getInstance(app).tagDAO()
        val vaccineDAO = PetCareRoomDatabase.getInstance(app).vaccineDAO()
        val articleJOINtagDAO = PetCareRoomDatabase.getInstance(app).articleJOINtagDAO()
        val userJOINarticle = PetCareRoomDatabase.getInstance(app).userJOINarticleDAO()
        val vaccineJOINPet = PetCareRoomDatabase.getInstance(app).vaccineJOINpetDAO()

        Repository = PetCareRepository(
            userDAO, petDAO, articleDAO, tagDAO, vaccineDAO,
            articleJOINtagDAO, userJOINarticle, vaccineJOINPet
        )
        allUsers = Repository.allUsers
        allPets = Repository.allPets
        allTags = Repository.allTags
        allArticle = Repository.allArticle
        allVaccines = Repository.allVaccines
        allVaccineJOINPets = Repository.allVaccineJOINPets
        allUserJOINArticles = Repository.allUserJOINArticles
        allArticleJOINTags = Repository.allArticleJOINTags
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insertUser(user)
    }

    fun insert(pet: Pet) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insertPet(pet)
    }

    fun insert(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insertArticle(article)
    }

    fun insert(tag: Tag) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insertTag(tag)
    }

    fun insert(vaccine: Vaccine) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insertVaccine(vaccine)
    }

    fun insert(vp: VaccineJOINPet) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insert(vp)
    }

    fun insert(at: ArticleJOINTag) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insert(at)
    }

    fun insert(ua: UserJOINArticle) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insert(ua)
    }

}