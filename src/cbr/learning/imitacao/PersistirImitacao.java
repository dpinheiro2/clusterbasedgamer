package cbr.learning.imitacao;

import CentroidesModelo.CaseBasesModelo;
import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.TrucoDescription;
import cbr.learning.Persistir;


public class PersistirImitacao implements Persistir {

	@Override
	public void persistir(TrucoDescription newCase, CbrModular cbr) {
       TrucoDescription descriptionToLearn = cbr.getValidaDevePersistir().criaDescriptionComOqueOcasoEutil(newCase, cbr);
       CaseBasesModelo caseBases = cbr.preencheCaseBase();
       aprendeCasos(caseBases.get_caseBaseMaos().size()+1, descriptionToLearn, cbr);
	
       
	}

    @Override
    public void persistir(TrucoDescription newCase, CbrModular cbr, boolean compulsoryRetention) {

    }

}
