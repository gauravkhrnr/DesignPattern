
class AnalyticLibrary{
	public void displayGraph(CustomLibraryObject customLibraryObject) {
		System.out.println("Displayed graph based on the custom object");
	}
}

class CustomLibraryObject{
	private String jsonData;
	CustomLibraryObject(String jsonData){
		this.jsonData = jsonData;
	}
}

interface DataVisulizer{
	public void displayGraph(String jsonData); 
}

class LibraryAdapter implements DataVisulizer{ 
	AnalyticLibrary analyticLibrary; //adaptee
	public LibraryAdapter(AnalyticLibrary analyticLibrary){
		this.analyticLibrary = analyticLibrary;
	}
	
	public void displayGraph(String jsonData) {
		CustomLibraryObject customLibraryObject =  getCustomLibraryObject(jsonData);
		analyticLibrary.displayGraph(customLibraryObject);
	}
	
	private CustomLibraryObject getCustomLibraryObject(String jsonData) {
		//convert json data to library object
		return new CustomLibraryObject(jsonData);
	}
}

public class AdapterPattern2 {
	public static void main(String[] args) {
		String jsonData = "{}";
		AnalyticLibrary library = new AnalyticLibrary();
		LibraryAdapter adapter = new LibraryAdapter(library);
		adapter.displayGraph(jsonData);
	}
}
