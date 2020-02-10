package ajudaCluster;

import cbr.cbrDescriptions.TrucoDescription;

public class UtilSaldos {
public static int retornaSaldoEnvido(TrucoDescription newCase) {
	 int saldoEnvido=0;
	 if(newCase.getQuemGanhouEnvido().equals(1) ) saldoEnvido = newCase.getTentosEnvido();
	 else if(newCase.getQuemGanhouEnvido().equals(2) ) saldoEnvido  = -newCase.getTentosEnvido();
	 
	 
	 return saldoEnvido;
}

public static int retornaSaldoTruco(TrucoDescription newCase) {
	int saldoTruco = 0;
	if(newCase.getQuemGanhouTruco().equals(1)  ) saldoTruco = newCase.getTentosTruco();
	
	else if(newCase.getQuemGanhouTruco().equals(2)  ) saldoTruco = -newCase.getTentosTruco();
	
	
	return saldoTruco;
	
}

}
