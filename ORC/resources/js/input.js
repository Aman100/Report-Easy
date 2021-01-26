var idIndex = 1;
var iI=1;
var rowIndex=0;
function send(imageName)
{
var JSONString={};
var div=document.getElementById("od").children;
for(element of div)
{
if(element=="[object HTMLSpanElement]")
{
if(element.children[1].value=="")
{
alert('Input Required')
return;
}
JSONString[element.id]=[element.children[0].value,element.children[1].value];
}
}
request=new XMLHttpRequest();
try
{
request.onreadystatechange=finish;
request.open("POST","process?nm="+imageName+"", true);
request.setRequestHeader("Content-Type","application/json");
if(Object.keys(JSONString).length==0)
{
alert('Input Required');
return;
}
request.send(JSON.stringify(JSONString));
document.getElementById('progressIndicatorDivision').style.display='block';
}
catch(exception)
{
alert('Unable to connect to server');
}
}

function finish()
{
if(request.readyState==4)
{
document.getElementById("link").innerHTML=request.responseText;
document.getElementById('progressIndicatorDivision').style.display='none';
}
}

function imageClick(event)
{
if((event.target!="[object HTMLTextAreaElement]" && event.target!="[object HTMLInputElement]") && event.button==0)
{
var spn=document.createElement("span");
spn.setAttribute("id","ddl"+iI+++"");
spn.addEventListener('mousedown',function(elem)
{
if(elem.button==2) spn.parentNode.removeChild(spn);
});
var element = document.createElement("textarea");
var ddl=document.createElement("input");
var x=event.pageX+50;
ddl.setAttribute("type","text");
ddl.setAttribute("value","20");
ddl.setAttribute("style", "resize:both;width:50px;height:25px;position:absolute;left:"+event.pageX+"px;top:"+event.pageY+"px;");

element.setAttribute("type", "text");
element.setAttribute("value", "");
element.setAttribute("name", "nm");
element.setAttribute("id", "element"+idIndex+++"");
element.setAttribute("style", "resize:both;width:200px;height:25px;position:absolute;left:"+x+"px;top:"+event.pageY+"px;");
var cool=document.getElementById("od");
spn.appendChild(ddl);
spn.appendChild(element);
cool.appendChild(spn);
}
}