package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginAct extends AppCompatActivity {

    EditText agentId,pass;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        agentId=(EditText)findViewById(R.id.AgenitIDeditText);
        pass=(EditText)findViewById(R.id.PasseditText);

    }

    public void onClickLogin(View view){



        Intent intent=new Intent(LoginAct.this,AssignmentChooseAct.class);
        startActivity(intent);
    }
}
