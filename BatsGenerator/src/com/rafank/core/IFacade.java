package com.rafank.core;

import java.util.List;

import com.rafank.application.Result;
import com.rafank.domain.DomainEntity;

public interface IFacade<T extends DomainEntity> {
	public Result<T> save(T entity);
	public Result<T> saveList(List<DomainEntity> entity);
	public Result<T> update(T entity);
	public Result<T> delete(T entity);
	public Result<T> find(T entity);
	public Result<T> findAll(T entity);
	public Result<T> view(T entity);
}
