package CentroidesModelo;

import java.util.Collection;

import jcolibri.cbrcore.CBRCase;

import jcolibri.cbrcore.Connector;

public class CaseBasesModelo {
	Collection<CBRCase> _caseBaseCentroidesGrupoIndexacao;
	
	Collection<CBRCase> _caseBaseCentroidesGrupoIndexacaoPontos;
	
	
	Collection<CBRCase> _caseBaseMaos;

	Collection<CBRCase> _caseBaseCentroidePrimeiraCartaRoboMao;
	
	// centroide primeira carta robo pe
	
	Collection<CBRCase> _caseBaseCentroidePrimeiraCartaRoboPe;
	// centroide segunda carta robo ganhouAprimeira
	
	Collection<CBRCase> _caseBaseCentroideSegundaCartaRoboGanhouAprimeira;
	// centroide segunda carta robo perdeuAprimeira
	
	Collection<CBRCase> _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira;
	// centroide terceira carta robo ganhouAprimeira
	
	Collection<CBRCase> _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda;
	// centroide segunda carta robo perdeu a segunda
	
	Collection<CBRCase> _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda;

	// centroide quem truco
	
	Collection<CBRCase> _caseBaseCentroideQuemTrucoPrimeiraMao;
	Collection<CBRCase> _caseBaseCentroideQuemTrucoPrimeiraPe;
	
	Collection<CBRCase> _caseBaseCentroideQuemTrucoSegundaGanhouAnterior;
	Collection<CBRCase> _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior;

	Collection<CBRCase> _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior;
	Collection<CBRCase> _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior;
	

	
	Collection<CBRCase> _caseBaseCentroideQuemGanhouEnvidoAgenteMao;
	// centroide quem envido jogador pe
	
	Collection<CBRCase> _caseBaseCentroideQuemGanhouEnvidoAgentePe;

	public Collection<CBRCase> get_caseBaseCentroidesGrupoIndexacao() {
		return _caseBaseCentroidesGrupoIndexacao;
	}

	public void set_caseBaseCentroidesGrupoIndexacao(Collection<CBRCase> _caseBaseCentroidesGrupoIndexacao) {
		this._caseBaseCentroidesGrupoIndexacao = _caseBaseCentroidesGrupoIndexacao;
	}

	public Collection<CBRCase> get_caseBaseCentroidesGrupoIndexacaoPontos() {
		return _caseBaseCentroidesGrupoIndexacaoPontos;
	}

	public void set_caseBaseCentroidesGrupoIndexacaoPontos(Collection<CBRCase> _caseBaseCentroidesGrupoIndexacaoPontos) {
		this._caseBaseCentroidesGrupoIndexacaoPontos = _caseBaseCentroidesGrupoIndexacaoPontos;
	}

	public Collection<CBRCase> get_caseBaseMaos() {
		return _caseBaseMaos;
	}

	public void set_caseBaseMaos(Collection<CBRCase> _caseBaseMaos) {
		this._caseBaseMaos = _caseBaseMaos;
	}

	public Collection<CBRCase> get_caseBaseCentroidePrimeiraCartaRoboMao() {
		return _caseBaseCentroidePrimeiraCartaRoboMao;
	}

	public void set_caseBaseCentroidePrimeiraCartaRoboMao(Collection<CBRCase> _caseBaseCentroidePrimeiraCartaRoboMao) {
		this._caseBaseCentroidePrimeiraCartaRoboMao = _caseBaseCentroidePrimeiraCartaRoboMao;
	}

	public Collection<CBRCase> get_caseBaseCentroidePrimeiraCartaRoboPe() {
		return _caseBaseCentroidePrimeiraCartaRoboPe;
	}

	public void set_caseBaseCentroidePrimeiraCartaRoboPe(Collection<CBRCase> _caseBaseCentroidePrimeiraCartaRoboPe) {
		this._caseBaseCentroidePrimeiraCartaRoboPe = _caseBaseCentroidePrimeiraCartaRoboPe;
	}

	public Collection<CBRCase> get_caseBaseCentroideSegundaCartaRoboGanhouAprimeira() {
		return _caseBaseCentroideSegundaCartaRoboGanhouAprimeira;
	}

