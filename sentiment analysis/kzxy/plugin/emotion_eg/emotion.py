# -*- coding:utf-8 -*-
from __future__ import print_function
import matplotlib.pyplot as plt
color=['#FFFF00','#FFDEAD','#FF6A6A','#FF34B3','#EEC591','#DB7093','#BFEFFF','#AB82FF','#98FB98','#36648B']
import xlrd
from xlutils.copy import copy
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.naive_bayes import MultinomialNB
def get_tfidf(row_content, stop_words):
    vectorizer = TfidfVectorizer(min_df=1, stop_words=stop_words)
    X = vectorizer.fit_transform(row_content)
    words = vectorizer.get_feature_names()
    return X.toarray(), words

def transform(dataset,n_features=1000,stopwords='english'):
    vectorizer = TfidfVectorizer(max_df=0.5, max_features=n_features,stop_words=stopwords, min_df=2,use_idf=True)
    X = vectorizer.fit_transform(dataset)
    return X,vectorizer

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

def naive_bayes_MNB(alpha, classes, know_dif,unknow_dif):
    clf = MultinomialNB(alpha).fit(know_dif, classes)
    result = clf.predict(unknow_dif)
    return result

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
    plt.savefig('./images/emotion_eg.png')

article = get_from_xls("./xls/input.xls", 0, 1, 0)
article_numbers = len(article)
#get train
train_article = get_from_xls("./xls/englishtrain.xls", 0, 0, 0)
#hebing
for one in train_article:
    article.append(one)
X, words = get_tfidf(article, "english")
#divide
i = 0
predict_X = []
train_X = []
for one in X:
    if i < article_numbers:
        predict_X.append(one.tolist())
    else:
        train_X.append(one.tolist())
    i += 1

classes = get_from_xls("./xls/englishtrain.xls", 0, 0, 1)
classesname = ["pos", "neg"]
result = naive_bayes_MNB(0.65, classes, train_X, predict_X)
write_xls("./xls/input.xls",0, 1, 5, result)
pos = 0
neg  = 0
for one in result:
    if one == 'pos':
        pos = pos + 1
    else :neg = neg +1
draw_cluster_totle([pos,neg], 2, classesname)
