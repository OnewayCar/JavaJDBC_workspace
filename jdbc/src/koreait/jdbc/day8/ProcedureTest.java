package koreait.jdbc.day8;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import koreait.jdbc.day2.OracleUtility;

public class ProcedureTest {

	public static void main(String[] args) {

		Connection con = OracleUtility.getConnection();
		String sql = "{call max_custom(?,?)}"; // 저장프로시저 max_custom 호출 sql. {} 안에서 호출하기
		try (CallableStatement cstmt = con.prepareCall(sql);) {
			// prepareCall는 저장프로시저 실행하기 위한 객체 생성 메소드
			
			// IN 매개변수가 있으면 cstmt.setXXX() 메소드로 값을 줍니다.

			cstmt.registerOutParameter(1, Types.VARCHAR);		// 매개변수 인덱스, 오라클 데이터 타입 지정
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.executeUpdate();

			System.out.println("===========가장 많은 구매 수량으로 제품을 구입한 고객 정보===========");
			System.out.println("고객 성명 : " + cstmt.getString(1));		// 프로시저 출력값 첫번째 가져오기
			System.out.println("고객 이름 : " + cstmt.getInt(2));			// 프로시저 출력값 두번째 가져오기

		} catch (SQLException e) {
			System.out.println("고객 정보 저장 간 예외 발생 : " + e.getMessage());
		}

	}

}
