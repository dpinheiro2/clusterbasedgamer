 package ajudaCluster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import CbrQuerys.CBRCentroides;
import CentroidesModelo.CaseBasesModelo;

import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.CentroidesGrupoIndexacaoDescription;
import cbr.cbrDescriptions.CentroidesGrupoIndexacaoPontosDescription;
import cbr.cbrDescriptions.CentroidesPrimeiraCartaRoboMaoDescription;
import cbr.cbrDescriptions.CentroidesPrimeiraCartaRoboPeDescription;

import cbr.cbrDescriptions.CentroidesQuemGanhouEnvidoDescription;
import cbr.cbrDescriptions.CentroidesQuemTrucoDescription;
import cbr.cbrDescriptions.CentroidesSegundaCartaRoboGanhouAprimeiraDescription;
import cbr.cbrDescriptions.CentroidesSegundaCartaRoboPerdeuAprimeiraDescription;
import cbr.cbrDescriptions.CentroidesTerceiraCartaRoboGanhouAsegundaDescription;
import cbr.cbrDescriptions.CentroidesTerceiraCartaRoboPerdeuAsegundaDescription;
import cbr.cbrDescriptions.TrucoDescription;
import jcolibri.cbrcore.CBRCase;

import jcolibri.method.retrieve.RetrievalResult;

public class CalcularCentroides {
	CaseBasesModelo casesBases;

	CbrQuerys.CBRCentroides ck;

	CbrModular quemChamou;

	public CalcularCentroides(CaseBasesModelo casesBases, CbrModular cbr) {
		this.casesBases = casesBases;
		this.quemChamou = cbr;
		ck = new CBRCentroides();
	}
	/*
	 * Pode ser bem otimizado o Calculo de indexaÃ§Ã£o, Ãºnica coisa que precisa ser
	 * feito Ã© recuperar o centroide atual e somar com o valor atual, nÃ£o precisa
	 * percorrer toda a lista como estÃ¡ sendo feito. Para atribuir o novo Centroide
	 * 
	 */

	// Recalcula Centroide Indexacao
	public void recalculaCentroidesIndexacao(TrucoDescription newCase) {
		Collection<CBRCase> jogadaIndexacao = casesBases.get_caseBaseMaos();

		jogadaIndexacao = quemChamou.retornaApenasCasosUteisParaCartaOuTruco(jogadaIndexacao);

		Collection<CBRCase> gruposIndexacao = casesBases.get_caseBaseCentroidesGrupoIndexacao();
		List<CBRCase> listOfCasesCentroide = converteCollectionToList(gruposIndexacao);

		// Iterator iteratorCentroide = gruposIndexacao.iterator();
		for (int i = 0; i < listOfCasesCentroide.size(); i++) {
			CBRCase actualRecuperado = (CBRCase) listOfCasesCentroide.get(i);
			CentroidesGrupoIndexacaoDescription centroideAtualRecuperado = (CentroidesGrupoIndexacaoDescription) actualRecuperado
					.getDescription();
			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if (newCase.getClusteringindexacao().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusteringindexacao(centroideAtualRecuperado.getGrupo());
				Collection<RetrievalResult> casosGrupoIndexacao = quemChamou.getBestResultCluster(jogadaIndexacao,
						trucoDescriptionConsulta, 12);

				Iterator iterator = casosGrupoIndexacao.iterator();
				while (iterator.hasNext()) {
					RetrievalResult retrievalCase = (RetrievalResult) iterator.next();
					CBRCase cbrCase = (CBRCase) retrievalCase.get_case();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) cbrCase.getDescription());
				}
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCentroidecartaaltarobomao() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCentroidecartamediarobomao() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCentroidecartabaixarobomao() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				CentroidesGrupoIndexacaoDescription centroideAtual = new CentroidesGrupoIndexacaoDescription();
				centroideAtual.setCentroidecartaaltarobomao((((double) (newCase.getCartaAltaRoboClustering())
						+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size()));
				centroideAtual.setCentroidecartamediarobomao((((double) (newCase.getCartaMediaRoboClustering())
						+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size()));
				centroideAtual.setCentroidecartabaixarobomao((((double) (newCase.getCartaBaixaRoboClustering())
						+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size()));

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);
				
				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(actualRecuperado);

				quemChamou.learnCasesIndexacao(tempCase, actualRecuperado);
			}
		}
	}

	public void recalculaCentroidesIndexacaoPontos(TrucoDescription newCase) {
		Collection<CBRCase> pontosIndexacaoEnvido = casesBases.get_caseBaseMaos();
		pontosIndexacaoEnvido = quemChamou.retornaApenasCasosUteisParaIndexacaoPontos(pontosIndexacaoEnvido);

		Collection<CBRCase> pontosIndexacao = casesBases.get_caseBaseCentroidesGrupoIndexacaoPontos();
		//List<CBRCase> listaPontosIndexacao = converteCollectionToList(pontosIndexacao);
		TrucoDescription trucoDescriptionConsulta = new TrucoDescription(); 
		CentroidesGrupoIndexacaoPontosDescription centroideAtualRecuperado = null;
		CBRCase actualRecuperado = null;
		
		Iterator iteratorCentroides = pontosIndexacao.iterator();
		 
		 while (iteratorCentroides.hasNext()) {
			 actualRecuperado = (CBRCase) iteratorCentroides.next();
			centroideAtualRecuperado = (CentroidesGrupoIndexacaoPontosDescription) actualRecuperado
					.getDescription();
			
			if (newCase.getClusteringIndexacaoPontos().equals(centroideAtualRecuperado.getGrupo())) {
				// aqui variaveis de soma utilizadas para calcular

				
				trucoDescriptionConsulta.setClusteringIndexacaoPontos(centroideAtualRecuperado.getGrupo());
				
				break;
				
			}
		}
		Collection<RetrievalResult> casosGrupoMaisSimilarConsultaPrimeiraCartaRoboMao = quemChamou
				.getBestResultCluster(pontosIndexacaoEnvido, trucoDescriptionConsulta, 13);
		List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
				Iterator iteratorCasosDoGrupo = casosGrupoMaisSimilarConsultaPrimeiraCartaRoboMao.iterator();
				while (iteratorCasosDoGrupo.hasNext()) {
					RetrievalResult retrieval = (RetrievalResult) iteratorCasosDoGrupo.next();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) retrieval.get_case().getDescription());
				}
				// listSize - 1 para nÃ£o contar o caso atual
				Double somaPontosEnvidoSemNormalizar = centroideAtualRecuperado.getCentroidepontosenvidorobo()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				CentroidesGrupoIndexacaoPontosDescription centroideAtual = new CentroidesGrupoIndexacaoPontosDescription();
				centroideAtual.setCentroidepontosenvidorobo(
						(((double) newCase.getPontosEnvidoRobo()) + somaPontosEnvidoSemNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);
				
				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(actualRecuperado);
				quemChamou.learnCasesIndexacaoPontos(tempCase, actualRecuperado);
			
			
			
		}

	

	public List<CBRCase> converteCollectionToList(Collection<CBRCase> collectionReceived) {
		Iterator<CBRCase> it = collectionReceived.iterator();
		List<CBRCase> lista = new ArrayList<CBRCase>();
		while (it.hasNext()) {
			lista.add(it.next());
		}

		return lista;
	}
	

