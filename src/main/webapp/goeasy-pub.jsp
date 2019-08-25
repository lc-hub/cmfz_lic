<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" type="text/css">
<!--引入js功能文件-->
<script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>   <script>
       $(function(){
           var goEasy = new GoEasy({
               appkey:"BC-e61b09f5c5dd45648d84b4d694e8f51a"
           })
           goEasy.publish({
               channel:"test",
               message:"Hello word",
           })
                })

    </script>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
