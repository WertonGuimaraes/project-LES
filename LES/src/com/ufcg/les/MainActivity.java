package com.ufcg.les;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import util.Cor;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.ufcg.entities.Data;
import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;


public class MainActivity extends Activity {
	
	private boolean registrouTarefaOntem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//Verifica se o usuario registrou alguma atividade ontem
		if(Session.getInstancia().mensagemLida == false){
			registrouTarefaOntem = Session.getInstancia().registrouTarefasOntem();
			if(!registrouTarefaOntem){
				Intent i = new Intent(MainActivity.this,VerificaTarefasActivity.class);
				startActivity(i);
				finish();
			}
		}
		
		Button adicionarTI = (Button) findViewById(R.id.Button_AddTI);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,AdicionarTIActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		Button compareWeeks = (Button) findViewById(R.id.Comparative_view);
		compareWeeks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,CompareWeeks.class);
				startActivity(i);
				finish();
			}
		});
		
		
ListView list = (ListView) findViewById(R.id.listWeek);
		
		//PieGraph pg = (PieGraph)findViewById(R.id.graph);
		//PieSlice slice;
		List<Ti> atividadesDaSemana = Session.getInstancia().atividadesDaSemana();
		List<Ti> tiAdapter = new ArrayList<Ti>();
		ArrayList<Bar> points = new ArrayList<Bar>();
		int tempoTotal = 0;
		
		for (Entry<String, Integer> entry : Session.getInstancia().resumeAtividades(atividadesDaSemana).entrySet()) {
//			slice = new PieSlice();
			String cor = "#"+Session.getInstancia().recuperaCor(entry.getKey());
//			slice.setColor(Color.parseColor(cor));
//			slice.setValue(entry.getValue());
//			pg.addSlice(slice);
			
			Bar d = new Bar();
			d.setColor(Color.parseColor(cor));
			d.setName(entry.getKey());
			d.setValue(entry.getValue());
			
			points.add(d);
			
			int prior = Session.getInstancia().recuperaPrioridade(entry.getKey());
			tiAdapter.add(new Ti(entry.getKey(), entry.getValue(), (long) 0, "", prior, cor)); 
			tempoTotal += entry.getValue();
			
		}
		BarGraph g = (BarGraph)findViewById(R.id.graph);
		g.setBars(points);
		
		TextView totalDeHoras = (TextView) findViewById(R.id.TotalHoras);
		totalDeHoras.setText(String.format("%.2f", (float)tempoTotal/60));
		
		TiAdapter adapter = new TiAdapter(getApplicationContext(), tiAdapter, tempoTotal);
		list.setAdapter(adapter);
		
	}
	
	// lembrar de fazer o ranking e propor��o de horas
		private Map retornaRanking() {
			List<Ti> atividadesDaSemana = Session.getInstancia().atividadesDaSemana();
			Map<String, Integer> titempo = new HashMap<String, Integer>();
			Map<String, Integer> tiprop = new HashMap<String, Integer>();
			
			// mapa com as atividades e tempos das atividades
			int tempoTotalInvestido = 0;
			for (Ti ti : atividadesDaSemana) {
				titempo.put(ti.getNome(), ti.getTempo());
				tempoTotalInvestido += ti.getTempo();
			}
			
			// mapa com as atividades e propor��o do tempo das atividades
			for (Entry<String, Integer> entry : titempo.entrySet()) {  
				tiprop.put(entry.getKey(), entry.getValue()/tempoTotalInvestido);   
			}
			return tiprop;
		}
}
