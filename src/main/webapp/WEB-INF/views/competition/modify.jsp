<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">修改赛事</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

	<form action="" target="_self" method="post" id="submit_form"
		class="edit-form form-horizontal panel-h-align col-xs-11">
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">语言</span>
			<div class="col-xs-10 stretch">
				<select name="language_id" class="drop-btn width-200 select-choose form-control">
					<c:forEach items="${language_list}" var="language">
					<c:choose>
                         <c:when test="${language.language_id == competition.language_id  }">
							<option value="${language.language_id }" selected>${language.language_name }</option>
						 </c:when>    
                         <c:otherwise>
                         	<option value="${language.language_id }">${language.language_name }</option>
                         </c:otherwise>
                     </c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right"><label class="warning-label">*</label>平台</span>
			<div class="col-xs-10 stretch">
				<select name="platform_id" class="drop-btn width-200 select-choose form-control"  required >
					<c:forEach items="${platform_list}" var="platform">
					<c:choose>
                         <c:when test="${platform.platform_id == competition.platform_id  }">
							<option value="${platform.platform_id }" selected>${platform.platform_name }</option>
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
            <span class="col-xs-2 align-right">城市</span>
            <div class="col-xs-10 stretch">
               <select name="id" class="drop-btn width-200 select-choose form-control">
                     <c:forEach items="${world_city_list}" var="city">
                        <option value="${city.id }">${city.name }</option>
                     </c:forEach> 
               </select>
            </div>
        </div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right"><label class="warning-label">*</label>赛事名称</span>
			<div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align" required rangelength="2,20" name="competition_name" value="${competition.competition_name }"
				placeholder="输入赛事名称">
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">赛事级别</span>
			<div class="col-xs-10 stretch">
				<select name="competition_level" class="drop-btn width-200 select-choose">
                      <option value="99">全国赛事</option>
                      <option value="0">俱乐部赛事</option>
               </select>
			</div>
		</div>
		
	<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">报名开始时间</span>
            <div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align time-control" id="registration_start_date" name="registration_start_date"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="报名开始时间" value="${competition.registration_start_date}">
            </div>
        </div>
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">报名结束时间</span>
			<div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align time-control" id="registration_end_date" name="registration_end_date"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="报名结束时间" value="${competition.registration_end_date}" >
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">比赛开始时间</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align time-control" id="competition_start_date" name="competition_start_date"
                data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="比赛开始时间" value="${competition.competition_start_date}" >
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">比赛结束时间</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align time-control" id="competition_end_date" name="competition_end_date" 
            	data-date-language="zh-CN" autocomplete="off" readonly="readonly" placeholder="比赛结束时间" value="${competition.competition_end_date}" >
        	</div>
        </div>
		<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">上传图片</span>
            <div class="col-xs-10 stretch">
            	<div class="col-xs-12 display-table stretch">
	            	<label class="label-file btn btn-default">
	            		选择图片<input id="fileToUpload" type="file" size="45" name="fileToUpload">
	            	</label>
	            	<input type="text" name="submit_file" id="submit_file" required value="${competition.competition_publicity_pictures}">
	            	<button type="button" class="btn btn-default upload-btn">上传</button>
	            	<div class="file-path text-ellipsis"></div>
            	</div>
 				
 				<img class="viewImg" src=".${competition.competition_publicity_pictures}">
            </div>
        </div>
<!--         <div class="col-xs-11 stretch form-group float-none"> -->
<!--             <span class="col-xs-2 align-right">赛事状态</span> -->
<!--             <div class="col-xs-10 checkbox stretch"> -->
<%--             	<label><input type="checkbox" data-checked="${competition.competition_status}" name="competition_status">生效</label> --%>
<!--             </div> -->
<!--         </div> -->
        <div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">赛事描述</span>
			<div class="col-xs-10 stretch">
				<textarea name="competition_description" class="form-control display-align" rows="5" placeholder="赛事描述">
				${competition.competition_description}</textarea>
			</div>
		</div>

		<input type="hidden" name="competition_id" value="">

		<div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit">保存</button>
		</div>
	</form>

	<script type="text/javascript">
	cadleo_data('/competition/modifyLoad.htmls?competition_id='+sessionStorage.getItem('edit_id'));
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
        	$('select').each(function(){
        		$(this).find('option[value="' + data.data.competition_query[$(this).attr('name')] + '"]').prop('selected',true);
        	});
	    })
	    .done(function(){
	    	$('.select-choose').chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
	    	$(':input').not('select,input[type="checkbox"]').each(function(){
	    		$(this).val(data.data.competition_query[$(this).attr('name')]);
	    	});
	    	$('.viewImg').attr('src','.'+data.data.competition_query.competition_publicity_pictures);
	    	$('#submit_file').val(data.data.competition_query.competition_publicity_pictures);

	    });
    }

        if( $("[name='competition_status']").data("checked") == 1 ){
            $("[name='competition_status']").prop("checked",'true'); 
       }

		$('#submit_form').validate({
				submitHandler : function() {
					if (confirm('确定要提交数据吗？')) {
					$.ajax({
						type : 'POST',
						url : url + '/competition/modifySave.htmls',
	           			data: $('#submit_form').serialize()
					})
		            .done(function(data){
		           	 	ns.site_back(data);
		            })
		            .fail(internal_error);
		       }
            return false;//阻止表单提交
        }
    }); 
      
	$('.time-control').datepicker({
    	format:'yyyy-mm-dd',
    	todayHighlight: true
    });
	</script>

