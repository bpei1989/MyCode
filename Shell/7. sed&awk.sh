sed "s/Tom/Tommy/" 1.txt #��Tom�����ָĳ�Tommy
sed "/Mike/ d" 1.txt #ɾ������Mike����
sed "1,3d" 1.txt #ɾ��һ������
sed "/^$/ d" 1.txt #ɾ�����пհ���
sed "5,10! d" 1.txt #��ʾ5-10��


a.txt
Mike Harrington:[510] 548-1278:250:100:175
Christian Dobbins:[408] 538-2358:155:90:201
Susan Dalsass:[206] 654-6279:250:60:50
Archie McNichol:[206] 548-1348:250:100:175
Jody Savage:[206] 548-1278:15:188:150
Guy Quigley:[916] 343-6410:250:100:175
Dan Savage:[406] 298-7744:450:300:275

awk -F ':' '{print $2}' a.txt #��ʾ���е绰����
awk -F ':' '/Dan/ {print $2}' a.txt #��ʾDan�ĵ绰����
awk -F ':' '/Susan/ {print $1, $2}' a.txt #��ʾSusan�����ֺ͵绰����
awk -F '[:| ]' '$2 ~ /^D/ {print $2}' a.txt #��ʾ������D��ͷ���գ�ע��$2 ~ /^D/�����ڶ�����ƥ��D��ͷ������perl�﷨
awk -F '[:| ]' '$1 ~ /^[C|E]/ {print $1}' a.txt #��ʾ������һ��C��E��ͷ����
awk 'length($1) == 4 {print $1}' a.txt #��ʾ����ֻ���ĸ��ַ�������ע�⣬$1��$2�ȿ�����Ϊ����������ƥ������Ͷ���
awk -F '[:| ]' '$3 ~ /\[916\]/ {print $1,$2}' a.txt #��ʾ��������Ϊ916��������ע�ⷽ������ת���ַ�

дһ��awk�Ľű�,��������:
	 - ��ʾSavage��ȫ���͵绰����
	 - ��ʾChet�ľ��
	 - ��ʾ����ͷһ���¾��$250������.
awk -F '[:| ]' '{if($2=="Savage") {print $1,$2,$3,$4} else if ($1=="Chet") {print $5,$6,$7} else if ($5=="250") {print $1,$2}}' 1.txt #ע��if-else