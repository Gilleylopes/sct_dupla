package br.com.controle.tarefa.util;

import java.sql.Connection;
import java.sql.SQLException;

public class BancoUtil {
	
	Connection conexao = null;

    public void CriarTabelas() {
        Conexao con = new Conexao();
        try {
            if (conexao == null) {
                conexao = con.getConexao();
            }

            java.sql.Statement stm = conexao.createStatement();

            String sqlCreate = TabelaTarefa()  +
            					TabelaProjeto() +
            					TabelaUsuario() +
            					TabelaTarefaLog();
            
            stm.executeQuery(sqlCreate);

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
            e.printStackTrace();
        }
    }
    
    /**
     * Criar Tabela Tarefa
     * @return
     */
    public static String TabelaTarefa(){
    	return "CREATE TABLE IF NOT EXISTS sct.tarefa ( "
    			+ "id_tarefa serial not null, "
    			+ "titulo character varying (255), "
    			+ "descricao character varying (255), "
    			+ "data_abertura date, "
    			+ "data_fechamento date, "
    			+ "porcentagem integer, "
    			+ "id_projeto integer, "
    			+ "id_usuario_abertura integer, "
    			+ "id_usuario_fechamento integer, "
    			+ "CONSTRAINT pk_Tarefa PRIMARY KEY (id_tarefa));\n";
    }
    
    /**
     * Criar Tabela Projeto
     * @return
     */
    public static String TabelaProjeto(){
    	return "CREATE TABLE IF NOT EXISTS sct.projeto ( "
    			+ "id_projeto serial not null, "
    			+ "descricao character varying (255), "
    			+ "data_inicio date, "
    			+ "data_fim date, "
    			+ "CONSTRAINT pk_Projeto PRIMARY KEY (id_projeto));\n";
    }
    
    /**
     * Criar Tabela Usuário
     * @return
     */
    public static String TabelaUsuario(){
    	return "CREATE TABLE IF NOT EXISTS sct.usuario ( "
    			+ "id_usuario serial not null, "
    			+ "nome character varying (255), "
    			+ "email character varying (255), "
    			+ "senha character varying (255), "
    			+ "CONSTRAINT pk_Usuario PRIMARY KEY (id_usuario));\n";
    }
    
    /**
     * Criar Tabela Tarefa Log
     * @return
     */
    public static String TabelaTarefaLog(){
    	return "CREATE TABLE IF NOT EXISTS sct.tarefa_log ( "
    			+ "id_tarefa_log serial not null, "
    			+ "id_tarefa integer, "
    			+ "nova_porcentagem integer, "
    			+ "antiga_porcentagem integer, "
    			+ "id_usuario_responsavel integer, "
    			+ "CONSTRAINT pk_Tarefa_log PRIMARY KEY (id_tarefa_log),"
    			+ "FOREIGN KEY (id_tarefa) REFERENCES sct.tarefa (id_tarefa));\n";
    }

}
