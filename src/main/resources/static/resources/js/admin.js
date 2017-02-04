

var menu = [ {
	name : "听力",
	img : "resources/plugin/adminTemplate-master/img/icons/menu/inbox.png",
	key : "one"
}, {
	name : "阅读",
	img : "resources/plugin/adminTemplate-master/img/icons/menu/layout.png",
	key : "two"
}, {
	name : "写作",
	img : "resources/plugin/adminTemplate-master/img/icons/menu/brush.png",
	key : "three"
} ,{
	name : "单词",
	img : "resources/plugin/adminTemplate-master/img/icons/menu/brush.png",
	key : "four"
}];
adminTemplate.init({
	menu : menu,
	managerIcon : '/resources/img/huser.png',
	managerName : window.curUserName,
	logoutUrl : '/login?logout',
	searchCallback : function(key) {
		alert("您要搜索的为：" + key);
	},
	menuHandler : handler,
});

// 菜单事件回调处理中心
function handler(key, text, title, content) {
	// 标题变换
	title.text(text);
	// 根据key值处理相关事件
	switch (key) {
	case "one": {
		content
				.html('<div id="container" class="dlshouwen-grid-container" onselectstart="return false;" style="-moz-user-select: none;"></div> <div id="toolBar" class="dlshouwen-grid-toolbar-container"></div>');
		getListen();
		break;
	}
	case "two": {
		content
				.html('<div id="containerRead" class="dlshouwen-grid-container" onselectstart="return false;" style="-moz-user-select: none;"></div> <div id="toolBarRead" class="dlshouwen-grid-toolbar-container"></div>');
		getRead();
		break;
	}
	case "three": {
		content
		.html('<div id="containerRead" class="dlshouwen-grid-container" onselectstart="return false;" style="-moz-user-select: none;"></div> <div id="toolBarRead" class="dlshouwen-grid-toolbar-container"></div>');
		getWriting();
		break;
	}
	case "four": {
		content
		.html('<div id="containerWord" class="dlshouwen-grid-container" onselectstart="return false;" style="-moz-user-select: none;"></div> <div id="toolBarWord" class="dlshouwen-grid-toolbar-container"></div>');
		getWord();
		break;
	}
	default: {
		
	}
	}

}

function getWord() {
	var gridColumns = [ {
		id : 'word',
		title : '单词',
		type : 'string',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true,
		/*hideType : 'md|sm|xs|lg'*/
	}, {
		id : 'explanation',
		title : '释义',
		type : 'string',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true
	}, {
		id : 'audio',
		title : '附件',
		type : 'string',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true
	}, {
		id : 'createTime',
		title : '创建时间',
		type : 'date',
		columnClass : 'text-center',
		fastQuery : true
	}, {
		type : "controll",
		columnClass : 'text-center',
		edit_btn : true,
		del_btn : true
	} ];
	var gridOption = {
		dataPath : "_embedded.words",
		ajaxLoad : true,
		exportFileName : '用户列表',
		loadURL : '/data/words',
		columns : gridColumns,
		gridContainer : 'containerWord',
		toolbarContainer : 'toolBarWord',
		pageSize : 10,
		pageSizeLimit : [ 10, 15, 20, 50, 100 ],
		lang : 'zh-cn',
		tools : 'refresh|fastQuery|newData|export[excel]|importExcel',
		pagerDetailPath : {
			pagerRootPath : "page",
			page : "number",
			size : "size",
			totalElements : "totalElements",
			totalPages : "totalPages",
			firstPage : 0
		},
		exportURL : '/export',
		importURL : '/file/upload',
		importAction : "word",
		fastQueryURL : "/users/search/default",
	    //导出的文件类型
	    exportType:"excel"
	
	};
	var grid = $.fn.dlshouwen.grid.init(gridOption);
	grid.load();
};

