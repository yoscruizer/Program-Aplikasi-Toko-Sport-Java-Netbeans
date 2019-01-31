/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Aplikasi_yoscruizer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author herry
 */
public class AksiDB {
    public String dbURL = "jdbc:mysql://localhost:3306/aplikasi_sport";
    public String username ="root";
    public String password = "";
    public Connection dbCon = null;
    public Statement stmt = null;
    public ResultSet rs = null;
    
    public AksiDB(){
        
    }
}
