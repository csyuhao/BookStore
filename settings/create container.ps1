docker pull mysql:5.7.28;
docker run --name db1 -p 3307:3306 -v /c/users/csyuh/database/db1:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=yuhao123 -d mysql:5.7.28;
docker run --name db2 -p 3308:3306 -v /c/users/csyuh/database/db2:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=yuhao123 -d mysql:5.7.28;
docker run --name db3 -p 3309:3306 -v /c/users/csyuh/database/db3:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=yuhao123 -d mysql:5.7.28;


