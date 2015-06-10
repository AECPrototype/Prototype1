import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Crud_Functions {

	String fileName;

	public int putData(String key, String value) throws Exception {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter(key + ".txt");
		fw.write(value);
		fw.close();
		return 0;
	}

	public static int deleteData(String key) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(key+".txt");
		if (file.delete()) {
			System.out.println("File deleted successfuly");
			return 1;
		} else {
			System.out.println("Error");
			return 0;
		}
	}
	
	public String readData(String key) throws Exception {
		// TODO Auto-generated method stub

		String sCurrentLine;
		String Data="";
		BufferedReader br = new BufferedReader(new FileReader(key + ".txt"));
		while ((sCurrentLine = br.readLine()) != null) {
			Data = Data + sCurrentLine;
		}
		return Data;
	}

	
	public int updateData(String key, String newData) throws Exception {
		// TODO Auto-generated method stub

		File file = new File(key + ".txt");
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		// true = append file
		FileWriter fileWritter = new FileWriter(file.getName(), true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(" " + newData);
		bufferWritter.close();
		System.out.println("File appended successfuly");
		return 0;
	}
}
