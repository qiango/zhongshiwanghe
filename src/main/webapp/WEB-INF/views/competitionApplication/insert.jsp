<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">生成赛事报名表单</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

	<form action="" target="_self" method="post" id="submit_form"
		class="edit-form form-horizontal panel-h-align col-xs-11">
		<div class="col-xs-12" >
		
			<table class="table table-hover table-align table-bordered">
				<caption>
					<h4 class="col-xs-8 col-xs-offset-2">
					  选择赛事:
						<select name="competition_id" class="drop-btn width-200 select-choose form-control" id="competition-choose" data-placeholder="请选择赛事" >
				            <option></option>
					        <c:forEach items="${competition_list }" var="competition">
			                   <option value="${competition.competition_id }">${competition.competition_name }<option>
					        </c:forEach>
	                	</select>
                	</h4>
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
						<td><input type="text" class="form-control" name="" maxlength="6" placeholder="请输入不超过6个字符的标题"></td>
						<td>
							<select name="controls_id" class="drop-btn width-200 select-choose form-control" data-placeholder="选择控件类型" >
			                    <option></option>
			                    <c:forEach items="${controls_list }" var="controls">
			                         <option value="${controls.controls_id }">${controls.controls_name } </option>
			                    </c:forEach>
              				</select>
              			</td>
						<td><input type="text" class="form-control" name="" placeholder="请使用英文“，”分隔"></td>
						<td><input type="text" class="form-control" name="" placeholder="请输入提示用户的信息"></td>
						<td><i class="fa fa-remove fa-delete"></i></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		
		<button class="btn btn-primary btn-theme pull-left col-xs-offset-6" id="submit_form_btn" type="submit" data-role="loading">保存</button>
	</form>

	<script type="text/javascript">
		cadleo_data('/competitionApplication/insertLoad.htmls');
		function showData(data){
			$.when((function(){
	    		var df = $.Deferred();
		    	load_page_data(data.data.competition_list,'competition_id','competition_name','select[name="competition_id"]');
		    	load_page_data(data.data.controls_list,'controls_id','controls_name','select[name="controls_id"]');
		    	df.resolve();
				return df.promise();
			})())
			.done(function(){
			    $(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
			});
	    }
   		$('.table-align').off('click','.fa-delete');
		$('.table-align').on('click','.fa-delete',function(){
			$('.fa-delete').length==1 && !alert('只剩一条，不能再删啦')||
			$(this).parents('tr').remove();
		});
		
		$('.edit-form').on('click','.add-control',function(){
			var cloneItem = $('tbody>tr:first-child').clone().find('.chosen-container').remove().end()
							.find(':input').val('').end()
							.find('.select-choose').removeAttr('style').end();			
			cloneItem.appendTo($('.table-align tbody')).find('.select-choose').chosen();			
		});
		$('.table-align tbody').sortable();
		
		/*提交字符串*/		
		$('#submit_form').submit(function(){
			//阻止多次请求
			$('[data-role="loading"]').button('loading');

			var competition_id = $('#competition-choose').val();
			var datas = "";
			$('tr').each(function(){
				var son_data = "";
				var _inputs = $(this).find(':input').not('.chosen-search input');
				
				_inputs.each(function(index){
					son_data += ';;' + $(this).val();
				});
				if(son_data != ""){
					son_data = competition_id + son_data;
					datas += "$$" + son_data;
				}
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
	                url: url+'/competitionApplication/insertSave.htmls',
	                data: {form_input:datas}
				})
                .done(function(data){
               	 	ns.site_back(data);
                })
                .fail(internal_error);
            }

			//恢复按钮可点击
			$('[data-role="loading"]').button('reset');

       	    return false;//阻止表单提交
		});
    </script>
