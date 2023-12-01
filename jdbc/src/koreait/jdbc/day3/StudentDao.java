package koreait.jdbc.day3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

// DAO(Data Access Object) : SQL 실행 메소드를 모아놓은 클래스
//			ㄴ 접근 - 읽기와 쓰기

// insert, update 는 SQL 파라미터에 전달한 데이터 타입을 DTO
// delete 는										원시형 또는 String

// insert,update,delete 는 정수 리턴값을 ㅗ반영된 행의 개수를 전달

// selectOne : SQL파라미터에 전달할 데이터(DTO)를 메소드 파라미터로 받음
// selectAll : SQL파라미터가 없으며 여러개의 행을 저장할 객체를 가짐 (List타입)
public class StudentDao {
	// 나중에 db를 쉽게 코딩하기 위한 프레임워크를 사용하면 Exception 처리 안해도 됩니다
	public int insert(StudentDto student) throws SQLException {
		Connection connection = OracleUtility.getConnection();

		String sql = "insert into tbl_student values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, student.getStdno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getAge());
		ps.setString(4, student.getAddress());
		int result = ps.executeUpdate();

		ps.close();
		connection.close();

		return result;
	}// insert()메소드

	public int update(StudentDto student) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "update tbl_student set age = ?, address = ? where stdno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(3, student.getStdno());
		ps.setInt(1, student.getAge());
		ps.setString(2, student.getAddress());
		int result = ps.executeUpdate();

		ps.close();
		connection.close();

		return result;

	}// update()메소드

	public int delete(StudentDto student) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "delete from tbl_student where stdno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, student.getStdno());
		int result = ps.executeUpdate();

		ps.close();
		connection.close();

		return result;

	}// delete() 메소드

	public StudentDto selectOne(String stdno) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from tbl_student where stdno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, stdno);
		ResultSet rs = ps.executeQuery();
//		StudentDto result = new StudentDto(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));

		StudentDto result = null;
		if (rs.next()) {
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String address = rs.getString(4);
			result = new StudentDto(stdno, name, age, address);
		}
		return result;

	}// selectOne() 메소드
	
	public List<StudentDto> selectAll() throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from tbl_student";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<StudentDto> results = new ArrayList<>();
		
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
//			String stdno=rs.getString(1);
//			String name = rs.getString(2);
//			int age = rs.getInt(3);
//			String address=rs.getString(4);
//			StudentDto dto = new StudentDto(stdno,name,age,address);
//			results.add(dto);
			
			results.add(new StudentDto(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			
		}
		
		return results;
	}

}
