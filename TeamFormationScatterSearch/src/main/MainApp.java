package main;

public class MainApp {

	public static void main(String[] args) {
		
		Instancia instancia = new Instancia();
		instancia.budget = Double.MAX_VALUE;
		instancia.minHerois = 1;
		instancia.maxHerois = 3;
		int viloes[] = {763, 627};
		instancia.viloes = viloes;
		
		ScatterSearch search = new ScatterSearch(instancia, 20);
		Solucao solucao = search.busca();
		if (solucao != null) {
			System.out.println("Melhor solução encontrada tem valor " + solucao.avalia(instancia));
			System.out.println("S = " + solucao);
		} else {
			System.out.println("Nenhuma solucao encontrada");
		}
		
	}
}
