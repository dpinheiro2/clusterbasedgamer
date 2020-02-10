package decisionsExtraClusterChanceSaldo;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import UtilSaldo.Saldos;
import cbr.cbrDescriptions.TrucoDescription;


import clusterModelo.SaldoModelo;
import decisions.DecisionExtraClusterEnvido;

public class DecisionExtraClusterChanceDeSucessoEsaldoEnvido implements DecisionExtraClusterEnvido {
	AcaoFeitasIntraClusterEnvido decisaoIntraCluster;
	Saldos retornaSaldo = new Saldos();
	
	public List<TrucoDescription> retornaListaComMaiorChancedeSucesso(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvidoAtual, SaldoModelo saldoModeloAtual) {

		List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = new ArrayList<TrucoDescription>();

		listaDeCasosClusterComMaiorChanceSucesso = hashEnvidoAtual.get(saldoModeloAtual.getClusterComMaiorSaldo());

		return listaDeCasosClusterComMaiorChanceSucesso;

	}

	public boolean chamarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {

		try {
			SaldoModelo saldoModeloAtual = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(hashEnvido, hashEnvido.keySet());
            List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = retornaListaComMaiorChancedeSucesso(
					gamestate, rodada, hashEnvido, saldoModeloAtual);
			

			return decisaoIntraCluster.chamarEnvidoIntraCluster(listaDeCasosClusterComMaiorChanceSucesso,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			////////System.out.println("caiu no catch do chamarEnvido ExtraCluster chance e saldo");
			e.printStackTrace();
			return false;
		}
	}

	public boolean chamarRealEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			SaldoModelo saldoModeloAtual =  retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(hashEnvido, hashEnvido.keySet());
			
			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = retornaListaComMaiorChancedeSucesso(
					gamestate, rodada, hashEnvido, saldoModeloAtual);

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
			SaldoModelo saldoModeloEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(hashEnvido, hashEnvido.keySet());
			
			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = retornaListaComMaiorChancedeSucesso(
					gamestate, rodada, hashEnvido, saldoModeloEnvido);

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
			SaldoModelo saldoModeloEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(hashEnvido, hashEnvido.keySet());
			
			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = retornaListaComMaiorChancedeSucesso(
					gamestate, rodada, hashEnvido, saldoModeloEnvido);

			return decisaoIntraCluster.aceitarEnvidoIntraCluster(listaDeCasosClusterComMaiorChanceSucesso,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			e.printStackTrace();
			////////System.out.println("caiu no catch do chamarEnvido ExtraCluster chance e saldo");
			return false;
		}
	}

	@Override
	public boolean aceitarRealEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			SaldoModelo saldoModeloEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(hashEnvido, hashEnvido.keySet());
			
			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = retornaListaComMaiorChancedeSucesso(
					gamestate, rodada, hashEnvido, saldoModeloEnvido);

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
			SaldoModelo saldoModeloEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(hashEnvido, hashEnvido.keySet());
			
			List<TrucoDescription> listaDeCasosClusterComMaiorChanceSucesso = retornaListaComMaiorChancedeSucesso(
					gamestate, rodada, hashEnvido, saldoModeloEnvido);

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
