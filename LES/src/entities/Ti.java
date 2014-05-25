package entities;

public class Ti {
	private String nome, foto; 
	private Integer tempo, prioridade;
	private Long data;
	
	/**
	 * @param nome
	 * @param tempo
	 * @param data
	 * @param foto
	 * @param prioridade
	 */
	public Ti(String nome, Integer tempo, Long data, String foto, Integer prioridade) {
		this.nome = nome;
		this.tempo = tempo;
		this.data = data;
		this.foto = foto;
		this.prioridade = prioridade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}
	
	

}
