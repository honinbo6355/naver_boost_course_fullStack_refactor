(function() {
    const logoutController = {
        init : function () {
            logoutView.init();
        },
    };

    const logoutView = {
        init : function() {
            this.cacheDom();
            this.bindEvent();
            this.render();
        },
        cacheDom : function() {
            this.$email = $("#email");
            this.$logoutBtn = $("#logoutBtn");
        },
        bindEvent : function() {
            this.$logoutBtn.on("click", this.logout);
        },
        render : function() {
            this.$email.val(localStorage.getItem("email"));
        },
        logout : function(e) {
            e.preventDefault();

            $.ajax({
                url: "/api/doLogout",
                type: "POST",
                headers : {"Authorization" : localStorage.getItem("grantType") + " " + localStorage.getItem("accessToken")},
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    "accessToken" : localStorage.getItem("accessToken"),
                    "refreshToken" : localStorage.getItem("refreshToken")
                })
            }).done(function(response, textStatus, jqXHR) {
                if (textStatus === 'success') {
                    localStorage.clear();
                    window.location.href = '/mainpage';
                }
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log("textStatus : " + textStatus);
            });
        }
    };

    logoutController.init();
})();
