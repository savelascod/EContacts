package com.application.econtacts.app;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import sqlLite.CompaniesDataSource;

/**
 * Created by mordreth on 10/11/15.
 */
public class SearchCompaniesActivity extends Activity {
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

    }

    public void searchAll(View view) {
    }
}
