package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDeleteMenu {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";

		
		System.out.println("::::::::::::::::학생정보수정메뉴::::::::::::::::");
		System.out.println("<< 지정된 학번에 대해 나이와 주소를 수정할 수 있습니다. >>");
		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			deleteStudent(conn);
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}

	}// main()

	private static void deleteStudent(Connection conn) throws SQLException {
		String stdno;
		Scanner sc = new Scanner(System.in);
		String sql = "delete from tbl_student where stdno = ?";

		System.out.println("학번 0000 입력은 수정 취소입니다.");
		System.out.print("삭제하려는 학생의 학번을 입력하세요 >>> ");
		stdno = sc.nextLine();
		if (stdno.equals("0000")) {
			System.out.println("학생 정보 삭제를 취소합니다.");
			return;
		}
		try (PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, stdno);
			int cnt = ps.executeUpdate();
			System.out.println("학생 정보 삭제 " + cnt + "건이 삭제 성공했습니다.");
		} catch (SQLException e) {
			System.out.println("잘못된 데이터 입력 : " + e.getMessage());
		}

	}// deleteStudennt()

}
