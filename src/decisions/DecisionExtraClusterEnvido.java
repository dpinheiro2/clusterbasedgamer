package decisions;

import java.util.HashMap;
import java.util.List;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import cbr.cbrDescriptions.TrucoDescription;

public interface DecisionExtraClusterEnvido {

	public boolean chamarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido);

	public boolean aceitarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido);

	public boolean chamarRealEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido);

	public boolean aceitarRealEnvido(TrucoDescription gamestate, int rodada,HashMap<Integer, List<TrucoDescription>> hashEnvido);

	public boolean chamarFaltaEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido);

	public boolean aceitarFaltaEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido);

	public void setDecisionIntraCluster(AcaoFeitasIntraClusterEnvido decisaoIntraCluster);

}
