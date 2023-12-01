package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentUpdateMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";

		System.out.println("::::::::::::::::학생정보수정메뉴::::::::::::::::");
		System.out.println("<< 지정된 학번에 대해 나이와 주소를 수정할 수 있습니다. >>");

		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			updateStudent(conn);
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}

	}// main

	private static void updateStudent(Connection connection) throws SQLException {
		String name, stdno, age, address;
		Scanner sc = new Scanner(System.in);
		String sql = "update tbl_student set age = ?, address = ? where stdno = ?";

		System.out.println("학번 0000 입력은 수정 취소입니다.");
		System.out.print("수정하려는 학생의 학번을 입력하세요 >>> ");
		stdno = sc.nextLine();
		if (stdno.equals("0000")) {
			System.out.println("학생 정보 수정을 취소합니다.");
			return;
		}
		System.out.print("수정할 나이를 입력하세요 >>> ");
		age = sc.nextLine();
		System.out.print("수정할 주소를 입력하세요 >>> ");
		address = sc.nextLine();

		
		try(PreparedStatement ps = connection.prepareStatement(sql)) 
		{	// 매개변수의 순서와 형식을 확인하고 전달하는 setXXX()메소드 실행
			ps.setString(1, age);
			ps.setString(2, address);
			ps.setString(3, stdno);
			// ps.execute();				// insert(Create),update,delete,select(Read) 모두 실행
			int cnt = ps.executeUpdate();	//  리턴값은 반영된 행의 개수
											// insert,update,delete, select 모두 실행
			System.out.println("학생 정보 수정" + cnt + "건이 변경 성공했습니다.");
		} catch (SQLException e) {
			System.out.println("잘못된 데이터 입력 : "+e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("나이 입력이 잘못되었습니다.");
		}

	}

}// StudentUpdateMenu
