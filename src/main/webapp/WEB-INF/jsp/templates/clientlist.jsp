<%-- 
    Document   : clientlist
    Created on : Dec 2, 2014, 10:39:27 AM
    Author     : yehster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/html" id="clientlist">
    <h1>data</h1>
    <table>
        <tbody data-bind="foreach: $data">
            <tr>
                <td data-bind="text: id" />
            </tr>
        </tbody>
    </table>
</script>