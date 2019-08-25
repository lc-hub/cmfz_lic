<%@ page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<body>
        <textarea id="editor_id" name="content" >
                 &lt;strong&gt;HTML内容&lt;/strong&gt;
        </textarea>
</body>

<head>
    <title>kindeditor</title>
    <script charset="utf-8" src="kindeditor/kindeditor-all-min.js"></script>
    <%--设置为中国工具栏提示信息--%>
    <script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>

    <script>
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',{

                width:'1100',//宽度
                height:'500',//高度
                minWidth:"500",//最小宽度
                minHeight:'500',//最小高度
             //   resizeType:2,
                //themeType:'simple',
                fullscreenMode:'true',//全屏
                allowFileManager:'true',//显示图片空间按钮
                // 图片空间按钮的URL
                fileManagerJson:'${pageContext.request.contextPath}/article/browser',
                // 上传文件的url
                uploadJson:'${pageContext.request.contextPath}/article/upload',
                //  指定上传文件的名称
                filePostName:'imageFile',

            });
        });
    </script>
</head>
</html>
