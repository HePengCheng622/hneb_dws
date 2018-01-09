/**
 * Created by Administrator on 2017/10/12 0012.
 */
/*
<!-- 先引入 eleme的样式 -->
document.write('<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css" crossorigin="anonymous">');
<!-- 先引入 Vue -->
document.write('<script src="https://unpkg.com/vue/dist/vue.js" crossorigin="anonymous"></script>');
<!-- 引入组件库 -->
document.write('<script src="https://unpkg.com/element-ui/lib/index.js" crossorigin="anonymous"></script>');
/!*引入网络资源文件*!/
document.write('<script src="https://cdn.bootcss.com/vue-resource/1.3.4/vue-resource.min.js" crossorigin="anonymous"></script>');
*/


function utils_(){
    this.get=function (url,options={async:true}) {
        // async:true//默认使用异步，true为异步，false同步
        return new Promise(function (resolve, reject) {
            var obj = new XMLHttpRequest();  // XMLHttpRequest对象
            obj.open('GET', url, options.async);
            obj.onreadystatechange = function () {
                // readyState == 4说明请求已完成
                if (obj.readyState == 4) {
                    if (obj.status == 200 || obj.status == 304) {
                        resolve(JSON.parse(obj.responseText));//直接将对象传入
                    } else {
                        reject(JSON.parse(obj.responseText));
                    }
                }
            };
            obj.send();
        });
    },
    this.post=function (url, data={},options={async:true}) {
        return new Promise(function (resolve, reject) {
            var obj = new XMLHttpRequest();  // XMLHttpRequest对象
            obj.open("POST", url, options.async);
            obj.setRequestHeader("X-Requested-With", "XMLHttpRequest");
            obj.setRequestHeader("Content-type", "application/json;charset=UTF-8");
            obj.onreadystatechange = function () {
                // readyState == 4说明请求已完成
                if (obj.readyState == 4) {
                    if (obj.status == 200 || obj.status == 304) {
                        resolve(JSON.parse(obj.responseText));//直接将对象传入
                    } else {
                        reject(JSON.parse(obj.responseText));
                    }
                }
            };
            console.log(data);
            obj.send(JSON.stringify(data));// 发送post数据
        });
    }

    /*this.ajax=function (url,data,options) {
        return new Promise(function(resolve,reject){
            var url_=url;
            if(url_.indexOf("?")!=-1){
                url_=url_+"&v="+new Date();
            }else{
                url_=url_+"?v="+new Date();
            }
            Vue.http.post("http://"+window.location.host+url_, data, {
                headers: {
                    "X-Requested-With": "XMLHttpRequest",
                }
            }).then(function(res){
                var responseData = res.body;
                resolve(responseData);
            },function(res){
                reject(res);
            });
        });
    }*/

    this.getRequest = function () {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }

    this.initCascader=function(obj){
        let parentCodes = [];
        for(let i=0;i<obj.length;i++){
            parentCodes.push(obj[i].code);
        }
        return this.post("/select/initCascader",{parentCodes:parentCodes}).then(res=>{//debugger;
            for(let i=0;i<obj.length;i++){
                let tmp = res.data[obj[i].code];
                for(let j=0;j<tmp.length;j++){
                    obj[i].model.push(tmp[j]);
                }
            }
        });
    }
    this.initSelect=function(obj){

        let params = [];
        for(let i=0;i<obj.length;i++){
            let tmp ={
                code:obj[i].code,
                type:obj[i].type
            }
            params.push(tmp);
        }
        return this.post("/select/initSelect",{params:params}).then(res=>{
            for(let i=0;i<obj.length;i++){
                let tmp = res.data[obj[i].type+"_"+obj[i].code];
                for(let j=0;j<tmp.length;j++){
                    obj[i].model.push(tmp[j]);
                }
            }
        });
    }

    this.closePopWin=function(){
        let i = document.getElementById("popwin_i_");
        i.click();
    }
    //弹窗
    this.openPopWin=function(title,src,width,height,onClose) {
        let div1, div2, div3, span, div4, iframe;
        div1 = document.getElementById("popwin_div1_");
        if (!div1) {
            div1 = document.createElement("div");
            div1.setAttribute("class", "popWin");
            div1.setAttribute("id", "popwin_div1_");
            div2 = document.createElement("div");
            div2.setAttribute("class", "popWinBox");
            div2.setAttribute("style", "width: " + width + "px;height: " + height + "px;");
            div2.setAttribute("id", "popwin_div2_");
            div3 = document.createElement("div");
            div3.setAttribute("class", "popWinHeader");
            div3.setAttribute("id", "popwin_div3_");
            span = document.createElement("span");
            span.innerText = title;
            span.setAttribute("id", "popwin_span_");
            i = document.createElement("i");
            i.setAttribute("class", "el-icon-close");
            i.setAttribute("id", "popwin_i_");
            div4 = document.createElement("div");
            div4.setAttribute("class", "popWinContent");
            div4.setAttribute("id", "popwin_div4_");
            iframe = document.createElement("iframe");
            iframe.setAttribute("src", src);
            iframe.setAttribute("style", "width: 100%;height: 100%;border: 0");
            iframe.setAttribute("id", "popwin_iframe_");

            div3.appendChild(span);
            div3.appendChild(i);
            div4.appendChild(iframe);
            div2.appendChild(div3);
            div2.appendChild(div4);
            div1.appendChild(div2);

            document.body.appendChild(div1);
        } else {
            div2 = document.getElementById("popwin_div2_");
            span = document.getElementById("popwin_span_");
            i = document.getElementById("popwin_i_");
            iframe = document.getElementById("popwin_iframe_");

            div2.setAttribute("style", "width: " + width + "px;height: " + height + "px;");
            span.innerText = title;
            iframe.setAttribute("src", src);
        }

        i.addEventListener("click", function () {
            div2.setAttribute("style", "margin-top:-30px;width: " + width + "px;height: " + height + "px;");
            setTimeout(function () {
                div1.setAttribute("style", "visibility:hidden;opacity:0");
                span.innerText = "";
                iframe.setAttribute("src", "");
            }, 400);
            if (typeof onClose === 'function') {
                onClose();
            }
        });

        div1.setAttribute("style", "visibility:visible;opacity:1");
        setTimeout(function () {
            div2.setAttribute("style", "margin-top:0px;width: " + width + "px;height: " + height + "px;");
        }, 1);
    }

    this.getParams=function(name){// 获取url指定的参数
        let str = location.search.match(name+"=[^&]+");
        if(str&&str.length>0){
            return str[0].split("=")[1];
        }else{
            return "";
        }
    }
}
var utils = new utils_();
