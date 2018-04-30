import seaborn as sns
import matplotlib.pyplot as plt

sns.set(style="ticks")


# Load the example tips dataset
#tips = sns.load_dataset("tips")
iris = sns.load_dataset("iris")


ax = sns.boxplot(data=iris, palette="Set2")


plt.savefig(
    '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/' + 'irinseaborn'  + '.jpg')
