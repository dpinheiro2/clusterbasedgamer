package cbr;

public class PesosConsulta {
	Double pesoIdMao;
	Double pesoIdPartida;
	Double pesoJogadorMao;
	Double pesoCartaAltaRobo;
	Double pesoCartaMediaRobo;
	Double pesoCartaBaixaRobo;
	Double pesoCartaAltaHumano;
	Double pesoCartaMediaHumano;
	Double pesoCartaBaixaHumano;
	Double pesoPrimeiraCartaRobo;
	Double pesoPrimeiraCartaHumano;
	Double pesoSegundaCartaRobo;
	Double pesoSegundaCartaHumano;
	Double pesoTerceiraCartaRobo;
	Double pesoTerceiraCartaHumano;
	Double pesoGanhadorPrimeiraRodada;
	Double pesoGanhadorSegundaRodada;
	Double pesoGanhadorTerceiraRodada;
	Double pesoRoboCartaVirada;
	Double pesoHumanoCartaVirada;
	Double pesoQuemPediuEnvido;
	Double pesoQuemPediuFaltaEnvido;
	Double pesoQuemPediuRealEnvido;
	Double pesoPontosEnvidoRobo;
	Double pesoPontosEnvidoHumano;
	Double pesoQuemNegouEnvido;
	Double pesoQuemGanhouEnvido;
	Double pesoTentosEnvido;
	Double pesoQuemFlor;
	Double pesoQuemContraFlor;
	Double pesoQuemContraFlorResto;
	Double pesoQuemNegouFlor;
	Double pesoPontosFlorRobo;
	Double pesoPontosFlorHumano;
	Double pesoQuemGanhouFlor;
	Double pesoTentosFlor;
	Double pesoQuemEscondeuPontosEnvido;
	Double pesoQuemEscondeuPontosFlor;
	Double pesoQuemTruco;
	Double pesoQuandoTruco;
	Double pesoQuemRetruco;
	Double pesoQuandoRetruco;
	Double pesoQuemValeQuatro;
	Double pesoQuandoValeQuatro;
	Double pesoQuemNegouTruco;
	Double pesoQuemGanhouTruco;
	Double pesoTentosTruco;
	Double pesoTentosAnterioresRobo;
	Double pesoTentosAnterioresHumano;
	Double pesoTentosPosterioresRobo;
	Double pesoTentosPosterioresHumano;
	Double pesoRoboMentiuEnvido;
	Double pesoHumanoMentiuEnvido;
	Double pesoRoboMentiuFlor;
	Double pesoHumanoMentiuFlor;
	Double pesoRoboMentiuTruco;
	Double pesoHumanoMentiuTruco;
	Double pesoQuemBaralho;
	Double pesoQuandoBaralho;
	Double pesoQuemContraFlorFalta;
	Double pesoQuemEnvidoEnvido;
	Double pesoQuemFlorFlor;
	Double pesoQuandoCartaVirada;
	Double pesoNaipeCartaAltaRobo;
	Double pesoNaipeCartaMediaRobo;
	Double pesoNaipeCartaBaixaRobo;
	Double pesoNaipeCartaAltaHumano;
	Double pesoNaipeCartaMediaHumano;
	Double pesoNaipeCartaBaixaHumano;
	Double pesoNaipePrimeiraCartaRobo;
	Double pesoNaipePrimeiraCartaHumano;
	Double pesoNaipeSegundaCartaRobo;
	Double pesoNaipeSegundaCartaHumano;
	Double pesoNaipeTerceiraCartaRobo;
	Double pesoNaipeTerceiraCartaHumano;
	Double bluff1Success;
	Double bluff2Success;
	Double bluff3Success;
	Double bluff4Success;
	Double bluff5Success;
	Double bluff6Success;
	Double bluff1Failure;
	Double bluff2Failure;
	Double bluff3Failure;
	Double bluff4Failure;
	Double bluff5Failure;
	Double bluff6Failure;

