import httplib
import urllib2
httplib.HTTPConnection.debuglevel = 1
useragent = 'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)'
#accept='text/plain',

def fetch(url):
    """ 返回传入的url的内容 """
    request = urllib2.Request(url)
    request.get_full_url()
    request.add_header('User-Agent', useragent)
    opener = urllib2.build_opener()
    return opener.open(request).read()
