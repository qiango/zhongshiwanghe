<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">报名表单修改</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

	<form action="" target="_self" method="post" id="submit_form"
		class="edit-form form-horizontal panel-h-align col-xs-11">
		<div class="col-xs-12" >
		
			
			<table class="table table-hover table-align table-bordered">
				<caption>
					<input type="hidden" name="competition_id">
				  	<h4 class="col-xs-10 col-xs-offset-1" id="competition_name"></h4>
					<div class="add-control stretch pull-right col-xs-1 btn btn-primary btn-theme"><i class="fa fa-plus fa-add"></i>新增</div>
				</caption>
				<thead>
					<tr>
						<th>标题名称</th>
						<th>控件类型</th>
						<th>控件数据</th>
						<th>提示信息</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				
					<tr>
					<input type="hidden" class="item_id" name="competition_application_id">
						<td><input type="text" class="form-control" name="title_name" value="${competition.title_name }" maxlength="6" placeholder="请输入不超过6个字符的标题"> </td>
						<td>
							<select name="controls_id" class="drop-btn width-200 select-choose form-control" data-placeholder="选择控件类型">
			                    <option></option>
			                    <c:forEach items="${controls_list }" var="controls">
    			                       <c:choose>
                                            <c:when test="${competition.controls_id == controls.controls_id  }">
			                         <option value="${controls.controls_id }"  selected="selected">${controls.controls_name } </option>
                                            </c:when>    
                                            <c:otherwise>
			                         <option value="${controls.controls_id }">${controls.controls_name } </option>
                                            </c:otherwise>
                                        </c:choose>
			                    </c:forEach>
              				</select>
              			</td>
						<td><input type="text" class="form-control" name="controls_data" placeholder="请使用英文“，”分隔" value="${competition.controls_data }"  ></td>
						<td><input type="text" class="form-control" name="controls_placeholder" placeholder="请输入提示用户的信息" value="${competition.controls_placeholder }" ></td>
						<td><i class="fa fa-remove fa-delete"></i></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		
		
		
		<button class="btn btn-primary btn-theme pull-left col-xs-offset-6" id="submit_form_btn" type="submit">保存</button>
	</form>

	<script type="text/javascript">
		$('.edit-form').on('click','.add-control',function(){
			var cloneItem = $('tbody>tr:first-child').clone().find('.chosen-container').remove().end()
							.find(':input').val('').end()
							.find('.select-choose').removeAttr('style').end();			
			cloneItem.appendTo($('.table-align tbody')).find('.select-choose').chosen();			
		});
		cadleo_data('/competitionApplication/modifyLoad.htmls?competition_id='+sessionStorage.getItem('edit_id'));
		function showData(data){
	    	$.when((function(){
	    		var df = $.Deferred();
		    	$('#competition_name').text('选择赛事:'+data.data.competition_name);
		    	$('[name="competition_id"]').val(data.data.competition_application_list[0].competition_id);
		    	load_page_data(data.data.controls_list,'controls_id','controls_name','select[name="controls_id"]');
		    	//$('.item_id').val();
		    	var list=data.data.competition_application_list;
		    	for(var j = 1,len = list.length; j<len;j++){
		    		$('tbody>tr:first-child').clone().appendTo($('.table-align tbody'))
		    	}
		    	for(var i = 0; i<len; i++ ){
		    		$('tbody tr').eq(i).find('select option').each(function(){
			    		list[i][$(this).parent().attr('name')]==$(this).val() && $(this).prop('selected',true);
		    	    });
			    	$('tbody tr').eq(i).find('input').each(function(){
			    		$(this).val(list[i][$(this).attr('name')]);
			    	});
		    	}
			    df.resolve();
			    return df.promise();
		    })())
		    .done(function(){
		    	$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')})
		    });
	    	
	    }

		$('.table-align').on('click','.fa-delete',function(){
			$('.fa-delete').length==1 && !alert('只剩一条，不能再删啦')||
			$(this).parents('tr').remove();
		});

		$('.table-align tbody').sortable();
		
		/*提交字符串*/		
		$('#submit_form').submit(function(){
			var datas = "";
			$('tr').each(function(){
				var son_data = "";
				
				var competition_id = $('[name="competition_id"]').val();
				var _inputs = $(this).find(':input').not('.chosen-search input').not('.item_id');

				$.when((function(){
		    		var df = $.Deferred();
		    		_inputs.each(function(index){
						son_data += ';;' + $(this).val();
					});
		    		son_data && df.resolve();
				    return df.promise();
			    })())
			    .done(function(){
			    	son_data = competition_id + son_data;
					datas += "$$" + son_data;
			    });
			});
			if(datas != ""){
				datas = datas.substring(2);
			}
			var buffer=datas.split('$$'),
				check = true;
			for(var i in buffer){
				if(!buffer[i].split(';;')[1]||!buffer[i].split(';;')[2]){
					alert('标题名称和控件类型为必填选项');check=false;
					break;
				}				
			}
			if(check){
				$.ajax({
	                type: "POST",
	                url: url+'/competitionApplication/modifySave.htmls',
	                data: {form_input:datas},
				})
                .done(function(data){
               	 	ns.site_back(data);
                })
                .fail(internal_error);
			}
       		return false;//阻止表单提交
		});
    </script>