	public PesosConsulta() {
		pesoIdMao = 1.0;
		pesoIdPartida = 1.0;
		pesoJogadorMao = 1.0;
		pesoCartaAltaRobo = 1.0;
		pesoCartaMediaRobo = 1.0;
		pesoCartaBaixaRobo = 1.0;
		pesoCartaAltaHumano = 1.0;
		pesoCartaMediaHumano = 1.0;
		pesoCartaBaixaHumano = 1.0;
		pesoPrimeiraCartaRobo = 1.0;
		pesoPrimeiraCartaHumano = 1.0;
		pesoSegundaCartaRobo = 1.0;
		pesoSegundaCartaHumano = 1.0;
		pesoTerceiraCartaRobo = 1.0;
		pesoTerceiraCartaHumano = 1.0;
		pesoGanhadorPrimeiraRodada = 1.0;
		pesoGanhadorSegundaRodada = 1.0;
		pesoGanhadorTerceiraRodada = 1.0;
		pesoRoboCartaVirada = 1.0;
		pesoHumanoCartaVirada = 1.0;
		pesoQuemPediuEnvido = 1.0;
		pesoQuemPediuFaltaEnvido = 1.0;
		pesoQuemPediuRealEnvido = 1.0;
		pesoPontosEnvidoRobo = 1.0;
		pesoPontosEnvidoHumano = 1.0;
		pesoQuemNegouEnvido = 1.0;
		pesoQuemGanhouEnvido = 1.0;
		pesoTentosEnvido = 1.0;
		pesoQuemFlor = 1.0;
		pesoQuemContraFlor = 1.0;
		pesoQuemContraFlorResto = 1.0;
		pesoQuemNegouFlor = 1.0;
		pesoPontosFlorRobo = 1.0;
		pesoPontosFlorHumano = 1.0;
		pesoQuemGanhouFlor = 1.0;
		pesoTentosFlor = 1.0;
		pesoQuemEscondeuPontosEnvido = 1.0;
		pesoQuemEscondeuPontosFlor = 1.0;
		pesoQuemTruco = 1.0;
		pesoQuandoTruco = 1.0;
		pesoQuemRetruco = 1.0;
		pesoQuandoRetruco = 1.0;
		pesoQuemValeQuatro = 1.0;
		pesoQuandoValeQuatro = 1.0;
		pesoQuemNegouTruco = 1.0;
		pesoQuemGanhouTruco = 1.0;
		pesoTentosTruco = 1.0;
		pesoTentosAnterioresRobo = 1.0;
		pesoTentosAnterioresHumano = 1.0;
		pesoTentosPosterioresRobo = 1.0;
		pesoTentosPosterioresHumano = 1.0;
		pesoRoboMentiuEnvido = 1.0;
		pesoHumanoMentiuEnvido = 1.0;
		pesoRoboMentiuFlor = 1.0;
		pesoHumanoMentiuFlor = 1.0;
		pesoRoboMentiuTruco = 1.0;
		pesoHumanoMentiuTruco = 1.0;
		pesoQuemBaralho = 1.0;
		pesoQuandoBaralho = 1.0;
		pesoQuemContraFlorFalta = 1.0;
		pesoQuemEnvidoEnvido = 1.0;
		pesoQuemFlorFlor = 1.0;
		pesoQuandoCartaVirada = 1.0;
		pesoNaipeCartaAltaRobo = 1.0;
		pesoNaipeCartaMediaRobo = 1.0;
		pesoNaipeCartaBaixaRobo = 1.0;
		pesoNaipeCartaAltaHumano = 1.0;
		pesoNaipeCartaMediaHumano = 1.0;
		pesoNaipeCartaBaixaHumano = 1.0;
		pesoNaipePrimeiraCartaRobo = 1.0;
		pesoNaipePrimeiraCartaHumano = 1.0;
		pesoNaipeSegundaCartaRobo = 1.0;
		pesoNaipeSegundaCartaHumano = 1.0;
		pesoNaipeTerceiraCartaRobo = 1.0;
		pesoNaipeTerceiraCartaHumano = 1.0;
		bluff1Success = 1.0;
		bluff2Success = 1.0;
		bluff3Success = 1.0;
		bluff4Success = 1.0;
		bluff5Success = 1.0;
		bluff6Success = 1.0;
		bluff1Failure = 1.0;
		bluff2Failure = 1.0;
		bluff3Failure = 1.0;
		bluff4Failure = 1.0;
		bluff5Failure = 1.0;
		bluff6Failure = 1.0;
	}

