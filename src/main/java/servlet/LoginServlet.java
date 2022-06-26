package servlet;

import dao.SqlDao;
import dto.LoginUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String user = request.getParameter("user_name");
        String password = request.getParameter("password");

        SqlDao sql = new SqlDao();
        List<LoginUser> login = sql.check(user, password);

        String login_user = login.get(0).getName();
        String login_pass = login.get(0).getPassword();

        if(user.equals(login_user) && password.equals(login_pass)){
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/user_list.jsp");
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