//PRIMEIRA CARTA ROBO MÃƒO
	public void recalculaCentroidesPrimeiraCartaRoboMao(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisCartaFromMaos = casesBases.get_caseBaseMaos();
		casesUteisCartaFromMaos = quemChamou.retornaApenasCasosUteisParaCarta(casesUteisCartaFromMaos);

		Collection<CBRCase> casesCentroidesRoboMao = casesBases.get_caseBaseCentroidePrimeiraCartaRoboMao();
		List<CBRCase> listaOfCentroidesRoboMao = converteCollectionToList(casesCentroidesRoboMao);

		// Iterator iteratorTodosOsCasosRoboMao = casesCentroidesRoboMao.iterator();
		for (int i = 0; i < listaOfCentroidesRoboMao.size(); i++) {
			CBRCase actualRecuperado = (CBRCase) listaOfCentroidesRoboMao.get(i);
			CentroidesPrimeiraCartaRoboMaoDescription centroideAtualRecuperado = (CentroidesPrimeiraCartaRoboMaoDescription) actualRecuperado
					.getDescription();
			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if (newCase.getClusterPrimeiraCartaAgenteMao().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterPrimeiraCartaAgenteMao(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarConsultaPrimeiraCartaRoboMao = quemChamou
						.getBestResultCluster(casesUteisCartaFromMaos, trucoDescriptionConsulta, 7);
				Iterator iteratorCasosGupoMaisSimilarPrimeiraCartaRoboMao = casosGrupoMaisSimilarConsultaPrimeiraCartaRoboMao
						.iterator();
				while (iteratorCasosGupoMaisSimilarPrimeiraCartaRoboMao.hasNext()) {
					RetrievalResult actualCases = (RetrievalResult) iteratorCasosGupoMaisSimilarPrimeiraCartaRoboMao
							.next();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) actualCases.get_case().getDescription());
				}
				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado.getCartaAltaRoboClustering()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaMediaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaBaixaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalPrimeiraCartaRoboSemNormalizar = centroideAtualRecuperado.getPrimeiraCartaRoboClustering()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				CentroidesPrimeiraCartaRoboMaoDescription centroideAtual = new CentroidesPrimeiraCartaRoboMaoDescription();
				centroideAtual.setPrimeiraCartaRoboClustering(
						(((double) newCase.getPrimeiraCartaRoboClustering()) + valorTotalPrimeiraCartaRoboSemNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				centroideAtual.setCartaAltaRoboClustering((((double) newCase.getCartaAltaRoboClustering())
						+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaMediaRoboClustering((((double) newCase.getCartaMediaRoboClustering())
						+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaBaixaRoboClustering((((double) newCase.getCartaBaixaRoboClustering())
						+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(actualRecuperado);
				quemChamou.learnCasesCentroidePrimeiraCartaRoboMao(tempCase, actualRecuperado);
			}
		}

	}

	public void recalculaCentroidesPrimeiraCartaRoboPe(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisCartaFromMaos = casesBases.get_caseBaseMaos();
		casesUteisCartaFromMaos = quemChamou.retornaApenasCasosUteisParaCarta(casesUteisCartaFromMaos);

		Collection<CBRCase> todosOsCasosRoboPe = casesBases.get_caseBaseCentroidePrimeiraCartaRoboPe();
		List<CBRCase> listaTodosOsCasosRoboPe = converteCollectionToList(todosOsCasosRoboPe);
		// Iterator iteratorTodosOsCasosRoboPeIterator = todosOsCasosRoboPe.iterator();
		for (int i = 0; i < listaTodosOsCasosRoboPe.size(); i++) {
			CBRCase centroideAtualCbrCaseRecuperado = listaTodosOsCasosRoboPe.get(i);
			CentroidesPrimeiraCartaRoboPeDescription centroideAtualRecuperado = (CentroidesPrimeiraCartaRoboPeDescription) centroideAtualCbrCaseRecuperado
					.getDescription();
			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if (newCase.getClusterPrimeiraCartaAgentePe().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterPrimeiraCartaAgentePe(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarConsultaPrimeiraCartaRoboPe = quemChamou
						.getBestResultCluster(
								casesUteisCartaFromMaos = quemChamou
										.retornaApenasCasosUteisParaCarta(casesUteisCartaFromMaos),
								trucoDescriptionConsulta, 7);
				Iterator iteratorTodosOsCasosGrupoPrimeiraCartaRoboPe = casosGrupoMaisSimilarConsultaPrimeiraCartaRoboPe
						.iterator();
				while (iteratorTodosOsCasosGrupoPrimeiraCartaRoboPe.hasNext()) {
					RetrievalResult primeiraCartaRoboPe = (RetrievalResult) iteratorTodosOsCasosGrupoPrimeiraCartaRoboPe
							.next();
					listaCasosRecuperadosDoGrupoAtual
							.add((TrucoDescription) primeiraCartaRoboPe.get_case().getDescription());
				}

				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado.getCartaAltaRoboClustering()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaMediaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaBaixaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalPrimeiraCartaRoboSemNormalizar = centroideAtualRecuperado.getPrimeiraCartaRoboClustering()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalPrimeiraCartaHumanoSemNormalizar = centroideAtualRecuperado.getPrimeiraCartaHumanoClustering()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				CentroidesPrimeiraCartaRoboPeDescription centroideAtual = new CentroidesPrimeiraCartaRoboPeDescription();
				centroideAtual.setPrimeiraCartaRoboClustering(
						(((double) newCase.getPrimeiraCartaRoboClustering()) + valorTotalPrimeiraCartaRoboSemNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setPrimeiraCartaHumanoClustering((((double) newCase.getPrimeiraCartaHumanoClustering())
						+ valorTotalPrimeiraCartaHumanoSemNormalizar) / listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaAltaRoboClustering((((double) newCase.getCartaAltaRoboClustering())
						+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaMediaRoboClustering((((double) newCase.getCartaMediaRoboClustering())
						+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaBaixaRoboClustering((((double) newCase.getCartaBaixaRoboClustering())
						+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(centroideAtualCbrCaseRecuperado);
				quemChamou.learnCasesCentroidePrimeiraCartaRoboPe(tempCase, centroideAtualCbrCaseRecuperado);
			}
		}

	}

	public void recalculaCentroidesSegundaCartaRoboGanhouAprimeira(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisCartaFromMaos = casesBases.get_caseBaseMaos();
		casesUteisCartaFromMaos = quemChamou.retornaApenasCasosUteisParaCarta(casesUteisCartaFromMaos);

		Collection<CBRCase> todosOsCasosSegundaCartaRoboGanhouAprimeira = casesBases
				.get_caseBaseCentroideSegundaCartaRoboGanhouAprimeira();
		List<CBRCase> listaTodosOscasosSegundaCartaRoboGanhouAprimeira = converteCollectionToList(
				todosOsCasosSegundaCartaRoboGanhouAprimeira);
		// Iterator iteratorTodosOsCasosSegundaCartaRoboGanhouAprimeira =
		// todosOsCasosSegundaCartaRoboGanhouAprimeira.iterator();

		for (int i = 0; i < listaTodosOscasosSegundaCartaRoboGanhouAprimeira.size(); i++) {
			CBRCase cbrCaseAtualRecuperado = (CBRCase) listaTodosOscasosSegundaCartaRoboGanhouAprimeira.get(i);
			CentroidesSegundaCartaRoboGanhouAprimeiraDescription centroideAtualRecuperado = (CentroidesSegundaCartaRoboGanhouAprimeiraDescription) cbrCaseAtualRecuperado
					.getDescription();
			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			
			if (newCase.getClusterSegundaCartaAgenteGanhouAprimeira().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterSegundaCartaAgenteGanhouAPrimeira(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarSegundaCartaRoboGanhouAprimeira = quemChamou
						.getBestResultCluster(casesUteisCartaFromMaos, trucoDescriptionConsulta, 7);

				Iterator iteratorCasosDoGrupoMaisSimilarSegundaCartaRoboGanhouAprimeira = casosGrupoMaisSimilarSegundaCartaRoboGanhouAprimeira
						.iterator();
				while (iteratorCasosDoGrupoMaisSimilarSegundaCartaRoboGanhouAprimeira.hasNext()) {
					RetrievalResult casosMaisSimilarSegundaCarta = (RetrievalResult) iteratorCasosDoGrupoMaisSimilarSegundaCartaRoboGanhouAprimeira
							.next();
					listaCasosRecuperadosDoGrupoAtual
							.add((TrucoDescription) casosMaisSimilarSegundaCarta.get_case().getDescription());
				}

				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalSegundaCartaRoboSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getSegundaCartaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				
				CentroidesSegundaCartaRoboGanhouAprimeiraDescription centroideAtual = new CentroidesSegundaCartaRoboGanhouAprimeiraDescription();
				centroideAtual.setSegundaCartaRoboClustering((((double) newCase.getSegundaCartaRoboClustering())
						+ valorTotalSegundaCartaRoboSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(cbrCaseAtualRecuperado);
				quemChamou.learnCasesCentroideSegundaCartaRoboGanhouAprimeira(tempCase, cbrCaseAtualRecuperado);
			}
		}

	}

	public void recalculaCentroidesSegundaCartaRoboPerdeuAprimeira(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisCartaFromMaos = casesBases.get_caseBaseMaos();
		casesUteisCartaFromMaos = quemChamou.retornaApenasCasosUteisParaCarta(casesUteisCartaFromMaos);

		Collection<CBRCase> todosOsCasosSegundaCartaRoboPerdeuAprimeira = casesBases
				.get_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira();
		List<CBRCase> listaTodosOsCasosPerdeuAprimeira = converteCollectionToList(
				todosOsCasosSegundaCartaRoboPerdeuAprimeira);
		// Iterator iteratorCasosSegundaCartaRoboPerdeuAprimeira =
		// todosOsCasosSegundaCartaRoboPerdeuAprimeira.iterator();
		for (int i = 0; i < listaTodosOsCasosPerdeuAprimeira.size(); i++) {
			CBRCase cbrCaseRecuperado = (CBRCase) listaTodosOsCasosPerdeuAprimeira.get(i);
			CentroidesSegundaCartaRoboPerdeuAprimeiraDescription centroideAtualRecuperado = (CentroidesSegundaCartaRoboPerdeuAprimeiraDescription) cbrCaseRecuperado
					.getDescription();
			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			
			if (newCase.getClusterSegundaCartaAgentePerdeuAprimeira().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterSegundaCartaAgentePerdeuAPrimeira(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarSegundaCartaRoboPerdeuAprimeira = quemChamou
						.getBestResultCluster(casesUteisCartaFromMaos, trucoDescriptionConsulta, 7);

				Iterator iteratorCasosMaisSimilarSegundaCarta = casosGrupoMaisSimilarSegundaCartaRoboPerdeuAprimeira
						.iterator();
				while (iteratorCasosMaisSimilarSegundaCarta.hasNext()) {
					RetrievalResult r = (RetrievalResult) iteratorCasosMaisSimilarSegundaCarta.next();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) r.get_case().getDescription());
				}
			
				CentroidesSegundaCartaRoboPerdeuAprimeiraDescription centroideAtual = new CentroidesSegundaCartaRoboPerdeuAprimeiraDescription();
				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalSegundaCartaRoboSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getSegundaCartaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				Double valorTotalSegundaCartaHumanoSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getSegundaCartaHumanoClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				centroideAtual.setSegundaCartaRoboClustering((((double) newCase.getSegundaCartaRoboClustering())
						+ valorTotalSegundaCartaRoboSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());
				centroideAtual.setSegundaCartaHumanoClustering((((double) newCase.getSegundaCartaHumanoClustering())
						+ valorTotalSegundaCartaHumanoSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(cbrCaseRecuperado);
				
				quemChamou.learnCasesCentroideSegundaCartaRoboPerdeuAprimeira(tempCase, cbrCaseRecuperado);

			}
		}

	}

	public void recalculaCentroidesTerceiraCartaRoboGanhouAsegunda(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisCartaFromMaos = casesBases.get_caseBaseMaos();
		casesUteisCartaFromMaos = quemChamou.retornaApenasCasosUteisParaCarta(casesUteisCartaFromMaos);

		Collection<CBRCase> todosOsCasosTerceiraCartaRoboGanhouAsegunda = casesBases
				.get_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda();

		List<CBRCase> listaTodosOsCasosTerceiraCartaGanhouAsegunda = converteCollectionToList(
				todosOsCasosTerceiraCartaRoboGanhouAsegunda);
		// Iterator iteratorTodosTerceiraRoboGanhouAsegunda =
		// todosOsCasosTerceiraCartaRoboGanhouAsegunda.iterator();

		for (int i = 0; i < listaTodosOsCasosTerceiraCartaGanhouAsegunda.size(); i++) {
			CBRCase cbrCaseActualRecuperado = (CBRCase) listaTodosOsCasosTerceiraCartaGanhouAsegunda.get(i);
			CentroidesTerceiraCartaRoboGanhouAsegundaDescription centroideAtualRecuperado = (CentroidesTerceiraCartaRoboGanhouAsegundaDescription) cbrCaseActualRecuperado
					.getDescription();
			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if (newCase.getClusterTerceiraCartaAgenteGanhouAsegunda().equals(centroideAtualRecuperado.getGrupo())) {
				// aqui variaveis de soma utilizadas para calcular
				Double somaTerceiraCartaRoboClustering = 0.0;

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterTerceiraCartaAgenteGanhouAsegunda(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarTerceiraCartaRoboGanhouAsegunda = quemChamou
						.getBestResultCluster(casesUteisCartaFromMaos, trucoDescriptionConsulta, 7);
				Iterator iteratorCasosGrupoMaisSimilarTerceiraGanhouAsegunda = casosGrupoMaisSimilarTerceiraCartaRoboGanhouAsegunda
						.iterator();
				while (iteratorCasosGrupoMaisSimilarTerceiraGanhouAsegunda.hasNext()) {
					RetrievalResult ractual = (RetrievalResult) iteratorCasosGrupoMaisSimilarTerceiraGanhouAsegunda
							.next();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) ractual.get_case().getDescription());
				}

				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalTerceiraCartaRoboSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getTerceiraCartaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				CentroidesTerceiraCartaRoboGanhouAsegundaDescription centroideAtual = new CentroidesTerceiraCartaRoboGanhouAsegundaDescription();
				centroideAtual.setTerceiraCartaRoboClustering((((double) newCase.getTerceiraCartaRoboClustering())
						+ valorTotalTerceiraCartaRoboSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(cbrCaseActualRecuperado);
				quemChamou.learnCasesCentroideTerceiraCartaRoboGanhouAsegunda(tempCase, cbrCaseActualRecuperado);
			}
		}

	}

	public void recalculaCentroidesTerceiraCartaRoboPerdeuAsegunda(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisCartaFromMaos = casesBases.get_caseBaseMaos();
		casesUteisCartaFromMaos = quemChamou.retornaApenasCasosUteisParaCarta(casesUteisCartaFromMaos);

		Collection<CBRCase> todosOsCasosTerceiraCartaRoboPerdeuAsegunda = casesBases
				.get_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda();

		List<CBRCase> listaTodosOsCasosTerceiraCartaRoboPerdeuAsegunda = converteCollectionToList(
				todosOsCasosTerceiraCartaRoboPerdeuAsegunda);
		// Iterator iteratorTodosOsCasosTerceiraCartaRoboPerdeuAsegunda =
		// todosOsCasosTerceiraCartaRoboPerdeuAsegunda.iterator();

		for (int i = 0; i < listaTodosOsCasosTerceiraCartaRoboPerdeuAsegunda.size(); i++) {
			CBRCase actualCbrCaseRecuperado = (CBRCase) listaTodosOsCasosTerceiraCartaRoboPerdeuAsegunda.get(i);
			CentroidesTerceiraCartaRoboPerdeuAsegundaDescription centroideAtualRecuperado = (CentroidesTerceiraCartaRoboPerdeuAsegundaDescription) actualCbrCaseRecuperado
					.getDescription();
			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();

			if (newCase.getClusterTerceiraCartaAgentePerdeuAsegunda().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterTerceiraCartaAgentePerdeuAsegunda(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarTerceiraCartaRoboPerdeuAsegunda = quemChamou
						.getBestResultCluster(casesUteisCartaFromMaos, trucoDescriptionConsulta, 7);
				Iterator iteratorCasosGrupoMaisSimilarTerceiraCartaRoboPerdeuAsegunda = casosGrupoMaisSimilarTerceiraCartaRoboPerdeuAsegunda
						.iterator();
				while (iteratorCasosGrupoMaisSimilarTerceiraCartaRoboPerdeuAsegunda.hasNext()) {
					RetrievalResult retrievalResultCasosDoGrupoAtual = (RetrievalResult) iteratorCasosGrupoMaisSimilarTerceiraCartaRoboPerdeuAsegunda
							.next();
					listaCasosRecuperadosDoGrupoAtual
							.add((TrucoDescription) retrievalResultCasosDoGrupoAtual.get_case().getDescription());
				}
				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalTerceiraCartaRoboSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getTerceiraCartaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalTerceiraCartaHumanoSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getTerceiraCartaHumanoClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				CentroidesTerceiraCartaRoboPerdeuAsegundaDescription centroideAtual = new CentroidesTerceiraCartaRoboPerdeuAsegundaDescription();
				centroideAtual.setTerceiraCartaRoboClustering((((double) newCase.getTerceiraCartaRoboClustering())
						+ valorTotalTerceiraCartaRoboSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setTerceiraCartaHumanoClustering((((double) newCase.getTerceiraCartaHumanoClustering())
						+ valorTotalTerceiraCartaHumanoSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(actualCbrCaseRecuperado);
				quemChamou.learnCasesCentroideTerceiraCartaRoboPerdeuAsegunda(tempCase, actualCbrCaseRecuperado);
			}
		}

	}

	public void recalculaCentroidesQuemTrucoPrimeiraMao(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisTrucoFromMaos = casesBases.get_caseBaseMaos();
		casesUteisTrucoFromMaos = quemChamou.retornaApenasCasosUteisParaTruco(casesUteisTrucoFromMaos);

		Collection<CBRCase> todosOsCasosQuemTruco = casesBases.get_caseBaseCentroideQuemTrucoPrimeiraMao();

		List<CBRCase> listTodosOsCasosQuemTruco = converteCollectionToList(todosOsCasosQuemTruco);

		for (int i = 0; i < listTodosOsCasosQuemTruco.size(); i++) {
			CBRCase actualCaseRecuperado = (CBRCase) listTodosOsCasosQuemTruco.get(i);

			CentroidesQuemTrucoDescription centroideAtualRecuperado = (CentroidesQuemTrucoDescription) actualCaseRecuperado
					.getDescription();

			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();

			if (newCase.getClusterQuemTrucoPrimeiraMao().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterQuemTrucoPrimeiraMao(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarQuemTruco = quemChamou
						.getBestResultCluster(casesUteisTrucoFromMaos, trucoDescriptionConsulta, 14);
				Iterator iteratorRetrievalResultCasosMaisSimilarQuemTruco = casosGrupoMaisSimilarQuemTruco.iterator();
				while (iteratorRetrievalResultCasosMaisSimilarQuemTruco.hasNext()) {
					RetrievalResult r = (RetrievalResult) iteratorRetrievalResultCasosMaisSimilarQuemTruco.next();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) r.get_case().getDescription());
				}
				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalQuemTrucoRetornado = centroideAtualRecuperado.getQuemTruco()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				Double valorTotalQuemRetrucoRetornado = centroideAtualRecuperado.getQuemRetruco()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalQuemValeQuatroRetornado = centroideAtualRecuperado.getQuemValeQuatro()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado.getCartaAltaRoboClustering()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaMediaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaBaixaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				
				CentroidesQuemTrucoDescription centroideAtual = new CentroidesQuemTrucoDescription();
				centroideAtual.setQuemTruco((((double) newCase.getQuemTrucoCluster()) + valorTotalQuemTrucoRetornado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setQuemRetruco((((double) newCase.getQuemRetrucoCluster()) + valorTotalQuemRetrucoRetornado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual
						.setQuemValeQuatro((((double) newCase.getQuemValeQuatroCluster()) + valorTotalQuemValeQuatroRetornado)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				centroideAtual.setCartaAltaRoboClustering((((double) newCase.getCartaAltaRoboClustering())
						+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaMediaRoboClustering((((double) newCase.getCartaMediaRoboClustering())
						+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaBaixaRoboClustering((((double) newCase.getCartaBaixaRoboClustering())
						+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(actualCaseRecuperado);
				quemChamou.learnCasesCentroideQuemTrucoPrimeiraMao(tempCase, actualCaseRecuperado);
			}
		}

	}

	public void recalculaCentroidesQuemTrucoPrimeiraPe(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisTrucoFromMaos = casesBases.get_caseBaseMaos();
		casesUteisTrucoFromMaos = quemChamou.retornaApenasCasosUteisParaTruco(casesUteisTrucoFromMaos);

		Collection<CBRCase> todosOsCasosQuemTruco = casesBases.get_caseBaseCentroideQuemTrucoPrimeiraPe();

		List<CBRCase> listTodosOsCasosQuemTruco = converteCollectionToList(todosOsCasosQuemTruco);
		for (int i = 0; i < listTodosOsCasosQuemTruco.size(); i++) {
			CBRCase actualCaseRecuperado = (CBRCase) listTodosOsCasosQuemTruco.get(i);

			CentroidesQuemTrucoDescription centroideAtualRecuperado = (CentroidesQuemTrucoDescription) actualCaseRecuperado
					.getDescription();

			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if (newCase.getClusterQuemTrucoPrimeiraPe().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterQuemTrucoPrimeiraPe(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarQuemTruco = quemChamou
						.getBestResultCluster(casesUteisTrucoFromMaos, trucoDescriptionConsulta, 15);
				Iterator iteratorRetrievalResultCasosMaisSimilarQuemTruco = casosGrupoMaisSimilarQuemTruco.iterator();
				while (iteratorRetrievalResultCasosMaisSimilarQuemTruco.hasNext()) {
					RetrievalResult r = (RetrievalResult) iteratorRetrievalResultCasosMaisSimilarQuemTruco.next();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) r.get_case().getDescription());
				}
				
				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalQuemTrucoRetornado = centroideAtualRecuperado.getQuemTruco()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				Double valorTotalQuemRetrucoRetornado = centroideAtualRecuperado.getQuemRetruco()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalQuemValeQuatroRetornado = centroideAtualRecuperado.getQuemValeQuatro()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado.getCartaAltaRoboClustering()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaMediaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaBaixaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				
				CentroidesQuemTrucoDescription centroideAtual = new CentroidesQuemTrucoDescription();
				centroideAtual.setQuemTruco((((double) newCase.getQuemTrucoCluster()) + valorTotalQuemTrucoRetornado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setQuemRetruco((((double) newCase.getQuemRetrucoCluster()) + valorTotalQuemRetrucoRetornado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual
						.setQuemValeQuatro((((double) newCase.getQuemValeQuatroCluster()) + valorTotalQuemValeQuatroRetornado)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				centroideAtual.setCartaAltaRoboClustering((((double) newCase.getCartaAltaRoboClustering())
						+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaMediaRoboClustering((((double) newCase.getCartaMediaRoboClustering())
						+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaBaixaRoboClustering((((double) newCase.getCartaBaixaRoboClustering())
						+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());


				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(actualCaseRecuperado);
				quemChamou.learnCasesCentroideQuemTrucoPrimeiraPe(tempCase, actualCaseRecuperado);
			}
		}

	}

	public void recalculaCentroidesQuemTrucoSegundaGanhouAnterior(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisTrucoFromMaos = casesBases.get_caseBaseMaos();
		casesUteisTrucoFromMaos = quemChamou.retornaApenasCasosUteisParaTruco(casesUteisTrucoFromMaos);

		Collection<CBRCase> todosOsCasosQuemTruco = casesBases.get_caseBaseCentroideQuemTrucoSegundaGanhouAnterior();
		List<CBRCase> listTodosOsCasosQuemTruco = converteCollectionToList(todosOsCasosQuemTruco);

		for (int i = 0; i < listTodosOsCasosQuemTruco.size(); i++) {
			CBRCase actualCaseRecuperado = (CBRCase) listTodosOsCasosQuemTruco.get(i);

			CentroidesQuemTrucoDescription centroideAtualRecuperado = (CentroidesQuemTrucoDescription) actualCaseRecuperado
					.getDescription();

			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if (newCase.getClusterQuemTrucoSegundaGanhouAnterior().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterQuemTrucoSegundaGanhouAnterior(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarQuemTruco = quemChamou
						.getBestResultCluster(casesUteisTrucoFromMaos, trucoDescriptionConsulta, 16);
				Iterator iteratorRetrievalResultCasosMaisSimilarQuemTruco = casosGrupoMaisSimilarQuemTruco.iterator();
				while (iteratorRetrievalResultCasosMaisSimilarQuemTruco.hasNext()) {
					RetrievalResult r = (RetrievalResult) iteratorRetrievalResultCasosMaisSimilarQuemTruco.next();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) r.get_case().getDescription());
				}
				CentroidesQuemTrucoDescription centroideAtual = new CentroidesQuemTrucoDescription();
				// -1 para nÃ£o contabilizar o atual
				// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
				Double valorTotalQuemTrucoRetornado = centroideAtualRecuperado.getQuemTruco()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				Double valorTotalQuemRetrucoRetornado = centroideAtualRecuperado.getQuemRetruco()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalQuemValeQuatroRetornado = centroideAtualRecuperado.getQuemValeQuatro()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

				Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado.getCartaAltaRoboClustering()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaMediaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
						.getCartaBaixaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				
				
				centroideAtual.setQuemTruco((((double) newCase.getQuemTrucoCluster()) + valorTotalQuemTrucoRetornado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setQuemRetruco((((double) newCase.getQuemRetrucoCluster()) + valorTotalQuemRetrucoRetornado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual
						.setQuemValeQuatro((((double) newCase.getQuemValeQuatroCluster()) + valorTotalQuemValeQuatroRetornado)
								/ listaCasosRecuperadosDoGrupoAtual.size());
			
				
				
				
				centroideAtual.setCartaAltaRoboClustering((((double) newCase.getCartaAltaRoboClustering())
						+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaMediaRoboClustering((((double) newCase.getCartaMediaRoboClustering())
						+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setCartaBaixaRoboClustering((((double) newCase.getCartaBaixaRoboClustering())
						+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
						/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);

				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(actualCaseRecuperado);
				quemChamou.learnCasesCentroideQuemTrucoSegundaGanhouAnterior(tempCase, actualCaseRecuperado);
			}
		}

	}

	public void recalculaCentroidesQuemTrucoSegundaPerdeuAnterior(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisTrucoFromMaos = casesBases.get_caseBaseMaos();
		casesUteisTrucoFromMaos = quemChamou.retornaApenasCasosUteisParaTruco(casesUteisTrucoFromMaos);

		Collection<CBRCase> todosOsCasosQuemTruco = casesBases.get_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior();
		List<CBRCase> listTodosOsCasosQuemTruco = converteCollectionToList(todosOsCasosQuemTruco);

		for (int i = 0; i < listTodosOsCasosQuemTruco.size(); i++) {
			CBRCase actualCaseRecuperado = (CBRCase) listTodosOsCasosQuemTruco.get(i);

			CentroidesQuemTrucoDescription centroideAtualRecuperado = (CentroidesQuemTrucoDescription) actualCaseRecuperado
					.getDescription();

			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();

			if(newCase.getClusterQuemTrucoSegundaPerdeuAnterior().equals(centroideAtualRecuperado.getGrupo())) {

			TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
			trucoDescriptionConsulta.setClusterQuemTrucoSegundaPerdeuAnterior(centroideAtualRecuperado.getGrupo());

			Collection<RetrievalResult> casosGrupoMaisSimilarQuemTruco = quemChamou
					.getBestResultCluster(casesUteisTrucoFromMaos, trucoDescriptionConsulta, 17);
			Iterator iteratorRetrievalResultCasosMaisSimilarQuemTruco = casosGrupoMaisSimilarQuemTruco.iterator();
			while (iteratorRetrievalResultCasosMaisSimilarQuemTruco.hasNext()) {
				RetrievalResult r = (RetrievalResult) iteratorRetrievalResultCasosMaisSimilarQuemTruco.next();
				listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) r.get_case().getDescription());
			}
			
			CentroidesQuemTrucoDescription centroideAtual = new CentroidesQuemTrucoDescription();
			
			// -1 para nÃ£o contabilizar o atual
			// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
			Double valorTotalQuemTrucoRetornado = centroideAtualRecuperado.getQuemTruco()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

			Double valorTotalQuemRetrucoRetornado = centroideAtualRecuperado.getQuemRetruco()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalQuemValeQuatroRetornado = centroideAtualRecuperado.getQuemValeQuatro()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

			Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado.getCartaAltaRoboClustering()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
					.getCartaMediaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
					.getCartaBaixaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			
			
			centroideAtual.setQuemTruco((((double) newCase.getQuemTrucoCluster()) + valorTotalQuemTrucoRetornado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setQuemRetruco((((double) newCase.getQuemRetrucoCluster()) + valorTotalQuemRetrucoRetornado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual
					.setQuemValeQuatro((((double) newCase.getQuemValeQuatroCluster()) + valorTotalQuemValeQuatroRetornado)
							/ listaCasosRecuperadosDoGrupoAtual.size());
			
			centroideAtual.setCartaAltaRoboClustering((((double) newCase.getCartaAltaRoboClustering())
					+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setCartaMediaRoboClustering((((double) newCase.getCartaMediaRoboClustering())
					+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setCartaBaixaRoboClustering((((double) newCase.getCartaBaixaRoboClustering())
					+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
			// aqui aprende
			CBRCase tempCase = new CBRCase();
			tempCase.setDescription(centroideAtual);
			
			Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
			aprenderCasos.add(tempCase);

			Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
			esquecerCasos.add(actualCaseRecuperado);
			quemChamou.learnCasesCentroideQuemTrucoSegundaPerdeuAnterior(tempCase, actualCaseRecuperado);

			}
			}

	}

	public void recalculaCentroidesQuemTrucoTerceiraGanhouAnterior(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisTrucoFromMaos = casesBases.get_caseBaseMaos();
		casesUteisTrucoFromMaos = quemChamou.retornaApenasCasosUteisParaTruco(casesUteisTrucoFromMaos);

		Collection<CBRCase> todosOsCasosQuemTruco = casesBases.get_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior();
		List<CBRCase> listTodosOsCasosQuemTruco = converteCollectionToList(todosOsCasosQuemTruco);

		for (int i = 0; i < listTodosOsCasosQuemTruco.size(); i++) {
			CBRCase actualCaseRecuperado = (CBRCase) listTodosOsCasosQuemTruco.get(i);

			CentroidesQuemTrucoDescription centroideAtualRecuperado = (CentroidesQuemTrucoDescription) actualCaseRecuperado
					.getDescription();

			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if(newCase.getClusterQuemTrucoTerceiraGanhouAnterior().equals(centroideAtualRecuperado.getGrupo())) {
			
			TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
			trucoDescriptionConsulta.setClusterQuemTrucoTerceiraGanhouAnterior(centroideAtualRecuperado.getGrupo());

			Collection<RetrievalResult> casosGrupoMaisSimilarQuemTruco = quemChamou
					.getBestResultCluster(casesUteisTrucoFromMaos, trucoDescriptionConsulta, 18);
			Iterator iteratorRetrievalResultCasosMaisSimilarQuemTruco = casosGrupoMaisSimilarQuemTruco.iterator();
			while (iteratorRetrievalResultCasosMaisSimilarQuemTruco.hasNext()) {
				RetrievalResult r = (RetrievalResult) iteratorRetrievalResultCasosMaisSimilarQuemTruco.next();
				listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) r.get_case().getDescription());
			}
			
			// -1 para nÃ£o contabilizar o atual
			// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
			Double valorTotalQuemTrucoRetornado = centroideAtualRecuperado.getQuemTruco()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

			Double valorTotalQuemRetrucoRetornado = centroideAtualRecuperado.getQuemRetruco()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalQuemValeQuatroRetornado = centroideAtualRecuperado.getQuemValeQuatro()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

			Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado.getCartaAltaRoboClustering()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
					.getCartaMediaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
					.getCartaBaixaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			CentroidesQuemTrucoDescription centroideAtual = new CentroidesQuemTrucoDescription();
			
			centroideAtual.setQuemTruco((((double) newCase.getQuemTrucoCluster()) + valorTotalQuemTrucoRetornado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setQuemRetruco((((double) newCase.getQuemRetrucoCluster()) + valorTotalQuemRetrucoRetornado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual
					.setQuemValeQuatro((((double) newCase.getQuemValeQuatroCluster()) + valorTotalQuemValeQuatroRetornado)
							/ listaCasosRecuperadosDoGrupoAtual.size());
			centroideAtual.setCartaAltaRoboClustering((((double) newCase.getCartaAltaRoboClustering())
					+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setCartaMediaRoboClustering((((double) newCase.getCartaMediaRoboClustering())
					+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setCartaBaixaRoboClustering((((double) newCase.getCartaBaixaRoboClustering())
					+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
			// aqui aprende
			CBRCase tempCase = new CBRCase();
			tempCase.setDescription(centroideAtual);
			Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
			aprenderCasos.add(tempCase);

			Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
			esquecerCasos.add(actualCaseRecuperado);
			quemChamou.learnCasesCentroideQuemTrucoTerceiraGanhouAnterior(tempCase, actualCaseRecuperado);
			}
		}

	}

	public void recalculaCentroidesQuemTrucoTerceiraCartaPerdeuAnterior(TrucoDescription newCase) {
		Collection<CBRCase> casesUteisTrucoFromMaos = casesBases.get_caseBaseMaos();
		casesUteisTrucoFromMaos = quemChamou.retornaApenasCasosUteisParaTruco(casesUteisTrucoFromMaos);

		Collection<CBRCase> todosOsCasosQuemTruco = casesBases.get_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior();
		List<CBRCase> listTodosOsCasosQuemTruco = converteCollectionToList(todosOsCasosQuemTruco);

		for (int i = 0; i < listTodosOsCasosQuemTruco.size(); i++) {
			CBRCase actualCaseRecuperado = (CBRCase) listTodosOsCasosQuemTruco.get(i);

			CentroidesQuemTrucoDescription centroideAtualRecuperado = (CentroidesQuemTrucoDescription) actualCaseRecuperado
					.getDescription();

			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if(newCase.getClusterQuemTrucoTerceiraPerdeuAnterior().equals(centroideAtualRecuperado.getGrupo())) {
			
			TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
			trucoDescriptionConsulta.setClusterQuemTrucoTerceiraPerdeuAnterior(centroideAtualRecuperado.getGrupo());

			Collection<RetrievalResult> casosGrupoMaisSimilarQuemTruco = quemChamou
					.getBestResultCluster(casesUteisTrucoFromMaos, trucoDescriptionConsulta, 19);
			// //System.out.println("grupo retornado: "+ centroideAtual.getGrupo());
			Iterator iteratorRetrievalResultCasosMaisSimilarQuemTruco = casosGrupoMaisSimilarQuemTruco.iterator();
			while (iteratorRetrievalResultCasosMaisSimilarQuemTruco.hasNext()) {
				RetrievalResult r = (RetrievalResult) iteratorRetrievalResultCasosMaisSimilarQuemTruco.next();
				listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) r.get_case().getDescription());
			}
			
			// -1 para nÃ£o contabilizar o atual
			// tem que diminuir 1 do caso atual que jÃ¡ foi adicionado
			Double valorTotalQuemTrucoRetornado = centroideAtualRecuperado.getQuemTruco()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

			Double valorTotalQuemRetrucoRetornado = centroideAtualRecuperado.getQuemRetruco()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalQuemValeQuatroRetornado = centroideAtualRecuperado.getQuemValeQuatro()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);

			Double valorTotalCartaAltaSemNormalizarCentroideRecuperado = centroideAtualRecuperado.getCartaAltaRoboClustering()
					* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalCartaMediaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
					.getCartaMediaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
			Double valorTotalCartaBaixaSemNormalizarCentroideRecuperado = centroideAtualRecuperado
					.getCartaBaixaRoboClustering() * (listaCasosRecuperadosDoGrupoAtual.size() - 1);
		
			CentroidesQuemTrucoDescription centroideAtual = new CentroidesQuemTrucoDescription();
			
			centroideAtual.setQuemTruco((((double) newCase.getQuemTrucoCluster()) + valorTotalQuemTrucoRetornado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setQuemRetruco((((double) newCase.getQuemRetrucoCluster()) + valorTotalQuemRetrucoRetornado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual
					.setQuemValeQuatro((((double) newCase.getQuemValeQuatroCluster()) + valorTotalQuemValeQuatroRetornado)
							/ listaCasosRecuperadosDoGrupoAtual.size());
			centroideAtual.setCartaAltaRoboClustering((((double) newCase.getCartaAltaRoboClustering())
					+ valorTotalCartaAltaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setCartaMediaRoboClustering((((double) newCase.getCartaMediaRoboClustering())
					+ valorTotalCartaMediaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setCartaBaixaRoboClustering((((double) newCase.getCartaBaixaRoboClustering())
					+ valorTotalCartaBaixaSemNormalizarCentroideRecuperado)
					/ listaCasosRecuperadosDoGrupoAtual.size());

			centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
			
			// aqui aprende
			CBRCase tempCase = new CBRCase();
			tempCase.setDescription(centroideAtual);
			Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
			aprenderCasos.add(tempCase);
			
			Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
			esquecerCasos.add(actualCaseRecuperado);
			
			quemChamou.learnCasesCentroideQuemTrucoTerceiraPerdeuAnterior(tempCase, actualCaseRecuperado);
			}
			}

	}

	public void recalculaCentroidesQuemEnvidoAgenteMao(TrucoDescription newCase) {
		Collection<CBRCase> pontosIndexacao = casesBases.get_caseBaseMaos();
		Collection<CBRCase> pontosUteisAgenteMao = quemChamou.retornaApenasCasosUteisParaEnvido(pontosIndexacao, 1);
		/// Collection<CBRCase> pontosUteisAgentePe =
		/// quemChamou.retornaApenasCasosUteisParaEnvido(pontosIndexacao, 2);

		pontosIndexacao.addAll(pontosUteisAgenteMao);
		// pontosIndexacao.addAll(pontosUteisAgentePe);

		Collection<CBRCase> todosOsCasosQuemEnvidoAgenteMao = casesBases
				.get_caseBaseCentroideQuemGanhouEnvidoAgenteMao();

		List<CBRCase> listaTodosOsCasos = converteCollectionToList(todosOsCasosQuemEnvidoAgenteMao);

		for (int i = 0; i < listaTodosOsCasos.size(); i++) {
			CBRCase caseAtualRecuperado = (CBRCase) listaTodosOsCasos.get(i);
			CentroidesQuemGanhouEnvidoDescription centroideAtualRecuperado = (CentroidesQuemGanhouEnvidoDescription) caseAtualRecuperado.getDescription();

			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();
			if (newCase.getClusterQuemEnvidoAgenteMao().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterQuemEnvidoAgenteMao(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarQuemEnvidoAgenteMao = quemChamou
						.getBestResultCluster(pontosIndexacao, trucoDescriptionConsulta, 10);
				Iterator iteratorCasosDoGrupoAtual = casosGrupoMaisSimilarQuemEnvidoAgenteMao.iterator();
				while (iteratorCasosDoGrupoAtual.hasNext()) {
					RetrievalResult casosDoGrupoAtual = (RetrievalResult) iteratorCasosDoGrupoAtual.next();
					listaCasosRecuperadosDoGrupoAtual
							.add((TrucoDescription) casosDoGrupoAtual.get_case().getDescription());
				}
				
				Double quemPediuEnvidoAntesNormalizar = centroideAtualRecuperado.getQuemPediuEnvido()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double quemPediuRealEnvidoAntesNormalizar = centroideAtualRecuperado.getQuemPediuRealEnvido()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double quemPediuFaltaEnvidoAntesNormalizar = centroideAtualRecuperado.getQuemPediuFaltaEnvido()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double somaPontosEnvidoSemNormalizar = centroideAtualRecuperado.getPontosEnvidoRobo()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				
				CentroidesQuemGanhouEnvidoDescription centroideAtual = new CentroidesQuemGanhouEnvidoDescription();
				

				
				centroideAtual.setPontosEnvidoRobo(
						(((double) newCase.getPontosEnvidoRobo()) + somaPontosEnvidoSemNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				
				
				centroideAtual
						.setQuemPediuEnvido((((double) newCase.getQuemPediuEnvidoCluster()) + quemPediuEnvidoAntesNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				centroideAtual.setQuemPediuRealEnvido(
						(((double) newCase.getQuemPediuRealEnvidoCluster()) + quemPediuEnvidoAntesNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				centroideAtual.setQuemPediuFaltaEnvido(
						(((double) newCase.getQuemPediuFaltaEnvidoCluster()) + quemPediuEnvidoAntesNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);
				
				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(caseAtualRecuperado);
				quemChamou.learnCasesCentroideQuemGanhouEnvidoAgenteMao(tempCase,  caseAtualRecuperado);
			}
		}

	}

	public void recalculaCentroidesQuemEnvidoAgentePe(TrucoDescription newCase) {
		Collection<CBRCase> pontosIndexacao = casesBases.get_caseBaseMaos();
		// Collection<CBRCase> pontosUteisAgenteMao =
		// quemChamou.retornaApenasCasosUteisParaEnvido(pontosIndexacao, 1);
		Collection<CBRCase> pontosUteisAgentePe = quemChamou.retornaApenasCasosUteisParaEnvido(pontosIndexacao, 2);

		// pontosIndexacao.addAll(pontosUteisAgenteMao);
		pontosIndexacao.addAll(pontosUteisAgentePe);

		Collection<CBRCase> todosOsCasosQuemEnvidoAgentePe = casesBases.get_caseBaseCentroideQuemGanhouEnvidoAgentePe();
		List<CBRCase> listaTodosOsCasos = converteCollectionToList(todosOsCasosQuemEnvidoAgentePe);

		for (int i = 0; i < listaTodosOsCasos.size(); i++) {
			CBRCase caseAtualRecuperado = (CBRCase) listaTodosOsCasos.get(i);
			CentroidesQuemGanhouEnvidoDescription centroideAtualRecuperado = (CentroidesQuemGanhouEnvidoDescription) caseAtualRecuperado
					.getDescription();

			List<TrucoDescription> listaCasosRecuperadosDoGrupoAtual = new ArrayList<TrucoDescription>();

			if (newCase.getClusterQuemEnvidoAgentePe().equals(centroideAtualRecuperado.getGrupo())) {

				TrucoDescription trucoDescriptionConsulta = new TrucoDescription();
				trucoDescriptionConsulta.setClusterQuemEnvidoAgentePe(centroideAtualRecuperado.getGrupo());

				Collection<RetrievalResult> casosGrupoMaisSimilarQuemEnvidoAgentePe = quemChamou
						.getBestResultCluster(pontosIndexacao, trucoDescriptionConsulta, 11);
				Iterator iteratorCasosGrupoMaisSimilarAgentePe = casosGrupoMaisSimilarQuemEnvidoAgentePe.iterator();
				while (iteratorCasosGrupoMaisSimilarAgentePe.hasNext()) {
					RetrievalResult casosDoGrupo = (RetrievalResult) iteratorCasosGrupoMaisSimilarAgentePe.next();
					listaCasosRecuperadosDoGrupoAtual.add((TrucoDescription) casosDoGrupo.get_case().getDescription());
				}
				Double quemPediuEnvidoAntesNormalizar = centroideAtualRecuperado.getQuemPediuEnvido()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double quemPediuRealEnvidoAntesNormalizar = centroideAtualRecuperado.getQuemPediuRealEnvido()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double quemPediuFaltaEnvidoAntesNormalizar = centroideAtualRecuperado.getQuemPediuFaltaEnvido()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				Double somaPontosEnvidoSemNormalizar = centroideAtualRecuperado.getPontosEnvidoRobo()
						* (listaCasosRecuperadosDoGrupoAtual.size() - 1);
				
				
				
				CentroidesQuemGanhouEnvidoDescription centroideAtual = new CentroidesQuemGanhouEnvidoDescription();
				
				centroideAtual.setPontosEnvidoRobo(
						(((double) newCase.getPontosEnvidoRobo()) + somaPontosEnvidoSemNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				
				
				centroideAtual
						.setQuemPediuEnvido((((double) newCase.getQuemPediuEnvidoCluster()) + quemPediuEnvidoAntesNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				centroideAtual.setQuemPediuRealEnvido(
						(((double) newCase.getQuemPediuRealEnvidoCluster()) + quemPediuEnvidoAntesNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());
				centroideAtual.setQuemPediuFaltaEnvido(
						(((double) newCase.getQuemPediuFaltaEnvidoCluster()) + quemPediuEnvidoAntesNormalizar)
								/ listaCasosRecuperadosDoGrupoAtual.size());

				centroideAtual.setGrupo(centroideAtualRecuperado.getGrupo());
				// aqui aprende
				CBRCase tempCase = new CBRCase();
				tempCase.setDescription(centroideAtual);
				Collection<CBRCase> aprenderCasos = new ArrayList<CBRCase>();
				aprenderCasos.add(tempCase);
				
				Collection<CBRCase> esquecerCasos = new ArrayList<CBRCase>();
				esquecerCasos.add(caseAtualRecuperado);

				quemChamou.learnCasesCentroideQuemGanhouEnvidoAgentePe(tempCase, caseAtualRecuperado);
			}

		}
	}
}