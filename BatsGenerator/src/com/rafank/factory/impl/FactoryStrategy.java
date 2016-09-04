package com.rafank.factory.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rafank.constant.Operation;
import com.rafank.core.IStrategy;
import com.rafank.domain.DomainEntity;
import com.rafank.domain.impl.Bat;
import com.rafank.domain.impl.Folder;
import com.rafank.strategy.impl.BatRequiredFieldsValidator;
import com.rafank.strategy.impl.UniqueFolderPathValidator;

public class FactoryStrategy {
	
	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade;
	 * O valor é um mapa que de regras de negócio indexado pela operação
	 * A Chave é o nome da entidade referente às regras de negócio e o valor é um mapa contendo 
	 * todos os Strategies referentes à cada operação (salvar,alterar,consultar,excluir)
	 */
	private static Map<String, Map<String, List<IStrategy>>> rns;
	private static Map<String, List<IStrategy>> rnsFolder;
	private static Map<String, List<IStrategy>> rnsBat;
	
	public static List<IStrategy> build(DomainEntity entity, String operation){
		if(rns == null){
			initMap();
		}
		List<IStrategy> operationRules = new ArrayList<>();
		Map<String, List<IStrategy>> entityRules = rns.get(entity.getClass().getName());
		if(entityRules != null){
			operationRules = entityRules.get(operation);
		}
		return operationRules;
	}
	
	public static List<IStrategy> buildList(List<DomainEntity> entityList, String operation){
		if(rns == null){
			initMap();
		}
		List<IStrategy> operationRules = new ArrayList<>();
		if(entityList != null && !entityList.isEmpty()){			
			Map<String, List<IStrategy>> entityRules = rns.get(entityList.get(0).getClass().getName());
			if(entityRules != null){
				operationRules = entityRules.get(operation);
			}
		}			
		return operationRules;
	}
	
	
	
	private static void initMap(){
		// inicialização do mapa de regras de negócio total
		rns = new HashMap<>();
		
		// Inicialização do mapa de regras de negócio para Folder
		rnsFolder = new HashMap<>();
		initFolderRns();
		
		rnsBat = new HashMap<>();
		initBatRns();
		
		rns.put(Folder.class.getName(), rnsFolder);
		rns.put(Bat.class.getName(), rnsBat);
	}
	
	private static void initBatRns(){
		// Definition of operationStrategies
		List<IStrategy> rnsSave = new ArrayList<>();
		List<IStrategy> rnsUpdate = new ArrayList<>();
		List<IStrategy> rnsFind = new ArrayList<>();  
		List<IStrategy> rnsDelete = new ArrayList<>();
		
		// Define the strategies used to SAVE bats
		rnsSave.add(new BatRequiredFieldsValidator());
		
		rnsBat.put(Operation.SAVE, rnsSave);
		rnsBat.put(Operation.UPDATE, rnsUpdate);
		rnsBat.put(Operation.DELETE, rnsDelete);
		rnsBat.put(Operation.FIND, rnsFind);				
	}
	private static void initFolderRns(){
		List<IStrategy> rnsSave = new ArrayList<>();
		List<IStrategy> rnsUpdate = new ArrayList<>();
		List<IStrategy> rnsFind = new ArrayList<>();  
		List<IStrategy> rnsDelete = new ArrayList<>();
		rnsSave.add(new UniqueFolderPathValidator());
	
//		 Insere as regras de negócio por operação 
		rnsFolder.put(Operation.SAVE_LIST, rnsSave);
		rnsFolder.put(Operation.UPDATE, rnsUpdate);
		rnsFolder.put(Operation.DELETE, rnsDelete);
		rnsFolder.put(Operation.FIND, rnsFind);		
	}
}
