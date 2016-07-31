package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResSub2 extends AppCompatActivity {

    EditText vehiclereget,carpetareaet,politicalet,remarket;
    String vehicleregstr,carpetareastr,politicalstr,remarkstr,file;
    DatabaseReference mDatabase;
    Button finishbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_sub2);
        finishbtn=(Button)findViewById(R.id.finishbtn);
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalreplydetails();
            }
        });
    }
    void finalreplydetails(){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        vehiclereget=(EditText)findViewById(R.id.vehiclereget);
        politicalet=(EditText)findViewById(R.id.politicalet);
        carpetareaet=(EditText)findViewById(R.id.carpetareaet);
        remarket=(EditText)findViewById(R.id.remarkset);

        vehicleregstr=vehiclereget.getText().toString();
        carpetareastr=carpetareaet.getText().toString().trim();
        politicalstr=politicalet.getText().toString();
        remarkstr=remarket.getText().toString();
        mDatabase.child("file").child("1").child("File").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                file=dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("reply").child("file").child(file).child("Vehicle Reg").setValue(vehicleregstr);
        mDatabase.child("reply").child("file").child(file).child("Carpet Area").setValue(carpetareastr);
        mDatabase.child("reply").child("file").child(file).child("Political Remark").setValue(politicalstr);
        mDatabase.child("reply").child("file").child(file).child("Remark").setValue(remarkstr);


        Intent intent=new Intent(ResSub2.this,AssignmentChooseAct.class);
        startActivity(intent);

    }
}
