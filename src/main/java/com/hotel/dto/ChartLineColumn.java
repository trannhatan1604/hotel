package com.hotel.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartLineColumn {
	private Map<Object,Object> map = null;
	private List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
	private List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
	
	public List<List<Map<Object, Object>>> getList() {
		return list;
	}
	
	public void addPoint(String label, double value) {
		map = new HashMap<Object,Object>();
		map.put("x", label); map.put("y", value);
		dataPoints1.add(map);
	}
	
	public void addDataPoint() {
		list.add(dataPoints1);
	}
}
