package sd_dtu.synergygopartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by sam AR on 8/15/2016.
 */
public class SerRes2Act extends AppCompatActivity {

    EditText registration,carpetArea,politicalInflu,otherRemarks;
    String sregistration,scarpetArea,spoliticalInflu,sotherRemarks,svehicle;
    Spinner vehicle;
    String filestr;
    ArrayAdapter<CharSequence> vehicleadapter;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res2);

        registration= (EditText)findViewById(R.id.RegNoeditText);
        carpetArea= (EditText)findViewById(R.id.CarpetAreaeditText);
        politicalInflu= (EditText)findViewById(R.id.PoliticaleditText);
        otherRemarks= (EditText)findViewById(R.id.OtherRemarkseditText);
        vehicle=(Spinner) findViewById(R.id.Vehiclespinner);





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

        databaseReference= FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("file").child("Residence").child(filestr).child("Vehicle Seen").setValue(svehicle);
//        databaseReference.child("file").child("Residence").child(filestr).child("Registeration No").setValue(sregistration);
//        databaseReference.child("file").child("Residence").child(filestr).child("Carpet Area").setValue(scarpetArea);
//        databaseReference.child("file").child("Residence").child(filestr).child("Political Influence").setValue(spoliticalInflu);
//        databaseReference.child("file").child("Residence").child(filestr).child("Other Remarks").setValue(sotherRemarks);





        Intent intent2=new Intent(SerRes2Act.this,LocationPhoto.class);
        intent2.putExtra("file",filestr);
        startActivity(intent2);
    }
}
