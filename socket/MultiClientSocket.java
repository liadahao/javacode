package com.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClientSocket {
	
	public static void main(String args[]) {
		Socket client = null;
		try {
			client = new Socket("127.0.0.1", 9898);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			Scanner text = new Scanner(System.in);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					client.getOutputStream()));
			String s;
			while (true) {
				s = text.next();
				out.println(s);
				out.flush();
				if (s.equals("exit"))
					break;
				s = in.readLine();
				System.out.println("Server:" + s);
				if (s.equals("exit"))
					break;
			}
			text.close();
			in.close();
			out.close();
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
