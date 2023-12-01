package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;
import koreait.jdbc.day3.StudentDto;

public class JProductDao {
	public List<JProduct> selectAll() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_product";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<JProduct> results = new ArrayList<>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new JProduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));

		}

		return results;
	}// 2. 상품목록보기

	public List<JProduct> selectByPname(String pname) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_product where pname like '%' || ? || '%'";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, pname);
		ResultSet rs = ps.executeQuery();

		List<JProduct> results = new ArrayList<>();
		while (rs.next()) {
			results.add(new JProduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		ps.close();
		connection.close();

		return results;
	}
}
