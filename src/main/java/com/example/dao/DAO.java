package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DAO {
    private Connection conn;

    public DAO(Connection conn) {
        this.conn = conn;
    }

    public void listar(String nomeTabela) {
        var sql = "select * from "+ nomeTabela;
        
        try {
    
            var statement = conn.createStatement();
            var result = statement.executeQuery(sql);
            var metadata = result.getMetaData();
            int cols = metadata.getColumnCount();
    
        for (int i = 1; i <= cols; i++) {
            System.out.printf("%-25s |", metadata.getColumnName(i));
        }
        System.out.println();
        while (result.next()) {
            for (int i = 1; i <= cols; i++) {
                System.out.printf("%-25s |", result.getString(i));
            }
            System.out.println();
        }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Erro ao tentar executar consulta SQL: "+ e.getMessage());
        }   
        
        }
}
