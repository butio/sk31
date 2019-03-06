package Area;

import java.sql.ResultSet;
import java.util.ArrayList;

import Dao.Dao;

public class Area {
	public static ArrayList<ArrayList<String>> AreaList(){
		String sql = "SELECT DISTINCT v.area_id,a.area_place" +
				" FROM vending v" +
				" INNER JOIN area a ON a.id = v.area_id;";

		ArrayList<ArrayList<String>> tbl = new ArrayList<ArrayList<String>>();


		Dao Dao = null;
		ResultSet rs =null;
		try{
			System.out.println("Dao参照");
			Dao = new Dao();
			System.out.println("DB接続");

			rs = Dao.execute(sql);

			while(rs.next()){
				ArrayList<String>rec = new ArrayList<String>();

				rec.add(rs.getString("area_id"));
				rec.add(rs.getString("area_place"));
				tbl.add(rec);

			}

		}catch(Exception e){
			System.out.println("Exception");
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
		return tbl;


	}
}
