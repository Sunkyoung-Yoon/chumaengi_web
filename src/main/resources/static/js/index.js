
var main = {
    init: function () {
        var _this = this;
        $('#user-save').on('click', function () {
            _this.save();
        });
        $('#user-update').on('click', function (){
            _this.update();
        });
        $('#user-delete').on('click',function (){
            _this.delete();
        });
        $('#admin-delete').on('click',function (){
            _this.admindelete();
        });
        $('#user-cancel').on('click',function (){
            location.href="/user/login";
        });

    },

    save: function () {
        var getCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/);
        var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);

        var data = {
            username: $('#userName').val(),
            nickname: $('#userNickname').val(),
            userid: $('#userID').val(),
            password: $('#userPW').val(),
            phone: $('#userPhone').val(),
            email: $('#userEmail').val(),
            address: $('#userAddress').val()
        };

        //이름
        if (!data.username || data.username.trim() == "") {
            alert("이름을 입력해주세요!");
            return false;
        }
        //닉네임
        else if (data.nickname.length < 2) {
            alert("닉네임은 2글자이상 입력해주세요");
            return false;
        }
        //아이디
        else if (!data.userid || data.userid.trim() == "") {
            alert("아이디를 입력해주세요");
            return false;
        } else if (!getCheck.test(data.userid)) {
            alert("아이디는 영문대소문자,숫자로 4자~11자이내로 입력해주세요.")
            return false;
        }
        //비밀번호
        else if (!data.password || data.password.trim() == "") {
            alert("비밀번호를 입력해주세요");
            return false;
        } else if (!getCheck.test(data.password)) {
            alert("비밀번호는 영문대소문자,숫자로 4자~11자이내로 입력해주세요.")
            return false;
        }
        //폰번호
        else if (!data.phone || data.phone.trim() == "") {
            alert("연락처를 입력해주세요");
            return false;
        }
        //이메일
        else if (!data.email || data.nickname.trim() == "") {
            alert("이메일을 입력해주세요");
            return false;
        }
        //이메일
        else if (!getMail.test(data.email)) {
            alert("이메일 형식에 맞춰 입력해주세요.")
            return false;
        }
        //주소
        else if (!data.address || data.address.trim() == "") {
            alert("주소를 입력해주세요");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: "/api/user/signup",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (data) {
                if (data == 'true') {
                    alert("회원가입에 성공했습니다.");
                    //회원가입에 성공하면 로그인페이지으로 이동
                    window.location.href = '/user/login';
                } else if(data == 'nicknamefalse'){
                    alert('이미 존재하는 닉네임입니다.');
                    return false;
                }else{
                    alert('이미 존재하는 아이디입니다.');
                    return false;
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })

        }

    },
    update: function (){
        var getCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/);
        var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);

        var data={
            nickname: $('#userNickname').val(),
            password: $('#userPW').val(),
            phone: $('#userPhone').val(),
            email: $('#userEmail').val(),
            address: $('#userAddress').val()
        };
        var userid = $('#userID').val();

        //닉네임
        if (data.nickname.length < 2) {
            alert("닉네임은 2글자이상 입력해주세요");
            return false;
        }
        //비밀번호
        else if (!data.password || data.password.trim() == "") {
            alert("비밀번호를 입력해주세요");
            return false;
        } else if (!getCheck.test(data.password)) {
            alert("비밀번호는 영문대소문자,숫자로 4자~11자이내로 입력해주세요.")
            return false;
        }
        //폰번호
        else if (!data.phone || data.phone.trim() == "") {
            alert("연락처를 입력해주세요");
            return false;
        }
        //이메일
        else if (!data.email || data.nickname.trim() == "") {
            alert("이메일을 입력해주세요");
            return false;
        }
        //이메일
        else if (!getMail.test(data.email)) {
            alert("이메일 형식에 맞춰 입력해주세요.")
            return false;
        }
        //주소
        else if (!data.address || data.address.trim() == "") {
            alert("주소를 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'PUT',
                url: "/api/user/update/"+userid,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (data) {
                if(data=='false')
                    alert('이미 사용중인 닉네임입니다.');
                else{
                    alert('수정완료');
                    //회원수정에 성공하면 마이페이지로 이동
                    window.location.href = '/user/mypage/'+userid;
                }

            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }


    },
    delete: function () {
        var data = {
            password: $('#loginPW').val()
        };

        var userid = $('#loginID').val();

        if (!data.password || data.password.trim() == "") {
            alert("비밀번호를 입력해주세요!");
            return false;
        } else {
            $.ajax({
                type: 'DELETE',
                url: "/api/user/check/" + userid,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function (data) {
                if (data == 'true') {
                    alert("탈퇴완료");
                    window.location.href = '/';
                } else {
                    alert("잘못된 비밀번호입니다.");
                    return false;
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }

    },
    admindelete: function (){
        var data = {
            useridcheck : $('#userIDcheck').val()

        };
        var  userid = $('#userID').val();

        if (userid==data.useridcheck) {

            $.ajax({
                type: 'DELETE',
                url: "/api/user/userfindAlldelete/" + userid,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function () {
                    alert("강제탈퇴완료");
                    window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
        else{
            alert("회원의 아이디와 다릅니다.");
            return false;
        }
    }




}



main.init();
