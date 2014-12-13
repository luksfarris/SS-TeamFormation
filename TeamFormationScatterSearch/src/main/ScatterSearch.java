package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
		
		refSet = new ArrayList<Solucao>(tamanhoRefSet);
		populacao = new ArrayList<Solucao>();
	}
	
	/**
	 * Atualiza o conjunto referencia com as 20 melhores solucoes da populacao.
	 * 
	 * @return <code>true</code> se houve alguma mudanca, 
	 * ou <code>false</code> se o conjunto continuou igual.
	 */
	private boolean atualizaConjuntoReferencia () {
		// atualiza o conjunto referencia com os N melhores resultados.
		boolean setChanged = false;
		Solucao lastBestSolution = null;
		if (refSet.size() == tamanhoRefSet) {
			lastBestSolution = refSet.get(0);
		}
		if (lastBestSolution !=null) {
			double mark = lastBestSolution.avalia(instancia);
			for (Solucao s : populacao) {
				if (!refSet.contains(s)) {
					if (s.avalia(instancia) > mark){
						setChanged = true;
						break;
					}
				}
			}
		} else {
			setChanged = true;
		}
		
		// ordena a populacao.
		Collections.sort(populacao, new Comparator<Solucao>() {
			@Override
			public int compare(Solucao o1, Solucao o2) {
				return Double.valueOf(o1.avalia(instancia)).compareTo(Double.valueOf(o2.avalia(instancia)));
			}
		});
		
		// pega os N ultimos
		refSet.clear();
		for (int i = populacao.size()-tamanhoRefSet; i < populacao.size(); i++) {
			refSet.add(populacao.get(i));
		}
		
		return setChanged;
	}
	
	/**
	 * Gera uma populacao de um determinado tamanho, com individuos da maneira mais
	 * variada possivel.
	 * @param tamanho tamanho total da populacao.
	 * 
	 * @author ivan
	 */
	public List<Solucao> gerarPopulacao(int tamanho) {
		List<Solucao> solucoes = new ArrayList<Solucao>();
		Random sorteiaTamanhoTime = new Random();
		// gera um conjunto de solucoes. Quanto mais variados forem as
		// solucoes, melhor sera o algoritmo. NAO ESQUECER de chamar melhora()
		// em cada solucao gerada!!!
		while(solucoes.size() < tamanho){
			//sorteia o tamanho do time a ser gerado, devendo estar entre minHerois e maxHerois.
			int tamanhoTime = sorteiaTamanhoTime.nextInt(this.instancia.maxHerois - this.instancia.minHerois) + this.instancia.minHerois;
			//gera uma nova solucao aleatoriamente composta
			Solucao novaSolucaoInicial = geraNovaSolucaoInicial(tamanhoTime);
			
			//TODO IMPROVE THIS
			if (novaSolucaoInicial.viavel(instancia) && !solucoes.contains(novaSolucaoInicial)) {
				solucoes.add(novaSolucaoInicial);
			}
		}
		return solucoes;
	}
	
	/**
	 * Gera os subconjuntos para serem combinados. Cada subconjunto deve ter metade
	 * das solucoes vindo do conjunto referencia, e metade do resto (pode ser aleatorio).
	 * @return lista de subconjuntos gerados.
	 * 
	 * @author ivan
	 */
	public List<SubConjunto> geraSubconjuntos () {
		//gera um novo conjunto de solucoes a partir do refset com metade das melhores solucoes do ref set atual e a outra metade eh pega aleatoriamente ou substitui uma solucao do ref set se for melhor 
		//NAO ATUALIZA o ref set
		List<Solucao> solucoesParaSubconjuntos = refinaRefSet();
		List<SubConjunto> listaSubconjuntos = new ArrayList<SubConjunto>();
		//inicializa lista de subconjuntos
		for(int i=0; i<4; i++) {
			SubConjunto s = new SubConjunto();
			s.solucoes = new ArrayList<Solucao>();
			listaSubconjuntos.add(s);
		}
		//distribuindo as solucoes entre os subconjuntos
		for(int i=0; i< solucoesParaSubconjuntos.size(); i++){
			listaSubconjuntos.get(i%4).solucoes.add(solucoesParaSubconjuntos.get(i));
		}
		return listaSubconjuntos;
	}
	
	private List<Solucao> refinaRefSet(){
		/* 
		 * O conjunto de referencia esta sempre ordenado quando esse metodo eh chamado. 
		 * Dividimos o conj de referencia em 4 partes; de 0 a tamanhoRefSet/4 - 1, sao as piores solucoes;
		 * as substituimos por solucoes aleatoriamente selecionadas da populacao 
		 * de tamanhoRefSet/4 a tamanhoRefSet/2 -1, substituimos por solucoes aleatoriamente selecionadas da
		 * populacao somente se o budget da solucao a ser incluida eh maior que o corrente;
		 * Os demais setores mantem as solucoes do conj de referencia atual
		 */
		List<Solucao> referenciaAlterado = new ArrayList<Solucao>();
		Random sorteiaDaPopulacao = new Random();
		int tamanhoPopulacao = populacao.size();
		for(int i=0; i<tamanhoRefSet; i++){
			if (i<(tamanhoRefSet/4)){
				int solucaoIndex= sorteiaDaPopulacao.nextInt(tamanhoPopulacao);
				//enquanto a solucao nao for uma que nao esta no refSet continua sorteando
				while(!estaNoRefSet(populacao.get(solucaoIndex))){
					solucaoIndex= sorteiaDaPopulacao.nextInt(tamanhoPopulacao);
				}
				//substitui pela solucao sorteada
				referenciaAlterado.add(populacao.get(solucaoIndex));
			}
			else if(i>=tamanhoRefSet/4 && i<tamanhoRefSet/2){
				int solucaoIndex= sorteiaDaPopulacao.nextInt(tamanhoPopulacao);
				//enquanto a solucao nao for uma que nao esta no refSet continua sorteando
				while(!estaNoRefSet(populacao.get(solucaoIndex))){
					solucaoIndex= sorteiaDaPopulacao.nextInt(tamanhoPopulacao);
				}
				//se solucao sorteada eh mais vantajosa substitui
				if(populacao.get(solucaoIndex).avalia(instancia) > populacao.get(i).avalia(instancia)){
					referenciaAlterado.add(populacao.get(solucaoIndex));
				}else{
					//senao mantem a antiga
					referenciaAlterado.add(populacao.get(i));
				}
			}
			else{
				//mantem as melhores solucoes do conj referencia
				referenciaAlterado.add(populacao.get(i));
			}
			//reordena o novo refSet
			Collections.sort(referenciaAlterado, new Comparator<Solucao>() {
				@Override
				public int compare(Solucao o1, Solucao o2) {
					return Double.valueOf(o1.avalia(instancia)).compareTo(Double.valueOf(o2.avalia(instancia)));
				}
			});
			
		}
		return referenciaAlterado;
	}
	

	/**
	 * Gera UMA solucao para constituir uma populacao, sorteando aleatoriamente um time de herois de tamanho = tamanhoTime 
	 * 
	 * @param tamanhoTime - tamanho do time de herois a ser gerado na solucao
	 * @return Solucao
	 * 
	 * @author ivan
	 */
	private Solucao geraNovaSolucaoInicial(int tamanhoTime){
		Solucao novaSolucao = new Solucao();
		Random sorteiaHeroi = new Random();
		int[] heroisSorteados = new int[tamanhoTime];
		//inicializa com um valor que nao pode ser indice
		for(int i=0; i< heroisSorteados.length; i++){
			heroisSorteados[i] = -1;
		}
		int numHeroisValidosSorteados = 0;
		while(numHeroisValidosSorteados < tamanhoTime){
			int personagemIndex = 0;
			boolean diferente = true;
			repLoop: while(diferente){
				//sorteia um heroi
				personagemIndex= sorteiaHeroi.nextInt(this.instancia.maxHeroIndex);
				//verifica se ja foi sorteado
				for(int i : heroisSorteados){
					//se ja foi sorteado,volta para sortear outro
					if(i == personagemIndex) continue repLoop;
				}
				diferente = false;
			}
			//nao eh repetido: adiciona ele na lista
			heroisSorteados[numHeroisValidosSorteados] = personagemIndex;
			numHeroisValidosSorteados++;
		}
		//adiciona os personagens na lista de herois da solucao
		for(int i=0; i<heroisSorteados.length; i++){
			novaSolucao.listaDeHerois.add(this.instancia.personagens.get(heroisSorteados[i]));
		}
		return novaSolucao;
	}
	
	/**
	 * Algoritmo Scatter Search. Metaheuristica que ira procurar a solucao mais proxima
	 * possivel do otimo. Para quando uma iteracao nao conseguir encontrar um resultado
	 * melhor.
	 * @return a Solucao encontrada. Pode nao ser factivel.
	 */
	public Solucao busca () {
		populacao.addAll(gerarPopulacao(tamanhoRefSet*10));
		atualizaConjuntoReferencia();
		
		boolean novasSolucoes = true;
		while (novasSolucoes) {
			novasSolucoes = false;
			List<SubConjunto> subconjuntos = geraSubconjuntos();
			Iterator<SubConjunto> it = subconjuntos.iterator();
			while (it.hasNext()) {
				SubConjunto subconjunto = (SubConjunto) it.next();
				List<Solucao> solucoesGeradas = Solucao.geraNovasSolucoes(subconjunto.solucoes, instancia);
				for (int i=0 ; i < solucoesGeradas.size() ; i++) {
					Solucao solucao = solucoesGeradas.get(i);
					solucao.melhoraSolucao(instancia);
					populacao.add(solucao);
				}
			}
			if (atualizaConjuntoReferencia()) {
				novasSolucoes = true;
			}
		}
		if (refSet.size() > 0) {
			return refSet.get(refSet.size()-1);
		} else {
			return null;
		}
	}
	
	/**
	 * Retorna se a solucao ocntem o personagem dado.
	 * @param personagem
	 * @return boolean
	 * 
	 * @author ivan
	 */
	public boolean estaNoRefSet(Solucao solucao){
		return this.populacao.contains(solucao);
	}
	
	/**
	 * @author Lucas Farris
	 * Subconjunto de solucoes gerado em cada passo da busca.
	 */
	private class SubConjunto {
		List<Solucao> solucoes;
	}
}
