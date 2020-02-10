package CbrQuerys;


import java.util.Collection;

import CentroidesModelo.ResultadoConsultaCentroidePrimeiraCartaRoboMaoModelo;
import CentroidesModelo.ResultadoConsultaCentroidePrimeiraCartaRoboPe;

import CentroidesModelo.ResultadoConsultaCentroideQuemGanhouEnvido;
import CentroidesModelo.ResultadoConsultaCentroideQuemTruco;
import CentroidesModelo.ResultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira;
import CentroidesModelo.ResultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira;
import CentroidesModelo.ResultadoConsultaTerceiraCartaRoboGanhouAsegunda;
import CentroidesModelo.ResultadoTerceiraCartaRoboPerdeuAsegunda;
import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.*;
import euclidean.GlobalEuclidean;
import euclidean.LocalEuclidean;
import jcolibri.casebase.CachedLinealCaseBaseGustavoNew;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBaseGustavo;
import jcolibri.cbrcore.CBRQuery;

import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnectorGustavoNew;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.InitializingException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;




import jcolibri.method.retrieve.selection.SelectCases;
import utils.debug.criaArquivosDebug;

public class CBRCentroides {
	static int centroidPrimimeiraCartaRoboMao = 1;
	static int centroidPrimimeiraCartaRoboPe = 2;
	static int centroidQuandoTruco = 3;
	static int centroidQuemGanhouEnvidoAgenteMao = 4;
	static int centroideQuemGanhouEnvidoAgentePe = 5;
	static int centroidQemTruco = 6;
	static int centroidSegundaCartaRoboGanhouAprimeira = 7;
	static int centroidSegundaCartaRoboPerdeuAprimeira = 8;
	static int centroidTerceiraCartaRoboGanhouAsegunda = 9;
	static int centroidTerceiraCartaRoboPerdeuAsegunda = 10;
	// recupera só o centroide do grupo mais similar
	int kcentroidessimilaresquedevemserreccuperados = 1;
	
	
	

