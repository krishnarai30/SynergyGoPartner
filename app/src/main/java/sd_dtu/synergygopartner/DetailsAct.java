package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetailsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    public void onClickSubmit(View view){

        Intent intent2=new Intent(DetailsAct.this,SubmitionAct.class);
        startActivity(intent2);
    }
}
