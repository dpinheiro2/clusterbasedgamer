package cbr.cbrDescriptions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class TrucoDescription implements CaseComponent {

	Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	Integer idMao;
	String idPartida;
	Integer jogadorMao;
	Integer cartaAltaRobo;
	Integer cartaMediaRobo;
	Integer cartaBaixaRobo;
	Integer cartaAltaHumano;
	Integer cartaMediaHumano;
	Integer cartaBaixaHumano;
	Integer primeiraCartaRobo;
	Integer primeiraCartaHumano;
	Integer segundaCartaRobo;
	Integer segundaCartaHumano;
	Integer terceiraCartaRobo;
	Integer terceiraCartaHumano;
	Integer ganhadorPrimeiraRodada;
	Integer ganhadorSegundaRodada;
	Integer ganhadorTerceiraRodada;
	Integer roboCartaVirada;
	Integer humanoCartaVirada;
	Integer quemPediuEnvido;
	Integer quemPediuFaltaEnvido;
	Integer quemPediuRealEnvido;
	Integer pontosEnvidoRobo;
	Integer pontosEnvidoHumano;
	Integer quemNegouEnvido;
	Integer quemGanhouEnvido;
	Integer tentosEnvido;
	Integer quemFlor;
	Integer quemContraFlor;
	Integer quemContraFlorResto;
	Integer quemNegouFlor;
	Integer pontosFlorRobo;
	Integer pontosFlorHumano;
	Integer quemGanhouFlor;
	Integer tentosFlor;
	Integer quemEscondeuPontosEnvido;
	Integer quemEscondeuPontosFlor;
	Integer quemTruco;
	Integer quandoTruco;
	Integer quemRetruco;
	Integer quandoRetruco;
	Integer quemValeQuatro;
	Integer quandoValeQuatro;
	Integer quemNegouTruco;
	Integer quemGanhouTruco;
	Integer tentosTruco;
	Integer tentosAnterioresRobo;
	Integer tentosAnterioresHumano;
	Integer tentosPosterioresRobo;
	Integer tentosPosterioresHumano;
	Integer roboMentiuEnvido;
	Integer humanoMentiuEnvido;
	Integer roboMentiuFlor;
	Integer humanoMentiuFlor;
	Integer roboMentiuTruco;
	Integer humanoMentiuTruco;
	Integer quemBaralho;
	Integer quandoBaralho;
	Integer quemContraFlorFalta;
	Integer quemEnvidoEnvido;
	Integer quemFlorFlor;
	Integer quandoCartaVirada;
	String naipeCartaAltaRobo;
	String naipeCartaMediaRobo;
	String naipeCartaBaixaRobo;
	String naipeCartaAltaHumano;
	String naipeCartaMediaHumano;
	String naipeCartaBaixaHumano;
	String naipePrimeiraCartaRobo;
	String naipePrimeiraCartaHumano;
	String naipeSegundaCartaRobo;
	String naipeSegundaCartaHumano;
	String naipeTerceiraCartaRobo;
	String naipeTerceiraCartaHumano;


	double SimilaridadeCaso;

	
	Integer cartaAltaRoboClustering;
	Integer cartaMediaRoboClustering;
	Integer cartaBaixaRoboClustering;

	Integer clusterPrimeiraCartaAgenteMao;
	Integer clusterPrimeiraCartaAgentePe;

	Integer clusterSegundaCartaAgenteGanhouAprimeira;
	Integer clusterSegundaCartaAgentePerdeuAprimeira;
	
	Integer primeiraCartaHumanoClustering;
	Integer segundaCartaHumanoClustering;
	Integer terceiraCartaHumanoClustering;

	Integer primeiraCartaRoboClustering;
	Integer segundaCartaRoboClustering;
	Integer terceiraCartaRoboClustering;


	
	Integer clusterTerceiraCartaAgenteGanhouAsegunda;
	Integer clusterTerceiraCartaAgentePerdeuAsegunda;
	
	Integer clusterGanhadorUltimaRodada;
	
	
	
	
	Integer clusterQuemEnvidoAgenteMao;
	Integer clusterQuemFlor;
	
	
	Integer ganhadorMao;
	
	Integer clusterQuemEnvidoAgentePe;
	
	Integer saldoTruco;
	Integer saldoEnvido;
	Integer saldoFlor;
	
	Integer clusteringindexacao;
	
	Integer clusterBaralho;
	
	Integer clusteringIndexacaoPontos;
	
	
	Integer utilTruco;
	Integer utilEnvido;
	Integer utilFlor;
	Integer utilCarta;
	
	Integer clusterQuemTrucoPrimeiraMao;
	Integer clusterQuemTrucoPrimeiraPe;
	Integer clusterQuemTrucoSegundaGanhouAnterior;
	Integer clusterQuemTrucoSegundaPerdeuAnterior;
	Integer clusterQuemTrucoTerceiraGanhouAnterior;
	Integer clusterQuemTrucoTerceiraPerdeuAnterior;
	
	Integer quemTrucoCluster;
	Integer quemRetrucoCluster;
	Integer quemValeQuatroCluster;
	
	Integer quemPediuEnvidoCluster;
	Integer quemPediuRealEnvidoCluster;
	Integer quemPediuFaltaEnvidoCluster;

	Integer hasDeception;
	Integer diferencaPontuacao;
	Integer bluff1Success;
	Integer bluff2Success;
	Integer bluff3Success;
	Integer bluff4Success;
	Integer bluff5Success;
	Integer bluff6Success;
	Integer bluff1Failure;
	Integer bluff2Failure;
	Integer bluff3Failure;
	Integer bluff4Failure;
	Integer bluff5Failure;
	Integer bluff6Failure;
	Integer bluff1Opponent;
	Integer bluff2Opponent;
	Integer bluff3Opponent;
	Integer bluff4Opponent;
	Integer bluff5Opponent;
	Integer bluff6Opponent;
	Integer bluff1ShowDown;
	Integer bluff2ShowDown;
	Integer bluff3ShowDown;
	Integer bluff4ShowDown;
	Integer bluff5ShowDown;
	Integer bluff6ShowDown;



	@Override
	public String toString() {
		return "TrucoDescription [id=" + id + ", idMao=" + idMao + ", idPartida=" + idPartida + ", jogadorMao="
				+ jogadorMao + ", cartaAltaRobo=" + cartaAltaRobo + ", cartaMediaRobo=" + cartaMediaRobo
				+ ", cartaBaixaRobo=" + cartaBaixaRobo + ", cartaAltaHumano=" + cartaAltaHumano + ", cartaMediaHumano="
				+ cartaMediaHumano + ", cartaBaixaHumano=" + cartaBaixaHumano + ", primeiraCartaRobo="
				+ primeiraCartaRobo + ", primeiraCartaHumano=" + primeiraCartaHumano + ", segundaCartaRobo="
				+ segundaCartaRobo + ", segundaCartaHumano=" + segundaCartaHumano + ", terceiraCartaRobo="
				+ terceiraCartaRobo + ", terceiraCartaHumano=" + terceiraCartaHumano + ", ganhadorPrimeiraRodada="
				+ ganhadorPrimeiraRodada + ", ganhadorSegundaRodada=" + ganhadorSegundaRodada
				+ ", ganhadorTerceiraRodada=" + ganhadorTerceiraRodada + ", roboCartaVirada=" + roboCartaVirada
				+ ", humanoCartaVirada=" + humanoCartaVirada + ", quemPediuEnvido=" + quemPediuEnvido
				+ ", quemPediuFaltaEnvido=" + quemPediuFaltaEnvido + ", quemPediuRealEnvido=" + quemPediuRealEnvido
				+ ", pontosEnvidoRobo=" + pontosEnvidoRobo + ", pontosEnvidoHumano=" + pontosEnvidoHumano
				+ ", quemNegouEnvido=" + quemNegouEnvido + ", quemGanhouEnvido=" + quemGanhouEnvido + ", tentosEnvido="
				+ tentosEnvido + ", quemFlor=" + quemFlor + ", quemContraFlor=" + quemContraFlor
				+ ", quemContraFlorResto=" + quemContraFlorResto + ", quemNegouFlor=" + quemNegouFlor
				+ ", pontosFlorRobo=" + pontosFlorRobo + ", pontosFlorHumano=" + pontosFlorHumano + ", quemGanhouFlor="
				+ quemGanhouFlor + ", tentosFlor=" + tentosFlor + ", quemEscondeuPontosEnvido="
				+ quemEscondeuPontosEnvido + ", quemEscondeuPontosFlor=" + quemEscondeuPontosFlor + ", quemTruco="
				+ quemTruco + ", quandoTruco=" + quandoTruco + ", quemRetruco=" + quemRetruco + ", quandoRetruco="
				+ quandoRetruco + ", quemValeQuatro=" + quemValeQuatro + ", quandoValeQuatro=" + quandoValeQuatro
				+ ", quemNegouTruco=" + quemNegouTruco + ", quemGanhouTruco=" + quemGanhouTruco + ", tentosTruco="
				+ tentosTruco + ", tentosAnterioresRobo=" + tentosAnterioresRobo + ", tentosAnterioresHumano="
				+ tentosAnterioresHumano + ", tentosPosterioresRobo=" + tentosPosterioresRobo
				+ ", tentosPosterioresHumano=" + tentosPosterioresHumano + ", roboMentiuEnvido=" + roboMentiuEnvido
				+ ", humanoMentiuEnvido=" + humanoMentiuEnvido + ", roboMentiuFlor=" + roboMentiuFlor
				+ ", humanoMentiuFlor=" + humanoMentiuFlor + ", roboMentiuTruco=" + roboMentiuTruco
				+ ", humanoMentiuTruco=" + humanoMentiuTruco + ", quemBaralho=" + quemBaralho + ", quandoBaralho="
				+ quandoBaralho + ", quemContraFlorFalta=" + quemContraFlorFalta + ", quemEnvidoEnvido="
				+ quemEnvidoEnvido + ", quemFlorFlor=" + quemFlorFlor + ", quandoCartaVirada=" + quandoCartaVirada
				+ ", naipeCartaAltaRobo=" + naipeCartaAltaRobo + ", naipeCartaMediaRobo=" + naipeCartaMediaRobo
				+ ", naipeCartaBaixaRobo=" + naipeCartaBaixaRobo + ", naipeCartaAltaHumano=" + naipeCartaAltaHumano
				+ ", naipeCartaMediaHumano=" + naipeCartaMediaHumano + ", naipeCartaBaixaHumano="
				+ naipeCartaBaixaHumano + ", naipePrimeiraCartaRobo=" + naipePrimeiraCartaRobo
				+ ", naipePrimeiraCartaHumano=" + naipePrimeiraCartaHumano + ", naipeSegundaCartaRobo="
				+ naipeSegundaCartaRobo + ", naipeSegundaCartaHumano=" + naipeSegundaCartaHumano
				+ ", naipeTerceiraCartaRobo=" + naipeTerceiraCartaRobo + ", naipeTerceiraCartaHumano="
				+ naipeTerceiraCartaHumano + ", SimilaridadeCaso=" + SimilaridadeCaso + ", cartaAltaRoboClustering="
				+ cartaAltaRoboClustering + ", cartaMediaRoboClustering=" + cartaMediaRoboClustering
				+ ", cartaBaixaRoboClustering=" + cartaBaixaRoboClustering + ", clusterPrimeiraCartaAgenteMao="
				+ clusterPrimeiraCartaAgenteMao + ", clusterPrimeiraCartaAgentePe=" + clusterPrimeiraCartaAgentePe
				+ ", clusterSegundaCartaAgenteGanhouAprimeira=" + clusterSegundaCartaAgenteGanhouAprimeira
				+ ", clusterSegundaCartaAgentePerdeuAprimeira=" + clusterSegundaCartaAgentePerdeuAprimeira
				+ ", primeiraCartaHumanoClustering=" + primeiraCartaHumanoClustering + ", segundaCartaHumanoClustering="
				+ segundaCartaHumanoClustering + ", terceiraCartaHumanoClustering=" + terceiraCartaHumanoClustering
				+ ", primeiraCartaRoboClustering=" + primeiraCartaRoboClustering + ", segundaCartaRoboClustering="
				+ segundaCartaRoboClustering + ", terceiraCartaRoboClustering=" + terceiraCartaRoboClustering
				+ ", clusterTerceiraCartaAgenteGanhouAsegunda=" + clusterTerceiraCartaAgenteGanhouAsegunda
				+ ", clusterTerceiraCartaAgentePerdeuAsegunda=" + clusterTerceiraCartaAgentePerdeuAsegunda
				+ ", clusterGanhadorUltimaRodada=" + clusterGanhadorUltimaRodada + ", clusterQuemEnvidoAgenteMao="
				+ clusterQuemEnvidoAgenteMao + ", clusterQuemFlor=" + clusterQuemFlor + ", ganhadorMao=" + ganhadorMao
				+ ", clusterQuemEnvidoAgentePe=" + clusterQuemEnvidoAgentePe + ", saldoTruco=" + saldoTruco
				+ ", saldoEnvido=" + saldoEnvido + ", saldoFlor=" + saldoFlor + ", clusteringindexacao="
				+ clusteringindexacao + ", clusterBaralho=" + clusterBaralho + ", clusteringIndexacaoPontos="
				+ clusteringIndexacaoPontos + ", utilTruco=" + utilTruco + ", utilEnvido=" + utilEnvido + ", utilFlor="
				+ utilFlor + ", utilCarta=" + utilCarta + ", clusterQuemTrucoPrimeiraMao=" + clusterQuemTrucoPrimeiraMao
				+ ", clusterQuemTrucoPrimeiraPe=" + clusterQuemTrucoPrimeiraPe
				+ ", clusterQuemTrucoSegundaGanhouAnterior=" + clusterQuemTrucoSegundaGanhouAnterior
				+ ", clusterQuemTrucoSegundaPerdeuAnterior=" + clusterQuemTrucoSegundaPerdeuAnterior
				+ ", clusterQuemTrucoTerceiraGanhouAnterior=" + clusterQuemTrucoTerceiraGanhouAnterior
				+ ", clusterQuemTrucoTerceiraPerdeuAnterior=" + clusterQuemTrucoTerceiraPerdeuAnterior
				+ ", quemTrucoCluster=" + quemTrucoCluster + ", quemRetrucoCluster=" + quemRetrucoCluster
				+ ", quemValeQuatroCluster=" + quemValeQuatroCluster + ", quemPediuEnvidoCluster="
				+ quemPediuEnvidoCluster + ", quemPediuRealEnvidoCluster=" + quemPediuRealEnvidoCluster
				+ ", quemPediuFaltaEnvidoCluster=" + quemPediuFaltaEnvidoCluster + "]";
	}

	public TrucoDescription(Integer id, Integer idMao, String idPartida, Integer jogadorMao, Integer cartaAltaRobo,
			Integer cartaMediaRobo, Integer cartaBaixaRobo, Integer cartaAltaHumano, Integer cartaMediaHumano,
			Integer cartaBaixaHumano, Integer primeiraCartaRobo, Integer primeiraCartaHumano, Integer segundaCartaRobo,
			Integer segundaCartaHumano, Integer terceiraCartaRobo, Integer terceiraCartaHumano,
			Integer ganhadorPrimeiraRodada, Integer ganhadorSegundaRodada, Integer ganhadorTerceiraRodada,
			Integer roboCartaVirada, Integer humanoCartaVirada, Integer quemPediuEnvido, Integer quemPediuFaltaEnvido,
			Integer quemPediuRealEnvido, Integer pontosEnvidoRobo, Integer pontosEnvidoHumano, Integer quemNegouEnvido,
			Integer quemGanhouEnvido, Integer tentosEnvido, Integer quemFlor, Integer quemContraFlor,
			Integer quemContraFlorResto, Integer quemNegouFlor, Integer pontosFlorRobo, Integer pontosFlorHumano,
			Integer quemGanhouFlor, Integer tentosFlor, Integer quemEscondeuPontosEnvido,
			Integer quemEscondeuPontosFlor, Integer quemTruco, Integer quandoTruco, Integer quemRetruco,
			Integer quandoRetruco, Integer quemValeQuatro, Integer quandoValeQuatro, Integer quemNegouTruco,
			Integer quemGanhouTruco, Integer tentosTruco, Integer tentosAnterioresRobo, Integer tentosAnterioresHumano,
			Integer tentosPosterioresRobo, Integer tentosPosterioresHumano, Integer roboMentiuEnvido,
			Integer humanoMentiuEnvido, Integer roboMentiuFlor, Integer humanoMentiuFlor, Integer roboMentiuTruco,
			Integer humanoMentiuTruco, Integer quemBaralho, Integer quandoBaralho, Integer quemContraFlorFalta,
			Integer quemEnvidoEnvido, Integer quemFlorFlor, Integer quandoCartaVirada, String naipeCartaAltaRobo,
			String naipeCartaMediaRobo, String naipeCartaBaixaRobo, String naipeCartaAltaHumano,
			String naipeCartaMediaHumano, String naipeCartaBaixaHumano, String naipePrimeiraCartaRobo,
			String naipePrimeiraCartaHumano, String naipeSegundaCartaRobo, String naipeSegundaCartaHumano,
			String naipeTerceiraCartaRobo, String naipeTerceiraCartaHumano, double similaridadeCaso,
			Integer cartaAltaRoboClustering, Integer cartaMediaRoboClustering, Integer cartaBaixaRoboClustering,
			Integer clusterPrimeiraCartaAgenteMao, Integer clusterPrimeiraCartaAgentePe,
			Integer clusterSegundaCartaAgenteGanhouAprimeira, Integer clusterSegundaCartaAgentePerdeuAprimeira,
			Integer primeiraCartaHumanoClustering, Integer segundaCartaHumanoClustering,
			Integer terceiraCartaHumanoClustering, Integer primeiraCartaRoboClustering,
			Integer segundaCartaRoboClustering, Integer terceiraCartaRoboClustering,
			Integer clusterTerceiraCartaAgenteGanhouAsegunda, Integer clusterTerceiraCartaAgentePerdeuAsegunda,
			Integer clusterGanhadorUltimaRodada, Integer clusterQuemEnvidoAgenteMao, Integer clusterQuemFlor,
			Integer ganhadorMao, Integer clusterQuemEnvidoAgentePe, Integer saldoTruco, Integer saldoEnvido,
			Integer saldoFlor, Integer clusteringindexacao, Integer clusterBaralho, Integer clusteringIndexacaoPontos,
			Integer utilTruco, Integer utilEnvido, Integer utilFlor, Integer utilCarta,
			Integer clusterQuemTrucoPrimeiraMao, Integer clusterQuemTrucoPrimeiraPe,
			Integer clusterQuemTrucoSegundaGanhouAnterior, Integer clusterQuemTrucoSegundaPerdeuAnterior,
			Integer clusterQuemTrucoTerceiraGanhouAnterior, Integer clusterQuemTrucoTerceiraPerdeuAnterior,
			Integer quemTrucoCluster, Integer quemRetrucoCluster, Integer quemValeQuatroCluster,
			Integer quemPediuEnvidoCluster, Integer quemPediuRealEnvidoCluster, Integer quemPediuFaltaEnvidoCluster) {
		super();
		this.id = id;
		this.idMao = idMao;
		this.idPartida = idPartida;
		this.jogadorMao = jogadorMao;
		this.cartaAltaRobo = cartaAltaRobo;
		this.cartaMediaRobo = cartaMediaRobo;
		this.cartaBaixaRobo = cartaBaixaRobo;
		this.cartaAltaHumano = cartaAltaHumano;
		this.cartaMediaHumano = cartaMediaHumano;
		this.cartaBaixaHumano = cartaBaixaHumano;
		this.primeiraCartaRobo = primeiraCartaRobo;
		this.primeiraCartaHumano = primeiraCartaHumano;
		this.segundaCartaRobo = segundaCartaRobo;
		this.segundaCartaHumano = segundaCartaHumano;
		this.terceiraCartaRobo = terceiraCartaRobo;
		this.terceiraCartaHumano = terceiraCartaHumano;
		this.ganhadorPrimeiraRodada = ganhadorPrimeiraRodada;
		this.ganhadorSegundaRodada = ganhadorSegundaRodada;
		this.ganhadorTerceiraRodada = ganhadorTerceiraRodada;
		this.roboCartaVirada = roboCartaVirada;
		this.humanoCartaVirada = humanoCartaVirada;
		this.quemPediuEnvido = quemPediuEnvido;
		this.quemPediuFaltaEnvido = quemPediuFaltaEnvido;
		this.quemPediuRealEnvido = quemPediuRealEnvido;
		this.pontosEnvidoRobo = pontosEnvidoRobo;
		this.pontosEnvidoHumano = pontosEnvidoHumano;
		this.quemNegouEnvido = quemNegouEnvido;
		this.quemGanhouEnvido = quemGanhouEnvido;
		this.tentosEnvido = tentosEnvido;
		this.quemFlor = quemFlor;
		this.quemContraFlor = quemContraFlor;
		this.quemContraFlorResto = quemContraFlorResto;
		this.quemNegouFlor = quemNegouFlor;
		this.pontosFlorRobo = pontosFlorRobo;
		this.pontosFlorHumano = pontosFlorHumano;
		this.quemGanhouFlor = quemGanhouFlor;
		this.tentosFlor = tentosFlor;
		this.quemEscondeuPontosEnvido = quemEscondeuPontosEnvido;
		this.quemEscondeuPontosFlor = quemEscondeuPontosFlor;
		this.quemTruco = quemTruco;
		this.quandoTruco = quandoTruco;
		this.quemRetruco = quemRetruco;
		this.quandoRetruco = quandoRetruco;
		this.quemValeQuatro = quemValeQuatro;
		this.quandoValeQuatro = quandoValeQuatro;
		this.quemNegouTruco = quemNegouTruco;
		this.quemGanhouTruco = quemGanhouTruco;
		this.tentosTruco = tentosTruco;
		this.tentosAnterioresRobo = tentosAnterioresRobo;
		this.tentosAnterioresHumano = tentosAnterioresHumano;
		this.tentosPosterioresRobo = tentosPosterioresRobo;
		this.tentosPosterioresHumano = tentosPosterioresHumano;
		this.roboMentiuEnvido = roboMentiuEnvido;
		this.humanoMentiuEnvido = humanoMentiuEnvido;
		this.roboMentiuFlor = roboMentiuFlor;
		this.humanoMentiuFlor = humanoMentiuFlor;
		this.roboMentiuTruco = roboMentiuTruco;
		this.humanoMentiuTruco = humanoMentiuTruco;
		this.quemBaralho = quemBaralho;
		this.quandoBaralho = quandoBaralho;
		this.quemContraFlorFalta = quemContraFlorFalta;
		this.quemEnvidoEnvido = quemEnvidoEnvido;
		this.quemFlorFlor = quemFlorFlor;
		this.quandoCartaVirada = quandoCartaVirada;
		this.naipeCartaAltaRobo = naipeCartaAltaRobo;
		this.naipeCartaMediaRobo = naipeCartaMediaRobo;
		this.naipeCartaBaixaRobo = naipeCartaBaixaRobo;
		this.naipeCartaAltaHumano = naipeCartaAltaHumano;
		this.naipeCartaMediaHumano = naipeCartaMediaHumano;
		this.naipeCartaBaixaHumano = naipeCartaBaixaHumano;
		this.naipePrimeiraCartaRobo = naipePrimeiraCartaRobo;
		this.naipePrimeiraCartaHumano = naipePrimeiraCartaHumano;
		this.naipeSegundaCartaRobo = naipeSegundaCartaRobo;
		this.naipeSegundaCartaHumano = naipeSegundaCartaHumano;
		this.naipeTerceiraCartaRobo = naipeTerceiraCartaRobo;
		this.naipeTerceiraCartaHumano = naipeTerceiraCartaHumano;
		SimilaridadeCaso = similaridadeCaso;
		this.cartaAltaRoboClustering = cartaAltaRoboClustering;
		this.cartaMediaRoboClustering = cartaMediaRoboClustering;
		this.cartaBaixaRoboClustering = cartaBaixaRoboClustering;
		this.clusterPrimeiraCartaAgenteMao = clusterPrimeiraCartaAgenteMao;
		this.clusterPrimeiraCartaAgentePe = clusterPrimeiraCartaAgentePe;
		this.clusterSegundaCartaAgenteGanhouAprimeira = clusterSegundaCartaAgenteGanhouAprimeira;
		this.clusterSegundaCartaAgentePerdeuAprimeira = clusterSegundaCartaAgentePerdeuAprimeira;
		this.primeiraCartaHumanoClustering = primeiraCartaHumanoClustering;
		this.segundaCartaHumanoClustering = segundaCartaHumanoClustering;
		this.terceiraCartaHumanoClustering = terceiraCartaHumanoClustering;
		this.primeiraCartaRoboClustering = primeiraCartaRoboClustering;
		this.segundaCartaRoboClustering = segundaCartaRoboClustering;
		this.terceiraCartaRoboClustering = terceiraCartaRoboClustering;
		this.clusterTerceiraCartaAgenteGanhouAsegunda = clusterTerceiraCartaAgenteGanhouAsegunda;
		this.clusterTerceiraCartaAgentePerdeuAsegunda = clusterTerceiraCartaAgentePerdeuAsegunda;
		this.clusterGanhadorUltimaRodada = clusterGanhadorUltimaRodada;
		this.clusterQuemEnvidoAgenteMao = clusterQuemEnvidoAgenteMao;
		this.clusterQuemFlor = clusterQuemFlor;
		this.ganhadorMao = ganhadorMao;
		this.clusterQuemEnvidoAgentePe = clusterQuemEnvidoAgentePe;
		this.saldoTruco = saldoTruco;
		this.saldoEnvido = saldoEnvido;
		this.saldoFlor = saldoFlor;
		this.clusteringindexacao = clusteringindexacao;
		this.clusterBaralho = clusterBaralho;
		this.clusteringIndexacaoPontos = clusteringIndexacaoPontos;
		this.utilTruco = utilTruco;
		this.utilEnvido = utilEnvido;
		this.utilFlor = utilFlor;
		this.utilCarta = utilCarta;
		this.clusterQuemTrucoPrimeiraMao = clusterQuemTrucoPrimeiraMao;
		this.clusterQuemTrucoPrimeiraPe = clusterQuemTrucoPrimeiraPe;
		this.clusterQuemTrucoSegundaGanhouAnterior = clusterQuemTrucoSegundaGanhouAnterior;
		this.clusterQuemTrucoSegundaPerdeuAnterior = clusterQuemTrucoSegundaPerdeuAnterior;
		this.clusterQuemTrucoTerceiraGanhouAnterior = clusterQuemTrucoTerceiraGanhouAnterior;
		this.clusterQuemTrucoTerceiraPerdeuAnterior = clusterQuemTrucoTerceiraPerdeuAnterior;
		this.quemTrucoCluster = quemTrucoCluster;
		this.quemRetrucoCluster = quemRetrucoCluster;
		this.quemValeQuatroCluster = quemValeQuatroCluster;
		this.quemPediuEnvidoCluster = quemPediuEnvidoCluster;
		this.quemPediuRealEnvidoCluster = quemPediuRealEnvidoCluster;
		this.quemPediuFaltaEnvidoCluster = quemPediuFaltaEnvidoCluster;
	}

	public Integer getQuemTrucoCluster() {
		return quemTrucoCluster;
	}

	public void setQuemTrucoCluster(Integer quemTrucoCluster) {
		this.quemTrucoCluster = quemTrucoCluster;
	}

	public Integer getQuemRetrucoCluster() {
		return quemRetrucoCluster;
	}

	public void setQuemRetrucoCluster(Integer quemRetrucoCluster) {
		this.quemRetrucoCluster = quemRetrucoCluster;
	}

	public Integer getQuemValeQuatroCluster() {
		return quemValeQuatroCluster;
	}

	public void setQuemValeQuatroCluster(Integer quemValeQuatroCluster) {
		this.quemValeQuatroCluster = quemValeQuatroCluster;
	}

	public Integer getQuemPediuEnvidoCluster() {
		return quemPediuEnvidoCluster;
	}

	public void setQuemPediuEnvidoCluster(Integer quemPediuEnvidoCluster) {
		this.quemPediuEnvidoCluster = quemPediuEnvidoCluster;
	}

	public Integer getQuemPediuRealEnvidoCluster() {
		return quemPediuRealEnvidoCluster;
	}

	public void setQuemPediuRealEnvidoCluster(Integer quemPediuRealEnvidoCluster) {
		this.quemPediuRealEnvidoCluster = quemPediuRealEnvidoCluster;
	}

	public Integer getQuemPediuFaltaEnvidoCluster() {
		return quemPediuFaltaEnvidoCluster;
	}

	public void setQuemPediuFaltaEnvidoCluster(Integer quemPediuFaltaEnvidoCluster) {
		this.quemPediuFaltaEnvidoCluster = quemPediuFaltaEnvidoCluster;
	}

	
	public Integer getClusterQuemTrucoPrimeiraMao() {
		return clusterQuemTrucoPrimeiraMao;
	}

	public void setClusterQuemTrucoPrimeiraMao(Integer clusterQuemTrucoPrimeiraMao) {
		this.clusterQuemTrucoPrimeiraMao = clusterQuemTrucoPrimeiraMao;
	}

	public Integer getClusterQuemTrucoPrimeiraPe() {
		return clusterQuemTrucoPrimeiraPe;
	}

	public void setClusterQuemTrucoPrimeiraPe(Integer clusterQuemTrucoPrimeiraPe) {
		this.clusterQuemTrucoPrimeiraPe = clusterQuemTrucoPrimeiraPe;
	}

	public Integer getClusterQuemTrucoSegundaGanhouAnterior() {
		return clusterQuemTrucoSegundaGanhouAnterior;
	}

	public void setClusterQuemTrucoSegundaGanhouAnterior(Integer clusterQuemTrucoSegundaGanhouAnterior) {
		this.clusterQuemTrucoSegundaGanhouAnterior = clusterQuemTrucoSegundaGanhouAnterior;
	}

	public Integer getClusterQuemTrucoSegundaPerdeuAnterior() {
		return clusterQuemTrucoSegundaPerdeuAnterior;
	}

	public void setClusterQuemTrucoSegundaPerdeuAnterior(Integer clusterQuemTrucoSegundaPerdeuAnterior) {
		this.clusterQuemTrucoSegundaPerdeuAnterior = clusterQuemTrucoSegundaPerdeuAnterior;
	}

	public Integer getClusterQuemTrucoTerceiraGanhouAnterior() {
		return clusterQuemTrucoTerceiraGanhouAnterior;
	}

	public void setClusterQuemTrucoTerceiraGanhouAnterior(Integer clusterQuemTrucoTerceiraGanhouAnterior) {
		this.clusterQuemTrucoTerceiraGanhouAnterior = clusterQuemTrucoTerceiraGanhouAnterior;
	}

	public Integer getClusterQuemTrucoTerceiraPerdeuAnterior() {
		return clusterQuemTrucoTerceiraPerdeuAnterior;
	}

	public void setClusterQuemTrucoTerceiraPerdeuAnterior(Integer clusterQuemTrucoTerceiraPerdeuAnterior) {
		this.clusterQuemTrucoTerceiraPerdeuAnterior = clusterQuemTrucoTerceiraPerdeuAnterior;
	}

	public Integer getUtilTruco() {
		return utilTruco;
	}

	public void setUtilTruco(Integer utilTruco) {
		this.utilTruco = utilTruco;
	}

	public Integer getUtilEnvido() {
		return utilEnvido;
	}

	public void setUtilEnvido(Integer utilEnvido) {
		this.utilEnvido = utilEnvido;
	}

	public Integer getUtilFlor() {
		return utilFlor;
	}

	public void setUtilFlor(Integer utilFlor) {
		this.utilFlor = utilFlor;
	}

	public Integer getUtilCarta() {
		return utilCarta;
	}

	public void setUtilCarta(Integer utilCarta) {
		this.utilCarta = utilCarta;
	}

	public Integer getClusterBaralho() {
		return clusterBaralho;
	}

	public void setClusterBaralho(Integer clusterBaralho) {
		this.clusterBaralho = clusterBaralho;
	}

	public Integer getClusteringIndexacaoPontos() {
		return clusteringIndexacaoPontos;
	}

	public void setClusteringIndexacaoPontos(Integer clusteringIndexacaoPontos) {
		this.clusteringIndexacaoPontos = clusteringIndexacaoPontos;
	}

	
	public Integer getClusteringindexacao() {
		return clusteringindexacao;
	}

	public void setClusteringindexacao(Integer clusteringindexacao) {
		this.clusteringindexacao = clusteringindexacao;
	}

	public double getSimilaridadeCaso() {
		return SimilaridadeCaso;
	}

	public void setSimilaridadeCaso(double SimilaridadeCaso) {
		this.SimilaridadeCaso = SimilaridadeCaso;
	}

	public Integer getIdMao() {
		return idMao;
	}

	public void setIdMao(Integer idMao) {
		this.idMao = idMao;
	}

	public String getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}

	public Integer getJogadorMao() {
		return jogadorMao;
	}

	public void setJogadorMao(Integer jogadorMao) {
		this.jogadorMao = jogadorMao;
	}

	public Integer getCartaAltaRobo() {
		return cartaAltaRobo;
	}

	public void setCartaAltaRobo(Integer cartaAltaRobo) {
		this.cartaAltaRobo = cartaAltaRobo;
	}

	public Integer getCartaMediaRobo() {
		return cartaMediaRobo;
	}

	public void setCartaMediaRobo(Integer cartaMediaRobo) {
		this.cartaMediaRobo = cartaMediaRobo;
	}

	public Integer getCartaBaixaRobo() {
		return cartaBaixaRobo;
	}

	public void setCartaBaixaRobo(Integer cartaBaixaRobo) {
		this.cartaBaixaRobo = cartaBaixaRobo;
	}

	public Integer getCartaAltaHumano() {
		return cartaAltaHumano;
	}

	public void setCartaAltaHumano(Integer cartaAltaHumano) {
		this.cartaAltaHumano = cartaAltaHumano;
	}

	public Integer getCartaMediaHumano() {
		return cartaMediaHumano;
	}

	public void setCartaMediaHumano(Integer cartaMediaHumano) {
		this.cartaMediaHumano = cartaMediaHumano;
	}

	public Integer getCartaBaixaHumano() {
		return cartaBaixaHumano;
	}

	public void setCartaBaixaHumano(Integer cartaBaixaHumano) {
		this.cartaBaixaHumano = cartaBaixaHumano;
	}

	public Integer getPrimeiraCartaRobo() {
		return primeiraCartaRobo;
	}

	public void setPrimeiraCartaRobo(Integer primeiraCartaRobo) {
		this.primeiraCartaRobo = primeiraCartaRobo;
	}

	public Integer getPrimeiraCartaHumano() {
		return primeiraCartaHumano;
	}

	public void setPrimeiraCartaHumano(Integer primeiraCartaHumano) {
		this.primeiraCartaHumano = primeiraCartaHumano;
	}

	public Integer getSegundaCartaRobo() {
		return segundaCartaRobo;
	}

	public void setSegundaCartaRobo(Integer segundaCartaRobo) {
		this.segundaCartaRobo = segundaCartaRobo;
	}

	public Integer getSegundaCartaHumano() {
		return segundaCartaHumano;
	}

	public void setSegundaCartaHumano(Integer segundaCartaHumano) {
		this.segundaCartaHumano = segundaCartaHumano;
	}

	public Integer getTerceiraCartaRobo() {
		return terceiraCartaRobo;
	}

	public void setTerceiraCartaRobo(Integer terceiraCartaRobo) {
		this.terceiraCartaRobo = terceiraCartaRobo;
	}

	public Integer getTerceiraCartaHumano() {
		return terceiraCartaHumano;
	}

	public void setTerceiraCartaHumano(Integer terceiraCartaHumano) {
		this.terceiraCartaHumano = terceiraCartaHumano;
	}

	public Integer getGanhadorPrimeiraRodada() {
		return ganhadorPrimeiraRodada;
	}

	public void setGanhadorPrimeiraRodada(Integer ganhadorPrimeiraRodada) {
		this.ganhadorPrimeiraRodada = ganhadorPrimeiraRodada;
	}

	public Integer getGanhadorSegundaRodada() {
		return ganhadorSegundaRodada;
	}

	public void setGanhadorSegundaRodada(Integer ganhadorSegundaRodada) {
		this.ganhadorSegundaRodada = ganhadorSegundaRodada;
	}

	public Integer getGanhadorTerceiraRodada() {
		return ganhadorTerceiraRodada;
	}

	public void setGanhadorTerceiraRodada(Integer ganhadorTerceiraRodada) {
		this.ganhadorTerceiraRodada = ganhadorTerceiraRodada;
	}

	public Integer getRoboCartaVirada() {
		return roboCartaVirada;
	}

	public void setRoboCartaVirada(Integer roboCartaVirada) {
		this.roboCartaVirada = roboCartaVirada;
	}

	public Integer getHumanoCartaVirada() {
		return humanoCartaVirada;
	}

	public void setHumanoCartaVirada(Integer humanoCartaVirada) {
		this.humanoCartaVirada = humanoCartaVirada;
	}

	public Integer getQuemPediuEnvido() {
		return quemPediuEnvido;
	}

	public void setQuemPediuEnvido(Integer quemPediuEnvido) {
		this.quemPediuEnvido = quemPediuEnvido;
	}

	public Integer getQuemPediuFaltaEnvido() {
		return quemPediuFaltaEnvido;
	}

	public void setQuemPediuFaltaEnvido(Integer quemPediuFaltaEnvido) {
		this.quemPediuFaltaEnvido = quemPediuFaltaEnvido;
	}

	public Integer getQuemPediuRealEnvido() {
		return quemPediuRealEnvido;
	}

	public void setQuemPediuRealEnvido(Integer quemPediuRealEnvido) {
		this.quemPediuRealEnvido = quemPediuRealEnvido;
	}

	public Integer getPontosEnvidoRobo() {
		return pontosEnvidoRobo;
	}

	public void setPontosEnvidoRobo(Integer pontosEnvidoRobo) {
		//////System.out.println("pontos envido Agente: "+ pontosEnvidoRobo);
		this.pontosEnvidoRobo = pontosEnvidoRobo;
	}

	public Integer getPontosEnvidoHumano() {
		return pontosEnvidoHumano;
	}

	public void setPontosEnvidoHumano(Integer pontosEnvidoHumano) {
		this.pontosEnvidoHumano = pontosEnvidoHumano;
	}

	public Integer getQuemNegouEnvido() {
		return quemNegouEnvido;
	}

	public void setQuemNegouEnvido(Integer quemNegouEnvido) {
		this.quemNegouEnvido = quemNegouEnvido;
	}

	public Integer getQuemGanhouEnvido() {
		return quemGanhouEnvido;
	}

	public void setQuemGanhouEnvido(Integer quemGanhouEnvido) {
		this.quemGanhouEnvido = quemGanhouEnvido;
	}

	public Integer getTentosEnvido() {
		return tentosEnvido;
	}

	public void setTentosEnvido(Integer tentosEnvido) {
		this.tentosEnvido = tentosEnvido;
	}

	public Integer getQuemFlor() {
		return quemFlor;
	}

	public void setQuemFlor(Integer quemFlor) {
		this.quemFlor = quemFlor;
	}

	public Integer getQuemContraFlor() {
		return quemContraFlor;
	}

	public void setQuemContraFlor(Integer quemContraFlor) {
		this.quemContraFlor = quemContraFlor;
	}

	public Integer getQuemContraFlorResto() {
		return quemContraFlorResto;
	}

	public void setQuemContraFlorResto(Integer quemContraFlorResto) {
		this.quemContraFlorResto = quemContraFlorResto;
	}

	public Integer getQuemNegouFlor() {
		return quemNegouFlor;
	}

	public void setQuemNegouFlor(Integer quemNegouFlor) {
		this.quemNegouFlor = quemNegouFlor;
	}

	public Integer getPontosFlorRobo() {
		return pontosFlorRobo;
	}

	public void setPontosFlorRobo(Integer pontosFlorRobo) {
		this.pontosFlorRobo = pontosFlorRobo;
	}

	public Integer getPontosFlorHumano() {
		return pontosFlorHumano;
	}

	public void setPontosFlorHumano(Integer pontosFlorHumano) {
		this.pontosFlorHumano = pontosFlorHumano;
	}

	public Integer getQuemGanhouFlor() {
		return quemGanhouFlor;
	}
	
	public void setQuemGanhouFlor(Integer quemGanhouFlor) {
		this.quemGanhouFlor = quemGanhouFlor;
	}

	public Integer getTentosFlor() {
		return tentosFlor;
	}

	public void setTentosFlor(Integer tentosFlor) {
		this.tentosFlor = tentosFlor;
	}

	public Integer getQuemEscondeuPontosEnvido() {
		return quemEscondeuPontosEnvido;
	}

	public void setQuemEscondeuPontosEnvido(Integer quemEscondeuPontosEnvido) {
		this.quemEscondeuPontosEnvido = quemEscondeuPontosEnvido;
	}

	public Integer getQuemEscondeuPontosFlor() {
		return quemEscondeuPontosFlor;
	}

	public void setQuemEscondeuPontosFlor(Integer quemEscondeuPontosFlor) {
		this.quemEscondeuPontosFlor = quemEscondeuPontosFlor;
	}

	public Integer getQuemTruco() {
		return quemTruco;
	}

	public void setQuemTruco(Integer quemTruco) {
		this.quemTruco = quemTruco;
	}

	public Integer getQuandoTruco() {
		return quandoTruco;
	}

	public void setQuandoTruco(Integer quandoTruco) {
		this.quandoTruco = quandoTruco;
	}

	public Integer getQuemRetruco() {
		return quemRetruco;
	}

	public void setQuemRetruco(Integer quemRetruco) {
		this.quemRetruco = quemRetruco;
	}

	public Integer getQuandoRetruco() {
		return quandoRetruco;
	}

	public void setQuandoRetruco(Integer quandoRetruco) {
		this.quandoRetruco = quandoRetruco;
	}

	public Integer getQuemValeQuatro() {
		return quemValeQuatro;
	}

	public void setQuemValeQuatro(Integer quemValeQuatro) {
		this.quemValeQuatro = quemValeQuatro;
	}

	public Integer getQuandoValeQuatro() {
		return quandoValeQuatro;
	}

	public void setQuandoValeQuatro(Integer quandoValeQuatro) {
		this.quandoValeQuatro = quandoValeQuatro;
	}

	public Integer getQuemNegouTruco() {
		return quemNegouTruco;
	}

	public void setQuemNegouTruco(Integer quemNegouTruco) {
		this.quemNegouTruco = quemNegouTruco;
	}

	public Integer getQuemGanhouTruco() {
		return quemGanhouTruco;
	}

	public void setQuemGanhouTruco(Integer quemGanhouTruco) {
		this.quemGanhouTruco = quemGanhouTruco;
	}

	public Integer getTentosTruco() {
		return tentosTruco;
	}

	public void setTentosTruco(Integer tentosTruco) {
		this.tentosTruco = tentosTruco;
	}

	public Integer getTentosAnterioresRobo() {
		return tentosAnterioresRobo;
	}

	public void setTentosAnterioresRobo(Integer tentosAnterioresRobo) {
		this.tentosAnterioresRobo = tentosAnterioresRobo;
	}

	public Integer getTentosAnterioresHumano() {
		return tentosAnterioresHumano;
	}

	public void setTentosAnterioresHumano(Integer tentosAnterioresHumano) {
		this.tentosAnterioresHumano = tentosAnterioresHumano;
	}

	public Integer getTentosPosterioresRobo() {
		return tentosPosterioresRobo;
	}

	public void setTentosPosterioresRobo(Integer tentosPosterioresRobo) {
		this.tentosPosterioresRobo = tentosPosterioresRobo;
	}

	public Integer getTentosPosterioresHumano() {
		return tentosPosterioresHumano;
	}

	public void setTentosPosterioresHumano(Integer tentosPosterioresHumano) {
		this.tentosPosterioresHumano = tentosPosterioresHumano;
	}

	public Integer getRoboMentiuEnvido() {
		return roboMentiuEnvido;
	}

	public void setRoboMentiuEnvido(Integer roboMentiuEnvido) {
		this.roboMentiuEnvido = roboMentiuEnvido;
	}

	public Integer getHumanoMentiuEnvido() {
		return humanoMentiuEnvido;
	}

	public void setHumanoMentiuEnvido(Integer humanoMentiuEnvido) {
		this.humanoMentiuEnvido = humanoMentiuEnvido;
	}

	public Integer getRoboMentiuFlor() {
		return roboMentiuFlor;
	}

	public void setRoboMentiuFlor(Integer roboMentiuFlor) {
		this.roboMentiuFlor = roboMentiuFlor;
	}

	public Integer getHumanoMentiuFlor() {
		return humanoMentiuFlor;
	}

	public void setHumanoMentiuFlor(Integer humanoMentiuFlor) {
		this.humanoMentiuFlor = humanoMentiuFlor;
	}

	public Integer getRoboMentiuTruco() {
		return roboMentiuTruco;
	}

	public void setRoboMentiuTruco(Integer roboMentiuTruco) {
		this.roboMentiuTruco = roboMentiuTruco;
	}

	public Integer getHumanoMentiuTruco() {
		return humanoMentiuTruco;
	}

	public void setHumanoMentiuTruco(Integer humanoMentiuTruco) {
		this.humanoMentiuTruco = humanoMentiuTruco;
	}

	public Integer getQuemBaralho() {
		return quemBaralho;
	}

	public void setQuemBaralho(Integer quemBaralho) {
		this.quemBaralho = quemBaralho;
	}

	public Integer getQuandoBaralho() {
		return quandoBaralho;
	}

	public void setQuandoBaralho(Integer quandoBaralho) {
		this.quandoBaralho = quandoBaralho;
	}

	public Integer getQuemContraFlorFalta() {
		return quemContraFlorFalta;
	}

	public void setQuemContraFlorFalta(Integer quemContraFlorFalta) {
		this.quemContraFlorFalta = quemContraFlorFalta;
	}

	public Integer getQuemEnvidoEnvido() {
		return quemEnvidoEnvido;
	}

	public void setQuemEnvidoEnvido(Integer quemEnvidoEnvido) {
		this.quemEnvidoEnvido = quemEnvidoEnvido;
	}

	public Integer getQuemFlorFlor() {
		return quemFlorFlor;
	}

	public void setQuemFlorFlor(Integer quemFlorFlor) {
		this.quemFlorFlor = quemFlorFlor;
	}

	public Integer getQuandoCartaVirada() {
		return quandoCartaVirada;
	}

	public void setQuandoCartaVirada(Integer quandoCartaVirada) {
		this.quandoCartaVirada = quandoCartaVirada;
	}

	public String getNaipeCartaAltaRobo() {
		return naipeCartaAltaRobo;
	}

	public void setNaipeCartaAltaRobo(String naipeCartaAltaRobo) {
		this.naipeCartaAltaRobo = naipeCartaAltaRobo;
	}

	public String getNaipeCartaMediaRobo() {
		return naipeCartaMediaRobo;
	}

	public void setNaipeCartaMediaRobo(String naipeCartaMediaRobo) {
		this.naipeCartaMediaRobo = naipeCartaMediaRobo;
	}

	public String getNaipeCartaBaixaRobo() {
		return naipeCartaBaixaRobo;
	}

	public void setNaipeCartaBaixaRobo(String naipeCartaBaixaRobo) {
		this.naipeCartaBaixaRobo = naipeCartaBaixaRobo;
	}

	public String getNaipeCartaAltaHumano() {
		return naipeCartaAltaHumano;
	}

	public void setNaipeCartaAltaHumano(String naipeCartaAltaHumano) {
		this.naipeCartaAltaHumano = naipeCartaAltaHumano;
	}

	public String getNaipeCartaMediaHumano() {
		return naipeCartaMediaHumano;
	}

	public void setNaipeCartaMediaHumano(String naipeCartaMediaHumano) {
		this.naipeCartaMediaHumano = naipeCartaMediaHumano;
	}

	public String getNaipeCartaBaixaHumano() {
		return naipeCartaBaixaHumano;
	}

	public void setNaipeCartaBaixaHumano(String naipeCartaBaixaHumano) {
		this.naipeCartaBaixaHumano = naipeCartaBaixaHumano;
	}

	public String getNaipePrimeiraCartaRobo() {
		return naipePrimeiraCartaRobo;
	}

	public void setNaipePrimeiraCartaRobo(String naipePrimeiraCartaRobo) {
		this.naipePrimeiraCartaRobo = naipePrimeiraCartaRobo;
	}

	public String getNaipePrimeiraCartaHumano() {
		return naipePrimeiraCartaHumano;
	}

	public void setNaipePrimeiraCartaHumano(String naipePrimeiraCartaHumano) {
		this.naipePrimeiraCartaHumano = naipePrimeiraCartaHumano;
	}

	public String getNaipeSegundaCartaRobo() {
		return naipeSegundaCartaRobo;
	}

	public void setNaipeSegundaCartaRobo(String naipeSegundaCartaRobo) {
		this.naipeSegundaCartaRobo = naipeSegundaCartaRobo;
	}

	public String getNaipeSegundaCartaHumano() {
		return naipeSegundaCartaHumano;
	}

	public void setNaipeSegundaCartaHumano(String naipeSegundaCartaHumano) {
		this.naipeSegundaCartaHumano = naipeSegundaCartaHumano;
	}

	public String getNaipeTerceiraCartaRobo() {
		return naipeTerceiraCartaRobo;
	}

	public void setNaipeTerceiraCartaRobo(String naipeTerceiraCartaRobo) {
		this.naipeTerceiraCartaRobo = naipeTerceiraCartaRobo;
	}

	public String getNaipeTerceiraCartaHumano() {
		return naipeTerceiraCartaHumano;
	}

	public void setNaipeTerceiraCartaHumano(String naipeTerceiraCartaHumano) {
		this.naipeTerceiraCartaHumano = naipeTerceiraCartaHumano;
	}
	
	
	
	

	public Integer getGanhadorMao() {
		return ganhadorMao;
	}

	public void setGanhadorMao(Integer ganhadorMao) {
		this.ganhadorMao = ganhadorMao;
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}



	
	public TrucoDescription() {
		super();
	}

	public Integer getCartaAltaRoboClustering() {
		return cartaAltaRoboClustering;
	}

	public void setCartaAltaRoboClustering(Integer cartaAltaRoboClustering) {
		this.cartaAltaRoboClustering = cartaAltaRoboClustering;
	}

	public Integer getCartaMediaRoboClustering() {
		return cartaMediaRoboClustering;
	}

	public void setCartaMediaRoboClustering(Integer cartaMediaRoboClustering) {
		this.cartaMediaRoboClustering = cartaMediaRoboClustering;
	}

	public Integer getCartaBaixaRoboClustering() {
		return cartaBaixaRoboClustering;
	}

	public void setCartaBaixaRoboClustering(Integer cartaBaixaRoboClustering) {
		this.cartaBaixaRoboClustering = cartaBaixaRoboClustering;
	}

	public Integer getClusterPrimeiraCartaAgenteMao() {
		return clusterPrimeiraCartaAgenteMao;
	}

	public void setClusterPrimeiraCartaAgenteMao(Integer clusterPrimeiraCartaAgenteMao) {
		this.clusterPrimeiraCartaAgenteMao = clusterPrimeiraCartaAgenteMao;
	}

	public Integer getClusterPrimeiraCartaAgentePe() {
		return clusterPrimeiraCartaAgentePe;
	}

	public void setClusterPrimeiraCartaAgentePe(Integer clusterPrimeiraCartaAgentePe) {
		this.clusterPrimeiraCartaAgentePe = clusterPrimeiraCartaAgentePe;
	}

	
	public Integer getClusterSegundaCartaAgenteGanhouAprimeira() {
		return clusterSegundaCartaAgenteGanhouAprimeira;
	}

	public void setClusterSegundaCartaAgenteGanhouAprimeira(Integer clusterSegundaCartaAgenteGanhaPrimeira) {
		this.clusterSegundaCartaAgenteGanhouAprimeira = clusterSegundaCartaAgenteGanhaPrimeira;
	}

	public Integer getClusterSegundaCartaAgentePerdeuAprimeira() {
		return clusterSegundaCartaAgentePerdeuAprimeira;
	}

	public void setClusterSegundaCartaAgentePerdeuAprimeira(Integer clusterSegundaCartaAgentePerdePrimeira) {
		this.clusterSegundaCartaAgentePerdeuAprimeira = clusterSegundaCartaAgentePerdePrimeira;
	}

	
	
	public Integer getClusterSegundaCartaAgenteGanhouAPrimeira() {
		return clusterSegundaCartaAgenteGanhouAprimeira;
	}

	public void setClusterSegundaCartaAgenteGanhouAPrimeira(Integer clusterSegundaCartaAgenteGanhouAPrimeira) {
		this.clusterSegundaCartaAgenteGanhouAprimeira = clusterSegundaCartaAgenteGanhouAPrimeira;
	}

	public Integer getClusterSegundaCartaAgentePerdeuAPrimeira() {
		return clusterSegundaCartaAgentePerdeuAprimeira;
	}

	public void setClusterSegundaCartaAgentePerdeuAPrimeira(Integer clusterSegundaCartaAgentePerdeuAPrimeira) {
		this.clusterSegundaCartaAgentePerdeuAprimeira = clusterSegundaCartaAgentePerdeuAPrimeira;
	}

	public Integer getPrimeiraCartaHumanoClustering() {
		return primeiraCartaHumanoClustering;
	}

	public void setPrimeiraCartaHumanoClustering(Integer primeiraCartaHumanoClustering) {
		this.primeiraCartaHumanoClustering = primeiraCartaHumanoClustering;
	}

	public Integer getSegundaCartaHumanoClustering() {
		return segundaCartaHumanoClustering;
	}

	public void setSegundaCartaHumanoClustering(Integer segundaCartaHumanoClustering) {
		this.segundaCartaHumanoClustering = segundaCartaHumanoClustering;
	}

	public Integer getTerceiraCartaHumanoClustering() {
		return terceiraCartaHumanoClustering;
	}

	public void setTerceiraCartaHumanoClustering(Integer terceiraCartaHumanoClustering) {
		this.terceiraCartaHumanoClustering = terceiraCartaHumanoClustering;
	}

	public Integer getPrimeiraCartaRoboClustering() {
		return primeiraCartaRoboClustering;
	}

	public void setPrimeiraCartaRoboClustering(Integer primeiraCartaRoboClustering) {
		this.primeiraCartaRoboClustering = primeiraCartaRoboClustering;
	}

	public Integer getSegundaCartaRoboClustering() {
		return segundaCartaRoboClustering;
	}

	public void setSegundaCartaRoboClustering(Integer segundaCartaRoboClustering) {
		this.segundaCartaRoboClustering = segundaCartaRoboClustering;
	}

	public Integer getTerceiraCartaRoboClustering() {
		return terceiraCartaRoboClustering;
	}

	public void setTerceiraCartaRoboClustering(Integer terceiraCartaRoboClustering) {
		this.terceiraCartaRoboClustering = terceiraCartaRoboClustering;
	}

	public Integer getClusterTerceiraCartaAgenteGanhouAsegunda() {
		return clusterTerceiraCartaAgenteGanhouAsegunda;
	}

	public void setClusterTerceiraCartaAgenteGanhouAsegunda(Integer clusterTerceiraCartaAgenteGanhouAsegunda) {
		this.clusterTerceiraCartaAgenteGanhouAsegunda = clusterTerceiraCartaAgenteGanhouAsegunda;
	}

	public Integer getClusterTerceiraCartaAgentePerdeuAsegunda() {
		return clusterTerceiraCartaAgentePerdeuAsegunda;
	}

	public void setClusterTerceiraCartaAgentePerdeuAsegunda(Integer clusterTerceiraCartaAgentePerdeuAsegunda) {
		this.clusterTerceiraCartaAgentePerdeuAsegunda = clusterTerceiraCartaAgentePerdeuAsegunda;
	}

	public Integer getClusterGanhadorUltimaRodada() {
		return clusterGanhadorUltimaRodada;
	}

	public void setClusterGanhadorUltimaRodada(Integer clusterGanhadorUltimaRodada) {
		this.clusterGanhadorUltimaRodada = clusterGanhadorUltimaRodada;
	}




	public Integer getClusterQuemEnvido() {
		return clusterQuemEnvidoAgenteMao;
	}

	public void setClusterQuemEnvido(Integer clusterQuemEnvido) {
		this.clusterQuemEnvidoAgenteMao = clusterQuemEnvido;
	}

	public Integer getClusterQuemFlor() {
		return clusterQuemFlor;
	}

	public void setClusterQuemFlor(Integer clusterQuandoFlor) {
		this.clusterQuemFlor = clusterQuandoFlor;
	}

	
	public Integer getClusterQuemEnvidoAgenteMao() {
		return clusterQuemEnvidoAgenteMao;
	}

	public void setClusterQuemEnvidoAgenteMao(Integer clusterQuemEnvidoAgenteMao) {
		this.clusterQuemEnvidoAgenteMao = clusterQuemEnvidoAgenteMao;
	}

	public Integer getClusterQuemEnvidoAgentePe() {
		return clusterQuemEnvidoAgentePe;
	}

	public void setClusterQuemEnvidoAgentePe(Integer clusterQuemEnvidoAgentePe) {
		this.clusterQuemEnvidoAgentePe = clusterQuemEnvidoAgentePe;
	}

	public Integer getHasDeception() {
		return hasDeception;
	}

	public void setHasDeception(Integer hasDeception) {
		this.hasDeception = hasDeception;
	}

	public Integer getDiferencaPontuacao() {
		return diferencaPontuacao;
	}

	public void setDiferencaPontuacao(Integer diferencaPontuacao) {
		this.diferencaPontuacao = diferencaPontuacao;
	}

	public Integer getBluff1Success() {
		return bluff1Success;
	}

	public void setBluff1Success(Integer bluff1Success) {
		this.bluff1Success = bluff1Success;
	}

	public Integer getBluff2Success() {
		return bluff2Success;
	}

	public void setBluff2Success(Integer bluff2Success) {
		this.bluff2Success = bluff2Success;
	}

	public Integer getBluff3Success() {
		return bluff3Success;
	}

	public void setBluff3Success(Integer bluff3Success) {
		this.bluff3Success = bluff3Success;
	}

	public Integer getBluff4Success() {
		return bluff4Success;
	}

	public void setBluff4Success(Integer bluff4Success) {
		this.bluff4Success = bluff4Success;
	}

	public Integer getBluff5Success() {
		return bluff5Success;
	}

	public void setBluff5Success(Integer bluff5Success) {
		this.bluff5Success = bluff5Success;
	}

	public Integer getBluff6Success() {
		return bluff6Success;
	}

	public void setBluff6Success(Integer bluff6Success) {
		this.bluff6Success = bluff6Success;
	}

	public Integer getBluff1Failure() {
		return bluff1Failure;
	}

	public void setBluff1Failure(Integer bluff1Failure) {
		this.bluff1Failure = bluff1Failure;
	}

	public Integer getBluff2Failure() {
		return bluff2Failure;
	}

	public void setBluff2Failure(Integer bluff2Failure) {
		this.bluff2Failure = bluff2Failure;
	}

	public Integer getBluff3Failure() {
		return bluff3Failure;
	}

	public void setBluff3Failure(Integer bluff3Failure) {
		this.bluff3Failure = bluff3Failure;
	}

	public Integer getBluff4Failure() {
		return bluff4Failure;
	}

	public void setBluff4Failure(Integer bluff4Failure) {
		this.bluff4Failure = bluff4Failure;
	}

	public Integer getBluff5Failure() {
		return bluff5Failure;
	}

	public void setBluff5Failure(Integer bluff5Failure) {
		this.bluff5Failure = bluff5Failure;
	}

	public Integer getBluff6Failure() {
		return bluff6Failure;
	}

	public void setBluff6Failure(Integer bluff6Failure) {
		this.bluff6Failure = bluff6Failure;
	}

	public Integer getSaldoTruco() {
		return saldoTruco;
	}

	public void setSaldoTruco(Integer saldoTruco) {
		this.saldoTruco = saldoTruco;
	}

	public Integer getSaldoEnvido() {
		return saldoEnvido;
	}

	public void setSaldoEnvido(Integer saldoEnvido) {
		this.saldoEnvido = saldoEnvido;
	}

	public Integer getSaldoFlor() {
		return saldoFlor;
	}

	public void setSaldoFlor(Integer saldoFlor) {
		this.saldoFlor = saldoFlor;
	}

	public Integer getBluff1Opponent() {
		return bluff1Opponent;
	}

	public void setBluff1Opponent(Integer bluff1Opponent) {
		this.bluff1Opponent = bluff1Opponent;
	}

	public Integer getBluff2Opponent() {
		return bluff2Opponent;
	}

	public void setBluff2Opponent(Integer bluff2Opponent) {
		this.bluff2Opponent = bluff2Opponent;
	}

	public Integer getBluff3Opponent() {
		return bluff3Opponent;
	}

	public void setBluff3Opponent(Integer bluff3Opponent) {
		this.bluff3Opponent = bluff3Opponent;
	}

	public Integer getBluff4Opponent() {
		return bluff4Opponent;
	}

	public void setBluff4Opponent(Integer bluff4Opponent) {
		this.bluff4Opponent = bluff4Opponent;
	}

	public Integer getBluff5Opponent() {
		return bluff5Opponent;
	}

	public void setBluff5Opponent(Integer bluff5Opponent) {
		this.bluff5Opponent = bluff5Opponent;
	}

	public Integer getBluff6Opponent() {
		return bluff6Opponent;
	}

	public void setBluff6Opponent(Integer bluff6Opponent) {
		this.bluff6Opponent = bluff6Opponent;
	}

	public Integer getBluff1ShowDown() {
		return bluff1ShowDown;
	}

	public void setBluff1ShowDown(Integer bluff1ShowDown) {
		this.bluff1ShowDown = bluff1ShowDown;
	}

	public Integer getBluff2ShowDown() {
		return bluff2ShowDown;
	}

	public void setBluff2ShowDown(Integer bluff2ShowDown) {
		this.bluff2ShowDown = bluff2ShowDown;
	}

	public Integer getBluff3ShowDown() {
		return bluff3ShowDown;
	}

	public void setBluff3ShowDown(Integer bluff3ShowDown) {
		this.bluff3ShowDown = bluff3ShowDown;
	}

	public Integer getBluff4ShowDown() {
		return bluff4ShowDown;
	}

	public void setBluff4ShowDown(Integer bluff4ShowDown) {
		this.bluff4ShowDown = bluff4ShowDown;
	}

	public Integer getBluff5ShowDown() {
		return bluff5ShowDown;
	}

	public void setBluff5ShowDown(Integer bluff5ShowDown) {
		this.bluff5ShowDown = bluff5ShowDown;
	}

	public Integer getBluff6ShowDown() {
		return bluff6ShowDown;
	}

	public void setBluff6ShowDown(Integer bluff6ShowDown) {
		this.bluff6ShowDown = bluff6ShowDown;
	}

	public boolean equalPonto(TrucoDescription query) {
	boolean equal = false;
	if(this.getJogadorMao() == query.getJogadorMao() && this.getPrimeiraCartaHumano() == query.getPrimeiraCartaHumano() && 
			this.getQuemPediuEnvido() == query.getQuemPediuEnvido() && this.getQuemPediuRealEnvido() == query.getQuemPediuRealEnvido() &&
			this.getPontosEnvidoRobo() == query.getPontosEnvidoRobo() && this.getTentosAnterioresRobo() == query.getTentosAnterioresRobo()&&
			this.getTentosAnterioresHumano() == query.getTentosAnterioresHumano()) equal = true;
	
	
	return equal;
}

