package com.rafank.strategy.impl;

import java.util.List;

import com.rafank.domain.impl.Bat;
import com.rafank.strategy.Validator;

/**
 * Verifies if the required fields of bat are set to save or update the Bat
 * @author rafae
 *
 */
public class BatRequiredFieldsValidator extends Validator<Bat> {

	/**
	 * No validator for List of Bats
	 */
	@Override
	public String validate(List<Bat> entityList) {
		return null;
	}

	@Override
	public String validate(Bat entity) {
		boolean hasError = false;
		StringBuilder msgBuilder = new StringBuilder();
		String msg = null;
		if(entity != null){
			
			boolean noParentPath = entity.getParentPath() == null || entity.getParentPath().isEmpty() ? true : false;
			boolean noName = entity.getName() == null || entity.getName().isEmpty() ? true : false;
			boolean hasContent = entity.getContentLines() == null || entity.getContentLines().isEmpty() ? true : false;
			
			if(noParentPath){
				msgBuilder.append("Parent folder is required to save \n");
			}
			if(noName){
				msgBuilder.append("Bat name is required to save \n");
			}
			if(hasContent){
				msgBuilder.append("Content is required to save \n");
			}			
		}else {
			msgBuilder = new StringBuilder();
			hasError = true;			
			msgBuilder.append("Please fill the required fields to save");
			
		}
		if(msgBuilder.length() > 0){
			msg = msgBuilder.toString();
		}
		
		return msg;
	}

}
