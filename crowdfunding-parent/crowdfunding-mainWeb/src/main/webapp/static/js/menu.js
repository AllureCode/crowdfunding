/**
 * 对页面展开闭合的js
 */

function showMenu() {
    var pathname = window.location.pathname;
    var $APPPATH = "${APP_PATH}";
    var path = pathname.substring($APPPATH.length);
    var alink = $(".list-group a[href*='" + path + "']");
    alink.css("color", "red");
    alink.parent().parent().parent().removeClass("tree-closed");
    alink.parent().parent().show();

}
