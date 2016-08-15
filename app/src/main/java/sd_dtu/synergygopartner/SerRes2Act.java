package sd_dtu.synergygopartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by sam AR on 8/15/2016.
 */
public class SerRes2Act extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_res2);
    }



    public void onClickFinishsr(View view){

        Intent intent2=new Intent(SerRes2Act.this,AssignmentChooseAct.class);
        startActivity(intent2);
    }
}
