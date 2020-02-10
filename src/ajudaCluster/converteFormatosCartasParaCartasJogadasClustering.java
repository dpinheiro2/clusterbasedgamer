package ajudaCluster;

import cbr.cbrDescriptions.TrucoDescription;

public class converteFormatosCartasParaCartasJogadasClustering {

	public TrucoDescription retornaCodificacaoPrimeiraCartaJogadaRoboClustering(TrucoDescription newCase) {
		TrucoDescription newCaseRetorno = newCase;
		newCaseRetorno.setCartaAltaRoboClustering((int) retornaCartaAlta(newCase));
		newCaseRetorno.setCartaMediaRoboClustering((int) retornaCartaMedia(newCase));
		newCaseRetorno.setCartaBaixaRoboClustering((int) retornaCartaBaixa(newCase));
		if (newCase.getCartaAltaRobo().equals(newCase.getPrimeiraCartaRobo())) {
			newCaseRetorno.setPrimeiraCartaRoboClustering(46);
		} else if (newCase.getCartaMediaRobo().equals(newCase.getPrimeiraCartaRobo())) {
			newCaseRetorno.setPrimeiraCartaRoboClustering(16);
		} else if (newCase.getCartaBaixaRobo().equals(newCase.getPrimeiraCartaRobo()) ) {
			newCaseRetorno.setPrimeiraCartaRoboClustering(4);
		}
		
		if (newCase.getCartaAltaRobo().equals(newCase.getSegundaCartaRobo())) {
			newCaseRetorno.setSegundaCartaRoboClustering(46);
		} else if (newCase.getCartaMediaRobo().equals(newCase.getSegundaCartaRobo())) {
			newCaseRetorno.setSegundaCartaRoboClustering(16);
		} else if (newCase.getCartaBaixaRobo().equals(newCase.getSegundaCartaRobo()) ) {
			newCaseRetorno.setSegundaCartaRoboClustering(4);
		}
		
		if (newCase.getCartaAltaRobo().equals(newCase.getTerceiraCartaRobo())) {
			newCaseRetorno.setTerceiraCartaRoboClustering(46);
		} else if (newCase.getCartaMediaRobo().equals(newCase.getTerceiraCartaRobo())) {
			newCaseRetorno.setTerceiraCartaRoboClustering(16);
		} else if (newCase.getCartaBaixaRobo().equals(newCase.getTerceiraCartaRobo())) {
			newCaseRetorno.setTerceiraCartaRoboClustering(4);
		}
		// a partir daqui valida carta virada oponente perdeu ou correu, agente foi ao
		// baralho e agente perdeu
		 if (newCase.getRoboCartaVirada() != null && newCase.getRoboCartaVirada().equals(1)) {
			newCaseRetorno.setPrimeiraCartaRobo(-1);
			newCaseRetorno.setPrimeiraCartaRoboClustering(-1);

		} else  if((newCase.getQuemBaralho() != null) && (newCase.getQuemBaralho().equals(1)  && newCase.getQuandoBaralho().equals(1))) {
			newCaseRetorno.setPrimeiraCartaRobo(-10);
			newCaseRetorno.setPrimeiraCartaRoboClustering(-10);
			newCaseRetorno.setPrimeiraCartaHumano(-10);
			newCaseRetorno.setPrimeiraCartaHumanoClustering(-10);
			//segunda carta
			newCaseRetorno.setSegundaCartaRobo(-10);
			newCaseRetorno.setSegundaCartaRoboClustering(-10);
			newCaseRetorno.setSegundaCartaHumano(-10);
			newCaseRetorno.setSegundaCartaHumanoClustering(-10);
			//terceira carta
			newCaseRetorno.setTerceiraCartaRobo(-10);
			newCaseRetorno.setTerceiraCartaRoboClustering(-10);
			newCaseRetorno.setTerceiraCartaHumano(-10);
			newCaseRetorno.setTerceiraCartaHumanoClustering(-10);
		}else if(newCase.getQuemBaralho() != null || newCase.getQuemTruco() != null){ 
		 
			if(((newCase.getQuemBaralho() != null) && (newCase.getQuemBaralho().equals(2)  && newCase.getQuandoBaralho().equals(1)) )|| 
			((newCase.getQuemNegouTruco() != null) && (newCase.getQuemNegouTruco().equals(2)&&(newCase.getQuandoTruco().equals(1))) ) ) {
			newCaseRetorno.setPrimeiraCartaRobo(-46);
			newCaseRetorno.setPrimeiraCartaRoboClustering(-46);
			newCaseRetorno.setPrimeiraCartaHumano(-46);
			newCaseRetorno.setPrimeiraCartaHumanoClustering(-46);
			
			newCaseRetorno.setSegundaCartaRobo(-46);
			newCaseRetorno.setSegundaCartaRoboClustering(-46);
			newCaseRetorno.setSegundaCartaHumano(-46);
			newCaseRetorno.setSegundaCartaHumanoClustering(-46);
			
			newCaseRetorno.setTerceiraCartaRobo(-46);
			newCase.setTerceiraCartaRoboClustering(-46);
			newCaseRetorno.setTerceiraCartaHumano(-46);
			newCaseRetorno.setTerceiraCartaHumanoClustering(-46);
		}
		}
		return newCaseRetorno;
	}

