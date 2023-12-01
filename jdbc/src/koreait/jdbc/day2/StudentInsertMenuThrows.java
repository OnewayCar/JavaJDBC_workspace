package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class StudentInsertMenuThrows {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		StudentInsertMenuThrows simth = new StudentInsertMenuThrows();
		System.out.println("::::::::::::::::::::학생 등록 메뉴입니다.::::::::::::::::::::");
		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			simth.insertStd(conn);
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}

	}// main

	public void insertStd(Connection connection) throws SQLException {
		String name,stdno,address;
		int age;
		Scanner sc = new Scanner(System.in);
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		while (true) {	// insert 실행을 반복적으로 
			
			System.out.println("학생번호 입력시 0000입력은 종료입니다.");
			try {
				System.out.print("학번을 입력하세요 >>> ");
				stdno = sc.nextLine();

				if (stdno.equals("0000")) {
					System.out.println("학생 등록을 중지합니다.");
					break;
				}
				ps.setString(1, stdno);

				System.out.print("이름을 입력하세요 >>> ");
				name = sc.nextLine();
				ps.setString(2, name);

				System.out.print("나이을 입력하세요(10이상, 30세 이하) >>> ");
				age = Integer.parseInt(sc.nextLine());
				ps.setInt(3, age);

				System.out.print("주소를 입력하세요 >>> ");
				address = sc.nextLine();
				ps.setString(4, address);

				ps.execute();
			} // try
			catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("잘못된 데이터 입력입니다. 다시 입력하세요");
			} // catch

			catch (NumberFormatException e) {
				System.out.println("나이 입력이 잘못되었습니다. 정수값을 입력해주세요.");
			}

			System.out.println("------------------------------------------------");
		} // while
		ps.close();
		sc.close();
		System.out.println("입력한 학생정보가 성공적으로 등록되었습니다..");

	}
}
