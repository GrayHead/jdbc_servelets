package logic;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/pages/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xxx?serverTimezone=UTC", "root", null);
            String userName = req.getParameter("userName");
            int userAge = Integer.parseInt(req.getParameter("userAge"));


            String insert = "insert into users(username,userage) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, userAge);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("select * from users");
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                int age = resultSet.getInt(3);
                User user = new User(id, name, age);
                users.add(user);
            }


            req.setAttribute("users", users);
            connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        req
                .getRequestDispatcher("/pages/index.jsp")
                .forward(req, resp);

    }
}
