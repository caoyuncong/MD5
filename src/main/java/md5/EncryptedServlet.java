package md5;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Administrator on 2017/2/17.
 */
@WebServlet(urlPatterns = "/md5")
public class EncryptedServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql:///db_md5?user=root&password=system";
    private static final String SQL = "SELECT * FROM db_md5.md5 WHERE encryptedPassword=?";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String encryptedPassword = req.getParameter("encryptedPassword");
        if (!encryptedPassword.matches("^[0-9a-fA-F]{32}$")) {
            req.setAttribute("message", "输入有误");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
        try {
            new Driver();
            try(Connection connection= DriverManager.getConnection(URL);
                PreparedStatement preparedStatement=connection.prepareStatement(SQL)){
                preparedStatement.setString(1, encryptedPassword);
                try (ResultSet resultSet=preparedStatement.executeQuery()){
                    if (resultSet.next()) {
                        String plainPassword=resultSet.getString("plainPassword");
                        req.setAttribute("plainPassword",plainPassword);
                    }else {
                        req.setAttribute("message", "未完成");
                    }
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
