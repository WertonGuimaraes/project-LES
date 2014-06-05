package com.ufcg.les;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.ufcg.entities.Data;
import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;
import com.ufcg.json.JSONParse;

public class AdicionarTIActivity extends Activity { 
	
	private static final int PRIORIDADE = 0;
	private static final String FOTO = null;
	private static final String COR = null;
	private String dono;
	private int tempo;
	NumberPicker horas, minutos;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adicionar_ti);
		dono = Session.getInstancia().getDono();
		
		horas = (NumberPicker) findViewById(R.id.hours);
		horas.setMaxValue(23);
		horas.setMinValue(0);
		horas.setOnValueChangedListener( new NumberPicker.OnValueChangeListener() {
			@Override
	        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				horas.setValue(newVal);
	        }
	    });
		
		
		minutos = (NumberPicker) findViewById(R.id.minutes);
		minutos.setMaxValue(59);
		minutos.setMinValue(0);
		minutos.setOnValueChangedListener( new NumberPicker.OnValueChangeListener() {
			@Override
	        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				minutos.setValue(newVal);
	        }
	    });
		
		
		Button adicionarTI = (Button) findViewById(R.id.botaoOK);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText nome = (EditText) findViewById(R.id.name_ti);
				tempo = horas.getValue()*60 + minutos.getValue();
				Data d = new Data();
				String data = String.valueOf(d.convertDateToMilissegundos());
				new SalvaJSON().execute(dono, nome.getText().toString(), String.valueOf(tempo), data);
			}
		});		
	}
	
	public class SalvaJSON extends AsyncTask<String, Void, String[]> {
		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(AdicionarTIActivity.this, "Espere", "Salvando sua atividade ..");
		}
		
		@Override
		protected String[] doInBackground(String... params) {
			salvaTi(params[0], params[1], params[2], params[3]);
			String[] retorno = {params[0], params[1], params[2], params[3]};
			return retorno;
		}
		
		@Override
		protected void onPostExecute(String[] result) {
			super.onPostExecute(result);
			String nome = result[1];
			Integer tempo = Integer.parseInt(result[2]);
			Long data = (long) Double.parseDouble(result[3]);
			Session.getInstancia().getAtividades().add(new Ti(nome, tempo, data, FOTO, PRIORIDADE, COR));
			dialog.dismiss();
		}

		private void salvaTi(String dono, String nome, String tempo, String data){		
			String url = "http://150.165.98.11:8080/povmt/atividade/salvarAtividade?dono=" + dono +	"&nome=" + nome + "&tempo=" + tempo + "&data=" + data;
			new JSONParse(url);
		}

	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(AdicionarTIActivity.this, MainActivity.class);
		startActivity(intent);

		finish();
	}
}
