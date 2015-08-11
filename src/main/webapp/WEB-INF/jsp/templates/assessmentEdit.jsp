<%-- 
    Document   : assessmentEdit
    Created on : Jul 21, 2015, 5:51:36 PM
    Author     : yehster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/html" id="assessmentEdit">
    <!-- ko if: clientEdit() -->
        <div>Add Assessment:</div>
        <div data-bind="foreach: assessmentOptions">
            <button data-bind="text:name, click: newAssessment"></button>
        </div>
        
    <!-- /ko -->
    <!-- ko if: assessmentEditType() -->
    <div data-bind="with: assessmentEditData">
        <input type="text" data-bind="value: month"></input>
        <input type="text" data-bind="value: year"></input>
    </div>
    <div data-bind="template: {name:assessmentEditType, data: assessmentEditData }"></div>
    <div data-bind="with: assessmentEditData">
        <button data-bind="click: deleteAssessment">Delete</button>
    </div>
    <!-- /ko -->
</script>

<script type="text/html" id="assessmentEditdiagnosis">
    <!-- ko if: AssessmentName()=="diagnosis" -->
        <div>Diagnosis</div>
        <input type="text" data-bind="value:code"></input>
        <input type="text" data-bind="value:codeType"></input>
        <input type="text" data-bind="value: description"></input>
    <!-- /ko -->
</script>

<script type="text/html" id="assessmentEditACE">
    <!-- ko if: AssessmentName()=="ACE" -->
        <div>ACE</div>
    <!-- /ko -->
</script>

<script type="text/html" id="assessmentEditSASSI">
    <!-- ko if: AssessmentName()=="SASSI" -->
        <div>SASSI</div>
        <select data-bind="options: ['low','medium','high'],value:risk"></select>
    <!-- /ko -->
</script>