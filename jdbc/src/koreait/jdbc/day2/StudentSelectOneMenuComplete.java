package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenuComplete {

	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println(":::::::::::학생을 학번으로 조회하는 메뉴:::::::::::");
		selectOneStudent(conn);

		OracleUtility.close(conn);

	}

	private static void selectOneStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String stdno;
		String sql = "select * from tbl_student where stdno = ?";

		System.out.print("학생정보를 조회할 학생의 학번을 입력하세요 >>> ");
		stdno = sc.nextLine();

		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, stdno);
			ResultSet rs = ps.executeQuery();
			System.out.println("rs 객체의 구현 클래스는 " + rs.getClass().getName());
			if (rs.next()) {
				System.out.println("조회결과 첫 번째 컬럼의 값 : " + rs.getString(1));
				System.out.println("조회결과 첫 번째 컬럼의 값 : " + rs.getString("STDNO"));
				System.out.println("조회결과 두 번째 컬럼의 값 : " + rs.getString(2));
				System.out.println("조회결과 두 번째 컬럼의 값 : " + rs.getString("NAME"));
				System.out.println("조회결과 세 번째 컬럼의 값 : " + rs.getInt(3));
				System.out.println("조회결과 세 번째 컬럼의 값 : " + rs.getInt("AGE"));
				System.out.println("조회결과 네 번째 컬럼의 값 : " + rs.getString(4));
				System.out.println("조회결과 네 번째 컬럼의 값 : " + rs.getString("ADDRESS"));
				
			} else {
				System.out.println("<< 조회 결과가 없습니다. >>");
			}

		} catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다.");
		}

	}

}
/*
 * 모든 학생을 조회하는 StudentSelectAllMenu 클래스 : 한줄에 한개 행을 출력 (칼럼들을 한 줄로 출력)
 * 과목명을 입력하면 해당 과목 조회하는 ScoreSelectWithSubject 클래스 
 */
