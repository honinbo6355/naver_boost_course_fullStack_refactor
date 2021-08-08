(function() {

    "use strict";

    const displayInfoId = $('#displayInfoId').val();

    let displayInfoImage = {};
    let commentArr = [];
    let averageScore = 0.0;

    const detailController = {
        init : function() {
            this.getProductDetail();

            common.productImageView.init();
            common.displayInfoView.init();
            commentView.init();
            detailInfoView.init();
        },
        getProductDetail : function() {
            $.ajax({
                url : "/api/products/" + displayInfoId,
                type : "GET",
                dataType : "json",
                async : false
            }).done(function(response, textStatus, jqXHR) {
                console.log("response : " + response);
                common.productImageObj.productImages = response.productImages;
                common.displayInfoObj = response.displayInfo;
                displayInfoImage = response.displayInfoImage;
                commentArr = response.comments;
                averageScore = response.averageScore;
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log("textStatus : " + textStatus);
            });
        },
    };

    const commentView = {
        init : function() {
            this.cacheDom();
            this.render();
        },
        cacheDom : function() {
            this.$scoreGraph = $("#scoreGraph");
            this.$averageScore = $("#averageScore");
            this.$totalCommentCnt = $("#totalCommentCnt");
            this.$reviewContainer = $("#reviewContainer");
        },
        render : function() {
            this.$scoreGraph.css("width", (averageScore/5.0) * 100);
            this.$averageScore.html(averageScore.toFixed(1));
            this.$totalCommentCnt.html(commentArr.length + "건");

            if (commentArr.length <= 0) {
                this.$reviewContainer.css("text-align", "center").html("리뷰가 존재하지 않습니다.");
                return;
            }

            this.$reviewContainer.append($("<ul>").attr("id", "reviewUl").attr("class", "list_short_review"));
            $.each(commentArr, function(index, item) {
                if (index >= 3) { // 3개를 초과할 경우 return
                    return false;
                }

                var resultItem = $.extend(true, {}, item, {
                    "score": item.score.toFixed(1),
                    "reservationDate": getDateCommaStr_yyyymmdd(item.reservationDate)
                });
                $("#commentItemTmpl").tmpl(resultItem).appendTo($("#reviewUl"));
            });

            if (commentArr.length > 3) {
                $("#reviewMore").attr("href", "/review/" + displayInfoId).show();
            }
        }
    };

    const detailInfoView = {
        init : function() {
            this.cacheDom();
            this.bindEvent();
            this.render();
        },
        cacheDom : function() {
            this.$reserveBtn = $("#reserveBtn");
            this.$detailInfoLis = $("#detailInfoTab > li");
            this.$productIntro = $("#productIntro");
            this.$detailInfoDiv = $("#detailInfoDiv");
            this.$detailLocationDiv = $("#detailLocationDiv");
            this.$locationMapImg = $("#locationMapImg");
            this.$placeStreet = $("#placeStreet");
            this.$placeLot = $("#placeLot");
            this.$placeName = $("#placeName");
            this.$telephone = $("#telephone");
        },
        bindEvent : function() {
            this.$reserveBtn.on('click', this.clickReserveBtn);
            this.$detailInfoLis.on('click', this.clickDetailInfoLi.bind(this));
        },
        render : function() {
            this.$productIntro.html(common.displayInfoObj.productContent);
            this.$locationMapImg.attr("src", "/" + displayInfoImage.saveFileName);
            this.$placeStreet.html(common.displayInfoObj.placeStreet);
            this.$placeLot.html(common.displayInfoObj.placeLot);
            this.$placeName.html(common.displayInfoObj.placeName);
            this.$telephone.html(common.displayInfoObj.telephone);
        },
        clickReserveBtn : function() {
            window.location.href = "/reserve/" + displayInfoId;
        },
        clickDetailInfoLi : function() {
            var $clickElement = $(event.currentTarget);

            this.$detailInfoLis.children().removeClass("active");
            $clickElement.children().addClass("active");

            switch ($clickElement.attr("id")) {
                case "detailInfo" :
                    this.$detailInfoDiv.removeClass("hide");
                    this.$detailLocationDiv.addClass("hide");
                    break;
                case "detailLocation" :
                    this.$detailInfoDiv.addClass("hide");
                    this.$detailLocationDiv.removeClass("hide");
                    break;
            }
        }
    };

    $('.header').addClass('fade');
    detailController.init();
})();

