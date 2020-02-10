package cbr.AtualizaConsultas.AuxiliaConsultas;

public class CartasModelo implements Comparable<CartasModelo>{
	private String carta;
	public int valorImportancia;
	private String naipe;
	private int valorQueContaParaOenvido;
	private Integer id;
	private boolean virada;
	
	
	public CartasModelo(String carta, int valorImportancia, String naipe, int valorQueContaParaOenvido, int id) {
		super();
		this.carta = carta;
		this.valorImportancia = valorImportancia;
		this.naipe = naipe;
		this.valorQueContaParaOenvido = valorQueContaParaOenvido;
		this.id = id;
		this.virada = false;
	}

	
	public CartasModelo(boolean virada) {
		super();
		this.carta = "Virada";
		this.valorImportancia = 0;
		this.naipe = null;
		this.valorQueContaParaOenvido = 0;
		this.id = null;
		this.virada = false;
	}
	public CartasModelo() {
	}
	
	public String getCarta() {
		return carta;
	}

	public void setCarta(String carta) {
		this.carta = carta;
	}

	public int getValorImportancia() {
		return valorImportancia;
	}

	public void setValorImportancia(int valorImportancia) {
		this.valorImportancia = valorImportancia;
	}

	public String getNaipe() {
		return naipe;
	}

	public void setNaipe(String naipe) {
		this.naipe = naipe;
	}

	public int getValorQueContaParaOenvido() {
		return valorQueContaParaOenvido;
	}

	public void setValorQueContaParaOenvido(int valorQueContaParaOenvido) {
		this.valorQueContaParaOenvido = valorQueContaParaOenvido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(CartasModelo outraCarta) {
		if(this.id > outraCarta.id) return -1;
		if(this.id < outraCarta.id) return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "CartasModelo [carta=" + carta + ", valorImportancia=" + valorImportancia + ", naipe=" + naipe
				+ ", valorQueContaParaOenvido=" + valorQueContaParaOenvido + ", id=" + id + ", virada=" + virada + "]";
	}

	public String toStringFile() {
		return carta + "," + valorImportancia + "," + naipe+ "," + valorQueContaParaOenvido + ","+ id;
	}


	public boolean isVirada() {
		return virada;
	}


	public void setVirada(boolean virada) {
		this.virada = virada;
	}
	
	public String toString2() {
		String dado="";
		dado += carta;
		if(virada)
			dado+="(Virada)";
		return dado;
	}
	
	

}
