<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

    <!--引入样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" type="text/css">
    <!--引入js功能文件-->
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js" type="text/javascript"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">

        function exit() {
            $.ajax({
                url:"${pageContext.request.contextPath}/safeExit/exit",
                type:"post",
                success:function () {
                    window.location.href="${pageContext.request.contextPath}/login/login.jsp";
                }
            })
        }

</script>
</head>
<body>
    <%--导航栏开始--%>
<div class="navbar navbar-inverse navbar-fixed-top ">
   <div class="container">

       <div class="navbar-collapse collapse">
           <h3  class="navbar-text text-muted navbar-left ">持名法洲后台管理系统</h3>
           <div class="navbar-right">
               <h3 class="text-muted navbar-text">当前用户：${admin.username}</h3>
               <a  onclick="exit()" class="text-muted navbar-text" style="font-size: 20px ">  安全退出</a>
           </div>
       </div>
   </div>
</div>
<%--导航栏结束--%>

    <hr>
    <hr>  <hr>
<div class="container-fluid">
    <div class="row">
        <%--手风琴开始--%>
        <div class="col-md-2">
            <div class="panel-group" id="acc">
                <%--轮播开始--%>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a href="#tg1" class="panel-title" data-toggle="collapse" data-parent="#acc">轮播管理</a>
                    </div>
                    <div class="panel-collapse collapse" id="tg1">
                        <div class="panel-body text-center">
                            <a href="javascript:$('#contentLayout').load('${pageContext.request.contextPath}/view/banner/banner_show.jsp')"  class="btn btn-default">查看详情</a>
                        </div>
                    </div>
                </div>
                <%--轮播结束--%>

                    <%--专辑管理开始--%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#tg2" class="panel-title" data-toggle="collapse" data-parent="#acc">专辑管理</a>
                        </div>
                        <div class="panel-collapse collapse" id="tg2">
                            <div class="panel-body text-center">
                                <a href="javascript:$('#contentLayout').load('${pageContext.request.contextPath}/view/album/album.jsp')"  class="btn btn-default">查看详情</a>
                            </div>
                        </div>
                    </div>
                    <%--专辑管理结束--%>

                    <%--文章管理开始--%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#tg3" class="panel-title" data-toggle="collapse" data-parent="#acc">文章管理</a>
                        </div>
                        <div class="panel-collapse collapse" id="tg3">
                            <div class="panel-body text-center">
                                <a href="javascript:$('#contentLayout').load('${pageContext.request.contextPath}/view/article/article_show.jsp')"  class="btn btn-default">查看详情</a>
                            </div>
                        </div>
                    </div>
                    <%--文章管理结束--%>

                    <%--用户管理开始--%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#tg4" class="panel-title" data-toggle="collapse" data-parent="#acc">用户管理</a>
                        </div>
                        <div class="panel-collapse collapse" id="tg4">
                            <div class="panel-body text-center">

                                <a href="javascript:$('#contentLayout').load('${pageContext.request.contextPath}/view/user/user_show.jsp')"  class="btn btn-default">查看详情</a>

                            </div>
                        </div>
                    </div>
                    <%--用户管理结束--%>
            </div>
            <a href="javascript:$('#contentLayout').load('${pageContext.request.contextPath}/view/user/enrol_show.jsp')"  class="btn btn-success" >注册时间</a>

            <a href="javascript:$('#contentLayout').load('${pageContext.request.contextPath}/view/user/area_show.jsp')"  class="btn btn-success" >地图分布</a>
        </div>


            <%--手风琴结束--%>
         <div class="col-md-10"  id="contentLayout">
            <%--巨幕开始--%>

                    <div class="jumbotron" style="padding-left: 80px" >
                        <h4>持名法洲后台管理系统!</h4>
                    </div>
                
                    <%--巨幕结束--%>
                <img  width="1100px" src="${pageContext.request.contextPath}/biaotou/shouye.jpg" >

        </div>
      </div>
    <%--<div class="text-center">--%>
        <%--<div class="jumbotron" style="width: 1500px;height: 30px" >--%>
            <%--<h5>持名法洲后台管理系统@百知教育 2019-8-13</h5>--%>
        <%--</div>--%>

    <%--</div>--%>
</div>
    <%--页脚--%>
    <div class="panel panel-footer text-center">
        <h4>持明法洲后台管理系统@百知教育 2019-08-14</h4>
    </div>

</body>
</html>
