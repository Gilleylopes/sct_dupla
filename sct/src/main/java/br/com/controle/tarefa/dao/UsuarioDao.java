package br.com.controle.tarefa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.controle.tarefa.dominio.Usuario;
import br.com.controle.tarefa.util.BancoUtil;
import br.com.controle.tarefa.util.Conexao;

public class UsuarioDao {

	public UsuarioDao() throws Exception {
		try {
			Connection conexao = Conexao.getConexao();
			Statement stm = conexao.createStatement();
			String sqlCreate = BancoUtil.TabelaUsuario();

			stm.executeQuery(sqlCreate);

		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> findAll() throws SQLException {
		ArrayList<Usuario> retorno = new ArrayList<Usuario>();
		Statement statement = Conexao.getConexao().createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM sct.usuario");
		while (rs.next()) {
			retorno.add(populaUsuario(rs));
		}
		rs.close();
		statement.close();
		return retorno;
	}

	public void inserir(Usuario usuario) throws Exception {
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("INSERT INTO sct.usuario " + "(nome, email, senha " + " VALUES (?,?,?) ");
		pst.setString(1, usuario.getNome());
		pst.setString(2, usuario.getEmail());
		pst.setString(3, usuario.getSenha());
		pst.executeUpdate();
		pst.close();
	}

	public void alterar(Usuario usuario) throws Exception {
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("UPDATE sct.usuario SET " + "nome = ?, email = ?, senha = ? WHERE id_usuario = ? ");
		pst.setString(1, usuario.getNome());
		pst.setString(2, usuario.getEmail());
		pst.setString(3, usuario.getSenha());
		pst.setInt(4, usuario.getId());
		pst.executeUpdate();
		pst.close();
	}

	public Usuario findById(int id) throws Exception {
		Usuario tarefa = new Usuario();
		PreparedStatement pst = Conexao.getConexao().prepareStatement("SELECT * FROM sct.usuario WHERE id_usuario = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			tarefa = populaUsuario(rs);
		}
		rs.close();
		pst.close();
		return tarefa;
	}

	public void remover(Usuario usuario) throws Exception {
		PreparedStatement pst = Conexao.getConexao().prepareStatement("DELETE FROM sct.usuario WHERE id_usuario = ? ");
		pst.setInt(1, usuario.getId());
		pst.executeUpdate();
		pst.close();

	}

	public ArrayList<Usuario> findAllByNome(String nome) throws Exception {
		ArrayList<Usuario> retorno = new ArrayList<Usuario>();
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("SELECT * FROM sct.usuario WHERE upper(nome) like ?");
		int col = 1;
		pst.setString(col++, nome + "%".toUpperCase());
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			retorno.add(populaUsuario(rs));
		}
		rs.close();
		pst.close();
		return retorno;
	}

	private Usuario populaUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("id_tarefa"));
		usuario.setNome(rs.getString("nome"));
		usuario.setEmail(rs.getString("email"));
		usuario.setSenha(rs.getString("senha"));
		return usuario;
	}

}
