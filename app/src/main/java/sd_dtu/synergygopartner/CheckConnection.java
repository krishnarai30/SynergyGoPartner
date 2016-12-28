package sd_dtu.synergygopartner;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

/**
 * Created by mohitkumar on 28/12/16.
 */

public class CheckConnection extends AsyncTask<Void,Void,Boolean> {

    Context context;
    String message;
    private Boolean error = false;
    ProgressDialog dialog;

    CheckConnection(Context context,String message) {
        this.context = context;
        this.message = message;
    }

    @Override
    protected Boolean doInBackground(Void... params) {


        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.show();
    }



    @Override
    protected void onPostExecute(Boolean b) {
        dialog.dismiss();
        if(error) {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
            alertdialog.setTitle("Some Error Ocurred")
                    .setMessage("It seems to be taking too long and for security purposes we request you to trty again later.").show();

        }
    }

}
