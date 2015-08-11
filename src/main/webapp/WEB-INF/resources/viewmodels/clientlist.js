/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

ko_bound=false;
function client_info(data)
{
    this.id=ko.observable(data.id);
    this.admAgeMonths=ko.observable(data.admissionAgeInMonths % 12);
    this.admAgeYears=ko.observable(Math.floor(data.admissionAgeInMonths / 12));
    this.admissionYear=ko.observable(data.admissionYear);
    this.admissionMonth=ko.observable(data.admissionMonth);
    this.lgbtq=ko.observable(data.lgbtq);
    this.assessments=ko.observableArray(data.assessments);
    return this;
}
function parse_client_list(data)
{
    tracker_vm.clients.removeAll();
    for(var idx=0;idx<data.length;idx++)
    {
        var cur_entry=new client_info(data[idx]);
        tracker_vm.clients.push(cur_entry);
    }
    if(!ko_bound)
    {
        ko_bound=true;
        ko.applyBindings(tracker_vm);    
    }
}

function retrieve_client_list()
{
    $.getJSON(client_ajax,{},parse_client_list)
}

function new_client(data,evt)
{
    tracker_vm.editInfo.editMode("new");
    var new_client = {
        id:ko.observable(""),
        admAgeMonths:ko.observable(0),
        admAgeYears:ko.observable(0),
        admissionYear:ko.observable(2015),
        admissionMonth:ko.observable(7),
        lgbtq:ko.observable("")
    };
    tracker_vm.editInfo.clientEdit(new_client);
}

function clientSave(data,evt)
{
    if(tracker_vm.editInfo.clientEdit().id()==="")
    {
        alert("Must specify an ID!");
        return false;
    }
    var parameters={
                id: tracker_vm.editInfo.clientEdit().id(),
                agem: tracker_vm.editInfo.clientEdit().admAgeMonths(),
                agey: tracker_vm.editInfo.clientEdit().admAgeYears(),
                admy: tracker_vm.editInfo.clientEdit().admissionYear(),
                admm: tracker_vm.editInfo.clientEdit().admissionMonth(),
                lgbtq: tracker_vm.editInfo.clientEdit().lgbtq(),

            }
    if(data.editMode()==="new")
    {
        $.getJSON(client_new,parameters,
            function(data)
            {
                retrieve_client_list();
                tracker_vm.editInfo.clientEdit(null);            
            }
        );        
    }
    else if(data.editMode()==="edit")
    {
        $.getJSON(client_edit,parameters,
            function(data)
            {
                retrieve_client_list();
                tracker_vm.editInfo.clientEdit(null);            
            }
        );   
    }
}

function editClient(data,evt)
{
    tracker_vm.editInfo.editMode("edit");
    tracker_vm.editInfo.clientEdit(data);
    tracker_vm.assessments(data.assessments());
}

function clientCancel(data,evt)
{
    retrieve_client_list();
    tracker_vm.editInfo.clientEdit(null);
}


function assessment(id,name,month,year)
{
    this.id=ko.observable(id);
    this.month=ko.observable(month);
    this.year=ko.observable(year);
    this.name=name;
    return this;
}
function assessmentOption(name,description,fields)
{
    this.name=name;
    this.description=description;
    this.fields=ko.observableArray(fields);
    return this;
}
var assessmentTypes={};
assessmentTypes["diagnosis"]= new assessmentOption("diagnosis","diagnosis",["CodeType","Code"]);
assessmentTypes["ACE"]= new assessmentOption("ACE","ACE",["Score"]);
assessmentTypes["SASSI"]=new assessmentOption("SASSI","SASSI",["Risk"]);


assessmentTypesList=[];
for(var propertyName in assessmentTypes)
{

    assessmentTypesList.push(assessmentTypes[propertyName]);
}

var tracker_vm = {
    clients: ko.observableArray(),
    editInfo: {
        clientEdit: ko.observable(),
        editMode: ko.observable(false),
        assessmentOptions: ko.observableArray(assessmentTypesList),
        assessmentEditMode: ko.observable(false),
        assessmentEditType: ko.observable(),
        assessmentEditData: ko.observable(),
        
    },
    assessments: ko.observableArray()

};


function editAssessment(data)
{

    $.getJSON(assessment_get+data.AssessmentName+".do",
        {assessmentID: data.id },
        function(assessment)
        {
            var assessmentData={};
            for(var property in assessment)
            {
                assessmentData[property]=ko.observable(assessment[property]);
            }
             tracker_vm.editInfo.assessmentEditData(assessmentData);
             tracker_vm.editInfo.assessmentEditType("assessmentEdit"+data.AssessmentName);


            
        }
    );
}

function newAssessment(data)
{   
    var parameters={
        clientID: tracker_vm.editInfo.clientEdit().id,
        assessmentName: data.name
    }
        $.getJSON(assessment_new,parameters,
        function(assessment)
        {
            tracker_vm.assessments.push(assessment);
            editAssessment(assessment);
        }
    );     
//    assessmentEditMode(true);
    
}

function deleteAssessment(data)
{
    confirm("Delete entry?\n"+data.DisplayInfo());
}