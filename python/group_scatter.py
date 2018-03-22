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

    final = df1.columns[-1]


    for a,b in enumerate(df1):

        if (df1.columns.values[a] != final):

            sns.lmplot(x=df1.columns.values[a], y = df1.columns.values[a+1], data=df1, fit_reg=False)

            plt.legend(loc='best')
            plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter' + df1.columns.values[a]  + '.jpg')
            sns.plt.show()









