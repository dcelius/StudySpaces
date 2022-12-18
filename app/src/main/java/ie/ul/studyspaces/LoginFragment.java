package ie.ul.studyspaces;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {

    EditText logUsername;
    EditText logPassword;
    TextView link_registration;
    Button btnLogIn;

    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logUsername = view.findViewById(R.id.logUsername);
        logPassword = view.findViewById(R.id.logPassword);
        link_registration = view.findViewById(R.id.link_registration);
        btnLogIn = view.findViewById(R.id.btnLogIn);

        mAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                    Toast.makeText(getActivity(), "Your Login was successful", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_menuFragment);
                                }else{
                                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
            }
        });

        link_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

    }


}