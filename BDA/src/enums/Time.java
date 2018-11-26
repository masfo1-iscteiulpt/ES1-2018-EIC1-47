package enums;

public enum Time {

	H12(43200,"12h"), D1(86400,"1d"), D2(172800,"2d"), D5(432000,"5d"), D10(864000,"10d"), D30(2592000,"30d"), ALL(0,"All");
	
	private int seconds;
	private String time;
	
	Time(int seconds, String time){
		this.seconds = seconds;
		this.time = time;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public String getTime() {
		return time;
	}
	
	 @Override
	    public String toString() {
	        return time;
	    }
}
