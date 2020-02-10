package decisionsExtraClusterProbabilitySorteio;



import java.util.Collection;
import java.util.HashMap;

import java.util.List;

import java.util.Set;

import DecisionsIntraCluster.CartasJogadasIntraCluster;
import cbr.cbrDescriptions.TrucoDescription;
import decisions.DecisionExtraClusterCarta;
import jcolibri.method.retrieve.RetrievalResult;
import utilProbabilidadeSorteio.SorteioProbabilidade;

public class DecisionExtraClusterProbabilitySorteioCarta implements DecisionExtraClusterCarta {
	CartasJogadasIntraCluster decisaoIntraCluster;
	
	private SorteioProbabilidade sorteioProb = new SorteioProbabilidade();

	public List<TrucoDescription> primeiraCarta(TrucoDescription gameState, int rodada, HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
		int idClusterQueMaisAparece = 0;
		////////System.out.println("hash dentro do método: "+ hashDeCasosMao.size());
		 idClusterQueMaisAparece = retornaClusterComProbabilidade(hashDeCasos, hashDeCasos.keySet());
		 
	 
	 
	 List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = null;
	
			listaDeCasosNoGrupoMaisAdequado = hashDeCasos.get(idClusterQueMaisAparece);
		
		
		return listaDeCasosNoGrupoMaisAdequado;
	}
	
	public List<TrucoDescription> retornaListaDeCasosComMaiorChanceDeVitoriaSegundaCarta(HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
		int idClusterProbabilidade = retornaClusterComProbabilidade(hashDeCasos, hashDeCasos.keySet());
		//////System.out.println("id escolhido: "+ idClusterProbabilidade);
		List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = hashDeCasos.get(idClusterProbabilidade);

		return listaDeCasosNoGrupoMaisAdequado;
	}

	public Integer retornaClusterComProbabilidade(HashMap<Integer, List<TrucoDescription>> hash, Set<Integer> listaSemRepetir) {
		return sorteioProb.retornaSorteadoComProbabilidade(hash, listaSemRepetir);
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
				try {
					Integer clusterMaioriaQuemTruco = retornaClusterComProbabilidade(hashQuemTruco, hashQuemTruco.keySet());

					return decisaoIntraCluster.irAoBaralhoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco), rodada, quemMao, ganhadorPrimeiraRodada, ganhadorSegundaRodada, consulta);
				} catch (Exception e) {
					return false;
				}
	}

	

	
	}
