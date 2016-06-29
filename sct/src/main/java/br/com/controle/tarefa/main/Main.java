package br.com.controle.tarefa.main;

import java.util.Date;

import br.com.controle.tarefa.dao.ProjetoDao;
import br.com.controle.tarefa.dao.TarefaDao;
import br.com.controle.tarefa.dao.UsuarioDao;
import br.com.controle.tarefa.dominio.Projeto;
import br.com.controle.tarefa.dominio.Tarefa;
import br.com.controle.tarefa.dominio.Usuario;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ProjetoDao projetoDao = new ProjetoDao();
		UsuarioDao usuarioDao = new UsuarioDao();
		TarefaDao tarefaDao = new TarefaDao();
		
		projetoDao.inserir(new Projeto("Projeto 1", new Date("22/01/2016"), new Date("05/05/2016") ));
		projetoDao.inserir(new Projeto("Projeto 2", new Date("20/07/2016"), new Date("12/12/2016") ));
		
		usuarioDao.inserir(new Usuario("usuario 1", "usuario1@email.com","secret"));
		usuarioDao.inserir(new Usuario("usuario 2", "usuario2@email.com","supersecret"));
		
		tarefaDao.inserir(new Tarefa("Tarefa 1", new Date(), null, 0, projetoDao.findById(1), usuarioDao.findById(2), null ));
		tarefaDao.inserir(new Tarefa("Tarefa 2", new Date(), null, 0, projetoDao.findById(2), usuarioDao.findById(1), null ));
		
//		TODO LANÇAR LOG TAREFA
		Tarefa tarefa1 = tarefaDao.findById(1);
		System.out.println("Tarefa: "+ tarefa1.getDescricao() + " - " + tarefa1.getPorcentagem() + "% - "+ tarefa1.getDataFechamento());
		
//		TODO LANÇAR LOG TAREFA
		Tarefa tarefa2 = tarefaDao.findById(2);
		System.out.println("Tarefa: "+ tarefa2.getDescricao() + " - " + tarefa2.getPorcentagem() + "% - "+ tarefa2.getDataFechamento());
		
		

	}

}
