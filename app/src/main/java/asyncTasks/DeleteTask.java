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
public class DeleteTask extends AsyncTask<Void, Void, Integer> {
    private Context context;
    private AsyncResponse delegate;
    private String companyId;

    public DeleteTask(AsyncResponse delegate, Context context, String companyId) {
        this.context = context;
        this.companyId = companyId;
        this.delegate = delegate;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        Cursor responseCursor;
        // Gets the data repository in write mode
        CompaniesSQLiteHelper mDbHelper = new CompaniesSQLiteHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define 'where' part of query.
        String selection = CompaniesDataSource.ColumnCompanies.ID_COMPANY + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(companyId)};
        // Issue SQL statement.
        return db.delete(CompaniesDataSource.ColumnCompanies.COMPANIES_TABLE_NAME, selection, selectionArgs);

    }

    @Override
    protected void onPostExecute(Integer result) {
        this.delegate.processFinish(result);
    }
}
