package Ajudas;

public class saldoModeloPorAcao {
	int maiorSaldoEnvido = 0;
	String acaoComMaiorSaldo;
	int quantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo = 0;
	public int getMaiorSaldoEnvido() {
		return maiorSaldoEnvido;
	}
	public void setMaiorSaldoEnvido(int maiorSaldoEnvido) {
		this.maiorSaldoEnvido = maiorSaldoEnvido;
	}
	public String getAcaoComMaiorSaldo() {
		return acaoComMaiorSaldo;
	}
	public void setAcaoComMaiorSaldo(String acaoComMaiorSaldo) {
		this.acaoComMaiorSaldo = acaoComMaiorSaldo;
	}
	public int getQuantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo() {
		return quantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo;
	}
	public void setQuantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo(int quantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo) {
		this.quantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo = quantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo;
	}
	public saldoModeloPorAcao(int maiorSaldoEnvido, String acaoComMaiorSaldo,
			int quantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo) {
		super();
		this.maiorSaldoEnvido = maiorSaldoEnvido;
		this.acaoComMaiorSaldo = acaoComMaiorSaldo;
		this.quantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo = quantidadeDeCasosRecuperadosNaAcaoComMaiorSaldo;
	}
	
	
	
	
}
