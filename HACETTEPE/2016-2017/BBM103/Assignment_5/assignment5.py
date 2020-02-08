# -*- coding: utf-8 -*-
import os
os.mkdir("filmList")
stoptext = open("stopwords.txt", "r",encoding = "ISO-8859-1")
kayıt=open("filmGenre.txt","w",encoding = "ISO-8859-1")
item=open("u.item","r", encoding = "ISO-8859-1")
data=open("u.data","r", encoding="ISO-8859-1")
genre=open("u.genre","r", encoding="ISO-8859-1")
occupation=open("u.occupation","r", encoding="ISO-8859-1")
user=open("u.user","r", encoding="ISO-8859-1")
def read_film():
    directory = os.path.normpath("film/")
    list=[]
    for subdir, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith(".txt"):
                f=open(os.path.join(subdir, file),'r',encoding="ISO-8859-1")
                a = f.read()
                a=a.split("(")
                a = a[0].rstrip()
                list.append(a)

        return list

def read_film2(movie):
    directory = os.path.normpath("film/")
    list=[]
    for subdir, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith(".txt"):
                f=open(os.path.join(subdir, file),'r',encoding="ISO-8859-1")
                a = f.read()
                a=a.split("(")
                a = a[0].rstrip()
                if a==movie:
                    return file



def read_item(dosya):
    info_item = []
    count =0
    for a in dosya.readlines():
        info_item.append({})
        b=a[-38:]
        b = b.rstrip()

        b=b.split("|")

        a=a.split("|")
        info_item[count]["movieid"]=a[0]
        a[1]=a[1].split("(")
        a[1]=a[1][0]
        a[1]=a[1].rstrip()
        a[1] = a[1].upper()

        info_item[count]["moviename"]=a[1]
        info_item[count]["date"]=a[2]
        info_item[count]["link"]=a[3]
        info_item[count]["genre"]=b
        count+=1
    dosya.seek(0)
    return info_item

def read_genre(dosya):
    genre=[[] for a in range(2)]

    for s in dosya.readlines():
        s = s.split("|")
        s[1] = s[1].rstrip()
        genre[0].append(s[0])
        genre[1].append(s[1])
    dosya.seek(0)
    return genre

def read_user(dosya):
    user = []

    count = 0
    for b in dosya:
        b=b.strip("\n")
        b=b.split("|")
        user.append({})
        user[count]["userid"] =b[0]
        user[count]["userage"] =b[1]
        user[count]["usergender"] = b[2]
        user[count]["useroccupation_id"] =b[3]
        user[count]["userzip_code"] = b[4]
        count += 1
    dosya.seek(0)
    return user

def read_occupation(dosya):
    occupation = []
    count = 0
    for f in dosya:
        occupation.append({})
        f=f.strip("\n")
        f=f.split("|")
        occupation[count]["occupation_id"] = f[0]
        occupation[count]["occupation_job"] = f[1]
        count += 1
    dosya.seek(0)
    return occupation

def read_data(dosya):
    data = []
    count = 0
    for g in dosya.readlines():
        data.append({})
        gg=g.split()
        data[count]["user_id"] = gg[0]
        data[count]["movie_id"] = gg[1]
        data[count]["rating"] = gg[2]
        data[count]["timestamp"] = gg[3]
        count += 1
    dosya.seek(0)

    return data
def ortak_film():
    birinci_liste=read_film()
    ikinci_liste1=read_item(item)
    ortak_liste=[]
    for a in birinci_liste:

        for b in ikinci_liste1:
            if a==b["moviename"]:
                ortak_liste.append(b)
    return ortak_liste
def write_rewiew():
    ortakliste22=ortak_film()
    ikinci_liste=read_item(item)
    review=open("review.txt","w",encoding="ISO-8859-1")

    exist=0
    for a in ikinci_liste:
        for b in ortakliste22:
            if a["moviename"]==b["moviename"]:
                review.write(a["movieid"]+"  "+a["moviename"]+"  "+" is found in folder"+"\n")
                exist=1
                break
            else:
                exist=0
        if exist==0:
            review.write(a["movieid"] + "  " + a["moviename"] + " is not found in folder. Look at " + a["link"] + "\n")




