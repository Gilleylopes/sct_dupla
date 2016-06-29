package br.com.controle.tarefa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.controle.tarefa.dominio.Tarefa;
import br.com.controle.tarefa.util.BancoUtil;
import br.com.controle.tarefa.util.Conexao;

public class TarefaDao {

	public TarefaDao() throws Exception {
		try {
			Connection conexao = Conexao.getConexao();
			Statement stm = conexao.createStatement();
			String sqlCreate = BancoUtil.TabelaTarefa();

			stm.executeQuery(sqlCreate);

		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
		}
	}

	public ArrayList<Tarefa> findAll() throws Exception {
		ArrayList<Tarefa> retorno = new ArrayList<Tarefa>();
		Statement statement = Conexao.getConexao().createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM sct.tarefa");
		while (rs.next()) {
			retorno.add(populaTarefa(rs));
		}
		rs.close();
		statement.close();
		return retorno;
	}

	public void inserir(Tarefa tarefa) throws Exception {
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("INSERT INTO sct.tarefa " + "(titulo, descricao, data_abertura, data_fechamento, "
						+ " porcentagem, id_projeto, id_usuario_abertura, id_usuario_fechamento) "
						+ " VALUES (?,?,?,?,?,?,?,?) ");
		pst.setString(1, tarefa.getTitulo());
		pst.setString(2, tarefa.getDescricao());
		pst.setDate(3, new Date(tarefa.getDataAbertura().getTime()));
		pst.setDate(4, new Date(tarefa.getDataFechamento().getTime()));
		pst.setInt(5, tarefa.getPorcentagem());
		pst.setInt(6, tarefa.getProjeto().getId());
		pst.setInt(7, tarefa.getUsuarioAbertura().getId());
		pst.setInt(8, tarefa.getUsuarioFechamento().getId());
		pst.executeUpdate();
		pst.close();
	}

	public void alterar(Tarefa tarefa) throws Exception {
		PreparedStatement pst = Conexao.getConexao().prepareStatement(
				"UPDATE sct.tarefa SET " + "titulo = ?, descricao = ?, data_abertura = ?, data_fechamento = ?, "
						+ " porcentagem = ?, id_projeto = ?, id_usuario_abertura = ?, "
						+ " id_usuario_fechamento = ? WHERE id_tarefa = ?");
		pst.setString(1, tarefa.getTitulo());
		pst.setString(2, tarefa.getDescricao());
		pst.setDate(3, new Date(tarefa.getDataAbertura().getTime()));
		pst.setDate(4, new Date(tarefa.getDataFechamento().getTime()));
		pst.setInt(5, tarefa.getPorcentagem());
		pst.setInt(6, tarefa.getProjeto().getId());
		pst.setInt(7, tarefa.getUsuarioAbertura().getId());
		pst.setInt(8, tarefa.getUsuarioFechamento().getId());
		pst.setInt(9, tarefa.getId());
		pst.executeUpdate();
		pst.close();
	}

	public Tarefa findById(int id) throws Exception {
		Tarefa tarefa = new Tarefa();
		PreparedStatement pst = Conexao.getConexao().prepareStatement("SELECT * FROM sct.tarefa WHERE id_tarefa = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			tarefa = populaTarefa(rs);
		}
		rs.close();
		pst.close();
		return tarefa;
	}

	public void remover(Tarefa tarefa) throws Exception {
		PreparedStatement pst = Conexao.getConexao().prepareStatement("DELETE FROM sct.tarefa WHERE id_tarefa= ? ");
		pst.setInt(1, tarefa.getId());
		pst.executeUpdate();
		pst.close();

	}

	public ArrayList<Tarefa> findAllByTitulo(String nome) throws Exception {
		ArrayList<Tarefa> retorno = new ArrayList<Tarefa>();
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("SELECT * FROM sct.tarefa WHERE upper(titulo) like ?");
		int col = 1;
		pst.setString(col++, nome + "%".toUpperCase());
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			retorno.add(populaTarefa(rs));
		}
		rs.close();
		pst.close();
		return retorno;
	}

	private Tarefa populaTarefa(ResultSet rs) throws Exception{
		Tarefa tarefa = new Tarefa();
		UsuarioDao usuarioDao = new UsuarioDao();
		tarefa.setId(rs.getInt("id_tarefa"));
		tarefa.setTitulo(rs.getString("titulo"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setPorcentagem(rs.getInt("porcentagem"));
		tarefa.setDataAbertura(rs.getDate("data_abertura"));
		tarefa.setDataFechamento(rs.getDate("data_fechamento"));
		tarefa.setUsuarioAbertura(usuarioDao.findById( rs.getInt("id_usuario_bertura") ));
		tarefa.setUsuarioFechamento(usuarioDao.findById( rs.getInt("id_usuario_fechamento") ));
		return tarefa;
	}

}
