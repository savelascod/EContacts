package com.application.econtacts.app;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import asyncTasks.AsyncResponse;
import asyncTasks.GetTask;
import sqlLite.CompaniesDataSource;

/**
 * Created by mordreth on 10/11/15.
 */
public class SearchCompaniesActivity extends Activity implements AsyncResponse {
    private CompaniesDataSource dataSource;
    private ListView itemList;
    private SimpleCursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.search_filters);
        itemList = (ListView) findViewById(R.id.searchListView);

        itemList.setAdapter(adapter);
    }

    public void searchByFilters(View view) {
        EditText nameFilterInput = (EditText) findViewById(R.id.filterNameInput);
        Spinner clasificationFilterSpinner = (Spinner) findViewById(R.id.clasificationFilterSpinner);
        GetTask getTask = new GetTask(this, getApplicationContext(), nameFilterInput.getText().toString(),
                clasificationFilterSpinner.getSelectedItem().toString());
        getTask.execute();
    }

    public void searchAll(View view) {
        GetTask getTask = new GetTask(this, getApplicationContext());
        getTask.execute();
    }

    @Override
    public void processFinish(Cursor responseCursor) {
        responseCursor.moveToFirst();

        String data = responseCursor.getString(responseCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.NAME_COMPANY));
        Log.v("cursor", data);
    }
}
