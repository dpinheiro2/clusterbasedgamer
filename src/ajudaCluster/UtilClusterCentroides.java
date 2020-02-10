package ajudaCluster;

import CentroidesModelo.AtributosConsultaCentroideJogadaModelo;
import CentroidesModelo.AtributosConsultaCentroideQuandoTrucoModelo;
import CentroidesModelo.AtributosConsultaCentroideQuemGanhouEnvido;
import CentroidesModelo.AtributosConsultaCentroideQuemTrucoModelo;
import cbr.cbrDescriptions.CentroidesPrimeiraCartaRoboMaoDescription;
import cbr.cbrDescriptions.CentroidesPrimeiraCartaRoboPeDescription;
import cbr.cbrDescriptions.CentroidesQuemTrucoDescription;
import cbr.cbrDescriptions.CentroidesSegundaCartaRoboGanhouAprimeiraDescription;
import cbr.cbrDescriptions.CentroidesSegundaCartaRoboPerdeuAprimeiraDescription;
import cbr.cbrDescriptions.CentroidesTerceiraCartaRoboGanhouAsegundaDescription;
import cbr.cbrDescriptions.CentroidesTerceiraCartaRoboPerdeuAsegundaDescription;

import cbr.cbrDescriptions.TrucoDescription;
import cbr.cbrDescriptions.CentroidesQuemGanhouEnvidoDescription;



