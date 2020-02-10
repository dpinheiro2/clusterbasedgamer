package cbr.learning.imitacao;

import java.util.Collection;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;

import cbr.learning.ValidaCriterioReusoAtivoOuAleatorio;
import jcolibri.method.retrieve.RetrievalResult;

public class ValidaCriterioReusoImitacao implements ValidaCriterioReusoAtivoOuAleatorio {

	@Override
	public boolean aprenderAtivoOuAleatorio(Collection<RetrievalResult> resultRecuperado) {
				
		return resultRecuperado.size() < 100? true: false;
	}

	@Override
	public boolean aprenderAtivoOuAleatorio(List<TrucoDescription> resultRecuperado) {
				
		return resultRecuperado.size() < 100? true: false;
	}

	@Override
	public boolean consultarEspecialista(double probabilidadeGanhar, int type) {
		return false;
	}


}
