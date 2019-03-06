package json;

import java.sql.ResultSet;
import java.util.ArrayList;

import Dao.Dao;

public class gsonClass {
	private String msg;
	private int status;
	public gsonClass(){
		msg ="";
		status = 0;
	}
	public ArrayList<jsonSetGet> GsonList(){
		String sql = "select id,place,area_id,latitude,longitude from vending;";

		ArrayList<jsonSetGet> Gsontbl = new ArrayList<jsonSetGet>();


		Dao Dao = null;
		ResultSet rs =null;
		try{
			System.out.println("Dao参照");
			Dao = new Dao();
			System.out.println("DB接続");

			rs = Dao.execute(sql);

			while(rs.next()){
				jsonSetGet jsg = new jsonSetGet();
				jsg.setId(rs.getInt("id"));
				jsg.setPlace(rs.getString("place"));
				jsg.setAreaId(rs.getInt("area_id"));
				jsg.setLatitude(rs.getDouble("latitude"));
				jsg.setLongitude(rs.getDouble("longitude"));
				Gsontbl.add(jsg);

			}
			status = 0;
			msg = "正常動作";

		}catch(Exception e){
			System.out.println("Exception");
			status = 1;
			msg = "エラー発生 :" + e;
		}
		finally{
			try{
				if(rs != null){
					rs.close();
				}
				Dao.close();
			}
			catch(Exception e){

			}

		}
		System.out.println(sql);
		return Gsontbl;
	}
	public void setMsg(String msg){
		this.msg =msg;
	}
	public void setStatus(int status){
		this.status  = status;
	}
	public String getMsg(){
		return msg;
	}
	public int getStatus(){
		return status;
	}

}
