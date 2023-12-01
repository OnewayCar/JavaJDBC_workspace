package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtility;

public class JCustomerDao {

	public JCustom select(String customid) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_custom where custom_id =?";
		PreparedStatement ps = connection.prepareStatement(sql);

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
