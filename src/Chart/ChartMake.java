package Chart;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Dao.Dao;

public class ChartMake {
	static String date;


	public static ArrayList<ArrayList<String>> ProductList(){
		String sql = "SELECT id,name FROM product;";

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

				rec.add(rs.getString("id"));
				rec.add(rs.getString("name"));
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
	public static ArrayList<ArrayList<String>> LineChartAll(){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -3);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT bay.売上年 AS BayYear, bay.売上月 AS BayMonth, SUM( bay.売上数 ) AS BayDrink , SUM( bay.売上価格 ) AS BayPrice" +
					" FROM ("+
					""+
					"SELECT product.id, product.name, year( earnings.date ) AS 売上年, month( earnings.date ) AS 売上月, count( earnings.product_id ) AS 売上数, stock.price * ( count( earnings.product_id ) ) AS 売上価格"+
					" FROM earnings"+
					" INNER JOIN stock ON earnings.vending_id = stock.vending_id"+
					" AND earnings.stock_count = stock.count"+
					" INNER JOIN product ON earnings.product_id = product.id"+
					" INNER JOIN vending ON stock.vending_id = vending.id"+
					" INNER JOIN category ON product.category_id = category.id"+
					" INNER JOIN area ON vending.area_id = area.id"+
					" WHERE date >= '"+ strdate + "'" +
					" GROUP BY year( earnings.date ) , month( earnings.date ) , product.id"+
					" )bay"+
					" GROUP BY bay.売上年, bay.売上月;";


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


				date = (rs.getString("BayYear"));
				date += "年";
				date += (rs.getString("BayMonth"));
				date += "月";
				rec.add(rs.getString(date));
				rec.add(rs.getString("BayDrink"));
				rec.add(rs.getString("BayPrice"));

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

	public static ArrayList<ArrayList<String>> LineChartProduct(String product){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -3);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT bay.売上年 AS BayYear, bay.売上月 AS BayMonth, SUM( bay.売上数 ) AS BayDrink , SUM( bay.売上価格 ) AS BayPrice" +
					" FROM ("+
					""+
					"SELECT product.id, product.name, year( earnings.date ) AS 売上年, month( earnings.date ) AS 売上月, count( earnings.product_id ) AS 売上数, stock.price * ( count( earnings.product_id ) ) AS 売上価格"+
					" FROM earnings"+
					" INNER JOIN stock ON earnings.vending_id = stock.vending_id"+
					" AND earnings.stock_count = stock.count"+
					" INNER JOIN product ON earnings.product_id = product.id"+
					" INNER JOIN vending ON stock.vending_id = vending.id"+
					" INNER JOIN category ON product.category_id = category.id"+
					" INNER JOIN area ON vending.area_id = area.id"+
					" WHERE date >= '"+ strdate + "'" +
					" and product.category_id = "+ product +""+
					" GROUP BY year( earnings.date ) , month( earnings.date ) , product.id"+
					" )bay"+
					" GROUP BY bay.売上年, bay.売上月;";


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


				date = (rs.getString("BayYear"));
				date += "年";
				date += (rs.getString("BayMonth"));
				date += "月";
				rec.add(rs.getString(date));
				rec.add(rs.getString("BayDrink"));
				rec.add(rs.getString("BayPrice"));

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




	public static ArrayList<ArrayList<String>> LineChartVendingAll(String vending_ID){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -3);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT bay.売上年 AS BayYear, bay.売上月 AS BayMonth, SUM( bay.売上数 ) AS BayDrink , SUM( bay.売上価格 ) AS BayPrice" +
				" FROM ("+
				""+
				"SELECT product.id, product.name, year( earnings.date ) AS 売上年, month( earnings.date ) AS 売上月, count( earnings.product_id ) AS 売上数, stock.price * ( count( earnings.product_id ) ) AS 売上価格"+
				" FROM earnings"+
				" INNER JOIN stock ON earnings.vending_id = stock.vending_id"+
				" AND earnings.stock_count = stock.count"+
				" INNER JOIN product ON earnings.product_id = product.id"+
				" INNER JOIN vending ON stock.vending_id = vending.id"+
				" INNER JOIN category ON product.category_id = category.id"+
				" INNER JOIN area ON vending.area_id = area.id"+
				" where vending.id = "+ vending_ID +""+
				" GROUP BY year( earnings.date ) , month( earnings.date ) , product.id"+
				" )bay"+
				" GROUP BY bay.売上年, bay.売上月;";



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
				date = (rs.getString("BayYear"));
				date += "年";
				date += (rs.getString("BayMonth"));
				date += "月";
				rec.add(rs.getString(date));
				rec.add(rs.getString("BayDrink"));
				rec.add(rs.getString("BayPrice"));

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




	public static ArrayList<ArrayList<String>> LineChartVendingProduct(String product,String vending_ID){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -3);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT bay.売上年 AS BayYear, bay.売上月 AS BayMonth, SUM( bay.売上数 ) AS BayDrink , SUM( bay.売上価格 ) AS BayPrice" +
				" FROM ("+
				""+
				"SELECT product.id, product.name, year( earnings.date ) AS 売上年, month( earnings.date ) AS 売上月, count( earnings.product_id ) AS 売上数, stock.price * ( count( earnings.product_id ) ) AS 売上価格"+
				" FROM earnings"+
				" INNER JOIN stock ON earnings.vending_id = stock.vending_id"+
				" AND earnings.stock_count = stock.count"+
				" INNER JOIN product ON earnings.product_id = product.id"+
				" INNER JOIN vending ON stock.vending_id = vending.id"+
				" INNER JOIN category ON product.category_id = category.id"+
				" INNER JOIN area ON vending.area_id = area.id"+
				" where vending.id = "+ vending_ID +""+
				" and product.category_id = "+ product +""+
				" and date >= '"+ strdate + "'" +
				" GROUP BY year( earnings.date ) , month( earnings.date ) , product.id"+
				" )bay"+
				" GROUP BY bay.売上年, bay.売上月;";



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
				date = (rs.getString("BayYear"));
				date += "年";
				date += (rs.getString("BayMonth"));
				date += "月";
				rec.add(rs.getString(date));
				rec.add(rs.getString("BayDrink"));
				rec.add(rs.getString("BayPrice"));

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

	public static ArrayList<ArrayList<String>> LineChartAreaAll(String area_ID){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -3);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT bay.売上年 AS BayYear, bay.売上月 AS BayMonth, SUM( bay.売上数 ) AS BayDrink , SUM( bay.売上価格 ) AS BayPrice" +
				" FROM ("+
				""+
				"SELECT product.id, product.name, year( earnings.date ) AS 売上年, month( earnings.date ) AS 売上月, count( earnings.product_id ) AS 売上数, stock.price * ( count( earnings.product_id ) ) AS 売上価格"+
				" FROM earnings"+
				" INNER JOIN stock ON earnings.vending_id = stock.vending_id"+
				" AND earnings.stock_count = stock.count"+
				" INNER JOIN product ON earnings.product_id = product.id"+
				" INNER JOIN vending ON stock.vending_id = vending.id"+
				" INNER JOIN category ON product.category_id = category.id"+
				" INNER JOIN area ON vending.area_id = area.id"+
				" where area.id = "+ area_ID +""+
				" GROUP BY year( earnings.date ) , month( earnings.date ) , product.id"+
				" )bay"+
				" GROUP BY bay.売上年, bay.売上月;";

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
				date = (rs.getString("BayYear"));
				date += "年";
				date += (rs.getString("BayMonth"));
				date += "月";
				rec.add(rs.getString(date));
				rec.add(rs.getString("BayDrink"));
				rec.add(rs.getString("BayPrice"));

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


	public static ArrayList<ArrayList<String>> LineChartAreaProduct(String product,String area_ID){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -3);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT bay.売上年 AS BayYear, bay.売上月 AS BayMonth, SUM( bay.売上数 ) AS BayDrink , SUM( bay.売上価格 ) AS BayPrice" +
				" FROM ("+
				""+
				"SELECT product.id, product.name, year( earnings.date ) AS 売上年, month( earnings.date ) AS 売上月, count( earnings.product_id ) AS 売上数, stock.price * ( count( earnings.product_id ) ) AS 売上価格"+
				" FROM earnings"+
				" INNER JOIN stock ON earnings.vending_id = stock.vending_id"+
				" AND earnings.stock_count = stock.count"+
				" INNER JOIN product ON earnings.product_id = product.id"+
				" INNER JOIN vending ON stock.vending_id = vending.id"+
				" INNER JOIN category ON product.category_id = category.id"+
				" INNER JOIN area ON vending.area_id = area.id"+
				" where area.id = "+ area_ID +""+
				" and product.category_id = "+ product +""+
				" and date >= '"+ strdate + "'" +
				" GROUP BY year( earnings.date ) , month( earnings.date ) , product.id"+
				" )bay"+
				" GROUP BY bay.売上年, bay.売上月;";

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
				date = (rs.getString("BayYear"));
				date += "年";
				date += (rs.getString("BayMonth"));
				date += "月";
				rec.add(rs.getString(date));
				rec.add(rs.getString("BayDrink"));
				rec.add(rs.getString("BayPrice"));

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
