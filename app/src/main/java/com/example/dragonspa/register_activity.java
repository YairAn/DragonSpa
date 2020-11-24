package com.example.dragonspa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_activity extends AppCompatActivity {
    private EditText userName, userEmail, userPassword;
    private Button register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        setupUIviews();
        register.setOnClickListener(new View.OnClickListener() {
            private FirebaseAuth fireBaseAuth = FirebaseAuth.getInstance();
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();
                    fireBaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(register_activity.this,"register secssesfull",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(register_activity.this,MainActivity.class));
                            }else{
                                Toast.makeText(register_activity.this,"register failed",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });
    }

    private void setupUIviews() {
        userName = (EditText) findViewById(R.id.edUserName);
        userEmail = (EditText) findViewById(R.id.edUserEmail);
        userPassword = (EditText) findViewById(R.id.edUserPassword);
        register = (Button) findViewById(R.id.register);
    }

    private Boolean validate() {
        Boolean ans = false;
        String name = userName.getText().toString();
        String Email = userEmail.getText().toString();
        String Password = userPassword.getText().toString();

        if (name.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "please enter all the details", Toast.LENGTH_LONG).show();
        } else {
            ans = true;
        }
          return ans;
    }
}