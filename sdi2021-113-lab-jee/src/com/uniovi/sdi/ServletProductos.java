package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletProductos
 */
@WebServlet("/productos")
public class ServletProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletProductos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<Producto> productosTienda = new ProductosService().getProductos();
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		out.println("Productos en la base de datos:");
		
		for ( Producto p : productosTienda ) {
			out.println( p.toString() );
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
