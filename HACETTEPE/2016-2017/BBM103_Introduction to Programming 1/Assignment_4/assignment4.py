import matplotlib.pyplot as plot
import csv
import numpy as np
import random
import sys
data1=sys.argv[1]
data2=sys.argv[2].split(",")
nominees =data2

kaydet1=open("retrievedData.txt","w")
kaydet2=open("myAnswer.txt","w")
def retrieveData(filename, nominees):
    first = True
    count = 0
    electcount = 0
    total = [0 for i in range(len(state(data1)) * len(nominees))]
    nomineelist = [0 for i in range(len(nominees))]
    with open(filename, newline='') as f:
        reader = csv.reader(f, delimiter=',')
        for row in reader:
            if (first):
                for i in nominees:
                    for j in range(len(row)):
                        if (i == row[j]):
                            nomineelist[count] = j
                            count += 1
                            first = False

            else:
                for k in range(len(nominees)):
                    total[51 * k + electcount] = int(row[nomineelist[k]])

                electcount += 1
    return total
def find_election(filename, nominees):
    first = True
    count = 0

    electıon = [0 for i in range(len(nominees))]
    with open(filename, newline='') as f:
        reader = csv.reader(f, delimiter=',')
        for row in reader:
            if (first):
                for i in nominees:
                    for j in range(len(row)):
                        if (i == row[j]):
                            electıon[count] = row[j]
                            count += 1
                            first = False


    return electıon


def state(dosya):
    state_name = []
    first1 = True
    with open(dosya, newline='') as f:
        reader = csv.reader(f, delimiter=',')
        for row in reader:
            if (first1):
                first1 = False
            else:
                state_name.append(row[0])
        return state_name
def eahceya():
    dic5 = {}
    baskanlar5 = find_election(data1, nominees)

    oylar5 = retrieveData(data1, nominees)
    for a in range(len(baskanlar5)):
        dic5[baskanlar5[a]] = oylar5[len(eyaletler) * (a):len(eyaletler) * (a + 1)]
    return dic5
def toplamtoplam():
    sozlukhalınde=max_vote()
    adamlar=baskanlar
    meto=0
    for m in adamlar:
        meto+=int(sozlukhalınde[m])
    return meto


def max_vote():
    dic={}
    baskanlar1 = find_election(data1, nominees)
    dic2={}
    oylar = retrieveData(data1, nominees)
    for a in range(len(baskanlar)):
        dic[baskanlar1[a]] =oylar[len(eyaletler)*(a):len(eyaletler)*(a+1)]
    for a in baskanlar1:
        toplamoy=0
        for d in dic[a]:

            d=int(d)
            toplamoy+=d
        dic2[a]=toplamoy

    return dic2
def select_best():
    dic3={}
    baskanlar2 = find_election(data1, nominees)
    toplamoylar1=max_vote()
    mu=0
    for j in baskanlar2:
        if(toplamoylar1[j]>mu ):
            mu=toplamoylar1[j]
            for s in range(5):
                if (toplamoylar1[j]==mu):
                    dic3[j]=mu
    return dic3
def name():
    name = []
    baskanlar3 = find_election(data1, nominees)
    toplamoylar2= max_vote()
    mu = 0
    for j in baskanlar3:
        if (toplamoylar2[j] > mu):
            mu = toplamoylar2[j]
        if   (toplamoylar2[j] == mu):
            name.append(j)
    return name
def name2():
    name2 = []
    baskanlar6 = find_election(data1, nominees)
    baskanlar6.remove(name()[0])
    toplamoylar6 = max_vote()
    mu = 0
    for j in baskanlar6:
        if (toplamoylar6[j] > mu):
            mu = toplamoylar6[j]
        if (toplamoylar6[j] == mu):
            name2.append(j)
    return name2

def selectsecond():
    baskanlar4 = find_election(data1, nominees)
    toplamoylar3=max_vote()
    baskanlar4.remove(name()[0])
    del toplamoylar3[name()[0]]

    mu=0
    dic4={}
    for j in baskanlar4:
        if(toplamoylar3[j]>mu ):
            mu=toplamoylar3[j]
            for s in range(5):
                if (toplamoylar3[j]==mu):
                    dic4[j]=mu
    return dic4
