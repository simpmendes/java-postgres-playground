package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class EstadoDAO {
    private Connection conn;

    public EstadoDAO(Connection conn){
        this.conn = conn;
    }

    public void listar() {
        
        try {
            
            
            var statement = conn.createStatement();
            var result = statement.executeQuery("select * from estado");
            while(result.next()){
                System.out.printf("Id: %d Nome: %s Uf: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
            }
         catch (SQLException e) {
            // TODO Auto-generated catch block
                System.err.println("Não foi possível conectar ao banco de dados: "+ e.getMessage());
        }
    }

    public void localizar(String uf) {
        try {
            var sql = "select * from estado where uf = ?";
            var statement = conn.prepareStatement(sql);
            statement.setString(1, uf);
            var result = statement.executeQuery();
            if(result.next())
                System.out.printf("Id: %d Nome: %s Uf: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Erro ao tentar executar consulta SQL");
        }
    }
}
