package ip.bean;

import java.io.Serializable;
import java.util.List;

import ip.dao.AttributeDAO;
import ip.dao.CategoryDAO;
import ip.dto.Attribute;
import ip.dto.Category;

public class CategoriesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6148848798147846018L;

	public CategoriesBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Category> getCategories() {
		return CategoryDAO.selectAll();
	}
	
	public Category getById(Integer id) {
		return CategoryDAO.selectById(id);
	}
	
	public boolean isNameAllowed(String name) {
		return !CategoryDAO.existsByName(name);
	}
	
	public boolean addCategory(Category category) {
		return CategoryDAO.insertCategory(category);
	}
	
	public boolean deleteCategory(Integer id) {
		return CategoryDAO.deleteById(id);
	}
	
	public boolean updateCategory(Category category) {
		return CategoryDAO.updateName(category);
	}
	
	public boolean isAttributeNameAllowed(Integer idCategory, String name) {
		return AttributeDAO.selectAttributeByIdCategoryAndName(idCategory, name) == null;
	}
	
	public boolean addAttributeForCategory(Attribute attribute) {
		return AttributeDAO.insertAttribute(attribute);
	}
	
	public String getFullName(Category c) {
		if(c == null)
			return "";
		if(c.getIdParentCategory() != null && c != null) {
			return getFullName(getById(c.getIdParentCategory())) + "  >>  " + c.getName();
		}
		else {
			return c.getName();
		}
	}

	public boolean deleteAttribute(Integer id) {
		return CategoryDAO.deleteAttributeById(id);
	}
}
