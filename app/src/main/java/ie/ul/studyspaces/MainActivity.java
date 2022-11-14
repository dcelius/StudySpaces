package ie.ul.studyspaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnLandingPage;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLandingPage = findViewById(R.id.btnLandingPage);

        btnLandingPage.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }


}