var totalRecord,currentPage;
$(function () {
    $.ajax({
        url:"/spc",
        type:"GET",
        success:function (data) {
            $("#tle").html(data.blog.title);
            $("#content").val(data.blog.content);
        }
    })

    to_page(1);
})
$(function(){
	$.ajax({
		url:"yz",
		type:"GET",
		success:function(data){
			if (data.state == "100") {
				$("#comt").removeAttr("disabled");
				$("#tj").removeAttr("disabled");

            }else{
            	var tsl = $("<li></li>").attr("id","tsl").append("未登录，点击登录");
                $("#ts").append(tsl);
            }
		}
	})
})
function to_page(pn) {
    $.ajax({
        url:"/comt",
        data:"pn="+pn,
        type:"GET",
        success:function (result) {
            build_comts_div(result);
            build_page_info(result);
            build_page_nav(result);
        }
    })
}
function build_comts_div(result) {
    $("#comts").empty();
    var comts = result.pageInfo.list;
    $.each(comts,function (index,item) {
        var comment = $("<h4></h4><hr><br>").append(item.user.username+"："+item.content);
        $("#comts").append(comment);
    })
}
function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前第"+result.pageInfo.pageNum+"页，总共" +
        result.pageInfo.pages+"页，总共"+result.pageInfo.total+"条记录")
    totalRecord = result.pageInfo.total;
    currentPage = result.pageInfo.pageNum;
}
function build_page_nav(result) {
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination")
    //构建元素
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.pageInfo.hasPreviousPage == false){
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    }else {
        //为元素添加点击翻页的事件
        firstPageLi.click(function () {
            to_page(1);
        })
        prePageLi.click(function () {
            to_page(result.pageInfo.pageNum -1);
        })
    }
    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
    if (result.pageInfo.hasNextPage == false){
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            to_page(result.pageInfo.pageNum +1);
        })
        lastPageLi.click(function () {
            to_page(result.pageInfo.pages);
        })
    }
    //添加首页和前一页的提示
    ul.append(firstPageLi).append(prePageLi);
    //ul中遍历添加页码号
    $.each(result.pageInfo.navigatepageNums,function (index,item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.pageInfo.pageNum == item){
            numLi.addClass("active")
        }
        numLi.click(function () {
            to_page(item);
        });
        ul.append(numLi);
    })
    //添加下一页和末页的提示
    ul.append(nextPageLi).append(lastPageLi);
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}

$("#tj").click(function () {
	var comt = $("#comt").val();
    $.ajax({
        url:"/insertC",
        type:"PUT",
        data:"comt="+comt,
        success:function (data) {
            if (data.state == "100") {
                to_page(1);
                $("#comt").val("");
            }else
                alert("提交失败");
        }
    })
})
$(document).on("click","#tsl",function () {
	var url1 = window.location.href;
	window.location.href="/login?url="+url1;
})
