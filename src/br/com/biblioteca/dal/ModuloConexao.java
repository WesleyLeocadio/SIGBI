package br.com.biblioteca.dal;

import java.sql.*;
import javax.swing.JOptionPane;

public class ModuloConexao {

    //Statement é reponsável por realizar a pesquisa no BD
    public Statement stn;
    //ResultSet vai armazenar o resultado dessa pesquisa
    public ResultSet rs;

    //connection resposavel pela conecção com BD
    public static Connection conector() {
        java.sql.Connection conexao = null;
        //alinha abaixo chama o drive
        String driver = "com.mysql.jdbc.Driver";
        //armazenando informações referente ao banco
        String url = "jdbc:mysql://localhost:3306/dbbiblioteca1";
        String user = "root";
        String password = "";
        //estabelecendo a conexao com bd
        try {
            Class.forName(driver);
            conexao=DriverManager.getConnection(url,user,password);
            return conexao;
           
        } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Erro ao se Conectar com o Banco de Dados "+e.getMessage());
            System.out.println(e);
      return null;
         
        }
        

    }
   
}
