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
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"
            type="text/javascript"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <%--设置为中国工具栏提示信息--%>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript">

        $(function () {
            //    c初始化数据表格
            $('#tab').jqGrid({
                //    整合使用BootStrap样式属性
                styleUI: 'Bootstrap',
                //页面加载之后执行展示所有员工信息
                url: '${pageContext.request.contextPath}/article/byPage',
                editurl: '${pageContext.request.contextPath}/article/edit',
                cellurl: '${pageContext.request.contextPath}/article/edit',// 编辑模式下的表格,提交的路径
                datatype: 'json',
                colNames: ['文章名称', '上师id', '文章', '创建日期', '操作'],
                //    开启表格编辑模式
                //cellEdit:true,
                colModel: [

                    {name: 'title', align: 'center', editable: true},
                    {name: 'guruId', align: 'center', editable: true},

                    {name: 'content', align: 'center', editable: true},
                    {
                        name: 'publishDate',
                        align: ' publishDate'
                        //   指定日期格式
                        ,
                        index: 'operateTime',
                        formatter: "date",
                        formatoptions: {newformat: 'Y-m-d'},
                        sortable: true,
                        width: 120

                    }, {
                        name: 'option', width: '80',
                        formatter: function (value, option, rows) {
                            return "<a class='btn btn-default' onclick=\"openModal('edit','" + rows.id + "')\">修改</a>";
                        }
                    }
                ],
                autowidth: true, //是否适应父容器
                width:'500px',
                pager: '#pager',//加入分页工具栏
                rowList: [2, 3, 4, 5],//指定每页展示的条数
                rowNum: 2, //指定默认展示的条数 必须在上一行存在
                page: 1,//默认展示的页数
                viewrecords: true,// 是否展示总条数
                multiselect: true,//是否展示复选框
                //rownumbers:true,//是否显示行号
                height: 400,// 表格高度


            }).jqGrid('navGrid', '#pager', {
                edit: false,
                add: false,
                search: false,
                del: true
            }, {closeAfterEdit: true,}, {closeAfterAdd: true,});
        })

        //   修改 | 添加触发模态框的函数
        function openModal(oper, id) {
            //给富文本编辑器清空内容
            KindEditor.html("#editor_id","")
            // 获取当前行数据
             var article =$("#tab").jqGrid("getRowData",id);
            // id赋值 选择要修改的id
            $("#article-id").val(id)
            //
            $("#article-oper").val(oper);
            //文章名称 回显
            $("#article-title").val(article.title);
            //作者回显

            //文章内容回显
            KindEditor.html("#editor_id",article.content)
            //展示模态框
            $("#article-modal").modal("show");
        }

        //    设置富文本编辑kindeditor相关属性
        KindEditor.create("#editor_id",{

            width:'100%',//宽度
            height:'200',//高度
            // minWidth:"500",//最小宽度
            // minHeight:'500',//最小高度
            //   resizeType:2,
            //themeType:'simple',
          //  fullscreenMode:'true',//全屏
            allowFileManager:'true',//显示图片空间按钮
            // 图片空间按钮的URL
            fileManagerJson:'${pageContext.request.contextPath}/article/browser',
            // 上传文件的url
            uploadJson:'${pageContext.request.contextPath}/article/upload',
            //  指定上传文件的名称
            filePostName:'imageFile',
        //    失去焦点事件
         afterBlur:function(){
        //      同步
              this.sync();

        }
        })

            //    模态框提交执行的函数
            function saveArticle() {

           var a = $("#article-form").serialize();
            console.log(a);
            $.ajax({
                url:'${pageContext.request.contextPath}/article/edit',
                data:$("#article-form").serialize(),
                type:'post',
                datatype:'JSON',
                success:function () {
                    //    关闭模态框
                       $("#article-modal").modal("hide");
                    //    刷新文章表
                      $("#tab").trigger('reloadGrid');
                }
            })
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">展示所有文章</a></li>
    <li role="presentation"><a onclick="openModal('add')">添加文章</a></li>
</ul>

<%--模态框开始--%>
<div class="modal fade" role="dialog" id="article-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">文章</h4>
            </div>
            <div class="modal-body">

                <form class="form-inline" id="article-form">
                    <input type="hidden" name="oper" id="article-oper">
                    <input type="hidden" name="id" id="article-id">

                    <%--文章名称--%>
                    <div class="form-group">
                        <label for="article-title">文章名称</label>
                        <input type="text" class="form-control"  name="title" id="article-title" placeholder="请输入文章名称...">
                    </div>
                    <%--上师ID--%>
                    <div class="form-group">
                        <label for="guruID">上师id</label>
                        <select id="guruID" name="guruId" >
                            <option>aaa</option>
                            <option>bbb</option>
                            <option>vvv</option>
                        </select>
                    </div>
                    <%--文章内容--%>
                    <div class="form-group">
                       <textarea id="editor_id" name="content" style="width:700px;height:300px;">

                       </textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveArticle()">保存</button>
            </div>
        </div>
    </div>
</div>


<table id="tab" class="table table-striped"></table>
<div id="pager" style="height: 30px;"></div>

</body>
</html>
