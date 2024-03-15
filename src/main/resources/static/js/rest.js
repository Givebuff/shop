function restFuncController(method, id, message){
    if(confirm('하시겠습니까?')) {
        method(id);
    }
}

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

function menuOptionDelete(id) {
    $.ajax({
        url: '/menu/option/delete',
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

function kitchenCookComplete(id) {
    $.ajax({
        url:'/kitchen/cook/complete',
        method: 'PUT',
        data:{
            "id" : id
        },
        dataType: 'json',
        success: function(data, status, xhr) {
            console.log("data : " + JSON.stringify(data));
        },
        error: function (data, status, err) {
            console.log("err");
            console.log(err);
        },
        complete: function () {
            location.reload(true);
        }
    })
}