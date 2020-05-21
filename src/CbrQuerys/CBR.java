package CbrQuerys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cbr.PesosConsulta;
import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.CentroidesGrupoIndexacaoDescription;
import cbr.cbrDescriptions.CentroidesGrupoIndexacaoPontosDescription;
import cbr.cbrDescriptions.TrucoDescription;
import euclidean.GlobalEuclidean;
import euclidean.LocalEuclidean;
import jcolibri.cbrcore.CBRCaseBaseGustavo;
import jcolibri.casebase.CachedLinealCaseBaseGustavoNew;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;

import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnectorGustavoNew;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.InitializingException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;

import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;

import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.selection.SelectCases;

public interface CBR {
	double taxaSimilaridadeAprender = 0.9; // Taxa minima de similirade pra pedir ajuda
	double taxaSimilaridadeMemorizar = 0.9; // taxa minima de similariadde pra aprendizgem
	double ProbChamadasEaceites = 0.5; // probabilidade de jogada aleatoria
	double ProbCartas = 0.25;
	double thresholdParaAprender = 0.97; // threshold minimo pra imitar a jogada passada
	int quantidadeDeCasos = 10; // quantidade de casos que vao ser retornados no KNN

	static final int ROBO = 1;
	static final int HUMANO = 2;
	static final int DEFAULT = 0;
	static final int contraflor = 3;
	static final int cartaCluster = 4;
	static final int pontoCluster = 5;
	static final int pontoClusterEx = 100;
	static final int trucoCartaCluster = 101;
	static final int pontoClusterAL = 500;
	static final int trucoClusterAL = 501;
	static final int active = 1000;
	static final int trucoCluster = 6;
	static final int recuperaGruposJogadas = 7;
	static final int recuperaGruposQuemEnvidoAgenteMao = 10;
	static final int recuperaGruposQuemEnvidoAgentePe = 11;
	static final int recuperaGruposIndexacaoJogada = 12;
	static final int recuperaGruposIndexacaoEnvido = 13;
	static final int recuperaGruposQuemTrucoPrimeiraMao = 14;
	static final int recuperaGruposQuemTrucoPrimeiraPe = 15;
	static final int recuperaGruposQuemTrucoSegundaGanhouAnterior = 16;
	static final int recuperaGruposQuemTrucoSegundaPerdeuAnterior = 17;
	static final int recuperaGruposQuemTrucoTerceiraGanhouAnterior = 18;
	static final int recuperaGruposQuemTrucoTerceiraPerdeuAnterior = 19;

	// a partir de 20 são os tipos de consultas para jogadas
	// por enquanto não achei necessidade de utilizar
	static final int primeiraCartaRoboMao = 20;
	static final int primeiraCartaRoboPe = 21;
	static final int segundaCartaRoboGanhouAprimeira = 22;
	static final int segundaCartaRoboPerdeuAprimeira = 23;
	static final int terceiraCartaRoboGanhouAsegunda = 24;
	static final int terceiraCartaPerdeuAsegunda = 25;

