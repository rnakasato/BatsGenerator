package com.rafank.factory.impl;

import java.util.HashMap;
import java.util.Map;

import com.rafank.core.IDAO;
import com.rafank.core.dao.impl.BatDAO;
import com.rafank.core.dao.impl.FolderDAO;
import com.rafank.domain.impl.Bat;
import com.rafank.domain.impl.Folder;

public class FactoryDAO {
		
	private static Map<String,IDAO> daoMap;
	
	private static void initMap(){
		if(daoMap == null){ 
			daoMap = new HashMap<>();
			daoMap.put(Folder.class.getName(), new FolderDAO());
			daoMap.put(Bat.class.getName(), new BatDAO());
//			daoMap.put(StoreCategory.class.getName(), new StoreCategoryDAO());
//			daoMap.put(Subcategory.class.getName(), new SubcategoryDAO());
//			
//			// TEMPORÁRIO ATÉ ADIÇÃO DOS FILTROS
//			daoMap.put(ProductFilter.class.getName(), new ProductDAO());
		}
	}
	
	/**	 
	 * @param className
	 * @return retorna instância de DAO para o Objeto
	 */
	public static IDAO build(String className) throws ClassNotFoundException{
		initMap();
		IDAO dao = daoMap.get(className);
		if(dao == null){
			throw new ClassNotFoundException();
		}
		return dao;
	}
}
