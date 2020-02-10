package backupExecuteScriptsR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ssh.SSHReadFile;


public class ExecuteScriptJogadaR {

	public void  executeRscriptsCarta()  {
		//scripts preleminares
		//executa primeiro para o R não solicitar permissão para concluir
		
				
		
		Runtime rt = Runtime.getRuntime();
		   
		   Process p = null;
		   String scriptFuncoesAuxiliares = "/home/gustavo/teste/scriptTeste.R";
		   String conteudoFuncoesAuxiliares  = lerArquivo(scriptFuncoesAuxiliares);
		   
		   String arquivoExecucao = "/home/gustavo/teste/scriptTeste2.R";
		   String conteudoExecucao = lerArquivo(arquivoExecucao);
		   
		   String scriptCompletoAserExecutado = conteudoFuncoesAuxiliares + conteudoExecucao + "\n"+" exit <- function() { q(\"no\") }"+ "\n exit()";
			
		   
			
		   ////System.out.println(scriptCompletoAserExecutado);
		   try {
			   File file = new File("/home/gustavo/teste/comando.R");
				  file.createNewFile();
	           
				 // Prepara para escrever no arquivo
	           FileWriter fw = new FileWriter(file.getAbsoluteFile());
	           BufferedWriter bw = new BufferedWriter(fw);
	           
	           // Escreve e fecha arquivo
	           bw.write(scriptCompletoAserExecutado);
	           bw.close();
	           
	           
			   p = rt.exec("Rscript /home/gustavo/teste/comando.R");
			   
			   BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			   String line;
			   while ((line = reader.readLine()) != null)
			       ////System.out.println("linha lida: " + line);
			
			 p.waitFor();
			 
			  
			   
		    
			////System.out.println("done");
			/*
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
 
			
			
			String s;
			
			// read any errors from the attempted command
			////System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				////System.out.println(s);
			}
 
			*/
			
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   finally {
			p.destroy();
		}

     ////System.out.println("veio aqui final");
	}
	public String lerArquivo(String caminhoCompleto) {
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
