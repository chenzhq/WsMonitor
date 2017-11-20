(function() {
    var db = {

        loadData: function(filter) {
        },

        insertItem: function(insertingClient) {
            this.clients.push(insertingClient);
        },

        updateItem: function(updatingClient) { },

        deleteItem: function(deletingClient) {
            var clientIndex = $.inArray(deletingClient, this.clients);
            this.clients.splice(clientIndex, 1);
        }

    };

    window.db = db;


    db.clients = [
		{
            "Name": "",
            "Age": "",
            "Country": "",
            "Address":  "",
            "Married":  "",
        },
		{
            "Name": "",
            "Age": "",
            "Country": "",
            "Address":  "",
            "Married":  "",
        },
		{
            "Name": "",
            "Age": "",
            "Country": "",
            "Address":  "",
            "Married":  "",
        }
     ];
/**/
}());
