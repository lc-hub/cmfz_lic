<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/22
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" type="text/css">
<!--引入js功能文件-->
<script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
 <script>
     $(function () {
         var goEasy = new GoEasy({
             appkey:"BC-e61b09f5c5dd45648d84b4d694e8f51a"
         })
         goEasy.subscribe({
             channel:"test",
             onMessage:function (message) {
                 alert("Channel:"+message.channel+"content"+message.connect());
             }
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
