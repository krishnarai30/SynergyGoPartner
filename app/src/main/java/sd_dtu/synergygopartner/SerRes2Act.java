package sd_dtu.synergygopartner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by sam AR on 8/15/2016.
 */
public class SerRes2Act extends AppCompatActivity {

    EditText registration,carpetArea,politicalInflu,otherRemarks;
    String sregistration,scarpetArea,spoliticalInflu,sotherRemarks,svehicle;
    Spinner vehicle;
    String filestr,agentid;
    ArrayAdapter<CharSequence> vehicleadapter;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res2);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/OpenSans-Regular.ttf",true);

        getSupportActionBar().setTitle("Fill Details");

        registration= (EditText)findViewById(R.id.RegNoeditText);
        carpetArea= (EditText)findViewById(R.id.CarpetAreaeditText);
        politicalInflu= (EditText)findViewById(R.id.PoliticaleditText);
        otherRemarks= (EditText)findViewById(R.id.OtherRemarkseditText);
        vehicle=(Spinner) findViewById(R.id.Vehiclespinner);

        agentid = getIntent().getStringExtra("agent");

        vehicleadapter=ArrayAdapter.createFromResource(this,R.array.vehicle,R.layout.support_simple_spinner_dropdown_item);
        vehicleadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        vehicle.setAdapter(vehicleadapter);

        vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        svehicle="Two Wheeler";
                        break;
                    case 1:
                        svehicle="Car";
                        break;
                    case 2:
                        svehicle="other";

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void onClickFinishsr(View view){
        filestr=getIntent().getExtras().getString("file");

        sregistration=registration.getText().toString().trim();
        scarpetArea=carpetArea.getText().toString().trim();
        spoliticalInflu=politicalInflu.getText().toString().trim();
        sotherRemarks=otherRemarks.getText().toString().trim();

        if(TextUtils.isEmpty(sregistration)||TextUtils.isEmpty(scarpetArea)||TextUtils.isEmpty(spoliticalInflu)||TextUtils.isEmpty(sotherRemarks)) {
            Toast.makeText(getApplicationContext(),"Please enter all fields..",Toast.LENGTH_LONG).show();
        }
        else {

            if (isNetworkAvailable(getApplicationContext())) {

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Saving Data...");
                progressDialog.show();

                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Data").child("Residence").child(filestr).child("Vehicle Seen").setValue(svehicle);
                databaseReference.child("Data").child("Residence").child(filestr).child("Registeration No").setValue(sregistration);
                databaseReference.child("Data").child("Residence").child(filestr).child("Carpet Area").setValue(scarpetArea);
                databaseReference.child("Data").child("Residence").child(filestr).child("Political Influence").setValue(spoliticalInflu);
                databaseReference.child("Data").child("Residence").child(filestr).child("Other Remarks").setValue(sotherRemarks);

                progressDialog.dismiss();


             //   Intent intent2 = new Intent(SerRes2Act.this, LocationPhoto.class);
             //   intent2.putExtra("file", filestr);
             //   intent2.putExtra("agent", agentid);
             //   intent2.putExtra("type","Residence");
             //   startActivity(intent2);
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
