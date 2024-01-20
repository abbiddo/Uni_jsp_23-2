package MyPage;

import java.io.IOException;

import java.lang.reflect.Method;
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

import Sign.User;
import Schedule.Vote;

@WebServlet("/MyVote")
public class MyVoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private VoteDAO votedao;
	private ServletContext ctx;
	private HttpSession session;
	private final String START_PAGE = "MyVotes.jsp";
       
    public MyVoteController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		votedao = new VoteDAO();
		ctx = getServletContext();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		votedao = new VoteDAO();
		
		Method m;
		String view = null;
		
		if(action == null) {
			System.out.println(action);
			action = "showMyVotes";
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
	public String showMyVotes(HttpServletRequest request)
	{
		List<Vote> vlist;
		session = request.getSession();
		String userId = (String) session.getAttribute("id");
		
	    try {
	    	vlist = votedao.getAll(userId);
	    	request.setAttribute("votelist",vlist);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	        ctx.log("Error:showMyVotes_getAll");
	        request.setAttribute("error", "showMyVotes_getAll");
	    }
		return "MyVotes.jsp";
	}

}
