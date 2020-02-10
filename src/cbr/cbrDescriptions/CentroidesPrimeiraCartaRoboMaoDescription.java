package cbr.cbrDescriptions;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CentroidesPrimeiraCartaRoboMaoDescription implements CaseComponent {

	int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	int grupo;
	
	double cartaAltaRoboClustering;
	double cartaMediaRoboClustering;
	double cartaBaixaRoboClustering;
	double primeiraCartaRoboClustering;
	double numerok;
	
	
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public double getCartaAltaRoboClustering() {
		return cartaAltaRoboClustering;
	}
	public void setCartaAltaRoboClustering(double cartaAltaRoboClustering) {
		this.cartaAltaRoboClustering = cartaAltaRoboClustering;
	}
	public double getCartaMediaRoboClustering() {
		return cartaMediaRoboClustering;
	}
	public void setCartaMediaRoboClustering(double cartaMediaRoboClustering) {
		this.cartaMediaRoboClustering = cartaMediaRoboClustering;
	}
	public double getCartaBaixaRoboClustering() {
		return cartaBaixaRoboClustering;
	}
	public void setCartaBaixaRoboClustering(double cartaBaixaRoboClustering) {
		this.cartaBaixaRoboClustering = cartaBaixaRoboClustering;
	}
	public double getPrimeiraCartaRoboClustering() {
		return primeiraCartaRoboClustering;
	}
	public void setPrimeiraCartaRoboClustering(double primeiraCartaRoboClustering) {
		this.primeiraCartaRoboClustering = primeiraCartaRoboClustering;
	}
	public double getNumerok() {
		return numerok;
	}
	public void setNumerok(double numerok) {
		this.numerok = numerok;
	}
	
	
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CentroidesPrimeiraCartaRoboMaoDescription(int id, int grupo, double cartaAltaRoboClustering,
			double cartaMediaRoboClustering, double cartaBaixaRoboClustering, double primeiraCartaRoboClustering,
			double numerok) {
		super();
		this.id = id;
		this.grupo = grupo;
		this.cartaAltaRoboClustering = cartaAltaRoboClustering;
		this.cartaMediaRoboClustering = cartaMediaRoboClustering;
		this.cartaBaixaRoboClustering = cartaBaixaRoboClustering;
		this.primeiraCartaRoboClustering = primeiraCartaRoboClustering;
		this.numerok = numerok;
	}
	public CentroidesPrimeiraCartaRoboMaoDescription() {
		
	}
	
	


}
