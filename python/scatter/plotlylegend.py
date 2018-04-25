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
            x_axis = df1.iloc[:20, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]


            y_axis = df1.iloc[:20, a+ 1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]

            z_axis = df1.iloc[10:20, a + 1].values.tolist()
            # make them positve
            z_label = df1.columns[a + 1]

            marker = random.choice(mark)

            # Create a trace
            trace = go.Scatter(
                x = x_axis,
                y = y_axis,
                name=x_label
            )

            trace2 = go.Scatter(
                x=z_axis,
                y=x_axis,
                name=y_label
            )

            data = [trace,trace2]

            #layout = go.Layout(title=graph_name, width=640, height=480, showlegend=True)

            layout = go.Layout(showlegend=True)

            fig = go.Figure(data=data, layout=layout)

            plot_url = py.plot(fig, filename='legend-visibility')

            py.image.save_as(fig, filename= 'scatter_plot' + 'plotyleg' + x_label + '.jpg')


            Image('scatter_plot' + 'legg' + x_label + '.jpg')