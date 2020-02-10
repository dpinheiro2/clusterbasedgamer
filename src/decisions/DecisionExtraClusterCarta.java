package decisions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import DecisionsIntraCluster.CartasJogadasIntraCluster;
import cbr.cbrDescriptions.TrucoDescription;
import jcolibri.method.retrieve.RetrievalResult;

public interface DecisionExtraClusterCarta {
	
	
	
	public List<TrucoDescription> primeiraCarta(TrucoDescription gameState, int rodada, HashMap<Integer, List<TrucoDescription>> hashDeCasos);
	
	public List<TrucoDescription> retornaListaDeCasosComMaiorChanceDeVitoriaSegundaCarta(HashMap<Integer, List<TrucoDescription>> hashDeCasos);

	public boolean irAoBaralho(List<TrucoDescription> listaCasos, int rodada, int quemMao, int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta, HashMap<Integer, List<TrucoDescription>> hashQuemTruco);
	
	public void setDecisionIntraCluster(CartasJogadasIntraCluster decisaoIntraCluster);

	
}