		public Double getPesoJogadorMao() {
		return pesoJogadorMao;
	}

	public Double getPesoCartaAltaRobo() {
		return pesoCartaAltaRobo;
	}

	public Double getPesoCartaMediaRobo() {
		return pesoCartaMediaRobo;
	}

	public Double getPesoCartaBaixaRobo() {
		return pesoCartaBaixaRobo;
	}

	public Double getPesoCartaAltaHumano() {
		return pesoCartaAltaHumano;
	}

	public Double getPesoCartaMediaHumano() {
		return pesoCartaMediaHumano;
	}

	public Double getPesoCartaBaixaHumano() {
		return pesoCartaBaixaHumano;
	}

	public Double getPesoPrimeiraCartaRobo() {
		return pesoPrimeiraCartaRobo;
	}

	public Double getPesoPrimeiraCartaHumano() {
		return pesoPrimeiraCartaHumano;
	}

	public Double getPesoSegundaCartaRobo() {
		return pesoSegundaCartaRobo;
	}

	public Double getPesoSegundaCartaHumano() {
		return pesoSegundaCartaHumano;
	}

	public Double getPesoTerceiraCartaRobo() {
		return pesoTerceiraCartaRobo;
	}

	public Double getPesoTerceiraCartaHumano() {
		return pesoTerceiraCartaHumano;
	}

	public Double getPesoGanhadorPrimeiraRodada() {
		return pesoGanhadorPrimeiraRodada;
	}

	public Double getPesoGanhadorSegundaRodada() {
		return pesoGanhadorSegundaRodada;
	}

	public Double getPesoGanhadorTerceiraRodada() {
		return pesoGanhadorTerceiraRodada;
	}

	public Double getPesoRoboCartaVirada() {
		return pesoRoboCartaVirada;
	}

	public Double getPesoHumanoCartaVirada() {
		return pesoHumanoCartaVirada;
	}

	public Double getPesoQuemPediuEnvido() {
		return pesoQuemPediuEnvido;
	}

	public Double getPesoQuemPediuFaltaEnvido() {
		return pesoQuemPediuFaltaEnvido;
	}

	public Double getPesoQuemPediuRealEnvido() {
		return pesoQuemPediuRealEnvido;
	}

	public Double getPesoPontosEnvidoRobo() {
		return pesoPontosEnvidoRobo;
	}

	public Double getPesoPontosEnvidoHumano() {
		return pesoPontosEnvidoHumano;
	}

	public Double getPesoQuemNegouEnvido() {
		return pesoQuemNegouEnvido;
	}

	public Double getPesoQuemGanhouEnvido() {
		return pesoQuemGanhouEnvido;
	}

	public Double getPesoTentosEnvido() {
		return pesoTentosEnvido;
	}

	public Double getPesoQuemFlor() {
		return pesoQuemFlor;
	}

	public Double getPesoQuemContraFlor() {
		return pesoQuemContraFlor;
	}

	public Double getPesoQuemContraFlorResto() {
		return pesoQuemContraFlorResto;
	}

	public Double getPesoQuemNegouFlor() {
		return pesoQuemNegouFlor;
	}

	public Double getPesoPontosFlorRobo() {
		return pesoPontosFlorRobo;
	}

	public Double getPesoPontosFlorHumano() {
		return pesoPontosFlorHumano;
	}

	public Double getPesoQuemGanhouFlor() {
		return pesoQuemGanhouFlor;
	}

	public Double getPesoTentosFlor() {
		return pesoTentosFlor;
	}

	public Double getPesoQuemEscondeuPontosEnvido() {
		return pesoQuemEscondeuPontosEnvido;
	}

