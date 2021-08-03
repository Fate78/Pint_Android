package com.example.projeto_pint.basededados;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class BD extends BD_Default implements Runnable{

    /*URI dbUri = new URI(System.getenv("eu-cdbr-west-01.cleardb.com"));
    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:mysql://"+dbUri.getHost()+dbUri.getPath();*/
    private static final String dbUrl= "jdbc:mysql://@eu-cdbr-west-01.cleardb.com/heroku_c5cf5753d7cc12c?reconnect=true";
    private static final String username= "b98d4499fc6c28";
    private static final String password= "ca5826cc";
    private static final String port= "3306";
    private static final String ip= "eu-cdbr-west-01.cleardb.coms";
    private static final String dbName= "heroku_c5cf5753d7cc12c";
    private static final String url2= "jdbc:mysql://" + ip + ":" + port
            + "/" + dbName;

    private Connection connection;

    public BD() throws URISyntaxException {
        super();
        this.run();
        this.connect();
        this.disconnect();
    }
    public void run()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=(Connection) DriverManager.getConnection(url2, username, password);
        }catch(Exception e){
            this._message=e.getMessage();
            this._status=false;
        }
    }

    /*public void testDB()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=(Connection) DriverManager.getConnection(dbUrl, username, password);

            String result = "Database connection succes\n";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from utilizador");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next())
            {
                result += resultSetMetaData.getColumnName(1) + ": " + resultSet.getString(1) + "\n";
                result += resultSetMetaData.getColumnName(2) + ": " + resultSet.getString(2) + "\n";
                result += resultSetMetaData.getColumnName(3) + ": " + resultSet.getString(3) + "\n";
            }
        }catch(Exception e){
            this._message=e.getMessage();
            this._status=false;
        }
    }*/
    private void connect()
    {
        Thread thread= new Thread(this);
        thread.start();
        try{
            thread.join();
    }catch (Exception e){
        this._message=e.getMessage();
        this._status=false;
        }
    }

    private void disconnect()
    {
        if (this.connection!=null)
        {
            try {

            }catch (Exception e){

            }
        }
    }

    public ResultSet select(String query){
        this.connect();
        ResultSet resultset=null;
        try{
            resultset = new ExecuteBD(this.connection, query).execute().get();
        }catch (Exception e){
            this._message=e.getMessage();
            this._status=false;
        }
        return resultset;
    }

    public void execute(String query) {
        this.connect();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteBD(this.connection, query).execute().get();
        } catch (Exception e) {
            this._status = false;
            this._message = e.getMessage();
        }

    }
}
