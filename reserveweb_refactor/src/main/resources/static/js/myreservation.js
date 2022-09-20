(function() {
    "use strict";

    let myReservations = [];

    const myReserveController = {
        init : function() {
            this.getReservationInfo();
            myReserveView.init();
        },

        getReservationInfo : function() {
            $.ajax({
                url : "/api/reservations",
                type : "GET",
                dataType : "json",
                async : false,
                headers : {"Authorization" : localStorage.getItem("grantType") + " " + localStorage.getItem("accessToken")}
            }).done(function(response, textStatus, jqXHR) {
                console.log("response : " + response);

                myReservations = response.reservations;

            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log("textStatus : " + textStatus);
                window.location.href = "/mainpage"
            });
        }
    };

    const myReserveView = {
        init : function() {
            this.cacheDom();
            this.render();
        },
        cacheDom : function() {
            this.myReserveList = document.querySelector("#myReserveList");
            this.emptyReserveList = document.querySelector("#emptyReserveList");
        },
        bindEvent : function() {

        },
        render : function() {
            if (!myReservations || myReservations.length == 0) {
                this.emptyReserveList.style.display = 'block';
                this.myReserveList.style.display = 'none';
            } else {
                myReservations.forEach((myReservation) => {
                    $("#reservationInfoTmpl").tmpl(myReservation).appendTo(this.myReserveList.querySelector(".card.confirmed"));
                });
            }
        }
    };

    common.drawLogin();
    myReserveController.init();
})();
