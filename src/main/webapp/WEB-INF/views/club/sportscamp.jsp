<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">俱乐部-运动派配置</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>
	<form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
        <input type="hidden" name="club_id" value="${ club_list.club_id}">
<!-- 		<div class="col-xs-11 stretch form-group float-none"> -->
<!--             <span class="col-xs-2 align-right">俱乐部</span>  -->
<%--             <div class="col-xs-10 stretch checkbox">${club_list.club_name }</div>    --%>
<!--         </div> -->
		<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">运动派</span> 
              <div class="col-xs-10 checkbox stretch sportscamp_checkbox" >
	             <c:forEach items="${sports_camp_listAll}" var="sports">
		             <label>
		               <input type="checkbox" name="sports_camp_id" id="sports_camp_id_${sports.sports_camp_id }" value="${sports.sports_camp_id }" >  ${sports.sports_camp_name } 
		             </label>
	             </c:forEach>             
             </div>
        </div>

		<div class="col-xs-11 stretch form-group mt-10">
						
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit">保存</button>
		</div>
    </form>
    
    
    <script type="text/javascript">
    cadleo_data('/club/sportscamp.htmls?club_id='+sessionStorage.getItem('edit_id'));
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
    		load_checkbox(data.data.sports_camps_all,'sports_camp_id','sports_camp_id','sports_camp_id_','sports_camp_name','.sportscamp_checkbox');
    		df.resolve();
    		return df.promise();
    	})())
    	.done(function(){
	    	for(var i=0;i< data.data.sports_camps_id_list.length;i++){
	       	  $('.sportscamp_checkbox input[type="checkbox"][value="'+ data.data.sports_camps_id_list[i] +'"]').prop('checked',true);
	        }
	    });
    }
    $('[name="club_id"]').val(sessionStorage.getItem('edit_id'));
    $('#submit_form').validate({
        submitHandler : function(){
        	var ids='';
        	$(':checked').each(function(){
        		ids += ',' + $(this).val()
        	});
            if(confirm('确定要提交数据吗？')) {
                $.ajax({
                     type: 'POST',
                     url: url+'/club/sportscampSave.htmls',
                     data: {club_id:$('[name="club_id"]').val(),sports_camp_id:ids.substring(1)},
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

