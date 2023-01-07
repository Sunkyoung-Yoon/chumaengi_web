var main = {
    init: function () {
        var _this = this;
        $('#information_list').on('click',function (){
            location.href="/information/list";
        });
        $('#information_save').on('click', function () {
            location.href="/information/save";
        });
        $('#information_detail').on('click',function (){
            var iid = $('#inputIid').val();
            location.href="/information/detail/"+iid;
        });
        $('#information-delete').on('click',function (){
            _this.delete();
        });
        $('#information-imageDelete').on('click',function (){
            _this.imageDelete();
        });
        $('#information_search').on('click',function () {
            var keyword = $('#keyword').val();
            if(!keyword||keyword.trim()==""){
                location.href="/information/list";
            }
            else{
                location.href="/information/search/"+keyword;
            }
        });
    },
    delete: function (){
        var iid = $('#inputIid').val();

        $.ajax({
            type: 'DELETE',
            url: "/api/information/delete/"+iid,
            dataType:"json",
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('삭제완료');
            location.href="/information/list";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    imageDelete: function (){
        var iid = $('#inputIid').val();

        $.ajax({
            type: 'DELETE',
            url: "/api/information/delete/"+iid+"/image",
            dataType:"json",
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('이미지 삭제완료');
            location.href="/information/detail/"+iid;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

main.init();