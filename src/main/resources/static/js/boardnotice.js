var main = {
    init: function () {
        var _this = this;
        $('#boardNotice_list').on('click',function (){
            location.href="/boardNotice/list";
        });
        $('#boardNotice_save').on('click', function () {
            location.href="/boardNotice/save";
        });
        $('#boardNotice_detail').on('click',function (){
            var iid = $('#inputIid').val();
            location.href="/boardNotice/detail/"+iid;
        });
        $('#boardNotice-delete').on('click',function (){
            _this.delete();
        });
        $('#boardNotice-imageDelete').on('click',function (){
            _this.imageDelete();
        });
        $('#boardNotice_search').on('click',function () {
            var keyword = $('#keyword').val();
            if(!keyword||keyword.trim()==""){
                location.href="/boardNotice/list";
            }
            else{
                location.href="/boardNotice/search/"+keyword;
            }
        });
    },
    delete: function (){
        var bid = $('#inputBid').val();

        $.ajax({
            type: 'DELETE',
            url: "/api/boardNotice/delete/"+bid,
            dataType:"json",
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('삭제완료');
            location.href="/boardNotice/list";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    imageDelete: function (){
        var bid = $('#inputBid').val();

        $.ajax({
            type: 'DELETE',
            url: "/api/boardNotice/delete/"+bid+"/image",
            dataType:"json",
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('이미지 삭제완료');
            location.href="/boardNotice/detail/"+bid;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

main.init();