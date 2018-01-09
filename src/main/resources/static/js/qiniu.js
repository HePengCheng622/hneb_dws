/**
 * Created by Administrator on 2017/10/24 0024.
 */
/**
 * Created by Administrator on 2017/3/31.
 */
// document.write('<script src="https://cdn.bootcss.com/vue-resource/1.3.4/vue-resource.min.js"></script>');
function QiNiu() {
    // 获取token
    this.getToken_=function() {
        return new Promise(function (resolve, reject) {
            utils.post('/chat/getQiniuToken').then(function (res) {
                resolve(res.body.data);
            }, function (res) {
                reject(res);
            });
        });

    }
    // 上传文件
    this.uploadFile=function(file,token, process) {
        var formData = new FormData();
        formData.append('file', file);
        formData.append('token', token);
        var that= this;
        return new Promise(function (resolve, reject) {
            utils.post('http://up-z0.qiniu.com', formData, {
                progress: function (event) {
                    process(event);
                }
            }).then(function (res) {
                    var mrk = true;
                    var fileNme = "";//文件名
                    var result = res.body;
                    if (result.hash && result.key) {
                        fileNme = result.key;
                        mrk = true;
                    } else {
                        mrk = false;
                    }
                    resolve({mrk: mrk, fileNme: fileNme});
                }, function (res) {
                    reject(res);
                }
            )
        });
    }

}

var qiniu =new QiNiu();
