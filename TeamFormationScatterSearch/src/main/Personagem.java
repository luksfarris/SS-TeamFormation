package main;

/**
 * @author Lucas Farris
 * Classe que representa um heroi.
 */
public class Personagem {	
	
	/**Indica se o personagem eh heroi ou vilao. 
	 * Se for <code>true</code> eh heroi. */
	private boolean heroi;
	/** Grade de poderes, vai de 0 a 5 indices.*/
	private int powerGrid[];
	/** Popularidade do personagem. */
	private int popularidade;
	/** identificador unico de cada personagem. */
	private int id;

	public int[] getPowerGrid() {
		return powerGrid;
	}

	/**
	 * Altera um poder do personagem.
	 * @param position indice do poder.
	 * @param value novo valor do poder.
	 */
	public void setPowerGrid(int position, int value) {
		powerGrid[position] = value;
	}

	public int getPopularidade() {
		return popularidade;
	}

	public void setPopularidade(int popularidade) {
		this.popularidade = popularidade;
	}

	public boolean isHeroi() {
		return heroi;
	}

	public void setHeroi(boolean heroi) {
		this.heroi = heroi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
