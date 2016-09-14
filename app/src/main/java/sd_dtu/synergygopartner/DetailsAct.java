package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailsAct extends AppCompatActivity {
    DatabaseReference mDatabasechecked;
    public String addstr,agentidstr,filestr,applicantnamestr,contactpstr,contactsstr,landmarkstr,addtypestr;
    TextView addtv,agenttv,filetv,appnametv,cantactptv,contactstv,landmarktv,addtyptv;
    Button btn;
    ArrayList<String> si = new ArrayList<String>();int i=0;

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
       final String str1=getIntent().getStringExtra("choice");

        mDatabasechecked.child("file").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                i=0;
                for (DataSnapshot file : dataSnapshot.getChildren()) {
                    Log.i("file", file.getKey());
                    si.add(file.getKey());
                    if(si.get(i).equals(str1)){

                        addstr = dataSnapshot.child(str1).child("Address").getValue(String.class);
                        agentidstr = dataSnapshot.child(str1).child("Agent ID").getValue(String.class);
                        filestr = dataSnapshot.child("File").getValue(String.class);
                        applicantnamestr = dataSnapshot.child(str1).child("Applicant's name").getValue(String.class);
                        contactpstr = dataSnapshot.child(str1).child("Contact Primary").getValue(String.class);
                        contactsstr = dataSnapshot.child(str1).child("Contact Secondary").getValue(String.class);
                        landmarkstr = dataSnapshot.child(str1).child("Landmark").getValue(String.class);
                        addtypestr = dataSnapshot.child(str1).child("Address Type").getValue(String.class);

                    }
                    i++;
                }

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
