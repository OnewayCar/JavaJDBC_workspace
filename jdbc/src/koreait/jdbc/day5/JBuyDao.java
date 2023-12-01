package koreait.jdbc.day5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

public class JBuyDao {
	
	// 트랜잭션을 처리하는 예시
	public int insert(List<JBuy> carts) throws SQLException {
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

	public int insertMany(List<JBuy> carts) {
		Connection connection = OracleUtility.getConnection();
		String sql = "insert into j_buy values(jbuy_seq.nextval,?,?,?,sysdate)";
		int cnt = 0;

		PreparedStatement ps = null;

		try {
			connection.setAutoCommit(false); // auto-commit 설정 - false
			ps = connection.prepareStatement(sql);
			for (JBuy b : carts) {
				ps.setString(1, b.getCustomid());
				ps.setString(2, b.getPcode());
				ps.setInt(3, b.getQuantity());
				cnt += ps.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			System.out.println("장바구니 상품 구매 예외 : " + e.getMessage());
			System.out.println("장바구니에 담긴 상품 구매가 취소되었습니다.");
			try {
				connection.rollback();
			} catch (SQLException e1) {

			}
		}

		return cnt;
	}

	public List<JBuy> selectAll() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_buy";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<JBuy> results = new ArrayList<>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new JBuy(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5)));
		}

		return results;
	}// 나의 구매 내역 보기

	// select * from mypage_buy where customid = 'twice';
	public List<MyPageBuy> mypageBuy(String customid) throws SQLException {

		Connection connection = OracleUtility.getConnection();
		String sql = "select * from mypage_buy where customid = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<MyPageBuy> results = new ArrayList<>();
		ps.setString(1, customid);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new MyPageBuy(rs.getDate(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
					rs.getInt(6), rs.getLong(7)));
		}
		return results;
	}

	// select sum(total) from mypage_buy where customid='twice';
	public long myTotal(String customid) throws SQLException {
		long result = 0;
		Connection connection = OracleUtility.getConnection();
		String sql = "select sum(total) from mypage_buy where customid=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, customid);

		ResultSet rs = ps.executeQuery();
		if (rs.next())
			result = rs.getLong(1);
		
		return result;
	}

	public int insert(JBuy buy) {
		return 1;
	}

	public JBuy selectOne(int buyseq) throws SQLException {
		// sql 실행을 구현하고 테스트 케이스 확인하기
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_buy where buy_seq = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, buyseq);
		JBuy buy =null;
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			buy = new JBuy(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5));
		}
		
		
		
		return buy;
	}
}
