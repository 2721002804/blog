var totalRecord,currentPage;
var name;
$(function () {
    to_page(1);
});
function to_page(pn) {
    $.ajax({
        url:"/blogs",
        data:"pn="+pn,
        type:"GET",
        success:function (result) {
            build_blogs_div(result);
            build_page_info(result);
            build_page_nav(result);
            name = result.user.username;
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
        var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
        //为删除按钮添加一个自定义的属性来表示当前删除的员工id
        delBtn.attr("del-id",item.id).css("float","right");
        var changeBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).attr("change-id",item.id).attr("change-state",item.state);
        if(item.state==0){
        	changeBtn.append("设为所有人可见");
        }else{
        	changeBtn.append("设为仅自己可见");
        }
        title.append(delBtn);
        content.append(changeBtn);
        var time=$("<div></div>").append($("<h5></h5>").append("最近更新时间："+new Date(item.upTime).Format("yyyy-MM-dd hh:mm:ss")).css("text-align","right")).append($("<hr><br>"));
        box.append(title).append(content).append(time);
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
$("#name").append($("<h3></h3>").append(name+"的文章"));
$("#main").click(function () {
    window.location.href='/index';
})
$("#write").click(function () {
    window.location.href='/texteditor';
})
$(document).on("click",".xx",function () {
    var blogid = $(this).parent().parent().attr("id")
    window.location.href = '/article?blogid='+blogid;
})

$(document).on("click",".delete_btn",function () {
    //1.弹出是否确认删除对话框
    var title = $(this).prev("h1").text();
    var blogid = $(this).attr("del-id");
    //alert($(this).parents("tr").find("td:eq(1)").text());
    if (confirm("确认删除【"+title+"】吗？")){
        //确认，发送ajax请求删除
        $.ajax({
            url:"/del/"+blogid,
            type:"DELETE",
            success:function (result) {
                alert(result.msg);
                //回到本页
                to_page(currentPage);
            }
        })
    }
})
$(document).on("click",".edit_btn",function () {
    var blogid = $(this).attr("change-id");
    var state=$(this).attr("change-state");
    if(state==1){
    	state=0;
    }else if(state==0){
    	state=1;
    }
    $.ajax({
        url:"/updatestate",
        type:"post",
        data:{
        	blogid:blogid,
        	state:state
        },
        success:function (result) {
        	to_page(currentPage);
        }
    })
  
})
