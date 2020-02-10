package cbr.cbrDescriptions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CentroidesQuemTrucoDescription implements CaseComponent {
    int id;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	int grupo;
    Double quemTruco;
    Double quemRetruco;
    Double quemValeQuatro;
   
    Double cartaAltaRoboClustering;
    Double cartaMediaRoboClustering;
    Double cartaBaixaRoboClustering;
    
    int numerok;
    

	public CentroidesQuemTrucoDescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public Double getQuemTruco() {
		return quemTruco;
	}
	public void setQuemTruco(Double quemTruco) {
		this.quemTruco = quemTruco;
	}
	public Double getQuemRetruco() {
		return quemRetruco;
	}
	public void setQuemRetruco(Double quemRetruco) {
		this.quemRetruco = quemRetruco;
	}
	public Double getQuemValeQuatro() {
		return quemValeQuatro;
	}
	public void setQuemValeQuatro(Double quemValeQuatro) {
		this.quemValeQuatro = quemValeQuatro;
	}
	
	public int getNumerok() {
		return numerok;
	}
	public void setNumerok(int numerok) {
		this.numerok = numerok;
	}
	public Double getCartaAltaRoboClustering() {
		return cartaAltaRoboClustering;
	}
	public void setCartaAltaRoboClustering(Double cartaAltaRoboClustering) {
		this.cartaAltaRoboClustering = cartaAltaRoboClustering;
	}
	public Double getCartaMediaRoboClustering() {
		return cartaMediaRoboClustering;
	}
	public void setCartaMediaRoboClustering(Double cartaMediaRoboClustering) {
		this.cartaMediaRoboClustering = cartaMediaRoboClustering;
	}
	public Double getCartaBaixaRoboClustering() {
		return cartaBaixaRoboClustering;
	}
	public void setCartaBaixaRoboClustering(Double cartaBaixaRoboClustering) {
		this.cartaBaixaRoboClustering = cartaBaixaRoboClustering;
	}
	
	
	public CentroidesQuemTrucoDescription(int id, int grupo, Double quemTruco, Double quemRetruco,
			Double quemValeQuatro, Double cartaAltaRoboClustering, Double cartaMediaRoboClustering,
			Double cartaBaixaRoboClustering, int numerok) {
		super();
		this.id = id;
		this.grupo = grupo;
		this.quemTruco = quemTruco;
		this.quemRetruco = quemRetruco;
		this.quemValeQuatro = quemValeQuatro;
		this.cartaAltaRoboClustering = cartaAltaRoboClustering;
		this.cartaMediaRoboClustering = cartaMediaRoboClustering;
		this.cartaBaixaRoboClustering = cartaBaixaRoboClustering;
		this.numerok = numerok;
	}
	@Override
	public String toString() {
		return "CentroidesQuemTrucoDescription [id=" + id + ", grupo=" + grupo + ", quemTruco=" + quemTruco
				+ ", quemRetruco=" + quemRetruco + ", quemValeQuatro=" + quemValeQuatro + ", cartaAltaRoboClustering="
				+ cartaAltaRoboClustering + ", cartaMediaRoboClustering=" + cartaMediaRoboClustering
				+ ", cartaBaixaRoboClustering=" + cartaBaixaRoboClustering + ", numerok=" + numerok + "]";
	}

	
	
	
	
	
	
	
     
}