public boolean validateInformationsCompleteInPontoQuery(TrucoDescription query) {
	boolean equal = false;
	if(((this.getJogadorMao() == null && query.getJogadorMao() == null) || (this.getJogadorMao() != null && query.getJogadorMao() != null)) 
			&& ((this.getPrimeiraCartaHumano() == null && query.getPrimeiraCartaHumano() == null) || (this.getPrimeiraCartaHumano() != null && query.getPrimeiraCartaHumano() != null))
			&& ((this.getQuemPediuEnvido() ==null && query.getQuemPediuEnvido() == null) || (this.getQuemPediuEnvido() !=null && query.getQuemPediuEnvido() != null)) 
			&& ((this.getQuemPediuRealEnvido() == null && query.getQuemPediuRealEnvido() == null) || (this.getQuemPediuRealEnvido() != null && query.getQuemPediuRealEnvido() != null))
			&& ((this.getPontosEnvidoRobo() == null && query.getPontosEnvidoRobo() == null) || (this.getPontosEnvidoRobo() != null && query.getPontosEnvidoRobo() != null))
			&& ((this.getTentosAnterioresRobo() == null && query.getTentosAnterioresRobo() == null) || (this.getTentosAnterioresRobo() != null && query.getTentosAnterioresRobo() != null))
			&&((this.getTentosAnterioresHumano() == null && query.getTentosAnterioresHumano() == null || this.getTentosAnterioresHumano() != null && query.getTentosAnterioresHumano() != null))) 
		equal = true;
	
	
	return equal;
}

