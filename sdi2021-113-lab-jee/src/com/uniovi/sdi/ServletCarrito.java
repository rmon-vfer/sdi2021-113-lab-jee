package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCarrito() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		HashMap<String, Integer> carrito = 
				(HashMap<String, Integer>) request.getSession().getAttribute("carrito");
		
		if (carrito == null) { // Si no hay carrito...
			//... creamos uno y lo insertamos en sesion
			carrito = new HashMap<String, Integer>();
			request.getSession().setAttribute("carrito", carrito);
		}
		
		String producto = request.getParameter("producto");
		String remove = request.getParameter("remove");
		
		if ( remove != null && remove.toLowerCase().equals("true") ) {
			// Eliminar el producto del carrito
			if (carrito.get( producto ) != null) {
				carrito.remove( producto );
			}
		} else {
			if (producto != null) {
				insertarEnCarrito(carrito, producto);
			}
		}
		
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void insertarEnCarrito(Map<String, Integer> carrito, String claveProducto) {
		if(carrito.get(claveProducto) == null) {
			carrito.put(claveProducto, new Integer(1));
			
		} else {
			int numeroArticulos = (Integer) carrito.get(claveProducto).intValue();
			carrito.put(claveProducto, new Integer(numeroArticulos +1));
		}
	}	
	
	private String carritoEnHTML(Map<String, Integer> carrito) {
		String carritoEnHTML = "";
		for ( String key : carrito.keySet() ) {
			carritoEnHTML += "<p>[" + key + "], " + carrito.get(key) + " unidades</p>";
		}
		return carritoEnHTML;
	}
}
