package servletAll.framework.common.filter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**  
 * @类名 DateJsonValueProcessor.java  
 * @描述: 
 *     TODO
 * @作者 田斌  
 * @创建时间 2013-4-15 下午17:23:02  
 * @版本 v1.0  
 */
public class DateJsonValueProcessor implements JsonValueProcessor  {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";  
    private DateFormat  dateFormat;  
    
    /** 
     * 构造方法. 
     * @param datePattern 日期格式 
     */  
    public DateJsonValueProcessor(String datePattern){ 
    	if("".equals(datePattern)||datePattern==null){
    		dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN); 
    	}else{
    		 dateFormat = new SimpleDateFormat(datePattern); 
    	} 
    }  
    
	public Object processArrayValue(Object value, JsonConfig jsonConfig) { 
		 return process(value);
	}

	public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
		return process(value);  
	}

    private Object process(Object value) {  
        return dateFormat.format((Date) value);  
    }  

}
