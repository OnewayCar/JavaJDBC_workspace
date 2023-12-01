package koreait.jdbc.day4;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class JCustom {
	private String custom_id;
	private String name;
	private String email;
	private int age;
	private Date reg_date;

}
