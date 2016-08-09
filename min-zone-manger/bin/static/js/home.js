$(function(){
	
	
//	   var header = $("meta[name='_csrf_header']").attr("content");
//	   var token = $("meta[name='_csrf']").attr("content");
//	   $(document).ajaxSend(function (e, xhr, options) {
//	       try {
//	           xhr.setRequestHeader(header, token);
//	       }
//	       catch (err) {
//	    	   alert(err);
//	       }
//	   });
	   
	   
	   
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			resizeType : 0,
			 height:"10px",
			 width:"400px",
			allowPreviewEmoticons : true,
			allowImageUpload : true,
			uploadJson:"user_blog/imageUpload",
			items : [
				 

			         'multiimage', 'emoticons',  'link',]						   
		});	
		editor.sync();
		
	});
	
	
	$("#submit").click(function(){		
		 var content= editor.html();
			$.ajax({
				type:"POST",
				url:"user_blog/submit",
				data:{content:content},				
				success:function(result){
					location.href ="/home";
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					
				}
			})
	})
	
	
	
	
	
	
	
	
	
	
	
})
