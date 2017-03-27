<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文件上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script src="<%=basePath%>resources/js/jquery-2.1.4/jquery.min.js"></script>
    <script src="<%=basePath%>resources/js/uploadify/jquery.uploadify.min.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=basePath%>resources/js/uploadify/uploadify.css">
    <style>
	<!--
	.hhhh{
	 margin:50px auto;
	 padding-left : 450px;
	 }
	.uuid{
	 color: red;
	 }
	-->
</style>
  </head>
  
  <body>
   <center>
	   <input type="file" name="uploadify" id="uploadFile" />
	   <div id="some_file_queue"></div>
	   <div  id="fileName"></div>
	   <div style="clear: both;margin-top: 20px;cursor: pointer;">
			<a
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary a-btn"
				onclick="javascript:$('#uploadFile').uploadify('upload','*')">
				<span class="glyphicon glyphicon-play"></span> <span
				class="ui-button-text">开始上传</span>
			</a> <a
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary a-btn"
				onclick="javascript:$('#uploadFile').uploadify('cancel','*')">
				<span class="glyphicon glyphicon-ban-circle"></span> <span
				class="ui-button-text">取消上传</span>
			</a>
		</div>
		<!-- <a href="javascript:changeBtnText();">变换按钮</a> -->
	</center>
