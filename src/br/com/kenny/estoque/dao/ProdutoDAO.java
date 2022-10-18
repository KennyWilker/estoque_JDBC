package br.com.kenny.estoque.dao;

import br.com.kenny.estoque.bd.EstoqueBD;
import br.com.kenny.estoque.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kenny
 */
public class ProdutoDAO {
    
    public void criar(Produto p) {
        
        Connection con = EstoqueBD.abrirConexao();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produto (descricao,quantidade,preco)VALUES(?,?,?)");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto Salvo!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            EstoqueBD.fecharConexao(con, stmt);
        }
    }

    public List<Produto> retornar() {

        Connection con = EstoqueBD.abrirConexao();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            EstoqueBD.fecharConexao(con, stmt, rs);
        }
        return produtos;
    }
    
    public List<Produto> retornarPorDescricao(String desc) {

        Connection con = EstoqueBD.abrirConexao();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                //produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            EstoqueBD.fecharConexao(con, stmt, rs);
        }
        return produtos;
    }

    public void atualizar(Produto p) {

        Connection con = EstoqueBD.abrirConexao();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET descricao = ? ,quantidade = ?,preco = ? WHERE id = ?");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto atualizado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            EstoqueBD.fecharConexao(con, stmt);
        }
    }
    
    public void deletar(Produto p) {

        Connection con = EstoqueBD.abrirConexao();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto excluido!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            EstoqueBD.fecharConexao(con, stmt);
        }
    }
}