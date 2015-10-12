package com.application.econtacts.app;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mordreth on 10/11/15.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ShowContactDialog extends DialogFragment {
    AlertPositiveExitListener alertPositiveExitListener;

    DialogInterface.OnClickListener exitListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            alertPositiveExitListener.onPositiveExitClick(true);
        }
    };

    private String name;
    private String siteURL;
    private String phone;
    private String email;
    private String productsAndServices;
    private String clasification;
    private Context context;

    public ShowContactDialog(Context context, String clasification, String productsAndServices, String email, String phone, String siteURL, String name) {
        this.context = context;
        this.clasification = clasification;
        this.productsAndServices = productsAndServices;
        this.email = email;
        this.phone = phone;
        this.siteURL = siteURL;
        this.name = name;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            alertPositiveExitListener = (AlertPositiveExitListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement AlertPositiveDeleteListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /** Creating a Builder for the alert window **/
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /** Setting a title for the window */

        /** Setting a label for the alert dialog**/

        View view = (View) LayoutInflater.from(context).inflate(R.layout.show_company, null);

        TextView nameText = (TextView) view.findViewById(R.id.showNametext);
        TextView emailText = (TextView) view.findViewById(R.id.showEmailtext);
        TextView phoneText = (TextView) view.findViewById(R.id.showPhonetext);
        TextView urlText = (TextView) view.findViewById(R.id.showURLtext);
        TextView psText = (TextView) view.findViewById(R.id.showPStext);
        TextView clasificationText = (TextView) view.findViewById(R.id.showClasificationtext);

        nameText.setText(name);
        phoneText.setText(phone);
        psText.setText(productsAndServices);
        urlText.setText(siteURL);
        emailText.setText(email);
        clasificationText.setText(clasification);


        builder.setView(view);

        /** Setting a positive button and its listener **/
        builder.setPositiveButton("OK", exitListener);

        /** Creating the alert dialog window using the builder class */
        AlertDialog alertDialog = builder.create();

        /** Return the alert dialog window */
        return alertDialog;
    }

    interface AlertPositiveExitListener {
        public void onPositiveExitClick(boolean exit);
    }
}
