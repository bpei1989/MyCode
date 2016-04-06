1. 对象拷贝
#Python默认是引用，不是拷贝需要用copy来深拷贝
import copy
new_list = copy.copy(esxisting_list)


2. 列表推导
#列表的每个元素都加23
thenewlist = [x + 23 for x in theoldlist]

#列表中大于5的加23
thenewlist = [x + 23 for x in theoldlist if x > 5]


3. 若列表存在某元素则返回
#这种方式比L[i]快
def list_get(L, i, v=None):
	if -len(L) <= i <= len(L): return L(i) #有效索引在-len（L）到len(L)
	else: return v


4. 循环访问序列中的元素和索引
#用enumerate
for index, item in enumerate(list):
	print index, item
#for index in range(len(list))也可以，但enumerate更直观


5. 创建一个多维列表，且全为0
#用推导列表
mlist = [[0 for col in range(5)] for row in range(10)]


6. 展开一个嵌套序列
#list和tuple可展开
def list_or_tuple(x):
	return isinstance(x, (list, tuple)) #xisinstance第二个参数可以是tuple
def flatten(sequence, to_expand=list_or_tuple):
	for item in sequence:
		if to_expand(item):
			for subitem in flatten(item, to expand):
				yield subitem
		else:
			yield item

生成器函数在Python中与迭代器协议的概念联系在一起。简而言之，包含yield语句的函数会被特地编译成生成器。当函数被调用时，
他们返回一个生成器对象，这个对象支持迭代器接口。函数也许会有个return语句，但它的作用是用来yield产生值的。
不像一般的函数会生成值后退出，生成器函数在生成值后会自动挂起并暂停他们的执行和状态，他的本地变量将保存状态信息，
这些信息在函数恢复时将再度有效
yield用法：
def fun:
	for xxx:
		yeild

for i in fun(x):
	print i

其中yeild就好象把每次执行结果都缓存起来然后等for循环时再一个个弹出来


7. 删除重排列表
#list删除第二列，第三四列互换，用列表推导
listRows = [ [row[0], row[3], row[2]] for row in list]


8. 列表行列互换
#列表推导
arr = [[1,2,3],[4,5,6]]
print [[r[col] for r in arr] for col in range(len(arr[0]))]
[[1,4],[2,5],[3,6]]
#[r[col] for r in arr]结果arr[0],arr[1],即[[arr[0][col], arr[1][col]] for col in range(len(arr[0]))]
#最后相等于[[arr[0][0], arr[1][0]], [arr[0][1],arr[1][1]]

#zip map
print map(list, zip(*arr))

#zip zip函数接受任意多个（包括0个和1个）序列作为参数，返回一个tuple列表
x = [1, 2, 3]
y = [4, 5, 6]
z = [7, 8, 9]
xyz = zip(x, y, z)
print xyz

[(1, 4, 7), (2, 5, 8), (3, 6, 9)]

#map 第一个参数接收一个函数名，第二个参数接收一个可迭代对象
ls = [1,2,3]
rs = map(str, ls)
#打印结果 ['1', '2', '3']

lt = [1, 2, 3, 4, 5, 6]
def add(num):
    return num + 1
 
rs = map(add, lt)
print rs #[2,3,4,5,6,7]


9. itertools
#chain的使用
import itertools  
listone = ['a','b','c']  
listtwo = ['11','22','abc']  
for item in  itertools.chain(listone,listtwo):  
    print item  
输出：a b c 11 22 abc

#count cout返回一个无界的迭代器
i = 0  
for item in itertools.count(100):  
    if i>10:  
        break  
    print item,  
    i = i+1  
输出：100 101 102 103 104 105 106 107 108 109 110

#cycle无限循环，从列表中取元素，到列表尾后再从头取
import itertools  
listone = ['a','b','c']  
  
for item in itertools.cycle(listone):  
    print item
a b c a b c a b c a b c a b c a b c a b c a b c a b c...

#ifilter ifilter(fun,iterator)返回一个可以让fun返回True的迭代器
import itertools  
def funLargeFive(x):  
    if x > 5:  
        return True  
for item in itertools.ifilter(funLargeFive,range(-10,10)):  
    print item,  
结果：6 7 8 9

#imap返回一个迭代器，对iterator中的每个项目调用fun
import itertools  
listthree = [1,2,3]  
def funAddFive(x):  
    return x + 5  
for item in itertools.imap(funAddFive,listthree):  
    print item
结果：6　7　8

#islice()(seq, [start,] stop [, step])返回迭代器，其中的项目来自　将seq，从start开始,到stop结束，以step步长切割
import itertools  
listone = ['a','b','c']  
listtwo = ['11','22','abc']  
listthree = listone + listtwo  
for item in itertools.islice(listthree,3,5):  
    print item
结果：11 22

#izip返回迭代器，项目tuple，来自iterator的组合
import itertools  
listone = ['a','b','c']  
listtwo = ['11','22','abc']  
listthree = listone + listtwo  
for item in itertools.izip(listone,listtwo):  
    print item
结果：('a', '11') ('b', '22') ('c', 'abc')

#repeate(elem [,n])
import itertools  
listone = ['a','b','c']  
for item in itertools.repeat(listone,3):  
    print item
结果：['a', 'b', 'c'] ['a', 'b', 'c'] ['a', 'b', 'c']


