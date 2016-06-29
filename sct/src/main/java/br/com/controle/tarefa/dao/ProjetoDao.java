package br.com.controle.tarefa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.controle.tarefa.dominio.Projeto;
import br.com.controle.tarefa.util.BancoUtil;
import br.com.controle.tarefa.util.Conexao;

public class ProjetoDao {

	public ProjetoDao() throws Exception {
		try {
			Connection conexao = Conexao.getConexao();
			Statement stm = conexao.createStatement();
			String sqlCreate = BancoUtil.TabelaProjeto();

			stm.executeUpdate(sqlCreate);

		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
		}
	}

	public ArrayList<Projeto> findAll() throws SQLException {
		ArrayList<Projeto> retorno = new ArrayList<Projeto>();
		Statement statement = Conexao.getConexao().createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM sct.projeto");
		while (rs.next()) {
			retorno.add(populaProjeto(rs));
		}
		rs.close();
		statement.close();
		return retorno;
	}

	public void inserir(Projeto projeto) throws Exception {
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("INSERT INTO sct.projeto " + "(descricao, data_inicio, data_fim) " + " VALUES (?,?,?) ");
		pst.setString(1, projeto.getDescricao());
		pst.setDate(2, new Date( projeto.getDataInicio().getTime() ));
		pst.setDate(3, new Date( projeto.getDataFim().getTime() ));
		pst.executeUpdate();
		pst.close();
	}

	public void alterar(Projeto projeto) throws Exception {
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("UPDATE sct.projeto SET " + "descricao = ?, data_inicio = ?, data_fim = ? WHERE id_projeto = ? ");
		pst.setString(1, projeto.getDescricao());
		pst.setDate(2, new Date( projeto.getDataInicio().getTime() ));
		pst.setDate(3, new Date( projeto.getDataFim().getTime() ));
		pst.setInt(4, projeto.getId());
		pst.executeUpdate();
		pst.close();
	}

	public Projeto findById(int id) throws Exception {
		Projeto projeto = new Projeto();
		PreparedStatement pst = Conexao.getConexao().prepareStatement("SELECT * FROM sct.projeto WHERE id_projeto = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			projeto = populaProjeto(rs);
		}
		rs.close();
		pst.close();
		return projeto;
	}

	public void remover(Projeto projeto) throws Exception {
		PreparedStatement pst = Conexao.getConexao().prepareStatement("DELETE FROM sct.projeto WHERE id_projeto = ? ");
		pst.setInt(1, projeto.getId());
		pst.executeUpdate();
		pst.close();

	}

	public ArrayList<Projeto> findAllByDescricao(String descricao) throws Exception {
		ArrayList<Projeto> retorno = new ArrayList<Projeto>();
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("SELECT * FROM sct.projeto WHERE upper(descricao) like ?");
		int col = 1;
		pst.setString(col++, descricao + "%".toUpperCase());
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			retorno.add(populaProjeto(rs));
		}
		rs.close();
		pst.close();
		return retorno;
	}

	private Projeto populaProjeto(ResultSet rs) throws SQLException {
		Projeto projeto = new Projeto();
		projeto.setId(rs.getInt("id_projeto"));
		projeto.setDescricao(rs.getString("descricao"));
		projeto.setDataInicio(rs.getDate("data_inicio"));
		projeto.setDataFim(rs.getDate("data_fim"));
		return projeto;
	}

}
