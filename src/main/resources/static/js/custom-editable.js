//editable page table settings
'use strict'
function custom_edittable(fields,str) {
    $("#jsGrid").jsGrid({
        /*width: "100%",*/
        width: str+68,
        height: "200%",

        filtering: false,
        editing: true,
        sorting: true,
        paging: true,

        data: db.clients,
        fields:fields
        /*fields: [
            {name: "Name", type: "text", width: 250},
            {name: "Age", type: "text", width: 250},
            {name: "Address", type: "text", width: 300},
            {name: "Country", type: "text", width: 200},
            {type: "control"}
        ]*/
    });
}
$('#data_table').editableTableWidget();
$('#data_table').DataTable();
