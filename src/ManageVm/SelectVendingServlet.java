package ManageVm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Vm.Vm;

/**
 * Servlet implementation class SelectVendingServlet
 */
@WebServlet("/SelectVendingServlet")
public class SelectVendingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectVendingServlet() {
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

		ArrayList<ArrayList<String>> VendingList = new ArrayList<ArrayList<String>>();


		VendingList = Vm.VmList();
		System.out.println(VendingList);
		String Choise = ("自販機");
		System.out.println(Choise);

		String ChoisePrice = ("vending");



		request.setAttribute("ChoisePrice",ChoisePrice);
		request.setAttribute("Choise",Choise);
		request.setAttribute("Result",VendingList);

		RequestDispatcher rd = request.getRequestDispatcher("SelectVending.jsp");
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
