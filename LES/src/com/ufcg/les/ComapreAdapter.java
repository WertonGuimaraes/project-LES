package com.ufcg.les;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufcg.entities.Session;
import com.ufcg.entities.Ti;

public class ComapreAdapter extends BaseAdapter {

	private List<Ti> mNomesTi;
	private LayoutInflater mInflater;
	private Context mContext;
	private CompareWeeks compWeeks;

	public ComapreAdapter(Context context, List<Ti> nomesTi, CompareWeeks compareWeeks) {
		mInflater = LayoutInflater.from(context);
		mNomesTi = nomesTi;
		mContext = context;
		compWeeks = compareWeeks;
		
	}

	static class ViewHolder {
		protected TextView nome;
		protected ImageView cor;
		protected CheckBox checkbox;
	}

	@Override
	public int getCount() {
		return mNomesTi.size();
	}

	@Override
	public Object getItem(int index) {
		return mNomesTi.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View view, ViewGroup viewGroup) {
		ViewHolder viewHolder = null;
		if (view == null) {
			view = mInflater.inflate(R.layout.compare_adapter, null);
			viewHolder = new ViewHolder();
			viewHolder.nome = (TextView) view.findViewById(R.id.nomeCompare);
			viewHolder.cor = (ImageView) view.findViewById(R.id.cor);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
			viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

					int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
					mNomesTi.get(getPosition).setChecked(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
					
					List<String> lista = Session.getInstancia().getIsCheckedList();

					if(!buttonView.isChecked()){
						Log.d("outro", " ");
						
						Log.d("outro", "tamanho: " +lista.size()+"");
						Log.d("outro", "removido: " +mNomesTi.get(getPosition).getNome());
						Log.d("outro", "indice: " +lista.indexOf(mNomesTi.get(getPosition).getNome()));

						int indice = lista.indexOf(mNomesTi.get(getPosition).getNome());
						if(indice > -1){
							lista.remove(indice);
						}
					}else{
						boolean existe = false;
						for (String nomes : lista) {
							if(nomes.equals(mNomesTi.get(getPosition).getNome())){
								existe = true;
								break;
							}
						}
						if(!existe){
							Session.getInstancia().getIsCheckedList().add(mNomesTi.get(getPosition).getNome());
						}
					}

					Log.d("outro", Session.getInstancia().getIsCheckedList().size()+"");
					compWeeks.geraGrafico(Session.getInstancia().getIsCheckedList());
				}
			});

			view.setTag(viewHolder);
			view.setTag(R.id.nomeCompare, viewHolder.nome);
			view.setTag(R.id.cor, viewHolder.cor);
			view.setTag(R.id.check, viewHolder.checkbox);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.checkbox.setTag(posicao); // This line is important.

		String nomeDaTi = mNomesTi.get(posicao).getNome();
		viewHolder.nome.setText(nomeDaTi);
		viewHolder.cor.setBackgroundColor(Color.parseColor("#"+Session.getInstancia().recuperaCor(nomeDaTi)));
		viewHolder.checkbox.setChecked(mNomesTi.get(posicao).isChecked());

		return view;
	}

}
