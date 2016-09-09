1. 交集
comm命令
comm t1 t2#两文件的交集

2. mkdir -p
该命令会自动创建不存在的路径
比如mkdir -p /home/ab/cd/e #ab cd 都会自动创建，不会提示错误


3. 文件权限
-rwxr-xr-x
第一个文件类型
第二组用户权限
第三组用户组
最后一个其他用户

chmod命令

粘滞位(+t)是应用于目录的权限类型，通过粘滞位，只有目录的所有者才能删除目录中的文件，即使用户组
和其他用户有权限也不能删除
chmod a+t dirname


4. 创建不可修改文件
chattr将文件设置为不可修改，即使root也不能修改
/etc/shadow文件就是这样的，该文件由当前系统中所有用户的密码组成，用passwd修改密码就是修改的这个文件
chattr +i file
或sudo chattr +i file
chattr -i file#去除该权限


5. touch建空白文件


6. 链接
ln -s target symblinkname

ln -l -s /var/ w#生成w为链接


7. 列举文件类型信息
#!/bin/bash
if [ $# -ne 1 ]; #参数个数
then
	echo $0 basepath;
	echo
fi
path=$1 #第一个参数

declare -A statarray; #声明关联数组

#while读入的写法，注意反引用的写法，注意关联数组，注意let
while read line;
do
	ftype = `file -b "$line"` #file命令用于查看文件具体信息
	let statarray["$ftype"]++;
	
done<<(find $path -type f -print)

#注意关联数组的写法，尤其是!，@用得多，因为返回列表
for ftype in "${!statarry[@]}";
do
	echo $ftype : ${statarray["$ftype"]}
done

$./filestat.sh /home/tmp
Bourne-Again shell script
ASCII text executable


8. 环回（loopback）文件与挂载
loopback文件是在文件中而非物理设备中创建的文件系统，可以把这些文件挂载到挂载点上，就像设备一样
步骤：
dd if=/dev/zero of=loopbackfile.img bs=1G count=1#创建1G大小空文件
mkfs.ext4 loopbackfile.img#按照ext4格式化
mkdir /mnt/loopback#挂载点
mount -o loop loopbackfile.img /mnt/loopback #-o loop挂载回环文件系统

卸载用 umount

iso可以回环挂载
mkdir /mnt/iso
mount -o loop linux.iso /mnt/iso #iso是一个只读文件系统

sync #强制写入磁盘，root可执行，因为对挂载设备操作不会立即写入磁盘


9. iso
创建iso文件
dd if=/dev/cdrom of=image.iso
mkisofs -V "Label1" -o  image.iso source_dir/ #-o iso文件路径，sourcedir是iso文件内容目录

isohybrid image.iso #生成类似系统盘
dd if=image.iso of=/dev/sdb1 #写入u盘


10. diff
diff file1 file2
-u选项用于一体化输出，容易读


11. head tail
cat a | head
head -n 4 file

cat a | tail
tail -n a

tail -f a #监视动态增长的文件


12. 统计
wc命令
#行数
wc -l file
cat file | wc -l

#单词数
wc -w file

#字符数
wc -c file


13. tree
tree ~/ #打印图形化目录

