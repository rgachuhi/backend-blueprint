#!/usr/bin/jjs -fv
var mvn_clean_cmd = "mvn clean"
var mvn_package_cmd = "mvn package"
var cmd = "docker build -t oli/authorization ."
$EXEC(mvn_clean_cmd);
print($OUT);
if ($ERR) print($ERR);
$EXEC(mvn_package_cmd);
print($OUT);
if ($ERR) print($ERR);
$EXEC(cmd);
print($OUT);
if ($ERR) print($ERR);
