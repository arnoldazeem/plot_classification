import seaborn as sns; sns.set(color_codes=True)
import matplotlib.pyplot as plt
import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))
    print(graph_name)

    # gets you only the numeric data
    # df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    df2 = df1.iloc[:20]

    final = df2.columns[-1]

    for a, b in enumerate(df2):

        if (df2.columns.values[a] != final):

            #sns.lmplot(x=x_label, y=y_label, data=df2, palette='coolwarm')
            sns.lmplot(x=df2.columns.values[a],y=df2.columns.values[a + 1], data=df2, fit_reg=False,hue=df2.columns.values[a])

            plt.title(graph_name)

            plt.savefig(
                '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/' + 'lmplotplotnofitcolor' +
                df1.columns.values[
                    a] + '.jpg')

            plt.show()

        else:
            print('done')







