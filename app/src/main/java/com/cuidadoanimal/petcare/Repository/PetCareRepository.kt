package com.cuidadoanimal.petcare.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.cuidadoanimal.petcare.Database.Entities.*
import com.cuidadoanimal.petcare.Database.EntitiesDAO.*

class PetCareRepository (private val UserDAO:UserDAO,private val PetDAO:PetDAO,private val ArticleDAO:ArticleDAO, private val TagDAO:TagDAO,private val VaccineDAO:VaccineDAO,private val ArticleJOINTagDAO:ArticleJOINTagDAO, private val UserJOINArticleDAO:UserJOINArticleDAO,private val VaccineJOINPetDAO:VaccineJOINPetDAO) {
    //GetAll
    val allUsers:LiveData<List<User>> = UserDAO.getAllUsers()
    val allPets:LiveData<List<Pet>> = PetDAO.getAllPets()
    val allTags:LiveData<List<Tag>> = TagDAO.getAllTags()
    val allArticle:LiveData<List<Article>> = ArticleDAO.getAllArticles()
    val allVaccines:LiveData<List<Vaccine>> = VaccineDAO.getAllVaccines()
    val allVaccineJOINPets:LiveData<List<VaccineJOINPet>> = VaccineJOINPetDAO.getAllVaccineJOINPets()
    val allUserJOINArticles:LiveData<List<UserJOINArticle>> = UserJOINArticleDAO. getAllUserJOINArticles()
    val allArticleJOINTags:LiveData<List<ArticleJOINTag>> = ArticleJOINTagDAO.getAllArticleJOINTags()

    //User
    @WorkerThread
    suspend fun insertUser(user: User) {
        UserDAO.insertUser(user)
    }

    fun setProjectImage(username: String,profile_picture:String){
        UserDAO.setUserImage(username,profile_picture)
    }

    fun getUserByName(name: String): LiveData<List<User>>{
        return UserDAO.getUserByName(name)
    }

    fun deleteUsers(){
        UserDAO.deleteUsers()
    }

    fun deleteUserByID(idUser: Int): LiveData<List<User>>{
        return UserDAO.deleteUserByID(idUser)
    }

    //Pet
    @WorkerThread
    suspend fun insertPet(pet: Pet) {
        PetDAO.insertPet(pet)
    }

    fun geyPetByName(name:String): LiveData<List<Pet>>{
        return PetDAO.geyPetByName(name)
    }

    fun setPetImage(name: String,pet_photo:String){
        PetDAO.setPetImage(name,pet_photo)
    }

    fun deletePetByID(idPet: Int): LiveData<List<Pet>>{
        return PetDAO.deletePetByID(idPet)
    }

    fun deletePets(){
        PetDAO.deletePets()
    }

    //Article
    @WorkerThread
    suspend fun insertArticle(article:Article){
        ArticleDAO.insertArticle(article)
    }

    fun getArticleByTitle(title:String): LiveData<List<Article>>{
        return ArticleDAO.getArticleByTitle(title)
    }

    fun deleteArticleByID(idArticle:Int): LiveData<List<Article>>{
        return ArticleDAO.deleteArticleByID(idArticle)
    }

    fun deleteArticles(){
        ArticleDAO.deleteArticles()
    }

    //TagDAO
    @WorkerThread
    suspend fun insertTag(tag: Tag){
        TagDAO.insertTag(tag)
    }

    fun getTagByName(name:String): LiveData<List<Tag>>{
        return TagDAO.getTagByName(name)
    }

    fun deleteTagByID(idTag:Int): LiveData<List<Tag>>{
        return TagDAO.deleteTagByID(idTag)
    }

    fun deleteTags(){
        TagDAO.deleteTags()
    }

    //VaccineDAO
    @WorkerThread
    suspend fun insertVaccine(vaccine: Vaccine){
        VaccineDAO.insertVaccine(vaccine)
    }

    fun getVaccineByName(name: String): LiveData<List<Vaccine>>{
        return VaccineDAO.getVaccineByName(name)
    }

    fun deleteVaccineByID(idVaccine: Int): LiveData<List<Vaccine>>{
        return VaccineDAO.deleteVaccineByID(idVaccine)
    }

    fun deleteVaccines(){
        VaccineDAO.deleteVaccines()
    }

    //VaccineJOINPet
    @WorkerThread
    suspend fun insert(vp: VaccineJOINPet) {
        VaccineJOINPetDAO.insert(vp)
    }

    fun getVaccineOfPets(vacunaID: Int): LiveData<List<Vaccine>> {
    return VaccineJOINPetDAO.getVaccineOfPets(vacunaID)

    }

    fun getPetsOfVaccines(mascotaID:Int):LiveData<List<Pet>>{
        return VaccineJOINPetDAO.getPetsOfVaccines(mascotaID)
    }

    //ArticleJOINTag
    @WorkerThread
    suspend fun insert(at:ArticleJOINTag){
        ArticleJOINTagDAO.insert(at)
    }
    fun getArticleOfTags(articleID:Int):LiveData<List<Article>>{
        return ArticleJOINTagDAO.getArticleOfTags(articleID)
    }

    fun getTagsOfArticle(tagID:Int): LiveData<List<Tag>>{
        return ArticleJOINTagDAO.getTagsOfArticle(tagID)
    }

    //UserJOINArticle
    @WorkerThread
    suspend fun insert(ua:UserJOINArticle){
        UserJOINArticleDAO.insert(ua)
    }

    fun getUserOfArticles(userID:Int): LiveData<List<User>>{
        return UserJOINArticleDAO.getUserOfArticles(userID)
    }

    fun getArticlesOfUsers(articleID:Int): LiveData<List<Article>>{
        return UserJOINArticleDAO.getArticlesOfUsers(articleID)
    }

}