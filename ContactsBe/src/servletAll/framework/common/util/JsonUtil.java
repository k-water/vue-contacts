/**
 * 
 */
package servletAll.framework.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import servletAll.framework.common.filter.DateJsonValueProcessor;
import servletAll.framework.common.filter.JsonPropertyFilter;
import servletAll.framework.ide.po.ChartBar;
import servletAll.framework.ide.po.ChartPie;
import servletAll.framework.ide.po.Datasets;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * @类名 JsonUtil
 * @描述:
     Json工具类
 * @创建人 meigx
 * @创建时间 2014年8月27日 下午2:27:41
 * @版本 v1.0
 */
public class JsonUtil {
	
	/** 
     * JSON 时间解析器具 
     * @param datePattern 
     * @return 
     */  
    public static JsonConfig configJson(String datePattern){     
        JsonConfig jsonConfig = new JsonConfig();     
        jsonConfig.setExcludes(new String[]{""});     
        jsonConfig.setIgnoreDefaultExcludes(false);     
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);     
        jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor(datePattern));    
        return jsonConfig;     
    } 
    
    /**  
     * 从一个Object转换为JSON格式的数据 
     * @param message,Obj
     * @return  JSONObject
     */  
	public static JSONObject generate(Object obj,String... message) {  
       Map <String, Object> map = new HashMap <String, Object>(); 
 		map.put("obj",obj);   
 		map.put("msg",message);  
 		map.put("flag", true); 
 		return JSONObject.fromObject(map); 
 	} 
	
	/**  
     * 从一个Object转换为JSON格式的数据 
     * @param message,Obj
     * @return  JSONObject
     */  
	public static JSONObject generateMsg(String... message) {  
       Map <String, Object> map = new HashMap <String, Object>(); 
 		map.put("msg",message);  
 		map.put("flag", true); 
 		return JSONObject.fromObject(map); 
 	} 
	
	/**  
     * 从一个Object转换为JSON格式的数据 ,不进行封装
     * @param message,Obj
     * @return  JSONObject
     */
	public static JSONObject generateObj(Object obj) {   
 		return JSONObject.fromObject(obj); 
 	} 
	
	/**  
     * 从一个flag转换为JSON格式的数据 
     * @param flag
     * @return  JSONObject
     */  
	public static JSONObject generate(Boolean flag) { 
	    Map <String, Object> map = new HashMap <String, Object>();  
	 	map.put("flag", flag); 
	 	return JSONObject.fromObject(map); 
	} 
	
	/**  
     * 从一个flag转换为JSON格式的数据 
     * @param flag
     * @return  JSONObject
     */  
	public static JSONArray generateTree(List list) {  
	 	return JSONArray.fromObject(list); 
	} 
	
	/**  
     * easyUI格式封装
     * @param message,Obj
     * @return  JSONObject
     */  
	public static JSONObject generateEasyUI(List list,Integer num) {  
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(""));
		jsonConfig.setJsonPropertyFilter(new JsonPropertyFilter());
        Map <String, Object> map = new HashMap <String, Object>(); 
 		map.put("rows",list);   
 		map.put("total",num);
 		return JSONObject.fromObject(map,jsonConfig); 
 	}
	
	/**
	 * map转换成JSON格式
	 * @param map
	 * @return JSONObject
	 */
	public static JSONObject generateMap(Map<String, Object> map){
		return JSONObject.fromObject(map);
	}
	/**
	 *  处理饼图、环形图、及地极图json的方法
	 * @param obj
	 * @return 
	 * @return 
	 * @throws Exception
	 * @throws IllegalAccessException
	 */
	public static JSONArray generateObjToChartPie(Object obj) throws Exception, IllegalAccessException{
		List<ChartPie> objList = new ArrayList<ChartPie>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field o : fields) {
		o.setAccessible(true);
		ChartPie pie = new ChartPie();
		Object value = o.get(obj);
		pie.setLabel(o.getName());
		if (value.getClass().isArray()) {
		Object[] arr = (Object[]) value; 
//		System.out.println("，变量值等于：" + Arrays.toString(arr));
		for (Object a : arr) {
		//System.out.println(a.getClass().getName());
		}
		} else {
			pie.setValue(value.toString());
			
		}
		pie.setColor(getRandColorCode());
		pie.setHighlight(getRandColorCode());
		o.setAccessible(false);
		objList.add(pie);
		}
		return JSONArray.fromObject(objList);
		
	};
	/**
	 * 将数据封装成图表中的柱状图、雷达图、曲线图所需的json格式
	 * @param obj
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public static JSONObject generateObjListToChartBar(List objList) throws IllegalArgumentException, Exception{
		
		List<String> labels = new ArrayList<String>(); // 用来存取所有的label
		List<Map<String, String>> mList = new ArrayList<Map<String, String>>(); // 用来存取所有的map
		String lab = ParamUtil.getChartLabels(); // 获取label对应字段
		List<String> label = new ArrayList<String>();//用来存取所有对象
		List<Datasets> sets = new ArrayList<Datasets>();
		
		for (int i=0;i<objList.size();i++) {
			if(i==0){
				Field[] fields = objList.get(i).getClass().getDeclaredFields();
				for (Field o : fields) {
					Map<String, String> map = new HashMap<String, String>();
					o.setAccessible(true);
					Object value = o.get(objList.get(i));
					System.out.println(o.getName());
					if(o.getName().contains(lab)){
						if (value.getClass().isArray()) {
							Object[] arr = (Object[]) value; 
	//						System.out.println("，变量值等于：" + Arrays.toString(arr));
							for (Object a : arr) {
							//System.out.println(a.getClass().getName());
							}
							} else {
								labels.add(value.toString());
							}
					}else{
						label.add(o.getName());
						map.put(o.getName(), value.toString());
						mList.add(map);
						
					
					}
				o.setAccessible(false);
				}
			}else{
				Field[] fields = objList.get(i).getClass().getDeclaredFields();
				for (Field o : fields) {
					Map<String, String> map = new HashMap<String, String>();
					o.setAccessible(true);
					Object value = o.get(objList.get(i));
					if(o.getName().contains(lab)){
						if (value.getClass().isArray()) {
							Object[] arr = (Object[]) value; 
	//						System.out.println("，变量值等于：" + Arrays.toString(arr));
							for (Object a : arr) {
							//System.out.println(a.getClass().getName());
							}
							} else {
								labels.add(value.toString());
							}
					}else{
						map.put(o.getName(), value.toString());
						mList.add(map);
						
					
					}
				o.setAccessible(false);
			    }
			}
		}
		for (String str : label) {
			List<Integer> d = new ArrayList<Integer>();
			for (Map ma : mList) {
				if(ma.containsKey(str)){
					System.out.println(ma.values().toString());
					d.add(Integer.parseInt(ma.get(str).toString()));
				}
			}
			Datasets datasets = new Datasets();
			datasets.setData(d);
			datasets.setFillColor(getRandColorCode());
			datasets.setPointColor(getRandColorCode());
			datasets.setPointHighlightFill(getRandColorCode());
			datasets.setPointHighlightStroke(getRandColorCode());
			datasets.setPointStrokeColor(getRandColorCode());
			datasets.setStrokeColor(getRandColorCode());
			sets.add(datasets);
		}
		ChartBar bar = new ChartBar();
		bar.setDatasets(sets);
		bar.setLabels(labels);
		return JSONObject.fromObject(bar);
		
	};
		/**
		 * 获取随机颜色
		 * @return
		 */
	public static String getRandColorCode(){
		String r,g,b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();
		r = r.length()==1 ? "0" + r : r ;
		g = g.length()==1 ? "0" + g : g ;
		b = b.length()==1 ? "0" + b : b ;
		return "#"+r+g+b;
		}
}
