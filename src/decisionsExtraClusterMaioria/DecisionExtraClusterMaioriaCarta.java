package decisionsExtraClusterMaioria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import DecisionsIntraCluster.CartasJogadasIntraCluster;
import cbr.cbrDescriptions.TrucoDescription;
import decisions.DecisionExtraClusterCarta;
import jcolibri.method.retrieve.RetrievalResult;

public class DecisionExtraClusterMaioriaCarta implements DecisionExtraClusterCarta {
	CartasJogadasIntraCluster decisaoIntraCluster;
	
	public List<TrucoDescription> primeiraCarta(TrucoDescription gameState, int rodada, HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
	
	int idClusterQueMaisAparece = retornaClusterComMaioria(hashDeCasos, hashDeCasos.keySet());
	
		List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = new ArrayList<TrucoDescription>();
		
			listaDeCasosNoGrupoMaisAdequado.addAll(hashDeCasos.get(idClusterQueMaisAparece));
		
		return listaDeCasosNoGrupoMaisAdequado;
	}
	
	public List<TrucoDescription> retornaListaDeCasosComMaiorChanceDeVitoriaSegundaCarta(HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
		int idClusterPresenteNaMaioria = retornaClusterComMaioria(hashDeCasos, hashDeCasos.keySet());

		List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = hashDeCasos.get(idClusterPresenteNaMaioria);

		return listaDeCasosNoGrupoMaisAdequado;
	}

	public Integer retornaClusterComMaioria(HashMap<Integer, List<TrucoDescription>> hash, Set<Integer> listaSemRepetir) {

		int grupoPresenteNaMaioriaDosCasosAnalisadosMaioria = 0;
		int quantidadeDeCasosRecuperadosNoGrupoQueMaisAparece = 0;
		Iterator<Integer> iteratorListaSemRepetir = listaSemRepetir.iterator();
		while (iteratorListaSemRepetir.hasNext()) {
			int k = iteratorListaSemRepetir.next();
			if (k != 0) {
				List<TrucoDescription> casosDoGrupoAtual = hash.get(k);
				int quantidadeDeCasosDoGrupoAtual = casosDoGrupoAtual.size();
				if(quantidadeDeCasosDoGrupoAtual > quantidadeDeCasosRecuperadosNoGrupoQueMaisAparece) {
					grupoPresenteNaMaioriaDosCasosAnalisadosMaioria =k;
					quantidadeDeCasosRecuperadosNoGrupoQueMaisAparece = quantidadeDeCasosDoGrupoAtual;
					
				}
				
				
		}
		

		
	}
		return grupoPresenteNaMaioriaDosCasosAnalisadosMaioria;
	
	}


	@Override
	public void setDecisionIntraCluster(CartasJogadasIntraCluster decisaoIntraCluster) {
		this.decisaoIntraCluster = decisaoIntraCluster;
		
	}

	@Override
	public boolean irAoBaralho(List<TrucoDescription> listaCasos, int rodada, int quemMao, int ganhadorPrimeiraRodada,
			int ganhadorSegundaRodada, TrucoDescription consulta,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		// Cluster com maior saldo para quemTruco e para quando Truco
		
			Integer clusterMaioriaQuemTruco = retornaClusterComMaioria(hashQuemTruco, hashQuemTruco.keySet());
			
			
			
			return decisaoIntraCluster.irAoBaralhoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco), rodada, quemMao, ganhadorPrimeiraRodada, ganhadorSegundaRodada, consulta);
	}

	

	

	
	}
