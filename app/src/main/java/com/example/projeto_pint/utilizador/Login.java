package com.example.projeto_pint.utilizador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projeto_pint.MainActivity;
import com.example.projeto_pint.Maps.MapsActivity;
import com.example.projeto_pint.R;
import com.example.projeto_pint.basededados.BD;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;

import java.net.URISyntaxException;

public class Login extends AppCompatActivity {
    private ConteudoLogin conteudoLogin;
    EditText email, password;
    Button login;
    Button test;
    Button map;
    TextInputLayout emailError, passError;

    boolean isEmailValid, isPasswordValid;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        try {
            this.conteudoLogin=new ConteudoLogin();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.email=(EditText) findViewById(R.id.txtEmail);
        this.password=(EditText) findViewById(R.id.txtPassword);
        login=(Button) findViewById(R.id.btnLogin);
        emailError=(TextInputLayout) findViewById(R.id.email_Error);
        passError=(TextInputLayout) findViewById(R.id.pass_Error);
        test=(Button) findViewById(R.id.btnTest);
        map=(Button) findViewById(R.id.btnMap);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    validar_dados_login();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void validar_dados_login() throws URISyntaxException {
        if(email.getText().toString().isEmpty())
        {
            emailError.setError("Introduza o seu e-mail");
            isEmailValid= false;
        } else {

            isEmailValid= true;
            emailError.setErrorEnabled(false);
        }

        if(password.getText().toString().isEmpty())
        {
            passError.setError("Introduza o sua palavra-passe");
            isPasswordValid= false;
        } else {

            isPasswordValid= true;
            passError.setErrorEnabled(false);
        }

        if(isPasswordValid && isEmailValid)
        {
            this.conteudoLogin.setEmail_input(this.email.getText().toString());
            this.conteudoLogin.setPass_input(this.password.getText().toString());
            this.conteudoLogin.entrarEmail();

            if(conteudoLogin.entrarEmail()) {
                this.conteudoLogin.entrarPassword();
                if (conteudoLogin.entrarPassword()) {
                    this.conteudoLogin.verificaEstado();
                    if(conteudoLogin.verificaEstado()){
                        //vai colocar o id na classe Shared Preferences
                        //SharedPreferencesHelper.funcSharedPreferences(Login.this, conteudoLogin.getId());
                        // vai bloquear a opção de voltar para a página de login
                        SharedPreferencesHelper.SessionStateSharedPreferences(Login.this, true);
                        finish();


                        //Toast.makeText(getApplicationContext(), "Bem Vindo " + this.conteudoLogin.getUsername() + "!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id_autenticacao", this.conteudoLogin.getId_autenticacao());
                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(), "A sua conta ainda não se encontra ativa!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Palavra Passe Errada!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Email errado!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}