var main = {
    init: function () {
        var _this = this;
        $('#comment-save').on('click', function () {
            _this.save();
        });
        $('#comment-update').on('click',function (){
            _this.update();
        });
        $('#comment-delete').on('click',function (){
            _this.delete();
        });
    },
    save: function (){
        var data={
            comment: $('#inputComment').val(),
            iid: $('#inputIid').val(),
            userid: $('#inputAuthor').val()
        };

        if(!data.comment||data.comment.trim()==""){
            alert("내용을 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'POST',
                url: "/api/information/save/"+data.userid+"/comment/"+data.iid,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function () {
                if(data=="false"){
                    alert("존재하지 않는 글입니다.");
                    return false;
                }
                else{
                    alert("등록완료");
                    location.href="/information/detail/"+data.iid;
                }

            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },
    update: function (){
        var data= {
            comment: $('#updateComment').val(),
            iid: $('#inputIid').val(),
            userid: $('#inputAuthor').val(),
            cid: $('#inputCid').val()
        };

        if(!data.comment||data.comment.trim()==""){
            alert("내용을 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'PUT',
                url: "/api/information/update/comment/"+data.cid,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function () {
                if(data=="false"){
                    alert("존재하지 않는 글입니다.");
                    return false;
                }
                else{
                    alert("수정완료");
                    location.href="/information/detail/"+data.iid;
                }


            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },
    delete: function (){
        var cid = $('#inputCid').val();
        var iid = $('#inputIid').val();

        $.ajax({
            type: 'DELETE',
            url: "/api/information/delete/comment/"+cid,
            dataType:"json",
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('삭제완료');
            location.href="/information/detail/"+iid;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

main.init();