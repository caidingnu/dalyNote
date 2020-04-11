/**
 * 扩展 VuePress 应用   ****  全局导入  element
 */
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

/**
 * 扩展 VuePress 应用   ****  全局导入  iview
 */
import iview from 'iview'
import 'iview/dist/styles/iview.css'

export default ({
                    Vue,
                }) => {
    // ...做一些其他的应用级别的优化
    Vue.use(Element);
    Vue.use(iview);
}



