package com.ufcg.les;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import JSON.JSONParse;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import entities.Data;
import entities.Ti;

public class MainActivity extends Activity{
	
	private Button salvar;
	private TextView resultTV;
	private final String DONO = "werton007";
	private ArrayList<Ti> TisDoUsuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		salvar = (Button) findViewById(R.id.salvarJSON);
		resultTV = (TextView) findViewById(R.id.result_JSON);
		new TiAsyncTask().execute(DONO);
		
		salvar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}
	
	private void ListaTarefas() {
		String name = "";
		for (Ti ti : TisDoUsuario)
			name += "Nome: " + ti.getNome() +", Tempo: " +ti.getTempo() + ", Data: " + ti.getData() +"\n";
		resultTV.setText(name);
	}
	
	
	
	public class TiAsyncTask extends AsyncTask<String, Void, Void> {
		
		private ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(MainActivity.this, "Espere", "Atualizando BD");
		}

		@Override
		protected Void doInBackground(String... params) {
			TisDoUsuario = getJSON(params[0]);	
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			ListaTarefas();
			dialog.dismiss();
		}		
		
		private ArrayList<Ti> getJSON(String dono){
			JSONParse parser = new JSONParse("http://150.165.98.11:8080/povmt/atividade/recuperaAtividades?dono="+dono);
			return parser.TiPars();
		}
	}
}
