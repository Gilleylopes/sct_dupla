package br.com.controle.tarefa.dominio;

import java.util.Date;

public class Tarefa {
	
	private Integer id;
	private String titulo;
	private String descricao;
	private Date dataAbertura;
	private Date dataFechamento;
	private Integer porcentagem;
	private Projeto projeto;
	private Usuario usuarioAbertura;
	private Usuario usuarioFechamento;
	
	/**
	 * Contrutor padrão
	 */
	public Tarefa(){
		
	}
	
	/**
	 * Contrutor novo objeto
	 * @param descricao
	 * @param dataAbertura
	 * @param dataFechamento
	 * @param porcentagem
	 * @param projeto
	 * @param usuarioAbertura
	 * @param usuarioFechamento
	 */
	public Tarefa (String titulo, String descricao, Date dataAbertura, Date dataFechamento,
			Integer porcentagem, Projeto projeto, Usuario usuarioAbertura, Usuario usuarioFechamento){
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.porcentagem = porcentagem;
		this.projeto = projeto;
		this.usuarioAbertura = usuarioAbertura;
		this.usuarioFechamento = usuarioFechamento;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Integer getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(Integer porcentagem) {
		this.porcentagem = porcentagem;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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

}
