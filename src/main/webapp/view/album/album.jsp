<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="album-tab"></table>
<div id="album-pager" style="height: 30px"></div>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
<script type="text/javascript">

    $("#album-tab").jqGrid({
        url: '${pageContext.request.contextPath}/album/byPage',
        editurl: '${pageContext.request.contextPath}/album/edit',
        datatype: "json",
        colNames: ['标题', '封面', '集数', '简介', '创建时间'],
        colModel: [
            {name: 'title', align: 'center', editable: true},
            {
                name: 'cover', align: 'center', editable: true,
                //    文件上传
                index: 'fileToUpload',
                edittype: 'file',
                width: 150, align: "left",
                editable: true,
                formatter: function (value, optiobs, row) {
                    return '<img style="height: 80px;"  src="${pageContext.request.contextPath}/view/album/img/' + row.cover + '">';
                }
            },
            {name: 'count', align: 'center', editable: true},
            {name: 'detail', align: 'center', editable: true},
            {
                name: 'createDate',
                align: 'center',
                //   指定日期格式
                index: 'createDate',
                formatter: "date",
                formatoptions: {newformat: 'Y-m-d'},
                sortable: true,
                width: 120
            }
        ],
        styleUI: 'Bootstrap',
        autowidth: true,
        height: '300px',
        rowNum: 3,
        rowList: [3, 8, 10, 20, 30],
        pager: '#album-pager',
        viewrecords: true,
        subGrid: true,
        caption: "专辑详细信息",
        subGridRowExpanded: function (subgrid_id, id) {  //1. 子表格的id   2
            var subgrid_table_id = subgrid_id + "_t";  //子表格的table id
            var pager_id = "p_" + subgrid_table_id;    //子表格的page id
            $("#" + subgrid_id).html(
                "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                "<div id='" + pager_id + "' class='scroll'></div>");
            $("#" + subgrid_table_id).jqGrid(
                {
                    url: "${pageContext.request.contextPath}/chapter/byPage?id=" + id,
                    editurl: '${pageContext.request.contextPath}/chapter/edit?aid=' + id,
                    datatype: "json",
                    colNames: ['编号', '名称', '大小', '时长', '创建日期', '在线试听'],
                    colModel: [
                        {name: "id", index: "num", width: 60},
                        {name: "title", index: "item", width: 50, editable: true},
                        {name: "size", index: "qty", width: 30, align: "right"},
                        {name: "duration", index: "duration", width: 30, align: "right"},
                        {
                            name: 'createDate',
                            align: 'center',
                            //   指定日期格式
                            index: 'createDate',
                            formatter: "date",
                            formatoptions: {newformat: 'Y-m-d'},
                            sortable: true,
                            width: 120
                        },
                        {
                            name: "audio",
                            index: "qty",
                            width: 50,
                            align: "right",
                            edittype: 'file',
                            width: 150, align: "left",
                            editable: true,

                            //音频展示
                            formatter: function (value, option, rows) {
                                return "<audio controls loop>\n" +
                                    "  <source src=\"${pageContext.request.contextPath}/view/album/MP3/" + value + "\" type=\"audio/mpeg\">\n" +
                                    "</audio>";
                            }
                        },

                    ],
                    rowNum: 20,
                    pager: pager_id,
                    height: '300px',
                    styleUI: 'Bootstrap',
                    autowidth: true,
                    rowList: [3, 8, 10, 20, 30],
                    //  第一个参数工具栏   第二个参数 工具栏DIV的ID 第三个展示工具栏的的工具 第四个修改  第五个添加 第六个删除
                }).jqGrid('navGrid', "#" + pager_id,{add:true,edit:true,del:true},
                {

                    // 章节修改操作
                    closeAfterEdit: true,  //关闭模态框
                    //上传文件
                    afterSubmit: function (response) {
                        var id = response.responseJSON.data;
                        if (id != null) {
                            $.ajaxFileUpload
                            ({
                                url: '${pageContext.request.contextPath}/chapter/upload',
                                //  type:'post',
                                secureuri: false,
                                fileElementId: 'audio',
                                dataType: 'JSON',
                                data: {id: id},
                                success: function () {
                                    //上传文件后刷新表格
                                    $("#album-tab").trigger("reloadGrid");
                                    $("#" + subgrid_table_id).trigger("reloadGrid");
                                }
                            })
                            return "1234";
                        }
                    }

                },{
                    //添加
                    closeAfterAdd: true,//关闭模态框
                    afterSubmit: function (response) {
                        var id = response.responseJSON.data;
                        if (id != null) {
                            $.ajaxFileUpload
                            ({
                                url: '${pageContext.request.contextPath}/chapter/upload',
                                //  type:'post',
                                secureuri: false,
                                fileElementId: 'audio',
                                dataType: 'JSON',
                                data: {id: id},
                                success: function () {
                                    //上传文件后刷新表格
                                    $("#album-tab").trigger("reloadGrid");
                                    $("#" + subgrid_table_id).trigger("reloadGrid");
                                }
                            })
                            return "12345";
                        }
                    }
                },{
                    closeAfterAdd: true,
                    afterSubmit: function (response) {
                        //删除后章节后更新专辑表
                        $("#album-tab").trigger("reloadGrid");
                        return "111";
                    }
                }
            );
        }
    }).jqGrid('navGrid', '#album-pager', {edit: true, add: true, del: true},
        {
            // 专辑修改
            closeAfterEdit: true,   <%--// 关闭模态框--%>
            //上传文件
            afterSubmit: function (response) {
                var id = response.responseJSON.data;

                if (id != null) {
                    $.ajaxFileUpload
                    ({
                        url: '${pageContext.request.contextPath}/album/upload',
                        //  type:'post',
                        secureuri: false,
                        fileElementId: 'cover',
                        dataType: 'JSON',
                        data: {id: id},
                        success: function () {
                            //上传文件后刷新表格
                            $("#album-tab").trigger("reloadGrid");
                        }
                    })
                    return "false";
                }
            }
        }, {
           //专辑添加
            closeAfterAdd: true,  <%--// 关闭模态框--%>
            //上传文件
            afterSubmit: function (response) {
                var id = response.responseJSON.data;

                if (id != null) {
                    $.ajaxFileUpload
                    ({
                        url: '${pageContext.request.contextPath}/album/upload',
                        //  type:'post',
                        secureuri: false,
                        fileElementId: 'cover',
                        dataType: 'JSON',
                        data: {id: id},
                        success: function () {
                            //上传文件后刷新表格
                            $("#album-tab").trigger("reloadGrid");
                        }
                    })
                    return "false";
                }
            }
        }, {
            //删除后执行的程序
            recreateForm: true,
            afterSubmit: function (response) {
                var msg = response.responseJSON.msg;
                // console.log(response);
                alert(msg)
                return "111";
            }

        });

    $("#excel").click(function () {
        window.location.href="${pageContext.request.contextPath}/album/export";

        <%--alert("jinru！")--%>
        <%--ajax({--%>
            <%--url:'${pageContext.request.contextPath}/album/export',--%>
            <%--type:'post',--%>
            <%--dataType:"json",--%>
            <%--success:function (xhr) {--%>
                <%--if(xhr=="ok"){--%>
                    <%--alert("下载成功！")--%>
                <%--}--%>
            <%--}--%>
        <%--})--%>
    })


</script>
<a class="btn btn-success" id="excel">导出Excel表格</a>