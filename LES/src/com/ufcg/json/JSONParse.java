package com.ufcg.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.ufcg.entities.Ti;


public class JSONParse {
	protected URL feedUrl;
	private JSONArray json;
	private static final String NOME = "nome";
	private static final String TEMPO = "tempo";
	private static final String DATA = "data";
	private static final String COR = "foto";
	private static final String PRIORIDADE = "prioridade";
	
	private boolean adicionou;
	

	public JSONParse(String feedUrl) {
		adicionou = true;
		try {
			this.feedUrl = new URL(feedUrl);
			json();
		} catch (MalformedURLException e) {
			adicionou = false;
		}
	}
	
	public boolean getAdicionou() {
		return adicionou;
	}

	private void json() {
		try {
			String jsonString = convertStreamToString(feedUrl.openConnection().getInputStream());
			json = new JSONArray(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String convertStreamToString(final InputStream input) {

		final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		final StringBuilder sBuf = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sBuf.append(line);
			}
		} catch (IOException e) {
		} finally {
			try {
				input.close();
			} catch (IOException e) {
			}
		}
		return sBuf.toString();
	}

	public List<Ti> tiPars() {
		List<Ti> temposInvestidos = new ArrayList<Ti>();

		if(json != null){
			for (int i = 0; i < json.length(); i++) {
				JSONObject item;
				try {
					item = json.getJSONObject(i);
					temposInvestidos.add(recuperaTi(item));
				} catch (JSONException e) {
					e.printStackTrace();
				}
	
			}
		}

		return temposInvestidos;
	}

	private Ti recuperaTi(JSONObject item) throws JSONException {
		return new Ti(convert(item.get(NOME), String.class),
				convert(item.get(TEMPO), Integer.class),
				convert(item.get(DATA), Long.class),
				convert(item.get(COR), String.class),
				1,
				null);
	}

	/**
	 * O metodo Auxiliar que converte um Objeto do JSON para um tipo especificado.
	 * 
	 * @param obj - O objesto do JSON.
	 * @param type - O tipo que sera o Objeto.
	 * @return O objeto convertido com algum tipo.
	 */
	
	@SuppressWarnings("unchecked")
	private <T> T convert(Object obj, Class<T> type) {
		if (obj == null) {
			return null;        
		}
		return (T) obj;
	}

}
