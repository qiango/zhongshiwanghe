<%@page import="org.aspectj.weaver.bcel.AtAjAttributes"%>
<%@page import="com.hongzhi.zswh.util.date.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">资讯列表</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
		<form class="form-horizontal" action="" id="search_form" target="_self" search_path="/information/list.htmls">
			<div class="panel-body stretch">
				<div class="input-group col-xs-6 pull-left">
					<span class="input-group-addon">资讯</span>
					<input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
					<input class="form-control" type="text" name="search_string" id="search-input" placeholder="请输入资讯标题"> 
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
				<div class="title-text col-xs-3 indent-collapse">资讯列表</div>
				<a href="#/information/insert"
					class="btn btn-primary btn-theme-bg pull-right indent-collapse btn-fix"
					id="btn_save_load">录入</a>
			</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>资讯标题</th>
					<th>资讯类型</th>
				<!-- 	<th>资讯摘要</th> -->
					<th>创建时间</th>
					<th>创建日期</th>
					<th>资讯状态</th>
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
	cadleo_data('/information/list.htmls');
	function showData(data){
		$('.cad-page').html(data.data.pageString);
		var data = data.data.result;
		var trs='';
		for(var i in data){
			var tds='<tr>';
			tds += "<td>"+(Number(i)+1)+"</td>";
			tds += "<td class='wj-title'>"+data[i].information_title+"</td>";
			tds += "<td>"+data[i].information_type+"</td>";
		/* 	tds += "<td class='wj-title'>"+data[i].information_abstract+"</td>"; */
			tds += "<td>"+data[i].create_date+"</td>";
			tds += "<td>"+data[i].create_time+"</td>";
			tds += "<td>"+data[i].information_status+"</td>";
			tds += "<td class='text-ellipsis'><a href='#/information/modify' edit_id='"+data[i].information_id+"' class='fa fa-pencil-square-o v-align wj_edit_data' title='编辑'></a><a href='javascript:void(0);' delete_id='"+data[i].information_id+"' delete_path='/information/delete.htmls?information_id="+ data[i].information_id +"' back_path='/information/index' class='fa fa-trash-o wj_delete_data' title='删除'></a><a href='#' detail_path='"+ url +"/information/index.htmls?information_id="+ data[i].information_id +"' class='fa fa-eye wj_view_data' title='资讯详情'></a></td>";
			tds +='</tr>';
			trs +=tds;
		}
		$('table.table-align tbody').html(trs);
		ns.ellipsis('.wj-title');
	}
	
	</script>
