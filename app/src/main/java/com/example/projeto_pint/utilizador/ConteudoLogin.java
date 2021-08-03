package com.example.projeto_pint.utilizador;


import android.annotation.SuppressLint;
import android.util.Log;

import com.example.projeto_pint.basededados.BD;
import com.example.projeto_pint.basededados.BD_Default;

import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConteudoLogin extends BD_Default {
    private int id_autenticacao;
    private String email, password;
    private String email_input, pass_input, novaPass;
    BD bd;
    public ConteudoLogin() throws URISyntaxException {
        this.id_autenticacao=-1;
        this.email="";
        this.password="";
        bd= new BD();
    }

    @SuppressLint("DefaultLocale")
    public void mudarPass() throws URISyntaxException {
        String comando="";
        comando=String.format("UPDATE autenticacao SET PASSWORD = '%s' WHERE ID_AUT = '%d';",
                this.getPassword(), this.getId_autenticacao());
        BD bd=new BD();
        bd.execute(comando);
    }

    public Boolean entrarEmail() throws URISyntaxException {
        try{
            ResultSet resultSet = bd.select("select count(*) from autenticacao where EMAIL = '" +this.getEmail_input()+ "'");
            if (resultSet.next()) {
                setId_autenticacao(resultSet.getInt("ID_AUT"));
                Log.i("INFO", "VALORES" + resultSet);
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }

    public Boolean entrarPassword() throws URISyntaxException {
        BD bd = new BD();

        try {
            ResultSet resultSet = bd.select("select * from autenticacao where EMAIL = '" +this.getEmail_input()+ "' and PASSWORD = '" +this.getPass_input()+ "'" );
            if (resultSet.next()) {
                setId_autenticacao(resultSet.getInt("ID_AUT"));

                Log.i("INFO", "VALORES" + resultSet);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Boolean verificaEstado() throws URISyntaxException {
        BD bd = new BD();

        try {
            ResultSet resultSet = bd.select("select * from autenticacao where EMAIL = '" +this.getEmail_input()+ "' and PASSWORD = '" +this.getPass_input());
            if (resultSet.next()) {
                setId_autenticacao(resultSet.getInt("ID_AUT"));

                Log.i("INFO", "VALORES" + resultSet);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String getNovaPass(){return novaPass;}

    public void setNovaPass(String novaPass){this.novaPass=novaPass;}

    public int getId_autenticacao() {
        return id_autenticacao;
    }

    public void setId_autenticacao(int id) {
        this.id_autenticacao = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_input() {
        return email_input;
    }

    public void setEmail_input(String email_input) {
        this.email_input = email_input;
    }

    public String getPass_input() {
        return pass_input;
    }

    public void setPass_input(String pass_input) {
        this.pass_input = pass_input;
    }
}
