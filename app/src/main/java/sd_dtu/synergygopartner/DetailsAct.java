package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsAct extends AppCompatActivity {
    DatabaseReference mDatabasechecked;
    public String addstr,agentidstr,filestr,applicantnamestr,contactpstr,contactsstr,landmarkstr,addtypestr;
    TextView addtv,agenttv,filetv,appnametv,cantactptv,contactstv,landmarktv,addtyptv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        addtv=(TextView)findViewById(R.id.addresstxtv);
        agenttv=(TextView)findViewById(R.id.agentv);
        filetv=(TextView)findViewById(R.id.filetv);
        appnametv=(TextView)findViewById(R.id.apptv);
        cantactptv=(TextView)findViewById(R.id.conptv);
        contactstv=(TextView)findViewById(R.id.constv);
        landmarktv=(TextView)findViewById(R.id.landtv);
        addtyptv=(TextView)findViewById(R.id.addtyptv);
        btn=(Button)findViewById(R.id.submitbtn);

        mDatabasechecked = FirebaseDatabase.getInstance().getReference();
        mDatabasechecked.child("file").child("1").child("Applicant's name").setValue("nikhil");

        mDatabasechecked.child("file").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    addstr = dataSnapshot.child("Address").getValue(String.class);
                    agentidstr = dataSnapshot.child("Agent ID").getValue(String.class);
                    filestr = dataSnapshot.child("File").getValue(String.class);
                    applicantnamestr = dataSnapshot.child("Applicant's name").getValue(String.class);
                    contactpstr = dataSnapshot.child("Contact Primary").getValue(String.class);
                    contactsstr = dataSnapshot.child("Contact Secondary").getValue(String.class);
                    landmarkstr = dataSnapshot.child("Landmark").getValue(String.class);
                    addtypestr = dataSnapshot.child("Address Type").getValue(String.class);


                    addtv.setText(addstr);
                    agenttv.setText(agentidstr);
                    addtyptv.setText(addtypestr);
                    filetv.setText(filestr);
                    landmarktv.setText(landmarkstr);
                    contactstv.setText(contactsstr);
                    cantactptv.setText(contactpstr);
                    appnametv.setText(applicantnamestr);
            }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addtypestr.equals("Residence")){
                    Intent intent2=new Intent(DetailsAct.this,Res1Act.class);
                    intent2.putExtra("file",filestr);
                    startActivity(intent2);
                }
                if(addtypestr.equals("Office")){
                    Intent intent2=new Intent(DetailsAct.this,Off1Act.class);
                    intent2.putExtra("file",filestr);
                    startActivity(intent2);
                }
                if(addtypestr.equals(" Business")){
                    Intent intent2=new Intent(DetailsAct.this,Bus1Act.class);
                    intent2.putExtra("file",filestr);
                    startActivity(intent2);
                }
            }
        });

        }
}
