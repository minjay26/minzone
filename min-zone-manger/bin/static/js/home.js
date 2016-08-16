$(function(){
	   var K = window.KindEditor;//编辑器全局变量
       var header = $("meta[name='_csrf_header']").attr("content");
	   var token = $("meta[name='_csrf']").attr("content");
	   $(document).ajaxSend(function (e, xhr, options) {
	       try {
	           xhr.setRequestHeader(header, token);
	       }
	       catch (err) {
	    	   alert(err);
	       }
	   });
	   
	
	function getEditor(id,name,height,width,items){
			return K.create('textarea[name='+name+']', {
				 id:id,
				 resizeType : 0,
				 height:height+"px",
				 width:width+"%",
				 allowPreviewEmoticons : true,
				 minHeight:"50px",
				 items : items				   
			});	
	}

	var name="content",
    height=150,
    width=100,
    items=['multiimage', 'emoticons',  'link'],
    id="blogid",
    editor =getEditor(id,name,height,width,items);
	
	$("#submit").click(function(){	
		 var content= editor.html();
			$.ajax({
				type:"post",
				url:"/user_blog/submit",
				data:{content:content},				
				success:function(result){
					location.href ="/home/1";
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					
				},	
			})
	})
	


//	$(".event_con").each(function(index){
//		
//		var $thisComment=$(this),
//            commentEditor;
//		
//		$thisComment.find(".glyphicon-comment").click(function(){
//			var uId=$(this).parents('.event_con').find(':input').val();			
//			var name="comment"+uId,
//		        height=100,
//		        width=50,
//		        items=[ 'emoticons', 'link'],
//			    editorid="comment"+uId;
//			
//			commentEditor= getEditor(editorid,name,height,width,items);
//			commentEditor.sync();
//			$.ajax({
//				type:"get",
//				url:"/user_comment/getComments/"+uId,
//				success:function(data){
//				   var html="";
//				   $.each(data,function(index,ele){
//					   html+="<div>" +
//					         "<a href='#'><span>"+ele.commentUser.username+"</span></a>:"+					         
//					         "<span>"+ele.commentContent+"</span>"+
//					         "<span>"+ele.createdDate+"</span>"+
//					         "</div>"
//				   })
//				  $thisComment.find(".show_comment").empty().append(html);
//				}
//			})
//			var $content=$(this).parents().children('.comment_content');
//			$content.slideToggle();
//		})
//		
//		$thisComment.find(".btn-default").click(function(){
//		    var id="comment"+$thisComment.find(':input').val();
//			alert(id);
//			var content=$('#'+id).val();
//			//var content=commentEditor.html();
//			//var content=$thisComment.find(".editor_comment").innerHTML;
//			alert(content);
//		})
//	})
	
	var commentEditor;
	$(".event_con").find(".glyphicon-comment").click(function(){
		var $parentId = $(this).closest('.event_con'),
		    uId=$(this).closest('.event_con').children('input').val();			
		var name="comment"+uId,
	        height=100,
	        width=50,
	        items=[ 'emoticons', 'link'],
		    editorid="comment"+uId;
		
		
		
		commentEditor = getEditor(editorid,name,height,width,items);
		commentEditor.sync();
	    
		$.ajax({
			type:"get",
			url:"/user_comment/getComments/"+uId,
			success:function(data){
			   var html="";
			   $.each(data,function(index,ele){
				   html+="<div>" +
				         "<a href='#'><span>"+ele.commentUser.username+"</span></a>:"+					         
				         "<span>"+ele.commentContent+"</span>"+
				         "<span>"+new Date(ele.createdDate).format("yyyy-MM-dd hh:mm:ss")+"</span>"+
				         "</div>"
			   })
			  $parentId.find(".show_comment").empty().append(html);
			}
		})
		var $content=$(this).parents().children('.comment_content');
		$content.slideToggle();
		
		
		
		$parentId.find(".btn-default").click(function(){
		    var bId=$parentId.children('input').val();
			var content=commentEditor.html();
		   $.ajax({
			    type:"post",
			    data:{"content":content},
			    url:"/user_comment/comment/"+bId,
			    success:function(){
			        alert("sdd").fadeOut(2000);
			    }
		   })
			
		  })
	})
	
	  
	
})
