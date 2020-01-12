<template>
    <div>
        <Row>
            <i-col span="24">
                <Card style="">
                    <p slot="title">登录</p>
                    <Form ref="formInline" :model="formInline" :rules="ruleInline">
                        <FormItem prop="user">
                            <label>
                                <Input type="text" v-model="formInline.name" placeholder="Username"> @keyup.enter="handleSubmit()"
                                    <Icon type="ios-person-outline" slot="prepend"></Icon>
                                </Input>
                            </label>
                        </FormItem>

                        <FormItem prop="password">
                            <Input type="password" v-model="formInline.password" placeholder="Password">
                                <Icon type="ios-lock-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="code">
                            <Row>
                                <i-col span="12">
                                    <Input type="text" v-model="formInline.code" placeholder="code"
                                           style="width: 200px;"></Input>
                                </i-col>
                                <i-col span="12">
                                    <img :src="codeUrl" @click="getcode"/>
                                </i-col>
                            </Row>
                        </FormItem>
                        <FormItem>
                            <Button type="primary" @click="handleSubmit()" >登录</Button>
                        </FormItem>
                    </Form>
                </Card>
            </i-col>
        </Row>
    </div>
</template>
<script>
    import {serverIp} from "../../api/index"
    export default {
        data() {
            return {
                formInline: {
                    name: 'admin',
                    password: '123',
                    code: ""
                },
                codeUrl: serverIp + "/code",
                ruleInline: {
                    name: [
                        {required: true, message: '账号不能为空', trigger: 'blur'}
                    ],
                    code: [
                        // {required: true, message: '验证码不能为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur'},
                        {
                            type: 'string',
                            min: 3,
                            message: 'The password length cannot be less than 3 bits',
                            trigger: 'blur'
                        }
                    ]
                }
            }
        },
        methods: {
            handleSubmit() {
                this.$refs['formInline'].validate((valid) => {
                    if (valid) {
                        // if (this.formInline.user === "admin" && this.formInline.password === "123") {
                        //     this.$router.push({name: 'Main'});
                        //
                        // }else {
                        //     this.$Message.error('账号或密码错误!');
                        // }
                        this.$api.post('/login', this.formInline, r => {
                            window.console.log(r);
                            window.console.log('---');
                            if (r.code === "00") {
                                this.$router.push({name: 'Main',params:{
                                    "token":"cdn"
                                    }});
                            } else {
                                this.formInline.code = "";
                                // this.$Message.error(r.msg);
                                this.$Modal.error({
                                    title: "系统提示",
                                    content:r.msg ,
                                    width:"80%"
                                });
                            }
                        });
                    } else {
                        this.$Message.error('必填项不能为空!');
                    }
                })
            },
            getcode: function () {
                this.codeUrl = serverIp + "/code?" + new Date()
            }
        }, mounted() {
            // this.$Message.config({
            //     top: 100,  //距离顶部的高度
            //     duration: 30 //时间
            // });
            // this.$Notice.config({
            //     top: 100,
            //     duration: 3
            // })
        },
        created() {  //全局监听键盘事件
            // var _this = this;
            // document.onkeydown = function (e) {
            //     let key = e.keyCode;
            //     if (key === 13) {
            //         _this.handleSubmit();
            //     }
            // };
        }
    }
</script>
<style>
    img:hover {
        cursor: pointer;
    }
</style>
