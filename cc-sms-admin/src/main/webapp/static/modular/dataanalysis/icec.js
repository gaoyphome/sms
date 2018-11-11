var Icec = {};
Icec.params = function () {
    var queryData = {};
    queryData['graphDataType'] = $("#graphDataType").val();
    queryData['graphLastday'] = $("#graphLastday").val();
    queryData['graphStartTermTransDate'] = $("#graphStartTermTransDate").val();
    queryData['graphEndTermTransDate'] = $("#graphEndTermTransDate").val();
    return queryData;
};

Icec.submitParams = function (params) {
    var ajax = new $ax(Feng.ctxPath + "/icec/graph", function (data) {
        var graphDataType = $("#graphDataType").val();
        Icec.showGraph(graphDataType, data);
        Feng.success("数据加载成功，刷新");
    },function (data) {
        Feng.error("数据加载失败!" + data.responseJSON.message + "!");
    });
    ajax.setData(params);
    ajax.start();
};

Icec.showGraph = function (graphDataType, data) {
    switch (graphDataType){

        case '6':{
            Icec.Graph6(data);
            break;
        }
        case '7':{ //电子卡每日刷卡数
            Icec.Graph7(data);
            break;
        }
        case '8':{ //IC卡每日新增刷卡笔数
            Icec.Graph8(data);
            break;
        }
        case '9':{ //IC卡和电子卡每日新增刷卡笔数
            Icec.Graph9(data);
            break;
        }
        case '14':{
            Icec.Graph14(data);
            break;
        }
        case '15':{//电子卡刷卡总数
            Icec.Graph15(data);
            break;
        }
        case '16':{//IC卡总刷卡数
            Icec.Graph16(data);
            break;
        }
        case '17':{//IC卡总刷卡数
            Icec.Graph17(data);
            break;
        }
        default:{
            alert("统计类型不支持！");
        }
    }
}

/**
 * 乘车码每日新增总数
 * @param data
 * @constructor
 */
