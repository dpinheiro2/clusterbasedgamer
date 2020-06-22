package chamaScriptsR;

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


public class ExecuteScripts {

	public void  executeRscripts(String ScriptFuncaoAuxiliar,String elbow, String scriptParaserExecutado)  {
		//scripts preleminares
		//executa primeiro para o R não solicitar permissão para concluir
		
				
		
		Runtime rt = Runtime.getRuntime();
		   
		   Process p = null;
		  
		   
		   String scriptCompletoAserExecutado = ScriptFuncaoAuxiliar + elbow +scriptParaserExecutado + "\n"+" exit <- function() { q(\"no\") }"+ "\n exit()";
			
		   
			
		   try {
			   File file = new File("C:/ufsm/scriptsR/comando.R");
				if(file.exists()) file.delete();  
			   file.createNewFile();
	           
				 // Prepara para escrever no arquivo
	           FileWriter fw = new FileWriter(file.getAbsoluteFile());
	           BufferedWriter bw = new BufferedWriter(fw);
	           
	           // Escreve e fecha arquivo
	           bw.write(scriptCompletoAserExecutado);
	           bw.close();
	           
	           
			   p = rt.exec("C:/Program Files/R/R-3.6.1/bin/x64/Rscript.exe C:/ufsm/scriptsR/comando.R");
			   
			   BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			   String line;
			   while ((line = reader.readLine()) != null)
			      //System.out.println("linha lida: " + line);
			
			 p.waitFor();
			 
			  
			   
		    
			System.out.println("done K");
			
			
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
 
			
			
			String s;
			
			// read any errors from the attempted command
			//System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				//System.out.println(s);
			}
 
			
			
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   finally {
			p.destroy();
		}

     //System.out.println("veio aqui final");
	}
	
}
