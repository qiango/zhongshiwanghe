<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">广告位管理</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
		<form class="form-horizontal" action="" id="search_form" target="_self" search_path="/ad/list.htmls">
			<div class="panel-body stretch">
				<div class="input-group col-xs-6 pull-left">
					<span class="input-group-addon">广告名称</span>
					<input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
					<input class="form-control" type="text" name="ad_name" id="search-input" placeholder="请输入广告名称">						
                </div>
                <div class="col-xs-1">
					<button type="submit" class="btn btn-primary btn-theme-bg pull-left">搜索</button>
				</div>
			</div>
		</form>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align scroll-auto">
		
		<table class="table table-hover table-striped table-align">	
			<caption class="title">						
				<div class="title-text col-xs-4 indent-collapse">广告位列表</div>
				<a href="#/advertisement/insert" class="btn btn-primary btn-theme-bg pull-right indent-collapse btn-fix" id="btn_save_load">录入</a>						
			</caption>
			<thead>	
				<tr>
				    <th>序号</th>
                    <th>广告名称</th>
                    <th>广告位置</th>
                    <th>广告类型</th>
                   <!--   <th>图片地址</th>
                      <th>图片链接</th>
                       <th>图片高度</th>
                       <th>图片宽度</th> -->
                    <th>广告状态</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>操作</th>
				</tr>
			</thead>
			<tbody>
			 
			</tbody>
		</table>
	</div>
	<div class="display-align cad-page">
	
	</div>
		<div class="modal fade in" id="detail-modal">
		    <div class="modal-dialog">
		      <div class="modal-content">		
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
		          <h3 class="modal-title"></h3>
		        </div>
		        <div class="modal-body container-fluid" id="view-detail">

	           	</div>
	            <div class="modal-footer">
        	   	   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      		    </div>
	        </div>
	      </div>
	    </div>
	    
	    <div class="modal fade in" id="fix-modal">
		    <div class="modal-dialog">
		      <div class="modal-content">		
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
		          <h3 class="modal-title">修改赛事状态</h3>
		        </div>
		        <div class="modal-body container-fluid" id="view-detail">
		        	
		        	<span class="col-xs-2 mt-7">请选择</span>
		        	<div class="col-xs-10 stretch">
              			<select name="new_status" class="drop-btn width-200 select-choose form-control">
							<option value="1">预热</option>
							<option value="2">报名中</option>
							<option value="3">赛事准备中</option>
							<option value="4">赛事进行中</option>
							<option value="5">赛事结束</option>
						</select>
            		</div>
            		
	           	</div>
	            <div class="modal-footer">
        	   	   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        	   	   <button type="button" class="btn btn-primary" data-dismiss="modal" id="modal-save-btn">修改</button>
      		    </div>
	        </div>
	      </div>
	    </div>
 	
	<script type="text/javascript">
	cadleo_data('/ad/list.htmls');
	function showData(data){
		$('.cad-page').html(data.data.pageString);
		var data = data.data.result;
		window.JSONdata = data;
		var trs='';
		for(var i in data){
			var tds='<tr>';
			tds += "<td>"+(Number(i)+1)+"</td>";
			tds += "<td>"+(data[i].ad_name||'')+"</td>";
			tds += "<td>"+(data[i].location_name||'')+"</td>";
			tds += "<td>"+(data[i].ad_type_name||'')+"</td>";
			
	/* 		tds += "<td>"+(data[i].image_url||'')+"</td>";
			tds += "<td>"+(data[i].information_link||'')+"</td>";
			tds += "<td>"+(data[i].image_height||'')+"</td>";
			tds += "<td>"+(data[i].image_width||'')+"</td>";
			 */
			tds += "<td>"+(data[i].ad_status_name||'')+"</td>";
			tds += "<td>"+(data[i].ad_start_time||'')+"</td>";
			tds += "<td>"+(data[i].ad_end_time||'')+"</td>";
			tds += "<td class='text-ellipsis'>\
						<a href='#/advertisement/modify' edit_id='"+data[i].ad_id+"' class='fa fa-pencil-square-o v-align wj_edit_data' title='编辑'></a>\
						<a href='javascript:void(0);' delete_id='"+data[i].ad_id+"' delete_path='/ad/delete.htmls?ad_id="+data[i].ad_id+"' back_path='/advertisement/index' class='fa fa-trash-o wj_delete_data' title='删除'></a></td>";
					//	<a href='#detail-modal' ad_id='"+data[i].ad_id+"' class='fa fa-eye wj_view_data' data-toggle='modal' data-view-id='"+ i +"' title='查看详情'></a></td>";
					//	<a href='#fix-modal' data-toggle='modal' fix_path='/competition/changeStatus.htmls' competition_id='"+ data[i].competition_id +"' competition_status='"+ data[i].competition_live_status +"' class='fa fa-wrench wj_fix_data' title='修改赛事状态'></a>\
					//	<a href='#/competition/clubCompRank'  edit_id='"+data[i].competition_id+"' class='fa fa-gavel v-align wj_edit_data' title='俱乐部赛事成绩排名'></a>
						
			tds += '</tr>';
			trs += tds;
		}
		$('table.table-align tbody').html(trs);
	}
	
	function showDetail(view_id,modal,JSONdata){
		modal.find('.modal-title').html(JSONdata[view_id].competition_name);
		var tds='';		
			tds += "<span class='col-xs-4 com-item'>用户名&nbsp;：&nbsp;"+(JSONdata[view_id].user_real_name||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>平台&nbsp;：&nbsp;"+(JSONdata[view_id].platform_name||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>语言&nbsp;：&nbsp;"+(JSONdata[view_id].language_name||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>赛事级别&nbsp;：&nbsp;"+(JSONdata[view_id].competition_level||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>赛事状态&nbsp;：&nbsp;"+(JSONdata[view_id].competition_status||' ')+"</span>";						
			tds += "<span class='col-xs-4 com-item'>创建日期&nbsp;：&nbsp;"+(JSONdata[view_id].create_date||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>创建时间&nbsp;：&nbsp;"+(JSONdata[view_id].create_time||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>报名开始时间&nbsp;：&nbsp;"+(JSONdata[view_id].registration_start_date||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>报名结束时间&nbsp;：&nbsp;"+(JSONdata[view_id].registration_end_date||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>比赛开始时间&nbsp;：&nbsp;"+(JSONdata[view_id].competition_start_date||' ')+"</span>";
			tds += "<span class='col-xs-4 com-item'>比赛结束时间&nbsp;：&nbsp;"+(JSONdata[view_id].competition_end_date||' ')+"</span>";
			tds += "<div class='embed-responsive rep-box'><img class='embed-responsive-item' src='"+url+JSONdata[view_id].competition_publicity_pictures+"' alt='加载失败'></div>";
			tds += "<fieldset><legend>比赛规则</legend>"+(JSONdata[view_id].competition_description||' ')+"</fieldset>";
		$('#view-detail').html(tds);
	}
	$('#detail-modal').on('show.bs.modal', function (event) {
		showDetail($(event.relatedTarget).attr('data-view-id'),$(this),JSONdata);
	});
	$('#fix-modal').on('hidden.bs.modal', function (event) {
		var parent = $(this).find('[name="new_status"]').parent();
		var select = $(this).find('[name="new_status"]').removeAttr('style').next().remove().end().clone();
		$(this).find('[name="new_status"]').remove();
		parent.append(select);
	});

	$('#fix-modal').on('show.bs.modal', function (event) {
		$('#modal-save-btn').attr('fix_path',$(event.relatedTarget).attr('fix_path'));
		$('#modal-save-btn').attr('competition_id',$(event.relatedTarget).attr('competition_id'));
		var opt = $(this).find('.select-choose option');
		for(var i = 0 ; i < opt.length; i++){
			if(opt.eq(i).val()==$(event.relatedTarget).attr('competition_status')){
				opt.prop('selected',false).eq(i).prop('selected',true);
				$('[name="new_status"]').chosen().next('.chosen-container').css({width:$('[name="new_status"]').css('width')});
				break;
			}
		}
	});
	</script>

