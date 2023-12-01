package koreait.jdbc.day6;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuMain {

	public static void main(String[] args) throws SQLException {
		// 만든 Dao를 정상적으로 동작하는지 확인하려면 데이터를 직접 주고
		// 메소드를 호출해서 실행하는 main을 만들어야 합니다.
		// main 메소드는 '응용프로그램'을 만들기 위해서 작성하는 것으로 테스트용은 아닙니다.

		// 특정 메소드가 잘 실행되는지 검증(테스트)하는 것을 단위테스트(unit test)라고 합니다.
		// Java에서 기본적으로 사용하는 단위테스트는 라이브러리로 JUnit 이 있습니다.
		// 테스트 케이스는 테스트 메소드 또는 이것을 포함하는 클래스입니다.
		// 테스트 케이스는 test 폴더(src와 구분되는 소스파일 폴더)에 별도 작성

		// 테스트 케이스는 빌드에 포함시키지 않음 (최종본이 아님)
		// src 폴더 : 프로덕션 코드 (빌드에 포함되는 코드)

		// 하나의 Dao를 완성하기 위해 main까지 만들지 않고도 검증하는 목적으로 사용가능

		// 싱글톤 Dao 사용하기
		MemberDao daoMem = MemberDao.getMemberDao();
		MoneyDao daoMon = MoneyDao.getMoneyDao();

		// select 시 사용할 List객체
		List<Member> selAll = new ArrayList<>();
		List<SaleChart> selChart = new ArrayList<>();
		List<SaleChart> selOtherChart = new ArrayList<>();

		selAll = daoMem.selectAll();

		System.out.println("전체 회원 조회");
		for (Member msa : selAll) {
			System.out.println(msa);
		}

		Member customer1 = new Member();
		Scanner sc = new Scanner(System.in);
//		System.out.println("홈쇼핑 회원 등록");
//		System.out.print("회원 성명 입력 >>> ");
//		customer1.setCustname(sc.nextLine());
//		System.out.print("회원 전화 입력 >>> ");
//		customer1.setPhone(sc.nextLine());
//		System.out.print("회원 주소 입력 >>> ");
//		customer1.setAddress(sc.nextLine());
//		System.out.print("고객 등급 입력(A:VIP,B:일반,C:직원) >>> ");
//		customer1.setGrade(sc.nextLine());
//		System.out.print("도시 코드 입력 >>> ");
//		customer1.setCity(sc.nextLine());
//
//		int a = daoMem.insert(customer1);
//		selAll = daoMem.selectAll();
//
//		for (Member msa : selAll) {
//			System.out.println(msa);
//		}

		System.out.println("홈쇼핑 회원 수정");
		System.out.print("회원 전화 입력 >>> ");
		customer1.setPhone(sc.nextLine());
		System.out.print("회원 주소 입력 >>> ");
		customer1.setAddress(sc.nextLine());
		System.out.print("도시 코드 입력 >>> ");
		customer1.setCity(sc.nextLine());

		System.out.print("회원 성명 입력 >>> ");
		customer1.setCustname(sc.nextLine());

		int a = daoMem.update(customer1);
		selAll = daoMem.selectAll();

		System.out.println("\n전체 회원 조회");
		for (Member msa : selAll) {
			System.out.println(msa);
		}
		
		selChart = daoMon.selectSales();
		System.out.println("\n회원 총 금액 조회");
		for (SaleChart msc : selChart) {
			System.out.println(msc);

		}
		selOtherChart = daoMon.selectOtherSales();
		System.out.println("\n매출이 없는 회원 포함 총 금액 조회");
		for (SaleChart msoc : selOtherChart) {
			System.out.println(msoc);

		}
	}

}
