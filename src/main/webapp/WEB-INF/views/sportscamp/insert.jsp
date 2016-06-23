<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">运动派录入</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>
	<form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
		<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>语言</span>
            <div class="col-xs-10 stretch">
               <select name="language_id" class="drop-btn width-200 select-choose form-control" required data-placeholder="请选择">
               		<option value=""></option>
                     <c:forEach items="${language_list}" var="language">
                        <option value="${language.language_id }">${language.language_name }</option>
                     </c:forEach> 
               </select>
               <label id="language_id-error" class="error" for="language_id"></label>
            </div>
        </div>
        
<!-- 		<div class="col-xs-11 stretch form-group float-none"> -->
<!--             <span class="col-xs-2 align-right">录入人</span> -->
<!--             <div class="col-xs-10 stretch">  -->
<!--                <select name="user_id" class="drop-btn width-200 select-choose"> -->
<%--                      <c:forEach items="${user_info_list}" var="user"> --%>
<%--                         <option value="${user.user_id }">${user.user_real_name }</option> --%>
<%--                      </c:forEach>  --%>
<!--                </select> -->
<!--             </div> -->
<!--         </div> -->

        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>运动派名称</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" name="sports_camp_name"
                placeholder="输入运动派名称" required>
            	<label id="sports_camp_name-error" class="error" for="sports_camp_name"></label>
            </div>
        </div>

		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">备注</span>
			<div class="col-xs-10 stretch">
				<textarea name="remark" class="form-control display-align" rows="4" placeholder="备注"></textarea>
			</div>
		</div>

		<div class="col-xs-11 stretch form-group mt-10">
				
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit">保存</button>
		</div>
    </form>
    
    
    <script type="text/javascript">
    cadleo_data('/sportscamp/insertLoad.htmls');
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
	    	load_page_data(data.data.language_list,'code','value','select[name="language_id"]');
	    	//load_page_data(data.data.user_info_list,'user_id','user_real_name','select[name="user_id"]');
	    	df.resolve();
	    	return df.promise();
    	})())
    	.done(function(){
    		$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
    	});
    }
    $("#submit_form").validate({
        submitHandler : function(){
            if(confirm("确定要提交数据吗？")) {
                $.ajax({
                     type: "POST",
                     url: url+"/sportscamp/insertSave.htmls",
                     data: $("#submit_form").serialize(),
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
</body>
</html>
