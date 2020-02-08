import matplotlib.pyplot as plot
import csv
import numpy as np
import random
import sys

heber_file=open("haberman.data","r")

def read_file(dosya):
    hastayasları=[]
    hastlandıgıyıl=[]
    sonuclar=[]
    yasadımı=[]
    for d in dosya:
        d=d.strip("\n")
        d=d.split(",")
        hastayasları.append(d[0])
        hastlandıgıyıl.append(d[1])
        sonuclar.append(int(d[2]))
        yasadımı.append(int(d[3]))
    return hastayasları,hastlandıgıyıl,sonuclar,yasadımı
def buyılkackisiyasadııldu():
    a,b,c,d=veriler
    yıllardayasayanlar=[ x for x in range(58,70)]
    yıllarolenler=[ x for x in range(58,70)]
    list1=[0 for x in range(12)]
    list2=[0 for x in range(12)]

    for v in range(len(b)):
        if (d[v]==1):

            for m in range(12):

                if  int(yıllardayasayanlar[m])==int(b[v]):
                    list1[m]+=1
        else:
            for m in range(12):
                if int(yıllarolenler[m]) == int(b[v]):

                    list2[m]+=1


    return list1,list2

    return yıllardayasayanlar,yıllarolenler
def calculate_NB():
    toplamsur=0
    toplamolu=0
    a,b=buyılkackisiyasadııldu()
    for v in a:
        toplamsur+=int(v)
    for t in b:
        toplamolu+=int(t)

    NB1=toplamsur//10
    NB2=toplamolu//10
    return NB1,NB2
def yıllaragoreolenvayasayan():
    yıllar=[]
    w,e,r,t=veriler
    for t in w:

        yıllar.append(int(t))
    puer_yıllar=set(yıllar)
    puer_yıllar=sorted(puer_yıllar)
    return puer_yıllar
def str_yıllar():
    gelidı=yıllaragoreolenvayasayan()
    list1=[]
    for h in gelidı:
        list1.append(str(h))
    return list1

""""
                type_weather.append(row[7])
        new_pure_file=set(type_weather)
        new_pure_file=sorted(new_pure_file)
        return new_pure_file
"""
def bıryıldayasaynlarınsayısı():
    a,b,c,d=veriler
    x,y=calculate_NB()
    gelenliste=yıllaragoreolenvayasayan()
    olcakolanyasayan=[0 for x in range(len(gelenliste))]
    olcakoleler=[0 for x in range(len(gelenliste))]

    for t in range(len(gelenliste)):
        for p in range(len(b)):
            if (gelenliste[t]==int(a[p]) and int(d[p])==1):
                olcakolanyasayan[t]+=1
            else:

                if(gelenliste[t]==int(a[p]) and int(d[p])==2):
                    olcakoleler[t]+=1
    return olcakolanyasayan,olcakoleler

def grafik1():
    figmeet = plot.subplots()
    width = 0.3
    a,b=buyılkackisiyasadııldu()
    N=len(a)
    ww=np.arange(0.9,len(a)+1,1)

    ind = np.arange(N)

    plot.bar(ind + 1.8 * width, a, width, label="survived", color='r')
    plot.bar(ind + 3 * width, b, width, label="died", color='b')



    plot.xticks(ww, [1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970], fontsize=10)

    plot.xlabel("years")
    plot.ylabel("# of patients", rotation=90)
    plot.xlim([0, ind.size + 0.5])
    plot.legend()
    plot.savefig("Fig1.pdf")
def grafik2():
    figmeet = plot.subplots()
    width = 0.3
    a, b = buyılkackisiyasadııldu()

    N = len(a)
    ww = np.arange(0.9, len(a) + 1, 1)

    ind = np.arange(N)

    plot.bar(ind + 1.8 * width, a, width, color='g')

    plot.xticks(ww, fontsize=10)

    plot.xlabel("years")
    plot.ylabel("# of patients", rotation=90)
    plot.xlim([0, ind.size + 0.5])
    plot.savefig("Fig2.pdf")

veriler=read_file(heber_file)
buyılkackisiyasadııldu()
grafik1()
grafik2()
#aa=calculate_NB()
bıryıldayasaynlarınsayısı()