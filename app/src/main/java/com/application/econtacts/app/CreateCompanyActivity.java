package com.application.econtacts.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by mordreth on 10/11/15.
 */
public class CreateCompanyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.create_company);
    }

    public void create(View view) {
        finish();
    }
}
