package cbr.learning.ativo;

import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.TrucoDescription;
import cbr.learning.ValidaPersistir;


public class ValidaDevePersistirAtivo implements ValidaPersistir{
	
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
		
		TrucoDescription trucoDescriptionParaConsultaPrimeiraCarta = new TrucoDescription();
		trucoDescriptionParaConsultaPrimeiraCarta.setJogadorMao(trucoDescription.getJogadorMao());
		trucoDescriptionParaConsultaPrimeiraCarta.setCartaAltaRobo(trucoDescription.getCartaAltaRobo());
		trucoDescriptionParaConsultaPrimeiraCarta.setCartaMediaRobo(trucoDescription.getCartaMediaRobo());
		trucoDescriptionParaConsultaPrimeiraCarta.setCartaBaixaRobo(trucoDescription.getCartaBaixaRobo());
		trucoDescriptionParaConsultaPrimeiraCarta.setPrimeiraCartaRobo(trucoDescription.getPrimeiraCartaRobo());
		
		
		
		if(trucoDescription.getJogadorMao() == 2) trucoDescriptionParaConsultaPrimeiraCarta.setPrimeiraCartaHumano(trucoDescription.getPrimeiraCartaHumano());
		aprenderPrimeiraCarta = cbr.faltaConhecimentoParaAdecisao(trucoDescriptionParaConsultaPrimeiraCarta, "primeiracarta");
		
		TrucoDescription trucoDescriptionParaConsultaSegundaCarta = new TrucoDescription();
		trucoDescriptionParaConsultaSegundaCarta.setGanhadorPrimeiraRodada(trucoDescription.getGanhadorPrimeiraRodada());
		trucoDescriptionParaConsultaSegundaCarta.setSegundaCartaRoboClustering(trucoDescription.getSegundaCartaRobo());
		if(trucoDescription.getJogadorMao() == 2)    trucoDescription.setSegundaCartaHumano(trucoDescription.getSegundaCartaHumano());
		aprenderSegundaCarta = cbr.faltaConhecimentoParaAdecisao(trucoDescription, "segundacarta");
		
		return aprenderPrimeiraCarta || aprenderSegundaCarta;
	}

	@Override
	public boolean validaCriteriosParaAprenderTruco(TrucoDescription trucoDescription, CbrModular cbr) {
		TrucoDescription trucoDescriptionValidaAprenderTruco = new TrucoDescription();
		trucoDescriptionValidaAprenderTruco.setJogadorMao(trucoDescription.getJogadorMao());
		trucoDescriptionValidaAprenderTruco.setCartaAltaRobo(trucoDescription.getCartaAltaRobo());
		trucoDescriptionValidaAprenderTruco.setCartaMediaRobo(trucoDescription.getCartaMediaRobo());
		trucoDescriptionValidaAprenderTruco.setCartaBaixaRobo(trucoDescription.getCartaBaixaRobo());
		if(trucoDescription.getQuemTruco()!=0 && trucoDescription.getQuemTruco() != null) {
			trucoDescriptionValidaAprenderTruco.setQuemTruco(trucoDescription.getQuemTruco());
			trucoDescriptionValidaAprenderTruco.setQuandoTruco(trucoDescription.getQuandoTruco());
		}
		if(trucoDescription.getQuemRetruco()!=0 && trucoDescription.getQuemRetruco() != null) {
			trucoDescriptionValidaAprenderTruco.setQuemRetruco(trucoDescription.getQuemRetruco());
			trucoDescriptionValidaAprenderTruco.setQuandoRetruco(trucoDescription.getQuandoRetruco());
		}
		if(trucoDescription.getQuemValeQuatro()!=0 && trucoDescription.getQuemValeQuatro() != null) {
			trucoDescriptionValidaAprenderTruco.setQuemValeQuatro(trucoDescription.getQuemValeQuatro());
			trucoDescriptionValidaAprenderTruco.setQuandoValeQuatro(trucoDescription.getQuandoValeQuatro());
		}
		if(trucoDescription.getQuemNegouTruco() != 0 && trucoDescription.getQuemNegouTruco() != null) 
			trucoDescriptionValidaAprenderTruco.setQuemNegouTruco(trucoDescription.getQuemNegouTruco());
		
		return cbr.faltaConhecimentoParaAdecisao(trucoDescriptionValidaAprenderTruco, "truco");
	}

	@Override
	public boolean validaCriteriosParaAprenderEnvido(TrucoDescription trucoDescription, CbrModular cbr) {
		
		TrucoDescription trucoDescriptionAprenderEnvido = new TrucoDescription();
		trucoDescriptionAprenderEnvido.setPontosEnvidoRobo(trucoDescription.getPontosEnvidoRobo());
		trucoDescriptionAprenderEnvido.setJogadorMao(trucoDescription.getJogadorMao());
		if(trucoDescription.getQuemPediuEnvido() !=0 && trucoDescription.getQuemPediuEnvido() != null)    
			trucoDescriptionAprenderEnvido.setQuemPediuEnvido(trucoDescription.getQuemPediuEnvido());
		if(trucoDescription.getQuemPediuRealEnvido() !=0 && trucoDescription.getQuemPediuRealEnvido() != null)    
			trucoDescriptionAprenderEnvido.setQuemPediuRealEnvido(trucoDescription.getQuemPediuRealEnvido());
		if(trucoDescription.getQuemPediuFaltaEnvido() !=0 && trucoDescription.getQuemPediuFaltaEnvido() != null)    
			trucoDescriptionAprenderEnvido.setQuemPediuFaltaEnvido(trucoDescription.getQuemPediuFaltaEnvido());
		if(trucoDescription.getQuemNegouEnvido() !=0 && trucoDescription.getQuemNegouEnvido() != null)    
			trucoDescriptionAprenderEnvido.setQuemNegouEnvido(trucoDescription.getQuemNegouEnvido());
		
		if(trucoDescription.getJogadorMao() == 2)    
			trucoDescriptionAprenderEnvido.setPrimeiraCartaHumano(trucoDescription.getJogadorMao());
		
		
		return cbr.faltaConhecimentoParaAdecisao(trucoDescriptionAprenderEnvido, "envido");
	}

	@Override
	public boolean validaCriteriosParaAprenderFlor(TrucoDescription trucoDescription, CbrModular cbr) {
		// TODO Auto-generated method stub
		TrucoDescription trucoDescriptionAprenderFlor = new TrucoDescription();
		if(trucoDescription.getPontosFlorRobo() != 0 && trucoDescription.getPontosFlorRobo() != null)          
			trucoDescriptionAprenderFlor.setPontosFlorRobo(trucoDescription.getPontosFlorRobo());
		
		if(trucoDescription.getQuemContraFlor() != 0 && trucoDescription.getQuemContraFlor() != null)
			trucoDescriptionAprenderFlor.setQuemContraFlor(trucoDescription.getQuemContraFlor());
		
		if(trucoDescription.getQuemContraFlor() != null && trucoDescription.getQuemContraFlor() !=0)
			return cbr.faltaConhecimentoParaAdecisao(trucoDescriptionAprenderFlor, "flor");
		else
		return false;
	}

	
		
	
}
