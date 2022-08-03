(function() {
    "use strict";

    const loginController = {
        init : function() {
            loginView.init();
        }
    };

    const loginView = {
        init : function() {
            this.cacheDom();
            this.bindEvent();
        },
        cacheDom : function() {
            this.$email = $("#email");
            this.$password = $("#password");
            this.$loginBtn = $("#loginBtn");
        },
        bindEvent : function() {
            this.$loginBtn.on("click", this.clickLoginBtn);
        },
        clickLoginBtn : function(e) {
            e.preventDefault();

            var payload = {
                email : loginView.$email.val(),
                password : loginView.$password.val()
            };

            $.ajax({
                url: "/api/doLogin",
                type: "POST",
                data: JSON.stringify(payload),
                contentType: "application/json; charset=utf-8",
            }).done(function(response, textStatus, jqXHR) {
                console.log("response : " + response);
                localStorage.setItem("grantType", response.grantType);
                localStorage.setItem("accessToken", response.accessToken);
                localStorage.setItem("refreshToken", response.refreshToken);
                localStorage.setItem("email", response.email);
                localStorage.setItem("userId", response.userId);
                localStorage.setItem("accessTokenValidityTime", response.accessTokenValidityTime);
                localStorage.setItem("refreshTokenValidityTime", response.refreshTokenValidityTime);

                window.location.href = "/mainpage";

            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log("textStatus : " + textStatus);
            });
        }
    };

    loginController.init();
})();
