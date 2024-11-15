package org.example.lab3_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/Servlet")
public class ExecuteQueryServlet extends HttpServlet {

    private static Connection getConnection() throws SQLException {
        return Database_connection.getConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("query");
        StringBuilder queryResult = new StringBuilder();

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Виконуємо запит
            boolean hasResultSet = stmt.execute(query);

            if (hasResultSet) {
                ResultSet rs = stmt.getResultSet();
                int columns = rs.getMetaData().getColumnCount();

                // Формуємо HTML-таблицю з результатом
                queryResult.append("<table border='1'>");
                queryResult.append("<tr>");
                for (int i = 1; i <= columns; i++) {
                    queryResult.append("<th>").append(rs.getMetaData().getColumnName(i)).append("</th>");
                }
                queryResult.append("</tr>");

                while (rs.next()) {
                    queryResult.append("<tr>");
                    for (int i = 1; i <= columns; i++) {
                        queryResult.append("<td>").append(rs.getString(i)).append("</td>");
                    }
                    queryResult.append("</tr>");
                }
                queryResult.append("</table>");
            } else {
                queryResult.append("<p>Request completed, lines changed: ").append(stmt.getUpdateCount()).append("</p>");
            }
        } catch (Exception e) {
            queryResult.append("<p style='color: red;'>Request execution error: ").append(e.getMessage()).append("</p>");
        }

        // Передаємо результат на JSP-сторінку для відображення
        request.setAttribute("queryResult", queryResult.toString());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
