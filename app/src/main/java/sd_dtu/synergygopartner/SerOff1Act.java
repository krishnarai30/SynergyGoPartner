package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SerOff1Act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_off1);
    }
    public void onClickNextso1(View view){

        Intent intent=new Intent(SerOff1Act.this,LocationPhoto.class);
        startActivity(intent);
    }
}
