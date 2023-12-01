package koreait.jdbc.day6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koreait.jdbc.day2.OracleUtility;
//1. 회원등록
//'회원등록' 화면은 회원번호, 회원성명, 회원전화, 회원주소, 가입일자, 고객등급, 도시코드 입력 항목으로 구성되어 있다. 

// DAO 에서는 입력과 출력은 포함시키지 않습니다.오직 어떤 형식의 데이터를 받는 형식
// 어떤 SQL을 실행하여, 어떤 값을 리턴할 것인가를 중점을 두고 정의하면 됩니다.
// DTO는 데이터(필드변수, 생성자 등)를 저장하는 목적의 클래스, DAO는 어떤 동작을 하는지(메소드)를 정의하는 목적의 클래스
public class MemberDao {
	
	// 싱글톤으로 만들어봅시다.
	private static MemberDao daoMember = new MemberDao();
	private MemberDao() {}
	public static MemberDao getMemberDao() {	
		return daoMember;
	}
	

	public int insert(Member member) throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "insert into MEMBER_TBL_02 values(member_tbl_02_seq.nextval,?,?,?,sysdate,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, member.getCustname());
		ps.setString(2, member.getPhone());
		ps.setString(3, member.getAddress());
		ps.setString(4, member.getGrade());
		ps.setString(5, member.getCity());

		int result = ps.executeUpdate();

		ps.close();
		con.close();
		return result;
	}

	public List<Member> selectAll() throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "SELECT custno, custname,	phone,	address, joindate,"
				+ " CASE WHEN grade='A' THEN 'VIP'"
				+ " WHEN grade='B' THEN '일반'"
				+ "	WHEN grade='C' THEN '직원'"
				+ "	END AS grade,"
				+ "	city\r\n" + "FROM"
				+ "	MEMBER_TBL_02";
		PreparedStatement ps = con.prepareStatement(sql);
		List<Member> results = new ArrayList<>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
					rs.getString(6), rs.getString(7)));
		}
		ps.close();
		con.close();

		return results;
	}

	public int update(Member member) throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "update MEMBER_TBL_02 set phone = ? , address = ? , grade = ? , city = ?" + "where custname = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member.getPhone());
		ps.setString(2, member.getAddress());
		ps.setString(3, member.getGrade());
		ps.setString(4, member.getCity());
		ps.setString(5, member.getCustname());

		int result = ps.executeUpdate();

		ps.close();
		con.close();

		return result;
	}
}
