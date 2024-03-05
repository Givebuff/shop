function menuCategoryDelete(id) {
    $.ajax({
        url: '/menu/category/delete',
        method: 'delete',
        data : {
            "id" : id
        },
        dataType : 'json',
        success: function (data, status, xhr) {
            console.log("data : : " + JSON.stringify(data));
        },
        error: function (data, status, err) {
            console.log("err");
            console.log(err);
        },
        complete: function () {
            document.location.href="/menu"
        }
    });
}

function menuDelete(id) {
    $.ajax({
        url: '/menu/delete',
        method: 'delete',
        data : {
            "id" : id
        },
        dataType : 'json',
        success: function (data, status, xhr) {
            console.log("data : : " + JSON.stringify(data));
        },
        error: function (data, status, err) {
            console.log("err");
            console.log(err);
        },
        complete: function () {
            document.location.href="/menu"
        }
    });
}