	public Double getPesoQuemEscondeuPontosFlor() {
		return pesoQuemEscondeuPontosFlor;
	}

	public Double getPesoQuemTruco() {
		return pesoQuemTruco;
	}

	public Double getPesoQuandoTruco() {
		return pesoQuandoTruco;
	}

	public Double getPesoQuemRetruco() {
		return pesoQuemRetruco;
	}

	public Double getPesoQuandoRetruco() {
		return pesoQuandoRetruco;
	}

	public Double getPesoQuemValeQuatro() {
		return pesoQuemValeQuatro;
	}

	public Double getPesoQuandoValeQuatro() {
		return pesoQuandoValeQuatro;
	}

	public Double getPesoQuemNegouTruco() {
		return pesoQuemNegouTruco;
	}

	public Double getPesoQuemGanhouTruco() {
		return pesoQuemGanhouTruco;
	}

	public Double getPesoTentosTruco() {
		return pesoTentosTruco;
	}

	public Double getPesoTentosAnterioresRobo() {
		return pesoTentosAnterioresRobo;
	}

	public Double getPesoTentosAnterioresHumano() {
		return pesoTentosAnterioresHumano;
	}

	public Double getPesoTentosPosterioresRobo() {
		return pesoTentosPosterioresRobo;
	}

	public Double getPesoTentosPosterioresHumano() {
		return pesoTentosPosterioresHumano;
	}

	public Double getPesoRoboMentiuEnvido() {
		return pesoRoboMentiuEnvido;
	}

	public Double getPesoHumanoMentiuEnvido() {
		return pesoHumanoMentiuEnvido;
	}

	public Double getPesoRoboMentiuFlor() {
		return pesoRoboMentiuFlor;
	}

	public Double getPesoHumanoMentiuFlor() {
		return pesoHumanoMentiuFlor;
	}

	public Double getPesoRoboMentiuTruco() {
		return pesoRoboMentiuTruco;
	}

	public Double getPesoHumanoMentiuTruco() {
		return pesoHumanoMentiuTruco;
	}

	public Double getPesoQuemBaralho() {
		return pesoQuemBaralho;
	}

	public Double getPesoIdMao() {
		return pesoIdMao;
	}

	public void setPesoIdMao(Double pesoIdMao) {
		this.pesoIdMao = pesoIdMao;
	}

	public Double getPesoIdPartida() {
		return pesoIdPartida;
	}

	public void setPesoIdPartida(Double pesoIdPartida) {
		this.pesoIdPartida = pesoIdPartida;
	}

	public Double getPesoQuandoBaralho() {
		return pesoQuandoBaralho;
	}

	public void setPesoQuandoBaralho(Double pesoQuandoBaralho) {
		this.pesoQuandoBaralho = pesoQuandoBaralho;
	}

	public Double getPesoQuemContraFlorFalta() {
		return pesoQuemContraFlorFalta;
	}

	public void setPesoQuemContraFlorFalta(Double pesoQuemContraFlorFalta) {
		this.pesoQuemContraFlorFalta = pesoQuemContraFlorFalta;
	}

	public Double getPesoQuemEnvidoEnvido() {
		return pesoQuemEnvidoEnvido;
	}

	public void setPesoQuemEnvidoEnvido(Double pesoQuemEnvidoEnvido) {
		this.pesoQuemEnvidoEnvido = pesoQuemEnvidoEnvido;
	}

	public Double getPesoQuemFlorFlor() {
		return pesoQuemFlorFlor;
	}

	public void setPesoQuemFlorFlor(Double pesoQuemFlorFlor) {
		this.pesoQuemFlorFlor = pesoQuemFlorFlor;
	}

	public Double getPesoQuandoCartaVirada() {
		return pesoQuandoCartaVirada;
	}

	public void setPesoQuandoCartaVirada(Double pesoQuandoCartaVirada) {
		this.pesoQuandoCartaVirada = pesoQuandoCartaVirada;
	}

	public Double getPesoNaipeCartaAltaRobo() {
		return pesoNaipeCartaAltaRobo;
	}

