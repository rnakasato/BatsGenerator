package com.rafank.core.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rafank.constant.FileNames;
import com.rafank.dao.IFolderDAO;
import com.rafank.domain.impl.Folder;
import com.rafank.util.Converter;
import com.rafank.util.FileUtil;

public class FolderDAO implements IFolderDAO {

	@Override
	public void save(Folder entityList) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveList(List<Folder> entityList) throws IOException {
		File file = new File(FileNames.BATS_FOLDER);
		List<String> lines = Converter.parseFolderList(entityList);
		FileUtil.writeList(file, lines);
	}

	@Override
	public void update(Folder entity) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Folder entity) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Folder> find(Folder entity) throws IOException {
		return null;
	}

	@Override
	public List<Folder> findAll() throws IOException {
		List<String> lines = null;
		List<Folder> folderList = null;
		try { // Verificando se o arquivo de lista de folders já existe
			folderList = new ArrayList<>();

			File folderFile = new File("folderPath.txt");
			lines = FileUtil.getContentLinesList(folderFile);

			for (String path : lines) {
				File file = new File(path);
				Folder folder = new Folder();
				folder.setFolderName(file.getName());
				folder.setPath(file.getAbsolutePath());
				int number = 0;
				List<String> batList = FileUtil.getFilePathListByExtension(file.getAbsolutePath(), FileUtil.DOT_BAT);
				if (batList != null) {
					number = batList.size();
				}

				folder.setBatsNumber(number);

				folderList.add(folder);
			}

		} catch (Exception e) { // se entrou aqui o arquivo não existe
			FileUtil.createFolder(FileNames.BATS_FOLDER);
		}
		return folderList;
	}
}
