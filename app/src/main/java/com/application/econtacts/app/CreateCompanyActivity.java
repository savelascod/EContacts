package com.application.econtacts.app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import asyncTasks.AsyncResponse;
import asyncTasks.CreateTask;
import sqlLite.CompaniesDataSource;
import sqlLite.CompaniesSQLiteHelper;

/**
 * Created by mordreth on 10/11/15.
 */
public class CreateCompanyActivity extends Activity implements AsyncResponse {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.create_company);
    }

    public void create(View view) {
        EditText nameInput = (EditText) findViewById(R.id.nameInput);
        EditText urlInput = (EditText) findViewById(R.id.urlInput);
        EditText phoneInput = (EditText) findViewById(R.id.phoneInput);
        EditText emailInput = (EditText) findViewById(R.id.emailInput);
        EditText psInput = (EditText) findViewById(R.id.productServiceInput);
        Spinner clasificationSpinner = (Spinner) findViewById(R.id.clasificationSpinner);

        if (nameInput.getText().toString().trim().equals("") || emailInput.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Name and email shouldnt be empty", Toast.LENGTH_LONG).show();
        } else {
            CompaniesSQLiteHelper dbHelper = new CompaniesSQLiteHelper(getApplicationContext());

            // Gets the data repository in write mode
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Map of values
            ContentValues values = new ContentValues();
            values.put(CompaniesDataSource.ColumnCompanies.NAME_COMPANY, nameInput.getText().toString());
            values.put(CompaniesDataSource.ColumnCompanies.URL_COMPANY, urlInput.getText().toString());
            values.put(CompaniesDataSource.ColumnCompanies.PHONE_COMPANY, Long.valueOf(phoneInput.getText().toString()));
            values.put(CompaniesDataSource.ColumnCompanies.EMAIL_COMPANY, emailInput.getText().toString());
            values.put(CompaniesDataSource.ColumnCompanies.PS_COMPANY, psInput.getText().toString());
            values.put(CompaniesDataSource.ColumnCompanies.CLASIFICATION_COMPANY, clasificationSpinner.getSelectedItem().toString());

            CreateTask createTask = new CreateTask(this, getApplicationContext(), values);
            createTask.execute();
            finish();
        }


    }

    @Override
    public void processFinish(Cursor responseCursor) {
    }
}
