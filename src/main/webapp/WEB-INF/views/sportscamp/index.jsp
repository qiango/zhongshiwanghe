<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">运动派管理</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
		<form class="form-horizontal" action="" target="_self" id="search_form" method="post" search_path="/sportscamp/list.htmls">
			<div class="panel-body stretch">
				<div class="input-group col-xs-6 pull-left">
					<span class="input-group-addon">运动派名称</span>
					<input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
					<input class="form-control" type="text" name="sports_camp_name" id="search-input" placeholder="请输入运动派名称">
				</div>
				<div class="col-xs-1">
					<button type="submit" class="btn btn-primary btn-theme-bg pull-left">搜索</button>
				</div>
			</div>
		</form>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align">
		
		<table class="table table-hover table-striped table-align">
			<caption class="title">
				<div class="title-text col-xs-3 indent-collapse">运动派列表</div>
				<a href="#/sportscamp/insert"
					class="btn btn-primary btn-theme-bg pull-right indent-collapse btn-fix"
					id="btn_save_load">录入</a>
			</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>语言</th>
					<!-- <th>用户</th> -->
					<th>运动派名称</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
	<div class="display-align cad-page">
	</div>
	<script type="text/javascript">
	cadleo_data('/sportscamp/list.htmls');
	function showData(data){
		$('.cad-page').html(data.data.pageString);
		var data = data.data.result;
		var trs='';
		for(var i in data){
			var tds='<tr>';
			tds += "<td>"+(Number(i)+1)+"</td>";
			tds += "<td>"+data[i].language_name+"</td>";
		/* 	tds += "<td>"+data[i].user_real_name+"</td>"; */
			tds += "<td>"+data[i].sports_camp_name+"</td>";
			tds += "<td>"+data[i].remark+"</td>";
			tds += "<td class=''><a href='#/sportscamp/modify' edit_id='"+data[i].sports_camp_id+"' class='fa fa-pencil-square-o v-align wj_edit_data' title='编辑'></a><a href='javascript:void(0);'delete_id='"+data[i].sports_camp_id+"' delete_path='/sportscamp/delete.htmls?sports_camp_id="+ data[i].sports_camp_id +"' back_path='/sportscamp/index' class='fa fa-trash-o wj_delete_data' title='删除'></a></td>";
			tds+='</tr>';
			trs+=tds;
		}
		$('table.table-align tbody').html(trs);
	}
	

	</script>