	public void setPesoNaipeCartaAltaRobo(Double pesoNaipeCartaAltaRobo) {
		this.pesoNaipeCartaAltaRobo = pesoNaipeCartaAltaRobo;
	}

	public Double getPesoNaipeCartaMediaRobo() {
		return pesoNaipeCartaMediaRobo;
	}

	public void setPesoNaipeCartaMediaRobo(Double pesoNaipeCartaMediaRobo) {
		this.pesoNaipeCartaMediaRobo = pesoNaipeCartaMediaRobo;
	}

	public Double getPesoNaipeCartaBaixaRobo() {
		return pesoNaipeCartaBaixaRobo;
	}

	public void setPesoNaipeCartaBaixaRobo(Double pesoNaipeCartaBaixaRobo) {
		this.pesoNaipeCartaBaixaRobo = pesoNaipeCartaBaixaRobo;
	}

	public Double getPesoNaipeCartaAltaHumano() {
		return pesoNaipeCartaAltaHumano;
	}

	public void setPesoNaipeCartaAltaHumano(Double pesoNaipeCartaAltaHumano) {
		this.pesoNaipeCartaAltaHumano = pesoNaipeCartaAltaHumano;
	}

	public Double getPesoNaipeCartaMediaHumano() {
		return pesoNaipeCartaMediaHumano;
	}

	public void setPesoNaipeCartaMediaHumano(Double pesoNaipeCartaMediaHumano) {
		this.pesoNaipeCartaMediaHumano = pesoNaipeCartaMediaHumano;
	}

	public Double getPesoNaipeCartaBaixaHumano() {
		return pesoNaipeCartaBaixaHumano;
	}

	public void setPesoNaipeCartaBaixaHumano(Double pesoNaipeCartaBaixaHumano) {
		this.pesoNaipeCartaBaixaHumano = pesoNaipeCartaBaixaHumano;
	}

	public Double getPesoNaipePrimeiraCartaRobo() {
		return pesoNaipePrimeiraCartaRobo;
	}

	public void setPesoNaipePrimeiraCartaRobo(Double pesoNaipePrimeiraCartaRobo) {
		this.pesoNaipePrimeiraCartaRobo = pesoNaipePrimeiraCartaRobo;
	}

	public Double getPesoNaipePrimeiraCartaHumano() {
		return pesoNaipePrimeiraCartaHumano;
	}

	public void setPesoNaipePrimeiraCartaHumano(Double pesoNaipePrimeiraCartaHumano) {
		this.pesoNaipePrimeiraCartaHumano = pesoNaipePrimeiraCartaHumano;
	}

	public Double getPesoNaipeSegundaCartaRobo() {
		return pesoNaipeSegundaCartaRobo;
	}

	public void setPesoNaipeSegundaCartaRobo(Double pesoNaipeSegundaCartaRobo) {
		this.pesoNaipeSegundaCartaRobo = pesoNaipeSegundaCartaRobo;
	}

	public Double getPesoNaipeSegundaCartaHumano() {
		return pesoNaipeSegundaCartaHumano;
	}

	public void setPesoNaipeSegundaCartaHumano(Double pesoNaipeSegundaCartaHumano) {
		this.pesoNaipeSegundaCartaHumano = pesoNaipeSegundaCartaHumano;
	}

	public Double getPesoNaipeTerceiraCartaRobo() {
		return pesoNaipeTerceiraCartaRobo;
	}

	public void setPesoNaipeTerceiraCartaRobo(Double pesoNaipeTerceiraCartaRobo) {
		this.pesoNaipeTerceiraCartaRobo = pesoNaipeTerceiraCartaRobo;
	}

	public Double getPesoNaipeTerceiraCartaHumano() {
		return pesoNaipeTerceiraCartaHumano;
	}

	public void setPesoNaipeTerceiraCartaHumano(Double pesoNaipeTerceiraCartaHumano) {
		this.pesoNaipeTerceiraCartaHumano = pesoNaipeTerceiraCartaHumano;
	}

