package br.com.controle.tarefa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Connection conexao;
	private static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static String nomeBanco = "sct";
    
    /**
     * Recuperar conexão. Abre nova conexão caso não possua.
     * 
     * @return
     */
    public static Connection getConexao (){
        try {
            Class.forName(DRIVER);
            if (conexao == null || conexao.isClosed())
            	conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/"+nomeBanco, 
            					"admin", "secret");
            
            if (conexao != null) {
                return conexao;
            } else {
                System.out.println("Não foi possível conectar com o banco");
                return null;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
            return null;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
            e.printStackTrace();
            return null;
        }
    }   

	/**
	 * Fechar conexão corrente
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		try {
			if (conexao != null && !conexao.isClosed())
				conexao.close();
		} catch (SQLException e) {
			throw e;
		}
	}


}
