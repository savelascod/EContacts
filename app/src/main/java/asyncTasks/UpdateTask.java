package asyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import sqlLite.CompaniesDataSource;
import sqlLite.CompaniesSQLiteHelper;

/**
 * Created by mordreth on 10/11/15.
 */
public class UpdateTask extends AsyncTask<Void, Void, Integer> {
    private CompaniesSQLiteHelper dbHelper;
    private Context context;
    private AsyncResponse delegate;
    ContentValues values;
    private String companyId;

    public UpdateTask(AsyncResponse delegate, Context context, ContentValues values, String companyId) {
        this.dbHelper = new CompaniesSQLiteHelper(context);
        this.delegate = delegate;
        this.values = values;
        this.companyId = companyId;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Which row to update, based on the ID
        String selection = CompaniesDataSource.ColumnCompanies.ID_COMPANY + " LIKE ?";
        String[] selectionArgs = {String.valueOf(companyId)};

        int count = db.update(
                CompaniesDataSource.ColumnCompanies.COMPANIES_TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    @Override
    protected void onPostExecute(Integer result) {
        this.delegate.processFinish(result);
    }
}
