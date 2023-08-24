package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.example.model.Estado;

public class EstadoDAO extends DAO {
    private Connection conn;

    public EstadoDAO(Connection conn){
        super(conn);
    }

    public List<Estado> listar() throws SQLException {
        var lista = new LinkedList<Estado>();
      
            var statement = conn.createStatement();
            var result = statement.executeQuery("select * from estado");
            while(result.next()){
                var estado = new Estado();
                estado.setId(result.getLong("id"));
                estado.setNome(result.getString("nome")); 
                estado.setNome(result.getString("uf"));
                lista.add(estado);
            }
        return lista;
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
            System.err.println("Erro ao tentar executar consulta SQL");
        }
    }
}
