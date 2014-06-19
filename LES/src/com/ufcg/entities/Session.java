package com.ufcg.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Cor;
import android.util.Log;

public class Session {

	private static Session instancia;
	private String dono;
	private List<Ti> atividades;
	public boolean mensagemLida = false;
	private List<String> isCheckedList;

	protected Session() {
		atividades = new ArrayList<Ti>();
		isCheckedList = new ArrayList<String>();
	}

	public static Session getInstancia() {
		if (instancia == null) {
			instancia = new Session();
		}
		return instancia;
	}
	
	public static void delInstancia(){
		instancia = null;
	}
	
	public List<String> getIsCheckedList() {
		return isCheckedList;
	}
	
	public List<Ti> getAtividades() {
		return atividades;
	}
	
	public void setDono(String dono) {
		this.dono = dono;
	}
	
	public String getDono() {
		return dono;
	}
	
	public List<Ti> atividadesDaSemana() {
		List<Ti> resposta = new ArrayList<Ti>();
		for (Ti ti : atividades) {
			if(Data.isSemana(ti.getData())){
				resposta.add(ti);
			}
		}
		return resposta;
	}
	
	/**
	 * Verifica se o usuario criou alguma tarefa no dia anterior
	 */
	public boolean registrouTarefasOntem(){
		mensagemLida = true;
		Data ontem = new Data();
		ontem.getOntem();
		for (Ti ti : atividades){
			if (ti.getData().getTime() == ontem.convertDateToMilissegundos())
				return true;
		}
		return false;
	}
	
	public List<Ti> atividadesDaSemanaPassada() {
		List<Ti> resposta = new ArrayList<Ti>();
		for (Ti ti : atividades) {
			if(Data.is2SemanasPassada(ti.getData(), 1)){
				resposta.add(ti);
			}
		}
		return resposta;
	}
	
	public List<Ti> atividadesDaSemanaRetrasada() {
		List<Ti> resposta = new ArrayList<Ti>();
		for (Ti ti : atividades) {
			if(Data.is2SemanasPassada(ti.getData(), 2)){
				resposta.add(ti);
			}
		}
		return resposta;
	}
	
	public Map<String, Integer> resumeAtividades(List<Ti> atividades) {
		Map<String, Integer> resposta = new HashMap<String, Integer>();
		
		for (Ti ti : atividades) {
			String nome = ti.getNome();
			Integer tempo = ti.getTempo();
			if(!resposta.containsKey(nome)){
				resposta.put(nome, 0);
			}
			resposta.put(nome, resposta.get(nome)+tempo);
		}
		return resposta;
	}
	
	public String recuperaCor(String nomeDaTi) {
		String cor = (new Cor()).getCor();
		for (Ti ti : getAtividades()) {
			if(ti.getNome().equals(nomeDaTi)){
				cor = ti.getFoto();
				break;
			}
		}
		return cor;
	}
	public int recuperaPrioridade(String nomeDaTi) {
		int prior = 0;
		for (Ti ti : getAtividades()) {
			if(ti.getNome().equals(nomeDaTi)){
				Log.d("werton", ti.getFoto());
				prior = ti.getPrioridade();
				break;
			}
		}
		return prior;
	}

}
