package cbr.Adaptacoes;

import java.util.*;
import java.util.concurrent.TimeUnit;

import CbrQuerys.CBR;
import CbrQuerys.CBRCentroides;
import CentroidesModelo.AtributosConsultaCentroideJogadaModelo;

import CentroidesModelo.AtributosConsultaCentroideQuemTrucoModelo;
import CentroidesModelo.CaseBasesModelo;
import CentroidesModelo.ResultadoConsultaCentroidePrimeiraCartaRoboMaoModelo;
import CentroidesModelo.ResultadoConsultaCentroidePrimeiraCartaRoboPe;

import CentroidesModelo.ResultadoConsultaCentroideQuemTruco;
import CentroidesModelo.ResultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira;
import CentroidesModelo.ResultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira;
import CentroidesModelo.ResultadoConsultaTerceiraCartaRoboGanhouAsegunda;
import CentroidesModelo.ResultadoTerceiraCartaRoboPerdeuAsegunda;
import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import DecisionsIntraCluster.CartasJogadasIntraCluster;
import DecisionsIntraClusterChanceSaldo.AcaoFeitasIntraClusterEnvidoChanceSaldo;
import DecisionsIntraClusterChanceSaldo.AcaoFeitasIntraClusterTrucoChanceSaldo;
import DecisionsIntraClusterChanceSaldo.CartasJogadasIntraClusterChanceSaldo;
import DecisionsIntraClusterMaioria.AcaoFeitasIntraClusterEnvidoMaioria;
import DecisionsIntraClusterMaioria.AcaoFeitasIntraClusterTrucoMaioria;
import DecisionsIntraClusterMaioria.CartasJogadasIntraClusterMaioria;
import DecisionsIntraClusterProbabilidadeChance.AcaoFeitasIntraClusterEnvidoProbabilidadeChance;
import DecisionsIntraClusterProbabilidadeChance.AcaoFeitasIntraClusterTrucoProbabilidadeChance;
import DecisionsIntraClusterProbabilidadeChance.CartasJogadasIntraClusterProbabilidadeChance;
import DecisionsIntraClusterProbabilidadeSorteio.AcaoFeitasIntraClusterEnvidoProbabilidadeSorteio;
import DecisionsIntraClusterProbabilidadeSorteio.AcaoFeitasIntraClusterTrucoProbabilidadeSorteio;
import DecisionsIntraClusterProbabilidadeSorteio.CartasJogadasIntraClusterProbabilidadeSorteio;
import UtilProbabilidadeChance.ProbabilidadeChance;
import ajudaCluster.UtilClusterCentroides;
import ajudaCluster.converteTrucoDescriptionParaCentroidesGrupoIndexacaoDescription;
import cbr.cbrDescriptions.*;

import cbr.learning.Persistir;

import cbr.learning.ValidaCriterioReusoAtivoOuAleatorio;
import cbr.learning.ValidaPersistir;
import cbr.learning.ativo.PersistirAtivo;
import cbr.learning.ativo.ValidaCriterioReusoAtivo;
import cbr.learning.ativo.ValidaDevePersistirAtivo;
import cbr.learning.imitacao.PersistirImitacao;
import cbr.learning.imitacao.ValidaCriterioReusoImitacao;
import cbr.learning.imitacao.ValidaDevePersistirImitacao;
import chamaScriptsR.ChamaScriptsRnegocio;
import clusterModelo.ControlaClustersAnterioresCartas;
import decisions.DecisionExtaClusterTruco;
import decisions.DecisionExtraClusterCarta;
import decisions.DecisionExtraClusterEnvido;
import decisionsExtraClusterChanceSaldo.DecisionExtaClusterChanceDeSucessoEsaldoCarta;
import decisionsExtraClusterChanceSaldo.DecisionExtraClusterChanceDeSucessoEsaldoEnvido;
import decisionsExtraClusterChanceSaldo.DecisionExtraClusterChanceDeSucessoEsaldoTruco;
import decisionsExtraClusterMaioria.DecisionExtraClusterMaioriaCarta;
import decisionsExtraClusterMaioria.DecisionExtraClusterMaioriaEnvido;
import decisionsExtraClusterMaioria.DecisionExtraClusterMaioriaTruco;
import decisionsExtraClusterProbabilityChance.DecisionExtaClusterProbabilidadeSucessoCarta;
import decisionsExtraClusterProbabilityChance.DecisionExtraClusterProbabilidadeSucessoEnvido;
import decisionsExtraClusterProbabilityChance.DecisionExtraClusterProbabilidadeTruco;
import decisionsExtraClusterProbabilitySorteio.DecisionExtraClusterProbabilitySorteioCarta;
import decisionsExtraClusterProbabilitySorteio.DecisionExtraClusterProbabilitySorteioEnvido;
import decisionsExtraClusterProbabilitySorteio.DecisionExtraClusterProbabilitySorteioTruco;
import hash.HashCarta;
import hash.HashEnvido;
import hash.HashTruco;
import hashCluster.HashClusterCarta;
import hashCluster.HashClusterEnvido;
import hashCluster.HashClusterTruco;
import hashSolucao.HashSolucaoCarta;
import hashSolucao.HashSolucaoEnvido;
import hashSolucao.HashSolucaoTruco;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBaseGustavo;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import selfModelingModelo.ContadorJogadasNegadas;
import selfModelingModelo.ContadorPontosNegados;
import utils.Deck;
import utils.Face;

public class CbrModular implements CBR {

    static int casosAprendidos = 0;
    public static int casosEnviadosPersistencia = 0;
    // armazena informações do jogo para evitar que o oponente passe dos limites em
    // blefes suicidas, por isso vai ter uma self modelagem simples
    /*
     * por exemplo caso o agente esteja negando muito falta envido ele detecta e faz
     * a consulta como envido
     */
    ContadorPontosNegados contadorRealEfaltaEnvidoNegados = new ContadorPontosNegados();
    /*
     * por exemplo caso o agente esteja negando muito falta envido ele detecta e faz
     * a consulta como envido
     */
    ContadorJogadasNegadas contadorRetrucoEvaleQuatroNegados = new ContadorJogadasNegadas();

    String dataBaseConectado = "";

    boolean ajusteAutomaticoDoK = false;

    //deve revisar ativo?
    boolean revisarAtivo = false;

    public boolean isRevisarAtivo() {
        return revisarAtivo;
    }

    public void setRevisarAtivo(boolean revisarAtivo) {
        this.revisarAtivo = revisarAtivo;
    }

    public boolean isAjusteAutomaticoDoK() {
        return ajusteAutomaticoDoK;
    }

    public void setAjusteAutomaticoDoK(boolean ajusteAutomaticoDoK) {
        this.ajusteAutomaticoDoK = ajusteAutomaticoDoK;
    }

    public Connector get_connectorMaosBaseline() {
        return _connectorMaosBaseline;
    }

    public void set_connectorMaosBaseline(Connector _connectorMaosBaseline) {
        this._connectorMaosBaseline = _connectorMaosBaseline;
    }

    public Connector get_connectorMaosImitacao() {
        return _connectorMaosImitacao;
    }

    public void set_connectorMaosImitacao(Connector _connectorMaosImitacao) {
        this._connectorMaosImitacao = _connectorMaosImitacao;
    }

    public Connector get_connectorMaosAtivo() {
        return _connectorMaosAtivo;
    }

    public void set_connectorMaosAtivo(Connector _connectorMaosAtivo) {
        this._connectorMaosAtivo = _connectorMaosAtivo;
    }

    public Connector get_connectorCentroidePrimeiraCartaRoboMaoBaseline() {
        return _connectorCentroidePrimeiraCartaRoboMaoBaseline;
    }

    public void set_connectorCentroidePrimeiraCartaRoboMaoBaseline(
            Connector _connectorCentroidePrimeiraCartaRoboMaoBaseline) {
        this._connectorCentroidePrimeiraCartaRoboMaoBaseline = _connectorCentroidePrimeiraCartaRoboMaoBaseline;
    }

    public Connector get_connectorCentroidePrimeiraCartaRoboMaoImitacao() {
        return _connectorCentroidePrimeiraCartaRoboMaoImitacao;
    }

    public void set_connectorCentroidePrimeiraCartaRoboMaoImitacao(
            Connector _connectorCentroidePrimeiraCartaRoboMaoImitacao) {
        this._connectorCentroidePrimeiraCartaRoboMaoImitacao = _connectorCentroidePrimeiraCartaRoboMaoImitacao;
    }

    public Connector get_connectorCentroidePrimeiraCartaRoboMaoAtivo() {
        return _connectorCentroidePrimeiraCartaRoboMaoAtivo;
    }

    public void set_connectorCentroidePrimeiraCartaRoboMaoAtivo(
            Connector _connectorCentroidePrimeiraCartaRoboMaoAtivo) {
        this._connectorCentroidePrimeiraCartaRoboMaoAtivo = _connectorCentroidePrimeiraCartaRoboMaoAtivo;
    }

    public Connector get_connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline() {
        return _connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline;
    }

    public void set_connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline(
            Connector _connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline) {
        this._connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline = _connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline;
    }

    public Connector get_connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao() {
        return _connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao;
    }

    public void set_connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao(
            Connector _connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao) {
        this._connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao = _connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao;
    }

    public Connector get_connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo() {
        return _connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo;
    }

    public void set_connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo(
            Connector _connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo) {
        this._connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo = _connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo;
    }

    public Connector get_connectorCentroideQuemGanhouEnvidoAgentePeBaseline() {
        return _connectorCentroideQuemGanhouEnvidoAgentePeBaseline;
    }

    public void set_connectorCentroideQuemGanhouEnvidoAgentePeBaseline(
            Connector _connectorCentroideQuemGanhouEnvidoAgentePeBaseline) {
        this._connectorCentroideQuemGanhouEnvidoAgentePeBaseline = _connectorCentroideQuemGanhouEnvidoAgentePeBaseline;
    }

    public Connector get_connectorCentroideQuemGanhouEnvidoAgentePeImitacao() {
        return _connectorCentroideQuemGanhouEnvidoAgentePeImitacao;
    }

    public void set_connectorCentroideQuemGanhouEnvidoAgentePeImitacao(
            Connector _connectorCentroideQuemGanhouEnvidoAgentePeImitacao) {
        this._connectorCentroideQuemGanhouEnvidoAgentePeImitacao = _connectorCentroideQuemGanhouEnvidoAgentePeImitacao;
    }

    public Connector get_connectorCentroideQuemGanhouEnvidoAgentePeAtivo() {
        return _connectorCentroideQuemGanhouEnvidoAgentePeAtivo;
    }

    public void set_connectorCentroideQuemGanhouEnvidoAgentePeAtivo(
            Connector _connectorCentroideQuemGanhouEnvidoAgentePeAtivo) {
        this._connectorCentroideQuemGanhouEnvidoAgentePeAtivo = _connectorCentroideQuemGanhouEnvidoAgentePeAtivo;
    }

    public Connector get_connectorCentroidesGrupoIndexacaoBaseline() {
        return _connectorCentroidesGrupoIndexacaoBaseline;
    }

    public void set_connectorCentroidesGrupoIndexacaoBaseline(Connector _connectorCentroidesGrupoIndexacaoBaseline) {
        this._connectorCentroidesGrupoIndexacaoBaseline = _connectorCentroidesGrupoIndexacaoBaseline;
    }

    public Connector get_connectorCentroidesGrupoIndexacaoImitacao() {
        return _connectorCentroidesGrupoIndexacaoImitacao;
    }

    public void set_connectorCentroidesGrupoIndexacaoImitacao(Connector _connectorCentroidesGrupoIndexacaoImitacao) {
        this._connectorCentroidesGrupoIndexacaoImitacao = _connectorCentroidesGrupoIndexacaoImitacao;
    }

    public Connector get_connectorCentroidesGrupoIndexacaoAtivo() {
        return _connectorCentroidesGrupoIndexacaoAtivo;
    }

    public void set_connectorCentroidesGrupoIndexacaoAtivo(Connector _connectorCentroidesGrupoIndexacaoAtivo) {
        this._connectorCentroidesGrupoIndexacaoAtivo = _connectorCentroidesGrupoIndexacaoAtivo;
    }

    public Connector get_connectorCentroidesGrupoIndexacaoPontosBaseline() {
        return _connectorCentroidesGrupoIndexacaoPontosBaseline;
    }

    public void set_connectorCentroidesGrupoIndexacaoPontosBaseline(
            Connector _connectorCentroidesGrupoIndexacaoPontosBaseline) {
        this._connectorCentroidesGrupoIndexacaoPontosBaseline = _connectorCentroidesGrupoIndexacaoPontosBaseline;
    }

    public Connector get_connectorCentroidesGrupoIndexacaoPontosImitacao() {
        return _connectorCentroidesGrupoIndexacaoPontosImitacao;
    }

    public void set_connectorCentroidesGrupoIndexacaoPontosImitacao(
            Connector _connectorCentroidesGrupoIndexacaoPontosImitacao) {
        this._connectorCentroidesGrupoIndexacaoPontosImitacao = _connectorCentroidesGrupoIndexacaoPontosImitacao;
    }

    public Connector get_connectorCentroidesGrupoIndexacaoPontosAtivo() {
        return _connectorCentroidesGrupoIndexacaoPontosAtivo;
    }

    public void set_connectorCentroidesGrupoIndexacaoPontosAtivo(
            Connector _connectorCentroidesGrupoIndexacaoPontosAtivo) {
        this._connectorCentroidesGrupoIndexacaoPontosAtivo = _connectorCentroidesGrupoIndexacaoPontosAtivo;
    }

    public CBRCaseBaseGustavo get_caseBaseMaos() {
        return _caseBaseMaos;
    }

