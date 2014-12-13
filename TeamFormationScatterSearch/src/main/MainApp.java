package main;

public class MainApp {

	public static void main(String[] args) {
		
		Instancia instancia = new Instancia();
		instancia.budget = Double.MAX_VALUE;
		instancia.minHerois = 3;
		instancia.maxHerois = 10;
		int viloes[] = {763, 627};
		instancia.viloes = viloes;
		
		ScatterSearch search = new ScatterSearch(instancia, 20);
		Solucao solucao = search.busca();
		
	}
}
