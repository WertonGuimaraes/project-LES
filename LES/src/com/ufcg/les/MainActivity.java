package com.ufcg.les;

import com.ufcg.entities.Session;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final String DONO = Session.getInstancia().getDono() ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button adicionarTI = (Button) findViewById(R.id.Button_AddTI);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,AdicionarTIActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		Button semana = (Button) findViewById(R.id.Button_ViewWeek);
		semana.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,Semana.class);
				startActivity(i);
				finish();
			}
		});
	}
	
	@Override
	public void onBackPressed() {

	}
}
