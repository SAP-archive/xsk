var stringUtil = {};
stringUtil.endsWith = function(str, suffix)
{
	return str.indexOf(suffix, str.length - suffix.length) !== -1;
};


stringUtil.hashCode = function(str) {
	var hash = 0;
    
    if(str.length === 0) return hash;
    
    for(let i = 0; i < str.length; i++) {
        let chr = str.charCodeAt(i);
        hash = ((hash << 5) - hash) + chr;
        hash |= 0;
    }    
    
    return hash;
    
};

module.exports = stringUtil;