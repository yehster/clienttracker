/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

ko_bound=false;
function compare_assessment_order(a,b)
{
    var total_a=a.year*12 + a.month;
    var total_b=b.year*12 + b.month;
    if(total_a>total_b)
    {
        return 1;
    }
    if(total_a<total_b)
    {
        return -1;
    }
    if(total_a==total_b)
    {
        if(a.AssessmentName>b.AssessmentName)
        {
            return 1
        }
        if(a.AssessmentName==b.AssessmentName)
        {
            return 0
        }
        if(a.AssessmentName<b.AssessmentName)
        {
            return -1
        }
    }

}
function client_info(data)
{
    this.id=ko.observable(data.id);
    this.admAgeMonths=ko.observable(data.admissionAgeInMonths % 12);
    this.admAgeYears=ko.observable(Math.floor(data.admissionAgeInMonths / 12));
    this.admissionYear=ko.observable(data.admissionYear);
    this.admissionMonth=ko.observable(data.admissionMonth);
    this.lgbtq=ko.observable(data.lgbtq);
    data.assessments.sort(compare_assessment_order);
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
    tracker_vm.editInfo.assessmentEditData(null);
    tracker_vm.editInfo.assessmentEditType(null);
    $.getJSON(client_get,
    {
        id:data.id()
    },
        function(client)
        {
            update_client_info(client);
        });

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
            $.getJSON(client_get,
            {
                id:tracker_vm.editInfo.clientEdit().id
            },
                function(client)
                {
                    update_client_info(client);
                    editAssessment(assessment);
                });

        }
    );     
    
}

// Takes a JSON client representation and brings it into the view model
function update_client_info(data)
{
    var client = new client_info(data);
    tracker_vm.editInfo.clientEdit(client);
    tracker_vm.assessments(client.assessments());

}
function deleteAssessment(data)
{
    if(confirm("Delete entry?\n"+data.year() +"/" +data.month() + ":"+data.DisplayInfo()))
    {
        $.post(assessment_delete,{assessmentID: data.id()},function(result){
                    tracker_vm.editInfo.assessmentEditData(null);
                    tracker_vm.editInfo.assessmentEditType(null);
                    $.getJSON(client_get,
                        {
                            id:tracker_vm.editInfo.clientEdit().id
                        },
                        function(client)
                        {
                            update_client_info(client);
                        }
                    );
                }
            );
        
    }
}

function saveAssessment(data)
{
    var assessment_json={};
    for(var property in data)
    {
        assessment_json[property]=data[property]();
    }
    var post_url=assessment_update+data.AssessmentName()+".do";
    $.post(post_url,assessment_json,
    function(data){
                        $.getJSON(client_get,
                        {
                            id:tracker_vm.editInfo.clientEdit().id
                        },
                        function(client)
                        {
                            update_client_info(client);
                        }
                );
        });
}