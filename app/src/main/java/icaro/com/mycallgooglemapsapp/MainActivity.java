package icaro.com.mycallgooglemapsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent intent = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?"
//                                + "saddr="+"Travessa Daniel Silveira de Mello"+ "&daddr="+"Rua Odorico Mendes" ));
//                        Uri.parse("http://maps.google.com/maps?daddr=Avenida Lins de Vasconcelos"+"+to:Rua Odorico Mendes"+"+to:Avenida Paulista"));


                //intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");

                //startActivity(intent);

                //EditText tv = findViewById(R.id.editText);
                //tv.setText(pegarToken());
                pegarToken();

            }
        });
    }
    public void pegarToken() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Erro", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg =  token;
                        Log.d("TokenGerado: ", msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                        EditText tv = findViewById(R.id.editText);
                        tv.setText(token);
                    }
                });
    }
}
