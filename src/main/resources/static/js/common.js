//展示loading
function g_showLoading(){
	var idx = layer.msg('处理中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '0px', time:100000}) ;  
	return idx;
}
//salt
var g_passsword_salt="1a2b3c4d";


// 获取url参数
function g_getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return unescape(r[2]);
    return null;
};
//设定时间格式化函数，使用new Date().format("yyyyMMddhhmmss");
Date.prototype.format = function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var i in args) {
        var n = args[i];
        if (new RegExp("(" + i + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
    }
    return format;
};


//取时间差方法
var timGap = function(date3){

    var days = Math.floor(date3/(24*3600*1000)); //取天数


    var level1 = date3%(24*3600*1000);//取天数后剩下的毫秒数
    var hours = Math.floor(level1/(3600*1000)); //取小时数

    var level2 = level1%(3600*1000);//取小时后剩下的毫秒数
    var minutes = Math.floor(level2/(60*1000));//取分钟

    var level3 = level2%(60*1000);//取分钟后剩下的毫秒数
    var seconds = Math.floor(level3/1000);//取秒

    //定义对象
    var tim = {};
    //赋值
    tim['d'] = days;
    tim['h'] = hours;
    tim['m'] = minutes;
    tim['s'] = seconds;
    //console.log(tim);
    return tim; //返回数据
}


