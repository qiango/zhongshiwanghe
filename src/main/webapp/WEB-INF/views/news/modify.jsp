<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">修改资讯</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

   <form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
       
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>平台</span>
            <div class="col-xs-10 stretch">
               <select name="platform_id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择">
               		<option value=""></option>
                     <c:forEach items="${platform_list}" var="platform">
                        <c:choose>
                            <c:when test="${information.platform_id == platform.platform_id  }">
                        <option value="${platform.platform_id }" selected="selected">${platform.platform_name }</option>
                            </c:when>    
                            <c:otherwise>
                        <option value="${platform.platform_id }">${platform.platform_name }</option>
                            </c:otherwise>
                        </c:choose>
                     </c:forEach> 
               </select>
               <label id="platform_id-error" class="error" for="platform_id"></label>
            </div>
        </div>
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">资讯类型</span>
            <div class="col-xs-10 stretch">
               <select name="news_type" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择">
               		<option value=""></option>
               <c:forEach items="${info_type }" var="info_t">
                        <c:choose>
                            <c:when test="${information.information_type == info_t.id  }">
                        <option value="${info_t.id }" selected="selected">${info_t.name } </option>
                            </c:when>    
                            <c:otherwise>
                        <option value="${info_t.id }">${info_t.name } </option>
                            </c:otherwise>
                        </c:choose>
                     </c:forEach> 
               </select>
            </div>
        </div>
        
        <style>
        	[name="circle_id"].drop-btn{ position: static !important; height: 34px !important; display: inline-block !important; border: 1px solid #ddd; }
        	select.none.drop-btn{ display: none !important; }
        </style>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">资讯范围</span>
            <div class="col-xs-10 stretch">
               <select name="news_range" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择">
                    <option value=""></option>
               </select>
               <select name="circle_id" class="drop-btn width-200 form-control com-select none" placeholder="请选择" required>
                    <option value="">请选择</option>
               </select>
               <select name="circle_id" class="drop-btn width-200 form-control club-select none" placeholder="请选择" required>
                    <option value="">请选择</option>
               </select>
               <label id="circle_id-error" class="error" for="circle_id"></label>
            </div>
        </div>
        
         <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>资讯标题</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-350 display-align" name="news_title"  value="" required
                placeholder="输入资讯标题" >
            </div>
        </div>

	   <div class="col-xs-11 stretch form-group float-none">
		   <span class="col-xs-2 align-right">资讯副标题</span>
		   <div class="col-xs-10 stretch">
			   <input type="text" class="form-control width-350 display-align" name="news_subtitle"  value=""
					  placeholder="输入资讯副标题" >
		   </div>
	   </div>

	   <div class="col-xs-11 stretch form-group float-none">
		   <span class="col-xs-2 align-right">权重排序</span>
		   <div class="col-xs-10 stretch">
			   <input type="text" class="form-control width-350 display-align" name="weights_order"  value=""
					  placeholder="输入权重排序号"  number="true">
		   </div>
	   </div>

        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">资讯摘要</span>
            <div class="col-xs-10 stretch">
            	<textarea class="form-control width-350 display-align" name="news_abstract" rows="5"
                placeholder="输入资讯摘要" >${information.information_abstract }</textarea>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none img-news uploadPic">
            <span class="col-xs-2 align-right">上传图片</span>
            <div class="col-xs-10 stretch">
            	<div class="col-xs-12 display-table stretch">
	            	<label class="label-file btn btn-default">
	            		选择图片<input id="fileToUpload" type="file" size="45" name="fileToUpload" accept="image/gif,image/jpg,image/jpeg,image/png">
	            	</label>
	            	<input type="text" name="submit_file" id="submit_file" value="${image.information_image_url }">
	            	<button type="button" class="btn btn-default upload-btn">上传</button>
	            	<label id="submit_file-error" class="error" for="submit_file"></label>
	            	<div class="file-path text-ellipsis"></div>
            	</div>
 				
 				
 				<img class="viewImg collapse" src='${image.information_image_url }'>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none img-news">
            <span class="col-xs-2 align-right">资讯内容</span>
            <div class="col-xs-10 stretch">
                <script id="editor" type="text/plain" style="width:500px;height:500px;" name="news_content"></script>
            </div>
        </div>
        
        <!-- 图片集 -->
        <div class="col-xs-11 stretch form-group float-none img-news-list">
            <span class="col-xs-2 align-right">图集列表</span>	
            <div class="col-xs-10 stretch">
            	<input type="hidden" name="media_information" value>
            	<button class="btn btn-default" id="clone-panel" type="button"><i class="fa fa-clone"></i>新增一条</button>
            	<button class="btn btn-default" id="delete-panel" type="button"><i class="fa fa-times"></i>删除一条</button>
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
			            	<label id="submit_file-error" class="error" for="submit_file"></label>
			            	<div class="file-path text-ellipsis"></div>
		            	</div>
		            	<textarea class="pull-left mt-8 form-control textarea_information" id="" cols="30" placeholder="请输入图片描述" style="height: 119px; resize: none;"></textarea>
	            	</div>
            	</div>
 				
            </div>
        </div>
        
        <!-- 
      <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">备注</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" name="remark"  value="${information.remark }"
                placeholder="输入备注" >
            </div>
        </div>
        -->
       <input  type="hidden" value="" name="news_id"> 

		<div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" data-loading-text="提交中" id="submit_form_btn" type="submit">保存</button>
		</div>
	</form>
    
    <script type="text/javascript">
    cadleo_data('/news/modifyLoad.htmls?news_id='+sessionStorage.getItem('edit_id'));
    function showData(data){
    	
    	$.when((function(){
    		var df = $.Deferred();
        	
    		//缓存需要复制的节点
    		sessionStorage.setItem('itemHTML',$('.panel-list-item').clone().find('#submit_file').prop('disabled',false).end().wrap('<div></div>').parent().html());
    		
    		load_page_data(data.data.information_type_list,'news_type','zh_value','select[name="news_type"]');
    		load_page_data(data.data.competition_list,'competition_id','competition_name','select[name="competition_id"]');
        	load_page_data(data.data.platform_list,'platform_id','platform_name','select[name="platform_id"]');
        	load_page_data(data.data.range_list,'range_type','range_name','select[name="news_range"]');
        	load_page_data(data.data.competition_list,'competition_id','competition_name','.com-select');
        	load_page_data(data.data.club_list,'club_id','club_name','.club-select');
        	
        	//复制节点
        	$('#clone-panel').click(function(){
        		$(sessionStorage.getItem('itemHTML')).appendTo($(this).parent());
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
        		$(this).find('option[value="'+ data.data.news[$(this).attr('name')] +'"]').prop('selected',true);
    	    });
        	if(data.data.news.newsrang){
	        	$('[name="news_range"]').find('option[value="'+ data.data.news.newsrang["news_range"] +'"]').prop('selected',true).end().change();
	        	setTimeout(function(){
		        	$('[name="news_range"]').siblings('select:visible').find('option[value="'+ data.data.news.newsrang["circle_id"] +'"]').prop('selected',true);
	        	});
        	}
    	})
    	.done(function(){
    		$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
	    	$(':input').not('select,input[type="checkbox"]').each(function(){
	    		$(this).val(data.data.news[$(this).attr('name')]);
	    	});
	    	if($('[name="news_type"]').val() != 1){
	    		//图文或者视频图片
	    		$('.img-news').find('.viewImg').attr('src','pic.htmls?p=' + (data.data.news.picture[0]?data.data.news.picture[0].media_url:'')).removeClass('collapse');
	    		$('.img-news').find('#submit_file').val((data.data.news.picture[0]?data.data.news.picture[0].media_url:''));
	    		
	    	} else if ($('[name="news_type"]').val() == 1){  
	    		//图集
	    		for(var i = 1; i < data.data.news.picture.length; i++){
	    			$('#clone-panel').click();
	    		}
	    		for(var j = 0; j < data.data.news.picture.length; j++){
	    			$('.panel-list-item').find('.viewImg').eq(j).attr('src','pic.htmls?p='+ (data.data.news.picture[j]?data.data.news.picture[j].media_url:'')).removeClass('collapse');
	    			$('.panel-list-item').find('#submit_file').eq(j).val((data.data.news.picture[j]?data.data.news.picture[j].media_url:''));
	    			$('.textarea_information').eq(j).val((data.data.news.picture[j]?data.data.news.picture[j].media_information:''));
	    		}
	    	}

    	});
    	
    	$('[name="news_type"]').change(function(){
        	if(this.value == 0){
        		$('.img-news').removeClass('fly-out').find('#submit_file').prop('disabled',false);//显示图文
        		$('.img-news-list').addClass('fly-out').find('#submit_file').prop('disabled',true);
        		$('[name="media_information"]').prop('disabled',true);

        	} else if (this.value == 1){
        		$('.img-news-list').removeClass('fly-out').find('#submit_file').prop('disabled',false);//显示图集
        		$('.img-news').addClass('fly-out').find('#submit_file').prop('disabled',true);
        		$('[name="media_information"]').prop('disabled',false);
        	} else if (this.value == 2){
                $('.img-news').removeClass('fly-out').find('#submit_file').prop('disabled',false);
                $('.img-news').not('.uploadPic').addClass('fly-out').find('#submit_file').prop('disabled',true);
                $('.img-news-list').addClass('fly-out').find('#submit_file').prop('disabled',true);
                $('[name="media_information"]').prop('disabled',true);
        	}
        }).change();
    	
    	$('[name="news_range"]').change(function(){
       		$('[name="circle_id"]').addClass('none').prop('disabled',true);
    		if (this.value == 1){
    			$('.club-select').removeClass('none').prop('disabled',false);
        	} else if (this.value == 2){
        		$('.com-select').removeClass('none').prop('disabled',false);
        	}
        }).change();
    	
	    var editor = new UE.ui.Editor();
	    editor.render("editor");
		if($('[name="news_type"]').val() == 0) {//图文才需要回显富文本编辑器
			UE.getEditor('editor').addListener("ready", function () {
				UE.getEditor('editor').setContent(data.data.news.news_content, false);
			});
		}
    }

	//表单验证
	ns.formSubmit($('#submit_form'), '/news/modifySave.htmls', $('#submit_form_btn'), function () {
		//提交图集说明
		if($('[name="news_type"]').val()==1){
			if($('.img-news-list').find('[name="submit_file"]').filter(function(){return $.trim($(this).val()) == '';}).length||
				$('.img-news-list').find('.textarea_information').filter(function(){return $.trim($(this).val()) == '';}).length){
				alert('请至少上传一张图片并填写图片描述');
				return false;
			}
			var val = '';
			$('.textarea_information').each(function(){
				val += '#$#' + $(this).val() ;
			});
			$('[name="media_information"]').val(val.substring(3));

		}
		return true;
	});

    </script>