	public void setPesoJogadorMao(Double pesoJogadorMao) {
		this.pesoJogadorMao = pesoJogadorMao;
	}

	public void setPesoCartaAltaRobo(Double pesoCartaAltaRobo) {
		this.pesoCartaAltaRobo = pesoCartaAltaRobo;
	}

	public void setPesoCartaMediaRobo(Double pesoCartaMediaRobo) {
		this.pesoCartaMediaRobo = pesoCartaMediaRobo;
	}

	public void setPesoCartaBaixaRobo(Double pesoCartaBaixaRobo) {
		this.pesoCartaBaixaRobo = pesoCartaBaixaRobo;
	}

	public void setPesoCartaAltaHumano(Double pesoCartaAltaHumano) {
		this.pesoCartaAltaHumano = pesoCartaAltaHumano;
	}

	public void setPesoCartaMediaHumano(Double pesoCartaMediaHumano) {
		this.pesoCartaMediaHumano = pesoCartaMediaHumano;
	}

	public void setPesoCartaBaixaHumano(Double pesoCartaBaixaHumano) {
		this.pesoCartaBaixaHumano = pesoCartaBaixaHumano;
	}

	public void setPesoPrimeiraCartaRobo(Double pesoPrimeiraCartaRobo) {
		this.pesoPrimeiraCartaRobo = pesoPrimeiraCartaRobo;
	}

	public void setPesoPrimeiraCartaHumano(Double pesoPrimeiraCartaHumano) {
		this.pesoPrimeiraCartaHumano = pesoPrimeiraCartaHumano;
	}

	public void setPesoSegundaCartaRobo(Double pesoSegundaCartaRobo) {
		this.pesoSegundaCartaRobo = pesoSegundaCartaRobo;
	}

	public void setPesoSegundaCartaHumano(Double pesoSegundaCartaHumano) {
		this.pesoSegundaCartaHumano = pesoSegundaCartaHumano;
	}

	public void setPesoTerceiraCartaRobo(Double pesoTerceiraCartaRobo) {
		this.pesoTerceiraCartaRobo = pesoTerceiraCartaRobo;
	}

	public void setPesoTerceiraCartaHumano(Double pesoTerceiraCartaHumano) {
		this.pesoTerceiraCartaHumano = pesoTerceiraCartaHumano;
	}

	public void setPesoGanhadorPrimeiraRodada(Double pesoGanhadorPrimeiraRodada) {
		this.pesoGanhadorPrimeiraRodada = pesoGanhadorPrimeiraRodada;
	}

	public void setPesoGanhadorSegundaRodada(Double pesoGanhadorSegundaRodada) {
		this.pesoGanhadorSegundaRodada = pesoGanhadorSegundaRodada;
	}

	public void setPesoGanhadorTerceiraRodada(Double pesoGanhadorTerceiraRodada) {
		this.pesoGanhadorTerceiraRodada = pesoGanhadorTerceiraRodada;
	}

	public void setPesoRoboCartaVirada(Double pesoRoboCartaVirada) {
		this.pesoRoboCartaVirada = pesoRoboCartaVirada;
	}

	public void setPesoHumanoCartaVirada(Double pesoHumanoCartaVirada) {
		this.pesoHumanoCartaVirada = pesoHumanoCartaVirada;
	}

	public void setPesoQuemPediuEnvido(Double pesoQuemPediuEnvido) {
		this.pesoQuemPediuEnvido = pesoQuemPediuEnvido;
	}

	public void setPesoQuemPediuFaltaEnvido(Double pesoQuemPediuFaltaEnvido) {
		this.pesoQuemPediuFaltaEnvido = pesoQuemPediuFaltaEnvido;
	}

	public void setPesoQuemPediuRealEnvido(Double pesoQuemPediuRealEnvido) {
		this.pesoQuemPediuRealEnvido = pesoQuemPediuRealEnvido;
	}

	public void setPesoPontosEnvidoRobo(Double pesoPontosEnvidoRobo) {
		this.pesoPontosEnvidoRobo = pesoPontosEnvidoRobo;
	}

