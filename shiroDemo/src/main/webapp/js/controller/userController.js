app.controller('userController',function($scope,userService){

    //登录
    $scope.login=function(){
        userService.login($scope.entity).success(
            function(response){
                if (response.success){
                   location.href="/admin/index.html";
                } else{
                    alert(response.message);
                }
            }
        );
    }

    //获取登录用户名
    $scope.findLoginName = function(){
        userService.findLoginName().success(
            function(response){
                $scope.loginEntity = response;
            }
        );
    }

    //退出
    $scope.logout = function () {
        userService.logout().success(
            function (response) {
                if (response.success) {
                    location.href="../login.html";
                }else{
                    alert(response.message);
                }
            }
        );
    }

});
