$(function(){
	
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			resizeType : 0,
			 height:"10px",
			 width:"400px",
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [
				 
				 'emoticons', 'image', 'link']						   
		});	
		editor.sync();
		
	});
	
	
	$("#submit").click(function(){		
		 var content= editor.html();
		//var content= $("#editor_id2").val();
			alert(content);
			$.ajax({
				type:"POST",
				url:"user_blog/submit",
				data:content,
				success:function(e){
					alert(e);
				}
				
			})
	})
	
	
	
	
	
	
	
	
	
	
	
})
