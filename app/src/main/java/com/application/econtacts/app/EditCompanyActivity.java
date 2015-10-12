package com.application.econtacts.app;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import asyncTasks.AsyncResponse;
import asyncTasks.UpdateTask;
import sqlLite.CompaniesDataSource;
import sqlLite.CompaniesSQLiteHelper;

/**
 * Created by mordreth on 10/11/15.
 */
public class EditCompanyActivity extends Activity implements AsyncResponse {
    private String companyId;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        Intent intent = getIntent();

        setContentView(R.layout.update_company);
        EditText nameInput = (EditText) findViewById(R.id.nameInputUpdate);
        EditText urlInput = (EditText) findViewById(R.id.urlInputUpdate);
        EditText phoneInput = (EditText) findViewById(R.id.phoneInputUpdate);
        EditText emailInput = (EditText) findViewById(R.id.emailInputUpdate);
        EditText psInput = (EditText) findViewById(R.id.productServiceInputUpdate);
        Spinner clasificationSpinner = (Spinner) findViewById(R.id.clasificationSpinnerUpdate);

        nameInput.setText(intent.getStringExtra(CompaniesDataSource.ColumnCompanies.NAME_COMPANY));
        urlInput.setText(intent.getStringExtra(CompaniesDataSource.ColumnCompanies.URL_COMPANY));
        phoneInput.setText(intent.getStringExtra(CompaniesDataSource.ColumnCompanies.PHONE_COMPANY));
        emailInput.setText(intent.getStringExtra(CompaniesDataSource.ColumnCompanies.EMAIL_COMPANY));
        psInput.setText(intent.getStringExtra(CompaniesDataSource.ColumnCompanies.PS_COMPANY));
        companyId = intent.getStringExtra(CompaniesDataSource.ColumnCompanies.ID_COMPANY);

        String compareValue = intent.getStringExtra(CompaniesDataSource.ColumnCompanies.CLASIFICATION_COMPANY);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.clasificationList, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clasificationSpinner.setAdapter(adapter);
        if (!compareValue.equals(null)) {
            int spinnerPosition = adapter.getPosition(compareValue);
            clasificationSpinner.setSelection(spinnerPosition);
        }
    }

    @Override
    public void processFinish(Cursor responseCursor) {

    }

    @Override
    public void processFinish(Integer result) {

    }

    public void updateById(View view) {
        EditText nameInput = (EditText) findViewById(R.id.nameInputUpdate);
        EditText urlInput = (EditText) findViewById(R.id.urlInputUpdate);
        EditText phoneInput = (EditText) findViewById(R.id.phoneInputUpdate);
        EditText emailInput = (EditText) findViewById(R.id.emailInputUpdate);
        EditText psInput = (EditText) findViewById(R.id.productServiceInputUpdate);
        Spinner clasificationSpinner = (Spinner) findViewById(R.id.clasificationSpinnerUpdate);

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

            UpdateTask updateTask = new UpdateTask(this, getApplicationContext(), values, companyId);
            updateTask.execute();
            finish();
        }
    }
}