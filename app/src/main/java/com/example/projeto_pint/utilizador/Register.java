package com.example.projeto_pint.utilizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projeto_pint.MainActivity;
import com.example.projeto_pint.R;
import com.google.android.material.textfield.TextInputLayout;

import java.net.URISyntaxException;
import java.sql.SQLException;

public class Register extends AppCompatActivity {
    private ConteudoRegister conteudoRegister;
    EditText email, password, nome, apelido, morada, data_nascimento, confirmarPass;
    TextInputLayout emailError, passError, confirmarPassError, nomeError, apelidoError, moradaError, data_nascimentoError;

    boolean isEmailValid, isPasswordValid, isFieldValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

    private void validar_dados_register() throws URISyntaxException, SQLException {
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

        if(nome.getText().toString().isEmpty())
        {
            nomeError.setError("Introduza o seu nome");
            isFieldValid= false;
        } else {
            isFieldValid= true;
            nomeError.setErrorEnabled(false);
        }

        if(apelido.getText().toString().isEmpty())
        {
            apelidoError.setError("Introduza o seu apelido");
            isFieldValid= false;
        } else {
            isFieldValid= true;
            apelidoError.setErrorEnabled(false);
        }

        if(morada.getText().toString().isEmpty())
        {
            moradaError.setError("Introduza a sua morada");
            isFieldValid= false;
        } else {
            isFieldValid= true;
            moradaError.setErrorEnabled(false);
        }

        if(data_nascimento.getText().toString().isEmpty())
        {
            data_nascimentoError.setError("Introduza a sua data de nascimento");
            isFieldValid= false;
        } else {
            isFieldValid= true;
            moradaError.setErrorEnabled(false);
        }

        if(confirmarPass.getText().toString().isEmpty())
        {
            confirmarPassError.setError("Confirme a sua palavra-passe");
            isFieldValid= false;
        } else {
            isFieldValid= true;
            confirmarPassError.setErrorEnabled(false);
        }

        if(isPasswordValid && isEmailValid)
        {
            this.conteudoRegister.setEmail(this.email.getText().toString());
            this.conteudoRegister.setPassword(this.password.getText().toString());
            this.conteudoRegister.setNome(this.nome.getText().toString());
            this.conteudoRegister.setApelido(this.apelido.getText().toString());
            this.conteudoRegister.setMorada(this.morada.getText().toString());
            this.conteudoRegister.setData_nascimento(this.data_nascimento.getText().toString());

            if(!conteudoRegister.verificarEmailExiste()) {
                this.conteudoRegister.registarUtilizador();
                this.conteudoRegister.registarAutenticacao();
                Toast.makeText(getApplicationContext(), "A sua conta foi criada!", Toast.LENGTH_SHORT).show();

                //ENTRAR NA PÁGINA DE LOGIN
                }
            }
        }
}