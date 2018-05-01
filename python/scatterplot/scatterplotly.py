import matplotlib.pyplot as plt
import numpy as np

import plotly.plotly as py
import plotly.tools as tls

# Learn about API authentication here: https://plot.ly/python/getting-started
# Find your api_key here: https://plot.ly/settings/api



import plotly.plotly as py
import plotly.graph_objs as go
import numpy as np
import glob
import pandas as pd
import os
import random
from IPython.display import Image
#kumar05
#IW5WKDoNWeWWon8haT7r

#plotly.tools.set_credentials_file(username='zanzy', api_key='6tzTYqsLP9zeG91eamft')
# Replace the username, and API key with your credentials.

#py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r')

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"

py.sign_in('zanzy', '6tzTYqsLP9zeG91eamft')


colors = ['b', 'c', 'y', 'm', 'r']



for fname in glob.glob(path):
    print(fname)
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    df1 = df._get_numeric_data()
    graph_name = (os.path.basename(fname))
    mark = ['o', '*', '.', '+','x']

    final = df1.columns[-1]

    # whole of the csv file
    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):

            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:10, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]


            s_axis = df1.iloc[10:20, a].values.tolist()

            print(len(x_axis))

            y_axis = df1.iloc[:10, a+ 1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]

            w_axis = df1.iloc[10:20, a + 1].values.tolist()


            marker = random.choice(mark)

            lo = plt.scatter(x_axis, y_axis, marker='x', color=colors[0])
            ll = plt.scatter(x_axis, s_axis, marker='o', color=colors[0])
            l = plt.scatter(y_axis, s_axis, marker='*', color=colors[1])
            a = plt.scatter(s_axis, w_axis, marker='.', color=colors[2])
            h = plt.scatter(w_axis, y_axis, marker='+', color=colors[3])

            text = iter([x_label, y_axis, 'Lo', 'Average', 'Hi', 'HiHi', 'High Outlier'])

            mpl_fig = plt.gcf()
            plotly_fig = tls.mpl_to_plotly(mpl_fig)

            for dat in plotly_fig['data']:
                t = text.__next__()
                dat.update({'name': t, 'text': t})

            plt.title(graph_name)
            plt.xlabel(x_label)
            plt.ylabel(y_label)

            plotly_fig['layout']['showlegend']  = True


            py.image.save_as(plotly_fig, filename= 'scatter_plot' + 'plotylegenda' + x_label + '.jpeg')


            Image('scatter_plot' + 'legg' + x_label + '.jpeg')



