package com.classes.Conexao;

import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.sql.Connection;

public class Conexao {
	final static String NOME_DO_BANCO = "clickbus";
    public static Connection conectar() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/" + NOME_DO_BANCO;
            return DriverManager.getConnection(url,"root","admin123");
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }
}
