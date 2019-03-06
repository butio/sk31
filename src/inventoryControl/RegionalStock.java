package inventoryControl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Area.Area;
/**
 * Servlet implementation class RegionalStock
 */
@WebServlet("/RegionalStock")
public class RegionalStock extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegionalStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		ArrayList<ArrayList<String>> AreaList = new ArrayList<ArrayList<String>>();


		String Choise = "エリア";
		String ChoisePrice = ("area");

		AreaList = Area.AreaList();

		request.setAttribute("Choise",Choise);
		request.setAttribute("ChoisePrice",ChoisePrice);
		request.setAttribute("Result",AreaList);

		RequestDispatcher rd = request.getRequestDispatcher("/RegionalStock.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
