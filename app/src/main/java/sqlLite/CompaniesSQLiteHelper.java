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
        db.execSQL(CompaniesDataSource.CREATE_COMPANIES_SCRIPT);
        db.execSQL(CompaniesDataSource.INSERT_COMPANY_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CompaniesDataSource.DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