public class UtilClusterCentroides {
	
	
	public CentroidesQuemGanhouEnvidoDescription retornaCentroidQuemGanhouEnvidoDescription(AtributosConsultaCentroideQuemGanhouEnvido atributosConsultaCentroideQuemGanhouEnvido) {
		CentroidesQuemGanhouEnvidoDescription quemGanhouEnvido = new CentroidesQuemGanhouEnvidoDescription();
		double quemPediuEnvido =0;
		double quemPediuRealEnvido =0;
		double quemPediuFaltaEnvido = 0;
		double quemNegouEnvido =0;
		//envido
		if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuEnvido() ==1) quemPediuEnvido =1;
		else if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuRealEnvido() == 2) quemPediuEnvido = 3;
		else if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuRealEnvido() == 0) quemPediuEnvido =2;
		//realEnvido
		if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuRealEnvido() ==1) quemPediuRealEnvido =1;
		else if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuRealEnvido() == 2) quemPediuRealEnvido = 3;
		else if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuRealEnvido() == 0) quemPediuRealEnvido =2;
		//faltaEnvido
		 if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuFaltaEnvido() ==1) quemPediuFaltaEnvido =1;
		 else if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuFaltaEnvido() == 2) quemPediuFaltaEnvido = 3;
		 else if(atributosConsultaCentroideQuemGanhouEnvido.getQuemPediuFaltaEnvido() == 0) quemPediuFaltaEnvido =2;
				
			
		quemGanhouEnvido.setQuemPediuEnvido(quemPediuEnvido);
		quemGanhouEnvido.setQuemPediuRealEnvido(quemPediuRealEnvido);
		quemGanhouEnvido.setQuemPediuFaltaEnvido(quemPediuFaltaEnvido);
		quemGanhouEnvido.setPontosEnvidoRobo(atributosConsultaCentroideQuemGanhouEnvido.getPontosEnvidoRobo());
		return quemGanhouEnvido;
	}
	
	public CentroidesQuemTrucoDescription retornaCentroidQuemTrucoDescription(AtributosConsultaCentroideQuemTrucoModelo atributosConsultaCentroideQuemTrucoModelo) {
		CentroidesQuemTrucoDescription centroideKmeansQuemTrucoDescription = new CentroidesQuemTrucoDescription();
		double quemPediuTruco =0;
		double quemPediuRetruco =0;
		double quemPediuValeQuatro = 0;
		double quemNegouTruco =0;
		
		double cartaAltaRoboClustering =0;
		double cartaMediaRoboClustering =0;
		double cartaBaixaRoboClustering =0;
		
		//truco
				if(atributosConsultaCentroideQuemTrucoModelo.getQuemTruco() ==1) quemPediuTruco =1;
				else if(atributosConsultaCentroideQuemTrucoModelo.getQuemTruco() == 2) quemPediuTruco = 3;
				else if(atributosConsultaCentroideQuemTrucoModelo.getQuemTruco() == 0) quemPediuTruco =2;
				//retruco
				if(atributosConsultaCentroideQuemTrucoModelo.getQuemRetruco() ==1) quemPediuRetruco =1;
				else if(atributosConsultaCentroideQuemTrucoModelo.getQuemRetruco() == 2) quemPediuRetruco = 3;
				else if(atributosConsultaCentroideQuemTrucoModelo.getQuemRetruco() == 0) quemPediuRetruco =2;
				//valequatro
				if(atributosConsultaCentroideQuemTrucoModelo.getQuemValeQuatro() ==1) quemPediuValeQuatro =1;
				else if(atributosConsultaCentroideQuemTrucoModelo.getQuemValeQuatro() == 2) quemPediuValeQuatro = 3;
				else if(atributosConsultaCentroideQuemTrucoModelo.getQuemValeQuatro() == 0) quemPediuValeQuatro =2;
					
				cartaAltaRoboClustering = atributosConsultaCentroideQuemTrucoModelo.getCartaAltaRoboClustering();
				cartaMediaRoboClustering = atributosConsultaCentroideQuemTrucoModelo.getCartaMediaRoboClustering();
				cartaBaixaRoboClustering = atributosConsultaCentroideQuemTrucoModelo.getCartaBaixaRoboClustering();
		
		
		centroideKmeansQuemTrucoDescription.setQuemTruco(atributosConsultaCentroideQuemTrucoModelo.getQuemTruco());
		centroideKmeansQuemTrucoDescription.setQuemRetruco(atributosConsultaCentroideQuemTrucoModelo.getQuemRetruco());
		centroideKmeansQuemTrucoDescription.setQuemValeQuatro(atributosConsultaCentroideQuemTrucoModelo.getQuemValeQuatro());
		
		centroideKmeansQuemTrucoDescription.setCartaAltaRoboClustering(cartaAltaRoboClustering);
		centroideKmeansQuemTrucoDescription.setCartaMediaRoboClustering(cartaMediaRoboClustering);
		centroideKmeansQuemTrucoDescription.setCartaBaixaRoboClustering(cartaBaixaRoboClustering);
		
		return centroideKmeansQuemTrucoDescription;
	}
	

	
	
	public CentroidesPrimeiraCartaRoboMaoDescription retornaCentroidPrimeiraCartaRoboMaoDescription(
			AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo) {
		CentroidesPrimeiraCartaRoboMaoDescription centroidePrimeiraCartaRoboMao = new CentroidesPrimeiraCartaRoboMaoDescription();
		centroidePrimeiraCartaRoboMao.setCartaAltaRoboClustering(atributosConsultaCentroideJogadaModelo.getCartaAltaRoboConsultaCentroidePrimeiraCarta());
		centroidePrimeiraCartaRoboMao.setCartaMediaRoboClustering(atributosConsultaCentroideJogadaModelo.getCartaMediaRoboConsultaCentroidePrimeiraCarta());
		centroidePrimeiraCartaRoboMao.setCartaBaixaRoboClustering(atributosConsultaCentroideJogadaModelo.getCartaBaixaRoboConsultaCentroidePrimeiraCarta());
		centroidePrimeiraCartaRoboMao.setPrimeiraCartaRoboClustering(atributosConsultaCentroideJogadaModelo.getPrimeiraCartaRoboClustering());
		return centroidePrimeiraCartaRoboMao;
	}
	
	public CentroidesPrimeiraCartaRoboPeDescription retornaCentroidPrimeiraCartaRoboPeDescription(
			AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo) {
		CentroidesPrimeiraCartaRoboPeDescription centroidePrimeiraCartaRoboPe = new CentroidesPrimeiraCartaRoboPeDescription();
		centroidePrimeiraCartaRoboPe.setCartaAltaRoboClustering(atributosConsultaCentroideJogadaModelo.getCartaAltaRoboConsultaCentroidePrimeiraCarta());
		centroidePrimeiraCartaRoboPe.setCartaMediaRoboClustering(atributosConsultaCentroideJogadaModelo.getCartaMediaRoboConsultaCentroidePrimeiraCarta());
		centroidePrimeiraCartaRoboPe.setCartaBaixaRoboClustering(atributosConsultaCentroideJogadaModelo.getCartaBaixaRoboConsultaCentroidePrimeiraCarta());
		centroidePrimeiraCartaRoboPe.setPrimeiraCartaRoboClustering(atributosConsultaCentroideJogadaModelo.getPrimeiraCartaRoboClustering());
		centroidePrimeiraCartaRoboPe.setPrimeiraCartaHumanoClustering(atributosConsultaCentroideJogadaModelo.getPrimeiraCartaHumanoClustering());
		return centroidePrimeiraCartaRoboPe;
	}
	
	
	public CentroidesSegundaCartaRoboGanhouAprimeiraDescription retornaCentroidSegundaCartaRoboGanhouAprimeira(
			AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo) {
		CentroidesSegundaCartaRoboGanhouAprimeiraDescription centroideSegundaCartaRoboGanhouAprimeira = new CentroidesSegundaCartaRoboGanhouAprimeiraDescription();
		centroideSegundaCartaRoboGanhouAprimeira.setSegundaCartaRoboClustering(atributosConsultaCentroideJogadaModelo.getSegundaCartaRoboClustering());
		return centroideSegundaCartaRoboGanhouAprimeira;
	}
	
	public CentroidesSegundaCartaRoboPerdeuAprimeiraDescription retornaCentroidSegundaCartaRoboPerdeuAprimeira(
			AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo) {
		CentroidesSegundaCartaRoboPerdeuAprimeiraDescription centroidesSegundaCartaRoboPerdeuAprimeira = new CentroidesSegundaCartaRoboPerdeuAprimeiraDescription();
		centroidesSegundaCartaRoboPerdeuAprimeira.setSegundaCartaHumanoClustering(atributosConsultaCentroideJogadaModelo.getSegundaCartaHumanoClustering());
		centroidesSegundaCartaRoboPerdeuAprimeira.setSegundaCartaRoboClustering(atributosConsultaCentroideJogadaModelo.getSegundaCartaRoboClustering());
		
		return centroidesSegundaCartaRoboPerdeuAprimeira;
	}
	
	
	public CentroidesTerceiraCartaRoboGanhouAsegundaDescription retornaCentroidTerceiraCartaRoboGanhouASegunda(
			AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo) {
		CentroidesTerceiraCartaRoboGanhouAsegundaDescription centroideTerceiraCartaRoboGanhouASegunda = new CentroidesTerceiraCartaRoboGanhouAsegundaDescription();
		centroideTerceiraCartaRoboGanhouASegunda.setTerceiraCartaRoboClustering(atributosConsultaCentroideJogadaModelo.getTerceiraCartaRoboClustering());
		return centroideTerceiraCartaRoboGanhouASegunda;
	}
	
	public CentroidesTerceiraCartaRoboPerdeuAsegundaDescription retornaCentroidTerceiraCartaRoboPerdeuASegunda(
			AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo) {
		CentroidesTerceiraCartaRoboPerdeuAsegundaDescription centroidesTerceiraCartaRoboPerdeuASegunda = new CentroidesTerceiraCartaRoboPerdeuAsegundaDescription();
		centroidesTerceiraCartaRoboPerdeuASegunda.setTerceiraCartaHumanoClustering(atributosConsultaCentroideJogadaModelo.getTerceiraCartaHumanoClustering());
		centroidesTerceiraCartaRoboPerdeuASegunda.setTerceiraCartaRoboClustering(atributosConsultaCentroideJogadaModelo.getTerceiraCartaRoboClustering());
		
		return centroidesTerceiraCartaRoboPerdeuASegunda;
	}
	public AtributosConsultaCentroideJogadaModelo retornaAtributosConsultaClusteringJogada(TrucoDescription newCase) {
		//ATRIBUTOS PRIMEIRA CARTA CONSULTAS
				Double cartaAltaRoboConsultaCentroidePrimeiraCarta = 0.0;
				Double cartaMediaRoboConsultaCentroidePrimeiraCarta =0.0;
				Double cartaBaixaRoboConsultaCentroidePrimeiraCarta =0.0;
				Double primeiraCartaRoboClustering =0.0;
				Double primeiraCartaHumanoClustering =0.0;
				//SEGUNDA CARTA atributos
				Double segundaCartaRoboClustering =0.0;
				Double segundaCartaHumanoClustering =0.0;
				//Terceira CARTA atributos
				Double terceiraCartaRoboClustering =0.0;
				Double terceiraCartaHumanoClustering =0.0;
		if(newCase.getCartaAltaRobo().equals(1) || newCase.getCartaAltaRobo().equals(2)  || newCase.getCartaAltaRobo().equals(3) || newCase.getCartaAltaRobo().equals(4) ) {
			cartaAltaRoboConsultaCentroidePrimeiraCarta =3.0;
		}else if(newCase.getCartaAltaRobo().equals(6) || newCase.getCartaAltaRobo().equals(7)  || newCase.getCartaAltaRobo().equals(8)) {
			cartaAltaRoboConsultaCentroidePrimeiraCarta =7.0;
		}else if(newCase.getCartaAltaRobo().equals(12) ) {
			cartaAltaRoboConsultaCentroidePrimeiraCarta =12.0;
		}else if(newCase.getCartaAltaRobo().equals(20) || newCase.getCartaAltaRobo().equals(24)  ) {
			cartaAltaRoboConsultaCentroidePrimeiraCarta =20.0;
		}else if(newCase.getCartaAltaRobo().equals(40) || newCase.getCartaAltaRobo().equals(42)  ) {
			cartaAltaRoboConsultaCentroidePrimeiraCarta =41.0;
		}else if(newCase.getCartaAltaRobo().equals(50) || newCase.getCartaAltaRobo().equals(52)  ) {
			cartaAltaRoboConsultaCentroidePrimeiraCarta =51.0;
		}else if(newCase.getRoboCartaVirada()!=null) {
			if(newCase.getRoboCartaVirada().equals(1))
			primeiraCartaRoboClustering =-1.0;
		}
		if(newCase.getCartaMediaRobo().equals(1) || newCase.getCartaMediaRobo().equals(2) || newCase.getCartaMediaRobo().equals(3) 
				|| newCase.getCartaMediaRobo().equals(4)) {
			cartaMediaRoboConsultaCentroidePrimeiraCarta =3.0;
		}else if(newCase.getCartaMediaRobo().equals(6) || newCase.getCartaMediaRobo().equals(7)  || newCase.getCartaMediaRobo().equals(8)) {
			cartaMediaRoboConsultaCentroidePrimeiraCarta =7.0;
		}else if(newCase.getCartaMediaRobo().equals(12) ) {
			cartaMediaRoboConsultaCentroidePrimeiraCarta =12.0;
		}else if(newCase.getCartaMediaRobo().equals(20) || newCase.getCartaMediaRobo().equals(24)  ) {
			cartaMediaRoboConsultaCentroidePrimeiraCarta =20.0;
		}else if(newCase.getCartaMediaRobo().equals(40) || newCase.getCartaMediaRobo().equals(42)  ) {
			cartaMediaRoboConsultaCentroidePrimeiraCarta =41.0;
		}else if(newCase.getCartaMediaRobo().equals(50) || newCase.getCartaMediaRobo().equals(52)  ) {
			cartaMediaRoboConsultaCentroidePrimeiraCarta =51.0;
		}
		
		if(newCase.getCartaBaixaRobo().equals(1) || newCase.getCartaBaixaRobo().equals(2)  || newCase.getCartaBaixaRobo().equals(3) 
				|| newCase.getCartaBaixaRobo().equals(4) ) {
			cartaBaixaRoboConsultaCentroidePrimeiraCarta =3.0;
		}else if(newCase.getCartaBaixaRobo().equals(6) || newCase.getCartaBaixaRobo().equals(7) || newCase.getCartaBaixaRobo().equals(8)) {
			cartaBaixaRoboConsultaCentroidePrimeiraCarta =7.0;
		}else if(newCase.getCartaBaixaRobo().equals(12) ) {
			cartaBaixaRoboConsultaCentroidePrimeiraCarta =12.0;
		}else if(newCase.getCartaBaixaRobo().equals(20) || newCase.getCartaBaixaRobo().equals(24) ) {
			cartaBaixaRoboConsultaCentroidePrimeiraCarta =20.0;
		}else if(newCase.getCartaBaixaRobo().equals(40) || newCase.getCartaBaixaRobo().equals(42)  ) {
			cartaBaixaRoboConsultaCentroidePrimeiraCarta =41.0;
		}else if(newCase.getCartaBaixaRobo().equals(50) || newCase.getCartaBaixaRobo().equals(52)  ) {
			cartaBaixaRoboConsultaCentroidePrimeiraCarta =51.0;
		}
		
		if(newCase.getCartaAltaRobo().equals(newCase.getPrimeiraCartaRobo()) ) {
			primeiraCartaRoboClustering = 46.0;
		}else if(newCase.getCartaMediaRobo().equals(newCase.getPrimeiraCartaRobo())) {
			primeiraCartaRoboClustering =16.0;
		}else if(newCase.getCartaBaixaRobo().equals(newCase.getPrimeiraCartaRobo())) {
			primeiraCartaRoboClustering =4.0;
		}else if(newCase.getRoboCartaVirada()!=null) {
			if(newCase.getRoboCartaVirada().equals(1))
			primeiraCartaRoboClustering =-1.0;
		}
		if(newCase.getPrimeiraCartaHumano() != null) {
		if(newCase.getPrimeiraCartaHumano().equals(1) || newCase.getPrimeiraCartaHumano().equals(2)
				|| newCase.getPrimeiraCartaHumano().equals(3) || newCase.getPrimeiraCartaHumano().equals(4) ) {
			primeiraCartaHumanoClustering =3.0;
		}else if(newCase.getPrimeiraCartaHumano().equals(6) || newCase.getPrimeiraCartaHumano().equals(7)  || newCase.getPrimeiraCartaHumano().equals(8)) {
			primeiraCartaHumanoClustering =7.0;
		}else if(newCase.getPrimeiraCartaHumano().equals(12) ) {
			primeiraCartaHumanoClustering =12.0;
		}else if(newCase.getPrimeiraCartaHumano().equals(20) || newCase.getPrimeiraCartaHumano().equals(24)  ) {
			primeiraCartaHumanoClustering =20.0;
		}else if(newCase.getPrimeiraCartaHumano().equals(40) || newCase.getPrimeiraCartaHumano().equals(42)  ) {
			primeiraCartaHumanoClustering =41.0;
		}else if(newCase.getPrimeiraCartaHumano().equals(50) || newCase.getPrimeiraCartaHumano().equals(52)  ) {
			primeiraCartaHumanoClustering =51.0;
		}else if(newCase.getHumanoCartaVirada()!=null) {
			if(newCase.getHumanoCartaVirada().equals(2))
			primeiraCartaHumanoClustering =-1.0;
		}
		}
		
		if(newCase.getCartaAltaRobo().equals( newCase.getSegundaCartaRobo())) {
			segundaCartaRoboClustering = 46.0;
		}else if(newCase.getCartaMediaRobo().equals(newCase.getSegundaCartaRobo())) {
			segundaCartaRoboClustering =16.0;
		}else if(newCase.getCartaBaixaRobo().equals(newCase.getSegundaCartaRobo()) ) {
			segundaCartaRoboClustering =4.0;
		}else if(newCase.getRoboCartaVirada()!=null) {
			if(newCase.getRoboCartaVirada().equals(2))
			segundaCartaRoboClustering =-1.0;
		}
	if(newCase.getSegundaCartaHumano() != null) {
		if(newCase.getSegundaCartaHumano().equals(1) || newCase.getSegundaCartaHumano().equals(2) || newCase.getSegundaCartaHumano().equals(3)
				|| newCase.getSegundaCartaHumano().equals(4)) {
			segundaCartaHumanoClustering =3.0;
		}else if(newCase.getSegundaCartaHumano().equals(6) || newCase.getSegundaCartaHumano().equals(7)  || newCase.getSegundaCartaHumano().equals(8)) {
			segundaCartaHumanoClustering =7.0;
		}else if(newCase.getSegundaCartaHumano().equals(12) ) {
			segundaCartaHumanoClustering =12.0;
		}else if(newCase.getSegundaCartaHumano().equals(20) || newCase.getSegundaCartaHumano().equals(24) ) {
			segundaCartaHumanoClustering =20.0;
		}else if(newCase.getSegundaCartaHumano().equals(40) || newCase.getSegundaCartaHumano().equals(42)  ) {
			segundaCartaHumanoClustering =41.0;
		}else if(newCase.getSegundaCartaHumano().equals(50) || newCase.getSegundaCartaHumano().equals(52)  ) {
			segundaCartaHumanoClustering =51.0;
		}else if(newCase.getHumanoCartaVirada()!=null) {
			if(newCase.getHumanoCartaVirada().equals(2))
			segundaCartaHumanoClustering =-1.0;
		}
	}
		if(newCase.getCartaAltaRobo().equals(newCase.getTerceiraCartaRobo()) ) {
			terceiraCartaRoboClustering = 46.0;
		}else if(newCase.getCartaMediaRobo().equals(newCase.getTerceiraCartaRobo()) ) {
			terceiraCartaRoboClustering =16.0;
		}else if(newCase.getCartaBaixaRobo().equals(newCase.getTerceiraCartaRobo()) ) {
			terceiraCartaRoboClustering =4.0;
		}else if(newCase.getRoboCartaVirada()!=null) {
			if(newCase.getRoboCartaVirada().equals(3))
			terceiraCartaRoboClustering =-1.0;
		}
		if(newCase.getTerceiraCartaHumano() != null) {
		if(newCase.getTerceiraCartaHumano().equals(1) || newCase.getTerceiraCartaHumano().equals(2)  || newCase.getTerceiraCartaHumano().equals(3) 
				|| newCase.getTerceiraCartaHumano().equals(4)) {
			terceiraCartaHumanoClustering =3.0;
		}else if(newCase.getTerceiraCartaHumano().equals(6) || newCase.getTerceiraCartaHumano().equals(7)  || newCase.getTerceiraCartaHumano().equals(8)) {
			terceiraCartaHumanoClustering =7.0;
		}else if(newCase.getTerceiraCartaHumano().equals(12) ) {
			terceiraCartaHumanoClustering =12.0;
		}else if(newCase.getTerceiraCartaHumano().equals(20) || newCase.getTerceiraCartaHumano().equals(24)  ) {
			terceiraCartaHumanoClustering =20.0;
		}else if(newCase.getTerceiraCartaHumano().equals(40) || newCase.getTerceiraCartaHumano().equals(42)  ) {
			terceiraCartaHumanoClustering =41.0;
		}else if(newCase.getTerceiraCartaHumano().equals(50) || newCase.getTerceiraCartaHumano().equals(52) ) {
			terceiraCartaHumanoClustering =51.0;
		}else if(newCase.getHumanoCartaVirada()!=null) {
			if(newCase.getHumanoCartaVirada().equals(3))
			primeiraCartaHumanoClustering =-1.0;
		}
		
		}
		if(newCase.getCartaAltaRobo().equals(newCase.getTerceiraCartaRobo()) ) {
			terceiraCartaRoboClustering = 46.0;
		}else if(newCase.getCartaMediaRobo().equals(newCase.getTerceiraCartaRobo()) ) {
			terceiraCartaRoboClustering =16.0;
		}else if(newCase.getCartaBaixaRobo().equals(newCase.getTerceiraCartaRobo()) ) {
			terceiraCartaRoboClustering =4.0;
		}
		
		return new AtributosConsultaCentroideJogadaModelo(cartaAltaRoboConsultaCentroidePrimeiraCarta, cartaMediaRoboConsultaCentroidePrimeiraCarta, cartaBaixaRoboConsultaCentroidePrimeiraCarta, primeiraCartaRoboClustering, primeiraCartaHumanoClustering, segundaCartaRoboClustering, segundaCartaHumanoClustering, terceiraCartaRoboClustering, terceiraCartaHumanoClustering);
	}
	
	public AtributosConsultaCentroideQuemTrucoModelo retornaAtributosConsultaClusteringQuemTruco(TrucoDescription newCase) {
		AtributosConsultaCentroideQuemTrucoModelo atributos = new AtributosConsultaCentroideQuemTrucoModelo();
		
	    if(newCase.getQuemTruco() != null) 	atributos.setQuemTruco(newCase.getQuemTruco());
		if(newCase.getQuemRetruco() != null) atributos.setQuemRetruco(newCase.getQuemRetruco());
		if(newCase.getQuemValeQuatro() != null)		atributos.setQuemValeQuatro(newCase.getQuemValeQuatro());
		AtributosConsultaCentroideJogadaModelo atributosCartasRecebidas = retornaAtributosConsultaClusteringJogada(newCase);
		
		atributos.setCartaAltaRoboClustering(atributosCartasRecebidas.getCartaAltaRoboConsultaCentroidePrimeiraCarta());
		atributos.setCartaMediaRoboClustering(atributosCartasRecebidas.getCartaMediaRoboConsultaCentroidePrimeiraCarta());
		atributos.setCartaBaixaRoboClustering(atributosCartasRecebidas.getCartaBaixaRoboConsultaCentroidePrimeiraCarta());
		
				
		return atributos;
	}

	public AtributosConsultaCentroideQuandoTrucoModelo retornaAtributosConsultaClusteringQuandoTruco(TrucoDescription newCase) {
		AtributosConsultaCentroideQuandoTrucoModelo atributos = new AtributosConsultaCentroideQuandoTrucoModelo();
		if(newCase.getQuandoTruco()!= null)
		atributos.setQuandoTruco(newCase.getQuandoTruco());
		if(newCase.getQuandoRetruco()!= null)
		atributos.setQuandoRetruco(newCase.getQuandoRetruco());
		if(newCase.getQuandoValeQuatro()!= null)
		atributos.setQuandoValeQuatro(newCase.getQuandoValeQuatro());
		
AtributosConsultaCentroideJogadaModelo atributosCartasRecebidas = retornaAtributosConsultaClusteringJogada(newCase);
		
		atributos.setCartaAltaRoboClustering(atributosCartasRecebidas.getCartaAltaRoboConsultaCentroidePrimeiraCarta());
		atributos.setCartaMediaRoboClustering(atributosCartasRecebidas.getCartaMediaRoboConsultaCentroidePrimeiraCarta());
		atributos.setCartaBaixaRoboClustering(atributosCartasRecebidas.getCartaBaixaRoboConsultaCentroidePrimeiraCarta());
		
				
		return atributos;
	}
	
	
}
