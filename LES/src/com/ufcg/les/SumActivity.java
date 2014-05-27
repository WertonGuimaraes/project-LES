package com.ufcg.les;

import com.ufcg.les.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;

public class SumActivity extends Activity {
	
	protected static final int SUB_ACTIVITY_REQUEST_CODE = 200;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sum);

		Button cancelar = (Button) findViewById(R.id.BotaoCancelar);
		cancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		Button ok = (Button) findViewById(R.id.botaoOK);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle param = new Bundle();
				param.putInt("sum",soma());
				
				Intent i = new Intent(SumActivity.this, MainActivity.class);
				i.putExtras(param);
				startActivity(i);
				finish();
			}
		});
		
	}
	
	public int soma(){
		EditText numero1EditText = (EditText) findViewById(R.id.CampoNumero1);
		EditText numero2EditText = (EditText) findViewById(R.id.CampoNumero2);
		int numero1 = Integer.parseInt(numero1EditText.getText().toString());
		int numero2 = Integer.parseInt(numero2EditText.getText().toString());
		return (numero1 + numero2);
	}

}
