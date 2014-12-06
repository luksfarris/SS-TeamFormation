package main;

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
		BufferedReader reader = null;
		File file = new File("assetsmarvel_character-victorfc.csv");
		String path = file.getAbsolutePath();
		String dado = "";
		String splitBy = ";";
		
		try {
			FileReader fr = new FileReader("C:\\Jowjow\\pathto\\marvel_character-victorfc.csv");
			reader = new BufferedReader(fr);
			dado = reader.readLine();
			while ((dado = reader.readLine()) != null) {
	 
				String[] dados = dado.split(splitBy); 
				
				Personagem novo = new Personagem();
				novo.setId(Integer.parseInt(dados[0]));
				novo.setPowerGrid(1, Integer.parseInt(dados[3]));
				novo.setPowerGrid(2, Integer.parseInt(dados[4]));
				novo.setPowerGrid(3, Integer.parseInt(dados[5]));
				novo.setPowerGrid(4, Integer.parseInt(dados[6]));
				novo.setPowerGrid(5, Integer.parseInt(dados[7]));
				novo.setPowerGrid(6, Integer.parseInt(dados[8]));
				novo.setPopularidade(Integer.parseInt(dados[9]));

				if(dados[2].equals("Hero")){
					novo.setHeroi(true);
				}
				else
					novo.setHeroi(false);

			}
		}
	  catch (FileNotFoundException e) {
		e.printStackTrace();
	  } catch (IOException e) {
		  e.printStackTrace();
	  } finally {
		  if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	  }
	}

}
