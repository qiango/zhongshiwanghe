<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">运动派修改</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>
	<form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
        <input type="hidden" name="sports_camp_id" value="${sports_camp.sports_camp_id }">
		<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>语言</span>
            <div class="col-xs-10 stretch">
               <select name="language_id" class="drop-btn width-200 select-choose form-control" required data-placeholder="请选择">
               			<option value=""></option>
<%--                      <c:forEach items="${language_list}" var="language"> --%>
<%--                      <c:choose> --%>
<%--                          <c:when test="${language.language_id == sports_camp.language_id  }"> --%>
<%--                         	<option value="${language.language_id }" selected>${language.language_name }</option> --%>
<%--                          </c:when>     --%>
<%--                          <c:otherwise> --%>
<%--                             <option value="${language.language_id }">${language.language_name }</option> --%>
<%--                          </c:otherwise> --%>
<%--                      </c:choose> --%>
<%--                      </c:forEach>  --%>
               </select>
               <label id="language_id-error" class="error" for="language_id"></label>
            </div>
        </div>
<!-- 		<div class="col-xs-11 stretch form-group float-none"> -->
<!--             <span class="col-xs-2 align-right">录入人</span> -->
<!--             <div class="col-xs-10 stretch"> -->
<!--                <select name="user_id" class="drop-btn width-200 select-choose form-control"> -->
<%--                       <c:forEach items="${user_info_list}" var="user"> --%> 
<%--                       <c:choose> --%>
<%--                         <c:when test="${user.user_id == sports_camp.user_id  }"> --%> 
<%--                         	<option value="${user.user_id }" selected>${user.user_real_name }</option> --%> 
<%--                         </c:when>     --%>
<%--                         <c:otherwise> --%>
<%--                         	<option value="${user.user_id }">${user.user_real_name }</option> --%> 
<%--                         </c:otherwise> --%>
<%--                      </c:choose> --%> 
<%--                      </c:forEach>  --%> 
<!--                </select> -->
<!--             </div> -->
<!--         </div> -->
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"> <label class="warning-label">*</label>运动派名称</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" name="sports_camp_name" id="sports_camp_name"
                placeholder="输入运动派名称" value="" required>
            	<label id="sports_camp_name-error" class="error" for="sports_camp_name"></label>
            </div>
        </div>
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">备注</span>
            <div class="col-xs-10 stretch">
            	<textarea class="form-control display-align" rows="4" name="remark"
                placeholder="备注"></textarea>
            </div>
        </div>
       
        <div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit">保存</button>
		</div>
    </form>    
    <script type="text/javascript">
    cadleo_data('/sportscamp/modifyLoad.htmls?sports_camp_id='+sessionStorage.getItem('edit_id'));
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
	    	load_page_data(data.data.language_list,'code','value','select[name="language_id"]');
    		
    		df.resolve();
    		return df.promise();
	    })())
	    .done(function(){
	    	$('select option').each(function(){
	    		data.data.sports_camp[$(this).parent().attr('name')]==$(this).val() && $(this).prop('selected',true);
	    	});
	    })
	    .done(function(){
	    	$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
	    	$(':input').not('select,input[type="checkbox"]').each(function(){
	    		$(this).val(data.data.sports_camp[$(this).attr('name')]);
	    	});
	    });
    }
    $("#submit_form").validate({
        submitHandler : function(){
            if(confirm("确定要提交数据吗？")) {
                $.ajax({
                     type: "POST",
                     url: url+"/sportscamp/modifySave.htmls",
                     data:$("#submit_form").serialize(),
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

