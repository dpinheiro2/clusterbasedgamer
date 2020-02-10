package clusterModelo;

public class MaiorEmenorSaldoModelo {
	int maiorSaldo =0;
	int menorSaldo = 0;
	
	public int getMaiorSaldo() {
		return maiorSaldo;
	}
	public void setMaiorSaldo(int maiorSaldo) {
		this.maiorSaldo = maiorSaldo;
	}
	public int getMenorSaldo() {
		return menorSaldo;
	}
	public void setMenorSaldo(int menorSaldo) {
		this.menorSaldo = menorSaldo;
	}
	public MaiorEmenorSaldoModelo(int maiorSaldo, int menorSaldo) {
		super();
		this.maiorSaldo = maiorSaldo;
		this.menorSaldo = menorSaldo;
	}
	
	
	
}
