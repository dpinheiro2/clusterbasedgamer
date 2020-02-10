package cbr.cbrDescriptions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CentroidesGrupoIndexacaoDescription implements CaseComponent {

int id;

double centroidecartaaltarobomao;
double centroidecartamediarobomao;
double centroidecartabaixarobomao;


int grupo;
int numerok;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public double getCentroidecartaaltarobomao() {
	return centroidecartaaltarobomao;
}
public void setCentroidecartaaltarobomao(double centroidecartaaltarobomao) {
	this.centroidecartaaltarobomao = centroidecartaaltarobomao;
}
public double getCentroidecartamediarobomao() {
	return centroidecartamediarobomao;
}
public void setCentroidecartamediarobomao(double centroidecartamediarobomao) {
	this.centroidecartamediarobomao = centroidecartamediarobomao;
}
public double getCentroidecartabaixarobomao() {
	return centroidecartabaixarobomao;
}
public void setCentroidecartabaixarobomao(double centroidecartabaixarobomao) {
	this.centroidecartabaixarobomao = centroidecartabaixarobomao;
}

public int getGrupo() {
	return grupo;
}
public void setGrupo(int grupo) {
	this.grupo = grupo;
}
public int getNumerok() {
	return numerok;
}
public void setNumerok(int numerok) {
	this.numerok = numerok;
}
@Override
public Attribute getIdAttribute() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public String toString() {
	return "CentroidesGrupoIndexacaoDescription [id=" + id + ", centroidecartaaltarobomao=" + centroidecartaaltarobomao
			+ ", centroidecartamediarobomao=" + centroidecartamediarobomao + ", centroidecartabaixarobomao="
			+ centroidecartabaixarobomao + ", grupo=" + grupo + ", numerok=" + numerok + "]";
}



	
}
