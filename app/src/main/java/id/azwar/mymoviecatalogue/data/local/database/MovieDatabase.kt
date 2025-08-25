package id.azwar.mymoviecatalogue.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import android.content.Context
import id.azwar.mymoviecatalogue.data.local.dao.FavoriteMovieDao
import id.azwar.mymoviecatalogue.data.local.entity.FavoriteMovieEntity

@Database(
    entities = [FavoriteMovieEntity::class],
    version = 3,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    
    abstract fun favoriteMovieDao(): FavoriteMovieDao
    
    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null
        
        // Migration from version 1 to 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create temporary table with new schema
                database.execSQL(
                    "CREATE TABLE favorite_movies_new (" +
                    "id INTEGER NOT NULL PRIMARY KEY, " +
                    "title TEXT NOT NULL, " +
                    "originalTitle TEXT NOT NULL, " +
                    "overview TEXT NOT NULL, " +
                    "posterPath TEXT, " +
                    "backdropPath TEXT, " +
                    "releaseDate TEXT NOT NULL, " +
                    "voteAverage REAL NOT NULL, " +
                    "voteCount INTEGER NOT NULL, " +
                    "popularity REAL NOT NULL, " +
                    "adult INTEGER NOT NULL, " +
                    "originalLanguage TEXT NOT NULL, " +
                    "mediaType TEXT NOT NULL, " +
                    "video INTEGER NOT NULL, " +
                    "addedAt INTEGER NOT NULL" +
                    ")"
                )
                
                // Copy data from old table to new table
                database.execSQL(
                    "INSERT INTO favorite_movies_new (" +
                    "id, title, originalTitle, overview, posterPath, backdropPath, " +
                    "releaseDate, voteAverage, voteCount, popularity, adult, " +
                    "originalLanguage, mediaType, video, addedAt" +
                    ") SELECT " +
                    "id, title, originalTitle, overview, posterPath, backdropPath, " +
                    "releaseDate, voteAverage, voteCount, popularity, adult, " +
                    "originalLanguage, mediaType, video, addedAt " +
                    "FROM favorite_movies"
                )
                
                // Drop old table
                database.execSQL("DROP TABLE favorite_movies")
                
                // Rename new table to original name
                database.execSQL("ALTER TABLE favorite_movies_new RENAME TO favorite_movies")
            }
        }
        
        // Migration from version 2 to 3
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create temporary table with nullable mediaType
                database.execSQL(
                    "CREATE TABLE favorite_movies_new (" +
                    "id INTEGER NOT NULL PRIMARY KEY, " +
                    "title TEXT NOT NULL, " +
                    "originalTitle TEXT NOT NULL, " +
                    "overview TEXT NOT NULL, " +
                    "posterPath TEXT, " +
                    "backdropPath TEXT, " +
                    "releaseDate TEXT NOT NULL, " +
                    "voteAverage REAL NOT NULL, " +
                    "voteCount INTEGER NOT NULL, " +
                    "popularity REAL NOT NULL, " +
                    "adult INTEGER NOT NULL, " +
                    "originalLanguage TEXT NOT NULL, " +
                    "mediaType TEXT, " +
                    "video INTEGER NOT NULL, " +
                    "addedAt INTEGER NOT NULL" +
                    ")"
                )
                
                // Copy data from old table to new table
                database.execSQL(
                    "INSERT INTO favorite_movies_new (" +
                    "id, title, originalTitle, overview, posterPath, backdropPath, " +
                    "releaseDate, voteAverage, voteCount, popularity, adult, " +
                    "originalLanguage, mediaType, video, addedAt" +
                    ") SELECT " +
                    "id, title, originalTitle, overview, posterPath, backdropPath, " +
                    "releaseDate, voteAverage, voteCount, popularity, adult, " +
                    "originalLanguage, mediaType, video, addedAt " +
                    "FROM favorite_movies"
                )
                
                // Drop old table
                database.execSQL("DROP TABLE favorite_movies")
                
                // Rename new table to original name
                database.execSQL("ALTER TABLE favorite_movies_new RENAME TO favorite_movies")
            }
        }
        
        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
