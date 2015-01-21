package org.wahlzeit.extension.domain;

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

/**
 * This class is part of the PancakeManager and PancakeFactory collaborations. See collaborating methods for more information.
 * 
 * @author qwert
 *
 */
public class PancakeManager extends ObjectManager {
	
	/**
	*
	*/
	protected static final PancakeManager instance = new PancakeManager();
	
	/**
	* In-memory cache for pancakes
	*/
	protected Map<Integer, Pancake> pancakeCache = new HashMap<Integer, Pancake>();
	
	/**
	 * meta-information 
	 */
	protected int currentId = 0;
	
	/**
	 * Public singleton access method
	 * 
	 * @methodtype get
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	public static final PancakeManager getInstance() {
		return instance;
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected PancakeManager() {
		//do nothing
	}
	
	/**
	 * @methodtype set
	 * @methodproperty primitive
	 * @pre currentId > 0
	 * @post 
	 */
	public void setCurrentId(int currentId) {
		if (currentId < 0) 
			throw new IllegalArgumentException("ID");
	
		this.currentId = currentId;
	}
	
	/**
	 * 
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre id != null (always true due to initiation at beginning)
	 * @post
	 */
	public int getCurrentId() {
		return currentId;
	}
	
	
	/**
	 * @methodtype boolean-query
	 * @methodproperties composed
	 * @pre
	 * @post
	 */
	public final boolean hasPancake(Integer id) {
		return pancakeCache.containsKey(id);
	}
	
	/**
	 * @methodtype boolean-query
	 * @methodproperties primitive
	 * @pre pancakeCache initiated
	 * @post
	 */
	protected boolean doHasPancake(Integer id) {
		return this.pancakeCache.containsKey(id);
	}
	
	/**
	 * @collaboration manager, PancakePhoto
	 * @methodtype get
	 * @methodproperty composed
	 * @pre id != null, pancake exists
	 * @post
	 */
	public final Pancake getPancakeFromId(Integer id) {
		if (id == null) 
			throw new IllegalArgumentException("ID");
		
		Pancake result = this.pancakeCache.get(id);
		if (result == null) {
			try {
				PreparedStatement stmt = getReadingStatement("SELECT * FROM pancakes WHERE id = ?");
				result = (Pancake) readObject(stmt, id.intValue());
			} catch (SQLException sex) {
				SysLog.logThrowable(sex);
			}
			if (result != null) {
				addPancake(result);
			}
		}
		
		return result;
	}
	
	/**
	 * @collaboration manager, factory
	 * @methodtype factory
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	public Pancake createPancake() throws Exception {
		this.currentId++;
		Integer id = Integer.valueOf(this.currentId);
		Pancake result = PancakeFactory.getInstance().createPancake(id);
		addPancake(result);
		return result;
	}
	
	/**
	 * @methodtype command
	 * @methodproperty composed
	 * @pre is a new pancake
	 * @post
	 */
	public void addPancake(Pancake pancake){
		try {
			assertIsNewPancake(pancake.getId());
			pancakeCache.put(pancake.getId(), pancake);
			PreparedStatement stmt = getReadingStatement("INSERT INTO pancakes(id) VALUES(?)");
			createObject(pancake, stmt, pancake.getId());
			savePancake(pancake);
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		} catch (IllegalArgumentException e) {
			SysLog.logThrowable(e);
			throw new AssertionError("ID");
		}
	}
	
	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * @pre id != null (checked in hasPancake(id))
	 * @post
	 */
	private void assertIsNewPancake(Integer id) {
		if (hasPancake(id)) 
			throw new IllegalArgumentException("ID");
	}
	
	/**
	 * @collaboration serializer, pancake
	 * @methodtype command
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Collection<Pancake> loadPancakes() {
		try {
			ArrayList<Pancake> list = new ArrayList<Pancake>();
			PreparedStatement stmt = getReadingStatement("SELECT * FROM pancakes");
			readObjects(list, stmt);
			
			for (Iterator<Pancake> i = list.iterator(); i.hasNext(); ) {
				Pancake pancake = i.next();
				if (!doHasPancake(pancake.getId())) 
					addPancake(pancake);
				else 
					SysLog.logSysInfo("pancake", pancake.getId().toString(), "pancake had already been loaded");
			}
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
		SysLog.logSysInfo("loaded all pancakes");
		
		return pancakeCache.values();
	}
	
	/**
	 * @collaboration serializer, pancake
	 * @methodtype command
	 * @methodproperty
	 * @pre pancake != null
	 * @post
	 */
	public void savePancake(Pancake pancake) {
		if (pancake == null)
			throw new IllegalArgumentException();
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM pancakes WHERE id = ?");
			updateObject(pancake, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
	
	/**
	 * @collaboration serializer, pancake
	 * @methodtype command
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void savePancakes() {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM pancakes WHERE id = ?");
			updateObjects(this.pancakeCache.values(), stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
	
	/**
	 * @collaboration serializer, pancake
	 * @methodtype factory
	 * @methodproperty hook
	 * @pre
	 * @post
	 */
	@Override
	protected Persistent createObject(ResultSet rset) throws SQLException {
		return PancakeFactory.getInstance().createPancake(rset);
	}
}
