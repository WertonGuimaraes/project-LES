package com.ufcg.les;

import com.ufcg.entities.Session;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

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
		openAlert("Logout", "Clique em OK para sair do sistema.");
	}
	
	private void openAlert(String titulo, String mensagem) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(titulo);
		alertDialogBuilder.setMessage(mensagem);
		// set positive button: Yes message
		alertDialogBuilder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog,int id) {
				
			}
		});
		alertDialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog,int id) {
				// go to a new activity of the app
				Intent backToPointsActivity = new Intent(getApplicationContext(), Login.class);
				startActivity(backToPointsActivity);
			}
		});
		

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