public boolean equalCarta(TrucoDescription query) {
	boolean equal = false;
	if(this.getJogadorMao()== query.getJogadorMao() && this.getCartaAltaRobo() == query.getCartaAltaRobo() && this.getCartaMediaRobo() == query.getCartaMediaRobo()
	&& this.getCartaBaixaRobo() == query.getCartaBaixaRobo()) {
		equal = true;
	}
	
	return equal;
}

public boolean validateInformationsCompleteInCarta(TrucoDescription query) {
	boolean equal = false;
	if(((this.getJogadorMao() == null &&	query.getJogadorMao() == null) || (this.getJogadorMao() != null &&	query.getJogadorMao() != null))
	 && ((this.getCartaAltaRobo() == null && query.getCartaAltaRobo()== null) || (this.getCartaAltaRobo() != null && query.getCartaAltaRobo() != null))
	 && ((this.getCartaMediaRobo() == null  && query.getCartaMediaRobo() == null) || (this.getCartaMediaRobo() != null  && query.getCartaMediaRobo() != null))
	&& ((this.getCartaBaixaRobo() == null && query.getCartaBaixaRobo() == null) || (this.getCartaMediaRobo() != null  && query.getCartaMediaRobo() != null))) {
		equal = true;
	}
	
	return equal;
}


