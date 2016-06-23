<%@page import="org.aspectj.weaver.bcel.AtAjAttributes"%>
<%@page import="com.hongzhi.zswh.util.date.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">俱乐部赛事成绩</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
		<form class="form-horizontal" action="" id="search_form" target="_self" search_path="/club/list.htmls">
			<div class="panel-body stretch">
				<div class="input-group col-xs-6 pull-left">
					<span class="input-group-addon">俱乐部名称</span>
					<input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
					<input class="form-control" type="text" name="club_name" id="search-input" placeholder="请输入俱乐部名称"> 
				</div>
                <div class="col-xs-1">
					<button class="btn btn-primary bg-theme border-theme pull-left">搜索</button>
				</div>
			</div>
		</form>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align">		
		<table class="table table-hover table-striped table-align">
			<caption class="title">
				<div class="title-text col-xs-3 indent-collapse">俱乐部列表</div>
				<a href="#/club/insert"
					class="btn btn-primary bg-theme border-theme pull-right indent-collapse btn-fix"
					id="btn_save_load">录入</a>
			</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>俱乐部名称</th>
					<th>申请人</th>
					<th>城市</th>
					<th>语言</th>
					<th>俱乐部成立日期</th>
					<!-- <th>俱乐部qq群</th> -->
					<th>俱乐部状态</th>
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
	cadleo_data('/clubScore/index.htmls');
	function showData(data){
		$('.cad-page').html(data.data.pageString);
		var data = data.data.result;
		var trs='';
		for(var i in data){
			var tds='<tr>';
			tds += "<td>"+(Number(i)+1)+"</td>";
			tds += "<td>"+(data[i].club_name||'')+"</td>";
			tds += "<td>"+(data[i].club_applicant_name||'')+"</td>";
			tds += "<td>"+(data[i].name||'')+"</td>";
			tds += "<td>"+(data[i].language_name||'')+"</td>";
			tds += "<td>"+(data[i].club_create_date||'')+"</td>";
		/* 	tds += "<td>"+data[i].club_qq+"</td>"; */
			tds += "<td>"+(data[i].club_status||'')+"</td>";
			tds += "<td class=''><a href='#/club/modify' edit_id='"+data[i].club_id+"' class='fa fa-pencil-square-o v-align wj_edit_data' title='编辑'></a><a href='javascript:void(0);' delete_id='"+data[i].club_id+"' delete_path='/club/delete.htmls?club_id="+ data[i].club_id +"' back_path='/club/index' class='fa fa-trash-o wj_delete_data' title='删除'></a><a href='javascript:void(0);' cog_path='/club/sportscamp' club_id='"+data[i].club_id+"' edit_id='"+data[i].club_id+"' class='fa fa-cog wj_cog_data' title='配置'></a></td>";
			tds +='</tr>';
			trs +=tds;
		}
		$('table.table-align tbody').html(trs);
	}
	//showData($.parseJSON('${club_list}'));

	</script>
</body>
</html>