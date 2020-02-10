package decisions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import cbr.cbrDescriptions.TrucoDescription;
import jcolibri.method.retrieve.RetrievalResult;

public interface DecisionExtaClusterTruco {
	public boolean chamarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco );
	
	public boolean aceitarTruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco );	
	
	
	
		
	
	public boolean chamarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco);
	
	public boolean aceitarRetruco(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco);
	
	public boolean chamarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco);
	public boolean aceitarValeQuatro(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best,
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco);
	


	public void setDecisionIntraClusterTruco(AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco);
	
}
