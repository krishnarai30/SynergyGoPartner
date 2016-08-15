package sd_dtu.synergygopartner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AssignmentChooseAct extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String[] files={"00001","00002","00003","00004","00005"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_choose);
        ListView fileslv=(ListView)findViewById(R.id.agentlv);

        adapter = new ArrayAdapter<String>(this,R.layout.lv_custom,R.id.list_item,files);
        fileslv.setAdapter(adapter);

        fileslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1=new Intent(AssignmentChooseAct.this,LoginAct.class);
                startActivity(intent1);
            }
        });
    }
}
