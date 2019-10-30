import numpy as np
import plotly.offline as ply
import plotly.graph_objs as go
import pandas as pd

def import_csv(fname):
    with open(f"../data/{fname}") as csv_file:
        return pd.read_csv(csv_file)


size_df = import_csv("sizes.csv")
edge_df = import_csv("edges.csv")


x_ax1 = np.asarray(size_df["Sizes"])
x_ax2 = np.asarray(edge_df[" Edges"])

fig = go.Figure()

fig.add_trace(
    go.Scatter(x=x_ax1,
               y=size_df[" CPU time"],
               name="Size-graph",
               fill='tozeroy'))

fig.add_trace(
    go.Scatter(x=x_ax2,
               y=edge_df[" CPU time"],
               name="Edge-graph",
               visible=False,
               line_color='red',
               fill='tozeroy')
)

fig.update_layout(
    updatemenus=[
        go.layout.Updatemenu(
            type="buttons",
            direction="right",
            active=0,
            x=0.57,
            y=1.2,
            buttons=list([
                dict(label="Sizes",
                     method="update",
                     args=[{"visible": [True, False]},
                           {"title": "CPU time vs Graph size",
                            "annotations": [],
                            "xaxis": dict(title="# Nodes",
                                          showexponent='all',
                                          exponentformat='power')}]),
                dict(label="Edges",
                     method="update",
                     args=[{"visible": [False, True]},
                           {"title": "CPU Time vs # edges",
                            "annotations": [],
                            "xaxis": dict(title="# Edges",
                                          showexponent='all',
                                          exponentformat='power')}]),
            ]),
        )
    ])


fig.update_layout(title_text="CPU time vs Graph size",
                  xaxis=dict(title="# Nodes",
                             showexponent='all',
                             exponentformat='power'),
                  yaxis=dict(title="CPU time (ns)",
                             showexponent='all',
                             exponentformat='power'))

ply.plot(fig, filename='results.html')