	public TrucoDescription retornaCodificacaoSegundaCartaJogadaRoboClustering(TrucoDescription newCase) {
		TrucoDescription newCaseRetorno = newCase;
		
		if (newCase.getCartaAltaRobo().equals(newCase.getSegundaCartaRobo())) {
			newCaseRetorno.setSegundaCartaRoboClustering(46);
		} else if (newCase.getCartaMediaRobo().equals(newCase.getSegundaCartaRobo()) ) {
			newCaseRetorno.setSegundaCartaRoboClustering(16);
		} else if (newCase.getCartaBaixaRobo().equals(newCase.getSegundaCartaRobo())) {
			newCaseRetorno.setSegundaCartaRoboClustering(4);
		}
		// a partir daqui valida carta virada oponente perdeu ou correu, agente foi ao
		// baralho e agente perdeu
		 if ((newCase.getRoboCartaVirada() != null) && (newCase.getRoboCartaVirada().equals(2))) {
			newCaseRetorno.setSegundaCartaRobo(-1);
			newCaseRetorno.setSegundaCartaRoboClustering(-1);

		} else if ((newCase.getQuemBaralho() != null) && ((newCase.getQuemBaralho().equals(2)  && newCase.getQuandoBaralho().equals(2))) 
				|| (newCase.getQuemNegouTruco() != null) &&((newCase.getQuemNegouTruco().equals(2) && 
						(newCase.getSegundaCartaRobo() == null || newCase.getSegundaCartaRobo().equals(0))))) {
			newCaseRetorno.setSegundaCartaRobo(-46);
			newCaseRetorno.setSegundaCartaRoboClustering(-46);
			newCaseRetorno.setSegundaCartaHumano(-46);
			newCaseRetorno.setSegundaCartaHumanoClustering(-46);
			
			
			newCaseRetorno.setTerceiraCartaRobo(-46);
			newCaseRetorno.setTerceiraCartaRoboClustering(-46);
			newCaseRetorno.setTerceiraCartaHumano(-46);
			newCaseRetorno.setTerceiraCartaHumanoClustering(-46);
			
			
			

		}

		else if((newCase.getQuemNegouTruco()!=null) &&((newCase.getQuemNegouTruco().equals(1)) && (newCase.getQuandoTruco().equals(2) && (newCase.getQuemTruco().equals(2)) 
				||((newCase.getQuemRetruco() != null || newCase.getQuemValeQuatro() != null) &&(newCase.getQuemRetruco().equals(2) || newCase.getQuemValeQuatro().equals(2)))))) {
			newCaseRetorno.setSegundaCartaRobo(-15);
			newCaseRetorno.setSegundaCartaRoboClustering(-15);
			newCaseRetorno.setSegundaCartaHumano(-15);
			newCaseRetorno.setSegundaCartaHumanoClustering(-15);
			
			newCaseRetorno.setTerceiraCartaRobo(-15);
			newCaseRetorno.setTerceiraCartaRoboClustering(-15);
			newCaseRetorno.setTerceiraCartaHumano(-15);
			newCaseRetorno.setTerceiraCartaHumanoClustering(-15);
	
		
		}
		 if ((newCase.getQuemBaralho() != null) && (newCase.getQuemBaralho().equals(1)  && newCase.getQuandoBaralho().equals(2)  && (newCase.getSegundaCartaRobo() == null || newCase.getSegundaCartaRobo().equals(0)) )) {
			newCaseRetorno.setSegundaCartaRobo(-10);
			newCaseRetorno.setSegundaCartaRoboClustering(-10);
			newCaseRetorno.setSegundaCartaHumano(-10);
			newCaseRetorno.setSegundaCartaHumanoClustering(-10);
			
			newCaseRetorno.setTerceiraCartaRobo(-10);
			newCaseRetorno.setTerceiraCartaRoboClustering(-10);
			newCaseRetorno.setTerceiraCartaHumano(-10);
			newCaseRetorno.setTerceiraCartaHumanoClustering(-10);
		}
		
	else if ((newCase.getSegundaCartaRobo() == null || newCase.getSegundaCartaRobo().equals(0) )
						&& (newCase.getQuemBaralho() != null && newCase.getQuemBaralho().equals(2)) ||
						((newCase.getSegundaCartaRobo() == null ||  newCase.getSegundaCartaRobo().equals(0) ) && (newCase.getQuemNegouTruco().equals(2))) || 
						((newCase.getSegundaCartaRobo() == null || newCase.getSegundaCartaRobo().equals(0)) && newCase.getGanhadorSegundaRodada().equals(1))) {
			newCaseRetorno.setSegundaCartaRobo(-46);
			newCaseRetorno.setSegundaCartaRoboClustering(-46);
			newCaseRetorno.setSegundaCartaHumano(-46);
			newCaseRetorno.setSegundaCartaHumanoClustering(-46);
			
			newCaseRetorno.setTerceiraCartaRobo(-46);
			newCaseRetorno.setTerceiraCartaRoboClustering(-46);
			newCaseRetorno.setTerceiraCartaHumano(-46);
			newCaseRetorno.setTerceiraCartaHumanoClustering(-46);

		}

		return newCaseRetorno;
	}

