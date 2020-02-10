package DecisionsIntraClusterProbabilidadeSorteio;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import ajudaCluster.MaioriaAceitarModelo;
import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;
import uteisRetornaQuantitativosSaldosOuProbabilidade.QuantitativosEnvido;

public class AcaoFeitasIntraClusterEnvidoProbabilidadeSorteio implements AcaoFeitasIntraClusterEnvido {
	QuantitativosEnvido quantitativosEnvido = new QuantitativosEnvido();


	@Override
	public boolean chamarEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			MaioriaModeloSelecao maioriaModelo = quantitativosEnvido
					.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaChamarEnvido(listaIntraClusterQuemEnvido);

			return selecaoJogada(maioriaModelo);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			MaioriaAceitarModelo maioriaModelo = quantitativosEnvido
					.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarEnvido(listaIntraClusterQuemEnvido);

			return selecaoAceitarJogada(maioriaModelo);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean chamarRealEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			MaioriaModeloSelecao maioriaModelo = quantitativosEnvido
					.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaRealenvido(listaIntraClusterQuemEnvido);

			return selecaoJogada(maioriaModelo);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarRealEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			MaioriaAceitarModelo maioriaModelo = quantitativosEnvido
					.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarRealEnvido(
							listaIntraClusterQuemEnvido);

			return selecaoAceitarJogada(maioriaModelo);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean chamarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			MaioriaModeloSelecao maioriaModelo = quantitativosEnvido
					.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaFaltaEnvido(listaIntraClusterQuemEnvido);

			return selecaoJogada(maioriaModelo);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
		MaioriaAceitarModelo maioriaModelo = quantitativosEnvido
				.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarFaltaEnvido(
						listaIntraClusterQuemEnvido);

		return selecaoAceitarJogada(maioriaModelo);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean selecaoJogada(MaioriaModeloSelecao maioriaModelo) {

		int quantidadeChamou = maioriaModelo.getChamouEganhou() + maioriaModelo.getChamouEperdeu();
		int quantidadeNaoChamou = maioriaModelo.getNaoChamou();
		List<String> listaDeCartelasParaOsorteio = new ArrayList<String>();
		for (int i = 0; i < quantidadeChamou; i++) {
			listaDeCartelasParaOsorteio.add("chamou");
		}
		for (int i = 0; i < quantidadeNaoChamou; i++) {
			listaDeCartelasParaOsorteio.add("naochamou");
		}

		java.util.Collections.shuffle(listaDeCartelasParaOsorteio);
		Random random = new Random();
		int randomRetornado = random.nextInt((quantidadeNaoChamou + quantidadeChamou)-1);
		String retorno = listaDeCartelasParaOsorteio.get(randomRetornado);

		if (retorno.equalsIgnoreCase("chamou") )
			return true;

		else
			return false;
	}

	public boolean selecaoAceitarJogada(MaioriaAceitarModelo maioriaModelo) {
		int quantidadeAceitou = maioriaModelo.getContadorAceitouEganhou() + maioriaModelo.getContadorAceitouEperdeu();
		int quantidadeNegou = maioriaModelo.getContadorNegou();

		List<String> listaDeCartelasParaOsorteio = new ArrayList<String>();
		for (int i = 0; i < quantidadeAceitou; i++) {
			listaDeCartelasParaOsorteio.add("aceitou");
		}
		for (int i = 0; i < quantidadeNegou; i++) {
			listaDeCartelasParaOsorteio.add("negou");
		}

		java.util.Collections.shuffle(listaDeCartelasParaOsorteio);
		Random random = new Random();
		int randomRetornado = random.nextInt((quantidadeAceitou + quantidadeNegou) - 1);
		String retorno = listaDeCartelasParaOsorteio.get(randomRetornado);

		if (retorno.equalsIgnoreCase("aceitou"))
			return true;

		else
			return false;
	}

}
