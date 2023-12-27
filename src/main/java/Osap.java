

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DecimalFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Osap
 */
@WebServlet("/Osap")
public class Osap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Osap() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected String calculatePayment(Double A, Double r, Double n) {
    	Double month = ((r/(12*100))*A)/(1-Math.pow((1+(r/(12*100))),-n));
    	String mon = new DecimalFormat("0.0").format(month);
    	return new String("" + mon + "\n");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Writer out = response.getWriter();
		ServletContext cont = this.getServletContext();

		
		out.write("---- Monthly Payments ----\n");
		String pal = request.getParameter("principal");
		String itt = request.getParameter("interest");
		String pd = request.getParameter("period");
		double perd,inter,peri;
		
		if(pal == null || pal.isEmpty()) pal = cont.getInitParameter("defaultPrincipal");
		if(itt == null || itt.isEmpty()) itt = cont.getInitParameter("defaultInterest");
		if(pd == null || pd.isEmpty()) pd = cont.getInitParameter("defaultPeriod");
		
		perd = Double.parseDouble(pal);
		inter = Double.parseDouble(itt);
		peri = Double.parseDouble(pd);
		String monthPayFormat = calculatePayment(perd,inter,peri);
		
    	request.setAttribute("payment", monthPayFormat);
    	request.setAttribute("principal", pal);
    	request.setAttribute("interest", itt);
    	request.setAttribute("period", pd);
    	
    	HttpSession session = request.getSession(true);
    	Integer itemCount = (Integer)session.getAttribute("count");
    	if(itemCount == null) itemCount = new Integer(1);
    	else {
    		int c = itemCount;
    		itemCount = new Integer(c+1);
    	}
    	session.setAttribute("count", itemCount);
		
    	request.getRequestDispatcher("ResultView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
