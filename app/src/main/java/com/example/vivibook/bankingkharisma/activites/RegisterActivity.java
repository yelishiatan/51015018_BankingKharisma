package com.example.vivibook.bankingkharisma.activites;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.vivibook.bankingkharisma.R;
import com.example.vivibook.bankingkharisma.helper.InputValidation;
import com.example.vivibook.bankingkharisma.model.User;
import com.example.vivibook.bankingkharisma.sql.DatabaseHelper;

/**
 * Created by VIVIBOOK on 1/26/2018.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutemail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputMoney;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextemail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEdittextmoney;

    private AppCompatButton appCompatButtonRegister;

    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListener();
        initObject();
    }

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.TextInputLayoutName);
        textInputLayoutemail = (TextInputLayout) findViewById(R.id.TextInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.TextInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.TextInputConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputTextName);
        textInputEditTextemail = (TextInputEditText) findViewById(R.id.textInputTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEdittextmoney = (TextInputEditText) findViewById(R.id.textInputEditTextMoney);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
    }

    private void initListener(){
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    private void initObject(){
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }
    private void postDataToSQLite(){
        if(!inputValidation.isinputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isinputEditTextFilled(textInputEditTextemail, textInputLayoutemail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextEmail(textInputEditTextemail, textInputLayoutemail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isinputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutPassword, getString(R.string.error_password_match))){
            return;
        }


        if (!databaseHelper.checkUser(textInputEditTextemail.getText().toString().trim()
                ,textInputEditTextPassword.getText().toString().trim())){
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextemail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setMoney(textInputEdittextmoney.getText().toString().trim());
            databaseHelper.create(user);

            //Snackbar to show success message that record uccessfully saved
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        } else{
            //Snackbar to show error message that record already exist
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextemail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEdittextmoney.setText(null);
    }



}
