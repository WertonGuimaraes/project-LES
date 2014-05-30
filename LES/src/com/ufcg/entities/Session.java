package com.ufcg.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Session {

	public static Session instancia;
	private String dono;
	private ArrayList<Ti> atividades;

	protected Session() {
		atividades = new ArrayList<Ti>();
	}

	public static Session getInstancia() {
		if (instancia == null)
			instancia = new Session();
		return instancia;
	}
	
	public void delInstancia(){
		instancia = null;
	}
	
	public ArrayList<Ti> getAtividades() {
		return atividades;
	}
	
	public void setDono(String dono) {
		this.dono = dono;
	}
	
	public String getDono() {
		return dono;
	}
	
	public ArrayList<Ti> atividadesDaSemana() {
		ArrayList<Ti> resposta = new ArrayList<Ti>();
		for (Ti ti : atividades) {
			if(Data.isSemana(ti.getData())){
				resposta.add(ti);
			}
		}
		return resposta;
	}
	
	public ArrayList<Ti> atividadesDaSemanaPassada() {
		ArrayList<Ti> resposta = new ArrayList<Ti>();
		for (Ti ti : atividades) {
			if(Data.is2SemanasPassada(ti.getData())){
				resposta.add(ti);
			}
		}
		return resposta;
	}
	
	public HashMap<String, Integer> resumeAtividades(ArrayList<Ti> atividades) {
		HashMap<String, Integer> resposta = new HashMap<String, Integer>();
		
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

