package Ajudas;

import java.util.Optional;

import cbr.cbrDescriptions.TrucoDescription;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AjudaPontos {

	boolean resposta;

	public boolean criaAlerta(String contentText, TrucoDescription gameStateRobo, String titleText) {

		ButtonType Sim = new ButtonType("SIM", ButtonBar.ButtonData.OK_DONE);
		ButtonType Nao = new ButtonType("NAO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alerta = new Alert(AlertType.WARNING,
				"The format for dates is year.month.day. " + "For example, today is .", Sim, Nao);

		String PontosEnvido = String.valueOf(gameStateRobo.getPontosEnvidoRobo());

		int PrimeiraJogadaOponente;
		if (gameStateRobo.getPrimeiraCartaHumano() != null)
			PrimeiraJogadaOponente = gameStateRobo.getPrimeiraCartaHumano();
		else
			PrimeiraJogadaOponente = 0;

		String JogadorMao = String.valueOf(gameStateRobo.getJogadorMao());
		String PrimeiraCartaOponente = new ConsultaCarta().ConsultaCartaPeloId(PrimeiraJogadaOponente);
		String TentosHumano = String.valueOf(gameStateRobo.getTentosAnterioresHumano());
		String TentosRobo = String.valueOf(gameStateRobo.getTentosAnterioresRobo());

		String PediuEnvido = String.valueOf(gameStateRobo.getQuemPediuEnvido());
		String PediuRealEnvido = String.valueOf(gameStateRobo.getQuemPediuRealEnvido());

		
		String Data = "";
		Data += "\n\n Jogador Mao = "+ JogadorMao;
		
		Data += "\n\n Pontos Envido = " + PontosEnvido;
		Data += "\n\n Primeira Carta Oponente = " + PrimeiraCartaOponente;
		
		if(gameStateRobo.getQuemPediuEnvido() != null) 
			Data+="\n\n Pediu Envido = " + PediuEnvido;
		if(gameStateRobo.getQuemPediuRealEnvido() != null) 
			Data+="\n\n Pediu Real Envido = " + PediuRealEnvido;		
		
		
		if (gameStateRobo.getTentosAnterioresHumano() != null) {
			Data += "\n\n Tentos  = " + TentosRobo;
			Data += "\n Tentos Oponente= " + TentosHumano;
		}

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