Icec.Graph6 = function (data) {
    var category_time = new Array();
    var series_num = new Array();
    $.each(data, function(index, obj){
        category_time[index] = obj.createtime;
        series_num[index] = obj.dayadd;
    });
    var chart = Highcharts.chart('graphcontainer', {
        chart: {
            type: 'line'
        },
        title: {
            text: '乘车码新增数'
        },
        xAxis: {
        //    categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            categories: category_time
},
        yAxis: {
            title: {
                text: '单位（个）'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [{
            name: '乘车码每日新增数',
            data: series_num
        }]
    });
}

/**
 * 电子卡每日新增刷卡笔数
 * @param data
 * @constructor
 */
Icec.Graph7 = function (data) {
    var category_time = new Array();
    var series_num = new Array();
    $.each(data, function(index, obj){
        category_time[index] = obj.createtime;
        series_num[index] = obj.dayadd;
    });
    var chart = Highcharts.chart('graphcontainer', {
        chart: {
            type: 'line'
        },
        title: {
            text: '电子卡每日新增刷卡笔数'
        },
        xAxis: {
            //    categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            categories: category_time
        },
        yAxis: {
            title: {
                text: '单位（个）'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [{
            name: '电子卡每日新增刷卡笔数',
            data: series_num
        }]
    });
}
/**
 * IC卡每日新增刷卡笔数
 * @param data
 * @constructor
 */
Icec.Graph8 = function (data) {
    var category_time = new Array();
    var series_num = new Array();
    $.each(data, function(index, obj){
        category_time[index] = obj.createtime;
        series_num[index] = obj.dayadd;
    });
    var chart = Highcharts.chart('graphcontainer', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'IC卡每日新增刷卡笔数'
        },
        xAxis: {
            //    categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            categories: category_time
        },
        yAxis: {
            title: {
                text: '单位（个）'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [{
            name: 'IC卡每日新增刷卡笔数',
            data: series_num
        }]
    });
}

/**
 * IC卡和电子卡每日新增刷卡笔数
 * @param data
 * @constructor
 */
Icec.Graph9 = function (data) {
    var category_time = new Array();
    var series_num = new Array();
    $.each(data, function(index, obj){
        category_time[index] = obj.createtime;
        series_num[index] = obj.dayadd;
    });
    var chart = Highcharts.chart('graphcontainer', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'IC卡和电子卡每日新增刷卡笔数'
        },
        xAxis: {
            categories: category_time
        },
        yAxis: {
            title: {
                text: '单位（个）'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [{
            name: 'IC卡和电子卡每日新增刷卡笔数',
            data: series_num
        }]
    });
}


/**
 * 乘车码总获取数
 * @param data
 * @constructor
 */
Icec.Graph14 = function (data) {
    var category_time = new Array();
    var series_num = new Array();
    $.each(data, function(index, obj){
        category_time[index] = obj.createtime;
        series_num[index] = obj.total;
    });
    var chart = Highcharts.chart('graphcontainer', {
        chart: {
            type: 'line'
        },
        title: {
            text: '乘车码总获取数'
        },
        xAxis: {
            //    categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            categories: category_time
        },
        yAxis: {
            title: {
                text: '单位（个）'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [{
            name: '乘车码总获取数',
            data: series_num
        }]
    });
}
/**
 * 电子卡总刷卡数
 * @param data
 * @constructor
 */
Icec.Graph15 = function (data) {
    var category_time = new Array();
    var series_num = new Array();
    $.each(data, function(index, obj){
        category_time[index] = obj.createtime;
        series_num[index] = obj.total;
    });
    var chart = Highcharts.chart('graphcontainer', {
        chart: {
            type: 'line'
        },
        title: {
            text: '电子卡总刷卡数'
        },
        xAxis: {
            //    categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            categories: category_time
        },
        yAxis: {
            title: {
                text: '单位（个）'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [{
            name: '电子卡总刷卡数',
            data: series_num
        }]
    });
}

/**
 * IC卡总刷卡数
 * @param data
 * @constructor
 */
Icec.Graph16 = function (data) {
    var category_time = new Array();
    var series_num = new Array();
    $.each(data, function(index, obj){
        category_time[index] = obj.createtime;
        series_num[index] = obj.total;
    });
    var chart = Highcharts.chart('graphcontainer', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'IC卡总刷卡数'
        },
        xAxis: {
            //    categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            categories: category_time
        },
        yAxis: {
            title: {
                text: '单位（个）'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [{
            name: 'IC卡总刷卡数',
            data: series_num
        }]
    });
}

/**
 * IC卡和电子卡总刷卡数
 * @param data
 * @constructor
 */
Icec.Graph17 = function (data) {
    var category_time = new Array();
    var series_num = new Array();
    $.each(data, function(index, obj){
        category_time[index] = obj.createtime;
        series_num[index] = obj.total;
    });
    var chart = Highcharts.chart('graphcontainer', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'IC卡和电子卡总刷卡数'
        },
        xAxis: {
            //    categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            categories: category_time
        },
        yAxis: {
            title: {
                text: '单位（个）'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true          // 开启数据标签
                },
                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
            }
        },
        series: [{
            name: 'IC卡和电子卡总刷卡数',
            data: series_num
        }]
    });
}

Icec.loaddata = function () {
    var params = Icec.params();
    Icec.submitParams(params);
};

Icec.initForm = function () {
    var days = 0;
    $("#graphLastday").change(function () {
        var key = $(this).children('option:selected').val();
        switch (parseInt(key)){
            case 1:
                days = 7;
                break;
            case 2:
                days = 15;
                break;
            case 3:
                days = 30;
                break;
            case 4:
                days = 90;
                break;
        }
        var date_now = new Date();
        var date_bef = beforeDate(date_now, -days);
        $("#graphStartTermTransDate").val(date_bef.Format("yyyy-MM-dd"));
        $("#graphEndTermTransDate").val(date_now.Format("yyyy-MM-dd"));
    });

    var date_now = new Date();
    var date_bef = beforeDate(date_now, -7);
    $("#graphStartTermTransDate").val(date_bef.Format("yyyy-MM-dd"));
    $("#graphEndTermTransDate").val(date_now.Format("yyyy-MM-dd"));
}
$(function () {
    Icec.initForm();
    Icec.loaddata();
});

