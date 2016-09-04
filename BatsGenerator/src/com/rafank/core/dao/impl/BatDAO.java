package com.rafank.core.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rafank.constant.BatType;
import com.rafank.converter.impl.BatContentToTargetPathConverter;
import com.rafank.dao.IBatDAO;
import com.rafank.domain.impl.Bat;
import com.rafank.factory.impl.FactoryBatType;
import com.rafank.util.FileUtil;

public class BatDAO implements IBatDAO {

	@Override
	public void save(Bat entity) throws IOException {
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(entity.getParentPath())
					.append("\\")
					.append(entity.getName());
		if(entity.getName().indexOf(FileUtil.DOT_BAT) < 0){
			pathBuilder.append(FileUtil.DOT_BAT);
		}
		String path = pathBuilder.toString();
		File newBat = new File(path);
		newBat.createNewFile();
		FileUtil.writeList(newBat, entity.getContentLines());
	}

	@Override
	public void saveList(List<Bat> entityList) throws IOException {
		//

	}

	@Override
	public void update(Bat entity) throws IOException {
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(entity.getParentPath())
					.append("\\")
					.append(entity.getName());
		if(entity.getName().indexOf(FileUtil.DOT_BAT) < 0){
			pathBuilder.append(FileUtil.DOT_BAT);
		}
		File oldFile = new File(entity.getOldPath());
		oldFile.delete();
		String path = pathBuilder.toString();
		File updatedBat = new File(path);
		updatedBat.createNewFile();
		FileUtil.writeList(updatedBat, entity.getContentLines());
	}

	@Override
	public void delete(Bat entity) throws IOException {

	}

	/**
	 * Para utilizar o find do BatDAO será necessário definir o parentPath Caso
	 * não seja definido um parentPath o retorno será uma lista vazia
	 */
	@Override
	public List<Bat> find(Bat entity) throws IOException {
		List<Bat> batList = null;
		if (entity != null && entity.getParentPath() != null && FileUtil.isValidFile(entity.getParentPath())) {
			batList = new ArrayList<>();
			List<File> fileList = FileUtil.getFileListByExtension(entity.getParentPath(), FileUtil.DOT_BAT);
			for (File f : fileList) {
				List<String> lineList = FileUtil.getContentLinesList(f);
				String type;
				if (lineList != null) {
					type = lineList.get(0);
				} else {
					type = BatType.UNKNOWN;
				}
				BatType batType = FactoryBatType.build(type);
				Bat bat = new Bat();
				bat.setType(batType);
				bat.setContentLines(lineList);
				bat.setName(f.getName());
				bat.setOldPath(f.getPath());
				bat.setPath(f.getAbsolutePath());
				bat.setParentPath(f.getParent());
				if(BatType.EXECUTE_PROGRAM.equals(bat.getBatType().getType())
						|| BatType.OPEN_FILE.equals(bat.getBatType().getType())
						|| BatType.OPEN_FOLDER.equals(bat.getBatType().getType())){
					bat.setTargetPath(BatContentToTargetPathConverter.convert(bat.getContentLines()));
				}else{
					bat.setTargetPath("NONE");
				}
				batList.add(bat);
			}
		}
		return batList;
	}

	@Override
	public List<Bat> findAll() throws IOException {

		return null;
	}

	public static void main(String[] args) throws IOException {
		Bat bat = new Bat();
		List<String> lineList = new ArrayList<>();
		lineList.add("TESTE");
		lineList.add("NOVA LINHA");
		bat.setParentPath("C:\\Meus Documentos\\Projects\\BatsUteis");
		bat.setName("NomeNovoBat");;
		bat.setContentLines(lineList);
		BatDAO dao = new BatDAO();
//		List<Bat> batList = dao.find(bat);
//		for (Bat b : batList) {
//			System.out.println(b.toString());
//			System.out
//					.println("--------------------------------------------------------------------------------------");
//		}
		
		dao.save(bat);
		
	}

}
