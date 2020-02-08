
import sys



def Sort(sub_list): 
    sub_list.sort(key = lambda x: x[0]) 
    return sub_list 
input_file_name=sys.argv[1]
out_file_name=sys.argv[2]
input_file = open(input_file_name,'r')
out= open(out_file_name,'w+')
data={}
for line in input_file.readlines():
	line=line.strip('\n')
	line=line.split('\t')
	
	if line[0] in data:
		newdata_cell=[]
		newdata_cell.append(line[1])
		newdata_cell.append(line[2])
		key=line[0]
		values=data[key]
		values.append(newdata_cell)
		data[key]=values
	else :
		newdata_cell=[]
		newdata_cell.append(line[1])
		newdata_cell.append(line[2])
		key=line[0]
		values=[]
		values.append(newdata_cell)
		data[key]=values

keys=data.keys();
keys=sorted(keys)
for i in range(len(keys)):

	sortedlist=Sort(data.get(keys[i]))
	out.write('Message {}\n'.format((i+1)))
	for line in sortedlist:
		out.write('{} {} {} \n'.format(keys[i],line[0],line[1]))

out.close()

