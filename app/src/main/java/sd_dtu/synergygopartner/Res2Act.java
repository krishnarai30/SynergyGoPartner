package sd_dtu.synergygopartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by sam AR on 8/15/2016.
 */
public class Res2Act extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res2);
    }



    public void onClickFinishsr(View view){

        Intent intent2=new Intent(Res2Act.this,AssignmentChooseAct.class);
        startActivity(intent2);
    }
}
