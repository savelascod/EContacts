package com.application.econtacts.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import asyncTasks.AsyncResponse;
import asyncTasks.DeleteTask;
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

    public void deleteCompany(View view) {
        TextView companyId = (TextView) findViewById(R.id.companyIdText);
        DeleteTask deleteTask = new DeleteTask(this, getApplicationContext(), companyId.getText().toString());
        deleteTask.execute();
    }

    @Override
    public void processFinish(Cursor responseCursor) {
        responseCursor.moveToFirst();
        displayListView(responseCursor);
    }

    @Override
    public void processFinish(Integer result) {
        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
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
                R.id.companyIdText,
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "works", Toast.LENGTH_LONG).show();
            }
        });
    }


}