def html_file():
    ortliste=ortak_film()
    genrelist=read_genre(genre)
    datalist = read_data(data)
    userlist = read_user(user)
    occupationlist = read_occupation(occupation)
    for a in ortliste:
        genre1=[]
        for i in range(19):
            if a["genre"][i]=="1":
                genre1.append(genrelist[0][i])
        totaluser = 0
        totalrate = 0
        html = ""
        for b in range(len(datalist)):
            if(datalist[b]["movie_id"] == a["movieid"]):
                totaluser += 1
                userrate = datalist[b]["rating"]
                totalrate += int(userrate)
                userreviwed = datalist[b]["user_id"]
                for c in range(len(userlist)):
                    if(userlist[c]["userid"] == userreviwed):
                        userage = userlist[c]["userage"]
                        useroccupationid = userlist[c]["useroccupation_id"]
                        usergender = userlist[c]["usergender"]
                        userzip_code = userlist[c]["userzip_code"]
                        for d in range(len(occupationlist)):
                            if(occupationlist[d]["occupation_id"] == useroccupationid):
                                useroccupation = occupationlist[d]["occupation_job"]
                                html += "<br><b>User: </b>"+userreviwed  
                                html += "<b> Rate: </b>"+userrate+"<br>" 
                                html += "<b>User Detail: </b>" 
                                html += "<b>  Age: </b>"+userage 
                                html += "<b>  Gender: </b>"+usergender 
                                html += "<b>  Occupation: </b>"+useroccupation
                                html += "<b>  Zip Code: </b>"+userzip_code

        totalrate /= totaluser


        f=open("filmList/"+a["movieid"]+".html","w",encoding="ISO-8859-1")

        f.write("<html><title> "+a['moviename']+"</title><body>")
        f.write("<font font='' <b='' color='red' size='' face='Times New Roman'>"+a['moviename']+" </font><br>")
        genres =""
        for c in genre1:
            genres += c +  "  "
        f.write("<b>Genre:</b> "+genres + "<br>" )

        f.write("<b>IMDB Link:</b> <a href='" + a["link"] + "'>"+a['moviename']+"</a><br>")
        file=read_film2(a['moviename'])
        file=open("film/"+file,"r",encoding="ISO-8859-1")

        f.write("<font font='' color='black' size='4' face='Times New Roman'><b>Review: </b><br>" + file.read()+ "</font><br><br>")


        f.write("<b>Total User:</b> "+str(totaluser)+" /")
        f.write(" <b>Total Rate:</b> "+str(totalrate)+"<br><br>" )
        f.write(" <b>User who rate this film:</b>" )
        f.write(html)

def readstop(dosya):
    list=[]
    for a in dosya.readlines():
        a=a.rstrip("\n")
        list.append(a)
    return list

def genrre_to_words2():
    ortliste=ortak_film()
    genrelist=read_genre(genre)
    dic={}
    liste=[]
    for a in ortliste:
        genre1=[]
        for i in range(19):
            if a["genre"][i]=="1":
                genre1.append(genrelist[0][i])
        file = read_film2(a['moviename'])
        file = open("film/" + file, "r",encoding="ISO-8859-1")
        text = ""
        for c in genre1:

            liste.append(c)

            for d in file.readlines():
                text+=d

            dic[c]=text.split()
        newliste_of_genre=set(liste)

    return dic
def genrre_to_words():
    ortliste=ortak_film()
    genrelist=read_genre(genre)
    dic={}
    liste=[]
    newliste_of_genre5=[]
    for a in ortliste:
        genre1=[]
        for i in range(19):
            if a["genre"][i]=="1":
                genre1.append(genrelist[0][i])
        file = read_film2(a['moviename'])
        file = open("film/" + file, "r",encoding="ISO-8859-1")
        text = ""
        for c in genre1:

            liste.append(c)

            for d in file.readlines():
                text+=d

            dic[c]=text.split()
        newliste_of_genre5=set(liste)

    return newliste_of_genre5
def liste_yap():
    newliste_of_genre4=genrre_to_words()
    liste=[]
    for a in newliste_of_genre4:
        liste.append(a)
    new_fix=sorted(liste)
    return new_fix
def complie():
    unquıe_word={}
    stop_words=readstop(stoptext)
    dicno=genrre_to_words2()
    newliste_of_genre3=genrre_to_words()
    text=[]
    listeetexte=[]
    for b in newliste_of_genre3:

        for c in range(len(dicno[b])):

            for a in range(len(stop_words)):

                if stop_words[a]==dicno[b][c]:

                    text.append(stop_words[a])
        text1=set(text)
        for v in text1:
            listeetexte.append(v)

        unquıe_word[b]=listeetexte
        text=[]
        listeetexte=[]
        text1=set()


    return  unquıe_word





def read_filmGuess():
    text=[]
    dic = {}

    directory = os.path.normpath("filmGuess/")
    for subdir, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith(".txt"):
                f=open(os.path.join(subdir, file),'r',encoding="ISO-8859-1")

                a = f.read()
                a=a.rstrip("\n")

                text.append(a)
    for film in text:
        find = film.index("(")
        film_name = film[0:find - 1]
        dic[film_name] = film.split()


    return dic
def name_of_filmGuess():
    text=[]
    liste=[]

    directory = os.path.normpath("filmGuess/")
    for subdir, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith(".txt"):
                f=open(os.path.join(subdir, file),'r',encoding="ISO-8859-1")

                a = f.read()
                a=a.rstrip("\n")

                text.append(a)
    for film in text:
        find = film.index("(")
        film_name = film[0:find - 1]
        liste.append(film_name)


    return liste

def result():
    film_content=read_filmGuess()
    name_of_film=name_of_filmGuess()
    unqıue_words=complie()
    newliste_of_genre2=liste_yap()
    sozluk={}
    sayı_listesi=[]
    exist=[]

    for a in name_of_film:
        for b in newliste_of_genre2:

            kesısım=set(film_content[a]) & set(unqıue_words[b])
            sayı_listesi.append(len(kesısım))

        exist.append(sayı_listesi)
        sayı_listesi=[]
    for c in range(len(exist)):
        sozluk[name_of_film[c]]=exist[c]

    return sozluk

def yazdır():
    nt=result()
    text="Guess Genres of Movie based on Movies"+"\n"
    name_of_film = name_of_filmGuess()
    newliste_of_genre1 = liste_yap()

    for a in name_of_film:
        text+=a+ " "
        for b in range(len(nt[a])):
            if int(nt[a][b])>=20:
                text +=  newliste_of_genre1[b]+" "

            else:
                pass
        text+="\n"
    kayıt.write(text)





html_file()
write_rewiew()
yazdır()