	public TrucoDescription retornaCodificacaoTerceiraCartaJogadaRoboClustering(TrucoDescription newCase) {
		TrucoDescription newCaseRetorno = newCase;
		if (newCase.getCartaAltaRobo() == newCase.getTerceiraCartaRobo()) {
			newCaseRetorno.setTerceiraCartaRoboClustering(46);	
		} else if (newCase.getCartaMediaRobo() == newCase.getTerceiraCartaRobo()) {
			newCaseRetorno.setTerceiraCartaRoboClustering(16);
		} else if (newCase.getCartaBaixaRobo() == newCase.getTerceiraCartaRobo()) {
			newCaseRetorno.setTerceiraCartaRoboClustering(4);
		}
		// a partir daqui valida carta virada oponente perdeu ou correu, agente foi ao
				// baralho ou agente perdeu
				else if (newCase.getRoboCartaVirada().equals(3) ) {
					newCaseRetorno.setTerceiraCartaRobo(-1);
					newCaseRetorno.setTerceiraCartaRoboClustering(-1);
					

				} else if (newCase.getQuemBaralho().equals(2) && newCase.getQuandoBaralho().equals(3) 
						|| (newCase.getGanhadorTerceiraRodada().equals(1))
								&& (newCase.getTerceiraCartaRobo() == null || newCase.getTerceiraCartaRobo().equals(0) )
					|| ((newCase.getTerceiraCartaRobo() == null || newCase.getTerceiraCartaRobo().equals(0) )
					&& newCase.getGanhadorTerceiraRodada().equals(1) )) {
					newCaseRetorno.setTerceiraCartaRobo(-46);
					newCaseRetorno.setTerceiraCartaRoboClustering(-46);
					newCaseRetorno.setTerceiraCartaHumano(-46);
					newCaseRetorno.setTerceiraCartaHumanoClustering(-46);

				}

				else if (newCase.getQuemBaralho().equals(2) && newCase.getQuandoBaralho().equals(3) 
						|| ((newCase.getTerceiraCartaHumano() == null || newCase.getTerceiraCartaHumano().equals(0) )
								&& newCase.getGanhadorTerceiraRodada().equals(1))) {

					newCaseRetorno.setTerceiraCartaHumano(-46);
					newCaseRetorno.setTerceiraCartaHumanoClustering(-46);

				}
				else if ((newCase.getQuemBaralho().equals(1)  && newCase.getQuandoBaralho().equals(1) ) ) {
					newCaseRetorno.setTerceiraCartaRobo(-10);
					newCaseRetorno.setTerceiraCartaRoboClustering(-10);
					newCaseRetorno.setTerceiraCartaHumano(-10);
					newCaseRetorno.setTerceiraCartaHumanoClustering(-10);
				}
				
				else if ((newCase.getQuemNegouTruco().equals(1) || newCase.getGanhadorTerceiraRodada().equals(2))&& newCase.getTerceiraCartaRobo() == null) {
					newCaseRetorno.setTerceiraCartaRobo(-15);
					newCaseRetorno.setTerceiraCartaRoboClustering(-15);
				
					newCaseRetorno.setTerceiraCartaHumano(-15);
					newCaseRetorno.setTerceiraCartaHumanoClustering(-15);
				
				}		
		
		
		return newCaseRetorno;
	}
	
	
	public double retornaCartaAlta(TrucoDescription newCase) {
		double cartaAltaRoboConsultaCentroide = 0;
		////////System.out.println("carta Alta robo ");
		
		if(newCase.getCartaAltaRobo().equals(1) || newCase.getCartaAltaRobo().equals(1)  || newCase.getCartaAltaRobo().equals(3) || newCase.getCartaAltaRobo().equals(4) ) {
			cartaAltaRoboConsultaCentroide = 3.0;
		}else if(newCase.getCartaAltaRobo().equals(6) || newCase.getCartaAltaRobo().equals(7)  || newCase.getCartaAltaRobo().equals(8)) {
			cartaAltaRoboConsultaCentroide =7.0;
		}else if(newCase.getCartaAltaRobo().equals(12) ) {
			cartaAltaRoboConsultaCentroide = 12.0;
		}else if(newCase.getCartaAltaRobo().equals(16) || newCase.getCartaAltaRobo().equals(24)) {
			cartaAltaRoboConsultaCentroide =20.0;
		}else if(newCase.getCartaAltaRobo().equals(40)  || newCase.getCartaAltaRobo().equals(42) ) {
			cartaAltaRoboConsultaCentroide = 41.0;
		}else if(newCase.getCartaAltaRobo().equals(50)  || newCase.getCartaAltaRobo().equals(52)  ) {
			cartaAltaRoboConsultaCentroide = 51.0;
		}
		return cartaAltaRoboConsultaCentroide;
	}
	
	
	
