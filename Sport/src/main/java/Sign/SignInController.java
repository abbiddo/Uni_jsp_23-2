package Sign;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;

@WebServlet("/Login")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userdao;
	private ServletContext ctx;
	private final String START_PAGE = "signIn.jsp";
	private final String adminCode = "admin";
      
	public HttpSession session;
	
	
    public SignInController() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userdao = new UserDAO();
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		userdao = new UserDAO();
		
		Method m;
		String view = null;
		
		if(action == null) {
			action = "loginUser";
		}
		
		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			
			view = (String)m.invoke(this, request);
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("no action");
			request.setAttribute("error", "action parameter error");
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
	
	public String login(HttpServletRequest request) {
		Login user = new Login();
		User requestUser = null;
		
		try {				
			user.setId(request.getParameter("id"));
			user.setPassword(request.getParameter("password"));
			
			requestUser = userdao.LoginUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("Error : login");
			request.setAttribute("error", "login");
			return "signIn.jsp";
		}
		
		if(requestUser != null) {			
			session = request.getSession();
			
			session.setAttribute("name", requestUser.getName());
			session.setAttribute("id", requestUser.getId());
			session.setAttribute("role", requestUser.getRole());
			session.setAttribute("point", requestUser.getPoint());
			
			return "redirect:/Schedule?action=recentMatch";
		}
		else {
			return "signIn.jsp";
		}
		
	}
	
	public String addUser(HttpServletRequest request) {
		User user = new User();

		request.setAttribute("error", null);
		if(!request.getParameter("password").equals(request.getParameter("re_password"))) {
			ctx.log("Error : confirm password");
			request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "signUp.jsp";
		}
		try {
			
			user.setId(request.getParameter("id"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setMajor(request.getParameter("major"));
			user.setStNumber(Integer.parseInt(request.getParameter("stnumber")));
			
			
			if(user.getId() == null || user.getPassword() == null || user.getName() == null || user.getMajor() == null || user.getStNumber() == 0) {
				ctx.log("empty parameter");
			}
			if(request.getParameter("adminNum").equals(adminCode)) {
				user.setRole("admin");
			}
			else {
				user.setRole("user");
			}
			
			if(!userdao.addUser(user)) {
				ctx.log("error : duplicate nickname");
				request.setAttribute("error", "중복된 아이디입니다.");
				return "signUp.jsp";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("Error : addUser");
			request.setAttribute("error", "addUser");
			return "signUp.jsp";
		}
		return "signIn.jsp";
	}
	
	public String loginUser(HttpServletRequest request) {
		session = request.getSession();
		session.invalidate();
		return "signIn.jsp";
	}
	
}
