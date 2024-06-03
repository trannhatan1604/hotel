package com.hotel.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadImage
 */
@WebServlet(urlPatterns = "/image/*")
public class LoadImage extends HttpServlet {
       
	private String imagePath;
	
	@Override
	public void init() throws ServletException {
		imagePath = "E:\\Spring MVC\\hotel\\src\\main\\webapp\\"+File.separator+"/template/admin/img";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get requested Image by pathInfo
		String requestedImage = request.getPathInfo();
		
		if(requestedImage!=null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		//Decode the file name
		File image = new File(imagePath, URLDecoder.decode(requestedImage,"UTF-8"));
		//Check exist in file system
		if(!image.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		//Get content type by file name
		String contentType = getServletContext().getMimeType(image.getName());
		
		//Check if file is actually an image
		if(contentType == null || !contentType.startsWith("image")) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(image.length()));
		
		Files.copy(image.toPath(), response.getOutputStream());
		
	}


}
