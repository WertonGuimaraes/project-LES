package com.ufcg.les;

import java.text.Format;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;

import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


// tres graficos de pizzas
public class Comparativo extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_week);		

		
		/*PieGraph pg = (PieGraph)findViewById(R.id.graph);
		PieSlice slice;
		ArrayList<Ti> atividadesDaSemana = Session.getInstancia().atividadesDaSemana();
				
		for (Entry<String, Integer> entry : Session.getInstancia().resumeAtividades(atividadesDaSemana).entrySet()) {
			slice = new PieSlice();
			String cor = geraCor();
			slice.setColor(Color.parseColor(cor));
			slice.setValue(entry.getValue());
			slice.setTitle(entry.getKey());
			pg.addSlice(slice);
			
		}
		*/
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
	
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Comparativo.this, MainActivity.class);
		startActivity(intent);

		finish();
	}
	
	

}

