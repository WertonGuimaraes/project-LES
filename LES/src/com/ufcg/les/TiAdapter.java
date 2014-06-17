package com.ufcg.les;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufcg.entities.Ti;

public class TiAdapter extends BaseAdapter {

	private List<Ti> mTi;
	private LayoutInflater mInflater;
	private int tempoTotal;

	public TiAdapter(Context context, List<Ti> ti, int tempoTotal) {
		mInflater = LayoutInflater.from(context);
		mTi = ti;
		this.tempoTotal = tempoTotal;
	}

	@Override
	public int getCount() {
		return mTi.size();
	}

	@Override
	public Object getItem(int index) {
		return mTi.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View view, ViewGroup viewGroup) {
		view = mInflater.inflate(R.layout.ti_adapter_item, null);
		Ti ti = mTi.get(posicao);
		
		ImageView ivCor = (ImageView) view.findViewById(R.id.cor);
		ivCor.setBackgroundColor(Color.parseColor(ti.getCor()));

		TextView tvNome = (TextView) view.findViewById(R.id.nome);
		tvNome.setText("Nome: " + ti.getNome());
		
		TextView tvTempo = (TextView) view.findViewById(R.id.tempo);
		tvTempo.setText("Tempo total: " + ti.getTempo());
		
		TextView tvPrioridade = (TextView) view.findViewById(R.id.prioridade);
		int indicePrioridade = ti.getPrioridade();
		String[] nomesPrioridade = {"Alta", "Média", "Baixa"};
		tvPrioridade.setText("Prioridade: " + nomesPrioridade[indicePrioridade]);
		
		TextView tvProporcao = (TextView) view.findViewById(R.id.proporcao);
		String prop = String.valueOf((ti.getTempo()/(float) tempoTotal)*100);
		tvProporcao.setText("Proporção: " + prop + "%");

		return view;
	}

}
