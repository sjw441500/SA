#-*- coding: utf-8 -*-
import xlrd
from xlutils.copy import copy
import matplotlib.pyplot as plt
color=['#FFFF00','#FFDEAD','#FF6A6A','#FF34B3','#EEC591','#DB7093','#BFEFFF','#AB82FF','#98FB98','#36648B']
import re
def get_from_xls(filepath, indexOfsheet, start_row, art_column):
     excel = xlrd.open_workbook(filepath)
     sheet = excel.sheets()[indexOfsheet]

     i = start_row
     row_content = []
     print (sheet.nrows)
     while i < sheet.nrows:
        if(len(sheet.cell(i, art_column).value) > 1):
            row_content.append(sheet.cell(i, art_column).value)
        else:
            row_content.append("9999")
        i += 1
     return row_content

def draw_line(data, time):
        figure = plt.figure(1)
        plt.subplot(513)
        plt.title('english data')
        plt.plot(data)
        plt.xticks(range(len(time)),time)
        plt.savefig("./images/base_count_eg")

def write_xls(filepath, indexOfsheet, start_row, art_column, content):
    print(len(content))
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


if __name__ == '__main__':
    #get airticle
    years = [2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015]
    print years
    sum_each_year = [0,0,0,0,0,0,0,0,0,0,0,0]
    yearsToW = []
    time = get_from_xls("./xls/base_eg.xls", 0, 1, 1)
    pattern = re.compile(r'\d\d\d\d')
    for one in time:
            match = pattern.search(one)
            if match:
                year = int(match.group())
                yearsToW.append(year)
                i = 0
                for oneyear in years:
                    if year == oneyear:
                        sum_each_year[i] += 1
                        break
                    i += 1

            else:
                yearsToW.append("9999")
    draw_line(sum_each_year, years)
    write_xls("./xls/base_eg.xls", 0, 1, 1, yearsToW)
