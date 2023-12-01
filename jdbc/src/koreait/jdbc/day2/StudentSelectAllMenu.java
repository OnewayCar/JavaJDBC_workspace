package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectAllMenu {

	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println(":::::::::::학생을 학번으로 조회하는 메뉴:::::::::::");
		selectOneStudent(conn);

		OracleUtility.close(conn);
		System.out.println("프로그램 종료.");

	}

	private static void selectOneStudent(Connection conn) {
		String stdno;
		String sql = "select * from tbl_student ";
		int cnt=0;
		
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			System.out.println("rs 객체의 구현 클래스는 " + rs.getClass().getName());
			System.out.println("\n학  번\t\t이  름\t\t나  이\t\t주  소");
			System.out.println("------------------------------------------------------------");
			while(rs.next()) {
				System.out.print(rs.getString(1));
				System.out.print("\t\t");
				System.out.print(rs.getString(2));
				System.out.print("\t\t");
				System.out.print(rs.getString(3));
				System.out.print("\t\t");
				System.out.print(rs.getString(4));
				System.out.println();
				cnt++;
			}
			System.out.println("총 "+cnt+"건의 학생정보를 조회 성공!");

		} catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다."+e.getMessage());
		}

	}

}
/*
 * 모든 학생을 조회하는 StudentSelectALLMenu 클래스 : 한줄에 한개 행을 출력 (칼럼들을 한 줄로 출력)
 * 과목명을 입력하면 해당 과목 조회하는 ScoreSelectWithSubject 클래스 
 */
