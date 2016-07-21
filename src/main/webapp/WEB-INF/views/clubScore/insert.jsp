<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">俱乐部录入</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>			
	</div>
	<form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">

		 <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">申请人</span>
            <div class="col-xs-10 stretch">
               <input type="text" name="club_applicant_name" class="form-control width-200 display-align" placeholder="请输入申请人名称">
            </div>
        </div>
		<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>城市</span>
            <div class="col-xs-10 stretch">
               <select name="id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required >
                   <option value=""></option>
               </select>
               <label id="id-error" class="error" for="id"></label>
            </div>
        </div>
		<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>语言</span>
            <div class="col-xs-10 stretch">
               <select name="language_id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择">
                   <option value=""></option>
               </select>
            </div>
        </div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">俱乐部成立日期</span>
			<div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align" id="club_create_date" data-date-language="zh-CN"
				name="club_create_date" placeholder="请输入俱乐部成立日期" autocomplete="off" readonly="readonly" >
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right"><label class="warning-label">*</label>俱乐部名称</span>
			<div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align" name="club_name"
				placeholder="请输入俱乐部名称" required>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right"><label class="warning-label">*</label>俱乐部简称</span>
			<div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align"
				name="club_name_short" placeholder="请输入俱乐部简称" required>
			</div>
		</div>

		<input type="hidden" class="form-control width-200 display-align"
			name="club_status"  value="99">

		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">俱乐部描述</span>
			<div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align" name="club_description"
				placeholder="请输入俱乐部描述">
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right"><label class="warning-label">*</label>俱乐部qq群</span>
			<div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align" name="club_qq"
				placeholder="请输入俱乐部qq群号" required number="true">
			</div>
		</div>
		<div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit" data-role="loading">保存</button>
		</div>
    </form>
    
    
    <script type="text/javascript">
    cadleo_data('/club/insertLoad.htmls');
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
	    	load_page_data(data.data.world_city_list,'id','name','select[name="id"]');
	    	load_page_data(data.data.language_list,'code','value','select[name="language_id"]');
	    	df.resolve();
    		return df.promise();
    	})())
    	.done(function(){
    		$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')})
    	});
    }

	//表单验证
	ns.formSubmit({
		submitUrl: '/club/insertSave.htmls',
		otherValidate:  function() {
			if($('[name="ad_type"]').val()==1 ||$('[name="ad_type"]').val()==2){
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
 
	$("#club_create_date").datepicker({
		format:'yyyy-mm-dd',
		todayHighlight: true
	});
    </script>
</body>
</html>
