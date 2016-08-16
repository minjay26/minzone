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
	   
	  loadData(1);
	  
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
	    
		$.ajax({
			type:"get",
			url:"/user_comment/getComments/"+bId,
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
			        alert("sdd");
			    }
		   })
			
		  })
	})

	
	
	function loadData(page){
		$.ajax({
			url:"/user_blog/getAllBlog/"+page,
			type:"get",
			success:function(data){
				var html="<input type='hidden' id='sumPage' value='"+data.sumPage+"' />";
				   // sumPage=data.sumPage;
				 
				 $.each(data.lists,function(index,ele){
					   html+="<div class='event_con'>"+
						"<input type='hidden'  value='"+ele.bId+"' />"+
						"<div>"+
							"<a href='#'><span>"+ele.blogUser.username+"</span></a>"+
							"<p>"+ele.content+"</p>"+
						"</div>"+

						"<div>"+
							"<span>"+new Date(ele.createdDate).format('yyyy-MM-dd hh:mm:ss')+"</span>"+ 
							"<a href='#' class='pl'>"+
							      "<span class='glyphicon glyphicon-comment' aria-hidden='true'"+
								        "title='评论'>"+ele.commentCount+"</span>"+
							"</a>"+ 
							"<a href='#' class='pl'>"+ 
							      "<span class='glyphicon glyphicon-thumbs-up unfavour'"+
								        "aria-hidden='true'  title='点赞'>"+ele.favour+"</span>"+
							"</a>"+
							
						"</div>"+
						"<div class='comment_content' style='display:none;'>"+
						     "<div class='show_comment'></div>"+
						     "<textarea id='comment"+ele.bId  +"' name='comment"+ele.bId+"'></textarea>"+
						     
						     "<div class='text-center' style='position:relative'>"+
							     "<button  class='btn btn-default' style='position:absolute;padding:1px 1px;left:293px;'>评论</button>"+
						     "</div>"+
                       "</div>"+
						"<hr></hr>"+
					"</div>"
				   })
				   
				   $("#blog_content").append(html);
				 //$('#blog_content').data('sumpage',sumPage)
				 //setPaginator(sumPage,page);
			}
		})
	
	}
//	var sumPage = $('#sumPage').val();
//	alert(sumPage);
	function setPaginator(){
		
		
		$('.pagination').jqPaginator({
		    totalPages: 10,
		    visiblePages: 7,
		    currentPage: 1,
		    onPageChange: function (num, type) {
		    	
		    }
		});
	}
	
	setPaginator();
	
	
	
})
