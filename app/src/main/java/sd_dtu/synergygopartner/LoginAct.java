package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAct extends AppCompatActivity {
    public String agentid,password;
    EditText agentidet,passet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void onClickLogin(View view){

        agentidet=(EditText)findViewById(R.id.agentidet);
        passet=(EditText)findViewById(R.id.passet);
        agentid=agentidet.getText().toString().trim();
        password=passet.getText().toString().trim();

        Intent intent=new Intent(LoginAct.this,AssignmentChooseAct.class);
        startActivity(intent);
    }
}
