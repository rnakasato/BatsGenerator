package com.rafank.strategy.impl;

import java.util.List;

import com.rafank.domain.impl.Folder;
import com.rafank.strategy.Validator;

/**
 * Validates if exists more than one folder with the same path in the list
 * @author rafae
 *
 */
public class UniqueFolderPathValidator extends Validator<Folder> {

	@Override
	public String validate(List<Folder> entityList) {
		boolean duplicated = false;
		String msg = null;
		for (Folder compare : entityList) {
			for (Folder compared : entityList) {
				if (compare.hashCode() != compared.hashCode() && compare.equals(compared)) {
					duplicated = true;
					break;
				}
			}
		}
		if (duplicated) {
			msg = "The selected folder already exists";
		}
		return msg;
	}

	/**
	 * No validations for single folder
	 */
	@Override
	public String validate(Folder entity) {				
		return null;
	}

}