def yuzdelık():
    baskanlar8=find_election(data1, nominees)
    dic10={}
    toplamoy=0
    for b in baskanlar8:

        toplamoy+=max_vote()[b]
    for g in   baskanlar8:
        oran=(int(max_vote()[g])/toplamtoplam())*100
        orancık=format(oran,".3f")
        dic10[g]=orancık

    return  dic10
def obtainHistogram(list):
    sum = 0
    number = [0 for i in range(10)]
    for i in list:
        if (len(str(i)) == 1):
            for j in range(10):
                if (j == i):
                    number[0] += 1
                    number[j] += 1
        else:
            last1 = str(i)
            last2 = last1[::-1]
            last = last2[0:2]
            for a in last:
                for b in range(10):
                    if (int(a) == b):
                        number[b] += 1
    for c in number:
        sum += c

    for d in range(10):
        number[d] = number[d] / sum
    return number
def plotHistogramWithSample():
    liste1=[]
    liste2=[]
    liste3=[]
    liste4=[]
    liste5=[]
    analiste=[]
    a=random.randint(1,100)
    for g in range(10):
        liste1.append(a)
        a=random.randint(1,100)
    analiste.append(liste1)
    for g in range(50):
        liste2.append(a)
        a=random.randint(1,100)
    analiste.append(liste2)
    for g in range(100):
        liste3.append(a)
        a=random.randint(1,100)
    analiste.append(liste3)
    for g in range(1000):
        liste4.append(a)
        a=random.randint(1,100)
    analiste.append(liste4)
    for g in range(10000):
        liste5.append(a)
        a=random.randint(1,100)
    analiste.append(liste5)
    return analiste
def listeli_yuzde():
    gelensoz=yuzdelık()
    listemiz=[]

    for t in baskanlar:
        listemiz.append(float(gelensoz[t]))


    return listemiz
def listeli_yuzde_yazı():
    gelensoz=yuzdelık()
    listemiz2=[]

    for t in baskanlar:
        listemiz2.append((gelensoz[t])+(" %"))



    return listemiz2
def calculateMSE(first,second):

    sayı=0
    for a in range(len(first)):
        result=(float(first[a])-float(second[a]))**2
        sayı+=result
    return sayı
def ideal_line():
    gelenler=b
    sayı4=0
    kactane=0
    for i in gelenler:
        sayı4+=float(i)
        kactane+=1
    idealdeger=sayı4/kactane
    listecik=[idealdeger for t in range(10)]
    return listecik
def compareMSEs(input):
    birsayı=0
    ikisayı=0
    for a in listeler():
        if (input>=a):
            birsayı+=1
        else:
            ikisayı+=1
    return birsayı,ikisayı
def text():
    texte=eyalet_ve_kisi_bası_oylar
    abcd=""
    for a in texte:
        abcd+=str(a)+","
    return abcd
def listeler():
    lists = [[] for _ in range(10000)]
    listcik=[]
    for n in range(10000):
        for i in range(len(eyalet_ve_kisi_bası_oylar)):
            hehe = random.randint(0, 10000)

            lists[n].append(hehe)

    for t in range(10000):
        okunacaklar=lists[t]
        degerler=obtainHistogram(okunacaklar)
        asıldegerler=calculateMSE(degerler,ideal_line())
        listcik.append(asıldegerler)
    return listcik
def calculate_p():
    d, b = compareMSEs(MSE_value_of)
    pınındegerı=d/(d+b)
    return  pınındegerı
def tenstep():
    a,b=compareMSEs(MSE_value_of)
    print("MSE value of 2012 USA election is  "+str(MSE_value_of))
    kaydet2.write("MSE value of 2012 USA election is  "+str(MSE_value_of)+"\n")
    print("The number of MSE of random samples which are larger than or equal to USA election MSE is  "+ str(a))
    kaydet2.write("The number of MSE of random samples which are larger than or equal to USA election MSE is  "+str(a)+"\n")
    print("The number of MSE of random samples which are smaller than USA election MSE is  "+ str(b))
    kaydet2.write("The number of MSE of random samples which are smaller than USA election MSE is  "+str(b)+"\n")
    print("2012 USA election rejection level p is "+str(calculate_p()))
    kaydet2.write("2012 USA election rejection level p is "+str(calculate_p())+"\n")
    if (b<500):
        print("Finding: We reject the null hypothesis at the p= "+str(calculate_p())+"level")
        kaydet2.write("Finding: We reject the null hypothesis at the p= "+str(calculate_p())+" level"+"\n")
    else:
        print("Finding: There is no statistical evidence to reject null hypothesis")
        kaydet2.write("Finding: There is no statistical evidence to reject null hypothesis"+"\n")

