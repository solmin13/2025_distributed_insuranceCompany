package main;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private String url = "";
    private String user = "solmin";
    private String password = "";
    public Connection con;

    public DBConnection(){
        try{
            this.con = DriverManager.getConnection(url,user,password);
            System.out.println("연결 성공");
        }catch(SQLException e){
            System.out.println("연결 실패");
            e.printStackTrace();
        }
    }

}
