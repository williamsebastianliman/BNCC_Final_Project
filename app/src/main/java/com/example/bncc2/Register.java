package com.example.bncc2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class Register extends AppCompatActivity {

    private EditText idBimbel, email, name, password, confirmPassword;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize all EditTexts and Button
        idBimbel = findViewById(R.id.idBimbel);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            boolean flag=true;
            if (TextUtils.isEmpty(email.getText().toString())) {
                flag = false;
                email.setError("Field Must Be Filled");
            }
            if (TextUtils.isEmpty(name.getText().toString())) {
                flag = false;
                name.setError("Field Must Be Filled");
            }
            if (TextUtils.isEmpty(password.getText().toString())) {
                flag = false;
                password.setError("Field Must Be Filled");
            }
            if (TextUtils.isEmpty(confirmPassword.getText().toString())) {
                flag = false;
                confirmPassword.setError("Field Must Be Filled");
            }
            if (TextUtils.isEmpty(idBimbel.getText().toString())) {
                flag = false;
                idBimbel.setError("Field Must Be Filled");
            }
            if(flag)
            {
                if (!(password.getText().toString().equals(confirmPassword.getText().toString()))) {

                    flag = false;
                    confirmPassword.setError("Password does not match");
                }
                if(name.getText().toString().length()<5)
                {
                    flag=false;
                    name.setError("Name must be more than 5 characters");
                }
                //Email Validation
                boolean hasAt=false;
                for(int i=0;i<email.getText().toString().length();i++)
                {
                    if(email.getText().toString().charAt(i)=='@')
                    {
                        hasAt=true;
                        break;
                    }
                }
                boolean dotcom=false;
                int in0=email.getText().toString().length()-1;
                int in1=email.getText().toString().length()-2;
                int in2=email.getText().toString().length()-3;
                int in3=email.getText().toString().length()-4;
                String emailS=email.getText().toString();
                if(emailS.length()>=5)
                {
                    if(emailS.charAt(in0)=='m'&&emailS.charAt(in1)=='o'&&emailS.charAt(in2)=='c'&&emailS.charAt(in3)=='.')
                    {
                        dotcom=true;
                    }
                }
                else
                {
                    flag=false;
                    email.setError("Must have @ and ends with .com");
                }
                if(!dotcom||!hasAt)
                {
                    flag=false;
                    email.setError("Must have @ and ends with .com");
                }
                if(flag)
                {
                    saveData();
                }
            }

        });
    }

    private void saveData() {
        SharedPreferences prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ID_Bimbel", idBimbel.getText().toString());
        editor.putString("Email", email.getText().toString());
        editor.putString("Name", name.getText().toString());
        editor.putString("Password", password.getText().toString());
        editor.apply();


        finish();
    }
}
