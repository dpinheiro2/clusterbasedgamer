package Ajudas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cbr.cbrDescriptions.TrucoDescription;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;

public class AjudaCartaVirada {

	boolean resposta = false;

	public boolean criaAlerta(String contentText, TrucoDescription gameStateRobo, String titleText) {

		ButtonType Sim = new ButtonType("SIM", ButtonBar.ButtonData.OK_DONE);
		ButtonType Nao = new ButtonType("NAO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alerta = new Alert(AlertType.WARNING,
				"The format for dates is year.month.day. " + "For example, today is .", Sim, Nao);

		String CartaAlta = new ConsultaCarta().ConsultaCartaPeloId(gameStateRobo.getCartaAltaRobo());
		String CartaMedia = new ConsultaCarta().ConsultaCartaPeloId(gameStateRobo.getCartaMediaRobo());
		String CartaBaixa = new ConsultaCarta().ConsultaCartaPeloId(gameStateRobo.getCartaBaixaRobo());

		int PrimeiraJogada;
		int PrimeiraJogadaOponente;
		int SegundaJogada;
		int SegundaJogadaOponente;
		int TerceiraJogada;
		int TerceiraJogadaOponente;

		if (gameStateRobo.getPrimeiraCartaRobo() != null)
			PrimeiraJogada = gameStateRobo.getPrimeiraCartaRobo();
		else
			PrimeiraJogada = 0;

		if (gameStateRobo.getPrimeiraCartaHumano() != null)
			PrimeiraJogadaOponente = gameStateRobo.getPrimeiraCartaHumano();
		else
			PrimeiraJogadaOponente = 0;

		if (gameStateRobo.getSegundaCartaRobo() != null)
			SegundaJogada = gameStateRobo.getSegundaCartaRobo();
		else
			SegundaJogada = 0;

		if (gameStateRobo.getSegundaCartaHumano() != null)
			SegundaJogadaOponente = gameStateRobo.getSegundaCartaHumano();
		else
			SegundaJogadaOponente = 0;

		if (gameStateRobo.getTerceiraCartaRobo() != null)
			TerceiraJogada = gameStateRobo.getTerceiraCartaRobo();
		else
			TerceiraJogada = 0;

		if (gameStateRobo.getTerceiraCartaHumano() != null)
			TerceiraJogadaOponente = gameStateRobo.getTerceiraCartaHumano();
		else
			TerceiraJogadaOponente = 0;

		String PrimeiraCarta = new ConsultaCarta().ConsultaCartaPeloId(PrimeiraJogada);
		String PrimeiraCartaOponente = new ConsultaCarta().ConsultaCartaPeloId(PrimeiraJogadaOponente);

		String SegundaCarta = new ConsultaCarta().ConsultaCartaPeloId(SegundaJogada);
		String SegundaCartaOponente = new ConsultaCarta().ConsultaCartaPeloId(SegundaJogadaOponente);

		String GanhadorPrimeiraRodada = String.valueOf(gameStateRobo.getGanhadorPrimeiraRodada());
		String GanhadorSegundaRodada = String.valueOf(gameStateRobo.getGanhadorSegundaRodada());

		String TerceiraCarta = new ConsultaCarta().ConsultaCartaPeloId(TerceiraJogada);
		String TerceiraCartaOponente = new ConsultaCarta().ConsultaCartaPeloId(TerceiraJogadaOponente);

		String Data = "";

		Data += "\n Carta Alta = " + CartaAlta;
		Data += "\n Carta Media = " + CartaMedia;
		Data += "\n Carta Baixa = " + CartaBaixa;

		Data += "\n\n Primeira Carta = " + PrimeiraCarta;
		Data += "\n PrimeiraCarta Oponente = " + PrimeiraCartaOponente;

		Data += "\n\n Segunda Carta = " + SegundaCarta;
		Data += "\n Segunda Carta Oponente = " + SegundaCartaOponente;

		Data += "\n\n Terceira Carta = " + TerceiraCarta;
		Data += "\n Terceira Carta Oponente = " + TerceiraCartaOponente;

		Data += "\n\n Ganhador Primeira Rodada = " + GanhadorPrimeiraRodada;
		Data += "\n Ganhador Segunda Rodada = " + GanhadorSegundaRodada;

		alerta.setTitle(titleText);
		alerta.setHeaderText(contentText);
		alerta.setContentText(Data);

		alerta.setTitle(titleText);

		Optional<ButtonType> result = alerta.showAndWait();
		if (result.get().getButtonData() == Sim.getButtonData()) {
			resposta = true;
		} else {
			resposta = false;
		}

		return resposta;
	}

}
