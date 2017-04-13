<%@ taglib prefix="@keyup" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2017-4-13
  Time: 20:48
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
    <div id="app1">
        <input type="text" @keyup.13="aaa" ref="aaa"/><br>
        <input type="text" @keyup.13="bbbb" v-model="bbb"/><br>
        <!-- Alt + C -->
        <input @keyup.alt.67="clear" ref="ccc"><br>
        <!-- Ctrl + Click -->
        <div @click.ctrl="doSomething">Do something</div><br>
        <input type="text" v-model="ddd" placeholder="123456" value="12456"/>
        <div v-if="this.ddd === ''">不能为空</div>
        <div v-else>{{ddd}}</div>
        <p>单个复选框：</p>
        <input type="checkbox" id="checkbox" v-model="checked">
        <label for="checkbox">{{ checked }}</label><br>

        <p>多个复选框：</p>
        <input type="checkbox" id="runoob" value="Runoob" v-model="checkedNames">
        <label for="runoob">runoob</label>
        <input type="checkbox" id="google" value="Google" v-model="checkedNames">
        <label for="google">Google</label>
        <input type="checkbox" id="taobao" value="Taobao" v-model="checkedNames">
        <label for="taobao">taobao</label>
        <br>
        <span>选择的值为: {{ checkedNames }}</span><br>
        <input type="radio" id="runoob1" value="Runoob" v-model="picked">
        <label for="runoob1">Runoob</label>
        <br>
        <input type="radio" id="google1" value="Google" v-model="picked">
        <label for="google1">Google</label>
        <br>
        <span>选中值为: {{ picked }}</span><br>
        <select v-model="selected">
            <option value="">选择一个网站</option>
            <option value="www.runoob.com">Runoob</option>
            <option value="www.google.com">Google</option>
        </select>
        <div>
            选择的网站是: {{selected}}
        </div><br>
        <%--焦点失去时触发--%>
        <input v-model.lazy="msg" >
        <div>修改为: {{msg}}</div><br>
        <input v-model.number="age" type="number"><br>
        <input v-model.trim="trim">
    </div>
</body>
<script src="<%=basePath%>sweetAlert/sweetalert.min.js"></script>
<script src="<%=basePath%>js/vue/vue.js"></script>
</html>
<script>
    new Vue({
        el:"#app1",
        data:{
            //v-moddel里的值一定要初始化
            bbb:"",
            ddd:"",
            checked : false,
            checkedNames: [],
            picked:'Google',
            selected:"",
            msg:"",
            age:'',
            trim:''
        },
        methods:{
            aaa:function () {
                swal(this.$refs.aaa.value);
                this.checked=true;
                this.picked = 'Google';
                this.age=26;
                this.trim='abc'
            },
            bbbb:function () {
                this.checkedNames=['Runoob','Taobao'];
                swal(this.bbb);
            },
            clear:function () {
                this.$refs.ccc.value="";
            },
            doSomething:function () {
                this.bbb="";
            }
        }
    });
</script>