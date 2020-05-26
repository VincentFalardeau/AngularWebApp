package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourcesManager {
	
	public String getFileContent(String filename) throws IOException{
		return getFileContent(filename, "");
	}
	
	public String getFileContent(String filename, int toAppend) throws IOException{
		return getFileContent(filename, toAppend+"");
	}
	
	public String getFileContent(String filename, String toAppend) throws IOException{
		return new String(Files.readAllBytes(Paths.get(getPath(filename)))) + toAppend;
	}
	
	public String getPath(String filename) throws IOException{
		return getClass().getClassLoader().getResource(filename).getPath();
	}
}
