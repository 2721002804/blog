var totalRecord,currentPage;
$(function () {
    to_page(1);
});
function to_page(pn) {
    $.ajax({
        url:"/blogs2",
        data:"pn="+pn,
        type:"GET",
        success:function (result) {
        	if(result.login){
        		$("#loginorwelcome").attr("href","/personal");
        		$('#loginorwelcome').html("您好，"+result.currentuser.username);
        	}else{
        		$("#loginorwelcome").attr("href","/login");
        		$('#loginorwelcome').html('您好，请登录');
        	}
            build_blogs_div(result);
            build_page_info(result);
            build_page_nav(result);
        }
    })
}
function build_blogs_div(result) {
    $("#contient").empty();
    var blogs = result.pageInfo.list;
    $.each(blogs,function (index,item) {
        var mcontent = item.content.substr(0,50);
        var box = $("<div></div>").attr("id",item.id);
        var title = $("<div></div>").append($("<h1></h1>").addClass("xx").css("cursor","pointer").append(item.title));
        var content = $("<div></div>").append($("<h3></h3>").append(mcontent));
        var auth=$("<div></div>").append($("<h5></h5>").append("作者："+item.user.username+"  最近更新时间："+new Date(item.upTime).Format("yyyy-MM-dd hh:mm:ss")).css("text-align","right")).append($("<hr><br>"));
        box.append(title).append(content).append(auth);
        $("#contient").append(box);
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

$(document).on("click",".xx",function () {
    var blogid = $(this).parent().parent().attr("id")
    window.location.href = '/article2?blogid2='+blogid;
})
