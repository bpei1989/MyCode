1. 对字典排序
#先对key排序，再根据key选value
def sortedDictVaule(adict):
	keys = adict.keys()#keys是list
	keys.sort()
	return [adict[key] for key in keys]


2. 不区分大小写对字符串列表排序
#默认排序区分大小写
#这是一种思路，用tuple的第一个为转换前的，第二个为原来的，根据第一个排序，然后再取第二个
#这种方法就是DSU
def case_insensitive_sort(string_list):
	auxiliary_list = [(x.lower(), x) for x in string_list]
	auxilary_list.sort()
	return [x[1] for x in auxiliary_list]

#直接用标准库
sorted(string_list, key=str.lower)#或string_list.sort(key=str.lower)
sorted(list)返回一个对象，可以用作表达式。原来的list不变，生成一个新的排好序的list对象
list.sort() 不会返回对象，改变原有的list


3. 根据对象属性排序
#可以用tuple转换的思想
#标准库更方便
import operator
lst.sort(key=operator.attrgetter(attr))

list.sort(func=None, key=None, reverse=False) 
实例1:正向排序
1
2
3
4
>>>L = [2,3,1,4]
>>>L.sort()
>>>L
>>>[1,2,3,4]
实例2:反向排序
1
2
3
4
>>>L = [2,3,1,4]
>>>L.sort(reverse=True)
>>>L
>>>[4,3,2,1]
实例3:对第二个关键字排序 (用函数重载cmp)
1
2
3
4
>>>L = [('b',6),('a',1),('c',3),('d',4)]
>>>L.sort(lambda x,y:cmp(x[1],y[1])) 
>>>L
>>>[('a', 1), ('c', 3), ('d', 4), ('b', 6)]
实例5: 对第二个关键字排序 
1
2
3
4
5
>>>L = [('b',2),('a',1),('c',3),('d',4)]
>>>import operator
>>>L.sort(key=operator.itemgetter(1)) 
>>>L
>>>[('a', 1), ('b', 2), ('c', 3), ('d', 4)]


4. 根据value排序key
#value为频率，key为具体事物，按频率把元素统计结果放到list
class hist(list): #继承list
	def __init__(self, n):
		'''初始化列表，统计n个不同项的出现'''
		list.__init__(self, n*[0])#初始化为0
	def add(self, item, increment=1):
		'''为item的条目增加计数'''
		self[item] += increment
	def count(self, reverse=False):
		'''返回根据对应值排序的索引列表'''
		aux = [ (v, k) for k, v in enumerate(self)]
		aux.sort()
		if reverse: aux.reverse()
		return [k for v, k in aux]


5. 根据内嵌数字排序
#3.txt和10.txt,需要3.txt在前面，python默认根据首字符排序
#把数字提取出来，然后DSU
import re
re_digit = re.compile(r'(\d+)')#r是原生态字符，如果没有r需要\\d，第一个\用于转义第二个\,而原生态就不用
def embedded_numbers(s):
	pieces = re_digit.split(s) #分成数字与非数字
	pieces[1::2] = map(int, pieces[1::2]) #步长为2，比如[1, .txt, 2, .txt],提取数字部分，map把数字部分int化，map第二参都执行第一参数定义的方法
	return pieces
def sort_strings_with_embeded_number(alist):
	aux = [ (embeded_numbers(s), s) for s in alist]#返回一个tuple，第一个元素执行提取数字并int化
	aux.sort()
	return [s for__ , s in aux ]

#直接用库函数
sorted(alist, key=embeded_numbers)
	
	
6. 随机顺序
def process_all_in_random(data, process):
	random.shuffle(data)
	for elem in data: process(elem)


7. 增加元素后保持list的顺序
#可以用list.sort(),这样每次增加都要sort
#用heapq模块
import heapq
heapq.heapify(tlist)


8. 获取序列中最小的n个元素
#可以用sort，但如果序列很长则用堆
import heapq
def isorted(data):
	data = list(data)
	heapq.heapify(data)
	while data:
		yeild heapq.heappop(data)

#还可以用库函数
import heapq
heapq.nsmallest(n, data)


9. 在排序后的序列中寻找一段元素
#二分查找，用bisect
import bisect
x_insert_point = bisect.bisect_right(L, x)
x_is_present = L[x_insert_point:x_insert_point] == [x]]


10. 寻找子串
#字符串及Unicode用find或re最好


11. 根据姓的首字母将名字排序和分组
#先sort然后再用itertools的groupby
def groupnames(name_iterable):
	sorted_names = sorted(name_iterable, key=sortkeyfunc) #用groupby必须先sort
	name_dict = {}
	for key, group in itertools.groupby(sorted_names, groupkeyfunc):
		name_dict[key] = tuple(group)
	return name_dict

#groupby
from itertools import *
def height_class(h):
    if h>180:
        return 'tall'
    elif h<160:
        return 'short'
    else:
        return 'middle'

friends = [191, 158, 159, 165, 170, 177, 181, 182, 190]

friends = sorted(friends,key = height_class)

for m,n in groupby(friends,key = height_class):
    print m
    print list(n)

结果：
middle
[165, 170, 177]
short
[158, 159]
tall
[191, 181, 182, 190]


