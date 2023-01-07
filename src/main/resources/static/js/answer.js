var main = {
    init: function () {
        var _this = this;
        $('#answer-save').on('click', function () {
            _this.save();
        });
        $('.answer-update').on('click',function (){
            _this.update();
        });
        $('.answer-delete').on('click',function (){
            _this.delete();
        });
    },
    save: function (){
        var data={
            comment: $('#inputComment').val(),
            qid: $('#inputQid').val(),
            userid: $('#inputAuthor').val()
        };

        if(!data.comment||data.comment.trim()==""){
            alert("내용을 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'POST',
                url: "/api/question/save/"+data.userid+"/comment/"+data.qid,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function () {
                if(data=="false"){
                    alert("존재하지 않는 글입니다.");
                    return false;
                }
                else{
                    alert("등록완료");
                    location.href="/question/detail/"+data.qid;
                }

            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }
}

main.init();