package Ajudas;

import cbr.cbrDescriptions.TrucoDescription;

public class ValidaGanhadorMao {

	public static int retornaGanhadorDaMao(TrucoDescription tc) {
		int ganhador = 0;
		if(tc.getGanhadorPrimeiraRodada() ==1 &&  tc.getGanhadorSegundaRodada() == 1) ganhador =1;
		else if(tc.getGanhadorPrimeiraRodada() ==0 &&  tc.getGanhadorSegundaRodada() == 1) ganhador =1;
		
		else	if(tc.getGanhadorPrimeiraRodada() ==1 &&  tc.getGanhadorSegundaRodada() ==0 ) ganhador =1;
		
		
		else if(tc.getGanhadorPrimeiraRodada()==1 && tc.getGanhadorSegundaRodada() ==2 && tc.getGanhadorTerceiraRodada() ==1) ganhador =1;
		else if(tc.getGanhadorPrimeiraRodada()==2 && tc.getGanhadorSegundaRodada() ==1 && tc.getGanhadorTerceiraRodada() ==1) ganhador =1;
		else if(tc.getGanhadorPrimeiraRodada()==0 && tc.getGanhadorSegundaRodada() ==0 && tc.getGanhadorTerceiraRodada() ==1) ganhador =1;
		else if(tc.getGanhadorPrimeiraRodada()==1 && tc.getGanhadorSegundaRodada() ==2 && tc.getGanhadorTerceiraRodada() ==0) ganhador =1;
		
		else	if(tc.getGanhadorPrimeiraRodada() ==2 &&  tc.getGanhadorSegundaRodada() == 2) ganhador =2;
		else if(tc.getGanhadorPrimeiraRodada() ==0 &&  tc.getGanhadorSegundaRodada() == 2) ganhador =2;
		
		else	if(tc.getGanhadorPrimeiraRodada() ==2 &&  tc.getGanhadorSegundaRodada() ==0 ) ganhador =2;
		
		
		else if(tc.getGanhadorPrimeiraRodada()==2 && tc.getGanhadorSegundaRodada() ==1 && tc.getGanhadorTerceiraRodada() ==2) ganhador =2;
		else if(tc.getGanhadorPrimeiraRodada()==1 && tc.getGanhadorSegundaRodada() ==2 && tc.getGanhadorTerceiraRodada() ==2) ganhador =2;
		else if(tc.getGanhadorPrimeiraRodada()==0 && tc.getGanhadorSegundaRodada() ==0 && tc.getGanhadorTerceiraRodada() ==2) ganhador =2;
		else if(tc.getGanhadorPrimeiraRodada()==2 && tc.getGanhadorSegundaRodada() ==1 && tc.getGanhadorTerceiraRodada() ==0) ganhador =2;
		else ganhador= tc.getQuemGanhouTruco();
		
		return ganhador;
	}
}
