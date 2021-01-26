package com.hyt;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
import java.io.*;
import com.hyt.configuration.*;
public class ProcessServlet extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
try
{
String imageName=request.getParameter("nm");
System.out.println("IMAGE_NAME : " + imageName);
InputStream inputStream=request.getInputStream();
StringBuffer stringBuffer=new StringBuffer();
String JSONString;
int x;
while(true)
{
x=inputStream.read();
if(x==-1) break;
stringBuffer.append((char)x);
}
JSONString=stringBuffer.toString();
JsonParser jsonParser=new JsonParser();
JsonObject jsonObject=jsonParser.parse(JSONString).getAsJsonObject();
System.out.println("JSONString : " + JSONString);
String s=null;
String currentDirectory;
System.out.println("PARAMETER : " + imageName + " " + request.getRealPath("/WEB-INF/classes/com/hyt"));
String filePath=request.getRealPath("/WEB-INF/classes/com/hyt/")+"ProcessImage.py";
File file;
file=new File(request.getRealPath("/")+"/data");
if(file.exists()==false) file.mkdirs();
file=new File(request.getRealPath("/")+"/data/data.json");
if(file.exists()) file.delete();
RandomAccessFile raf=new RandomAccessFile(file,"rw");
raf.writeBytes(JSONString);
raf.close();
Process p=Runtime.getRuntime().exec("python "+filePath+" "+imageName+""); //for args "python test.py arg1 arg2"
BufferedReader in=new BufferedReader(new InputStreamReader(p.getInputStream()));
BufferedReader error=new BufferedReader(new InputStreamReader(p.getErrorStream()));

PrintWriter pw=response.getWriter();
response.setContentType("text/html");

pw.println("<a href=\"/"+ApplicationConfiguration.ContextName+"/data/data.zip\">Click here to download zip file</a>");

//OUTPUT

while(true)
{
s=in.readLine();
System.out.println("OUTPUT : " + s);
if(s==null) break;
}

//ERROR
while(true)
{
s=error.readLine();
System.out.println("ERROR : " + s);
if(s==null) break;
}

System.out.println("done done");
}catch(Exception e)
{
e.printStackTrace();
}
}
}