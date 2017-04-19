package pariuri.networking;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import pariuri.display.*;
import pariuri.logic.Game;

public class Server {

	public final static int port = 4444;
	private static ServerDisplay display = null;
	public static boolean modified = false;

	private static int serverBetCounter = 0;
	private static int clientBetCounter = 0;
	
	private static ArrayList <Game> clientGames;
	private static ArrayList <Game> serverGames;

	public static int modify(){
		if(serverBetCounter != clientBetCounter){
			if(serverBetCounter > clientBetCounter)
				return 1;
			else return 2;
		}
		return 0;

	}

	public static void main (String args[]) throws IOException{

		try{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					display = new ServerDisplay();
				}

			});

		}catch(Exception e){
			e.printStackTrace();
		}

		try{	
			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = serverSocket.accept();
			new Thread(new InputHandler(socket)).start();
			new Thread(new OutputHandler(socket)).start();
		}catch(Exception e){
			e.printStackTrace();
		}


	}

	private static class InputHandler implements Runnable{

		private Socket socket;
		public InputHandler(Socket socket){
			this.socket = socket;
		}

		@Override
		public void run(){
			boolean running = true;
			ObjectInputStream in = null;
			try{
				in = new ObjectInputStream(socket.getInputStream());
				while(running){
					clientBetCounter = in.readInt();
					Thread.sleep(100);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					in.close();
				}catch(Exception e){
					{
					}
					try{
						socket.close();
					}catch(Exception e1){

					}
				}
			}

		}
	}

	private static class OutputHandler implements Runnable{

		private Socket socket;

		public OutputHandler(Socket socket){
			this.socket = socket;
		}
		public void sendGamesList(ObjectOutputStream out){
			try {
				ArrayList <Game> games = display.getGames();
				int count = games.size();
				out.writeInt(count);
				if(count > 0)
					for(int i = 0 ; i < count ; ++i)
						out.writeObject(games.get(i));
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		public void updateCounter(){
			if(display == null)
				serverBetCounter = 0;
			else{
				ArrayList<Game> games = display.getGames();
				serverBetCounter = games.size();
			}
		}
		public void sendServerCounter(ObjectOutputStream out) throws IOException{
			out.writeInt(serverBetCounter);
			out.flush();
		}
		
		public void clientUpdate(ObjectOutputStream out, int option){
			switch(option){
			case 1:
				sendGamesList(out);
				break;
			case 2:
				sendGamesList(out);
				break;
			default: 
				break;
			}
		}
		@Override
		public void run(){
			boolean running = true;
			ObjectOutputStream out = null;
			try{
				out = new ObjectOutputStream(socket.getOutputStream());
				while(true){
					updateCounter();
					sendServerCounter(out);
					Thread.sleep(100);
					switch(modify()){
					case 1: 
						clientUpdate(out,1);
						break;
					case 2:
						clientUpdate(out,2);
						break;
					default:
						break;
					}
					Thread.sleep(100);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					out.close();
				}
				catch(Exception e){

				}
				try{
					socket.close();
				}catch(Exception e){


				}
			}
		}
	}
}



