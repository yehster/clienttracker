<%-- 
    Document   : assessmentslist
    Created on : Dec 2, 2014, 4:52:16 PM
    Author     : yehster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/html" id="assessmentslist">
    <table>
        <thead>
            <tr>
                <th>Date</th>
            </tr>
        </thead>
        <tbody data-bind="foreach: $data">
            <tr data-bind="click: editAssessment">
                <td>
                    <span data-bind="text: month"></span>/<span data-bind="text:year"></span>
                </td>
                <td>
                    <span data-bind="text: AssessmentName"></span>
                </td>
                <td>
                    <span data-bind="text:DisplayInfo"></span>
                </td>
            </tr>
        </tbody>
    </table>
</script>