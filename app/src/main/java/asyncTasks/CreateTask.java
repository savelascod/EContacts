package asyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import sqlLite.CompaniesDataSource;
import sqlLite.CompaniesSQLiteHelper;

/**
 * Created by mordreth on 10/2/15.
 */
public class CreateTask extends AsyncTask<Void, Void, Cursor> {

    private CompaniesSQLiteHelper dbHelper;
    private Context context;
    private AsyncResponse delegate;
    ContentValues values;


    public CreateTask(AsyncResponse delegate, Context context, ContentValues values) {
        this.dbHelper = new CompaniesSQLiteHelper(context);
        this.delegate = delegate;
        this.values = values;
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        Cursor responseCursor = null;
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.insert(CompaniesDataSource.ColumnCompanies.COMPANIES_TABLE_NAME, null, values);

        return responseCursor;
    }

    @Override
    protected void onPostExecute(Cursor responseCursor) {
        this.delegate.processFinish(responseCursor);
    }
}