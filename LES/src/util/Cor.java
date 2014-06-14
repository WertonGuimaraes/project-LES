package util;

import java.util.Random;

public class Cor {
	private String cor;
	
	public Cor() {
		this.cor = geraCor();
	}
	
	private String geraCor() {

		Random randCol = new Random();
		String r = Integer.toHexString(randCol.nextInt(256));
		if (r.length() == 1) {
			r = "0" + r;
		}
		String g = Integer.toHexString(randCol.nextInt(256));
		if (g.length() == 1) {
			g = "0" + g;
		}
		String b = Integer.toHexString(randCol.nextInt(256));
		if (b.length() == 1) {
			b = "0" + b;
		}
		return r + g + b;
	}
	
	public String getCor() {
		return cor;
	}

}
