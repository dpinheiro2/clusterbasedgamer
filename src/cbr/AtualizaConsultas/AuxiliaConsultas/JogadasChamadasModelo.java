package cbr.AtualizaConsultas.AuxiliaConsultas;
//essa classe é utilizada para armazenar quem foi o jogador que chamou e o que chamou
public class JogadasChamadasModelo {

	private String jogadaChamada;
	private int quemChamou;
	private int emQualMao;
	
	public String getJogadaChamada() {
		return jogadaChamada;
	}

	public void setJogadaChamada(String jogadaChamada) {
		this.jogadaChamada = jogadaChamada;
	}

	public int getQuemChamou() {
		return quemChamou;
	}

	public void setQuemChamou(int quemChamou) {
		this.quemChamou = quemChamou;
	}

	public int getEmQualRodada() {
		return emQualMao;
	}

	public void setEmQualRodada(int emQualRodada) {
		this.emQualMao = emQualRodada;
	}
	
}
