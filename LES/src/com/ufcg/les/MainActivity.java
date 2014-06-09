package com.ufcg.les;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


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
		ArrayList<Ti> atividadesDaSemana = Session.getInstancia().atividadesDaSemana();
		ArrayList<Ti> TiAdapter = new ArrayList<Ti>();
		int tempoTotal = 0;
		
		for (Entry<String, Integer> entry : Session.getInstancia().resumeAtividades(atividadesDaSemana).entrySet()) {
			slice = new PieSlice();
			String cor = geraCor();
			slice.setColor(Color.parseColor(cor));
			slice.setValue(entry.getValue());
			TiAdapter.add(new Ti(entry.getKey(), entry.getValue(), (long) 0, "", 0, cor)); 
			tempoTotal += entry.getValue();
			pg.addSlice(slice);
		}
		
		TextView totalDeHoras = (TextView) findViewById(R.id.TotalHoras);
		totalDeHoras.setText(String.format("%.2f", (float)tempoTotal/60));
		
		TiAdapter adapter = new TiAdapter(getApplicationContext(), TiAdapter, tempoTotal);
		list.setAdapter(adapter);
		
	}
	
	private String geraCor() {
		
		Random randCol = new Random();  
		String r = Integer.toHexString(randCol.nextInt(256));
		if(r.length() ==1)	r = "0" +r;
		String g = Integer.toHexString(randCol.nextInt(256));  
		if(g.length() ==1)	g = "0" +g;
		String b = Integer.toHexString(randCol.nextInt(256));  
		if(b.length() ==1)	b = "0" +b;
		return "#"+r+g+b; 
	}
	
	// lembrar de fazer o ranking e proporção de horas
		private Map retornaRanking() {
			ArrayList<Ti> atividadesDaSemana = Session.getInstancia().atividadesDaSemana();
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
