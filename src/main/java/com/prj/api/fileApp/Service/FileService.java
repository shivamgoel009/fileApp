package com.prj.api.fileApp.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prj.api.fileApp.Exception.ErrorCodesEnum;
import com.prj.api.fileApp.Exception.FileProcessingException;

@Service
public class FileService {
	 private Logger log= LoggerFactory.getLogger(FileService.class);
	/*
	 * expecting path to be comma separated value
	 */
	public List<String> getCommonWordsFromFiles(String path) throws IOException, FileProcessingException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		List<String> filePath = new ArrayList<>();

		String paths[] = path.split(",");
		for (int i = 0; i < paths.length; i++) {
			
			File file = new File(paths[i]);
			boolean exists = file.exists();     
			boolean isFile = file.isFile();  
				
			if(exists && isFile)
				filePath.add(paths[i]);
			else
				throw new FileProcessingException("Not a valid file/path on server: "+paths[i], ErrorCodesEnum.BAD_REQUEST.getErrorCodeValue());
		}
		/*
		 * We just need to return the common words and not preserve the order
		 * so using HashSet instead of LinkedHashSet
		 */
		HashSet<String> wordsSet = new HashSet<>();
		HashSet<String> commonWords = new HashSet<>();
		int number_of_files = filePath.size();
		if(number_of_files<2)
			throw new FileProcessingException("Input atleast two file paths to compare",ErrorCodesEnum.PARAMETER_INVALID.getErrorCodeValue());
		boolean firstFile = true;
		while (number_of_files-- > 0) {
			try {
				inputStream = new FileInputStream(filePath.get(number_of_files));
				log.info("Processing file with path: " + filePath.get(number_of_files));
				sc = new Scanner(inputStream, "UTF-8");

				String line = null;
				String lineArr[] = null;
				if (firstFile) {
					while (sc.hasNextLine()) {
						line = sc.nextLine();
						lineArr = line.split("\\s+");
						for (int i = 0; i < lineArr.length; i++) {
							if (!wordsSet.contains(lineArr[i].toLowerCase().replaceAll("[^\\w\\s]", "")))
								wordsSet.add(lineArr[i].toLowerCase().replaceAll("[^\\w\\s]", ""));
						}
					}
					log.debug("First file ("+filePath.get(number_of_files)+") processed, words added to set, count of distinct words are " + wordsSet.size());
					firstFile = false;
				} else {
					while (sc.hasNextLine()) {
						line = sc.nextLine();
						lineArr = line.split("\\s+");
						for (int i = 0; i < lineArr.length; i++) {
							if (wordsSet.contains(lineArr[i].toLowerCase().replaceAll("[^\\w\\s]", "")))
								commonWords.add(lineArr[i].toLowerCase().replaceAll("[^\\w\\s]", ""));
						}
					}
					log.debug("File ("+filePath.get(number_of_files)+") processed and common words now are: "
							+ commonWords.size());
					wordsSet.clear();
					wordsSet.addAll(commonWords);
					commonWords.clear();
				}
				// note that Scanner suppresses exceptions
				if (sc.ioException() != null) {
					throw new FileProcessingException(sc.ioException().getMessage(), sc.ioException());
				}
			} catch (Exception e) {
				throw new FileProcessingException(e.getMessage(), e);
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (sc != null) {
					sc.close();
				}
			}
		}
		return new ArrayList<String>(wordsSet);
	}

}

////filePath.add("C:\\Users\\shivgoel\\Downloads\\SampleTextFile_1000kb.txt");
////filePath.add("C:\\Users\\shivgoel\\Downloads\\SampleTextFile_500kb.txt");
////filePath.add("C:\\Users\\shivgoel\\Downloads\\SampleTextFile_200kb.txt");
//
//filePath.add("C:\\Users\\shivgoel\\Downloads\\file1.txt");
//filePath.add("C:\\Users\\shivgoel\\Downloads\\file2.txt");
//filePath.add("C:\\Users\\shivgoel\\Downloads\\file3.txt");
