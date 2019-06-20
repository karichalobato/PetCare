package com.cuidadoanimal.petcare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cuidadoanimal.petcare.database.entities.*
import com.cuidadoanimal.petcare.database.entitiesDAO.*
import com.example.gameon.data.database.converters.Converters

@Database(
    entities = [User::class,
        Pet::class,
        Article::class,
        Tag::class,
        Vaccine::class, ArticleJOINTag::class, UserJOINArticle::class, VaccineJOINPet::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PetCareRoomDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun petDAO(): PetDAO
    abstract fun articleDAO(): ArticleDAO
    abstract fun tagDAO(): TagDAO
    abstract fun vaccineDAO(): VaccineDAO
    abstract fun vaccineJOINpetDAO(): VaccineJOINPetDAO
    abstract fun userJOINarticleDAO(): UserJOINArticleDAO
    abstract fun articleJOINtagDAO(): ArticleJOINTagDAO

    companion object {
        @Volatile
        private var INSTANCE: PetCareRoomDatabase? = null

        fun getInstance(
            context: Context
        ): PetCareRoomDatabase {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context, PetCareRoomDatabase::class.java,
                        "PetCare_DataBase"
                    )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}