<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SQL Query Executor</title>
</head>
<body>
<center>
    <h2>SQL Query Executor for Tables: group_of_goods, goods, storage</h2>

    <h3>Enter SQL Query</h3>
    <form action="ExecuteQueryServlet" method="POST">
        <table>
            <tr>
                <td><label for="query">SQL Query:</label></td>
            </tr>
            <tr>
                <td>
                    <textarea name="query" id="query" rows="4" cols="60" placeholder="For example: SELECT * FROM table_name;"></textarea>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <input type="submit" value="Execute Query">
                </td>
            </tr>
        </table>
    </form>

    <div id="results">
        <h3>Query Result:</h3>
        <pre>${queryResult}</pre>
    </div>
</center>
</body>
</html>
