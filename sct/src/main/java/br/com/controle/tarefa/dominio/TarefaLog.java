package br.com.controle.tarefa.dominio;

public class TarefaLog {
	
	private Integer id;
	private Tarefa tarefa;
	private Integer novaPorcentagem;
	private Integer antigaPorcentagem;
	private Usuario usuarioAbertura;
	private Usuario usuarioFechamento;
	
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

	public Usuario getUsuarioAbertura() {
		return usuarioAbertura;
	}
	public void setUsuarioAbertura(Usuario usuarioAbertura) {
		this.usuarioAbertura = usuarioAbertura;
	}
	
	public Usuario getUsuarioFechamento() {
		return usuarioFechamento;
	}
	public void setUsuarioFechamento(Usuario usuarioFechamento) {
		this.usuarioFechamento = usuarioFechamento;
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
