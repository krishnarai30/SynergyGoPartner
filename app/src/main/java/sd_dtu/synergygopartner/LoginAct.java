package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginAct extends AppCompatActivity {

    Button office;
    Button residence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        office=(Button)findViewById(R.id.officebtn);
        residence=(Button)findViewById(R.id.residencebtn);

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginAct.this,OfficeAct.class);
                startActivity(intent);

            }
        });

        residence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginAct.this,ResidenceAct.class);
                startActivity(intent);
            }
        });

    }
}