	public double retornaCartaMedia(TrucoDescription newCase) {
		double cartaMediaRoboConsultaCentroide = 0;
		if(newCase.getCartaMediaRobo().equals(1) || newCase.getCartaMediaRobo().equals(2)|| newCase.getCartaMediaRobo().equals(3) || newCase.getCartaMediaRobo().equals(4) ) {
			cartaMediaRoboConsultaCentroide = 3.0;
		}else if(newCase.getCartaMediaRobo().equals(6) || newCase.getCartaMediaRobo().equals(7)  || newCase.getCartaMediaRobo().equals(8)) {
			cartaMediaRoboConsultaCentroide =7.0;
		}else if(newCase.getCartaMediaRobo().equals(12) ) {
			cartaMediaRoboConsultaCentroide = 12.0;
		}else if(newCase.getCartaMediaRobo().equals(16) || newCase.getCartaMediaRobo().equals(24)  ) {
			cartaMediaRoboConsultaCentroide =20.0;
		}else if(newCase.getCartaMediaRobo().equals(40)  || newCase.getCartaMediaRobo().equals(42)  ) {
			cartaMediaRoboConsultaCentroide = 41.0;
		}else if(newCase.getCartaMediaRobo().equals(50)  || newCase.getCartaMediaRobo().equals(52) ) {
			cartaMediaRoboConsultaCentroide = 51.0;
		}
		return cartaMediaRoboConsultaCentroide;
	}
	
