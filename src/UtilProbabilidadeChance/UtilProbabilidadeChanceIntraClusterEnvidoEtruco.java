package UtilProbabilidadeChance;

import java.util.Iterator;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;

public class UtilProbabilidadeChanceIntraClusterEnvidoEtruco {

	public boolean probabilidadeDeChamarEganharEmaiorDoQueChamarEperderEnvido(List<TrucoDescription> listaIntraClusterQuemEnvido) {
		Iterator<TrucoDescription> iteratorListaIntraClusterQuemEnvido = listaIntraClusterQuemEnvido.iterator();
		Double quantidadeChamouOuAceitouEganhou =0.0;
		Double quantidadeChamouAceitouEperdeu =0.0;
		Double quantidadeChamouOuAceitou =0.0;
		//////System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!analisa se chance de ganhar é maior do que perder envido !!!!!!!!!!!!!!!!");
		while(iteratorListaIntraClusterQuemEnvido.hasNext()) {
			TrucoDescription t = iteratorListaIntraClusterQuemEnvido.next();
			
			//////System.out.println("quemPediuEnvido: "+ t.getQuemPediuEnvido() +" quemGanhouEnvido: "+ t.getQuemGanhouEnvido());
			if((!t.getQuemPediuEnvido().equals(0) || !t.getQuemPediuRealEnvido().equals(0) || !t.getQuemPediuFaltaEnvido().equals(0))&& !t.getQuemNegouEnvido().equals(1)) {
				quantidadeChamouOuAceitou = quantidadeChamouOuAceitou  + 1.0;
				if(t.getQuemGanhouEnvido().equals(1)) quantidadeChamouOuAceitouEganhou  = quantidadeChamouOuAceitouEganhou + 1.0;
				else if(t.getQuemGanhouEnvido().equals(2)) quantidadeChamouAceitouEperdeu = quantidadeChamouAceitouEperdeu + 1.0;
				
			}
		}
		
		double probabilidadeGanhar;
		probabilidadeGanhar =(double) quantidadeChamouOuAceitouEganhou / quantidadeChamouOuAceitou;
		double probabilidadePerder;
		probabilidadePerder =(double) quantidadeChamouAceitouEperdeu /quantidadeChamouOuAceitou;
		
		
		return probabilidadeGanhar >= probabilidadePerder;
	}
	
	public boolean probabilidadeDeChamarEganharEmaiorDoQueChamarEperderTruco(List<TrucoDescription> listaIntraClusterQuemTruco) {
		Iterator<TrucoDescription> iteratorListaIntraClusterQuemTruco = listaIntraClusterQuemTruco.iterator();
		Double quantidadeChamouOuAceitouEganhou =0.0;
		Double quantidadeChamouAceitouEperdeu =0.0;
		Double quantidadeChamouOuAceitou =0.0;
		while(iteratorListaIntraClusterQuemTruco.hasNext()) {
			TrucoDescription t = iteratorListaIntraClusterQuemTruco.next();
			if((!t.getQuemTruco().equals(0)  || !t.getQuemRetruco().equals(0) || !t.getQuemValeQuatro().equals(0) )&& !t.getQuemNegouTruco().equals(1)) {
				quantidadeChamouOuAceitou = quantidadeChamouOuAceitou + 1.0;
				if(t.getQuemGanhouTruco().equals(1)) quantidadeChamouOuAceitouEganhou = quantidadeChamouOuAceitouEganhou  + 1.0;
				else if(t.getQuemGanhouTruco().equals(2)) quantidadeChamouAceitouEperdeu = quantidadeChamouAceitouEperdeu + 1.0 ;
				
			}
		}
		double probabilidadeGanhar;
		probabilidadeGanhar = (double) quantidadeChamouOuAceitouEganhou / quantidadeChamouOuAceitou;
		double probabilidadePerder;
		probabilidadePerder = (double) quantidadeChamouAceitouEperdeu /quantidadeChamouOuAceitou;
		
		return probabilidadeGanhar >= probabilidadePerder;
	}
	
	public int rodadaComMaiorProbabilidadeDeGanharTruco(List<TrucoDescription> listaIntraClusterQuandoTruco, String tipoAcao) {
		Iterator<TrucoDescription> iteratorListaIntraClusterQuemTruco = listaIntraClusterQuandoTruco.iterator();
		Double quantidadeChamouOuAceitouEganhou =0.0;
		
		Double quantidadeGanhouRodadaUm =0.0;
		Double quantidadeGanhouRodadaDois =0.0;
		Double quantidadeGanhouRodadaTres =0.0;
		
		while(iteratorListaIntraClusterQuemTruco.hasNext()) {
			TrucoDescription t = iteratorListaIntraClusterQuemTruco.next();
			int quemChamou =0; 
			int quemNegou =0;
			int quemGanhouTruco =0;
			
			int quandoFoiChamado =0;
			if(tipoAcao.equalsIgnoreCase("truco")) { 
				quandoFoiChamado = t.getQuandoTruco();
				quemChamou= t.getQuemTruco();
			}
			else if(tipoAcao.equalsIgnoreCase("retruco")) { 
				quandoFoiChamado = t.getQuandoRetruco();
				quemChamou = t.getQuemRetruco();
			}
			else if(tipoAcao.equalsIgnoreCase("valequatro")) { 
				quandoFoiChamado = t.getQuandoValeQuatro();
				quemChamou = t.getQuemValeQuatro();
			}
			quemNegou = t.getQuemNegouTruco();
			quemGanhouTruco = t.getQuemGanhouTruco();
			
			if(quemChamou != 0 && quemNegou != 1) {
					
				if(quemGanhouTruco == 1) {
					
					quantidadeChamouOuAceitouEganhou =  quantidadeChamouOuAceitouEganhou + 1.0;
					if(quandoFoiChamado == 1) quantidadeGanhouRodadaUm =  quantidadeGanhouRodadaUm + 1.0;
					else if(quandoFoiChamado == 2) quantidadeGanhouRodadaDois = quantidadeGanhouRodadaDois + 1.0;
					else if(quandoFoiChamado ==3) quantidadeGanhouRodadaTres = quantidadeGanhouRodadaTres + 1.0;
				}
				 
			}
		}
		
		double probabilidadeRodadaUm;
		probabilidadeRodadaUm =(double) quantidadeGanhouRodadaUm/quantidadeChamouOuAceitouEganhou;
		double probabilidadeRodadaDois;
		probabilidadeRodadaDois = (double) quantidadeGanhouRodadaDois /quantidadeChamouOuAceitouEganhou;
		double probabilidadeRodadaTres;
		probabilidadeRodadaTres = (double)quantidadeGanhouRodadaTres/quantidadeChamouOuAceitouEganhou;
		
		int rodadaMaisChamadaOuAceita =0;
		if(probabilidadeRodadaUm >  probabilidadeRodadaDois && probabilidadeRodadaUm > probabilidadeRodadaTres) rodadaMaisChamadaOuAceita =1;
		else if(probabilidadeRodadaDois > probabilidadeRodadaUm && probabilidadeRodadaDois > probabilidadeRodadaTres) rodadaMaisChamadaOuAceita =2;
		else if(probabilidadeRodadaTres > probabilidadeRodadaUm && probabilidadeRodadaTres > probabilidadeRodadaDois) rodadaMaisChamadaOuAceita =3;
		
		return rodadaMaisChamadaOuAceita;
	}
}
