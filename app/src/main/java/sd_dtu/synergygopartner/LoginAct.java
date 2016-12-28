package sd_dtu.synergygopartner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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

        Log.d("Taken","Values entered");

        if(TextUtils.isEmpty(AgentIDin)||TextUtils.isEmpty(PassIn)) {
            Toast.makeText(getApplicationContext(),"Agent ID or Password not entered",Toast.LENGTH_LONG).show();
        }
        else {
            if (isNetworkAvailable(getApplicationContext())) {

                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage("Validating your details....");
                dialog.show();
                dialog.setCancelable(true);

                DatabaseReference mdatabaseref = FirebaseDatabase.getInstance().getReference();

                mdatabaseref.child("AgentID").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.d("Entered", "IN HERE");
                        for (DataSnapshot file : dataSnapshot.getChildren()) {
                            if (file.getKey().toString().equals(AgentIDin)) {
                                if (file.child("password").getValue().toString().equals(PassIn)) {
                                    Log.d("CHECKED", "MATCHED!");
                                    dialog.dismiss();
                                    Intent intent = new Intent(LoginAct.this, AssignmentChooseAct.class);
                                    intent.putExtra("Agent", AgentIDin);
                                    startActivity(intent);
                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                dialog.dismiss();
                                //Toast.makeText(getApplicationContext(), "Agent ID not registered!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "The Error is still here", Toast.LENGTH_LONG).show();
                    }
                });

            } else {
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
        }
//        mAuth=FirebaseAuth.getInstance();
//
//        mAuth.signInWithEmailAndPassword(AgentIDin, PassIn)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        final String PREFS_NAME = "MyPrefsFile";
//                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//                        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
//                        editor.putString("AgentID",AgentIDin);
//                        editor.apply();
//                        Intent intent=new Intent(LoginAct.this,AssignmentChooseAct.class);
//                        startActivity(intent);
//
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(LoginAct.this, "Login Failed check credentials",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
