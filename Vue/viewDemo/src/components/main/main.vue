<!-- eslint-disable -->
<template>
    <div :title="msg">
        <div v-for="item in todos">
            {{item.text}}
        </div>
        {{inputMsg}}：
        <i-input icon="ios-clock-outline" placeholder="请输入..." style="width: 300px" v-model="inputMsg"></i-input>
        <a :href="href">百度</a>
        <br>
        <div>
            <i-button type="primary" @click="modal1 = true">显示对话框{{modal1}}</i-button>
            <Modal v-model="modal1" title="普通的Modal对话框标题" @on-ok="ok" @on-cancel="cancel" :mask-closable="false">
                <p>对话框内容</p>
                <p>对话框内容</p>
                <p>对话框内容</p>
            </Modal>

            <Modal v-model="modal2" title="普通的Modal对话框标题" @on-ok="ok" @on-cancel="cancel" :mask-closable="false">
                <p> name:{{tableData.name}}</p>
                <p>password:{{tableData.password}}</p>
            </Modal>
        </div>
        <div style="margin-top: 30px">
            <Steps :current="current" status="error">
                <Step title="已完成" content="这里是该步骤的描述信息"></Step>
                <Step title="进行中" content="这里是该步骤的描述信息"></Step>
                <Step title="待进行" content="这里是该步骤的描述信息"></Step>
                <Step title="待进行" content="这里是该步骤的描述信息"></Step>
            </Steps>
            <i-button v-on:click="currNum()">下一步</i-button>
        </div>
        <hr/>
        <div style="width: 600px;text-align: left;margin-top: 30px">
            <i-input v-model="keyword" placeholder="请输入用户名" style="width: 300px" left></i-input>
            <i-button v-on:click="search()" type="success">搜索</i-button>
            <i-button v-on:click="model()" type="success">新增</i-button>
            <i-button type="error" @click="batshDelByIds()">批量删除</i-button>
        </div>

        <div style="text-align: center;justify-content: center;">
            <i-table
                    border
                    height="400"
                    :columns="tabletitle"
                    :data="data2"
                    :row-class-name="rowClassName"
                    @on-selection-change="fun1">
            </i-table>
        </div>
        <button @click="back">ss</button>
        <Modal v-model="add" title="新增用户" @on-ok="addUser" @on-cancel="cancelUser" :mask-closable="false">
            <i-form :rules="ruleInline" :label-width="60">
                <Form-item prop="name" label=" 账号">
                    <i-input type="text" v-model="adddata.name" placeholder="Username">
                        <Icon type="ios-person-outline" slot="prepend"></Icon>
                    </i-input>
                </Form-item>
                <Form-item prop="password" label=" 密码">
                    <i-input type="password" v-model="adddata.password" placeholder="Password">
                        <Icon type="ios-person-outline" slot="prepend"></Icon>
                    </i-input>
                </Form-item>
                <Form-item label="昵称">
                    <i-input type="text" v-model="adddata.nickName" placeholder="nickName">
                        <Icon type="ios-person-outline" slot="prepend"></Icon>
                    </i-input>
                </Form-item>
                <Form-item label="年龄">
                    <input-number v-model="adddata.age" :min="1" :max="150" style="width: 100%;"></input-number>
                </Form-item>
            </i-form>
            <div slot="footer">
                <i-button type="success" @click="addUser">保存</i-button>
                <i-button type="error" @click="hiden()">取消</i-button>
            </div>
        </Modal>
    </div>
</template>

