package com.example.nterlien;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registratie_screen extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private EditText editTextNaam, editTextEmail,editTextWachtwoord;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registratie_screen);



        mAuth = FirebaseAuth.getInstance();

        editTextNaam = findViewById(R.id.editText_Naam);
        editTextEmail = findViewById(R.id.editText_Email);
        editTextWachtwoord = findViewById(R.id.editText_Wachtwoord);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


        findViewById(R.id.buttonRegistreer).setOnClickListener(this);




    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String name = editTextNaam.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextWachtwoord.getText().toString().trim();

        Intent Home = new Intent(this, HomeActivity.class);


        if (name.isEmpty()) {
            editTextNaam.setError("naam moet ingevuld worden");
            editTextNaam.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email moet ingevuld worden");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("ongeldig Email adress");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextWachtwoord.setError("wachtwoord moet ingevuld worden");
            editTextWachtwoord.requestFocus();
            return;
        }


        if (password.length() < 6) {
            editTextWachtwoord.setError("te kort wachtwoord");
            editTextWachtwoord.requestFocus();
            return;
        }

        if (password.length() > 25) {
            editTextWachtwoord.setError("te lang wachtwoord");
            editTextWachtwoord.requestFocus();
            return;
        }



        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {



                            User user = new User(
                                    name,
                                    email


                            );
                            Toast.makeText(getApplicationContext(), "succesvol", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent (Registratie_screen.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registratie_screen.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(Registratie_screen.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRegistreer:
                registerUser();
                break;
        }
    }






    public void ReturnHome(View view){
        onBackPressed();
    }
}
