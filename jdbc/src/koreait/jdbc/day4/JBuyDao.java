package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

public class JBuyDao {
	public int buy(List<JBuy> carts) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "insert into j_buy values(seq.nextval,?,?,?,sysdate)";
		PreparedStatement ps = connection.prepareStatement(sql);

		for (JBuy jb : carts) {
			ps.setString(1, jb.getCustomid());
			ps.setString(2, jb.getPcode());
			ps.setInt(3, jb.getQuantity());
		}
		int result = ps.executeUpdate();

		ps.close();
		connection.close();

		return result;

	}// 상품 구매(결제)하기 - 장바구니의 데이터를 구매 테이블에 입력하기

	public List<JBuy> selectAll() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_buy";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<JBuy> results = new ArrayList<>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new JBuy(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5)));
		}

		return results;
	}// 나의 구매 내역 보기
}
