package ajudaCluster;

import java.util.Collection;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;
import clusterModelo.MaiorEmenorSaldoModelo;
import jcolibri.method.retrieve.RetrievalResult;

public class RetornaMaiorEmenorSaldo {

	public MaiorEmenorSaldoModelo retornaMaiorEmenorSaldoTruco(Collection<RetrievalResult> listaCasosDoGrupo) {
		Integer maiorSaldo =null;
		Integer menorSaldo = null;
		  for(RetrievalResult r : listaCasosDoGrupo) {
			  TrucoDescription t = (TrucoDescription) r.get_case().getDescription();
			 if(maiorSaldo == null && menorSaldo == null) {
				maiorSaldo =  UtilSaldos.retornaSaldoTruco(t);
				menorSaldo =   UtilSaldos.retornaSaldoTruco(t);
			 } 
			if(maiorSaldo != null && menorSaldo != null) {
			 if(UtilSaldos.retornaSaldoTruco(t) > maiorSaldo) maiorSaldo = UtilSaldos.retornaSaldoTruco(t);
			 
			 if(UtilSaldos.retornaSaldoTruco(t) < maiorSaldo) menorSaldo = UtilSaldos.retornaSaldoTruco(t);
			}
			 
		 }
		if(maiorSaldo == null && menorSaldo == null) {
			
			maiorSaldo =0;
			menorSaldo =0;
		}
		
		return new MaiorEmenorSaldoModelo(maiorSaldo, menorSaldo);
	}
	
	public MaiorEmenorSaldoModelo retornaMaiorEmenorSaldoEnvido(Collection<RetrievalResult> listaCasosDoGrupo) {
		Integer maiorSaldo =null;
		Integer menorSaldo = null;
		  for(RetrievalResult r : listaCasosDoGrupo) {
			  TrucoDescription t = (TrucoDescription) r.get_case().getDescription();
			 if(maiorSaldo == null && menorSaldo == null) {
				maiorSaldo =  UtilSaldos.retornaSaldoEnvido(t);
				menorSaldo =   UtilSaldos.retornaSaldoEnvido(t);
			 } 
			
			 if(UtilSaldos.retornaSaldoEnvido(t) > maiorSaldo) maiorSaldo = UtilSaldos.retornaSaldoEnvido(t);
			 
			 if(UtilSaldos.retornaSaldoEnvido(t) < maiorSaldo) menorSaldo = UtilSaldos.retornaSaldoEnvido(t);
			 
			 
		 }
		
		
		return new MaiorEmenorSaldoModelo(maiorSaldo, menorSaldo);
	}
	
	public MaiorEmenorSaldoModelo retornaMaiorEmenorSaldoFlor(Collection<RetrievalResult> listaCasosDoGrupo) {
		Integer maiorSaldo =null;
		Integer menorSaldo = null;
		  for(RetrievalResult r : listaCasosDoGrupo) {
			TrucoDescription t = (TrucoDescription) r.get_case().getDescription();
			 if(maiorSaldo == null && menorSaldo == null) {
				maiorSaldo =  t.getSaldoFlor();
				menorSaldo =   t.getSaldoFlor();
			 } 
			
			 if(t.getSaldoFlor() > maiorSaldo) maiorSaldo = t.getSaldoFlor();
			 
			 if(t.getSaldoFlor() < maiorSaldo) menorSaldo = t.getSaldoFlor();
			 
			 
		 }
		
		
		return new MaiorEmenorSaldoModelo(maiorSaldo, menorSaldo);
	}
	
}
