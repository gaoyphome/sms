var Terminal = {};

Terminal.params = function () {
    var queryData = {};
    queryData['graphDataType'] = $("#graphDataType").val();
    queryData['graphLastday'] = $("#graphLastday").val();
    queryData['graphStartTermTransDate'] = $("#graphStartTermTransDate").val();
    queryData['graphEndTermTransDate'] = $("#graphEndTermTransDate").val();
    return queryData;
};

Terminal.submitParams = function (params) {
    var ajax = new $ax(Feng.ctxPath + "/terminalanalysis/graph", function (data) {
        var graphDataType = $("#graphDataType").val();
        Terminal.showGraph(graphDataType, data);
        Feng.success("数据加载成功，刷新");
    },function (data) {
        Feng.error("数据加载失败!" + data.responseJSON.message + "!");
    });
    ajax.setData(params);
    ajax.start();
};

Terminal.showGraph = function (graphDataType, data) {
    switch (graphDataType) {
        case '1': {//新增线路数
            Terminal.Graph1(data);
            break;
        }
        case '2': { //新增车辆数
            Terminal.Graph2(data);
            break;
        }
        case '3': { //新增终端数
            Terminal.Graph3(data);
            break;
        }
        case '4': { //总线路数
            Terminal.Graph4(data);
            break;
        }
        case '5': {//总车辆数
            Terminal.Graph5(data);
            break;
        }
        case '6': {//总终端数
            Terminal.Graph6(data);
            break;
        }
        default: {
            alert("统计类型不支持！");
        }
    }
}

    /**
     * 线路每日新增总数
     * @param data
     * @constructor
     */
    Terminal.Graph1 = function (data) {
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
                text: '线路新增数'
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
                name: '线路每日新增数',
                data: series_num
            }]
        });
    }

/**
 * 车辆每日新增总数
 * @param data
 * @constructor
 */
Terminal.Graph2 = function (data) {
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
            text: '车辆新增数'
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
            name: '车辆每日新增数',
            data: series_num
        }]
    });
}

/**
 * POS每日新增总数
 * @param data
 * @constructor
 */
Terminal.Graph3 = function (data) {
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
            text: 'POS新增数'
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
            name: 'POS每日新增数',
            data: series_num
        }]
    });
}

/**
 * 线路总数
 * @param data
 * @constructor
 */
Terminal.Graph4 = function (data) {
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
            text: '线路总数'
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
            name: '线路总数',
            data: series_num
        }]
    });
}

/**
 * 车辆总数
 * @param data
 * @constructor
 */
Terminal.Graph5 = function (data) {
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
            text: '车辆总数'
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
            name: '车辆总数',
            data: series_num
        }]
    });
}

/**
 * POS总数
 * @param data
 * @constructor
 */
Terminal.Graph6 = function (data) {
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
            text: 'POS总数'
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
            name: 'POS总数',
            data: series_num
        }]
    });
}

Terminal.loaddata = function () {
    var params = Terminal.params();
    Terminal.submitParams(params);
};

Terminal.initForm = function () {
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
    Terminal.initForm();
    Terminal.loaddata();
});
