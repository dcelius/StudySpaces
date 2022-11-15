package ie.ul.studyspaces;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText logUsername;
    EditText logPassword;
    TextView link_registration;
    Button btnLogIn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        logUsername = findViewById(R.id.logUsername);
        logPassword = findViewById(R.id.logPassword);
        link_registration = findViewById(R.id.link_registration);
        btnLogIn = findViewById(R.id.btnLogIn);

        mAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(view -> {
            loginUser();
        });
        link_registration.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(){
        String email = logUsername.getText().toString();
        String password = logPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            logUsername.setError("Email cannot be empty");
            logUsername.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            logPassword.setError("Password cannot be empty");
            logPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Your Login was successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}