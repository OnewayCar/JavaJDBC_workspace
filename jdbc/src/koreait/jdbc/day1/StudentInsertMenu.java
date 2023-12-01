package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInsertMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		String str;
		int num;
		boolean status = true;

		System.out.println("::::::::::::::::::::학생 등록 메뉴입니다.::::::::::::::::::::");

		try (Connection conn = DriverManager.getConnection(url, user, password);) {
//			System.out.println("연결 상태 = " + conn);
			String sql = "insert into TBL_STUDENT values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			while (status) {
				System.out.println("학생번호 입력시 0000입력은 종료입니다.");
				System.out.print("학번을 입력하세요 >>> ");
				str = sc.nextLine();
				
				if (str.equals("0000")) {
					System.out.println("학생 등록을 중지합니다.");
					status = false;
					break;
				}
				pstmt.setString(1, str);

				System.out.print("이름을 입력하세요 >>> ");
				str = sc.nextLine();
				pstmt.setString(2, str);

				System.out.print("나이을 입력하세요 >>> ");
				num = Integer.parseInt(sc.nextLine());
				pstmt.setInt(3, num);
				sc.nextLine();

				System.out.print("주소를 입력하세요 >>> ");
				str = sc.nextLine();
				pstmt.setString(4, str);

				pstmt.execute(); 
				System.out.println("------------------------------------------------");
			} // while



			pstmt.close();
			System.out.println("입력한 학생정보가 성공적으로 등록되었습니다..");
		} catch (Exception e) {
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 password가 잘못됐습니다.");
			System.out.println("오류 메시지 = " + e);
			e.printStackTrace();
		}

	}//main
	

}



