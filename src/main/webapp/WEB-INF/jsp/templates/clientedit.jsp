<%-- 
    Document   : clientedit
    Created on : Dec 2, 2014, 3:17:04 PM
    Author     : yehster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/html" id="clientedit">
    <button data-bind="click: new_client">New Client</button>
    <!-- ko if: clientEdit() -->
    <table>
        <tbody>
            <tr>
                <td>ID</td>
                <td><input type="text" data-bind="value:clientEdit().id,disable: editMode()=='edit'"/></td>
            </tr>
            <tr>
                <td>Age Years</td>
                <td><input type="text" data-bind="value:clientEdit().admAgeYears"/></td>
            </tr>
            <tr>
                <td>Age Months</td>
                <td><input type="text" data-bind="value:clientEdit().admAgeMonths"/></td>
            </tr>
            <tr>
                <td>LGBTQ</td>
                <td><input type="text" data-bind="value:clientEdit().lgbtq"/></td>
            </tr>
            <tr>
                <td>Admission Year</td>
                <td><input type="text" data-bind="value:clientEdit().admissionYear"/></td>
            </tr>
            <tr>
                <td>Admission Month</td>
                <td><input type="text" data-bind="value:clientEdit().admissionMonth"/></td>
            </tr>
        </tbody>
    </table> 
    <button data-bind="click: clientSave">Save</button><button data-bind="click: clientCancel">Cancel</button>
    <!-- /ko -->
    
</script>