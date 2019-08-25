<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

    <!--引入样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css"
          type="text/css">
    <!--引入js功能文件-->
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-3.3.1.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"
            type="text/javascript"></script>
    <script type="text/javascript">

        function  getCityById(selectNum,province){
            alert("获取到的id==="+selectNum);

            alert("城市"+province)


        }



        $(function () {
            //    c初始化数据表格
            $('#tab').jqGrid({
                //    整合使用BootStrap样式属性
                styleUI: 'Bootstrap',
                //页面加载之后执行展示所有员工信息
                url: '${pageContext.request.contextPath}/user/byPage',
                editurl: '${pageContext.request.contextPath}/user/edit',
                datatype: 'json',
                colNames: ['用户名', '头像', '法名', '手机号', '密码', '性别', '所在省', '所在市', '签名', '注册时间', '状态'],
                //    开启表格编辑模式
                //cellEdit: true,
                colModel: [

                    {name: 'username', align: 'center', editable: true},
                    {
                        name: 'photo', align: 'center', editable: true,
                        index: 'photo',
                        edittype: "file",
                        width: 235, align: "left",
                        editable: true,
                        formatter: function (value, optiobs, row) {
                            return '<img style="height: 80px;"  src="${pageContext.request.contextPath}/view/user/photo/' + row.photo + '">';
                        }

                    },
                    {name: 'dharma', align: 'center', editable: true},
                    {name: 'phone', align: 'center', editable: true},
                    {name: 'password', align: 'center', editable: true},
                    {name: 'sex', align: 'center', editable: true},
                    {
                        name: 'province', align: 'center', editable: true,
                        //    设置为下拉列表
                        edittype: 'select',
                        editoptions: {dataUrl: '${pageContext.request.contextPath}/province/findAll',//获取所有省
                            dataEvents:[ //当前控件追加触发事件
                                {
                                    type:'change', //事件下拉
                                    fn:function(e){
                                        var itemName = this.id;
                                        var selectNum = itemName.eatch(/^\d+/);
                                     //   alert("获取到的id==="+selectNum);
                                        var province = this.value;
                                       // alert("城市"+province)
                                        getCityById(selectNum,province);//获取对应省下的城市方法
                                    }
                            }]

                        },
                    },
                    {name: 'city', align: 'center', editable: true,
                        edittype: 'select',
                        editoptions: {dataUrl: '${pageContext.request.contextPath}/city/findAll'},
                    },
                    {name: 'sign', align: 'center', editable: true},
                    {
                        name: 'createDate', align: 'center',

                        formatter: "date",
                        formatoptions: {newformat: 'Y-m-d'},
                        sortable: true,
                        width: 170
                    },
                    {
                        name: 'status', align: 'center', editable: true,
                        edittype: 'select',
                        editoptions: {value: "正常:正常; 冻结:冻结"}
                    },
                ],
                autowidth: true, //是否适应父容器
                pager: '#pager',//加入分页工具栏
                rowList: [2, 3, 4, 5],//指定每页展示的条数
                rowNum: 2, //指定默认展示的条数 必须在上一行存在
                page: 1,//默认展示的页数
                viewrecords: true,// 是否展示总条数
                multiselect: true,//是否展示复选框
                //rownumbers:true,//是否显示行号
                height: 400,// 表格高度


            }).jqGrid('navGrid', '#pager', {edit: true, add: true, del: true},
                {
                    // 控制修改操作
                    closeAfterEdit: true,
                    //上传文件
                    afterSubmit: function (response) {
                        var id = response.responseJSON.data;

                        if (id != null) {
                            $.ajaxFileUpload({
                               url: '${pageContext.request.contextPath}/user/upload',
                                type:'post',
                                secureuri: false,
                                fileElementId: 'photo',
                                dataType: 'json',
                                data: {id: id},
                                success: function () {
                                    //上传文件后刷新表格
                                    $("#tab").trigger("reloadGrid");
                                }
                            })
                            return "false";
                        }
                    }
                }, {
                    <%--// 、 控制添加操作--%>
                    <%--// 关闭模态框--%>
                    closeAfterAdd: true,
                    //上传文件
                    afterSubmit: function (response) {
                        var id = response.responseJSON.data;

                        if (id != null) {
                            $.ajaxFileUpload
                            ({
                                url: '${pageContext.request.contextPath}/user/upload',
                                //  type:'post',
                                secureuri: false,
                                fileElementId: 'photo',
                                dataType: 'text',
                                data: {id: id},
                                success: function () {
                                    //上传文件后刷新表格
                                    $("#tab").trigger("reloadGrid");
                                }
                            })
                            return "false";
                        }
                    }
                }, {});
        })

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="page-header">
        <h3>展示所有轮播图</h3>
        <hr>
        <table id="tab" class="table table-striped"></table>
    </div>
    <div id="pager" style="height: 30px;"></div>
</div>
</body>
</html>
