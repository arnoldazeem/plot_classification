import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
import seaborn as sns

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/data/*.csv"

for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))
    # gets you only the numeric data
    df1 = df._get_numeric_data()
    listhem = []
    final = df1.columns[-1]

    df2 = df1.iloc[:2]

    final = df2.columns[-1]

    for a, b in enumerate(df2):

        if (df2.columns.values[a] != final):

            x_label = df2.columns[a]

            y_label = df2.columns[a+1]



            sns.boxplot(x=x_label,y=y_label, data=df2,palette='coolwarm')

            plt.xlabel(x_label)
            plt.ylabel(y_label)

            plt.title(graph_name)

            plt.savefig(
                    '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/'+ 'seaborboxagain' +  df2.columns.values[
                        a] + '.jpg')
            plt.show()

