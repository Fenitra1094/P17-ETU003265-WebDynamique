<%@ page import="models.Credit" %>
<%@ page import="java.util.List" %>
<% 
    List<Credit> listes = (List<Credit>) request.getAttribute("dashbord");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashbord</title>
        <link rel="stylesheet"  type = "text/css" href="web/assets/style.css">
        
    </head>
<body>
    <h2>Dashbord</h2>
    <table>
        <thead>
            <tr>
                <th>Credit</th>
                <th>Montant depense</th>
                <th>Reste</th>
                
            </tr>
        </thead>
        <tbody>
            <% for (Credit cre : listes) { %>
                <tr>
                    <td><%= cre.getLibelle() %></td>
                    <td><%= cre.getMontant() %></td>
                    <td><%= cre.getReste() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>