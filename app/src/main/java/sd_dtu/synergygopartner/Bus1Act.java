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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Bus1Act extends AppCompatActivity {

    EditText name,desig,contact,offTele,bussNature,YearCompany,noEmployee;
    String sname,sdesig,scontact,soffTele,sbussNature,sYearCompany,snoEmployee;
    Spinner typeCompany;
    String filestr,agenti;
    String stypeCompany;
    ArrayAdapter<CharSequence> typecompadapter;
    DatabaseReference databaseReference;
    int i=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_forms1);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/OpenSans-Regular.ttf",true);

        getSupportActionBar().setTitle("Fill Details");

        agenti = getIntent().getStringExtra("agent");

        name= (EditText)findViewById(R.id.nameeditText);
        desig= (EditText)findViewById(R.id.designationeditText);
        contact= (EditText)findViewById(R.id.phoneeditText);
        offTele= (EditText)findViewById(R.id.offteleditText);
        bussNature= (EditText)findViewById(R.id.busnatureeditText);
        YearCompany= (EditText)findViewById(R.id.yearseditText);
        noEmployee= (EditText)findViewById(R.id.EmpeditText);
        typeCompany= (Spinner) findViewById(R.id.spinnecompanytyper);

        if(isNetworkAvailable(getApplicationContext())) {

//     databaseReference.child("file").child("Continue").child("Bussiness").addValueEventListener(new ValueEventListener() {
//         @Override
//         public void onDataChange(DataSnapshot dataSnapshot) {
//             i=dataSnapshot.getValue(int.class);
//         }
//
//         @Override
//         public void onCancelled(DatabaseError databaseError) {
//
//         }
//     });


            typecompadapter = ArrayAdapter.createFromResource(this, R.array.company, R.layout.support_simple_spinner_dropdown_item);
            typecompadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            typeCompany.setAdapter(typecompadapter);

            typeCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    switch (i) {
                        case 0:
                            stypeCompany = "C1";
                            break;
                        case 1:
                            stypeCompany = "C2";
                            break;
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
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



    public void onClickNextb1(View view){

        filestr=getIntent().getExtras().getString("file");
        sname = name.getText().toString().trim();
        sdesig = desig.getText().toString().trim();
        scontact = contact.getText().toString().trim();
        soffTele = offTele.getText().toString().trim();
        sbussNature = bussNature.getText().toString().trim();
        sYearCompany = YearCompany.getText().toString().trim();
        snoEmployee = noEmployee.getText().toString().trim();


        if(TextUtils.isEmpty(sname)||TextUtils.isEmpty(sdesig)||TextUtils.isEmpty(soffTele)||TextUtils.isEmpty(sbussNature)
                ||TextUtils.isEmpty(sYearCompany)||TextUtils.isEmpty(snoEmployee))
        {
            Toast.makeText(getApplicationContext(),"Fill all Fields !",Toast.LENGTH_LONG).show();
        } else {

            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Data").child("Business").child(filestr).child("Person Contacted").setValue(sname);
            databaseReference.child("Data").child("Business").child(filestr).child("Designation").setValue(sdesig);
            databaseReference.child("Data").child("Business").child(filestr).child("Contact No").setValue(scontact);
            databaseReference.child("Data").child("Business").child(filestr).child("Office Telephone No").setValue(soffTele);
            databaseReference.child("Data").child("Business").child(filestr).child("Nature Of Business").setValue(sbussNature);
            databaseReference.child("Data").child("Business").child(filestr).child("No of Years of Company").setValue(sYearCompany);
            databaseReference.child("Data").child("Business").child(filestr).child("No of Employees").setValue(snoEmployee);
            databaseReference.child("Data").child("Business").child(filestr).child("Type of Company").setValue(stypeCompany);


            Intent intent = new Intent(Bus1Act.this, LocationPhoto.class);
            intent.putExtra("file", filestr);
            intent.putExtra("agent", agenti);
            intent.putExtra("type","Business");
            startActivity(intent);
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
