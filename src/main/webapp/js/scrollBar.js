(function($){
	$.fn.scrollWidth=function(){
		return this[0]?this[0].scrollWidth:0;
	};

	$.fn.scrollMaxLeft=function(){
		var maxLeft=this.scrollWidth()-this.height();
		return maxLeft>0?maxLeft:0;
	};

	$.fn.scrollHeight=function(){
		return this[0]?this[0].scrollHeight:0;
	};

	$.fn.scrollMaxTop=function(){
		var maxTop=this.scrollHeight()-this.height();
		return maxTop>0?maxTop:0;
	};
	
	var mCustomScrollbar=$.fn.mCustomScrollbar;
	$.fn.mCustomScrollbar=function(method){
		var args=false;
		if(method=='update'){
			args=Array.prototype.slice.call(arguments,1);
			this.filter('.mCustomScrollbar').each(function(){
				$(this).data('scrollBar_OnUpdateBeforeHandler').apply(this,args);
			});
		}
		if(typeof(method)=='object'){
			this.not('.mCustomScrollbar').each(function(){
				$(this).data("scrollBar_OnUpdateBeforeHandler",method.updateBeforeHandler);
				$(this).data("scrollBar_OnUpdateAfterHandler",method.updateAfterHandler);
			});
		}
		try{
			mCustomScrollbar.apply(this,arguments);
		}catch(e){}
		if(args){
			this.filter('.mCustomScrollbar').each(function(){
				$(this).data('scrollBar_OnUpdateAfterHandler').apply(this,args);
			});
		}
		return this;
	};

	$.fn.scrollBar=function(isSourceSelector,opt){
		this.not('.mCustomScrollbar').mCustomScrollbar($.extend(true,{
			set_width:false, /*optional element width: boolean, pixels, percentage*/
			set_height:false, /*optional element height: boolean, pixels, percentage*/
			horizontalScroll:false, /*scroll horizontally: boolean*/
			scrollInertia:300, /*scrolling inertia: integer (milliseconds)*/
			scrollEasing:"easeOutCirc", /*scrolling easing: string*/
			mouseWheel:"pixels", /*mousewheel support and velocity: boolean, "auto", integer, "pixels"*/
			mouseWheelPixels:60, /*mousewheel pixels amount: integer*/
			autoDraggerLength:true, /*auto-adjust scrollbar dragger length: boolean*/
			scrollButtons:{ /*scroll buttons*/
				enable:false, /*scroll buttons support: boolean*/
				scrollType:"continuous", /*scroll buttons scrolling type: "continuous", "pixels"*/
				scrollSpeed:20, /*scroll buttons continuous scrolling speed: integer*/
				scrollAmount:40 /*scroll buttons pixels scroll amount: integer (pixels)*/
			},
			advanced:{
				updateOnBrowserResize:true, /*update scrollbars on browser resize (for layouts based on percentages): boolean*/
				updateOnContentResize:true, /*auto-update scrollbars on content resize (for dynamic content): boolean*/
				autoExpandHorizontalScroll:false, /*auto expand width for horizontal scrolling: boolean*/
				autoScrollOnFocus:true /*auto-scroll on focused elements: boolean*/
			},
			callbacks:{
				onScrollStart:function(){}, /*user custom callback function on scroll start event*/
				onScroll:function(){}, /*user custom callback function on scroll event*/
				onTotalScroll:function(){}, /*user custom callback function on scroll end reached event*/
				onTotalScrollBack:function(){}, /*user custom callback function on scroll begin reached event*/
				onTotalScrollOffset:0, /*scroll end reached offset: integer (pixels)*/
				whileScrolling:false, /*user custom callback function on scrolling event*/
				whileScrollingInterval:0 /*interval for calling whileScrolling callback: integer (milliseconds)*/
			},updateBeforeHandler:function(){},updateAfterHandler:function(){}
		},opt));
		if(typeof(opt)=='string'){
			this.mCustomScrollbar.apply(this,Array.prototype.slice.call(arguments,1));
		}
		return isSourceSelector?this:this.find('.mCustomScrollBox > .mCSB_container');
	};
})(jQuery);						
