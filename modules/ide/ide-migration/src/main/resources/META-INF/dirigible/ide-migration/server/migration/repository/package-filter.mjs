let log = console;

let GENERIC_FILTER_PACKAGES = [".externalToolBuilders", ".settings"];

let QUOTE_PATTERN = /\"([^\"]*)\"/;

export function PackageFilter() {
    this.splitName = function (name) {
        let n = name;
        let paths = [];
        while (n.length > 0 && (m = n.match(QUOTE_PATTERN)) != null) {
            let pre = n.substring(0, m.index);
            if (pre[pre.length - 1] === ".") {
                pre = pre.substring(0, pre.length - 1);
            }
            if (pre.length > 0) {
                paths = paths.concat(pre.split("."));
            }
            paths.push(m[1]);
            n = n.substring(m.index + m[1].length + 2);
            if (n.length > 0 && n[0] === ".") {
                n = n.substring(1);
            }
        }
        if (n.length > 0) {
            paths.concat(n.split("."));
        }
        return paths;
    };

    this.filterGenericPackages = function (packageList) {
        let that = this;
        let filteredPackages = [];
        packageList.forEach(function (pkg) {
            let name = pkg.packageName;
            let paths = that.splitName(name);
            let filtered = false;
            for (let i = 0; i < GENERIC_FILTER_PACKAGES.length; i++) {
                if (paths[paths.length - 1] === GENERIC_FILTER_PACKAGES[i]) {
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
    };

    this.filterPackages = function (context, opackageList) {
        let packageList = this.filterGenericPackages(opackageList);
        let newPackageList = [];
        if ("exclude-packages" in context) {
            let exclude = context["exclude-packages"];
            packageList.forEach(function (candPackage) {
                let filtered = false;
                exclude.forEach(function (excludePackage) {
                    if (excludePackage.subpackages) {
                        if (!candPackage.packageName.startsWith(excludePackage.package)) {
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
                if (!filtered) {
                    newPackageList.push(candPackage);
                }
            });
        } else {
            newPackageList = packageList;
        }
        log.trace("filtered " + (opackageList.length - newPackageList.length) + " packages.");
        return newPackageList;
    };
}
