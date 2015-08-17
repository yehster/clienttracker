<%-- 
    Document   : clientlist
    Created on : Dec 2, 2014, 10:39:27 AM
    Author     : yehster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/html" id="clientlist">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Years</th>
                <th>Months</th>
                <th>LGBTQ</th>
                <th>Mommy and Me</th>
                <th>Trauma</th>
                <th>Medical</th>
                <th>Admission</th>
            </tr>
        </thead>
        <tbody data-bind="foreach: $data">
            <tr data-bind="click: editClient">
                <td data-bind="text: id"></td>
                <td data-bind="text: admAgeYears"></td>
                <td data-bind="text: admAgeMonths"></td>
                <td data-bind="text: lgbtq"></td>
                <td data-bind="text: mommy_and_me"></td>
                <td data-bind="text: trauma"></td>
                <td data-bind="text: medical"></td>
                <td ><span data-bind="text: admissionMonth"></span>/<span data-bind="text: admissionYear"></span></td>
            </tr>
        </tbody>
    </table>
</script>