     
var K = window.KindEditor;

		function loadData(aurl,page){
			$.ajax({
				"url":aurl+page,
				"type":"get",
				"success":function(data){
					var html="";
					//alert(data.lists);
					if(data.lists==null||data.lists==""){
						html="<div id='no_content'>此分组你还没有关注任何人哦！</div>";
					}else{
					    $.each(data.lists,function(index,ele){
						// alert(ele.isShare)
						 if(ele.isShare=="NO"){
						   html+="<div class='event_con'>"+
							"<input type='hidden'  value='"+ele.bId+"' />"+
							"<div>"+
								"<a href='#'><span>"+ele.blogUser.username+"</span></a>"+
								"<p>"+ele.content+"</p>"+
							"</div>"+

							"<div>"+
								"<span>"+new Date(ele.createdDate).format('MM月dd日 hh:mm')+"</span>"+ 
								"<a href='#' class='pl'>"+
								      "<span class='glyphicon glyphicon-comment' aria-hidden='true'"+
									        "title='评论'>"+ele.commentCount+"</span>"+
								"</a>"+ 
								"<a href='#' class='pl'>"+ 
								      "<span class='glyphicon glyphicon-thumbs-up unfavour'"+
									        "aria-hidden='true'  title='点赞'>"+ele.favour+"</span>"+
								"</a>"+
								"<a href='#' class='pl'>"+ 
							      "<span class='glyphicon glyphicon-share'"+
								        "aria-hidden='true'  title='转发'>"+ele.countShare+"</span>"+
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
						 }
						 else{
							 html+="<div class='event_con'>"+
								"<input type='hidden'  value='"+ele.bId+"' />"+
								"<div>"+
									"<a href='#'><span>"+ele.blogUser.username+"</span></a>"+
									"<p>"+ele.content+"</p>"+
								"</div>"+
                                "<div>" +
                                   "<a href='#'><span>"+ele.shareBlog.blogUser.username+"</span></a>"+
									"<p>"+ele.shareBlog.content+"</p>"+
									"<span>"+new Date(ele.shareBlog.createdDate).format('MM月dd日 hh:mm')+"</span>"+ 
									"<a href='#' class='pl'>"+
									      "<span class='glyphicon glyphicon-comment' aria-hidden='true'"+
										        "title='评论'>"+ele.shareBlog.commentCount+"</span>"+
									"</a>"+ 
									"<a href='#' class='pl'>"+ 
									      "<span class='glyphicon glyphicon-thumbs-up unfavour'"+
										        "aria-hidden='true'  title='点赞'>"+ele.shareBlog.favour+"</span>"+
									"</a>"+
									"<a href='#' class='pl'>"+ 
								      "<span class='glyphicon glyphicon-share'"+
									        "aria-hidden='true'  title='转发'>"+ele.shareBlog.countShare+"</span>"+
								    "</a>"+
                                "</div>"+
								"<div>"+
									"<span>"+new Date(ele.createdDate).format('MM月dd日 hh:mm')+"</span>"+ 
									"<a href='#' class='pl'>"+
									      "<span class='glyphicon glyphicon-comment' aria-hidden='true'"+
										        "title='评论'>"+ele.commentCount+"</span>"+
									"</a>"+ 
									"<a href='#' class='pl'>"+ 
									      "<span class='glyphicon glyphicon-thumbs-up unfavour'"+
										        "aria-hidden='true'  title='点赞'>"+ele.favour+"</span>"+
									"</a>"+
									"<a href='#' class='pl'>"+ 
								      "<span class='glyphicon glyphicon-share'"+
									        "aria-hidden='true'  title='转发'>"+ele.countShare+"</span>"+
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
						 }
					   })
					} 
					   $("#blog_content").empty().append(html);
					 //$('#blog_content').data('sumpage',sumPage)
					 //setPaginator(sumPage,page);
					 
					 
				}
			})
		
		}
		
		function setPaginator(url,sumPage){
	     	$('.pagination').jqPaginator({
			    totalPages: sumPage,
			    visiblePages: 7,
			    currentPage: 1,
			    onPageChange: function (num, type) {
			    	loadData(url,num);
			    }
			});
		}
		
		function getEachBlogComment(parent,bId){
			$.ajax({
				type:"get",
				url:"/user_comment/getComments/"+bId,
				success:function(data){
				   var html="";
				   $.each(data,function(index,ele){
					   html+="<div>" +
					         "<a href='#'><span>"+ele.commentUser.username+"</span></a>:"+					         
					         "<span>"+ele.commentContent+"</span>&nbsp&nbsp&nbsp&nbsp"+
					         "<span>"+new Date(ele.createdDate).format("yyyy-MM-dd hh:mm:ss")+"</span>"+
					         "</div>"
				   })
				  parent.find(".show_comment").empty().append(html);
				}
			})
		}
		
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

