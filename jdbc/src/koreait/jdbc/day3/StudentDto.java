package koreait.jdbc.day3;

// 메소드의 매개변수 또는 리턴 타입으로 사용하기 위해
// 테이블의 컬럼 구성과 같은 클래스의 필드 멤버(변수)를 정의하여 값을 저장하기 위한 것입니다.
// VO(Value Object) : 불변객체로 equals 와 hashcode 재정의를 하여 set 또는 map 객체에서 활용
// DTO(Data Transfer Object) : 가변객체로 데이터를 객체간에 전달하기 위한 목적
public class StudentDto {

	private String stdno;
	private String name;
	private int age;
	private String address;
	
	public StudentDto() {
		// TODO Auto-generated constructor stub
	
	}

	public StudentDto(String stdno, String name, int age, String address) {
		super();
		this.stdno = stdno;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getStdno() {
		return stdno;
	}



	public String getName() {
		return name;
	}



	public int getAge() {
		return age;
	}



	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "StudentDto [stdno=" + stdno + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}

	
}
