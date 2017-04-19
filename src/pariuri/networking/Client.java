package pariuri.networking;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import pariuri.display.ClientDisplay;
import pariuri.display.Display;
import pariuri.display.ServerDisplay;
import pariuri.logic.Game;

public class Client {



	private static ClientDisplay display = null;
	private static int serverBetCounter = 0;
	private static int clientBetCounter = 0;
	
	private ArrayList <Game> serverGames;
	private ArrayList <Game> clientGames;

	public static int modify(){
		if(serverBetCounter != clientBetCounter){
			if(serverBetCounter > clientBetCounter)
				return 1;
			else return 2;
		}
		return 0;
	}
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					display = new ClientDisplay();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		try{
			Socket socket = new Socket("localhost",4444);
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

		public void addGamesToList(ObjectInputStream in){
			try {
				int count = in.readInt();
				if(count > 0){
					ArrayList <Game> games = new ArrayList<Game>();
					for(int i = 0 ; i < count ; ++i)
						games.add((Game) in.readObject());
					display.setGames(games);
				} 


			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run(){

			boolean running = true;
			ObjectInputStream in = null;
			try{
				in = new ObjectInputStream(socket.getInputStream());
				while(running){
					serverBetCounter = in.readInt();
					Thread.sleep(100);
					switch(modify()){
					case 1:
						addGamesToList(in);
						break;
					case 2:
						removeGamesFromList(in);
						break;
					default:
						break;

					}
					Thread.sleep(100);
				}
			}catch(Exception e){
				e.printStackTrace();

			}finally{
				try{
					in.close();
				}catch(Exception e){

				}try{
					socket.close();
				}catch(Exception e){
				}
			}
		}
		public void removeGamesFromList(ObjectInputStream in){
			try {
				int count = in.readInt();
				ArrayList <Game> serverGames = new ArrayList<Game>();
				for(int i = 0 ; i < count ; ++i)
					serverGames.add((Game) in.readObject());
				display.removeAllAndAdd(serverGames);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public static class OutputHandler implements Runnable{

		private Socket socket;

		public OutputHandler(Socket socket){
			this.socket = socket;
		}

		public void updateCounter(){
			if(display != null)
				clientBetCounter = display.getGames().size();
			else clientBetCounter = 0;
		}

		public void sendClientCounter(ObjectOutputStream out) throws IOException{
			out.writeInt(clientBetCounter);
			out.flush();
		}

		@Override
		public void run(){
			boolean running = true;
			ObjectOutputStream out = null;
			boolean ok = true;
			try{
				out = new ObjectOutputStream(socket.getOutputStream());
				while(running){
					updateCounter();	
					sendClientCounter(out);
					Thread.sleep(100);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					out.close();
				}catch(Exception e){

				}
				try{
					socket.close();
				}catch(Exception e1){

				}
			}


		}
	}
}
