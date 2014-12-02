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
        admissionYear:ko.observable(2014),
        admissionMonth:ko.observable(12),
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
var tracker_vm = {
    clients: ko.observableArray(),
    editInfo: {
        clientEdit: ko.observable(),
        editMode: ko.observable(false)
        
    },
    assessments: ko.observableArray()
};
