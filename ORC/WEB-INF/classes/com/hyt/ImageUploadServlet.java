package com.hyt;
 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class ImageUploadServlet extends HttpServlet
{ 
public ImageUploadServlet()
{
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
doPost(request,response);
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
response.setContentType("text/html");
PrintWriter out = response.getWriter();
Part part = request.getPart("imageData");
String appPath = request.getServletContext().getRealPath("");
String imagePath = appPath + "images";
File fileDir = new File(imagePath);
if (!fileDir.exists())
{
fileDir.mkdirs();
}
         
String imageName = part.getSubmittedFileName();
if(validateImage(imageName))
{
try
{
part.write(imagePath + File.separator + imageName);
RequestDispatcher requestDispatcher;
request.setAttribute("imageName",imageName);
requestDispatcher=request.getRequestDispatcher("/input.jsp");
requestDispatcher.forward(request,response);
}catch (Exception ex)
{
}
}
else
{
RequestDispatcher requestDispatcher;
request.setAttribute("typeError","Image type should be \"jpg\" or \"png\"");
requestDispatcher=request.getRequestDispatcher("/index.jsp");
requestDispatcher.forward(request,response);
}
}
     
//Validates uploaded file is Image or not
private boolean validateImage(String imageName)
{
String fileExt = imageName.substring(imageName.length()-3);
if("jpg".equals(fileExt) || "png".equals(fileExt)) return true;
return false;
}
}