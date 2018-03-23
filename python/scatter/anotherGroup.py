import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import pylab


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/*.csv"

def plotfunction(datas,groups,colors,name):

    x,y,z = datas
    plt.scatter(x,y)
    plt.title(name)
    pylab.scatter(x,y)
    plt.xlabel(x_label)
    plt.ylabel(y_label)
    plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter' + name + '.jpg')
    pylab.show()



col_1 = 0

for fname in glob.glob(path):
    df = (pd.read_csv(fname))
    graph_name = (os.path.basename(fname))

    #gets you only the numeric data
    df1 = df.select_dtypes(['number'])
    #df1 = df._get_numeric_data()

    for a in df1:

        if (df1.columns[-1] != df1.columns[col_1]):
            #check to see if it numeric
            x_axis = df.iloc[:10, col_1].values.tolist()
            x_label = df.columns[col_1]
            col_2 = col_1 + 1

            if (df1.columns[-1] != df1.columns[col_2]):
                y_axis = df.iloc[:10, col_2].values.tolist()
                y_label = df.columns[col_2]
                col_3 = col_2 + 1

                if (df1.columns[-1] != df1.columns[col_3]):
                    z_axis = df.iloc[:10, col_3].values.tolist()
                    z_label = df.columns[col_3]
                    col_3 = col_2 + 1

                    #plot
                    data = (x_axis,y_axis,z_axis)

                    groups = (x_label,y_label,z_label)

                    colors = ("red","green","blue")

                    plotfunction(data,groups,colors,y_label)




                else:
                    print('check')
                #plot


