<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">修改广告</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

   <form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
		<!-- ################################################################## -->
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>广告名称</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" rangelength="2,20" required name="ad_name"
                placeholder="输入广告名称" >
            </div>
        </div>
        
        <!-- ################################################################## -->
       
       
        
        
         <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>位置</span>
            <div class="col-xs-10 stretch">
               <select name="ad_location" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required>
               		<option value=""></option>
               		
               </select>
               <label id="ad_location-error" class="error" for="ad_location"></label>
            </div>
        </div>
       
        <!-- ################################################################## -->
         <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>类型</span>
            <div class="col-xs-10 stretch">
               <select name="ad_type" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required>
               		<option value=""></option>
               		<option value="1">轮播图</option>
               		<option value="2">栏位图</option>
               		<option value="3">单图</option>

               </select>
               <label id="ad_type-error" class="error" for="ad_type"></label>
            </div>
        </div>
        
        <!-- ################################################################## -->
        
          
     	<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>广告开始时间</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align time-control" id="registration_start_date" name="ad_start_time"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="广告开始时间" required>
            </div>
        </div>
        <!-- ################################################################## -->
        
         <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>广告结束时间</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align time-control" id="registration_end_date" name="ad_end_time"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="广告结束时间" required>
            </div>
        </div>
         
        <!-- ################################################################## -->        
              <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>图片像素比例</span>
            <div class="col-xs-10 stretch">
               <select name="ad_width_high" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required="required">
