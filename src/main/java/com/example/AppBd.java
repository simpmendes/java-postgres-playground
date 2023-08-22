package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class AppBd {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            var conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "gitpod", "");
            System.out.println("Conexão com o banco realizada com sucesso.");
            
            var statement = conn.createStatement();
            var result = statement.executeQuery("select * from estado");
            while(result.next()){
                System.out.printf("Id: %d Nome: %s Uf: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.err.println("Não foi possível carregar a biblioteca para o acesso ao banco de dados :"+ e.getMessage());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Não foi possível conectar ao banco de dados: "+ e.getMessage());
        }

    }
}
