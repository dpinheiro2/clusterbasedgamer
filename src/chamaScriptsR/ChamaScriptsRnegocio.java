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
		if(base.equalsIgnoreCase("imitacao")) caminhoFuncaoAuxiliar ="/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/utilitarios/funcoesAuxiliaresImitacao.R";
		else if(base.equalsIgnoreCase("ativo")) caminhoFuncaoAuxiliar ="/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/utilitarios/funcoesAuxiliaresAtivo.R";
		else caminhoFuncaoAuxiliar ="/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/utilitarios/funcoesAuxiliaresDefault.R";
		//pega o conteudo do arquivo de função auxiliar que deve ser utilizado
		String conteudoScriptFuncoesAuxiliares = lerArquivo(caminhoFuncaoAuxiliar);
		//conteudo elbow
		String caminhoElbow = "/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/utilitarios/UtilitariosElbowEgraficosFinal.R"; 
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
		String conteudoScriptTrucoPrimeiraCartaMao = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/truco/QuemTrucoPrimeiraMao.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTrucoPrimeiraCartaMao);
		
		//Execute primeira carta pé
		String conteudoScriptTrucoPrimeiraCartaPe = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/truco/QuemTrucoPrimeiraPe.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTrucoPrimeiraCartaPe);
		
		/*
		 * Segunda carta
		 */
		
		//Execute segunda carta ganhou a primeira
				String conteudoScriptTrucoSegundaCartaGanhouAnterior= lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/truco/QuemTrucoSegundaGanhouAnterior.R");
				execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTrucoSegundaCartaGanhouAnterior);
				
				//Execute segunda perdeu a primeira
				String conteudoScriptTrucoSegundaCartarooboPerdeuAnterior = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/truco/QuemTrucoSegundaPerdeuAnterior.R");
				execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTrucoSegundaCartarooboPerdeuAnterior);
				/*
				 * Terceira carta
				 */
				
				//Execute terceira carta ganhou a segunda
						String conteudoScriptQuemTrucoTerceiraCartaGanhouAsegunda= lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/truco/QuemTrucoTerceiraGanhouAnterior.R");
						execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptQuemTrucoTerceiraCartaGanhouAsegunda);
						
						//Execute terceira carta perdeu a segunda
						String conteudoScriptQuemTrucoTerceiraCartaPerdeuAsegunda = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/truco/QuemTrucoTerceiraPerdeuAnterior.R");
						execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptQuemTrucoTerceiraCartaPerdeuAsegunda );
				
				
	}
	
	private void executeScriptsCartas(String conteudoScriptFuncoesAuxiliares, String ScriptConteudoElbow) {
		/*
		 * PrimeiraCarta
		 */
		//Execute primeira carta mão
		String conteudoScriptPrimeiraCartaMao = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/cartas/scriptPrimeiraCartaRoboMao.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptPrimeiraCartaMao);
		
		//Execute primeira carta pé
		String conteudoScriptPrimeiraCartaPe = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/cartas/scriptPrimeiraCartaRoboEpe.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptPrimeiraCartaPe);
		
		/*
		 * Segunda carta
		 */
		
		//Execute segunda carta ganhou a primeira
				String conteudoScriptSegundaCartaGanhouAprimeira= lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/cartas/segundaCartaRoboGanhouAprimeira.R");
				execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptSegundaCartaGanhouAprimeira);
				
				//Execute segunda perdeu a primeira
				String conteudoScriptSegundaCartarooboPerdeuAprimeira = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/cartas/segundaCartaroboPerdeuAprimeiraScript.R");
				execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptSegundaCartarooboPerdeuAprimeira);
				/*
				 * Terceira carta
				 */
				
				//Execute terceira carta ganhou a segunda
						String conteudoScriptTerceiraCartaGanhouAsegunda= lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/cartas/scriptTerceiraCartaRoboGanhouAsegunda.R");
						execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTerceiraCartaGanhouAsegunda);
						
						//Execute terceira carta perdeu a segunda
						String conteudoScriptTerceiraCartaPerdeuAsegunda = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/cartas/scriptTerceiraCartaRoboPerdeuAsegunda.R");
						execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptTerceiraCartaPerdeuAsegunda );
				
				
	}
	
	private void executeScriptsPontos(String conteudoScriptFuncoesAuxiliares, String ScriptConteudoElbow) {
		//Execute indexacao jogadas
		String conteudoScriptEnvidoMao = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/envido/scriptQuemGanhouEnvidoAgenteMao.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptEnvidoMao);
		
		//Execute indexacaoPontos
		String conteudoScriptEnvidoPe = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/envido/scriptQuemGanhouEnvidoAgentePe.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptEnvidoPe);
	}
	
	private void executeScriptsIndexacao(String conteudoScriptFuncoesAuxiliares, String ScriptConteudoElbow) {
		//Execute indexacao jogadas
		String conteudoScriptIndexacaoJogada = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/indexacao/indexacao.R");
		execute.executeRscripts(conteudoScriptFuncoesAuxiliares, ScriptConteudoElbow, conteudoScriptIndexacaoJogada);
		
		//Execute indexacaoPontos
		String conteudoScriptIndexacaoPontos = lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/scriptsR/indexacao/indexacaoPontos.R");
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
