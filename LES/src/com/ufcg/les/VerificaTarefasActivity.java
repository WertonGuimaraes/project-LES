package com.ufcg.les;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VerificaTarefasActivity extends Activity {

	//private ImageView imageView1;
	//private TextView textView1;
	private Button adicionarTI;
	private Button paginaInicial;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verifica_criacao_tarefas);
		
		adicionarTI = (Button)findViewById(R.id.Button_AddTI2);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { //addTiOntem
				Intent i = new Intent(VerificaTarefasActivity.this,AdicionarTIDeOntem.class);
				startActivity(i);
				finish();
			}
		});
		
		paginaInicial = (Button)findViewById(R.id.Voltar);
		paginaInicial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(VerificaTarefasActivity.this,MainActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
}
