package main;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

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
		lePersonagens();
		leGrafo(personagens.size());
		
	}
	
	public void leGrafo(int size){
		BufferedReader reader = null;
		String dado = "";
		String splitBy = ";";
		grafo = new double[personagens.size()+1][personagens.size()+1];
			
		try {
			FileReader fr = new FileReader("assets/shared_comic_books-victorfc.csv");
			reader = new BufferedReader(fr);
			dado = reader.readLine();
			while ((dado = reader.readLine()) != null) {
	 
				String[] dados = dado.split(splitBy); //1;comet;hero;1;1;1;1;1;1;
				System.out.println(dado);
				grafo[Integer.parseInt(dados[0])][Integer.parseInt(dados[1])]=Integer.parseInt(dados[2]);
				grafo[Integer.parseInt(dados[1])][Integer.parseInt(dados[0])]=Integer.parseInt(dados[2]);

			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void lePersonagens(){

		personagens = new ArrayList<Personagem>();
		BufferedReader reader = null;
		String dado = "";
		String splitBy = ";";
		
		try {
			FileReader fr = new FileReader("assets/marvel_character-victorfc.csv");
			reader = new BufferedReader(fr);
			dado = reader.readLine();
			while ((dado = reader.readLine()) != null) {
	 
				String[] dados = dado.split(splitBy); //1;comet;hero;1;1;1;1;1;1;9
				
				Personagem novo = new Personagem();
				novo.setId(Integer.parseInt(dados[0]));
				novo.setPowerGrid(1, Integer.parseInt(dados[3]));
				novo.setPowerGrid(2, Integer.parseInt(dados[4]));
				novo.setPowerGrid(3, Integer.parseInt(dados[5]));
				novo.setPowerGrid(4, Integer.parseInt(dados[6]));
				novo.setPowerGrid(5, Integer.parseInt(dados[7]));
				novo.setPowerGrid(6, Integer.parseInt(dados[8]));
				novo.setPopularidade(Integer.parseInt(dados[9]));

				if(dados[2].equals("hero")){
					novo.setHeroi(true);
				}
				else
					novo.setHeroi(false);
				personagens.add(novo);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (reader != null) {
				try {
					reader.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
