app.service('userService',function($http){

    this.login=function(entity){
        return $http.post('../user/login.do',entity);
    }
    
    this.findLoginName=function () {
        return $http.get("../user/name.do");
    }

    this.logout=function () {
        return $http.get("../user/logout.do");
    }

});