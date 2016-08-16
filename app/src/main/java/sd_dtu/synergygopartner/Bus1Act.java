package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Bus1Act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_1);
    }
    public void onClickNextb1(View view){

        Intent intent=new Intent(Bus1Act.this,LocationPhoto.class);
        startActivity(intent);
    }
}
