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
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button adicionarTI = (Button) findViewById(R.id.Button_AddTI);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,AdicionarTIActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		/*Button semana = (Button) findViewById(R.id.Button_ViewWeek);
		semana.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,Semana.class);
				startActivity(i);
				finish();
			}
		});*/
		
		ListView list = (ListView) findViewById(R.id.listWeek);
		
		PieGraph pg = (PieGraph)findViewById(R.id.graph);
		PieSlice slice;
		List<Ti> atividadesDaSemana = Session.getInstancia().atividadesDaSemana();
		List<Ti> tiAdapter = new ArrayList<Ti>();
		int tempoTotal = 0;
		
		for (Entry<String, Integer> entry : Session.getInstancia().resumeAtividades(atividadesDaSemana).entrySet()) {
			slice = new PieSlice();
			String cor = (new Cor()).getCor();
			slice.setColor(Color.parseColor(cor));
			slice.setValue(entry.getValue());
			tiAdapter.add(new Ti(entry.getKey(), entry.getValue(), (long) 0, "", 0, cor)); 
			tempoTotal += entry.getValue();
			pg.addSlice(slice);
		}
		
		TextView totalDeHoras = (TextView) findViewById(R.id.TotalHoras);
		totalDeHoras.setText(String.format("%.2f", (float)tempoTotal/60));
		
		TiAdapter adapter = new TiAdapter(getApplicationContext(), tiAdapter, tempoTotal);
		list.setAdapter(adapter);
		
	}
	
	// lembrar de fazer o ranking e proporção de horas
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
			
			// mapa com as atividades e proporção do tempo das atividades
			for (Entry<String, Integer> entry : titempo.entrySet()) {  
				tiprop.put(entry.getKey(), entry.getValue()/tempoTotalInvestido);   
			}
			return tiprop;
		}
}
