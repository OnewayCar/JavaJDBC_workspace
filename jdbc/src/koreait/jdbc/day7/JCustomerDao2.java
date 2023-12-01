package koreait.jdbc.day7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtility;
import koreait.jdbc.day5.JCustom;

public class JCustomerDao2 {
	private static JCustomerDao2 dao = new JCustomerDao2();
	private JCustomerDao2() {}

	public static JCustomerDao2 getJCustomerDao2() {
		return dao;
	}

	public JCustom login(String id, String pw) throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "select custom_id, name from J_CUSTOM where custom_id= ? and password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, id);
		ps.setString(2, pw);
		JCustom result = null;
		
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			/*
			 * result = new JCustom(rs.getString(1),rs.getString(2),
			 * rs.getString(3),rs.getInt(4),rs.getDate(5));
			 */
			result = JCustom.builder()
					.custom_id(rs.getString(1))
					.name(rs.getString(2))
					.build();
		}
		return result;
	}
}
// LoginMain 에서 사용자에게 아이디와 패스워드 입력받기
// 로그인 처리 결과 '로그인 성공했습니다. XXX 님 환영합니다.' 또는 '입력한 계정정보가 잘못되었습니다.'