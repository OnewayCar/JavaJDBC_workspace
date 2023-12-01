package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// 학생 성적처리 프로그램 중에 새로운 학생을 등록(입력)하는 기능을 만들어 봅시다. (테이블에 insert 실행)
// 파라미터를 전달하는 방식으로 SQL 실행하기
public class InsertDMLUsingParameter {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";

		try (Connection conn = DriverManager.getConnection(url, user, password);) {

			System.out.println("연결 상태 = " + conn);
			if (conn != null)
				System.out.println("오라클 데이터베이스 연결 성공!!");

			// DB연결 완료 후에 SQL 실행
			//String sql = ""; // insertSQL 작성

			// insert SQL문 작성 : 제약조건(기본키 stdno) 위반되지 않는 값으로 입력
			
			String sql ="insert into TBL_STUDENT values(?,?,?,?)";
			// PreparedStatement 객체를 생성하면서 실행할 SQL입력
			// PreparedStatement 객체를 Connection SQL입력 (Connection 구현객체는 DBMS 종류에 따라 생성되고 prepareStatement 객체도 그에 따라 구현객체가 결정됩니다.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// prepareStatement()는 객체를 생성해서 리턴합니다.
			
			// 매개변수에 값 전달하기 : 매개변수의 데이터형식 순서 (문자열,문자열,정수,문자열)
			// set'XXX'() 메소드 사용
			//	ㄴ 'XXX'는 데이터 타입
			// 	ㄴ 첫번째 파라미터는 인덱스 번호(1부터 시작), 두번째 파라미터는 값
			
			pstmt.setString(1, "2023003");
			pstmt.setString(2, "김땡사");
			pstmt.setInt(3, 17);
			pstmt.setString(4, "강원도");
			
			pstmt.execute();	// prepareStatement 객체로 execute 하면 SQL이 실행됩니다.
			pstmt.close();		

			System.out.println("정상적으로 새로운 학생이 입력 되었습니다.");
		} catch (Exception e) {
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 password가 잘못됐습니다.");
			System.out.println("오류 메시지 = " + e);
			e.printStackTrace();
		}

	}// main

}// InsertDMLTest

/* 
 * Statement 인터페이스는 SQL쿼리 처리와 관련된 방법을 정의
 * 객체는 SQL쿼리문을 데이터베이스에 전송
 * Connection객체를 통해서 만듬
 * 
 * PreparedStatement는 Statement의 자식 인터페이스.
 * 특징은 SQL을 먼저 컴파일하고 SQL실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식
 * 
 */
