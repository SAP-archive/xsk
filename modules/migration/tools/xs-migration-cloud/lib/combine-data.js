var path = require('path');
var objectClone = require('../lib/object-clone');

var seperatorProp = "sep";

function accumulatefileList(list1, list2, prefix1, prefix2, number){
	if(!number)
		number = 0;
	if(!list1){
		return updateFileListURL(number, list2, prefix2);
	}
	if(!list2){
		return updateFileListURL(number, list1, prefix1);
	}
	var ret = updateFileListURL(number, list1, prefix1);
	ret = updateFileListURL(number, list2, prefix2, ret);
	return ret;
}

function accumulatefileListArray(list1, list2, prefix1, prefix2){
	if(!list1){
		return updateFileListArrayURL(list2, prefix2);
	}
	if(!list2){
		return updateFileListArrayURL(list1, prefix1);
	}
	var ret = updateFileListArrayURL(list1, prefix1);
	ret = updateFileListArrayURL(list2, prefix2, ret);
	return ret;
}

function updateFileListURL(number, list, prefix, existList){
	var cloneList = {};
	if(existList)
		cloneList = objectClone.cloneObject(existList);
	for(var prop in list){
		if(prop == seperatorProp){
			//nothing
			cloneList[prop] = objectClone.cloneObject(list[prop]);
		}
		else{
			var temp = objectClone.cloneObject(list[prop]);
			temp.fileIndex += number;
			cloneList[path.join((prefix?prefix:""), prop)] = temp;
		}
	}
	return cloneList;
}

function updateFileListArrayURL(list, prefix, existList){
	var cloneList = [];
	if(existList)
		cloneList = objectClone.cloneObject(existList);
	for(var i = 0; i < list.length; i++){
		var temp = objectClone.cloneObject(list[i]);
		temp.name = path.join((prefix?prefix:""), temp.name);
		cloneList.push(temp);
	}
	return cloneList;
}

function accumulateDatafile(data1, data2, prefix1, prefix2) {
	var ret = {};
	
	//sum part
	var clonedata1 = objectClone.cloneObject(data1);
	var clonedata2 = objectClone.cloneObject(data2);

	if(!data1){
		clonedata2.errors.list = [];
		for(let i2 in data2.errors.list){
			let err = objectClone.cloneObject(data2.errors.list[i2]);
			if(prefix2)
				err.projectURL = path.join(prefix2, err.file);
			clonedata2.errors.list = clonedata2.errors.list.concat(err);
		}
		return clonedata2;
	}
		
	if(!data2){
		clonedata1.errors.list = [];
		for(let i1 in data1.errors.list){
			let err = objectClone.cloneObject(data1.errors.list[i1]);
			if(prefix1)
				err.projectURL = path.join(prefix1, err.file);
			clonedata1.errors.list = clonedata1.errors.list.concat(err);
		}
		return clonedata1;
	}
		
	ret.sum = [];
	for(var c1_index in data1.sum){
		var d1_container_Info = data1.sum[c1_index];
		var found = false;
		for(let c2_index in data2.sum){
			let d2_container_Info = data2.sum[c2_index];
			if(d1_container_Info.name == d2_container_Info.name)
			{
				found = true;
				var detail = null;
				if(d1_container_Info.detail && d2_container_Info.detail)
				{
					detail = accumulateNumber(d1_container_Info.detail, d2_container_Info.detail);
				}
				let detailObj = {
						name:d1_container_Info.name,
						number: d1_container_Info.number + d2_container_Info.number,
						detail:detail,
					};
				if(!detail)
					delete detailObj.detail;
				ret.sum.push(detailObj);
				delete clonedata2.sum[c2_index];
				break;
			}
		}
	}
	for(let c2_index in clonedata2.sum){
		let d2_container_Info = clonedata2.sum[c2_index];
		var detailObj = {
				name:d2_container_Info.name,
				number: d2_container_Info.number,
				detail:d2_container_Info.detail,
			};
		if(!d2_container_Info.detail)
			delete detailObj.detail;
		ret.sum.push(detailObj);
	}
	//other parts
	ret.errors = {};
	ret.errors.list = [];
	ret.errors.number = (data1.errors.number? data1.errors.number : 0)+ (data2.errors.number ? data2.errors.number : 0) ;
	for(let i2 in data2.errors.list){
		let err = objectClone.cloneObject(data2.errors.list[i2]);
		if(prefix2 && "file" in err)
			err.projectURL = path.join(prefix2, err.file);
		ret.errors.list = ret.errors.list.concat(err)
	}
	for(let i1 in data1.errors.list){
		let err = objectClone.cloneObject(data1.errors.list[i1]);
		if(prefix1)
			err.projectURL = path.join(prefix1, err.file);
		ret.errors.list = ret.errors.list.concat(err)
	}
	return ret;
}

function accumulateNumber(Obj1, Obj2){
	var cloneobj2 = objectClone.cloneObject(Obj2);
	
	var ret = {};

	for(var prop1 in Obj1)
	{
		var found = false;
		var value1 = Obj1[prop1];
		for(var prop2 in Obj2){
			var value2 = Obj2[prop2];
			if(prop1 == prop2){
				found = true;
				ret[prop1] = null;
				if(isInt(value1) && isInt(value2)){
					ret[prop1] = value1 + value2;
				}
				else{
					
					//TODO
				}
				delete cloneobj2[prop2];
			}
		}
		if(found == false){
			ret[prop1] = value1;
		}
	}
	for(var prop2 in cloneobj2){
		ret[prop2] = cloneobj2[prop2];
	}
	return ret;
}

function isInt(value){
	return (typeof value) === 'number';
}

module.exports.accumulateDatafile = accumulateDatafile;
module.exports.accumulatefileList = accumulatefileList;
module.exports.accumulatefileListArray = accumulatefileListArray;