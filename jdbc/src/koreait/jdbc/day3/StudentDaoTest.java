package koreait.jdbc.day3;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class StudentDaoTest {

	public static void main(String[] args) {

		StudentDao dao = new StudentDao();

		System.out.println("1. insert 테스트");
		System.out.println("2023009 김땡구 17 강남구 - 데이터 입력");
		StudentDto dto = new StudentDto("2023009", "김땡구", 17, "강남구");
		try {
			int cnt = dao.insert(dto);
			System.out.println("학생 등록 : " + cnt + " 건 입력 성공!!");
			System.out.println("입력 결과 조회 : " + dao.selectOne(dto.getStdno()));
		} catch (SQLException e) {
			System.out.println("예외 발생 - " + e.getMessage());
		}

		System.out.println("2. update 테스트");
		System.out.println("2023009 김땡구 16 용산구 - 데이터 수정");
		dto = new StudentDto("2023009", "김땡구", 16, "용산구");
		try {
			int cnt = dao.update(dto);
			System.out.println("학생 등록 : " + cnt + " 건 수정 성공!!");
			System.out.println("수정 결과 조회 : " + dao.selectOne(dto.getStdno()));
		} catch (SQLException e) {
			System.out.println("예외 발생 - " + e.getMessage());
		}

		System.out.println("3. delete 테스트");
		System.out.println("2023009 김땡구 16 용산구 - 데이터 삭제");
		try {
			int cnt = dao.delete(dto);
			System.out.println("학생 등록 : " + cnt + " 건 삭제 성공!!");
			System.out.println("삭제 결과 조회 : " + dao.selectOne(dto.getStdno()));

		} catch (SQLException e) {
			System.out.println("예외 발생 - " + e.getMessage());
		}

		System.out.println("4. select * 테스트");
		System.out.println("학생 테이블의 모든 데이터 조회하여 출력합니다.");
		try {
			List<StudentDto> list = dao.selectAll();
//			System.out.println(list.toString());
			Iterator it = list.iterator();
			System.out.println("\t\tSTDNO\t    NAME\tAGE\tADRESS");
			System.out.println("------------------------------------------------------------");
			for (StudentDto stdto : list) {
				System.out.println(stdto);
			}
		} catch (SQLException e) {
			System.out.println("예외 발생 - " + e.getMessage());
		}
	}

}