<script type="text/javascript">
  var flag = true;
  $(function() {
       $("#uploadFile").uploadify({
    	   'swf'           : '<%=basePath%>resources/js/uploadify/uploadify.swf',
           'uploader'      : '<%=basePath%>file/uploadFile',  //服务器端方法
           //'formData'      : {'someKey' : 'someValue', 'someOtherKey' : 1},//传输数据json格式
    	   'height'        : 30,  //按钮高度
           'width'         : 100,  //按钮宽度  
           'fileObjName'   : 'uploadify',//默认 Filedata, $_FILES控件name名称
           'multi'       : true,  //设置是否允许一次选择多个文件，true为允许，false不允许
           'auto'        : false,  //是否自动上传
           'buttonText'  : '选择文件',//按钮显示文字
           //'buttonClass'   : 'uuid', //按钮辅助class
           'buttonCursor': 'hand', //设置鼠标移到按钮上的开状，接受两个值'hand'和'arrow'(手形和箭头)
           'debug'       : false, //开启或关闭debug模式
           //'buttonImage' :  按钮图片样式
           'cancelImg'   : '<%=basePath%>resources/js/img/uploadify-cancel.png', //这个没测试出来，默认是放在与uploadify同级的img文件夹下
       	   //文件后缀限制 默认：'*.*'
           'fileTypeExts':'*.jpg;*.jpge;*.gif;*.psd;*.png;*.txt;*.doc;*.docx;*.ppt;*.pptx;*.pdf', 
           'fileSizeLimit':'10MB',//接受一个单位（B,KB,MB,GB）。如果是数字则默认单位为KB。设置为0时表示不限制
           'fileTypeDesc' : 'All Files',//可选文件的描述。这个值出现在文件浏览窗口中的文件类型下拉选项中。（chrome下不支持，会显示为'自定义文件',ie and firefox下可显示描述）
           'method'       :'post', //提交上传文件的方法，接受post或get两个值，默认为post
           'progressData' : 'percentage',//设置文件上传时显示数据，有‘percentage’ or ‘speed’两个参数(百分比和速度)
           'queueID'     : 'some_file_queue',//设置上传队列DOM元素的ID，上传的项目会增加进这个ID的DOM中。设置为false时则会自动生成队列DOM和ID。默认为false
           'queueSizeLimit' : 5,//一个队列上传文件数限制
           'simUploadLimit' : 5, //一次同步上传的文件数目
           'removeCompleted' : true, //完成时是否清除队列 默认true  
           'removeTimeout'   : 1,   //完成时清除队列显示秒数,默认3秒  
           'requeueErrors'   : false, //设置上传过程中因为出错导致上传失败的文件是否重新加入队列中上传
           'successTimeout'  : 30,   //设置文件上传后等待服务器响应的秒数，超出这个时间，将会被认为上传成功，默认为30秒
           'uploadLimit'     : 99,  //允许上传的最多张数 
           'onUploadSuccess' : function(file, data, response) { //上传成功
           	   var jdata = $.parseJSON(data);
           	   $("#fileName").append("<p><em name='fileName' onclick = 'downLoadFile(this)' style='color:#555555'>"+jdata.fileName+"</em><em style='color:red' onclick = 'deleteFile(this)'>删除</em><a name='filePath' style='display:none'>"+jdata.filePath+"</a></p>");
	           	console.log( 'id: ' + file.id
	                    + ' - 索引: ' + file.index
	　　　　　　　　　+ ' - 文件名: ' + file.name
	　　　　　　　　　+ ' - 文件大小: ' + file.size
	　　　　　　　　　+ ' - 类型: ' + file.type
	　　　　　　　　　+ ' - 创建日期: ' + file.creationdate
	　　　　　　　　　+ ' - 修改日期: ' + file.modificationdate
	　　　　　　　　　+ ' - 文件状态: ' + file.filestatus
	　　　　　　　　　+ ' - 服务器端消息: ' + data
	　　　　　　　　　+ ' - 是否上传成功: ' + response);
            },
            'onFallback':function(){
               alert("您未安装FLASH控件，无法一次性上传多个文件！请安装FLASH控件后再试。");
            },
            onSelectError:function(file, errorCode, errorMsg){ //选择失败
                   switch(errorCode) {
                       case -100:  
                    	   alert("上传的文件数量已经超出系统限制的"+$('#uploadFile').uploadify('settings','queueSizeLimit')+"个文件！"); 	
                           break;  
                       case -110:  
                       	alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#uploadFile').uploadify('settings','fileSizeLimit')+"大小！");
                           break;  
                       case -120:  
                    	   alert("文件 ["+file.name+"] 大小异常！");
                           break;  
                       case -130:  
                    	   alert("文件 ["+file.name+"] 类型不正确！");
                           break;
                   }
               },
              /* //上传汇总
             'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {  
                 $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');  
              },
             'onUploadComplete' : function(file) { //上传完成  
                  console.log('The file ' + file.name + ' finished processing.');  
              },  
              //修改formData数据 ,每个文件即将上传前触发
              'onUploadStart' : function(file) {
                  $("#uploadFile").uploadify("settings", "someOtherKey", 2);
              },
              //删除时触发  
              'onCancel' : function(file) {
                  alert('The file ' + file.name + '--' + file.size + ' was cancelled.');  
              },
              //清除队列  
              'onClearQueue' : function(queueItemCount) {
                  alert(queueItemCount + ' file(s) were removed from the queue');  
              },
              //调用destroy是触发  
              'onDestroy' : function() {  
                  alert('我被销毁了');  
              },  
              //每次初始化一个队列是触发  
              'onInit' : function(instance){  
                  alert('The queue ID is ' + instance.settings.queueID);  
              }, 
              //上传错误  
              'onUploadError' : function(file, errorCode, errorMsg, errorString) {
                  alert('The file ' + file.name + ' could not be uploaded: ' + errorString);  
              }, */
       });
   });
  //变换按钮 
  function changeBtnText() {
	  if(flag){
		  $('#uploadFile').uploadify('settings','buttonText','继续上传');
		  flag=false;
	  }else{
		  $('#uploadFile').uploadify('settings','buttonText','选择文件');
		  flag=true;
	  }
      
  }
function deleteFile(i){
	$(i).parent().remove();
}
function downLoadFile(i){
	location.href="<%=basePath%>file/downLoadFile?fileName="+$(i).html()+"&filePath="+$(i).next().next().html();
}
</script>  
  </body>
</html>
