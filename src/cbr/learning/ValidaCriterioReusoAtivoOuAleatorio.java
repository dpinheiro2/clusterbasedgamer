package cbr.learning;

import java.util.Collection;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;
import jcolibri.cbrcore.CBRCase;
import jcolibri.method.retrieve.RetrievalResult;

public interface ValidaCriterioReusoAtivoOuAleatorio {
	public boolean aprenderAtivoOuAleatorio(Collection<RetrievalResult> resultRecuperadoS);


	public boolean aprenderAtivoOuAleatorio(List<TrucoDescription> resultRecuperado);

	public boolean consultarEspecialista(double probabilidadeGanhar, int type);
}
