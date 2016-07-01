require.config({
  baseUrl : 'style',
	 shim : {
	  	'jquery-ui' : ['jquery'],
        'fileinput' : ['jquery'],
     'fileinput-zh' : ['jquery','fileinput'],
       'datepicker' : ['jquery','jquery-ui'],
    'datepicker-zh' : ['jquery','datepicker'],
               'bs' : ['jquery'],
         'validate' : ['jquery'],
      'validate-zh' : ['jquery','validate'],
           'chosen' : ['jquery','jquery-ui'],
       'ajaxUpload' : ['jquery'],
	  	'ue-config' : ['chosen','validate-zh','datepicker-zh','fileinput-zh'],
	  		   'ue' : ['ue-config'],
	  	   'ue-lan' : ['ue']
	},
	paths : {
		  	  'ue-config' : 'uEditor/ueditor.config',
 	  		 		 'ue' : 'uEditor/ueditor.all.min',
 	  		 	 'ue-lan' : 'uEditor/lang/zh-cn/zh-cn',
                 'jquery' : 'jquery/jquery-1.11.3',
              'jquery-ui' : 'jquery-ui/jquery-ui.min',           
              'fileinput' : 'upload/fileinput.min',
           'fileinput-zh' : 'upload/fileinput_locale_zh', 
             'datepicker' : 'bootstrap-datepicker/bootstrap-datepicker.min', 
          'datepicker-zh' : 'bootstrap-datepicker/bootstrap-datepicker.zh-CN.min',
                     'bs' : 'bootstrap/js/bootstrap.min',
               'validate' : 'validate/jquery.validate.min',
            'validate-zh' : 'validate/messages_cn',
                 'chosen' : 'chosen/chosen.jquery',
             'ajaxUpload' : 'ajaxUpload/ajaxfileupload',
 	  	  'ZeroClipboard' : 'uEditor/third-party/zeroclipboard/ZeroClipboard.min'
	}
});
require(['jquery','ZeroClipboard','datepicker-zh','bs','validate-zh','chosen','ajaxUpload','ue-lan'],function($,ZeroClipboard){
    $(function(){
    	
    	/*声明一个全局对象，当作命名空间*/
    	window.ns = {
			site_back: function(data){
				/*提交后返回*/
	        	data.code == 0 && !history.go(-1) || alert('请求出错，请重试');
	        },
	        ellipsis: function (str) {
	            var arr = str.split(",");       
	            for(var i = 0; i<arr.length ; i++) {            
	                $(arr[i]).each(function(){
	                	$(this).attr('title', $(this).text());
	                    if(20 < $(this).text().length) {
	                    	$(this).html($(this).text().substring(0,20) + "...");
	                    }
	                });
	            }
	        }    		
	    };
    	
    	/*ajax配置*/
    	$.ajaxSetup({cache:false});
    	$(document)
    	.ajaxStart(function(){
    		$('.loading-modal').addClass('load');
    	})
    	.ajaxStop(function(){
    		$('.loading-modal').removeClass('load');
    	});
    	
    	/*不规范的使用了不少全局变量，懒得改了,url为全局基础url*/
          window.url = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/'));
          
        /*view的 基础url*/
        var temp_url = url + '/redirect.htmls?url=';
        
        /*解决富文本编辑器的问题*/
        window['ZeroClipboard'] = ZeroClipboard;

        require.ueconfig = {
                toolbars:[
                    [ 'source','fontfamily','fontsize','|','simpleupload','link','attachment','date','time','bold','italic','underline','forecolor','backcolor','insertorderedlist','insertunorderedlist','|','justifyleft','justifycenter','justifyright','|','help']
                   ], 
                wordCount:false,
                initialContent:'请输入',
                lang:'zh-cn',
                initialFrameWidth:'80%', 
                initialFrameHeight:150,
                autoClearinitialContent:true,
                elementPathEnabled:false,
                emotionLocalization:true
        };

        function deleteData(back_path,delete_path,editable){
            if(editable=='false'){
             alert('信息不可删除');
            }else{
            if(confirm('确定要删除数据吗？')) {
                $.ajax({
                     type: 'POST',
                     url: url+delete_path,
                })
                .done(function(data){
                	data.code == 0 && $(window).trigger('hashchange') || alert('删除出错，请重试');
                })
                .fail(internal_error);
            }
          }  
        }

        window.internal_error = function(xhr,status,error){
            location.hash = '#';
            var html = xhr.status != '404' ? 
                        '<div style="text-align:left; padding: 20px;>'+xhr.responseText+'<h2>STATUS:'+status+'</h2><h2>error:'+error+'</h2></div>' :
                        '<div class="display-table col-xs-12 bg-404 over-h">\
                            <div id="errorbox">\
                                <div id="reflection">\
                                    <div id="txt">很抱歉，找不到该页面</div>\
                                    <div id="big-404">404</div>\
                                </div>\
                            </div>\
                            <div class="pic-404"><img src="images/error-pic.jpg"></div>\
                        </div>';
            $('#wj-main-content').html(html)
        }
        /*加载模板*/
        window.goUrl = function(str) {
            $.ajax({
                type: 'GET',
                url: temp_url + str,
            })
            .done(function(data){
                $('#wj-main-content').html(data); 
            })
            .fail(internal_error);
        };


        window.cadleo_data = function(data_url){
            !!location.hash.substring(1) && $.get(url + data_url, function(data) {
               data.code == 0 && !showData(data) || (data.message && !alert(data.message)||alert('请求出错，请重试'));
            });
        };

        window.load_page_data = function(data,param_id,param_name,selector){
            var opts = '';
            for(var i = 0,len = data.length; i < len; i++){
                opts += '<option value="' + data[i][param_id] + '">' + data[i][param_name] + '</option>';
            }
            $(selector).append(opts);
        };

        window.load_checkbox = function(data,param_na,param_id,param_tag,param_name,selector){
            var opts = '';
            for(var i = 1,len = data.length; i <= len; i++){
                opts += '<label><input type="checkbox" name="'+ param_na +'"  id="' + param_tag + data[i-1][param_id] + '" value="' + data[i-1][param_id] + '" required>' + data[i-1][param_name] + '</label>';
                if(i % 5 == 0) {
                    opts += '<br>';
                }
            }
            $(selector).append(opts);
        };
       
		$(window).on('hashchange',function(e){
			if(location.hash.substring(1)) {
				goUrl(location.hash.substring(1));
			}
		});
		
		/*编辑*/
	    $(document).on('click', '.wj_edit_data', function(e){
	    	sessionStorage.setItem('edit_id', $(this).attr('edit_id'));
	    	if($(this).attr('editable') == 'false'){
       		 	alert('信息不可修改');
       		 	e.preventDefault();
       	 	}			
		});
	    
	    /*菜单点击加载页面，重复点击相同按钮也需要刷新*/
        $(document).on('click', '.child-item.text-hovered a', function(e){
            (location.hash == $(this).attr('href')) && goUrl(location.hash.substring(1));;
        });
        
        /*返回按钮*/
        $(document).on('click', '#return_index_btn', function(e){
            history.go(-1);
            e.preventDefault();
        });
        
        /*删除*/
		$(document).on('click', '.wj_delete_data', function(){
			var _this=$(this);
			deleteData(_this.attr('back_path'),_this.attr('delete_path'),_this.attr('editable'));
		});
    	
		/*详情页*/
       	$(document).on('click','.wj_view_data',function(e){
            var _this = $(this)
            sessionStorage.setItem('edit_id', _this.attr('edit_id'));
            _this.attr('detail_path') && $.ajax({
                                                url: _this.attr('detail_path'),
                                                type: 'GET',
                                            })
                                            .done(function(data){
                                                $('#wj-main-content').html(data); 
                                            })
                                            .fail(internal_error);

       	});
    	
       	/*修改状态*/
    	$(document).on('click','#modal-save-btn',function(){
    		var self = $(this);
    		self.attr('new_status',$('[name="new_status"]').val());
    		$.ajax({
    			type: 'GET',
    			url: url+self.attr('fix_path'),
    			data: {new_status: self.attr('new_status'),competition_id: self.attr('competition_id')},
    		})
            .done(function(data){
                    data.code == 0 && !goUrl('/competition/index')||alert('无赛事报名表，无法修改');
                })
            .fail(internal_error)
    	});
    	
    	/*搜索*/
    	$(document).on('submit','#search_form',function(){
	       	var search_path = $(this).attr('search_path');
	            $.ajax({
	                 type: 'POST',
	                 url: url + search_path,
	                 data: $('#search_form').serialize(),
	             })
	             .done(function(data){
	                	data.code == 0 && showData(data);
                 })
                 .fail(internal_error);
	        return false;//阻止表单提交
    	});
    	
    	$(document).on('click', '.wj_cog_data', function(){
    		var _this=$(this);
    		sessionStorage.setItem('edit_id', _this.attr('edit_id'));
    		if(_this.attr('data-apply')=='confirm_pass'){
    			if(confirm("确定要通过审核吗？")) {
    	             $.ajax({
    	                  type: 'POST',
    	                  url: url+_this.attr('set_path'),
    	                  data: {user_id:_this.attr('user_id'),club_id:_this.attr('club_id')},
    	              })
    	              .done(function(data){
	                	 data.code == 0 && !(alert('审核通过'),goUrl(_this.attr('back_path'))) || alert('请求出错，请重试');
	                  })
	                  .fail(internal_error);
    	         }
    			return false;
    		} else if(true){
    			location.hash = '#' + $(this).attr('cog_path');
    		}
    	});
    	
    	$(document).on('change','#fileToUpload',function(){
    		var fileName = '';
    		if($(this).val().lastIndexOf('\\') !=-1){
    			fileName = $(this).val().substring($(this).val().lastIndexOf('\\')+1);
    		} else {
    			fileName = $(this).val().substring($(this).val().lastIndexOf('/')+1);
    		}
    		$(this).parent().siblings('.file-path').text('您选择的文件为：'+fileName).attr('title',fileName);
    	});
    	
    	$(document).on('click','.upload-btn',function() {
    		var parent = $(this).parent();
    		if(!$(this).siblings().find('#fileToUpload').val()){
    			parent.find('.file-path').text('请先选择图片');
    			return ;
    		}
    		var val = $(this).siblings().find('#fileToUpload').val();
    		if(!/^(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$/.test(val.substring(val.lastIndexOf('.')))){
    			$(this).siblings().find('#fileToUpload').val('');
    			$(this).siblings('.file-path').text('只能上传后缀名为".jpg"，".jpeg"，".png"，".gif"，".bmp"的文件');
    			return ;
    		}
    		var path = $(this).siblings('.file-path');
        	$.ajaxFileUpload({
	   	    	 url : 'picUpload.htmls',
	   	    	 secureuri : false,
	   	    	 fileElementId : 'fileToUpload',
	   	    	 parentNode: parent,
	   	    	 dataType : 'json',
	   	    	 success : function(data, status) {
	   	    		parent.parent().parent().find('.viewImg').attr('src','pic.htmls?p='+data.picUrl).removeClass('collapse');
	   	    		parent.find('#submit_file').val(data.picUrl);
	   	    	 },
	   	    	 error : function(xhr, status, error) {
	   	    		path.text('服务器错误，上传出错，请重试');
	   	    	 }
       		});
        	path.text('');
        });
    	
        /*登出*/
    	$(document).on('click','#un-login',function(){
            var str = url + $(this).data('path');
            $.ajax({
                 type: 'get',
                 url: str,
                 dataType: 'html',
            })
            .done(function(data){
            	location.href = url || '/';
            })
            .fail(internal_error);
    	});
    	
        $.ajax({
            type: 'get',
            url: url + '/init.htmls',
        })
        .done(function(data){
            if(data.code == '0'){
                $('.user-name').html(data.data.user.user_real_name);
                var main_menu = '',
                     children = data.data.menu_tree.children,
                	 club_sum = '',
                	club_data = data.data.clubRank;
                for(var j = 0; j < club_data.length; j++){
                	club_sum += '<tr>\
									<td>'+ (club_data[j].rank||'') +'</td>\
									<td>'+ (club_data[j].club_name||'') +'</td>\
									<td>'+ (club_data[j].club_people_number||'') +'</td>\
								</tr>'
                }
                
                $('.word-doc tbody').html(club_sum);
                
                for (var i = 0, len = children.length; i < len; i++) {
                    main_menu += '<li class="cell-list">\
                                    <div class="text-hovered" data-toggle="collapse"\
                                        data-target="#all-order-'+ children[i].menu_id +'" >\
                                        <i class="fa fa-file-text-o"></i><a href="javascript:void(0);">'+ children[i].menu_name +'</a>\
                                    </div>\
                                    <ul class="retract collapse" id="all-order-'+ children[i].menu_id +'">';
                    for (var j = 0, leng = children[i].children.length; j < leng; j++) {    
                        main_menu += '<li class="child-item text-hovered">\
                                        <a href="#'+ children[i].children[j].menu_url +'" class="" title="">'+ children[i].children[j].menu_name +'</a>\
                                      </li>';
                    }
                    main_menu += '</ul></li>';
                }
                $('#main-menu').html(main_menu);
                $('#wj-header,.site-content,#wj-footer').removeClass('hide');
                $('.loading-modal').insertAfter($('#wj-main-content'));
            } else {
                location.href = url || '/';
            }
        })
        .fail(internal_error);
        
        /*F5刷新时加载数据*/
        setTimeout(function(){
        	$(window).trigger('hashchange');
        });
        
               
        /*低版本浏览器的发光特效*/
        function light_cad(lightCad,time){
        	lightCad.css({left:-1280}).animate({left:'120%'},time,function(){
        		lightCad.css({left:-1280});
        		light_cad(lightCad,time);
        	});
        }
        if(document.body.style.transition == undefined){
        	var lightCad = $('<div class="light-cad"></div>').appendTo('.welcome-content').css({height:2000});
        	lightCad.css({opacity: 0}).animate({left:'120%'},300,function(){
        		lightCad.css({opacity: 1});
        		light_cad(lightCad,2400);
        	});
        }
        
        /*离线缓存*/
		var cache = window.applicationCache || window.webkitApplicationCache || window.mozApplicationCache;
		if(cache){
			if(localStorage.getItem('siteCacheTag') !== 'false'){
				localStorage.setItem('siteCacheTag','true');
			}		

			cache.addEventListener('error', function (e){
				if(localStorage.getItem('siteCacheTag') === 'false'){
					if(confirm('加载缓存出错，是否重新加在页面？')){
						location.reload();
					}
				} else {
					console.log('缓存出错，请查看清单文件');
				}
				localStorage.setItem('siteCacheTag','true');
			}); 
	 		cache.addEventListener('downloading', function(){
	 			localStorage.setItem('siteCacheTag','true');
	 		}); 
			cache.addEventListener('obsolete', function(){
				localStorage.setItem('siteCacheTag','false');
			});  
			cache.addEventListener('updateready', function(e) { 
				if (cache.status == cache.UPDATEREADY) { 
					cache.swapCache(); 
					if (confirm('缓存更新完毕，是否刷新页面以获得更好的体验?')) { 
						location.reload(); 
					} else { 
					
					} 
				}
			}); 
		}
	});
});