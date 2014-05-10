package com.ufcg.les;


import com.ufcg.les.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
	
	private int result;
	private TextView result_TV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		result_TV = (TextView) findViewById(R.id.result_sum);
		
		Intent it = getIntent();
		Bundle param = it.getExtras();
		if(param != null){
			result = param.getInt("sum");
			result_TV.setText(getResources().getString(R.string.result) + " = " + result);			
		} else {
			result_TV.setText("");
		}		
		
		
		Button somar = (Button) findViewById(R.id.Button_Sum);
		somar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SumActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		
		
		
		
		
	}
}
