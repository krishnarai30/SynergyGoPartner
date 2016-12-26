package sd_dtu.synergygopartner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Off1Act extends AppCompatActivity {

    EditText name,designation,mobile,joinDate,desigApp,noYears;
    String sname,sdesignation,smobile,sjoinDate,sdesigApp,snoYears;
    String filestr;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off1);

        name=(EditText)findViewById(R.id.personconteditText);
        designation=(EditText)findViewById(R.id.desigPCeditText);
        mobile=(EditText)findViewById(R.id.mobPCeditText);
        joinDate=(EditText)findViewById(R.id.DateAppjoineditText);
        desigApp=(EditText)findViewById(R.id.DesgAppeditText);
        noYears=(EditText)findViewById(R.id.yearseditText);
    }
    public void onClickNextso1(View view){
        filestr=getIntent().getStringExtra("file");
        sname=name.getText().toString().trim();
        sdesignation=designation.getText().toString().trim();
        smobile=mobile.getText().toString().trim();
        sjoinDate=joinDate.getText().toString().trim();
        sdesigApp=desigApp.getText().toString().trim();
        snoYears=noYears.getText().toString().trim();


        if(isNetworkAvailable(getApplicationContext())) {
//        databaseReference= FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("file").child("Office").child(filestr).child("Name of Person Contacted").setValue(sname);
//        databaseReference.child("file").child("Office").child(filestr).child("Designation of Person Contacted").setValue(sdesignation);
//        databaseReference.child("file").child("Office").child(filestr).child("Mobile").setValue(smobile);
//        databaseReference.child("file").child("Office").child(filestr).child("Date of Joining of Applicant").setValue(sjoinDate);
//        databaseReference.child("file").child("Office").child(filestr).child("Designation of Applicant").setValue(sdesigApp);
//        databaseReference.child("file").child("Office").child(filestr).child("No. of years in present Employment ").setValue(snoYears);


            Intent intent = new Intent(Off1Act.this, Off2Act.class);
            intent.putExtra("file", filestr);
            startActivity(intent);
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("No Internet Connection...")
                    .setMessage("Click Here to set Active connection")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.error)
                    .show();
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
