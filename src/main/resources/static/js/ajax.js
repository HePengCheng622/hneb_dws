/**
 * Created by Administrator on 2017/12/5 0005.
 */
var Ajax = {
    get: function (url) {
        return new Promise(function (resolve, reject) {
            var obj = new XMLHttpRequest();  // XMLHttpRequest对象
            obj.open('GET', url, true);
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
    post: function (url, data) {
        return new Promise(function (resolve, reject) {
            var obj = new XMLHttpRequest();  // XMLHttpRequest对象
            obj.open("POST", url, true);
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
            obj.send(JSON.stringify(data));// 发送post数据
        });
    }
}
