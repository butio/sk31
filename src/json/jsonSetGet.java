package json;

public class jsonSetGet {
	private int id;
	private String place;
	private int area_id;
	private double latitude;
	private double longitude;

	public jsonSetGet(){
	}
	public void setId(int _id){
		this.id = _id;
	}
	public void setPlace(String _place){
		this.place = _place;
	}
	public void setAreaId(int _areaId){
		this.area_id = _areaId;
	}
	public void setLatitude(double _latitude){
		this.latitude = _latitude;
	}
	public void setLongitude(double _longitude){
		this.longitude = _longitude;
	}
	public int getId(){
		return this.id;
	}
	public String getPlace(){
		return this.place;
	}
	public int getAreaId(){
		return this.area_id;
	}
	public double getLatitude(){
		return this.latitude;
	}
	public double getLongitude(){
		return this.longitude;
	}
}
