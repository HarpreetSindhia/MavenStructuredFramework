package MavenFramework.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PurchaseOrder {

	public List<HashMap<String, Object>> getJsonDataToHashMap() throws IOException
	{
		
		//Read json file to string
		
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\MavenFramework\\data\\DataReader.json"),"StandardCharsets.UTF_8");
		
		//String to HashMap using JackSon DataBind dependency
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,Object>>>(){});
		return data;
		
	}
}
