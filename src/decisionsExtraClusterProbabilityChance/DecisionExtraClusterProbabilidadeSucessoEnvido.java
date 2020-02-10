
package decisionsExtraClusterProbabilityChance;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import UtilProbabilidadeChance.ProbabilidadeChance;
import UtilSaldo.Saldos;
import cbr.cbrDescriptions.TrucoDescription;

import clusterModelo.SaldoModelo;
import decisions.DecisionExtraClusterEnvido;

public class DecisionExtraClusterProbabilidadeSucessoEnvido implements DecisionExtraClusterEnvido {
	AcaoFeitasIntraClusterEnvido decisaoIntraCluster;
	ProbabilidadeChance probabilidadeChance = new ProbabilidadeChance();

	public boolean chamarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {

		try {
			int grupoComMaiorProbabilidadeDeVitoriaEnvido = probabilidadeChance
					.retornaClusterComMaiorChanceDeVitoriaEnvido(hashEnvido, hashEnvido.keySet());
			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = hashEnvido
					.get(grupoComMaiorProbabilidadeDeVitoriaEnvido);
			//////System.out.println("grupo com maior chance de vitória chamar envido extra cluster: "+ grupoComMaiorProbabilidadeDeVitoriaEnvido);
			
			return decisaoIntraCluster.chamarEnvidoIntraCluster(listaDeCasosClusterComMaiorChanceSucesso,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			//////////System.out.println("caiu no catch do chamarEnvido ExtraCluster chance e saldo");
			e.printStackTrace();
			return false;
		}
	}

	public boolean chamarRealEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			int grupoComMaiorProbabilidadeDeVitoriaEnvido = probabilidadeChance
					.retornaClusterComMaiorChanceDeVitoriaEnvido(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = hashEnvido
					.get(grupoComMaiorProbabilidadeDeVitoriaEnvido);

			//////System.out.println("grupo com maior chance de vitória chamar falta envido extra cluster: "+grupoComMaiorProbabilidadeDeVitoriaEnvido);
			return decisaoIntraCluster.chamarRealEnvidoIntraCluster(listaDeCasosClusterComMaiorChanceSucesso,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean chamarFaltaEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			int grupoComMaiorProbabilidadeDeVitoriaEnvido = probabilidadeChance
					.retornaClusterComMaiorChanceDeVitoriaEnvido(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = hashEnvido
					.get(grupoComMaiorProbabilidadeDeVitoriaEnvido);

			
			return decisaoIntraCluster.chamarFaltaEnvidoIntraCluster(listaDeCasosClusterComMaiorChanceSucesso,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean aceitarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			int grupoComMaiorProbabilidadeDeVitoriaEnvido = probabilidadeChance
					.retornaClusterComMaiorChanceDeVitoriaEnvido(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = hashEnvido
					.get(grupoComMaiorProbabilidadeDeVitoriaEnvido);
			return decisaoIntraCluster.aceitarEnvidoIntraCluster(listaDeCasosClusterComMaiorChanceSucesso,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			//////////System.out.println("caiu no catch do chamarEnvido ExtraCluster chance e saldo");
		e.printStackTrace();
		
			return false;
		}
	}

	@Override
	public boolean aceitarRealEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			int grupoComMaiorProbabilidadeDeVitoriaEnvido = probabilidadeChance
					.retornaClusterComMaiorChanceDeVitoriaEnvido(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = hashEnvido
					.get(grupoComMaiorProbabilidadeDeVitoriaEnvido);

			return decisaoIntraCluster.aceitarRealEnvidoIntraCluster(listaDeCasosClusterComMaiorChanceSucesso,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean aceitarFaltaEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			int grupoComMaiorProbabilidadeDeVitoriaEnvido = probabilidadeChance
					.retornaClusterComMaiorChanceDeVitoriaEnvido(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = hashEnvido
					.get(grupoComMaiorProbabilidadeDeVitoriaEnvido);
			
			//////System.out.println("grupo aceitar falta envido: "+grupoComMaiorProbabilidadeDeVitoriaEnvido);
			
			return decisaoIntraCluster.aceitarFaltaEnvidoIntraCluster(listaDeCasosClusterComMaiorChanceSucesso,
					gamestate.getJogadorMao());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void setDecisionIntraCluster(AcaoFeitasIntraClusterEnvido decisaoIntraCluster) {
		this.decisaoIntraCluster = decisaoIntraCluster;

	}

}
