package pojo;

import java.util.List;


public class ComplexPojoEncap {
	
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tags> tags;
	private String status;
	
	public ComplexPojoEncap() {
		//super();
	}

	public ComplexPojoEncap(Integer id, Category category, String name, List<String> photoUrls, List<Tags> tags,
			String status) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	


	public static class Category {
		private Integer id;
		private String name;
		
		
		public Category() {
			//super();
		}

		public Category(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
	


	public static class Tags {
		private Integer id;
		private String name;
		
		public Tags() {
			//super();
		}

		public Tags(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<String> getPhotoUrls() {
		return photoUrls;
	}



	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}



	public List<Tags> getTags() {
		return tags;
	}



	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
 
}
