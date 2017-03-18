package servletAll.framework.common.filter;

import java.lang.reflect.Field;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.util.PropertyFilter;

/**  
 * @类名 JsonPropertyFilter.java  
 * @描述: 
 *     TODO
 * @作者 田斌  
 * @创建时间 2013-4-15 下午17:23:02  
 * @版本 v1.0  
 */
public class JsonPropertyFilter implements PropertyFilter{

	
	
	/** 
     * 过滤的属性名称 
     */  
    private String[] filterProperties;  
    /** 
     * 是否忽略集合 
     */  
    private boolean ignoreColl = false; 
    /** 
     * 空参构造方法
     * 默认不忽略集合 
     */  
	public JsonPropertyFilter(){
		//empty
	}
	/** 
     * 构造方法 
     * @param fields 忽略属性名称数组 
     */  
    public JsonPropertyFilter(String[] filterProperties) {  
        this.filterProperties = filterProperties;   
    }  
    /** 
     * 构造方法 
     * @param ignoreColl    是否忽略集合 
     * @param filterProperties    忽略属性名称数组 
     */  
    public JsonPropertyFilter(boolean ignoreColl, String[] filterProperties) {  
        this.filterProperties = filterProperties;  
        this.ignoreColl = ignoreColl;   
    }  
    /** 
     * 构造方法 
     * @param ignoreColl 是否忽略集合 
     */  
    public JsonPropertyFilter(boolean ignoreColl) {  
        this.ignoreColl = ignoreColl;   
    }   
	
	public boolean apply(Object source, String name, Object value) {
		//获取全部属性的名称
		Field declaredField = null; 
		Log log = LogFactory.getLog(this.getClass());  
		//过来掉值为空或为空串的属性
		if(value==null ||value.toString().trim().equals("")) 
			 return true;
		try {
			//declaredField = source.getClass().getDeclaredField(name);
		} catch (SecurityException e) {
			e.printStackTrace();
		}  
	   // 忽略设定的属性  
        if(filterProperties != null && filterProperties.length > 0) {  
            if(juge(filterProperties,name)) {    
                return true;    
            } else {    
                return false;    
            }   
         }  
		// 忽略集合  
        /* if (declaredField != null) {  
             if(ignoreColl) {  
                 if(declaredField.getType() == Collection.class  
                         || declaredField.getType() == Set.class||declaredField.getType() == List.class) {  
                     return true;  
                 } 
             }  
         } */ 
          
		return false;  
	    
	}

	/**  
     * 过滤忽略的属性  
     * @param s  
     * @param s2  
     */  
     public boolean juge(final String[] s,final String s2){     
             boolean b = false;     
               for(String sl : s){     
                  if(s2.equals(sl)){     
                       b=true;     
                   }     
                }     
              return b;     
           }      

	public String[] getFilterProperties() {
		return filterProperties;
	}
 
	public void setFilterProperties(String[] filterProperties) {
		this.filterProperties = filterProperties;
	} 
	public boolean isIgnoreColl() {
		return ignoreColl;
	}
 
	public void setIgnoreColl(boolean ignoreColl) {
		this.ignoreColl = ignoreColl;
	}
 
}
