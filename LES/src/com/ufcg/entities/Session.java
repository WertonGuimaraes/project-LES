package com.ufcg.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {

	private static Session instancia;
	private String dono;
	private List<Ti> atividades;

	protected Session() {
		atividades = new ArrayList<Ti>();
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
	
	public List<Ti> atividadesDaSemanaPassada() {
		List<Ti> resposta = new ArrayList<Ti>();
		for (Ti ti : atividades) {
			if(Data.is2SemanasPassada(ti.getData())){
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
}

