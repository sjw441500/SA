# -*- coding: cp936 -*-
from __future__ import print_function
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import HashingVectorizer
import matplotlib.pyplot as plt
from sklearn import svm
import jieba
import xlrd
import xlwt
from xlutils.copy import copy
color=['#FFFF00','#FFDEAD','#FF6A6A','#FF34B3','#EEC591','#DB7093','#BFEFFF','#AB82FF','#98FB98','#36648B']
def loadStopword():
    stopwords=[str(line) for line in open('./plugin/emotion_cn/stopword.txt').readlines()]
    #stopwords=[str(line) for line in open('stopword.txt').readlines()]
    return stopwords
def loadTrainset():
    stopwords=loadStopword()
    ff=xlrd.open_workbook('./xls/trainset_cn.xlsx')
    #ff=xlrd.open_workbook('trainset_cn.xlsx')
    table=ff.sheets()[0]
    nrows = table.nrows
    ncols = table.ncols
    dataset=[]
    Y=[]
    for i in range(nrows):
        lst=table.row_values(i)
        content=lst[1]
        emotion=lst[4]
        cut_word=jieba.cut(content,cut_all=False)
        stop_cut_word=[i for i in cut_word if i not in stopwords]
        wordstr=" ".join(stop_cut_word)
        dataset.append(wordstr)
        Y.append(emotion)
    return dataset,Y

def transform(dataset,n_features=1000,stopwords='english'):
    vectorizer = TfidfVectorizer(max_df=0.5, max_features=n_features,stop_words=stopwords, min_df=2,use_idf=True)
    X = vectorizer.fit_transform(dataset)
    return X,vectorizer

def train(X,Y):
        clf=svm.SVC()
        clf.fit(X, Y)
        return clf
def test():
    dataset = loadDataset()
    dataset1=[data['feature'] for data in dataset]
    trainset,Y=loadTrainset()
    stop_words=loadStopword()
    print("%d documents" % len(dataset))
    X1,vectorizer = transform(trainset,n_features=500,stopwords=stop_words)
    X2,_=           transform(dataset1,n_features=500,stopwords=stop_words)
    clf=train(X1,Y)
    result=clf.predict(X2)
    for data in dataset:
        index=dataset.index(data)
        data['emotion']=result[index]
    write_data_emotion(dataset)

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
    plt.savefig('./images/emotion_cn.png')
    #plt.savefig('emotion_cn.png')

def get_from_xls(filepath, indexOfsheet, start_row, art_column):
    stopwords=loadStopword()
    excel = xlrd.open_workbook(filepath)
    sheet = excel.sheets()[indexOfsheet]
    i = start_row
    row_content = []
    print (sheet.nrows)
    while i < sheet.nrows:
        content=sheet.cell(i, art_column).value
        cut_word=list(jieba.cut(content,cut_all=False))
        #print(cut_word)
        stop_cut_word=[m for m in cut_word if m not in stopwords]
        word=wordstr=" ".join(stop_cut_word)
        row_content.append(word)
        i += 1
    return row_content

def write_xls(filepath, indexOfsheet, start_row, art_column, content):
    xls_to_write = xlrd.open_workbook(filepath)
    excel_new  = copy(xls_to_write)
    sheet = excel_new.get_sheet(indexOfsheet)
    i = start_row
    if isinstance(content, int):
        sheet.write(i, art_column, str(content))
    else:
        for one in content:
            sheet.write(i, art_column, str(one))
            i += 1
    excel_new.save(filepath)


article = get_from_xls("./xls/input_cn.xls", 0, 1, 0)
#article = get_from_xls("input_cn.xls", 0, 1, 0)
article_numbers = len(article)
trainset,Y=loadTrainset()
stop_words=loadStopword()
X1,vectorizer = transform(trainset,n_features=500,stopwords=stop_words)
X2,_=           transform(article,n_features=500,stopwords=stop_words)
clf=train(X1,Y)
result=clf.predict(X2)
classesname = ["pos", "neg"]
write_xls("./xls/input_cn.xls",0, 1, 5, result)
#write_xls("input_cn.xls",0, 1, 5, result)
pos = 0
neg  = 0
for one in result:
    if one == 'pos':
        pos += 1
    else :neg += 1
draw_cluster_totle([pos,neg], 2, classesname)










        
    
