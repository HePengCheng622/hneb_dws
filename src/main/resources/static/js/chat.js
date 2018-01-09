/**
 * Created by Administrator on 2017/10/23 0023.
 */
// 导入其他网络资源
document.write('<script src="http://app.cloopen.com/im50/ytx-web-im-min-new.js"></script>');
// document.write('<script src="https://cdn.bootcss.com/vue-resource/1.3.4/vue-resource.min.js"></script>');
function chat(){
    /**
     * 请求后台的Controller映射路径
     * 携带的请求参数
     * @param data
     */
    this.initChat=function(data,onMsgReceiveMsg){
        if(data.params==null){
            data.params={};
        }
        // 去后台获取签名
        utils.post("http://"+window.location.host+data.url, data.params).then(function(res){
            //初始化SDK
            var resp = RL_YTX.init("aaf98f8950e15fc10150f478cd742ca8");
            if(170002== resp.code){
                //缺少必要参数，详情见msg参数
                //用户逻辑处理
            }else if(174001 == resp.code){
                //不支持HTML5，关闭页面
                //用户逻辑处理
            }else if(200 == resp.code){
                console.log("初始化成功！");
                //账号登录参数设置
                var loginBuilder = new RL_YTX.LoginBuilder();
                loginBuilder.setType(1);//登录类型 1账号登录，2voip账号密码登录
                loginBuilder.setUserName(res.data.userId);//设置用户名
                loginBuilder.setPwd();//type值为1时，密码可以不赋值
                loginBuilder.setSig(res.data.sig);//设置sig
                loginBuilder.setTimestamp(res.data.timestamp);//设置时间戳
                //执行用户登录
                RL_YTX.login(loginBuilder, function(obj){
                    //登录成功回调
                    RL_YTX.onMsgReceiveListener(function(obj){
                        console.log("收到消息！");
                        onMsgReceiveMsg(obj);
                    });
                    RL_YTX.onConnectStateChangeLisenter(function(obj){
                        console.log(obj.code)
                    });
                }, function(obj){
                    //登录失败方法回调
                    console.log("登录失败！");
                })
            }
        },function(res){
           // 初始化失败
            console.log("初始化失败！");
        });
    }

    /**
     * 发送消息
     * @param orderId 订单id
     * @param type 发送信息类型
     * @param msg 发送的内容
     * @param otherId 接收者的id
     * @param fnsuccess 发送成功的回调方法
     * @param fnfail 发送失败的回调方法
     */
    this.sendMsg_=function(otherId,orderId,type,msg,fnsuccess,fnfail){
        console.log("发送消息给："+otherId)
        //新建消息体对象
        var obj = new RL_YTX.MsgBuilder();
        //设置自定义消息id,把时间当做id
        obj.setId(new Date().getTime());
        //设置发送的文本内容
        obj.setText(msg);
        //设置发送的消息类型1:文本消息 4:图片消息 6:压缩文件 7:非压缩文件
        obj.setType(1);
        //设置接收者
        obj.setReceiver(otherId);
        // 设置自定义的数据
        obj.setDomain(JSON.stringify({orderId:orderId,msgType:type}));
        RL_YTX.sendMsg(obj,fnsuccess, fnfail, function(sended, total){
            //发送图片或附件时的进度条
            //如果发送文本消息，可以不传该参数
        });
    }

    /**
     * 发送文本消息
     */
    this.sendTextMsg=function(otherId,orderId,msg,fnsuccess,fnfail){
        this.sendMsg_(otherId,orderId,'1',msg,fnsuccess,fnfail)
    }

    /**
     * 发送图片消息
     */
    this.sendImgMsg=function(otherId,orderId,msg,fnsuccess,fnfail){
        this.sendMsg_(otherId,orderId,'4',msg,fnsuccess,fnfail)
    }

    /**
     * 发送音频消息
     */
    this.sendAudioMsg=function(otherId,orderId,msg,fnsuccess,fnfail){
        this.sendMsg_(otherId,orderId,'2',msg,fnsuccess,fnfail)
    }

    /**
     * 发送报告消息
     */
    this.sendReportMsg=function(otherId,orderId,msg,fnsuccess,fnfail){
        this.sendMsg_(otherId,orderId,'6',msg,fnsuccess,fnfail)
    }
}
var chat = new chat();