def grafik1():
    figmeet= plot.subplots()

    N = len(eyaletler)
    ww=np.arange(0.9,len(eyaletler)+1,1)
    width = 0.3
    ind = np.arange(N)
    birinci = name()[0]
    ikinci = name2()[0]
    aldıklarıoy0 = eahceya()
    plot.bar(ind + 2*width, aldıklarıoy0[ikinci], width, label=ikinci, color='r')
    plot.bar(ind+ 3*width, aldıklarıoy0[birinci], width, label=birinci, color='b')

    plot.xlim([0,ind.size+0.5])

    plot.xticks(ww, eyaletler, rotation=90,fontsize = 8)

    plot.xlabel("States")
    plot.ylabel("Vote Count", rotation=90)
    plot.legend()
    plot.subplots_adjust(left=0.18)
    plot.subplots_adjust(bottom=0.25)

    plot.savefig("ComparativeVotes.pdf")
def grafik2():
    adamsayısı=baskanlar
    if (len(adamsayısı)==4):
        figc = plot.subplots()

        N=1
        width=0.8

        xx1=np.arange(0.4,len(listeli_yuzde_yazı())+2,1.6)
        ind = np.arange(N)
        secmenler=baskanlar

        aldıklarıoy=listeli_yuzde()
        plot.bar(ind , aldıklarıoy[0], width, label=secmenler[0],color='r')
        plot.bar(ind + 2 * width, aldıklarıoy[1], width, label=secmenler[1],color='b')
        plot.bar(ind + 4* width, aldıklarıoy[2], width, label=secmenler[2],color='y')
        plot.bar(ind + 6* width, aldıklarıoy[3], width, label=secmenler[3],color='c')

        plot.xticks(xx1, listeli_yuzde_yazı())

        plot.xlabel("Nominees")
        plot.ylabel("Vote percentages",rotation=90)

        plot.subplots_adjust(bottom=0.20)
        plot.legend()
        plot.savefig("CompVotePercs.pdf")
    elif    (len(adamsayısı)==3):
        fig11 = plot.subplots()
        N = 1
        width = 0.8
        ind = np.arange(N)
        secmenler = baskanlar
        xx1 = np.arange(0.4, len(listeli_yuzde_yazı()) + 2, 1.6)
        aldıklarıoy = listeli_yuzde()
        plot.bar(ind, aldıklarıoy[0], width, label=secmenler[0], color='r')
        plot.bar(ind + 2 * width, aldıklarıoy[1], width, label=secmenler[1], color='b')
        plot.bar(ind + 4 * width, aldıklarıoy[2], width, label=secmenler[2], color='y')

        plot.xticks(xx1, listeli_yuzde_yazı())

        plot.xlabel("Nominees")
        plot.ylabel("Vote percentages", rotation=90)

        plot.subplots_adjust(bottom=0.20)
        plot.legend()
        plot.savefig("CompVotePercs.pdf")
    elif (len(adamsayısı) == 2):
        fig22 = plot.subplots()
        N = 1
        width = 0.8
        ind = np.arange(N)
        secmenler = baskanlar
        xx1 = np.arange(0.4, len(listeli_yuzde_yazı()) + 2, 1.6)
        aldıklarıoy = listeli_yuzde()
        plot.bar(ind, aldıklarıoy[0], width, label=secmenler[0], color='r')
        plot.bar(ind + 2 * width, aldıklarıoy[1], width, label=secmenler[1], color='b')


        plot.xticks(xx1, listeli_yuzde_yazı())

        plot.xlabel("Nominees")
        plot.ylabel("Vote percentages", rotation=90)

        plot.subplots_adjust(bottom=0.20)
        plot.legend()
        plot.savefig("CompVotePercs.pdf")

