package decisionsExtraClusterMaioria;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import cbr.cbrDescriptions.TrucoDescription;
import decisions.DecisionExtaClusterTruco;
import jcolibri.method.retrieve.RetrievalResult;


public class DecisionExtraClusterMaioriaTruco implements DecisionExtaClusterTruco{

	
	private AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco;
	
	public boolean chamarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
	
		
		Integer clusterMaioriaQuemTruco = retornaClusterComMaioria(hashQuemTruco, hashQuemTruco.keySet());
		
		return decisaoIntraClusterTruco.chamarTrucoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco), rodada);
        
		
	}
	public boolean aceitarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
	
	
		Integer clusterMaioriaQuemTruco = retornaClusterComMaioria(hashQuemTruco, hashQuemTruco.keySet());
		
      return decisaoIntraClusterTruco.aceitarTrucoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco),  rodada);
		

	}
	
	
	
	public boolean chamarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		// Cluster com maior saldo para quemTruco e para quando Truco
				
				Integer clusterMaioriaQuemTruco = retornaClusterComMaioria(hashQuemTruco, hashQuemTruco.keySet());
				
		        
				return decisaoIntraClusterTruco.chamarRetrucoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco),  rodada);

		
	}
	
	public boolean aceitarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		
				Integer clusterMaioriaQuemTruco = retornaClusterComMaioria(hashQuemTruco, hashQuemTruco.keySet());
				
		        
				
				
				return decisaoIntraClusterTruco.aceitarRetrucoIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco), rodada);

		
	}
	
	
	public boolean chamarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		
				Integer clusterMaioriaQuemTruco = retornaClusterComMaioria(hashQuemTruco, hashQuemTruco.keySet());
			
				return decisaoIntraClusterTruco.chamarValeQuatroIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco),  rodada);
		
	}
	
	public boolean aceitarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco) {
		
				Integer clusterMaioriaQuemTruco = retornaClusterComMaioria(hashQuemTruco, hashQuemTruco.keySet());
				
		        
				return decisaoIntraClusterTruco.aceitarValeQuatroIntraCluster(hashQuemTruco.get(clusterMaioriaQuemTruco),  rodada);

		
	}
	
	

	
	public Integer retornaClusterComMaioria(HashMap<Integer, List<TrucoDescription>> hash, Set<Integer> listaSemRepetir) {

		int grupoPresenteNaMaioriaDosCasosAnalisadosMaioria = 0;
		int quantidadeDeCasosRecuperadosNoGrupoQueMaisAparece = 0;
        Iterator<Integer> iteratorK = listaSemRepetir.iterator();
		while (iteratorK.hasNext()) {
			int k = iteratorK.next();
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
				public void setDecisionIntraClusterTruco(AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco) {
				 this.decisaoIntraClusterTruco = decisaoIntraClusterTruco;
					
				}
				
		
}
