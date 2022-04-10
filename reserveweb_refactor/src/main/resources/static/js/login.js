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
                localStorage.setItem("accessToken", response.grantType + " " + response.accessToken);
                localStorage.setItem("refreshToken", response.grantType + " " + response.refreshToken);
                localStorage.setItem("email", response.email);

                window.location.href = "/mainpage";
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log("textStatus : " + textStatus);
            });
        }
    };

    loginController.init();
})();
