<%@ page import="models.Credit" %>
<%@ page import="models.Depense" %>
<%@ page import="dao.CreditDAO" %>
<%@ page import="dao.DepenseDAO" %>
<%@ page import="java.util.List" %>

<% 
    List<Credit> credit = (List<Credit>) request.getAttribute("credit_existant");
%>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulaire Depense</title>
        <link rel="stylesheet"  type = "text/css" href="web/assets/style.css">
    </head>
<body>
    <h2>Formulaire Depense</h2>
    <form action="depenseServlet" method="post">
       

        

        <label for="credit">Libelle:</label>
        <select class="form-control" id="idCredit" name="idCredit">
            <% for (Credit cre : credit) { %>
                <option value="<%= cre.getId() %>" >
                    <%= cre.getLibelle() %>
                </option>
            <% } %>
        </select>
        <p>Montant : <input type="text" name="montant_depense"></p>


        <input type="submit" value="Valider">
    </form>
</body>
</html>
