package com.rafank.core;

import java.util.List;

import com.rafank.domain.DomainEntity;

public interface IStrategy<T extends DomainEntity> {
	public String process(T entity);
	public String process(List<T> entityList);
}