function getListen() {
	var gridColumns = [ {
		id : 'content',
		title : '听力内容',
		type : 'string',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true,
		/*hideType : 'md|sm|xs|lg'*/
	}, {
		id : 'audio',
		title : '听力附件',
		type : 'string',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true
	}, {
		id : 'core',
		title : '听力分数',
		type : 'number',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true
	}, {
		id : 'pushTime',
		title : '推送时间',
		type : 'date',
		columnClass : 'text-center',
		fastQuery : true,
		resolution : function(value, record, column, grid, dataNo, columnNo) {

			if (value) {
				return value;
			}
			return '未推送';
		}
	}, {
		id : 'createTime',
		title : '创建时间',
		type : 'date',
		columnClass : 'text-center',
		fastQuery : true
	}, {
		type : "controll",
		columnClass : 'text-center',
		edit_btn : true,
		del_btn : true
	} ];
	var gridOption = {
		dataPath : "_embedded.listens",
		ajaxLoad : true,
		exportFileName : '用户列表',
		loadURL : '/data/listens',
		columns : gridColumns,
		gridContainer : 'container',
		toolbarContainer : 'toolBar',
		pageSize : 10,
		pageSizeLimit : [ 10, 15, 20, 50, 100 ],
		lang : 'zh-cn',
		tools : 'refresh|fastQuery|newData|export[excel]|importExcel',
		pagerDetailPath : {
			pagerRootPath : "page",
			page : "number",
			size : "size",
			totalElements : "totalElements",
			totalPages : "totalPages",
			firstPage : 0
		},
		exportURL : '/export',
		importURL : '/file/upload',
		importAction : "listen",
		fastQueryURL : "/users/search/default",
	    //导出的文件类型
	    exportType:"excel"
	
	};
	var grid = $.fn.dlshouwen.grid.init(gridOption);
	grid.load();
};

function getRead() {
	var gridColumns = [ {
		id : 'content',
		title : '阅读内容',
		type : 'string',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true,
		hideType : 'md|sm|xs|lg'
	}, {
		id : 'core',
		title : '阅读分数',
		type : 'number',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true
	}, {
		id : 'pushTime',
		title : '推送时间',
		type : 'date',
		columnClass : 'text-center',
		fastQuery : true,
		resolution : function(value, record, column, grid, dataNo, columnNo) {

			if (value) {
				return value;
			}
			return '未推送';
		}
	}, {
		id : 'createTime',
		title : '创建时间',
		type : 'date',
		columnClass : 'text-center',
		fastQuery : true
	}, {
		type : "controll",
		columnClass : 'text-center',
		edit_btn : true,
		del_btn : true
	} ];
	var gridOption = {
		dataPath : "_embedded.reads",
		ajaxLoad : true,
		exportFileName : '用户列表',
		loadURL : '/data/reads',
		columns : gridColumns,
		gridContainer : 'containerRead',
		toolbarContainer : 'toolBarRead',
		pageSize : 10,
		pageSizeLimit : [ 10, 15, 20, 50, 100 ],
		lang : 'zh-cn',
		tools : 'refresh|fastQuery|newData|export[excel,csv,pdf,txt]|importExcel',
		pagerDetailPath : {
			pagerRootPath : "page",
			page : "number",
			size : "size",
			totalElements : "totalElements",
			totalPages : "totalPages",
			firstPage : 0
		},
		exportURL : '/export',
		importURL : '/file/upload',
		importAction : "none",
		fastQueryURL : "/users/search/default"
	};
	var grid = $.fn.dlshouwen.grid.init(gridOption);
	grid.load();

}

function getWriting() {
	var gridColumns = [ {
		id : 'content',
		title : '写作内容',
		type : 'string',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true,
		hideType : 'md|sm|xs|lg'
	}, {
		id : 'core',
		title : '写作分数',
		type : 'number',
		columnClass : 'text-center',
		fastQuery : true,
		newData : true
	}, {
		id : 'pushTime',
		title : '推送时间',
		type : 'date',
		columnClass : 'text-center',
		fastQuery : true,
		resolution : function(value, record, column, grid, dataNo, columnNo) {

			if (value) {
				return value;
			}
			return '未推送';
		}
	}, {
		id : 'createTime',
		title : '创建时间',
		type : 'date',
		columnClass : 'text-center',
		fastQuery : true
	}, {
		type : "controll",
		columnClass : 'text-center',
		edit_btn : true,
		del_btn : true
	} ];
	var gridOption = {
		dataPath : "_embedded.writings",
		ajaxLoad : true,
		exportFileName : '用户列表',
		loadURL : '/data/writings',
		columns : gridColumns,
		gridContainer : 'containerWriting',
		toolbarContainer : 'toolBarWriting',
		pageSize : 10,
		pageSizeLimit : [ 10, 15, 20, 50, 100 ],
		lang : 'zh-cn',
		tools : 'refresh|fastQuery|newData|export[excel,csv,pdf,txt]|importExcel',
		pagerDetailPath : {
			pagerRootPath : "page",
			page : "number",
			size : "size",
			totalElements : "totalElements",
			totalPages : "totalPages",
			firstPage : 0
		},
		exportURL : '/export',
		importURL : '/file/upload',
		importAction : "none",
		fastQueryURL : "/users/search/default"
	};
	var grid = $.fn.dlshouwen.grid.init(gridOption);
	grid.load();
}
$(document).ajaxSend(function() {
	H5_loading.show({scale:0.8});
})

$(document).ajaxComplete(function() {
	H5_loading.hide();
})

$(document).ready(function() {
	 
	layer.msg('hello'); 	  
});