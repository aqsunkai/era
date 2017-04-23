<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2017-4-22
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!--兼容最高版本的IE-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--使用移动端时界面是移动端大小-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户列表</title>
    <link rel="stylesheet" href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>bootgrid/jquery.bootgrid.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>sweetAlert/sweetalert2.min.css" />

</head>
<body>
  <div class="container-fluid">
      <div class="row">
          <div class="col-md-1" style="background-color: #000;color: #fff">左侧</div>
          <div class="col-md-10">
              <table id="grid-data" class="table table-condensed table-hover table-striped">
                  <thead>
                  　　　　 <tr>
                      　　　　<th data-column-id="id" data-order="asc" data-type="numeric">序号</th>
                      　　　　<th data-column-id="nickname" data-sortable="false">昵称</th>
                      　　　　<th data-column-id="email">邮箱</th>
                      　　　　<th data-column-id="createTime" data-converter="datetime">创建时间</th>
                      　　　　<th data-column-id="lastLoginTime" data-converter="datetime">最后登录时间</th>
                      　　　　<th data-column-id="status" data-formatter="status">是否有效</th>
                             <th data-column-id="command" data-sortable="false" data-formatter="command">操作</th>
                      　　</tr>
                  　　</thead>
              </table>
          </div>
      </div>
  </div>
</body>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<%--<script type="text/javascript" src="<%=basePath%>bootgrid/jquery.bootgrid.fa.min.js"></script>--%>
<script src="<%=basePath%>bootgrid/jquery.bootgrid.min.js"></script>
<script type="text/javascript" src="<%=basePath%>sweetAlert/sweetalert2.min.js"></script>
<%--FOR IE SUPPORT--%>
<script src="<%=basePath%>sweetAlert/es6-promise.min.js"></script>
<script src="<%=basePath%>js/moment.js"></script>
<script type="text/javascript">
    $("#grid-data").bootgrid({
        ajax:true,
        url:"<%=basePath%>user/queryList",
        selection: true,
        //multiSelect: true,
        rowSelect: true,
        //keepSelection: true,
        //rowCount:2,  //一个页面上最多显示的记录条数
        navigation:3, //navigation:  表的样式，0，1，2有表尾，3有表头和表尾
        post: function (){},
        labels:{
            search:"输入用户名",
            all: "全部",
            //表头左边显示提示信息
            infos: "显示{{ctx.start}}～{{ctx.end}}条， 共{{ctx.total}}条",
            loading: "加载中...",
            noResults: "没有相关数据",
            refresh: "刷新中..."
        },
        converters: {
            datetime: {
                from: function (value) { return moment(value); },
                to: function (value) {
                    if(value == null || value == ''){
                        return '';
                    }
                    return moment(value).format('YYYY-MM-DD HH:mm:ss');
                }
            }
        },
        formatters:{
            "command": function(column, row)
            {
              return "<button type=\"button\" class=\"btn btn-xs btn-primary\" onclick='edit(\"" +row.id+ "\")'><span class='glyphicon glyphicon-pencil' title='修改'></span></button> " +
                    "<button type=\"button\" class=\"btn btn-xs btn-danger\" onclick='del(\"" +row.id+ "\")'><span class='glyphicon glyphicon-trash' title='删除'></span></button>";
            },
            "status":function(column,row){
                if(row.status==0){
                    return "有效";
                }else{
                    return "无效";
                }
            }
        }
    },"json");
function edit(id){
    swal({
        title: '温馨提示',
        text: "您正在修改"+id,
        confirmButtonText: '确认',
        confirmButtonColor: 'Green'
    })
}
function del(id){
    swal({
        text: "信息已删除成功！"+id,
        type: "success",
        confirmButtonText: '确认',
        confirmButtonColor: '#4cd964'
    })
}
</script>
</html>
