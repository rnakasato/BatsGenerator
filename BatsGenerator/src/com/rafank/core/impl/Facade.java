package com.rafank.core.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rafank.application.Result;
import com.rafank.constant.Operation;
import com.rafank.core.IDAO;
import com.rafank.core.IFacade;
import com.rafank.core.IStrategy;
import com.rafank.domain.DomainEntity;
import com.rafank.factory.impl.FactoryDAO;
import com.rafank.factory.impl.FactoryStrategy;

public class Facade<T extends DomainEntity> implements IFacade<T> {

	private Result<T> result;
	
	public Facade(){				
	
	}
	
	
	@Override
	public Result<T> save(T entity) {
		result = new Result<T>();
		String nmClasse = entity.getClass().getName();	
		String msg = runRules(entity, Operation.SAVE);
			
		if(msg == null){
			
			try {
				IDAO dao = FactoryDAO.build(nmClasse);
				dao.save(entity);
				List<T> entityList = new ArrayList<T>();
				entityList.add((T) entity);
				result.setEntityList(entityList);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				result.setMsg("SAVE ERROR");
				
			}
		}else{
			result.setMsg(msg);
		}
		
		return result;
	}
	
	@Override
	public Result<T> saveList(List<DomainEntity> entity) {
		result = new Result<T>();
		String msg = null;
		String nmClasse = null;
		if(entity != null && !entity.isEmpty()){
			nmClasse = entity.get(0).getClass().getName();	
			msg = runListRules(entity, Operation.SAVE_LIST);
		}else{
			msg = "[ERROR] Empty list!";
		}
			
		if(msg == null){
			
			try {
				IDAO dao = FactoryDAO.build(nmClasse);
				dao.saveList(entity);
				List<T> entityList = new ArrayList<>();
				for(DomainEntity e: entityList){
					entityList.add((T) e);
				}		
				result.setEntityList(entityList);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				result.setMsg("SAVE ERROR");
				
			}
		}else{
			result.setMsg(msg);
		}
		return result;
	}


	@Override
	public Result<T> update(T entity) {
		result = new Result<T>();
		String nmClasse = entity.getClass().getName();	
		
		String msg = runRules(entity, Operation.UPDATE);
	
		if(msg == null){
			
			try {
				IDAO dao = FactoryDAO.build(nmClasse);
				dao.update(entity);
				List<T> entityList = new ArrayList<T>();
				entityList.add(entity);
				result.setEntityList((ArrayList<T>)entityList);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar a alteração!");
				
			}
		}else{
			result.setMsg(msg);
		}
		
		return result;

	}

	@Override
	public Result<T> delete(T entity) {
		result = new Result<T>();
		String classNm = entity.getClass().getName();	
		
		String msg = runRules(entity, Operation.DELETE);
		
		if(msg == null){
			try {
				IDAO dao = FactoryDAO.build(classNm);			
				dao.delete(entity);
				List<DomainEntity> entityList = new ArrayList<DomainEntity>();
				entityList.add(entity);
				result.setEntityList((ArrayList<T>)entityList);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível deletar o item!");
				
			}
		}else{
			result.setMsg(msg);
		}
		
		return result;

	}

	@Override
	public Result<T> find(T entity) {
		result = new Result<T>();
		String classNm = entity.getClass().getName();	
		
		String msg = runRules(entity, Operation.FIND);
		
		
		if(msg == null){
			try {
				IDAO dao = FactoryDAO.build(classNm);			
				result.setEntityList((ArrayList<T>)dao.find(entity));				
			} catch (IOException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar a consulta!");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar a consulta!");
			}
		}else{
			result.setMsg(msg);
			
		}
		
		return result;
	}
	
	@Override
	public Result<T> view(DomainEntity entity) {
		result = new Result<T>();
		result.setEntityList(new ArrayList<T>(1));
		result.getEntityList().add((T)entity);		
		return result;

	}

	
	private String runRules(DomainEntity entity, String operation){
		StringBuilder msg = null;
		List<IStrategy> rules = FactoryStrategy.build(entity, operation);
		if(rules != null){
			msg = new StringBuilder();
			for(IStrategy s: rules){			
				String m = s.process(entity);
				if(m != null){
					msg.append(m);
					break;
				}			
			}	
		}
		String messages = null; 
		if(msg.length()>0){
			messages = msg.toString();
		}
		return messages;
	}
	
	private String runListRules(List<DomainEntity> entityList, String operation){
		StringBuilder msg = null;
		List<IStrategy> rules = FactoryStrategy.buildList(entityList, operation);
		if(rules != null){
			msg = new StringBuilder();
			for(IStrategy s: rules){			
				String m = s.process(entityList);
				if(m != null){
					msg.append(m);
					break;
				}			
			}	
		}
		String messages = null; 
		if(msg.length()>0){
			messages = msg.toString();
		}
		return messages;
	}


	@Override
	public Result<T> findAll(T entity) {
		result = new Result<T>();
		String classNm = entity.getClass().getName();	
		
		String msg = runRules(entity, Operation.FIND);
		
		
		if(msg == null){
			try {
				IDAO dao = FactoryDAO.build(classNm);			
				result.setEntityList((ArrayList<T>)dao.findAll());				
			} catch (IOException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar a consulta!");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar a consulta!");
			}
		}else{
			result.setMsg(msg);
			
		}
		
		return result;
	}
}
