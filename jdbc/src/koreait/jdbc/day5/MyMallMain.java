package koreait.jdbc.day5;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMallMain {

	public static void main(String[] args) {
		// Dao 클래스 객체 생성
		JCustomerDao customDao = new JCustomerDao();
		JProductDao productDao = new JProductDao();
		JBuyDao buyDao = new JBuyDao();

		System.out.println(":::::::::::::일도 쇼핑몰에 오신걸 환영합니다.:::::::::::::");
		System.out.println(">>>>상품 목록<<<<");
		// --------------------------전체 조회--------------------------
		List<JProduct> allPrdList = null;
		try {
			allPrdList = productDao.selectAll();
		} catch (SQLException e) {
			System.out.println("상품 목록 조회 예외 : " + e.getMessage());
		} // try~catch 상품 목록 로딩
		for (JProduct jp : allPrdList) {
			System.out.println(jp);
		} // foreach 출력문

		// --------------------------조건 조회--------------------------
		System.out.println("\n>>>> 상품명으로 검색하기 <<<<");
		Scanner sc = new Scanner(System.in);
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
		System.out.println("\n>>>> 상품 구매를 위해 로그인 하기 <<<<");
		JCustom currCustomer = null;
		String customid = null;
		boolean isLogin = false;
		List<JBuy> carts = new ArrayList<>();
		while (!isLogin) {

			System.out.print("간편 로그인 - 사용자ID를 입력하세요(로그인 취소 : 0000 입력) >>> ");
			customid = sc.nextLine();
			if(customid.equals("0000"))
				break;
			try {
				currCustomer = customDao.select(customid);
				if (currCustomer != null) {
					isLogin = true;
					System.out.println(currCustomer.getName() + "고객님 환영합니다.");
				} else {
					System.out.println("존재하지 않는 회원입니다.");
				}

			} catch (SQLException e) {
				System.out.println("로그인 시도 에외 : " + e.getMessage());
			}

		}
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
			if(cnt==0)	
				System.out.println("오류 발생. 결제 취소");
			else
				System.out.println("결제 완료.\n현재까지 "+currCustomer.getName()+" 회원님의 구매 내역 입니다.");
		}else
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
			long totalMoney=buyDao.myTotal(customid);
			System.out.println("현재 "+currCustomer.getName()+" 회원님의 총 구매 금액은 "+totalMoney+" 원 입니다.");
		} catch (SQLException e) {
			System.out.println("구매 총 금액 산출 에외 : " + e.getMessage());
		}
		
		
		
		
		
	}// main

}// MyMallMain
