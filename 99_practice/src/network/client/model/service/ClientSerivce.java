package network.client.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientSerivce {
	
	public void service() {
		// 1. 참조변수 생성
		// 1-1. 아이피와 포트 지정
		String serverIP = "127.0.0.1";
		int port = 7700;
		
		// 1-2. 클라이언트의 소켓 변수 작성
		Socket clientSocket = null;
		
		// 1-3. 스트림 변수 작성
		InputStream is = null;
		OutputStream os = null;
		
		// 1-4. 보조 스트림 변수 작성
		BufferedReader br = null;
		PrintWriter pw = null;
		
		
		try {
			// 2. 클라이언트와 서버를 연결
			// 2-1. 클라이언트 객체를 생성
			clientSocket = new Socket(serverIP, port);
			
			// 만약 clientSocket이 null이 아니라면(연결이 되었다면)
			if(clientSocket != null) {
				// 입출력 스트림에 값을 받아옴
				is = clientSocket.getInputStream();
				os = clientSocket.getOutputStream();
				
				// 보조 스트림을 사용
				br = new BufferedReader(new InputStreamReader(is));
				pw = new PrintWriter(os);
				
				
				// 스트림을 이용해 읽고 쓰기
				String serverMessage = br.readLine();
				System.out.println(serverMessage);
				
				// 스트림을 이용해 메세지 입력
				Scanner sc = new Scanner(System.in);
				
				String str = "";
					
				System.out.print("메세지 입력 : ");
				str = sc.nextLine();
				
				pw.println(str);
				pw.flush();
			} else {
				System.out.println("연결 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					System.out.println("접속이 해제되었습니다.");
					if(br != null) br.close();
					if(pw != null) pw.close();
					
					if(clientSocket != null) clientSocket.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	public void service2() {
		
		// 아이피 및 포트번호 설정
		String IP = "127.0.0.1";
		int port = 7878;
		
		
		// 변수 선언
		Socket clientSocket = null;
		
		// 스트림 선언
		InputStream is = null;
		OutputStream os = null;
		
		// 보조 스트림 선언
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 소켓 연결
			System.out.println("[클라이언트]");
			clientSocket = new Socket(IP, port);
			if(clientSocket != null) {
				System.out.println("[서버 연결 성공]");
				
				// 메인 스트림 연결
				is = clientSocket.getInputStream();
				os = clientSocket.getOutputStream();
				
				// 보조스트림 사용
				br = new BufferedReader(new InputStreamReader(is));
				pw = new PrintWriter(os);
				
				// 서버에서 받아온 값 출력
				System.out.println(br.readLine());
				
				// 서버로 메세지 전송
				Scanner sc = new Scanner(System.in);
				
				System.out.println("메세지 입력 : ");
				String message = sc.nextLine();
				
				pw.println(message);
				pw.flush();
				System.out.println("메세지가 전송되었습니다.");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) br.close();
				if(pw != null) pw.close();
				if(clientSocket != null) clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