    public void set_caseBaseMaos(CBRCaseBaseGustavo _caseBaseMaos) {
        this._caseBaseMaos = _caseBaseMaos;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroidePrimeiraCartaRoboMao() {
        return _caseBaseCentroidePrimeiraCartaRoboMao;
    }

    public void set_caseBaseCentroidePrimeiraCartaRoboMao(CBRCaseBaseGustavo _caseBaseCentroidePrimeiraCartaRoboMao) {
        this._caseBaseCentroidePrimeiraCartaRoboMao = _caseBaseCentroidePrimeiraCartaRoboMao;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroidePrimeiraCartaRoboPe() {
        return _caseBaseCentroidePrimeiraCartaRoboPe;
    }

    public void set_caseBaseCentroidePrimeiraCartaRoboPe(CBRCaseBaseGustavo _caseBaseCentroidePrimeiraCartaRoboPe) {
        this._caseBaseCentroidePrimeiraCartaRoboPe = _caseBaseCentroidePrimeiraCartaRoboPe;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideSegundaCartaRoboGanhouAprimeira() {
        return _caseBaseCentroideSegundaCartaRoboGanhouAprimeira;
    }

    public void set_caseBaseCentroideSegundaCartaRoboGanhouAprimeira(
            CBRCaseBaseGustavo _caseBaseCentroideSegundaCartaRoboGanhouAprimeira) {
        this._caseBaseCentroideSegundaCartaRoboGanhouAprimeira = _caseBaseCentroideSegundaCartaRoboGanhouAprimeira;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira() {
        return _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira;
    }

    public void set_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira(
            CBRCaseBaseGustavo _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira) {
        this._caseBaseCentroideSegundaCartaRoboPerdeuAprimeira = _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda() {
        return _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda;
    }

    public void set_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda(
            CBRCaseBaseGustavo _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda) {
        this._caseBaseCentroideTerceiraCartaRoboGanhouAsegunda = _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda() {
        return _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda;
    }

    public void set_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda(
            CBRCaseBaseGustavo _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda) {
        this._caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda = _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideQuemTrucoPrimeiraMao() {
        return _caseBaseCentroideQuemTrucoPrimeiraMao;
    }

    public void set_caseBaseCentroideQuemTrucoPrimeiraMao(CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoPrimeiraMao) {
        this._caseBaseCentroideQuemTrucoPrimeiraMao = _caseBaseCentroideQuemTrucoPrimeiraMao;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideQuemTrucoPrimeiraPe() {
        return _caseBaseCentroideQuemTrucoPrimeiraPe;
    }

    public void set_caseBaseCentroideQuemTrucoPrimeiraPe(CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoPrimeiraPe) {
        this._caseBaseCentroideQuemTrucoPrimeiraPe = _caseBaseCentroideQuemTrucoPrimeiraPe;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideQuemTrucoSegundaGanhouAnterior() {
        return _caseBaseCentroideQuemTrucoSegundaGanhouAnterior;
    }

    public void set_caseBaseCentroideQuemTrucoSegundaGanhouAnterior(
            CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoSegundaGanhouAnterior) {
        this._caseBaseCentroideQuemTrucoSegundaGanhouAnterior = _caseBaseCentroideQuemTrucoSegundaGanhouAnterior;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior() {
        return _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior;
    }

    public void set_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior(
            CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior) {
        this._caseBaseCentroideQuemTrucoSegundaPerdeuAnterior = _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior() {
        return _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior;
    }

    public void set_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior(
            CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior) {
        this._caseBaseCentroideQuemTrucoTerceiraGanhouAnterior = _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior() {
        return _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior;
    }

    public void set_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior(
            CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior) {
        this._caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior = _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideQuemGanhouEnvidoAgenteMao() {
        return _caseBaseCentroideQuemGanhouEnvidoAgenteMao;
    }

    public void set_caseBaseCentroideQuemGanhouEnvidoAgenteMao(
            CBRCaseBaseGustavo _caseBaseCentroideQuemGanhouEnvidoAgenteMao) {
        this._caseBaseCentroideQuemGanhouEnvidoAgenteMao = _caseBaseCentroideQuemGanhouEnvidoAgenteMao;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroideQuemGanhouEnvidoAgentePe() {
        return _caseBaseCentroideQuemGanhouEnvidoAgentePe;
    }

    public void set_caseBaseCentroideQuemGanhouEnvidoAgentePe(
            CBRCaseBaseGustavo _caseBaseCentroideQuemGanhouEnvidoAgentePe) {
        this._caseBaseCentroideQuemGanhouEnvidoAgentePe = _caseBaseCentroideQuemGanhouEnvidoAgentePe;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroidesGrupoIndexacao() {
        return _caseBaseCentroidesGrupoIndexacao;
    }

    public void set_caseBaseCentroidesGrupoIndexacao(CBRCaseBaseGustavo _caseBaseCentroidesGrupoIndexacao) {
        this._caseBaseCentroidesGrupoIndexacao = _caseBaseCentroidesGrupoIndexacao;
    }

    public CBRCaseBaseGustavo get_caseBaseCentroidesGrupoIndexacaoPontos() {
        return _caseBaseCentroidesGrupoIndexacaoPontos;
    }

    public void set_caseBaseCentroidesGrupoIndexacaoPontos(CBRCaseBaseGustavo _caseBaseCentroidesGrupoIndexacaoPontos) {
        this._caseBaseCentroidesGrupoIndexacaoPontos = _caseBaseCentroidesGrupoIndexacaoPontos;
    }

    public CaseBasesModelo getCaseBases() {
        return caseBases;
    }

    public void setCaseBases(CaseBasesModelo caseBases) {
        this.caseBases = caseBases;
    }

    public Connector get_connectorCentroidePrimeiraCartaRoboPeBaseline() {
        return _connectorCentroidePrimeiraCartaRoboPeBaseline;
    }

    public void set_connectorCentroidePrimeiraCartaRoboPeBaseline(
            Connector _connectorCentroidePrimeiraCartaRoboPeBaseline) {
        this._connectorCentroidePrimeiraCartaRoboPeBaseline = _connectorCentroidePrimeiraCartaRoboPeBaseline;
    }

    public Connector get_connectorCentroidePrimeiraCartaRoboPeImitacao() {
        return _connectorCentroidePrimeiraCartaRoboPeImitacao;
    }

    public void set_connectorCentroidePrimeiraCartaRoboPeImitacao(
            Connector _connectorCentroidePrimeiraCartaRoboPeImitacao) {
        this._connectorCentroidePrimeiraCartaRoboPeImitacao = _connectorCentroidePrimeiraCartaRoboPeImitacao;
    }

    public Connector get_connectorCentroidePrimeiraCartaRoboPeAtivo() {
        return _connectorCentroidePrimeiraCartaRoboPeAtivo;
    }

    public void set_connectorCentroidePrimeiraCartaRoboPeAtivo(Connector _connectorCentroidePrimeiraCartaRoboPeAtivo) {
        this._connectorCentroidePrimeiraCartaRoboPeAtivo = _connectorCentroidePrimeiraCartaRoboPeAtivo;
    }

    public Connector get_connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline() {
        return _connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline;
    }

    public void set_connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline(
            Connector _connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline) {
        this._connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline = _connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline;
    }

    public Connector get_connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao() {
        return _connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao;
    }

    public void set_connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao(
            Connector _connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao) {
        this._connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao = _connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao;
    }

    public Connector get_connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo() {
        return _connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo;
    }

    public void set_connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo(
            Connector _connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo) {
        this._connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo = _connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo;
    }

    public Connector get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline() {
        return _connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline;
    }

    public void set_connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline(
            Connector _connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline) {
        this._connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline = _connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline;
    }

    public Connector get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao() {
        return _connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao;
    }

    public void set_connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao(
            Connector _connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao) {
        this._connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao = _connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao;
    }

    public Connector get_connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo() {
        return _connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo;
    }

    public void set_connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo(
            Connector _connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo) {
        this._connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo = _connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo;
    }

    public Connector get_connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline() {
        return _connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline;
    }

    public void set_connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline(
            Connector _connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline) {
        this._connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline = _connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline;
    }

    public Connector get_connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao() {
        return _connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao;
    }

    public void set_connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao(
            Connector _connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao) {
        this._connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao = _connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao;
    }

    public Connector get_connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo() {
        return _connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo;
    }

    public void set_connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo(
            Connector _connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo) {
        this._connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo = _connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo;
    }

    public Connector get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline() {
        return _connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline;
    }

    public void set_connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline(
            Connector _connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline) {
        this._connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline = _connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline;
    }

    public Connector get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao() {
        return _connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao;
    }

    public void set_connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao(
            Connector _connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao) {
        this._connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao = _connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao;
    }

    public Connector get_connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo() {
        return _connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo;
    }

    public void set_connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo(
            Connector _connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo) {
        this._connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo = _connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo;
    }

    public Connector get_connectorCentroideQuemTrucoPrimeiraMaoBaseline() {
        return _connectorCentroideQuemTrucoPrimeiraMaoBaseline;
    }

    public void set_connectorCentroideQuemTrucoPrimeiraMaoBaseline(
            Connector _connectorCentroideQuemTrucoPrimeiraMaoBaseline) {
        this._connectorCentroideQuemTrucoPrimeiraMaoBaseline = _connectorCentroideQuemTrucoPrimeiraMaoBaseline;
    }

    public Connector get_connectorCentroideQuemTrucoPrimeiraMaoImitacao() {
        return _connectorCentroideQuemTrucoPrimeiraMaoImitacao;
    }

    public void set_connectorCentroideQuemTrucoPrimeiraMaoImitacao(
            Connector _connectorCentroideQuemTrucoPrimeiraMaoImitacao) {
        this._connectorCentroideQuemTrucoPrimeiraMaoImitacao = _connectorCentroideQuemTrucoPrimeiraMaoImitacao;
    }

    public Connector get_connectorCentroideQuemTrucoPrimeiraMaoAtivo() {
        return _connectorCentroideQuemTrucoPrimeiraMaoAtivo;
    }

    public void set_connectorCentroideQuemTrucoPrimeiraMaoAtivo(
            Connector _connectorCentroideQuemTrucoPrimeiraMaoAtivo) {
        this._connectorCentroideQuemTrucoPrimeiraMaoAtivo = _connectorCentroideQuemTrucoPrimeiraMaoAtivo;
    }

    public Connector get_connectorCentroideQuemTrucoPrimeiraPeBaseline() {
        return _connectorCentroideQuemTrucoPrimeiraPeBaseline;
    }

    public void set_connectorCentroideQuemTrucoPrimeiraPeBaseline(
            Connector _connectorCentroideQuemTrucoPrimeiraPeBaseline) {
        this._connectorCentroideQuemTrucoPrimeiraPeBaseline = _connectorCentroideQuemTrucoPrimeiraPeBaseline;
    }

    public Connector get_connectorCentroideQuemTrucoPrimeiraPeImitacao() {
        return _connectorCentroideQuemTrucoPrimeiraPeImitacao;
    }

    public void set_connectorCentroideQuemTrucoPrimeiraPeImitacao(
            Connector _connectorCentroideQuemTrucoPrimeiraPeImitacao) {
        this._connectorCentroideQuemTrucoPrimeiraPeImitacao = _connectorCentroideQuemTrucoPrimeiraPeImitacao;
    }

    public Connector get_connectorCentroideQuemTrucoPrimeiraPeAtivo() {
        return _connectorCentroideQuemTrucoPrimeiraPeAtivo;
    }

    public void set_connectorCentroideQuemTrucoPrimeiraPeAtivo(Connector _connectorCentroideQuemTrucoPrimeiraPeAtivo) {
        this._connectorCentroideQuemTrucoPrimeiraPeAtivo = _connectorCentroideQuemTrucoPrimeiraPeAtivo;
    }

    public Connector get_connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline() {
        return _connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline;
    }

    public void set_connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline(
            Connector _connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline) {
        this._connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline = _connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline;
    }

    public Connector get_connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao() {
        return _connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao;
    }

    public void set_connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao(
            Connector _connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao) {
        this._connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao = _connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao;
    }

    public Connector get_connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo() {
        return _connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo;
    }

    public void set_connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo(
            Connector _connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo) {
        this._connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo = _connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo;
    }

    public Connector get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline() {
        return _connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline;
    }

    public void set_connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline(
            Connector _connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline) {
        this._connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline = _connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline;
    }

    public Connector get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao() {
        return _connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao;
    }

    public void set_connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao(
            Connector _connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao) {
        this._connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao = _connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao;
    }

    public Connector get_connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo() {
        return _connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo;
    }

    public void set_connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo(
            Connector _connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo) {
        this._connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo = _connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo;
    }

    public Connector get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline() {
        return _connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline;
    }

    public void set_connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline(
            Connector _connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline) {
        this._connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline = _connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline;
    }

    public Connector get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao() {
        return _connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao;
    }

    public void set_connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao(
            Connector _connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao) {
        this._connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao = _connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao;
    }

    public Connector get_connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo() {
        return _connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo;
    }

    public void set_connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo(
            Connector _connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo) {
        this._connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo = _connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo;
    }

    public Connector get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline() {
        return _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline;
    }

    public void set_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline(
            Connector _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline) {
        this._connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline = _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline;
    }

    public Connector get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao() {
        return _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao;
    }

    public void set_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao(
            Connector _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao) {
        this._connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao = _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao;
    }

    public Connector get_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo() {
        return _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo;
    }

    public void set_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo(
            Connector _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo) {
        this._connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo = _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo;
    }

    /*
     * A porcaria do hibernate não está fechando as conexões do mysql 5 com o método
     * close, por isso vou precisar ter conexões estaticas para cada uma das
     * diferentes bases em cada diferente tabela, se precisar utilizar outra base
     * tem que configurar aqui também.
     */

    private Connector _connectorMaosBaseline = null;
    private Connector _connectorMaosImitacao = null;
    private Connector _connectorMaosAtivo = null;

    private CBRCaseBaseGustavo _caseBaseMaos = null;

    // centroide primeira CARTA ROBO MAO
    private Connector _connectorCentroidePrimeiraCartaRoboMaoBaseline = null;
    private Connector _connectorCentroidePrimeiraCartaRoboMaoImitacao = null;
    private Connector _connectorCentroidePrimeiraCartaRoboMaoAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroidePrimeiraCartaRoboMao = null;

    // centroide primeira carta robo pe
    private Connector _connectorCentroidePrimeiraCartaRoboPeBaseline = null;
    private Connector _connectorCentroidePrimeiraCartaRoboPeImitacao = null;
    private Connector _connectorCentroidePrimeiraCartaRoboPeAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroidePrimeiraCartaRoboPe = null;

    // centroide segunda carta robo ganhouAprimeira
    private Connector _connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline = null;
    private Connector _connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao = null;
    private Connector _connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo = null;

    public CBRCaseBaseGustavo _caseBaseCentroideSegundaCartaRoboGanhouAprimeira = null;

    // centroide segunda carta robo perdeuAprimeira
    private Connector _connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline = null;
    private Connector _connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao = null;
    private Connector _connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira = null;
    // centroide terceira carta robo ganhouAprimeira
    private Connector _connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline = null;
    private Connector _connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao = null;
    private Connector _connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda = null;
    // centroide segunda carta robo perdeu a segunda
    private Connector _connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline = null;
    private Connector _connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao = null;
    private Connector _connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda = null;

    // centroide quem truco primeiraMao
    private Connector _connectorCentroideQuemTrucoPrimeiraMaoBaseline = null;
    private Connector _connectorCentroideQuemTrucoPrimeiraMaoImitacao = null;
    private Connector _connectorCentroideQuemTrucoPrimeiraMaoAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoPrimeiraMao = null;

    // centroide quem truco primeiraPe
    private Connector _connectorCentroideQuemTrucoPrimeiraPeBaseline = null;
    private Connector _connectorCentroideQuemTrucoPrimeiraPeImitacao = null;
    private Connector _connectorCentroideQuemTrucoPrimeiraPeAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoPrimeiraPe = null;

    // centroide quem truco segunda ganhou anterior
    private Connector _connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline = null;
    private Connector _connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao = null;
    private Connector _connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoSegundaGanhouAnterior = null;

    // centroide quem truco segunda perdeu anterior
    private Connector _connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline = null;
    private Connector _connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao = null;
    private Connector _connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior = null;

    // centroide quem truco terceira ganhou anterior
    private Connector _connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline = null;
    private Connector _connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao = null;
    private Connector _connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior = null;

    // centroide quem truco terceira ganhou anterior
    private Connector _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline = null;
    private Connector _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao = null;
    private Connector _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior = null;

    // centroide quem envido jogador mao
    private Connector _connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline = null;
    private Connector _connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao = null;
    private Connector _connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideQuemGanhouEnvidoAgenteMao = null;
    // centroide quem envido jogador pe
    private Connector _connectorCentroideQuemGanhouEnvidoAgentePeBaseline = null;
    private Connector _connectorCentroideQuemGanhouEnvidoAgentePeImitacao = null;
    private Connector _connectorCentroideQuemGanhouEnvidoAgentePeAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroideQuemGanhouEnvidoAgentePe = null;

    // centroide conector indexacao
    private Connector _connectorCentroidesGrupoIndexacaoBaseline = null;
    private Connector _connectorCentroidesGrupoIndexacaoImitacao = null;
    private Connector _connectorCentroidesGrupoIndexacaoAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroidesGrupoIndexacao = null;

    // centroide conector indexacao pontos
    private Connector _connectorCentroidesGrupoIndexacaoPontosBaseline = null;
    private Connector _connectorCentroidesGrupoIndexacaoPontosImitacao = null;
    private Connector _connectorCentroidesGrupoIndexacaoPontosAtivo = null;

    private CBRCaseBaseGustavo _caseBaseCentroidesGrupoIndexacaoPontos = null;

    // se for fazer para contra flor é preciso fazer os conectores para o centroide
    // de contra flor

    String TipoAprendizagem = "Nenhum";
    String TipoRetencao = "Nada";

    CBRCentroides ck;

    private Deck deck;

    Double thresholdReuso = 0.98;
    Double thresholdAprendizagem = 0.98;
    Double thresholdAprendizadoAtivo = 0.5;
    int kMinimo = 5;
    double taxaDeAdaptacaoThreshold = 0.02;

    CaseBasesModelo caseBases;
    // hashMapEnvido
    HashMap<Integer, List<TrucoDescription>> hashEnvidoConsultaAtual;
    // hashMapTruco

    HashMap<Integer, List<TrucoDescription>> hashQuemTruco;
    // hashMap carta
    HashMap<Integer, List<TrucoDescription>> hashDeCasos;

    // Interfaces para aprendizagem
    ValidaCriterioReusoAtivoOuAleatorio validaCriterioDeveAprender;
    ValidaPersistir validaDevePersistir;
    Persistir persistir;
    private boolean activeLearning = false;

    //TODOS OS TIPOS DE TOMADAS DE DECISÃO  Extra CLuster
    DecisionExtraClusterCarta decisionCarta;
    DecisionExtaClusterTruco decisionTruco;
    DecisionExtraClusterEnvido decisionEnvido;

    // todos os tipos de tomadas de decisão intra cluster
    CartasJogadasIntraCluster decisaoIntraClusterCarta;
    AcaoFeitasIntraClusterTruco decisaoIntraClusterTruco;
    AcaoFeitasIntraClusterEnvido decisaoIntraClusterEnvido;

    /*
     * opções chancesucesso probabilidade maioria maissimilar
     */

    String tipoDecisao;
    String tipoReusoIntraCluster;
    // reuso com cluster ou sem cluster
    boolean reusoComCluster = false;

    // Hashs para todos os tipos de decisões
    HashCarta hashCarta;
    HashTruco hashTruco;
    HashEnvido hashEnvido;

    // grupo do caso atual
    int indexacaoEnvido = 0;
    int indexacaoJogada = 0;

    HashMap<Integer, Collection<CBRCase>> hashIndexacaoGruposJogada;
    HashMap<Integer, Collection<CBRCase>> hashIndexacaoGruposEnvido;
    // Collections com casos filtrados para cada uma das propostas
    Collection<CBRCase> casosUteisCartaJaIndexado = null;
    Collection<CBRCase> casosUteisTrucoJaIndexado = null;
    Collection<CBRCase> casosUteisEnvidoJaIndexado = null;
    Collection<CBRCase> casosUteisFLorJaIndexado = null;

    // Truco description para query
    TrucoDescription query = new TrucoDescription();

    public String getTipoDecisao() {
        return tipoDecisao;
    }

    public String getTipoReusoIntraCluster() {
        return tipoReusoIntraCluster;
    }

    private void setTipoReusoIntraCluster(String tipoReusoIntraCluster) {
        this.tipoReusoIntraCluster = tipoReusoIntraCluster;
    }

    public ValidaCriterioReusoAtivoOuAleatorio getValidaCriterioDeveAprender() {
        return validaCriterioDeveAprender;
    }

    public void setValidaCriterioDeveAprender(ValidaCriterioReusoAtivoOuAleatorio validaCriterioDeveAprender) {
        this.validaCriterioDeveAprender = validaCriterioDeveAprender;
    }

    public ValidaPersistir getValidaDevePersistir() {
        return validaDevePersistir;
    }

    public void setValidaDevePersistir(ValidaPersistir validaDevePersistir) {
        this.validaDevePersistir = validaDevePersistir;
    }

    public void atualizaTipoIntraClusterCarta() {
        if (getTipoReusoIntraCluster().equalsIgnoreCase("chancesucesso"))
            decisaoIntraClusterCarta = new CartasJogadasIntraClusterChanceSaldo();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("probabilidadesorteio"))
            decisaoIntraClusterCarta = new CartasJogadasIntraClusterProbabilidadeSorteio();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("probabilidadechance"))
            decisaoIntraClusterCarta = new CartasJogadasIntraClusterProbabilidadeChance();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("maioria"))
            decisaoIntraClusterCarta = new CartasJogadasIntraClusterMaioria();

    }

    public void atualizaTipoIntraClusterTruco() {
        if (getTipoReusoIntraCluster().equalsIgnoreCase("chancesucesso"))
            decisaoIntraClusterTruco = new AcaoFeitasIntraClusterTrucoChanceSaldo();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("probabilidadesorteio"))
            decisaoIntraClusterTruco = new AcaoFeitasIntraClusterTrucoProbabilidadeSorteio();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("probabilidadechance"))
            decisaoIntraClusterTruco = new AcaoFeitasIntraClusterTrucoProbabilidadeChance();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("maioria"))
            decisaoIntraClusterTruco = new AcaoFeitasIntraClusterTrucoMaioria();
    }

    public void atualizaTipoIntraClusterEnvido() {
        if (getTipoReusoIntraCluster().equalsIgnoreCase("chancesucesso"))
            decisaoIntraClusterEnvido = new AcaoFeitasIntraClusterEnvidoChanceSaldo();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("probabilidadesorteio"))
            decisaoIntraClusterEnvido = new AcaoFeitasIntraClusterEnvidoProbabilidadeSorteio();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("probabilidadechance"))
            decisaoIntraClusterEnvido = new AcaoFeitasIntraClusterEnvidoProbabilidadeChance();
        else if (getTipoReusoIntraCluster().equalsIgnoreCase("maioria"))
            decisaoIntraClusterEnvido = new AcaoFeitasIntraClusterEnvidoMaioria();
    }

    // se quiser parâmetrizar a decisão intra cluster também é só passar um
    // parÂmetro no construtor do método com o tipo de decisão intracluster
    public void setTipoDecisao(String tipoDecisao, String tipoReusoIntraCluster) {
        this.tipoDecisao = tipoDecisao;
        setTipoReusoIntraCluster(tipoReusoIntraCluster);
        atualizaTipoIntraClusterCarta();
        atualizaTipoIntraClusterEnvido();
        atualizaTipoIntraClusterTruco();
        if (tipoDecisao.equalsIgnoreCase("chancesucesso")) {
            decisionCarta = new DecisionExtaClusterChanceDeSucessoEsaldoCarta();
            decisionCarta.setDecisionIntraCluster(decisaoIntraClusterCarta);
            decisionTruco = new DecisionExtraClusterChanceDeSucessoEsaldoTruco();
            decisionTruco.setDecisionIntraClusterTruco(decisaoIntraClusterTruco);
            decisionEnvido = new DecisionExtraClusterChanceDeSucessoEsaldoEnvido();
            decisionEnvido.setDecisionIntraCluster(decisaoIntraClusterEnvido);

        } else if (tipoDecisao.equalsIgnoreCase("probabilidadesorteio")) {
            decisionCarta = new DecisionExtraClusterProbabilitySorteioCarta();
            decisionCarta.setDecisionIntraCluster(decisaoIntraClusterCarta);

            decisionTruco = new DecisionExtraClusterProbabilitySorteioTruco();
            decisionTruco.setDecisionIntraClusterTruco(decisaoIntraClusterTruco);

            decisionEnvido = new DecisionExtraClusterProbabilitySorteioEnvido();
            decisionEnvido.setDecisionIntraCluster(decisaoIntraClusterEnvido);

        } else if (tipoDecisao.equalsIgnoreCase("probabilidadechance")) {
            decisionCarta = new DecisionExtaClusterProbabilidadeSucessoCarta();
            decisionCarta.setDecisionIntraCluster(decisaoIntraClusterCarta);

            decisionTruco = new DecisionExtraClusterProbabilidadeTruco();
            decisionTruco.setDecisionIntraClusterTruco(decisaoIntraClusterTruco);

            decisionEnvido = new DecisionExtraClusterProbabilidadeSucessoEnvido();
            decisionEnvido.setDecisionIntraCluster(decisaoIntraClusterEnvido);

        } else if (tipoDecisao.equalsIgnoreCase("maioria")) {
            decisionCarta = new DecisionExtraClusterMaioriaCarta();
            decisionCarta.setDecisionIntraCluster(decisaoIntraClusterCarta);
            decisionCarta.setDecisionIntraCluster(decisaoIntraClusterCarta);

            decisionTruco = new DecisionExtraClusterMaioriaTruco();
            decisionTruco.setDecisionIntraClusterTruco(decisaoIntraClusterTruco);

            decisionEnvido = new DecisionExtraClusterMaioriaEnvido();
            decisionEnvido.setDecisionIntraCluster(decisaoIntraClusterEnvido);
        }
    }

    public boolean isReusoComCluster() {
        return reusoComCluster;
    }

    public void setReusoComCluster(boolean reusoComCluster) {
        this.reusoComCluster = reusoComCluster;
        if (reusoComCluster) {
            hashEnvido = new HashClusterEnvido();
            hashTruco = new HashClusterTruco();
            hashCarta = new HashClusterCarta();
        } else {
            hashEnvido = new HashSolucaoEnvido();
            hashTruco = new HashSolucaoTruco();
            hashCarta = new HashSolucaoCarta();
        }
    }

    public CbrModular(String tipoCBR, String database) {

        try {
            this.dataBaseConectado = database;

            caseBases = new CaseBasesModelo();

            ck = new CBRCentroides();

            deck = new Deck();
            deck.createDeck();
            deck.gerarAllHandsPossible();
//bases

            ////////// //////System.out.println("database: " + database);

            initialize_conector("Maos", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                openConnectionBase(_caseBaseMaos, _connectorMaosImitacao, "Maos", this);
            else if (database.equalsIgnoreCase("ativo"))
                openConnectionBase(_caseBaseMaos, _connectorMaosAtivo, "Maos", this);
            else
                openConnectionBase(_caseBaseMaos, _connectorMaosBaseline, "Maos", this);
            caseBases.set_caseBaseMaos(_caseBaseMaos.getCases());

            initialize_conector("CentroideIndexacao", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                openConnectionBase(_caseBaseCentroidesGrupoIndexacao, _connectorCentroidesGrupoIndexacaoImitacao,
                        "CentroideIndexacao", this);
            else if (database.equalsIgnoreCase("ativo"))
                openConnectionBase(_caseBaseCentroidesGrupoIndexacao, _connectorCentroidesGrupoIndexacaoAtivo,
                        "CentroideIndexacao", this);
            else
                openConnectionBase(_caseBaseCentroidesGrupoIndexacao, _connectorCentroidesGrupoIndexacaoBaseline,
                        "CentroideIndexacao", this);
            caseBases.set_caseBaseCentroidesGrupoIndexacao(_caseBaseCentroidesGrupoIndexacao.getCases());

            initialize_conector("CentroideIndexacaoPontos", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                openConnectionBase(_caseBaseCentroidesGrupoIndexacaoPontos,
                        _connectorCentroidesGrupoIndexacaoPontosImitacao, "CentroideIndexacaoPontos", this);
            else if (database.equalsIgnoreCase("ativo"))
                openConnectionBase(_caseBaseCentroidesGrupoIndexacaoPontos,
                        _connectorCentroidesGrupoIndexacaoPontosAtivo, "CentroideIndexacaoPontos", this);
            else
                openConnectionBase(_caseBaseCentroidesGrupoIndexacaoPontos,
                        _connectorCentroidesGrupoIndexacaoPontosBaseline, "CentroideIndexacaoPontos", this);
            caseBases.set_caseBaseCentroidesGrupoIndexacaoPontos(_caseBaseCentroidesGrupoIndexacaoPontos.getCases());

            // inicializa a validaÃ§Ã£o dos centroides
            // centroides primeiraCartaRoboMao
            ck.initialize_conector("centroidePrimeiraCartaRoboMao", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroidePrimeiraCartaRoboMao,
                        _connectorCentroidePrimeiraCartaRoboMaoImitacao, "centroidePrimeiraCartaRoboMao", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroidePrimeiraCartaRoboMao,
                        _connectorCentroidePrimeiraCartaRoboMaoAtivo, "centroidePrimeiraCartaRoboMao", this);
            else
                ck.openConnectionBase(_caseBaseCentroidePrimeiraCartaRoboMao,
                        _connectorCentroidePrimeiraCartaRoboMaoBaseline, "centroidePrimeiraCartaRoboMao", this);
            caseBases.set_caseBaseCentroidePrimeiraCartaRoboMao(_caseBaseCentroidePrimeiraCartaRoboMao.getCases());

            // centroides primeiraCartaRoboPe
            ck.initialize_conector("centroidePrimeiraCartaRoboPe", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroidePrimeiraCartaRoboPe,
                        _connectorCentroidePrimeiraCartaRoboPeImitacao, "centroidePrimeiraCartaRoboPe", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroidePrimeiraCartaRoboPe,
                        _connectorCentroidePrimeiraCartaRoboPeAtivo, "centroidePrimeiraCartaRoboPe", this);
            else
                ck.openConnectionBase(_caseBaseCentroidePrimeiraCartaRoboPe,
                        _connectorCentroidePrimeiraCartaRoboPeBaseline, "centroidePrimeiraCartaRoboPe", this);
            caseBases.set_caseBaseCentroidePrimeiraCartaRoboPe(_caseBaseCentroidePrimeiraCartaRoboPe.getCases());

            // centroides segundaCartaRoboGanhou a primeira
            ck.initialize_conector("centroidSegundaCartaRoboGanhouAprimeira", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideSegundaCartaRoboGanhouAprimeira,
                        _connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao,
                        "centroidSegundaCartaRoboGanhouAprimeira", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideSegundaCartaRoboGanhouAprimeira,
                        _connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo,
                        "centroidSegundaCartaRoboGanhouAprimeira", this);
            else
                ck.openConnectionBase(_caseBaseCentroideSegundaCartaRoboGanhouAprimeira,
                        _connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline,
                        "centroidSegundaCartaRoboGanhouAprimeira", this);
            caseBases.set_caseBaseCentroideSegundaCartaRoboGanhouAprimeira(
                    _caseBaseCentroideSegundaCartaRoboGanhouAprimeira.getCases());

            // centroides segunda carta robo perdeu a primeira
            ck.initialize_conector("centroidSegundaCartaRoboPerdeuAprimeira", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira,
                        _connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao,
                        "centroidSegundaCartaRoboPerdeuAprimeira", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira,
                        _connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo,
                        "centroidSegundaCartaRoboPerdeuAprimeira", this);
            else
                ck.openConnectionBase(_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira,
                        _connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline,
                        "centroidSegundaCartaRoboPerdeuAprimeira", this);
            caseBases.set_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira(
                    _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira.getCases());

            // centroides terceira carta robo ganhou a segunda
            ck.initialize_conector("centroidTerceiraCartaRoboGanhouAsegunda", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda,
                        _connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao,
                        "centroidTerceiraCartaRoboGanhouAsegunda", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda,
                        _connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo,
                        "centroidTerceiraCartaRoboGanhouAsegunda", this);
            else
                ck.openConnectionBase(_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda,
                        _connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline,
                        "centroidTerceiraCartaRoboGanhouAsegunda", this);
            caseBases.set_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda(
                    _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda.getCases());
            // teceira carta perdeu a segunda

            ck.initialize_conector("centroidTerceiraCartaRoboPerdeuAsegunda", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda,
                        _connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao,
                        "centroidTerceiraCartaRoboPerdeuAsegunda", this);
            if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda,
                        _connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo,
                        "centroidTerceiraCartaRoboPerdeuAsegunda", this);
            ck.openConnectionBase(_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda,
                    _connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline,
                    "centroidTerceiraCartaRoboPerdeuAsegunda", this);
            caseBases.set_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda(
                    _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda.getCases());
            // centroides quem truco primeira mÃ£o
            ck.initialize_conector("centroidQuemTrucoPrimeiraMao", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoPrimeiraMao,
                        _connectorCentroideQuemTrucoPrimeiraMaoImitacao, "centroidQuemTrucoPrimeiraMao", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoPrimeiraMao,
                        _connectorCentroideQuemTrucoPrimeiraMaoAtivo, "centroidQuemTrucoPrimeiraMao", this);
            else
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoPrimeiraMao,
                        _connectorCentroideQuemTrucoPrimeiraMaoBaseline, "centroidQuemTrucoPrimeiraMao", this);
            caseBases.set_caseBaseCentroideQuemTrucoPrimeiraMao(_caseBaseCentroideQuemTrucoPrimeiraMao.getCases());

            // centroides quem truco primeira pÃ©
            ck.initialize_conector("centroidQuemTrucoPrimeiraPe", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoPrimeiraPe,
                        _connectorCentroideQuemTrucoPrimeiraPeImitacao, "centroidQuemTrucoPrimeiraPe", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoPrimeiraPe,
                        _connectorCentroideQuemTrucoPrimeiraPeAtivo, "centroidQuemTrucoPrimeiraPe", this);
            else
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoPrimeiraPe,
                        _connectorCentroideQuemTrucoPrimeiraPeBaseline, "centroidQuemTrucoPrimeiraPe", this);
            caseBases.set_caseBaseCentroideQuemTrucoPrimeiraPe(_caseBaseCentroideQuemTrucoPrimeiraPe.getCases());

            // centroides segunda ganhou anterior
            ck.initialize_conector("centroidQuemTrucoSegundaGanhouAnterior", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoSegundaGanhouAnterior,
                        _connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao,
                        "centroidQuemTrucoSegundaGanhouAnterior", this);
            if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoSegundaGanhouAnterior,
                        _connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo,
                        "centroidQuemTrucoSegundaGanhouAnterior", this);
            else
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoSegundaGanhouAnterior,
                        _connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline,
                        "centroidQuemTrucoSegundaGanhouAnterior", this);
            caseBases.set_caseBaseCentroideQuemTrucoSegundaGanhouAnterior(
                    _caseBaseCentroideQuemTrucoSegundaGanhouAnterior.getCases());

            // centroides segunda Perdeu anterior
            ck.initialize_conector("centroidQuemTrucoSegundaPerdeuAnterior", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior,
                        _connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao,
                        "centroidQuemTrucoSegundaPerdeuAnterior", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior,
                        _connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo,
                        "centroidQuemTrucoSegundaPerdeuAnterior", this);
            else
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior,
                        _connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline,
                        "centroidQuemTrucoSegundaPerdeuAnterior", this);
            caseBases.set_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior(
                    _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior.getCases());

            // centroides Terceira ganhou anterior
            ck.initialize_conector("centroidQuemTrucoTerceiraGanhouAnterior", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior,
                        _connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao,
                        "centroidQuemTrucoTerceiraGanhouAnterior", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior,
                        _connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo,
                        "centroidQuemTrucoTerceiraGanhouAnterior", this);
            else
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior,
                        _connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline,
                        "centroidQuemTrucoTerceiraGanhouAnterior", this);

            caseBases.set_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior(
                    _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior.getCases());

            // centroides Terceira Perdeu anterior
            ck.initialize_conector("centroidQuemTrucoTerceiraPerdeuAnterior", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior,
                        _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao,
                        "centroidQuemTrucoTerceiraPerdeuAnterior", this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior,
                        _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo,
                        "centroidQuemTrucoTerceiraPerdeuAnterior", this);
            else
                ck.openConnectionBase(_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior,
                        _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline,
                        "centroidQuemTrucoTerceiraPerdeuAnterior", this);
            caseBases.set_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior(
                    _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior.getCases());

            // centroides grupos quemEnvidoAgenteMao
            ck.initialize_conector("centroidQuemGanhouEnvidoAgenteMao", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideQuemGanhouEnvidoAgenteMao,
                        _connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao, "centroidQuemGanhouEnvidoAgenteMao",
                        this);
            else if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideQuemGanhouEnvidoAgenteMao,
                        _connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo, "centroidQuemGanhouEnvidoAgenteMao", this);
            else
                ck.openConnectionBase(_caseBaseCentroideQuemGanhouEnvidoAgenteMao,
                        _connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline, "centroidQuemGanhouEnvidoAgenteMao",
                        this);
            caseBases.set_caseBaseCentroideQuemGanhouEnvidoAgenteMao(
                    _caseBaseCentroideQuemGanhouEnvidoAgenteMao.getCases());

            // centroides grupos quemEnvidoAgentePe

            ck.initialize_conector("centroidQuemGanhouEnvidoAgentePe", database, this);
            if (database.equalsIgnoreCase("imitacao"))
                ck.openConnectionBase(_caseBaseCentroideQuemGanhouEnvidoAgentePe,
                        _connectorCentroideQuemGanhouEnvidoAgentePeImitacao, "centroidQuemGanhouEnvidoAgentePe", this);
            if (database.equalsIgnoreCase("ativo"))
                ck.openConnectionBase(_caseBaseCentroideQuemGanhouEnvidoAgentePe,
                        _connectorCentroideQuemGanhouEnvidoAgentePeAtivo, "centroidQuemGanhouEnvidoAgentePe", this);
            else
                ck.openConnectionBase(_caseBaseCentroideQuemGanhouEnvidoAgentePe,
                        _connectorCentroideQuemGanhouEnvidoAgentePeBaseline, "centroidQuemGanhouEnvidoAgentePe", this);
            caseBases.set_caseBaseCentroideQuemGanhouEnvidoAgentePe(
                    _caseBaseCentroideQuemGanhouEnvidoAgentePe.getCases());

        } catch (ExecutionException e) {
            org.apache.commons.logging.LogFactory.getLog(CBR.class).error(e);
            e.printStackTrace();

        }
        hashIndexacaoGruposEnvido = retornaHashsDeGruposEnvidoPorCasos(_caseBaseMaos,
                _caseBaseCentroidesGrupoIndexacaoPontos);
        hashIndexacaoGruposJogada = retornaHashsDeGruposJogadaPorCasos(_caseBaseMaos,
                _caseBaseCentroidesGrupoIndexacao);
    }

    public CaseBasesModelo preencheCaseBase() {
        caseBases.set_caseBaseMaos(_caseBaseMaos.getCases());

        caseBases.set_caseBaseCentroidesGrupoIndexacao(_caseBaseCentroidesGrupoIndexacao.getCases());

        caseBases.set_caseBaseCentroidesGrupoIndexacaoPontos(_caseBaseCentroidesGrupoIndexacaoPontos.getCases());

        caseBases.set_caseBaseCentroidePrimeiraCartaRoboMao(_caseBaseCentroidePrimeiraCartaRoboMao.getCases());

        caseBases.set_caseBaseCentroidePrimeiraCartaRoboPe(_caseBaseCentroidePrimeiraCartaRoboPe.getCases());

        caseBases.set_caseBaseCentroideSegundaCartaRoboGanhouAprimeira(
                _caseBaseCentroideSegundaCartaRoboGanhouAprimeira.getCases());

        caseBases.set_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira(
                _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira.getCases());

        caseBases.set_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda(
                _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda.getCases());

        caseBases.set_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda(
                _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda.getCases());

        caseBases.set_caseBaseCentroideQuemTrucoPrimeiraMao(_caseBaseCentroideQuemTrucoPrimeiraMao.getCases());

        caseBases.set_caseBaseCentroideQuemTrucoPrimeiraPe(_caseBaseCentroideQuemTrucoPrimeiraPe.getCases());

        caseBases.set_caseBaseCentroideQuemTrucoSegundaGanhouAnterior(
                _caseBaseCentroideQuemTrucoSegundaGanhouAnterior.getCases());

        caseBases.set_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior(
                _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior.getCases());

        caseBases.set_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior(
                _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior.getCases());

        caseBases.set_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior(
                _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior.getCases());

        caseBases
                .set_caseBaseCentroideQuemGanhouEnvidoAgenteMao(_caseBaseCentroideQuemGanhouEnvidoAgenteMao.getCases());

        caseBases.set_caseBaseCentroideQuemGanhouEnvidoAgentePe(_caseBaseCentroideQuemGanhouEnvidoAgentePe.getCases());

        return caseBases;
    }

    public int getIndexacaoEnvido() {
        return indexacaoEnvido;
    }

    public int getIndexacaoJogada() {
        return indexacaoJogada;
    }

    public void setaGrupoMaisSimilarIndexadoJogada(TrucoDescription newCase) {
        preencheCaseBase();

        CentroidesGrupoIndexacaoDescription consultaGrupoMaisSimilarIndexacao = new converteTrucoDescriptionParaCentroidesGrupoIndexacaoDescription()
                .converte(newCase);
        CBRQuery query = new CBRQuery();
        query.setDescription(consultaGrupoMaisSimilarIndexacao);

        Collection<RetrievalResult> executeQueryIndexacao;
        CentroidesGrupoIndexacaoDescription cgi = null;

        try {
            executeQueryIndexacao = executeQueryIndexacao(_caseBaseCentroidesGrupoIndexacao, query);
            Iterator<RetrievalResult> i = executeQueryIndexacao.iterator();
            if (i.hasNext())
                cgi = (CentroidesGrupoIndexacaoDescription) i.next().get_case().getDescription();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        indexacaoJogada = cgi.getGrupo();

        // preenche os COllections uteis com os hashs
        if (hashIndexacaoGruposJogada.isEmpty())
            hashIndexacaoGruposJogada = retornaHashsDeGruposJogadaPorCasos(_caseBaseMaos,
                    _caseBaseCentroidesGrupoIndexacao);

        casosUteisCartaJaIndexado = retornaApenasCasosUteisParaCarta(hashIndexacaoGruposJogada.get(indexacaoJogada));

        casosUteisTrucoJaIndexado = retornaApenasCasosUteisParaTruco(hashIndexacaoGruposJogada.get(indexacaoJogada));

    }

    public void zeraGruposInformacoesRodadaFinalizada() {

        indexacaoJogada = 0;
        hashIndexacaoGruposJogada = new HashMap<Integer, Collection<CBRCase>>();
        casosUteisCartaJaIndexado = null;
        casosUteisTrucoJaIndexado = null;

        indexacaoEnvido = 0;
        hashIndexacaoGruposEnvido = new HashMap<Integer, Collection<CBRCase>>();
        casosUteisEnvidoJaIndexado = null;
        casosUteisFLorJaIndexado = null;

    }

    public void setaGrupoMaisSimilarIndexadoPontos(TrucoDescription newCase) {
        preencheCaseBase();
        CentroidesGrupoIndexacaoPontosDescription descriptionEnvido = new CentroidesGrupoIndexacaoPontosDescription();
        descriptionEnvido.setCentroidepontosenvidorobo(newCase.getPontosEnvidoRobo());

        CBRQuery query = new CBRQuery();
        query.setDescription(descriptionEnvido);

        Collection<RetrievalResult> executeQueryIndexacao;
        CentroidesGrupoIndexacaoPontosDescription cgi = null;

        try {
            executeQueryIndexacao = executeQueryIndexacaoEnvido(_caseBaseCentroidesGrupoIndexacaoPontos, query);
            Iterator<RetrievalResult> i = executeQueryIndexacao.iterator();
            if (i.hasNext())
                cgi = (CentroidesGrupoIndexacaoPontosDescription) i.next().get_case().getDescription();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        indexacaoEnvido = cgi.getGrupo();

        // //System.out.println("grupo indexado envido: " + indexacaoEnvido);
        if (hashIndexacaoGruposEnvido.isEmpty())
            hashIndexacaoGruposEnvido = retornaHashsDeGruposEnvidoPorCasos(_caseBaseMaos,
                    _caseBaseCentroidesGrupoIndexacaoPontos);

        // //System.out.println("grupo mais similar indexação envido: " +
        // indexacaoEnvido);

        casosUteisEnvidoJaIndexado = retornaApenasCasosUteisParaEnvido(hashIndexacaoGruposEnvido.get(indexacaoEnvido),
                newCase.getJogadorMao());

        casosUteisFLorJaIndexado = retornaApenasCasosUteisParaFlor(hashIndexacaoGruposEnvido.get(indexacaoEnvido),
                newCase.getJogadorMao());

    }

    public Collection<RetrievalResult> retornaRecuperadosFiltradoPontosAL(TrucoDescription gamestate, double threshold) {
        // ////System.out.println("casosUteisEnvidoJaIndexado "+
        // casosUteisEnvidoJaIndexado.size());
        if (casosUteisEnvidoJaIndexado == null || indexacaoEnvido == 0)
            setaGrupoMaisSimilarIndexadoPontos(gamestate);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisEnvidoJaIndexado, gamestate, pontoClusterAL);

        System.out.println("Casos Recuperados" + bestMatch.size());

        int aux = 0;
        for (RetrievalResult retrievalResult : bestMatch) {
            aux++;
            if (aux == 1) {
                System.out.println("Maior Similaridade: " + retrievalResult.getEval());
            }

        }



        bestMatch = FiltraResultsEnvido(bestMatch, threshold, gamestate);

        return bestMatch;
    }

    public Collection<RetrievalResult> retornaRecuperadosFiltradoPontos(TrucoDescription gamestate, double threshold) {
        // ////System.out.println("casosUteisEnvidoJaIndexado "+
        // casosUteisEnvidoJaIndexado.size());
        if (casosUteisEnvidoJaIndexado == null || indexacaoEnvido == 0)
            setaGrupoMaisSimilarIndexadoPontos(gamestate);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisEnvidoJaIndexado, gamestate,
                pontoCluster);

        bestMatch = FiltraResultsEnvido(bestMatch, threshold, gamestate);
        // System.out.println("jogador mão passado no chamar envido: " +
        // gamestate.getJogadorMao());
        // System.out.println("tamanho da lista filtrada: " + bestMatch.size());

        return bestMatch;
    }

    public boolean chamarEnvido(TrucoDescription gameState, int rodada) {

        boolean retorno = false;

        // //////System.out.println("entrou no chamar envido");
        Collection<RetrievalResult> bestMatch = retornaRecuperadosFiltradoPontos(gameState, thresholdReuso);

        // //System.out.println("está no chamar envido");
        double novoThreshold = thresholdReuso;
        while (bestMatch.size() < kMinimo) {

            novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
            bestMatch = retornaRecuperadosFiltradoPontos(gameState, novoThreshold);
            //// //////System.out.println("novo threshold chamar envido: "+ novoThreshold);
            if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                break;
        }

        retorno = chamarEnvidoCluster(gameState, rodada, bestMatch);
        if (revisarAtivo)
            System.out.println("[CHAMAR_ENVIDO] REUSE_POLICY_MOVE: " + retorno);

        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "ENVIDO") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 500, "ENVIDO", rodada, novoThreshold);
            System.out.println("[CHAMAR_ENVIDO] REVIEWED_MOVE: " + retorno);

        }


