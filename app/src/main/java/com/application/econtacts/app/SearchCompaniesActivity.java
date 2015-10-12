package com.application.econtacts.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
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

        displayListView(responseCursor);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void displayListView(Cursor itemCursor) {
        // The desired columns to be bound
        String[] columns = new String[]{
                CompaniesDataSource.ColumnCompanies.ID_COMPANY,
                CompaniesDataSource.ColumnCompanies.NAME_COMPANY,
                CompaniesDataSource.ColumnCompanies.CLASIFICATION_COMPANY
        };
        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.companyNameText,
                R.id.companyClasificationText
        };
        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
                this, R.layout.company,
                itemCursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.searchListView);
        // Assign adapter to ListView
        if (itemCursor.getCount() != 0) {
            listView.setAdapter(dataAdapter);
        }
    }
}