	public void set_caseBaseCentroideSegundaCartaRoboGanhouAprimeira(
			Collection<CBRCase> _caseBaseCentroideSegundaCartaRoboGanhouAprimeira) {
		this._caseBaseCentroideSegundaCartaRoboGanhouAprimeira = _caseBaseCentroideSegundaCartaRoboGanhouAprimeira;
	}

	public Collection<CBRCase> get_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira() {
		return _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira;
	}

	public void set_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira(
			Collection<CBRCase> _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira) {
		this._caseBaseCentroideSegundaCartaRoboPerdeuAprimeira = _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira;
	}

	public Collection<CBRCase> get_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda() {
		return _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda;
	}

	public void set_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda(
			Collection<CBRCase> _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda) {
		this._caseBaseCentroideTerceiraCartaRoboGanhouAsegunda = _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda;
	}

	public Collection<CBRCase> get_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda() {
		return _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda;
	}

	public void set_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda(
			Collection<CBRCase> _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda) {
		this._caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda = _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda;
	}

	public Collection<CBRCase> get_caseBaseCentroideQuemTrucoPrimeiraMao() {
		return _caseBaseCentroideQuemTrucoPrimeiraMao;
	}

	public void set_caseBaseCentroideQuemTrucoPrimeiraMao(Collection<CBRCase> _caseBaseCentroideQuemTrucoPrimeiraMao) {
		this._caseBaseCentroideQuemTrucoPrimeiraMao = _caseBaseCentroideQuemTrucoPrimeiraMao;
	}

	public Collection<CBRCase> get_caseBaseCentroideQuemTrucoPrimeiraPe() {
		return _caseBaseCentroideQuemTrucoPrimeiraPe;
	}

	public void set_caseBaseCentroideQuemTrucoPrimeiraPe(Collection<CBRCase> _caseBaseCentroideQuemTrucoPrimeiraPe) {
		this._caseBaseCentroideQuemTrucoPrimeiraPe = _caseBaseCentroideQuemTrucoPrimeiraPe;
	}

	public Collection<CBRCase> get_caseBaseCentroideQuemTrucoSegundaGanhouAnterior() {
		return _caseBaseCentroideQuemTrucoSegundaGanhouAnterior;
	}

	public void set_caseBaseCentroideQuemTrucoSegundaGanhouAnterior(
			Collection<CBRCase> _caseBaseCentroideQuemTrucoSegundaGanhouAnterior) {
		this._caseBaseCentroideQuemTrucoSegundaGanhouAnterior = _caseBaseCentroideQuemTrucoSegundaGanhouAnterior;
	}

	public Collection<CBRCase> get_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior() {
		return _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior;
	}

	public void set_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior(
			Collection<CBRCase> _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior) {
		this._caseBaseCentroideQuemTrucoSegundaPerdeuAnterior = _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior;
	}

	public Collection<CBRCase> get_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior() {
		return _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior;
	}

	public void set_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior(
			Collection<CBRCase> _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior) {
		this._caseBaseCentroideQuemTrucoTerceiraGanhouAnterior = _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior;
	}

	public Collection<CBRCase> get_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior() {
		return _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior;
	}

	public void set_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior(
			Collection<CBRCase> _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior) {
		this._caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior = _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior;
	}

	public Collection<CBRCase> get_caseBaseCentroideQuemGanhouEnvidoAgenteMao() {
		return _caseBaseCentroideQuemGanhouEnvidoAgenteMao;
	}

	public void set_caseBaseCentroideQuemGanhouEnvidoAgenteMao(
			Collection<CBRCase> _caseBaseCentroideQuemGanhouEnvidoAgenteMao) {
		this._caseBaseCentroideQuemGanhouEnvidoAgenteMao = _caseBaseCentroideQuemGanhouEnvidoAgenteMao;
	}

	public Collection<CBRCase> get_caseBaseCentroideQuemGanhouEnvidoAgentePe() {
		return _caseBaseCentroideQuemGanhouEnvidoAgentePe;
	}

	public void set_caseBaseCentroideQuemGanhouEnvidoAgentePe(
			Collection<CBRCase> _caseBaseCentroideQuemGanhouEnvidoAgentePe) {
		this._caseBaseCentroideQuemGanhouEnvidoAgentePe = _caseBaseCentroideQuemGanhouEnvidoAgentePe;
	}
	
	
	
	
	
	
	
	
}
