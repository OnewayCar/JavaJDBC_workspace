package koreait.jdbc.day4;

import java.sql.SQLException;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {
		// dao 클래스 객체 선언
		JCustomerDao customDao = new JCustomerDao();
		JProductDao productDao = new JProductDao();
		JBuyDao buyDao = new JBuyDao();

		// 1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
		System.out.println("1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공");
		JCustom jc1 = null;
		try {
			jc1 = customDao.select("wonder");
		} catch (SQLException e) {
			System.out.println("오류 발생 : " + e.getMessage());
		}

		System.out.println(jc1);
		// ~1번

		// 2. 상품 목록 보기
		System.out.println("\n2. 상품 목록 보기");
		List<JProduct> prdList = null;
		try {
			prdList = productDao.selectAll();
		} catch (SQLException e) {
			System.out.println("오류 발생 : " + e.getMessage());
		}
		for (JProduct j : prdList) {
			System.out.println(j);
		} // ~2번

		// 3. 상품명으로 검색하기
		System.out.println("\n3. 상품명으로 검색하기");
		List<JProduct> jp1 = null;
		try {
			jp1 = productDao.selectByPname("사과");
		} catch (SQLException e) {
			System.out.println("오류 발생 : " + e.getMessage());
		}
		System.out.println(jp1);
		// ~3번

		// 4. 상품 장바구니 담기 - 장바구니 테이블이 없으므로 구매를 원하는 상품을 List 에 담기
		
		// ~4번

		// 5. 상품 구매(결제)하기 - 장바구니의 데이터를 구매 테이블에 입력하기

		// ~5번

		// 6. 나의 구매 내역 보기
		System.out.println("\n6. 나의 구매 내역 보기");
		List<JBuy> buyList = null;
		try {
			buyList = buyDao.selectAll();
		} catch (SQLException e) {
			System.out.println("오류 발생 : " + e.getMessage());
		}
		for (JBuy jb : buyList) {
			System.out.println(jb);
		}
		// ~6번

	}// main

}
