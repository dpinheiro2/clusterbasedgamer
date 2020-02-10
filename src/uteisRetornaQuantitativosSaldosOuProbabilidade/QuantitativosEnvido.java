package uteisRetornaQuantitativosSaldosOuProbabilidade;


import java.util.Iterator;
import java.util.List;

import ajudaCluster.MaioriaAceitarModelo;
import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;

public class QuantitativosEnvido {

	public MaioriaModeloSelecao retornaMaioriaModeloChamarGenerico(List<TrucoDescription> listaCasos,
			String tipoDeChamada) {
		MaioriaModeloSelecao maioriaModeloSelecao = new MaioriaModeloSelecao();
		Iterator<TrucoDescription> listaIterator = listaCasos.iterator();

		// caso nenhum tenha chamado truco cria um grupo com o número 30
		while (listaIterator.hasNext()) {
			TrucoDescription c = listaIterator.next();

			int quemChamou = 0;
			int quemChamouMaior1 =0;
			int quemChamouMaior2 =0;
			
			int quemNegou = 0;
			// aqui recebe o atributo objetivo de acordo com o tipo de chamada
			if (tipoDeChamada.equalsIgnoreCase("envido")) {
				quemChamou = c.getQuemPediuEnvido();
				quemChamouMaior1 = c.getQuemPediuRealEnvido();
				quemChamouMaior2 = c.getQuemPediuFaltaEnvido();
				
				quemNegou = c.getQuemNegouEnvido();
			} else if (tipoDeChamada.equalsIgnoreCase("realenvido")) {
				quemChamou = c.getQuemPediuRealEnvido();
				quemChamouMaior1 = c.getQuemPediuFaltaEnvido();
				
				quemNegou = c.getQuemNegouEnvido();
			} else if (tipoDeChamada.equalsIgnoreCase("faltaenvido")) {
				quemChamou = c.getQuemPediuFaltaEnvido();
				quemNegou = c.getQuemNegouEnvido();
			}

			if ((((quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0) || (quemChamou == 2 && quemNegou == 1)))) {
				maioriaModeloSelecao.setNaoChamou(maioriaModeloSelecao.getNaoChamou() + 1);
			} else if((((quemChamou !=0 || quemChamouMaior1 !=0 || quemChamouMaior2 != 0 ) || (quemChamou == 2  && quemNegou != 1)))) {
				if (c.getQuemGanhouEnvido().equals(1)) {
					maioriaModeloSelecao.setChamouEganhou(maioriaModeloSelecao.getChamouEganhou() + 1);
				} else if (c.getQuemGanhouEnvido().equals(2) ) {
					maioriaModeloSelecao.setChamouEperdeu(maioriaModeloSelecao.getChamouEperdeu() + 1);
				}
			}
		}

		return maioriaModeloSelecao;
	}

	public MaioriaModeloSelecao retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaChamarEnvido(
			List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar) {
		MaioriaModeloSelecao maioriaModeloSelecao = retornaMaioriaModeloChamarGenerico(
				listaMaioriaCasosDoGrupoMaisSimilar, "envido");
		return maioriaModeloSelecao;
	}

	public MaioriaModeloSelecao retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaRealenvido(
			List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar) {
		MaioriaModeloSelecao maioriaModeloSelecao = retornaMaioriaModeloChamarGenerico(
				listaMaioriaCasosDoGrupoMaisSimilar, "realenvido");
		return maioriaModeloSelecao;
	}

	public MaioriaModeloSelecao retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaFaltaEnvido(
			List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar) {
		MaioriaModeloSelecao maioriaModeloSelecao = retornaMaioriaModeloChamarGenerico(
				listaMaioriaCasosDoGrupoMaisSimilar, "valequatro");
		return maioriaModeloSelecao;
	}

//accept

	public MaioriaAceitarModelo retornaAceitarMaioriaModeloGenerico(List<TrucoDescription> listaCasos,
			String tipoAceite) {
		MaioriaAceitarModelo maioriaAceitarModelo = new MaioriaAceitarModelo();
		Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
		while (iteratorCasos.hasNext()) {
			TrucoDescription c = iteratorCasos.next();
			
			int quemChamou = 0;
			int quemChamouMaior1 =0;
			int quemChamouMaior2 =0;
			
			int quemNegou = 0;

				// aqui recebe o atributo objetivo de acordo com o tipo de chamada
				if (tipoAceite.equalsIgnoreCase("envido")) {
					quemChamou = c.getQuemPediuEnvido();
					quemChamouMaior1 = c.getQuemPediuRealEnvido();
					quemChamouMaior2 = c.getQuemPediuFaltaEnvido();
					
					quemNegou = c.getQuemNegouEnvido();
				} else if (tipoAceite.equalsIgnoreCase("realenvido")) {
					quemChamou = c.getQuemPediuRealEnvido();
					quemChamouMaior1 = c.getQuemPediuFaltaEnvido();
					quemNegou = c.getQuemNegouEnvido();
				} else if (tipoAceite.equalsIgnoreCase("faltaenvido")) {
					quemChamou = c.getQuemPediuFaltaEnvido();
					quemNegou = c.getQuemNegouEnvido();
				}

				if ((((quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0) || (quemChamou == 2 && quemNegou == 1)))) {
					maioriaAceitarModelo.setContadorNegou(maioriaAceitarModelo.getContadorNegou() + 1);
				} else if ((((quemChamou !=0 || quemChamouMaior1 !=0 || quemChamouMaior2 != 0 ) || (quemChamou == 2  && quemNegou != 1)))) {
					if (c.getQuemGanhouEnvido() == 1)
						maioriaAceitarModelo
								.setContadorAceitouEganhou(maioriaAceitarModelo.getContadorAceitouEganhou() + 1);
					if (c.getQuemGanhouEnvido() == 2)
						maioriaAceitarModelo
								.setContadorAceitouEperdeu(maioriaAceitarModelo.getContadorAceitouEperdeu() + 1);
				}

			}
		

		return maioriaAceitarModelo;
	}
	
	public MaioriaAceitarModelo retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarEnvido(
			List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar) {
		MaioriaAceitarModelo maioriaAceitarModelo  = retornaAceitarMaioriaModeloGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "envido");
		return maioriaAceitarModelo;
	}

	public MaioriaAceitarModelo retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarRealEnvido(
			List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar) {
		MaioriaAceitarModelo maioriaAceitarModelo  = retornaAceitarMaioriaModeloGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "realenvido");
		return maioriaAceitarModelo;
		}

	public MaioriaAceitarModelo retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarFaltaEnvido(
			List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar) {
		MaioriaAceitarModelo maioriaAceitarModelo  = retornaAceitarMaioriaModeloGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "faltaenvido");
		return maioriaAceitarModelo;
	}
	}
