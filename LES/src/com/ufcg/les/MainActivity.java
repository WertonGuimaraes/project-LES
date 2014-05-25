package com.ufcg.les;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.ufcg.les.R;

import entities.Ti;
import JSON.JSONParse;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
	
	private Button salvar, capturar;
	private TextView resultTV;
	private final String DONO = "werton007";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		salvar = (Button) findViewById(R.id.salvarJSON);
		capturar = (Button) findViewById(R.id.capturarJSON);
		resultTV = (TextView) findViewById(R.id.result_JSON);
		
		salvar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resultTV.setText("salvou");
			}
		});
		
		capturar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new TiAsyncTask().execute(DONO);
			}
		});
		
		
		
	}
	
	public class TiAsyncTask extends AsyncTask<String, Void, Void> {
		
		private ProgressDialog dialog;
		private ArrayList<Ti> TisDoUsuario;
		
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
			resultTV.setText("Total de Tis: " + TisDoUsuario.size());
			dialog.dismiss();
		}		
		
		private ArrayList<Ti> getJSON(String dono){
			JSONParse parser = new JSONParse("http://150.165.98.11:8080/povmt/atividade/recuperaAtividades?dono="+dono);
			return parser.TiPars();
		}
		
	}
	
	
}
