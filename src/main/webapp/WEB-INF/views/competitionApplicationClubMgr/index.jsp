<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    <div class="order-title stretch">
        <div class="title-text col-xs-3 padding-collapse"> 赛事报名表</div>
    </div>
    <div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
        <form class="form-horizontal" action="" target="_self" id="search_form" search_path="/competitionApplication/list.htmls">
            <div class="panel-body stretch">
                <div class="input-group col-xs-6 pull-left">
                    <span class="input-group-addon">赛事名</span>
                    <input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
                    <input class="form-control" type="text" name="competition_name" id="search-input" placeholder="请输入赛事名">
                </div>
                <div class="col-xs-1">
                    <button type="submit" class="btn btn-primary bg-theme border-theme pull-left">搜索</button>
                </div>
            </div>
        </form>
    </div>
    
    
    <div class="panel panel-default col-xs-11 stretch panel-h-align">
        
        <table class="table table-hover table-striped table-align">
        	<caption class="title">
	            <div class="title-text col-xs-4 indent-collapse">赛事列表</div>
	            
        	</caption>
            <thead>
                <tr>
                    <th>序号</th>
                    <th>赛事名称</th>
                    <th>已报名人数</th>
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
	cadleo_data('/competitionApplication/list.htmls');
	function showData(data){
		$('.cad-page').html(data.data.pageString);
		var data = data.data.result;
		var trs='';
		for(var i in data){
			var tds='<tr>';
			tds += "<td>"+(Number(i)+1)+"</td>";
			tds += "<td>"+data[i].competition_name+"</td>";
			if(data[i].numberOfSignUp==null){
    			tds += "<td>0</td>";
    		}else{
    			tds += "<td>"+data[i].numberOfSignUp+"</td>";
    		}

			tds += "<td class='text-ellipsis'><a href='#/competitionApplicationClubMgr/eye' edit_id='"+ data[i].competition_id +"' class='fa fa-eye wj_view_data' title='详情'></a></td>";

			tds +='</tr>';
			trs +=tds;
		}
		$('table.table-align tbody').html(trs);		
	}
 
	</script>
    