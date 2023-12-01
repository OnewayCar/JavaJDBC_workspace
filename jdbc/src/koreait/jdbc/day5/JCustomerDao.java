package koreait.jdbc.day5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtility;

public class JCustomerDao {

	public JCustom select(String customid) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_custom where custom_id =?";	// PK값으로 조회하기에 결과 행은 0 또는 1
		
		// Statement VS PreparedStatement
		// Statement 인터페이스 : SQL 실행에 필요한 데이터를 동시에 포함시켜서 컴파일을 합니다.
		
		PreparedStatement ps = connection.prepareStatement(sql);	// Prepared : 미리 컴파일되어 준비된, Statement : SQL
																	// PreparedSQL : sql이 미리 컴파일되어 준비되어있다.
		
			
		ps.setString(1, customid);

		ResultSet rs = ps.executeQuery();

		JCustom result = null;
		if (rs.next()) {
			result = new JCustom(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
		}
		ps.close();
		connection.close();
		return result;

	}// 1.간단히 회원아이디를 입력해서 존재하면 로그인 성공
}
