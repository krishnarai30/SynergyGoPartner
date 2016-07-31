package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsAct extends AppCompatActivity {
    DatabaseReference mDatabasechecked;
    TextView addtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        addtv=(TextView)findViewById(R.id.addresstxtv);
        mDatabasechecked = FirebaseDatabase.getInstance().getReference();

            mDatabasechecked.child("file").child("1").child("Address").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String test=dataSnapshot.getValue(String.class);
                    addtv.setText(test);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }

    public void onClickSubmit(View view){

        Intent intent2=new Intent(DetailsAct.this,SubmitionAct.class);
        startActivity(intent2);
    }
}