	public double retornaCartaBaixa(TrucoDescription newCase) {
		double cartaBaixaRoboConsultaCentroide = 0;
		if(newCase.getCartaBaixaRobo().equals(1) || newCase.getCartaBaixaRobo().equals(2)  || newCase.getCartaBaixaRobo().equals(3) || newCase.getCartaBaixaRobo().equals(4) ) {
			cartaBaixaRoboConsultaCentroide = 3.0;
		}else if(newCase.getCartaBaixaRobo().equals(6) || newCase.getCartaBaixaRobo().equals(7)  || newCase.getCartaBaixaRobo().equals(8)) {
			cartaBaixaRoboConsultaCentroide =7.0;
		}else if(newCase.getCartaBaixaRobo().equals(12)  ) {
			cartaBaixaRoboConsultaCentroide = 12.0;
		}else if(newCase.getCartaBaixaRobo().equals(16) || newCase.getCartaBaixaRobo().equals(24)  ) {
			cartaBaixaRoboConsultaCentroide =20.0;
		}else if(newCase.getCartaBaixaRobo().equals(40) || newCase.getCartaBaixaRobo().equals(42)  ) {
			cartaBaixaRoboConsultaCentroide = 41.0;
		}else if(newCase.getCartaBaixaRobo().equals(50)  || newCase.getCartaBaixaRobo().equals(52)  ) {
			cartaBaixaRoboConsultaCentroide = 51.0;
		}
		return cartaBaixaRoboConsultaCentroide;
	}

	public TrucoDescription retornaCartasJogadasHumanoClustering(TrucoDescription newCase) {
		if(newCase.getPrimeiraCartaHumano() != null) newCase.setPrimeiraCartaHumanoClustering((int) transformaCartasEmCartasClustering(newCase.getPrimeiraCartaHumano()));
		if(newCase.getSegundaCartaHumano() != null) newCase.setSegundaCartaHumanoClustering((int) transformaCartasEmCartasClustering(newCase.getSegundaCartaHumano()));
		if(newCase.getTerceiraCartaHumano() != null) newCase.setTerceiraCartaHumanoClustering(transformaCartasEmCartasClustering(newCase.getTerceiraCartaHumano()));
		return newCase;
	}
	
	public int transformaCartasEmCartasClustering(Integer carta) {
		int cartaClustering = 0;
		if(carta.equals(1) || carta.equals(2)  || carta.equals(3) || carta.equals(4) ) {
			cartaClustering = 3;
		}else if(carta.equals(6) || carta.equals(7)  || carta.equals(8)) {
			cartaClustering =7;
		}else if(carta.equals(12)  ) {
			cartaClustering = 12;
		}else if(carta.equals(16) || carta.equals(24)  ) {
			cartaClustering =20;
		}else if(carta.equals(40) || carta.equals(42)  ) {
			cartaClustering = 41;
		}else if(carta.equals(50)  || carta.equals(52)  ) {
			cartaClustering = 51;
		}
		return cartaClustering;
	}
}