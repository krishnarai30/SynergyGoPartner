package sd_dtu.synergygopartner;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class AssignmentChooseAct extends AppCompatActivity {

    //ListView fileslv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    DatabaseReference mDatabasechecked;
    ArrayList<String> fi = new ArrayList<String>();
    ArrayList<CardData> list = new ArrayList<CardData>();
    public static final int EXTERNAL_STORAGE_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_choose);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/OpenSans-Regular.ttf",true);

        final String AgentID = getIntent().getStringExtra("Agent");
        getSupportActionBar().setTitle("Agent ID : " + AgentID);
        recyclerView = (RecyclerView)findViewById(R.id.recyc_view);



       // fileslv=(ListView)findViewById(R.id.agentlv);

        boolean permissionCheck = Utility.checkPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(isNetworkAvailable(getApplicationContext())) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Wait...");
            progressDialog.show();
            progressDialog.setCancelable(true);


            mDatabasechecked = FirebaseDatabase.getInstance().getReference();
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            //String AgentID= prefs.getString("AgentID","");
            mDatabasechecked.child("file").child(AgentID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int i = 0;
                    for (DataSnapshot file : dataSnapshot.getChildren()) {
                        Log.i("file", file.getKey());
                        fi.add(file.getKey());
                        String name = file.child("Applicant's name").getValue().toString();
                        String address = file.child("Address").getValue().toString();
                        String addtype = file.child("Address Type").getValue().toString();
                        String cprimary = file.child("Contact Primary").getValue().toString();
                        String sprimary = file.child("Contact Secondary").getValue().toString();
                        String fileno = file.child("File").getValue().toString();
                        String landmark = file.child("Landmark").getValue().toString();

                        CardData cardData = new CardData(name, fileno, address, addtype, landmark, cprimary, sprimary, AgentID, fi.get(i));
                        list.add(cardData);
                        i++;
                    }
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    adapter = new RecyclerCardAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(adapter);
                    //ArrayAdapter arrayAdapter = new ArrayAdapter(AssignmentChooseAct.this, android.R.layout.simple_list_item_1,fi);

                    //fileslv.setAdapter(arrayAdapter);

                    progressDialog.dismiss();

                }

                @Override
                public void onCancelled(DatabaseError firebaseError) {
                    Toast.makeText(getApplicationContext(), "Unable to contact the server", Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("No Internet Connection...")
                    .setMessage("Click Here to set Active connection")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.error)
                    .show();
        }




//        fileslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//              Intent intent=new Intent(AssignmentChooseAct.this,DetailsAct.class);
//              String option= fi.get(i);
//                intent.putExtra("choice",option);
//                startActivity(intent);
//            }
//        });
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_CODE :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //if (userChoosenTask.equals("STORAGE"))
                    //performTaskOperation();//this method what you need to perform
                }
                break;
        }
    }

    public static class Utility {
        public static boolean checkPermissions(Context context, String... permissions) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
                for (String permission : permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},EXTERNAL_STORAGE_CODE);
                        } else {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_CODE);
                        }
                        return false;
                    }
                }
            }
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AssignmentChooseAct.this,LoginAct.class);
        startActivity(intent);
    }
}
