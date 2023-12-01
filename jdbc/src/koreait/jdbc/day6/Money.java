package koreait.jdbc.day6;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Money {
	private int custno; // 회원번호
	private int salemo; // 회원성명
	private int pcost; // 회원전화
	private int amount; // 주소
	private long price; // 가입일자
	private String pcode; // 고객등급
	private Date sdate; // 거주도시
}
