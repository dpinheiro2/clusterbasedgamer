package decisionsExtraClusterChanceSaldo;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import UtilSaldo.Saldos;
import cbr.cbrDescriptions.TrucoDescription;

import clusterModelo.SaldoModelo;
import decisions.DecisionExtaClusterTruco;
import jcolibri.method.retrieve.RetrievalResult;

public class DecisionExtraClusterChanceDeSucessoEsaldoTruco implements DecisionExtaClusterTruco {

	AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco;
	Saldos saldo = new Saldos();
	public boolean chamarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
	try {
		
		SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
      
		return decisaoIntraClusterTruco.chamarTrucoIntraCluster(hashQuemTruco.get(saldoModeloQuemTruco.getClusterComMaiorSaldo()),  rodada);
	}catch (Exception e) {
		////////System.out.println("entrou no catch no truco extra cluster chance saldo do chamar Truco");
		e.printStackTrace();
		return false;
	}
	}
	
		

	
	
				@Override
		public boolean aceitarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
			try {
			
				SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
		      
				return decisaoIntraClusterTruco.aceitarTrucoIntraCluster(hashQuemTruco.get(saldoModeloQuemTruco.getClusterComMaiorSaldo()), 
						rodada);
			}catch (Exception e) {
				////////System.out.println("entrou no catch no truco extra cluster chance saldo do chamar Truco");
				e.printStackTrace();
				return false;
			}
		}

		
		@Override
		public boolean aceitarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
			try {
				// Cluster com maior saldo para quemTruco e para quando Truco
					SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
		      
				return decisaoIntraClusterTruco.aceitarRetrucoIntraCluster(hashQuemTruco.get(saldoModeloQuemTruco.getClusterComMaiorSaldo()), rodada);
				}catch (Exception e) {
					e.printStackTrace();
					return false;
				}
		}

		
		@Override
		public boolean aceitarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
			try {
				
				SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
		      
				return decisaoIntraClusterTruco.aceitarValeQuatroIntraCluster(hashQuemTruco.get(saldoModeloQuemTruco.getClusterComMaiorSaldo()), rodada);
				}catch (Exception e) {
					e.printStackTrace();
					return false;
				}
		}

		
		@Override
		public boolean chamarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
			try {
			// Cluster com maior saldo para quemTruco e para quando Truco
			
			SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
	      
			return decisaoIntraClusterTruco.chamarRetrucoIntraCluster(hashQuemTruco.get(saldoModeloQuemTruco.getClusterComMaiorSaldo()), 
					 rodada);
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		public boolean chamarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
				HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
			try {
			
			SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
	      
			return decisaoIntraClusterTruco.chamarValeQuatroIntraCluster(hashQuemTruco.get(saldoModeloQuemTruco.getClusterComMaiorSaldo()), rodada);
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		

		@Override
		public void setDecisionIntraClusterTruco(AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco) {
			
			this.decisaoIntraClusterTruco = decisaoIntraClusterTruco;
		}

}
