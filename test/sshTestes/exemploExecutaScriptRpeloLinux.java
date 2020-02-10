package sshTestes;

import ssh.SSHReadFile;

public class exemploExecutaScriptRpeloLinux {

	public static void main(String[] args) {
		//se não funcionar precisa executar o outro scripto das funcoes auxiliares primeiro
		new SSHReadFile().executaComando("Rscript /home/gustavo/testes/testeR.R");
		//criar um arquivo .sh para executar todos scripts de uma vez só para cada propósito, no inicio precisa executar também a conexão ao banco(jogada, contraFlor e pontos)

	}

}