	public void setPesoPontosEnvidoHumano(Double pesoPontosEnvidoHumano) {
		this.pesoPontosEnvidoHumano = pesoPontosEnvidoHumano;
	}

	public void setPesoQuemNegouEnvido(Double pesoQuemNegouEnvido) {
		this.pesoQuemNegouEnvido = pesoQuemNegouEnvido;
	}

	public void setPesoQuemGanhouEnvido(Double pesoQuemGanhouEnvido) {
		this.pesoQuemGanhouEnvido = pesoQuemGanhouEnvido;
	}

	public void setPesoTentosEnvido(Double pesoTentosEnvido) {
		this.pesoTentosEnvido = pesoTentosEnvido;
	}

	public void setPesoQuemFlor(Double pesoQuemFlor) {
		this.pesoQuemFlor = pesoQuemFlor;
	}

	public void setPesoQuemContraFlor(Double pesoQuemContraFlor) {
		this.pesoQuemContraFlor = pesoQuemContraFlor;
	}

	public void setPesoQuemContraFlorResto(Double pesoQuemContraFlorResto) {
		this.pesoQuemContraFlorResto = pesoQuemContraFlorResto;
	}

	public void setPesoQuemNegouFlor(Double pesoQuemNegouFlor) {
		this.pesoQuemNegouFlor = pesoQuemNegouFlor;
	}

	public void setPesoPontosFlorRobo(Double pesoPontosFlorRobo) {
		this.pesoPontosFlorRobo = pesoPontosFlorRobo;
	}

	public void setPesoPontosFlorHumano(Double pesoPontosFlorHumano) {
		this.pesoPontosFlorHumano = pesoPontosFlorHumano;
	}

	public void setPesoQuemGanhouFlor(Double pesoQuemGanhouFlor) {
		this.pesoQuemGanhouFlor = pesoQuemGanhouFlor;
	}

	public void setPesoTentosFlor(Double pesoTentosFlor) {
		this.pesoTentosFlor = pesoTentosFlor;
	}

	public void setPesoQuemEscondeuPontosEnvido(Double pesoQuemEscondeuPontosEnvido) {
		this.pesoQuemEscondeuPontosEnvido = pesoQuemEscondeuPontosEnvido;
	}

	public void setPesoQuemEscondeuPontosFlor(Double pesoQuemEscondeuPontosFlor) {
		this.pesoQuemEscondeuPontosFlor = pesoQuemEscondeuPontosFlor;
	}

	public void setPesoQuemTruco(Double pesoQuemTruco) {
		this.pesoQuemTruco = pesoQuemTruco;
	}

	public void setPesoQuandoTruco(Double pesoQuandoTruco) {
		this.pesoQuandoTruco = pesoQuandoTruco;
	}

	public void setPesoQuemRetruco(Double pesoQuemRetruco) {
		this.pesoQuemRetruco = pesoQuemRetruco;
	}

	public void setPesoQuandoRetruco(Double pesoQuandoRetruco) {
		this.pesoQuandoRetruco = pesoQuandoRetruco;
	}

	public void setPesoQuemValeQuatro(Double pesoQuemValeQuatro) {
		this.pesoQuemValeQuatro = pesoQuemValeQuatro;
	}

	public void setPesoQuandoValeQuatro(Double pesoQuandoValeQuatro) {
		this.pesoQuandoValeQuatro = pesoQuandoValeQuatro;
	}

	public void setPesoQuemNegouTruco(Double pesoQuemNegouTruco) {
		this.pesoQuemNegouTruco = pesoQuemNegouTruco;
	}

	public void setPesoQuemGanhouTruco(Double pesoQuemGanhouTruco) {
		this.pesoQuemGanhouTruco = pesoQuemGanhouTruco;
	}

	public void setPesoTentosTruco(Double pesoTentosTruco) {
		this.pesoTentosTruco = pesoTentosTruco;
	}

	public void setPesoTentosAnterioresRobo(Double pesoTentosAnterioresRobo) {
		this.pesoTentosAnterioresRobo = pesoTentosAnterioresRobo;
	}

