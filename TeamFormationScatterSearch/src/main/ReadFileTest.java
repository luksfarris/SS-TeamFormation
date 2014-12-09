package main;

import java.util.List;


public class ReadFileTest {
	public static void main(String args[]){
		Instancia inst = new Instancia();
		List<Personagem> personagens = inst.personagens;

		inst.budget = 30;
		inst.maxHerois = 6;
		inst.minHerois = 4;
		int viloes[] = {763, 627};
		inst.viloes = viloes;
		
//		for(Personagem persona : personagens){
//			System.out.println(persona.toString());
//		}
		
		ScatterSearch search = new ScatterSearch(inst, 20);
		search.populacao = search.gerarPopulacao(100);
		search.geraSubconjuntos();
		
//		for(Solucao solucao : populacao){
//			System.out.println(solucao.toString());
//		}
	}
}
