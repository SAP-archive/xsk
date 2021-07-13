// --------------------------------------------------------------------------
//
// Filter certain Packages (Eclipse)
//
// --------------------------------------------------------------------------

var log = require('./log-util');

var GENERIC_FILTER_PACKAGES = [
                    ".externalToolBuilders",
                    ".settings"];

var QUOTE_PATTERN = /\"([^\"]*)\"/;

function PackageFilter() {

    this.splitName = function(name) {
        var n = name;
        var paths = [];
        while(n.length > 0 && (m = n.match(QUOTE_PATTERN)) != null) {
            var pre = n.substring(0, m.index);
            if (pre[pre.length-1] === '.') {
                pre = pre.substring(0, pre.length-1);
            }
            if (pre.length > 0) {
                paths = paths.concat(pre.split("."));
            }
            paths.push(m[1]);
            n = n.substring(m.index + m[1].length+2);
            if (n.length > 0 && n[0] === '.') {
                n = n.substring(1);
            }
        }
        if (n.length >0) {
            paths.concat(n.split("."));
        }
        return paths;
    }

    this.filterGenericPackages = function(packageList) {
        var that = this;
        var filteredPackages = [];
        packageList.forEach(function (pkg) {
            var name = pkg.packageName;
            var paths = that.splitName(name);
            var filtered = false;
            for (var i=0; i < GENERIC_FILTER_PACKAGES.length; i++) {
                if (paths[paths.length-1] === GENERIC_FILTER_PACKAGES[i]) {
                    log.trace("Filtered package " + name);
                    filtered = true;
                    break;
                }
            }
            if (!filtered) {
                filteredPackages.push(pkg);
            }
        });
        return filteredPackages;
    }

    this.filterPackages = function(context, opackageList) {

        var packageList = this.filterGenericPackages(opackageList);
        var newPackageList = [];
        if ("exclude-packages" in context) {
            var exclude = context["exclude-packages"];
            packageList.forEach(function (candPackage) {
                var filtered = false;
                exclude.forEach(function (excludePackage) {
                    // log.error("exclude is " + excludePackage.name)
                    if (excludePackage.subpackages) {
                        if (!(candPackage.packageName.startsWith(excludePackage.package))) {                            
                        } else {
                            log.debug("Filtered package " + candPackage.packageName);
                            filtered = true;
                        }
                    } else {
                        if (candPackage.packageName !== excludePackage.package) {
                        } else {
                            log.debug("Filtered package " + candPackage.packageName);
                            filtered = true;
                        }
                    }
                });
                if(!filtered) {
                    newPackageList.push(candPackage);
                }
            });
        } else {
            newPackageList = packageList;
        }
        log.trace("filtered " + (opackageList.length - newPackageList.length) + " packages.");
        return newPackageList;
    }
}

module.exports = new PackageFilter();
