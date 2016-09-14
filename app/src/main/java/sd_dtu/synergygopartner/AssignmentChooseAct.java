package sd_dtu.synergygopartner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class AssignmentChooseAct extends AppCompatActivity {

    ListView fileslv;
    DatabaseReference mDatabasechecked;
    ArrayList<String> fi = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_choose);

        fileslv=(ListView)findViewById(R.id.agentlv);


       final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Wait...");
        progressDialog.show();
        progressDialog.setCancelable(true);


        mDatabasechecked=FirebaseDatabase.getInstance().getReference();

        mDatabasechecked.child("file").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot file : dataSnapshot.getChildren()) {
                    Log.i("file", file.getKey());
                    fi.add(file.getKey());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(AssignmentChooseAct.this, android.R.layout.simple_list_item_1,fi);

                fileslv.setAdapter(arrayAdapter);

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });


        fileslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              Intent intent=new Intent(AssignmentChooseAct.this,DetailsAct.class);
              String option= fi.get(i);
                intent.putExtra("choice",option);
                startActivity(intent);
            }
        });
    }
}