<script>
    export default {
        components: {},
        data() {
            return {
                msg: "鼠标经过显示",
                todos: [
                    {text: '学习 JavaScript'},
                    {text: '学习 Vue'},
                    {text: '整个牛项目'}
                ],
                adddata: {
                    name: '',
                    password: '',
                    nickName: '',
                    age: 0
                },
                inputMsg: '双向绑定',
                href: 'http://www.baidu.com',
                modal1: false,
                modal2: false,
                add: false,
                current: 0,
                keyword: '',
                tableData: {},
                batshDelIds: [],
                ruleInline: {
                    name: [
                        {required: true, message: '账号不能为空', trigger: 'blur'}
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
                },
                tabletitle: [
                    {
                        type: 'selection',
                        width: 50,
                    },
                    {
                        title: 'id',
                        sortable: true,
                        align: 'center',
                        width: 150,
                        key: 'id'
                    },
                    {
                        title: '名称',
                        align: 'center',
                        key: 'name',
                    },
                    {
                        title: '密码',
                        align: 'center',
                        className: 'demo-table-info-column', //该列统一设置类名背景色
                        key: 'password'
                    },
                    {
                        title: '昵称',
                        align: 'center',
                        key: 'nickName',
                        render: (h, params) => {
                            if (params.row.id === 1) {
                                return h('span', {
                                    style: {
                                        color: 'red'
                                    },
                                }, params.row.nickName);
                            } else {
                                return h('span', params.row.nickName);
                            }
                        }
                    },
                    {
                        title: '年龄',
                        align: 'center',
                        key: 'age',
                        render: (h, params) => {
                            return h('a', {}, '判断年龄=' + params.row.age);
                        }
                    },
                    {
                        title: '操作',
                        width: 200,
                        align: 'center',
                        render: (h, params) => {
                            if (params.row.id === 1) {
                                return h('div', [
                                        h('Button', {
                                            props: {
                                                type: 'error',
                                                size: 'small'
                                            },
                                            style: {
                                                marginRight: '5px'
                                            },
                                            on: {
                                                click: () => {
                                                    this.tan(params.index)
                                                }
                                            }
                                        }, '弹')

                                    ],
                                );
                            }
                            return h('div', [
                                    h('Button', {
                                        props: {
                                            type: 'primary',
                                            size: 'small'
                                        },
                                        style: {
                                            marginRight: '5px'
                                        },
                                        on: {
                                            click: () => {
                                                this.tableData = params.row;
                                                this.modal2 = true
                                            }
                                        }
                                    }, '编辑'),
                                    h('Button', {
                                        props: {
                                            type: 'error',
                                            size: 'small'
                                        },
                                        style: {
                                            marginRight: '5px'
                                        },
                                        on: {
                                            click: () => {
                                                this.remove(params)
                                            }
                                        }
                                    }, '删除'),
                                    h('Button', {
                                        props: {
                                            type: 'error',
                                            size: 'small'
                                        },
                                        style: {
                                            marginRight: '5px'
                                        },
                                        on: {
                                            click: () => {
                                                this.tan(params.index)
                                            }
                                        }
                                    }, '弹')

                                ],
                            );
                        }
                    }
                ],
                data2: []
            };
        },
        beforeCreate() {

        },
        created() {
            this.tableInit();
        }, beforeUpdate() {
        },
        mounted() {
            if (this.$route.params.token === undefined) {
                this.$router.push({name: 'HelloWorld'});
            }
        },
        methods: {
            rowClassName(row, index) {
                if (index === 1) {
                    return 'demo-table-info-row';
                } else if (index === 3) {
                    return 'demo-table-error-row';
                }
                return '';
            },
            back() {
                this.$router.push({name: 'HelloWorld'});
            },
            currNum() {
                if (this.current === 4) {
                    this.current = 0;
                } else {
                    this.current += 1;
                }
            },
            fun1(selection) {
                //监听多选
                this.batshDelIds = selection;
            },
            batshDelByIds() {
                //    批量删除
                let ids = [];
                for (let i = 0; i < this.batshDelIds.length; i++) {
                    ids.push(this.batshDelIds[i].id)
                }
                if (ids.length === 0) {
                    this.$Message.error("请选择要删除的条目")
                } else {
                    this.$api.post("removeByIds", ids, r => {
                        if (r.code == "00") {
                            this.$Message.info("删除成功")
                            this.tableInit();
                        } else {
                            this.$Message.error(r.msg)
                        }
                    })
                }

            },
            model() {
                this.add = true;
                this.removeValue(this.adddata, 'adddata')
            },
            ok() {
                this.$Message.info('点击了确定');
            },
            cancel() {
                this.$Message.info('点击了取消');
            },
            addUser() {
                // this.$Message.info('点击了确定');
                if (this.adddata.name === '' || this.adddata.password === '') {
                    this.$Message.error("不能为空");
                } else {
                    this.$api.get('/insert', this.adddata, r => {
                        if (r.code === "00") {
                            this.$Message.info('成功');
                            this.hiden();
                            this.tableInit()
                            this.removeValue(this.adddata, 'adddata')
                        } else {
                            this.$Message.err(r.msg);
                        }
                    });
                }
            },
            removeValue(jsonObj, type) {
                for (let key in jsonObj) {
                    jsonObj[key] = ''
                }
                if (type === 'adddata') {
                    jsonObj.age = 1;
                }
            },
            hiden() {
                this.add = false;
            },
            cancelUser() {
                this.$Message.info('点击了取消');
            },
            tableInit() {
                this.$api.get("/userList", {"keyword": this.keyword}, r => {
                    if (r.code === "00") {
                        this.data2 = [];
                        for (let i = 0; i < r.data.records.length; i++) {
                            let listDate = r.data.records[i];
                            //给单个单元格设置背景色
                            if (listDate.id === 1) {
                                listDate.cellClassName = {
                                    id: 'demo-table-info-cell-age',  //给id为1的，表格的key为id的设置类名样式
                                    age: 'demo-table-info-cell-address'//给id为1的，表格的key为age的设置类名样式
                                };
                            }
                            this.data2.push(r.data.records[i])
                        }
                    }
                });
            },
            tan(index) {
                this.$Modal.info({
                    title: '用户信息',
                    duration: 3,
                    content: `账号：${this.data2[index].name}<br>
                            密码：${this.data2[index].password}<br>
                            昵称：${this.data2[index].nickName}`
                })
            },
            remove(param) {
                //删除用户
                // console.log(this.data2.splice(index, 1))
                this.$api.get("/delById", {"id": param.row.id}, r => {
                    if (r.code === "00") {
                        this.data2.splice(param.index, 1);
                        this.$Message.info("删除成功", {
                            duration: 2000
                        });
                        this.search();
                    } else {
                        this.$Message.error(r.msg, {
                            top: 20,
                            duration: 2000
                        });
                    }
                })
            },
            search() {
                this.$api.get("/userList", {"keyword": this.keyword}, r => {
                    this.data2 = [];
                    if (r.code === "00") {
                        for (let i = 0; i < r.data.records.length; i++) {
                            this.data2.push(r.data.records[i])
                        }
                    }
                });
            }
        }
    }
</script>
<style>
    .ivu-table .demo-table-info-row td {
        background-color: #2db7f5;
        color: #fff;
    }

    .ivu-table .demo-table-error-row td {
        background-color: #ff6600;
        color: #fff;
    }

    .ivu-table td.demo-table-info-column {
        background-color: #2db7f5;
        color: #fff;
    }

    .ivu-table .demo-table-info-cell-name {
        background-color: #2db7f5;
        color: #fff;
    }

    .ivu-table .demo-table-info-cell-age {
        background-color: #ff6600;
        color: #fff;
    }

    .ivu-table .demo-table-info-cell-address {
        background-color: #187;
        color: #fff;
    }

    .first {
        background-color: crimson;
    }
</style>
