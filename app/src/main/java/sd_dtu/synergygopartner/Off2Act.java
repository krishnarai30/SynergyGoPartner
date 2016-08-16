package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Off2Act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off2);
    }
    public void onClickFinishso(View view){

        Intent intent=new Intent(Off2Act.this,AssignmentChooseAct.class);
        startActivity(intent);
    }
}
