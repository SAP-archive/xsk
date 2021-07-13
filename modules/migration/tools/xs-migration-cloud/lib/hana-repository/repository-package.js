/**
 * Created by SAP SE on 17.08.16.
 */

class RepositoryPackage{
    
    constructor(pkg){
        this._pkg = pkg;
    }
    
    get packageName() {
        return this._pkg.package;
    }
    
    get packageFile() {
        var path = this.packageName.replace(/\./g, '/');

        return '/' + path;
    }
    
    
}

module.exports = RepositoryPackage;