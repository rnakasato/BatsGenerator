package com.rafank.strategy.impl;

import java.util.List;

import com.rafank.domain.impl.Bat;
import com.rafank.strategy.Validator;
import com.rafank.util.FileUtil;

/**
 * Validates if the defined parent folder is a directory or doesn't exist
 * @author rafae
 *
 */
public class ValidParentFolderValidator extends Validator<Bat> {
	
	@Override
	public String validate(List<Bat> entityList) {

		return null;
	}

	@Override
	public String validate(Bat entity) {
		String msg = null; 
		if(entity != null && entity.getParentPath() != null && !entity.getParentPath().isEmpty()){
			if(!FileUtil.isValidFolder(entity.getParentPath())){
				msg = "The selected path is invalid";
			}
		}
		return msg;
	}

}
