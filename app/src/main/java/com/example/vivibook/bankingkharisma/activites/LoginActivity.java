package com.example.vivibook.bankingkharisma.activites;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import com.example.vivibook.bankingkharisma.R;
import com.example.vivibook.bankingkharisma.helper.InputValidation;
import com.example.vivibook.bankingkharisma.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity  activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutemail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextMoney;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListener();
        initObject();
    }
    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutemail = (TextInputLayout) findViewById(R.id.TextInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.TextInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputTextPassword);
      //  textInputEditTextMoney = (TextInputEditText) findViewById(R.id.textInputTextMoney);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
    }

    private void initListener(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObject(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite(){
        if(!inputValidation.isinputEditTextFilled(textInputEditTextEmail, textInputLayoutemail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutemail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isinputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }

        if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())){
           Intent accountIntent = new Intent(activity, UsersActivity.class);
           accountIntent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
//           accountIntent.putExtra("MONEY",textInputEditTextMoney.getText().toString().trim());
           emptyInputEditText();
           startActivity(accountIntent);
        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }


    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
