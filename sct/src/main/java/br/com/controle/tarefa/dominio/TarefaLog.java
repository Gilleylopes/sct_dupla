package br.com.controle.tarefa.dominio;

public class TarefaLog {
	
	private Integer id;
	private Tarefa tarefa;
	private String novaPorcentagem;
	private String antigaPorcentagem;
	private Usuario usuarioAbertura;
	private Usuario usuariofechamento;
	
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
	public String getNovaPorcentagem() {
		return novaPorcentagem;
	}
	public void setNovaPorcentagem(String novaPorcentagem) {
		this.novaPorcentagem = novaPorcentagem;
	}
	public String getAntigaPorcentagem() {
		return antigaPorcentagem;
	}
	public void setAntigaPorcentagem(String antigaPorcentagem) {
		this.antigaPorcentagem = antigaPorcentagem;
	}
	public Usuario getUsuarioAbertura() {
		return usuarioAbertura;
	}
	public void setUsuarioAbertura(Usuario usuarioAbertura) {
		this.usuarioAbertura = usuarioAbertura;
	}
	public Usuario getUsuariofechamento() {
		return usuariofechamento;
	}
	public void setUsuariofechamento(Usuario usuariofechamento) {
		this.usuariofechamento = usuariofechamento;
	}
	

}
