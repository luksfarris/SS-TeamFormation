package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScatterSearch {

	/** Instancia do problema.*/
	Instancia instancia;
	/** Tamanho do conjunto referencia. */
	int tamanhoRefSet;
	/** Conjunto referencia. */
	List<Solucao> refSet;
	/** Conjunto total de solucoes. */
	List<Solucao> populacao;
	
	
	/**
	 * Construtor.
	 * @param instancia instancia do problema que queremos encontrar uma solucao.
	 * @param tamanhoRefSet tamanho do conjunto referencia.
	 */
	public ScatterSearch(Instancia instancia, int tamanhoRefSet) {
		this.instancia = instancia;
		this.tamanhoRefSet = tamanhoRefSet;
		
		refSet = new ArrayList<>(tamanhoRefSet);
		populacao = new ArrayList<>(tamanhoRefSet*10);
	}
	
	/**
	 * Atualiza o conjunto referencia com as 20 melhores solucoes da populacao.
	 * 
	 * @return <code>true</code> se houve alguma mudanca, 
	 * ou <code>false</code> se o conjunto continuou igual.
	 */
	private boolean atualizaConjuntoReferencia () {
		// TODO: atualiza o conjunto referencia com os N melhores resultados.
		return false;
	}
	
	/**
	 * Gera uma populacao de um determinado tamanho, com individuos da maneira mais
	 * variada possivel.
	 * @param tamanho tamanho total da populacao.
	 */
	private List<Solucao> gerarPopulacao(int tamanho) {
		// TODO: gera um conjunto de solucoes. Quanto mais variados forem as
		// solucoes, melhor sera o algoritmo. NAO ESQUECER de chamar melhora()
		// em cada solucao gerada!!!
		return null;
	}
	
	/**
	 * Gera os subconjuntos para serem combinados. Cada subconjunto deve ter metade
	 * das solucoes vindo do conjunto referencia, e metade do resto (pode ser aleatorio).
	 * @return lista de subconjuntos gerados.
	 */
	private List<SubConjunto> geraSubconjuntos () {
		// TODO: gerar os subconjuntos.
		return null;
	}
	
	
	/**
	 * Algoritmo Scatter Search. Metaheuristica que ira procurar a solucao mais proxima
	 * possivel do otimo. Para quando uma iteracao nao conseguir encontrar um resultado
	 * melhor.
	 * @return a Solucao encontrada. Pode nao ser factivel.
	 */
	public Solucao busca () {
		populacao.addAll(gerarPopulacao(tamanhoRefSet));
		atualizaConjuntoReferencia();
		
		boolean novasSolucoes = true;
		while (novasSolucoes) {
			novasSolucoes = false;
			List<SubConjunto> subconjuntos = geraSubconjuntos();
			Iterator<SubConjunto> it = subconjuntos.iterator();
			while (it.hasNext()) {
				SubConjunto subconjunto = (SubConjunto) it.next();
				List<Solucao> solucoesGeradas = Solucao.geraNovasSolucoes(subconjunto.solucoes);
				for (int i=0 ; i < solucoesGeradas.size() ; i++) {
					Solucao solucao = solucoesGeradas.get(i);
					solucao.melhoraSolucao(instancia);
					populacao.add(solucao);
				}
				
				if (atualizaConjuntoReferencia()) {
					novasSolucoes = true;
				}
				
				it.remove();
			}
		}
		
		return refSet.get(0);
	}
	
	/**
	 * @author Lucas Farris
	 * Subconjunto de solucoes gerado em cada passo da busca.
	 */
	private class SubConjunto {
		List<Solucao> solucoes;
	}
}
