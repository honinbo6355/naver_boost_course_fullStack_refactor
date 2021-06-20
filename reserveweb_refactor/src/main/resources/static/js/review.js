(function() {
    "use strict";

    const displayInfoId = $('#displayInfoId').val();

    let commentArr = [];
    let averageScore = 0.0;

    const reviewController = {
        init : function() {
            this.getProductDetail();

            commentView.init();
        },
        getProductDetail : function() {
            $.ajax({
                url : "/api/products/" + displayInfoId,
                type : "GET",
                dataType : "json",
                async : false
            }).done(function(response, textStatus, jqXHR) {
                console.log("response : " + response);
                commentArr = response.comments;
                averageScore = response.averageScore;
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log("textStatus : " + textStatus);
            });
        }
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
            this.$reviewUl = $("#reviewUl");
        },
        render : function() {
            this.$scoreGraph.css("width", (averageScore/5.0) * 100);
            this.$averageScore.html(averageScore.toFixed(1));
            this.$totalCommentCnt.html(commentArr.length + "건");

            $.each(commentArr, function(index, item) {
                var resultItem = $.extend(true, {}, item, {
                    "score": item.score.toFixed(1),
                    "reservationDate": getDateCommaStr_yyyymmdd(item.reservationDate)
                });
                $("#commentItemTmpl").tmpl(resultItem).appendTo(this.$reviewUl);
            }.bind(this));
        }
    }

    $('.header').addClass('fade');
    reviewController.init();
})();