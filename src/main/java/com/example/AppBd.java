package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppBd {
    private static final String PASSWORD = "";
    private static final String USERNAME = "gitpod";
    private static final String JDBC_URL = "jdbc:postgresql://localhost/postgres";

    public static void main(String[] args) {
        new AppBd();
    }
    public AppBd(){
        carregarDriverJDBC();
        try (var conn = getConnection()){
            listarEstados(conn);
            localizarEstado(conn, "PR");
            listarDadosTabela(conn, "cliente");    
        } catch (SQLException e) {
            // TODO: handle exception
        }
        
        
    }

  private void listarDadosTabela(Connection conn, String nomeTabela) {
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
 private void localizarEstado(Connection conn, String uf) {
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

 private void listarEstados(Connection conn) {
        
        try {
            
            System.out.println("Conexão com o banco realizada com sucesso.");
            
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

    private static Connection getConnection() throws SQLException{
        return  DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private static void carregarDriverJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.err.println("Não foi possível carregar a biblioteca para o acesso ao banco de dados :"+ e.getMessage());
        }
    }
}
