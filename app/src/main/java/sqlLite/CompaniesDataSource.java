package sqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by mordreth on 10/11/15.
 */
public class CompaniesDataSource {

    private CompaniesSQLiteHelper openHelper;
    private SQLiteDatabase database;

    //Data base meta
    public static final String COMPANIES_TABLE_NAME = "Companies";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String LONG_TYPE = "bigint";

    //Table fields
    public static class ColumnCompanies {
        public static final String ID_COMPANY = BaseColumns._ID;
        public static final String NAME_COMPANY = "name";
        public static final String URL_COMPANY = "url";
        public static final String PHONE_COMPANY = "phone";
        public static final String EMAIL_COMPANY = "email";
        public static final String PS_COMPANY = "ps";
        public static final String CLASIFICATION_COMPANY = "clasification";
    }

    //Table creation Script
    public static final String CREATE_COMPANIES_SCRIPT =
            "create table " + COMPANIES_TABLE_NAME + "(" +
                    ColumnCompanies.ID_COMPANY + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnCompanies.NAME_COMPANY + " " + STRING_TYPE + " not null," +
                    ColumnCompanies.URL_COMPANY + " " + STRING_TYPE + "," +
                    ColumnCompanies.PHONE_COMPANY + " " + LONG_TYPE + "," +
                    ColumnCompanies.EMAIL_COMPANY + " " + STRING_TYPE + " not null," +
                    ColumnCompanies.PS_COMPANY + " " + STRING_TYPE + "," +
                    ColumnCompanies.CLASIFICATION_COMPANY + " " + STRING_TYPE + " not null)";

    //default values
    public static final String INSERT_COMPANY_SCRIPT =
            "insert into " + COMPANIES_TABLE_NAME + " values(" +
                    "null," +
                    "\"MyCreateam\"," +
                    "\"www.mycreateam.com\"," +
                    "123456789," +
                    "\"mycreateam@gmail.com\"," +
                    "\"desarrollo de software, produccion musical\"," +
                    "\"Software Factory\")";


    public CompaniesDataSource(Context context) {
        openHelper = new CompaniesSQLiteHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void insert(ContentValues values) {
        database.insert(COMPANIES_TABLE_NAME, null, values);
    }
}

