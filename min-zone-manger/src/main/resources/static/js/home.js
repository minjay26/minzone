$(function(){
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
	   
	var name="content",
	    height=100,
	    width=100,
	    items=['multiimage', 'emoticons',  'link'],
	    id="blogid";
	getEditor(id,name,height,width,items);
	
	function getEditor(id,name,height,width,items){
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name='+name+']', {
				 id:id,
				 resizeType : 0,
				 height:height+"px",
				 width:width+"%",
				 allowPreviewEmoticons : true,
				 items : items				   
			});			
			editor.sync();
			
		});
	}


	
	
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
	
//    $.ajax({
//    	'url':"user_blog/get_blogs/1"
//    	'type':GET,
//    	'success':function(data){
//    		var html="";
//    		for(var i=0; i<data.personBLogs.length; i++){
//    			html += "<div class='event_con'>"
//				+"<div>"
//					+"<a href='#'><span>"+data.personBLogs[i].blogUser.username+"</span></a>"
//					+"<p>"+data.personBLogs[i].content+"</p>"
//				+"</div>"
//				+"<div>"
//					+"<span>"+data.personBLogs[i].createdDate+"</span>"
//					+"<a href='#'> "
//					    +"<span class='glyphicon glyphicon-comment' aria-hidden='true' title='评论'>"
//					    +data.personBLogs[i].commentCount
//					    +"</span>"
//					+"</a>"
//					+"<a href="#">" 
//					   +"<span class='glyphicon glyphicon-thumbs-up' aria-hidden='true' title='点赞'>"
//					  +data.personBLogs[i].favour
//					  +"</span>"
//					+"</a>"
//				+"</div>"
//				+"<hr></hr>"
//			+"</div>"
//    		};
//    		
//    		$("#comment_content").append(html);
//    		
//    	}
//    })
    
//    $.jqPaginator('#pagination', {
//        totalPages: 100,
//        visiblePages: 10,
//        currentPage: 3,
////        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
//        onPageChange: function (num, type) {
//            $('#p1').text(type + '：' + num);
//        }
//    });
//    $.jqPaginator('#pagination1', {
//        totalPages: 100,
//        visiblePages: 10,
//        currentPage: 3,
//        prev: '<li class="prev"><a href="javascript:;">Previous</a></li>',
//        next: '<li class="next"><a href="javascript:;">Next</a></li>',
//        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
//        onPageChange: function (num, type) {
//            $('#p2').text(type + '：' + num);
//        }
//    });
	$

	$(".event_con").each(function(index){
		
		$(this).find(".glyphicon-comment").click(function(){
			var uId=$(this).parents('.event_con').find(':input').val();			
			var name="comment",
		        height=100,
		        width=40,
		        items=[ 'emoticons', 'link'],
			    editorid="comment"+uId;
		    getEditor(editorid,name,height,width,items);
			$.ajax({
				type:"get",
				url:"/user_comment/getComments/"+uId,
				success:function(data){
					alert(data);
				}
			})
			var $content=$(this).parents().children('.comment_content');
			$content.slideToggle();
		})
		//$(".comment_content").slideToggle();
	})
	
	
	
	
	
	
	
	
	
	
})
