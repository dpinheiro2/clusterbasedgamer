package decisionsExtraClusterChanceSaldo;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import DecisionsIntraCluster.CartasJogadasIntraCluster;
import UtilProbabilidadeChance.ProbabilidadeChance;
import UtilSaldo.Saldos;
import cbr.cbrDescriptions.TrucoDescription;
import clusterModelo.SaldoModelo;
import decisions.DecisionExtraClusterCarta;
import jcolibri.method.retrieve.RetrievalResult;





public class DecisionExtaClusterChanceDeSucessoEsaldoCarta implements DecisionExtraClusterCarta{
	
	CartasJogadasIntraCluster cartasJogadasIntraCluster;
	Saldos saldo = new Saldos();
	
	ProbabilidadeChance probabilidadeChance = new ProbabilidadeChance();
	public List<TrucoDescription> primeiraCarta(TrucoDescription gameState, int rodada, HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
	
	int idClusterComMaiorChanceDeVitoria = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaCarta(hashDeCasos, hashDeCasos.keySet());
	
		List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = hashDeCasos
				.get(idClusterComMaiorChanceDeVitoria);
		
		return listaDeCasosNoGrupoMaisAdequado;
	}
	
	public List<TrucoDescription> retornaListaDeCasosComMaiorChanceDeVitoriaSegundaCarta(HashMap<Integer, List<TrucoDescription>> hashDeCasos) {
		int idClusterComMaiorChanceDeVitoria = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaCarta(hashDeCasos, hashDeCasos.keySet());
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
		cartasJogadasIntraCluster = decisaoIntraCluster;
		
	}

	@Override
	public boolean irAoBaralho(List<TrucoDescription> listaCasos, int rodada, int quemMao, int ganhadorPrimeiraRodada,
			int ganhadorSegundaRodada, TrucoDescription consulta, HashMap<Integer, List<TrucoDescription>> hashCartas) {
		try {
			List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado  = new ArrayList<TrucoDescription>();
        	if(rodada == 1) listaDeCasosNoGrupoMaisAdequado = primeiraCarta(consulta, rodada, hashCartas);
        	else if(rodada == 2) listaDeCasosNoGrupoMaisAdequado = retornaListaDeCasosComMaiorChanceDeVitoriaSegundaCarta(hashCartas);
        	else if(rodada == 3) listaDeCasosNoGrupoMaisAdequado = retornaListaDeCasosComMaiorChanceDeVitoriaTerceiraCarta(hashCartas);
		return cartasJogadasIntraCluster.irAoBaralhoIntraCluster(listaDeCasosNoGrupoMaisAdequado, rodada, quemMao, ganhadorPrimeiraRodada, ganhadorSegundaRodada, consulta);
        }catch (Exception e) {
        	e.printStackTrace();
			return false;
		}
	}

	

	

	
	
}
