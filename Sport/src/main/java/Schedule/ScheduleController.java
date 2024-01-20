package Schedule;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = "/Schedule")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location = "c:/Temp/img")
public class ScheduleController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private ScheduleDAO dao;
	private ServletContext ctx;
	
	private HttpSession session;

	private final String START_PAGE = "schedule.jsp";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		dao = new ScheduleDAO();

		Method m;
		String view = null;

		if (action == null) {
			action = "listSchedules";
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
		if (view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		dao = new ScheduleDAO();
		ctx = getServletContext();

	}
	
	public String recentMatch(HttpServletRequest request) {
		try {
			Schedule schedule = dao.recentMatch();
			request.setAttribute("match", schedule);
			
		} catch (Exception e) {
			ctx.log("Error : recentMatch");
			request.setAttribute("error", "recentMatch");
		} finally {
			return "mainpage.jsp";			
		}
	}
	
	public String listSchedules(HttpServletRequest request) {
		List<Schedule> list;
		
		try {
			list = dao.getAll();
			request.setAttribute("schedulelist", list);
		} catch (Exception e) {
			ctx.log("Error : listNews");
			request.setAttribute("error", "listNews");
		}

		return "schedule.jsp";
	}
	
	public String votePoint1(HttpServletRequest request) {
		session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		int sid = Integer.parseInt(request.getParameter("sid"));
		int choice = Integer.parseInt(request.getParameter("choice"));		
		int score = Integer.parseInt(request.getParameter("score"));

		session.setAttribute("point", (Integer) session.getAttribute("point")-score);
		String team = "";
		try {
			team = dao.getSchedule(sid, choice);
		} catch (Exception e) {
			ctx.log("Error : getSchedule");
			request.setAttribute("error", "getSchedule");
		}
		
		Vote v = new Vote();
		try {
			v.setUserid(id);
			v.setPoint(score);
			v.setSid(sid);
			v.setVoteParticipant(team);
			
			dao.addVote(v);
		} catch (Exception e) {
			ctx.log("Error : addVote");
			request.setAttribute("error", "addVote");
		}
		
		try {
			dao.updatePoint(id, score);
		} catch (Exception e) {
			ctx.log("Error : updatePoint");
			request.setAttribute("error", "updatePoint");
		}

		return "redirect:/Schedule?action=listSchedules";
	}
	
	public String lastSchedule(HttpServletRequest request) {
		
		List<Schedule> list;
		
		try {
			list = dao.getLastSchedule();
			request.setAttribute("lastSchedule", list);
		} catch (Exception e) {
			ctx.log("Error : lastSchedule");
			request.setAttribute("error", "lastSchedule");
		}
		
		return "lastSchedule.jsp"; 
		
	}
	
	public String selectWinner(HttpServletRequest request) {
		int sid = Integer.parseInt(request.getParameter("sid"));
		int win = Integer.parseInt(request.getParameter("win"));
		String winner;
		
		// schedule에 winner 저장
		try {
			winner = dao.getSchedule(sid, win);
			dao.winnerSave(sid, winner);
		} catch(Exception e) {
			ctx.log("Error : winnerSave");
			request.setAttribute("error", "winnerSave");
		}
		
		// 투표한 인원에 대해 users에 point 반영
		try {
			winner = dao.getSchedule(sid, win);
			dao.winnerPoint(sid, winner);
		} catch(Exception e) {
			ctx.log("Error : winnerPoint");
			request.setAttribute("error", "winnerPoint");
		}
		
		return "redirect:/Schedule?action=lastSchedule";
	}
	
	public String addSchedule(HttpServletRequest request) {
		Schedule schedule = new Schedule();
		
		try {
			schedule.setSubject(request.getParameter("subject"));
			schedule.setDate(request.getParameter("date"));
			schedule.setParticipant1(request.getParameter("participant1"));
			schedule.setParticipant2(request.getParameter("participant2"));
			
			dao.addSchedule(schedule);
			return "redirect:/Schedule?action=listSchedules";
		} catch(Exception e) {
			ctx.log("Error : addSchedule");
			request.setAttribute("error", "addSchedule");
			return "addSchedule.jsp";
		}
	}
	
}
