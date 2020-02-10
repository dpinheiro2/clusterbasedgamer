package decisionsExtraClusterProbabilityChance;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import UtilProbabilidadeChance.ProbabilidadeChance;
import UtilSaldo.Saldos;
import cbr.cbrDescriptions.TrucoDescription;

import clusterModelo.SaldoModelo;
import decisions.DecisionExtaClusterTruco;
import jcolibri.method.retrieve.RetrievalResult;

public class DecisionExtraClusterProbabilidadeTruco implements DecisionExtaClusterTruco {

	AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco;
	ProbabilidadeChance probabilidadeChance = new ProbabilidadeChance();
	public boolean chamarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
	try {
		
		int grupoComMaiorProbabilidadeQuemTruco  = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());
      ////////System.out.println("grupo com o maior probabilidade de vitória quemTRuco extra cluster: "+grupoComMaiorProbabilidadeQuemTruco);
		return decisaoIntraClusterTruco.chamarTrucoIntraCluster(hashQuemTruco.get(grupoComMaiorProbabilidadeQuemTruco), 
				rodada);
	}catch (Exception e) {
		////////System.out.println("entrou no catch no truco extra cluster chance saldo do chamar Truco");
		return false;
	}
	}
	
	
	
	
				@Override
		public boolean aceitarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
			try {

				int grupoComMaiorProbabilidadeQuemTruco  = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());
		      
				return decisaoIntraClusterTruco.aceitarTrucoIntraCluster(hashQuemTruco.get(grupoComMaiorProbabilidadeQuemTruco), rodada);
			}catch (Exception e) {
				////////System.out.println("entrou no catch no truco extra cluster chance saldo do chamar Truco");
				return false;
			}
		}

		
		@Override
		public boolean aceitarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
			try {
				// Cluster com maior saldo para quemTruco e para quando Truco
					int grupoComMaiorProbabilidadeQuemTruco  = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());
		      
				return decisaoIntraClusterTruco.aceitarRetrucoIntraCluster(hashQuemTruco.get(grupoComMaiorProbabilidadeQuemTruco), 
						 rodada);
				}catch (Exception e) {
					return false;
				}
		}

		
		@Override
		public boolean aceitarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
			try {
				
				int grupoComMaiorProbabilidadeQuemTruco  = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());
		      
				return decisaoIntraClusterTruco.aceitarValeQuatroIntraCluster(hashQuemTruco.get(grupoComMaiorProbabilidadeQuemTruco), rodada);
				}catch (Exception e) {
					return false;
				}
		}

		
		@Override
		public boolean chamarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco
				) {
			try {
			// Cluster com maior saldo para quemTruco e para quando Truco
				
				int grupoComMaiorProbabilidadeQuemTruco  = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());
		      
			return decisaoIntraClusterTruco.chamarRetrucoIntraCluster(hashQuemTruco.get(grupoComMaiorProbabilidadeQuemTruco), 
					 rodada);
			}catch (Exception e) {
				return false;
			}
		}

		@Override
		public boolean chamarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco
				) {
			try {
				
				int grupoComMaiorProbabilidadeQuemTruco  = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());
		      
			return decisaoIntraClusterTruco.chamarValeQuatroIntraCluster(hashQuemTruco.get(grupoComMaiorProbabilidadeQuemTruco), 
					rodada);
			}catch (Exception e) {
				return false;
			}
		}
		
		

		@Override
		public void setDecisionIntraClusterTruco(AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco) {
			
			this.decisaoIntraClusterTruco = decisaoIntraClusterTruco;
		}

}
