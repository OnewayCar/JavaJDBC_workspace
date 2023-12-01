package koreait.jdbc.day7;

// 싱글톤패턴 : 객체를 만들때 오직1개만 만들어서 사용하는 코드 패턴
//		   : 웹에서는 메소드만 있는 DAO같은 클래스를 싱글톤으로 작성
//		   : static은 정적인 영역. DAO를 정적인 영역에 만들지는 않음 (DAO는 static으로 만들지 않는다. 멀티 스레드 환경에서 부적합)

public class SingletonClass {
	// 1. 미리 객체를 만들어서 전역변수(필드)로 선언해 둡니다. public static
	private static SingletonClass single = new SingletonClass(); // SingletonClass 객체는 오직 한번만 만듬
	
	// 2. 생성자는 private 입니다.
	// 외부의 다른 클래스에서 SingletonClass() 실행 불가
	private SingletonClass() {
		
	}
	
	//3. 외부의 다른 클래스에서 객체를 요청할 때 리턴해 주는 메소드
	public static SingletonClass getInstance() {
		return single;
	}
}
