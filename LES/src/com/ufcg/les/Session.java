package com.ufcg.les;

import java.util.ArrayList;

import com.ufcg.entities.Ti;

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
}

