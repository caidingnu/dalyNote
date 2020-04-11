module.exports = {
    title: 'Hello VuePress',
    description: 'Hello, my friend!',
    head: [
        ['link', {
            rel: 'icon',
            href: `/favicon.ico`
        }]
    ],
    plugins:[
    ],
    markdown: {   // 代码片段显示行号
        lineNumbers: true
    },
    dest: './docs/.vuepress/dist',
    ga: '',
    evergreen: true,
    themeConfig: {
        // navbar: true,  //禁用启用导航
        // displayAllHeaders: true, // 默认值：false
        // activeHeaderLinks: true, // 默认值：true
        nav: [
            {text: 'Home', target: '_self', link: '/'},
            {text: 'Guide', link: '/guide/'},
            {text: 'External', link: 'https://google.com'},
            {
                text: 'language',
                items: [
                    {text: 'Chinese', link: '/language/chinese/'},
                    {text: 'Japanese', link: '/language/japanese/'}
                ]
            },
        ],
        sidebar: [
            {
                title: '概述',
                // collapsable: false,
                path: '/home/home'
            },
            {
                title: '编程',   // 必要的
                collapsable: true, // 可选的, 默认值是 true,
                children: [
                    {title: 'Java', path: '/java/'},
                    {title: 'PHP', path: '/php/'},
                    {
                        title: 'HTML',
                        collapsable: true,
                        path: '/html/'
                    },
                    {
                        title: 'Css',
                        path: '/css/'
                    },
                ]
            },
            {
                title: '义务教育',
                children: [ /* ... */]
            },
        ]
    }
}


