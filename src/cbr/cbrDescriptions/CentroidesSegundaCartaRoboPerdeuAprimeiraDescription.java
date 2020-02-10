package cbr.cbrDescriptions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CentroidesSegundaCartaRoboPerdeuAprimeiraDescription implements CaseComponent {
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int grupo;
	Double segundaCartaHumanoClustering;
	Double segundaCartaRoboClustering;
	int numerok;

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public Double getSegundaCartaHumanoClustering() {
		return segundaCartaHumanoClustering;
	}

	public void setSegundaCartaHumanoClustering(Double segundaCartaHumanoClustering) {
		this.segundaCartaHumanoClustering = segundaCartaHumanoClustering;
	}

	public Double getSegundaCartaRoboClustering() {
		return segundaCartaRoboClustering;
	}

	public void setSegundaCartaRoboClustering(Double segundaCartaRoboClustering) {
		this.segundaCartaRoboClustering = segundaCartaRoboClustering;
	}

	public int getNumerok() {
		return numerok;
	}

	public void setNumerok(int numerok) {
		this.numerok = numerok;
	}



	public CentroidesSegundaCartaRoboPerdeuAprimeiraDescription(int id, int grupo, Double segundaCartaHumanoClustering,
			Double segundaCartaRoboClustering, int numerok) {
		super();
		this.id = id;
		this.grupo = grupo;
		this.segundaCartaHumanoClustering = segundaCartaHumanoClustering;
		this.segundaCartaRoboClustering = segundaCartaRoboClustering;
		this.numerok = numerok;
	}

	public CentroidesSegundaCartaRoboPerdeuAprimeiraDescription() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

}