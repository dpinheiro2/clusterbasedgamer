package cbr.learning.ativo;

import CentroidesModelo.CaseBasesModelo;
import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.TrucoDescription;
import cbr.learning.Persistir;


public class PersistirAtivo implements Persistir {

	@Override
	public void persistir(TrucoDescription newCase, CbrModular cbr) {
		//TrucoDescription descriptionToLearn = cbr.getValidaDevePersistir().criaDescriptionComOqueOcasoEutil(newCase, cbr);

		//TODO add validaPersistir Threshold e td mais se for o caso
		CaseBasesModelo caseBases = cbr.preencheCaseBase();
		aprendeCasos(caseBases.get_caseBaseMaos().size()+1, newCase, cbr);

	}

	@Override
	public void persistir(TrucoDescription newCase, CbrModular cbr, boolean compulsoryRetention) {
		if (compulsoryRetention) {
			CaseBasesModelo caseBases = cbr.preencheCaseBase();
			aprendeCasos(caseBases.get_caseBaseMaos().size()+1, newCase, cbr);
		}

	}

}
