package com.application.econtacts.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import asyncTasks.AsyncResponse;
import asyncTasks.DeleteTask;
import asyncTasks.GetTask;
import com.application.econtacts.app.DeleteDialog.AlertPositiveDeleteListener;
import sqlLite.CompaniesDataSource;

/**
 * Created by mordreth on 10/11/15.
 */
public class SearchCompaniesActivity extends Activity implements AsyncResponse, AlertPositiveDeleteListener, ShowContactDialog.AlertPositiveExitListener {
    private CompaniesDataSource dataSource;
    private ListView itemList;
    private SimpleCursorAdapter adapter;
    private Cursor savedItemCursor;

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
        showDeleteDialog();
    }

    public void updateCompany(View view) {
        TextView companyId = (TextView) findViewById(R.id.companyIdText);
        Intent intent = new Intent(this, EditCompanyActivity.class);

        if (savedItemCursor.moveToFirst()) {
            do {
                if (savedItemCursor.getString(savedItemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.ID_COMPANY))
                        .equals(companyId.getText().toString())) {
                    intent.putExtra(CompaniesDataSource.ColumnCompanies.ID_COMPANY, companyId.getText().toString());
                    intent.putExtra(CompaniesDataSource.ColumnCompanies.NAME_COMPANY,
                            savedItemCursor.getString(savedItemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.NAME_COMPANY)));
                    intent.putExtra(CompaniesDataSource.ColumnCompanies.CLASIFICATION_COMPANY,
                            savedItemCursor.getString(savedItemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.CLASIFICATION_COMPANY)));
                    intent.putExtra(CompaniesDataSource.ColumnCompanies.EMAIL_COMPANY,
                            savedItemCursor.getString(savedItemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.EMAIL_COMPANY)));
                    intent.putExtra(CompaniesDataSource.ColumnCompanies.PHONE_COMPANY,
                            savedItemCursor.getString(savedItemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.PHONE_COMPANY)));
                    intent.putExtra(CompaniesDataSource.ColumnCompanies.PS_COMPANY,
                            savedItemCursor.getString(savedItemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.PHONE_COMPANY)));
                    intent.putExtra(CompaniesDataSource.ColumnCompanies.PS_COMPANY,
                            savedItemCursor.getString(savedItemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.PS_COMPANY)));
                    intent.putExtra(CompaniesDataSource.ColumnCompanies.URL_COMPANY,
                            savedItemCursor.getString(savedItemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.URL_COMPANY)));
                }
            } while (savedItemCursor.moveToNext());
        }
        startActivity(intent);
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
    public void displayListView(final Cursor itemCursor) {
        if (itemCursor != null) {
            savedItemCursor = itemCursor;
        }
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

        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = null;
                String siteURL = null;
                String phone = null;
                String email = null;
                String productsAndServices;
                productsAndServices = null;
                String clasification = null;
                TextView companyId = (TextView) view.findViewById(R.id.companyIdText);

                if (itemCursor.moveToFirst()) {
                    do {
                        if (companyId.getText().toString()
                                .equals(itemCursor.getString(itemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.ID_COMPANY)))) {
                            name = itemCursor.getString(itemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.NAME_COMPANY));
                            siteURL = itemCursor.getString(itemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.URL_COMPANY));
                            phone = itemCursor.getString(itemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.PHONE_COMPANY));
                            email = itemCursor.getString(itemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.EMAIL_COMPANY));
                            productsAndServices = itemCursor.getString(itemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.PS_COMPANY));
                            clasification = itemCursor.getString(itemCursor.getColumnIndex(CompaniesDataSource.ColumnCompanies.CLASIFICATION_COMPANY));
                        }
                    } while (itemCursor.moveToNext());
                }
                showCompanyDialog(name, siteURL, phone, email, productsAndServices, clasification);
            }
        });
    }

    @Override
    public void onPositiveDeleteClick(boolean deleteCompany) {
        TextView companyId = (TextView) findViewById(R.id.companyIdText);
        DeleteTask deleteTask = new DeleteTask(this, getApplicationContext(), companyId.getText().toString());
        deleteTask.execute();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showDeleteDialog() {
        FragmentManager manager = getFragmentManager();
        DeleteDialog deleteDialog = new DeleteDialog();
        deleteDialog.show(manager, "delete");
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showCompanyDialog(String name, String siteURL, String phone, String email, String productsAndServices, String clasification) {
        FragmentManager manager = getFragmentManager();
        ShowContactDialog showContactDialog = new ShowContactDialog(getApplicationContext(), clasification, productsAndServices, email, phone, siteURL, name);
        showContactDialog.show(manager, "show");
    }

    @Override
    public void onPositiveExitClick(boolean exit) {

    }
}
