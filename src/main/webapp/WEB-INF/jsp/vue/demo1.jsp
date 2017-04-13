<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2017-4-11
  Time: 23:01
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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>sweetAlert/sweetalert2.min.css">
</head>
<style>
    #app-3{
        color: red;
    }
</style>
<body>
    <div id="app">
        {{ message }}<br>
        {{ abc }}
    </div>
    <div id="app-2">
          <span v-bind:title="message">
            鼠标悬停几秒钟查看此处动态绑定的提示信息！
          </span>
    </div>
    <div id="app-3">
        <p v-if="seen">现在你看到我了</p>
    </div>
    <div id="app-4">
        <ol>
            <li v-for="a in todos">
                {{ a.text }}
            </li>
        </ol>
    </div>
    <div id="app-5">
        <p>{{ message }}</p>
        <button v-on:click="reverseMessage">逆转消息</button>
    </div>
    <div id="app-6">
        <p>{{ message }}</p>
        <input v-model="message">
    </div>
    <div id="app-7">
        <ol>
            <!-- 现在我们为每个todo-item提供待办项对象    -->
            <!-- 待办项对象是变量，即其内容可以是动态的 -->
            <todo-item v-for="item in groceryList" v-bind:todo="item"></todo-item>
        </ol>
    </div>
    <div id="app-8">
        <ul class="pagination">
            <li v-for="n in pageCount">
                <a href="javascripit:void(0)" v-bind:class="activeNumber === n + 1 ? 'active' : ''">{{ n + 1 }}</a>
            </li>
        </ul>
    </div>
    <div id="app-9">
        <p><input type="text" v-model="message"></p>
        <p>
            <!--click事件直接绑定一个方法-->
            <button v-on:click="greet">Greet</button>
        </p>
        <p>
            <!--click事件使用内联语句-->
            <button v-on:click="say('Hi')">Hi</button>
        </p>
    </div>
</body>
<script src="<%=basePath%>sweetAlert/sweetalert2.min.js"></script>
<script src="<%=basePath%>js/vue/vue.js"></script>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!',
            abc: "abcd"

        }
    });
    var app2 = new Vue({
        el: '#app-2',
        data: {
            message: '页面加载于 ' + new Date()
        }
    });
    var app3 = new Vue({
        el:"#app-3",
        data:{
            seen: true
        }
    });
    var app4 = new Vue({
        el: '#app-4',
        data: {
            todos: [
                { text: '学习 JavaScript' },
                { text: '学习 Vue' },
                { text: '整个牛项目' }
            ]
        }
    })
    var app5 = new Vue({
        el: '#app-5',
        data: {
            message: 'Hello Vue.js!'
        },
        methods: {
            reverseMessage: function () {
                this.message = this.message.split('').reverse().join('')
            }
        }
    })
    var app6 = new Vue({
        el: '#app-6',
        data: {
            message: 'Hello Vue!'
        }
    })
    Vue.component('todo-item', {
        props: ['todo'],
        template: '<li>{{ todo.text }}</li>'
    })
    var app7 = new Vue({
        el: '#app-7',
        data: {
            groceryList: [
                { text: '蔬菜' },
                { text: '奶酪' },
                { text: '随便其他什么人吃的东西' }
            ]
        }
    })
    var app8 = new Vue({
        el: '#app-8',
        data: {
            activeNumber: 1,
            pageCount: 10
        }
    });
    var app9 = new Vue({
        el: '#app-9',
        data: {
            message: 'Hello, Vue.js!'
        },
        // 在 `methods` 对象中定义方法
        methods: {
            greet: function() {
                // // 方法内 `this` 指向 vm
                swal(this.message,"success");
            },
            say: function(msg) {
                swal({
                    text: msg,
                    type: "success",
                    confirmButtonText: '确认',
                    confirmButtonColor: '#4cd964'
                });
            }
        }
    });
</script>
</html>
