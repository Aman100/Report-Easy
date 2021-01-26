<jsp:include page='htmlTopSection.jsp' />
<div class='outerdiv' style="background-image:url('resources/images/book.jpg');width:100%;">
<div class="cool" id="image_frame">
<div class='typeErrorStyle'>${typeError}</div>
</div>
<form action="/ORC/uploadImage" method="post" enctype="multipart/form-data">
<div class='inputstyle'>
<input type="file" name="imageData" required="required">
<input type="submit" value="Upload">
</div>
</form>
</div>
</body>
</html>

