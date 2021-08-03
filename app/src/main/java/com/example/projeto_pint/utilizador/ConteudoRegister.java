package com.example.projeto_pint.utilizador;

import android.util.Log;

import com.example.projeto_pint.basededados.BD;
import com.example.projeto_pint.basededados.BD_Default;

import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConteudoRegister extends BD_Default {
    private String email;
    private String password;
    private String nome;
    private String apelido;
    private String morada;
    private String data_nascimento;
    BD bd;

    public ConteudoRegister() throws URISyntaxException{
        this.email="";
        this.password="";
        this.password="";
        this.bd=new BD();
    }

    public void registarUtilizador() throws URISyntaxException{
        String comando="";
        comando=String.format("INSERT INTO utilizador(id_gpermissao, ranking, nome, apelido, data_nascimento, morada, email, dt_criacao) Values(0, 0, " +
                this.getNome() + ", " + this.getApelido() + ", " + this.getData_nascimento() + ", "+ this.getMorada() + ", " + this.getEmail() + ", sysdate()" + ")");
        bd.execute(comando);
    }

    public void registarAutenticacao() throws URISyntaxException{
        String comando="";
        comando=String.format("INSERT INTO autenticacao(id_utilizador, email, password, data) Values(last_insert_id(), " +
                this.getEmail() + ", " + this.getPassword() + "sysdate()"+ ")");
        bd.execute(comando);
    }

    public boolean verificarEmailExiste() throws URISyntaxException, SQLException {
        try {
            ResultSet resultSet = bd.select("select count(*) from autenticacao where EMAIL = '" + this.getEmail() + "'");
            if (resultSet.next()) {
                Log.i("INFO", "Valores" + resultSet);
                return true;
            }
        }catch(Exception e){
            return false;}
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
