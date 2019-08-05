$(function () {
    $.ajax({
        url:"/spc",
        type:"GET",
        success:function (data) {
        	$("#tle").val(data.blog.title);
            $("#cont").val(data.blog.content);
        }
    })

    to_page(1);
})
function to_page(pn) {
    $.ajax({
        url:"/comt",
        data:"pn="+pn,
        type:"GET",
        success:function (result) {
            build_comts_div(result);
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
$("#bj").click(function () {
    $("#tle").removeAttr("disabled");
    $("#cont").removeAttr("disabled");
    $("#xg").removeAttr("disabled");
})
$("#xg").click(function () {
    $.ajax({
        url:"/update",
        type:"PUT",
        data:$("#blog form").serialize(),
        success:function (data) {
            if (data.state == "100") {
                alert("提交成功")
                window.location.href="/personal";

            }else
                alert("提交失败");
        }
    })
})
