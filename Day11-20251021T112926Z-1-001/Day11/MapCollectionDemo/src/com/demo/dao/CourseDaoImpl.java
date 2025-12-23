package com.demo.dao;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CourseDaoImpl implements CourseDao{
	static Map<String,Integer> hm;
	static {
		hm=new HashMap<>();
		hm.put("DAC", 240);
		hm.put("DBDA",65);
		hm.put("DTISS",60);
		}
	@Override
	public boolean save(String cname, int capacity) {
		if(hm.containsKey(cname))//key exist or not=if duplicate then not add
			return false;
		hm.put(cname, capacity);
		return true;
	}
	@Override
	public Map<String, Integer> findAll() {
		return hm;
	}
	@Override
	public int findByName(String cname) {
		if(hm.containsKey(cname))//if keu exist
		     return hm.get(cname);
		else
			return -1;
	}
	@Override
	public boolean updateByName(String cname, Integer newcap) {
		if(hm.containsKey(cname)) {  
		   hm.put(cname, newcap); //value update
		   return true;
		}
		return false;
	}
	@Override
	public Set<String> findByCapacity(int capacity) {
		Set<String> keys=hm.keySet();   //keys lega
		Set<String> cnames=new HashSet(); //new bnaya hashset or isme dalega
		for(String s:keys) {
			//if capacity is greater the add in the set
			if(hm.get(s)>capacity) {
				cnames.add(s);
			}
		}
		if(cnames.size()>0) {
			return cnames;
		}
		return null;
	}  //Get keys → create result set → check value → add if greater → return result."
	
	@Override
	public boolean updateCourseName(String oname, String nname) {
		if(hm.containsKey(oname)) {
			int c=hm.remove(oname);  //value put in c
			hm.put(nname, c);  //dac 500==dbda 500
			return true;
		}
		return false;
	}
	@Override
	public boolean removeByName(String cname) {
		Integer val=hm.remove(cname);   //value deta hai integer not string
		return val!=null;
	}
	@Override
	public boolean removeByCapacity(int capacity) {
		int cnt=0;  ///value remove krni h
		Set<String> keys=hm.keySet();  
		Iterator<String> it=keys.iterator();   //step by step go
		while(it.hasNext()) { //Checks if there are more keys left. //goes through each key //hasnext==use for iterate function(koi value hai ki nhi prev)
			String s=it.next(); //key dalega s me //take one key at a tym
			if(hm.get(s)==capacity) { //value
				it.remove();
				cnt++; //Counts how many deletions occurred.
			}
		}
	// "Get keys → use iterator → check value → remove safely → return true if removed."
		return cnt>0;
	}

// 	💡 Easy Way to Remember
// Think: "Create TreeMap → get keys → copy all → return sorted map."
	@Override
	public Map<String, Integer> sortByName() {
		Map<String,Integer> tmap=new TreeMap<>();   
		Set<String> keys=hm.keySet();
		for(String s:keys) {
			tmap.put(s,hm.get(s));  //key se sort here
		}
		return tmap;
		
	}
	@Override
	public Set<Entry<String, Integer>> sorByCapacity() {
		Set<Map.Entry<String, Integer>> es=hm.entrySet(); //entry set ==key value dono dalta hai
		//comparator to sort on values
		Comparator<Map.Entry<String, Integer>> c=(o1,o2)->{
			return o1.getValue()-o2.getValue();
		};
		Set<Map.Entry<String, Integer>> tset=new TreeSet<>(c);//when c to comparator call
		//copy data into TreeSet
		for(Map.Entry<String, Integer> e:es) {
			tset.add(e);
		}
		return tset;


	// Get all entries (key-value pairs) from map → hm.entrySet().
	// Create a comparator that compares entries by their value.
	// Create a TreeSet that uses that comparator.
	// Add all entries from the map into the TreeSet.
	// Return the TreeSet, which now contains entries sorted by value.
	}
}
