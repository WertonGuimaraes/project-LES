package com.ufcg.les;

import java.util.List;
import java.util.Map.Entry;

import util.Cor;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;

// tres graficos de pizzas
public class Comparativo extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_week);

		PieGraph pg = (PieGraph) findViewById(R.id.graph);
		PieSlice slice;
		List<Ti> atividadesDaSemana = Session.getInstancia()
				.atividadesDaSemana();

		for (Entry<String, Integer> entry : Session.getInstancia()
				.resumeAtividades(atividadesDaSemana).entrySet()) {
			slice = new PieSlice();
			String cor = (new Cor()).getCor();
			slice.setColor(Color.parseColor(cor));
			slice.setValue(entry.getValue());
			slice.setTitle(entry.getKey());
			pg.addSlice(slice);

		}
	}

	

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Comparativo.this, MainActivity.class);
		startActivity(intent);

		finish();
	}

	// ranking para cada semana

}
