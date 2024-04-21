from chain import *
from openAdressing import *
# chained = ChainHashDict()
# chained.insert(1,3)
# chained.insert(2,4)
# chained.insert(5,'zyrafa')
# chained.insert(6,'osiem')
# chained.insert(9,15)
#
# print(chained.find(9))
# print(chained.find(5))
#
# chained.delete(5)
#
# print(chained.find(5))

open_add = OpenAddressingDict()
open_add.insert(10,2)
open_add.insert(20,4)
open_add.insert(5,'zyrafa')
open_add.insert(6,'osiem')
open_add.insert(9,15)
open_add.insert(5,'zyraf')

print(open_add.find(9))
print(open_add.find(5))

open_add.delete(5)

print(open_add.find(5))

open_add.delete(1)


print(open_add.find(5))