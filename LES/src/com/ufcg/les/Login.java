package com.ufcg.les;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;
import com.ufcg.json.JSONParse;

public class Login extends Activity {

	EditText login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		login = (EditText) findViewById(R.id.login);
		Button btLogar = (Button) findViewById(R.id.buttonLogar);
		btLogar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new CapturaJSON().execute(login.getText().toString());
				Intent i = new Intent(Login.this,MainActivity.class);
				startActivity(i);
			}
		});
	}
	
	public class CapturaJSON extends AsyncTask<String, Void, ArrayList<Ti>> {
		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(Login.this, "Espere", "Logando");
		}

		@Override
		protected ArrayList<Ti> doInBackground(String... params) {
			return getJSON(params[0]);
		}

		@Override
		protected void onPostExecute(ArrayList<Ti> result) {
			super.onPostExecute(result);
			
			Session.getInstancia().delInstancia();
			Session.getInstancia().setDono(login.getText().toString());
			
			for (Ti ti : result) {
				Session.getInstancia().getAtividades().add(ti);
			}
			
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
