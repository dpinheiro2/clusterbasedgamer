package decisionsExtraClusterProbabilitySorteio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import ajudaCluster.MaioriaAceitarModelo;
import ajudaCluster.MaioriaIrAoBaralhoModelo;
import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;
import decisions.DecisionExtaClusterTruco;
import jcolibri.method.retrieve.RetrievalResult;
import uteisRetornaQuantitativosSaldosOuProbabilidade.EspacoDePossibilidadesModelo;
import utilProbabilidadeSorteio.SorteioProbabilidade;

public class DecisionExtraClusterProbabilitySorteioTruco implements DecisionExtaClusterTruco {

	private AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco;
	private SorteioProbabilidade sorteioProb = new SorteioProbabilidade();
	
	public boolean chamarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		try {
			
			Integer clusterMaioriaQuemTruco = retornaClusterComProbabilidade(hashQuemTruco, hashQuemTruco.keySet());

			return decisaoIntraClusterTruco.chamarTrucoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco), rodada);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean aceitarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		try {
			
			Integer clusterMaioriaQuemTruco = retornaClusterComProbabilidade(hashQuemTruco, hashQuemTruco.keySet());

			return decisaoIntraClusterTruco.aceitarTrucoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco), rodada);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean chamarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		try {
	
			Integer clusterMaioriaQuemTruco = retornaClusterComProbabilidade(hashQuemTruco, hashQuemTruco.keySet());

			return decisaoIntraClusterTruco.chamarRetrucoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco),
					 rodada);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean aceitarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		try {
			
			Integer clusterMaioriaQuemTruco = retornaClusterComProbabilidade(hashQuemTruco, hashQuemTruco.keySet());

			return decisaoIntraClusterTruco.aceitarTrucoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco), rodada);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean chamarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		// Cluster com maior saldo para quemTruco e para quando Truco
	
		Integer clusterMaioriaQuemTruco = retornaClusterComProbabilidade(hashQuemTruco, hashQuemTruco.keySet());

		return decisaoIntraClusterTruco.chamarValeQuatroIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco),
				 rodada);

	}

	public boolean aceitarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco
			) {
		
		Integer clusterMaioriaQuemTruco = retornaClusterComProbabilidade(hashQuemTruco, hashQuemTruco.keySet());

		return decisaoIntraClusterTruco.aceitarValeQuatroIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco), rodada);

	}

	

	public Integer retornaClusterComProbabilidade(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {
		return sorteioProb.retornaSorteadoComProbabilidade(hash, listaSemRepetir);	}


	@Override
	public void setDecisionIntraClusterTruco(AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco) {
		this.decisaoIntraClusterTruco = decisaoIntraClusterTruco;

	}

}
