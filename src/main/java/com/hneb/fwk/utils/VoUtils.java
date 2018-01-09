package com.hneb.fwk.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

public class VoUtils {

	public static Logger logger = Logger.getLogger(VoUtils.class);
	
	public static void touchOnCreate(Object obj,Date date,String userId){
		try {
			PropertyUtils.setProperty(obj, "tCrtTm", date);
			PropertyUtils.setProperty(obj, "tUpdTm", date);
			PropertyUtils.setProperty(obj, "cCrtId", userId);
			PropertyUtils.setProperty(obj, "cUpdId", userId);
		} catch (IllegalAccessException e) {
			logger.warn("设置对象"+obj+"的创建人/时间，修改人/时间出错!");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.warn("设置对象"+obj+"的创建人/时间，修改人/时间出错!");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.warn("设置对象"+obj+"的创建人/时间，修改人/时间出错!");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public static void touchOnUpdate(Object obj,Date date,String userId){
		try {
			PropertyUtils.setProperty(obj, "tUpdTm", date);
			PropertyUtils.setProperty(obj, "cUpdId", userId);
		} catch (IllegalAccessException e) {
			logger.warn("设置对象"+obj+"的创建人/时间，修改人/时间出错1!");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.warn("设置对象"+obj+"的创建人/时间，修改人/时间出错2!");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.warn("设置对象"+obj+"的创建人/时间，修改人/时间出错3!");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	
	public static void touch(Object obj,Date date,String userId){
		try {
			if(PropertyUtils.getProperty(obj, "tCrtTm")==null||PropertyUtils.getProperty(obj, "tUpdTm")==null){
				VoUtils.touchOnCreate(obj, date, userId);
			}else{
				VoUtils.touchOnUpdate(obj, date, userId);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
