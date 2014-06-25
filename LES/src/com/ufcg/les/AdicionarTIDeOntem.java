package com.ufcg.les;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
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
public class AdicionarTIDeOntem extends Activity {
	private static final String FOTO = null;
	private static final String COR = null;
	private static final int MAX_HOURS = 23, MAX_MINUTES = 60;
	private String dono;
	private int tempo;
	private NumberPicker horas, minutos;
	private Context mContext;
	private Spinner spinner; 
	private int prioridade;


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

		spinner = (Spinner) findViewById(R.id.priority);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.priority_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
				prioridade = posicao;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});

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
				String dataOntem = String.valueOf(d.convertDateToMilissegundos() - 86400000);
				String prior = String.valueOf(prioridade);
				new SalvaJSON().execute(dono, nome.getText().toString().trim(), String.valueOf(tempo), dataOntem, prior);
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
			dialog = ProgressDialog.show(AdicionarTIDeOntem.this, "Espere", "Salvando sua atividade ..");
		}

		@Override
		protected String[] doInBackground(String... params) {
			boolean wasAdd = salvaTi(params[0], params[1], params[2], params[3], params[4]);
			String[] retorno = {params[0], params[1], params[2], params[3], params[4], String.valueOf(wasAdd), 
					cor};
			return retorno;
		}

		@Override
		protected void onPostExecute(String[] result) {
			super.onPostExecute(result);
			String nome = result[1];
			Long data = (long) Double.parseDouble(result[3]);
			String cor = result[6];
			if (Boolean.parseBoolean(result[5])) {
				Session.getInstancia().getAtividades().add(new Ti(nome, Integer.parseInt(result[2]), data, 
						cor, prioridade, COR));
				Toast.makeText(mContext, "Tempo investido adicionado com SUCESSO!", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(mContext, "Tempo investido FALHOU ao ser adicionado.", 
						Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
			onBackPressed();
		}

		/**
		 * O metodo salavara a atividade no BD.
		 * @param prioridade 
		 *
		 * @paramdono: � quem es.t� logado;
		 * @paramnome: � o nome da atividade;
		 * @paramtempo: � o tempo que a atividade conseumiu em minutos;
		 * @paramdata: a data da cria��o da atividade em milissegundos;
		 * @return se a atividade foi criada ou n�o.
		 */
		private boolean salvaTi(String dono, String nome, String tempo, String data, String prioridade) {
			cor = Session.getInstancia().recuperaCor(nome);
			
			nome = mudaCaractere(nome, " ", "_");
						
			String url = "http://150.165.98.11:8080/povmt/atividade/salvarAtividade?dono=" + dono +	"&nome="
					+ nome + "&tempo=" + tempo + "&data=" + data + "&prioridade=" + prioridade 
					+"&foto=" + cor;
			JSONParse json = new JSONParse(url);
			return json.getAdicionou();
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(AdicionarTIDeOntem.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	public String mudaCaractere(String str, String antigo, String novo){
		str = str.replace(antigo, novo);
		return str;
	}
	
}
