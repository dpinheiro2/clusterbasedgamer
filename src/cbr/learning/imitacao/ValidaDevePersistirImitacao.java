package cbr.learning.imitacao;

import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.TrucoDescription;
import cbr.learning.ValidaPersistir;


public class ValidaDevePersistirImitacao implements ValidaPersistir{
	
	@Override
	public TrucoDescription criaDescriptionComOqueOcasoEutil(TrucoDescription trucoDescription, CbrModular cbr) {
		
		
		trucoDescription.setUtilCarta(validaCriteriosParaAprenderCarta(trucoDescription, cbr)== true?1:0);
		
		
		trucoDescription.setUtilEnvido(validaCriteriosParaAprenderEnvido(trucoDescription, cbr)== true ?1 : 0);
		
		
		trucoDescription.setUtilTruco(validaCriteriosParaAprenderTruco(trucoDescription, cbr) == true? 1: 0);
		
		
		trucoDescription.setUtilFlor(validaCriteriosParaAprenderFlor(trucoDescription, cbr)== true ? 1 : 0);
		
		return trucoDescription;
	}
	
	@Override
	public boolean validaCriteriosParaAprenderCarta(TrucoDescription trucoDescription, CbrModular cbr) {
		boolean aprenderPrimeiraCarta = false;
		boolean aprenderSegundaCarta = false;
		
		TrucoDescription trucoDescriptionParaConsultaCartas = criaDescriptionComInformacoesRelevantesCarta(trucoDescription, cbr);
		
		

		if(trucoDescription.getJogadorMao().equals(2) ) trucoDescriptionParaConsultaCartas.setPrimeiraCartaHumano(trucoDescription.getPrimeiraCartaHumano());
		aprenderPrimeiraCarta = cbr.faltaConhecimentoParaAdecisao(trucoDescriptionParaConsultaCartas, "primeiracarta");
		
		
		if(trucoDescription.getJogadorMao().equals(2))    trucoDescriptionParaConsultaCartas.setSegundaCartaHumano(trucoDescription.getSegundaCartaHumano());
		aprenderSegundaCarta = cbr.faltaConhecimentoParaAdecisao(trucoDescriptionParaConsultaCartas, "segundacarta");
		
		boolean naoIgnorar = trucoDescriptionParaConsultaCartas.getUtilCarta() == null;
		return (aprenderPrimeiraCarta || aprenderSegundaCarta) && naoIgnorar;
	}

	@Override
	public boolean validaCriteriosParaAprenderTruco(TrucoDescription trucoDescription, CbrModular cbr) {
		TrucoDescription trucoDescriptionValidaAprenderTruco = criaDescriptionComInformacoesRelevantesTruco(trucoDescription, cbr);
		boolean naoIgnorar = trucoDescriptionValidaAprenderTruco.getUtilTruco() == null;
			//2 significa ignorar
		return cbr.faltaConhecimentoParaAdecisao(trucoDescriptionValidaAprenderTruco, "truco") && naoIgnorar;
	}

	@Override
	public boolean validaCriteriosParaAprenderEnvido(TrucoDescription trucoDescription, CbrModular cbr) {
		
		TrucoDescription trucoDescriptionAprenderEnvido = criaDescriptionComInformacoesRelevantesEnvido(trucoDescription, cbr);
		
		boolean naoIgnorar = trucoDescriptionAprenderEnvido.getUtilEnvido() == null;
		
		
		return cbr.faltaConhecimentoParaAdecisao(trucoDescriptionAprenderEnvido, "envido") && naoIgnorar;
	}

	@Override
	public boolean validaCriteriosParaAprenderFlor(TrucoDescription trucoDescription, CbrModular cbr) {
		// TODO Auto-generated method stub
		TrucoDescription trucoDescriptionAprenderFlor = criaDescriptionComInformacoesRelevantesEnvido(trucoDescription, cbr);
		if(!trucoDescription.getPontosFlorRobo().equals(0)  && trucoDescription.getPontosFlorRobo() != null)          
			trucoDescriptionAprenderFlor.setPontosFlorRobo(trucoDescription.getPontosFlorRobo());
		
		if(!trucoDescription.getQuemContraFlor().equals(0)  && trucoDescription.getQuemContraFlor() != null)
			trucoDescriptionAprenderFlor.setQuemContraFlor(trucoDescription.getQuemContraFlor());
		
		if(trucoDescription.getQuemContraFlor() != null && !trucoDescription.getQuemContraFlor().equals(0) )
			return cbr.faltaConhecimentoParaAdecisao(trucoDescriptionAprenderFlor, "flor");
		else
		return false;
	}

	
		
	
}
