package Schema;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
	
	/*
	 * There was an issue with lombok jar 
	 * https://stackoverflow.com/questions/11803948/lombok-is-not-generating-getter-and-setter
	 * Refer to above solution and fixed it refer to 20 solution
	 */
	private String name;
	private String email;
	private String gender;
	private String status;
	private String id;
	

	public UserPojo(String name, String email, String gender, String status) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}




}
