package com.application.econtacts.app;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by mordreth on 10/11/15.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DeleteDialog extends DialogFragment {
    AlertPositiveDeleteListener alertPositiveDeleteListener;

    DialogInterface.OnClickListener deleteListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            alertPositiveDeleteListener.onPositiveDeleteClick(true);
        }
    };

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            alertPositiveDeleteListener = (AlertPositiveDeleteListener) activity;
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
        builder.setMessage("Are you sure you want to delete the Company?");

        /** Setting a positive button and its listener **/
        builder.setPositiveButton("Yes", deleteListener);

        /** Creating the alert dialog window using the builder class */
        AlertDialog alertDialog = builder.create();

        /** Return the alert dialog window */
        return alertDialog;
    }

    interface AlertPositiveDeleteListener {
        public void onPositiveDeleteClick(boolean deleteCompany);
    }
}
