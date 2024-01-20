package MyPage;

import java.io.IOException;

import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Sign.User;


@WebServlet(urlPatterns = "/MyPage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO dao;
	private ServletContext ctx;
	
	private HttpSession session;
	private final String START_PAGE = "MyPage.jsp";
	
	public MyPageController() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		dao = new UserDAO();
		ctx = getServletContext();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		dao = new UserDAO();
		
		Method m;
		String view = null;
		
		if(action == null) {
			action = "showMyInformation";
		}
		
		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class);	
			view = (String)m.invoke(this, request);
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("error");
			request.setAttribute("error", "action X");
			view = START_PAGE;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
	public String showMyInformation(HttpServletRequest request)
	{
		session = request.getSession();
		String userId = (String) session.getAttribute("id");
		User requestUser = null;
	    try {
	    	requestUser = dao.getUser(userId);
	    	request.setAttribute("user", requestUser);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	        ctx.log("Error : getUser");
	        request.setAttribute("error", "getUser");
	    }
		return "MyPage.jsp";
	}
	
}
