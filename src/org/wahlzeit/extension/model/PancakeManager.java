package org.wahlzeit.extension.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

public class PancakeManager extends ObjectManager {

	/**
	*
	*/
	protected static final PancakeManager instance = new PancakeManager();
	
	/**
	* In-memory cache for pancakes
	*/
	protected Map<Integer, Pancake> pancakeCache = new HashMap<Integer, Pancake>();
	protected int currentId = 0;
	
	public void setCurrentId(int currentId) {
		if (currentId < 0) 
			throw new IllegalArgumentException();
	
		this.currentId = currentId;
	}
	
	public int getCurrentId() {
	return currentId;
	}
	
	/**
	*
	*/
	public static final PancakeManager getInstance() {
	return instance;
	}
	
	/**
	* @methodtype constructor
	*/
	protected PancakeManager() {
		//do nothing
	}
	
	public final boolean hasPancake(Integer id) {
		return getPancakeFromId(id) != null;
	}
	
	protected boolean doHasPancake(Integer id) {
		return this.pancakeCache.containsKey(id);
	}
	
	public final Pancake getPancakeFromId(Integer id) {
		if (id == null && id < 0) 
			throw new IllegalArgumentException();
		
		Pancake result = this.doGetPancakeFromId(id);
		if (result == null) {
			try {
				PreparedStatement stmt = getReadingStatement("SELECT * FROM pancakes WHERE id = ?");
				result = (Pancake) readObject(stmt, id.intValue());
			} catch (SQLException sex) {
				SysLog.logThrowable(sex);
			}
			if (result != null) {
				doAddPancake(result);
			}
		}
		
		return result;
	}
	
	protected Pancake doGetPancakeFromId(Integer id) {
		return this.pancakeCache.get(id);
	}
	
	public void addPancake(Pancake pancake){
		assertIsNewPancake(pancake.getId());
		doAddPancake(pancake);
		try {
			PreparedStatement stmt = getReadingStatement("INSERT INTO pancakes(id) VALUES(?)");
			createObject(pancake, stmt, pancake.getId());
			savePancake(pancake);
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
	
	private void assertIsNewPancake(Integer id) {
		if (hasPancake(id)) 
			throw new IllegalStateException("Pancake already exists!");
	}
	
	protected void doAddPancake(Pancake pancake) {
		this.pancakeCache.put(pancake.getId(), pancake);
	}
	
	public Pancake createPancake() throws Exception {
		this.currentId++;
		Integer id = Integer.valueOf(this.currentId);
		Pancake result = PancakeFactory.getInstance().createPancake(id);
		addPancake(result);
		return result;
	}
	
	public void savePancake(Pancake guitar) {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM pancakes WHERE id = ?");
			updateObject(guitar, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
	
	public void savePancakes() {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM pancakes WHERE id = ?");
			updateObjects(this.pancakeCache.values(), stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
	
	public Collection<Pancake> loadPancakes() {
		try {
			ArrayList<Pancake> list = new ArrayList<Pancake>();
			PreparedStatement stmt = getReadingStatement("SELECT * FROM pancakes");
			readObjects(list, stmt);
			
			for (Iterator<Pancake> i = list.iterator(); i.hasNext(); ) {
				Pancake pancake = i.next();
				if (!doHasPancake(pancake.getId())) 
					doAddPancake(pancake);
				else 
					SysLog.logSysInfo("pancake", pancake.getId().toString(), "pancake had already been loaded");
			}
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
		SysLog.logSysInfo("loaded all pancakes");
		
		return pancakeCache.values();
	}
	
	@Override
	protected Persistent createObject(ResultSet rset) throws SQLException {
		return PancakeFactory.getInstance().createPancake(rset);
	}

}