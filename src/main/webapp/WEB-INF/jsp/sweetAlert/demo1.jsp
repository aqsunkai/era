<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2017-4-13
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>SweetAlert2</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!--标准mui.css-->
    <link rel="stylesheet" href="<%=basePath%>sweetAlert/mui.min.css">
    <link rel="stylesheet" href="<%=basePath%>sweetAlert/sweetalert2.min.css" />
    <script type="text/javascript" src="<%=basePath%>sweetAlert/sweetalert2.min.js"></script>
    <%--FOR IE SUPPORT--%>
    <script src="<%=basePath%>sweetAlert/es6-promise.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-2.1.4/jquery.min.js"></script>
    <style>
        .mui-content-padded {
            margin: 10px;
        }
    </style>
</head>
<body>
<div class="mui-content">
    <div class="mui-content-padded">
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="base">基本信息框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="success">成功提示框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="error">错误提示框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="warning">警告提示框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="info">消息提示框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="question">疑问提示框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="autoclose">自动关闭对话框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="design">自定义标签和按钮样式</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="function">按钮带回调事件</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="image">自定义图片</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="background">自定义背景弹出框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="ajax">AJAX异步回调对话框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="input">文本输入对话框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="textarea">多行输入对话框</button>
        <button type="button" class="mui-btn mui-btn-primary mui-btn-block mui-btn-success" id="select">下拉选择对话框</button>
    </div>
</div>
</body>
<script src="<%=basePath%>sweetAlert/mui.min.js"></script>
<script>
    mui.init({
        swipeBack: true //启用右滑关闭功能
    });
</script>

