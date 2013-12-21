package event.parse.notifications;

public enum RestApi {
	PARSE_HOST("https://api.parse.com/"),
	INSTALLATION("1/installations"),
	PUSH("1/push");
	
	private String data;
	
	private RestApi(String string) {
		data = string;
	}
	
	public String toString(){
		return data;
	}
}
