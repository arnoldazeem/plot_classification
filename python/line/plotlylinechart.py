import plotly.plotly as py
import plotly.graph_objs as go
import numpy as np
import glob
import pandas as pd
import os
import random


#kumar05
#IW5WKDoNWeWWon8haT7r

#plotly.tools.set_credentials_file(username='zanzy', api_key='6tzTYqsLP9zeG91eamft')
# Replace the username, and API key with your credentials.

#py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r')

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"

py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r')

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
            x_axis = df1.iloc[:5, a].values.tolist()

            w_axis = df1.iloc[5:10, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]


            y_axis = df1.iloc[:5, a+ 1].values.tolist()

            z_axis = df1.iloc[5:10, a].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]


            marker = random.choice(mark)

            # Create a trace
            trace = go.Scatter(
                x = x_axis,
                y = y_axis,
                mode = 'lines+markers',
                name = x_label
            )

            # Create a trace
            trace2 = go.Scatter(
                x=z_axis,
                y=w_axis,
                mode='lines+markers',
                name=y_label
            )

            # Create a trace
            trace3 = go.Scatter(
                x=y_axis,
                y=w_axis,
                mode='lines+markers',
                name=y_label
            )



            data = [trace, trace2,trace3]

            layout = go.Layout(width=640, height=480,title='Plot Title',
                xaxis=dict(
                    title='x Axis',
                    titlefont=dict(
                        family='Courier New, monospace',
                        size=18,
                        color='#7f7f7f'
                    )
                ),
                yaxis=dict(
                    title='y Axis',
                    titlefont=dict(
                        family='Courier New, monospace',
                        size=18,
                        color='#7f7f7f'
                    )
                )
            )

            fig = go.Figure(data=data, layout=layout)

            py.image.save_as(fig, filename= 'scatter_plot' + 'plotylinechartthripple' + x_label + '.jpeg')

            from IPython.display import Image

            Image('scatter_plot' + '1' + x_label + '.jpeg')