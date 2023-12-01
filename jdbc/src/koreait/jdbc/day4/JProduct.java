package koreait.jdbc.day4;
// JProduct_DTO

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;import lombok.ToString;


@ToString
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class JProduct {
	private String pcode;
	private String category;
	private String pname;
	private int price;
}
