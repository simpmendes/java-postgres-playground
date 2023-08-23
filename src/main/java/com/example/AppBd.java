package com.example;
import java.sql.SQLException;
import com.example.dao.ConnectionManager;
import com.example.dao.DAO;
import com.example.dao.EstadoDAO;
import com.example.dao.ProdutoDAO;
import com.example.model.Marca;
import com.example.model.Produto;

public class AppBd {
    public static void main(String[] args) {
        new AppBd();
    }
    public AppBd(){
        carregarDriverJDBC();
        try (var conn = ConnectionManager.getConnection()){
            var estadoDAO = new EstadoDAO(conn);
            estadoDAO.listar();
            estadoDAO.localizar("PR");
            
             var marca = new Marca();
            marca.setId(1L);
            var produto = new Produto();
            produto.setMarca(marca);
            produto.setNome("Produto teste");
            produto.setValor(10);

            var produtoDAO = new ProdutoDAO(conn);
            
            produtoDAO.inserir(produto);
            produtoDAO.excluir(201L);  
            
            var dao = new DAO(conn);
            dao.listar("produto");
        } catch (SQLException e) {
            // TODO: handle exception
            System.err.println("Erro ao tentar executar consulta SQL: "+ e.getMessage());
        }
        
        
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
