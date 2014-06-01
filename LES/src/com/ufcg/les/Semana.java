package com.ufcg.les;

import java.text.Format;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;

//import com.echo.holographlibrary.PieGraph;
//import com.echo.holographlibrary.PieSlice;
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

public class Semana extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_week);		

		/*
		PieGraph pg = (PieGraph)findViewById(R.id.graph);
		PieSlice slice;
		ArrayList<Ti> atividadesDaSemana = Session.getInstancia().atividadesDaSemana();
				
		for (Entry<String, Integer> entry : Session.getInstancia().resumeAtividades(atividadesDaSemana).entrySet()) {
			slice = new PieSlice();
			String cor = geraCor();
			slice.setColor(Color.parseColor(cor));
			slice.setValue(entry.getValue());
			slice.setTitle(entry.getKey());
			pg.addSlice(slice);
			
		}*/
		
		
		
		
		//--------------------------ListView------------------------
		/*ListView listview = (ListView) findViewById(R.id.listview);
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2"};
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.list_content, values);
	       setListAdapter(adapter);


	    ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }*/

	    /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	        final String item = (String) parent.getItemAtPosition(position);
	        view.animate().setDuration(2000).alpha(0)
	            .withEndAction(new Runnable() {
	              @Override
	              public void run() {
	                list.remove(item);
	                adapter.notifyDataSetChanged();
	                view.setAlpha(1);
	              }
	            });
	      }

	    });*/
	    
	    
	    
	    //-------------------------FIM LISTVIEW--------
		
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
		Intent intent = new Intent(Semana.this, MainActivity.class);
		startActivity(intent);

		finish();
	}
	
	

}

