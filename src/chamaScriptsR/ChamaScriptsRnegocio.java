package chamaScriptsR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ChamaScriptsRnegocio {

	private ExecuteScripts execute = new ExecuteScripts();

	public void controlaExecucaoTodosOsScripts(String base) {
		String caminhoFuncaoAuxiliar ="";
		if(base.equalsIgnoreCase("imitacao")) caminhoFuncaoAuxiliar ="C:/ufsm/scriptsR/utilitarios/funcoesAuxiliaresImitacao.R";
		else if(base.equalsIgnoreCase("ativo")) caminhoFuncaoAuxiliar ="C:/ufsm/scriptsR/utilitarios/funcoesAuxiliaresAtivo.R";
		else caminhoFuncaoAuxiliar ="C:/ufsm/scriptsR/utilitarios/funcoesAuxiliaresDefault.R";
		//pega o conteudo do arquivo de função auxiliar que deve ser utilizado
		String conteudoScriptFuncoesAuxiliares = lerArquivo(caminhoFuncaoAuxiliar);
		//conteudo elbow
		String caminhoElbow = "C:/ufsm/scriptsR/utilitarios/UtilitariosElbowEgraficosFinal.R";
 		String conteudoScriptElbow = lerArquivo(caminhoElbow);
 		
 		 executeScriptsIndexacao(conteudoScriptFuncoesAuxiliares, conteudoScriptElbow);
 		
 		 executeScriptsPontos(conteudoScriptFuncoesAuxiliares, conteudoScriptElbow);
 		
 		 executeScriptsCartas(conteudoScriptFuncoesAuxiliares, conteudoScriptElbow);
 		
 		executeScriptsTruco(conteudoScriptFuncoesAuxiliares, conteudoScriptElbow);
	}
	
	private void executeScriptsTruco(String conteudoScriptFuncoesAuxiliares, String ScriptConteudoElbow) {
		/*
		 * PrimeiraCarta
		 */
		//Execute primeira carta mão
		String conteudoScriptTrucoPrimeiraCartaMao = lerArquivo("C:/ufsm/scriptsR/truco/QuemTrucoPrimeiraMao.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTrucoPrimeiraCartaMao);
		
		//Execute primeira carta pé
		String conteudoScriptTrucoPrimeiraCartaPe = lerArquivo("C:/ufsm/scriptsR/truco/QuemTrucoPrimeiraPe.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTrucoPrimeiraCartaPe);
		
		/*
		 * Segunda carta
		 */
		
		//Execute segunda carta ganhou a primeira
				String conteudoScriptTrucoSegundaCartaGanhouAnterior= lerArquivo("C:/ufsm/scriptsR/truco/QuemTrucoSegundaGanhouAnterior.R");
				execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTrucoSegundaCartaGanhouAnterior);
				
				//Execute segunda perdeu a primeira
				String conteudoScriptTrucoSegundaCartarooboPerdeuAnterior = lerArquivo("C:/ufsm/scriptsR/truco/QuemTrucoSegundaPerdeuAnterior.R");
				execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTrucoSegundaCartarooboPerdeuAnterior);
				/*
				 * Terceira carta
				 */
				
				//Execute terceira carta ganhou a segunda
						String conteudoScriptQuemTrucoTerceiraCartaGanhouAsegunda= lerArquivo("C:/ufsm/scriptsR/truco/QuemTrucoTerceiraGanhouAnterior.R");
						execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptQuemTrucoTerceiraCartaGanhouAsegunda);
						
						//Execute terceira carta perdeu a segunda
						String conteudoScriptQuemTrucoTerceiraCartaPerdeuAsegunda = lerArquivo("C:/ufsm/scriptsR/truco/QuemTrucoTerceiraPerdeuAnterior.R");
						execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptQuemTrucoTerceiraCartaPerdeuAsegunda );
				
				
	}
	
	private void executeScriptsCartas(String conteudoScriptFuncoesAuxiliares, String ScriptConteudoElbow) {
		/*
		 * PrimeiraCarta
		 */
		//Execute primeira carta mão
		String conteudoScriptPrimeiraCartaMao = lerArquivo("C:/ufsm/scriptsR/cartas/scriptPrimeiraCartaRoboMao.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptPrimeiraCartaMao);
		
		//Execute primeira carta pé
		String conteudoScriptPrimeiraCartaPe = lerArquivo("C:/ufsm/scriptsR/cartas/scriptPrimeiraCartaRoboEpe.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptPrimeiraCartaPe);
		
		/*
		 * Segunda carta
		 */
		
		//Execute segunda carta ganhou a primeira
				String conteudoScriptSegundaCartaGanhouAprimeira= lerArquivo("C:/ufsm/scriptsR/cartas/segundaCartaRoboGanhouAprimeira.R");
				execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptSegundaCartaGanhouAprimeira);
				
				//Execute segunda perdeu a primeira
				String conteudoScriptSegundaCartarooboPerdeuAprimeira = lerArquivo("C:/ufsm/scriptsR/cartas/segundaCartaroboPerdeuAprimeiraScript.R");
				execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptSegundaCartarooboPerdeuAprimeira);
				/*
				 * Terceira carta
				 */
				
				//Execute terceira carta ganhou a segunda
						String conteudoScriptTerceiraCartaGanhouAsegunda= lerArquivo("C:/ufsm/scriptsR/cartas/scriptTerceiraCartaRoboGanhouAsegunda.R");
						execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTerceiraCartaGanhouAsegunda);
						
						//Execute terceira carta perdeu a segunda
						String conteudoScriptTerceiraCartaPerdeuAsegunda = lerArquivo("C:/ufsm/scriptsR/cartas/scriptTerceiraCartaRoboPerdeuAsegunda.R");
						execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTerceiraCartaPerdeuAsegunda );
				
				
	}
	
	private void executeScriptsPontos(String conteudoScriptFuncoesAuxiliares, String ScriptConteudoElbow) {
		//Execute indexacao jogadas
		String conteudoScriptEnvidoMao = lerArquivo("C:/ufsm/scriptsR/envido/scriptQuemGanhouEnvidoAgenteMao.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptEnvidoMao);
		
		//Execute indexacaoPontos
		String conteudoScriptEnvidoPe = lerArquivo("C:/ufsm/scriptsR/envido/scriptQuemGanhouEnvidoAgentePe.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptEnvidoPe);
	}
	
	private void executeScriptsIndexacao(String conteudoScriptFuncoesAuxiliares, String ScriptConteudoElbow) {
		//Execute indexacao jogadas
		String conteudoScriptIndexacaoJogada = lerArquivo("C:/ufsm/scriptsR/indexacao/indexacao.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptIndexacaoJogada);
		
		//Execute indexacaoPontos
		String conteudoScriptIndexacaoPontos = lerArquivo("C:/ufsm/scriptsR/indexacao/indexacaoPontos.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptIndexacaoPontos);
	}
	
	private String lerArquivo(String caminhoCompleto) {
		 Path path = Paths.get(caminhoCompleto);
		 String conteudo ="";
	        List<String> linhasArquivo;
			try {
				linhasArquivo = Files.readAllLines(path);
				for (String linha : linhasArquivo) {
		           conteudo += "\n" + linha;
		        }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conteudo;
	        
	}
}
