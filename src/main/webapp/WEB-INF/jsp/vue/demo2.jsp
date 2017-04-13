<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2017-4-12
  Time: 22:00
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
<style>
    .class1{
        background: #444;
        color: #fff;
    }
</style>
<body>
    <div id="app1">
        <a>{{message}}</a>
    </div>
    <div id="app2">
        <div v-html="message"></div>
    </div>
    <div id="app3">
        <input type="checkbox" v-model="class1" id="r1"><label for="r1">修改颜色</label>
        <br><br>
        <div v-bind:class="{class1: class1}">
            directiva v-bind:class
        </div>
    </div>
    <div id="app4">
        <pre><a v-bind:href="url">博客地址</a></pre>
        <pre><a :href="url1">博客地址</a></pre>
    </div>
    <div id="app5">
        <p>{{message}}</p>
        <button v-on:click="abc">弹出页面</button>
        <button @click="abcd">再次弹出页面</button>
    </div>
    <div id="app6">
        {{ message | capitalize }}
    </div>
    <a id = "example"></a>
    <div id="app7">
        <ul>
            <li v-for="(value, key, index) in object">
                {{ index+1 }}. {{ key }} : {{ value }}
            </li>
        </ul>
    </div>
    <div id="app8">
        <ul>
            <a v-for="n in 10">
                {{ n }}
            </a>
        </ul>
    </div>
    <div id="app9">
        <button @click="counter += 1">增加 1</button>
        <p>这个按钮被点击了 {{ counter }} 次。</p>
    </div>
    <div id="app10">
        <!-- `greet` 是在下面定义的方法名 -->
        <button @click="greet">Greet</button>
        <button v-on:click="say('what')">Say what</button>
    </div>
</body>
<script src="<%=basePath%>sweetAlert/sweetalert.min.js"></script>
<script src="<%=basePath%>js/vue/vue.js"></script>
<script>

    new Vue({
        el:"#app1",
        data:{
            message: "努力加油！"
        }
    });

    new Vue({
        el: "#app2",
        data:{
            message: "<h1>输出html</h1>"
        }
    });
    new Vue({
        el: '#app3',
        data:{
            class1: false
        }
    });
    new Vue({
        el:"#app4",
        data:{
            url: "http://www.cnblogs.com/aqsunkai/",
            url1:"http://www.cnblogs.com/aqsunkai/"
        }
    });
    new Vue({
        el:"#app5",
        data:{
            message:"弹我弹我"
        },
        methods:{
            abc:function(){
                swal(this.message,"弹出我了","success");
            },
            abcd:function () {
                swal(this.message);
            }
        }
    })
    new Vue({
        el: '#app6',
        data: {
            message: 'abc'
        },
        filters: {
            capitalize: function (value) {
                if (!value) return '';
                value = value.toString();
                return value.charAt(0).toUpperCase() + value.slice(1);
            }
        }
    });
    var data = { a: 1 };
    var vm = new Vue({
        el: '#example',
        data: data
    });
    console.log(vm.a === 1);
    console.info(vm.$data === data);
    console.info(vm.$el === document.getElementById("example"));
    // $watch 是一个实例方法
    vm.$watch('a', function (newVal, oldVal) {
        // 这个回调将在 `vm.a`  改变后调用
        console.log(11111111);
    });
    vm.a = 2;
    data.a = 3;
    new Vue({
       el:"#app7",
        data:{
           object:{
               a:"菜鸟教程",
               b:"慢慢学习",
               c:"前后端都厉害才牛逼"
           }
        }
    });
    new Vue({
       el:"#app8"
    });
    new Vue({
        el:"#app9",
        data:{
            counter:0
        }
    });
    new Vue({
       el:"#app10",
        data:{
           name:"Vue.js"
        },
        methods:{
           greet:function () {
               swal('Hello ' + this.name + '!');
               // `event` 是原生 DOM 事件
               /*if (event) {
                   sweetAlert(event.target.tagName);
               }*/
           },
            say:function (event) {
               swal(event);
            }
        }
    });
</script>
</html>
