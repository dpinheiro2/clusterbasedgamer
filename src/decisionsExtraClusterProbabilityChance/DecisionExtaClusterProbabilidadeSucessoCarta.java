package decisionsExtraClusterProbabilityChance;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.CartasJogadasIntraCluster;
import UtilProbabilidadeChance.ProbabilidadeChance;
import cbr.cbrDescriptions.TrucoDescription;
import decisions.DecisionExtraClusterCarta;
import jcolibri.method.retrieve.RetrievalResult;




public class DecisionExtaClusterProbabilidadeSucessoCarta implements DecisionExtraClusterCarta{
	
	ProbabilidadeChance probabilidadeChance = new ProbabilidadeChance();
	CartasJogadasIntraCluster decisaoIntraCluster;
		
		
	
	public List<TrucoDescription> primeiraCarta(TrucoDescription gameState, int rodada, HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
	
	int idClusterComMaiorChanceDeVitoria = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaCarta(hashDeCasos, hashDeCasos.keySet());
	
		List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = hashDeCasos
				.get(idClusterComMaiorChanceDeVitoria);
		
		return listaDeCasosNoGrupoMaisAdequado;
	}
	
	public List<TrucoDescription> retornaListaDeCasosComMaiorChanceDeVitoriaSegundaCarta(HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
		int idClusterComMaiorChanceDeVitoria = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaCarta(hashDeCasos, hashDeCasos.keySet());
		//////System.out.println("cluster escolhido: " + idClusterComMaiorChanceDeVitoria);
		List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = hashDeCasos.get(idClusterComMaiorChanceDeVitoria);

		return listaDeCasosNoGrupoMaisAdequado;
	}

	


	public List<TrucoDescription> retornaListaDeCasosComMaiorChanceDeVitoriaTerceiraCarta(HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
		int idClusterComMaiorChanceDeVitoria = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaCarta(hashDeCasos, hashDeCasos.keySet());
		List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = hashDeCasos.get(idClusterComMaiorChanceDeVitoria);

		return listaDeCasosNoGrupoMaisAdequado;
	}
	

	@Override
	public void setDecisionIntraCluster(CartasJogadasIntraCluster decisaoIntraCluster) {
		this.decisaoIntraCluster = decisaoIntraCluster;
		
	}

	@Override
	public boolean irAoBaralho(List<TrucoDescription> listaCasos, int rodada, int quemMao, int ganhadorPrimeiraRodada,
			int ganhadorSegundaRodada, TrucoDescription consulta, HashMap<Integer, List<TrucoDescription>> hashCartas) {
		try {
			List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado  = new ArrayList<TrucoDescription>();
        	if(rodada == 1) listaDeCasosNoGrupoMaisAdequado = primeiraCarta(consulta, rodada, hashCartas);
        	else if(rodada == 2) listaDeCasosNoGrupoMaisAdequado = retornaListaDeCasosComMaiorChanceDeVitoriaSegundaCarta(hashCartas);
        	else if(rodada == 3) listaDeCasosNoGrupoMaisAdequado = retornaListaDeCasosComMaiorChanceDeVitoriaTerceiraCarta(hashCartas);
		
        	return decisaoIntraCluster.irAoBaralhoIntraCluster(listaDeCasosNoGrupoMaisAdequado, rodada, quemMao, ganhadorPrimeiraRodada, ganhadorSegundaRodada, consulta);
        }catch (Exception e) {
        	e.printStackTrace();
			return false;
		}

	}

	

	
	
}
