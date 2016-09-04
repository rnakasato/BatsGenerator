package com.rafank.core;

import java.io.IOException;
import java.util.List;

import com.rafank.domain.DomainEntity;

public interface IDAO<T extends DomainEntity> {
	public void save(T entity) throws IOException;
	public void saveList(List<T> entityList) throws IOException;
	public void update(T entity) throws IOException;
	public void delete(T entity) throws IOException;
	public List<T> find(T entity)throws IOException;
	public List<T> findAll() throws IOException;

}
