<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">新增赛事</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

   <form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">语言</span>
            <div class="col-xs-10 stretch">
               <select name="language_id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择">
               		<option value=""></option>
                     <c:forEach items="${language_list}" var="language">
                        <option value="${language.language_id }">${language.language_name }</option>
                     </c:forEach> 
               </select>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>平台</span>
            <div class="col-xs-10 stretch">
               <select name="platform_id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required>
               		<option value=""></option>
                     <c:forEach items="${platform_list}" var="platform">
                        <option value="${platform.platform_id }">${platform.platform_name }</option>
                     </c:forEach> 
               </select>
               <label id="platform_id-error" class="error" for="platform_id"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">城市</span>
            <div class="col-xs-10 stretch">
               <select name="id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择">
               		<option value=""></option>
                     <c:forEach items="${world_city_list}" var="city">
                        <option value="${city.id }">${city.name }</option>
                     </c:forEach> 
               </select>
            </div>
        </div>
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>赛事名称</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" rangelength="2,20" required name="competition_name"
                placeholder="输入赛事名称" >
            </div>
        </div>
         <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">赛事级别</span>
            <div class="col-xs-10 stretch">
               <select name="competition_level" class="drop-btn width-200 select-choose" data-placeholder="请选择">
               		  <option value=></option>
                      <option value="99">全国赛事</option>
                      <option value="0">俱乐部赛事</option>
               </select>
            </div>
        </div>
      <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">报名开始时间</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align time-control" id="registration_start_date" name="registration_start_date"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="报名开始时间">
            </div>
        </div>
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">报名结束时间</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align time-control" id="registration_end_date" name="registration_end_date"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="报名结束时间" >
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">比赛开始时间</span>
            <div class="col-xs-10 stretch">
           		<input type="text" class="form-control width-200 display-align time-control" id="competition_start_date" name="competition_start_date"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="比赛开始时间" >
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">比赛结束时间</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align time-control" id="competition_end_date" name="competition_end_date"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="比赛结束时间" >
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>上传图片</span>
            <div class="col-xs-10 stretch">
            	<div class="col-xs-12 display-table stretch">
	            	<label class="label-file btn btn-default">
	            		选择图片<input id="fileToUpload" type="file" size="45" name="fileToUpload" accept="image/gif,image/jpg,image/jpeg,image/png">
	            	</label>
	            	<input type="text" name="submit_file" id="submit_file" required>
	            	<button type="button" class="btn btn-default upload-btn">上传</button>
	            	<div class="file-path text-ellipsis"></div>
            	</div>
 				
 				<img class="viewImg collapse">
            </div>
        </div>
<!--         <div class="col-xs-11 stretch form-group float-none"> -->
<!--             <span class="col-xs-2 align-right">赛事状态</span> -->
<!--             <div class="col-xs-10 checkbox stretch"> -->
<!--             	<label><input type="checkbox" name="competition_status" value="1">生效</label> -->
<!--             </div> -->
<!--         </div> -->
        
        
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">赛事描述</span>
            <div class="col-xs-10 stretch">
                <textarea name="competition_description" class="form-control display-align" rows="5" placeholder="赛事描述"></textarea>
            </div>
        </div>


		<div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit" data-role="loading">保存</button>
		</div>
	</form>
    
    <script type="text/javascript">
    cadleo_data('/competition/insertLoad.htmls');
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
    		load_page_data(data.data.language_list,'code','value','select[name="language_id"]');
        	load_page_data(data.data.platform_list,'platform_id','platform_name','select[name="platform_id"]');
        	load_page_data(data.data.world_city_list,'id','name','select[name="id"]');
        	df.resolve();
    		return df.promise();
    	})())
    	.done(function(){
    		$('.select-choose').chosen().next('.chosen-container').css({width:$('.select-choose').css('width')})
    	});
    }

    //表单验证
    ns.formSubmit({
        submitUrl: '/competition/insertSave.htmls'
    });

    $('.time-control').datepicker({
    	format:'yyyy-mm-dd',
    	todayHighlight: true
    });

    </script>

