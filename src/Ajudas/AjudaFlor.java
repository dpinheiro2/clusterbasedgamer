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

public class AjudaFlor {

	boolean resposta;

	public boolean criaAlerta(String contentText, TrucoDescription gameStateRobo, String titleText) {

		//////System.out.println(gameStateRobo);

		ButtonType Sim = new ButtonType("SIM", ButtonBar.ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("NAO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alerta = new Alert(AlertType.WARNING,
				"The format for dates is year.month.day. " + "For example, today is .", Sim, bar);

		String PontosFlor = String.valueOf(gameStateRobo.getPontosFlorRobo());
		
		String TentosHumano = String.valueOf(gameStateRobo.getTentosAnterioresHumano());
		String TentosRobo = String.valueOf(gameStateRobo.getTentosAnterioresRobo());
		String QuemEnvido= String.valueOf(gameStateRobo.getQuemPediuEnvido());
		String QuemRealEnvido= String.valueOf(gameStateRobo.getQuemPediuRealEnvido());

		
		String Data = "";

		Data += "\n\n Pontos Flor = " + PontosFlor;
		

		if (gameStateRobo.getQuemPediuEnvido() != null) {
			Data += "\n Quem Pediu ENvido = " + QuemEnvido;
		}
		
		if (gameStateRobo.getQuemPediuRealEnvido() != null) {
			Data += "\n Quem Pediu Real ENvido = " + QuemRealEnvido;
		}
		
		if (gameStateRobo.getTentosAnterioresHumano() != null) {
			Data += "\n Tentos Robo = " + TentosRobo;
			Data += "\n Tentos Humano = " + TentosHumano;
		}

		alerta.setTitle(titleText);
		alerta.setHeaderText(contentText);
		alerta.setContentText(Data);

		alerta.setTitle(titleText);
		Optional<ButtonType> result = alerta.showAndWait();
//
		if (result.get().getButtonData() == Sim.getButtonData()) {
			resposta = true;
		} else {
			resposta = false;
		}

//		 //////System.out.println(" \n\n\n RESPOSTA ==" +resposta);

		return resposta;
	}

}
