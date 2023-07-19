package etec.etecapplication.dataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    public  Connection dataBaseLink;

    public  Connection getConnection(){

        String databaseName = "etecschool";
        String databaseUser = "###";
        String databasePassword = "###";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;


        try{
            dataBaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println("Successs");

        }catch (Exception e){
            e.printStackTrace();
        }


        return dataBaseLink;
    }
}
