package koreait.jdbc.day5;
// JBUY_DTO

import java.sql.Date;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JBuy {	// 구매와 관련된 CRUD 실행 SQL. DAO:JCustomerDao, JProductDao
	// 메소드 이름은 insert, update, delete, select, selectByPname 등등으로 이름을 작성하시오.
	
	private int buy_seq;
	private String customid;
	private String pcode;
	private int quantity;
	private Date buy_date;
	
}
// 필드값이 모두 같으면 equals 로 true가 되도록 하고 싶다.
// -> equals 와 hashcode를 재정의
// -> vo 이빈다. vo는 테스트 케이스에서 유용하게 사용할 수 있음 assertEquals 비교.	