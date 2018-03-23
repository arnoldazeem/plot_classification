import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname))
    graph_name = (os.path.basename(fname))

    # gets you only the numeric data
    #df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    listhem = []

    final = df1.columns[-1]

    for fname in glob.glob(path):
        df = (pd.read_csv(fname))
        graph_name = (os.path.basename(fname))

        # gets you only the numeric data
        # df1 = df.select_dtypes(['number'])
        df1 = df._get_numeric_data()

        df2 = df1.iloc[:20]

        final = df2.columns[-1]

        for a, b in enumerate(df2):

            if (df2.columns.values[a] != final):

                sns.boxplot(x=df2.columns.values[a], data=df2, hue=df2.columns.values[a])


                plt.legend(bbox_to_anchor=(1.05, 1), loc=2, borderaxespad=0.);

                plt.savefig(
                        '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter'+ 'seaborn' +  df1.columns.values[
                            a] + '.jpg')
                plt.show()



