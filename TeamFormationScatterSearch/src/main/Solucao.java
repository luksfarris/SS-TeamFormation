package main;

import java.util.ArrayList;
import java.util.List;

public class Solucao {

	List<Personagem> listaDeHerois = new ArrayList<Personagem>();
	double valor = -1;
	
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
	private double calculaBudget (Instancia instancia) {
		// calcula o budget usando funcao enviada por email pelo professor.
		double exp1 = 0;
		double exp2 = 0;
		double powerGridHerois = 0; // calcula o power grid medio dos herois
		double popularidadeHerois = 0;
		for (Personagem p : listaDeHerois) {
			for (int i=0; i<6; i++) {
				powerGridHerois += (p.getPowerGrid()[i] / listaDeHerois.size());
			}
			popularidadeHerois += p.getPopularidade() / listaDeHerois.size();
		}
		double powerGridViloes = 0;
		double popularidadeViloes = 0;
		double vtCost = 0;
		for (int id : instancia.viloes) {
			Personagem p = instancia.personagens.get(id);
			for (int i=0; i<6; i++) {
				powerGridViloes += ((double) p.getPowerGrid()[i] / (double) instancia.viloes.length);
				vtCost += (p.getPowerGrid()[i]/6.0) * p.getPopularidade();
			}
			popularidadeViloes += p.getPopularidade() / instancia.viloes.length;
		}
		double ratioPG = powerGridHerois/powerGridViloes;
		double ratioPop = popularidadeHerois/popularidadeViloes;
		
		double powerGridTodosViloes = 0;
		int quantidadeViloes = 0;
		for (Personagem p : instancia.personagens) {
			if (!p.isHeroi()){
				quantidadeViloes ++;
				for(int i=0 ; i< 6; i++){
					powerGridTodosViloes += p.getPowerGrid()[i];
				}
			}
		}
		powerGridTodosViloes /= quantidadeViloes;
		double factor = powerGridViloes / powerGridTodosViloes;
		
		exp1 = ratioPG * ratioPop * vtCost;
		exp2 = factor * powerGridHerois * popularidadeHerois * instancia.viloes.length;
		return Math.max(exp1, exp2);
	}
	
	/**
	 * Determina se uma solucao é factivel ou não.
	 * @param instancia uma instancia do problema.
	 * @return <code>true</code> se for factivel, <code>false</code> caso contrario.
	 */
	public boolean factível (Instancia instancia) {
		return (listaDeHerois.size() >= instancia.minHerois 
				&& listaDeHerois.size() <= instancia.maxHerois
				&& calculaBudget(instancia) < instancia.budget);
	}
	
	/**
	 * Determina se o conjunto de herois da solucao eh capaz de derrotar os viloes.
	 * @param instancia .
	 * @return Se o powergrid dos herois eh maior que o dos viloes.
	 */
	public boolean viavel (Instancia instancia) {
		int viloesPG[] = new int[6];
		int heroisPG[] = new int[6];
		boolean viavel = true;
		for (int i=0;i<6;i++){
			for (int v=0; v<instancia.viloes.length;v++) {
				Personagem vilao = instancia.personagens.get(v);
				viloesPG[i] += vilao.getPowerGrid()[i];
			}
			for (Personagem p : listaDeHerois) {
				heroisPG[i] += p.getPowerGrid()[i];
			}
			
			if (viloesPG[i] > heroisPG[i]) {
				return false;
			}
		}		
		return viavel;
	}
	
	/**
	 * Realiza o calculo do valor da solucao. O valor eh calculado pela soma das
	 * arestas entre todos os herois da solucao mais as arestas e entre os herois
	 * da solocao e os viloes da instancia.
	 * @return o valor calculado da solucao.
	 */
	public double avalia(Instancia instancia) {
		// calcula o valor dessa solucao.
		if (valor < 0) {
			valor = 0;
			// se a solucao nem for solucao, o valor sera zero.
			if (viavel(instancia)) {
				for (Personagem p : listaDeHerois) {
					// soma a colaboracao com os outros herois do time
					for (Personagem q : listaDeHerois) {
						if (p.getId() != q.getId()) {
							valor += instancia.grafo[p.getId()][q.getId()];
						}
					}
					// soma as arestas com os viloes
					for (int vId : instancia.viloes) {				
						valor += instancia.grafo[p.getId()][vId];
					}
				}
			}
			// se a solucao nao for factivel, dividimos o valor por 2
			if (!factível(instancia)) {
				valor /=2;
			}
		}
		return valor;
	}

	@Override
	public String toString() {
		return "Solucao [listaDeHerois=" + listaDeHerois + "]";
	}
	
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
