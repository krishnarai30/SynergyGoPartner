package sd_dtu.synergygopartner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
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



        addtypestr = getIntent().getStringExtra("addtype");
        addstr = getIntent().getStringExtra("address");
        agentidstr = getIntent().getStringExtra("agentid");
        filestr = getIntent().getStringExtra("fileno");
        applicantnamestr = getIntent().getStringExtra("name");
        contactpstr = getIntent().getStringExtra("pcontact");
        contactsstr = getIntent().getStringExtra("scontact");
        landmarkstr = getIntent().getStringExtra("landmark");




                    addtv.setText(addstr);
                    agenttv.setText(agentidstr);
                    addtyptv.setText(addtypestr);
                    filetv.setText(filestr);
                    landmarktv.setText(landmarkstr);
                    contactstv.setText(contactsstr);
                    cantactptv.setText(contactpstr);
                    appnametv.setText(applicantnamestr);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addtypestr.equals("RESIDENTIAL")){
                    Intent intent2=new Intent(DetailsAct.this,Res1Act.class);
                    intent2.putExtra("file",filestr);
                    startActivity(intent2);
                }
                if(addtypestr.equals("OFFICE")){
                    Intent intent2=new Intent(DetailsAct.this,Off1Act.class);
                    intent2.putExtra("file",filestr);
                    startActivity(intent2);
                }
                if(addtypestr.equals(" BUSINESS")){
                    Intent intent2=new Intent(DetailsAct.this,Bus1Act.class);
                    intent2.putExtra("file",filestr);
                    startActivity(intent2);
                }
            }
        });
            }

                //@Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}


//        mDatabasechecked = FirebaseDatabase.getInstance().getReference();

//        uniid = getIntent().getStringExtra("uniid");

// final String str1=getIntent().getStringExtra("choice");
// SharedPreferences prefs = getPreferences(MODE_PRIVATE);
//        String AgentID= getIntent().getStringExtra("agentid");
//        mDatabasechecked.child("file").child(AgentID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                i=0;
//                for (DataSnapshot file : dataSnapshot.getChildren()) {
//                    Log.i("file", file.getKey());
//                    si.add(file.getKey());
//                    if(si.get(i).equals(str1)){
//
//                        addstr = dataSnapshot.child(str1).child("Address").getValue(String.class);
//                        agentidstr = dataSnapshot.child(str1).child("Agent ID").getValue(String.class);
//                        filestr = dataSnapshot.child("File").getValue(String.class);
//                        applicantnamestr = dataSnapshot.child(str1).child("Applicant's name").getValue(String.class);
//                        contactpstr = dataSnapshot.child(str1).child("Contact Primary").getValue(String.class);
//                        contactsstr = dataSnapshot.child(str1).child("Contact Secondary").getValue(String.class);
//                        landmarkstr = dataSnapshot.child(str1).child("Landmark").getValue(String.class);
//                        addtypestr = dataSnapshot.child(str1).child("Address Type").getValue(String.class);
//
//                    }
//                    i++;
//                }