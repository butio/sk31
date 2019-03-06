package Sales;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EarningVending
 */
@WebServlet("/EarningVending")
public class EarningVending extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EarningVending() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsp =("EarningTable.jsp");
		String choise = request.getParameter("choise");
		String action = request.getParameter("action");
		System.out.println("action");
		System.out.println(action);
		String select = request.getParameter("select");
		String age = request.getParameter("Age");
		String sex = request.getParameter("sex");
		String product = request.getParameter("product");


		String year = request.getParameter("year");
		int intyear = 0;
		int intyear2 = 0;
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year2 = request.getParameter("year2");
		String month2 = request.getParameter("month2");
		String day2 = request.getParameter("day2");
		String date = "";
		String date2 = "";
		String Message = ("");
		String sex_mes = "";
		String age_mes = "";
		String date_mes = "";
		boolean Age_flg = false;


		if(choise == null){
			choise = "";
		}

		if(sex == null){
			sex = "";
		}
		if(age == null){
			age = "";
		}
		if(product == null){
			product = "";
		}

		if(year == null){
			year = "";
		}
		if(year2 == null){
			year2 = "";
		}
		if(month == null){
			month = "";
		}
		if(month2 == null){
			month2 = "";
		}

		if(day == null){
			day = "";
		}
		if(day2 == null){
			day2 = "";
		}


		ArrayList<ArrayList<String>> EarningList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> ProductList = new ArrayList<ArrayList<String>>();



		System.out.println(action);
		System.out.println(select);
		System.out.println(choise);
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(year2);
		System.out.println(month2);
		System.out.println(day2);

		if((year.equals(year2) && month.equals(month2) && day.equals(day2)) && (year != "" && month != "" && day != "")){
			int intday2 = Integer.parseInt(day2) + 1;
			day2 = Integer.toString(intday2);

		}


		if((year != "" && month != "" && day !="") || (year != "" && month == "" && day =="")){
			if(year != null){
				date = year;
				if(year2 == "" && month =="" && day ==""){
					intyear= Integer.parseInt(year) - 1;
					year = Integer.toString(intyear);
					date= year;
					date= date += ("-");
					date= date += ("12");
					date= date += ("-");
					date= date += ("31");
				}
				if(!month.equals("")){
					date= date += ("-");
					date= date += (month);
					date= date += ("-");
					date= date += (day);
				}
			}
		}else if(!(year == "" && month == "" && day =="")){
			Message = ("期間の選択形式が誤っています。期間の選択は反映せずに表示します。");
		}

		if((year2 != "" && month2 != "" && day2 !="") || (year2 != "" && month2 == "" && day2 =="")){
			if(year2 != null){
				date2 = year2;
				if(year == "" && month2 =="" && day2 ==""){
					intyear2= Integer.parseInt(year2) + 1;
					year2 = Integer.toString(intyear2);
					date2= year2;
					date2= date2 += ("-");
					date2= date2 += ("01");
					date2= date2 += ("-");
					date2= date2 += ("01");
				}
				if(!month2.equals("")){
					date2= date2 += ("-");
					date2= date2 += (month2);
					date2= date2 += ("-");
					date2= date2 += (day2);
				}
			}
		}else if(!(year2 == "" && month2 == "" && day2 =="")){
			Message = ("期間の選択形式が誤っています。期間の選択は反映せずに表示します。");
		}



		Earnings E = new Earnings();

		if(sex.equals("")){
			sex_mes = "指定なし";
		}else if(sex.equals("1")){
			sex_mes = "男性";
		}else if(sex.equals("2")){
			sex_mes = "女性";
		}

		System.out.println("年代");
		System.out.println(age);
		if(age.equals("")){
			age_mes = "指定なし";
		}else if(age.equals("9")){
			age_mes = "10代以下";
		}else if(age.equals("19")){
			age_mes = "10代";
		}else if(age.equals("29")){
			age_mes = "20代";
		}else if(age.equals("39")){
			age_mes = "30代";
		}else if(age.equals("49")){
			age_mes = "40代";
		}else if(age.equals("59")){
			age_mes = "50代";
		}else if(age.equals("60")){
			age_mes = "60代以上";
		}

		System.out.println(age_mes);

		if(date == "" && date2 == ""){
			date_mes = "直近一年間";
		}else if(date != "" && date2 == ""){
			if(month.equals("")){
				date_mes = year;
				date_mes += "以降";
			}else{
				date_mes = date;
				date_mes += "以降";
			}
		}else if(date == "" && date2 != ""){
			if(month2.equals("")){
				date_mes = year2;
				date_mes += "以降";
			}else{
				date_mes = date2;
				date_mes += "まで";
			}
		}else if(date != "" && date2 != ""){
			date_mes = date;
			date_mes += "から";
			date_mes += date2;
		}
		if(Age_flg){
			date_mes = date;
		}

		if(action.equals("table")){

		EarningList = new ArrayList<ArrayList<String>>();


		if(choise == ""){
			if(date.equals("") && date2.equals("")){
				if((sex == "" && age == "") || (sex == null && age == null)){
				EarningList = Earnings.earningProduct();
				}else if(sex == "" && age != ""){
					EarningList = Earnings.earningAgeProduct(age);
				}else if(sex != "" && age == ""){
					EarningList = Earnings.earningSexProduct(sex);
				}else if(sex != "" && age != ""){
					EarningList = Earnings.earningSexAgeProduct(sex,age);
				}
			}else if(sex == "" && age == ""){
				EarningList = Earnings.earningFixeddateProduct(date,date2);
			}else if(sex == "" && age != ""){
				EarningList = Earnings.earningAgeFixeddateProduct(date,date2,age);
			}else if(sex != "" && age == ""){
				EarningList = Earnings.earningSexFixeddateProduct(date,date2,age);
			}else if(sex != "" && age != ""){
				EarningList = Earnings.earningSexAgeFixeddateProduct(date,date2,sex,age);
			}
			}else if(choise.equals("vending")){
				if(date.equals("") && date2.equals("")){
					if((sex == "" && age == "") || (sex == null && age == null)){
						EarningList = Earnings.earningVendingProduct(select);
					}else if(sex == "" && age != ""){
						EarningList = Earnings.earningAgeVendingProduct(select,age);
					}else if(sex != "" && age == ""){
						EarningList = Earnings.earningSexVendingProduct(select,sex);
					}else if(sex != "" && age != ""){
						EarningList = Earnings.earningSexAgeVendingProduct(select,sex,age);
					}
				}else if(sex == "" && age == ""){
					EarningList = Earnings.earningVendingFixeddateProduct(select,date,date2);
				}else if(sex == "" && age != ""){
					EarningList = Earnings.earningAgeVendingFixeddateProduct(select,date,date2,age);
				}else if(sex != "" && age == ""){
					EarningList = Earnings.earningSexVendingFixeddateProduct(select,date,date2,age);
				}else if(sex != "" && age != ""){
					EarningList = Earnings.earningSexAgeVendingFixeddateProduct(select,date,date2,sex,age);
				}
			}else if(choise.equals("area")){
				if(date.equals("") && date2.equals("")){
					if((sex == "" && age == "") || (sex == null && age == null)){
						EarningList = Earnings.earningAreaProduct(select);
					}else if(sex == "" && age != ""){
						EarningList = Earnings.earningAgeAreaProduct(select,age);
					}else if(sex != "" && age == ""){
						EarningList = Earnings.earningSexAreaProduct(select,sex);
					}else if(sex != "" && age != ""){
						EarningList = Earnings.earningSexAgeAreaProduct(select,sex,age);
					}else if(sex == "" && age == ""){
						EarningList = Earnings.earningAreaFixeddateProduct(select,date,date2);
					}
				}else if(sex == "" && age != ""){
					EarningList = Earnings.earningAgeAreaFixeddateProduct(select,date,date2,age);
				}else if(sex != "" && age == ""){
					EarningList = Earnings.earningSexAreaFixeddateProduct(select,date,date2,age);
				}else if(sex != "" && age != ""){
					EarningList = Earnings.earningSexAgeAreaFixeddateProduct(select,date,date2,sex,age);
				}
			}

		System.out.println(Message);
		System.out.println(jsp);

		}else if(action.equals("Piechart")){
			jsp = "Chart.jsp";
			if(choise == ""){
				if(date.equals("") && date2.equals("")){
					if((sex == "" && age == "") || (sex == null && age == null)){
					EarningList = Earnings.earningProduct();
					}else if(sex == "" && age != ""){
						EarningList = Earnings.earningAgeProduct(age);
					}else if(sex != "" && age == ""){
						EarningList = Earnings.earningSexProduct(sex);
					}else if(sex != "" && age != ""){
						EarningList = Earnings.earningSexAgeProduct(sex,age);
					}
				}else if(sex == "" && age == ""){
					EarningList = Earnings.earningFixeddateProduct(date,date2);
				}else if(sex == "" && age != ""){
					EarningList = Earnings.earningAgeFixeddateProduct(date,date2,age);
				}else if(sex != "" && age == ""){
					EarningList = Earnings.earningSexFixeddateProduct(date,date2,age);
				}else if(sex != "" && age != ""){
					EarningList = Earnings.earningSexAgeFixeddateProduct(date,date2,sex,age);
				}
				}else if(choise.equals("vending")){
					if(date.equals("") && date2.equals("")){
						if((sex == "" && age == "") || (sex == null && age == null)){
							EarningList = Earnings.earningVendingProduct(select);
						}else if(sex == "" && age != ""){
							EarningList = Earnings.earningAgeVendingProduct(select,age);
						}else if(sex != "" && age == ""){
							EarningList = Earnings.earningSexVendingProduct(select,sex);
						}else if(sex != "" && age != ""){
							EarningList = Earnings.earningSexAgeVendingProduct(select,sex,age);
						}
					}else if(sex == "" && age == ""){
						EarningList = Earnings.earningVendingFixeddateProduct(select,date,date2);
					}else if(sex == "" && age != ""){
						EarningList = Earnings.earningAgeVendingFixeddateProduct(select,date,date2,age);
					}else if(sex != "" && age == ""){
						EarningList = Earnings.earningSexVendingFixeddateProduct(select,date,date2,age);
					}else if(sex != "" && age != ""){
						EarningList = Earnings.earningSexAgeVendingFixeddateProduct(select,date,date2,sex,age);
					}
				}else if(choise.equals("area")){
					if(date.equals("") && date2.equals("")){
						if((sex == "" && age == "") || (sex == null && age == null)){
							EarningList = Earnings.earningAreaProduct(select);
						}else if(sex == "" && age != ""){
							EarningList = Earnings.earningAgeAreaProduct(select,age);
						}else if(sex != "" && age == ""){
							EarningList = Earnings.earningSexAreaProduct(select,sex);
						}else if(sex != "" && age != ""){
							EarningList = Earnings.earningSexAgeAreaProduct(select,sex,age);
						}else if(sex == "" && age == ""){
							EarningList = Earnings.earningAreaFixeddateProduct(select,date,date2);
						}
					}else if(sex == "" && age != ""){
						EarningList = Earnings.earningAgeAreaFixeddateProduct(select,date,date2,age);
					}else if(sex != "" && age == ""){
						EarningList = Earnings.earningSexAreaFixeddateProduct(select,date,date2,age);
					}else if(sex != "" && age != ""){
						EarningList = Earnings.earningSexAgeAreaFixeddateProduct(select,date,date2,sex,age);
					}
				}
			}else if(action.equals("Piechart_category")){
				jsp = ("Chart.jsp");
				if(choise == ""){
					if(date.equals("") && date2.equals("")){
						if((sex == "" && age == "") || (sex == null && age == null)){
						EarningList = Category.earningProduct();
						}else if(sex == "" && age != ""){
							EarningList = Category.earningAgeProduct(age);
						}else if(sex != "" && age == ""){
							EarningList = Category.earningSexProduct(sex);
						}else if(sex != "" && age != ""){
							EarningList = Category.earningSexAgeProduct(sex,age);
						}
					}else if(sex == "" && age == ""){
						EarningList = Category.earningFixeddateProduct(date,date2);
					}else if(sex == "" && age != ""){
						EarningList = Category.earningAgeFixeddateProduct(date,date2,age);
					}else if(sex != "" && age == ""){
						EarningList = Category.earningSexFixeddateProduct(date,date2,age);
					}else if(sex != "" && age != ""){
						EarningList = Category.earningSexAgeFixeddateProduct(date,date2,sex,age);
					}
					}else if(choise.equals("vending")){
						if(date.equals("") && date2.equals("")){
							if((sex == "" && age == "") || (sex == null && age == null)){
								EarningList = Category.earningVendingProduct(select);
							}else if(sex == "" && age != ""){
								EarningList = Category.earningAgeVendingProduct(select,age);
							}else if(sex != "" && age == ""){
								EarningList = Category.earningSexVendingProduct(select,sex);
							}else if(sex != "" && age != ""){
								EarningList = Category.earningSexAgeVendingProduct(select,sex,age);
							}
						}else if(sex == "" && age == ""){
							EarningList = Category.earningVendingFixeddateProduct(select,date,date2);
						}else if(sex == "" && age != ""){
							EarningList = Category.earningAgeVendingFixeddateProduct(select,date,date2,age);
						}else if(sex != "" && age == ""){
							EarningList = Category.earningSexVendingFixeddateProduct(select,date,date2,age);
						}else if(sex != "" && age != ""){
							EarningList = Category.earningSexAgeVendingFixeddateProduct(select,date,date2,sex,age);
						}
					}else if(choise.equals("area")){
						if(date.equals("") && date2.equals("")){
							if((sex == "" && age == "") || (sex == null && age == null)){
								EarningList = Category.earningAreaProduct(select);
							}else if(sex == "" && age != ""){
								EarningList = Category.earningAgeAreaProduct(select,age);
							}else if(sex != "" && age == ""){
								EarningList = Category.earningSexAreaProduct(select,sex);
							}else if(sex != "" && age != ""){
								EarningList = Category.earningSexAgeAreaProduct(select,sex,age);
							}else if(sex == "" && age == ""){
								EarningList = Category.earningAreaFixeddateProduct(select,date,date2);
							}
						}else if(sex == "" && age != ""){
							EarningList = Category.earningAgeAreaFixeddateProduct(select,date,date2,age);
						}else if(sex != "" && age == ""){
							EarningList = Category.earningSexAreaFixeddateProduct(select,date,date2,age);
						}else if(sex != "" && age != ""){
							EarningList = Category.earningSexAgeAreaFixeddateProduct(select,date,date2,sex,age);
						}
					}

			}else if(action.equals("Linechart")){
				jsp = "LineChart.jsp";

				ProductList = Chart.ChartMake.ProductList();

				if(product == ""){
					product = "指定なし";
					E.setProduct_Mes(product);
				}
				else{
					E.setProduct_Mes2(product);
				}

				if(choise == ""){
					if(date.equals("") && date2.equals("")){
						if((sex == "" && age == "") || (sex == null && age == null)){
						EarningList = Chart.ChartMake.LineChartAll();
						}else if(sex == "" && age != ""){
							EarningList = Category.earningAgeProduct(age);
						}else if(sex != "" && age == ""){
							EarningList = Category.earningSexProduct(sex);
						}else if(sex != "" && age != ""){
							EarningList = Category.earningSexAgeProduct(sex,age);
						}
					}else if(sex == "" && age == ""){
						EarningList = Category.earningFixeddateProduct(date,date2);
					}else if(sex == "" && age != ""){
						EarningList = Category.earningAgeFixeddateProduct(date,date2,age);
					}else if(sex != "" && age == ""){
						EarningList = Category.earningSexFixeddateProduct(date,date2,age);
					}else if(sex != "" && age != ""){
						EarningList = Category.earningSexAgeFixeddateProduct(date,date2,sex,age);
					}
					}else if(choise.equals("vending")){
						if(date.equals("") && date2.equals("")){
							if((sex == "" && age == "") || (sex == null && age == null)){
								EarningList = Chart.ChartMake.LineChartVendingAll(select);
							}else if(sex == "" && age != ""){
								EarningList = Category.earningAgeVendingProduct(select,age);
							}else if(sex != "" && age == ""){
								EarningList = Category.earningSexVendingProduct(select,sex);
							}else if(sex != "" && age != ""){
								EarningList = Category.earningSexAgeVendingProduct(select,sex,age);
							}
						}else if(sex == "" && age == ""){
							EarningList = Category.earningVendingFixeddateProduct(select,date,date2);
						}else if(sex == "" && age != ""){
							EarningList = Category.earningAgeVendingFixeddateProduct(select,date,date2,age);
						}else if(sex != "" && age == ""){
							EarningList = Category.earningSexVendingFixeddateProduct(select,date,date2,age);
						}else if(sex != "" && age != ""){
							EarningList = Category.earningSexAgeVendingFixeddateProduct(select,date,date2,sex,age);
						}
					}else if(choise.equals("area")){
						if(date.equals("") && date2.equals("")){
							if((sex == "" && age == "") || (sex == null && age == null)){
								EarningList = Chart.ChartMake.LineChartAreaAll(select);
							}else if(sex == "" && age != ""){
								EarningList = Category.earningAgeAreaProduct(select,age);
							}else if(sex != "" && age == ""){
								EarningList = Category.earningSexAreaProduct(select,sex);
							}else if(sex != "" && age != ""){
								EarningList = Category.earningSexAgeAreaProduct(select,sex,age);
							}else if(sex == "" && age == ""){
								EarningList = Category.earningAreaFixeddateProduct(select,date,date2);
							}
						}else if(sex == "" && age != ""){
							EarningList = Category.earningAgeAreaFixeddateProduct(select,date,date2,age);
						}else if(sex != "" && age == ""){
							EarningList = Category.earningSexAreaFixeddateProduct(select,date,date2,age);
						}else if(sex != "" && age != ""){
							EarningList = Category.earningSexAgeAreaFixeddateProduct(select,date,date2,sex,age);
						}
					}
			}



		E.setSex_Mes(sex_mes);
		E.setAge_Mes(age_mes);
		E.setDate_Mes(date_mes);

		E.setMes(Message);

		System.out.println(product);

		request.setAttribute("Product",ProductList);
		request.setAttribute("LineChart",EarningList);
		request.setAttribute("PieChart",EarningList);
		request.setAttribute("Result",EarningList);
		request.setAttribute("E",E);
		request.setAttribute("select",select);
		request.setAttribute("choise",choise);




		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		doGet(request,response);
	}

}