	default void initialize_conector(String Base, String tipoBase, CbrModular quemPediu) throws ExecutionException {

		try {

			String path;
			if (tipoBase.equalsIgnoreCase("imitacao")) {
				path = "cbr/HibernateImitacao/";
				if (Base.equalsIgnoreCase("Maos") && quemPediu.get_connectorMaosImitacao() == null) {
					//// .out.println("path do hibernate: "+path);
					quemPediu.set_connectorMaosImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorMaosImitacao().initFromXMLfile(jcolibri.util.FileIO.findFile(path + "databases/databaseconfig.xml"));

				}

				else	if (Base.equalsIgnoreCase("CentroideIndexacao") && quemPediu.get_connectorCentroidesGrupoIndexacaoImitacao() == null) {
					quemPediu.set_connectorCentroidesGrupoIndexacaoImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidesGrupoIndexacaoImitacao().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroideIndexacao.xml"));;

				}
				else if (Base.equalsIgnoreCase("CentroideIndexacaoPontos") && quemPediu.get_connectorCentroidesGrupoIndexacaoPontosImitacao() == null) {
					quemPediu.set_connectorCentroidesGrupoIndexacaoPontosImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidesGrupoIndexacaoPontosImitacao().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroideIndexacaoPontos.xml"));
				}


			} else if (tipoBase.equalsIgnoreCase("ativo")) {

				path = "cbr/HibernateAtivo/";
				if (Base.equalsIgnoreCase("Maos") && quemPediu.get_connectorMaosAtivo() == null) {
					//// .out.println("path do hibernate: "+path);
					quemPediu.set_connectorMaosAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorMaosAtivo().initFromXMLfile(jcolibri.util.FileIO.findFile(path + "databases/databaseconfig.xml"));

				}

				else	if (Base.equalsIgnoreCase("CentroideIndexacao") && quemPediu.get_connectorCentroidesGrupoIndexacaoAtivo() == null) {
					quemPediu.set_connectorCentroidesGrupoIndexacaoAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidesGrupoIndexacaoAtivo().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroideIndexacao.xml"));;

				}
				else if (Base.equalsIgnoreCase("CentroideIndexacaoPontos") && quemPediu.get_connectorCentroidesGrupoIndexacaoPontosAtivo() == null) {
					quemPediu.set_connectorCentroidesGrupoIndexacaoPontosAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidesGrupoIndexacaoPontosAtivo().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroideIndexacaoPontos.xml"));
				}

			}else {
				path = "cbr/Hibernate/";
				if (Base.equalsIgnoreCase("Maos") && quemPediu.get_connectorMaosBaseline() == null) {
					//// .out.println("path do hibernate: "+path);
					quemPediu.set_connectorMaosBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorMaosBaseline().initFromXMLfile(jcolibri.util.FileIO.findFile(path + "databases/databaseconfig.xml"));

				}

				else	if (Base.equalsIgnoreCase("CentroideIndexacao") && quemPediu.get_connectorCentroidesGrupoIndexacaoBaseline() == null) {
					quemPediu.set_connectorCentroidesGrupoIndexacaoBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidesGrupoIndexacaoBaseline().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroideIndexacao.xml"));;

				}
				else if (Base.equalsIgnoreCase("CentroideIndexacaoPontos") && quemPediu.get_connectorCentroidesGrupoIndexacaoPontosBaseline() == null) {
					quemPediu.set_connectorCentroidesGrupoIndexacaoPontosBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidesGrupoIndexacaoPontosBaseline().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroideIndexacaoPontos.xml"));
				}
			}



		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	default void openConnectionBase(CBRCaseBaseGustavo _caseBase, Connector _connector,String Base, CbrModular quemPediu) throws InitializingException {
		if (Base.equalsIgnoreCase("Maos") && quemPediu.get_caseBaseMaos() == null) {
			//// .out.println("path do hibernate: "+path);
			quemPediu.set_caseBaseMaos(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseMaos().init(_connector);

		}

		else if (Base.equalsIgnoreCase("CentroideIndexacao") && quemPediu.get_caseBaseCentroidesGrupoIndexacao() == null) {
			quemPediu.set_caseBaseCentroidesGrupoIndexacao(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroidesGrupoIndexacao().init(_connector);

		}
		else if (Base.equalsIgnoreCase("CentroideIndexacaoPontos") && quemPediu.get_caseBaseCentroidesGrupoIndexacaoPontos() == null) {
			quemPediu.set_caseBaseCentroidesGrupoIndexacaoPontos(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroidesGrupoIndexacaoPontos().init(_connector);

		}

	}

	default void closeConnection(Connector _connector) throws ExecutionException {
		if(_connector != null)
			_connector.close();
	}

	//MUDAR TUDO PARA ITERATOR TUDO
	// preenche hashs Jogada
	default HashMap<Integer, Collection<CBRCase>> retornaHashsDeGruposJogadaPorCasos(CBRCaseBaseGustavo caseBaseCasos,
																					 CBRCaseBaseGustavo caseBaseCentroide) {
		Collection<CBRCase> listaCasosDaBase = caseBaseCasos.getCases();

		HashMap<Integer, Collection<CBRCase>> hashGruposJogada = new HashMap<Integer, Collection<CBRCase>>();
		Collection<CBRCase> centroides = caseBaseCentroide.getCases();
		Iterator iteratorCentroides = centroides.iterator();
		while (iteratorCentroides.hasNext()) {
			CBRCase centroideIndexacaoCbrCase = (CBRCase) iteratorCentroides.next();
			CentroidesGrupoIndexacaoDescription centroideIndexacao = (CentroidesGrupoIndexacaoDescription) centroideIndexacaoCbrCase
					.getDescription();

			Collection<CBRCase> listaCasosDeJogadaDoGrupoAtual = new ArrayList<CBRCase>();
			Iterator iteratorCasosPresentesNaBase = listaCasosDaBase.iterator();
			while (iteratorCasosPresentesNaBase.hasNext()) {
				CBRCase actual = (CBRCase) iteratorCasosPresentesNaBase.next();
				TrucoDescription t = (TrucoDescription) actual.getDescription();
				if (t.getClusteringindexacao() != null) {
					if (t.getClusteringindexacao().equals(centroideIndexacao.getGrupo())
							&& ((t.getUtilCarta() != null && t.getUtilCarta() == 1)
							|| (t.getUtilTruco() != null && t.getUtilTruco() == 1)))
						listaCasosDeJogadaDoGrupoAtual.add(actual);
				}
			}
			if (listaCasosDeJogadaDoGrupoAtual.size() > 0)
				hashGruposJogada.put(centroideIndexacao.getGrupo(), listaCasosDeJogadaDoGrupoAtual);
		}

		return hashGruposJogada;
	}

	// retorna uteis carta ou truco
	default Collection<CBRCase> retornaApenasCasosUteisParaCartaOuTruco(Collection<CBRCase> casosDoGrupoEscolhido) {
		Collection<CBRCase> casosUteisCarta = new ArrayList<CBRCase>();
		//// .out.println("Casos Do grupo escolhido: "+
		//// casosDoGrupoEscolhido.size());
		Iterator iterator = casosDoGrupoEscolhido.iterator();
		while (iterator.hasNext()) {
			CBRCase actualCase = (CBRCase) iterator.next();
			TrucoDescription t = (TrucoDescription) actualCase.getDescription();
			if (t.getUtilCarta().equals(1) || t.getUtilTruco().equals(1))
				casosUteisCarta.add(actualCase);
		}

		return casosUteisCarta;
	}

	// retorna casos
	default Collection<CBRCase> retornaApenasCasosUteisParaIndexacaoPontos(Collection<CBRCase> casosDoGrupoEscolhido) {
		Iterator iteratorCasosDoGrupoEscolhido = casosDoGrupoEscolhido.iterator();
		Collection<CBRCase> casosUteisEnvido = new ArrayList<CBRCase>();
		while (iteratorCasosDoGrupoEscolhido.hasNext()) {
			CBRCase actualCase = (CBRCase) iteratorCasosDoGrupoEscolhido.next();
			TrucoDescription t = (TrucoDescription) actualCase.getDescription();
			if (t.getUtilEnvido().equals(1)  || t.getUtilFlor().equals(1) )
				casosUteisEnvido.add(actualCase);
		}

		return casosUteisEnvido;
	}

	// retorna Apenas casos uteis carta
	default Collection<CBRCase> retornaApenasCasosUteisParaCarta(Collection<CBRCase> casosDoGrupoEscolhido) {
		Collection<CBRCase> casosUteisCarta = new ArrayList<CBRCase>();
		if(casosDoGrupoEscolhido != null) {
			Iterator iterator = casosDoGrupoEscolhido.iterator();
			while (iterator.hasNext()) {
				CBRCase actualCase = (CBRCase) iterator.next();
				TrucoDescription t = (TrucoDescription) actualCase.getDescription();
				if (t.getUtilCarta().equals(1))
					casosUteisCarta.add(actualCase);
			}
		}
		return casosUteisCarta;
	}

	default Collection<CBRCase> retornaApenasCasosUteisParaTruco(Collection<CBRCase> casosDoGrupoEscolhido) {
		Collection<CBRCase> casosUteisTruco = new ArrayList<CBRCase>();
		if(casosDoGrupoEscolhido != null) {
			Iterator iteratorCasosDoGrupoEscolhido = casosDoGrupoEscolhido.iterator();
			while (iteratorCasosDoGrupoEscolhido.hasNext()) {
				CBRCase actualCase = (CBRCase) iteratorCasosDoGrupoEscolhido.next();
				TrucoDescription t = (TrucoDescription) actualCase.getDescription();
				if (t.getUtilTruco().equals(1) )
					casosUteisTruco.add(actualCase);
			}
		}
		return casosUteisTruco;
	}

	default Collection<CBRCase> retornaApenasCasosUteisParaEnvido(Collection<CBRCase> casosDoGrupoEscolhido,
																  int jogadorMao) {
		Collection<CBRCase> casosUteisEnvido = new ArrayList<CBRCase>();
		if(casosDoGrupoEscolhido != null) {
			Iterator iteratorCasosDoGrupoEscolhido = casosDoGrupoEscolhido.iterator();

			while (iteratorCasosDoGrupoEscolhido.hasNext()) {
				CBRCase actualCase = (CBRCase) iteratorCasosDoGrupoEscolhido.next();
				TrucoDescription t = (TrucoDescription) actualCase.getDescription();
				if (t.getUtilEnvido().equals(1) && t.getJogadorMao().equals(jogadorMao))
					casosUteisEnvido.add(actualCase);
			}
		}

		return casosUteisEnvido;
	}

	default Collection<CBRCase> retornaApenasCasosUteisParaFlor(Collection<CBRCase> casosDoGrupoEscolhido,
																int jogadorMao) {
		Collection<CBRCase> casosUteisFlor = new ArrayList<CBRCase>();

		if(casosDoGrupoEscolhido != null) {
			Iterator iteratorCasosDoGrupoEscolhido = casosDoGrupoEscolhido.iterator();
			while (iteratorCasosDoGrupoEscolhido.hasNext()) {
				CBRCase actualCase = (CBRCase) iteratorCasosDoGrupoEscolhido.next();
				TrucoDescription t = (TrucoDescription) actualCase.getDescription();
				if (t.getUtilFlor().equals(1)  && t.getJogadorMao().equals(jogadorMao))
					casosUteisFlor.add(actualCase);
			}
		}
		return casosUteisFlor;
	}

	// preenche hashs
	default HashMap<Integer, Collection<CBRCase>> retornaHashsDeGruposEnvidoPorCasos(CBRCaseBaseGustavo caseBaseCasos,
																					 CBRCaseBaseGustavo caseBaseCentroide) {
		Collection<CBRCase> listaCasosDaBase = caseBaseCasos.getCases();

		Collection<CBRCase> listaCentroides = caseBaseCentroide.getCases();
		Iterator iteratorCentroides = listaCentroides.iterator();

		HashMap<Integer, Collection<CBRCase>> hashGruposEnvido = new HashMap<Integer, Collection<CBRCase>>();

		while (iteratorCentroides.hasNext()) {
			CBRCase cbrCaseCentroide = (CBRCase) iteratorCentroides.next();
			CentroidesGrupoIndexacaoPontosDescription centroidesPontos = (CentroidesGrupoIndexacaoPontosDescription) cbrCaseCentroide
					.getDescription();
			Collection<CBRCase> listaCasosDeJogadaDoGrupoAtual = new ArrayList<CBRCase>();
			Iterator iteratorCasos = listaCasosDaBase.iterator();
			while (iteratorCasos.hasNext()) {
				CBRCase actual = (CBRCase) iteratorCasos.next();
				TrucoDescription t = (TrucoDescription) actual.getDescription();
				if (t.getClusteringIndexacaoPontos() != null) {
					if (t.getClusteringIndexacaoPontos().equals(centroidesPontos.getGrupo())
							&& ((t.getUtilEnvido() != null && t.getUtilEnvido().equals(1))
							|| (t.getUtilFlor() != null && t.getUtilFlor().equals(1))))
						listaCasosDeJogadaDoGrupoAtual.add(actual);
				}
			}
			if (listaCasosDeJogadaDoGrupoAtual.size() > 0)
				hashGruposEnvido.put(centroidesPontos.getGrupo(), listaCasosDeJogadaDoGrupoAtual);
		}

		return hashGruposEnvido;
	}

	// clustering indexacao
	// cbrQueryIndexacao jogada

	default Collection<RetrievalResult> executeQueryIndexacao(CBRCaseBaseGustavo caseCentroideJogada, CBRQuery query)
			throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());


		simConfig.addMapping(new Attribute("centroidecartaaltarobomao", CentroidesGrupoIndexacaoDescription.class),
				new LocalEuclidean(51));

		Attribute centroidecartamediarobomao = new Attribute("centroidecartamediarobomao",
				CentroidesGrupoIndexacaoDescription.class);
		simConfig.addMapping(centroidecartamediarobomao, new LocalEuclidean(51)); // carta

		Attribute centroidecartabaixarobomao = new Attribute("centroidecartabaixarobomao",
				CentroidesGrupoIndexacaoDescription.class);
		simConfig.addMapping(centroidecartabaixarobomao, new LocalEuclidean(51)); // jogador

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(caseCentroideJogada.getCases(), query,
				simConfig);

		eval = SelectCases.selectTopKRR(eval, 1);
		return eval;
	}
	// hash envido

	default Collection<RetrievalResult> executeQueryIndexacaoEnvido(CBRCaseBaseGustavo caseCentroideEnvido, CBRQuery query)
			throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());

		Attribute centroidePontosEnvidoRobo = new Attribute("centroidepontosenvidorobo",
				CentroidesGrupoIndexacaoPontosDescription.class);
		simConfig.addMapping(centroidePontosEnvidoRobo, new LocalEuclidean(33)); // jogador

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(caseCentroideEnvido.getCases(), query,
				simConfig);

		eval = SelectCases.selectTopKRR(eval, 1);
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterJogadaCarta(Collection<CBRCase> cases,
																						 CBRQuery query) throws ExecutionException {
		TrucoDescription desc = (TrucoDescription) query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		if (desc.getClusterPrimeiraCartaAgenteMao() != null && !desc.getClusterPrimeiraCartaAgenteMao().equals(0) )
			simConfig.addMapping(new Attribute("clusterPrimeiraCartaAgenteMao", TrucoDescription.class), new Equal()); // jogador

		if (desc.getClusterPrimeiraCartaAgentePe() != null && !desc.getClusterPrimeiraCartaAgentePe().equals(0) )
			simConfig.addMapping(new Attribute("clusterPrimeiraCartaAgentePe", TrucoDescription.class), new Equal()); // jogador

		if (desc.getClusterSegundaCartaAgenteGanhouAPrimeira() != null
				&& !desc.getClusterSegundaCartaAgenteGanhouAPrimeira().equals(0) )
			simConfig.addMapping(new Attribute("clusterSegundaCartaAgenteGanhouAprimeira", TrucoDescription.class),
					new Equal()); // jogador

		if (desc.getClusterSegundaCartaAgentePerdeuAprimeira() != null
				&& !desc.getClusterSegundaCartaAgentePerdeuAprimeira().equals(0))
			simConfig.addMapping(new Attribute("clusterSegundaCartaAgentePerdeuAprimeira", TrucoDescription.class),
					new Equal());

		if (desc.getClusterTerceiraCartaAgenteGanhouAsegunda() != null
				&& !desc.getClusterTerceiraCartaAgenteGanhouAsegunda().equals(0))
			simConfig.addMapping(new Attribute("clusterTerceiraCartaAgenteGanhouAsegunda", TrucoDescription.class),
					new Equal()); // jogador

		if (desc.getClusterTerceiraCartaAgentePerdeuAsegunda() != null
				&& !desc.getClusterTerceiraCartaAgentePerdeuAsegunda().equals(0) )
			simConfig.addMapping(new Attribute("clusterTerceiraCartaAgentePerdeuAsegunda", TrucoDescription.class),
					new Equal()); // jogador

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());

		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterQuemTrucoPrimeiraMao(Collection<CBRCase> cases,
																								  CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusterQuemTrucoPrimeiraMao", TrucoDescription.class), new Equal()); // Quem Truco

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterQuemTrucoPrimeiraPe(Collection<CBRCase> cases,
																								 CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusterQuemTrucoPrimeiraPe", TrucoDescription.class), new Equal()); // Quem Truco

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterQuemTrucoSegundaGanhouAnterior(Collection<CBRCase> cases,
																											CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusterQuemTrucoSegundaGanhouAnterior", TrucoDescription.class), new Equal()); // Quem Truco

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}


	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterQuemTrucoSegundaPerdeuAnterior(Collection<CBRCase> cases,
																											CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusterQuemTrucoSegundaPerdeuAnterior", TrucoDescription.class), new Equal()); // Quem Truco

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterQuemTrucoTerceiraGanhouAnterior(Collection<CBRCase> cases,
																											 CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusterQuemTrucoTerceiraGanhouAnterior", TrucoDescription.class), new Equal()); // Quem Truco

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterQuemTrucoTerceiraPerdeuAnterior(Collection<CBRCase> cases,
																											 CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusterQuemTrucoTerceiraPerdeuAnterior", TrucoDescription.class), new Equal()); // Quem Truco

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterQuemEnvidoAgenteMao(
			Collection<CBRCase> cases, CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusterQuemEnvidoAgenteMao", TrucoDescription.class), new Equal()); // Quem
		// Truco

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAindexacao(Collection<CBRCase> cases,
																				CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusteringindexacao", TrucoDescription.class), new Equal());

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAindexacaoPontos(Collection<CBRCase> cases,
																					  CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusteringIndexacaoPontos", TrucoDescription.class), new Equal());

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryCasosQuePertencerAclusterQuemEnvidoAgentePe(
			Collection<CBRCase> cases, CBRQuery query) throws ExecutionException {

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		simConfig.addMapping(new Attribute("clusterQuemEnvidoAgentePe", TrucoDescription.class), new Equal()); // Quem
		// Truco

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryJogadaCluster(Collection<CBRCase> cases, CBRQuery query)
			throws ExecutionException {
		TrucoDescription desc = (TrucoDescription) query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		PesosConsulta pesos = new PesosConsulta();
		if (desc.getJogadorMao() != null) {
			Attribute jogadorMao = new Attribute("jogadorMao", TrucoDescription.class);

			simConfig.addMapping(jogadorMao, new Equal()); // jogador
			simConfig.setWeight(jogadorMao, pesos.getPesoJogadorMao());
		}
		if (desc.getCartaAltaRobo() != null) {
			Attribute cartaAltaRobo = new Attribute("cartaAltaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaAltaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaAltaRobo, pesos.getPesoCartaAltaRobo());
		}
		if (desc.getCartaMediaRobo() != null) {
			Attribute cartaMediaRobo = new Attribute("cartaMediaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaMediaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaMediaRobo, pesos.getPesoCartaMediaRobo());
		}
		if (desc.getCartaBaixaRobo() != null) {
			Attribute cartaBaixaRobo = new Attribute("cartaBaixaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaBaixaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaBaixaRobo, pesos.getPesoCartaBaixaRobo());
		}

		if (desc.getPrimeiraCartaRobo() != null) {
			Attribute primeiraCartaRobo = new Attribute("primeiraCartaRobo", TrucoDescription.class);
			simConfig.addMapping(primeiraCartaRobo, new Interval(52)); // carta
			simConfig.setWeight(primeiraCartaRobo, pesos.getPesoPrimeiraCartaRobo());
		}
		if (desc.getSegundaCartaRobo() != null) {
			Attribute segundaCartaRobo = new Attribute("segundaCartaRobo", TrucoDescription.class);
			simConfig.addMapping(segundaCartaRobo, new Interval(52)); // carta
			simConfig.setWeight(segundaCartaRobo, pesos.getPesoSegundaCartaRobo());
		}
		if (desc.getTerceiraCartaRobo() != null) {
			Attribute terceiraCartaRobo = new Attribute("terceiraCartaRobo", TrucoDescription.class);
			simConfig.addMapping(terceiraCartaRobo, new Interval(52)); // carta
			simConfig.setWeight(terceiraCartaRobo, pesos.getPesoTerceiraCartaRobo());
		}



		if (desc.getPrimeiraCartaRoboClustering() != null) {
			Attribute primeiraCartaRoboClustering = new Attribute("primeiraCartaRoboClustering", TrucoDescription.class);
			simConfig.addMapping(primeiraCartaRoboClustering, new Equal()); // carta
			simConfig.setWeight(primeiraCartaRoboClustering, pesos.getPesoPrimeiraCartaRobo());
		}
		if (desc.getSegundaCartaRoboClustering() != null) {
			Attribute segundaCartaRoboClustering = new Attribute("segundaCartaRoboClustering", TrucoDescription.class);
			simConfig.addMapping(segundaCartaRoboClustering, new Equal()); // carta
			simConfig.setWeight(segundaCartaRoboClustering, pesos.getPesoSegundaCartaRobo());
		}
		if (desc.getTerceiraCartaRoboClustering() != null) {
			Attribute terceiraCartaRoboClustering = new Attribute("terceiraCartaRoboClustering", TrucoDescription.class);
			simConfig.addMapping(terceiraCartaRoboClustering, new Equal()); // carta
			simConfig.setWeight(terceiraCartaRoboClustering, pesos.getPesoTerceiraCartaRobo());
		}

		if (desc.getPrimeiraCartaHumano() != null) {
			Attribute primeiraCartaHumano = new Attribute("primeiraCartaHumano", TrucoDescription.class);
			simConfig.addMapping(primeiraCartaHumano, new Interval(52)); // carta
			simConfig.setWeight(primeiraCartaHumano, pesos.getPesoPrimeiraCartaHumano());
		}
		if (desc.getSegundaCartaHumano() != null) {
			Attribute segundaCartaHumano = new Attribute("segundaCartaHumano", TrucoDescription.class);
			simConfig.addMapping(segundaCartaHumano, new Interval(52)); // carta
			simConfig.setWeight(segundaCartaHumano, pesos.getPesoSegundaCartaHumano());
		}
		if (desc.getTerceiraCartaHumano() != null) {
			Attribute terceiraCartaHumano = new Attribute("terceiraCartaHumano", TrucoDescription.class);
			simConfig.addMapping(terceiraCartaHumano, new Interval(52)); // carta
			simConfig.setWeight(terceiraCartaHumano, pesos.getPesoTerceiraCartaHumano());
		}
		if (desc.getGanhadorPrimeiraRodada() != null) {
			Attribute ganhadorPrimeiraRodada = new Attribute("ganhadorPrimeiraRodada", TrucoDescription.class);
			simConfig.addMapping(ganhadorPrimeiraRodada, new Equal());// Interval(2)); // jogador
			simConfig.setWeight(ganhadorPrimeiraRodada, pesos.getPesoGanhadorPrimeiraRodada());
		}
		if (desc.getGanhadorSegundaRodada() != null) {
			Attribute ganhadorSegundaRodada = new Attribute("ganhadorSegundaRodada", TrucoDescription.class);
			simConfig.addMapping(ganhadorSegundaRodada, new Equal());// Interval(2)); // jogador
			simConfig.setWeight(ganhadorSegundaRodada, pesos.getPesoGanhadorSegundaRodada());
		}
		if (desc.getQuemTruco() != null) {
			Attribute quemTruco = new Attribute("quemTruco", TrucoDescription.class);
			simConfig.addMapping(quemTruco, new Equal()); // jogador
			simConfig.setWeight(quemTruco, pesos.getPesoQuemTruco());
		}
		if (desc.getQuemRetruco() != null) {
			Attribute quemRetruco = new Attribute("quemRetruco", TrucoDescription.class);
			simConfig.addMapping(quemRetruco, new Equal()); // jogador
			simConfig.setWeight(quemRetruco, pesos.getPesoQuemRetruco());
		}
		if (desc.getQuemValeQuatro() != null) {
			Attribute quemValeQuatro = new Attribute("quemValeQuatro", TrucoDescription.class);
			simConfig.addMapping(quemValeQuatro, new Equal()); // jogador
			simConfig.setWeight(quemValeQuatro, pesos.getPesoQuemValeQuatro());
		}

		if (desc.getQuandoTruco() != null) {

			Attribute quandoTruco = new Attribute("quandoTruco", TrucoDescription.class);

			simConfig.addMapping(quandoTruco, new Equal()); // jogador

			simConfig.setWeight(quandoTruco, pesos.getPesoQuandoTruco());

		}

		if (desc.getQuandoRetruco() != null) {

			Attribute quandoRetruco = new Attribute("quandoRetruco", TrucoDescription.class);

			simConfig.addMapping(quandoRetruco, new Equal()); // jogador

			simConfig.setWeight(quandoRetruco, pesos.getPesoQuandoRetruco());

		}

		if (desc.getQuemValeQuatro() != null) {

			Attribute quandoValeQuatro = new Attribute("quandoValeQuatro", TrucoDescription.class);

			simConfig.addMapping(quandoValeQuatro, new Equal()); // jogador

			simConfig.setWeight(quandoValeQuatro, pesos.getPesoQuandoValeQuatro());

		}

		if (desc.getTentosAnterioresRobo() != null && !desc.getTentosAnterioresRobo().equals(0)) {

			Attribute tentosAnterioresRobo = new Attribute("tentosAnterioresRobo", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresRobo, new Interval(24)); // numero
			//simConfig.addMapping(tentosAnterioresRobo, new Equal()); // numero
			simConfig.setWeight(tentosAnterioresRobo, pesos.getPesoTentosAnterioresRobo());
		}
		if (desc.getTentosAnterioresHumano() != null && !desc.getTentosAnterioresHumano().equals(0)) {

			Attribute tentosAnterioresHumano = new Attribute("tentosAnterioresHumano", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresHumano, new Interval(24)); // numero
			//simConfig.addMapping(tentosAnterioresHumano, new Equal()); // numero
			simConfig.setWeight(tentosAnterioresHumano, pesos.getPesoTentosAnterioresHumano());
		}


		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult>  executeQueryActiveLearning(Collection<CBRCase> cases, CBRQuery query)
			throws ExecutionException {

		TrucoDescription desc = (TrucoDescription) query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		PesosConsulta pesos = new PesosConsulta();

		if (desc.getBluff1Success() != null && !desc.getBluff1Success().equals(-1)) {

			Attribute bluff1Success = new Attribute("bluff1Success", TrucoDescription.class);
			simConfig.addMapping(bluff1Success, new Equal()); // numero
			simConfig.setWeight(bluff1Success, pesos.getBluff1Success());
		}

		if (desc.getBluff2Success() != null && !desc.getBluff2Success().equals(-1)) {

			Attribute bluff2Success = new Attribute("bluff2Success", TrucoDescription.class);
			simConfig.addMapping(bluff2Success, new Equal()); // numero
			simConfig.setWeight(bluff2Success, pesos.getBluff1Success());
		}

		if (desc.getBluff3Success() != null && !desc.getBluff3Success().equals(-1)) {

			Attribute bluff3Success = new Attribute("bluff3Success", TrucoDescription.class);
			simConfig.addMapping(bluff3Success, new Equal()); // numero
			simConfig.setWeight(bluff3Success, pesos.getBluff1Success());
		}

		if (desc.getBluff4Success() != null && !desc.getBluff4Success().equals(-1)) {

			Attribute bluff4Success = new Attribute("bluff4Success", TrucoDescription.class);
			simConfig.addMapping(bluff4Success, new Equal()); // numero
			simConfig.setWeight(bluff4Success, pesos.getBluff1Success());
		}

		if (desc.getBluff5Success() != null && !desc.getBluff5Success().equals(-1)) {

			Attribute bluff5Success = new Attribute("bluff5Success", TrucoDescription.class);
			simConfig.addMapping(bluff5Success, new Equal()); // numero
			simConfig.setWeight(bluff5Success, pesos.getBluff1Success());
		}

		if (desc.getBluff6Success() != null && !desc.getBluff6Success().equals(-1)) {

			Attribute bluff6Success = new Attribute("bluff6Success", TrucoDescription.class);
			simConfig.addMapping(bluff6Success, new Equal()); // numero
			simConfig.setWeight(bluff6Success, pesos.getBluff1Success());
		}

		if (desc.getBluff1Failure() != null && !desc.getBluff1Failure().equals(-1)) {

			Attribute bluff1Failure = new Attribute("bluff1Failure", TrucoDescription.class);
			simConfig.addMapping(bluff1Failure, new Equal()); // numero
			simConfig.setWeight(bluff1Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff2Failure() != null && !desc.getBluff2Failure().equals(-1)) {

			Attribute bluff2Failure = new Attribute("bluff2Failure", TrucoDescription.class);
			simConfig.addMapping(bluff2Failure, new Equal()); // numero
			simConfig.setWeight(bluff2Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff3Failure() != null && !desc.getBluff3Failure().equals(-1)) {

			Attribute bluff3Failure = new Attribute("bluff3Failure", TrucoDescription.class);
			simConfig.addMapping(bluff3Failure, new Equal()); // numero
			simConfig.setWeight(bluff3Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff4Failure() != null && !desc.getBluff4Failure().equals(-1)) {

			Attribute bluff4Failure = new Attribute("bluff4Failure", TrucoDescription.class);
			simConfig.addMapping(bluff4Failure, new Equal()); // numero
			simConfig.setWeight(bluff4Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff5Failure() != null && !desc.getBluff5Failure().equals(-1)) {

			Attribute bluff5Failure = new Attribute("bluff5Failure", TrucoDescription.class);
			simConfig.addMapping(bluff5Failure, new Equal()); // numero
			simConfig.setWeight(bluff5Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff6Failure() != null && !desc.getBluff6Failure().equals(-1)) {

			Attribute bluff6Failure = new Attribute("bluff6Failure", TrucoDescription.class);
			simConfig.addMapping(bluff6Failure, new Equal()); // numero
			simConfig.setWeight(bluff6Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff1Opponent() != null && !desc.getBluff1Opponent().equals(-1)) {

			Attribute bluff1Opponent = new Attribute("bluff1Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff1Opponent, new Equal()); // numero
			simConfig.setWeight(bluff1Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff2Opponent() != null && !desc.getBluff2Opponent().equals(-1)) {

			Attribute bluff2Opponent = new Attribute("bluff2Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff2Opponent, new Equal()); // numero
			simConfig.setWeight(bluff2Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff3Opponent() != null && !desc.getBluff3Opponent().equals(-1)) {

			Attribute bluff3Opponent = new Attribute("bluff3Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff3Opponent, new Equal()); // numero
			simConfig.setWeight(bluff3Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff4Opponent() != null && !desc.getBluff4Opponent().equals(-1)) {

			Attribute bluff4Opponent = new Attribute("bluff4Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff4Opponent, new Equal()); // numero
			simConfig.setWeight(bluff4Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff5Opponent() != null && !desc.getBluff5Opponent().equals(-1)) {

			Attribute bluff5Opponent = new Attribute("bluff5Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff5Opponent, new Equal()); // numero
			simConfig.setWeight(bluff5Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff6Opponent() != null && !desc.getBluff6Opponent().equals(-1)) {

			Attribute bluff6Opponent = new Attribute("bluff6Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff6Opponent, new Equal()); // numero
			simConfig.setWeight(bluff6Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff1ShowDown() != null && !desc.getBluff1ShowDown().equals(-1)) {

			Attribute bluff1ShowDown = new Attribute("bluff1ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff1ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff1ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff2ShowDown() != null && !desc.getBluff2ShowDown().equals(-1)) {

			Attribute bluff2ShowDown = new Attribute("bluff2ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff2ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff2ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff3ShowDown() != null && !desc.getBluff3ShowDown().equals(-1)) {

			Attribute bluff3ShowDown = new Attribute("bluff3ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff3ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff3ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff4ShowDown() != null && !desc.getBluff4ShowDown().equals(-1)) {

			Attribute bluff4ShowDown = new Attribute("bluff4ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff4ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff4ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff5ShowDown() != null && !desc.getBluff5ShowDown().equals(-1)) {

			Attribute bluff5ShowDown = new Attribute("bluff5ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff5ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff5ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff6ShowDown() != null && !desc.getBluff6ShowDown().equals(-1)) {

			Attribute bluff6ShowDown = new Attribute("bluff6ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff6ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff6ShowDown, pesos.getBluff1Failure());
		}


		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());

		return eval;

	}

	default Collection<RetrievalResult> executeQueryJogadaClusterActiveLearning(Collection<CBRCase> cases, CBRQuery query)
			throws ExecutionException {

		TrucoDescription desc = (TrucoDescription) query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		PesosConsulta pesos = new PesosConsulta();
		/*if (desc.getJogadorMao() != null) {
			Attribute jogadorMao = new Attribute("jogadorMao", TrucoDescription.class);

			simConfig.addMapping(jogadorMao, new Equal()); // jogador
			simConfig.setWeight(jogadorMao, pesos.getPesoJogadorMao());
		}*/
		if (desc.getCartaAltaRobo() != null) {
			Attribute cartaAltaRobo = new Attribute("cartaAltaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaAltaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaAltaRobo, pesos.getPesoCartaAltaRobo());
		}
		if (desc.getCartaMediaRobo() != null) {
			Attribute cartaMediaRobo = new Attribute("cartaMediaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaMediaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaMediaRobo, pesos.getPesoCartaMediaRobo());
		}
		if (desc.getCartaBaixaRobo() != null) {
			Attribute cartaBaixaRobo = new Attribute("cartaBaixaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaBaixaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaBaixaRobo, pesos.getPesoCartaBaixaRobo());
		}

		/*if (desc.getPrimeiraCartaRobo() != null) {
			Attribute primeiraCartaRobo = new Attribute("primeiraCartaRobo", TrucoDescription.class);
			simConfig.addMapping(primeiraCartaRobo, new Interval(52)); // carta
			simConfig.setWeight(primeiraCartaRobo, pesos.getPesoPrimeiraCartaRobo());
		}
		if (desc.getSegundaCartaRobo() != null) {
			Attribute segundaCartaRobo = new Attribute("segundaCartaRobo", TrucoDescription.class);
			simConfig.addMapping(segundaCartaRobo, new Interval(52)); // carta
			simConfig.setWeight(segundaCartaRobo, pesos.getPesoSegundaCartaRobo());
		}
		if (desc.getTerceiraCartaRobo() != null) {
			Attribute terceiraCartaRobo = new Attribute("terceiraCartaRobo", TrucoDescription.class);
			simConfig.addMapping(terceiraCartaRobo, new Interval(52)); // carta
			simConfig.setWeight(terceiraCartaRobo, pesos.getPesoTerceiraCartaRobo());
		}

		if (desc.getPrimeiraCartaHumano() != null) {
			Attribute primeiraCartaHumano = new Attribute("primeiraCartaHumano", TrucoDescription.class);
			simConfig.addMapping(primeiraCartaHumano, new Interval(52)); // carta
			simConfig.setWeight(primeiraCartaHumano, pesos.getPesoPrimeiraCartaHumano());
		}
		if (desc.getSegundaCartaHumano() != null) {
			Attribute segundaCartaHumano = new Attribute("segundaCartaHumano", TrucoDescription.class);
			simConfig.addMapping(segundaCartaHumano, new Interval(52)); // carta
			simConfig.setWeight(segundaCartaHumano, pesos.getPesoSegundaCartaHumano());
		}
		if (desc.getTerceiraCartaHumano() != null) {
			Attribute terceiraCartaHumano = new Attribute("terceiraCartaHumano", TrucoDescription.class);
			simConfig.addMapping(terceiraCartaHumano, new Interval(52)); // carta
			simConfig.setWeight(terceiraCartaHumano, pesos.getPesoTerceiraCartaHumano());
		}
		if (desc.getGanhadorPrimeiraRodada() != null) {
			Attribute ganhadorPrimeiraRodada = new Attribute("ganhadorPrimeiraRodada", TrucoDescription.class);
			simConfig.addMapping(ganhadorPrimeiraRodada, new Equal());// Interval(2)); // jogador
			simConfig.setWeight(ganhadorPrimeiraRodada, pesos.getPesoGanhadorPrimeiraRodada());
		}
		if (desc.getGanhadorSegundaRodada() != null) {
			Attribute ganhadorSegundaRodada = new Attribute("ganhadorSegundaRodada", TrucoDescription.class);
			simConfig.addMapping(ganhadorSegundaRodada, new Equal());// Interval(2)); // jogador
			simConfig.setWeight(ganhadorSegundaRodada, pesos.getPesoGanhadorSegundaRodada());
		}
		if (desc.getQuemTruco() != null) {
			Attribute quemTruco = new Attribute("quemTruco", TrucoDescription.class);
			simConfig.addMapping(quemTruco, new Equal()); // jogador
			simConfig.setWeight(quemTruco, pesos.getPesoQuemTruco());
		}
		if (desc.getQuemRetruco() != null) {
			Attribute quemRetruco = new Attribute("quemRetruco", TrucoDescription.class);
			simConfig.addMapping(quemRetruco, new Equal()); // jogador
			simConfig.setWeight(quemRetruco, pesos.getPesoQuemRetruco());
		}
		if (desc.getQuemValeQuatro() != null) {
			Attribute quemValeQuatro = new Attribute("quemValeQuatro", TrucoDescription.class);
			simConfig.addMapping(quemValeQuatro, new Equal()); // jogador
			simConfig.setWeight(quemValeQuatro, pesos.getPesoQuemValeQuatro());
		}

		if (desc.getQuandoTruco() != null) {

			Attribute quandoTruco = new Attribute("quandoTruco", TrucoDescription.class);

			simConfig.addMapping(quandoTruco, new Equal()); // jogador

			simConfig.setWeight(quandoTruco, pesos.getPesoQuandoTruco());

		}

		if (desc.getQuandoRetruco() != null) {

			Attribute quandoRetruco = new Attribute("quandoRetruco", TrucoDescription.class);

			simConfig.addMapping(quandoRetruco, new Equal()); // jogador

			simConfig.setWeight(quandoRetruco, pesos.getPesoQuandoRetruco());

		}

		if (desc.getQuemValeQuatro() != null) {

			Attribute quandoValeQuatro = new Attribute("quandoValeQuatro", TrucoDescription.class);

			simConfig.addMapping(quandoValeQuatro, new Equal()); // jogador

			simConfig.setWeight(quandoValeQuatro, pesos.getPesoQuandoValeQuatro());

		}*/

		if (desc.getTentosAnterioresRobo() != null && !desc.getTentosAnterioresRobo().equals(0)) {

			Attribute tentosAnterioresRobo = new Attribute("tentosAnterioresRobo", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresRobo, new Interval(24)); // numero
			simConfig.setWeight(tentosAnterioresRobo, pesos.getPesoTentosAnterioresRobo());
		}
		if (desc.getTentosAnterioresHumano() != null && !desc.getTentosAnterioresHumano().equals(0)) {

			Attribute tentosAnterioresHumano = new Attribute("tentosAnterioresHumano", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresHumano, new Interval(24)); // numero
			simConfig.setWeight(tentosAnterioresHumano, pesos.getPesoTentosAnterioresHumano());
		}

		if (desc.getBluff1Success() != null && !desc.getBluff1Success().equals(-1)) {

			Attribute bluff1Success = new Attribute("bluff1Success", TrucoDescription.class);
			simConfig.addMapping(bluff1Success, new Equal()); // numero
			simConfig.setWeight(bluff1Success, pesos.getBluff1Success());
		}

		if (desc.getBluff2Success() != null && !desc.getBluff2Success().equals(-1)) {

			Attribute bluff2Success = new Attribute("bluff2Success", TrucoDescription.class);
			simConfig.addMapping(bluff2Success, new Equal()); // numero
			simConfig.setWeight(bluff2Success, pesos.getBluff1Success());
		}

		if (desc.getBluff3Success() != null && !desc.getBluff3Success().equals(-1)) {

			Attribute bluff3Success = new Attribute("bluff3Success", TrucoDescription.class);
			simConfig.addMapping(bluff3Success, new Equal()); // numero
			simConfig.setWeight(bluff3Success, pesos.getBluff1Success());
		}

		if (desc.getBluff4Success() != null && !desc.getBluff4Success().equals(-1)) {

			Attribute bluff4Success = new Attribute("bluff4Success", TrucoDescription.class);
			simConfig.addMapping(bluff4Success, new Equal()); // numero
			simConfig.setWeight(bluff4Success, pesos.getBluff1Success());
		}

		if (desc.getBluff5Success() != null && !desc.getBluff5Success().equals(-1)) {

			Attribute bluff5Success = new Attribute("bluff5Success", TrucoDescription.class);
			simConfig.addMapping(bluff5Success, new Equal()); // numero
			simConfig.setWeight(bluff5Success, pesos.getBluff1Success());
		}

		if (desc.getBluff6Success() != null && !desc.getBluff6Success().equals(-1)) {

			Attribute bluff6Success = new Attribute("bluff6Success", TrucoDescription.class);
			simConfig.addMapping(bluff6Success, new Equal()); // numero
			simConfig.setWeight(bluff6Success, pesos.getBluff1Success());
		}

		if (desc.getBluff1Failure() != null && !desc.getBluff1Failure().equals(-1)) {

			Attribute bluff1Failure = new Attribute("bluff1Failure", TrucoDescription.class);
			simConfig.addMapping(bluff1Failure, new Equal()); // numero
			simConfig.setWeight(bluff1Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff2Failure() != null && !desc.getBluff2Failure().equals(-1)) {

			Attribute bluff2Failure = new Attribute("bluff2Failure", TrucoDescription.class);
			simConfig.addMapping(bluff2Failure, new Equal()); // numero
			simConfig.setWeight(bluff2Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff3Failure() != null && !desc.getBluff3Failure().equals(-1)) {

			Attribute bluff3Failure = new Attribute("bluff3Failure", TrucoDescription.class);
			simConfig.addMapping(bluff3Failure, new Equal()); // numero
			simConfig.setWeight(bluff3Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff4Failure() != null && !desc.getBluff4Failure().equals(-1)) {

			Attribute bluff4Failure = new Attribute("bluff4Failure", TrucoDescription.class);
			simConfig.addMapping(bluff4Failure, new Equal()); // numero
			simConfig.setWeight(bluff4Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff5Failure() != null && !desc.getBluff5Failure().equals(-1)) {

			Attribute bluff5Failure = new Attribute("bluff5Failure", TrucoDescription.class);
			simConfig.addMapping(bluff5Failure, new Equal()); // numero
			simConfig.setWeight(bluff5Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff6Failure() != null && !desc.getBluff6Failure().equals(-1)) {

			Attribute bluff6Failure = new Attribute("bluff6Failure", TrucoDescription.class);
			simConfig.addMapping(bluff6Failure, new Equal()); // numero
			simConfig.setWeight(bluff6Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff1Opponent() != null && !desc.getBluff1Opponent().equals(-1)) {

			Attribute bluff1Opponent = new Attribute("bluff1Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff1Opponent, new Equal()); // numero
			simConfig.setWeight(bluff1Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff2Opponent() != null && !desc.getBluff2Opponent().equals(-1)) {

			Attribute bluff2Opponent = new Attribute("bluff2Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff2Opponent, new Equal()); // numero
			simConfig.setWeight(bluff2Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff3Opponent() != null && !desc.getBluff3Opponent().equals(-1)) {

			Attribute bluff3Opponent = new Attribute("bluff3Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff3Opponent, new Equal()); // numero
			simConfig.setWeight(bluff3Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff4Opponent() != null && !desc.getBluff4Opponent().equals(-1)) {

			Attribute bluff4Opponent = new Attribute("bluff4Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff4Opponent, new Equal()); // numero
			simConfig.setWeight(bluff4Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff5Opponent() != null && !desc.getBluff5Opponent().equals(-1)) {

			Attribute bluff5Opponent = new Attribute("bluff5Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff5Opponent, new Equal()); // numero
			simConfig.setWeight(bluff5Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff6Opponent() != null && !desc.getBluff6Opponent().equals(-1)) {

			Attribute bluff6Opponent = new Attribute("bluff6Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff6Opponent, new Equal()); // numero
			simConfig.setWeight(bluff6Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff1ShowDown() != null && !desc.getBluff1ShowDown().equals(-1)) {

			Attribute bluff1ShowDown = new Attribute("bluff1ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff1ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff1ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff2ShowDown() != null && !desc.getBluff2ShowDown().equals(-1)) {

			Attribute bluff2ShowDown = new Attribute("bluff2ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff2ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff2ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff3ShowDown() != null && !desc.getBluff3ShowDown().equals(-1)) {

			Attribute bluff3ShowDown = new Attribute("bluff3ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff3ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff3ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff4ShowDown() != null && !desc.getBluff4ShowDown().equals(-1)) {

			Attribute bluff4ShowDown = new Attribute("bluff4ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff4ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff4ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff5ShowDown() != null && !desc.getBluff5ShowDown().equals(-1)) {

			Attribute bluff5ShowDown = new Attribute("bluff5ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff5ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff5ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff6ShowDown() != null && !desc.getBluff6ShowDown().equals(-1)) {

			Attribute bluff6ShowDown = new Attribute("bluff6ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff6ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff6ShowDown, pesos.getBluff1Failure());
		}


		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> executeQueryPontoClusterActiveLearning(Collection<CBRCase> cases, CBRQuery query)
			throws ExecutionException {
		TrucoDescription desc = (TrucoDescription) query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		PesosConsulta pesos = new PesosConsulta();

		/*if (desc.getJogadorMao() != null && !desc.getJogadorMao().equals(0))
			simConfig.addMapping(new Attribute("jogadorMao", TrucoDescription.class), new Equal()); // jogador*/

		/*if (desc.getCartaAltaRobo() != null && !desc.getCartaAltaRobo().equals(0)) {
			Attribute cartaAltaRobo = new Attribute("cartaAltaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaAltaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaAltaRobo, pesos.getPesoCartaAltaRobo());
		}
		if (desc.getCartaMediaRobo() != null && !desc.getCartaMediaRobo().equals(0)) {
			Attribute cartaMediaRobo = new Attribute("cartaMediaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaMediaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaMediaRobo, pesos.getPesoCartaMediaRobo());
		}
		if (desc.getCartaBaixaRobo() != null && !desc.getCartaBaixaRobo().equals(0)) {
			Attribute cartaBaixaRobo = new Attribute("cartaBaixaRobo", TrucoDescription.class);
			simConfig.addMapping(cartaBaixaRobo, new Interval(52)); // carta
			simConfig.setWeight(cartaBaixaRobo, pesos.getPesoCartaBaixaRobo());
		}*/

		if (desc.getPrimeiraCartaHumano() != null && !desc.getPrimeiraCartaHumano().equals(0)) {
			Attribute primeiraCartaHumano = new Attribute("primeiraCartaHumano", TrucoDescription.class);
			simConfig.addMapping(primeiraCartaHumano, new Interval(52)); // carta
			simConfig.setWeight(primeiraCartaHumano, pesos.getPesoPrimeiraCartaHumano());
		}

		/*if (desc.getQuemPediuEnvido() != null && !desc.getQuemPediuEnvido().equals(0)) {
			Attribute quemPediuEnvido = new Attribute("quemPediuEnvido", TrucoDescription.class);
			simConfig.addMapping(quemPediuEnvido, new Equal()); // jogador
			simConfig.setWeight(quemPediuEnvido, pesos.getPesoQuemPediuEnvido());
		}
		if (desc.getQuemPediuRealEnvido() != null && !desc.getQuemPediuRealEnvido().equals(0)) {
			Attribute quemPediuRealEnvido = new Attribute("quemPediuRealEnvido", TrucoDescription.class);
			simConfig.addMapping(quemPediuRealEnvido, new Equal()); // jogador
			simConfig.setWeight(quemPediuRealEnvido, pesos.getPesoQuemPediuRealEnvido());
		}

		if (desc.getQuemPediuFaltaEnvido() != null && !desc.getQuemPediuFaltaEnvido().equals(0)) {
			Attribute quemPediuFaltaEnvido = new Attribute("quemPediuFaltaEnvido", TrucoDescription.class);
			simConfig.addMapping(quemPediuFaltaEnvido, new Equal()); // jogador
			simConfig.setWeight(quemPediuFaltaEnvido, pesos.getPesoQuemPediuFaltaEnvido());
		}
*/
		if (desc.getPontosEnvidoRobo() != null && !desc.getPontosEnvidoRobo().equals(0)) {

			Attribute pontosEnvidoRobo = new Attribute("pontosEnvidoRobo", TrucoDescription.class);
			simConfig.addMapping(pontosEnvidoRobo, new Equal()); // numero
			simConfig.setWeight(pontosEnvidoRobo, pesos.getPesoPontosEnvidoRobo());
		}
		if (desc.getTentosAnterioresRobo() != null && !desc.getTentosAnterioresRobo().equals(0)) {

			Attribute tentosAnterioresRobo = new Attribute("tentosAnterioresRobo", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresRobo, new Interval(24)); // numero
			simConfig.setWeight(tentosAnterioresRobo, pesos.getPesoTentosAnterioresRobo());
		}
		if (desc.getTentosAnterioresHumano() != null && !desc.getTentosAnterioresHumano().equals(0)) {

			Attribute tentosAnterioresHumano = new Attribute("tentosAnterioresHumano", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresHumano, new Interval(24)); // numero
			simConfig.setWeight(tentosAnterioresHumano, pesos.getPesoTentosAnterioresHumano());
		}

		if (desc.getBluff1Success() != null && !desc.getBluff1Success().equals(-1)) {

			Attribute bluff1Success = new Attribute("bluff1Success", TrucoDescription.class);
			simConfig.addMapping(bluff1Success, new Equal()); // numero
			simConfig.setWeight(bluff1Success, pesos.getBluff1Success());
		}

		if (desc.getBluff2Success() != null && !desc.getBluff2Success().equals(-1)) {

			Attribute bluff2Success = new Attribute("bluff2Success", TrucoDescription.class);
			simConfig.addMapping(bluff2Success, new Equal()); // numero
			simConfig.setWeight(bluff2Success, pesos.getBluff1Success());
		}

		if (desc.getBluff3Success() != null && !desc.getBluff3Success().equals(-1)) {

			Attribute bluff3Success = new Attribute("bluff3Success", TrucoDescription.class);
			simConfig.addMapping(bluff3Success, new Equal()); // numero
			simConfig.setWeight(bluff3Success, pesos.getBluff1Success());
		}

		if (desc.getBluff4Success() != null && !desc.getBluff4Success().equals(-1)) {

			Attribute bluff4Success = new Attribute("bluff4Success", TrucoDescription.class);
			simConfig.addMapping(bluff4Success, new Equal()); // numero
			simConfig.setWeight(bluff4Success, pesos.getBluff1Success());
		}

		if (desc.getBluff5Success() != null && !desc.getBluff5Success().equals(-1)) {

			Attribute bluff5Success = new Attribute("bluff5Success", TrucoDescription.class);
			simConfig.addMapping(bluff5Success, new Equal()); // numero
			simConfig.setWeight(bluff5Success, pesos.getBluff1Success());
		}

		if (desc.getBluff6Success() != null && !desc.getBluff6Success().equals(-1)) {

			Attribute bluff6Success = new Attribute("bluff6Success", TrucoDescription.class);
			simConfig.addMapping(bluff6Success, new Equal()); // numero
			simConfig.setWeight(bluff6Success, pesos.getBluff1Success());
		}

		if (desc.getBluff1Failure() != null && !desc.getBluff1Failure().equals(-1)) {

			Attribute bluff1Failure = new Attribute("bluff1Failure", TrucoDescription.class);
			simConfig.addMapping(bluff1Failure, new Equal()); // numero
			simConfig.setWeight(bluff1Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff2Failure() != null && !desc.getBluff2Failure().equals(-1)) {

			Attribute bluff2Failure = new Attribute("bluff2Failure", TrucoDescription.class);
			simConfig.addMapping(bluff2Failure, new Equal()); // numero
			simConfig.setWeight(bluff2Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff3Failure() != null && !desc.getBluff3Failure().equals(-1)) {

			Attribute bluff3Failure = new Attribute("bluff3Failure", TrucoDescription.class);
			simConfig.addMapping(bluff3Failure, new Equal()); // numero
			simConfig.setWeight(bluff3Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff4Failure() != null && !desc.getBluff4Failure().equals(-1)) {

			Attribute bluff4Failure = new Attribute("bluff4Failure", TrucoDescription.class);
			simConfig.addMapping(bluff4Failure, new Equal()); // numero
			simConfig.setWeight(bluff4Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff5Failure() != null && !desc.getBluff5Failure().equals(-1)) {

			Attribute bluff5Failure = new Attribute("bluff5Failure", TrucoDescription.class);
			simConfig.addMapping(bluff5Failure, new Equal()); // numero
			simConfig.setWeight(bluff5Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff6Failure() != null && !desc.getBluff6Failure().equals(-1)) {

			Attribute bluff6Failure = new Attribute("bluff6Failure", TrucoDescription.class);
			simConfig.addMapping(bluff6Failure, new Equal()); // numero
			simConfig.setWeight(bluff6Failure, pesos.getBluff1Failure());
		}

		if (desc.getBluff1Opponent() != null && !desc.getBluff1Opponent().equals(-1)) {

			Attribute bluff1Opponent = new Attribute("bluff1Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff1Opponent, new Equal()); // numero
			simConfig.setWeight(bluff1Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff2Opponent() != null && !desc.getBluff2Opponent().equals(-1)) {

			Attribute bluff2Opponent = new Attribute("bluff2Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff2Opponent, new Equal()); // numero
			simConfig.setWeight(bluff2Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff3Opponent() != null && !desc.getBluff3Opponent().equals(-1)) {

			Attribute bluff3Opponent = new Attribute("bluff3Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff3Opponent, new Equal()); // numero
			simConfig.setWeight(bluff3Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff4Opponent() != null && !desc.getBluff4Opponent().equals(-1)) {

			Attribute bluff4Opponent = new Attribute("bluff4Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff4Opponent, new Equal()); // numero
			simConfig.setWeight(bluff4Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff5Opponent() != null && !desc.getBluff5Opponent().equals(-1)) {

			Attribute bluff5Opponent = new Attribute("bluff5Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff5Opponent, new Equal()); // numero
			simConfig.setWeight(bluff5Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff6Opponent() != null && !desc.getBluff6Opponent().equals(-1)) {

			Attribute bluff6Opponent = new Attribute("bluff6Opponent", TrucoDescription.class);
			simConfig.addMapping(bluff6Opponent, new Equal()); // numero
			simConfig.setWeight(bluff6Opponent, pesos.getBluff1Failure());
		}

		if (desc.getBluff1ShowDown() != null && !desc.getBluff1ShowDown().equals(-1)) {

			Attribute bluff1ShowDown = new Attribute("bluff1ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff1ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff1ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff2ShowDown() != null && !desc.getBluff2ShowDown().equals(-1)) {

			Attribute bluff2ShowDown = new Attribute("bluff2ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff2ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff2ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff3ShowDown() != null && !desc.getBluff3ShowDown().equals(-1)) {

			Attribute bluff3ShowDown = new Attribute("bluff3ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff3ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff3ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff4ShowDown() != null && !desc.getBluff4ShowDown().equals(-1)) {

			Attribute bluff4ShowDown = new Attribute("bluff4ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff4ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff4ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff5ShowDown() != null && !desc.getBluff5ShowDown().equals(-1)) {

			Attribute bluff5ShowDown = new Attribute("bluff5ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff5ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff5ShowDown, pesos.getBluff1Failure());
		}

		if (desc.getBluff6ShowDown() != null && !desc.getBluff6ShowDown().equals(-1)) {

			Attribute bluff6ShowDown = new Attribute("bluff6ShowDown", TrucoDescription.class);
			simConfig.addMapping(bluff6ShowDown, new Equal()); // numero
			simConfig.setWeight(bluff6ShowDown, pesos.getBluff1Failure());
		}

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);
		eval = SelectCases.selectTopKRR(eval, cases.size());

		return eval;
	}

	default Collection<RetrievalResult> executeQueryPontoCluster(Collection<CBRCase> cases, CBRQuery query)
			throws ExecutionException {
		TrucoDescription desc = (TrucoDescription) query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		PesosConsulta pesos = new PesosConsulta();
		if (desc.getJogadorMao() != null && !desc.getJogadorMao().equals(0))
			simConfig.addMapping(new Attribute("jogadorMao", TrucoDescription.class), new Equal()); // jogador
		/*
		 * if (desc.getPrimeiraCartaHumano() != null) { Attribute primeiraCartaHumano =
		 * new Attribute("primeiraCartaHumano", TrucoDescription.class);
		 * simConfig.addMapping(primeiraCartaHumano, new Interval(52)); // carta
		 * simConfig.setWeight(primeiraCartaHumano, pesos.getPesoPrimeiraCartaHumano());
		 * }
		 */
		if (desc.getQuemPediuEnvido() != null && !desc.getQuemPediuEnvido().equals(0)) {
			Attribute quemPediuEnvido = new Attribute("quemPediuEnvido", TrucoDescription.class);
			simConfig.addMapping(quemPediuEnvido, new Equal()); // jogador
			simConfig.setWeight(quemPediuEnvido, pesos.getPesoQuemPediuEnvido());
		}
		if (desc.getQuemPediuRealEnvido() != null && !desc.getQuemPediuRealEnvido().equals(0)) {
			Attribute quemPediuRealEnvido = new Attribute("quemPediuRealEnvido", TrucoDescription.class);
			simConfig.addMapping(quemPediuRealEnvido, new Equal()); // jogador
			simConfig.setWeight(quemPediuRealEnvido, pesos.getPesoQuemPediuRealEnvido());
		}
		if (desc.getPontosEnvidoRobo() != null && !desc.getPontosEnvidoRobo().equals(0)) {

			Attribute pontosEnvidoRobo = new Attribute("pontosEnvidoRobo", TrucoDescription.class);
			simConfig.addMapping(pontosEnvidoRobo, new Equal()); // numero
			simConfig.setWeight(pontosEnvidoRobo, pesos.getPesoPontosEnvidoRobo());
		}
		if (desc.getTentosAnterioresRobo() != null && !desc.getTentosAnterioresRobo().equals(0)) {

			Attribute tentosAnterioresRobo = new Attribute("tentosAnterioresRobo", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresRobo, new Interval(24)); // numero
			//simConfig.addMapping(tentosAnterioresRobo, new Equal()); // numero
			simConfig.setWeight(tentosAnterioresRobo, pesos.getPesoTentosAnterioresRobo());
		}
		if (desc.getTentosAnterioresHumano() != null && !desc.getTentosAnterioresHumano().equals(0)) {

			Attribute tentosAnterioresHumano = new Attribute("tentosAnterioresHumano", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresHumano, new Interval(24)); // numero
			//simConfig.addMapping(tentosAnterioresHumano, new Equal()); // numero
			simConfig.setWeight(tentosAnterioresHumano, pesos.getPesoTentosAnterioresHumano());
		}

		/*if (desc.getTentosAnterioresHumano() != null && !desc.getTentosAnterioresHumano().equals(0)) {

			Attribute tentosAnterioresHumano = new Attribute("tentosAnterioresHumano", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresHumano, new Interval(24)); // numero
			simConfig.setWeight(tentosAnterioresHumano, pesos.getPesoTentosAnterioresHumano());
		}*/


		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);
		eval = SelectCases.selectTopKRR(eval, cases.size());

		return eval;
	}

	//====================================== Métodos Vargas ===================================

    default Collection<RetrievalResult> executeQueryPontoClusterEx(Collection<CBRCase> cases, CBRQuery query)
            throws ExecutionException {

	    TrucoDescription desc = (TrucoDescription) query.getDescription();
        NNConfig simConfig = new NNConfig();
        simConfig.setDescriptionSimFunction(new Average());
        PesosConsulta pesos = new PesosConsulta();
        if (desc.getJogadorMao() != null && !desc.getJogadorMao().equals(0))
            simConfig.addMapping(new Attribute("jogadorMao", TrucoDescription.class), new Equal()); // jogador

         if (desc.getPrimeiraCartaHumano() != null) {
             Attribute primeiraCartaHumano = new Attribute("primeiraCartaHumano", TrucoDescription.class);
            //simConfig.addMapping(primeiraCartaHumano, new Interval(52)); // carta
            simConfig.addMapping(primeiraCartaHumano, new Equal()); // carta
            simConfig.setWeight(primeiraCartaHumano, pesos.getPesoPrimeiraCartaHumano());
         }

        if (desc.getQuemPediuEnvido() != null && !desc.getQuemPediuEnvido().equals(0)) {
            Attribute quemPediuEnvido = new Attribute("quemPediuEnvido", TrucoDescription.class);
            simConfig.addMapping(quemPediuEnvido, new Equal()); // jogador
            simConfig.setWeight(quemPediuEnvido, pesos.getPesoQuemPediuEnvido());
        }

        if (desc.getQuemPediuRealEnvido() != null && !desc.getQuemPediuRealEnvido().equals(0)) {
            Attribute quemPediuRealEnvido = new Attribute("quemPediuRealEnvido", TrucoDescription.class);
            simConfig.addMapping(quemPediuRealEnvido, new Equal()); // jogador
            simConfig.setWeight(quemPediuRealEnvido, pesos.getPesoQuemPediuRealEnvido());
        }

        if (desc.getQuemPediuFaltaEnvido() != null && !desc.getQuemPediuFaltaEnvido().equals(0)) {
            Attribute quemPediuFaltaEnvido = new Attribute("quemPediuFaltaEnvido", TrucoDescription.class);
            simConfig.addMapping(quemPediuFaltaEnvido, new Equal()); // jogador
            simConfig.setWeight(quemPediuFaltaEnvido, pesos.getPesoQuemPediuFaltaEnvido());
        }

        if (desc.getPontosEnvidoRobo() != null && !desc.getPontosEnvidoRobo().equals(0)) {
            Attribute pontosEnvidoRobo = new Attribute("pontosEnvidoRobo", TrucoDescription.class);
            simConfig.addMapping(pontosEnvidoRobo, new Equal()); // numero
            simConfig.setWeight(pontosEnvidoRobo, pesos.getPesoPontosEnvidoRobo());
        }

        if (desc.getTentosAnterioresRobo() != null && !desc.getTentosAnterioresRobo().equals(0)) {
            Attribute tentosAnterioresRobo = new Attribute("tentosAnterioresRobo", TrucoDescription.class);
            simConfig.addMapping(tentosAnterioresRobo, new Equal()); // numero
            simConfig.setWeight(tentosAnterioresRobo, pesos.getPesoTentosAnterioresRobo());
        }

        if (desc.getTentosAnterioresHumano() != null && !desc.getTentosAnterioresHumano().equals(0)) {
            Attribute tentosAnterioresHumano = new Attribute("tentosAnterioresHumano", TrucoDescription.class);
            simConfig.addMapping(tentosAnterioresHumano, new Equal()); // numero
            simConfig.setWeight(tentosAnterioresHumano, pesos.getPesoTentosAnterioresHumano());
        }


        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);
        eval = SelectCases.selectTopKRR(eval, cases.size());

        return eval;
    }

    default Collection<RetrievalResult> executeQueryJogadaClusterEx(Collection<CBRCase> cases, CBRQuery query)
            throws ExecutionException {

        TrucoDescription desc = (TrucoDescription) query.getDescription();
        NNConfig simConfig = new NNConfig();
        simConfig.setDescriptionSimFunction(new Average());
        PesosConsulta pesos = new PesosConsulta();

        if (desc.getJogadorMao() != null) {
            Attribute jogadorMao = new Attribute("jogadorMao", TrucoDescription.class);
            simConfig.addMapping(jogadorMao, new Equal()); // jogador
            simConfig.setWeight(jogadorMao, pesos.getPesoJogadorMao());
        }

        if (desc.getCartaAltaRobo() != null) {
            Attribute cartaAltaRobo = new Attribute("cartaAltaRobo", TrucoDescription.class);
            //simConfig.addMapping(cartaAltaRobo, new Interval(52)); // carta
            simConfig.addMapping(cartaAltaRobo, new Equal()); // carta
            simConfig.setWeight(cartaAltaRobo, pesos.getPesoCartaAltaRobo());
        }

        if (desc.getCartaMediaRobo() != null) {
            Attribute cartaMediaRobo = new Attribute("cartaMediaRobo", TrucoDescription.class);
            //simConfig.addMapping(cartaMediaRobo, new Interval(52)); // carta
            simConfig.addMapping(cartaMediaRobo, new Equal()); // carta
            simConfig.setWeight(cartaMediaRobo, pesos.getPesoCartaMediaRobo());
        }

        if (desc.getCartaBaixaRobo() != null) {
            Attribute cartaBaixaRobo = new Attribute("cartaBaixaRobo", TrucoDescription.class);
            //simConfig.addMapping(cartaBaixaRobo, new Interval(52)); // carta
            simConfig.addMapping(cartaBaixaRobo, new Equal()); // carta
            simConfig.setWeight(cartaBaixaRobo, pesos.getPesoCartaBaixaRobo());
        }

        if (desc.getPrimeiraCartaRobo() != null) {
            Attribute primeiraCartaRobo = new Attribute("primeiraCartaRobo", TrucoDescription.class);
            //simConfig.addMapping(primeiraCartaRobo, new Interval(52)); // carta
            simConfig.addMapping(primeiraCartaRobo, new Equal()); // carta
            simConfig.setWeight(primeiraCartaRobo, pesos.getPesoPrimeiraCartaRobo());
        }

        if (desc.getSegundaCartaRobo() != null) {
            Attribute segundaCartaRobo = new Attribute("segundaCartaRobo", TrucoDescription.class);
            //simConfig.addMapping(segundaCartaRobo, new Interval(52)); // carta
            simConfig.addMapping(segundaCartaRobo, new Equal()); // carta
            simConfig.setWeight(segundaCartaRobo, pesos.getPesoSegundaCartaRobo());
        }

        if (desc.getTerceiraCartaRobo() != null) {
            Attribute terceiraCartaRobo = new Attribute("terceiraCartaRobo", TrucoDescription.class);
            simConfig.addMapping(terceiraCartaRobo, new Interval(52)); // carta
            simConfig.addMapping(terceiraCartaRobo, new Equal()); // carta
            simConfig.setWeight(terceiraCartaRobo, pesos.getPesoTerceiraCartaRobo());
        }

        if (desc.getPrimeiraCartaRoboClustering() != null) {
            Attribute primeiraCartaRoboClustering = new Attribute("primeiraCartaRoboClustering", TrucoDescription.class);
            simConfig.addMapping(primeiraCartaRoboClustering, new Equal()); // carta
            simConfig.setWeight(primeiraCartaRoboClustering, pesos.getPesoPrimeiraCartaRobo());
        }

        if (desc.getSegundaCartaRoboClustering() != null) {
            Attribute segundaCartaRoboClustering = new Attribute("segundaCartaRoboClustering", TrucoDescription.class);
            simConfig.addMapping(segundaCartaRoboClustering, new Equal()); // carta
            simConfig.setWeight(segundaCartaRoboClustering, pesos.getPesoSegundaCartaRobo());
        }

        if (desc.getTerceiraCartaRoboClustering() != null) {
            Attribute terceiraCartaRoboClustering = new Attribute("terceiraCartaRoboClustering", TrucoDescription.class);
            simConfig.addMapping(terceiraCartaRoboClustering, new Equal()); // carta
            simConfig.setWeight(terceiraCartaRoboClustering, pesos.getPesoTerceiraCartaRobo());
        }

        if (desc.getPrimeiraCartaHumano() != null) {
            Attribute primeiraCartaHumano = new Attribute("primeiraCartaHumano", TrucoDescription.class);
            //simConfig.addMapping(primeiraCartaHumano, new Interval(52)); // carta
            simConfig.addMapping(primeiraCartaHumano, new Equal()); // carta
            simConfig.setWeight(primeiraCartaHumano, pesos.getPesoPrimeiraCartaHumano());
        }

        if (desc.getSegundaCartaHumano() != null) {
            Attribute segundaCartaHumano = new Attribute("segundaCartaHumano", TrucoDescription.class);
            //simConfig.addMapping(segundaCartaHumano, new Interval(52)); // carta
            simConfig.addMapping(segundaCartaHumano, new Equal()); // carta
            simConfig.setWeight(segundaCartaHumano, pesos.getPesoSegundaCartaHumano());
        }

        if (desc.getTerceiraCartaHumano() != null) {
            Attribute terceiraCartaHumano = new Attribute("terceiraCartaHumano", TrucoDescription.class);
            simConfig.addMapping(terceiraCartaHumano, new Interval(52)); // carta
            simConfig.addMapping(terceiraCartaHumano, new Equal()); // carta
            simConfig.setWeight(terceiraCartaHumano, pesos.getPesoTerceiraCartaHumano());
        }

        if (desc.getGanhadorPrimeiraRodada() != null) {
            Attribute ganhadorPrimeiraRodada = new Attribute("ganhadorPrimeiraRodada", TrucoDescription.class);
            simConfig.addMapping(ganhadorPrimeiraRodada, new Equal());// Interval(2)); // jogador
            simConfig.setWeight(ganhadorPrimeiraRodada, pesos.getPesoGanhadorPrimeiraRodada());
        }

        if (desc.getGanhadorSegundaRodada() != null) {
            Attribute ganhadorSegundaRodada = new Attribute("ganhadorSegundaRodada", TrucoDescription.class);
            simConfig.addMapping(ganhadorSegundaRodada, new Equal());// Interval(2)); // jogador
            simConfig.setWeight(ganhadorSegundaRodada, pesos.getPesoGanhadorSegundaRodada());
        }

        if (desc.getQuemTruco() != null) {
            Attribute quemTruco = new Attribute("quemTruco", TrucoDescription.class);
            simConfig.addMapping(quemTruco, new Equal()); // jogador
            simConfig.setWeight(quemTruco, pesos.getPesoQuemTruco());
        }

        if (desc.getQuemRetruco() != null) {
            Attribute quemRetruco = new Attribute("quemRetruco", TrucoDescription.class);
            simConfig.addMapping(quemRetruco, new Equal()); // jogador
            simConfig.setWeight(quemRetruco, pesos.getPesoQuemRetruco());
        }

        if (desc.getQuemValeQuatro() != null) {
            Attribute quemValeQuatro = new Attribute("quemValeQuatro", TrucoDescription.class);
            simConfig.addMapping(quemValeQuatro, new Equal()); // jogador
            simConfig.setWeight(quemValeQuatro, pesos.getPesoQuemValeQuatro());
        }

        if (desc.getQuandoTruco() != null) {
            Attribute quandoTruco = new Attribute("quandoTruco", TrucoDescription.class);
            simConfig.addMapping(quandoTruco, new Equal()); // jogador
            simConfig.setWeight(quandoTruco, pesos.getPesoQuandoTruco());
        }

        if (desc.getQuandoRetruco() != null) {
            Attribute quandoRetruco = new Attribute("quandoRetruco", TrucoDescription.class);
            simConfig.addMapping(quandoRetruco, new Equal()); // jogador
            simConfig.setWeight(quandoRetruco, pesos.getPesoQuandoRetruco());
        }

        if (desc.getQuemValeQuatro() != null) {
            Attribute quandoValeQuatro = new Attribute("quandoValeQuatro", TrucoDescription.class);
            simConfig.addMapping(quandoValeQuatro, new Equal()); // jogador
            simConfig.setWeight(quandoValeQuatro, pesos.getPesoQuandoValeQuatro());
        }

        if (desc.getTentosAnterioresRobo() != null && !desc.getTentosAnterioresRobo().equals(0)) {

            Attribute tentosAnterioresRobo = new Attribute("tentosAnterioresRobo", TrucoDescription.class);
            //simConfig.addMapping(tentosAnterioresRobo, new Interval(24)); // numero
            simConfig.addMapping(tentosAnterioresRobo, new Equal()); // numero
            simConfig.setWeight(tentosAnterioresRobo, pesos.getPesoTentosAnterioresRobo());
        }
        if (desc.getTentosAnterioresHumano() != null && !desc.getTentosAnterioresHumano().equals(0)) {

            Attribute tentosAnterioresHumano = new Attribute("tentosAnterioresHumano", TrucoDescription.class);
            //simConfig.addMapping(tentosAnterioresHumano, new Interval(24)); // numero
            simConfig.addMapping(tentosAnterioresHumano, new Equal()); // numero
            simConfig.setWeight(tentosAnterioresHumano, pesos.getPesoTentosAnterioresHumano());
        }


        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

        eval = SelectCases.selectTopKRR(eval, cases.size());
        return eval;
    }

    ////====================================Fim Métodos Vargas

	default Collection<RetrievalResult> executeQueryContraFlor(Collection<CBRCase> cases, CBRQuery query)
			throws ExecutionException {
		TrucoDescription desc = (TrucoDescription) query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		PesosConsulta pesos = new PesosConsulta();
		if (desc.getPontosFlorRobo() != null) {
			Attribute pontosFlorRobo = new Attribute("pontosFlorRobo", TrucoDescription.class);
			simConfig.addMapping(pontosFlorRobo, new Interval(38)); // jogador
			simConfig.setWeight(pontosFlorRobo, pesos.getPesoPontosFlorRobo());
		}
		if (desc.getTentosAnterioresHumano() != null) {
			Attribute tentosAnterioresHumano = new Attribute("tentosAnterioresHumano", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresHumano, new Interval(24)); // carta
			simConfig.setWeight(tentosAnterioresHumano, pesos.getPesoTentosAnterioresHumano());
		}
		if (desc.getTentosAnterioresRobo() != null) {
			Attribute tentosAnterioresRobo = new Attribute("tentosAnterioresRobo", TrucoDescription.class);
			simConfig.addMapping(tentosAnterioresRobo, new Interval(24)); // carta
			simConfig.setWeight(tentosAnterioresRobo, pesos.getPesoTentosAnterioresRobo());
		}
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);

		eval = SelectCases.selectTopKRR(eval, cases.size());
		return eval;
	}

	default Collection<RetrievalResult> getBestResultCluster(Collection<CBRCase> cases, TrucoDescription game_state,
															 int tipoConsulta) {
		Collection<RetrievalResult> results = null;
		try {
			TrucoDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);
			// verifica qual query executar


			switch (tipoConsulta) {
				// se não encontrar casos similares para flor pode estar aqui o problema
				case contraflor:
					results = executeQueryContraFlor(cases, query);
					break;
				case cartaCluster:
					results = executeQueryJogadaCluster(cases, query);
					break;
				case pontoCluster:
					results = executeQueryPontoCluster(cases, query);
					break;
                case pontoClusterEx:
                    results = executeQueryPontoClusterEx(cases, query);
                    break;
				case pontoClusterAL:
					results = executeQueryPontoClusterActiveLearning(cases, query);
					break;
				case trucoClusterAL:
					results = executeQueryJogadaClusterActiveLearning(cases, query);
					break;
				case active:
					results = executeQueryActiveLearning(cases, query);
					break;
				case trucoCluster:
					results = executeQueryJogadaCluster(cases, query);
					break;
                case trucoCartaCluster:
                    results = executeQueryJogadaClusterEx(cases, query);
                    break;
				case recuperaGruposJogadas:
					results = executeQueryCasosQuePertencerAclusterJogadaCarta(cases, query);
					break;
				case recuperaGruposQuemTrucoPrimeiraMao:
					results = executeQueryCasosQuePertencerAclusterQuemTrucoPrimeiraMao(cases, query);
					break;
				case recuperaGruposQuemTrucoPrimeiraPe:
					results = executeQueryCasosQuePertencerAclusterQuemTrucoPrimeiraPe(cases, query);
					break;
				case recuperaGruposQuemTrucoSegundaGanhouAnterior:
					results = executeQueryCasosQuePertencerAclusterQuemTrucoSegundaGanhouAnterior(cases, query);
					break;
				case recuperaGruposQuemTrucoSegundaPerdeuAnterior:
					results = executeQueryCasosQuePertencerAclusterQuemTrucoSegundaPerdeuAnterior(cases, query);
					break;
				case recuperaGruposQuemTrucoTerceiraGanhouAnterior:
					results = executeQueryCasosQuePertencerAclusterQuemTrucoTerceiraGanhouAnterior(cases, query);
					break;
				case recuperaGruposQuemTrucoTerceiraPerdeuAnterior:
					results = executeQueryCasosQuePertencerAclusterQuemTrucoTerceiraPerdeuAnterior(cases, query);
					break;
				case recuperaGruposQuemEnvidoAgenteMao:
					results = executeQueryCasosQuePertencerAclusterQuemEnvidoAgenteMao(cases, query);
					break;
				case recuperaGruposQuemEnvidoAgentePe:
					results = executeQueryCasosQuePertencerAclusterQuemEnvidoAgentePe(cases, query);
					break;
				case recuperaGruposIndexacaoJogada:
					results = executeQueryCasosQuePertencerAindexacao(cases, query);
					break;
				case recuperaGruposIndexacaoEnvido:
					results = executeQueryCasosQuePertencerAindexacaoPontos(cases, query);
					break;
				default:
					break;
			}

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBR.class).error(e);
		}

		return results;
	}

	default void IncrementaUso(Collection<RetrievalResult> best) {

	}

	boolean aceitarEnvido(TrucoDescription gameStateRobo, int rodada);

	boolean chamarEnvido(TrucoDescription gameStateRobo, int rodada);

	boolean chamarRealEnvido(TrucoDescription gameStateRobo, int rodada);

	boolean chamarFaltaEnvido(TrucoDescription gameStateRobo, int rodada);

	boolean aceitarRealEnvido(TrucoDescription gameStateRobo, int rodada);

	boolean aceitarFaltaEnvido(TrucoDescription gameStateRobo, int rodada);

	boolean chamarTruco(TrucoDescription gameStateRobo, int rodada);

	boolean chamarReTruco(TrucoDescription gameStateRobo, int rodada);

	boolean chamarValeQuatro(TrucoDescription gameStateRobo, int rodada);

	boolean aceitarTruco(TrucoDescription gameStateRobo, int rodada);

	boolean aceitarReTruco(TrucoDescription gameStateRobo, int rodada);

	boolean aceitarValeQuatro(TrucoDescription gameStateRobo, int rodada);

	int primeiraCarta(TrucoDescription gameStateRobo, int rodada);

	int segundaCarta(TrucoDescription gameStateRobo, int rodada);

	int terceiraCarta(TrucoDescription gameState, int rodada);

	boolean cartaVirada(TrucoDescription gameStateRobo, int rodada);

	boolean aceitarContraFlor(TrucoDescription gameStateRobo, int rodada);

	boolean aceitarContraFlorResto(TrucoDescription gameStateRobo, int rodada);

	boolean chamarContraFlor(TrucoDescription gameStateRobo, int rodada);

	boolean chamarContraFlorResto(TrucoDescription gameStateRobo, int rodada);

	boolean irAoBaralho(TrucoDescription gameStateRobo, int rodada);

	Collection<RetrievalResult> retornaRecuperadosFiltradoPontos(TrucoDescription gamestate, double threshold);

	Collection<RetrievalResult> retornaRecuperadosFiltradosTruco(TrucoDescription gamestate, double threshold, int rodada);

	Collection<RetrievalResult> retornaRecuperadosFiltradosPrimeiraCarta(TrucoDescription gamestate, double threshold, int jogadorMao);

	List<TrucoDescription> retornaRecuperadosFiltradosSegundaCarta(TrucoDescription gamestate, double threshold);

	Collection<RetrievalResult> retornaRecuperadosFiltradosTerceiraCarta(TrucoDescription gamestate, double threshold);

	default double calculoMedSim(Collection<RetrievalResult> casos) {
		double media;
		double soma = 0;
		int quant = 0;
		for (RetrievalResult R : casos) {
			soma = soma + R.getEval();
			quant++;
		}
		media = soma / quant;
		return media;
	}

	default double calculoPiorSim(Collection<RetrievalResult> casos) {
		double sim = 1;
		for (RetrievalResult R : casos) {
//			////////.out.println(R.getEval());
			if (R.getEval() < sim)
				sim = R.getEval();
		}
		return sim;
	}

	default double calculoMelhorSim(Collection<RetrievalResult> casos) {
		double sim = 0;
		for (RetrievalResult R : casos) {
			if (R.getEval() > sim)
				sim = R.getEval();
		}
		return sim;
	}

	boolean selecaoJogada(int Nao, int SimGanhou, int SimPerdeu);

	boolean selecaoJogadaVitoria(int Ganhou, int Perdeu);

	void retain(TrucoDescription newCase);

	default TrucoDescription selecionaPiorSim(Collection<RetrievalResult> results) {
		RetrievalResult selected = null;
		double sim = 1;
		for (RetrievalResult r : results) {
			if (r.getEval() < sim) {
				selected = r;
				sim = r.getEval();
			}
		}
		TrucoDescription Caso = (TrucoDescription) selected.get_case().getDescription();
		Caso.setSimilaridadeCaso(selected.getEval());
		return Caso;
	}

	default TrucoDescription selecionaMelhorSim(Collection<RetrievalResult> results) {
		RetrievalResult selected = new RetrievalResult(new CBRCase(), 0.0);
//		 ////////.out.println(results);
		double sim = 0;
		for (RetrievalResult r : results) {
			if (r.getEval() >= sim) {
				selected = r;
				sim = r.getEval();
			}
		}
//		 ////////.out.println(selected);
		TrucoDescription Caso = (TrucoDescription) selected.get_case().getDescription();
		Caso.setSimilaridadeCaso(selected.getEval());
		return Caso;
	}

	default boolean jogadaAleatoria(double Prob) {
		// double sorteio = Math.random();
//		////////.out.println("Jogada Aleatoria   " + sorteio);
		return Math.random() < Prob;
	}

	default int primeiraCartaAleatoria(Double probjogada, TrucoDescription gameState) {
		int altaRobo = gameState.getCartaAltaRobo();
		int mediaRobo = gameState.getCartaMediaRobo();
		int baixaRobo = gameState.getCartaBaixaRobo();
		int carta = ThreadLocalRandom.current().nextInt(0, 3);
//		////////.out.println("carta aleatoria " + carta);
		if (carta == 0)
			return altaRobo;
		if (carta == 1)
			return mediaRobo;
		return baixaRobo;
	}

	default int segundaCartaAleatoria(Double probjogada, TrucoDescription gameState) {
		int primeiraJogada = gameState.getPrimeiraCartaRobo();
		int altaRobo = gameState.getCartaAltaRobo();
		int mediaRobo = gameState.getCartaMediaRobo();
		int baixaRobo = gameState.getCartaBaixaRobo();
		while (true) {
			int carta = ThreadLocalRandom.current().nextInt(0, 3);
			if (carta == 0 && primeiraJogada != altaRobo)
				return altaRobo;
			if (carta == 1 && primeiraJogada != mediaRobo)
				return mediaRobo;
			if (carta == 2 && primeiraJogada != baixaRobo)
				return baixaRobo;
		}
	}

	void setAprendizagem(String tipo);

	void setRetencao(String tipo);

	default TrucoDescription swapAgent(TrucoDescription newCase) {
		TrucoDescription otherAgent = new TrucoDescription();
		otherAgent.setJogadorMao(complementar(newCase.getJogadorMao()));
		otherAgent.setCartaAltaHumano(newCase.getCartaAltaRobo());
		otherAgent.setCartaMediaHumano(newCase.getCartaMediaRobo());
		otherAgent.setCartaBaixaHumano(newCase.getCartaBaixaRobo());
		otherAgent.setPrimeiraCartaHumano(newCase.getPrimeiraCartaRobo());
		otherAgent.setSegundaCartaHumano(newCase.getSegundaCartaRobo());
		otherAgent.setTerceiraCartaHumano(newCase.getTerceiraCartaRobo());
		otherAgent.setPrimeiraCartaRobo(newCase.getPrimeiraCartaHumano());
		otherAgent.setSegundaCartaRobo(newCase.getSegundaCartaHumano());
		otherAgent.setTerceiraCartaRobo(newCase.getTerceiraCartaHumano());
		otherAgent.setGanhadorPrimeiraRodada(complementar(newCase.getGanhadorPrimeiraRodada()));
		otherAgent.setGanhadorSegundaRodada(complementar(newCase.getGanhadorSegundaRodada()));
		otherAgent.setPontosEnvidoHumano(newCase.getPontosEnvidoRobo());
		otherAgent.setQuemGanhouEnvido(complementar(newCase.getQuemGanhouEnvido()));
		otherAgent.setQuemPediuEnvido(complementar(newCase.getQuemPediuEnvido()));
		otherAgent.setQuemPediuRealEnvido(complementar(newCase.getQuemPediuRealEnvido()));
		otherAgent.setQuemTruco(complementar(newCase.getQuemTruco()));
		otherAgent.setQuemRetruco(complementar(newCase.getQuemRetruco()));
		otherAgent.setQuemValeQuatro(complementar(newCase.getQuemValeQuatro()));
		otherAgent.setTentosAnterioresHumano(newCase.getTentosAnterioresRobo());
		otherAgent.setTentosAnterioresRobo(newCase.getTentosAnterioresHumano());
		otherAgent.setNaipeCartaAltaHumano(newCase.getNaipeCartaAltaRobo());
		otherAgent.setNaipeCartaMediaHumano(newCase.getNaipeCartaMediaRobo());
		otherAgent.setNaipeCartaBaixaHumano(newCase.getNaipeCartaBaixaRobo());
		otherAgent.setPontosFlorHumano(newCase.getPontosFlorRobo());
		otherAgent.setTentosEnvido(newCase.getTentosEnvido());
		otherAgent.setTentosFlor(newCase.getTentosFlor());
		otherAgent.setTentosTruco(newCase.getTentosTruco());
		return otherAgent;
	}

	default Integer complementar(Integer i) {
		if (i == null) {
			return null;
		}
		if (i == 1)
			return 2;
		else if (i == 2)
			return 1;
		else
			return i;
	}

	default int complementaAtivo(int i) {
		if (i == 1)
			return 0;
		else
			return 1;
	}

	default int comparaSaldos(TrucoDescription best, TrucoDescription newCase) {
		int saldoRoboCaso = newCase.getTentosPosterioresRobo() - newCase.getTentosAnterioresRobo();
		int saldoHumanoCaso = newCase.getTentosPosterioresHumano() - newCase.getTentosAnterioresHumano();
		int saldoCaso = saldoRoboCaso - saldoHumanoCaso;
		int saldoRoboBest = best.getTentosPosterioresRobo() - best.getTentosAnterioresRobo();
		int saldoHumanoBest = best.getTentosPosterioresHumano() - best.getTentosAnterioresHumano();
		int saldoBest = saldoRoboBest - saldoHumanoBest;
		if (saldoBest > saldoCaso)
			return 0;
		else
			return 1;
	}

	default int comparaJogo(TrucoDescription best, TrucoDescription newCase) {
		if (best.getTentosTruco() > newCase.getTentosTruco())
			return 0;
		else
			return 1;
	}

	default int comparaFlores(TrucoDescription best, TrucoDescription newCase) {
		if (best.getTentosFlor() > newCase.getTentosFlor())
			return 0;
		else
			return 1;
	}

	default int comparaPontos(TrucoDescription best, TrucoDescription newCase) {
		if (best.getTentosEnvido() > newCase.getTentosEnvido())
			return 0;
		else
			return 1;
	}

	void fechaBase() throws ExecutionException;

	default Collection<RetrievalResult> FiltraResults(Collection<RetrievalResult> results) {
		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {
			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();
			if (actualResult.getEval() > thresholdParaAprender) {
				validados.add(actualResult);
			}
		}
		 return validados;
	}

	default Collection<CBRCase> FiltraAprendidosPeloAtivo(Collection<RetrievalResult> results) {

		Collection<CBRCase> casesFiltrados = new ArrayList<CBRCase>();

		Iterator iteratorCasosParaFiltrar = results.iterator();

		while (iteratorCasosParaFiltrar.hasNext()) {
			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();
			CBRCase casoAtual = (CBRCase) actualResult.get_case();
			TrucoDescription casoAtualDescription = (TrucoDescription) casoAtual.getDescription();
			//System.out.println(casoAtualDescription.getIdPartida());
			if (casoAtualDescription.getIdPartida().equalsIgnoreCase("aprendizado_ativo")) {
				casesFiltrados.add(casoAtual);
			}
		}
		return casesFiltrados;
	}

	default Collection<RetrievalResult> FiltraResultsFlor(Collection<RetrievalResult> results,
														  Double thresholdCluster) {
		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {
			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();
			if (actualResult.getEval() > thresholdCluster) {
				validados.add(actualResult);
			}
		}

		return validados;
	}

	default Collection<RetrievalResult> filtraResultsBlefe(Collection<RetrievalResult> results,
															Double thresholdCluster) {

		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {
			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();

			TrucoDescription casoAtual = (TrucoDescription) actualResult.get_case().getDescription();
			if (actualResult.getEval() >= thresholdCluster)
				validados.add(actualResult);
		}

		return validados;
	}

	default Collection<RetrievalResult> FiltraResultsEnvido(Collection<RetrievalResult> results,
															Double thresholdCluster, TrucoDescription gameState) {
		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {
			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();

			TrucoDescription casoAtual = (TrucoDescription) actualResult.get_case().getDescription();
			////System.out.println("jogado mão do caso recuperado: "+casoAtual.getJogadorMao());
			if (actualResult.getEval() >= thresholdCluster
					&& gameState.getJogadorMao().equals(casoAtual.getJogadorMao()))
				validados.add(actualResult);

		}

		return validados;
	}

	default Collection<RetrievalResult> FiltraResultsTruco(Collection<RetrievalResult> results,
														   Double thresholdCluster, TrucoDescription gameState, int rodada, int kminimo) {

		/*Cenários possíveis para chamar truco:
		 * primeiraCarta: agente mão, agente pé
		 * Segunda carta: agente ganhou a primeira, agente perdeu a primeira
		 * terceira carta: agente ganhou a segunda, agente perdeu a segunda
		 */
		int jogadorMao = gameState.getJogadorMao();
		if(rodada == 1 ) return FiltraResultsClusterPrimeiraCarta(results, thresholdCluster, gameState, jogadorMao);
		else if(rodada == 2) return FiltraResultsClusterSegundaCarta(results, thresholdCluster, gameState, kminimo);
		else if(rodada == 3) return FiltraResultsClusterTerceiraCarta(results, thresholdCluster, gameState, kminimo);
		else return null;


	}

	default Collection<RetrievalResult> FiltraResultsClusterPrimeiraCarta(Collection<RetrievalResult> results,
																		  Double thresholdCluster, TrucoDescription gameState, int jogadorMao) {
		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {
			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();
			TrucoDescription casoAtual = (TrucoDescription) actualResult.get_case().getDescription();

			if (actualResult.getEval() >= thresholdCluster
					&& gameState.getJogadorMao().equals(casoAtual.getJogadorMao()))
				validados.add(actualResult);

		}

		return validados;
	}

	default Collection<RetrievalResult> FiltraResultsClusterSegundaCarta(Collection<RetrievalResult> results,
																		 Double thresholdCluster, TrucoDescription gameState, int ktoReuse) {
		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {

			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();

			TrucoDescription casoAtual = (TrucoDescription) actualResult.get_case().getDescription();
			int ganhadorPrimeiraRodadaCasoAtualRecuperado = casoAtual.getGanhadorPrimeiraRodada();
			/*
			if (ganhadorPrimeiraRodadaCasoAtualRecuperado == 0)
				ganhadorPrimeiraRodadaCasoAtualRecuperado = casoAtual.getJogadorMao();
*/
			int ganhadorPrimeiraRodadaGameState = gameState.getGanhadorPrimeiraRodada();
			/*
			if (ganhadorPrimeiraRodadaGameState == 0)
				ganhadorPrimeiraRodadaGameState = casoAtual.getJogadorMao();
			*/
			if ((actualResult.getEval() >= thresholdCluster)
					&& (ganhadorPrimeiraRodadaCasoAtualRecuperado == ganhadorPrimeiraRodadaGameState)) {
				validados.add(actualResult);

			}
		}
		if(validados.size() < ktoReuse  && thresholdCluster <= 0.75) validados = FiltraResultsClusterSegundaCartaNaoConsideraEmpardou(results, thresholdCluster, gameState);

		return validados;
	}

	default Collection<RetrievalResult> FiltraResultsClusterTerceiraCarta(Collection<RetrievalResult> results,
																		  Double thresholdCluster, TrucoDescription gameState, int ktoReuse) {
		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {

			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();

			TrucoDescription casoAtual = (TrucoDescription) actualResult.get_case().getDescription();
			int ganhadorSegundaRodadaCasoAtualRecuperado = casoAtual.getGanhadorSegundaRodada();
			int ganhadorPrimeiraRodadaCasoRecuperado = casoAtual.getGanhadorPrimeiraRodada();
			/*
			if (ganhadorSegundaRodadaCasoAtualRecuperado == 0 && ganhadorPrimeiraRodadaCasoRecuperado !=0)
				ganhadorSegundaRodadaCasoAtualRecuperado = ganhadorPrimeiraRodadaCasoRecuperado;
			else if(ganhadorSegundaRodadaCasoAtualRecuperado == 0 &&  ganhadorPrimeiraRodadaCasoRecuperado == 0)
				ganhadorSegundaRodadaCasoAtualRecuperado = casoAtual.getJogadorMao();
			*/
			int ganhadorSegundaRodadaGameState = gameState.getGanhadorSegundaRodada();
			int ganhadorPrimeiraRodadaQuery = gameState.getGanhadorPrimeiraRodada();
			/*
			if (ganhadorSegundaRodadaGameState == 0 && ganhadorPrimeiraRodadaQuery != 0)
				ganhadorSegundaRodadaGameState = ganhadorPrimeiraRodadaQuery;
			else if (ganhadorSegundaRodadaGameState == 0 && ganhadorPrimeiraRodadaQuery == 0)
				ganhadorSegundaRodadaGameState = gameState.getJogadorMao();
			*/
			if ((actualResult.getEval() >= thresholdCluster)
					&& (ganhadorSegundaRodadaCasoAtualRecuperado == ganhadorSegundaRodadaGameState)) {
				validados.add(actualResult);

			}
		}
		if(validados.size() < ktoReuse) validados = FiltraResultsClusterTerceiraCartaNaoConsideraEmpardou(results, thresholdCluster, gameState);

		return validados;
	}

	default Collection<RetrievalResult> FiltraResultsClusterSegundaCartaNaoConsideraEmpardou(Collection<RetrievalResult> results,
																							 Double thresholdCluster, TrucoDescription gameState) {
		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {

			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();

			TrucoDescription casoAtual = (TrucoDescription) actualResult.get_case().getDescription();
			int ganhadorPrimeiraRodadaCasoAtualRecuperado = casoAtual.getGanhadorPrimeiraRodada();

			if (ganhadorPrimeiraRodadaCasoAtualRecuperado == 0)
				ganhadorPrimeiraRodadaCasoAtualRecuperado = casoAtual.getJogadorMao();

			int ganhadorPrimeiraRodadaGameState = gameState.getGanhadorPrimeiraRodada();

			if (ganhadorPrimeiraRodadaGameState == 0)
				ganhadorPrimeiraRodadaGameState = casoAtual.getJogadorMao();

			if ((actualResult.getEval() >= thresholdCluster)
					&& (ganhadorPrimeiraRodadaCasoAtualRecuperado == ganhadorPrimeiraRodadaGameState)) {
				validados.add(actualResult);

			}
		}
		return validados;
	}

	default Collection<RetrievalResult> FiltraResultsClusterTerceiraCartaNaoConsideraEmpardou(Collection<RetrievalResult> results,
																							  Double thresholdCluster, TrucoDescription gameState) {
		Collection<RetrievalResult> validados = new ArrayList<RetrievalResult>();
		Iterator iteratorCasosParaFiltrar = results.iterator();
		while (iteratorCasosParaFiltrar.hasNext()) {

			RetrievalResult actualResult = (RetrievalResult) iteratorCasosParaFiltrar.next();

			TrucoDescription casoAtual = (TrucoDescription) actualResult.get_case().getDescription();
			int ganhadorSegundaRodadaCasoAtualRecuperado = casoAtual.getGanhadorSegundaRodada();
			int ganhadorPrimeiraRodadaCasoRecuperado = casoAtual.getGanhadorPrimeiraRodada();

			if (ganhadorSegundaRodadaCasoAtualRecuperado == 0 && ganhadorPrimeiraRodadaCasoRecuperado !=0)
				ganhadorSegundaRodadaCasoAtualRecuperado = ganhadorPrimeiraRodadaCasoRecuperado;
			else if(ganhadorSegundaRodadaCasoAtualRecuperado == 0 &&  ganhadorPrimeiraRodadaCasoRecuperado == 0)
				ganhadorSegundaRodadaCasoAtualRecuperado = casoAtual.getJogadorMao();

			int ganhadorSegundaRodadaGameState = gameState.getGanhadorSegundaRodada();
			int ganhadorPrimeiraRodadaQuery = gameState.getGanhadorPrimeiraRodada();
			if (ganhadorSegundaRodadaGameState == 0 && ganhadorPrimeiraRodadaQuery != 0)
				ganhadorSegundaRodadaGameState = ganhadorPrimeiraRodadaQuery;
			else if (ganhadorSegundaRodadaGameState == 0 && ganhadorPrimeiraRodadaQuery == 0)
				ganhadorSegundaRodadaGameState = gameState.getJogadorMao();

			if ((actualResult.getEval() >= thresholdCluster)
					&& (ganhadorSegundaRodadaCasoAtualRecuperado == ganhadorSegundaRodadaGameState)) {
				validados.add(actualResult);

			}
		}
		return validados;
	}


	void realizaConfiguracoesIniciais();

	void realizaConfiguracoesIniciais2(String cartaAlta, String cartaMedia, String cartaBaixa);

	void setThreshold(double threshold);

	double getThreshold();

	boolean faltaConhecimentoParaAdecisao(TrucoDescription query, String tipoDaConsulta);

	void zeraGruposInformacoesRodadaFinalizada();

	void setTipoDecisao(String Tipo1, String tipoReusoIntraCluster1);
	void setReusoComCluster(boolean cluster);
	void setaGrupoMaisSimilarIndexadoJogada(TrucoDescription stateAgent1Jogada);
	void setaGrupoMaisSimilarIndexadoPontos(TrucoDescription stateAgent1Envido);
	void setAjusteAutomaticoDoK(boolean ajusteAutomaticoDoK);
	void autoAjustarK();

}