	public void setPesoTentosAnterioresHumano(Double pesoTentosAnterioresHumano) {
		this.pesoTentosAnterioresHumano = pesoTentosAnterioresHumano;
	}

	public void setPesoTentosPosterioresRobo(Double pesoTentosPosterioresRobo) {
		this.pesoTentosPosterioresRobo = pesoTentosPosterioresRobo;
	}

	public void setPesoTentosPosterioresHumano(Double pesoTentosPosterioresHumano) {
		this.pesoTentosPosterioresHumano = pesoTentosPosterioresHumano;
	}

	public void setPesoRoboMentiuEnvido(Double pesoRoboMentiuEnvido) {
		this.pesoRoboMentiuEnvido = pesoRoboMentiuEnvido;
	}

	public void setPesoHumanoMentiuEnvido(Double pesoHumanoMentiuEnvido) {
		this.pesoHumanoMentiuEnvido = pesoHumanoMentiuEnvido;
	}

	public void setPesoRoboMentiuFlor(Double pesoRoboMentiuFlor) {
		this.pesoRoboMentiuFlor = pesoRoboMentiuFlor;
	}

	public void setPesoHumanoMentiuFlor(Double pesoHumanoMentiuFlor) {
		this.pesoHumanoMentiuFlor = pesoHumanoMentiuFlor;
	}

	public void setPesoRoboMentiuTruco(Double pesoRoboMentiuTruco) {
		this.pesoRoboMentiuTruco = pesoRoboMentiuTruco;
	}

	public void setPesoHumanoMentiuTruco(Double pesoHumanoMentiuTruco) {
		this.pesoHumanoMentiuTruco = pesoHumanoMentiuTruco;
	}

	public void setPesoQuemBaralho(Double pesoQuemBaralho) {
		this.pesoQuemBaralho = pesoQuemBaralho;
	}

	public Double getBluff1Success() {
		return bluff1Success;
	}

	public void setBluff1Success(Double bluff1Success) {
		this.bluff1Success = bluff1Success;
	}

	public Double getBluff2Success() {
		return bluff2Success;
	}

	public void setBluff2Success(Double bluff2Success) {
		this.bluff2Success = bluff2Success;
	}

	public Double getBluff3Success() {
		return bluff3Success;
	}

	public void setBluff3Success(Double bluff3Success) {
		this.bluff3Success = bluff3Success;
	}

	public Double getBluff4Success() {
		return bluff4Success;
	}

	public void setBluff4Success(Double bluff4Success) {
		this.bluff4Success = bluff4Success;
	}

	public Double getBluff5Success() {
		return bluff5Success;
	}

	public void setBluff5Success(Double bluff5Success) {
		this.bluff5Success = bluff5Success;
	}

	public Double getBluff6Success() {
		return bluff6Success;
	}

	public void setBluff6Success(Double bluff6Success) {
		this.bluff6Success = bluff6Success;
	}

	public Double getBluff1Failure() {
		return bluff1Failure;
	}

	public void setBluff1Failure(Double bluff1Failure) {
		this.bluff1Failure = bluff1Failure;
	}

	public Double getBluff2Failure() {
		return bluff2Failure;
	}

	public void setBluff2Failure(Double bluff2Failure) {
		this.bluff2Failure = bluff2Failure;
	}

	public Double getBluff3Failure() {
		return bluff3Failure;
	}

	public void setBluff3Failure(Double bluff3Failure) {
		this.bluff3Failure = bluff3Failure;
	}

	public Double getBluff4Failure() {
		return bluff4Failure;
	}

	public void setBluff4Failure(Double bluff4Failure) {
		this.bluff4Failure = bluff4Failure;
	}

	public Double getBluff5Failure() {
		return bluff5Failure;
	}

	public void setBluff5Failure(Double bluff5Failure) {
		this.bluff5Failure = bluff5Failure;
	}

	public Double getBluff6Failure() {
		return bluff6Failure;
	}

	public void setBluff6Failure(Double bluff6Failure) {
		this.bluff6Failure = bluff6Failure;
	}
}
