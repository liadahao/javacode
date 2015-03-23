package com.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MultiServerSocket {

	static int clientnum = 0;

	public static void main(String args[]) {
		int port = 9898;
		try {
			ServerSocket server = new ServerSocket(port);
			Boolean listerning = true;
			while (listerning) {
				new Thread(new ServerThread(server.accept(), clientnum))
						.start();
				clientnum++;
			}
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ServerThread implements Runnable {

	Socket socket = null;
	int clientnum;

	public ServerThread(Socket socket, int num) {
		this.socket = socket;
		this.clientnum = num;
	}

	@Override
	public void run() {

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			Scanner text = new Scanner(System.in);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			String message;
			while (true) {
				message = in.readLine();
				System.out.println("Client:" + clientnum + message);
				if ("exit".equals(message)) {
					break;
				}
				message = text.next();
				out.println(message);
				out.flush();

			}
			text.close();
			in.close();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
