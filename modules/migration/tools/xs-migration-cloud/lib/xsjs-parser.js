var esprima = require('esprima');

function codeScanner(){
	
	var that = this;
	
	this.prepareCheck = function(checkStr){
		var id_obj_full = esprima.parse(checkStr, {});
		var id_obj = id_obj_full.body[0];
		id_obj.type = "$any$";
		id_obj["object_expression_callee_left_right_value_init"] = id_obj.expression;
		id_obj.expression = null;
		delete id_obj.expression;
		return id_obj;
	};
	this.findObject = function(codeTree, id_objs){
		if(codeTree == null){
			return {match: [],search: []};
		}
		var matchingArrays = [];
		var lineSearcherArray = [];
		// check self
		for(var i = 0 ; i< id_objs.length; i++){
			var id_obj = id_objs[i];
			if(codeTree.type && (codeTree.type == id_obj.type || id_obj.type == "$any$")){
				var match = that.compareObj(codeTree, id_obj);
                /*if(codeTree.loc.start.line == 10){
                    console.log(JSON.stringify(codeTree, null, 4));
                }*/
				if(match == true && codeTree.loc){
					matchingArrays.push({found: id_obj, foundIndex: i, pos:codeTree.loc});
				}
				else if(match == true && !codeTree.loc){
					lineSearcherArray.push({found: id_obj, foundIndex: i});
				}
			}
		}
		// check properties
		for(var prop in codeTree){
			if(typeof codeTree[prop] === 'object'){
				var ret = that.findObject(codeTree[prop], id_objs);
				matchingArrays = matchingArrays.concat(ret.match);
				lineSearcherArray = lineSearcherArray.concat(ret.search);
			}
			else if(Object.prototype.toString.call(codeTree[prop]) === '[object Array]'){
				for(var pIndex = 0; pIndex < codeTree[prop].length; pIndex++){
					var ret = that.findObject(codeTree[prop][pIndex], id_objs);
					matchingArrays = matchingArrays.concat(ret.match);
					lineSearcherArray = lineSearcherArray.concat(ret.search);
				}
			}
			else{
				// TODO
				}
		}
		if(codeTree.loc){
			for(var i = 0; i < lineSearcherArray.length; i++){
				lineSearcherArray[i].pos = codeTree.loc;
				matchingArrays.push(lineSearcherArray[i]);
			}
			lineSearcherArray = [];
		}
		return {
			match: matchingArrays,
			search: lineSearcherArray,
		}
	};
	this.compareObj = function(obj_in_tree, id_obj){
		if(Object.prototype.toString.call(id_obj) === '[object Array]'  || typeof id_obj === 'object'){
			if(Object.prototype.toString.call(id_obj) === '[object Array]'){
				for(var i = 0; i < id_obj.length; i++){
					if(!that.compareObj(obj_in_tree[i], id_obj[i])){
                        return false;
                    }
				}
			}
			else{
				for(var prop in id_obj){
					var oneFound = false;
                    var leftCase = true;
					var newprop = null;
					var checkArray = prop.split("_");
					if(checkArray.length > 1){
						for(var i = 0; i < checkArray.length; i++){
							if(obj_in_tree[checkArray[i]] != null && obj_in_tree[checkArray[i]] != undefined){
								oneFound = true;
								newprop = checkArray[i];
							}
						}
						if(oneFound){
                            if(newprop == "left" || newprop == "right"){
                                return (that.compareObj(obj_in_tree["left"], id_obj[prop]) || that.compareObj(obj_in_tree["right"], id_obj[prop]));
                            }
                            else{
                                return that.compareObj(obj_in_tree[newprop], id_obj[prop]);
                            }
                            
						}
						else{
							return false;
						}
					}
					else if(obj_in_tree[prop] == null || obj_in_tree[prop] == undefined){
						return false;
					}
					else{
						if(!that.compareObj(obj_in_tree[prop], id_obj[prop])){
							return false;
						}
					}
				}
			
			}
			}
		else if(id_obj === obj_in_tree || id_obj == "$any$"){
			return true;
		}
		else{
			return false
		}
		return true;
	};
}

module.exports = new codeScanner();
