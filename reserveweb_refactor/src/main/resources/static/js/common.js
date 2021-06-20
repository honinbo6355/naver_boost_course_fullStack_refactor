const common = (function() {
    return {
        productImageObj : {
            productImages : [],
            curImgIdx : 0,
            slideWidth : 414,
            slideSpeed : 300
        },
    
        displayInfoObj : {},
    
        productImageView : {
            init : function() {
                this.cacheDom();
                this.bindEvent();
                this.render();
            },
            cacheDom : function() {
                this.$ul = $("#productImageList");
                this.$currentPage = $("#currentPage");
                this.$totalPage = $("#totalPage");
                this.$prevNav = $("#prevNav");
                this.$nextNav = $("#nextNav");
            },
            bindEvent : function() {
                this.$prevNav.on("click", this.prevSlideClick.bind(this));
                this.$nextNav.on("click", this.nextSlideClick.bind(this));
            },
            render : function() {
                $.each(common.productImageObj.productImages, function(index, item) {
                    const resultItem = $.extend(true, {}, item, {"title" : common.displayInfoObj.productDescription});
                    $("#productImageItemTmpl").tmpl(resultItem).appendTo(this.$ul);
                }.bind(this));
        
                if (common.productImageObj.productImages.length > 1) {
                    let $firstNodeClone = this.$ul.children().first().clone();
                    let $lastNodeClone = this.$ul.children().last().clone();
        
                    this.$ul.prepend($lastNodeClone); // 맨 앞에 복사한 마지막 노드 붙이기
                    this.$ul.append($firstNodeClone); // 맨 뒤에 복사한 첫번째 노드 붙이기
        
                    this.$ul.css("transform", "translate3d(-" + common.productImageObj.slideWidth * (common.productImageObj.curImgIdx+1) + "px, 0px, 0px)"); // 최초에는 translate3d(-414px, 0px, 0px)
                    this.$prevNav.show();
                    this.$nextNav.show();
                    this.$totalPage.text(common.productImageObj.productImages.length);
                }
            },
            prevSlideClick : function() {
                if (common.productImageObj.curImgIdx >= 0) {
                    this.$ul.css("transition", "transform 0.3ms ease-out");
                    this.$ul.css("transform", "translate3d(-" + (common.productImageObj.slideWidth * common.productImageObj.curImgIdx) + "px, 0px, 0px)");
                }
                if (common.productImageObj.curImgIdx === 0) { // 첫번째 노드에서 이전 버튼 클릭했을 경우
                    setTimeout(function() {
                        this.$ul.css("transition", "0ms");
                        this.$ul.css("transform", "translate3d(-" + (common.productImageObj.slideWidth * common.productImageObj.productImages.length) + "px, 0px, 0px)"); // 마지막 노드 위치로 이동
                    }.bind(this), common.productImageObj.slideSpeed);
                    common.productImageObj.curImgIdx = common.productImageObj.productImages.length;
                }
                common.productImageObj.curImgIdx--;
                this.$currentPage.text(common.productImageObj.curImgIdx+1);
            },
            nextSlideClick : function() {
                if (common.productImageObj.curImgIdx <= common.productImageObj.productImages.length - 1) {
                    this.$ul.css("transition", "transform 0.3ms ease-out");
                    this.$ul.css("transform", "translate3d(-" + (common.productImageObj.slideWidth * (common.productImageObj.curImgIdx+2)) + "px, 0px, 0px)");
                }
                if (common.productImageObj.curImgIdx === common.productImageObj.productImages.length - 1) { // 마지막 노드에서 다음 버튼 클릭했을 경우
                    setTimeout(function() {
                        this.$ul.css("transition", "0ms");
                        this.$ul.css("transform", "translate3d(-" + common.productImageObj.slideWidth + "px, 0px, 0px)"); // 첫번째 노드 위치로 이동
                    }.bind(this), common.productImageObj.slideSpeed);
                    common.productImageObj.curImgIdx = -1;
                }
                common.productImageObj.curImgIdx++;
                this.$currentPage.text(common.productImageObj.curImgIdx+1);
            }
        },
    
        displayInfoView : {
            init : function() {
                this.cacheDom();
                this.bindEvent();
                this.render();
            },
            cacheDom : function() {
                this.$productDscContainer = $("#productDscContainer");
                this.$productDsc = $("#productDsc");
                this.$dscOpenBtn = $("#dscOpenBtn");
                this.$dscCloseBtn = $("#dscCloseBtn");
            },
            bindEvent : function() {
                this.$dscOpenBtn.on("click", this.dscOpenBtnClick.bind(this));
                this.$dscCloseBtn.on("click", this.dscCloseBtnClick.bind(this));
            },
            render : function() {
                this.$productDsc.html(common.displayInfoObj.productContent);
            },
            dscOpenBtnClick : function() {
                this.$dscOpenBtn.hide();
                this.$dscCloseBtn.show();
                this.$productDscContainer.removeClass("close3");
            },
            dscCloseBtnClick : function() {
                this.$dscCloseBtn.hide();
                this.$dscOpenBtn.show();
                this.$productDscContainer.addClass("close3");
            }
        }
    }
})();

function getDateCommaStr_yyyymmdd(dateStr) {
    var date = new Date(dateStr);
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth()+1).toString();
    var dd = date.getDate().toString();

    return yyyy + "." + mm + "." + dd;
}
