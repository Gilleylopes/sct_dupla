package br.com.controle.tarefa.dominio;

import java.util.Date;

public class Projeto {
	
	private Integer id;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	
	/**
	 * Contrutor padrão
	 */
	public Projeto(){
		
	}
	
	/**
	 * Contrutor novo objeto
	 * @param descricao
	 * @param dataInicio
	 * @param dataFim
	 */
	public Projeto (String descricao, Date dataInicio, Date dataFim){
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	

}
