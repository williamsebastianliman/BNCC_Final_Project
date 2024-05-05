package com.example.bncc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registerLink = findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Go to Register Page");
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String enteredEmail = emailEditText.getText().toString();
            String enteredPassword = passwordEditText.getText().toString();
            login(enteredEmail, enteredPassword);
        });
    }
    private void login(String email, String password) {
        SharedPreferences prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        String storedEmail = prefs.getString("Email", "");
        String storedPassword = prefs.getString("Password", "");
        String Username=prefs.getString("Name","");
        boolean flag=true;
        if(email.length()==0)
        {
            flag=false;
            emailEditText.setError("Field must be filled!");
        }
        if(password.length()==0)
        {
            flag=false;
            passwordEditText.setError("Field must be filled!");
        }
        //Email Validation
        if(flag)
        {
            boolean hasAt=false;
            for(int i=0;i<email.length();i++)
            {
                if(email.charAt(i)=='@')
                {
                    hasAt=true;
                    break;
                }
            }
            boolean dotcom=false;
            int in0=email.length()-1;
            int in1=email.length()-2;
            int in2=email.length()-3;
            int in3=email.length()-4;
            String emailS=email;
            if(emailS.length()>=5)
            {
                if(emailS.charAt(in0)=='m'&&emailS.charAt(in1)=='o'&&emailS.charAt(in2)=='c'&&emailS.charAt(in3)=='.')
                {
                    dotcom=true;
                }
            }
            else
            {
                emailEditText.setError("Must have @ and ends with .com");
            }
            if(!dotcom||!hasAt)
            {
                emailEditText.setError("Must have @ and ends with .com");
            }
            if (email.equals(storedEmail) && password.equals(storedPassword)) {
                //auth correct go to main activity
                Intent intent = new Intent(Login.this, MainActivity.class);
                //intent.putExtra("USERNAME_KEY", Username);
                startActivity(intent);
                finish();

            } else {
                // auth incorrect, show error
                Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }

    }
}