	/**
	 * Cria um conector e define com qual base ele vai conectar Se foi passado Base
	 * = "Casos" conecta com a base de casos senao conecta com a base de results
	 * 
	 * @param Base Com qual base vai ser feito a conexao
	 * @return
	 * @throws ExecutionException
	 */
	public CBRCentroides() {
		//////////System.out.println("iniciou o construtor cbr centroides");
	}
	
public void initialize_conector(String centroide, String baseRetencao, CbrModular quemPediu) throws ExecutionException {
		
		try {


			String path = null;
			if (baseRetencao.equalsIgnoreCase("ativo")) {
				path = "cbr/HibernateAtivo/";
				if (centroide.equalsIgnoreCase("centroidePrimeiraCartaRoboMao") &&  quemPediu.get_connectorCentroidePrimeiraCartaRoboMaoAtivo() == null) {
					quemPediu.set_connectorCentroidePrimeiraCartaRoboMaoAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidePrimeiraCartaRoboMaoAtivo().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidPrimeiraCartaRoboMao.xml"));
				}
				else	if (centroide.equalsIgnoreCase("centroidePrimeiraCartaRoboPe") && quemPediu.get_connectorCentroidePrimeiraCartaRoboPeAtivo() == null) {
					quemPediu.set_connectorCentroidePrimeiraCartaRoboPeAtivo(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroidePrimeiraCartaRoboPeAtivo().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidPrimeiraCartaRoboPe.xml"));
				}
				
				else	if (centroide.equalsIgnoreCase("centroidQuemGanhouEnvidoAgenteMao") && quemPediu.get_connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo() == null) {
					quemPediu.set_connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidQuemGanhouEnvidoAgenteMao.xml"));
				}

				else if (centroide.equalsIgnoreCase("centroidQuemGanhouEnvidoAgentePe") && quemPediu.get_connectorCentroideQuemGanhouEnvidoAgentePeAtivo() == null) {
					quemPediu.set_connectorCentroideQuemGanhouEnvidoAgentePeAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemGanhouEnvidoAgentePeAtivo().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidQuemGanhouEnvidoAgentePe.xml"));
				}
				

				else if (centroide.equalsIgnoreCase("centroidQuemTrucoPrimeiraMao") && quemPediu.get_connectorCentroideQuemTrucoPrimeiraMaoAtivo() == null) {
					quemPediu.set_connectorCentroideQuemTrucoPrimeiraMaoAtivo(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoPrimeiraMaoAtivo().initFromXMLfile(jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoPrimeiraMao.xml"));
				}
				
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoPrimeiraPe") && quemPediu.get_connectorCentroideQuemTrucoPrimeiraPeAtivo() == null) {
					quemPediu.set_connectorCentroideQuemTrucoPrimeiraPeAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoPrimeiraPeAtivo().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoPrimeiraPe.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoSegundaGanhouAnterior") && quemPediu.get_connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo() == null) {
					quemPediu.set_connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoSegundaGanhouAnterior.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoSegundaPerdeuAnterior") && quemPediu.get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo() == null) {
					quemPediu.set_connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoSegundaPerdeuAnterior.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoTerceiraGanhouAnterior") && quemPediu.get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo() == null) {
					quemPediu.set_connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoTerceiraGanhouAnterior.xml"));
				}
				
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoTerceiraPerdeuAnterior") && quemPediu.get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo() == null) {
					quemPediu.set_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoTerceiraPerdeuAnterior.xml"));
				}
				
				
				
				
				
				else if (centroide.equalsIgnoreCase("centroidSegundaCartaRoboGanhouAprimeira") && quemPediu.get_connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo() == null) {
					quemPediu.set_connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidSegundaCartaRoboGanhouAprimeira.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidSegundaCartaRoboPerdeuAprimeira") && quemPediu.get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo() == null) {
					quemPediu.set_connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidSegundaCartaRoboPerdeuAprimeira.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidTerceiraCartaRoboGanhouAsegunda") && quemPediu.get_connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo() == null) {
					quemPediu.set_connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidTerceiraCartaRoboGanhouAsegunda.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidTerceiraCartaRoboPerdeuAsegunda") && quemPediu.get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo() == null) {
					quemPediu.set_connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidTerceiraCartaRoboPerdeuAsegunda.xml"));
				}

			}else if (baseRetencao.equalsIgnoreCase("imitacao")) {
				path = "cbr/HibernateImitacao/";
				if (centroide.equalsIgnoreCase("centroidePrimeiraCartaRoboMao") &&  quemPediu.get_connectorCentroidePrimeiraCartaRoboMaoImitacao() == null) {
					quemPediu.set_connectorCentroidePrimeiraCartaRoboMaoImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidePrimeiraCartaRoboMaoImitacao().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidPrimeiraCartaRoboMao.xml"));
				}
				else	if (centroide.equalsIgnoreCase("centroidePrimeiraCartaRoboPe") && quemPediu.get_connectorCentroidePrimeiraCartaRoboPeImitacao() == null) {
					quemPediu.set_connectorCentroidePrimeiraCartaRoboPeImitacao(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroidePrimeiraCartaRoboPeImitacao().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidPrimeiraCartaRoboPe.xml"));
				}
				
				else	if (centroide.equalsIgnoreCase("centroidQuemGanhouEnvidoAgenteMao") && quemPediu.get_connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao() == null) {
					quemPediu.set_connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidQuemGanhouEnvidoAgenteMao.xml"));
				}

				else if (centroide.equalsIgnoreCase("centroidQuemGanhouEnvidoAgentePe") && quemPediu.get_connectorCentroideQuemGanhouEnvidoAgentePeImitacao() == null) {
					quemPediu.set_connectorCentroideQuemGanhouEnvidoAgentePeImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemGanhouEnvidoAgentePeImitacao().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidQuemGanhouEnvidoAgentePe.xml"));
				}
				

				else if (centroide.equalsIgnoreCase("centroidQuemTrucoPrimeiraMao") && quemPediu.get_connectorCentroideQuemTrucoPrimeiraMaoImitacao() == null) {
					quemPediu.set_connectorCentroideQuemTrucoPrimeiraMaoImitacao(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoPrimeiraMaoImitacao().initFromXMLfile(jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoPrimeiraMao.xml"));
				}
				
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoPrimeiraPe") && quemPediu.get_connectorCentroideQuemTrucoPrimeiraPeImitacao() == null) {
					quemPediu.set_connectorCentroideQuemTrucoPrimeiraPeImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoPrimeiraPeImitacao().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoPrimeiraPe.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoSegundaGanhouAnterior") && quemPediu.get_connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao() == null) {
					quemPediu.set_connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoSegundaGanhouAnterior.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoSegundaPerdeuAnterior") && quemPediu.get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao() == null) {
					quemPediu.set_connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoSegundaPerdeuAnterior.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoTerceiraGanhouAnterior") && quemPediu.get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao() == null) {
					quemPediu.set_connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoTerceiraGanhouAnterior.xml"));
				}
				
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoTerceiraPerdeuAnterior") && quemPediu.get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao() == null) {
					quemPediu.set_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoTerceiraPerdeuAnterior.xml"));
				}
				
				
				
				
				
				else if (centroide.equalsIgnoreCase("centroidSegundaCartaRoboGanhouAprimeira") && quemPediu.get_connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao() == null) {
					quemPediu.set_connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidSegundaCartaRoboGanhouAprimeira.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidSegundaCartaRoboPerdeuAprimeira") && quemPediu.get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao() == null) {
					quemPediu.set_connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidSegundaCartaRoboPerdeuAprimeira.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidTerceiraCartaRoboGanhouAsegunda") && quemPediu.get_connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao() == null) {
					quemPediu.set_connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidTerceiraCartaRoboGanhouAsegunda.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidTerceiraCartaRoboPerdeuAsegunda") && quemPediu.get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao() == null) {
					quemPediu.set_connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidTerceiraCartaRoboPerdeuAsegunda.xml"));
				}
				
			} else if (baseRetencao.equalsIgnoreCase("Nada") || baseRetencao.equalsIgnoreCase("Baseline")) {
				path = "cbr/Hibernate/";
				
				if (centroide.equalsIgnoreCase("centroidePrimeiraCartaRoboMao") &&  quemPediu.get_connectorCentroidePrimeiraCartaRoboMaoBaseline() == null) {
					quemPediu.set_connectorCentroidePrimeiraCartaRoboMaoBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroidePrimeiraCartaRoboMaoBaseline().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidPrimeiraCartaRoboMao.xml"));
				}
				else	if (centroide.equalsIgnoreCase("centroidePrimeiraCartaRoboPe") && quemPediu.get_connectorCentroidePrimeiraCartaRoboPeBaseline() == null) {
					quemPediu.set_connectorCentroidePrimeiraCartaRoboPeBaseline(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroidePrimeiraCartaRoboPeBaseline().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidPrimeiraCartaRoboPe.xml"));
				}
				
				else	if (centroide.equalsIgnoreCase("centroidQuemGanhouEnvidoAgenteMao") && quemPediu.get_connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline() == null) {
					quemPediu.set_connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidQuemGanhouEnvidoAgenteMao.xml"));
				}

				else if (centroide.equalsIgnoreCase("centroidQuemGanhouEnvidoAgentePe") && quemPediu.get_connectorCentroideQuemGanhouEnvidoAgentePeBaseline() == null) {
					quemPediu.set_connectorCentroideQuemGanhouEnvidoAgentePeBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemGanhouEnvidoAgentePeBaseline().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidQuemGanhouEnvidoAgentePe.xml"));
				}
				

				else if (centroide.equalsIgnoreCase("centroidQuemTrucoPrimeiraMao") && quemPediu.get_connectorCentroideQuemTrucoPrimeiraMaoBaseline() == null) {
					quemPediu.set_connectorCentroideQuemTrucoPrimeiraMaoBaseline(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoPrimeiraMaoBaseline().initFromXMLfile(jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoPrimeiraMao.xml"));
				}
				
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoPrimeiraPe") && quemPediu.get_connectorCentroideQuemTrucoPrimeiraPeBaseline() == null) {
					quemPediu.set_connectorCentroideQuemTrucoPrimeiraPeBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoPrimeiraPeBaseline().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoPrimeiraPe.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoSegundaGanhouAnterior") && quemPediu.get_connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline() == null) {
					quemPediu.set_connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoSegundaGanhouAnterior.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoSegundaPerdeuAnterior") && quemPediu.get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline() == null) {
					quemPediu.set_connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoSegundaPerdeuAnterior.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoTerceiraGanhouAnterior") && quemPediu.get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline() == null) {
					quemPediu.set_connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline(new DataBaseConnectorGustavoNew());
					
					quemPediu.get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoTerceiraGanhouAnterior.xml"));
				}
				
				else if (centroide.equalsIgnoreCase("centroidQuemTrucoTerceiraPerdeuAnterior") && quemPediu.get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline() == null) {
					quemPediu.set_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline().initFromXMLfile(
							jcolibri.util.FileIO.findFile(path + "databases/databaseconfigCentroidQuemTrucoTerceiraPerdeuAnterior.xml"));
				}
				
				
				
				
				
				else if (centroide.equalsIgnoreCase("centroidSegundaCartaRoboGanhouAprimeira") && quemPediu.get_connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline() == null) {
					quemPediu.set_connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidSegundaCartaRoboGanhouAprimeira.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidSegundaCartaRoboPerdeuAprimeira") && quemPediu.get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline() == null) {
					quemPediu.set_connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidSegundaCartaRoboPerdeuAprimeira.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidTerceiraCartaRoboGanhouAsegunda") && quemPediu.get_connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline() == null) {
					quemPediu.set_connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidTerceiraCartaRoboGanhouAsegunda.xml"));
				}
				else if (centroide.equalsIgnoreCase("centroidTerceiraCartaRoboPerdeuAsegunda") && quemPediu.get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline() == null) {
					quemPediu.set_connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline(new DataBaseConnectorGustavoNew());
					quemPediu.get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline().initFromXMLfile(jcolibri.util.FileIO
							.findFile(path + "databases/databaseconfigCentroidTerceiraCartaRoboPerdeuAsegunda.xml"));
				}

			}
			
			
						
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}




	/**
	 * Realiza a conecao do conetor com a base
	 * 
	 * @param _caseBase
	 * @param _connector
	 * @return
	 * @throws InitializingException
	 */
	
public void openConnectionBase(CBRCaseBaseGustavo _caseBase, Connector _connector, String centroide, CbrModular quemPediu) throws InitializingException {
		
		if (centroide.equalsIgnoreCase("centroidePrimeiraCartaRoboMao") &&  quemPediu.get_caseBaseCentroidePrimeiraCartaRoboMao() == null) {
			quemPediu.set_caseBaseCentroidePrimeiraCartaRoboMao(new CachedLinealCaseBaseGustavoNew() );
			quemPediu.get_caseBaseCentroidePrimeiraCartaRoboMao().init(_connector);
		}
		else	if (centroide.equalsIgnoreCase("centroidePrimeiraCartaRoboPe") && quemPediu.get_caseBaseCentroidePrimeiraCartaRoboPe() == null) {
			quemPediu.set_caseBaseCentroidePrimeiraCartaRoboPe(new CachedLinealCaseBaseGustavoNew());
			
			quemPediu.get_caseBaseCentroidePrimeiraCartaRoboPe().init(_connector);
		}
		
		else	if (centroide.equalsIgnoreCase("centroidQuemGanhouEnvidoAgenteMao") && quemPediu.get_caseBaseCentroideQuemGanhouEnvidoAgenteMao() == null) {
			quemPediu.set_caseBaseCentroideQuemGanhouEnvidoAgenteMao(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideQuemGanhouEnvidoAgenteMao().init(_connector);
		}

		else if (centroide.equalsIgnoreCase("centroidQuemGanhouEnvidoAgentePe") && quemPediu.get_caseBaseCentroideQuemGanhouEnvidoAgentePe() == null) {
			quemPediu.set_caseBaseCentroideQuemGanhouEnvidoAgentePe(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideQuemGanhouEnvidoAgentePe().init(_connector);
		}
		

		else if (centroide.equalsIgnoreCase("centroidQuemTrucoPrimeiraMao") && quemPediu.get_caseBaseCentroideQuemTrucoPrimeiraMao() == null) {
			quemPediu.set_caseBaseCentroideQuemTrucoPrimeiraMao(new CachedLinealCaseBaseGustavoNew());
			
			quemPediu.get_caseBaseCentroideQuemTrucoPrimeiraMao().init(_connector);
		}
		
		else if (centroide.equalsIgnoreCase("centroidQuemTrucoPrimeiraPe") && quemPediu.get_caseBaseCentroideQuemTrucoPrimeiraPe() == null) {
			quemPediu.set_caseBaseCentroideQuemTrucoPrimeiraPe(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideQuemTrucoPrimeiraPe().init(_connector);
		}
		else if (centroide.equalsIgnoreCase("centroidQuemTrucoSegundaGanhouAnterior") && quemPediu.get_caseBaseCentroideQuemTrucoSegundaGanhouAnterior() == null) {
			quemPediu.set_caseBaseCentroideQuemTrucoSegundaGanhouAnterior(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideQuemTrucoSegundaGanhouAnterior().init(_connector);
		}
		else if (centroide.equalsIgnoreCase("centroidQuemTrucoSegundaPerdeuAnterior") && quemPediu.get_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior() == null) {
			quemPediu.set_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior(new CachedLinealCaseBaseGustavoNew());
			
			quemPediu.get_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior().init(_connector);
		}
		else if (centroide.equalsIgnoreCase("centroidQuemTrucoTerceiraGanhouAnterior") && quemPediu.get_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior() == null) {
			quemPediu.set_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior(new CachedLinealCaseBaseGustavoNew());
			
			quemPediu.get_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior().init(_connector);
		}
		
		else if (centroide.equalsIgnoreCase("centroidQuemTrucoTerceiraPerdeuAnterior") && quemPediu.get_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior() == null) {
			quemPediu.set_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior().init(_connector);
		}
		
		
		
		
		
		else if (centroide.equalsIgnoreCase("centroidSegundaCartaRoboGanhouAprimeira") && quemPediu.get_caseBaseCentroideSegundaCartaRoboGanhouAprimeira() == null) {
			quemPediu.set_caseBaseCentroideSegundaCartaRoboGanhouAprimeira(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideSegundaCartaRoboGanhouAprimeira().init(_connector);
		}
		else if (centroide.equalsIgnoreCase("centroidSegundaCartaRoboPerdeuAprimeira") && quemPediu.get_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira() == null) {
			quemPediu.set_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira().init(_connector);
		}
		else if (centroide.equalsIgnoreCase("centroidTerceiraCartaRoboGanhouAsegunda") && quemPediu.get_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda() == null) {
			quemPediu.set_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda().init(_connector);
		}
		else if (centroide.equalsIgnoreCase("centroidTerceiraCartaRoboPerdeuAsegunda") && quemPediu.get_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda() == null) {
			quemPediu.set_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda(new CachedLinealCaseBaseGustavoNew());
			quemPediu.get_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda().init(_connector);
		}

	}


	
	/**
	 * Fecha a conexao entre a base e o conector
	 * 
	 * @param _connector
	 * @throws ExecutionException
	 */
	public void closeConnection(Connector _connector) throws ExecutionException {
	  if(_connector != null)
		_connector.close();
	  
	}
	

	
	
	
	
	
	
//similaridadeCentroides primeiraCartaRoboMao
	public Collection<RetrievalResult> executeQueryCentroidePrimeiraCartaRoboMao(CBRCaseBaseGustavo _caseBase, CBRQuery query)
			throws ExecutionException {
		CentroidesPrimeiraCartaRoboMaoDescription desc = (CentroidesPrimeiraCartaRoboMaoDescription) query
				.getDescription();

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());

		simConfig.addMapping(new Attribute("cartaAltaRoboClustering", CentroidesPrimeiraCartaRoboMaoDescription.class),
				new LocalEuclidean(51));

		Attribute cartaMediaRoboClustering = new Attribute("cartaMediaRoboClustering",
				CentroidesPrimeiraCartaRoboMaoDescription.class);
		simConfig.addMapping(cartaMediaRoboClustering, new LocalEuclidean(51)); // carta

		Attribute cartaBaixaRoboClustering = new Attribute("cartaBaixaRoboClustering",
				CentroidesPrimeiraCartaRoboMaoDescription.class);
		simConfig.addMapping(cartaBaixaRoboClustering, new LocalEuclidean(51)); // jogador

		Attribute primeiraCartaRoboClustering = new Attribute("primeiraCartaRoboClustering",
				CentroidesPrimeiraCartaRoboMaoDescription.class);
		simConfig.addMapping(primeiraCartaRoboClustering, new LocalEuclidean(46)); // jogador


		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
	
		

		eval = SelectCases.selectTopKRR(eval, kcentroidessimilaresquedevemserreccuperados);
		return eval;
	}

	public ResultadoConsultaCentroidePrimeiraCartaRoboMaoModelo getBestResultCentroidePrimeiraCartaRoboMao(
			CBRCaseBaseGustavo _caseBase, CentroidesPrimeiraCartaRoboMaoDescription game_state) {
		Collection<RetrievalResult> results = null;
		ResultadoConsultaCentroidePrimeiraCartaRoboMaoModelo resultadoConsultaCentroidePrimeiraCartaRoboMaoModelo = new ResultadoConsultaCentroidePrimeiraCartaRoboMaoModelo();

		try {
			CentroidesPrimeiraCartaRoboMaoDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroidePrimeiraCartaRoboMao(_caseBase, query);

			if (results.size() == 0) {
				// PARA QUANDO NÃO RETORNAR NADA
				resultadoConsultaCentroidePrimeiraCartaRoboMaoModelo.setSimilaridadeComAconsulta(404.00);

			} else {
				RetrievalResult resultRecuperado = results.iterator().next();
				
				resultadoConsultaCentroidePrimeiraCartaRoboMaoModelo
						.setCentroideMaisSimilar((CentroidesPrimeiraCartaRoboMaoDescription) resultRecuperado.get_case().getDescription()) ;
				resultadoConsultaCentroidePrimeiraCartaRoboMaoModelo
						.setSimilaridadeComAconsulta(resultRecuperado.getEval());

			}

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return resultadoConsultaCentroidePrimeiraCartaRoboMaoModelo;
	}

	// similaridadeCentroides primeiraCartaRoboPe
	public Collection<RetrievalResult> executeQueryCentroidePrimeiraCartaRoboPe(CBRCaseBaseGustavo _caseBase, CBRQuery query)
			throws ExecutionException {
		CentroidesPrimeiraCartaRoboPeDescription desc = (CentroidesPrimeiraCartaRoboPeDescription) query
				.getDescription();

		NNConfig simConfig = new NNConfig();
		//simConfig.setDescriptionSimFunction(new Average());
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());


		Attribute cartaAltaRoboClustering = new Attribute("cartaAltaRoboClustering",
				CentroidesPrimeiraCartaRoboPeDescription.class);
		simConfig.addMapping(cartaAltaRoboClustering, new LocalEuclidean(51));
		
		Attribute cartaMediaRoboClustering = new Attribute("cartaMediaRoboClustering",
				CentroidesPrimeiraCartaRoboPeDescription.class);
		simConfig.addMapping(cartaMediaRoboClustering, new LocalEuclidean(51)); // carta

		Attribute cartaBaixaRoboClustering = new Attribute("cartaBaixaRoboClustering",
				CentroidesPrimeiraCartaRoboPeDescription.class);
		simConfig.addMapping(cartaBaixaRoboClustering, new LocalEuclidean(51)); // jogador

		Attribute primeiraCartaRoboClustering = new Attribute("primeiraCartaRoboClustering",
				CentroidesPrimeiraCartaRoboPeDescription.class);
		simConfig.addMapping(primeiraCartaRoboClustering, new LocalEuclidean(46)); // jogador

		Attribute primeiraCartaHumanoClustering = new Attribute("primeiraCartaHumanoClustering",
				CentroidesPrimeiraCartaRoboPeDescription.class);
		simConfig.addMapping(primeiraCartaHumanoClustering, new LocalEuclidean(51)); // jogador

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		
			
			
		
		eval = SelectCases.selectTopKRR(eval, kcentroidessimilaresquedevemserreccuperados);
		return eval;
	}

	public ResultadoConsultaCentroidePrimeiraCartaRoboPe getBestResultCentroidePrimeiraCartaRoboPe(
			CBRCaseBaseGustavo _caseBase, CentroidesPrimeiraCartaRoboPeDescription game_state) {
		Collection<RetrievalResult> resultPrimeiraCartaRoboPe = null;
		ResultadoConsultaCentroidePrimeiraCartaRoboPe resultadoConsultaCentroidePrimeiraCartaRoboPe = new ResultadoConsultaCentroidePrimeiraCartaRoboPe();
		try {
			CentroidesPrimeiraCartaRoboPeDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			resultPrimeiraCartaRoboPe = executeQueryCentroidePrimeiraCartaRoboPe(_caseBase, query);
			
			

			if (resultPrimeiraCartaRoboPe.size() == 0 ) {
				// PARA QUANDO NÃO RETORNAR NADA
				resultadoConsultaCentroidePrimeiraCartaRoboPe.setSimilaridadeComAconsulta(404.00);

			} else {
				////////////System.out.println("tamanho no array: "+ resultPrimeiraCartaRoboPe.size());
				
				RetrievalResult resultadoRecuperado = resultPrimeiraCartaRoboPe.iterator().next();
				//se o resultado recuperado for zero significa que está sendo computado similaridade negativa precisa ser descoberto por que
				if(resultadoRecuperado.getEval() !=0) {
				
				CentroidesPrimeiraCartaRoboPeDescription casoRecuperado = new CentroidesPrimeiraCartaRoboPeDescription();
				casoRecuperado = (CentroidesPrimeiraCartaRoboPeDescription) resultadoRecuperado.get_case()
						.getDescription();
				
				resultadoConsultaCentroidePrimeiraCartaRoboPe.setCentroideMaisSimilar(casoRecuperado);
				resultadoConsultaCentroidePrimeiraCartaRoboPe
						.setSimilaridadeComAconsulta(resultadoRecuperado.getEval());
				}else {
					resultadoConsultaCentroidePrimeiraCartaRoboPe.setSimilaridadeComAconsulta(404.00);
				}
				
			}

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return resultadoConsultaCentroidePrimeiraCartaRoboPe;
	}

	public ResultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira getBestResultCentroideSegundaCartaRoboGanhouAprimeira(
			CBRCaseBaseGustavo _caseBase, CentroidesSegundaCartaRoboGanhouAprimeiraDescription game_state) {
		Collection<RetrievalResult> results = null;
		ResultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira resultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira = new ResultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira();
		try {
			CentroidesSegundaCartaRoboGanhouAprimeiraDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideSegundaCartaRoboGanhouAprimeira(_caseBase, query);

			if (results.size() == 0) {
				// PARA QUANDO NÃO RETORNAR NADA
				resultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira.setMaiorSimilaridade(404.00);

			} else {
				RetrievalResult resultRetornado = results.iterator().next();
				
				resultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira.setCentroideSegundaCartaRoboGanhouAprimeira(
						(CentroidesSegundaCartaRoboGanhouAprimeiraDescription) resultRetornado.get_case().getDescription());
								
				resultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira
						.setMaiorSimilaridade(resultRetornado.getEval());

			}
		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return resultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira;
	}

	public ResultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira getBestResultCentroideSegundaCartaRoboPerdeuAprimeira(
			CBRCaseBaseGustavo _caseBase, CentroidesSegundaCartaRoboPerdeuAprimeiraDescription game_state) {
		Collection<RetrievalResult> results = null;
		ResultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira resultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira = new ResultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira();
		try {
			CentroidesSegundaCartaRoboPerdeuAprimeiraDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideSegundaCartaRoboPerdeuAprimeira(_caseBase, query);

			if (results.size() == 0) {
				// PARA QUANDO NÃO RETORNAR NADA
				resultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira.setMaiorSimilaridade(404.00);

			} else {
				RetrievalResult resultRetornado = results.iterator().next();
				
				resultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira.setCentroideSegundaCartaRoboPerdeuAprimeira(
						(CentroidesSegundaCartaRoboPerdeuAprimeiraDescription) resultRetornado.get_case()
								.getDescription());
				resultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira
						.setMaiorSimilaridade(resultRetornado.getEval());

			}

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return resultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira;
	}

	public ResultadoConsultaTerceiraCartaRoboGanhouAsegunda getBestResultCentroideTerceiraCartaRoboGanhouASegunda(
			CBRCaseBaseGustavo _caseBase, CentroidesTerceiraCartaRoboGanhouAsegundaDescription game_state) {
		Collection<RetrievalResult> results = null;
		ResultadoConsultaTerceiraCartaRoboGanhouAsegunda resultadoConsultaCentroideTerceiraCartaRoboGanhouASegunda = new ResultadoConsultaTerceiraCartaRoboGanhouAsegunda();
		try {
			CentroidesTerceiraCartaRoboGanhouAsegundaDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideTerceiraCartaRoboGanhouAsegunda(_caseBase, query);

			if (results.size() == 0) {
				// PARA QUANDO NÃO RETORNAR NADA
				resultadoConsultaCentroideTerceiraCartaRoboGanhouASegunda.setSimilaridadeCasoMaisSimilar(404.00);

			} else {
				RetrievalResult resultRetornado = results.iterator().next();
				
				resultadoConsultaCentroideTerceiraCartaRoboGanhouASegunda.setCentroideTerceiraCartaRoboGanhouAsegunda(
						(CentroidesTerceiraCartaRoboGanhouAsegundaDescription) resultRetornado.get_case().getDescription());
				resultadoConsultaCentroideTerceiraCartaRoboGanhouASegunda
						.setSimilaridadeCasoMaisSimilar(resultRetornado.getEval());

			}

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return resultadoConsultaCentroideTerceiraCartaRoboGanhouASegunda;
	}

	public ResultadoTerceiraCartaRoboPerdeuAsegunda getBestResultCentroideTerceiraCartaRoboPerdeuASegunda(
			CBRCaseBaseGustavo _caseBase, CentroidesTerceiraCartaRoboPerdeuAsegundaDescription game_state) {
		Collection<RetrievalResult> results = null;
		ResultadoTerceiraCartaRoboPerdeuAsegunda resultadoConsultaCentroideTerceiraCartaRoboPerdeuASegunda = new ResultadoTerceiraCartaRoboPerdeuAsegunda();
		try {
			CentroidesTerceiraCartaRoboPerdeuAsegundaDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideTerceiraCartaRoboPerdeuAsegunda(_caseBase, query);

			if (results.size() == 0) {
				// PARA QUANDO NÃO RETORNAR NADA
				resultadoConsultaCentroideTerceiraCartaRoboPerdeuASegunda.setSimilaridadeCentroideMaisSimilar(404.00);

			} else {
				RetrievalResult resultRetornado = results.iterator().next();

				resultadoConsultaCentroideTerceiraCartaRoboPerdeuASegunda
						.setCentroideMaisSimilar((CentroidesTerceiraCartaRoboPerdeuAsegundaDescription) resultRetornado.get_case().getDescription());
				resultadoConsultaCentroideTerceiraCartaRoboPerdeuASegunda
						.setSimilaridadeCentroideMaisSimilar(resultRetornado.getEval());

			}

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return resultadoConsultaCentroideTerceiraCartaRoboPerdeuASegunda;
	}

	public ResultadoConsultaCentroideQuemTruco getBestResultCentroideQuemTruco(CBRCaseBaseGustavo _caseBase,
			CentroidesQuemTrucoDescription game_state) {
		Collection<RetrievalResult> resultsQuemTruco = null;
		ResultadoConsultaCentroideQuemTruco resultadoQuemTruco = new ResultadoConsultaCentroideQuemTruco();
		try {
			CentroidesQuemTrucoDescription queryDesc = game_state;
		

			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);
 //System.out.println("tamanho da base passada: "+ _caseBase.getCases().size());
			resultsQuemTruco = executeQueryCentroideQuemTruco(_caseBase, query);

			if (resultsQuemTruco.size() == 0) {
				// PARA QUANDO NÃO RETORNAR NADA
				resultadoQuemTruco.setSimilaridadeComAconsulta(404.00);

			} else {
				RetrievalResult resultRetornado = resultsQuemTruco.iterator().next();
				
				resultadoQuemTruco.setCentroideMaisSimilar((CentroidesQuemTrucoDescription) resultRetornado.get_case().getDescription());
				resultadoQuemTruco.setSimilaridadeComAconsulta(resultRetornado.getEval());
			}

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return resultadoQuemTruco;
	}

	

	public ResultadoConsultaCentroideQuemGanhouEnvido getBestResultCentroideQuemEnvido(CBRCaseBaseGustavo _caseBase,
			CentroidesQuemGanhouEnvidoDescription game_state) {
		Collection<RetrievalResult> results = null;
		ResultadoConsultaCentroideQuemGanhouEnvido resultadoQuemEnvido = new ResultadoConsultaCentroideQuemGanhouEnvido();
		try {
			CentroidesQuemGanhouEnvidoDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideQuemGanhouEnvido(_caseBase, query);
			if (results.size() == 0) {
				// PARA QUANDO NÃO RETORNAR NADA
				CentroidesQuemGanhouEnvidoDescription  centroide404= new CentroidesQuemGanhouEnvidoDescription();
				centroide404.setGrupo(404);
				resultadoQuemEnvido.setCentroide(centroide404);
				resultadoQuemEnvido.setSimilaridadeDaConsultaComOcentroide(404.00);
				

			} else {
				RetrievalResult resultRetornado = results.iterator().next();
				
				resultadoQuemEnvido.setCentroide(
						(CentroidesQuemGanhouEnvidoDescription) resultRetornado.get_case().getDescription());
				resultadoQuemEnvido.setSimilaridadeDaConsultaComOcentroide(resultRetornado.getEval());
				if(resultadoQuemEnvido.getCentroide() == null) {
					////////////System.out.println("resultado nulo");
				}

			}

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}
		if(resultadoQuemEnvido.getSimilaridadeDaConsultaComOcentroide()== null) {
			////////////System.out.println("retornou nulo");
		}
		
		return resultadoQuemEnvido;
	}

	// Centroid QuemGanhouEnvido

	public Collection<RetrievalResult> executeQueryCentroideQuemGanhouEnvido(CBRCaseBaseGustavo _caseBase, CBRQuery query)
			throws ExecutionException {
		CentroidesQuemGanhouEnvidoDescription desc = (CentroidesQuemGanhouEnvidoDescription) query.getDescription();

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());

		Attribute quemPediuEnvido = new Attribute("quemPediuEnvido", CentroidesQuemGanhouEnvidoDescription.class);
		simConfig.addMapping(quemPediuEnvido, new LocalEuclidean(3));

		Attribute quemPediuRealEnvido = new Attribute("quemPediuRealEnvido",
				CentroidesQuemGanhouEnvidoDescription.class);
		simConfig.addMapping(quemPediuRealEnvido, new LocalEuclidean(3)); // carta

		Attribute quemPediuFaltaEnvido = new Attribute("quemPediuFaltaEnvido",
				CentroidesQuemGanhouEnvidoDescription.class);
		simConfig.addMapping(quemPediuFaltaEnvido, new LocalEuclidean(3)); // jogador

		
		Attribute pontosEnvidoRobo = new Attribute("pontosEnvidoRobo",
				CentroidesQuemGanhouEnvidoDescription.class);
		simConfig.addMapping(pontosEnvidoRobo, new LocalEuclidean(33)); // jogador

		
		
		CentroidesQuemGanhouEnvidoDescription descriptionCentroide = (CentroidesQuemGanhouEnvidoDescription) query
				.getDescription();

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
	
		
		

		
		eval = SelectCases.selectTopKRR(eval, _caseBase.getCases().size());

		return eval;
	}

	public Collection<RetrievalResult> getBestResultCentroideQuemGanhouEnvido(CBRCaseBaseGustavo _caseBase,
			CentroidesQuemGanhouEnvidoDescription game_state, int tipoConsulta) {
		Collection<RetrievalResult> results = null;
		try {
			CentroidesQuemGanhouEnvidoDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideQuemGanhouEnvido(_caseBase, query);

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return results;
	}

	// Centroid QuemTruco

	public Collection<RetrievalResult> executeQueryCentroideQuemTruco(CBRCaseBaseGustavo _caseBase, CBRQuery query)
			throws ExecutionException {
		CentroidesQuemTrucoDescription desc = (CentroidesQuemTrucoDescription) query.getDescription();

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());

		simConfig.addMapping(new Attribute("quemTruco", CentroidesQuemTrucoDescription.class), new LocalEuclidean(2));

		Attribute quemRetruco = new Attribute("quemRetruco", CentroidesQuemTrucoDescription.class);
		simConfig.addMapping(quemRetruco, new LocalEuclidean(3));

		Attribute quemValeQuatro = new Attribute("quemValeQuatro", CentroidesQuemTrucoDescription.class);
		simConfig.addMapping(quemValeQuatro, new LocalEuclidean(3)); // jogador
		
		Attribute cartaAltaRoboClustering = new Attribute("cartaAltaRoboClustering",
				CentroidesPrimeiraCartaRoboPeDescription.class);
		simConfig.addMapping(cartaAltaRoboClustering, new LocalEuclidean(51));
		
		Attribute cartaMediaRoboClustering = new Attribute("cartaMediaRoboClustering",
				CentroidesPrimeiraCartaRoboPeDescription.class);
		simConfig.addMapping(cartaMediaRoboClustering, new LocalEuclidean(51)); // carta

		Attribute cartaBaixaRoboClustering = new Attribute("cartaBaixaRoboClustering",
				CentroidesPrimeiraCartaRoboPeDescription.class);
		simConfig.addMapping(cartaBaixaRoboClustering, new LocalEuclidean(51)); // jogador

		//System.out.println("quantidade de casos da base "+_caseBase.getCases().size());
				Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);

		eval = SelectCases.selectTopKRR(eval, _caseBase.getCases().size());
		return eval;
	}

	public Collection<RetrievalResult> getBestResultCentroideQuemTruco(CBRCaseBaseGustavo _caseBase,
			CentroidesQuemTrucoDescription game_state, int tipoConsulta) {
		Collection<RetrievalResult> results = null;
		try {
			CentroidesQuemTrucoDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideQuemTruco(_caseBase, query);

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return results;
	}

	// Centroid Segunda Ganhou A primeira

	public Collection<RetrievalResult> executeQueryCentroideSegundaCartaRoboGanhouAprimeira(CBRCaseBaseGustavo _caseBase,
			CBRQuery query) throws ExecutionException {
		CentroidesSegundaCartaRoboGanhouAprimeiraDescription desc = (CentroidesSegundaCartaRoboGanhouAprimeiraDescription) query
				.getDescription();

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());

		simConfig.addMapping(
				new Attribute("segundaCartaRoboClustering", CentroidesSegundaCartaRoboGanhouAprimeiraDescription.class),
				new LocalEuclidean(46));

		////////////System.out.println("Ponto \t" + _caseBase.getCases().size());
		////////////System.out.println(query);
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, _caseBase.getCases().size());
		return eval;
	}

	public Collection<RetrievalResult> getBestResultCentroideSegundaCartaRoboGanhouAprimeira(CBRCaseBaseGustavo _caseBase,
			CentroidesSegundaCartaRoboGanhouAprimeiraDescription game_state, int tipoConsulta) {
		Collection<RetrievalResult> results = null;
		try {
			CentroidesSegundaCartaRoboGanhouAprimeiraDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideSegundaCartaRoboGanhouAprimeira(_caseBase, query);

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return results;
	}

	// Centroid Segunda Perdeu A primeira

	public Collection<RetrievalResult> executeQueryCentroideSegundaCartaRoboPerdeuAprimeira(CBRCaseBaseGustavo _caseBase,
			CBRQuery query) throws ExecutionException {
		CentroidesSegundaCartaRoboPerdeuAprimeiraDescription desc = (CentroidesSegundaCartaRoboPerdeuAprimeiraDescription) query
				.getDescription();

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());

		simConfig.addMapping(
				new Attribute("segundaCartaRoboClustering", CentroidesSegundaCartaRoboPerdeuAprimeiraDescription.class),
				new LocalEuclidean(46));

		simConfig.addMapping(new Attribute("segundaCartaHumanoClustering",
				CentroidesSegundaCartaRoboPerdeuAprimeiraDescription.class), new LocalEuclidean(51));

		////////////System.out.println("Ponto \t" + _caseBase.getCases().size());
		////////////System.out.println(query);
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, _caseBase.getCases().size());
		return eval;
	}

	public Collection<RetrievalResult> getBestResultCentroideSegundaCartaRoboPerdeuAprimeira(CBRCaseBaseGustavo _caseBase,
			CentroidesSegundaCartaRoboPerdeuAprimeiraDescription game_state, int tipoConsulta) {
		Collection<RetrievalResult> results = null;
		try {
			CentroidesSegundaCartaRoboPerdeuAprimeiraDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideSegundaCartaRoboPerdeuAprimeira(_caseBase, query);

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return results;
	}

	// Centroid Terceira Ganhou A segunda

	public Collection<RetrievalResult> executeQueryCentroideTerceiraCartaRoboGanhouAsegunda(CBRCaseBaseGustavo _caseBase,
			CBRQuery query) throws ExecutionException {
		CentroidesTerceiraCartaRoboGanhouAsegundaDescription desc = (CentroidesTerceiraCartaRoboGanhouAsegundaDescription) query
				.getDescription();

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());

		simConfig.addMapping(new Attribute("terceiraCartaRoboClustering",
				CentroidesTerceiraCartaRoboGanhouAsegundaDescription.class), new LocalEuclidean(46));

			Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, _caseBase.getCases().size());
		return eval;
	}

	public Collection<RetrievalResult> getBestResultCentroideTerceiraCartaRoboGanhouAprimeira(CBRCaseBaseGustavo _caseBase,
			CentroidesTerceiraCartaRoboGanhouAsegundaDescription game_state, int tipoConsulta) {
		Collection<RetrievalResult> results = null;
		try {
			CentroidesTerceiraCartaRoboGanhouAsegundaDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideTerceiraCartaRoboGanhouAsegunda(_caseBase, query);

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return results;
	}

	// Centroid Terceira Perdeu a segunda

	public Collection<RetrievalResult> executeQueryCentroideTerceiraCartaRoboPerdeuAsegunda(CBRCaseBaseGustavo _caseBase,
			CBRQuery query) throws ExecutionException {
		CentroidesTerceiraCartaRoboPerdeuAsegundaDescription desc = (CentroidesTerceiraCartaRoboPerdeuAsegundaDescription) query
				.getDescription();

		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new GlobalEuclidean());

		simConfig.addMapping(new Attribute("terceiraCartaRoboClustering",
				CentroidesTerceiraCartaRoboPerdeuAsegundaDescription.class), new LocalEuclidean(46));

		simConfig.addMapping(new Attribute("terceiraCartaHumanoClustering",
				CentroidesTerceiraCartaRoboPerdeuAsegundaDescription.class), new LocalEuclidean(51));

		////////////System.out.println("Ponto \t" + _caseBase.getCases().size());
		////////////System.out.println(query);
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, _caseBase.getCases().size());
		return eval;
	}

	public Collection<RetrievalResult> getBestResultCentroideTerceiraCartaRoboPerdeuAprimeira(CBRCaseBaseGustavo _caseBase,
			CentroidesTerceiraCartaRoboPerdeuAsegundaDescription game_state, int tipoConsulta) {
		Collection<RetrievalResult> results = null;
		try {
			CentroidesTerceiraCartaRoboPerdeuAsegundaDescription queryDesc = game_state;
			CBRQuery query = new CBRQuery();
			query.setDescription(queryDesc);

			results = executeQueryCentroideTerceiraCartaRoboPerdeuAsegunda(_caseBase, query);

		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
		}

		return results;
	}

	public double calculoMedSim(Collection<RetrievalResult> casos) {
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

	public double calculoPiorSim(Collection<RetrievalResult> casos) {
		double sim = 1;
		for (RetrievalResult R : casos) {
//			////////////System.out.println(R.getEval());
			if (R.getEval() < sim)
				sim = R.getEval();
		}
		return sim;
	}

	public double calculoMelhorSim(Collection<RetrievalResult> casos) {
		double sim = 0;
		for (RetrievalResult R : casos) {
			if (R.getEval() > sim)
				sim = R.getEval();
		}
		return sim;
	}

	/**
	 * Retorna o TrucoDescription da pior similariadde dado os N casos retornados na
	 * consulta
	 * 
	 * @param results Lista de casos mais simialres
	 * @return Caso Mais similar
	 */
	public TrucoDescription selecionaPiorSim(Collection<RetrievalResult> results) {
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

	/**
	 * Retorna o TrucoDescription da maior similariadde dado os N casos retornados
	 * na consulta
	 * 
	 * @param results Lista de casos mais simialres
	 * @return Caso Mais similar
	 */
	public TrucoDescription selecionaMelhorSim(Collection<RetrievalResult> results) {
		RetrievalResult selected = new RetrievalResult(new CBRCase(), 0.0);
//		 ////////////System.out.println(results);
		double sim = 0;
		for (RetrievalResult r : results) {
			if (r.getEval() >= sim) {
				selected = r;
				sim = r.getEval();
			}
		}
//		 ////////////System.out.println(selected);
		TrucoDescription Caso = (TrucoDescription) selected.get_case().getDescription();
		Caso.setSimilaridadeCaso(selected.getEval());
		return Caso;
	}

}
