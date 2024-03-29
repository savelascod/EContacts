package sqlLite;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by mordreth on 10/11/15.
 */
public class CompaniesDataSource {

    private CompaniesSQLiteHelper openHelper;
    private SQLiteDatabase database;

    //Data base meta

    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String LONG_TYPE = "bigint";

    //Table fields
    public static abstract class ColumnCompanies implements BaseColumns {
        public static final String COMPANIES_TABLE_NAME = "Companies";
        public static final String ID_COMPANY = "_id";
        public static final String NAME_COMPANY = "name";
        public static final String URL_COMPANY = "url";
        public static final String PHONE_COMPANY = "phone";
        public static final String EMAIL_COMPANY = "email";
        public static final String PS_COMPANY = "ps";
        public static final String CLASIFICATION_COMPANY = "clasification";
    }

    //Table creation Script
    public static final String CREATE_COMPANIES_SCRIPT =
            "create table " + ColumnCompanies.COMPANIES_TABLE_NAME + "(" +
                    ColumnCompanies.ID_COMPANY + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnCompanies.NAME_COMPANY + " " + STRING_TYPE + " not null," +
                    ColumnCompanies.URL_COMPANY + " " + STRING_TYPE + "," +
                    ColumnCompanies.PHONE_COMPANY + " " + LONG_TYPE + "," +
                    ColumnCompanies.EMAIL_COMPANY + " " + STRING_TYPE + " not null," +
                    ColumnCompanies.PS_COMPANY + " " + STRING_TYPE + "," +
                    ColumnCompanies.CLASIFICATION_COMPANY + " " + STRING_TYPE + " not null)";

    //default values
    public static final String INSERT_COMPANY_SCRIPT =
            "insert into " + ColumnCompanies.COMPANIES_TABLE_NAME + " values(" +
                    "null," +
                    "\"MyCreateam\"," +
                    "\"www.mycreateam.com\"," +
                    "123456789," +
                    "\"mycreateam@gmail.com\"," +
                    "\"software development, music production\"," +
                    "\"Software Factory\")," +
                    "(" +
                    "null," +
                    "\"Google\"," +
                    "\"www.google.com\"," +
                    "66666," +
                    "\"google@gmail.com\"," +
                    "\"Hardware, everything\"," +
                    "\"Consultory\")," +
                    "(" +
                    "null," +
                    "\"Tumix\"," +
                    "\"www.tumix.com\"," +
                    "5555," +
                    "\"tumix@gmail.com\"," +
                    "\"gums, nothing more\"," +
                    "\"Tailor-made Software\")";

    //delete table
    public static final String DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ColumnCompanies.COMPANIES_TABLE_NAME;

    public CompaniesDataSource() {
    }
}

