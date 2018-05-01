import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns

print(sns.__version__)

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))
    print(fname)
    # gets you only the numeric data
    #df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    final = df1.columns[-1]


    for a,b in enumerate(df1):

        if (df1.columns.values[a] != final):
            mark = ['o', '*', '.', '+', 'x']
            marker = random.choice(mark)

            sns.lmplot(x=df1.columns.values[a], y = df1.columns.values[a+1], data=df1, fit_reg=False, markers=marker)

            plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter' + 'seaborn'  + df1.columns.values[a]  + '.jpg')
            plt.show()









