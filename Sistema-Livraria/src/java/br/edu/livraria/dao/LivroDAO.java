/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.livraria.dao;

import br.edu.livraria.entidade.Livro;
import br.edu.livraria.util.FabricaConexao;
import br.edu.livraria.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;

/**
 *
 * @author Notorius B.I.G
 */
public class LivroDAO implements CrudDAO<Livro> {

    @Override
    public void salvar(Livro livro) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if (livro.getId() == null) {
                ps = conexao.prepareStatement("INSERT INTO `livro` (`nome`,`editora`,`ator`,`edicao`) VALUES (?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update livro set nome=?, editora=?, ator=?, edicao=? where id=?");
                ps.setInt(5, livro.getId());
            }
            ps.setString(1, livro.getNome());
            ps.setString(2, livro.getEditora());
            ps.setString(3, livro.getAtor());
            ps.setString(4, livro.getEdicao());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }

    @Override
    public void deletar(Livro livro) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("delete from livro where id = ?");
            ps.setInt(1, livro.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o livro!", ex);
        }
    }

    @Override
    public List<Livro> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from livro");
            ResultSet resultSet = ps.executeQuery();
            List<Livro> livros = new ArrayList<>();
            while (resultSet.next()) {
                Livro livro = new Livro();
                livro.setId(resultSet.getInt("id"));
                livro.setNome(resultSet.getString("nome"));
                livro.setEditora(resultSet.getString("editora"));
                livro.setAtor(resultSet.getString("ator"));
                livro.setEdicao(resultSet.getString("edicao"));
                livros.add(livro);
            }
            FabricaConexao.fecharConexao();
            return livros;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
            throw new ErroSistema("Erro ao buscar os livros!", ex);

        }
    }

}
