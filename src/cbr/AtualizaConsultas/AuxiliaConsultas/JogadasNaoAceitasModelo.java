package cbr.AtualizaConsultas.AuxiliaConsultas;

public class JogadasNaoAceitasModelo {

	private String jogadaNaoAceita;
	private int quemNaoAceitou;
	
	public JogadasNaoAceitasModelo() {
		
	}
	
	public JogadasNaoAceitasModelo(String jogadaNaoAceita, int quemNaoAceitou) {
		super();
		this.jogadaNaoAceita = jogadaNaoAceita;
		this.quemNaoAceitou = quemNaoAceitou;
	}

	public String getJogadaNaoAceita() {
		return jogadaNaoAceita;
	}

	public void setJogadaNaoAceita(String jogadaNaoAceita) {
		this.jogadaNaoAceita = jogadaNaoAceita;
	}

	public int getQuemNaoAceitou() {
		return quemNaoAceitou;
	}

	public void setQuemNaoAceitou(int quemNaoAceitou) {
		this.quemNaoAceitou = quemNaoAceitou;
	}
	
}
