package asyncTasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import sqlLite.CompaniesDataSource;
import sqlLite.CompaniesSQLiteHelper;

/**
 * Created by mordreth on 10/11/15.
 */
public class GetTask extends AsyncTask<Void, Void, Cursor> {
    private Context context;
    private AsyncResponse delegate;
    private String name;
    private String clasification;


    public GetTask(AsyncResponse delegate, Context context, String name, String clasification) {
        this.delegate = delegate;
        this.name = name;
        this.clasification = clasification;
        this.context = context;
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        Cursor responseCursor;
        // Gets the data repository in write mode
        CompaniesSQLiteHelper mDbHelper = new CompaniesSQLiteHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CompaniesDataSource.ColumnCompanies.NAME_COMPANY,
                CompaniesDataSource.ColumnCompanies.CLASIFICATION_COMPANY
        };

        String selection = CompaniesDataSource.ColumnCompanies.NAME_COMPANY + " = ?"
                + " AND " + CompaniesDataSource.ColumnCompanies.CLASIFICATION_COMPANY + "= ?";

        String[] selectionArgs = {
                name,
                clasification
        };
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                CompaniesDataSource.ColumnCompanies.NAME_COMPANY + " DESC";

        responseCursor = db.query(
                CompaniesDataSource.ColumnCompanies.COMPANIES_TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return responseCursor;
    }

    @Override
    protected void onPostExecute(Cursor responseCursor) {
        this.delegate.processFinish(responseCursor);
    }
}