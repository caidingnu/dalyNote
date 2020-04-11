# 概述
| Tables        | Are           | Cool  |
| :------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |
| zz            |
::: tip
提示
:::

## 代码片段
```js {1,2}
export default {
  data () {
    return {
      msg: 'Highlighted!'
    }
  }
}
```
***
加粗的文字啊
***

---



## 页面信息
{{$page}} 


##  组件引入  路径是  /.vuepress/components   （文件夹下的组件格式   文件夹-组件名）
<ClientOnly>
<My />
<demo-Demo />
</ClientOnly>


## 路由
config.js 中配置路由的时候默认REANME.md  可以省略