package com.ufcg.les;

import java.util.ArrayList;

import com.ufcg.entities.Ti;
import com.ufcg.json.JSONParse;
import com.ufcg.les.R;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static ArrayList<Ti> TisDoUsuario;
	private static final String DONO = "xpto";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		new CapturaJSON().execute(DONO);
		
		Button adicionarTI = (Button) findViewById(R.id.Button_AddTI);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,AdicionarTIActivity.class);
				startActivity(i);
			}
		});
	}
	
	public static ArrayList<Ti> getTisDoUsuario() {
		return TisDoUsuario;
	}
	
	public static String getDONO() {
		return DONO;
	}

	public class CapturaJSON extends AsyncTask<String, Void, Void> {
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
			dialog.dismiss();
		}

		private ArrayList<Ti> getJSON(String dono) {
			JSONParse parser = new JSONParse(
					"http://150.165.98.11:8080/povmt/atividade/recuperaAtividades?dono="
							+ dono);
			return parser.TiPars();
		}
	}
}
