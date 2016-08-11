$(function(){
	var index = {
			init:function(){
				this.bindEvents();
			},
			
			jumpPage:function(e){
				var $Item = $(e.target).closest('li'),
				    activeUrl = $Item.children('a').data('url'),
				$.ajax({
					url:activeUrl,
					type:"get",
					success:function(result){
						$("#showBannerMsg").html(result);

					}
				})
			},

			bindEvents:function(){
				$("#left_menu>li").on('click', this.jumpPage.bind(this))
			}
	};
	
	index.init();
})





