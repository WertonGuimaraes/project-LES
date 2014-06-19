package com.ufcg.les;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;

public class CompareWeeks extends Activity /*implements OnItemClickListener*/{
	
	private List<Ti> semanaAtual = Session.getInstancia().atividadesDaSemana();
	private List<Ti> semanaPassada = Session.getInstancia().atividadesDaSemanaPassada();
	private List<Ti> semanaRetrasada = Session.getInstancia().atividadesDaSemanaRetrasada();
	private float maiorTempo = 0;
	private LineGraph li;
	private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.compare_week);
		super.onCreate(savedInstanceState);
				
		geraGrafico(Session.getInstancia().getIsCheckedList());
		
		displayListView();
		
		
	}
	

	private void displayListView() {
		list = (ListView) findViewById(R.id.listNamesTi);
		ComapreAdapter adapter = new ComapreAdapter(getApplicationContext(), allTi(), this);
		list.setAdapter(adapter);		
		//list.setOnItemClickListener(this); 
	}
	
	public List<String> retornaNomeTis(List<Ti> tis){
		List<String> nomes = new ArrayList<String>();
		for (Ti ti : tis) {
			nomes.add(ti.getNome());
		}
		return nomes;
	}
	
	
	
	public void geraGrafico(List<String> nomeDeTis){
		if(this.li == null){
			this.li = (LineGraph)findViewById(R.id.graph);
		}else{
			li.removeAllLines();
		}
		
		for (String nome : nomeDeTis) {
			Line l = new Line();
			//semana retrasada
			LinePoint p = new LinePoint();
			p.setX(0);
			p.setY(recuperaTempo(nome, semanaRetrasada));
			l.addPoint(p);
			//semana passada
			p = new LinePoint();
			p.setX(1);
			p.setY(recuperaTempo(nome, semanaPassada));
			l.addPoint(p);
			//semana atual
			p = new LinePoint();
			p.setX(2);
			p.setY(recuperaTempo(nome, semanaAtual));
			l.addPoint(p);
			
			l.setColor(Color.parseColor("#"+Session.getInstancia().recuperaCor(nome)));
			li.addLine(l);
		}
		
		li.setRangeY(0, maiorTempo);
		li.setLineToFill(0);
	}

	private boolean existeAtividade(String nome, List<Ti> semana){
		boolean existeTi = false;
		for (Ti ti : semana) {
			if(ti.getNome().equals(nome)){
				existeTi = true;
				break;
			}
		}
		return existeTi;
	}
	
	private float recuperaTempo(String nome, List<Ti> semana) {
		float tempo = 0;
		if(existeAtividade(nome, semana)){
			Map<String, Integer> semanaResumida = Session.getInstancia().resumeAtividades(semana);
			tempo = semanaResumida.get(nome);
		}
		tempo = tempo/((float)60);
		if(tempo > maiorTempo){
			maiorTempo = tempo;
		}
		return tempo;
	}

	public List<Ti> allTi(){
		List <Ti> resposta = new ArrayList<Ti>();
		List<List<Ti>> semanas = semanas();		
		for (List<Ti> semana : semanas) {
			for (Ti ti : semana) {
				if(!existeAtividade(ti.getNome(), resposta)){
					resposta.add(ti);
				}
			}
		}
		return resposta;
	}

	private List<List<Ti>> semanas() {
		List <List<Ti>> semanas = new ArrayList<List<Ti>>();
		semanas.add(semanaAtual);
		semanas.add(semanaPassada);
		semanas.add(semanaRetrasada);
		return semanas;
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(CompareWeeks.this, MainActivity.class);
		startActivity(intent);
		finish();
	}


//	@Override
//    public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
//		TextView label = (TextView) v.getTag(R.id.nomeCompare);
//		CheckBox checkbox = (CheckBox) v.getTag(R.id.check);
//		Toast.makeText(v.getContext(), label.getText().toString()+" "+isCheckedOrNot(checkbox), Toast.LENGTH_LONG).show();
//    }
//	
//	private String isCheckedOrNot(CheckBox checkbox) {
//        if(checkbox.isChecked())
//        return "is checked";
//        else
//        return "is not checked";
//    }
}
	