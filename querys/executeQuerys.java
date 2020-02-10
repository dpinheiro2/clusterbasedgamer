package querys;

import java.util.Collection;

import CbrQuerys.CBRCentroides;
import CentroidesModelo.ResultadoConsultaCentroideQuemGanhouEnvido;
import cbr.cbrDescriptions.CentroidesQuemGanhouEnvidoDescription;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.InitializingException;
import jcolibri.method.retrieve.RetrievalResult;

public class executeQuerys {
	// centroide quem envido jogador mao
		Connector _connectorCentroideQuemGanhouEnvidoAgenteMao;
		CBRCaseBase _caseBaseCentroideQuemGanhouEnvidoAgenteMao;
		// centroide quem envido jogador pe
		Connector _connectorCentroideQuemGanhouEnvidoAgentePe;
		CBRCaseBase _caseBaseCentroideQuemGanhouEnvidoAgentePe;

		public executeQuerys() throws InitializingException, ExecutionException {
			CBRCentroides ck = new CBRCentroides();
			try {
				_caseBaseCentroideQuemGanhouEnvidoAgenteMao = ck.initialize_caseBase();
				_caseBaseCentroideQuemGanhouEnvidoAgenteMao = ck.openConnectionBase(
						_caseBaseCentroideQuemGanhouEnvidoAgenteMao, _connectorCentroideQuemGanhouEnvidoAgenteMao);
				_connectorCentroideQuemGanhouEnvidoAgenteMao = ck.initialize_conector("centroidQuemGanhouEnvidoAgenteMao","MelhoresAleatorioCluster");
				
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		public ResultadoConsultaCentroideQuemGanhouEnvido getBestResultCentroideQuemEnvido(CentroidesQuemGanhouEnvidoDescription game_state) {
			Collection<RetrievalResult> results = null;
			ResultadoConsultaCentroideQuemGanhouEnvido resultadoQuemEnvido = new ResultadoConsultaCentroideQuemGanhouEnvido();
			try {
				CentroidesQuemGanhouEnvidoDescription queryDesc = game_state;
				CBRQuery query = new CBRQuery();
				query.setDescription(queryDesc);
				CBRCentroides ck = new CBRCentroides();
				
					results = ck.executeQueryCentroideQuemGanhouEnvido(_caseBaseCentroideQuemGanhouEnvidoAgenteMao, query);
					if( results.size() == 0) {
						//PARA QUANDO NÃO RETORNAR NADA
						resultadoQuemEnvido.setSimilaridadeDaConsultaComOcentroide(404.00);
						////System.out.println("não recuperou nada");
					}else {
						
					
					 resultadoQuemEnvido.setCentroide((CentroidesQuemGanhouEnvidoDescription) results.iterator().next().get_case().getDescription());
					 resultadoQuemEnvido.setSimilaridadeDaConsultaComOcentroide(results.iterator().next().getEval());
					////System.out.println("similaridade do caso recuperado: "+  results.iterator().next().getEval());
					}

			} catch (ExecutionException e) {
				org.apache.commons.logging.LogFactory.getLog(CBRCentroides.class).error(e);
			}
			return resultadoQuemEnvido;
		
}
		}