        return retorno;
    }

    public boolean chamarEnvidoCluster(TrucoDescription gamestate, int rodada, Collection<RetrievalResult> best) {

        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashEnvidoConsultaAtual = hashEnvido.retornaHashChamarEnvido(tc, gamestate.getJogadorMao());

        boolean decisao = decisionEnvido.chamarEnvido(gamestate, rodada, hashEnvidoConsultaAtual);
        // revisa casos que chamam com pontuação baixa quando é mão, melhor deixar para
        // o oponente tentar caçar pontos
		/*if (gamestate.getJogadorMao().equals(1) && gamestate.getPontosEnvidoRobo() < 27
				&& gamestate.getJogadorMao().equals(1))
			decisao = false;*/

        return decisao;

    }

    public boolean chamarRealEnvido(TrucoDescription gameState, int rodada) {

        boolean retorno = false;

        // //System.out.println("entrou no chamar real envido");
        Collection<RetrievalResult> bestMatch = retornaRecuperadosFiltradoPontos(gameState, thresholdReuso);

        double novoThreshold = thresholdReuso;
        while (bestMatch.size() < kMinimo) {

            novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
            bestMatch = retornaRecuperadosFiltradoPontos(gameState, novoThreshold);
            if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                break;
        }
        // //////System.out.println("threshold retornada para chamar real envido: "+
        // novoThreshold);
        retorno = chamarRealEnvidoCluster(gameState, rodada, bestMatch);
        if (revisarAtivo)
            System.out.println("[CHAMAR_REAL_ENVIDO] REUSE_POLICY_MOVE: " + retorno);

        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "ENVIDO") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 500, "REAL_ENVIDO", rodada, novoThreshold);
            System.out.println("[CHAMAR_REAL_ENVIDO] REVIEWED_MOVE: " + retorno);
        }

        return retorno;
    }

    public boolean chamarRealEnvidoCluster(TrucoDescription gamestate, int rodada, Collection<RetrievalResult> best) {

        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashEnvidoConsultaAtual = hashEnvido.retornaHashChamarRealEnvido(tc, gamestate.getJogadorMao());

        boolean decisao = decisionEnvido.chamarRealEnvido(gamestate, rodada, hashEnvidoConsultaAtual);
        // revisa para garantir que está seguindo o fluxo, isso é não está chamando real
        // envido direto, isso ajuda a deixar o bot menos previsivel
        // ele vai consultar envido depois disso
		/*if (decisao && (gamestate.getQuemPediuEnvido() == null || gamestate.getQuemPediuEnvido() == 0))
			decisao = false;*/

        return decisao;
    }

    private List<TrucoDescription> ConvertRetrievalResultToList(Collection<RetrievalResult> best) {
        List<TrucoDescription> tc = new ArrayList<TrucoDescription>();
        Iterator iteratorRetrievalResult = best.iterator();
        while (iteratorRetrievalResult.hasNext()) {
            RetrievalResult r = (RetrievalResult) iteratorRetrievalResult.next();
            tc.add((TrucoDescription) r.get_case().getDescription());
        }
        return tc;
    }

    public boolean chamarFaltaEnvido(TrucoDescription gameState, int rodada) {

        boolean retorno = false;

        Collection<RetrievalResult> bestMatch = retornaRecuperadosFiltradoPontos(gameState, thresholdReuso);
        double novoThreshold = thresholdReuso;
        // //System.out.println("entrou no chamar falta envido");
        // //System.out.println("quantidade de casos recuperados " + bestMatch.size());
        while (bestMatch.size() < kMinimo) {
            // //System.out.println(bestMatch.size());
            novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
            bestMatch = retornaRecuperadosFiltradoPontos(gameState, novoThreshold);
            if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                break;
        }
        // //////System.out.println("threshold retornado para chamar falta envido");
        retorno = chamarFaltaEnvidoCluster(gameState, rodada, bestMatch);
        if (revisarAtivo)
            System.out.println("[CHAMAR_FALTA_ENVIDO] REUSE_POLICY_MOVE: " + retorno);
        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "ENVIDO") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 500, "FALTA_ENVIDO", rodada, novoThreshold);
            System.out.println("[CHAMAR_FALTA_ENVIDO] REVIEWED_MOVE: " + retorno);
        }

        return retorno;
    }

    public boolean chamarFaltaEnvidoCluster(TrucoDescription gamestate, int rodada, Collection<RetrievalResult> best) {

        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);
        hashEnvidoConsultaAtual = hashEnvido.retornaHashChamarFaltaEnvido(tc, gamestate.getJogadorMao());
        boolean decisao = decisionEnvido.chamarFaltaEnvido(gamestate, rodada, hashEnvidoConsultaAtual);

        // revisa para garantir que está seguindo o fluxo, isso é não está chamando
        // falta envido direto, isso ajuda a deixar o bot menos previsivel
        // ele vai consultar real envido depois disso
		/*if (decisao && ((gamestate.getQuemPediuRealEnvido() == null || gamestate.getQuemPediuRealEnvido() == 0)))
			decisao = false;*/

        return decisao;

    }

    public boolean aceitarEnvido(TrucoDescription gameState, int rodada) {


        //if (gameState.getPontosEnvidoRobo() >= 20) {
        Collection<RetrievalResult> bestMatch = retornaRecuperadosFiltradoPontos(gameState, thresholdReuso);
        double novoThreshold = thresholdReuso;
        // //System.out.println("entrou no aceitar envido");
        while (bestMatch.size() < kMinimo) {

            novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
            bestMatch = retornaRecuperadosFiltradoPontos(gameState, novoThreshold);
            if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                break;
        }
        boolean decisao = false;
        decisao = aceitarEnvidoCluster(gameState, rodada, bestMatch);
			/*if (gameState.getJogadorMao().equals(2) && gameState.getPontosEnvidoRobo() < 27 && decisao == true)
				decisao = false;*/

        if (revisarAtivo)
            System.out.println("[ACEITAR_ENVIDO] REUSE_POLICY_MOVE: " + decisao);



        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "ENVIDO") && revisarAtivo){

            decisao = realizaRevisaoAtivo(gameState, decisao, bestMatch, 500, "ACEITAR_ENVIDO", rodada, novoThreshold);
            System.out.println("[ACEITAR_ENVIDO] REVIEWED_MOVE: " + decisao);
        }

        return decisao;
		/*} else
			return false;*/
    }

    public boolean aceitarEnvidoCluster(TrucoDescription gamestate, int rodada, Collection<RetrievalResult> best) {
        //////// //////System.out.println("quantidade de casos recuperados: "+
        //////// best.size());
        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);
        //////// //////System.out.println("lista depois de convertida aceitar envido:
        //////// "+tc.size());
        hashEnvidoConsultaAtual = hashEnvido.retornaHashAceitarEnvido(tc, gamestate.getJogadorMao());

        return decisionEnvido.aceitarEnvido(gamestate, rodada, hashEnvidoConsultaAtual);

    }

    public boolean aceitarRealEnvido(TrucoDescription gameState, int rodada) {

        boolean aceitarRealEnvido = false;

		/*if (gameState.getPontosEnvidoRobo() >= 20) {
			if (contadorRealEfaltaEnvidoNegados.getQuantidadeRealEnvidoNegado() >= 1
					&& gameState.getPontosEnvidoRobo() >= 26) {
				// zera o contador do real envido
				contadorRealEfaltaEnvidoNegados.setQuantidadeRealEnvidoNegado(0);
				return aceitarEnvido(gameState, rodada);
			} else {*/

        Collection<RetrievalResult> bestMatch = retornaRecuperadosFiltradoPontos(gameState, thresholdReuso);
        double novoThreshold = thresholdReuso;

        while (bestMatch.size() < kMinimo) {

            novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
            bestMatch = retornaRecuperadosFiltradoPontos(gameState, novoThreshold);
            if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                break;
        }
        // //////System.out.println("threshold retornado aceitar real envido:
        // "+novoThreshold);
        boolean retorno = aceitarRealEnvidoCluster(gameState, rodada, bestMatch);
        if (!retorno)
            contadorRealEfaltaEnvidoNegados.setQuantidadeFaltaEnvidoNegado(
                    contadorRealEfaltaEnvidoNegados.getQuantidadeRealEnvidoNegado() + 1);

        aceitarRealEnvido = retorno;
        if (revisarAtivo)
            System.out.println("[ACEITAR_REAL_ENVIDO] REUSE_POLICY_MOVE: " + aceitarRealEnvido);
			/*}
		}*/ /*else
			contadorRealEfaltaEnvidoNegados.setQuantidadeFaltaEnvidoNegado(
					contadorRealEfaltaEnvidoNegados.getQuantidadeRealEnvidoNegado() + 1);*/

        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "ENVIDO") && revisarAtivo){

            aceitarRealEnvido = realizaRevisaoAtivo(gameState, aceitarRealEnvido, bestMatch, 500, "ACEITAR_REAL_ENVIDO", rodada, novoThreshold);
            System.out.println("[ACEITAR_REAL_ENVIDO] REVIEWED_MOVE: " + aceitarRealEnvido);
        }

        return aceitarRealEnvido;

    }

    public boolean aceitarRealEnvidoCluster(TrucoDescription gamestate, int rodada, Collection<RetrievalResult> best) {

        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);
        hashEnvidoConsultaAtual = hashEnvido.retornaHashAceitarRealEnvido(tc, gamestate.getJogadorMao());

        return decisionEnvido.aceitarRealEnvido(gamestate, rodada, hashEnvidoConsultaAtual);
    }

    public boolean aceitarFaltaEnvido(TrucoDescription gameState, int rodada) {

        boolean aceitarFaltaEnvido = false;

		/*if (gameState.getPontosEnvidoRobo() >= 20) {
			// evitar blefes sem parar aí faz a consulta como envido
			if (contadorRealEfaltaEnvidoNegados.getQuantidadeFaltaEnvidoNegado() >= 1
					&& gameState.getPontosEnvidoRobo() >= 27) {
				// zera o contador de blefe faltaENvido
				contadorRealEfaltaEnvidoNegados.setQuantidadeFaltaEnvidoNegado(0);
				aceitarFaltaEnvido =  aceitarEnvido(gameState, rodada);
			} else {*/
        Collection<RetrievalResult> bestMatch = retornaRecuperadosFiltradoPontos(gameState, thresholdReuso);
        double novoThreshold = thresholdReuso;
        while (bestMatch.size() < kMinimo) {

            novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
            bestMatch = retornaRecuperadosFiltradoPontos(gameState, novoThreshold);
            if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                break;
        }
        // //////System.out.println("Threshold retornado para aceitar falta envido: "+
        // novoThreshold);
        boolean retorno = aceitarFaltaEnvidoCluster(gameState, rodada, bestMatch);
        if (!retorno)
            contadorRealEfaltaEnvidoNegados.setQuantidadeFaltaEnvidoNegado(
                    contadorRealEfaltaEnvidoNegados.getQuantidadeFaltaEnvidoNegado() + 1);
        aceitarFaltaEnvido = retorno;
        if (revisarAtivo)
            System.out.println("[ACEITAR_FALTA_ENVIDO] REUSE_POLICY_MOVE: " + aceitarFaltaEnvido);
			/*}
		}*/ /*else
			contadorRealEfaltaEnvidoNegados.setQuantidadeFaltaEnvidoNegado(
					contadorRealEfaltaEnvidoNegados.getQuantidadeFaltaEnvidoNegado() + 1);*/

        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "ENVIDO") && revisarAtivo){

            aceitarFaltaEnvido = realizaRevisaoAtivo(gameState, aceitarFaltaEnvido, bestMatch, 500, "ACEITAR_FALTA_ENVIDO", rodada, novoThreshold);
            System.out.println("[ACEITAR_FALTA_ENVIDO] REVIEWED_MOVE: " + aceitarFaltaEnvido);
        }

        return aceitarFaltaEnvido;

    }

    public boolean aceitarFaltaEnvidoCluster(TrucoDescription gamestate, int rodada, Collection<RetrievalResult> best) {

        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashEnvidoConsultaAtual = hashEnvido.retornaHashAceitarFaltaEnvido(tc, gamestate.getJogadorMao());

        return decisionEnvido.aceitarFaltaEnvido(gamestate, rodada, hashEnvidoConsultaAtual);
    }

    public Collection<RetrievalResult> retornaRecuperadosFiltradosTrucoAL(TrucoDescription gamestate, double threshold, int rodada) {

        if (casosUteisTrucoJaIndexado == null || indexacaoJogada == 0)
            setaGrupoMaisSimilarIndexadoJogada(gamestate);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisTrucoJaIndexado, gamestate,
                trucoClusterAL);

        System.out.println("Casos Recuperados" + bestMatch.size());

        int aux = 0;
        for (RetrievalResult retrievalResult : bestMatch) {
            aux++;
            if (aux == 1) {
                System.out.println("Maior Similaridade: " + retrievalResult.getEval());
            }

        }


        Collection<RetrievalResult> bestRoboFiltrado = FiltraResultsTruco(bestMatch, threshold, gamestate, rodada,
                kMinimo);
        return bestRoboFiltrado;
    }

    public Collection<RetrievalResult> retornaRecuperadosFiltradosTruco(TrucoDescription gamestate, double threshold,
                                                                        int rodada) {
        if (casosUteisTrucoJaIndexado == null || indexacaoJogada == 0)
            setaGrupoMaisSimilarIndexadoJogada(gamestate);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisTrucoJaIndexado, gamestate,
                trucoCluster);
        Collection<RetrievalResult> bestRoboFiltrado = FiltraResultsTruco(bestMatch, threshold, gamestate, rodada,
                kMinimo);
        return bestRoboFiltrado;
    }

    public boolean chamarTruco(TrucoDescription gameState, int rodada) {

        boolean retorno = false;
        double novoThreshold = thresholdReuso;
        Collection<RetrievalResult> bestMatch = null;

        if (deveRealizarConsultaCbrTrucoTerceiraCartaEaBaixa(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEaMedia(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEalta(gameState, rodada)) {

            bestMatch = retornaRecuperadosFiltradosTruco(gameState, thresholdReuso, rodada);


            while (bestMatch.size() < kMinimo) {

                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTruco(gameState, novoThreshold, rodada);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                    break;
            }

            retorno = chamarTrucoCluster(gameState, rodada, bestMatch);
            if (rodada == 2) {
                retorno = revisaChamarEaceitarTrucoSegundaCarta(gameState, rodada, retorno);
                retorno = revisaApenasChamarNaSegundaRodada(gameState, retorno);
            }

            // revisão ultima carta boa
            if (rodada == 3) {
                // perdeu a segunda e tem uma carta maior do que a do oponente
                if (gameState.getGanhadorSegundaRodada().equals(2) && gameState.getTerceiraCartaHumano() != null) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && (gameState.getCartaAltaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaAltaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && (gameState.getCartaMediaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaMediaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && (gameState.getCartaBaixaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaBaixaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                }
                // ganhou a segunda e tem uma carta boa para a terceira
                else if (gameState.getGanhadorSegundaRodada().equals(1)) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && gameState.getCartaAltaRobo() >= 40)
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && gameState.getCartaMediaRobo() >= 40)
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && gameState.getCartaBaixaRobo() >= 40)
                        retorno = true;

                }

            }
            // revisa se não ta chamando truco sem chamar os pontos quando é mão para evitar
            // que o oponente perceba que não vai aceitar pontos e ganhe ponto
            if (rodada == 1 && gameState.getJogadorMao().equals(1) && retorno)
                retorno = deveRealizarConsultaTrucoPrimeiraRodadaNaoChamouPontos(gameState, rodada);

            retorno = retorno;
        } else {
            retorno = false;
        }
        if (revisarAtivo)
            System.out.println("[CHAMAR_TRUCO] REUSE_POLICY_MOVE: " + retorno);

        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "JOGADA") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 501, "TRUCO", rodada, novoThreshold);
            System.out.println("[CHAMAR_TRUCO] REVIEWED_MOVE: " + retorno);
        }
        return retorno;
    }

    public boolean realizaRevisaoAtivo(TrucoDescription queryDefault, boolean acaoParaSerFeita, Collection<RetrievalResult> bestMatch,
                                       int tipoConsulta, String move, int rodada, double novoThreshold){
        try {
            int count = 0;
            Collection<CBRCase> apenasCasosDeAtivo = FiltraAprendidosPeloAtivo(bestMatch);
            System.out.println("Ativos Filtrados (" + count + "): " + apenasCasosDeAtivo.size());
            Collection<RetrievalResult> casosRecuperados = getBestResultCluster(apenasCasosDeAtivo, queryDefault, tipoConsulta);

            while (casosRecuperados.size() < 1) {
                count++;
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTruco(queryDefault, novoThreshold, rodada);
                apenasCasosDeAtivo = FiltraAprendidosPeloAtivo(bestMatch);
                System.out.println("Ativos Filtrados (" + count + "): " + apenasCasosDeAtivo.size());
                casosRecuperados = getBestResultCluster(apenasCasosDeAtivo, queryDefault, tipoConsulta);
            }

            TrucoDescription casoMaisSimilar = new TrucoDescription();

            Iterator iterator = casosRecuperados.iterator();
            if (iterator.hasNext()) {
                RetrievalResult r = (RetrievalResult) iterator.next();
                System.out.println("[SIMILARIDADE]-> " + r.getEval() + "[CaseId]-> " + r.get_case().getDescription().getIdAttribute());
                casoMaisSimilar = (cbr.cbrDescriptions.TrucoDescription) r.get_case().getDescription();
                System.out.println(casoMaisSimilar.toString());
            }

            /* TrucoDescription casoMaisSimilar = (cbr.cbrDescriptions.TrucoDescription) casosRecuperados.iterator().next().get_case().getDescription();*/

            return utilizaOcasoMaisSimilarParaDecidir(casoMaisSimilar, move);
        } catch (Exception e){
            return  acaoParaSerFeita;
        }
    }

    public boolean realizaRevisaoAtivoBkp(TrucoDescription queryDefault, boolean acaoParaSerFeita, Collection<RetrievalResult> bestMatch,
                                       int tipoConsulta, String move, int rodada, double novoThreshold){
        try {
            int count = 0;
            Collection<CBRCase> apenasCasosDeAtivo = FiltraAprendidosPeloAtivo(bestMatch);
            System.out.println("Ativos Filtrados (" + count + "): " + apenasCasosDeAtivo.size());
            Collection<RetrievalResult> casosRecuperados = getBestResultCluster(apenasCasosDeAtivo, queryDefault, tipoConsulta);

            while (casosRecuperados.size() < 1) {
                count++;
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTruco(queryDefault, novoThreshold, rodada);
                apenasCasosDeAtivo = FiltraAprendidosPeloAtivo(bestMatch);
                System.out.println("Ativos Filtrados (" + count + "): " + apenasCasosDeAtivo.size());
                casosRecuperados = getBestResultCluster(apenasCasosDeAtivo, queryDefault, tipoConsulta);
            }

            TrucoDescription casoMaisSimilar = new TrucoDescription();

            Iterator iterator = casosRecuperados.iterator();
            if (iterator.hasNext()) {
                RetrievalResult r = (RetrievalResult) iterator.next();
                System.out.println("[SIMILARIDADE]-> " + r.getEval() + "[CaseId]-> " + r.get_case().getDescription().getIdAttribute());
                casoMaisSimilar = (cbr.cbrDescriptions.TrucoDescription) r.get_case().getDescription();
                System.out.println(casoMaisSimilar.toString());
            }

            /* TrucoDescription casoMaisSimilar = (cbr.cbrDescriptions.TrucoDescription) casosRecuperados.iterator().next().get_case().getDescription();*/

            return utilizaOcasoMaisSimilarParaDecidir(casoMaisSimilar, move);
        } catch (Exception e){
            return  acaoParaSerFeita;
        }
    }

    public boolean realizaRevisaoAtivoCarta(TrucoDescription queryDefault, boolean acaoParaSerFeita, Collection<RetrievalResult> bestMatch, int tipoConsulta, String move){

        Collection<CBRCase>  apenasCasosDeAtivo = FiltraAprendidosPeloAtivo(bestMatch);
        Collection<RetrievalResult> casosRecuperados =  getBestResultCluster(apenasCasosDeAtivo, queryDefault, tipoConsulta);

        TrucoDescription casoMaisSimilar = (cbr.cbrDescriptions.TrucoDescription) casosRecuperados.iterator().next().get_case().getDescription();

        boolean retorno = utilizaOcasoMaisSimilarParaDecidir(casoMaisSimilar, move);

        return retorno;
    }

    public boolean utilizaOcasoMaisSimilarParaDecidir(TrucoDescription casoMaisSimilar, String move){
        boolean oQueDeveSerFeito = false;

        switch (move) {
            case "ENVIDO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemPediuEnvido() != null && !casoMaisSimilar.getQuemPediuEnvido().equals(0)
                        && casoMaisSimilar.getQuemPediuEnvido().equals(1));
                break;
            case "REAL_ENVIDO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemPediuRealEnvido() != null && !casoMaisSimilar.getQuemPediuRealEnvido().equals(0)
                        && casoMaisSimilar.getQuemPediuRealEnvido().equals(1));
                break;
            case "FALTA_ENVIDO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemPediuFaltaEnvido() != null && !casoMaisSimilar.getQuemPediuFaltaEnvido().equals(0)
                        && casoMaisSimilar.getQuemPediuFaltaEnvido().equals(1));
                break;
            case "ACEITAR_ENVIDO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemPediuEnvido() != null && !casoMaisSimilar.getQuemPediuEnvido().equals(0)
                        && casoMaisSimilar.getQuemPediuEnvido().equals(2) && (casoMaisSimilar.getQuemNegouEnvido() == null || !casoMaisSimilar.getQuemNegouEnvido().equals(1)));
                break;
            case "ACEITAR_REAL_ENVIDO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemPediuRealEnvido() != null && !casoMaisSimilar.getQuemPediuRealEnvido().equals(0)
                        && casoMaisSimilar.getQuemPediuRealEnvido().equals(2) && (casoMaisSimilar.getQuemNegouEnvido() == null || !casoMaisSimilar.getQuemNegouEnvido().equals(1)));
                break;
            case "ACEITAR_FALTA_ENVIDO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemPediuFaltaEnvido() != null && !casoMaisSimilar.getQuemPediuFaltaEnvido().equals(0)
                        && casoMaisSimilar.getQuemPediuFaltaEnvido().equals(2) && (casoMaisSimilar.getQuemNegouEnvido() == null || !casoMaisSimilar.getQuemNegouEnvido().equals(1)));
                break;
            case "TRUCO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemTruco() != null && !casoMaisSimilar.getQuemTruco().equals(0)
                        && casoMaisSimilar.getQuemTruco().equals(1));
                break;
            case "RETRUCO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemRetruco() != null && !casoMaisSimilar.getQuemRetruco().equals(0)
                        && casoMaisSimilar.getQuemRetruco().equals(1));
                break;
            case "VALE4":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemValeQuatro() != null && !casoMaisSimilar.getQuemValeQuatro().equals(0)
                        && casoMaisSimilar.getQuemValeQuatro().equals(1));
                break;
            case "ACEITAR_TRUCO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemTruco() != null && !casoMaisSimilar.getQuemTruco().equals(0)
                        && casoMaisSimilar.getQuemTruco().equals(2) && (casoMaisSimilar.getQuemNegouTruco() == null || !casoMaisSimilar.getQuemNegouTruco().equals(1)));
                break;
            case "ACEITAR_RETRUCO":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemRetruco() != null && !casoMaisSimilar.getQuemRetruco().equals(0)
                        && casoMaisSimilar.getQuemRetruco().equals(2) && (casoMaisSimilar.getQuemNegouTruco() == null || !casoMaisSimilar.getQuemNegouTruco().equals(1)));
                break;
            case "ACEITAR_VALE4":
                oQueDeveSerFeito = (casoMaisSimilar.getQuemValeQuatro() != null && !casoMaisSimilar.getQuemValeQuatro().equals(0)
                        && casoMaisSimilar.getQuemValeQuatro().equals(2) && (casoMaisSimilar.getQuemNegouTruco() == null || !casoMaisSimilar.getQuemNegouTruco().equals(1)));
                break;
        }

        return oQueDeveSerFeito;
    }

    public boolean deveRevisarDeAcordoComAProbabilidade(TrucoDescription queryDefault, String tipoConsulta){

        boolean deveRevisar = false;
        int isHand = queryDefault.getJogadorMao() == 1 ? 1 : 0;
        double prob = 0.0;

        //Aqui você utiliza a query default para fazer o calculo de probabilidade
        switch (tipoConsulta) {
            case "ENVIDO":
                int pontosEnvido = -1;
                String carta = "";
                if (queryDefault.getPontosEnvidoRobo() != null && !queryDefault.getPontosEnvidoRobo().equals(0)) {
                    pontosEnvido = queryDefault.getPontosEnvidoRobo();
                }

                if (queryDefault.getPrimeiraCartaHumano() != null && queryDefault.getNaipePrimeiraCartaHumano() != null) {
                    carta = getCartaByCodeESuit(queryDefault.getPrimeiraCartaHumano(), queryDefault.getNaipePrimeiraCartaHumano());
                }

                if (pontosEnvido > -1) {
                    if (!carta.equals("")) {
                        prob = 1 - deck.getProbabilidadeMelhorEnvido(isHand, pontosEnvido, carta);
                    } else {
                        prob = 1 - deck.getProbabilidadeMelhorEnvido(isHand, pontosEnvido);
                    }
                }

                break;
            case "JOGADA":
                prob = (1 - getProbabilidadeMaoByEstadoJogo(isHand, queryDefault));
                break;
        }

        System.out.println("[PROBABILIDADE_CBR_MODULAR] --> " + prob);

        deveRevisar = (prob < 0.5 || prob > 0.85);

        return deveRevisar;
    }

    public TrucoDescription retornaQueryAtivo(TrucoDescription queryDefault){
        TrucoDescription queryAtivo = new TrucoDescription();
		/*
		Daniel, aqui você implementa um método para preencher com as tuas coisas o queryAtivo
		 */


        return queryAtivo;
    }
    private boolean revisaApenasChamarNaSegundaRodada(TrucoDescription gameState, boolean retorno) {
        // revisar apenas chamar na segunda rodada
        // ganhou a primeira jogando a alta e a outra carta é menor do que um dois
        if (retorno == true && gameState.getGanhadorPrimeiraRodada().equals(1)
                && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                && gameState.getCartaMediaRobo() < 16)
            retorno = false;
            // ganhou a primeira jogando a alta e a outra carta é maior do que um dois
        else if (retorno == false && gameState.getGanhadorPrimeiraRodada().equals(1)
                && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                && gameState.getCartaMediaRobo() > 16)
            retorno = true;

        // ganhou a primeira jogando a média e a outra carta é menor do que um dois
        if (retorno == true && gameState.getGanhadorPrimeiraRodada().equals(1)
                && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                && gameState.getCartaAltaRobo() < 16)
            retorno = false;
            // ganhou a primeira jogando a alta e a outra carta é maior do que um dois
        else if (retorno == false && gameState.getGanhadorPrimeiraRodada().equals(1)
                && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                && gameState.getCartaAltaRobo() > 16)
            retorno = true;

        // perdeu a primeira jogando a alta e a outra carta é menor do que um dois
        if (retorno == true && gameState.getGanhadorPrimeiraRodada().equals(2)
                && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                && gameState.getCartaMediaRobo() < 16)
            retorno = false;

        // perdeu a primeira jogando a média e a outra carta é menor do que um dois
        if (retorno == true && gameState.getGanhadorPrimeiraRodada().equals(2)
                && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                && gameState.getCartaAltaRobo() < 16)
            retorno = false;

        return retorno;
    }

    private boolean revisaChamarEaceitarTrucoSegundaCarta(TrucoDescription gameState, int rodada, boolean retorno) {
        // revisão segunda carta
        if (rodada == 2) {
            // perdeu a primeira e tem uma carta ruim para a segunda
            if (gameState.getGanhadorPrimeiraRodada().equals(2) && gameState.getSegundaCartaHumano() != null) {
                if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && (gameState.getCartaAltaRobo() < gameState.getSegundaCartaHumano()))
                    retorno = false;
                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                        && (gameState.getCartaMediaRobo() < gameState.getSegundaCartaHumano()))
                    retorno = false;

                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                        && (gameState.getCartaBaixaRobo() < gameState.getSegundaCartaHumano()))
                    retorno = false;

            }
            // ganhou a primeira e tem uma carta boa para a segunda ou terceira
            else if (gameState.getGanhadorPrimeiraRodada().equals(1)) {
                if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && gameState.getCartaAltaRobo() >= 40)
                    retorno = true;
                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                        && gameState.getCartaMediaRobo() >= 40)
                    retorno = true;

                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                        && gameState.getCartaBaixaRobo() >= 40)
                    retorno = true;
            }
            // empardou a primeira e é pé
            if (gameState.getGanhadorPrimeiraRodada().equals(0) && gameState.getSegundaCartaHumano() != null) {
                if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && (gameState.getCartaAltaRobo() < gameState.getSegundaCartaHumano()))
                    retorno = false;
                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && (gameState.getCartaAltaRobo() > gameState.getSegundaCartaHumano()))
                    retorno = true;
                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                        && (gameState.getCartaMediaRobo() < gameState.getSegundaCartaHumano()))
                    retorno = false;
                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                        && (gameState.getCartaMediaRobo() > gameState.getSegundaCartaHumano()))
                    retorno = true;

                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                        && (gameState.getCartaBaixaRobo() < gameState.getSegundaCartaHumano()))
                    retorno = false;

                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                        && (gameState.getCartaBaixaRobo() > gameState.getSegundaCartaHumano()))
                    retorno = true;

            }
            // empardou a primeira e é mão
            if (gameState.getGanhadorPrimeiraRodada().equals(0) && gameState.getSegundaCartaHumano() == null) {
                if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && (gameState.getCartaAltaRobo() > 40))
                    retorno = true;
                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && (gameState.getCartaAltaRobo() < 6))
                    retorno = false;

                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                        && (gameState.getCartaMediaRobo() > 40))
                    retorno = true;
                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                        && (gameState.getCartaMediaRobo() < 6))
                    retorno = false;

                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                        && (gameState.getCartaBaixaRobo() < 6))
                    retorno = false;

                else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                        && (gameState.getCartaBaixaRobo() > 40))
                    retorno = true;

            }

        }
        return retorno;
    }

    public boolean chamarTrucoCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best) {

        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashQuemTruco = hashTruco.retornaHashQuemChamouTruco(tc, rodada);


        return decisionTruco.chamarTruco(gameState, rodada, best, hashQuemTruco);

    }

    public boolean chamarReTruco(TrucoDescription gameState, int rodada) {

        boolean retorno = false;
        double novoThreshold = thresholdReuso;
        Collection<RetrievalResult> bestMatch = null;


        if (deveRealizarConsultaCbrTrucoTerceiraCartaEaBaixa(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEaMedia(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEalta(gameState, rodada)) {

            bestMatch = retornaRecuperadosFiltradosTruco(gameState, thresholdReuso, rodada);

            while (bestMatch.size() < kMinimo) {

                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTruco(gameState, novoThreshold, rodada);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                    break;
            }

            retorno = chamarRetrucoCluster(gameState, rodada, bestMatch);
            if (rodada == 2) {
                retorno = revisaChamarEaceitarTrucoSegundaCarta(gameState, rodada, retorno);
                retorno = revisaApenasChamarNaSegundaRodada(gameState, retorno);
            }
            // revisão ultima carta boa
            if (rodada == 3) {
                // perdeu a segunda e tem uma carta maior do que a do oponente
                if (gameState.getGanhadorSegundaRodada().equals(2) && gameState.getTerceiraCartaHumano() != null) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && (gameState.getCartaAltaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaAltaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && (gameState.getCartaMediaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaMediaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && (gameState.getCartaBaixaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaBaixaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                }
                // ganhou a segunda e tem uma carta boa para a terceira
                else if (gameState.getGanhadorSegundaRodada().equals(1)) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && gameState.getCartaAltaRobo() >= 40)
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && gameState.getCartaMediaRobo() >= 40)
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && gameState.getCartaBaixaRobo() >= 40)
                        retorno = true;

                }

            }

            retorno = retorno;

        } else
            retorno = false;
        if (revisarAtivo)
            System.out.println("[CHAMAR_RETRUCO] REUSE_POLICY_MOVE: " + retorno);
        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "JOGADA") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 501, "RETRUCO", rodada, novoThreshold);
            System.out.println("[CHAMAR_RETRUCO] REVIEWED_MOVE: " + retorno);
        }
        return retorno;

    }

    public boolean chamarRetrucoCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best) {
        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashQuemTruco = hashTruco.retornaHashQuemChamouReTruco(tc, rodada);

        return decisionTruco.chamarRetruco(gameState, rodada, best, hashQuemTruco);

    }

    public boolean chamarValeQuatro(TrucoDescription gameState, int rodada) {

        boolean retorno = false;
        double novoThreshold = thresholdReuso;
        Collection<RetrievalResult> bestMatch = null;

        if (deveRealizarConsultaCbrTrucoTerceiraCartaEaBaixa(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEaMedia(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEalta(gameState, rodada)) {

            bestMatch = retornaRecuperadosFiltradosTruco(gameState, thresholdReuso, rodada);

            while (bestMatch.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTruco(gameState, novoThreshold, rodada);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                    break;
            }

            retorno = chamarValeQuatroCluster(gameState, rodada, bestMatch);
            if (rodada == 2) {
                retorno = revisaChamarEaceitarTrucoSegundaCarta(gameState, rodada, retorno);
                retorno = revisaApenasChamarNaSegundaRodada(gameState, retorno);
            }
            // revisão ultima carta boa
            if (rodada == 3) {
                // perdeu a segunda e tem uma carta maior do que a do oponente
                if (gameState.getGanhadorSegundaRodada().equals(2) && gameState.getTerceiraCartaHumano() != null) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && (gameState.getCartaAltaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaAltaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && (gameState.getCartaMediaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaMediaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && (gameState.getCartaBaixaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaBaixaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                }
                // ganhou a segunda e tem uma carta boa para a terceira
                else if (gameState.getGanhadorSegundaRodada().equals(1)) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && gameState.getCartaAltaRobo() >= 40)
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && gameState.getCartaMediaRobo() >= 40)
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && gameState.getCartaBaixaRobo() >= 40)
                        retorno = true;

                }

            }

            retorno = retorno;

        } else
            retorno = false;
        if (revisarAtivo)
            System.out.println("[CHAMAR_VALE4] REUSE_POLICY_MOVE: " + retorno);
        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "JOGADA") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 501, "VALE4", rodada, novoThreshold);
            System.out.println("[CHAMAR_VALE4] REVIEWED_MOVE: " + retorno);
        }
        return retorno;
    }

    public boolean chamarValeQuatroCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best) {

        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashQuemTruco = hashTruco.retornaHashQuemChamouValeQuatro(tc, rodada);

        return decisionTruco.chamarValeQuatro(gameState, rodada, best, hashQuemTruco);

    }

    public boolean deveRealizarConsultaTrucoPrimeiraRodadaNaoChamouPontos(TrucoDescription gameState, int rodada) {
        if (rodada == 1 && gameState.getJogadorMao().equals(1) && (gameState.getQuemPediuEnvido() != null
                || gameState.getQuemPediuRealEnvido() != null || gameState.getQuemPediuFaltaEnvido() != null))
            return true;
        else
            return false;

    }

    public boolean deveRealizarConsultaCbrTrucoTerceiraCartaEaBaixa(TrucoDescription gameState, int rodada) {
        if (rodada == 3 && (gameState.getCartaBaixaRobo() < 6
                && (!gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                && !gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo()))))
            return false;
        else
            return true;

    }

    public boolean deveRealizarConsultaCbrTrucoTerceiraCartaEaMedia(TrucoDescription gameState, int rodada) {
        if (rodada == 3 && (gameState.getCartaMediaRobo() < 6
                && (!gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                && !gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo()))))
            return false;
        else
            return true;

    }

    public boolean deveRealizarConsultaCbrTrucoTerceiraCartaEalta(TrucoDescription gameState, int rodada) {
        if (rodada == 3 && (gameState.getCartaAltaRobo() < 6
                && (!gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                && !gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo()))))
            return false;
        else
            return true;

    }

    public boolean aceitarTruco(TrucoDescription gameState, int rodada) {

        boolean retorno = false;
        double novoThreshold = thresholdReuso;
        Collection<RetrievalResult> bestMatch = null;

        /*
         * na terceira rodada, caso a terceira carta seja baixa não faz sentido fazer
         * uma consulta Cbr se deve aceitar, primeiro que pelo alto custo computacional,
         * segundo porque o sistema CBR pode ser acionado para questões de acordo com o
         * definido pelo especialista e terceiro que por não possuirmos ainda uma base
         * que represente todas as possibilidades de combinações de cartas 527 não
         * podemos utilizar equal para as cartas, o que faz muitas vezes que os caso
         * recuperados outliers. Não faz sentido fazer consulta CBR nessas situações,
         * principalmente por não modelarmos oponente. Não é viavel ter esse custo já
         * que por padrão ultima carta baixa é só se aceita em blefe
         */
        if (deveRealizarConsultaCbrTrucoTerceiraCartaEaBaixa(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEaMedia(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEalta(gameState, rodada)) {

            // //System.out.println("entrou no aceitar truco");
            bestMatch = retornaRecuperadosFiltradosTruco(gameState, thresholdReuso, rodada);

            while (bestMatch.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTruco(gameState, novoThreshold, rodada);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                    break;
            }

            retorno = aceitarTrucoCluster(gameState, rodada, bestMatch);
            if (rodada == 2)
                retorno = revisaChamarEaceitarTrucoSegundaCarta(gameState, rodada, retorno);
            // revisão ultima carta boa
            if (rodada == 3) {
                // perdeu a segunda e tem uma carta maior do que a do oponente
                if (gameState.getGanhadorSegundaRodada().equals(2) && gameState.getTerceiraCartaHumano() != null) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && (gameState.getCartaAltaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaAltaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && (gameState.getCartaMediaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaMediaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && (gameState.getCartaBaixaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaBaixaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                }
                // ganhou a segunda e tem uma carta boa para a terceira
                else if (gameState.getGanhadorSegundaRodada().equals(1)) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && gameState.getCartaAltaRobo() >= 24)
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && gameState.getCartaMediaRobo() >= 24)
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && gameState.getCartaBaixaRobo() >= 24)
                        retorno = true;

                }

            }

            retorno = retorno;

        } else {

            retorno = false;
        }
        if (revisarAtivo)
            System.out.println("[ACEITAR_TRUCO] REUSE_POLICY_MOVE: " + retorno);

        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "JOGADA") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 501, "ACEITAR_TRUCO", rodada, novoThreshold);
            System.out.println("[ACEITAR_TRUCO] REVIEWED_MOVE: " + retorno);
        }
        return retorno;
    }

    public boolean aceitarTrucoCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best) {
        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashQuemTruco = hashTruco.retornaHashAceitarTruco(tc, rodada);

        return decisionTruco.aceitarTruco(gameState, rodada, best, hashQuemTruco);

    }

    public boolean aceitarReTruco(TrucoDescription gameState, int rodada) {

        boolean retorno = false;
        double novoThreshold = thresholdReuso;
        Collection<RetrievalResult> bestMatch = null;

        if (deveRealizarConsultaCbrTrucoTerceiraCartaEaBaixa(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEaMedia(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEalta(gameState, rodada)) {
            bestMatch = retornaRecuperadosFiltradosTruco(gameState, thresholdReuso, rodada);

            while (bestMatch.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTruco(gameState, novoThreshold, rodada);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1 && bestMatch.size() >= 1)
                    break;
            }
            retorno = false;
            if (contadorRetrucoEvaleQuatroNegados.getQuantidadeRetrucoNegado() >= 1
                    && (gameState.getCartaAltaRobo() >= 16 && gameState.getCartaMediaRobo() >= 6)) {
                // zera o contador do real envido
                contadorRetrucoEvaleQuatroNegados.setQuantidadeRetrucoNegado(0);
                retorno = aceitarTrucoCluster(gameState, rodada, bestMatch);
            } else {
                retorno = aceitarRetrucoCluster(gameState, rodada, bestMatch);
            }
            if (rodada == 2)
                retorno = revisaChamarEaceitarTrucoSegundaCarta(gameState, rodada, retorno);
            // revisão ultima carta boa
            if (rodada == 3) {
                // perdeu a segunda e tem uma carta maior do que a do oponente
                if (gameState.getGanhadorSegundaRodada().equals(2) && gameState.getTerceiraCartaHumano() != null) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && (gameState.getCartaAltaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaAltaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && (gameState.getCartaMediaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaMediaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && (gameState.getCartaBaixaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaBaixaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                }
                // ganhou a segunda e tem uma carta boa para a terceira
                else if (gameState.getGanhadorSegundaRodada().equals(1)) {
                    System.out.println("entrou no if para ver se a carta está correta");
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && gameState.getCartaAltaRobo() >= 24)
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && gameState.getCartaMediaRobo() >= 24)
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && gameState.getCartaBaixaRobo() >= 24)
                        retorno = true;

                }

            }

            if (retorno == false)
                contadorRetrucoEvaleQuatroNegados
                        .setQuantidadeRetrucoNegado(contadorRetrucoEvaleQuatroNegados.getQuantidadeRetrucoNegado() + 1);
            retorno = retorno;

        } else
            retorno = false;
        if (revisarAtivo)
            System.out.println("[ACEITAR_RETRUCO] REUSE_POLICY_MOVE: " + retorno);
        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "JOGADA") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 501, "ACEITAR_RETRUCO", rodada, novoThreshold);
            System.out.println("[ACEITAR_RETRUCO] REVIEWED_MOVE: " + retorno);
        }
        return retorno;

    }

    public boolean aceitarRetrucoCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best) {
        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashQuemTruco = hashTruco.retornaHashAceitarRetruco(tc, rodada);

        return decisionTruco.aceitarRetruco(gameState, rodada, best, hashQuemTruco);

    }

    public boolean aceitarValeQuatro(TrucoDescription gameState, int rodada) {

        boolean retorno = false;
        double novoThreshold = thresholdReuso;
        Collection<RetrievalResult> bestMatch = null;

        if (deveRealizarConsultaCbrTrucoTerceiraCartaEaBaixa(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEaMedia(gameState, rodada)
                && deveRealizarConsultaCbrTrucoTerceiraCartaEalta(gameState, rodada)) {

            bestMatch = retornaRecuperadosFiltradosTruco(gameState, thresholdReuso, rodada);

            while (bestMatch.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTruco(gameState, novoThreshold, rodada);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                    break;
            }
            retorno = false;
            if (contadorRetrucoEvaleQuatroNegados.getQuantidadeValeQuatroNegado() >= 1
                    && (gameState.getCartaAltaRobo() >= 16 && gameState.getCartaMediaRobo() >= 8)) {
                // zera o contador do real envido
                contadorRetrucoEvaleQuatroNegados.setQuantidadeValeQuatroNegado(0);
                retorno = aceitarTrucoCluster(gameState, rodada, bestMatch);
            } else {
                retorno = aceitarValeQuatroCluster(gameState, rodada, bestMatch);
            }
            if (rodada == 2)
                retorno = revisaChamarEaceitarTrucoSegundaCarta(gameState, rodada, retorno);
            // revisão ultima carta boa
            if (rodada == 3) {
                // perdeu a segunda e tem uma carta maior do que a do oponente
                if (gameState.getGanhadorSegundaRodada().equals(2) && gameState.getTerceiraCartaHumano() != null) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && (gameState.getCartaAltaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaAltaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && (gameState.getCartaMediaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaMediaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && (gameState.getCartaBaixaRobo() > gameState.getTerceiraCartaHumano()
                            || (gameState.getCartaBaixaRobo().equals(gameState.getTerceiraCartaHumano())
                            && gameState.getGanhadorPrimeiraRodada().equals(1))))
                        retorno = true;

                }
                // ganhou a segunda e tem uma carta boa para a terceira
                else if (gameState.getGanhadorSegundaRodada().equals(1)) {
                    if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaAltaRobo())
                            && gameState.getCartaAltaRobo() >= 24)
                        retorno = true;
                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaMediaRobo())
                            && gameState.getCartaMediaRobo() >= 24)
                        retorno = true;

                    else if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && !gameState.getSegundaCartaRobo().equals(gameState.getCartaBaixaRobo())
                            && gameState.getCartaBaixaRobo() >= 24)
                        retorno = true;

                }

            }
            if (retorno == false)
                contadorRetrucoEvaleQuatroNegados.setQuantidadeValeQuatroNegado(
                        contadorRetrucoEvaleQuatroNegados.getQuantidadeValeQuatroNegado() + 1);
            retorno = retorno;

        } else
            retorno = false;
        if (revisarAtivo)
            System.out.println("[ACEITAR_VALE4] REUSE_POLICY_MOVE: " + retorno);
        //valida se o retorno está correto
        if(deveRevisarDeAcordoComAProbabilidade(gameState, "JOGADA") && revisarAtivo){

            retorno = realizaRevisaoAtivo(gameState, retorno, bestMatch, 501, "ACEITAR_VALE4", rodada, novoThreshold);
            System.out.println("[ACEITAR_VALE4] REVIEWED_MOVE: " + retorno);
        }
        return retorno;

    }

    public boolean aceitarValeQuatroCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best) {
        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);

        hashQuemTruco = hashTruco.retornaHashAceitarValeQuatro(tc, rodada);

        return decisionTruco.aceitarValeQuatro(gameState, rodada, best, hashQuemTruco);

    }

    public Collection<RetrievalResult> recuperaCasosContraFlor(TrucoDescription gameState, double threshold) {
        if (casosUteisFLorJaIndexado == null || indexacaoEnvido == 0)
            setaGrupoMaisSimilarIndexadoPontos(gameState);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisFLorJaIndexado, gameState, contraflor);
        Collection<RetrievalResult> best = FiltraResultsFlor(bestMatch, threshold);

        return best;

    }

    public boolean aceitarContraFlor(TrucoDescription gameState, int rodada) {
        if (casosUteisFLorJaIndexado == null)
            setaGrupoMaisSimilarIndexadoPontos(gameState);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisFLorJaIndexado, gameState, contraflor);

        // System.out.println("Aceitar Contra Flor");
        return aceitarContraFlorRestoRobo(gameState, bestMatch);

    }

    public boolean aceitarContraFlorResto(TrucoDescription gameState, int rodada) {
        if (casosUteisFLorJaIndexado == null)
            setaGrupoMaisSimilarIndexadoPontos(gameState);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisFLorJaIndexado, gameState, contraflor);

        return aceitarContraFlorRestoRobo(gameState, bestMatch);
    }

    public boolean aceitarContraFlorRestoRobo(TrucoDescription gamestate, Collection<RetrievalResult> best) {
        int Ganhou = 0;
        int Perdeu = 0;
        for (RetrievalResult R : best) {
            try {
                TrucoDescription Caso = (TrucoDescription) R.get_case().getDescription();
                if (Caso.getQuemContraFlorResto() == ROBO)
                    Ganhou++;
                else
                    Perdeu++;
            } catch (Exception e) {
                Perdeu++;
            }
        }
        return selecaoJogadaVitoria(Ganhou, Perdeu);
    }

    public boolean chamarContraFlor(TrucoDescription gameState, int rodada) {
        if (casosUteisFLorJaIndexado == null)
            setaGrupoMaisSimilarIndexadoPontos(gameState);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisFLorJaIndexado, gameState, contraflor);
        // System.out.println("Entrou no chamar contra Flor");
        return chamarContraFlorRobo(gameState, bestMatch);
    }

    public boolean chamarContraFlorRobo(TrucoDescription gamestate, Collection<RetrievalResult> best) {
        int Ganhou = 0;
        int Perdeu = 0;
        for (RetrievalResult R : best) {
            try {
                TrucoDescription Caso = (TrucoDescription) R.get_case().getDescription();
                if (Caso.getQuemContraFlor() == ROBO)
                    Ganhou++;
                else
                    Perdeu++;
            } catch (Exception e) {
                Perdeu++;
            }
        }
        return selecaoJogadaVitoria(Ganhou, Perdeu);
    }

    public boolean chamarContraFlorResto(TrucoDescription gameState, int rodada) {
        if (casosUteisFLorJaIndexado == null)
            setaGrupoMaisSimilarIndexadoPontos(gameState);

        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisFLorJaIndexado, gameState, contraflor);
        return chamarContraFlorRestoRobo(gameState, bestMatch);
    }

    public boolean chamarContraFlorRestoRobo(TrucoDescription gamestate, Collection<RetrievalResult> best) {
        int Ganhou = 0;
        int Perdeu = 0;
        for (RetrievalResult R : best) {
            try {
                TrucoDescription Caso = (TrucoDescription) R.get_case().getDescription();
                if (Caso.getQuemContraFlorResto() == ROBO)
                    Ganhou++;
                else
                    Perdeu++;
            } catch (Exception e) {
                Perdeu++;
            }
        }
        return selecaoJogadaVitoria(Ganhou, Perdeu);
    }

    public boolean irAoBaralho(TrucoDescription gameState, int rodada) {
        Collection<RetrievalResult> bestMatch = null;
        // //System.out.println("rodada ir ao baralho: " + rodada);

        int jogadorMao;
        jogadorMao = gameState.getJogadorMao();
        // retorna por cada um
        if (rodada == 1) {
            bestMatch = retornaRecuperadosFiltradosPrimeiraCarta(gameState, thresholdReuso, jogadorMao);
            double novoThreshold = thresholdReuso;

            while (bestMatch.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosPrimeiraCarta(gameState, novoThreshold, jogadorMao);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                    break;
            }
            // //System.out.println("bestMatch tamanho: " + bestMatch.size());
        } else if (rodada == 2) {
            bestMatch = retornaRecuperadosFiltradosSegundaCartaRetrievalResult(gameState, thresholdReuso);

            double novoThreshold = thresholdReuso;
            while (bestMatch.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosSegundaCartaRetrievalResult(gameState, novoThreshold);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                    break;
            }
        } else if (rodada == 3) {
            bestMatch = retornaRecuperadosFiltradosTerceiraCarta(gameState, thresholdReuso);

            double novoThreshold = thresholdReuso;
            while (bestMatch.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestMatch = retornaRecuperadosFiltradosTerceiraCarta(gameState, novoThreshold);
                if (novoThreshold < 0.85 && bestMatch.size() >= 1)
                    break;
            }

        }
        boolean decisaoRetornada = irAoBaralhoCluster(gameState, rodada, bestMatch);

        // //System.out.println("decisão retornada : " + decisaoRetornada);
        return decisaoRetornada;
    }

    public boolean irAoBaralhoCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best) {

        List<TrucoDescription> tc = ConvertRetrievalResultToList(best);
        int jogadorMao = gameState.getJogadorMao() != null ? gameState.getJogadorMao() : 0;
        int ganhadorPrimeiraRodada = gameState.getGanhadorPrimeiraRodada() != null
                ? gameState.getGanhadorPrimeiraRodada()
                : 0;
        int ganhadorSegundaRodada = gameState.getGanhadorSegundaRodada() != null ? gameState.getGanhadorSegundaRodada()
                : 0;
        boolean decisaoRetornada;
        if (rodada != 3) {
            hashQuemTruco = hashCarta.retornaHashQuemChamouFoiAoBaralho(tc, rodada, jogadorMao, ganhadorPrimeiraRodada,
                    ganhadorSegundaRodada, gameState);
            decisaoRetornada = decisionCarta.irAoBaralho(tc, rodada, jogadorMao, ganhadorPrimeiraRodada,
                    ganhadorSegundaRodada, gameState, hashQuemTruco);

            // terceira carta sempre joga porque pode carregar muitos casos que não foram
            // até a terceira carta e vai forçar o agente ir ao baralho.
        } else {
            decisaoRetornada = false;
        }

        return decisaoRetornada;

    }

    public int primeiraCarta(TrucoDescription gameState, int rodada) {
        // //System.out.println("vai entrar na primeira carta");
        int jogadorMao = gameState.getJogadorMao();
        Collection<RetrievalResult> best = retornaRecuperadosFiltradosPrimeiraCarta(gameState, thresholdReuso,
                jogadorMao);

        double novoThreshold = thresholdReuso;

        while (best.size() < kMinimo) {
            novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
            best = retornaRecuperadosFiltradosPrimeiraCarta(gameState, novoThreshold, jogadorMao);
            if (novoThreshold < 0.85 && best.size() >= 1)
                break;
        }

        // System.out.println("Primeira Carta");
        // //////System.out.println("threshold primeira carta retornada:
        // "+novoThreshold);
        return primeiraCartaCluster(gameState, rodada, best);

    }

    public Collection<RetrievalResult> retornaRecuperadosFiltradosPrimeiraCarta(TrucoDescription gamestate,
                                                                                double threshold, int jogadorMao) {
        if (casosUteisCartaJaIndexado == null || indexacaoJogada == 0)
            setaGrupoMaisSimilarIndexadoJogada(gamestate);
        Collection<RetrievalResult> best = getBestResultCluster(casosUteisCartaJaIndexado, gamestate, cartaCluster);
        Collection<RetrievalResult> bestRoboFiltrado = FiltraResultsClusterPrimeiraCarta(best, threshold, gamestate,
                jogadorMao);
        return bestRoboFiltrado;
    }

    public int primeiraCartaCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> bestOriginal) {
        int retorno = 0;

        List<TrucoDescription> listaCasos = ConvertRetrievalResultToList(bestOriginal);
        ////// //////System.out.println("jogador mão: "+ gameState.getJogadorMao());
        hashDeCasos = hashCarta.retornaHashPrimeiraCarta(listaCasos, gameState.getJogadorMao());

        // aqui fazer um if com as diferentes formas de reuso para retornar a lista do
        // grupo
        List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = new ArrayList<TrucoDescription>();

        listaDeCasosNoGrupoMaisAdequado = decisionCarta.primeiraCarta(gameState, rodada, hashDeCasos);

        String cartaQueDeveSerJogada = decisaoIntraClusterCarta.verificaPrimeiraCartaMaisJogadaNoGrupoMaisProvavel(
                listaDeCasosNoGrupoMaisAdequado, gameState.getJogadorMao());

        if (cartaQueDeveSerJogada.equalsIgnoreCase("alta")) {

            retorno = gameState.getCartaAltaRobo();

        } else if (cartaQueDeveSerJogada.equalsIgnoreCase("baixa")) {
            // ////////////////////////////////System.out.println("jogou a baixa");
            retorno = gameState.getCartaBaixaRobo();
        } else {
            retorno = gameState.getCartaMediaRobo();
        }
        // revisão primeira carta
        if (gameState.getJogadorMao().equals(2) && gameState.getPrimeiraCartaHumano() != null) {
            // consegue fazer e faz com a carta errada
            if (gameState.getCartaAltaRobo().equals(retorno)
                    && gameState.getCartaBaixaRobo() > gameState.getPrimeiraCartaHumano())
                retorno = gameState.getCartaBaixaRobo();
            else if (gameState.getCartaAltaRobo().equals(retorno)
                    && gameState.getCartaMediaRobo() > gameState.getPrimeiraCartaHumano())
                retorno = gameState.getCartaMediaRobo();
            else if (gameState.getCartaMediaRobo().equals(retorno)
                    && gameState.getCartaBaixaRobo() > gameState.getPrimeiraCartaHumano())
                retorno = gameState.getCartaBaixaRobo();
            // não consegue fazer a primeira
            if (gameState.getCartaAltaRobo() < gameState.getPrimeiraCartaHumano())
                retorno = gameState.getCartaBaixaRobo();
            else if (gameState.getCartaMediaRobo().equals(retorno)
                    && gameState.getCartaMediaRobo() < gameState.getPrimeiraCartaHumano())
                retorno = gameState.getCartaBaixaRobo();
            // pode fazer a primeira mas resolve empardar isso só deve ser feito com
            // modelagem de oponente, caso contrario não
            // estou validando só quando emparda com a carta baixa, com as outras ele tem
            // que saber que só deve empardar se estiver muito bem
            if (gameState.getCartaBaixaRobo().equals(retorno)
                    && gameState.getCartaBaixaRobo().equals(gameState.getPrimeiraCartaHumano())
                    && gameState.getCartaMediaRobo() > gameState.getPrimeiraCartaHumano())
                retorno = gameState.getCartaMediaRobo();

        }
        int cartaJogadaConvertida;
        if (cartaQueDeveSerJogada.equalsIgnoreCase("alta"))
            cartaJogadaConvertida = 46;
        if (cartaQueDeveSerJogada.equalsIgnoreCase("baixa"))
            cartaJogadaConvertida = 4;
        else
            cartaJogadaConvertida = 16;

        // aqui seta os atributos de histórico por cartas jogadas
        ControlaClustersAnterioresCartas.setClusterPrimeiraCarta(cartaJogadaConvertida);

        return retorno;

    }

    public boolean verificaSeAPrimeiraCartaMaisJogadaFoiViradaNoGrupoMaisProvavel(List<TrucoDescription> jogador) {
        int quantidadeCartaAlta = 0;
        int quantidadeMedia = 0;
        int quantidadeBaixa = 0;
        int quantidadeVirada = 0;
//quando tiver saco ver o threshold correto para não faltar casos pra essa etapa e tirar fora o try catch
        try {
            for (TrucoDescription d : jogador) {
                int cartaAlta = d.getCartaAltaRobo();
                int cartaMedia = d.getCartaMediaRobo();
                int cartaBaixa = d.getCartaBaixaRobo();
                if (d.getPrimeiraCartaRobo() == cartaAlta)
                    quantidadeCartaAlta++;
                else if (d.getPrimeiraCartaRobo() == cartaMedia)
                    quantidadeMedia++;
                else if (d.getPrimeiraCartaRobo() == cartaBaixa)
                    quantidadeBaixa++;
                else if (d.getRoboCartaVirada() == 1)
                    quantidadeVirada++;
            }
            if (quantidadeVirada > quantidadeCartaAlta && quantidadeVirada > quantidadeMedia
                    && quantidadeVirada > quantidadeBaixa) {
                ControlaClustersAnterioresCartas.setClusterPrimeiraCarta(-1);
                return true;

            } else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean verificaSeAsegundaCartaMaisJogadaFoiViradaNoGrupoMaisProvavel(List<TrucoDescription> jogador) {
        int quantidadeCartaAlta = 0;
        int quantidadeMedia = 0;
        int quantidadeBaixa = 0;
        int quantidadeVirada = 0;
        try {
            for (TrucoDescription d : jogador) {
                int cartaAlta = d.getCartaAltaRobo();
                int cartaMedia = d.getCartaMediaRobo();
                int cartaBaixa = d.getCartaBaixaRobo();
                if (d.getSegundaCartaRobo() == cartaAlta)
                    quantidadeCartaAlta++;
                else if (d.getSegundaCartaRobo() == cartaMedia)
                    quantidadeMedia++;
                else if (d.getSegundaCartaRobo() == cartaBaixa)
                    quantidadeBaixa++;
                else if (d.getRoboCartaVirada() == 2)
                    quantidadeVirada++;
            }
            if (quantidadeVirada > quantidadeCartaAlta && quantidadeVirada > quantidadeMedia
                    && quantidadeVirada > quantidadeBaixa)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verificaSeAterceiraCartaMaisJogadaFoiViradaNoGrupoMaisProvavel(List<TrucoDescription> jogador) {
        int quantidadeCartaAlta = 0;
        int quantidadeMedia = 0;
        int quantidadeBaixa = 0;
        int quantidadeVirada = 0;
        try {
            for (TrucoDescription d : jogador) {
                int cartaAlta = d.getCartaAltaRobo();
                int cartaMedia = d.getCartaMediaRobo();
                int cartaBaixa = d.getCartaBaixaRobo();
                if (d.getTerceiraCartaRobo() == cartaAlta)
                    quantidadeCartaAlta++;
                else if (d.getTerceiraCartaRobo() == cartaMedia)
                    quantidadeMedia++;
                else if (d.getTerceiraCartaRobo() == cartaBaixa)
                    quantidadeBaixa++;
                else if (d.getRoboCartaVirada() == 3)
                    quantidadeVirada++;
            }
            if (quantidadeVirada > quantidadeCartaAlta && quantidadeVirada > quantidadeMedia
                    && quantidadeVirada > quantidadeBaixa)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
            // TODO: handle exception
        }
    }

    public int segundaCarta(TrucoDescription gameState, int rodada) {
        // //System.out.println("entrou na segunda carta");
        List<TrucoDescription> listaFiltradaDeAcordoComClusterDaPrimeira = retornaRecuperadosFiltradosSegundaCarta(
                gameState, thresholdReuso);

        double novoThreshold = thresholdReuso;
        while (listaFiltradaDeAcordoComClusterDaPrimeira.size() < kMinimo) {
            novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
            listaFiltradaDeAcordoComClusterDaPrimeira = retornaRecuperadosFiltradosSegundaCarta(gameState,
                    novoThreshold);
            if (novoThreshold < 0.85 && listaFiltradaDeAcordoComClusterDaPrimeira.size() >= 1)
                break;
        }
        // //System.out.println("lista de cartas filtradas de acordo com a primeira na
        // classe cbr modular: "+ listaFiltradaDeAcordoComClusterDaPrimeira);
        // System.out.println("Entrou na Segunda Carta ");
        return segundaCartaCluster(gameState, rodada, listaFiltradaDeAcordoComClusterDaPrimeira);
    }

    public Collection<RetrievalResult> retornaRecuperadosFiltradosSegundaCartaRetrievalResult(
            TrucoDescription gamestate, double threshold) {
        if (casosUteisCartaJaIndexado == null)
            setaGrupoMaisSimilarIndexadoJogada(gamestate);

        Collection<RetrievalResult> best = getBestResultCluster(casosUteisCartaJaIndexado, gamestate, cartaCluster);

        Collection<RetrievalResult> bestRoboFiltrado = FiltraResultsClusterSegundaCarta(best, threshold, gamestate,
                kMinimo);

        return bestRoboFiltrado;
    }

    public List<TrucoDescription> retornaRecuperadosFiltradosSegundaCarta(TrucoDescription gamestate,
                                                                          double threshold) {
        if (casosUteisCartaJaIndexado == null || indexacaoJogada == 0)
            setaGrupoMaisSimilarIndexadoJogada(gamestate);

        Collection<RetrievalResult> best = getBestResultCluster(casosUteisCartaJaIndexado, gamestate, cartaCluster);

        Collection<RetrievalResult> bestRoboFiltrado = FiltraResultsClusterSegundaCarta(best, threshold, gamestate,
                kMinimo);

        List<TrucoDescription> listaCasosAcimadoThreshold = ConvertRetrievalResultToList(bestRoboFiltrado);

        return listaCasosAcimadoThreshold;
    }

    public int segundaCartaCluster(TrucoDescription gameState, int rodada,
                                   List<TrucoDescription> listaFiltradaDeAcordoComClusterDaPrimeira) {
        int cartaParaSerJogada = 0;
        // cartas jogadas
        int primeiraJogada = gameState.getPrimeiraCartaRobo();
        int altaRobo = gameState.getCartaAltaRobo();
        int mediaRobo = gameState.getCartaMediaRobo();
        int baixaRobo = gameState.getCartaBaixaRobo();

        HashMap<Integer, List<TrucoDescription>> hashDeCasos;
        try {
            hashDeCasos = hashCarta.retornaHashSegundaCarta(listaFiltradaDeAcordoComClusterDaPrimeira,
                    gameState.getGanhadorPrimeiraRodada(), gameState);

            List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = new ArrayList<TrucoDescription>();

            listaDeCasosNoGrupoMaisAdequado = decisionCarta
                    .retornaListaDeCasosComMaiorChanceDeVitoriaSegundaCarta(hashDeCasos);


            String cartaQueDeveSerJogada = decisaoIntraClusterCarta.verificaSegundaCartaMaisJogadaNoGrupoMaisProvavel(
                    listaDeCasosNoGrupoMaisAdequado, gameState.getGanhadorPrimeiraRodada(), gameState);

            if (cartaQueDeveSerJogada.equalsIgnoreCase("media") && primeiraJogada != mediaRobo) {

                cartaParaSerJogada = gameState.getCartaMediaRobo();
            } else if (cartaQueDeveSerJogada.equalsIgnoreCase("alta") && primeiraJogada != altaRobo)
                cartaParaSerJogada = gameState.getCartaAltaRobo();
            else if (cartaQueDeveSerJogada.equalsIgnoreCase("baixa") && primeiraJogada != baixaRobo)
                cartaParaSerJogada = gameState.getCartaBaixaRobo();

            //e.printStackTrace();
            if (gameState.getGanhadorPrimeiraRodada().equals(2) && gameState.getSegundaCartaHumano() != null) {
                if (gameState.getCartaAltaRobo().equals(cartaParaSerJogada)
                        && !gameState.getCartaBaixaRobo().equals(primeiraJogada)
                        && gameState.getCartaBaixaRobo() > gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaBaixaRobo();
                else if (gameState.getCartaAltaRobo().equals(cartaParaSerJogada)
                        && !gameState.getCartaMediaRobo().equals(primeiraJogada)
                        && gameState.getCartaMediaRobo() > gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaMediaRobo();
                else if (gameState.getCartaMediaRobo().equals(cartaParaSerJogada)
                        && !gameState.getCartaBaixaRobo().equals(primeiraJogada)
                        && gameState.getCartaBaixaRobo() > gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaBaixaRobo();
            } else if (gameState.getGanhadorPrimeiraRodada().equals(0)) {
                cartaParaSerJogada = gameState.getCartaAltaRobo();
            }

//revisa se perdeu ou empardou a primeira e ta jogando a carta baixa (isso pode acontecer por causa da similaridade interval)
            // o ideal é preencher ainda mais a base e utilizar EQUAL
            // perdeu ou empardou a primeira revisa para jogar a alta e é o primeiro a jogar
            // a segunda carta
            if((gameState.getGanhadorPrimeiraRodada().equals(2) || gameState.getGanhadorPrimeiraRodada().equals(0))  && gameState.getSegundaCartaHumano() != null) {
                if ( (gameState.getGanhadorPrimeiraRodada().equals(2) || gameState.getGanhadorPrimeiraRodada().equals(0))  && gameState.getSegundaCartaHumano() != null
                        && !gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo()) &&
                        gameState.getCartaBaixaRobo() < gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaAltaRobo();
                    // perdeu ou empardou a primeira jogando a alta
                else if ( (gameState.getGanhadorPrimeiraRodada().equals(2)
                        || gameState.getGanhadorPrimeiraRodada().equals(0))
                        && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo() ) && gameState.getCartaBaixaRobo() < gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaMediaRobo();

            } else if  ( (gameState.getGanhadorPrimeiraRodada().equals(0) )  && gameState.getSegundaCartaHumano() == null ){
                if ( (gameState.getGanhadorPrimeiraRodada().equals(2) || gameState.getGanhadorPrimeiraRodada().equals(0))
                        && !gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo()) )
                    cartaParaSerJogada = gameState.getCartaAltaRobo();
                    // perdeu ou empardou a primeira jogando a alta
                else if ( (gameState.getGanhadorPrimeiraRodada().equals(2)
                        || gameState.getGanhadorPrimeiraRodada().equals(0))
                        && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo() ))
                    cartaParaSerJogada = gameState.getCartaMediaRobo();



            }
        }catch (Exception e) {
            //e.printStackTrace();
            if (gameState.getGanhadorPrimeiraRodada().equals(2) && gameState.getSegundaCartaHumano() != null) {
                if (gameState.getCartaAltaRobo().equals(cartaParaSerJogada)
                        && !gameState.getCartaBaixaRobo().equals(primeiraJogada)
                        && gameState.getCartaBaixaRobo() > gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaBaixaRobo();
                else if (gameState.getCartaAltaRobo().equals(cartaParaSerJogada)
                        && !gameState.getCartaMediaRobo().equals(primeiraJogada)
                        && gameState.getCartaMediaRobo() > gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaMediaRobo();
                else if (gameState.getCartaMediaRobo().equals(cartaParaSerJogada)
                        && !gameState.getCartaBaixaRobo().equals(primeiraJogada)
                        && gameState.getCartaBaixaRobo() > gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaBaixaRobo();
            } else if (gameState.getGanhadorPrimeiraRodada().equals(0)) {
                cartaParaSerJogada = gameState.getCartaAltaRobo();
            }

//revisa se perdeu ou empardou a primeira e ta jogando a carta baixa (isso pode acontecer por causa da similaridade interval)
            // o ideal é preencher ainda mais a base e utilizar EQUAL
            // perdeu ou empardou a primeira revisa para jogar a alta e é o primeiro a jogar
            // a segunda carta
            if( (gameState.getGanhadorPrimeiraRodada().equals(2) || gameState.getGanhadorPrimeiraRodada().equals(0))  && gameState.getSegundaCartaHumano() != null) {

                if ( (gameState.getGanhadorPrimeiraRodada().equals(2) || gameState.getGanhadorPrimeiraRodada().equals(0))  && gameState.getSegundaCartaHumano() != null
                        && !gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo()) &&
                        gameState.getCartaBaixaRobo() < gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaAltaRobo();
                    // perdeu ou empardou a primeira jogando a alta
                else if ( (gameState.getGanhadorPrimeiraRodada().equals(2)
                        || gameState.getGanhadorPrimeiraRodada().equals(0))
                        && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo() ) && gameState.getCartaBaixaRobo() < gameState.getSegundaCartaHumano())
                    cartaParaSerJogada = gameState.getCartaMediaRobo();

            }
            //empardou a primeira e vai iniciar jogando a segunda
            else if  ( (gameState.getGanhadorPrimeiraRodada().equals(0) )  && gameState.getSegundaCartaHumano() == null ){
                if (!gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo())
                        && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaMediaRobo()) )
                    cartaParaSerJogada = gameState.getCartaAltaRobo();
                    // perdeu ou empardou a primeira jogando a alta
                else if ( (gameState.getGanhadorPrimeiraRodada().equals(0) && gameState.getSegundaCartaHumano() == null)
                        && gameState.getPrimeiraCartaRobo().equals(gameState.getCartaAltaRobo() ))
                    cartaParaSerJogada = gameState.getCartaMediaRobo();

            }

        }
        //revisão final
        if (cartaParaSerJogada == 0) {
            if (gameState.getGanhadorPrimeiraRodada().equals(1) && primeiraJogada != baixaRobo)
                cartaParaSerJogada = gameState.getCartaBaixaRobo();
            else if (gameState.getGanhadorPrimeiraRodada().equals(1) && primeiraJogada != mediaRobo)
                cartaParaSerJogada = gameState.getCartaMediaRobo();
            else if (gameState.getGanhadorPrimeiraRodada().equals(2) && primeiraJogada != altaRobo)
                cartaParaSerJogada = gameState.getCartaAltaRobo();
            else if (gameState.getGanhadorPrimeiraRodada().equals(2) && primeiraJogada != mediaRobo)
                cartaParaSerJogada = gameState.getCartaMediaRobo();
            else if (primeiraJogada != altaRobo)
                cartaParaSerJogada = gameState.getCartaAltaRobo();
            else if (primeiraJogada != mediaRobo)
                cartaParaSerJogada = gameState.getCartaMediaRobo();
            else
                cartaParaSerJogada = gameState.getCartaBaixaRobo();
        }
        return cartaParaSerJogada;

    }

    public int terceiraCarta(TrucoDescription gameState, int rodada) {
        if (hashIndexacaoGruposJogada.isEmpty())
            hashIndexacaoGruposJogada = retornaHashsDeGruposJogadaPorCasos(_caseBaseMaos,
                    _caseBaseCentroidesGrupoIndexacao);
        return terceiraCartaRobo(gameState, rodada);

    }

    public int terceiraCartaRobo(TrucoDescription gameState, int rodada) {
        Integer primeira = gameState.getPrimeiraCartaRobo();
        Integer segunda = gameState.getSegundaCartaRobo();
        Integer altaRobo = gameState.getCartaAltaRobo();
        Integer mediaRobo = gameState.getCartaMediaRobo();
        Integer baixaRobo = gameState.getCartaBaixaRobo();


        if (!primeira.equals(altaRobo) && !segunda.equals(altaRobo)) {
            return altaRobo;
        } else if (!primeira.equals(mediaRobo) && !segunda.equals(mediaRobo)) {
            return mediaRobo;
        } else {
            return baixaRobo;
        }

        // System.out.println("Entrou terceira Carta");
        //return mediaRobo;
    }

    public boolean cartaVirada(TrucoDescription gameState, int rodada) {

        if (casosUteisCartaJaIndexado == null)
            setaGrupoMaisSimilarIndexadoJogada(gameState);
        Collection<RetrievalResult> bestMatch = getBestResultCluster(casosUteisCartaJaIndexado, gameState,
                cartaCluster);

        // System.out.println("Carta Virada");
        return cartaViradaCluster(gameState, rodada, bestMatch);
    }

    public boolean cartaViradaCluster(TrucoDescription gameState, int rodada, Collection<RetrievalResult> best) {
        // //System.out.println("entrou na carta virada");
        boolean retorno = false;
        ProbabilidadeChance probabilidade = new ProbabilidadeChance();

        if (rodada == 1) {
            int jogadorMao = gameState.getJogadorMao();
            Collection<RetrievalResult> bestRoboFiltradoPrimeiraCarta = retornaRecuperadosFiltradosPrimeiraCarta(
                    gameState, thresholdReuso, jogadorMao);
            double novoThreshold = thresholdReuso;
            while (bestRoboFiltradoPrimeiraCarta.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                bestRoboFiltradoPrimeiraCarta = retornaRecuperadosFiltradosPrimeiraCarta(gameState, novoThreshold,
                        jogadorMao);
                if (novoThreshold < 0.85 && bestRoboFiltradoPrimeiraCarta.size() >= 1)
                    break;
            }

            if (bestRoboFiltradoPrimeiraCarta.size() > 0) {

                List<TrucoDescription> listaDeCasosFiltrados = ConvertRetrievalResultToList(
                        bestRoboFiltradoPrimeiraCarta);
                hashDeCasos = hashCarta.retornaHashPrimeiraCarta(listaDeCasosFiltrados, gameState.getJogadorMao());

            }

            int idClusterComMaiorChanceDeVitoria = probabilidade.retornaClusterComMaiorChanceDeVitoriaCarta(hashDeCasos,
                    hashDeCasos.keySet());

            List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = new ArrayList<TrucoDescription>();
            listaDeCasosNoGrupoMaisAdequado = hashDeCasos.get(idClusterComMaiorChanceDeVitoria);

            retorno = verificaSeAPrimeiraCartaMaisJogadaFoiViradaNoGrupoMaisProvavel(listaDeCasosNoGrupoMaisAdequado);
        } else if (rodada == 2) {
            // cartas jogadas

            List<TrucoDescription> listaFiltradaDeAcordoComClusterDaPrimeira = retornaRecuperadosFiltradosSegundaCarta(
                    gameState, thresholdReuso);

            double novoThreshold = thresholdReuso;
            while (listaFiltradaDeAcordoComClusterDaPrimeira.size() < kMinimo) {
                novoThreshold = novoThreshold - taxaDeAdaptacaoThreshold;
                listaFiltradaDeAcordoComClusterDaPrimeira = retornaRecuperadosFiltradosSegundaCarta(gameState,
                        novoThreshold);
                if (novoThreshold < 0.85 && listaFiltradaDeAcordoComClusterDaPrimeira.size() >= 1)
                    break;

            }
            HashMap<Integer, List<TrucoDescription>> hashDeCasos;

            hashDeCasos = hashCarta.retornaHashSegundaCarta(listaFiltradaDeAcordoComClusterDaPrimeira,
                    gameState.getGanhadorPrimeiraRodada(), gameState);

            DecisionExtaClusterChanceDeSucessoEsaldoCarta clusterCarta = new DecisionExtaClusterChanceDeSucessoEsaldoCarta();
            int idClusterComMaiorChanceDeVitoria = probabilidade.retornaClusterComMaiorChanceDeVitoriaCarta(hashDeCasos,
                    hashDeCasos.keySet());

            List<TrucoDescription> listaDeCasosNoGrupoMaisAdequado = hashDeCasos.get(idClusterComMaiorChanceDeVitoria);

            retorno = verificaSeAsegundaCartaMaisJogadaFoiViradaNoGrupoMaisProvavel(listaDeCasosNoGrupoMaisAdequado);

        }

        return retorno;

    }

    public double calculoSimilaridade(Collection<RetrievalResult> casos) {
        double media;
        double soma = 0;
        int quant = 0;
        for (RetrievalResult R : casos) {
            soma = soma + R.getEval();
            quant++;
        }
        media = soma / quant;
        return media;
    }

    public void fechaBase() throws ExecutionException {
        // ,//System.out.println("fechou base");

        if (_caseBaseMaos != null)
            _caseBaseMaos.close();
        closeConnection(_connectorMaosBaseline);
        closeConnection(_connectorMaosImitacao);
        closeConnection(_connectorMaosAtivo);

        if (_caseBaseCentroidesGrupoIndexacao != null)
            _caseBaseCentroidesGrupoIndexacao.close();
        closeConnection(_connectorCentroidesGrupoIndexacaoBaseline);
        closeConnection(_connectorCentroidesGrupoIndexacaoImitacao);
        closeConnection(_connectorCentroidesGrupoIndexacaoAtivo);

        if (_caseBaseCentroidesGrupoIndexacaoPontos != null)
            _caseBaseCentroidesGrupoIndexacaoPontos.close();
        closeConnection(_connectorCentroidesGrupoIndexacaoPontosBaseline);
        closeConnection(_connectorCentroidesGrupoIndexacaoPontosImitacao);
        closeConnection(_connectorCentroidesGrupoIndexacaoPontosAtivo);
        // inicia o ck
        if (_caseBaseCentroidePrimeiraCartaRoboMao != null)
            _caseBaseCentroidePrimeiraCartaRoboMao.close();
        closeConnection(_connectorCentroidePrimeiraCartaRoboMaoBaseline);
        closeConnection(_connectorCentroidePrimeiraCartaRoboMaoImitacao);
        closeConnection(_connectorCentroidePrimeiraCartaRoboMaoAtivo);

        if (_caseBaseCentroidePrimeiraCartaRoboPe != null)
            _caseBaseCentroidePrimeiraCartaRoboPe.close();
        closeConnection(_connectorCentroidePrimeiraCartaRoboPeBaseline);
        closeConnection(_connectorCentroidePrimeiraCartaRoboPeImitacao);
        closeConnection(_connectorCentroidePrimeiraCartaRoboPeAtivo);

        if (_caseBaseCentroideSegundaCartaRoboGanhouAprimeira != null)
            _caseBaseCentroideSegundaCartaRoboGanhouAprimeira.close();
        closeConnection(_connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline);
        closeConnection(_connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao);
        closeConnection(_connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo);

        if (_caseBaseCentroideSegundaCartaRoboPerdeuAprimeira != null)
            _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira.close();
        closeConnection(_connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline);
        closeConnection(_connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao);
        closeConnection(_connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo);

        if (_caseBaseCentroideTerceiraCartaRoboGanhouAsegunda != null)
            _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda.close();
        closeConnection(_connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline);
        closeConnection(_connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao);
        closeConnection(_connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo);

        if (_caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda != null)
            _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda.close();
        closeConnection(_connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline);
        closeConnection(_connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao);
        closeConnection(_connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo);

        if (_caseBaseCentroideQuemTrucoPrimeiraMao != null)
            _caseBaseCentroideQuemTrucoPrimeiraMao.close();
        closeConnection(_connectorCentroideQuemTrucoPrimeiraMaoBaseline);
        closeConnection(_connectorCentroideQuemTrucoPrimeiraMaoImitacao);
        closeConnection(_connectorCentroideQuemTrucoPrimeiraMaoAtivo);

        if (_caseBaseCentroideQuemTrucoPrimeiraPe != null)
            _caseBaseCentroideQuemTrucoPrimeiraPe.close();
        closeConnection(_connectorCentroideQuemTrucoPrimeiraPeBaseline);
        closeConnection(_connectorCentroideQuemTrucoPrimeiraPeImitacao);
        closeConnection(_connectorCentroideQuemTrucoPrimeiraPeAtivo);

        if (_caseBaseCentroideQuemTrucoSegundaGanhouAnterior != null)
            _caseBaseCentroideQuemTrucoSegundaGanhouAnterior.close();
        closeConnection(_connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline);
        closeConnection(_connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao);
        closeConnection(_connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo);

        if (_caseBaseCentroideQuemTrucoSegundaPerdeuAnterior != null)
            _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior.close();
        closeConnection(_connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline);
        closeConnection(_connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao);
        closeConnection(_connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo);

        if (_caseBaseCentroideQuemTrucoTerceiraGanhouAnterior != null)
            _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior.close();
        closeConnection(_connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline);
        closeConnection(_connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao);
        closeConnection(_connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo);

        if (_caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior != null)
            _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior.close();
        closeConnection(_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline);
        closeConnection(_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao);
        closeConnection(_connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo);

        if (_caseBaseCentroideQuemGanhouEnvidoAgenteMao != null)
            _caseBaseCentroideQuemGanhouEnvidoAgenteMao.close();
        closeConnection(_connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline);
        closeConnection(_connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao);
        closeConnection(_connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo);

        if (_caseBaseCentroideQuemGanhouEnvidoAgentePe != null)
            _caseBaseCentroideQuemGanhouEnvidoAgentePe.close();
        closeConnection(_connectorCentroideQuemGanhouEnvidoAgentePeBaseline);
        closeConnection(_connectorCentroideQuemGanhouEnvidoAgentePeImitacao);
        closeConnection(_connectorCentroideQuemGanhouEnvidoAgentePeAtivo);

        // autoAjustarK
        //autoAjustarK();

    }

    public void autoAjustarK() {
        // executa os scripts de auto ajuste
        if (ajusteAutomaticoDoK) {
            if (dataBaseConectado.equalsIgnoreCase("imitacao"))
                new ChamaScriptsRnegocio().controlaExecucaoTodosOsScripts("imitacao");
            else if (dataBaseConectado.equalsIgnoreCase("ativo"))
                new ChamaScriptsRnegocio().controlaExecucaoTodosOsScripts("ativo");
            else
                new ChamaScriptsRnegocio().controlaExecucaoTodosOsScripts("default");
        }
    }

    @Override
    public boolean selecaoJogada(int Nao, int SimGanhou, int SimPerdeu) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean selecaoJogadaVitoria(int Ganhou, int Perdeu) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setAprendizagem(String tipo) {
        TipoAprendizagem = tipo;
        // ////System.out.println("tipo aprendizagem: " + tipo);

        if (TipoAprendizagem.equalsIgnoreCase("ativo")) {
            activeLearning = true;
            validaDevePersistir = new ValidaDevePersistirAtivo();
            persistir = new PersistirAtivo();
            validaCriterioDeveAprender = new ValidaCriterioReusoAtivo();

        } else if (TipoAprendizagem.equalsIgnoreCase("imitacao")) {
            // ////System.out.println("entrou na imitacao");
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // alterei aqui para teste alterei para ativo ao inves de valida criterio
            // imitação
            validaDevePersistir = new ValidaDevePersistirImitacao();
            persistir = new PersistirImitacao();

            validaCriterioDeveAprender = new ValidaCriterioReusoImitacao();

        } else {
            activeLearning = false;
        }

    }

    public void setRetencao(String tipo) {
        TipoRetencao = tipo;
    }

    @Override
    public void realizaConfiguracoesIniciais() {
        preencheCaseBase();
        hashIndexacaoGruposJogada = retornaHashsDeGruposJogadaPorCasos(_caseBaseMaos,
                _caseBaseCentroidesGrupoIndexacao);
        hashIndexacaoGruposEnvido = retornaHashsDeGruposEnvidoPorCasos(_caseBaseMaos,
                _caseBaseCentroidesGrupoIndexacaoPontos);

    }

    @Override
    public void realizaConfiguracoesIniciais2(String cartaAlta, String cartaMedia, String cartaBaixa) {

        deck.gerarAllOpponentHandsPossible(cartaAlta, cartaMedia, cartaBaixa);

    }

    @Override
    public void setThreshold(double threshold) {

    }

    @Override
    public double getThreshold() {
        // TODO Auto-generated method stub
        return thresholdReuso;
    }

    @Override
    public Collection<RetrievalResult> retornaRecuperadosFiltradosTerceiraCarta(TrucoDescription gamestate,
                                                                                double threshold) {
        if (casosUteisCartaJaIndexado == null || indexacaoJogada == 0)
            setaGrupoMaisSimilarIndexadoJogada(gamestate);

        Collection<RetrievalResult> best = getBestResultCluster(casosUteisCartaJaIndexado, gamestate, cartaCluster);

        Collection<RetrievalResult> bestRoboFiltrado = FiltraResultsClusterTerceiraCarta(best, threshold, gamestate,
                kMinimo);

        return bestRoboFiltrado;

    }
    /*
     * Métodos auxiliares para aprendizagem
     */

    // retenção
    public void retain(TrucoDescription newCase) {
        if (persistir != null) {
            persistir.persistir(newCase, this);
        }
        // //System.out.println("jogador mão na retenção: "+ newCase.getJogadorMao());
    }

    //tem que vir do controlaRodadaAuto
    //Tipo de ação é envido ou truco apenas essas duas opções

    public boolean deveChamarTelaAtivoEmCadaAcaoIndividual(String tipoAcao, boolean isOportunidadeBlefe, int rodada,
                                                           TrucoDescription queryPadrao, TrucoDescription queryApenasAtributosDeBlefe) {
        boolean retornoThreshold = false;

        //colllections para chamar a tela ou revisar
        Collection<CBRCase> casosAtributosBlefe;

        if (tipoAcao.equalsIgnoreCase("envido")) {

            //Recupera os casos mais similares com threshold flutuante
            Collection<RetrievalResult> casosRecuperadosEnvidoPadrao = retornaRecuperadosFiltradoPontos(queryPadrao,
                    thresholdAprendizagem);

            System.out.println("[ENVIDO] Casos Recuperados Filtrados PADRÃO " + casosRecuperadosEnvidoPadrao.size());


            //Filtra desses casos recuperados somente aqueles que foram aprendidos ativamente
            casosAtributosBlefe = FiltraAprendidosPeloAtivo(casosRecuperadosEnvidoPadrao);

            System.out.println("[ENVIDO] Casos Recuperados Filtrados AL " + casosAtributosBlefe.size());

            //Computa similaridade apenas com os atributos de blefe
            Collection<RetrievalResult> casosRecuperadosEnvidoBlefe = getBestResultCluster(casosAtributosBlefe, queryApenasAtributosDeBlefe, active);

            //Recupera as situações de blefes mais similares de acordo com o threshold Ativo
            Collection<RetrievalResult> filtradosBlefe = filtraResultsBlefe(casosRecuperadosEnvidoBlefe, thresholdAprendizadoAtivo);

            System.out.println("[ENVIDO] Casos Filtrados Blefe " + filtradosBlefe.size());

            retornoThreshold = validaCriterioDeveAprender.aprenderAtivoOuAleatorio(filtradosBlefe);

        } else if (tipoAcao.equalsIgnoreCase("truco") || tipoAcao.equalsIgnoreCase("carta") ) {

            if (rodada == 1) {
                //Recupera os casos mais similares com threshold flutuante
                Collection<RetrievalResult> casosRecuperadosTrucoPrimeiraRodadaPadrao = retornaRecuperadosFiltradosTruco(queryPadrao,
                        thresholdAprendizagem, 1);

                System.out.println("[TRUCO/CARTA] Casos Recuperados Filtrados PADRÃO " + casosRecuperadosTrucoPrimeiraRodadaPadrao.size());

                //Filtra desses casos recuperados somente aqueles que foram aprendidos ativamente
                casosAtributosBlefe = FiltraAprendidosPeloAtivo(casosRecuperadosTrucoPrimeiraRodadaPadrao);

                System.out.println("[TRUCO/CARTA 1] Casos Recuperados Filtrados AL " + casosAtributosBlefe.size());

                //Computa similaridade apenas com os atributos de blefe
                Collection<RetrievalResult> casosRecuperadosTrucoPrimeiraRodadaBlefe = getBestResultCluster(casosAtributosBlefe, queryApenasAtributosDeBlefe,
                        active);


                //Recupera as situações de blefes mais similares de acordo com o threshold Ativo
                Collection<RetrievalResult> filtradosBlefe = filtraResultsBlefe(casosRecuperadosTrucoPrimeiraRodadaBlefe, thresholdAprendizadoAtivo);

                System.out.println("[TRUCO/CARTA 1] Casos Filtrados Blefe " + filtradosBlefe.size());

                //aqui coloca a validação só para essa etapa e seta
                retornoThreshold = validaCriterioDeveAprender.aprenderAtivoOuAleatorio(filtradosBlefe);

            }
            if (rodada == 2) {
                //Recupera os casos mais similares com threshold flutuante
                Collection<RetrievalResult> casosRecuperadosTrucoSegundaRodadaPadrao = retornaRecuperadosFiltradosTruco(queryPadrao,
                        thresholdAprendizagem, 2);

                System.out.println("[TRUCO/CARTA] Casos Recuperados Filtrados PADRÃO " + casosRecuperadosTrucoSegundaRodadaPadrao.size());

                //Filtra desses casos recuperados somente aqueles que foram aprendidos ativamente
                casosAtributosBlefe = FiltraAprendidosPeloAtivo(casosRecuperadosTrucoSegundaRodadaPadrao);

                System.out.println("[TRUCO/CARTA 2] Casos Recuperados Filtrados AL " + casosAtributosBlefe.size());

                //Computa similaridade apenas com os atributos de blefe
                Collection<RetrievalResult> casosRecuperadosTrucoSegundaRodadaBlefe = getBestResultCluster(casosAtributosBlefe, queryApenasAtributosDeBlefe,
                        active);

                //Recupera as situações de blefes mais similares de acordo com o threshold Ativo
                Collection<RetrievalResult> filtradosBlefe = filtraResultsBlefe(casosRecuperadosTrucoSegundaRodadaBlefe, thresholdAprendizadoAtivo);

                System.out.println("[TRUCO/CARTA 2] Casos Filtrados Blefe " + filtradosBlefe.size());

                //aqui coloca a validação só para essa etapa e seta
                retornoThreshold = validaCriterioDeveAprender.aprenderAtivoOuAleatorio(filtradosBlefe);

            }
            if (rodada == 3) {

                //Recupera os casos mais similares com threshold flutuante
                Collection<RetrievalResult> casosRecuperadosTrucoTerceiraRodadaPadrao = retornaRecuperadosFiltradosTruco(queryPadrao,
                        thresholdAprendizagem, 3);

                System.out.println("[TRUCO/CARTA] Casos Recuperados Filtrados PADRÃO " + casosRecuperadosTrucoTerceiraRodadaPadrao.size());

                //Filtra desses casos recuperados somente aqueles que foram aprendidos ativamente
                casosAtributosBlefe = FiltraAprendidosPeloAtivo(casosRecuperadosTrucoTerceiraRodadaPadrao);
                System.out.println("[TRUCO/CARTA 3] Casos Recuperados Filtrados AL " + casosAtributosBlefe.size());

                //Computa similaridade apenas com os atributos de blefe
                Collection<RetrievalResult> casosRecuperadosTrucoTerceiraRodadaBlefe = getBestResultCluster(casosAtributosBlefe, queryApenasAtributosDeBlefe,
                        active);

                //Recupera as situações de blefes mais similares de acordo com o threshold Ativo
                Collection<RetrievalResult> filtradosBlefe = filtraResultsBlefe(casosRecuperadosTrucoTerceiraRodadaBlefe, thresholdAprendizadoAtivo);

                System.out.println("[TRUCO/CARTA 3] Casos Filtrados Blefe " + filtradosBlefe.size());

                //aqui coloca a validação só para essa etapa e seta
                retornoThreshold = validaCriterioDeveAprender.aprenderAtivoOuAleatorio(filtradosBlefe);

            }

        }

        return retornoThreshold && isOportunidadeBlefe;
    }



    public void retain(TrucoDescription newCase, boolean compulsoryRetention) {

        if (persistir != null) {
            persistir.persistir(newCase, this, compulsoryRetention);

        }

    }

    public boolean faltaConhecimentoParaAdecisao(TrucoDescription query, String tipoDaConsulta) {
        /*
         * Consultas Possiveis: truco, envido, primeiracarta, segundacarta, flor
         */
        boolean faltaConhecimento = false;
        if (tipoDaConsulta.equalsIgnoreCase("truco")) {
            Collection<RetrievalResult> casosRecuperadosTrucoPrimeiraRodada = retornaRecuperadosFiltradosTruco(query,
                    thresholdAprendizagem, 1);
            Collection<RetrievalResult> casosRecuperadosTrucoSegundaRodada = retornaRecuperadosFiltradosTruco(query,
                    thresholdAprendizagem, 2);
            Collection<RetrievalResult> casosRecuperadosTrucoTerceiraRodada = retornaRecuperadosFiltradosTruco(query,
                    thresholdAprendizagem, 3);
            boolean faltaConhecimentoPrimeiraRodada = validaCriterioDeveAprender
                    .aprenderAtivoOuAleatorio(casosRecuperadosTrucoPrimeiraRodada);
            boolean faltaConhecimentoSegundaRodada = validaCriterioDeveAprender
                    .aprenderAtivoOuAleatorio(casosRecuperadosTrucoSegundaRodada);
            boolean faltaConhecimentoTerceiraRodada = validaCriterioDeveAprender
                    .aprenderAtivoOuAleatorio(casosRecuperadosTrucoTerceiraRodada);
            faltaConhecimento = faltaConhecimentoPrimeiraRodada || faltaConhecimentoSegundaRodada
                    || faltaConhecimentoTerceiraRodada;
        } else if (tipoDaConsulta.equalsIgnoreCase("envido")) {
            Collection<RetrievalResult> casosRecuperadosEnvido = retornaRecuperadosFiltradoPontos(query,
                    thresholdAprendizagem);
            faltaConhecimento = validaCriterioDeveAprender.aprenderAtivoOuAleatorio(casosRecuperadosEnvido);

        } else if (tipoDaConsulta.equalsIgnoreCase("primeiracarta")) {
            Collection<RetrievalResult> casosRecuperadosPrimeiraCarta = retornaRecuperadosFiltradosPrimeiraCarta(query,
                    thresholdAprendizagem, query.getJogadorMao());
            faltaConhecimento = validaCriterioDeveAprender.aprenderAtivoOuAleatorio(casosRecuperadosPrimeiraCarta);

        } else if (tipoDaConsulta.equalsIgnoreCase("segundacarta")) {
            List<TrucoDescription> casosRecuperadosSegundaCarta = retornaRecuperadosFiltradosSegundaCarta(query,
                    thresholdParaAprender);
            faltaConhecimento = validaCriterioDeveAprender.aprenderAtivoOuAleatorio(casosRecuperadosSegundaCarta);
        } else if (tipoDaConsulta.equalsIgnoreCase("flor")) {
            Collection<RetrievalResult> casosRecuperadosFlor = recuperaCasosContraFlor(query, thresholdAprendizagem);
            faltaConhecimento = validaCriterioDeveAprender.aprenderAtivoOuAleatorio(casosRecuperadosFlor);
        }
        return faltaConhecimento;
    }

    public int retornaCentroideMaisSimilarEnvido(TrucoDescription newCase) {

        CentroidesModelo.AtributosConsultaCentroideQuemGanhouEnvido atributos = new CentroidesModelo.AtributosConsultaCentroideQuemGanhouEnvido();
        atributos.setQuemPediuEnvido(newCase.getQuemPediuEnvido() != null ? newCase.getQuemPediuEnvido() : 0);
        atributos.setQuemPediuRealEnvido(
                newCase.getQuemPediuRealEnvido() != null ? newCase.getQuemPediuRealEnvido() : 0);
        atributos.setQuemPediuFaltaEnvido(
                newCase.getQuemPediuFaltaEnvido() != null ? newCase.getQuemPediuFaltaEnvido() : 0);
        double pontosEnvidoRobo = newCase.getPontosEnvidoRobo();
        atributos.setPontosEnvidoRobo(pontosEnvidoRobo);
        CentroidesQuemGanhouEnvidoDescription centroideEnvido = new ajudaCluster.UtilClusterCentroides()
                .retornaCentroidQuemGanhouEnvidoDescription(atributos);

        // realiza a consulta para recuperar o centroide mais similar
        CBRCentroides ck = new CBRCentroides();
        // esse método agora retorna o centroide mais similar e a similaridade do
        // centroide em relação a consulta em um objeto do tipo resultado modelo
        CentroidesModelo.ResultadoConsultaCentroideQuemGanhouEnvido centroideMaisSimilar = null;
        if (newCase.getJogadorMao() == 1)
            centroideMaisSimilar = (CentroidesModelo.ResultadoConsultaCentroideQuemGanhouEnvido) ck
                    .getBestResultCentroideQuemEnvido(_caseBaseCentroideQuemGanhouEnvidoAgenteMao, centroideEnvido);
        else if (newCase.getJogadorMao() == 2)
            centroideMaisSimilar = (CentroidesModelo.ResultadoConsultaCentroideQuemGanhouEnvido) ck
                    .getBestResultCentroideQuemEnvido(_caseBaseCentroideQuemGanhouEnvidoAgentePe, centroideEnvido);

        return centroideMaisSimilar.getCentroide().getGrupo();
    }

    public TrucoDescription retornaCentroideMaisSimilarQuemTruco(TrucoDescription newCase, int rodada) {
        // atributos
        AtributosConsultaCentroideQuemTrucoModelo atributosConsultaCentroideQuemTrucoModelo = new ajudaCluster.UtilClusterCentroides()
                .retornaAtributosConsultaClusteringQuemTruco(newCase);

        CentroidesQuemTrucoDescription centroideQuemTruco = new ajudaCluster.UtilClusterCentroides()
                .retornaCentroidQuemTrucoDescription(atributosConsultaCentroideQuemTrucoModelo);

        CBRCaseBaseGustavo caseBaseToReturn = null;
        if (rodada == 1) {
            if (newCase.getJogadorMao().equals(1))
                caseBaseToReturn = _caseBaseCentroideQuemTrucoPrimeiraMao;
            else if (newCase.getJogadorMao().equals(2))
                caseBaseToReturn = _caseBaseCentroideQuemTrucoPrimeiraPe;
        } else if (rodada == 2) {
            if (newCase.getGanhadorPrimeiraRodada().equals(1)
                    || (newCase.equals(0) && newCase.getJogadorMao().equals(1)))
                caseBaseToReturn = _caseBaseCentroideQuemTrucoSegundaGanhouAnterior;
            else if (newCase.getGanhadorPrimeiraRodada().equals(2)
                    || (newCase.equals(0) && newCase.getJogadorMao().equals(2)))
                caseBaseToReturn = _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior;
        } else if (rodada == 3) {
            if (newCase.getGanhadorSegundaRodada().equals(1)
                    || ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(1))
                    || (newCase.getGanhadorSegundaRodada().equals(0)
                    && newCase.getGanhadorPrimeiraRodada().equals(0)
                    && newCase.getJogadorMao().equals(1))))
                caseBaseToReturn = _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior;

            else if (newCase.getGanhadorSegundaRodada().equals(2)
                    || ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(2))
                    || (newCase.getGanhadorSegundaRodada().equals(0)
                    && newCase.getGanhadorPrimeiraRodada().equals(0)
                    && newCase.getJogadorMao().equals(2))))
                caseBaseToReturn = _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior;
        }
        ResultadoConsultaCentroideQuemTruco centroideMaisSimilar = (ResultadoConsultaCentroideQuemTruco) ck
                .getBestResultCentroideQuemTruco(caseBaseToReturn, centroideQuemTruco);

        // seta os centroides
        if (rodada == 1) {
            if (newCase.getJogadorMao().equals(1))
                newCase.setClusterQuemTrucoPrimeiraMao(centroideMaisSimilar.getCentroideMaisSimilar().getGrupo());
            else if (newCase.getJogadorMao().equals(2))
                newCase.setClusterQuemTrucoPrimeiraPe(centroideMaisSimilar.getCentroideMaisSimilar().getGrupo());
        } else if (rodada == 2) {
            if (newCase.getGanhadorPrimeiraRodada().equals(1)
                    || (newCase.equals(0) && newCase.getJogadorMao().equals(1)))
                newCase.setClusterQuemTrucoSegundaGanhouAnterior(
                        centroideMaisSimilar.getCentroideMaisSimilar().getGrupo());
            else if (newCase.getGanhadorPrimeiraRodada().equals(2)
                    || (newCase.equals(0) && newCase.getJogadorMao().equals(2)))
                newCase.setClusterQuemTrucoSegundaPerdeuAnterior(
                        centroideMaisSimilar.getCentroideMaisSimilar().getGrupo());
        } else if (rodada == 3) {
            if (newCase.getGanhadorSegundaRodada().equals(1)
                    || ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(1))
                    || (newCase.getGanhadorSegundaRodada().equals(0)
                    && newCase.getGanhadorPrimeiraRodada().equals(0)
                    && newCase.getJogadorMao().equals(1))))
                newCase.setClusterQuemTrucoTerceiraGanhouAnterior(
                        centroideMaisSimilar.getCentroideMaisSimilar().getGrupo());

            else if (newCase.getGanhadorSegundaRodada().equals(2)
                    || ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(2))
                    || (newCase.getGanhadorSegundaRodada().equals(0)
                    && newCase.getGanhadorPrimeiraRodada().equals(0)
                    && newCase.getJogadorMao().equals(2))))
                newCase.setClusterQuemTrucoTerceiraPerdeuAnterior(
                        centroideMaisSimilar.getCentroideMaisSimilar().getGrupo());
        }
        return newCase;
    }

    public TrucoDescription preencheTodosOsGruposMaisSimilaresQuemTruco(TrucoDescription newCase, int rodada) {
        // atributos
        AtributosConsultaCentroideQuemTrucoModelo atributosConsultaCentroideQuemTrucoModelo = new ajudaCluster.UtilClusterCentroides()
                .retornaAtributosConsultaClusteringQuemTruco(newCase);

        CentroidesQuemTrucoDescription centroideQuemTruco = new ajudaCluster.UtilClusterCentroides()
                .retornaCentroidQuemTrucoDescription(atributosConsultaCentroideQuemTrucoModelo);

        CBRCaseBaseGustavo caseBaseToReturnPrimeiraCarta = null;
        CBRCaseBaseGustavo caseBaseToReturnSegundaCarta = null;
        CBRCaseBaseGustavo caseBaseToReturnTerceiraCarta = null;

        if (newCase.getJogadorMao().equals(1))
            caseBaseToReturnPrimeiraCarta = _caseBaseCentroideQuemTrucoPrimeiraMao;
        else if (newCase.getJogadorMao().equals(2))
            caseBaseToReturnPrimeiraCarta = _caseBaseCentroideQuemTrucoPrimeiraPe;

        if (newCase.getGanhadorPrimeiraRodada().equals(1)
                || (newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(1))) {
            // System.out.println("Entrou no if ganhador primeira rodada");
            // System.out.println("Ganhou a primeira! ");
            caseBaseToReturnSegundaCarta = _caseBaseCentroideQuemTrucoSegundaGanhouAnterior;
            // System.out.println("quantidade de casos quemTrucoSegundaGanhouAnterior: "+
            // caseBaseToReturnSegundaCarta.getCases().size());
            // System.out.println("Centroide da consulta: " +
            // centroideQuemTruco.toString());

        } else if (newCase.getGanhadorPrimeiraRodada().equals(2)
                || (newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(2))) {
            // System.out.println("perdeu a primeira");
            caseBaseToReturnSegundaCarta = _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior;
            // System.out.println("quantidade de casos quemTrucoSegundaGanhouAnterior: "+
            // caseBaseToReturnSegundaCarta.getCases().size());
            // System.out.println("Centroide da consulta: " +
            // centroideQuemTruco.toString());

        }
        if (newCase.getGanhadorSegundaRodada().equals(1)
                || ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(1))
                || (newCase.getGanhadorSegundaRodada().equals(0)
                && newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(1))))
            caseBaseToReturnTerceiraCarta = _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior;

        else if (newCase.getGanhadorSegundaRodada().equals(2)
                || ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(2))
                || (newCase.getGanhadorSegundaRodada().equals(0)
                && newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(2))))
            caseBaseToReturnTerceiraCarta = _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior;

        ResultadoConsultaCentroideQuemTruco centroideMaisSimilarPrimeiraCarta = (ResultadoConsultaCentroideQuemTruco) ck
                .getBestResultCentroideQuemTruco(caseBaseToReturnPrimeiraCarta, centroideQuemTruco);

        // System.out.println("Ganhador primeira rodada: " +
        // newCase.getGanhadorPrimeiraRodada());
        // System.out.println("jogador mão: " + newCase.getJogadorMao());
        // System.out.println("Centroide da consulta: " +
        // centroideQuemTruco.toString());
        // System.out.println("Base segunda carta: " + caseBaseToReturnSegundaCarta);

        ResultadoConsultaCentroideQuemTruco centroideMaisSimilarSegundaCarta = (ResultadoConsultaCentroideQuemTruco) ck
                .getBestResultCentroideQuemTruco(caseBaseToReturnSegundaCarta, centroideQuemTruco);

        ResultadoConsultaCentroideQuemTruco centroideMaisSimilarTerceiraCarta = (ResultadoConsultaCentroideQuemTruco) ck
                .getBestResultCentroideQuemTruco(caseBaseToReturnTerceiraCarta, centroideQuemTruco);

        // seta os centroides
        if (newCase.getJogadorMao().equals(1))
            newCase.setClusterQuemTrucoPrimeiraMao(
                    centroideMaisSimilarPrimeiraCarta.getCentroideMaisSimilar().getGrupo());
        else if (newCase.getJogadorMao().equals(2))
            newCase.setClusterQuemTrucoPrimeiraPe(
                    centroideMaisSimilarPrimeiraCarta.getCentroideMaisSimilar().getGrupo());

        if (newCase.getGanhadorPrimeiraRodada().equals(1)
                || (newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(1)))
            newCase.setClusterQuemTrucoSegundaGanhouAnterior(
                    centroideMaisSimilarSegundaCarta.getCentroideMaisSimilar().getGrupo());
        else if (newCase.getGanhadorPrimeiraRodada().equals(2)
                || (newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(2)))
            newCase.setClusterQuemTrucoSegundaPerdeuAnterior(
                    centroideMaisSimilarSegundaCarta.getCentroideMaisSimilar().getGrupo());

        if (newCase.getGanhadorSegundaRodada().equals(1)
                || ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(1))
                || (newCase.getGanhadorSegundaRodada().equals(0)
                && newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(1))))
            newCase.setClusterQuemTrucoTerceiraGanhouAnterior(
                    centroideMaisSimilarTerceiraCarta.getCentroideMaisSimilar().getGrupo());

        else if (newCase.getGanhadorSegundaRodada().equals(2)
                || ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(2))
                || (newCase.getGanhadorSegundaRodada().equals(0)
                && newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(2))))
            newCase.setClusterQuemTrucoTerceiraPerdeuAnterior(
                    centroideMaisSimilarTerceiraCarta.getCentroideMaisSimilar().getGrupo());

        return newCase;
    }

    public int retornaCentroideMaisSimilarPrimeiraCartaRoboMao(TrucoDescription newCase) {
        AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo = new ajudaCluster.UtilClusterCentroides()
                .retornaAtributosConsultaClusteringJogada(newCase);
        CentroidesPrimeiraCartaRoboMaoDescription centroidePrimeiraCartaRoboMao = new UtilClusterCentroides()
                .retornaCentroidPrimeiraCartaRoboMaoDescription(atributosConsultaCentroideJogadaModelo);

        // realiza a consulta para recuperar o centroide mais similar
        CBRCentroides ck = new CBRCentroides();
        // esse método agora retorna o centroide mais similar e a similaridade do
        // centroide em relação a consulta em um objeto do tipo resultado modelo
        ResultadoConsultaCentroidePrimeiraCartaRoboMaoModelo centroideMaisSimilar = (ResultadoConsultaCentroidePrimeiraCartaRoboMaoModelo) ck
                .getBestResultCentroidePrimeiraCartaRoboMao(_caseBaseCentroidePrimeiraCartaRoboMao,
                        centroidePrimeiraCartaRoboMao);
        return centroideMaisSimilar.getCentroideMaisSimilar().getGrupo();
    }

    public int retornaCentroideMaisSimilarPrimeiraCartaRoboPe(TrucoDescription newCase) {
        AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo = new ajudaCluster.UtilClusterCentroides()
                .retornaAtributosConsultaClusteringJogada(newCase);
        CentroidesPrimeiraCartaRoboPeDescription centroidePrimeiraCartaRoboPe = new UtilClusterCentroides()
                .retornaCentroidPrimeiraCartaRoboPeDescription(atributosConsultaCentroideJogadaModelo);

        // realiza a consulta para recuperar o centroide mais similar
        CBRCentroides ck = new CBRCentroides();
        // esse método agora retorna o centroide mais similar e a similaridade do
        // centroide em relação a consulta em um objeto do tipo resultado modelo
        ResultadoConsultaCentroidePrimeiraCartaRoboPe centroideMaisSimilar = (ResultadoConsultaCentroidePrimeiraCartaRoboPe) ck
                .getBestResultCentroidePrimeiraCartaRoboPe(_caseBaseCentroidePrimeiraCartaRoboPe,
                        centroidePrimeiraCartaRoboPe);
        return centroideMaisSimilar.getCentroideMaisSimilar().getGrupo();
    }

    public int retornaCentroideSegundaCartaRoboGanhouAprimeira(TrucoDescription newCase) {
        AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo = new ajudaCluster.UtilClusterCentroides()
                .retornaAtributosConsultaClusteringJogada(newCase);
        CentroidesSegundaCartaRoboGanhouAprimeiraDescription centroideSegundaCartaRoboGanhouAprimeira = new UtilClusterCentroides()
                .retornaCentroidSegundaCartaRoboGanhouAprimeira(atributosConsultaCentroideJogadaModelo);

        // realiza a consulta para recuperar o centroide mais similar
        CBRCentroides ck = new CBRCentroides();

// esse método agora retorna o centroide mais similar e a similaridade do
        // centroide em relação a consulta em um objeto do tipo resultado modelo
        ResultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira centroideMaisSimilar = (ResultadoConsultaCentroideSegundaCartaRoboGanhouAprimeira) ck
                .getBestResultCentroideSegundaCartaRoboGanhouAprimeira(
                        _caseBaseCentroideSegundaCartaRoboGanhouAprimeira, centroideSegundaCartaRoboGanhouAprimeira);

        return centroideMaisSimilar.getCentroideSegundaCartaRoboGanhouAprimeira().getGrupo();
    }

    public int retornaCentroideSegundaCartaRoboPerdeuAprimeira(TrucoDescription newCase) {
        AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo = new ajudaCluster.UtilClusterCentroides()
                .retornaAtributosConsultaClusteringJogada(newCase);
        CentroidesSegundaCartaRoboPerdeuAprimeiraDescription centroideSegundaCartaRoboPerdeuAprimeira = new UtilClusterCentroides()
                .retornaCentroidSegundaCartaRoboPerdeuAprimeira(atributosConsultaCentroideJogadaModelo);

        // realiza a consulta para recuperar o centroide mais similar
        CBRCentroides ck = new CBRCentroides();

// esse método agora retorna o centroide mais similar e a similaridade do
        // centroide em relação a consulta em um objeto do tipo resultado modelo
        ResultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira centroideMaisSimilar = (ResultadoConsultaCentroideSegundaCartaRoboPerdeuAprimeira) ck
                .getBestResultCentroideSegundaCartaRoboPerdeuAprimeira(
                        _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira, centroideSegundaCartaRoboPerdeuAprimeira);

        return centroideMaisSimilar.getCentroideSegundaCartaRoboPerdeuAprimeira().getGrupo();
    }

    public int retornaCentroideTerceiraCartaRoboGanhouAsegunda(TrucoDescription newCase) {
        AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo = new ajudaCluster.UtilClusterCentroides()
                .retornaAtributosConsultaClusteringJogada(newCase);
        CentroidesTerceiraCartaRoboGanhouAsegundaDescription centroidesTerceiraCartaRoboGanhouAsegundaDescription = new UtilClusterCentroides()
                .retornaCentroidTerceiraCartaRoboGanhouASegunda(atributosConsultaCentroideJogadaModelo);

        // realiza a consulta para recuperar o centroide mais similar
        CBRCentroides ck = new CBRCentroides();

// esse método agora retorna o centroide mais similar e a similaridade do
        // centroide em relação a consulta em um objeto do tipo resultado modelo
        ResultadoConsultaTerceiraCartaRoboGanhouAsegunda centroideMaisSimilar = (ResultadoConsultaTerceiraCartaRoboGanhouAsegunda) ck
                .getBestResultCentroideTerceiraCartaRoboGanhouASegunda(
                        _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda,
                        centroidesTerceiraCartaRoboGanhouAsegundaDescription);

        return centroideMaisSimilar.getCentroideTerceiraCartaRoboGanhouAsegunda().getGrupo();
    }

    public int retornaCentroideTerceiraCartaRoboPerdeuAsegunda(TrucoDescription newCase) {
        AtributosConsultaCentroideJogadaModelo atributosConsultaCentroideJogadaModelo = new ajudaCluster.UtilClusterCentroides()
                .retornaAtributosConsultaClusteringJogada(newCase);
        CentroidesTerceiraCartaRoboPerdeuAsegundaDescription centroidesTerceiraCartaRoboPerdeuAsegundaDescription = new UtilClusterCentroides()
                .retornaCentroidTerceiraCartaRoboPerdeuASegunda(atributosConsultaCentroideJogadaModelo);

        // realiza a consulta para recuperar o centroide mais similar
        CBRCentroides ck = new CBRCentroides();

// esse método agora retorna o centroide mais similar e a similaridade do
        // centroide em relação a consulta em um objeto do tipo resultado modelo
        ResultadoTerceiraCartaRoboPerdeuAsegunda centroideMaisSimilar = (ResultadoTerceiraCartaRoboPerdeuAsegunda) ck
                .getBestResultCentroideTerceiraCartaRoboPerdeuASegunda(
                        _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda,
                        centroidesTerceiraCartaRoboPerdeuAsegundaDescription);

        return centroideMaisSimilar.getCentroideMaisSimilar().getGrupo();
    }

    public int retornaGrupoMaisSimilarIndexadoJogada(TrucoDescription newCase) {
        CentroidesGrupoIndexacaoDescription consultaGrupoMaisSimilarIndexacao = new converteTrucoDescriptionParaCentroidesGrupoIndexacaoDescription()
                .converte(newCase);
        CBRQuery query = new CBRQuery();
        query.setDescription(consultaGrupoMaisSimilarIndexacao);

        Collection<RetrievalResult> executeQueryIndexacao;
        CentroidesGrupoIndexacaoDescription cgi = null;

        try {
            executeQueryIndexacao = executeQueryIndexacao(_caseBaseCentroidesGrupoIndexacao, query);
            cgi = (CentroidesGrupoIndexacaoDescription) executeQueryIndexacao.iterator().next().get_case()
                    .getDescription();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cgi.getGrupo();
    }

    public int retornaGrupoMaisSimilarIndexadoPontos(TrucoDescription newCase) {
        CentroidesGrupoIndexacaoPontosDescription descriptionEnvido = new CentroidesGrupoIndexacaoPontosDescription();
        descriptionEnvido.setCentroidepontosenvidorobo(newCase.getPontosEnvidoRobo());

        CBRQuery query = new CBRQuery();
        query.setDescription(descriptionEnvido);

        Collection<RetrievalResult> executeQueryIndexacao;
        CentroidesGrupoIndexacaoPontosDescription cgi = null;

        try {
            executeQueryIndexacao = executeQueryIndexacaoEnvido(_caseBaseCentroidesGrupoIndexacaoPontos, query);
            cgi = (CentroidesGrupoIndexacaoPontosDescription) executeQueryIndexacao.iterator().next().get_case()
                    .getDescription();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cgi.getGrupo();
    }
    // vou tentar esquecer antes para ver se subsistitui

    public void learnCasesIndexacao(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroidesGrupoIndexacao.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * //System.out.println("entrou para deletar imitação");
         * //System.out.println("objeto conector imitação: "+
         * _connectorCentroidesGrupoIndexacaoImitacao.getClass());
         * //System.out.println("quantidade de casos para esquecer: "+esquecerCasos.size
         * ());
         *
         * _connectorCentroidesGrupoIndexacaoImitacao.deleteCases(esquecerCasos);
         *
         *
         * } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroidesGrupoIndexacaoAtivo.deleteCases(esquecerCasos); }
         */
        _caseBaseCentroidesGrupoIndexacao.learnCase(aprenderCaso, "indexacaoJogada");
    }

    public void learnCasesIndexacaoPontos(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroidesGrupoIndexacaoPontos.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroidesGrupoIndexacaoPontosBaseline.deleteCases(esquecerCasos);
         * } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroidesGrupoIndexacaoPontosImitacao.deleteCases(esquecerCasos);
         * } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroidesGrupoIndexacaoPontosAtivo.deleteCases(esquecerCasos); }
         */
        _caseBaseCentroidesGrupoIndexacaoPontos.learnCase(aprenderCaso, "indexacaoPontos");
    }

    public void learnCasesCentroidePrimeiraCartaRoboMao(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroidePrimeiraCartaRoboMao.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroidePrimeiraCartaRoboMaoBaseline.deleteCases(esquecerCasos); }
         * else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroidePrimeiraCartaRoboMaoImitacao.deleteCases(esquecerCasos); }
         * else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroidePrimeiraCartaRoboMaoAtivo.deleteCases(esquecerCasos); }
         */
        _caseBaseCentroidePrimeiraCartaRoboMao.learnCase(aprenderCaso, "PrimeiraCartaRoboMao");
    }

    public void learnCasesCentroidePrimeiraCartaRoboPe(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroidePrimeiraCartaRoboPe.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroidePrimeiraCartaRoboPeBaseline.deleteCases(esquecerCasos); }
         * else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroidePrimeiraCartaRoboPeImitacao.deleteCases(esquecerCasos); }
         * else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroidePrimeiraCartaRoboPeAtivo.deleteCases(esquecerCasos); }
         */
        _caseBaseCentroidePrimeiraCartaRoboPe.learnCase(aprenderCaso, "PrimeiraCartaRoboPe");
    }

    public void learnCasesCentroideSegundaCartaRoboGanhouAprimeira(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideSegundaCartaRoboGanhouAprimeira.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideSegundaCartaRoboGanhouAprimeiraBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideSegundaCartaRoboGanhouAprimeiraImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideSegundaCartaRoboGanhouAprimeiraAtivo.deleteCases(
         * esquecerCasos); }
         */
        _caseBaseCentroideSegundaCartaRoboGanhouAprimeira.learnCase(aprenderCaso, "SegundaCartaRoboGanhouAprimeira");
    }

    public void learnCasesCentroideSegundaCartaRoboPerdeuAprimeira(CBRCase aprenderCaso, CBRCase esquecerCasos) {
        _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira.forgetCase(esquecerCasos);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideSegundaCartaRoboPerdeuAprimeiraBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideSegundaCartaRoboPerdeuAprimeiraImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideSegundaCartaRoboPerdeuAprimeiraAtivo.deleteCases(
         * esquecerCasos); }
         */
        _caseBaseCentroideSegundaCartaRoboPerdeuAprimeira.learnCase(aprenderCaso, "SegundaCartaRoboPerdeuAprimeira");
    }

    public void learnCasesCentroideTerceiraCartaRoboGanhouAsegunda(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideTerceiraCartaRoboGanhouAsegundaBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideTerceiraCartaRoboGanhouAsegundaImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideTerceiraCartaRoboGanhouAsegundaAtivo.deleteCases(
         * esquecerCasos); }
         */
        _caseBaseCentroideTerceiraCartaRoboGanhouAsegunda.learnCase(aprenderCaso, "TerceiraCartaRoboGanhouAsegunda");
    }

    public void learnCasesCentroideTerceiraCartaRoboPerdeuAsegunda(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideTerceiraCartaRoboPerdeuAsegundaBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideTerceiraCartaRoboPerdeuAsegundaImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideTerceiraCartaRoboPerdeuAsegundaAtivo.deleteCases(
         * esquecerCasos); }
         */
        _caseBaseCentroideTerceiraCartaRoboPerdeuAsegunda.learnCase(aprenderCaso, "TerceiraCartaRoboPerdeuAsegunda");
    }

    public void learnCasesCentroideQuemTrucoPrimeiraMao(CBRCase aprenderCaso, CBRCase esquecerCasos) {
        _caseBaseCentroideQuemTrucoPrimeiraMao.forgetCase(esquecerCasos);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideQuemTrucoPrimeiraMaoBaseline.deleteCases(esquecerCasos); }
         * else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideQuemTrucoPrimeiraMaoImitacao.deleteCases(esquecerCasos); }
         * else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideQuemTrucoPrimeiraMaoAtivo.deleteCases(esquecerCasos); }
         */
        _caseBaseCentroideQuemTrucoPrimeiraMao.learnCase(aprenderCaso, "QuemTruco");
    }

    public void learnCasesCentroideQuemTrucoPrimeiraPe(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideQuemTrucoPrimeiraPe.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideQuemTrucoPrimeiraPeBaseline.deleteCases(esquecerCasos); }
         * else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideQuemTrucoPrimeiraPeImitacao.deleteCases(esquecerCasos); }
         * else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideQuemTrucoPrimeiraPeAtivo.deleteCases(esquecerCasos); }
         */
        _caseBaseCentroideQuemTrucoPrimeiraPe.learnCase(aprenderCaso, "QuemTruco");
    }

    public void learnCasesCentroideQuemTrucoSegundaGanhouAnterior(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideQuemTrucoSegundaGanhouAnterior.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideQuemTrucoSegundaGanhouAnteriorBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideQuemTrucoSegundaGanhouAnteriorImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideQuemTrucoSegundaGanhouAnteriorAtivo.deleteCases(
         * esquecerCasos); }
         */
        _caseBaseCentroideQuemTrucoSegundaGanhouAnterior.learnCase(aprenderCaso, "QuemTruco");
    }

    public void learnCasesCentroideQuemTrucoSegundaPerdeuAnterior(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideQuemTrucoSegundaPerdeuAnteriorBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideQuemTrucoSegundaPerdeuAnteriorImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideQuemTrucoSegundaPerdeuAnteriorAtivo.deleteCases(
         * esquecerCasos); }
         */
        _caseBaseCentroideQuemTrucoSegundaPerdeuAnterior.learnCase(aprenderCaso, "QuemTruco");
    }

    public void learnCasesCentroideQuemTrucoTerceiraGanhouAnterior(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideQuemTrucoTerceiraGanhouAnteriorBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideQuemTrucoTerceiraGanhouAnteriorImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideQuemTrucoTerceiraGanhouAnteriorAtivo.deleteCases(
         * esquecerCasos); }
         */
        _caseBaseCentroideQuemTrucoTerceiraGanhouAnterior.learnCase(aprenderCaso, "QuemTruco");
    }

    public void learnCasesCentroideQuemTrucoTerceiraPerdeuAnterior(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideQuemTrucoTerceiraPerdeuAnteriorAtivo.deleteCases(
         * esquecerCasos); }
         */
        _caseBaseCentroideQuemTrucoTerceiraPerdeuAnterior.learnCase(aprenderCaso, "QuemTruco");
    }

    public void learnCasesCentroideQuemGanhouEnvidoAgenteMao(CBRCase aprenderCasos, CBRCase esquecerCasos) {
        _caseBaseCentroideQuemGanhouEnvidoAgenteMao.forgetCase(esquecerCasos);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideQuemGanhouEnvidoAgenteMaoBaseline.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideQuemGanhouEnvidoAgenteMaoImitacao.deleteCases(
         * esquecerCasos); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideQuemGanhouEnvidoAgenteMaoAtivo.deleteCases(esquecerCasos);
         * }
         */
        _caseBaseCentroideQuemGanhouEnvidoAgenteMao.learnCase(aprenderCasos, "QuemGanhouEnvido");
    }

    public void learnCasesCentroideQuemGanhouEnvidoAgentePe(CBRCase aprenderCaso, CBRCase esquecerCaso) {
        _caseBaseCentroideQuemGanhouEnvidoAgentePe.forgetCase(esquecerCaso);
        /*
         * if (dataBaseConectado.equalsIgnoreCase("baseline")) {
         * _connectorCentroideQuemGanhouEnvidoAgentePeBaseline.deleteCases(esquecerCasos
         * ); } else if (dataBaseConectado.equalsIgnoreCase("imitacao")) {
         * _connectorCentroideQuemGanhouEnvidoAgentePeImitacao.deleteCases(esquecerCasos
         * ); } else if (dataBaseConectado.equalsIgnoreCase("ativo")) {
         * _connectorCentroideQuemGanhouEnvidoAgentePeAtivo.deleteCases(esquecerCasos);
         * }
         */
        _caseBaseCentroideQuemGanhouEnvidoAgentePe.learnCase(aprenderCaso, "QuemGanhouEnvido");
    }

    // esse aqui não pode esquecer porque não é o mesmo caso.

    public void learnCasesMaos(CBRCase aprenderCaso) {
        // System.out.println("chamou aprender");
        // System.out.println("vai aprender caso");
        casosAprendidos++;
        // System.out.println("casos aprendidos " + casosAprendidos);
        _caseBaseMaos.learnCase(aprenderCaso, "maos");
    }

    public boolean isActiveLearning() {
        return activeLearning;
    }

    public void setActiveLearning(boolean activeLearning) {
        this.activeLearning = activeLearning;
    }

    public double getProbabilidadeMaoByEstadoJogo(int isHand, TrucoDescription queryDefault) {

        double prob = deck.getProbabilidadeMelhorMao(isHand, getCartaByCodeESuit(queryDefault.getCartaAltaRobo(), queryDefault.getNaipeCartaAltaRobo()),
                getCartaByCodeESuit(queryDefault.getCartaMediaRobo(), queryDefault.getNaipeCartaMediaRobo()),
                getCartaByCodeESuit(queryDefault.getCartaBaixaRobo(), queryDefault.getNaipeCartaBaixaRobo()));

        if (queryDefault.getPrimeiraCartaHumano() != null && queryDefault.getNaipePrimeiraCartaHumano() != null) {

            prob = deck.getProbabilidadeMelhorMao(isHand, getCartaByCodeESuit(queryDefault.getCartaAltaRobo(), queryDefault.getNaipeCartaAltaRobo()),
                    getCartaByCodeESuit(queryDefault.getCartaMediaRobo(), queryDefault.getNaipeCartaMediaRobo()),
                    getCartaByCodeESuit(queryDefault.getCartaBaixaRobo(), queryDefault.getNaipeCartaBaixaRobo()),
                    getCartaByCodeESuit(queryDefault.getPrimeiraCartaHumano(), queryDefault.getNaipePrimeiraCartaHumano()));

        } else if (queryDefault.getPrimeiraCartaHumano() != null && queryDefault.getNaipePrimeiraCartaHumano() != null &&
                queryDefault.getSegundaCartaHumano() != null && queryDefault.getNaipeSegundaCartaHumano() != null) {

            prob = deck.getProbabilidadeMelhorMao(isHand, getCartaByCodeESuit(queryDefault.getCartaAltaRobo(), queryDefault.getNaipeCartaAltaRobo()),
                    getCartaByCodeESuit(queryDefault.getCartaMediaRobo(), queryDefault.getNaipeCartaMediaRobo()),
                    getCartaByCodeESuit(queryDefault.getCartaBaixaRobo(), queryDefault.getNaipeCartaBaixaRobo()),
                    getCartaByCodeESuit(queryDefault.getPrimeiraCartaHumano(), queryDefault.getNaipePrimeiraCartaHumano()),
                    getCartaByCodeESuit(queryDefault.getSegundaCartaHumano(), queryDefault.getNaipeSegundaCartaHumano()));

        } else if (queryDefault.getPrimeiraCartaHumano() != null && queryDefault.getNaipePrimeiraCartaHumano() != null &&
                queryDefault.getSegundaCartaHumano() != null && queryDefault.getNaipeSegundaCartaHumano() != null &&
                queryDefault.getTerceiraCartaHumano() != null && queryDefault.getNaipeTerceiraCartaHumano() != null) {

            if (isHand == 1) {

                if (!queryDefault.getPrimeiraCartaRobo().equals(queryDefault.getCartaAltaRobo())
                        && !queryDefault.getSegundaCartaRobo().equals(queryDefault.getCartaAltaRobo())) {

                    if (queryDefault.getCartaAltaRobo() >= queryDefault.getTerceiraCartaHumano()) {
                        prob = 1.0;
                    } else {
                        prob = 0.0;
                    }

                } else if (!queryDefault.getPrimeiraCartaRobo().equals(queryDefault.getCartaMediaRobo())
                        && !queryDefault.getSegundaCartaRobo().equals(queryDefault.getCartaMediaRobo())) {

                    if (queryDefault.getCartaMediaRobo() >= queryDefault.getTerceiraCartaHumano()) {
                        prob = 1.0;
                    } else {
                        prob = 0.0;
                    }

                } else if (!queryDefault.getPrimeiraCartaRobo().equals(queryDefault.getCartaBaixaRobo())
                        && !queryDefault.getSegundaCartaRobo().equals(queryDefault.getCartaBaixaRobo())) {

                    if (queryDefault.getCartaBaixaRobo() >= queryDefault.getTerceiraCartaHumano()) {
                        prob = 1.0;
                    } else {
                        prob = 0.0;
                    }

                }

            } else {

                if (!queryDefault.getPrimeiraCartaRobo().equals(queryDefault.getCartaAltaRobo())
                        && !queryDefault.getSegundaCartaRobo().equals(queryDefault.getCartaAltaRobo())) {

                    if (queryDefault.getCartaAltaRobo() > queryDefault.getTerceiraCartaHumano()) {
                        prob = 1.0;
                    } else {
                        prob = 0.0;
                    }

                } else if (!queryDefault.getPrimeiraCartaRobo().equals(queryDefault.getCartaMediaRobo())
                        && !queryDefault.getSegundaCartaRobo().equals(queryDefault.getCartaMediaRobo())) {

                    if (queryDefault.getCartaMediaRobo() > queryDefault.getTerceiraCartaHumano()) {
                        prob = 1.0;
                    } else {
                        prob = 0.0;
                    }

                } else if (!queryDefault.getPrimeiraCartaRobo().equals(queryDefault.getCartaBaixaRobo())
                        && !queryDefault.getSegundaCartaRobo().equals(queryDefault.getCartaBaixaRobo())) {

                    if (queryDefault.getCartaBaixaRobo() > queryDefault.getTerceiraCartaHumano()) {
                        prob = 1.0;
                    } else {
                        prob = 0.0;
                    }

                }

            }

        }

        return prob;

    }

    private String getCartaByCodeESuit(int CBRCode, String suit) {

        String carta = "";
        String face = "";
        String naipe= "";

        switch (CBRCode) {
            case 52:
                face = "1";
                break;
            case 50:
                face = "1";
                break;
            case 42:
                face = "7";
                break;
            case 40:
                face = "7";
                break;
            case 24:
                face = "3";
                break;
            case 16:
                face = "2";
                break;
            case 12:
                face = "1";
                break;
            case 8:
                face = "12";
                break;
            case 7:
                face = "11";
                break;
            case 6:
                face = "10";
                break;
            case 4:
                face = "7";
                break;
            case 3:
                face = "6";
                break;
            case 2:
                face = "5";
                break;
            case 1:
                face = "4";
                break;
        }

        switch (suit) {
            case "ESPADAS":
                naipe = "e";
                break;
            case "BASTOS":
                naipe = "p";
                break;
            case "OURO":
                naipe = "o";
                break;
            case "COPAS":
                naipe = "c";
                break;
        }

        carta = face + naipe;
        return carta;

    }

}