<!--                		<option value=""></option> -->
               </select>
               <label id="ad_width_high-error" class="error" for="ad_width_high"></label>
            </div>
        </div>
        
        <!-- ################################################################## -->        

        <!-- ################################################################## --> 
        <div class="col-xs-11 stretch form-group float-none  img-news">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>上传图片</span>
            <div class="col-xs-10 stretch">
            	<div class="col-xs-12 display-table stretch">
	            	<label class="label-file btn btn-default">
	            		选择图片<input id="fileToUpload" type="file" size="45" name="fileToUpload" accept="image/gif,image/jpg,image/jpeg,image/png">
	            	</label>
	            	<input type="text" name="submit_file" id="submit_file" required>
	            	<button type="button" class="btn btn-default upload-btn">上传</button>
	            	<label id="submit_file-error" class="error" for="submit_file"></label>
	            	<div class="file-path text-ellipsis"></div>
            	</div>
 				
 				<img class="viewImg collapse">
            </div>
        </div>
       <!-- ################################################################## -->
  
       <div class="col-xs-11 stretch form-group float-none image-ad">
            <span class="col-xs-2 align-right">图片描述</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" rangelength="2,20" name="image_text"
                placeholder="输入图片描述" >
            </div>
           
        </div>
       <!-- ################################################################## -->       
       <!-- ################################################################## -->
  
       <div class="col-xs-11 stretch form-group float-none link-ad">
            <span class="col-xs-2 align-right">信息链接</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" rangelength="2,20" name="information_link"
                placeholder="输入链接地址" >
            </div>
        </div>
        
         <!-- ################################################################## -->

   


       <!-- ################################################################## -->
                          
		<!-- 图片集 -->
        <div class="col-xs-11 stretch form-group float-none img-news-list hide">
            <span class="col-xs-2 align-right">图片列表</span>	
            <div class="col-xs-10 stretch">
            	<input type="hidden" name="image_information" value>
            	<input type="hidden" name="information_link_ad" value>
            	<button class="btn btn-default" id="clone-panel" type="button"><i class="fa fa-clone"></i>新增一条</button>
            	<button class="btn btn-default" id="delete-panel" type="button"><i class="fa fa-times"></i>删除一条</button>
            	<label class="error" data-tips="panel"></label>
            	<!-- 模版样例 -->
            	<div class="clearfix shadow-panel mt-8 panel-list-item">
					<img class="viewImg collapse pull-left">
					<div class="ov-h mt-8">
		            	<div class="display-table stretch">
			            	<label class="label-file btn btn-default">
			            		选择图片<input id="fileToUpload" type="file" size="45" name="fileToUpload" accept="image/gif,image/jpg,image/jpeg,image/png">
			            	</label>
			            	<input type="text" name="submit_file" id="submit_file">
			            	<button type="button" class="btn btn-default upload-btn">上传</button>
			            	
			            	<div class="file-path text-ellipsis"></div>
		            	</div>
		            	<textarea class="pull-left mt-8 form-control textarea_information" id="" cols="30" placeholder="请输入图片描述" style="height: 61px; resize: none;"></textarea>
	            		<textarea class="pull-left mt-8 form-control textarea_information_link" id="" cols="20" placeholder="请输入图片链接地址" style="height: 50px; resize: none;"></textarea>
	            	</div>
            	</div>
 				
            </div>
        </div>
        
  <!-- ################################################################## -->

		<input  type="hidden" value="" name="ad_id">
		<div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit" data-role="loading">保存</button>
		</div>
	</form>
    
    <script type="text/javascript">
    cadleo_data('/ad/modifyLoad.htmls?ad_id='+sessionStorage.getItem('edit_id'));
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
    		
			sessionStorage.setItem('itemHTML',$('.panel-list-item').clone().find('#submit_file').prop('disabled',false).end().wrap('<div></div>').parent().html());
    		
    		$('.viewImg').load(imgRatio);
    		
    		load_page_data(data.data.adEntity.adLocationList,'location_id','location_name','select[name="ad_location"]');
    		load_page_data(data.data.adEntity.pixel_list,'pixel','pixel','select[name="ad_width_high"]');
    		
        		//复制节点
        	$('#clone-panel').click(function(){
        		if($('[name="ad_type"]').val() == 2){
        			if($('.panel-list-item').length>=3){
        				alert('栏位图最多只能上传3张');
        				return;
        			}
        		}
        		$(sessionStorage.getItem('itemHTML')).appendTo($(this).parent()).find('.viewImg').load(imgRatio);
        	});
        	//删除节点
        	$('#delete-panel').click(function(){
        		($('.panel-list-item').length-1) && $('.panel-list-item').eq(-1).remove() || alert('只剩一条，不能再删了');
        	});
        	df.resolve();
    		return df.promise();
    	})())
    	.done(function(){
//         	$('select option').each(function(){
//     	    	data.data.news[$(this).parent().attr('name')]==$(this).val() && $(this).prop('selected',true);
//     	    });
        	$('select').each(function(){
        		$(this).find('option[value="'+ data.data.adEntity[$(this).attr('name')] +'"]').prop('selected',true).end().change();
        		
    	    });
        	if($(data.data.adEntity.image_list).size()>0){
        		$('[name="ad_width_high"]').find('option[value="'+ data.data.adEntity.image_list[0][$('[name="ad_width_high"]').attr('name')] +'"]').prop('selected',true);
        	    
        	}
        })
    	
    	
    	
    	/* .done(function(){
    		$('.select-choose').chosen().next('.chosen-container').css({width:$('.select-choose').css('width')})
    	}); */
    	
      	.done(function(){
    		$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
	    	$(':input').not('select,input[type="checkbox"]').each(function(){
	    		$(this).val(data.data.adEntity[$(this).attr('name')]);
	    	});
	    	
	    	 if($('[name="ad_type"]').val() == 3){
	    		//图文图片
	    		$('.img-news').find('.viewImg').attr('src','pic.htmls?p=' + (data.data.adEntity.image_list[0]?data.data.adEntity.image_list[0].image_url:'')).removeClass('collapse');
	    		$('.img-news').find('#submit_file').val((data.data.adEntity.image_list[0]?data.data.adEntity.image_list[0].image_url:''));
	    		
	    		$.each(data.data.adEntity.image_list[0],function(k,v){
	    			$('[name="'+ k +'"]').val(v)
	    		});
	    	} else if ($('[name="ad_type"]').val() != 3){
	    		//图集
	    		for(var i = 1; i < data.data.adEntity.image_list.length; i++){
	    			$('#clone-panel').click();
	    		}
	    		for(var j = 0; j < data.data.adEntity.image_list.length; j++){
	    			$('.panel-list-item').find('.viewImg').eq(j).attr('src','pic.htmls?p='+ (data.data.adEntity.image_list[j]?data.data.adEntity.image_list[j].image_url:'')).removeClass('collapse');
	    			$('.panel-list-item').find('#submit_file').eq(j).val((data.data.adEntity.image_list[j]?data.data.adEntity.image_list[j].image_url:''));
	    			$('.textarea_information').eq(j).val((data.data.adEntity.image_list[j]?data.data.adEntity.image_list[j].image_text:''));
	    			$('.textarea_information_link').eq(j).val((data.data.adEntity.image_list[j]?data.data.adEntity.image_list[j].information_link:''));
	    		}
	    	}
 
    	});
    } 
  //######################################################################################3
   
    $('[name="ad_type"]').change(function(){
    	if(this.value == 3){
    		$('[data-tips="panel"]').text('');
    		$('.img-news').removeClass('hide').find('#submit_file').prop('disabled',false);//显示图文
    		$('.image-ad').removeClass('hide').find('#submit_file').prop('disabled',false);//显示图片描述
    		$('.link-ad').removeClass('hide').find('#submit_file').prop('disabled',false);//显示链接地址
    		$('.img-news-list').addClass('hide').find('#submit_file').prop('disabled',true);
    		$('[name="image_information"]').prop('disabled',true);
    		$('[name="information_link_ad"]').prop('disabled',true);
    	} else if (this.value == 1){
    		$('.img-news-list').removeClass('hide').find('#submit_file').prop('disabled',false);//显示图集
    		$('.img-news').addClass('hide').find('#submit_file').prop('disabled',true);
    		$('.image-ad').addClass('hide').find('#submit_file').prop('disabled',true);
    		$('.link-ad').addClass('hide').find('#submit_file').prop('disabled',true);
    		$('[name="image_information"]').prop('disabled',false);
    		$('[name="information_link_ad"]').prop('disabled',false);
    		$('[data-tips="panel"]').text('轮播图最少需要上传一张');
    	}else if (this.value == 2){
    		$('.img-news-list').removeClass('hide').find('#submit_file').prop('disabled',false);//显示图集
    		$('.img-news').addClass('hide').find('#submit_file').prop('disabled',true);
    		$('.image-ad').addClass('hide').find('#submit_file').prop('disabled',true);
    		$('.link-ad').addClass('hide').find('#submit_file').prop('disabled',true);
    		$('[name="image_information"]').prop('disabled',false);
    		$('[name="information_link_ad"]').prop('disabled',false);
    		if($('.panel-list-item').length>=3){
    			$('.panel-list-item').filter(function(i){return i>=3;}).remove();
			}
    		$('[data-tips="panel"]').text('栏位图需要上传1-3张');
    	}
    }).change();
    $('[name="ad_width_high"]').change(function(){
    	$('.panel-list-item').replaceWith(sessionStorage.getItem('itemHTML'));
    	$('.panel-list-item').find('.viewImg').load(imgRatio);
    	$('.img-news').find('.viewImg').addClass('collapse').attr('src','').end().find('#submit_file').val('');
    });
    
    //######################################################################################3
    
    //判断图片尺寸是否合格
     function imgRatio(e){
 		var target = $(this);
		var ratio = Number(target.css('width').replace('px',''))/Number(target.css('height').replace('px',''));
		var targetRatio = Number($('[name="ad_width_high"]').val().split('*')[0])/Number($('[name="ad_width_high"]').val().split('*')[1]);
		if(ratio/targetRatio>1.1 || ratio/targetRatio<0.9){
			$(this).addClass('collapse').attr('src','').parent().find('#submit_file').val('');
			alert('图片尺寸不合格，请重新上传');
		}
	}
    
