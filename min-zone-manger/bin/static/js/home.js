$(function(){
       var header = $("meta[name='_csrf_header']").attr("content");
	   var token = $("meta[name='_csrf']").attr("content");
	   $(document).ajaxSend(function (e, xhr, options) {
	       try {
	           if(xhr.setRequestHeader){
	    	       xhr.setRequestHeader(header, token);
	           }else{
	               //options.accepts[header] = token;
	           }
	       }
	       catch (err) {
	    	   alert(err);
	       }
	   });
	   

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
					location.href ="/home";
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					
				},	
			})
	})
	
  $(".glyphicon-thumbs-up").click(function(){
	  var $this=$(this),
	      bId=$this.closest('.event_con').children('input').val();
	  if($this.hasClass("unfavour")){
		  $this.toggleClass("unfavour");
		  $.post("/user_blog/favour/"+bId,function(){
			  alert("favour success");
		  })
	  }else{
		  $this.toggleClass("unfavour");
		  
	  }
  })
  
  $("#blog_content").on('click',".glyphicon-thumbs-up",function(e){
	  var $thisItem = $(e.target),
	      $parentId = $thisItem.closest('.event_con'),
	      bId=$parentId.children('input').val();
	  if($thisItem.hasClass("unfavour")){
		  $thisItem.toggleClass("unfavour");
		  $.post("/user_blog/favour/"+bId,function(){
			  alert("success");
		  })
	  }else{
		  $thisItem.toggleClass("unfavour");
		  
	  }
  })
	
	var commentEditor;
	$("#blog_content").on('click',".glyphicon-comment",function(e){
		var $thisItem = $(e.target),
		    $parentId = $thisItem.closest('.event_con'),
		    bId=$parentId.children('input').val();	
		var name="comment"+bId,
	        height=100,
	        width=50,
	        items=[ 'emoticons', 'link'],
		    editorid="comment"+bId;
		
		commentEditor = getEditor(editorid,name,height,width,items);	    
		getEachBlogComment($parentId,bId)	
		var $content=$thisItem.parents().children('.comment_content');
		$content.slideToggle();
				
		$parentId.find(".btn-default").click(function(){
		    var bId=$parentId.children('input').val();
			var content=commentEditor.html();
		   $.ajax({
			    type:"post",
			    data:{"content":content},
			    url:"/user_comment/comment/"+bId,
			    success:function(){
			    	getEachBlogComment($parentId,bId)
			    }
		   })
			
		  })
	})
	
   

	$("#blog_content").on('click',".glyphicon-share",function(e){
		var $thisItem = $(e.target),
		    $parentId = $thisItem.closest('.event_con'),
	        bId=$parentId.children('input').val();	
		$.get("/user_blog/share/"+bId,function(r){
			$("#share").empty().html(r);
		})
		
	})
	
	
	
	var sumPage = $('#blog_content').attr('name');
	setPaginator(url,parseInt(sumPage));
	
	
	
$("#start_upload").on('click',function(){
    $.ajaxFileUpload({
        url: '/user/uploadHead',
        secureuri: false,
        fileElementId: 'file',
        dataType: 'json',
        // 服务器响应失败处理函数
        error: function (data, status, e){
                 alert("failed");
        },     
        // 服务器成功响应处理函数
        success: function (data, status){
                alert("success:"+status)
        }        
    });
})
	
})
