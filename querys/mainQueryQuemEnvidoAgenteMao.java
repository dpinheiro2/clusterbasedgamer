package querys;

import cbr.cbrDescriptions.CentroidesQuemGanhouEnvidoDescription;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.InitializingException;

public class mainQueryQuemEnvidoAgenteMao {

	public static void main(String[] args) throws InitializingException, ExecutionException {
		CentroidesQuemGanhouEnvidoDescription ce = new CentroidesQuemGanhouEnvidoDescription();
		ce.setQuemPediuEnvido(1.0);
		ce.setQuemPediuRealEnvido(2.0);
		ce.setQuemPediuFaltaEnvido(0.0);
		ce.setQuemNegouEnvido(1.0);
		new executeQuerys().getBestResultCentroideQuemEnvido(ce);

	}

}
