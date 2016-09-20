package sd_dtu.synergygopartner;

import android.content.Intent;
import android.content.SharedPreferences;
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
    public String AgentIDin,PassIn;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        agentId=(EditText)findViewById(R.id.AgenitIDeditText);
        pass=(EditText)findViewById(R.id.PasseditText);

    }

    public void onClickLogin(View view){

        AgentIDin=agentId.getText().toString();
        PassIn=pass.getText().toString();


        mAuth=FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(AgentIDin, PassIn)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        final String PREFS_NAME = "MyPrefsFile";
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                        editor.putString("AgentID",AgentIDin);
                        editor.apply();
                        Intent intent=new Intent(LoginAct.this,AssignmentChooseAct.class);
                        startActivity(intent);

                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginAct.this, "Login Failed check credentials",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }
}
