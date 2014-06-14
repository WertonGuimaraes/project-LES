package com.ufcg.les;

import java.util.List;

import util.Cor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.ufcg.entities.Data;
import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;
import com.ufcg.json.JSONParse;
/**
 *
 * @author Werton
 *
 */
public class AdicionarTIActivity extends Activity {
	private static final int PRIORIDADE = 0;
	private static final String FOTO = null;
	private static final String COR = null;
	private static final int MAX_HOURS = 23, MAX_MINUTES = 60;
	private String dono;
	private int tempo;
	private NumberPicker horas, minutos;
	private Context mContext;

	/**
	 * O metodo ser� responsavel por criar o conteudo da tela.
	 * @param savedInstanceState
	 *
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adicionar_ti);
		mContext = getApplicationContext();
		dono = Session.getInstancia().getDono();
		horas = (NumberPicker) findViewById(R.id.hours);
		horas.setMaxValue(MAX_HOURS);
		horas.setMinValue(0);
		horas.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
	        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				horas.setValue(newVal);
	        }
	    });

		minutos = (NumberPicker) findViewById(R.id.minutes);
		minutos.setMaxValue(MAX_MINUTES);
		minutos.setMinValue(0);
		minutos.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
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
				tempo = horas.getValue() * MAX_MINUTES + minutos.getValue();
				Data d = new Data();
				String data = String.valueOf(d.convertDateToMilissegundos());
				new SalvaJSON().execute(dono, nome.getText().toString(), String.valueOf(tempo), data);
				
			} });
		
		
		Button voltar = (Button) findViewById(R.id.BotaoCancelar);
		voltar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			} });
	}

	/**
	 *
	 * � uma inerclass que tem por objetivo salvar uma atividade no BD.
	 * @author Werton
	 *
	 */
	private class SalvaJSON extends AsyncTask<String, Void, String[]> {
		private ProgressDialog dialog;
		private String cor;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(AdicionarTIActivity.this, "Espere", "Salvando sua atividade ..");
		}

		@Override
		protected String[] doInBackground(String... params) {
			boolean wasAdd = salvaTi(params[0], params[1], params[2], params[3]);
			String[] retorno = {params[0], params[1], params[2], params[3], String.valueOf(wasAdd), cor};
			return retorno;
		}

		@Override
		protected void onPostExecute(String[] result) {
			super.onPostExecute(result);
			String nome = result[1];
			Long data = (long) Double.parseDouble(result[3]);
			String cor = result[5];
			if (Boolean.parseBoolean(result[4])) {
				Session.getInstancia().getAtividades().add(new Ti(nome, Integer.parseInt(result[2]), data, cor, PRIORIDADE, COR));
				Toast.makeText(mContext, "Tempo investido adicionado com SUCESSO!", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(mContext, "Tempo investido FALHOU ao ser adicionado.", Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
			onBackPressed();
		}

		/**
		 * O metodo salavara a atividade no BD.
		 *
		 * @paramdono: � quem es.t� logado;
		 * @paramnome: � o nome da atividade;
		 * @paramtempo: � o tempo que a atividade conseumiu em minutos;
		 * @paramdata: a data da cria��o da atividade em milissegundos;
		 * @return se a atividade foi criada ou n�o.
		 */
		private boolean salvaTi(String dono, String nome, String tempo, String data) {
			cor = Session.getInstancia().recuperaCor(nome);
			String url = "http://150.165.98.11:8080/povmt/atividade/salvarAtividade?dono=" + dono +	"&nome=" + nome + "&tempo=" + tempo + "&data=" + data+ "&foto=" + cor;
			JSONParse json = new JSONParse(url);
			return json.getAdicionou();
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(AdicionarTIActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
