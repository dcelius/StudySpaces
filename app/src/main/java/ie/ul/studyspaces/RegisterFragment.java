package ie.ul.studyspaces;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {

    EditText regFirstName;
    EditText regLastName;
    EditText regEmail;
    EditText regPassword;
    Button btnSignIn;

    FirebaseAuth mAuth;
    FirebaseFirestore mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        regFirstName = view.findViewById(R.id.regFirstName);
        regLastName = view.findViewById(R.id.regLastName);
        regEmail = view.findViewById(R.id.regEmail);
        regPassword = view.findViewById(R.id.regPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseFirestore.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = regFirstName.getText().toString().trim();
                String lastname = regLastName.getText().toString().trim();
                String email = regEmail.getText().toString().trim();
                String password = regPassword.getText().toString().trim();

                Map<String,Object> user = new HashMap<>();
                user.put("First Name", firstname);
                user.put("Last Name", lastname);
                user.put("Email", email);

                if (TextUtils.isEmpty(firstname)) {
                    regFirstName.setError("First Name cannot be empty");
                    regFirstName.requestFocus();
                }
                if (TextUtils.isEmpty(lastname)) {
                    regLastName.setError("Last Name cannot be empty");
                    regLastName.requestFocus();
                }
                if (TextUtils.isEmpty(email)) {
                    regEmail.setError("Email cannot be empty");
                    regEmail.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    regEmail.setError("Please use the right format");
                    regEmail.requestFocus();
                }
                if (TextUtils.isEmpty(password)) {
                    regPassword.setError("Password cannot be empty");
                    regPassword.requestFocus();
                }
                if (password.length() < 6) {
                    regPassword.setError("Password should be at least 6 characters");
                    regPassword.requestFocus();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                mDatabase.collection("user")
                                        //This line pulls the UID of the user, using that in the
                                        //collection, allowing for access to that data later.
                                        .document(mAuth.getCurrentUser().getUid()).set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getActivity(), "Your registration was successful", Toast.LENGTH_SHORT).show();
                                                Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_loginFragment);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }

}