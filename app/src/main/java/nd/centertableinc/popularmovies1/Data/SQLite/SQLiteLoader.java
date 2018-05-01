package nd.centertableinc.popularmovies1.Data.SQLite;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

public class SQLiteLoader extends CursorLoader {
    public SQLiteLoader(Context context) {
        super(context);
    }

    public SQLiteLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }
}