def grafik3():
    fig = plot.subplots()
    digit = np.arange(10)
    value_of_result = obtainHistogram([7, 24, 25, 180, 249, 326, 446, 446, 512, 552, 612, 618, 618, 714, 780, 839, 846, 890, 949, 951])
    ortlamadeger = ideal_line()

    plot.plot(digit, value_of_result, "-", label="Digit Dist.", color="r")
    plot.plot(digit, ortlamadeger, "--", label="Mean", color="g")
    plot.xlabel("Digits")
    plot.ylabel("Distribution")
    plot.title("Histogram of least sign. digits")
    plot.legend()
    plot.savefig("Histogram.pdf")
def grafik4():



    for d in range(len(plotHistogramWithSample())):

        if (d==0):
            fig1=plot.subplots()
            value_of_result = obtainHistogram(plotHistogramWithSample()[d])
            digit = np.arange(10)
            ortlamadeger = ideal_line()
            plot.plot(digit, value_of_result, "-", label="Digit Dist.", color="r")
            plot.plot(digit, ortlamadeger, "--", label="Mean", color="g")
            plot.xlabel("Digits")
            plot.ylabel("Distribution")
            plot.title("Histogram of least sign. digits - Sample:1")
            plot.legend()
            plot.savefig("HistogramofSample1.pdf")
        elif(d==1):
            fig2 = plot.subplots()
            value_of_result = obtainHistogram(plotHistogramWithSample()[d])
            digit = np.arange(10)
            ortlamadeger = ideal_line()
            plot.plot(digit, value_of_result, "-", label="Digit Dist.", color="r")
            plot.plot(digit, ortlamadeger, "--", label="Mean", color="g")
            plot.xlabel("Digits")
            plot.ylabel("Distribution")
            plot.title("Histogram of least sign. digits - Sample:2")
            plot.legend()
            plot.savefig("HistogramofSample2.pdf")
        elif (d==2):
            fig3= plot.subplots()
            value_of_result = obtainHistogram(plotHistogramWithSample()[d])
            digit = np.arange(10)
            ortlamadeger = ideal_line()
            plot.plot(digit, value_of_result, "-", label="Digit Dist.", color="r")
            plot.plot(digit, ortlamadeger, "--", label="Mean", color="g")
            plot.xlabel("Digits")
            plot.ylabel("Distribution")
            plot.title("Histogram of least sign. digits - Sample:3")
            plot.legend()
            plot.savefig("HistogramofSample3.pdf")
        elif   (d==3):
            fig4= plot.subplots()
            value_of_result = obtainHistogram(plotHistogramWithSample()[d])
            digit = np.arange(10)
            ortlamadeger = ideal_line()
            plot.plot(digit, value_of_result, "-", label="Digit Dist.", color="r")
            plot.plot(digit, ortlamadeger, "--", label="Mean", color="g")
            plot.xlabel("Digits")
            plot.ylabel("Distribution")
            plot.title("Histogram of least sign. digits - Sample:4")
            plot.legend()
            plot.savefig("HistogramofSample4.pdf")
        elif    (d == 4):
            fig5 = plot.subplots()
            value_of_result = obtainHistogram(plotHistogramWithSample()[d])
            digit = np.arange(10)
            ortlamadeger = ideal_line()
            plot.plot(digit, value_of_result, "-", label="Digit Dist.", color="r")
            plot.plot(digit, ortlamadeger, "--", label="Mean", color="g")
            plot.xlabel("Digits")
            plot.ylabel("Distribution")
            plot.title("Histogram of least sign. digits - Sample:5")
            plot.legend()
            plot.savefig("HistogramofSample5.pdf")
eya=state(data1)
eyaletler = state(data1)
baskanlar = find_election(data1, nominees)
eyalet_ve_kisi_bası_oylar=retrieveData(data1, nominees)
b=obtainHistogram([7, 24, 25, 180, 249, 326, 446, 446, 512, 552, 612, 618, 618, 714, 780, 839, 846, 890, 949, 951])
a=obtainHistogram(eyalet_ve_kisi_bası_oylar)
MSE_value_of=calculateMSE(a,ideal_line())
kaydet1.write(str(text()))
compareMSEs(MSE_value_of)
grafik1()
grafik2()
grafik3()
grafik4()
tenstep()
