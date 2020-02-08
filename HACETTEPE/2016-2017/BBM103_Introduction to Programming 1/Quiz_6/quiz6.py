import csv
import matplotlib.pyplot as plot
def readfile(filename1):
    first=True
    temvalue2=[]
    with open(filename1, newline='') as f:
        reader = csv.reader(f, delimiter=',')
        for row in reader:

            if (first):
                first = False
            else:
                for a in row[1:3]:
                    temvalue2.append(a)
        return temvalue2


def maxtem():
    m= okunacak
    list1=[]
    list2 = [0, 1486, 2880, 4368, 5808, 7296, 8736, 10224, 11748, 13148, 14640, 16080, 17568]
    for g in range(12):
        d = m[list2[g]:list2[g+1]]
        min = float(d[0])
        for i in range(len(d)):
            if (float(d[i]) > float(min)):
                min = d[i]
        list1.append(min)

    return list1

def convert():
    new_list=[]
    new=maxtem()
    for a in new:
        a=float(a)
        new_list.append(a)
    return new_list

def readfile1(filename):
    first=True
    temvalue1=[]
    with open(filename, newline='') as f:
        reader = csv.reader(f, delimiter=',')
        for row in reader:

            if (first):
                first = False
            else:

                temvalue1.append(row)
        return temvalue1
def weahter(filename):
    first = True
    type_weather = []
    with open(filename, newline='') as f:
        reader = csv.reader(f, delimiter=',')
        for row in reader:

            if (first):
                first = False
            else:


                type_weather.append(row[7])
        new_pure_file=set(type_weather)
        new_pure_file=sorted(new_pure_file)
        return new_pure_file


def calculata():
    value=[]
    pure_file1=havadurumu
    reader2=bbb
    total=[0 for x in range(len(pure_file1))]
    total_time=[0 for x in range(len(pure_file1))]
    for i in range(len(pure_file1)):
        for m in range(len(reader2)):
            if(pure_file1[i]==reader2[m][7]):
                total[i]+=+float(reader2[m][1])

                total_time[i]+=1

        result=float(total[i])/float(total_time[i])
        value.append(result)
    return value
def calculata2():
    son_liste=[]
    gelenler=calculata()
    for v in range(len(gelenler)):
        son_liste.append('{:f}'.format(gelenler[v]))

    return son_liste
def output1(mon,maxtem):
    text="Month\t\t\t\t\t\t\t\t\t\t\t\tMax Temperature   \n"
    ff=[]
    for f in range(len(mon)):
        text+=("{:50}\t\t{:>10}".format(str(mon[f]),float(maxtem[f]),)+"\n")
    return text
        #text+=mon[f]+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+maxtem[f]+"\n"

    #print(text)
def yazma():
    val=calculata2()
    out="Weather\t\t\t\t\t\t\t\t\t\t\t\tMean Temperature \n"
    pure_file=havadurumu
    for a in range(len(val)):
        out+=("{:50}\t\t{:>10f}".format(str(pure_file[a]),float(val[a]))+"\n")
    print(out)


def grafık():
    tempu = convert()
    x_pos = [x for x in range(len(listmon))]
    plot.bar(x_pos, tempu, align="center", color="g", alpha=0.9)
    plot.xticks(x_pos, listmon, rotation=45)
    plot.title("Max tempperature for Months")
    plot.savefig("maxtemp.png")
    plot.show()


def grafık2():
    statu = havadurumu
    tempi = calculata()
    x_pos = [x for x in range(len(statu))]
    plot.bar(x_pos, tempi, align="center", color="b", alpha=0.9)
    plot.xticks(x_pos, statu, rotation=45)
    plot.title("Main tempperature for Weather")
    plot.savefig("meantemp.png")
    plot.show()

listmon = ["January", "February","March", "April", "May", "June", "July", "August", "September","October", "November", "December" ]
okunacak=readfile("weather_2012.csv")
havadurumu=weahter("weather_2012.csv")
bbb=readfile1("weather_2012.csv")
sonuc=output1(listmon,maxtem())
print(sonuc)
grafık()

yazma()
grafık2()


