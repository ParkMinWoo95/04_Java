package serction04.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharService {

	/* [문자 기반 스트림]
	 * 
	 * - 2byte 문자(char) 단위로 입/출력하는 스트림
	 * - 문자만 작성된 파일(txt, bin 등), 채팅, JSON, 웹 요청(주소)/응답(HTML)
	 * - 문자 기반 스트림 최상위 인터페이스 : Reader, Writer
	 */
	
	// 문자 기반 스트림을 이용한 파일 출력
	public void fileOutput1() {
		
		FileWriter fw = null;
		
		try {
			String content = """
					널 볼 수 있어 행복했었지
그대가 날 위해 있어 준 시간만큼
너의 부모님께 전해 들었지
나 아닌 사람과 결혼하게 된 걸
너 그렇게 힘든데
내게 말 못 하고 울고 있던 게 생각나
떠나는 그대여 울지 말아요, 슬퍼 말아요
내가 단념할게요
마음 편히 가시도록
내 사랑 그대가 날 떠나 행복할 수 있다면
내가 떠나갈게요
나의 그대 삶에 축복을
너무 걱정 마 철없던 내가
너 없인 무엇도 할 수는 없지만
넌 널 위해 살아줘
나는 어떻게든 살아갈 수 있을 거야
떠나는 그대여 울지 말아요, 슬퍼 말아요
내가 단념할게요
마음 편히 가시도록
내 사랑 그대가 날 떠나 행복할 수 있다면
내가 떠나갈게요
그대 만나 느낀 기억도
내가 가질 행복도
모두 가져가세요
나의 그대 삶에 축복을
					""";
			
			String path = "io_test/char";
			File folder = new File(path);
			
			if(!folder.exists()) folder.mkdirs();
			
			// 문자 기반 파일 출력 스트림 객체 생성
			fw = new FileWriter(folder + "/" + "이미슬픈사랑.txt");
			
			// 문자 기반 스트림을 이용해서 출력하기
			fw.write(content); // String, char[] 출력을 위한 기능이 제공
			 
			// 스트림에 남은 데이터를 밀어냄
			fw.flush(); // close() 시 자동 수행됨
			
			System.out.println("출력 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null) fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fileOutput2() {
		
		FileWriter fw = null;
		BufferedWriter bw = null; // 버퍼를 이용한 라이팅
		
		try {
			String content = """
					널 볼 수 있어 행복했었지
그대가 날 위해 있어 준 시간만큼
너의 부모님께 전해 들었지
나 아닌 사람과 결혼하게 된 걸
너 그렇게 힘든데
내게 말 못 하고 울고 있던 게 생각나
떠나는 그대여 울지 말아요, 슬퍼 말아요
내가 단념할게요
마음 편히 가시도록
내 사랑 그대가 날 떠나 행복할 수 있다면
내가 떠나갈게요
나의 그대 삶에 축복을
너무 걱정 마 철없던 내가
너 없인 무엇도 할 수는 없지만
넌 널 위해 살아줘
나는 어떻게든 살아갈 수 있을 거야
떠나는 그대여 울지 말아요, 슬퍼 말아요
내가 단념할게요
마음 편히 가시도록
내 사랑 그대가 날 떠나 행복할 수 있다면
내가 떠나갈게요
그대 만나 느낀 기억도
내가 가질 행복도
모두 가져가세요
나의 그대 삶에 축복을
					""";
			
			String path = "io_test/char";
			File folder = new File(path);
			
			if(!folder.exists()) folder.mkdirs();
			
			// 문자 기반 파일 출력 스트림 객체 생성
			fw = new FileWriter(folder + "/" + "이미슬픈사랑_buffer.txt");
			
			// 보조 스트림 객체 생성
			bw = new BufferedWriter(fw);
			
			// 문자 기반 스트림을 이용해서 출력하기
			bw.write(content); // String, char[] 출력을 위한 기능이 제공
			 
			// 스트림에 남은 데이터를 밀어냄
			bw.flush(); // close() 시 자동 수행됨
			
			System.out.println("출력 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 문자 기반 입력 스트림 + 보조 스트림 이용해서 파일 읽어오기
	public void fileInput() {
		
		/* [try - with - resource]
		 * - 자바 7버전에서 추가된 기능
		 * - finally에서 작성하던 close 처리 구문을 자동으로 수행하도록 만드는 구문
		 */
		String path = "io_test/char/이미슬픈사랑.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			// 보조 스트림을 이용해서 파일 내용 읽어오기
			
			StringBuilder sb = new StringBuilder(); // 읽어온 내용 누적
			String temp = null; // 임시 변수
			
			while(true) {
				temp = br.readLine(); // 한 줄씩 읽어오기
				
				if(temp == null) break; // 읽어온 내용이 없으면 반복 종료
				
				sb.append(temp); // 읽어온 내용 누적(엔터 미포함)
				sb.append("\n"); // 엔터 추가
				
			}
			
			System.out.println(("읽어오기 성공!"));
			System.out.println("------------------------");
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// finally에서 close하던 구문 생략 가능
	}
	
	// Scanner 대신 스트림을 이용한 문자열 입력 받기
	// -> Scanner가 편리하긴 한데 매우 느림
	// 키보드 입력 -> 바이트(2진수) 코드 입력 -> 문자 변환
	
	public void keyboardInput() {
		
		// System.in : 기본 장치(키보드)와 연결된 InputStream 반환
		// InputStreamReader : 바이트 스트림 -> 문자 스트림 변환
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			
			System.out.print("문자열 입력1 : ");
			String input1 = br.readLine(); // 입력된 한 줄 읽어오기
			
			System.out.print("문자열 입력2 : ");
			String input2 = br.readLine(); // 입력된 한 줄 읽어오기
			
			System.out.println("input1 : " + input1);
			System.out.println("input2 : " + input2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
