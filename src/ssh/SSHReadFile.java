/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssh;

/**
 *
 * @author gustavo
 */

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.*;

/**
 *
 * @author World
 */
public class SSHReadFile {

       
    public static void executaComando(String comando) {
    
          
        String user = "gustavo";
        String password = "start";
        String host = "127.0.0.1";
        int port = 22;
     
        String remoteFile = "/home/gustavo/testeExecutaComando.txt";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            ////System.out.println("Establishing Connection...");
            session.connect();
            ////System.out.println("Connection established.");
            ////System.out.println("Criando channel.");
            Channel channel = session.openChannel("exec");
            //((ChannelExec) channel).setCommand("su");
            //((ChannelExec) channel).setCommand(password);
            
            ((ChannelExec) channel).setCommand(comando);
            InputStream in = channel.getInputStream();
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();

            ////System.out.println("comando executado.");
            
            if(!channel.isClosed()) channel.disconnect();
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
