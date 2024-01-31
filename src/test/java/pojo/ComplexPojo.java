package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) /* this i'm using because in Pojo the id is generated as null
so with this it wont even include id in the payload of pojo object */
public class ComplexPojo {
	
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tags> tags;
	private String status;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Category {
		private Integer id;
		private String name;	
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Tags {
		private Integer id;
		private String name;
	}
 
}
