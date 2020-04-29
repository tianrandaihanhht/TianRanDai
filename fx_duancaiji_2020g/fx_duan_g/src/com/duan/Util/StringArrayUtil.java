package com.duan.Util;

  
  
import java.util.HashMap;   
import java.util.HashSet;   
import java.util.LinkedList;   
import java.util.Map;   
import java.util.Map.Entry;   
import java.util.Set;   
  
public class StringArrayUtil {   
	public static void main(String[] args) {
		String[] s1 = {"abc","123","der","der","56"};
		String[] s2 = {"abc","222"};
		/**************找交集*****************/
		String[] intersect12 = intersect(s1,s2);
		System.out.println("交集是：");
		for(String str : intersect12)
			System.out.print(str);
		System.out.println("");
		/****************下面是找并集********************/
		String[] union12 = union(s1,s2);
		System.out.println("并集是：");
		for(String str : union12)
			System.out.print(str);
		System.out.println("");
		/****************下面是找差集********************/
		String[] minus12 = minus(s1,s2);
		System.out.println("差集是：");
		for(String str :minus12)
			System.out.print(str);
		System.out.println("");
		
	}
	/*******************找交集******************/
	public static String[] intersect(String[] s1,String[] s2){
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		LinkedList<String> list = new LinkedList<String>();
		
		for(String str:s1){
			if(!map.containsKey(str)){
				map.put(str, Boolean.TRUE);//没有找到 就放进去,设为true
			}
		}
		for(String str:s2){
			if(map.containsKey(str)){
				map.put(str, Boolean.FALSE);//找到的话就设置为false
			}	
		}
		for(Entry<String,Boolean> e: map.entrySet()){
			if(e.getValue().equals(Boolean.FALSE)){
				list.add(e.getKey());
			}
		}
		String[] res = {};
		return list.toArray(res);	
	}
	/*************找并集,利用HashSet不重复的特性，也可以修改交集****************/
	public static String[] union(String[] s1,String[] s2){
		Set<String> set = new HashSet<String>();
		for(String str: s1){
			set.add(str);
		}
		for(String str: s2){
			set.add(str);
		}
		String[] res = {};
		return set.toArray(res);	
	}
	/*************找差集****************/
	public static String[] minus(String[] s1,String[] s2){
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		LinkedList<String> list = new LinkedList<String>();
		for(String str:s1){
			if(!map.containsKey(str)){
				map.put(str,Boolean.TRUE);
			}
		}
		for(String str:s2){
			if(map.containsKey(str)){
				map.put(str,Boolean.FALSE);
			}
		}
		for(Entry<String,Boolean> e:map.entrySet()){
			if(e.getValue().equals(Boolean.TRUE))
				list.add(e.getKey());
		}
		String[] res = {};
		return list.toArray(res);
	}
 
}