10. 从字典中取值
#从字典中取值而不用担心不存在时抛出异常
d = {'key': 'value'}
print d.get('key', 'not found')
#dict.get(key, default=None)
#key -- 字典中要查找的键。
#default -- 如果指定键的值不存在时，返回该默认值值。
d.pop（'key'）#执行取出后删除


11. 增加一条dict条目
#当k是字典d的key时，直接用d[k]，不是则增加条目
#dict.setdefault(key, default=None)
#key -- 查找的键值。
#default -- 键不存在时，设置的默认键值
dict = {'Name': 'Zara', 'Age': 7}
print "Value : %s" %  dict.setdefault('Age', 9)
print "Value : %s" %  dict.setdefault('Sex', 'male')
print dict

Value : 7
Value : male
{'Name': 'Zara', 'Age': 7, 'Sex': 'male'}


12. 创建字典
d = dict(red=1, orange=2, blue=3) #更简洁
d = {'red': 1, 'orange': 2, 'blue': 3}
d = dict(zip(thekey, thevalue))

#数据很大可用itertools模块提高速度
import itertools
d = dict(itertools.izip(thekey, thevalue))


13. 交替用列表中元素作为k，v创建dict
#主要是注意分片步长
def dict_from_list(kvlist):
	return dict(zip(kvlist[::2], kvlist[1::2]))

kvlist[::2]等价于kvlist[0:len(list):2]
kvlist[1::2]等价于kvlist[1:len(list):2]

numbers = [1,2,3,4,5,6,7,8,9,10]
print numbers[7:10] #序列中最大的索引为9，而分片中的第二个索引为10，因为要包含第一个，不包含第二个，所以10，10-1=9，最后一个索引
输出：[8, 9, 10]
print numbers[-3:] # 获取最后3位元素 输出：[8, 9, 10]
print numbers[:3] #输出：[1, 2, 3]
print numbers[:] #[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
#：可以理解成到最前或最后
print numbers[0:10:1] #第三个参数为步长，[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
print numbers[::4] #第一个参数默认0，第二个参数默认len(numbers),输出：[1, 5, 9]
#步长不能为0，可为负，即从右到左提取元素，这时索引也是从右到左，第一个参数大于第二个参数
print numbers[6:3:-1]# 输出：[7, 6, 5]
print numbers[::-2]#输出：[10, 8, 6, 4, 2]
print numbers[5::-2] # 输出：[6, 4, 2]
print numbers[:3:-2]# 输出：[10, 8, 6],因为第二个参数不包含，所以4没有输出


14. 字典的子集
#获取字典中一些键属于特定结合，创建一个这些键组成的字典
def sub_dict(somedict, somekeys, default=None):
	return dict([(k, somedict.get(k, default)) for k in somekeys])

#从字典中删除子集
def sub_dict_remove(somedict, somekeys, default=None):
	return dict([k, somedict.pop(k, default)) for k in somekeys])


15. 反转字典
#value为key，key为value
def invert_dict(d):
	return dict([ (v, k) for k, v in d.iteritems()])

#字典很大的情况，用itertools更快
from itertools import izip
def invert_dict_fast(d):
	return dict(izip(d.itervalues, d.iterkeys()))


16. key对多value
#list作为字典的value，可重复
d1 = {}
d1.setdefault(key, []).append(value)

#dict为value
d2 = {}
d2.setdefault(key,{})[value] = 1


17. 字典交集和并集
#找字典key的交集，dict.fromkeys
#fromkeys()方法从序列键和值设置为value来创建一个新的字典
#dict.fromkeys(seq[, value]))
#seq -- 这是将用于字典的键准备的值的列表;value -- 这是可选的，如果提供的话则值将被设置为这个值
inter = dict.fromkeys([x for x in a if x in b])
#Dict = dict.fromkeys(['a','b'])
#{'a':None,'b':None}

#并集
union = dict(a, **b) #具体原理不知道，记住

Python的函数定义中有两种特殊的情况，即出现*，**的形式。
如：def myfun1(username, *keys)或def myfun2(username, **keys)等
 * 用来传递任意个无名字参数，这些参数会一个Tuple的形式访问。
 **用来处理传递任意个有名字的参数，这些参数用dict来访问。
>>> def fun1(*keys):
...     print "keys type=%s" % type(keys)
...     print "keys=%s" % str(keys)
...     for i in range(0, len(keys)):
...             print "keys[" + str(i) + "]=%s" % str(keys[i])
...
>>> fun1(2,3,4,5)
输出以下结果：
keys type=
keys=(2, 3, 4, 5)
keys[0]=2
keys[1]=3
keys[2]=4
keys[3]=5
#########################
# “**” 的应用
#########################
>>> def fun2(**keys):
...     print "keys type=%s" % type(keys)
...     print "keys=%s" % str(keys)
...     print "name=%s" % str(keys['name'])
...
>>>
>>> fun2(name="vp", age=19)
输出以下结果：
keys type=
keys={'age': 19, 'name': 'vp'}
name=vp

#用set
try:
	set
except NameError:
	from sets  import Set as set
a = set(Dict1.keys()) # dict.keys()返回一个list
b = set(Dict2.keys())
union = a | b
inter = a & b

18. 逐个或逐行处理
for line in f: #这种写法清晰，python中常用
	print line


