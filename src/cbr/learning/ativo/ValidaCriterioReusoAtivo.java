package cbr.learning.ativo;

import java.util.Collection;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;

import cbr.learning.ValidaCriterioReusoAtivoOuAleatorio;
import jcolibri.method.retrieve.RetrievalResult;

public class ValidaCriterioReusoAtivo implements ValidaCriterioReusoAtivoOuAleatorio {

	@Override
	public boolean aprenderAtivoOuAleatorio(Collection<RetrievalResult> resultRecuperado) {

		System.out.println("Casos Filtrados " + resultRecuperado.size());
				
		return resultRecuperado.size() < 5? true: false;
	}

	@Override
	public boolean aprenderAtivoOuAleatorio(List<TrucoDescription> resultRecuperado) {

		System.out.println("Casos Filtrados " + resultRecuperado.size());
		return resultRecuperado.size() < 5? true: false;
	}

	@Override
	public boolean consultarEspecialista(double probabilidadeGanhar, int type) {

		if (type == 2) {
			return (/*probabilidadeGanhar > 0 &&*/ probabilidadeGanhar < 0.26) || (probabilidadeGanhar > 0.85);
		} else {
			return (/*probabilidadeGanhar > 0 &&*/ probabilidadeGanhar < 0.5) || (probabilidadeGanhar > 0.85);
		}

	}


}
