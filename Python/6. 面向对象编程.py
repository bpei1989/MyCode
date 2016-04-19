1. 定义常量
常量是指一旦初始化后就不能修改的固定值。c++中使用const保留字指定常量，而python并没有定义常量的保留字。
但是python是一门功能强大的语言，可以自己定义一个常量类来实现常量的功能。 
# -*- coding: UTF-8 -*- 
# Filename: const.py 
# 定义一个常量类实现常量的功能 
# 
# 该类定义了一个方法__setattr()__，和一个异常ConstError, ConstError类继承 
# 自类TypeError. 通过调用类自带的字典__dict__, 判断定义的常量是否包含在字典 
# 中。如果字典中包含此变量，将抛出异常，否则，给新创建的常量赋值。 
# 最后两行代码的作用是把const类注册到sys.modules这个全局字典中。 
class _const: 
    class ConstError(TypeError):pass 
    def __setattr__(self, name, value): 
        if self.__dict__.has_key(name): 
            raise self.ConstError, "Can't rebind const (%s)" %name 
        self.__dict__[name]=value 
import sys 
sys.modules[__name__] = _const() 

    这样就完成了一个常量类的定义，我们可以在use_const.py中调用const类。 

import const 
const.magic = 23 
const.magic = 33 

    运行之后，python解释器会提示错误： 

const.ConstError: can't rebind const(magic) 


2. 限制属性的设置
python允许给类和实例增加属性，但对于某些特定的类，想去掉这个功能
重写其__setattr__

def no_new_attibutes(wrapped_setattr):
	def __setattr__(self, name, value):
		if hasattr(self, name):
			wrapped_setattr(self, name, value)
		else:
			raise AttributeError("XXX")
	return __setattr__

class NoNewAttrs(object):
	__setattr__ = no_new_attributes(object.__setattr__)
	class __metaclass__ (type):
		__setattr__ = no_new_attributes(type.__setattr__)


3. Chainmap
查找一个字典链，如果键不在其中则尝试第二个，以此类推
（
self名称不是必须的，在python中self不是关键词，你可以定义成a或b或其它名字都可以,
但是约定成俗，不要搞另类，大家会不明白的。下例中将self改为myname一样没有错误：
class Person:
          def _init_(myname,name):
                   myname.name=name
          def sayhello(myname):
                   print 'My name is:',myname.name
p=Person('Bill')
print p

self指的是类实例对象本身(注意：不是类本身)。
）
_xxx      不能用'from module import *'导入 
__xxx__ 系统定义名字 
__xxx    类中的私有变量名

核心风格：避免用下划线作为变量名的开始。

"单下划线" 开始的成员变量叫做保护变量，意思是只有类对象和子类对象自己能访问到这些变量；
"双下划线" 开始的是私有成员，意思是只有类对象自己能访问，连子类对象也不能访问到这个数据。

以单下划线开头（_foo）的代表不能直接访问的类属性，需通过类提供的接口进行访问，
不能用“from xxx import *”而导入；以双下划线开头的（__foo）代表类的私有成员；
以双下划线开头和结尾的（__foo__）代表python里特殊方法专用的标识，如 __init__（）代表类的构造函数。
class Chainmap(object):
	def __init__(self, *mappings):
	#  * 用来传递任意个无名字参数，这些参数会一个Tuple的形式访问。 **用来处理传递任意个有名字的参数，这些参数用dict来访问。*
		self._mappings = mappings
	def __getitem__(self, key):
		for mapping in self._mappings:
			try:
				return mapping[key]
			except KeyError:
				pass
		raise KeyError, key
	def get(self, key, default=None):
		try:
			return self[key]
		except KeyError:
			return default
	def __contains__(self, key):
		try:
			self[key]
			return True
		except KeyError:
			return False


		