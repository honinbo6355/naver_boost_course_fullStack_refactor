(function() {
	var mainPage = {
		totalProductCount : 0,
		selectedCategoryId : '0',

		init : function() {
			this.drawLogin();
			this.getCategories();
			this.getPromotions();
			this.getProducts(0);
		},

		eventListener : function() {
		    $("#totalList").on("click", mainPage.selectTotalList);
			$("#moreViewBtn").on("click", mainPage.moreView);
		},

		drawLogin : function() {
			if (localStorage.getItem("accessToken") == null) {
				$("#login").show();
				$("#mypage").hide();
			} else {
				$("#login").hide();
				$("#mypage > span").text(localStorage.getItem("email"));
				$("#mypage").show();
			}
		},

		getProducts : function(id, viewCount) {
			$.ajax({
				url : "/api/products",
				data : {
					categoryId: id,
					start: viewCount
				},
				type: "GET",
				dataType: "json"
			}).done(function( response, textStatus, jqXHR ) {
				console.log("response : " + response);

				mainPage.drawProducts(response);
				mainPage.drawMoreViewBtn(response);
			}).fail(function( jqXHR, textStatus, errorThrown ) {
				console.log("textStatus : " + textStatus);
			});
		},

		getCategories : function() {
			$.ajax({
				url: "/api/categories",
				type: "GET",
				dataType: "json"
			}).done(function( response, textStatus, jqXHR ) {
				console.log("response : " + response);
				mainPage.drawCategories(response);
			}).fail(function( jqXHR, textStatus, errorThrown ) {
				console.log("textStatus : " + textStatus);
			});
		},

		getPromotions : function() {
			$.ajax({
				url: "/api/promotions",
				type: "GET",
				dataType: "json"
			}).done(function(response, textStatus, jqXHR) {
				console.log("response : " + response);
				mainPage.drawPromotions(response);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				console.log("textStatus : " + textStatus);
			});
		},

		drawProducts : function(response) {
			$.each(response.items, function(index, item) {
				var parentNodeIdx = index%2;
				$('#productItemTmpl').tmpl(item).appendTo($('.lst_event_box:eq(' + parentNodeIdx + ')'));
			});
		},

		drawMoreViewBtn : function(response) {
			if (response.items.length === 0) {
				$("#moreViewBtn").hide();
			}
		},

		drawCategories : function(response) {
			$.each(response.items, function(index, item) {
				var li = document.createElement("li");
				var a = document.createElement("a");
				var span = document.createElement("span");

				li.className = "item";
				li.dataset.category = item.id;
				li.addEventListener("click", function() {
					mainPage.selectCate(this, item.count);
				});

				a.className = "anchor category";
				span.textContent = item.name;

				mainPage.totalProductCount += item.count;

				a.append(span);
				li.append(a);

				$("#category_tab").append(li);
			});

			mainPage.setProductCount(mainPage.totalProductCount);
		},

		selectTotalList : function() {
			mainPage.selectCate(this, mainPage.totalProductCount);
		},

		setProductCount : function(count) {
			$("#product_count").html(count + "개");
		},

		selectCate : function(selectedCate, count) {
			$('.anchor.category').removeClass('active');
			$(selectedCate).children().addClass('active');
			$('.lst_event_box').empty();
			$("#moreViewBtn").data("view", 1).show();

			mainPage.selectedCategoryId = selectedCate.dataset.category;
			mainPage.setProductCount(count);
			mainPage.getProducts(selectedCate.dataset.category);
		},

		moreView : function() {
			var viewCount = $("#moreViewBtn").data("view");

			mainPage.getProducts(mainPage.selectedCategoryId, viewCount);
			$("#moreViewBtn").data("view", viewCount+1);
		},

		drawPromotions : function(response) {
			var promotionUl = document.querySelector("#promotionArea");
			var promotionCnt = response.items.length;

			$.each(response.items, function(index, item) {
				var resultProductImageUrl = item.productImageUrl;
				$('#promotionItemTmpl').tmpl({resultProductImageUrl}).appendTo(promotionUl);
			});

			slideShow();

			function slideShow() {
				var firstItemClone = promotionUl.firstElementChild.cloneNode(true);
				promotionUl.append(firstItemClone);

				var curIndex = 0;

				setInterval(function() {
					promotionUl.style.transition = "transform 0.5s ease-out";
					promotionUl.style.transform = "translate3d(-" + 414 * (curIndex+1) + "px, 0px, 0px)";
					curIndex++;

					if (curIndex === promotionCnt - 1) {
						setTimeout(function() {
							promotionUl.style.transition = "0s";
							promotionUl.style.transform = "translate3d(0px, 0px, 0px)";
						}, 2001);
					}
					if (curIndex === promotionCnt) {
						curIndex = 0;
					}
				}, 2000);
			}
		}
	};

	mainPage.init();
	mainPage.eventListener();
})();
