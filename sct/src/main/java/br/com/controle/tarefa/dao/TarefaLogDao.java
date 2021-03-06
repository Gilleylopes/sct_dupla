package br.com.controle.tarefa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.controle.tarefa.dominio.TarefaLog;
import br.com.controle.tarefa.util.BancoUtil;
import br.com.controle.tarefa.util.Conexao;

public class TarefaLogDao {
	public TarefaLogDao() throws Exception {
		try {
			Connection conexao = Conexao.getConexao();
			Statement stm = conexao.createStatement();
			String sqlCreate = BancoUtil.TabelaTarefaLog();

			stm.executeUpdate(sqlCreate);

		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
		}
	}

	public ArrayList<TarefaLog> findAll() throws Exception {
		ArrayList<TarefaLog> retorno = new ArrayList<TarefaLog>();
		Statement statement = Conexao.getConexao().createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM sct.tarefa_log");
		while (rs.next()) {
			retorno.add(populaTarefaLog(rs));
		}
		rs.close();
		statement.close();
		return retorno;
	}

	public void inserir(TarefaLog tarefaLog) throws Exception {
		Connection connection = Conexao.getConexao();
		connection.setAutoCommit(false);
		try{
		PreparedStatement pst = connection
				.prepareStatement("INSERT INTO sct.tarefa_log " + "(id_tarefa, nova_porcentagem, antiga_porcentagem, id_usuario_responsavel) "
						+ " VALUES (?,?,?,?) ");
		pst.setInt(1, tarefaLog.getTarefa().getId());
		pst.setInt(2, tarefaLog.getNovaPorcentagem());
		pst.setInt(3, tarefaLog.getAntigaPorcentagem());
		pst.setInt(4, tarefaLog.getUsuarioResponsavel().getId());
		pst.executeUpdate();
		
		atualizarPorcentagem(tarefaLog, connection);
		atualizarUsuario(tarefaLog, connection);
		
		if(tarefaLog.getNovaPorcentagem() == 100)
			fecharTarefa(tarefaLog, connection);
		
		connection.commit();
		pst.close();
		}catch(Exception e){
			connection.rollback();
		}finally{
			connection.setAutoCommit(true);
		}
	}

	public void alterar(TarefaLog tarefaLog) throws Exception {
		PreparedStatement pst = Conexao.getConexao().prepareStatement(
				"UPDATE sct.tarefa_log SET " + "nova_porcentagem = ?, antiga_porcentagem = ?, id_usuario_responsavel = ?" +
				" WHERE id_tarefa_log = ?");
		pst.setInt(1, tarefaLog.getNovaPorcentagem());
		pst.setInt(2, tarefaLog.getAntigaPorcentagem());
		pst.setInt(3, tarefaLog.getUsuarioResponsavel().getId());
		pst.setInt(4, tarefaLog.getId());
		pst.executeUpdate();
		pst.close();
	}

	public TarefaLog findById(int id) throws Exception {
		TarefaLog tarefaLog = new TarefaLog();
		PreparedStatement pst = Conexao.getConexao().prepareStatement("SELECT * FROM sct.tarefa_log WHERE id_tarefa_log = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			tarefaLog = populaTarefaLog(rs);
		}
		rs.close();
		pst.close();
		return tarefaLog;
	}

	public void remover(TarefaLog tarefaLog) throws Exception {
		PreparedStatement pst = Conexao.getConexao().prepareStatement("DELETE FROM sct.tarefa_log WHERE id_tarefa_log= ? ");
		pst.setInt(1, tarefaLog.getId());
		pst.executeUpdate();
		pst.close();

	}

	public ArrayList<TarefaLog> findAllByTitulo(String nome) throws Exception {
		ArrayList<TarefaLog> retorno = new ArrayList<TarefaLog>();
		PreparedStatement pst = Conexao.getConexao()
				.prepareStatement("SELECT * FROM sct.tarefa_log WHERE upper(titulo) like ?");
		int col = 1;
		pst.setString(col++, nome + "%".toUpperCase());
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			retorno.add(populaTarefaLog(rs));
		}
		rs.close();
		pst.close();
		return retorno;
	}

	private TarefaLog populaTarefaLog(ResultSet rs) throws Exception{
		TarefaLog tarefaLog = new TarefaLog();
		UsuarioDao usuarioDao = new UsuarioDao();
		TarefaDao tarefaDao = new TarefaDao();
		tarefaLog.setId(rs.getInt("id_tarefa_log"));
		tarefaLog.setTarefa(tarefaDao.findById( rs.getInt("id_tarefa") ));
		tarefaLog.setNovaPorcentagem(rs.getInt("nova_porcentagem"));
		tarefaLog.setAntigaPorcentagem(rs.getInt("data_abertura"));
		tarefaLog.setUsuarioResponsavel(usuarioDao.findById( rs.getInt("id_usuario_responsavel") ));
		return tarefaLog;

	}
	
	public void atualizarPorcentagem (TarefaLog tarefaLog, Connection connection) throws SQLException{		
		PreparedStatement pst = Conexao.getConexao().prepareStatement(
				"UPDATE sct.tarefa SET " + " porcentagem = ? WHERE id_tarefa = ?");
		pst.setInt(1, tarefaLog.getNovaPorcentagem());
		pst.setInt(2, tarefaLog.getTarefa().getId());
		pst.executeUpdate();
		pst.close();
	}
	
	public void atualizarUsuario (TarefaLog tarefaLog, Connection connection) throws SQLException{	
		PreparedStatement pst = Conexao.getConexao().prepareStatement(
				"UPDATE sct.tarefa SET " + " id_usuario_abertura = ? WHERE id_tarefa = ?");
		pst.setInt(1, tarefaLog.getUsuarioResponsavel().getId());
		pst.setInt(2, tarefaLog.getTarefa().getId());
		pst.executeUpdate();
		pst.close();
	}
	
	public void fecharTarefa (TarefaLog tarefaLog, Connection connection) throws SQLException{	
		PreparedStatement pst = Conexao.getConexao().prepareStatement(
				"UPDATE sct.tarefa SET " + " data_fechamento = ? , id_usuario_fechamento = ? WHERE id_tarefa = ?");
		pst.setDate(1, new Date(new java.util.Date().getTime()));
		pst.setInt(2, tarefaLog.getUsuarioResponsavel().getId());
		pst.setInt(3, tarefaLog.getTarefa().getId());
		pst.executeUpdate();
		pst.close();
	}
}
