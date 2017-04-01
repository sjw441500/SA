# -*- coding:utf-8 -*-
from __future__ import print_function
import matplotlib.pyplot as plt
color=['#FFFF00','#FFDEAD','#FF6A6A','#FF34B3','#EEC591','#DB7093','#BFEFFF','#AB82FF','#98FB98','#36648B']
import xlrd
from xlutils.copy import copy
from sklearn.cluster import KMeans
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
import jieba
def get_tfidf(row_content, stop_words):
    vectorizer = TfidfVectorizer(min_df=1, stop_words=stop_words)
    X = vectorizer.fit_transform(row_content)
    words = vectorizer.get_feature_names()
    return X, words
def loadStopword():
    stopwords=[str(line) for line in open('./plugin/cluster_cn/stopword.txt').readlines()]
    #stopwords=[str(line) for line in open('stopword.txt').readlines()]
    return stopwords
def transform(dataset,n_features=1000,stopwords='english'):
    vectorizer = TfidfVectorizer(max_df=0.5, max_features=n_features,stop_words=stopwords, min_df=2,use_idf=True)
    X = vectorizer.fit_transform(dataset)
    return X,vectorizer

def cluster_xls(numOfclass, X, words):

    number = int(numOfclass)
    km = KMeans(n_clusters=numOfclass, init='k-means++', max_iter=1000, n_init=10, verbose=False)
    #print (X.toarray()[0])
    km.fit(X)
    order_centroids = km.cluster_centers_.argsort()[:, ::-1]
    keywords = []
    for i in range(numOfclass):
        temp = []
        for ind in order_centroids[i, :10]:
            temp.append(words[ind])
        keywords.append(temp)

    #for i in range(numOfclass):
        #for j in range(0,10):
            #keywords[i][j] = keywords[i][j].encode('ascii')
    #print(keywords)
    return km.labels_, keywords

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

def train(X,vectorizer,true_k=10,minibatch = False,showLable = False):
    if minibatch:
        km = MiniBatchKMeans(n_clusters=true_k, init='k-means++', n_init=1,
                             init_size=1000, batch_size=1000, verbose=False)
    else:
        km = KMeans(n_clusters=true_k, init='k-means++', max_iter=300, n_init=1,
                    verbose=False)
    km.fit(X)
    term_name=[]
    if showLable:
        #print("Top terms per cluster:")
        order_centroids = km.cluster_centers_.argsort()[:, ::-1]
        terms = vectorizer.get_feature_names()
        #print (vectorizer.get_stop_words())
        for i in range(true_k):
            #print("Cluster %d:" % i, end='')
            for ind in order_centroids[i, :10]:
                #print(' %s' % terms[ind], end='')
                term_name.append(terms[ind])
            print()
    result = list(km.predict(X))

    #print ('Cluster distribution:')
    count=dict([(i, result.count(i)) for i in result])
    #print (count)
    return -km.score(X),result,count,term_name

def get_cluster_number(dataset):
    stop=loadStopword()
    true_ks = []
    scores = []
    slop=[]
    X,vectorizer = transform(dataset,n_features=500,stopwords=stop)
    for i in xrange(1,40,1):
        score,result,c,t= train(X,vectorizer,true_k=i)
        score=score/len(dataset)
        #print (i,score)
        true_ks.append(i)
        if len(scores)==0:
            scores.append(1)
        scores.append(score)
        slop.append(scores[i]-scores[i-1])
    plt.figure(figsize=(8,4))
    #print(true_ks)
    #print(scores)
    #print(slop)
    del scores[0]
    plt.plot(true_ks,scores,label="error",color="red",linewidth=1)
    plt.xlabel("n_features")
    plt.ylabel("error")
    plt.legend()
    #plt.savefig('./images/kmeans_cn.png')
    plt.savefig('kmeans_cn.png')
    maxslop=max(slop)
    pos=slop.index(maxslop)
    return int(true_ks[pos])

def draw_cluster_totle(sum_of_article, numb):
    global color
    totle=[]
    bars=[]
    clusterName = [i for i in range(1,numb+1)]
    data_value = sum_of_article
    while len(color)< len(clusterName):
        color+=color
    plt.xlabel(u'class')
    plt.ylabel(u'number')
    plt.plot()
    plt.figure()
    plt.bar(left=range(0,len(clusterName)*2,2),height=data_value,width=1,color=color[2], linewidth=0, align='center')
    plt.xticks(range(0,len(clusterName)*2,2),tuple(clusterName))
    plt.savefig('./images/cluster_show_cn.png')
    #plt.savefig('cluster_show_cn.png')
stopword=loadStopword()
article = get_from_xls("./xls/input_cn.xls", 0, 1, 0)
#article = get_from_xls("input_cn.xls", 0, 1, 0)
#get the number
numb = get_cluster_number(article)
print(numb)
X, words = get_tfidf(article, stop_words=stopword)
result, keywords = cluster_xls(numb, X, words)
#print(result)


write_xls("./xls/input_cn.xls",0,1,1,result)
#write_xls("input_cn.xls",0,1,1,result)
write_xls("./xls/input_cn.xls",0,1,2,keywords)
#write_xls("input_cn.xls",0,1,2,keywords)
#count
sum_of_article = [0] * numb
percent_of_article = [0] * numb
for one in result:
    sum_of_article[one-1]  = sum_of_article[one-1] + 1
print(sum_of_article)
for i in range(numb):
    percent_of_article[i] = round(sum_of_article[i]*100.0 / len(article), 1)
print(percent_of_article)
write_xls("./xls/input_cn.xls",0,0,1,numb)
#write_xls("input_cn.xls",0,0,1,numb)
write_xls("./xls/input_cn.xls",0,1,3,sum_of_article)
#write_xls("input_cn.xls",0,1,3,sum_of_article)
write_xls("./xls/input_cn.xls",0,1,4,percent_of_article)
#write_xls("input_cn.xls",0,1,4,percent_of_article)
#print('write all data in input_cn.xls')
draw_cluster_totle(percent_of_article, numb)
#print('draw picuture')