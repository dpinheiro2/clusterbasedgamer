package UtilSaldo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cbr.cbrDescriptions.TrucoDescription;
import clusterModelo.SaldoModelo;

public class Saldos {
	public SaldoModelo retornaAcaoOuGrupoComMaiorSaldoEnvido(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {
		int quantidadeGruposAnalisados = 0;
		Double maiorSaldoEnvido = 0.0;
		int grupoComMaiorSaldo = 0;
		Double quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldo = 0.0;
		Iterator<Integer> iteratorKeys = listaSemRepetir.iterator();
	
		while (iteratorKeys.hasNext()) {
			int k = iteratorKeys.next();
			List<TrucoDescription> casosDoGrupoAtual = hash.get(k);
			double saldoDoGrupoAtualAntesDeNormalizar = 0.0;
			double saldoDoGrupoAtualDepoisDeNormalizar = 0.0;
			
			
		    Iterator iteratorD = casosDoGrupoAtual.iterator();
			while (iteratorD.hasNext()) {
				TrucoDescription d = (TrucoDescription) iteratorD.next();
				// //////System.out.println("id do caso Recuperado " + d.getId() + "JOGADOR MAO " +
				// d.getJogadorMao());

				saldoDoGrupoAtualAntesDeNormalizar += d.getSaldoEnvido();

			}
			Double quantidadeDeCasosNoGrupoAtual = (double) casosDoGrupoAtual.size();
			saldoDoGrupoAtualDepoisDeNormalizar = (double)saldoDoGrupoAtualAntesDeNormalizar / quantidadeDeCasosNoGrupoAtual;
			
			
			if (saldoDoGrupoAtualDepoisDeNormalizar > maiorSaldoEnvido || (saldoDoGrupoAtualDepoisDeNormalizar == maiorSaldoEnvido
					&& quantidadeDeCasosNoGrupoAtual > quantidadeDeCasosNoGrupoAtual)) {
				maiorSaldoEnvido = saldoDoGrupoAtualDepoisDeNormalizar;
                grupoComMaiorSaldo =k;
				quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldo = quantidadeDeCasosNoGrupoAtual;
			} else if (quantidadeGruposAnalisados == 0) {
				maiorSaldoEnvido = saldoDoGrupoAtualDepoisDeNormalizar;

				grupoComMaiorSaldo = k;
				quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldo = quantidadeDeCasosNoGrupoAtual;
				quantidadeGruposAnalisados = 1;
			}
		}
		
		

		return new SaldoModelo(grupoComMaiorSaldo, maiorSaldoEnvido, quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldo);
	}

	public SaldoModelo retornaAcaoOuGrupoComMaiorSaldoTruco(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {
		////System.out.println("tamanho da lista analisada: "+ listaSemRepetir.size());
		
		int quantidadeGruposAnalisados =0;
		Double maiorSaldoTruco = 0.0;
		int grupoComMaiorSaldoTruco = 0;
		Double quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldoTruco = 0.0;
		Iterator<Integer> iteratorsKeys = listaSemRepetir.iterator();
		while (iteratorsKeys.hasNext()) {
			int k = iteratorsKeys.next();
			////System.out.println("primeiro k analisado");
			List<TrucoDescription> casosDoGrupoAtual = hash.get(k);
			double saldoDoGrupoAtualAntesDeNormalizar = 0.0;
			double saldoDoGrupoAtualDepoisDeNormalizar = 0.0;
			
			Iterator<TrucoDescription> iteratorD = casosDoGrupoAtual.iterator();
			while (iteratorD.hasNext()) {
				TrucoDescription d = iteratorD.next();
				saldoDoGrupoAtualAntesDeNormalizar += d.getSaldoTruco();

			}
			
			double quantidadeDeCasosNoGrupoAtual = casosDoGrupoAtual.size();
			saldoDoGrupoAtualDepoisDeNormalizar = (double)saldoDoGrupoAtualAntesDeNormalizar / quantidadeDeCasosNoGrupoAtual;
			
			if (saldoDoGrupoAtualDepoisDeNormalizar > maiorSaldoTruco || (saldoDoGrupoAtualDepoisDeNormalizar == maiorSaldoTruco
					&& quantidadeDeCasosNoGrupoAtual > quantidadeDeCasosNoGrupoAtual)) {
				maiorSaldoTruco = saldoDoGrupoAtualDepoisDeNormalizar;
				grupoComMaiorSaldoTruco = k;
				quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldoTruco = quantidadeDeCasosNoGrupoAtual;
			}
			else if (quantidadeGruposAnalisados == 0) {
				maiorSaldoTruco = saldoDoGrupoAtualDepoisDeNormalizar;
				grupoComMaiorSaldoTruco = k;
				quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldoTruco = quantidadeDeCasosNoGrupoAtual;
				quantidadeGruposAnalisados=1;
			}
		}
		////System.out.println("grupo retornado: saldo "+ grupoComMaiorSaldoTruco);
		////System.out.println("grupo retornado: saldo "+ grupoComMaiorSaldoTruco);
		////System.out.println("quantidade de casos no grupo com maior saldo: "+ quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldoTruco);
		return new SaldoModelo(grupoComMaiorSaldoTruco, maiorSaldoTruco,
				quantidadeDeCasosRecuperadosNoGrupoComMaiorSaldoTruco);
	}
		

}
