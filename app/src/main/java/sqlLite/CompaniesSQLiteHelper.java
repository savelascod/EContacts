package sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mordreth on 10/11/15.
 */


public class CompaniesSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Companies.db";
    public static final int DATABASE_VERSION = 1;

    public CompaniesSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}