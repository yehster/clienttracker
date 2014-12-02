/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

ko_bound=false;
function parse_client_list(data)
{
    tracker_vm.clients.removeAll();
    for(var idx=0;idx<data.length;idx++)
    {
        tracker_vm.clients.push(data[idx]);
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

var tracker_vm = {
    clients: ko.observableArray()
};
