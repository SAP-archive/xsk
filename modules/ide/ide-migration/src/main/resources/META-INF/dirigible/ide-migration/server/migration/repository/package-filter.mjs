export class PackageFilter {
    QUOTE_PATTERN = /\"([^\"]*)\"/;
    GENERIC_FILTER_PACKAGES = [".externalToolBuilders", ".settings"];

    splitName(name){
        let n = name;
        let m;
        let paths = [];
        while (n.length > 0 && (m = n.match(this.QUOTE_PATTERN)) != null) {
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

    filterGenericPackages(packageList) {
        let filteredPackages = [];
        packageList.forEach((pkg) => {
            let name = pkg.packageName;
            let paths = this.splitName(name);
            let filtered = false;
            for (let i = 0; i < this.GENERIC_FILTER_PACKAGES.length; i++) {
                if (paths[paths.length - 1] === this.GENERIC_FILTER_PACKAGES[i]) {
                    console.trace("Filtered package " + name);
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

    filterPackages(context, opackageList) {
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
                            console.debug("Filtered package " + candPackage.packageName);
                            filtered = true;
                        }
                    } else {
                        if (candPackage.packageName !== excludePackage.package) {
                        } else {
                            console.debug("Filtered package " + candPackage.packageName);
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
        console.trace("filtered " + (opackageList.length - newPackageList.length) + " packages.");
        return newPackageList;
    };
}
