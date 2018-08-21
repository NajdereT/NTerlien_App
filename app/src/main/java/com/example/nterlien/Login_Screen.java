package com.example.nterlien;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Screen extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar1;
    private EditText editTextNaam1, editTextEmail1,editTextWachtwoord1;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);
        mAuth = FirebaseAuth.getInstance();

        editTextNaam1 = (EditText) findViewById(R.id.editText_Naam1) ;

        editTextEmail1 = (EditText) findViewById(R.id.editText_Email1);
        editTextWachtwoord1 = (EditText) findViewById(R.id.editText_Wachtwoord1);
        progressBar1 = (ProgressBar) findViewById(R.id.progressbar_1);
        progressBar1.setVisibility(View.GONE);

        findViewById(R.id.buttonLogin).setOnClickListener(this);

    }


    private void userLogin() {
        final String name = editTextNaam1.getText().toString().trim();
        final String email = editTextEmail1.getText().toString().trim();
        String password = editTextWachtwoord1.getText().toString().trim();


        Intent Home = new Intent(this, HomeActivity.class);


        if (name.isEmpty()) {
            editTextNaam1.setError("naam moet ingevuld worden");
            editTextNaam1.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail1.setError("Email moet ingevuld worden");
            editTextEmail1.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail1.setError("ongeldig Email adress");
            editTextEmail1.requestFocus();
            return;
        }



        if (password.isEmpty()) {
            editTextWachtwoord1.setError("wachtwoord moet ingevuld worden");
            editTextWachtwoord1.requestFocus();
            return;
        }


        if (password.length() < 6) {
            editTextWachtwoord1.setError("te kort wachtwoord");
            editTextWachtwoord1.requestFocus();
            return;
        }
        progressBar1.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar1.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "succesvol ingelogt", Toast.LENGTH_SHORT).show();

                    finish();
                    Intent intent = new Intent(Login_Screen.this, Login_Screen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewRegistreer:
                finish();
                startActivity(new Intent(this, Registratie_screen.class));
                break;

            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }
}
