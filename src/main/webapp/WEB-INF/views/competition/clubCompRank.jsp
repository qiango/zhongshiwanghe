<%@page import="org.aspectj.weaver.bcel.AtAjAttributes"%>
<%@page import="com.hongzhi.zswh.util.date.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">俱乐部赛事成绩</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>
	<!-- 
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
	 -->
	<div class="panel panel-default col-xs-11 stretch panel-h-align">		
		<table class="table table-hover table-striped table-align">
			<caption class="title">
				<div class="title-text col-xs-3 indent-collapse">俱乐部列表</div>
				<a href="javascript:void(0)"
					class="btn btn-primary btn-theme-bg pull-right indent-collapse btn-fix" data-publish = "1"
					id="btn-publish">发布</a>
			</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>俱乐部名称</th>
					<th>俱乐部排名</th>
				</tr>
			</thead>
			<tbody>
		
			</tbody>
		</table>
	</div>

	<div class="display-align cad-page">
	</div>
	<script type="text/javascript">
	cadleo_data('/clubScore/index.htmls?competition_id='+sessionStorage.getItem('edit_id'));
	function showData(data){
		var data = data.data.clubList;
		var trs='';
		for(var i = 0; i < data.length; i++ ){
			trs += '<tr>\
						<td>'+(Number(i)+1)+'</td>\
						<td>'+(data[i].club_name||'')+'</td>\
						<td><input class="rank-modify" type="text" data-club-id="'+ (data[i].club_id||'') +'" value="'+(data[i].rank||'')+'" data-value = "'+ (data[i].rank||'') +'" ></td>\
				    </tr>';

		}
		$('table.table-align tbody').html(trs);
	}
	$('table.table-align tbody').on('blur','.rank-modify',function(){
		var _this = $(this);
		$.ajax({
			type: 'post',
			 url: url + '/clubScore/modify.htmls',
			 data: $.param({club_id: _this.attr('data-club-id'), competition_id: sessionStorage.getItem('edit_id'), rank: _this.val() })
		})
		.fail(function(){
			alert('修改失败');
			_this.val(_this.attr('data-value'));
		})
		.done(function(data){
			data.code != 0 && (alert('修改失败'),_this.val(_this.attr('data-value')));
		});
	});
	$('#btn-publish').click(function(){
		var _this = $(this);
		$.ajax({
			 type: 'post',
			  url: url + '/clubScore/publish.htmls',
			 data: $.param({competition_id: sessionStorage.getItem('edit_id'),publish: _this.attr('data-publish')})
		})
		.fail(function(){
			alert('修改失败');
		})
		.done(function(data){
 			if(data.code == 0){
 				 _this.attr('data-publish') == 1 && (_this.text('取消发布'),_this.attr('data-publish',0)) || (_this.text('发布'),_this.attr('data-publish',1));
 			}
 			else{
 				alert('修改失败');
 			}
	 	})
	});
	</script>

