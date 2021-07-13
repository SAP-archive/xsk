var pkg = require('./package.json');
var path = require('path');
var fs = require('fs');
var UglifyJS = require('uglify-js');

var calcMigrationVersion = pkg['calc-migration'];
var version = pkg.version;

var assembly_base = ['package.json', 'config/**', 'xs-migration.js', 'lib/**', 'templates/**', 'init/*', '.env-template'];
var assembly = {
		ntamd64 : assembly_base.slice(),
		linuxx86_64 : assembly_base.slice(),
};
assembly.ntamd64.push('xs-migration.bat');
assembly.linuxx86_64.push('xs-migration');

module.exports = function(grunt) {
	grunt.registerTask('download-manual-deps', ['curl:lib/plugin_handlers/calcview/calc_migration.jar']);
	grunt.registerTask('move-manual-deps', ['move:calc_migration']);
	grunt.registerTask('package-linuxx86_64', [
                                               'unzip:jre_linux',
	                                           'move:jre_linux',
	                                           'computeProdDeps',
	                                           'copy:linuxx86_64',
						                       'cleanLinuxPackages',
	                                           'move:node_linux',
	                                           'compress:linuxx86_64' ]);
	grunt.registerTask('package-ntamd64', [
                                               'unzip:jre_windows',
	                                           'move:jre_windows',
	                                           'computeProdDeps',
	                                           'copy:ntamd64',
						                       'cleanWindowsPackages',
	                                           'move:node_windows',
		                                       'compress:ntamd64' ]);

	grunt.registerTask('updateReportTemplate', ['combineReportJSFiles', 'copy:customer_report']);
	
	grunt.initConfig({
		curl : {
			'lib/plugin_handlers/calcview/calc_migration.jar' : 'http://nexus.wdf.sap.corp:8081/nexus/service/local/artifact/maven/redirect?r=build.milestones.xmake&g=com.sap.modeler&a=hdimigration&c=standalone&e=jar&v=' + calcMigrationVersion
		},
		copy : {
			ntamd64 : {
				options : {
					mode : true,
				},
				dot : true,
				src : assembly.ntamd64,
				dest : 'dist/ntamd64/xs-migration/',
			},
			linuxx86_64 : {
				options : {
					mode : true,
				},
				dot : true,
				src : assembly.linuxx86_64,
				dest : 'dist/linuxx86_64/xs-migration/',
			},
            customer_report: {
			    options: {
			        mode: true
                },
                dot: true,
                src: ['customer_report/report/icons/**', 'customer_report/report/lib/**', 'customer_report/report/theme/**', 'customer_report/report.html', 'customer_report/report/content.html', 'customer_report/report/main.js'],
                dest: 'templates/'
            },
            
		},
        move: {
            calc_migration: {
                src: 'import/content/hdimigration-*-standalone.jar',
                dest: 'lib/plugin_handlers/calcview/calc_migration.jar'
            },
            jre_windows: {
                src: 'import/content/ntamd64/jre/sapjvm_8_jre',
                dest: 'dist/ntamd64/xs-migration/'
            },
            jre_linux: {
                src: 'import/content/linuxx86_64/jre/sapjvm_8_jre',
                dest: 'dist/linuxx86_64/xs-migration/'
            },
            node_windows: {
                src: 'import/content/ntamd64/node/nodejs_ntamd64*.zip',
                dest: 'dist/ntamd64/xs-migration/init/java.dat'
            },
            node_linux: {
                src: 'import/content/linuxx86_64/node/nodejs_linuxx86_64*.zip',
                dest: 'dist/linuxx86_64/xs-migration/init/java.dat'
            }
        },
        unzip: {
            jre_windows: {
                src: 'import/content/ntamd64/jre/*.zip',
                dest: 'import/content/ntamd64/jre'
            },
            jre_linux: {
                src: 'import/content/linuxx86_64/jre/*.zip',
                dest: 'import/content/linuxx86_64/jre'
            }
        },
		compress : {
			ntamd64 : {
				options : {
					archive : 'dist/xs-migration.ntamd64-' + version + '.zip'
				},
				files : [ {
					expand : true, cwd : 'dist/ntamd64/xs-migration', src : [ '**' ], dest : 'xs-migration/', dot : true
				} ]
			},
			linuxx86_64 : {
				options : {
					archive : 'dist/xs-migration.linuxx86_64-' + version + '.zip'
				},
				files : [ {
					expand : true, cwd : 'dist/linuxx86_64/xs-migration', src : [ '**' ], dest : 'xs-migration/', dot : true
				} ]
			},
		}
	});

	grunt.loadNpmTasks('grunt-curl');
	grunt.loadNpmTasks('grunt-zip');
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-compress');
    grunt.loadNpmTasks('grunt-move');
	
	grunt.registerTask('computeProdDeps', function() {
		npm = '"' + path.join(path.dirname(process.execPath), 'npm') + '"';
		var done = this.async();
		require('child_process').exec(npm + ' ls --prod=true --parseable=true', function(err, stdout, stderr) {
			if(err) {
				done(err);
			}
			
			var dirs = stdout.split('\n');
			console.log(dirs[0]);
			if(dirs[0].endsWith('xs-migration')) {
				dirs = dirs.slice(1);
			}
			if(dirs[dirs.length - 1].length === 0) {
				dirs = dirs.slice(0, dirs.length - 1);
			}
			
			for (i = 0; i < dirs.length; i++) {
				if(path.relative(process.cwd(), dirs[i]).trim().length > 0) {
					assembly.ntamd64.push(path.relative(process.cwd(), dirs[i]) + '/**');
					assembly.linuxx86_64.push(path.relative(process.cwd(), dirs[i]) + '/**');
				}
			}
			
			done();
		});
	});
	
	

	grunt.registerTask('cleanLinuxPackages', function () {
		var done = this.async();
		console.log('cleaning packages');

		var linuxCwd = path.join('.', 'dist/linuxx86_64/xs-migration/');
		var cleanBin = path.join('..', '..', '..', 'node_modules', 'filter-node-package', 'lib', 'clean-packages.js');

		require('child_process').exec(cleanBin + ' -w', {
			cwd: linuxCwd
		}, function(err, stdout, stderr) {
			console.log(stderr);
			console.log(stdout);
			if(err){
				console.log(err);
				return done(err);
			}
			console.log('cleaning packages done');
			done();
		});

	});

	grunt.registerTask('cleanWindowsPackages', function () {
                var done = this.async();
                console.log('cleaning packages');

                var linuxCwd = path.join('.', 'dist/ntamd64/xs-migration/');
                var cleanBin = path.join('..', '..', '..', 'node_modules', 'filter-node-package', 'lib', 'clean-packages.js');

                require('child_process').exec(cleanBin + ' -w', {
                        cwd: linuxCwd
                }, function(err, stdout, stderr) {
                        console.log(stderr);
                        console.log(stdout);
                        if(err){
                                console.log(err);
                                return done(err);
                        }
                        console.log('cleaning packages done');
                        done();
                });

        });
	
	
	grunt.registerTask('combineReportJSFiles', function () {
       
	    var jsFiles = [
	        path.join('control', 'clib.js'),
            path.join('control', 'utils.js'),
            path.join('view', 'detailView.js'),
            path.join('view', 'listView.js'),
            path.join('view', 'mainView.js'),
            path.join('view', 'statisticsView.js'),
            'appmain.js'
        ];
	    
	    var complete = '';
	    
	    for(let jsFile of jsFiles) {
	        complete += fs.readFileSync(path.join('customer_report', 'report', jsFile), 'utf-8');
        }
	    
        var minfied = UglifyJS.minify(complete);
        
        fs.writeFileSync(path.join('customer_report', 'report', 'main.js'), minfied.code);
	    
	    	    
    });


};
