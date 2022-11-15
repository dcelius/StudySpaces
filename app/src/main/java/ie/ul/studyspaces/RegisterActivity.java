package ie.ul.studyspaces;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    EditText regFirstName;
    EditText regLastName;
    EditText regEmail;
    EditText regPassword;
    Button btnSignIn;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);

        regFirstName = findViewById(R.id.regFirstName);
        regLastName = findViewById(R.id.regLastName);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        mAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(view ->{
            createUser();
        });
    }

    private void createUser(){
        String firstname = regFirstName.getText().toString().trim();
        String lastname = regLastName.getText().toString().trim();
        String email = regEmail.getText().toString().trim();
        String password = regPassword.getText().toString().trim();

        if(TextUtils.isEmpty(firstname)){
            regFirstName.setError("First Name cannot be empty");
            regFirstName.requestFocus();
        }if(TextUtils.isEmpty(lastname)){
            regLastName.setError("Last Name cannot be empty");
            regLastName.requestFocus();
        }if(TextUtils.isEmpty(email)){
            regEmail.setError("Email cannot be empty");
            regEmail.requestFocus();
        }if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regEmail.setError("Please use the right format");
            regEmail.requestFocus();
        }if(TextUtils.isEmpty(password)){
            regPassword.setError("Password cannot be empty");
            regPassword.requestFocus();
        }if(password.length() < 6){
            regPassword.setError("Password should be at least 6 characters");
            regPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        User user = new User(firstname, lastname, email);
                        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "Your registration was successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{
                            Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                }
            });
        }
    }

}