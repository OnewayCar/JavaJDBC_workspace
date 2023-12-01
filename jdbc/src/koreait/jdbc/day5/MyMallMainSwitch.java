package koreait.jdbc.day5;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMallMainSwitch {

	public static void main(String[] args) {
		// Dao 클래스 객체 생성
		JCustomerDao customDao = new JCustomerDao();
		JProductDao productDao = new JProductDao();
		JBuyDao buyDao = new JBuyDao();
		// 메뉴 선택을 위한 Scanner 객체 및 선택값 저장 변수
		Scanner sc = new Scanner(System.in);
		List<JProduct> allPrdList = new ArrayList<>();
		System.out.println(":::::::::::::일도 쇼핑몰에 오신걸 환영합니다.:::::::::::::");

// J_CUSTOM 테이블의 CUSTOMID 를 이용하여 회원 로그인--------------------------------------------
		boolean isLogin = false; // 로그인 성공 여부로 사용할 boolean타입 변수
									// (기본값 false, 성공시 true)
		String customid = null; // 입력 받을 회원ID
		JCustom loginCustomer = null; // 로그인한 고객의 정보를 담을 JCustom 타입 객체

		System.out.println("-------회원 로그인-------");
		while (!isLogin) { // 회원ID를 입력받아 로그인 하기 위한 반복문
			System.out.print("고객ID를 입력하세요. (로그인 하지 않으려면 'N(n)' 입력) : ");
			customid = sc.nextLine(); // 회원 ID 입력
			if (customid.toLowerCase().equals("n")) // 입력 받은 회원 ID를 소문자로 처리하여 'n'이면
				break; // 반복문 탈출
			try {
				loginCustomer = customDao.select(customid); // 입력한 customid를 갖는 열을 loginCustomer에 저장
				if (loginCustomer != null) { // 가져온 열이 있는 경우 = customid와 일치하는 열이 있는 경우
					isLogin = true; // 반복문 탈출
					System.out.println(loginCustomer.getName() + "고객님 환영합니다."); // 가져온 열의 NAME칼럼 값과 함께 출력문 출력
				} else { // 가져온 열이 없는 경우 = customid와 일치하는 열이 없는 경우
					System.out.println("존재하지 않는 회원입니다."); // 출력문 출력과 함께 반복문 반복
				} // if문

			} catch (SQLException e) {
				System.out.println("로그인 시도 에외 : " + e.getMessage());
			} // try catch문

		} // while문
		int sel;
		System.out.println("===========실행 메뉴===========");
		System.out.println("1.상품 전체 조회\t2.상품명으로 상품 검색\t3.장바구니 추가\t4.장바구니 조회\t5.종료");
		sel = Integer.parseInt(sc.nextLine());

		switch (sel) {
		case 1:
			System.out.println(">>>>상품 목록<<<<");
			allPrdList = null;			// JProduct 상품정보를 담을 List 객체 allPrdList
			try {
				allPrdList = productDao.selectAll();	// 상품테이블에서 전체 조회한 데이터를 가져와 allPrdList에 저장
			} catch (SQLException e) {
				System.out.println("상품 목록 조회 예외 : " + e.getMessage());
			} // try~catch 상품 목록 로딩
			for (JProduct jp : allPrdList) {			// 반복문 foreach문을 통해 allPrdList 속 JProduct 타입 객체 접근
				System.out.println(jp);					// 각 JProduct타입 객체들을 출력
			} // foreach 출력문
		}

		// --------------------------전체 조회--------------------------

		// --------------------------조건 조회--------------------------
		System.out.println("\n>>>> 상품명으로 검색하기 <<<<");
		System.out.print("검색할 상품명 입력 : ");
		String pname = sc.nextLine();

		List<JProduct> findPrdList = null;
		try {
			findPrdList = productDao.selectByPname(pname);
		} catch (SQLException e) {
			System.out.println("상품명으로 상품 조회 예외 : " + e.getMessage());
		} // try~catch 상품 목록 로딩
		for (JProduct jp : findPrdList) {
			System.out.println(jp);
		} // foreach 출력문

		// --------------------------장바구니 추가--------------------------
		List<JBuy> carts = new ArrayList<>();
		if (isLogin) {
			while (true) {
				System.out.println("\n장바구니에 담기 합니다. (중단 방법 : 상품코드에 0000 입력)");
				System.out.print("구매할 상품코드 입력 >>> ");
				String pcode = sc.nextLine();
				if (pcode.equals("0000"))
					break;

				System.out.print("구매할 수량을 입력하세요 >>> ");
				int quantity = Integer.parseInt(sc.nextLine());

				for (JProduct a : allPrdList) {
					if (a.getPcode().equals(pcode)) {
						carts.add(new JBuy(0, customid, pcode, quantity, null));
					}

				}
				System.out.print("장바구니에 담긴 상품을 결제하시겠습니까? (Y/N)");
				if (sc.nextLine().toLowerCase().equals("y"))
					break;
			} // while
			System.out.println("장바구니 확인 : " + carts);
			int cnt = buyDao.insertMany(carts);
			if (cnt == 0)
				System.out.println("오류 발생. 결제 취소");
			else
				System.out.println("결제 완료.\n현재까지 " + loginCustomer.getName() + " 회원님의 구매 내역 입니다.");
		} else
			System.out.println("로그인 취소. 프로그램 종료.");

		System.out.println("\n::::::::::나의 구매 내역::::::::::");
		List<MyPageBuy> myPage = new ArrayList<>();
		try {
			myPage = buyDao.mypageBuy(customid);
			for (MyPageBuy p : myPage) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			System.out.println("구매 내역 조회 에외 : " + e.getMessage());
		}
		try {
			long totalMoney = buyDao.myTotal(customid);
			System.out.println("현재 " + loginCustomer.getName() + " 회원님의 총 구매 금액은 " + totalMoney + " 원 입니다.");
		} catch (SQLException e) {
			System.out.println("구매 총 금액 산출 에외 : " + e.getMessage());
		}

	}// main

}// MyMallMain
