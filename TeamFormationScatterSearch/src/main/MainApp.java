package main;

public class MainApp {
	
	public static int[] getViloes(int instancia) {

		if (instancia == 0) {
			int viloes[] = {763, 627};
			return viloes;
		} else if (instancia == 1) {
			int viloes[] = {763, 627, 577, 558};
			return viloes;
		} else if (instancia == 2) {
			int viloes[] = {763, 627, 577, 558, 749, 438};
			return viloes;
		} else if (instancia == 3) {
			int viloes[] = {763, 627, 577, 558, 749, 438, 624, 716};
			return viloes;
		} else if (instancia == 4) {
			int viloes[] = {763, 627, 577, 558, 749, 438, 624, 716, 607, 711};
			return viloes;
		} else if (instancia == 5) {
			int viloes[] = {763, 627, 577, 558, 749, 438, 624, 716, 607, 711, 560, 748};
			return viloes;
		} else if (instancia == 6) {
			int viloes[] = {763, 627, 577, 558, 749, 438, 624, 716, 607, 711, 560, 748, 706, 615};
			return viloes;
		} else if (instancia == 7) {
			int viloes[] = {763, 627, 577, 558, 749, 438, 624, 716, 607, 711, 560, 578, 602, 615, 541, 479};
			return viloes;
		} else if (instancia == 8) {
			int viloes[] = {763, 627, 577, 558, 749, 438, 624, 716, 607, 711, 560, 423, 748, 706, 479, 578, 541, 615};
			return viloes;
		} else if (instancia == 9) {
			int viloes[] = {763, 627, 577, 558, 749, 438, 624, 716, 607, 711, 560, 578, 602, 615, 541, 479, 706, 748, 423, 620};
			return viloes;
		} else {
			int viloes[] = {763, 627};
			return viloes;
		}
	}
	
	
	public static void main(String[] args) {
		
		Instancia instancia = new Instancia();
		instancia.budget = Double.MAX_VALUE;
		int viloes[] = getViloes(9);
		instancia.minHerois = 1;
		instancia.maxHerois = viloes.length + 1;
		instancia.viloes = viloes;
		
		ScatterSearch search = new ScatterSearch(instancia, 20);
		Solucao solucao = search.busca(2000);
		if (solucao != null) {
			System.out.println("Melhor solução encontrada tem valor " + solucao.avalia(instancia));
			System.out.println("S = " + solucao);
		} else {
			System.out.println("Nenhuma solucao encontrada");
		}
		
	}
}
