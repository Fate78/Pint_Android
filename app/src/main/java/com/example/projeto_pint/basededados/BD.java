package com.example.projeto_pint.basededados;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class BD extends BD_Default implements Runnable{
    /*private static String ip = "192.168.1.8";
    private static String port = "1433";
    private static String driver = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "Pint";*/
   //private static String server = "DESKTOP-LODJ3UU";

    URI dbUri = new URI(System.getenv("eu-cdbr-west-01.cleardb.com"));
    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:mysql://"+dbUri.getHost()+dbUri.getPath();

    private Connection connection = null;

    public BD() throws URISyntaxException {
        super();
        this.run();
        this.connect();
        this.disconnect();
    }
    public void run()
    {
        try{
            connection=(Connection) DriverManager.getConnection(dbUrl, username, password);
        }catch(Exception e){
            this._message=e.getMessage();
            this._status=false;
        }
    }
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
