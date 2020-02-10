package utils.debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class criaArquivosDebug {

	public void criaArquivo(String titulo, String conteudo) {
		
		/*
		try {
			String conteudoTotalArquivo = "";
			File file = new File("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/utils/debug/"+titulo);
			
			if (file.exists()) {
				conteudoTotalArquivo =lerArquivo("/home/gustavo/Mestrado/CodigoDissertacao/clusterBasedGamer/src/utils/debug/"+titulo);
				conteudoTotalArquivo += conteudo;
			}
			else   file.createNewFile();
            
			 // Prepara para escrever no arquivo
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            // Escreve e fecha arquivo
            bw.write(conteudoTotalArquivo);
            bw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    **/
	}
	public String lerArquivo(String caminhoCompleto) {
		 Path path = Paths.get(caminhoCompleto);
		 String conteudo = null;
	        List<String> linhasArquivo;
			try {
				linhasArquivo = Files.readAllLines(path);
				for (String linha : linhasArquivo) {
		           conteudo +=  linha;
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conteudo;
	        
	}
	
}
