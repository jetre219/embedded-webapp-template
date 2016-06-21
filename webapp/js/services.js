angular.module('telephonyApp.services',[]).factory('Record',function($resource){
    return $resource('http://localhost:8090/api/telephony/records/:id',{id:'@id'},{
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