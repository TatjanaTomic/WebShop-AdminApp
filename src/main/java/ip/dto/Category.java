package ip.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6402879056586405552L;
	private Integer id;
	private String name;
	private boolean isDeleted;
	private List<Attribute> attributes = new ArrayList<>();
	private Integer idParentCategory;

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(Integer id, String name, boolean isDeleted, Integer idParentCategory) {
		super();
		this.id = id;
		this.name = name;
		this.isDeleted = isDeleted;
		this.idParentCategory = idParentCategory;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, isDeleted, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && isDeleted == other.isDeleted && Objects.equals(name, other.name);
	}

	public Integer getIdParentCategory() {
		return idParentCategory;
	}

	public void setIdParentCategory(Integer idParentCategory) {
		this.idParentCategory = idParentCategory;
	}

}
