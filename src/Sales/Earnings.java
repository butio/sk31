package Sales;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Dao.Dao;

public class Earnings {
	public String Mes;
	public String sex_Mes;
	public String Age_Mes;
	public String date_Mes;
	public String product_Mes;



	public String getProduct_Mes() {
		return product_Mes;
	}

	public void setProduct_Mes(String product_Mes) {
		this.product_Mes = product_Mes;
	}




	public void setProduct_Mes2(String product) {
		String sql = "SELECT name" +
				" FROM product"+
				" WHERE id = "+ product +";";




		Dao Dao = null;
		ResultSet rs =null;
		try{
			System.out.println("Dao参照");
			Dao = new Dao();
			System.out.println("DB接続");

			rs = Dao.execute(sql);


			while(rs.next()){
				this.product_Mes = (rs.getString("name"));
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
	}




	public String getSex_Mes() {
		return sex_Mes;
	}


	public void setSex_Mes(String sex_Mes) {
		this.sex_Mes = sex_Mes;
	}


	public String getAge_Mes() {
		return Age_Mes;
	}


	public void setAge_Mes(String age_Mes) {
		Age_Mes = age_Mes;
	}


	public String getDate_Mes() {
		return date_Mes;
	}


	public void setDate_Mes(String date_Mes) {
		this.date_Mes = date_Mes;
	}



	private static int intage;

	public Earnings(){
		this.Mes ="";
	}


	public String getMes() {
		return this.Mes;
	}


	public void setMes(String mes) {
		this.Mes = mes;
	}


	/**
	 * 商品毎の売り上げを二次元配列にして返す。
	 * @return tbl
	 */
	public static ArrayList<ArrayList<String>> earningProduct(){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT p.name, COUNT(*)" +
				" FROM earnings e" +
				" INNER JOIN product p ON p.id = e.product_id" +
				" INNER JOIN member m ON m.id = e.member_id" +
				" WHERE date >= '"+ strdate + "'" +
				" GROUP BY p.name" +
				" ORDER BY COUNT(*) DESC;";
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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningSexProduct(String sex){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT p.name, COUNT(*)" +
				" FROM earnings e" +
				" INNER JOIN product p ON p.id = e.product_id" +
				" INNER JOIN member m ON m.id = e.member_id" +
				" WHERE date >= '"+ strdate + "'" +
				" AND m.sex = "+ sex + "" +
				" GROUP BY p.name" +
				" ORDER BY COUNT(*) DESC;";


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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningAgeProduct(String age){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;
        String sql = "";

        if(age == "60"){
        	sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
        			" FROM earnings e"+
        			" INNER JOIN product p ON p.id = e.product_id"+
        			" INNER JOIN member m ON m.id = e.member_id"+
        			" INNER JOIN ("+
        			" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        			" CASE WHEN month( now( ) ) > birthmonth"+
        			" THEN 0"+
        			" ELSE ("+

    				" CASE WHEN month( now( ) ) = birthmonth"+
    				" THEN ("+

    				" CASE WHEN day( now( ) ) >= birthday"+
    				" THEN 0"+
    				" ELSE 1"+
    				" END )"+
    				" ELSE 1"+
    				" END"+
    				" )"+
    				" END"+
    				" ) AS age"+
    				" FROM member"+
    				")age ON m.id = age.id"+
    				" WHERE date >= '"+ strdate + "'" +
    				" AND age >= "+ age + "" +
    				" GROUP BY p.name"+
    				" ORDER BY COUNT( * ) DESC;";

        }else{


        	sql = "SELECT p.name, COUNT( * ) as c_earnings"+
        		" FROM earnings e"+
        		" INNER JOIN product p ON p.id = e.product_id"+
        		" INNER JOIN member m ON m.id = e.member_id"+
        		" INNER JOIN ("+
        		"SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        		" CASE WHEN month( now( ) ) > birthmonth"+
        		" THEN 0"+
        		" ELSE ("+

				" CASE WHEN month( now( ) ) = birthmonth"+
				" THEN ("+

				" CASE WHEN day( now( ) ) >= birthday"+
				" THEN 0"+
				" ELSE 1"+
				" END )"+
				" ELSE 1"+
				" END"+
				" )"+
				" END"+
				" ) AS age"+
				" FROM member"+
				") age ON m.id = age.id"+
				" WHERE date >= '"+ strdate + "'" +
				" AND age <= "+ age + " AND age > "+ intage +"" +
				" GROUP BY p.name"+
				" ORDER BY COUNT( * ) DESC;";
        }
        System.out.println("年代表示");
        System.out.println(sql);

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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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

	public static ArrayList<ArrayList<String>> earningSexAgeProduct(String sex,String age){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;
        String sql = "";
        if(age == "60"){
        	sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
        			" FROM earnings e"+
        			" INNER JOIN product p ON p.id = e.product_id"+
        			" INNER JOIN member m ON m.id = e.member_id"+
        			" INNER JOIN ("+
        			" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        			" CASE WHEN month( now( ) ) > birthmonth"+
        			" THEN 0"+
        			" ELSE ("+

    				" CASE WHEN month( now( ) ) = birthmonth"+
    				" THEN ("+

    				" CASE WHEN day( now( ) ) >= birthday"+
    				" THEN 0"+
    				" ELSE 1"+
    				" END )"+
    				" ELSE 1"+
    				" END"+
    				" )"+
    				" END"+
    				" ) AS age"+
    				" FROM member"+
    				")age ON m.id = age.id"+
    				" WHERE date >= '"+ strdate + "'" +
    				" AND age >= "+ age + "" +
    				" AND m.sex = "+ sex + "" +
    				" GROUP BY p.name"+
    				" ORDER BY COUNT( * ) DESC;";
        }else{


        	sql = "SELECT p.name, COUNT( * ) as c_earnings"+
        		" FROM earnings e"+
        		" INNER JOIN product p ON p.id = e.product_id"+
        		" INNER JOIN member m ON m.id = e.member_id"+
        		" INNER JOIN ("+
        		"SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        		" CASE WHEN month( now( ) ) > birthmonth"+
        		" THEN 0"+
        		" ELSE ("+

				" CASE WHEN month( now( ) ) = birthmonth"+
				" THEN ("+

				" CASE WHEN day( now( ) ) >= birthday"+
				" THEN 0"+
				" ELSE 1"+
				" END )"+
				" ELSE 1"+
				" END"+
				" )"+
				" END"+
				" ) AS age"+
				" FROM member"+
				") age ON m.id = age.id"+
				" WHERE date >= '"+ strdate + "'" +
				" AND age <= "+ age + " AND age > "+ intage +"" +
				" AND m.sex = "+ sex + "" +
				" GROUP BY p.name"+
				" ORDER BY COUNT( * ) DESC;";
        }


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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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

	//これより下、日付指定。


	public static ArrayList<ArrayList<String>> earningFixeddateProduct(String date,String date2){

		String sql = "";


		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
		if(!date.equals("") && !date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
			" FROM earnings e" +
			" INNER JOIN product p ON p.id = e.product_id" +
			" INNER JOIN member m ON m.id = e.member_id" +
			" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
			" GROUP BY p.name" +
			" ORDER BY COUNT(*) DESC;";
		}else if(!date.equals("") && date2.equals("")){

			sql = "SELECT p.name, COUNT(*)" +
			" FROM earnings e" +
			" INNER JOIN product p ON p.id = e.product_id" +
			" INNER JOIN member m ON m.id = e.member_id" +
			" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
			" GROUP BY p.name" +
			" ORDER BY COUNT(*) DESC;";

		}else if(date.equals("") && !date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
			" FROM earnings e" +
			" INNER JOIN product p ON p.id = e.product_id" +
			" INNER JOIN member m ON m.id = e.member_id" +
			" WHERE date <= '" + date2 +"'" +
			" GROUP BY p.name" +
			" ORDER BY COUNT(*) DESC;";

		}


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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningSexFixeddateProduct(String date,String date2,String sex){

		String sql = "";


		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
		if(!date.equals("") && !date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
			" FROM earnings e" +
			" INNER JOIN product p ON p.id = e.product_id" +
			" INNER JOIN member m ON m.id = e.member_id" +
			" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
			" AND m.sex = "+ sex + "" +
			" GROUP BY p.name" +
			" ORDER BY COUNT(*) DESC;";
		}else if(!date.equals("") && date2.equals("")){

			sql = "SELECT p.name, COUNT(*)" +
			" FROM earnings e" +
			" INNER JOIN product p ON p.id = e.product_id" +
			" INNER JOIN member m ON m.id = e.member_id" +
			" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
			" AND m.sex = "+ sex + "" +
			" GROUP BY p.name" +
			" ORDER BY COUNT(*) DESC;";

		}else if(date.equals("") && !date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
			" FROM earnings e" +
			" INNER JOIN product p ON p.id = e.product_id" +
			" INNER JOIN member m ON m.id = e.member_id" +
			" WHERE date <= '" + date2 +"'" +
			" AND m.sex = "+ sex + "" +
			" GROUP BY p.name" +
			" ORDER BY COUNT(*) DESC;";

		}


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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningAgeFixeddateProduct(String date,String date2,String age){

		String sql = "";


		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;

		if(age == "60"){
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age >= "+ age + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";

			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age >= "+ age + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age >= "+ age + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}else{
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age <= "+ age + " AND age > "+ intage +"" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";
			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}


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

					rec.add(rs.getString("p.name"));
					System.out.println(rs.getString("p.name"));
					rec.add(rs.getString("c_earnings"));
					System.out.println(rs.getString("c_earnings"));
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

	public static ArrayList<ArrayList<String>> earningSexAgeFixeddateProduct(String date,String date2,String sex,String age){

		String sql = "";


		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;

		if(age == "60"){
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age >= "+ age + "" +
	    				" AND m.sex = "+ sex + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";

			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age >= "+ age + "" +
		    				" AND m.sex = "+ sex + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age >= "+ age + "" +
		    				" AND m.sex = "+ sex + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}else{
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age <= "+ age + " AND age > "+ intage +"" +
	    				" AND m.sex = "+ sex + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";
			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" AND m.sex = "+ sex + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" AND m.sex = "+ sex + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}




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
				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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



	//これより下、自販機ごとでの取得。




	/**
	 * 商品毎の売り上げを二次元配列にして返す。
	 * @param 日付指定
	 * @return tbl
	 */

	public static ArrayList<ArrayList<String>> earningVendingProduct(String vending_ID){

		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT p.name, COUNT(*)" +
				" FROM earnings e" +
				" INNER JOIN vending v ON v.id = e.vending_id" +
				" INNER JOIN product p ON p.id = e.product_id" +
				" INNER JOIN member m ON m.id = e.member_id" +
				" WHERE v.id = '"+ vending_ID +"'" +
				" AND date >= '"+ strdate + "'" +
				" GROUP BY p.name" +
				" ORDER BY COUNT(*) DESC;";



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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningSexVendingProduct(String vending_ID,String sex){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT p.name, COUNT(*)" +
				" FROM earnings e" +
				" INNER JOIN vending v ON v.id = e.vending_id" +
				" INNER JOIN product p ON p.id = e.product_id" +
				" INNER JOIN member m ON m.id = e.member_id" +
				" WHERE v.id = '"+ vending_ID +"'" +
				" AND date >= '"+ strdate + "'" +
				" AND m.sex = '"+ sex + "'" +
				" GROUP BY p.name" +
				" ORDER BY COUNT(*) DESC;";



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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningAgeVendingProduct(String vending_ID,String age){

		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;
        String sql = "";

        if(age == "60"){
        	sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
        			" FROM earnings e"+
        			" INNER JOIN product p ON p.id = e.product_id"+
        			" INNER JOIN member m ON m.id = e.member_id"+
        			" INNER JOIN ("+
        			" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        			" CASE WHEN month( now( ) ) > birthmonth"+
        			" THEN 0"+
        			" ELSE ("+

    				" CASE WHEN month( now( ) ) = birthmonth"+
    				" THEN ("+

    				" CASE WHEN day( now( ) ) >= birthday"+
    				" THEN 0"+
    				" ELSE 1"+
    				" END )"+
    				" ELSE 1"+
    				" END"+
    				" )"+
    				" END"+
    				" ) AS age"+
    				" FROM member"+
    				")age ON m.id = age.id"+
    				" WHERE vending_id = '"+ vending_ID +"'" +
    				" AND date >= '"+ strdate + "'" +
    				" AND age >= "+ age + "" +
    				" GROUP BY p.name"+
    				" ORDER BY COUNT( * ) DESC;";

        }else{


        	sql = "SELECT p.name, COUNT( * ) as c_earnings"+
        		" FROM earnings e"+
        		" INNER JOIN product p ON p.id = e.product_id"+
        		" INNER JOIN member m ON m.id = e.member_id"+
        		" INNER JOIN ("+
        		"SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        		" CASE WHEN month( now( ) ) > birthmonth"+
        		" THEN 0"+
        		" ELSE ("+

				" CASE WHEN month( now( ) ) = birthmonth"+
				" THEN ("+

				" CASE WHEN day( now( ) ) >= birthday"+
				" THEN 0"+
				" ELSE 1"+
				" END )"+
				" ELSE 1"+
				" END"+
				" )"+
				" END"+
				" ) AS age"+
				" FROM member"+
				") age ON m.id = age.id"+
				" WHERE vending_id = '"+ vending_ID +"'" +
				" AND date >= '"+ strdate + "'" +
				" AND age <= "+ age + " AND age > "+ intage +"" +
				" GROUP BY p.name"+
				" ORDER BY COUNT( * ) DESC;";
        }



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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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

	public static ArrayList<ArrayList<String>> earningSexAgeVendingProduct(String vending_ID,String sex,String age){

		String sql = "";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;

        if(age == "60"){
        	sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
        			" FROM earnings e"+
        			" INNER JOIN product p ON p.id = e.product_id"+
        			" INNER JOIN member m ON m.id = e.member_id"+
        			" INNER JOIN ("+
        			" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        			" CASE WHEN month( now( ) ) > birthmonth"+
        			" THEN 0"+
        			" ELSE ("+

    				" CASE WHEN month( now( ) ) = birthmonth"+
    				" THEN ("+

    				" CASE WHEN day( now( ) ) >= birthday"+
    				" THEN 0"+
    				" ELSE 1"+
    				" END )"+
    				" ELSE 1"+
    				" END"+
    				" )"+
    				" END"+
    				" ) AS age"+
    				" FROM member"+
    				")age ON m.id = age.id"+
    				" WHERE vending_id = '"+ vending_ID +"'" +
    				" AND date >= '"+ strdate + "'" +
    				" AND age >= "+ age + "" +
    				" AND m.sex = "+ sex + "" +
    				" GROUP BY p.name"+
    				" ORDER BY COUNT( * ) DESC;";

        }else{


        	sql = "SELECT p.name, COUNT( * ) as c_earnings"+
        		" FROM earnings e"+
        		" INNER JOIN product p ON p.id = e.product_id"+
        		" INNER JOIN member m ON m.id = e.member_id"+
        		" INNER JOIN ("+
        		"SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        		" CASE WHEN month( now( ) ) > birthmonth"+
        		" THEN 0"+
        		" ELSE ("+

				" CASE WHEN month( now( ) ) = birthmonth"+
				" THEN ("+

				" CASE WHEN day( now( ) ) >= birthday"+
				" THEN 0"+
				" ELSE 1"+
				" END )"+
				" ELSE 1"+
				" END"+
				" )"+
				" END"+
				" ) AS age"+
				" FROM member"+
				") age ON m.id = age.id"+
				" WHERE vending_id = '"+ vending_ID +"'" +
				" AND date >= '"+ strdate + "'" +
				" AND age <= "+ age + " AND age > "+ intage +"" +
				" AND m.sex = "+ sex + "" +
				" GROUP BY p.name"+
				" ORDER BY COUNT( * ) DESC;";
        }




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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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

	//これより下、自販機ごとかつ日付指定。


	public static ArrayList<ArrayList<String>> earningVendingFixeddateProduct(String vending_ID,String date,String date2){
		String sql ="";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
					" FROM earnings e" +
					" INNER JOIN vending v ON v.id = e.vending_id" +
					" INNER JOIN product p ON p.id = e.product_id" +
					" INNER JOIN member m ON m.id = e.member_id" +
					" WHERE v.id = '"+ vending_ID +"'" +
					" AND date >= '"+ date + "' AND date <= '" + date2 +"'" +
					" GROUP BY p.name" +
					" ORDER BY COUNT(*) DESC;";
			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
						" FROM earnings e" +
						" INNER JOIN product p ON p.id = e.product_id" +
						" INNER JOIN member m ON m.id = e.member_id" +
						" WHERE v.id = '"+ vending_ID +"'" +
						" AND date <= '"+ date + "' AND date <='"+ strdate +"'" +
						" GROUP BY p.name" +
						" ORDER BY COUNT(*) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
						" FROM earnings e" +
						" INNER JOIN product p ON p.id = e.product_id" +
						" INNER JOIN member m ON m.id = e.member_id" +
						" WHERE v.id = '"+ vending_ID +"'" +
						" AND date <= '" + date2 +"'" +
						" GROUP BY p.name" +
						" ORDER BY COUNT(*) DESC;";

	}





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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningSexVendingFixeddateProduct(String vending_ID,String date,String date2,String sex){
		String sql ="";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
					" FROM earnings e" +
					" INNER JOIN vending v ON v.id = e.vending_id" +
					" INNER JOIN product p ON p.id = e.product_id" +
					" INNER JOIN member m ON m.id = e.member_id" +
					" WHERE v.id = '"+ vending_ID +"'" +
					" AND date >= '"+ date + "' AND date <= '" + date2 +"'" +
					" AND m.sex = '"+ sex + "'" +
					" GROUP BY p.name" +
					" ORDER BY COUNT(*) DESC;";
			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
						" FROM earnings e" +
						" INNER JOIN product p ON p.id = e.product_id" +
						" INNER JOIN member m ON m.id = e.member_id" +
						" WHERE v.id = '"+ vending_ID +"'" +
						" AND date <= '"+ date + "' AND date <='"+ strdate +"'" +
						" AND m.sex = '"+ sex + "'" +
						" GROUP BY p.name" +
						" ORDER BY COUNT(*) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
						" FROM earnings e" +
						" INNER JOIN product p ON p.id = e.product_id" +
						" INNER JOIN member m ON m.id = e.member_id" +
						" WHERE v.id = '"+ vending_ID +"'" +
						" AND date <= '" + date2 +"'" +
						" AND m.sex = '"+ sex + "'" +
						" GROUP BY p.name" +
						" ORDER BY COUNT(*) DESC;";

	}





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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningAgeVendingFixeddateProduct(String vending_ID,String date,String date2,String age){
		String sql ="";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
					" FROM earnings e" +
					" INNER JOIN vending v ON v.id = e.vending_id" +
					" INNER JOIN product p ON p.id = e.product_id" +
					" INNER JOIN member m ON m.id = e.member_id" +
					" WHERE vending_id = '"+ vending_ID +"'" +
					" AND date >= '"+ date + "' AND date <= '" + date2 +"'" +
					" AND sex = '"+ age + "'" +
					" GROUP BY p.name" +
					" ORDER BY COUNT(*) DESC;";
			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
						" FROM earnings e" +
						" INNER JOIN product p ON p.id = e.product_id" +
						" INNER JOIN member m ON m.id = e.member_id" +
						" WHERE vending_id = '"+ vending_ID +"'" +
						" AND date <= '"+ date + "' AND date <='"+ strdate +"'" +
						" AND sex = '"+ age + "'" +
						" GROUP BY p.name" +
						" ORDER BY COUNT(*) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT(*)" +
						" FROM earnings e" +
						" INNER JOIN product p ON p.id = e.product_id" +
						" INNER JOIN member m ON m.id = e.member_id" +
						" WHERE vending_id = '"+ vending_ID +"'" +
						" AND date <= '" + date2 +"'" +
						" AND sex = '"+ age + "'" +
						" GROUP BY p.name" +
						" ORDER BY COUNT(*) DESC;";

	}





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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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

	public static ArrayList<ArrayList<String>> earningSexAgeVendingFixeddateProduct(String vending_ID,String date,String date2,String sex,String age){
		String sql ="";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;
		if(age == "60"){
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age >= "+ age + "" +
	    				" AND m.sex = "+ sex + "" +
	    				" AND vending_id = "+ vending_ID + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";

			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND m.sex = "+ sex + "" +
		    				" AND age >= "+ age + "" +
		    				" AND vending_id = "+ vending_ID + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age >= "+ age + "" +
		    				" AND m.sex = "+ sex + "" +
		    				" AND vending_id = "+ vending_ID + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}else{
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age <= "+ age + " AND age > "+ intage +"" +
	    				" AND m.sex = "+ sex + "" +
	    				" AND vending_id = "+ vending_ID + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";
			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" AND m.sex = "+ sex + "" +
		    				" AND vending_id = "+ vending_ID + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" AND m.sex = "+ sex + "" +
		    				" AND vending_id = "+ vending_ID + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}









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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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


	//これより下、エリアごとでの取得



	public static ArrayList<ArrayList<String>> earningAreaProduct(String area){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT p.name, COUNT(*)" +
				" FROM earnings e" +
				" INNER JOIN vending v ON v.id = e.vending_id" +
				" INNER JOIN product p ON p.id = e.product_id" +
				" INNER JOIN member m ON m.id = e.member_id" +
				" WHERE area_id = "+ area +"" +
				" AND date >= '"+ strdate + "'" +
				" GROUP BY p.name" +
				" ORDER BY COUNT(*) DESC;";



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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningSexAreaProduct(String area,String sex){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
		String sql = "SELECT p.name, COUNT(*)" +
				" FROM earnings e" +
				" INNER JOIN vending v ON v.id = e.vending_id" +
				" INNER JOIN product p ON p.id = e.product_id" +
				" INNER JOIN member m ON m.id = e.member_id" +
				" WHERE area_id = '"+ area +"'" +
				" AND date >= '"+ strdate + "'" +
				" AND m.sex = '"+ sex + "'" +
				" GROUP BY p.name" +
				" ORDER BY COUNT(*) DESC;";



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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningAgeAreaProduct(String area,String age){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;
        String sql = "";

        if(age == "60"){
        	sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
        			" FROM earnings e"+
        			" INNER JOIN product p ON p.id = e.product_id"+
        			" INNER JOIN member m ON m.id = e.member_id"+
        			" INNER JOIN vending v ON v.id = e.vending_id"+
        			" INNER JOIN ("+
        			" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        			" CASE WHEN month( now( ) ) > birthmonth"+
        			" THEN 0"+
        			" ELSE ("+

    				" CASE WHEN month( now( ) ) = birthmonth"+
    				" THEN ("+

    				" CASE WHEN day( now( ) ) >= birthday"+
    				" THEN 0"+
    				" ELSE 1"+
    				" END )"+
    				" ELSE 1"+
    				" END"+
    				" )"+
    				" END"+
    				" ) AS age"+
    				" FROM member"+
    				")age ON m.id = age.id"+
    				" WHERE area_id = '"+ area +"'" +
    				" AND date >= '"+ strdate + "'" +
    				" AND age >= "+ age + "" +
    				" GROUP BY p.name"+
    				" ORDER BY COUNT( * ) DESC;";

        }else{


        	sql = "SELECT p.name, COUNT( * ) as c_earnings"+
        		" FROM earnings e"+
        		" INNER JOIN product p ON p.id = e.product_id"+
        		" INNER JOIN member m ON m.id = e.member_id"+
        		" INNER JOIN vending v ON v.id = e.vending_id"+
        		" INNER JOIN ("+
        		"SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        		" CASE WHEN month( now( ) ) > birthmonth"+
        		" THEN 0"+
        		" ELSE ("+

				" CASE WHEN month( now( ) ) = birthmonth"+
				" THEN ("+

				" CASE WHEN day( now( ) ) >= birthday"+
				" THEN 0"+
				" ELSE 1"+
				" END )"+
				" ELSE 1"+
				" END"+
				" )"+
				" END"+
				" ) AS age"+
				" FROM member"+
				") age ON m.id = age.id"+
				" WHERE area_id = '"+ area +"'" +
				" AND date >= '"+ strdate + "'" +
				" AND age <= "+ age + " AND age > "+ intage +"" +
				" GROUP BY p.name"+
				" ORDER BY COUNT( * ) DESC;";
        }





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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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

	public static ArrayList<ArrayList<String>> earningSexAgeAreaProduct(String area,String sex,String age){
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		cl.add(Calendar.MONTH, -12);
		String strdate = (nowdate.format(cl.getTime()));
        strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;
        String sql = "";

        if(age == "60"){
        	sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
        			" FROM earnings e"+
        			" INNER JOIN product p ON p.id = e.product_id"+
        			" INNER JOIN member m ON m.id = e.member_id"+
        			" INNER JOIN vending v ON v.id = e.vending_id"+
        			" INNER JOIN ("+
        			" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        			" CASE WHEN month( now( ) ) > birthmonth"+
        			" THEN 0"+
        			" ELSE ("+

    				" CASE WHEN month( now( ) ) = birthmonth"+
    				" THEN ("+

    				" CASE WHEN day( now( ) ) >= birthday"+
    				" THEN 0"+
    				" ELSE 1"+
    				" END )"+
    				" ELSE 1"+
    				" END"+
    				" )"+
    				" END"+
    				" ) AS age"+
    				" FROM member"+
    				")age ON m.id = age.id"+
    				" WHERE area_id = '"+ area +"'" +
    				" AND date >= '"+ strdate + "'" +
    				" AND age >= "+ age + "" +
    				" AND m.sex = "+ sex + "" +
    				" GROUP BY p.name"+
    				" ORDER BY COUNT( * ) DESC;";

        }else{


        	sql = "SELECT p.name, COUNT( * ) as c_earnings"+
        		" FROM earnings e"+
        		" INNER JOIN product p ON p.id = e.product_id"+
        		" INNER JOIN member m ON m.id = e.member_id"+
        		" INNER JOIN vending v ON v.id = e.vending_id "+
        		" INNER JOIN ("+
        		"SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
        		" CASE WHEN month( now( ) ) > birthmonth"+
        		" THEN 0"+
        		" ELSE ("+

				" CASE WHEN month( now( ) ) = birthmonth"+
				" THEN ("+

				" CASE WHEN day( now( ) ) >= birthday"+
				" THEN 0"+
				" ELSE 1"+
				" END )"+
				" ELSE 1"+
				" END"+
				" )"+
				" END"+
				" ) AS age"+
				" FROM member"+
				") age ON m.id = age.id"+
				" WHERE area_id = '"+ area +"'" +
				" AND date >= '"+ strdate + "'" +
				" AND age <= "+ age + " AND age > "+ intage +"" +
				" AND m.sex = "+ sex + "" +
				" GROUP BY p.name"+
				" ORDER BY COUNT( * ) DESC;";
        }



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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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


	//これより下、エリアごとかつ日付指定

	public static ArrayList<ArrayList<String>> earningAreaFixeddateProduct(String area,String date,String date2){

		String sql = "";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));


		if(!date.equals("") && !date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
				" FROM earnings e" +
				" INNER JOIN vending v ON v.id = e.vending_id" +
				" INNER JOIN product p ON p.id = e.product_id" +
				" INNER JOIN member m ON m.id = e.member_id" +
				" WHERE area_id = '"+ area +"'" +
				" AND date >= '"+ date + "' AND date <= '" + date2 +"'" +
				" GROUP BY p.name" +
				" ORDER BY COUNT(*) DESC;";
		}else if(!date.equals("") && date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
					" FROM earnings e" +
					" INNER JOIN product p ON p.id = e.product_id" +
					" INNER JOIN member m ON m.id = e.member_id" +
					" WHERE area_id = '"+ area +"'" +
					" AND date <= '"+ date + "' AND date <='"+ strdate +"'" +
					" GROUP BY p.name" +
					" ORDER BY COUNT(*) DESC;";

		}else if(date.equals("") && !date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
					" FROM earnings e" +
					" INNER JOIN product p ON p.id = e.product_id" +
					" INNER JOIN member m ON m.id = e.member_id" +
					" WHERE area_id = '"+ area +"'" +
					" AND date <= '" + date2 +"'" +
					" GROUP BY p.name" +
					" ORDER BY COUNT(*) DESC;";

}


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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningSexAreaFixeddateProduct(String area,String date,String date2,String sex){

		String sql = "";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));


		if(!date.equals("") && !date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
				" FROM earnings e" +
				" INNER JOIN vending v ON v.id = e.vending_id" +
				" INNER JOIN product p ON p.id = e.product_id" +
				" INNER JOIN member m ON m.id = e.member_id" +
				" WHERE area_id = '"+ area +"'" +
				" AND date >= '"+ date + "' AND date <= '" + date2 +"'" +
				" AND m.sex = '"+ sex + "'" +
				" GROUP BY p.name" +
				" ORDER BY COUNT(*) DESC;";
		}else if(!date.equals("") && date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
					" FROM earnings e" +
					" INNER JOIN product p ON p.id = e.product_id" +
					" INNER JOIN member m ON m.id = e.member_id" +
					" WHERE v.id = '"+ area +"'" +
					" AND date <= '"+ date + "' AND date <='"+ strdate +"'" +
					" AND m.sex = '"+ sex + "'" +
					" GROUP BY p.name" +
					" ORDER BY COUNT(*) DESC;";

		}else if(date.equals("") && !date2.equals("")){
			sql = "SELECT p.name, COUNT(*)" +
					" FROM earnings e" +
					" INNER JOIN product p ON p.id = e.product_id" +
					" INNER JOIN member m ON m.id = e.member_id" +
					" WHERE v.id = '"+ area +"'" +
					" AND date <= '" + date2 +"'" +
					" AND m.sex = '"+ sex + "'" +
					" GROUP BY p.name" +
					" ORDER BY COUNT(*) DESC;";

}


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

				rec.add(rs.getString("p.name"));
				rec.add(rs.getString("COUNT(*)"));
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

	public static ArrayList<ArrayList<String>> earningAgeAreaFixeddateProduct(String area,String date,String date2,String age){

		String sql = "";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;



		if(age == "60"){
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age >= "+ age + "" +
	    				" AND area_id = "+ area + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";

			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age >= "+ age + "" +
		    				" AND area_id = "+ area + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age >= "+ age + "" +
		    				" AND area_id = "+ area + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}else{
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age <= "+ age + " AND age > "+ intage +"" +
	    				" AND area_id = "+ area + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";
			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+

						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" AND area_id = "+ area + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" AND area_id = "+ area + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}



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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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

	public static ArrayList<ArrayList<String>> earningSexAgeAreaFixeddateProduct(String area,String date,String date2,String sex,String age){

		String sql = "";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = (nowdate.format(cl.getTime()));
        intage = Integer.parseInt(age) - 10;


		if(age == "60"){
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age >= "+ age + "" +
	    				" AND m.sex = "+ sex + "" +
	    				" AND area_id = "+ area + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";

			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+

						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age >= "+ age + "" +
		    				" AND m.sex = "+ sex + "" +
		    				" AND area_id = "+ area + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+
						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age >= "+ age + "" +
		    				" AND m.sex = "+ sex + "" +
		    				" AND area_id = "+ area + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}else{
			if(!date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+

						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

	    				" CASE WHEN month( now( ) ) = birthmonth"+
	    				" THEN ("+

	    				" CASE WHEN day( now( ) ) >= birthday"+
	    				" THEN 0"+
	    				" ELSE 1"+
	    				" END )"+
	    				" ELSE 1"+
	    				" END"+
	    				" )"+
	    				" END"+
	    				" ) AS age"+
	    				" FROM member"+
	    				")age ON m.id = age.id"+
	    				" WHERE date >= '"+ date + "' AND date <= '" + date2 +"'" +
	    				" AND age <= "+ age + " AND age > "+ intage +"" +
	    				" AND m.sex = "+ sex + "" +
	    				" AND area_id = "+ area + "" +
	    				" GROUP BY p.name"+
	    				" ORDER BY COUNT( * ) DESC;";
			}else if(!date.equals("") && date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+

						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date >= '"+ date + "' AND date <='"+ strdate +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" AND m.sex = "+ sex + "" +
		    				" AND area_id = "+ area + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";

			}else if(date.equals("") && !date2.equals("")){
				sql = "SELECT p.name, COUNT( * ) as c_earnings, age"+
						" FROM earnings e"+
						" INNER JOIN product p ON p.id = e.product_id"+
						" INNER JOIN member m ON m.id = e.member_id"+
		        		" INNER JOIN vending v ON v.id = e.vending_id "+

						" INNER JOIN ("+
						" SELECT id, birthyear, birthmonth, birthday, sex, year( now( ) ) - birthyear - ("+
						" CASE WHEN month( now( ) ) > birthmonth"+
						" THEN 0"+
						" ELSE ("+

		    				" CASE WHEN month( now( ) ) = birthmonth"+
		    				" THEN ("+

		    				" CASE WHEN day( now( ) ) >= birthday"+
		    				" THEN 0"+
		    				" ELSE 1"+
		    				" END )"+
		    				" ELSE 1"+
		    				" END"+
		    				" )"+
		    				" END"+
		    				" ) AS age"+
		    				" FROM member"+
		    				")age ON m.id = age.id"+
		    				" WHERE date <= '" + date2 +"'" +
		    				" AND age <= "+ age + " AND age > "+ intage +"" +
		    				" AND m.sex = "+ sex + "" +
		    				" AND area_id = "+ area + "" +
		    				" GROUP BY p.name"+
		    				" ORDER BY COUNT( * ) DESC;";
			}
		}


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

				rec.add(rs.getString("p.name"));
				System.out.println(rs.getString("p.name"));
				rec.add(rs.getString("c_earnings"));
				System.out.println(rs.getString("c_earnings"));
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
