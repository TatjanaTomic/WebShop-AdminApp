package ip.dto;

import java.io.Serializable;
import java.util.Objects;

public class Attribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7700054520312863163L;
	private Integer id;
	private Integer idCategory;
	private String name;
	private boolean isDeleted;
	
	public Attribute() {
		// TODO Auto-generated constructor stub
	}

	public Attribute(Integer id, Integer idCategory, String name, boolean isDeleted) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.name = name;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, idCategory, isDeleted, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		return Objects.equals(id, other.id) && Objects.equals(idCategory, other.idCategory)
				&& isDeleted == other.isDeleted && Objects.equals(name, other.name);
	}
	
	
}
