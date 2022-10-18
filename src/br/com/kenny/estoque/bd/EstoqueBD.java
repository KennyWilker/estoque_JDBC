package br.com.kenny.estoque.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstoqueBD {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String CAMINHODB = "jdbc:mysql://localhost:3306/produtosDB";
    private static final String USUARIO = "root";
    private static final String SENHA = "237733";

    public static Connection abrirConexao() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(CAMINHODB, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }

    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        fecharConexao(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        EstoqueBD.fecharConexao(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}