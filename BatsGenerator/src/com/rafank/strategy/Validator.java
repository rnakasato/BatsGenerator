package com.rafank.strategy;

import java.util.List;

import com.rafank.core.IStrategy;
import com.rafank.domain.DomainEntity;

public abstract class Validator<T extends DomainEntity> implements IStrategy<T>{

	@Override
	public String process(T entity) {
		return validate(entity);
	}

	@Override
	public String process(List<T> entityList) {
		return validate(entityList);
	}
	
	public abstract String validate(List<T> entityList);
	public abstract String validate(T entity);
	
	
}
