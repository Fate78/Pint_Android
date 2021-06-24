package com.example.projeto_pint.utilizador;


import android.util.Log;

import com.example.projeto_pint.basededados.BD;
import com.example.projeto_pint.basededados.BD_Default;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConteudoLogin extends BD_Default {
    private int id, id_avatar, rank, pontuacao;
    private String email, username, password;
    private String email_input, pass_input, novaPass;

    public ConteudoLogin()
    {
        this.id=-1;
        this.id_avatar=-1;
        this.email="";
        this.username="";
        this.password="";
        this.rank=-1;
        this.pontuacao=-1;
    }

    public void mudarPass(){
        String comando="";
        comando=String.format("UPDATE Utilizador SET PASSWORD = '%s' WHERE ID_UTILIZADOR = '%d';",
                this.getPassword(), this.getId());
        BD bd=new BD();
        bd.execute(comando);
    }

    public Boolean entrarEmail(){
        BD bd=new BD();

        try{
            ResultSet resultSet = bd.select("select * from UTILIZADOR where email = '" +this.getEmail_input()+ "'");
            if (resultSet.next()) {
                setId(resultSet.getInt("ID_UTILIZADOR"));
                Log.i("INFO", "VALORES" + resultSet);
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }

    public Boolean entrarPassword() {
        BD bd = new BD();

        try {
            ResultSet resultSet = bd.select("select * from UTILIZADOR where email = '" +this.getEmail_input()+ "' and password = '" +this.getPass_input()+ "'" );
            if (resultSet.next()) {
                setId(resultSet.getInt("id_utilizador"));
                setUsername(resultSet.getString("username"));

                Log.i("INFO", "VALORES" + resultSet);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Boolean verificaEstado() {
        BD bd = new BD();

        try {
            ResultSet resultSet = bd.select("select * from UTILIZADOR where email = '" +this.getEmail_input()+ "' and password = '" +this.getPass_input()+ "' and deleted = '0'" );
            if (resultSet.next()) {
                setId(resultSet.getInt("id_utilizador"));
                setUsername(resultSet.getString("username"));

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_avatar() {
        return id_avatar;
    }

    public void setId_avatar(int id_avatar) {
        this.id_avatar = id_avatar;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
