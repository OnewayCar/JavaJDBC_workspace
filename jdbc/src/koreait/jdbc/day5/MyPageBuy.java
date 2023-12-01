package koreait.jdbc.day5;

import java.sql.Date;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyPageBuy {
	private Date buy_date;
	private String customid;
	private String pcode;
	private String pname;
	private int price;
	private int quantity;
	private long total;

}
