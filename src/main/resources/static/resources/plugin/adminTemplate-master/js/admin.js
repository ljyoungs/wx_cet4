;(function($,undefined){
	var defaults = {
		//后台管理员链接
		managerUrl	:	"javascript:void(0)",
		//后台管理员名
		managerName : 	"管理员",
		managerIcon :   "",
		//欢迎语
		sayHello	:	"Hi",
		//是否有登出
		hasLogout	:	true,
		//登出点击跳转的地址
		logoutUrl	:	"javascript:void(0)",
		//登出的文本
		logoutText	:	"Logout",
		//是否有搜索
		hasSearch	:	true,
		//搜索框占位字符串，hasSearch = true时才生效
		searchPlaceHolder	:	"Looking for something?",
		//搜索框提交的地址
		searchAction		:	"javascript:void(0)",
		//搜索框点击回调函数，如果函数被定义则searchAction永远为javascript:void(0)，并在回调时将搜索框内容传入该方法
		searchCallback		:	null,
		//搜索框name属性，该属性为后台接受的key
		searchName			:	"keyword",
		//菜单，数组形式，最多支持到二级菜单
		menu				:	null,
		//一级菜单没有指定图标时使用的默认图标
		menuDefaultImg		:	"img/icons/menu/layout.png",
		//菜单项点击事件处理中心，传入菜单key值、菜单文本、内容体title、内容体
		menuHandler			:	function(key,text,title,content){
			title.text(text);
			content.text(key);
		}
		
	};
	var options;
	window.adminTemplate = {
		
		init : function(opt){
			options = $.extend({},defaults,opt);
			this.generateHead();
			this.generateMenu();
			this.generateContent();
			menuClick();
		},
		getSearchKey : function(){
			if(options.hasSearch){
				return $("#aT_search").val();
			}
		},
		//生成菜单栏
		generateMenu : function(){
			if(options.menu != null){
				var menu = options.menu;
				var slider = '<div id="at-sidebar"><ul>';
				for(var i = 0; i < menu.length; i++){
					var li = '<li' + (i==0?(" class=\"current\""):("")) + '><a href="javascript:void(0)"' + (menu[i].subMenu?(""):(" key=\""+ menu[i].key +"\" class=\"aT_getEvent\"")) + '>';
					li += '<img src=\"' + (menu[i].img?(menu[i].img):(options.menuDefaultImg)) +'\" \/>';
					li += menu[i].name + '</a>';
					if(menu[i].subMenu){
						li += '<ul>';
						var subMenu = menu[i].subMenu;
						for(var j = 0; j < subMenu.length; j++){
							var subLi = '<li' + ((j==0 && i==0)?(" class=\"current\""):("")) + '><a href="javascript:void(0)" key=\"'+ subMenu[j].key +'\" class=\"aT_getEvent\">';
							subLi += subMenu[j].name + '</a></li>';
							li += subLi;
						}
						li += '</ul>';
					}
					li += '</li>';
					slider += li;
				}
				slider += '</ul></div>';
				document.write(slider);
			}
		},
		//生成标题栏
		generateHead : function(){

    		var head = '<div id="at-head"><div class="left">';
    		head += '<a href="javascript:void(0)" class="button profile"><img src="'+ options.managerIcon +'" alt="" /></a>';
    		head += options.sayHello + ', ' + '<a href="'+ options.managerUrl +'">'+ options.managerName +'</a>';
    		if(options.hasLogout == true){
    			head += '&nbsp;|&nbsp;<a href="'+ options.logoutUrl +'">'+ options.logoutText +'</a>';
    		}
    		head += '</div>';
    		if(options.hasSearch == true){
    			head += '        <div class="right">'+
				'                <form action="'+ (options.searchCallback == null?options.searchAction:defaults.searchAction) +'" class="search placeholder">'+
				'                    <input type="text" value="" id="aT_search" name="'+ options.searchName +'" class="text" placeholder="'+ options.searchPlaceHolder +'"/>'+
				'                    <input type="submit" value="rechercher" id="aT_search_button" class="submit"/>'+
				'                </form>'+
				'        		 </div>';
    		}
    		head += '</div>';
			document.write(head);
		},
		//生成内容体,默认为第一项的文本
		generateContent : function(){
			var defaultTitle = "内容标题";
			var content = 
			'<div id="at-content" class="white">'+
			'        <div class="bloc">'+
			'	    <div class="title">'+ defaultTitle +
			'	    </div>'+
			'	    <div class="content"></div>'+
			'        </div>'+
			'</div>';
			document.write(content);
		},
		/**
		 * 得到内容体容器
		 * @param{Boolean} dom 是否返回js-dom对象，默认返回Jquery对象
		 */
		getContent : function(dom){
			var content = $("#at-content .content");
			if(dom){
				return content[0];
			}
			return content;
		},
		/**
		 * 得到内容体标题
		 * @param{Boolean} dom 是否返回js-dom对象，默认返回Jquery对象
		 */
		getContentTitle : function(dom){
			var title = $("#at-content .title");
			if(dom){
				return title[0];
			}
			return title;
		}
		
	}
	//必须运行
	function menuClick(){
		var innerTemplate = window.adminTemplate;
		var currentMenu = null;
	    $('#at-sidebar>ul>li').each(function() {
	        if ($(this).find('li').length == 0) {
	            $(this).addClass('nosubmenu');
	        }
	    });
	    $('#at-sidebar>ul>li[class!="nosubmenu"]>a').each(function() {
	        if (!$(this).parent().hasClass('current')) {
	            $(this).parent().find('ul:first').hide();
	        } else {
	            currentMenu = $(this);
	        }
	        $(this).click(function() {
	            $('#at-sidebar>ul>li.current').removeClass('current');
	            if (currentMenu != null && currentMenu.text() != $(this).text()) {
	                currentMenu.parent().find('ul:first').slideUp();
	            }
	            if (currentMenu != null && currentMenu.text() == $(this).text()) {
	                currentMenu.parent().find('ul:first').slideUp();
	                currentMenu = null;
	            } else {
	                currentMenu = $(this);
	                currentMenu.parent().addClass('current');
	                currentMenu.parent().find('ul:first').slideDown();
	            }
	            return false;
	        });
	    });
	    //菜单事件
	    $(".aT_getEvent").click(function(){
	    	var cur = $(this);
	    	$(".current").removeClass("current");
    		cur.parent().addClass("current");
    		options.menuHandler(cur.attr("key"),cur.text(),innerTemplate.getContentTitle(),innerTemplate.getContent());
	    });
		var cur = $(".current");
		var curSub = cur.find(".current");
		if(curSub.length == 0){
			cur.find("a").click();
		}else{
			curSub.find("a").click();
		}
	    //搜索事件
	    $("#aT_search_button").click(function(){
	    	if(options.searchCallback != null && options.searchCallback instanceof Function){
	    		options.searchCallback(innerTemplate.getSearchKey());
	    	}
	    });
	}

})(jQuery);