</html>
<script type="text/javascript">
    $("#base").on("click", function() {
        swal({
            title: '温馨提示',
            text: "您好这是一个基本的信息提示框",
            confirmButtonText: '确认',
            confirmButtonColor: 'Green',
        })
    });

    $("#success").on("click", function() {
        swal({
            text: "信息已提交成功！",
            type: "success",
            confirmButtonText: '确认',
            confirmButtonColor: '#4cd964',
        })
    });

    $("#error").on("click", function() {
        swal({
            text: "对不起信息删除失败",
            type: "error",
            confirmButtonText: '确认',
            confirmButtonColor: '#f27474',
        })
    });

    $("#warning").on("click", function() {
        swal({
            text: "您好，信息正在提交中",
            type: "warning",
            confirmButtonText: '确认',
            confirmButtonColor: '#f8bb86',
        })
    });

    $("#info").on("click", function() {
        swal({
            text: "您好，信息正在提交中",
            type: "info",
            confirmButtonText: '确认',
            confirmButtonColor: '#3fc3ee',
        })
    });

    $("#question").on("click", function() {
        swal({
            text: "您还没有关注我们？",
            type: "question",
            confirmButtonText: '确认',
            confirmButtonColor: '#c9dae1',
        })
    });

    $("#autoclose").on("click", function() {
        swal({
            title: "自动关闭",
            text: "将在三秒钟自动关闭该对话框？",
            showConfirmButton: false,
            timer: 3000
        })
    });

    $("#design").on("click", function() {
        swal({
            title: '<h2 style="font-weight:bold;color:red">温馨提示</h2>',
            type: 'info',
            html: '<a href="http://www.baidu.com" style="color:green">自定义的html内容</a>',
            showCloseButton: true,
            showCancelButton: true,
            confirmButtonColor: 'gray',
            cancelButtonColor: '#3fc3ee',
            confirmButtonText: ' <i class="mui-icon mui-icon-refresh"></i>取消',
            cancelButtonText: ' <i  class="mui-icon mui-icon-trash"></i>确认'
        })
    });

    $("#function").on("click", function() {
        swal({
            text: "您还没有关注我们，建议先关注?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#f8bb86',
            cancelButtonColor: 'gray',
            cancelButtonText: '取消',
            reverseButtons: true, //控制按钮反转
            confirmButtonText: '立即关注',
        }).then(function(isConfirm) {
            if(!isConfirm) {
                swal({
                    text: "取消了！",
                    type: "error",
                    confirmButtonText: '确认',
                    confirmButtonColor: '#f27474',
                })
            } else {
                swal({
                    text: "已成功关注！",
                    type: "success",
                    confirmButtonText: '确认',
                    confirmButtonColor: '#4cd964',
                })
            }
        })
    });

    $("#image").on("click", function() {
        swal({
            title: '图片',
            text: '这是一个自定义的图片',
            imageUrl: '<%=basePath%>image/IMG_0662.JPG',
            imageWidth: 280,
            imageHeight: 280,
            animation: true, //控制是否有动画
            confirmButtonText: '夏守成真他妈帅',
            confirmButtonColor: '#4cd964',
        })
    });

    $("#background").on("click", function() {
        swal({
            title: '<h3 style="color:white">这是一个自定义的背景弹出框</h3>',
            width: 600,
            padding: 100,
            background: '#fff url(http://img2.3lian.com/2014/f5/172/d/31.jpg)',
            //showConfirmButton: false,
            confirmButtonText: '确定',
            confirmButtonColor: '#4cd964',
        })
    });

    $("#ajax").on("click", function() {
        swal({
            title: '输入您的姓名',
            input: 'text',
            confirmButtonText: '提交',
            confirmButtonColor: '#4cd964',
            showLoaderOnConfirm: true, //加载按钮是否可见
            preConfirm: function() {
                return new Promise(function(resolve) {
                    setTimeout(function() {
                        resolve();
                    }, 2000);
                });
            },
            allowOutsideClick: false //弹框外是否可点
        }).then(function(result) {
            if(result) {
                //实际使用过程中将此处换成ajax代码即可
                swal({
                    type: 'success',
                    title: 'Ajax 请求完成',
                    html: '您的邮箱是: ' + '<strong>' + result + '</strong>',
                    confirmButtonText: '确定',
                    confirmButtonColor: '#4cd964'
                });
            }
        });
    });

    $("#input").on("click", function() {
        swal({
            title: '请输入您的姓名',
            input: 'text',
            confirmButtonText: '确定',
            confirmButtonColor: '#4cd964',
            inputValidator: function(value) {
                return new Promise(function(resolve, reject) {
                    if(value) {
                        resolve();
                    } else {
                        reject('至少要输入一个值哦！');
                    }
                });
            }
        }).then(function(result) {
            if(result) {
                swal({
                    title: '结果通知',
                    text: '您的名字是: <strong>' + result + '</strong>',
                    confirmButtonText: '确定',
                    confirmButtonColor: '#4cd964'
                });
            }
        });
    });

    $("#textarea").on("click", function() {
        swal({
            input: 'textarea',
            confirmButtonText: '确定',
            confirmButtonColor: '#4cd964'
        }).then(function(result) {
            if(result) {
                swal({
                    title: '结果通知',
                    text: '您输入的是: <strong>' + result + '</strong>',
                    confirmButtonText: '确定',
                    confirmButtonColor: '#4cd964'
                });
            }
        });
    });

    $("#select").on("click", function() {
        swal({
            title: '请选择您的姓名',
            input: 'select',
            inputOptions: {
                '夏守成': '夏守成',
                '杨林静': '杨林静',
                'William': 'William'
            },
            inputPlaceholder: '选择你的名字',
            showCancelButton: true,
            confirmButtonText: '确定',
            confirmButtonColor: '#4cd964',
            preConfirm: function(value) {
                return new Promise(function(resolve, reject) {
                    if(value){
                        resolve();
                    }else{
                        swal({
                            text:"请选择",
                            type:"error",
                            confirmButtonText: '确定',
                            confirmButtonColor: '#f27474'
                        });
                    }
                });
            }
        }).then(function(result) {
            if(result) {
                swal({
                    type: 'success',
                    //html: '你选择的是: <strong>' + result[0] + '</strong>',
                    html: '你选择的是: <strong>' + result + '</strong>',
                    confirmButtonText: '确定',
                    confirmButtonColor: '#4cd964'
                });
            }
        });
    });

</script>