public boolean equalJogada(TrucoDescription query) {
	boolean equal = false;
	if(this.getJogadorMao() == query.getJogadorMao() && this.getCartaAltaRobo() == query.getCartaAltaRobo() && this.getCartaMediaRobo() == query.getCartaMediaRobo()
			&& this.getCartaBaixaRobo() == query.getCartaBaixaRobo() && this.getPrimeiraCartaRobo() == query.getPrimeiraCartaRobo() &&
			this.getSegundaCartaRobo() == query.getSegundaCartaRobo() && this.getTerceiraCartaRobo() == query.getTerceiraCartaRobo() 
			&& this.getPrimeiraCartaHumano() == query.getPrimeiraCartaHumano() && this.getSegundaCartaHumano() == query.getSegundaCartaHumano()
			&& this.getTerceiraCartaHumano() == query.getTerceiraCartaHumano() && this.getGanhadorPrimeiraRodada() == query.getGanhadorPrimeiraRodada()
			&& this.getGanhadorSegundaRodada() == query.getGanhadorSegundaRodada() && this.getQuemTruco() == query.getQuemTruco()
			&& this.getQuemRetruco() ==query.getQuemRetruco() && this.getQuemValeQuatro() == query.getQuemValeQuatro() && 
			this.getQuandoTruco() == query.getQuandoTruco() && this.getQuandoRetruco() == query.getQuandoRetruco() && 
			this.getQuandoValeQuatro() == query.getQuandoValeQuatro()) equal = true;
	
	return equal;
}
public boolean validateInformationCompleteJogada(TrucoDescription query) {
	boolean equal = false;
	if(((this.getJogadorMao() == null && query.getJogadorMao() == null) || (this.getJogadorMao() != null && query.getJogadorMao() != null))
			&& ((this.getCartaAltaRobo() == null && query.getCartaAltaRobo()== null) || (this.getCartaAltaRobo() != null && query.getCartaAltaRobo()!= null)) 
			&& ((this.getCartaMediaRobo() == null && query.getCartaMediaRobo()== null) || (this.getCartaAltaRobo() != null && query.getCartaAltaRobo()!= null))
			&& ((this.getCartaBaixaRobo() == null && query.getCartaBaixaRobo() == null) || (this.getCartaBaixaRobo() != null && query.getCartaBaixaRobo() != null)) 
			&& ((this.getPrimeiraCartaRobo() == null && query.getPrimeiraCartaRobo() == null) || (this.getPrimeiraCartaRobo() != null && query.getPrimeiraCartaRobo() != null))  
			&& ((this.getSegundaCartaRobo() == null && query.getSegundaCartaRobo() == null) || (this.getSegundaCartaRobo() != null && query.getSegundaCartaRobo() != null)) 
			&& ((this.getTerceiraCartaRobo() == null && query.getTerceiraCartaRobo() == null) || (this.getTerceiraCartaRobo() != null && query.getTerceiraCartaRobo() != null)) 
			&& ((this.getPrimeiraCartaHumano() == null && query.getPrimeiraCartaHumano() == null)|| (this.getPrimeiraCartaHumano() != null && query.getPrimeiraCartaHumano() != null)) 
			&& ((this.getSegundaCartaHumano() == null && query.getSegundaCartaHumano()== null) || (this.getSegundaCartaHumano() != null && query.getSegundaCartaHumano()!= null))
			&& ((this.getTerceiraCartaHumano() == null && query.getTerceiraCartaHumano()== null) || (this.getTerceiraCartaHumano() != null && query.getTerceiraCartaHumano() != null)) 
			&& ((this.getGanhadorPrimeiraRodada() == null && query.getGanhadorPrimeiraRodada() == null) || (this.getGanhadorPrimeiraRodada() != null && query.getGanhadorPrimeiraRodada() != null ))
			&& ((this.getGanhadorSegundaRodada() == null && query.getGanhadorSegundaRodada() == null) || (this.getGanhadorSegundaRodada() != null && query.getGanhadorSegundaRodada() != null)) 
			&& ((this.getQuemTruco() == null && query.getQuemTruco() == null) || (this.getQuemTruco() != null && query.getQuemTruco() != null))
			&& ((this.getQuemRetruco() == null && query.getQuemRetruco() == null) || (this.getQuemRetruco() != null && query.getQuemRetruco() != null )) 
			&& ((this.getQuemValeQuatro() == null && query.getQuemValeQuatro() == null) || (this.getQuemValeQuatro() != null && query.getQuemValeQuatro() != null)) 
			&& ((this.getQuandoTruco() == null && query.getQuandoTruco() == null) || (this.getQuandoTruco() != null && query.getQuandoTruco() != null)) 
			&& ((this.getQuandoRetruco() == null && query.getQuandoRetruco() == null) || (this.getQuandoRetruco() != null && query.getQuandoRetruco() != null)) 
			&& ((this.getQuandoValeQuatro() == null && query.getQuandoValeQuatro()== null) || (this.getQuandoValeQuatro() != null && query.getQuandoValeQuatro()!= null))) 
				equal = true;
	
	return equal;
}



public boolean equalContraFlor(TrucoDescription query) {
	boolean equal = false;
	if(this.getPontosFlorRobo() == query.getPontosFlorRobo() && this.getTentosAnterioresHumano() == query.getTentosAnterioresHumano() &&
			this.getTentosAnterioresRobo() == query.getTentosAnterioresRobo()
			) equal = true;
	
	return equal;
}

public boolean validateInformationCompleteContraFlor(TrucoDescription query) {
	boolean equal = false;
	if(((this.getPontosFlorRobo() == null  && query.getPontosFlorRobo() == null) || (this.getPontosFlorRobo() != null  && query.getPontosFlorRobo() != null))
		&& ((this.getTentosAnterioresHumano() == null && query.getTentosAnterioresHumano() == null) || (this.getTentosAnterioresHumano() != null && query.getTentosAnterioresHumano() != null))
		&& ((this.getTentosAnterioresRobo() == null && query.getTentosAnterioresRobo() == null) || (this.getTentosAnterioresRobo() != null && query.getTentosAnterioresRobo() != null))) 
				equal = true;
	
	return equal;
}
	
}