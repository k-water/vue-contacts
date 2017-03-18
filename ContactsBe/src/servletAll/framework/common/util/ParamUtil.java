package servletAll.framework.common.util;  

import java.util.ResourceBundle;

/**  
 * @类名 ParamUtil.java  
 * @描述: 
 *     配置文件读取工具类
 * @作者 梅光弦
 * @创建时间 2013-4-19 上午09:27:27  
 * @版本 v1.0  
 */
public class ParamUtil {
	public static ResourceBundle rs=null;
	
	static{
    	rs=ResourceBundle.getBundle("config");
    }
	 
	/**
	 * 上传文件路径
	 * @return
	 */
	public static String getUploadFile(){
		return rs.getString("uploadFile");
	}
	
		
	/**
	 * 获取communication主机IP
	 * @return
	 */
	public static String getCommunicationHostIp(){
		return rs.getString("communication.host.ip");
	}
	
	
	/**
	 * 获取communication主机端口号
	 * @return
	 */
	public static Integer getCommunicationHostPort(){
		return new Integer(rs.getString("communication.host.port"));
	}
	
	/**
	 * 获取communication 超时时间
	 * @return
	 */
	public static Integer getCommunicationTimeout(){
		return new Integer(rs.getString("communication.timeout"));
	}
	
	/**
	 * jsp路径
	 */
	
	public static String getJspPath(){
		return rs.getString("jsp.path");
	}
	
	
	/**
	 * story路径
	 */
	
	public static String getStoryPath(){
		return rs.getString("story.path");
	}
	/**
	 * 获取数据库字段对应的label或者月份
	 * @return
	 */
	public static String getChartLabels(){
		return rs.getString("chart.labels");
	}
	
	/**
	 * main方法
	 * @param args
	 */
	public static void main(String args[]){
		System.out.println(ParamUtil.getCommunicationHostIp());
	}
}
 