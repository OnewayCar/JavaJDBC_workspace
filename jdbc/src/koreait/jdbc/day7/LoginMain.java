package koreait.jdbc.day7;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

import com.google.common.hash.Hashing;

import koreait.jdbc.day5.JCustom;

public class LoginMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String id, pw;
		int cntErr = 0; // 로그인 실패 횟수를 count할 변수
		JCustomerDao2 dao = JCustomerDao2.getJCustomerDao2(); // 싱글톤 클래스, JCustomerDao2 객체 생성
		JCustom customer1 = null; // login() 반환 결과를 담을 JCustom 객체 준비

		System.out.println(":::::::회원 로그인:::::::");
		while (true) {
			System.out.print("아이디를 입력하세요 >>> ");
			id = sc.nextLine();
			System.out.print("비밀번호를 입력하세요 >>> ");
			pw = Hashing.sha256().hashString(sc.nextLine(), StandardCharsets.UTF_8).toString(); // String 타입 비밀번호 입력 받음과 동시에 해시코드로 변환하여 저장

			// 입력받은 아이디와 비밀번호를 가지고 로그인 시도
			try {
				customer1 = dao.login(id, pw);
			} catch (SQLException e) {
				System.out.println("로그인 간 예외 발생 : " + e.getMessage());
			} // ~로그인 시도

			// 로그인 성공시 처리
			if (customer1 != null) {
				System.out.println("로그인 성공했습니다. " + customer1.getName() + "님 환영합니다."); // 로그인 성공시 문구
				break;
			} // ~로그인 성공시 처리
			
			// 로그인 실패시 처리
			else {
				cntErr++;
				System.out.println("입력한 계정정보가 잘못되었습니다.("+cntErr+"회 오류)");
			} // ~로그인 실패시 처리

			// 로그인 5회 실패시 처리
			if (cntErr == 5) {
				System.out.println("로그인 5회 실패. 프로그램을 종료합니다.");
				break;
			} // ~로그인 5회 실패시 처리
		}

	}

}
