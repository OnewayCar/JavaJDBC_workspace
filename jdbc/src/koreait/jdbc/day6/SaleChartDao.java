package koreait.jdbc.day6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

public class SaleChartDao {
	public List<SaleChart> selectSales() throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "SELECT custno,custname," + "CASE WHEN grade='A' THEN 'VIP'\r\n"
				+ "	WHEN grade='B' THEN '일반'\r\n" + "	WHEN grade='C' THEN '직원'\r\n"
				+ "	END AS grade,sum(totalprice) AS sales\r\n" + "FROM SALECHART \r\n"
				+ "GROUP BY custno,custname,grade\r\n" + "ORDER BY sales desc";
		PreparedStatement ps = con.prepareStatement(sql);

		List<SaleChart> results = new ArrayList<>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new SaleChart(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4)));
		}
		ps.close();
		con.close();
		return results;
	}

}
