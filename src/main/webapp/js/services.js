angular.module('telephonyApp.contactServices',[]).factory('Contact',function($resource){
    return $resource('http://localhost:8080/api/telephony/contacts/:id',{id:'@id'},{
        update: {
            method: 'PUT'
        },
        save:{
            method: 'POST'
        },
        delete: {
            method: 'DELETE'
        }
    });
});

angular.module('telephonyApp.callLogServices',[]).factory('CallLog',function($resource){
    return $resource('http://localhost:8080/api/telephony/calllogs/:id',{id:'@id'},{
        update: {
            method: 'PUT'
        },
        delete: {
            method: 'DELETE',
            params: {
              id: "@id"
            }
        }
    });
});