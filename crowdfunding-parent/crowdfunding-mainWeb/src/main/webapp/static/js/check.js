/**
 * 对账户进行校验
 * 字符和数字类型
 */
function checkloginaccte(){
    var loginacct = $("#loginacct").val();
    //定义用户名的正则表达式
    var reg_username = /^[a-z0-9]{5,16}$/;
    var flag = reg_username.test(loginacct);
    if (flag){
        //如果输入正确 设置边框为绿色
        $("#loginacct").css("border","");
    }else{
        //如果输入错误 设置边框为红色
        $("#loginacct").css("border","1px solid red");

    }
    return flag;
}
/***
 * 校验密码
 */
function checkuserpswd(){
    var userpswd = $("#userpswd").val();
    //匹配包括下划线的任意字符
    var reg_password = /^\w{6,16}/;
    var flag = reg_password.test(userpswd);
    if(flag){
        //输入正确
        $("#userpswd").css("border","");
    }else{
        //输入错误 设置边框颜色
        $("#userpswd").css("border","1px solid red");
    }
    return  flag;
}

/**
 * 校验邮箱
 */
function checkEmail(){
    var email = $("#email").val();
    var reg_email=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    var flag = reg_email.test(email);
    if (flag){
        //输入正确
        $("#email").css("border","");
    }else{
        //输入错误 设置边框颜色
        $("#email").css("border","1px solid red");
    }
    return flag;
}