1. tar
tar -cf out.tar file1 file2...
文件名必须紧跟在f后面，即-cf不是-fc
tar -rvf o.tar new_file # r已生成的tar中加文件，v或vv是现实详细信息

tar -xf a.tar #把归档内容提取到当前目录，-x是提取
tar -xvf file.tar file1 file3 #只提取file1 file3

tar -Af t1.tar t2.tar #把t2合并到t1中

tar -f a.tar --delete file1 file3 #把file1 file3从a中删除
tar -cf t.tar --exclude "*.txt" #不打包txt文件

tar主要记住核心，-x，-c，-f三个

-z gzip压缩
-j bunzip2压缩
可以不明确给出压缩格式，用-a表示自动选择压缩


2. gunzip gzip
gzip filename #压缩成filename.gz
gunzip filename.gz #解压缩，删除filename.gz并声称filename.gz未压缩形式

tar -czvvf tz.tar.gz [file] #用gzip压缩，-c创建tar，vv详细，f紧跟文件名
tar -xavvf tz.tar.gz -c extract-dir #x 解包，a是采取自动识别，也可用-z即gzip解压，一般用a


3. bunzip bzip
与gzip类似

bzip2 filename
bunzip2 filename

tar -xavvf ...


4. zip
类似
zip file.zip file
unzip file.zip


5. ssh
ssh自动化认证
1. 创建ssh密钥
2. 将生成的公钥传输到远程主机，加入到文件~/.ssh/authorized_keys中
$ssh-keygen -t rsa
输入密码生成密钥，~/.ssh/id_rsa.pub和~/.ssh/id_rsa已经生成，公钥添加到远程服务器的~/.ssh/authorized_keys中


6. 网络流量与端口
lsof -i #列出系统中开放的端口和服务的详细信息
netstat -tnp #列出开放的端口与服务


7. df du
du file1 file2 #找出文件占用的磁盘空间
du -a dir #文件夹

du默认字节， -h会更友好，KB，MB，GB

du -c file1 file2或dir #所有文件总共占有空间大小

-c -a -h可合并使用，显示友好而且在最后一行显示总共多大

du还支持--max-depth --exclude -type等

df现实磁盘空间信息
df -h #常用


8. time COMMAND
time ls#计算ls耗费的时间


9. who
who会打印出当前登录用户的相关信息，伪终端，登录IP
who
root     pts/2        2016-06-27 22:36 (pek2-office-04-dhcp221.eng.vmware.com)

users会打印登录主机的用户列表
#users
root

uptime #打印系统已运行了多久等信息
uptime
07:39:24 up 6 days,  3:18,  1 user,  load average: 0.00, 0.01, 0.05

last提供登录信息
last root                                                                                                                                                                                          
root     pts/2        pek2-office-04-d Mon Jun 27 22:36   still logged in   
root     pts/2        pek2-office-04-d Wed Jun 22 04:25 - 15:15 (5+10:50)   
root     pts/1        pek2-office-04-d Wed Jun 22 04:24 - 15:15 (5+10:51)   


10. history
#!/bin/bash
#top command，~/.bash_history记录

printf "COMMAND\tCOUNT\n";

cat ~/.bash_history | awk '{ list[$1]++; }' \ #默认分隔符，$1,第一个区间
END{
	for(i in list) {
	printf("%s\t%d\n",i,list[i]);
	}
}' | sort -nrk 2 | head # sort -n file.txt #  -k指定了排序按照哪一键来进行  -nr表明按照数字，采用逆序形式排


11. 列出1小时内占用CPU最多的10个程序
#用ps命令
ps -eo comm,pcpu #以command cpu显示
结果
updatedb.mlocat  2.2
vdnet            0.2
perl            60.0
用关联数组
#!/bin/bash
ps -eo comm,pcpu | tail -n +2 >> /tmp/cpu.usage #tail -n +2将ps头部去除，+2从第2行开始显示文件

