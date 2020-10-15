let watermark = {}
/**
 *
 * @param {要设置的水印的内容} str
 * @param {需要设置水印的容器} container
 */
const setWatermark = (str, container) => {
    const id = '1.23452384164.123412415'
    // 查看页面上有没有，如果有则删除
    if (document.getElementById(id) !== null) {
        const childelement = document.getElementById(id)
        childelement.parentNode.removeChild(childelement)
    }

    var containerWidth = container.offsetWidth // 获取父容器宽
    var containerHeight = container.offsetHeight // 获取父容器高
    container.style.position = 'relative' // 设置布局为相对布局

    // 创建canvas元素(先制作一块背景图)
    const can = document.createElement('canvas')
    //宽高可以设置水印密集度
    can.width = 190 // 设置每一块的宽度
    can.height = 300 // 高度
    const cans = can.getContext('2d') // 获取canvas画布
    cans.rotate(-20 * Math.PI / 180) // 逆时针旋转π/9
    cans.font = '20px Vedana' // 设置字体
    cans.fillStyle = 'rgba(200, 200, 200, 0.40)' // 设置字体的颜色
    cans.textAlign = 'left' // 文本对齐方式
    cans.textBaseline = 'Middle' // 文本基线
    cans.fillText(str, 0, 4 * can.height / 5) // 绘制文字

    // 创建一个div元素
    const div = document.createElement('div')
    div.id = id // 设置id
    div.style.pointerEvents = 'none' // 取消所有事件
    div.style.top = '0px'
    div.style.left = '0px'
    div.style.position = 'absolute'
    div.style.zIndex = '10000000'
    div.style.width = containerWidth + 'px'
    div.style.height = containerHeight + 'px'
    div.style.background = 'url(' + can.toDataURL('image/png') + ') left top repeat'
    container.appendChild(div) // 追加到页面

    return id
}

// 该方法只允许调用一次
/**
 *
 * @param {要设置的水印的内容} str
 * @param {需要设置水印的容器HTMLElement} container
 */
watermark.set = (str, container) => {

    //不传容器标识参数则整个页面水印
    if (!container){
        container=document.body;
    }
    let id = setWatermark(str, container)
    setInterval(() => {
        if (document.getElementById(id) === null) {
            id = setWatermark(str, container)
        }
    }, 500)
    // 监听页面大小的变化
    window.onresize = () => {
        setWatermark(str, container)
    }
}
export default watermark;
