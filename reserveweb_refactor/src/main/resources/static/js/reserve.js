(function() {
    "use strict";

    const displayInfoId = $("#displayInfoId").val();
    let prices = [];
    let totalCount = 0;

    let payload = {
        "displayInfoId" : Number(displayInfoId),
        "prices" : [],
        "productId" : 0,
        "reservationName" : "",
        "reservationTelephone" : "",
        "reservationEmail" : "",
        "reservationYearMonthDay" : ""
    };

    let formValidCheck = {
        "reservationName" : false,
        "reservationTelephone" : false,
        "reservationEmail" : false,
        "terms" : false
    };

    const reserveController = {
        init : function() {
            this.getReservePageInfo();

            common.productImageView.init();
            common.displayInfoView.init();
            priceView.init();
            reserveFormView.init();
        },

        getReservePageInfo : function() {
            $.ajax({
                url : "/api/reserve/" + displayInfoId,
                type : "GET",
                dataType : "json",
                async : false,
                headers : {"Authorization" : localStorage.getItem("token")}
            }).done(function(response, textStatus, jqXHR) {
                console.log("response : " + response);
                common.productImageObj.productImages = response.productImages;
                common.displayInfoObj = response.displayInfo;

                payload.productId = response.displayInfo.productId;
                payload.reservationYearMonthDay = response.reservationDate;
                prices = response.prices;
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log("textStatus : " + textStatus);
                window.location.href = "/mainpage";
            });
        }
    };

    const priceView = {
        init : function() {
            this.cacheDom();
            this.bindEvent();
            this.render();
        },
        cacheDom : function() {
            this.$ticketContainer = $("#ticketContainer");
        },
        bindEvent : function() {
            prices.forEach(function(item, index) {
                payload.prices[index] = {
                    "productPriceId" : item.productPriceId,
                    "count" : 0
                };

                this.$ticketContainer.on("click", "#amountMinusBtn_" + item.productPriceId, function() {
                    this.clickAmountMinusBtn(index, item.productPriceId);
                }.bind(this));

                this.$ticketContainer.on("click", "#amountPlusBtn_" + item.productPriceId, function() {
                    this.clickAmountPlusBtn(index, item.productPriceId);
                }.bind(this));
            }.bind(this));
        },
        render : function() {
            prices.forEach(function(item) {
                $("#priceItemTmpl").tmpl(item).appendTo(this.$ticketContainer);
            }.bind(this));
        },
        clickAmountMinusBtn : function(index, productPriceId) {
            var $amount = $("#amount_" + productPriceId);
            var resultAmountVal;

            if (Number($amount.val()) === 0) {
                return;
            }

            resultAmountVal = Number($amount.val())-1;

            $amount.val(resultAmountVal);

            if (resultAmountVal === 0) {
                $(event.target).addClass("disabled");
            }

            this.calculateTotalTypePrice(resultAmountVal, $("#price_" + productPriceId), $("#totalPrice_" + productPriceId));
            this.calculatePayloadProductPriceCount(index, -1);
            this.calculateTotalCount(-1);
            reserveFormView.validReserveBtn();
        },
        clickAmountPlusBtn : function(index, productPriceId) {
            var $amount = $("#amount_" + productPriceId);
            var resultAmountVal;

            if (Number($amount.val()) === 0) {
                $("#amountMinusBtn_" + productPriceId).removeClass("disabled");
            }

            resultAmountVal = Number($amount.val())+1;
            $amount.val(resultAmountVal);

            this.calculateTotalTypePrice(resultAmountVal, $("#price_" + productPriceId), $("#totalPrice_" + productPriceId));
            this.calculatePayloadProductPriceCount(index, 1);
            this.calculateTotalCount(1);
            reserveFormView.validReserveBtn();
        },
        calculateTotalTypePrice : function(amountVal, $typePrice, $totalTypePrice) {
            $totalTypePrice.text(amountVal * Number($typePrice.text()));
        },
        calculatePayloadProductPriceCount : function(index, count) {
            payload.prices[index].count += count;
        },
        calculateTotalCount : function(count) {
            totalCount += count;
            reserveFormView.$totalCount.text(totalCount);
        }
    };

    const reserveFormView = {
        init : function() {
            this.cacheDom();
            this.bindEvent();
            this.render();
        },
        cacheDom : function() {
            this.$name = $("#name");
            this.$tel = $("#tel");
            this.$email = $("#email");
            this.$reservationDate = $("#reservationDate");
            this.$totalCount = $("#totalCount");
            this.$termsChk = $("#termsChk");
            this.$reserveDiv = $("#reserveDiv");
        },
        bindEvent : function() {
            this.$name.on("keyup", this.inputName);
            this.$tel.on("keyup", this.inputTel);
            this.$email.on("keyup", this.inputEmail);
            this.$termsChk.on("click", this.clickTerms);
            this.$reserveDiv.on("click", this.clickReserveBtn);
        },
        inputName : function() {
            payload.reservationName = $(this).val().trim();

            if ($(this).val().trim().length === 0) {
                formValidCheck.reservationName = false;
            } else {
                formValidCheck.reservationName = true;
            }

            reserveFormView.validReserveBtn();
        },
        inputTel : function() {
            payload.reservationTelephone = $(this).val().trim();

            if ($(this).val().trim().length === 0) {
                formValidCheck.reservationTelephone = false;
            } else {
                formValidCheck.reservationTelephone = true;
            }

            reserveFormView.validReserveBtn();
        },
        inputEmail : function() {
            payload.reservationEmail = $(this).val();

            if ($(this).val().trim().length === 0) {
                formValidCheck.reservationEmail = false;
            } else {
                formValidCheck.reservationEmail = true;
            }

            reserveFormView.validReserveBtn();
        },
        clickTerms : function() {
            formValidCheck.terms = $(this).prop("checked");
            reserveFormView.validReserveBtn();
        },
        validReserveBtn : function() {
            if (Object.keys(formValidCheck).every(element => formValidCheck[element] === true) && totalCount > 0) {
                this.$reserveDiv.removeClass("disable");
            } else {
                this.$reserveDiv.addClass("disable");
            }
        },
        clickReserveBtn : function() {
            $.ajax({
                url: "/api/reserve",
                type: "POST",
                data: JSON.stringify(payload),
                contentType: "application/json; charset=utf-8",
            }).done(function(response, textStatus, jqXHR) {
                console.log("response : " + response);
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log("textStatus : " + textStatus);
            });
        },
        render : function() {
            this.$reservationDate.text(getDateCommaStr_yyyymmdd(payload.reservationYearMonthDay));
        }
    };

    $('.header').addClass('fade');
    reserveController.init();
})();