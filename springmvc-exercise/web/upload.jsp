<%--
  Created by IntelliJ IDEA.
  User: yanglh
  Date: 2021/3/15
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.util.*"  isELIgnored="false" %>
<form action="uploadImage" method="post" enctype="multipart/form-data">
    选择图片:<input type="file" name="image" accept="image/*"/><br>
    <input type="submit" value="上传">
</form>
