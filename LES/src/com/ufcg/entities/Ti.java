package com.ufcg.entities;

import java.util.Date;


public class Ti {
	private String nome, foto, cor; 
	private int tempo, prioridade;
	private Date data;
	private boolean checked;
	/**
	 * @param nome
	 * @param tempo
	 * @param data
	 * @param foto
	 * @param prioridade
	 */
	public Ti(String nome, int tempo, Long dataMilisegundos, String foto, int prioridade, String cor) {
		this.nome = nome;
		this.tempo = tempo;
		this.data = new Date();
		this.data.setTime(dataMilisegundos);
		this.foto = foto;
		this.prioridade = prioridade;
		this.cor = cor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
