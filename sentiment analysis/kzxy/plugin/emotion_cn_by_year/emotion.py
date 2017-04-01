# -*- coding:utf-8 -*-
from __future__ import print_function
import matplotlib.pyplot as plt
color=['#FFFF00','#FFDEAD','#FF6A6A','#FF34B3','#EEC591','#DB7093','#BFEFFF','#AB82FF','#98FB98','#36648B']
import xlrd

def get_from_xls(filepath, indexOfsheet, start_row, art_column):
     excel = xlrd.open_workbook(filepath)
     sheet = excel.sheets()[indexOfsheet]

     i = start_row
     row_content = []
     print (sheet.nrows)
     while i < sheet.nrows:
         row_content.append(sheet.cell(i, art_column).value)
         i += 1
     return row_content

def draw_cluster_totle(sum_of_article, numb, classesname):
    global color
    totle=[]
    bars=[]
    clusterName = classesname
    data_value = sum_of_article
    while len(color)< len(clusterName):
        color+=color
    plt.xlabel(u'class')
    plt.ylabel(u'number')
    plt.plot()
    plt.figure()
    plt.bar(left=range(0,len(clusterName)*2,2),height=data_value,width=1,color=color[2], linewidth=0, align='center')
    plt.xticks(range(0,len(clusterName)*2,2),tuple(clusterName))
    plt.savefig('./images/emotion_cn_by_year')

time = get_from_xls("./xls/base.xls", 0, 1, 1)
emotion = get_from_xls("./xls/input_cn.xls", 0, 1, 5)
sum_of_article = [0, 0, 0, 0, 0, 0]
nrows = len(time)
for i in range(0,nrows):
    if int(time[i]) < 2009:
        if emotion[i] == "pos":
            sum_of_article[0] += 1
        else:
            sum_of_article[1] += 1
    elif int(time[i]) < 2013:
        if emotion[i] == "pos":
            sum_of_article[2] += 1
        else:
            sum_of_article[3] += 1
    elif int(time[i]) < 2016:
        if emotion[i] == "pos":
            sum_of_article[4] += 1
        else:
            sum_of_article[5] += 1
classesname = ["04-08pos", "04-08neg", "09-12pos", "09-12neg", "13-15pos", "13-15neg"]
draw_cluster_totle(sum_of_article, 6, classesname)
