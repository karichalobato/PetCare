package com.cuidadoanimal.petcare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cuidadoanimal.petcare.database.entities.*
import com.cuidadoanimal.petcare.database.entitiesDAO.*
import com.example.gameon.data.database.converters.Converters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration


@Database(
    entities = [User::class,
        Pet::class,
        Article::class,
        Tag::class,
        Vaccine::class,
        ArticleJOINTag::class,
        UserJOINArticle::class,
        VaccineJOINPet::class],
    version = 2,
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

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("CREATE TABLE `User_backup` AS SELECT `idUser`, `Name`, `Email` FROM `User`")
                database.execSQL("DROP TABLE `User`")
                database.execSQL("ALTER TABLE `User_backup` RENAME TO `User`")


                database.execSQL("CREATE TABLE `Pet_backup` AS SELECT `idPet`, `Name`, `Owner`, `Sex`, `Pet_Breed`, `Size` FROM `Pet`")
                database.execSQL("DROP TABLE `Pet`")
                database.execSQL("ALTER TABLE `Pet_backup` RENAME TO `Pet`")

            }
        }

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
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}