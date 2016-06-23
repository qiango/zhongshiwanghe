<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">新增视频</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

   <form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
     	
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>平台</span>
            <div class="col-xs-10 stretch">
               <select name="platform_id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required >
                     <option value=""></option>
               </select>
               <label id="platform_id-error" class="error" for="platform_id"></label>
            </div>
        </div>
<!--        <div class="col-xs-11 stretch form-group float-none"> -->
<!--             <span class="col-xs-2 align-right"><label class="warning-label">*</label>资讯类型</span> -->
<!--             <div class="col-xs-10 stretch"> -->
<!--                <select name="news_type" class="drop-btn width-200 select-choose form-control" required> -->
               		
<!--                </select> -->
<!--                <label id="news_type-error" class="error" for="news_type"></label> -->
<!--             </div> -->
<!--         </div> -->
        <style>
        	[name="circle_id"].drop-btn{ position: static !important; height: 34px !important; display: inline-block !important; border: 1px solid #ddd; }
        	select.none.drop-btn{ display: none !important; }
        </style>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>资讯范围</span>
            <div class="col-xs-10 stretch">
               <select name="news_range" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择">
                    
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
        <input type="hidden" name="news_type" value>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>频道</span>
            <div class="col-xs-10 stretch">
            	<select name="category_id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required >
                     <option value=""></option>
               </select>
               <label id="category_id-error" class="error" for="category_id"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>资讯标题</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-350 display-align" name="news_title" required
                placeholder="输入资讯标题" >
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>视频封面</span>
            	<div class="col-xs-10 stretch">
            	<div class="col-xs-12 display-table stretch">
	            	<label class="label-file btn btn-default">
	            		选择图片<input id="fileToUpload" type="file" size="45" name="fileToUpload">
	            	</label>
	            	<input type="text" name="submit_file" id="submit_file" required>
	            	<button type="button" class="btn btn-default upload-btn">上传</button>
	            	<label id="submit_file-error" class="error" for="submit_file"></label>
	            	<div class="file-path text-ellipsis"></div>
            	</div>
 				
 				<img class="viewImg collapse">
            </div>
        </div>
        <input type="hidden" name="news_type" value="2">
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>资讯摘要</span>
            <div class="col-xs-10 stretch">
                <textarea name="news_abstract" class="form-control width-350 display-align" rows="5" placeholder="输入资讯摘要" required></textarea>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>上传视频</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-350 display-align fly-out" name="file_id" placeholder="输入资讯标题" >
            	<script id="replacer" type="text/plain"></script>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit">保存</button>
		</div>
	</form>


    <script type="text/javascript">
    cadleo_data('/news/newLoad.htmls');
    function showData(data){
    	    	
    	$.when((function(){
    		var df = $.Deferred();
    		$('#replacer').replaceWith('<iframe src="style/iqy/sdkdemo.html" id="ifm" name="ifm" frameborder="0" width="100%" height="400" style="border: 1px solid #ddd; box-shadow: 0 0 5px 0px #ddd inset; border-radius: 6px;"></iframe>')
    		//缓存需要复制的节点
    		sessionStorage.setItem('itemHTML',$('.panel-list-item').clone().find('#submit_file').prop('disabled',false).end().wrap('<div></div>').parent().html());
    		
    		load_page_data(data.data.information_type_list,'news_type','zh_value','select[name="news_type"]');
    		load_page_data(data.data.competition_list,'competition_id','competition_name','select[name="competition_id"]');
        	load_page_data(data.data.platform_list,'platform_id','platform_name','select[name="platform_id"]');
        	load_page_data(data.data.range_list,'range_type','range_name','select[name="news_range"]');
        	load_page_data(data.data.category_video,'category_id','category_name','select[name="category_id"]');
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
    		$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
    	});
    }
    
    $('[name="news_range"]').change(function(){
   		$('[name="circle_id"]').addClass('none').prop('disabled',true);
   		if (this.value == 1){
			$('.club-select').removeClass('none').prop('disabled',false);
    	} else if (this.value == 2){
    		$('.com-select').removeClass('none').prop('disabled',false);
    	}
    }).change();

    $("#submit_form").validate({
        submitHandler : function(){
           	if(!$('#ifm')[0].contentWindow.uploadTag){
           		alert('请先上传视频或等待视频上传完成');
           	}
            if(confirm("确定要提交数据吗？")) {
            	//提交
                $.ajax({
                     type: "POST",
                     url: url + "/news/newSave.htmls",
                     dataType:"json",
                     data: $("#submit_form").serialize() ,
                })
                .done(function(data){
               	 	ns.site_back(data);
                })
                .fail(internal_error);
            }
            return false;//阻止表单提交
        }
    }); 
   
    </script>
