layui.define(['layer', 'table'], function (exports) {
    var $ = layui.jquery;
    var layer = layui.layer;
    var table = layui.table;

    //数据分类
    var dataClassification = function(treeSpid,data,resultArr){
        if(isClass(resultArr)==='Null' || isClass(resultArr)==='Undefined'){
            resultArr=[];
        }
        for (var i = 0; i < data.length; i++) {
            var index = data[i];
            if (index.pid == treeSpid) {
                var childrenList = [];
                var indexMap = {data:data[i],children:childrenList};
                resultArr.push(indexMap);
                dataClassification(data[i].id, data,childrenList);
            }
        }
        return resultArr;
    },
    //判断数据是什么类型
    isClass = function (object) {
        if(object===null) return "Null";
        if(object===undefined) return "Undefined";
        return Object.prototype.toString.call(object).slice(8,-1);
    },
    //深度克隆参数param，只使用于本项目。慎用！
    deepClone = function (obj) {
        var classType = isClass(obj);
        if(classType==='Null' || classType==='Undefined'){return '';}
        if(classType==='Array'){
            return JSON.parse(JSON.stringify(obj));
        }else if(classType==='Object'){
            return JSON.parse(JSON.stringify(obj));
        }else {
            return obj;
        }
    },
    //递归获取子级
    findChild = function (id,data,set) {
        for(var i=0;i<data.length;i++){
            var index=data[i];
            if(id===index.pid){
                set.add(index);
                findChild(index.id,data,set);
            }
        }
    },
    //递归获取父级
    findParent = function (pid,data,set) {
        for(var i=0;i<data.length;i++){
            var index=data[i];
            if(pid===index.id){
                set.add(index);
                if(index.pid != -1){
                    findParent(index.pid,data,set);
                }
                break;
            }
        }
    },
    //递归子级搜索
    search = function (data,fieldName,keyword,set) {
        if(isClass(set)==='Null' || isClass(set)==='Undefined'){
            set=new Set();
        }
        for(var i=0;i<data.length;i++){
            var index = data[i];
            if(index.data[fieldName].indexOf(keyword)>-1){
                set.add(index.data);
            }else {
                if(index.children.length>0){
                    search(index.children,fieldName,keyword,set);
                }
            }
        }
        return set;
    },
    //有序的放入一个数组集合中
    toArray = function (data,resultArr) {
        if(isClass(resultArr)==='Null' || isClass(resultArr)==='Undefined'){
            resultArr=new Array();
        }
        for(var i=0;i<data.length;i++){
            var index=data[i];
            resultArr.push(index.data);
            if(index.children.length>0){
                index.data.isParent=true;
                toArray(index.children,resultArr);
            }
        }
        return resultArr;
    },
    // 对数据进行排序
    sort = function (s_pid, data,mData) {
        if(isClass(mData)==='Null' || isClass(mData)==='Undefined'){
            mData=new Array();
        }
        for (var i = 0; i < data.length; i++) {
            if (data[i].pid == s_pid) {
                var len = mData.length;
                if (len > 0 && mData[len - 1].id == s_pid) {
                    mData[len - 1].isParent = true;
                }
                mData.push(data[i]);
                sort(data[i].id, data,mData);
            }
        }
        return mData;
    },
    //数据逐级按一定方式排序
    dataSort = function (data,sortField,sortClass) {
        data.sort(function (x,y) {
            var sortX=x.data[sortField],sortY=y.data[sortField];
            if(typeof sortX === 'string' && typeof sortY === 'string'){
                sortX=sortX.replace(/-/g,'/');
                sortX=new Date(sortX).getTime();
                sortY=sortY.replace(/-/g,'/');
                sortY=new Date(sortY).getTime();
            }
            if(isNaN(sortX) || isNaN(sortY)){
                sortX=x.data[sortField].length;
                sortY=y.data[sortField].length;
            }
            if('desc'===sortClass){
                return sortY-sortX;
            }else {
                return sortX-sortY;
            }
        });
        for (var i = 0; i < data.length; i++) {
            if(data[i].children.length>0){
                dataSort(data[i].children,sortField,sortClass);
            }
        }
    };

    var treetable = {
        // 渲染树形表格
        render: function (param) {
            // 检查参数
            if (!treetable.checkParam(param)) {
                return;
            }

            //深度对象拷贝参数
            var params={};
            for(var key in param){
                params[key]=deepClone(param[key]);
            }
            treetable.params=params;

            // 获取数据
            if (param.data) {
                treetable.init(param, param.data);
            } else {
                $.getJSON(param.url, param.where, function (res) {
                    if("function"==typeof param.parseData){
                        treetable.init(param,param.parseData(res).data);
                    }else{
                        treetable.init(param, res.data);
                    }
                });
            }
        },
        // 渲染表格
        init: function (param, data) {
            var mData = [];
            var doneCallback = param.done;
            var tNodes = data;
            // 补上id和pid字段
            for (var i = 0; i < tNodes.length; i++) {
                var tt = tNodes[i];
                if (!tt.id) {
                    if (!param.treeIdName) {
                        layer.msg('参数treeIdName不能为空', {icon: 5});
                        return;
                    }
                    tt.id = tt[param.treeIdName];
                }
                if (!tt.pid) {
                    if (!param.treePidName) {
                        layer.msg('参数treePidName不能为空', {icon: 5});
                        return;
                    }
                    tt.pid = tt[param.treePidName];
                }
            }

            //拷贝一份数据
            var dataClass = JSON.parse(JSON.stringify(data));
            //数据归类数组
            var sortArr = dataClassification(param.treeSpid,dataClass);
            if(param.sortInit){
                //按规则排序
                dataSort(sortArr,param.sortInit.field,param.sortInit.type);
                mData=toArray(sortArr);
            }else {
                //不按规则排序
                mData=sort(param.treeSpid, tNodes);
            }

            //保存数据
            if(!treetable.treeData || treetable.treeData.length===0){
                //深度拷贝缓存数据.注意：此方式不能拷贝undefined的属性，以及只能拷贝基本数据类型的数据，不能拷贝方法等。
                treetable.treeData = JSON.parse(JSON.stringify(sortArr));
            }else {
                //updateTreeDataCache为true则更新缓存的treeData数据，false则不更新。
                if(isClass(param.updateTreeDataCache)!='Undefined'
                    && isClass(param.updateTreeDataCache)!='Null' && param.updateTreeDataCache===true){
                    treetable.treeData = JSON.parse(JSON.stringify(sortArr));
                }
            }

            // 重写参数
            param.url = undefined;
            param.data = mData;
            param.page = {
                count: param.data.length,
                limit: param.data.length
            };
            param.cols[0][param.treeColIndex].templet = function (d) {
                var mId = d.id;
                var mPid = d.pid;
                var isDir = d.isParent;
                var emptyNum = treetable.getEmptyNum(mPid, mData);
                var iconHtml = '';
                for (var i = 0; i < emptyNum; i++) {
                    iconHtml += '<span class="treeTable-empty"></span>';
                }
                if (isDir) {
                    iconHtml += '<i class="layui-icon layui-icon-triangle-d"></i> <i class="layui-icon layui-icon-layer"></i>';
                } else {
                    iconHtml += '<i class="layui-icon layui-icon-file"></i>';
                }
                iconHtml += '&nbsp;&nbsp;';
                var ttype = isDir ? 'dir' : 'file';
                var vg = '<span class="treeTable-icon open" lay-tid="' + mId + '" lay-tpid="' + mPid + '" lay-ttype="' + ttype + '">';
                return vg + iconHtml + d[param.cols[0][param.treeColIndex].field] + '</span>'
            };

            param.done = function (res, curr, count) {
                $(param.elem).next().addClass('treeTable');
                $('.treeTable .layui-table-page').css('display', 'none');
                $(param.elem).next().attr('treeLinkage', param.treeLinkage);
                // 绑定事件换成对body绑定
                /*$('.treeTable .treeTable-icon').click(function () {
                    treetable.toggleRows($(this), param.treeLinkage);
                });*/
                if (param.treeDefaultClose) {
                    treetable.foldAll(param.elem);
                }
                if (doneCallback) {
                    doneCallback(res, curr, count);
                }
            };

            // 渲染表格
            table.render(param);
        },
        // 计算缩进的数量
        getEmptyNum: function (pid, data) {
            var num = 0;
            if (!pid) {
                return num;
            }
            var tPid;
            for (var i = 0; i < data.length; i++) {
                if (pid == data[i].id) {
                    num += 1;
                    tPid = data[i].pid;
                    break;
                }
            }
            return num + treetable.getEmptyNum(tPid, data);
        },
        // 展开/折叠行
        toggleRows: function ($dom, linkage) {
            var type = $dom.attr('lay-ttype');
            var mId = $dom.attr('lay-tid');
            //执行点击列图标回调事件
            if(treetable.params.clickIcon){
                treetable.params.clickIcon(mId,type);
            }
            if ('file' == type) {
                return;
            }
            var isOpen = $dom.hasClass('open');
            if (isOpen) {
                $dom.removeClass('open');
            } else {
                $dom.addClass('open');
            }
            $dom.closest('tbody').find('tr').each(function () {
                var $ti = $(this).find('.treeTable-icon');
                var pid = $ti.attr('lay-tpid');
                var ttype = $ti.attr('lay-ttype');
                var tOpen = $ti.hasClass('open');
                if (mId == pid) {
                    if (isOpen) {
                        $(this).hide();
                        if ('dir' == ttype && tOpen == isOpen) {
                            $ti.trigger('click');
                        }
                    } else {
                        $(this).show();
                        if (linkage && 'dir' == ttype && tOpen == isOpen) {
                            $ti.trigger('click');
                        }
                    }
                }
            });
        },
        // 检查参数
        checkParam: function (param) {
            if (!param.treeSpid && param.treeSpid != 0) {
                layer.msg('参数treeSpid不能为空', {icon: 5});
                return false;
            }

            if (!param.treeColIndex && param.treeColIndex != 0) {
                layer.msg('参数treeColIndex不能为空', {icon: 5});
                return false;
            }

            if(isClass(param.treeShowName) === 'Null' && isClass(param.treeShowName) === 'Undefined' && param.treeShowName===''){
                layer.msg('参数treeShowName不能为空', {icon: 5});
                return false;
            }
            return true;
        },
        // 展开所有
        expandAll: function (dom) {
            $(dom).next('.treeTable').find('.layui-table-body tbody tr').each(function () {
                var $ti = $(this).find('.treeTable-icon');
                var ttype = $ti.attr('lay-ttype');
                var tOpen = $ti.hasClass('open');
                if ('dir' == ttype && !tOpen) {
                    $ti.trigger('click');
                }
            });
        },
        // 折叠所有
        foldAll: function (dom) {
            $(dom).next('.treeTable').find('.layui-table-body tbody tr').each(function () {
                var $ti = $(this).find('.treeTable-icon');
                var ttype = $ti.attr('lay-ttype');
                var tOpen = $ti.hasClass('open');
                if ('dir' == ttype && tOpen) {
                    $ti.trigger('click');
                }
            });
        },
        //对外暴露的重新渲染方法where -> {params:{},where:{}}
        reload:function (where) {
            //此为关键字搜索
            var keyword='';
            if(isClass(where)!='Undefined' && isClass(where)!='Null'){
                if(where.params){
                    if(isClass(where.params.updateTreeDataCache)==='Null' || isClass(where.params.updateTreeDataCache)==='Undefined'){
                        where.params.updateTreeDataCache = false;
                    }
                    if(isClass(where.params.treeDefaultClose)==='Null' || isClass(where.params.treeDefaultClose)==='Undefined'){
                        where.params.treeDefaultClose = true;
                    }
                    $.extend(this.params,where.params);
                }
                if(where.where && where.where.keyword){
                    keyword=where.where.keyword;
                }
            }else {
                this.params.updateTreeDataCache = false;
                this.params.treeDefaultClose = true;
            }
            if(isClass(this.params.data)!='Null' && isClass(this.params.data)!='Undefined'){
                //此为关键字搜索，不用ajax请求后台，只是对前端数据进行搜索，并重新渲染！
                var data = JSON.stringify(treetable.treeData);
                data = JSON.parse(data);
                var existSet = search(data,this.params.treeShowName,keyword);
                var dataArr = toArray(data);
                var resultSet = new Set();
                existSet.forEach(function (e) {
                    if(e.pid!=-1){
                        findParent(e.pid,dataArr,resultSet);
                    }
                    findChild(e.id,dataArr,resultSet);
                    resultSet.add(e);
                });
                this.params.data=Array.from(resultSet);
            }else {
                //此为ajax后台搜索，得到数据后重新渲染！
                this.params.where=where.where;
            }
            this.render(this.params);
        }
    };

    layui.link(layui.cache.base + 'treetable-lay/treetable.css');

    // 给图标列绑定事件
    $('body').on('click', '.treeTable .treeTable-icon', function () {
        var treeLinkage = $(this).parents('.treeTable').attr('treeLinkage');
        if ('true' == treeLinkage) {
            treetable.toggleRows($(this), true);
        } else {
            treetable.toggleRows($(this), false);
        }
    });

    exports('treetable', treetable);
});
