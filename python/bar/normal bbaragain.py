import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
import  numpy as np

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))


    df1 = df._get_numeric_data()

    final = df1.columns[-1]

    for a, b in enumerate(df1):




        if (df1.columns.values[a] != final):
            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:5, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]


            y_axis = df1.iloc[:5, a+1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]

            city = [x_label, y_label, 'Washington', 'Tokyo', 'Moscow']

            pos = np.arange(len(city))

            # plot

            plt.bar(pos, y_axis, color='blue', edgecolor='black')

            plt.yticks(pos, city)

            plt.title(graph_name)

            plt.xlabel(x_label)

            plt.ylabel(y_label)

            plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/bar/bar data' + 'normalhhorgain' + x_label + '.jpg')


            plt.show()

        else:
            print('done')
            # end


