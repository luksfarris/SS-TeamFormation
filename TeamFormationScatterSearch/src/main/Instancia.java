package main;

import java.util.List;

/**
 * @author Lucas Farris
 * Classe que representa uma instacia do problema.
 */
public class Instancia {
	
	/** conjunto de N herois e de M viloes com tamanho N+M. */
	List<Personagem> personagens;
	
	/** Ids do grupo de viloes que queremos derrotar.*/
	int viloes[];
	/** grafo onde os valores de 0 a N representam vertices de herois, e os vertices de 
	N a N+M representam os viloes.*/
	double[][] grafo;
	/** limite superior do time de herois */
	int maxHerois;
	/** limite inferior do time de herois */
	int minHerois;
	/** Budget maximo. */
	double budget;
	
	public Instancia() {
		lerDados();
	}
	
	/**
	 * Le os arquivos de herois e do grafo, e cria as estruturas de dados.
	 */
	public void lerDados() {
		// TODO: ler os csv's. CSV nada mais eh que um txt organizado, entao da pra ler
		// normal. Essa função precisa peencher as variaveis herois, viloes e grafo!!
		//Vou fazer essa parte aqui - André

	}
}
