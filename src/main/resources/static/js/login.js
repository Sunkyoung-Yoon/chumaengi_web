var main = {
    init: function () {
        var _this = this;
        $('#user-login').on('click', function () {
            _this.login();
        });
        $('#user-logout').on('click', function () {
            location.href="/user/logout";
        });
        $('#user-finduserid').on('click',function (){
            _this.finduserid();
        });
        $('#user-finduserpw').on('click',function (){
            _this.finduserpw();
        });
    },
    login: function (){
        var data = {
            userid: $('#loginID').val(),
            password: $('#loginPW').val()
        };
        if(!data.userid||data.userid.trim()==""){
            alert("아이디를 입력해주세요");
            return false;
        }
        else if(!data.password||data.password.trim()==""){
            alert("비밀번호를 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'POST',
                url: '/api/user/login',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function (data) {
                if(data=='true'){
                    window.location.href = '/';
                }else{
                    alert("아이디와 비밀번호를 확인해주세요.");
                    return false;
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },
    finduserid: function (){
        var data = {
            nickname: $('#nickName').val(),
            phone: $('#userPhone').val()
        };
        if(!data.nickname||data.nickname.trim()==""){
            alert("닉네임을 입력해주세요");
            return false;
        }
        else if(!data.phone||data.phone.trim()==""){
            alert("휴대폰번호를 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'POST',
                url: '/api/user/finduserid',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function (data) {
                if(data=='false'){
                    alert("존재하지 않는 회원입니다.");
                    return false;
                }else{
                    alert('아이디는 '+data+'입니다.');
                    window.location.href = '/user/login';
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },
    finduserpw: function (){
        var data = {
            userid: $('#userID').val(),
            nickname: $('#nickName').val()
        };
        if(!data.userid||data.userid.trim()==""){
            alert("아이디를 입력해주세요");
            return false;
        }
        else if(!data.nickname||data.nickname.trim()==""){
            alert("닉네임을 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'POST',
                url: '/api/user/finduserpw',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function (data) {
                if(data=='false'){
                    alert("존재하지 않는 회원입니다.");
                    return false;
                }else{
                    alert('비밀번호는 '+data+'입니다.');
                    window.location.href = '/user/login';
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }

}

main.init();