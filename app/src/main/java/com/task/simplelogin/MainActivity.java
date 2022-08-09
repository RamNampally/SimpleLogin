package com.task.simplelogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.task.simplelogin.realm.RealmDatabase;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout inputName, inputPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        inputName = findViewById(R.id.edit_name);
        inputPassword = findViewById(R.id.edit_password);
        MaterialButton btnLogin = findViewById(R.id.button_login);

        btnLogin.setOnClickListener(view -> {

            String username = Objects.requireNonNull(inputName.getEditText()).getText().toString().trim();
            String password = Objects.requireNonNull(inputPassword.getEditText()).getText().toString().trim();

            if (!username.isEmpty() && !password.isEmpty()) {

                if (password.length() >= 7) {
                    if (passwordValidation(password)) {
                        mAuth.signInWithEmailAndPassword(username, password)
                                .addOnCompleteListener(this, task -> {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), RealmDatabase.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(getApplicationContext(), "Password must one Uppercase , Special Character and Numeric", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Password must be 7 Characters", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter the login credentials", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean passwordValidation(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}