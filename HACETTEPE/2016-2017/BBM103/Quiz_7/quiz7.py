import sys
sayılar1=sys.argv[1].split(",")


def fonksiyon(sayılar):
    list1=[]
    count = 0
    list2=[]

    for a in sayılar:
        sayı = 0
        list1.append(str(a)+str("="))
        for c in range(a-1):
            bül=[x for x in range(a)]
            if (a%int(bül[c+1])==0):
                list1.append(int(bül[c]+1))
                sayı+=1
        count+=1
        list2.append(sayı)


    print(list1)
fonksiyon(sayılar1)