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
        <span>Month&nbsp;</span><input size="4" type="text" data-bind="value: month"></input>
        <span>Year&nbsp;</span><input size="4" type="text" data-bind="value: year"></input>
    </div>
    <div data-bind="template: {name:assessmentEditType(), data: assessmentEditData }"></div>
    <div data-bind="with: assessmentEditData">
        
        <button data-bind="click: saveAssessment">Save</button>
        <button data-bind="click: deleteAssessment">Delete</button>
    </div>
    <!-- /ko -->
</script>

<script type="text/html" id="assessmentEditdiagnosis">
    <!-- ko if: $data -->

        <!-- ko if: AssessmentName()=="diagnosis" -->
            <div>Diagnosis</div>
            <table>
                <tbody>
                    <tr>
                        <td>Code</td>
                        <td>
                            <input type="text" data-bind="value:code"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>Code Type</td>
                        <td>
                            <input type="text" data-bind="value:codeType"></input>
                        </td>
                    </tr>  
                    <tr>
                        <td>Description</td>
                        <td>
                            <input type="text" data-bind="value: description"></input>
                        </td>
                    </tr>
                </tbody>
            </table>
        <!-- /ko -->
    
    <!-- /ko -->
</script>

<script type="text/html" id="assessmentEditACE">
    <!-- ko if: $data -->
        <!-- ko if: AssessmentName()=="ACE" -->
            <div>ACE</div>
            <span>Score</span>
            <input size="3" type="text" data-bind="value: score"></input>
        <!-- /ko -->
    <!-- /ko -->
</script>

<script type="text/html" id="assessmentEditSASSI">
    <!-- ko if: $data -->
        <!-- ko if: AssessmentName()=="SASSI" -->
            <div>SASSI</div>
            <span>Risk</span>
            <select data-bind="options: ['low','medium','high'],value:risk"></select>
        <!-- /ko -->
    <!-- /ko -->
</script>