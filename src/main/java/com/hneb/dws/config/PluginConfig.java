package com.hneb.dws.config;

public class PluginConfig {

	//容联云通讯
    public static class YTX{
		public static String APPID="aaf98f8950e15fc10150f478cd742ca8";
		public static String APPTOKEN="261b49dc6433f992e24e662dca6fd26f";
	}
	
	//七牛云存储相关变量
	public static class QINIU{
		public static String AC="tEj9UlB4qD37uGKs2-zZpnX3I2ntdAPnIKsbfw-d";
		public static String SK="_PO7iS3ock8MDyJKOAWDpeaQ8mJErH1bO7rwbhx2";

		public static String DOMAIN_CHAT="http://chatdata.qiniu.hneb.net/";
		public static String DOMAIN_SYS="http://sysdata.qiniu.hneb.net/";
		public static String DOMAIN_USER="http://userdata.qiniu.hneb.net/";
		
		public static String BUCKET_USER="userdata";//用户数据空间
		public static String BUCKET_CHAT="chatdata";//聊天数据空间
		public static String BUCKET_SYS="sysdata";//系统数据空间
	}
}