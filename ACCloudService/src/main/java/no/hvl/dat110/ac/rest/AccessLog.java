package no.hvl.dat110.ac.rest;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class AccessLog {
	
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// TODO: add an access entry for the message and return assigned id
	public int add(String message) {
		
		int id = cid.getAndIncrement();
		
		AccessEntry a = new AccessEntry(id, message);
		log.put(id, a);

		return id;
	}
		
	// TODO: retrieve a specific access entry 
	public AccessEntry get(int id) {
		AccessEntry entry = log.get(id);
		return entry;
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		cid.set(0);
		log.clear();
	}
	
	// TODO: JSON representation of the access log
	public String toJson () {
    	
		String json = null;
		json = "[";
		int value = cid.get();
		for (int i = 0; i < value; i++) {
			json +="{";
			json += "\"id\": "+i+",";
			json += "\"message\": \"" + log.get(i).getMessage() + "\"";
			json +="}";
			if (i != value - 1) {
				json +=",";
			}
		}
		json += "]";
    	
    	return json;
    }
}
