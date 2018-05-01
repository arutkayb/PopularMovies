package nd.centertableinc.popularmovies1.Data.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// Contract class
public class SQLiteMoviesDb extends SQLiteOpenHelper{
    private List<SQLiteMovies> sqLiteMovies;

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteMoviesDb(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteMovies = new ArrayList<>();
    }

    public void addSQLiteMovieToList(SQLiteMovies sqLiteMovie)
    {
        if(!sqLiteMovies.contains(sqLiteMovie))
            sqLiteMovies.add(sqLiteMovie);
    }

    public void removeSQLiteMovieFromList(SQLiteMovies sqLiteMovie)
    {
        if(sqLiteMovies.contains(sqLiteMovie))
            sqLiteMovies.remove(sqLiteMovie);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for(int i = 0; i < sqLiteMovies.size(); i++)
        {
            sqLiteMovies.get(i).onCreate(sqLiteDatabase);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        for(int it = 0; it < sqLiteMovies.size(); it++)
        {
            sqLiteMovies.get(it).onUpgrade(sqLiteDatabase,i ,i1);
        }
    }
}
