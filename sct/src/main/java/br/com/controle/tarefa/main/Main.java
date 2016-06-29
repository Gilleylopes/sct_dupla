package br.com.controle.tarefa.main;

import java.util.Date;

import br.com.controle.tarefa.dao.ProjetoDao;
import br.com.controle.tarefa.dao.TarefaDao;
import br.com.controle.tarefa.dao.TarefaLogDao;
import br.com.controle.tarefa.dao.UsuarioDao;
import br.com.controle.tarefa.dominio.Projeto;
import br.com.controle.tarefa.dominio.Tarefa;
import br.com.controle.tarefa.dominio.TarefaLog;
import br.com.controle.tarefa.dominio.Usuario;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
		ProjetoDao projetoDao = new ProjetoDao();
		UsuarioDao usuarioDao = new UsuarioDao();
		TarefaDao tarefaDao = new TarefaDao();
		TarefaLogDao tarefaLogDao = new TarefaLogDao();

		criarProjetos(projetoDao);
		criarUsuarios(usuarioDao);
		criarTarefas(tarefaDao, projetoDao, usuarioDao);

		// TODO LAN�AR LOG TAREFA
		Tarefa tarefa1 = tarefaDao.findById(1);
		tarefaLogDao.inserir(new TarefaLog(tarefa1, 30, tarefa1.getPorcentagem(), usuarioDao.findById(2)));

		tarefa1 = tarefaDao.findById(1);
		System.out.println("Tarefa 1 Antes: " + tarefa1.getDescricao() + " - " + tarefa1.getPorcentagem() + "% - "
				+ tarefa1.getDataFechamento());

		tarefaLogDao.inserir(new TarefaLog(tarefa1, 100, tarefa1.getPorcentagem(), usuarioDao.findById(2)));

		tarefa1 = tarefaDao.findById(1);
		System.out.println("Tarefa 1 Depois: " + tarefa1.getDescricao() + " - " + tarefa1.getPorcentagem() + "% - "
				+ tarefa1.getDataFechamento());

		// TODO LAN�AR LOG TAREFA
		Tarefa tarefa2 = tarefaDao.findById(2);
		tarefaLogDao.inserir(new TarefaLog(tarefa2, 30, tarefa2.getPorcentagem(), usuarioDao.findById(1)));

		tarefa2 = tarefaDao.findById(2);
		System.out.println("Tarefa 2 Antes: " + tarefa2.getDescricao() + " - " + tarefa2.getPorcentagem() + "% - "
				+ tarefa2.getDataFechamento());

		tarefaLogDao.inserir(new TarefaLog(tarefa2, 100, tarefa2.getPorcentagem(), usuarioDao.findById(1)));
		tarefa2 = tarefaDao.findById(2);
		System.out.println("Tarefa 2 Depois: " + tarefa2.getDescricao() + " - " + tarefa2.getPorcentagem() + "% - "
				+ tarefa2.getDataFechamento());

	}
	
	public static void criarProjetos(ProjetoDao projetoDao) throws Exception{
		projetoDao.inserir(new Projeto("Projeto 1", new Date("22/01/2016"), new Date("05/05/2016") ));
		projetoDao.inserir(new Projeto("Projeto 2", new Date("20/07/2016"), new Date("12/12/2016") ));
		
	}
	
	public static void criarUsuarios(UsuarioDao usuarioDao) throws Exception{
		usuarioDao.inserir(new Usuario("usuario 1", "usuario1@email.com","secret"));
		usuarioDao.inserir(new Usuario("usuario 2", "usuario2@email.com","supersecret"));
	}
	
	public static void criarTarefas(TarefaDao tarefaDao, ProjetoDao projetoDao, UsuarioDao usuarioDao) throws Exception{
		tarefaDao.inserir(new Tarefa("Titulo Tarefa 1", "Tarefa 1", new Date(), null, 0, projetoDao.findById(1), usuarioDao.findById(2), null ));
		tarefaDao.inserir(new Tarefa("Titulo Tarefa 2", "Tarefa 2", new Date(), null, 0, projetoDao.findById(2), usuarioDao.findById(1), null ));
	}
	

}
