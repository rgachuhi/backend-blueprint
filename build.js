#!/usr/bin/jjs -fv
var cmd = "docker build -t oli/authorization ."
$EXEC(cmd);
print($OUT);
if ($ERR) print($ERR);
