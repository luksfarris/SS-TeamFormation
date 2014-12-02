package main;

import java.util.List;

public class Solucao {

	List<Personagem> listaDeHerois;
	
	/**
	 * Heuristica gulosa ou aleatoria, ou os dois, que tenta caminhar pra
	 * mais perto do otimo.
	 * 
	 * @param instancia instancia do problema com os herois disponiveis.
	 */
	public void melhoraSolucao (Instancia instancia) {
		// TODO: melhora a solucao de algum jeito. Quanto mais inteligente for
		// esse passo, melhor sera o algoritmo.
	}

	/**
	 * Recebe pelo menos duas solucoes, e gera pelo menos uma outra. Essa funcao tem
	 * que combinar as solucoes de maneira nao aleatoria.
	 * @param solucoes conjunto de solucoes que sera combinado.
	 * @return combinacao de pelo menos uma solucao gerada.
	 */
	public static List<Solucao> geraNovasSolucoes (List<Solucao> solucoes) {
		// TODO: quanto melhor for a combinacao de solucoes, melhor sera o algoritmo.
		return null;
	}
	
	/**
	 * Calcula o budget da solução corrente.
	 */
	private double calculaBudget (int[] viloes, Instancia instancia) {
		// TODO: calcula o budget usando funcao enviada por email pelo professor.
		return 0;
	}
	
	/**
	 * Determina se uma solucao é factivel ou não.
	 * @param instancia uma instancia do problema.
	 * @return <code>true</code> se for factivel, <code>false</code> caso contrario.
	 */
	public boolean factível (Instancia instancia) {
		return (listaDeHerois.size() >= instancia.minHerois 
				&& listaDeHerois.size() <= instancia.maxHerois
				&& calculaBudget(instancia.viloes, instancia) < instancia.budget);
	}
	
	/**
	 * Realiza o calculo do valor da solucao. O valor eh calculado pela soma das
	 * arestas entre todos os herois da solucao mais as arestas e entre os herois
	 * da solocao e os viloes da instancia.
	 * @return o valor calculado da solucao.
	 */
	public double avalia() {
		// TODO: calcula o valor dessa solucao.
		return 0;
	}
	
}
