package nd.centertableinc.popularmovies1.Data.SQLite;

import android.database.sqlite.SQLiteDatabase;

public interface SQLiteMovies {
    void onCreate(SQLiteDatabase sqLiteDatabase);
    void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1);
}
