<%@ page import="static com.hyt.configuration.ApplicationConfiguration.*" %>
<jsp:include page='htmlTopSection.jsp' />
<div id='progressIndicatorDivision' style="position:absolute;top:0px;left:0px;bottom:0px;right:0px;margin:auto;z-index:1;display:none;">
<img src='/<%=ContextName%>/resources/images/Spinner.gif' style='position:absolute;top:0px;left:0px;bottom:0px;right:0px;margin:auto;'/>
<div id='maskDivision' style="position:absolute;top:0px;left:0px;width:100%;height:100%;background-image:url('/<%=ContextName%>/resources/images/color1.png');opacity:0.5;background-repeat:repeat-xy;"></div>
</div>
<div class='outerdiv' id="od" style="background-image:url('resources/images/book.jpg');">
<div class="cool" id="div1">
<img id='image' class='image' onmousedown="imageClick(event)" src="/<%=ContextName%>/images/${imageName}" />
<input type="hidden" name="nm" id="nm" value='${imageName}'>
</div>
<div class='save'>
<button onclick="send('${imageName}')">Save</button>
<span id='link'></span>
<a href="/<%=ContextName%>/index.jsp"> | Home</a>
</div>
</div>
</body>
</html>