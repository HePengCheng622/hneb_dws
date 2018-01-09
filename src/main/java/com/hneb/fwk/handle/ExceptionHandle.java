package com.hneb.fwk.handle;

import com.hneb.ResultEnum;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.exception.HnebException;
import com.hneb.fwk.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class ExceptionHandle {

	private final static Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Result handle(Exception e){
		if(e instanceof HnebException){
			HnebException ex = (HnebException)e;
			return ResultUtils.error(ex.getCode(), ex.getMessage());
		}else{
			log.error("【系统异常】",e);
			return ResultUtils.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg()+"【"+e.getMessage()+"】");
		}
	}
}
