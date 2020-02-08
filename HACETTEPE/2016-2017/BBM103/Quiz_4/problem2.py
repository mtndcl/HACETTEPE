import sys
file_input=sys.argv[1]
def calculateTotalCost(list):
    resultList = [0 for i in range(5)]
    for i in range(5):
        resultList[i] += float(list[i][0])*10*float(list[i][2]) + float(list[i][1])*10+ float(list[i][0])    
    return resultList

def displayCosts(list):
    displayList = calculateTotalCost(list)
    for i in range(5):
        print("the total cost of each house :")
        print("{}. House total cost is {}".format(i+1,displayList[i]) )

def selectBestBuy(list):
    BestBuyList = calculateTotalCost(list)

    min = float(BestBuyList[0])
    index = 1
    for i in range(5):
        if (float(BestBuyList[i]) < min):
            min = BestBuyList[i]
            index = i+1
    print("Best option is {}. house which costs {}".format(index, min))
    

BulletList=[[0 for i in range(3)] for j in range(5)]
x = 0
y = 0
file=open(file_input,"r")
for i in file.readlines():
    i = i.split(" ")
    i[-1] = i[-1].strip()
    for j in i:
        BulletList[x][y] = j
        y += 1
    y = 0
    x += 1

displayCosts(BulletList)
selectBestBuy(BulletList)


