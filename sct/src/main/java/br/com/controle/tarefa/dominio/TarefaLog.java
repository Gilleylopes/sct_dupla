package br.com.controle.tarefa.dominio;


public class TarefaLog {
	
	private Integer id;
	private Tarefa tarefa;
	private Integer novaPorcentagem;
	private Integer antigaPorcentagem;
	private Usuario usuarioResponsavel;
	
	
	public TarefaLog(){
		
	}
	
	/**
	 * Construtor do objeto
	 * @param tarefa
	 * @param novaPorcentagem
	 * @param antigaPorcentagem
	 * @param usuarioResponsavel
	 */
	public TarefaLog (Tarefa tarefa, Integer novaPorcentagem, Integer antigaPorcentagem, Usuario usuarioResponsavel){
		this.tarefa = tarefa;
		this.novaPorcentagem = novaPorcentagem;
		this.antigaPorcentagem = antigaPorcentagem;
		this.usuarioResponsavel = usuarioResponsavel;
	}
	


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}
	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}
	public Integer getNovaPorcentagem() {
		return novaPorcentagem;
	}
	public void setNovaPorcentagem(Integer novaPorcentagem) {
		this.novaPorcentagem = novaPorcentagem;
	}
	public Integer getAntigaPorcentagem() {
		return antigaPorcentagem;
	}
	public void setAntigaPorcentagem(Integer antigaPorcentagem) {
		this.antigaPorcentagem = antigaPorcentagem;
	}
	

}
