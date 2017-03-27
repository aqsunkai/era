<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
  
  function download(){
	  location.href="<%=basePath%>user/alipayforward3/"+$("#contNo3").val();
  }
  function goA(){
	  location.href="<%=basePath%>user/goA";
  }
  function goB(){
	  location.href="<%=basePath%>user/goB";
  }
  function testHttp(){
	  location.href="<%=basePath%>http/testHttp";
  }
    function go(){
    	var param = {};
    	param.userName=$("#userName").val();
    	param.password=$("#password").val();
    	param.age=$("#age").val();
    	$.ajax({
    		url:"<%=basePath%>user/insertSelective",
    		data:param,
    		dataType:"json",
    		type:"post",
    		success:function(data){
    			if(data==1){
    				alert("添加成功");
        			window.location.href="<%=basePath%>/eagle/showPictures";
    			}
    		}
    	});
    }
    function deleteById(){
    	$.post("<%=basePath%>user/deleteByPrimaryKey",{id:$("#id").val()},function(data){
    		if(data==1){
				alert("删除成功");
    			window.location.href="<%=basePath%>/eagle/showPictures";
			}
    	},"json");
    }
    function find(){
    	var param = {};
    	param.contNo="8688800002048238";
    	$.ajax({
    		url:"<%=basePath%>user/showElecContDownloadAddress",
    		data:param,
    		type:"post",
    		success:function(data){
    			alert(data);
    			alert(1);
    		},
    		error:function(data){
    			alert(data);
    			alert(2);
    		}
    	});
    }
    
    function sendMessage(name){
    	var param={};
    	param.message=$("#message").val();
    	param.name=name;
    	console.log(param);
    	$.ajax({
    		url:"<%=basePath%>user/sendMessage",
    		data:param,
    		dataType:"json",
    		type:"post",
    		success:function(data){
    			if(data == 1){
    				alert('发送成功');
    			}else{
    				alert(data);
    			}
    		},
    		error:function(data){
    			alert('发送失败');
    		}
    	});
    	
    }
    
  </script>
    <base href="<%=basePath%>">
    
    <title>用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script src="<%=basePath%>js/jquery-2.1.4/jquery.min.js"></script>
  </head>
  
  <body>
  <form action="<%=basePath%>user/insertSelective">
     <a>编号</a> <input id="id" type="text" value="${user.id}" placeholder="请输入" name="id"/><br/><br/>
     <a>姓名</a> <input id="userName" type="text" value="${user.userName}" placeholder="请输入" name="userName"/><br/><br/>
     <a>密码</a> <input id="password" type="text" value="${user.password}" placeholder="请输入" name="password"/><br/><br/>
     <a>年龄</a> <input id="age" type="text" value="${user.age}" placeholder="请输入" name="age"/><br/><br/>
     <input type="button" onclick="go()" value="提交"/>
     <input type="button" onclick="deleteById()" value="删除"/>
  </form>
  <br>
   <form action='<%=basePath%>user/alipayforward1' method="post">
      <input type="text" id="contNo1" name= "contNo1" value="8688800002026528">
      <input type="submit" value="电子保单下载1">
   </form>
   <br>
   <form action='<%=basePath%>user/alipayforward2' method="post">
      <input type="text" id="contNo2" name= "contNo2" value="8688800002026528">
      <input type="submit" value="电子保单下载2">
   </form>
   <br>
   <input type="text" id="contNo3" name= "contNo3" value="8688800002026528">
   <input type="button" onclick="download()" value="电子保单下载">
   <br>
   <input type="button" onclick="goA()" value="A渠道跳转">
   <br>
   <input type="button" onclick="goB()" value="B渠道跳转">
   <br>
   <input type="button" onclick="find()" value="获得url">
   <br>
   <input type="button" onclick="testHttp()" value="测试http">
   
   <form action='<%=basePath%>user/readExcel' enctype="multipart/form-data" method="post">
      <input type="file" name= "excelFile">
      <input type="submit" value="上传">
   </form>
   <textarea rows="3" cols="4" id="message" style="margin: 0px; width: 289px; height: 65px;"></textarea>
   <input type="button" onclick="sendMessage('queue')" value="发送Queue消息"/>
   <input type="button" onclick="sendMessage('topic')" value="发送Topic消息"/>
  </body>
  
</html>
