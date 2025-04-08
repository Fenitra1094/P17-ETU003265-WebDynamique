<%@ page import="models.Emp" %>
<%@ page import="models.Dept" %>
<%@ page import="dao.EmpDAO" %>
<%@ page import="dao.DeptDAO" %>
<%@ page import="java.util.List" %>

<% 
    List<Dept> departement = (List<Dept>) request.getAttribute("dept_existant");
    Emp employe = (Emp) request.getAttribute("employe");
    boolean isEdit = (employe != null);
%>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulaire Employé</title>
        <link rel="stylesheet"  type = "text/css" href="web/assets/style.css">
    </head>
<body>
    <h2>Formulaire Employé</h2>
    <form action="EmpServlet" method="post">
       
        <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= employe.getIdEmp() %>">
        <% } %>

        <label for="nom">Nom:</label>
        <input type="text" id="nomEmploye" name="nom" 
                   value="<%= isEdit ? employe.getNom() : "" %>">

        <label for="email">Email:</label>
        <% if (employe != null) { %>
            <input type="email" id="email" name="email" value="<%= employe.getEmail() %>" >
        <% } else { %> 
            <input type="email" id="email" name="email" required>
        <% } %>

        <label for="departement">Département:</label>
        <select class="form-control" id="idDepart" name="departement">
            <% for (Dept dep : departement) { %>
                <option value="<%= dep.getId() %>" 
                    <% if (isEdit && employe.getIdDept() == dep.getId()) { %> selected <% } %> >
                    <%= dep.getNom() %>
                </option>
            <% } %>
        </select>
        

          
         
    


        <input type="submit" value="Valider">
    </form>
</body>
</html>
