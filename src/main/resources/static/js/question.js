var main = {
    init: function () {
        var _this = this;
        $('#question_list').on('click',function (){
            location.href="/question/list";
        });
        $('#question_save').on('click', function () {
            location.href="/question/save";
        });
        $('#question_detail').on('click',function (){
            var qid = $('#inputQid').val();
            location.href="/question/detail/"+qid;
        });
        $('#question-delete').on('click',function (){
            _this.delete();
        });
        $('#question-imageDelete').on('click',function (){
            _this.imageDelete();
        });
    },
    delete: function (){
        var qid = $('#inputQid').val();

        $.ajax({
            type: 'DELETE',
            url: "/api/question/delete/"+qid,
            dataType:"json",
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('삭제완료');
            location.href="/question/list";
        }).fail(function (error) {
            alert(qid);
            alert(JSON.stringify(error));
        })
    },
    imageDelete: function (){
        var qid = $('#inputQid').val();

        $.ajax({
            type: 'DELETE',
            url: "/api/question/delete/"+qid+"/image",
            dataType:"json",
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('이미지 삭제완료');
            location.href="/question/detail/"+qid;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

main.init();