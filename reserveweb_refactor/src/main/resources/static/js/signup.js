(function() {
    "use strict";

    const signupController = {
        init : function() {
            signupView.init();
        }
    };

    const signupView = {
        init : function() {
            this.cacheDom();
            this.bindEvent();
        },
        cacheDom : function() {
            this.$email = $("#email");
            this.$password = $("#password");
            this.$passwordCheck = $("#password_check");
            this.$signupBtn = $("#signup_btn");
        },
        bindEvent : function() {
            this.$signupBtn.on("click", this.clickSignupBtn);
        },
        clickSignupBtn : function(e) {
            e.preventDefault();

            if (!signupView.isValidInputData()) {
                return;
            }

            var payload = {
                email : signupView.$email.val(),
                password : signupView.$password.val()
            };

            $.ajax({
                url: "/api/signup",
                type: "POST",
                data: JSON.stringify(payload),
                contentType: "application/json; charset=utf-8",
            }).done(function(response, textStatus, jqXHR) {
                window.location.href = "/loginPage";
            }).fail(function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == 400) {
                    alert(jqXHR.responseJSON.message);
                }
            });
        },
        isValidInputData : function() {
            let regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');

            if (!regex.test(this.$email.val())) {
                alert("이메일 형식이 올바르지 않습니다.");
                return false;
            }

            if (this.$password.val() != this.$passwordCheck.val()) {
                alert("비밀번호가 일치하지 않습니다.");
                return false;
            }

            if (this.$password.val().trim().length > 15) {
                alert("비밀번호 길이가 15자 이내여야 합니다.");
                return false;
            }

            return true;
        }
    };

    signupController.init();
})();
