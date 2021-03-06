package com.example.ux32vd.gudangbaliyonimobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MenuLogin extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog pDialog;
    FirebaseAuth mAuth;
    EditText loginEmail, loginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_login).setOnClickListener(this);

        loginEmail = findViewById(R.id.txt_email);
        loginPassword = findViewById(R.id.txt_password);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

    }

        private void userLogin() {

            String email = loginEmail.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();

            //validasi kolom tidak boleh kosong
            if (email.isEmpty()) {
                loginEmail.setError("Email tidak boleh kosong");
                loginEmail.requestFocus();
                return;
            }

            // mengecek validasi email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                loginEmail.setError("Masukkan email yang valid");
                loginEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                loginPassword.setError("Password tidak boleh kosong");
                loginPassword.requestFocus();
                return;
            }

            //mengecek panjang password
            if (password.length() < 8) {
                loginPassword.setError("Minimal panjang password adalah 8");
            }

            //Autorisasi login email dan password
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(MenuLogin.this, MenuAdmin.class);
                        //Menghilangkan activity sebelumnya, agar user tidak kembali ke menu login
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                pDialog.setMessage("Logging in ...");
                showDialog();
                userLogin();
                hideDialog();
                break;
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}