//    $('#submit_form').validate({
//        submitHandler : function(){
//            if(confirm('确定要提交数据吗？')) {
//
//            	if($('[name="ad_type"]').val()==1 ||$('[name="ad_type"]').val()==2){
//            		if($('.img-news-list').find('[name="submit_file"]').filter(function(){return $.trim($(this).val()) == '';}).length){
//	            		alert('请至少上传一张图片');
//	            		return;
//	            	}
//	            	var val = '';
//	            	$('.textarea_information').each(function(){
//	            		val += '#$#' + $(this).val() ;
//	            	});
//	            	$('[name="image_information"]').val(val.substring(3));
//
//
//	            	var val = '';
//	            	$('.textarea_information_link').each(function(){
//	            		val += '#$#' + $(this).val() ;
//	            	});
//	            	$('[name="information_link_ad"]').val(val.substring(3));
//            	}
//
//
//                $.ajax({
//                     type: 'POST',
//                     url: url + '/ad/modifySave.htmls',
//                     data: $('#submit_form').serialize() ,
//                })
//                .done(function(data){
//               	 	ns.site_back(data);
//                })
//                .fail(internal_error);
//           }
//            return false;//阻止表单提交
//        }
//    });

	//表单验证
	ns.formSubmit({
		submitUrl: '/ad/modifySave.htmls',
		otherValidate: function() {
			if($('[name="ad_type"]').val()==1 ||$('[name="ad_type"]').val()==2){
				//轮播图和栏位图需要至少一张图片
				if($('.img-news-list').find('[name="submit_file"]').filter(function(){return $.trim($(this).val()) == '';}).length){
					alert('请至少上传一张图片');
					return;
				}
				var val = '';
				$('.textarea_information').each(function(){
					val += '#$#' + $(this).val() ;
				});
				$('[name="image_information"]').val(val.substring(3));


				var val_temp = '';
				$('.textarea_information_link').each(function(){
					val_temp += '#$#' + $(this).val() ;
				});
				$('[name="information_link_ad"]').val(val_temp.substring(3));
			}
			return true;
		}
	});
   
    $('.time-control').datepicker({
    	format:'yyyy-mm-dd',
    	todayHighlight: true
    });

    </script>