cat /tmp/cpu.usage | \
awk '{ process[$1]=$2; }
END{
	for(i in process){
		printf("%-20s %s",i,process[i]);
	}
}' | sort -nrk 2 | head #-k指定了排序按照哪一键来进行  -nr表明按照数字，采用逆序形式排


12. watch
watch COMMAND#监视命令输出内容，默认两秒更新
watch 'ls -l' 


13. 文件访问记录
inotifywait收集文件访问信息，类似watch


14. logrotate（了解）
把日志文件的大小限制在给定size，自动新建文件
/etc/logroate.d配置文件控制命令的配置


15. syslog（了解）
/var/log/保存日志文件，syslog记录应用进程日志


16. cron
一个crontab条目
第1列表示分钟1～59 每分钟用*或者 */1表示 
第2列表示小时1～23（0表示0点） 
第3列表示日期1～31 
第4列表示月份1～12 
第5列标识号星期0～6（0表示星期天） 
第6列要运行的命令 
分钟 小时 天 月 工作日 命令
02 * * * * /home/sylynux/test.c #每天个小时的第2分钟执行
00 5，6，7 * * /home/sylynux/test.c #每天5、6、7小时执行
00 */12 * * 0 t.sh #周日每隔12个小时执行


17. 进程
top ps pgrep
ps -o 指定显示的列比如ps -eo comm,pcpua
ps 打印当前终端的进程
ps -ax 全部进程
ps -eo comm,pcpu --sort -pcpu | head #注意和sort搭配
ps -C command #打印出具体进程command的信息
ps -C COMMAND_NAME -o pid

top 显示的信息多一些，有mem等

pgrep可以获取一个特定命令的进程ID列表
pgrep md              #找到所有md相关的进程号                                                                                                                                                                              
36
42
1337

pgrep -u root #打印root的所有进程

ps -eLf # 显示进程和线程，L是显示线程
UID        PID  PPID   LWP  C NLWP STIME TTY          TIME CMD
root         1     0     1  0    1 Jun22 ?        00:00:04 /sbin/init
root         2     0     2  0    1 Jun22 ?        00:00:00 [kthreadd]
#NLWP进程的线程数量，NLP表示ps输出中每个条目的线程ID
ps -eLF --sort -nlwp #注意与sort的结合

打印进程的环境变量，e选项
ps -eo pid,cmd e | tail -n 3


18. kill
kill -l#列出所有可发送的信号
kill processID #kill进程
kill -s SIGNAL PID #发送指定信号

kill -9 PID#强制杀死进程，-9（SIGKILL）


19. which whereis file whatis
which 显示命令的位置
$which ls
/bin/ls

whereis 与which类似，不过更详细，显示命令手册，源代码等
whereis ls
ls: /bin/ls /usr/share/man/man1/ls.1.gz

file filename#显示文件详细信息
file /bin/ls
/bin/ls: ELF 32-bit LSB executable, Intel 80386, version 1 (SYSV), dynamically linked (uses shared libs), for GNU/Linux 2.6.24, BuildID[sha1]=0x5f580e4b387193789cb865afdebb75442e1d5516, stripped

whatis#简介命令
whatis ls
ls (1)               - list directory contents

平均负载，系统中可运行进程总量的平均值，第一个之是一分钟内平均值，第二个五分钟内平均值，第三个15分钟内平均值
uptime
08:32:32 up 6 days,  4:12,  1 user,  load average: 0.02, 0.03, 0.05


20. 系统
hostname #主机名
uname -n #内核版本，硬件架构
uname -a#内核发行版本
uname -m #主机类型

cat /proc/cpuinfo #cup信息
cat /proc/meminfo #内存信息
cat /proc/partitions #硬盘分区


21. /proc
cat /proc/4295/environ #environ 环境变量
readlink /proc/4295/exe #cwd进程工作目录的链接
fd文件描述符


22. MySQL(用-u -p)
mysql -uuser -ppasswd -e"insert LogTable values(...)"  
shell 连mysql
准备一个sql脚本，如update.sql，然后执行如下命令：
mysql -uroot -ppassword < update.sql  