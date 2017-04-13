<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2017-4-13
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>学习Vue.js</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>sweetAlert/sweetalert.css">
</head>
<body>
    <div id="app">
        <runoob></runoob><br>
        <div>
            <input v-model="parentMsg" v-focus>
            <br>
            <child v-bind:message="parentMsg"></child>
        </div><br>
        <ol>
            <todo-item v-for="item in sites" v-bind:todo="item"></todo-item>
        </ol>
    </div>
</body>
<script src="<%=basePath%>sweetAlert/sweetalert.min.js"></script>
<script src="<%=basePath%>js/vue/vue.js"></script>
<script>
    // 注册
    Vue.component('child', {
        // 声明 props
        props: ['message'],
        // 同样也可以在 vm 实例中像 “this.message” 这样使用
        template: '<span>{{ message }}</span>'
    });
    Vue.component('runoob', {
        template: '<h2>嘿嘿嘿</h2>'
    });
    Vue.component('todo-item', {
        props: ['todo'],
        template: '<li>{{ todo.text }}</li>'
    });
    // 注册一个全局自定义指令 v-focus
    /*Vue.directive('focus', {
        // 当绑定元素插入到 DOM 中。
        inserted: function (el) {
            // 聚焦元素
            el.focus()
        }
    });*/
    // 创建根实例
    new Vue({
        el: '#app',
        data: {
            parentMsg: '父组件内容',
            sites: [
                { text: 'Runoob' },
                { text: 'Google' },
                { text: 'Taobao' }
            ]
        },
        directives: {
            // 注册一个局部的自定义指令 v-focus
            focus: {
                // 指令的定义
                inserted: function (el) {
                    // 聚焦元素
                    el.focus()
                }
            }
        }
    });

</script>
</html>
