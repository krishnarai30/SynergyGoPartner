package sd_dtu.synergygopartner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Off1Act extends AppCompatActivity {

    EditText name,designation,mobile,joinDate,desigApp,noYears;
    String sname,sdesignation,smobile,sjoinDate,sdesigApp,snoYears;
    String filestr,agentid;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off1);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/OpenSans-Regular.ttf",true);
        getSupportActionBar().setTitle("Record the responses");

        name=(EditText)findViewById(R.id.personconteditText);
        designation=(EditText)findViewById(R.id.desigPCeditText);
        mobile=(EditText)findViewById(R.id.mobPCeditText);
        joinDate=(EditText)findViewById(R.id.DateAppjoineditText);
        desigApp=(EditText)findViewById(R.id.DesgAppeditText);
        noYears=(EditText)findViewById(R.id.yearseditText);

        agentid = getIntent().getStringExtra("agent");

    }
    public void onClickNextso1(View view){
        filestr=getIntent().getStringExtra("file");
        sname=name.getText().toString().trim();
        sdesignation=designation.getText().toString().trim();
        smobile=mobile.getText().toString().trim();
        sjoinDate=joinDate.getText().toString().trim();
        sdesigApp=desigApp.getText().toString().trim();
        snoYears=noYears.getText().toString().trim();

        if(TextUtils.isEmpty(sname)||TextUtils.isEmpty(sdesignation)||TextUtils.isEmpty(smobile)||TextUtils.isEmpty(sjoinDate)
                ||TextUtils.isEmpty(sdesigApp)||TextUtils.isEmpty(snoYears)) {
            Toast.makeText(getApplicationContext(),"Enter all the fields .. . ",Toast.LENGTH_LONG).show();
        } else {

            if (isNetworkAvailable(getApplicationContext())) {
                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Data").child("Office").child(filestr).child("Name of Person Contacted").setValue(sname);
                databaseReference.child("Data").child("Office").child(filestr).child("Designation of Person Contacted").setValue(sdesignation);
                databaseReference.child("Data").child("Office").child(filestr).child("Mobile").setValue(smobile);
                databaseReference.child("Data").child("Office").child(filestr).child("Date of Joining of Applicant").setValue(sjoinDate);
                databaseReference.child("Data").child("Office").child(filestr).child("Designation of Applicant").setValue(sdesigApp);
                databaseReference.child("Data").child("Office").child(filestr).child("Number of years in present Employment ").setValue(snoYears);


                Intent intent = new Intent(Off1Act.this, Off2Act.class);
                intent.putExtra("file", filestr);
                intent.putExtra("agent", agentid);
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
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
