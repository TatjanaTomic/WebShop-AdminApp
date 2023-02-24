package ip.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ip.bean.AdminBean;
import ip.bean.CategoriesBean;
import ip.bean.LogsBean;
import ip.bean.UsersBean;
import ip.dto.Attribute;
import ip.dto.Category;
import ip.dto.User;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainController() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String address = "/WEB-INF/pages/404.jsp";
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		session.setAttribute("notification", "");
		session.setAttribute("resultNotification", "");
		
		if (action == null || ("").equals(action)) {
			address = "/WEB-INF/pages/Login.jsp";
		}
		else if(("login").equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			AdminBean adminBean = new AdminBean();
			if(adminBean.logIn(username, password)) {
				session.setAttribute("adminBean", adminBean);
				address = "/WEB-INF/pages/Categories.jsp";
			} else {
				session.setAttribute("notification", "Pogrešno korisničko ime ili lozinka.");
				address = "/WEB-INF/pages/Login.jsp";
			}
		}
		else if ("logout".equals(action)) {
			session.invalidate();
			address = "/WEB-INF/pages/Login.jsp";
		}
		else {
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			if (adminBean == null || !adminBean.isLoggedIn()) {
				address = "/WEB-INF/pages/Login.jsp";
			} else {
				
				if ("categories".equals(action)) {
					CategoriesBean categoriesBean = new CategoriesBean();
					session.setAttribute("categoriesBean", categoriesBean);
					address = "/WEB-INF/pages/Categories.jsp";
				}
				else if ("users".equals(action)) {
					UsersBean usersBean = new UsersBean();
					session.setAttribute("usersBean", usersBean);
					address = "/WEB-INF/pages/Users.jsp";
				}
				else if ("logs".equals(action)) {
					LogsBean logsBean = new LogsBean();
					session.setAttribute("logsBean", logsBean);
					address = "/WEB-INF/pages/Logs.jsp";
				}
				else if ("delete".equals(action) && (request.getParameter("id") != null) ) {
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
					boolean result = usersBean.deleteUser(id);
					if(result)
						session.setAttribute("resultNotification", "Nalog je uspješno obrisan!");
					else
						session.setAttribute("resultNotification", "Dogodila se greška prilikom brisanja naloga!");
					
					address = "/WEB-INF/pages/Users.jsp";
				}
				else if ("newUser".equals(action)) {
					address = "/WEB-INF/pages/NewUser.jsp";
				}
				else if ("addNewUser".equals(action) && (request.getParameter("submit") != null)  && (request.getParameter("username") != null)) {
					
					UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
					String username = request.getParameter("username");
					if(!usersBean.isUsernameAllowed(username)) {
						session.setAttribute("notification", "Korisničko ime je zauzeto!");	
					}
					else {
						User user = new User(null, request.getParameter("firstName"), request.getParameter("lastName"),
								request.getParameter("username"), request.getParameter("city"), request.getParameter("avatar"),
								request.getParameter("mail"), false, false);
						if(usersBean.addUser(user)) {
							session.setAttribute("notification", "Novi korisnik je uspješno dodan!");
						}
						else {
							session.setAttribute("notification", "Desila se greška prilikom dodavanja novog korisnika!");
						}
					}
					address = "/WEB-INF/pages/NewUser.jsp";
				}
				else if("addNewCategory".equals(action) && (request.getParameter("submit") != null)  && (request.getParameter("name") != null)) {
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					String name = request.getParameter("name");
					if(!categoriesBean.isNameAllowed(name)) {
						session.setAttribute("notification", "Već postoji kategorija sa datim nazivom!");	
					}
					else {
						Category category = new Category(null, name, false, null);
						if(categoriesBean.addCategory(category)) {
							session.setAttribute("notification", "Nova kategorija je uspješno dodana!");
						}
						else {
							session.setAttribute("notification", "Desila se greška prilikom dodavanja nove kategorije!");
						}
					}
					
					address = "/WEB-INF/pages/Categories.jsp";
				}
				else if("addSubcategory".equals(action) && (request.getParameter("submit") != null)  && (request.getParameter("nameSubcategory") != null) && (request.getParameter("id") != null)) {
					
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					String name = request.getParameter("nameSubcategory");
					Integer idParentCategory = Integer.parseInt(request.getParameter("id"));
					
					if(!categoriesBean.isNameAllowed(name)) {
						session.setAttribute("notification", "Već postoji kategorija sa datim nazivom!");	
					}
					else {
						Category category = new Category(null, name, false, idParentCategory);
						if(categoriesBean.addCategory(category)) {
							session.setAttribute("notification", "Nova potkategorija je uspješno dodana!");
						}
						else {
							session.setAttribute("notification", "Desila se greška prilikom dodavanja nove potkategorije!");
						}
					}
					
					address = "/WEB-INF/pages/Categories.jsp";
				}
				else if("updateCategory".equals(action) && (request.getParameter("id") != null)) {
					address = "/WEB-INF/pages/UpdateCategory.jsp?id="+request.getParameter("id");
				}
				else if("deleteCategory".equals(action)) {
					Integer id = Integer.parseInt(request.getParameter("id"));
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					
					boolean result = categoriesBean.deleteCategory(id);
					if(result)
						session.setAttribute("resultNotification", "Kategorija je uspješno obrisana!");
					else
						session.setAttribute("resultNotification", "Dogodila se greška prilikom brisanja kategorije!");
					
					address = "/WEB-INF/pages/Categories.jsp";
				}
				else if("deleteAttribute".equals(action)) {
					Integer id = Integer.parseInt(request.getParameter("id"));
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					
					boolean result = categoriesBean.deleteAttribute(id);
					if(result)
						session.setAttribute("resultNotification", "Atribut je uspješno obrisan!");
					else
						session.setAttribute("resultNotification", "Dogodila se greška prilikom brisanja atributa!");
					
					address = "/WEB-INF/pages/Categories.jsp";
				}
				else if("saveCategory".equals(action) && (request.getParameter("submit") != null)  && (request.getParameter("name") != null) && (request.getParameter("id") != null)) {
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					String name = request.getParameter("name");
					Integer id = Integer.parseInt(request.getParameter("id"));
					Integer idParentCategory = Integer.parseInt(request.getParameter("idParentCategory"));
					
					if(!categoriesBean.isNameAllowed(name)) {
						session.setAttribute("notification", "Već postoji kategorija sa datim nazivom!");	
					}
					else {
						Category category = new Category(id, name, false, idParentCategory);
						if(categoriesBean.updateCategory(category)) {
							session.setAttribute("notification", "Podaci su uspješno sačuvani!");
						}
						else {
							session.setAttribute("notification", "Desila se greška prilikom čuvanja izmjena!");
						}
					}
					
					address = "/WEB-INF/pages/UpdateCategory.jsp?id=" + id;
				}
				else if("saveAttribute".equals(action) && (request.getParameter("submit") != null)  && (request.getParameter("name") != null) && (request.getParameter("idCategory") != null)) {
					
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					String name = request.getParameter("name");
					Integer idCategory = Integer.parseInt(request.getParameter("idCategory"));
					
					if(!categoriesBean.isAttributeNameAllowed(idCategory, name)) {
						session.setAttribute("notification", "Već postoji atribut sa datim nazivom za izabranu kategoriju!");	
					}
					else {
						Attribute attribute = new Attribute(null, idCategory, name, false);
						if(categoriesBean.addAttributeForCategory(attribute)) {
							session.setAttribute("notification", "Podaci su uspješno sačuvani!");
						}
						else {
							session.setAttribute("notification", "Desila se greška prilikom dodavanja atributa!");
						}
					}
					
					address = "/WEB-INF/pages/UpdateCategory.jsp?id=" + idCategory;
				}
				
				
			}
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
